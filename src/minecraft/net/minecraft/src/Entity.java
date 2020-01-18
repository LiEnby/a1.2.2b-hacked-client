package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public abstract class Entity
{
    public boolean fly = false;
    
    public Entity(World world)
    {
        field_620_ab = field_864_a++;
        field_619_ac = 1.0D;
        field_618_ad = false;
        onGround_00 = false;
        field_597_ay = false;
        field_596_az = true;
        field_646_aA = false;
        yOffset_00 = 0.0F;
        width_00 = 0.6F;
        height_01 = 1.8F;
        field_4078_aJ = 0.0F;
        field_641_aF = 0.0F;
        entityWalks = true;
        fallDistance = 0.0F;
        field_653_b = 1;
        field_635_aL = 0.0F;
        field_634_aM = 0.0F;
        field_633_aN = false;
        field_632_aO = 0.0F;
        field_4077_aU = false;
        rand_05 = new Random();
        field_4076_aW = 0;
        field_628_aS = 1;
        fire_00 = 0;
        field_4075_aZ = 300;
        field_4081_ba = false;
        field_4080_bb = 0;
        air_00 = 300;
        field_862_c = true;
        field_4079_be = false;
        field_621_aZ = false;
        worldObj_09 = world;
        setPosition(0.0D, 0.0D, 0.0D);
    }

    public boolean equals(Object obj)
    {
        if(obj instanceof Entity)
        {
            return ((Entity)obj).field_620_ab == field_620_ab;
        } else
        {
            return false;
        }
    }

    public int hashCode()
    {
        return field_620_ab;
    }

    protected void func_374_q()
    {
        if(worldObj_09 == null)
        {
            return;
        }
        do
        {
            if(posY <= 0.0D)
            {
                break;
            }
            setPosition(posX, posY, posZ);
            if(worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox).size() == 0)
            {
                break;
            }
            posY++;
        } while(true);
        motionX = motionY = motionZ = 0.0D;
        rotationPitch = 0.0F;
    }

    public void setEntityDead()
    {
        field_646_aA = true;
    }

    protected void setSize(float f, float f1)
    {
        width_00 = f;
        height_01 = f1;
    }

    protected void setRotation(float f, float f1)
    {
        rotationYaw = f;
        rotationPitch = f1;
    }

    public void setPosition(double d, double d1, double d2)
    {
        posX = d;
        posY = d1;
        posZ = d2;
        float f = width_00 / 2.0F;
        float f1 = height_01;
        boundingBox.setBounds(d - (double)f, (d1 - (double)yOffset_00) + (double)field_635_aL, d2 - (double)f, d + (double)f, (d1 - (double)yOffset_00) + (double)field_635_aL + (double)f1, d2 + (double)f);
    }

    public void func_346_d(float f, float f1)
    {
        float f2 = rotationPitch;
        float f3 = rotationYaw;
        rotationYaw += (double)f * 0.14999999999999999D;
        rotationPitch -= (double)f1 * 0.14999999999999999D;
        if(rotationPitch < -90F)
        {
            rotationPitch = -90F;
        }
        if(rotationPitch > 90F)
        {
            rotationPitch = 90F;
        }
        field_602_at += rotationPitch - f2;
        field_603_as += rotationYaw - f3;
    }

    public void onUpdate()
    {
        func_391_y();
    }

    public void func_391_y()
    {
        if(field_616_af != null && field_616_af.field_646_aA)
        {
            field_616_af = null;
        }
        field_4076_aW++;
        field_4078_aJ = field_641_aF;
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        field_602_at = rotationPitch;
        field_603_as = rotationYaw;
        if(handleWaterMovement())
        {
            if(!field_4081_ba && !field_862_c)
            {
                float f = MathHelper.sqrt_double(motionX * motionX * 0.20000000298023224D + motionY * motionY + motionZ * motionZ * 0.20000000298023224D) * 0.2F;
                if(f > 1.0F)
                {
                    f = 1.0F;
                }
                worldObj_09.playSoundAtEntity(this, "random.splash", f, 1.0F + (rand_05.nextFloat() - rand_05.nextFloat()) * 0.4F);
                float f1 = MathHelper.convertToBlockCoord_00(boundingBox.minY);
                for(int i = 0; (float)i < 1.0F + width_00 * 20F; i++)
                {
                    float f2 = (rand_05.nextFloat() * 2.0F - 1.0F) * width_00;
                    float f4 = (rand_05.nextFloat() * 2.0F - 1.0F) * width_00;
                    worldObj_09.spawnParticle_00("bubble", posX + (double)f2, f1 + 1.0F, posZ + (double)f4, motionX, motionY - (double)(rand_05.nextFloat() * 0.2F), motionZ);
                }

                for(int j = 0; (float)j < 1.0F + width_00 * 20F; j++)
                {
                    float f3 = (rand_05.nextFloat() * 2.0F - 1.0F) * width_00;
                    float f5 = (rand_05.nextFloat() * 2.0F - 1.0F) * width_00;
                    worldObj_09.spawnParticle_00("splash", posX + (double)f3, f1 + 1.0F, posZ + (double)f5, motionX, motionY, motionZ);
                }

            }
            fallDistance = 0.0F;
            field_4081_ba = true;
            fire_00 = 0;
        } else
        {
            field_4081_ba = false;
        }
        if(fire_00 > 0)
        {
            if(field_4079_be)
            {
                fire_00 -= 4;
                if(fire_00 < 0)
                {
                    fire_00 = 0;
                }
            } else
            {
                if(fire_00 % 20 == 0)
                {
                    attackEntity(null, 1);
                }
                fire_00--;
            }
        }
        if(func_359_G())
        {
            func_4038_J();
        }
        if(posY < -64D)
        {
            func_4034_G();
        }
        field_862_c = false;
    }

    protected void func_4038_J()
    {
        if(!field_4079_be)
        {
            attackEntity(null, 4);
            fire_00 = 600;
        }
    }

    protected void func_4034_G()
    {
        setEntityDead();
    }

    public boolean func_403_b(double d, double d1, double d2)
    {
        AxisAlignedBB axisalignedbb = boundingBox.getOffsetBoundingBox(d, d1, d2);
        List list = worldObj_09.getCollidingBoundingBoxes_00(this, axisalignedbb);
        if(list.size() > 0)
        {
            return false;
        }
        return !worldObj_09.getIsAnyLiquid(axisalignedbb);
    }

    public void moveEntity(double d, double d1, double d2)
    {
        if(field_633_aN)
        {
            boundingBox.offset(d, d1, d2);
            posX = (boundingBox.minX_00 + boundingBox.maxX) / 2D;
            posY = (boundingBox.minY + (double)yOffset_00) - (double)field_635_aL;
            posZ = (boundingBox.minZ + boundingBox.maxZ) / 2D;
            return;
        }
        double d3 = posX;
        double d4 = posZ;
        double d5 = d;
        double d6 = d1;
        double d7 = d2;
        AxisAlignedBB axisalignedbb = boundingBox.copy();
        boolean flag = onGround_00 && func_381_o();
        if(flag)
        {
            double d8 = 0.050000000000000003D;
            for(; d != 0.0D && worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox.getOffsetBoundingBox(d, -1D, 0.0D)).size() == 0; d5 = d)
            {
                if(d < d8 && d >= -d8)
                {
                    d = 0.0D;
                    continue;
                }
                if(d > 0.0D)
                {
                    d -= d8;
                } else
                {
                    d += d8;
                }
            }

            for(; d2 != 0.0D && worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox.getOffsetBoundingBox(0.0D, -1D, d2)).size() == 0; d7 = d2)
            {
                if(d2 < d8 && d2 >= -d8)
                {
                    d2 = 0.0D;
                    continue;
                }
                if(d2 > 0.0D)
                {
                    d2 -= d8;
                } else
                {
                    d2 += d8;
                }
            }

        }
        List list = worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox.addCoord(d, d1, d2));
        for(int i = 0; i < list.size(); i++)
        {
            d1 = ((AxisAlignedBB)list.get(i)).func_1172_b(boundingBox, d1);
        }

        boundingBox.offset(0.0D, d1, 0.0D);
        if(!field_596_az && d6 != d1)
        {
            d = d1 = d2 = 0.0D;
        }
        boolean flag1 = onGround_00 || d6 != d1 && d6 < 0.0D;
        for(int j = 0; j < list.size(); j++)
        {
            d = ((AxisAlignedBB)list.get(j)).func_1163_a(boundingBox, d);
        }

        boundingBox.offset(d, 0.0D, 0.0D);
        if(!field_596_az && d5 != d)
        {
            d = d1 = d2 = 0.0D;
        }
        for(int k = 0; k < list.size(); k++)
        {
            d2 = ((AxisAlignedBB)list.get(k)).func_1162_c(boundingBox, d2);
        }

        boundingBox.offset(0.0D, 0.0D, d2);
        if(!field_596_az && d7 != d2)
        {
            d = d1 = d2 = 0.0D;
        }
        if(field_634_aM > 0.0F && flag1 && field_635_aL < 0.05F && (d5 != d || d7 != d2))
        {
            double d9 = d;
            double d11 = d1;
            double d13 = d2;
            d = d5;
            d1 = field_634_aM;
            d2 = d7;
            AxisAlignedBB axisalignedbb1 = boundingBox.copy();
            boundingBox.setBB(axisalignedbb);
            List list1 = worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox.addCoord(d, d1, d2));
            for(int j2 = 0; j2 < list1.size(); j2++)
            {
                d1 = ((AxisAlignedBB)list1.get(j2)).func_1172_b(boundingBox, d1);
            }

            boundingBox.offset(0.0D, d1, 0.0D);
            if(!field_596_az && d6 != d1)
            {
                d = d1 = d2 = 0.0D;
            }
            for(int k2 = 0; k2 < list1.size(); k2++)
            {
                d = ((AxisAlignedBB)list1.get(k2)).func_1163_a(boundingBox, d);
            }

            boundingBox.offset(d, 0.0D, 0.0D);
            if(!field_596_az && d5 != d)
            {
                d = d1 = d2 = 0.0D;
            }
            for(int l2 = 0; l2 < list1.size(); l2++)
            {
                d2 = ((AxisAlignedBB)list1.get(l2)).func_1162_c(boundingBox, d2);
            }

            boundingBox.offset(0.0D, 0.0D, d2);
            if(!field_596_az && d7 != d2)
            {
                d = d1 = d2 = 0.0D;
            }
            if(d9 * d9 + d13 * d13 >= d * d + d2 * d2)
            {
                d = d9;
                d1 = d11;
                d2 = d13;
                boundingBox.setBB(axisalignedbb1);
            } else
            {
                field_635_aL += 0.5D;
            }
        }
        posX = (boundingBox.minX_00 + boundingBox.maxX) / 2D;
        posY = (boundingBox.minY + (double)yOffset_00) - (double)field_635_aL;
        posZ = (boundingBox.minZ + boundingBox.maxZ) / 2D;
        field_599_aw = d5 != d || d7 != d2;
        field_598_ax = d6 != d1;
                if(!fly) {

            onGround_00 = d6 != d1 && d6 < 0.0D;
        }
        else
        {
            onGround_00 = true;
        }
        field_597_ay = field_599_aw || field_598_ax;
        if(onGround_00)
        {
            if(fallDistance > 0.0F)
            {
                fall(fallDistance);
                fallDistance = 0.0F;
            }
        } else
        if(d1 < 0.0D)
        {
            fallDistance -= d1;
        }
        if(d5 != d)
        {
            motionX = 0.0D;
        }
        if(d6 != d1)
        {
            motionY = 0.0D;
        }
        if(d7 != d2)
        {
            motionZ = 0.0D;
        }
        double d10 = posX - d3;
        double d12 = posZ - d4;
        field_641_aF += (double)MathHelper.sqrt_double(d10 * d10 + d12 * d12) * 0.59999999999999998D;
        if(entityWalks && !flag)
        {
            int l = MathHelper.convertToBlockCoord_00(posX);
            int j1 = MathHelper.convertToBlockCoord_00(posY - 0.20000000298023224D - (double)yOffset_00);
            int l1 = MathHelper.convertToBlockCoord_00(posZ);
            int i3 = worldObj_09.getBlockId(l, j1, l1);
            if(field_641_aF > (float)field_653_b && i3 > 0)
            {
                field_653_b++;
                StepSound stepsound = Block.blocksList[i3].stepSound;
                if(worldObj_09.getBlockId(l, j1 + 1, l1) == Block.snow_00.blockID_00)
                {
                    stepsound = Block.snow_00.stepSound;
                    worldObj_09.playSoundAtEntity(this, stepsound.func_1145_d(), stepsound.func_1147_b() * 0.15F, stepsound.func_1144_c());
                } else
                if(!Block.blocksList[i3].blockMaterial.getIsLiquid())
                {
                    worldObj_09.playSoundAtEntity(this, stepsound.func_1145_d(), stepsound.func_1147_b() * 0.15F, stepsound.func_1144_c());
                }
                Block.blocksList[i3].onEntityWalking(worldObj_09, l, j1, l1, this);
            }
        }
        int i1 = MathHelper.convertToBlockCoord_00(boundingBox.minX_00);
        int k1 = MathHelper.convertToBlockCoord_00(boundingBox.minY);
        int i2 = MathHelper.convertToBlockCoord_00(boundingBox.minZ);
        int j3 = MathHelper.convertToBlockCoord_00(boundingBox.maxX);
        int k3 = MathHelper.convertToBlockCoord_00(boundingBox.maxY);
        int l3 = MathHelper.convertToBlockCoord_00(boundingBox.maxZ);
        for(int i4 = i1; i4 <= j3; i4++)
        {
            for(int j4 = k1; j4 <= k3; j4++)
            {
                for(int k4 = i2; k4 <= l3; k4++)
                {
                    int l4 = worldObj_09.getBlockId(i4, j4, k4);
                    if(l4 > 0)
                    {
                        Block.blocksList[l4].onEntityCollidedWithBlock(worldObj_09, i4, j4, k4, this);
                    }
                }

            }

        }

        field_635_aL *= 0.4F;
        boolean flag2 = handleWaterMovement();
        if(worldObj_09.func_605_c(boundingBox))
        {
            func_355_a(1);
            if(!flag2)
            {
                fire_00++;
                if(fire_00 == 0)
                {
                    fire_00 = 300;
                }
            }
        } else
        if(fire_00 <= 0)
        {
            fire_00 = -field_628_aS;
        }
        if(flag2 && fire_00 > 0)
        {
            worldObj_09.playSoundAtEntity(this, "random.fizz", 0.7F, 1.6F + (rand_05.nextFloat() - rand_05.nextFloat()) * 0.4F);
            fire_00 = -field_628_aS;
        }
    }

    public boolean func_381_o()
    {
        return false;
    }

    public AxisAlignedBB func_372_f_()
    {
        return null;
    }

    protected void func_355_a(int i)
    {
        if(!field_4079_be)
        {
            attackEntity(null, i);
        }
    }

    protected void fall(float f)
    {
    }

    public boolean handleWaterMovement()
    {
        return worldObj_09.func_682_a(boundingBox.expands(0.0D, -0.40000000596046448D, 0.0D), Material.water, this);
    }

    public boolean isInsideOfMaterial(Material material)
    {
        double d = posY + (double)func_373_s();
        int i = MathHelper.convertToBlockCoord_00(posX);
        int j = MathHelper.convertToBlockCoord(MathHelper.convertToBlockCoord_00(d));
        int k = MathHelper.convertToBlockCoord_00(posZ);
        int l = worldObj_09.getBlockId(i, j, k);
        if(l != 0 && Block.blocksList[l].blockMaterial == material)
        {
            float f = BlockFluids.func_288_b(worldObj_09.getBlockMetadata_01(i, j, k)) - 0.1111111F;
            float f1 = (float)(j + 1) - f;
            return d < (double)f1;
        } else
        {
            return false;
        }
    }

    public float func_373_s()
    {
        return 0.0F;
    }

    public boolean func_359_G()
    {
        return worldObj_09.func_689_a(boundingBox.expands(0.0D, -0.40000000596046448D, 0.0D), Material.lava);
    }

    public void func_351_a(float f, float f1, float f2)
    {
        float f3 = MathHelper.sqrt_float(f * f + f1 * f1);
        if(f3 < 0.01F)
        {
            return;
        }
        if(f3 < 1.0F)
        {
            f3 = 1.0F;
        }
        f3 = f2 / f3;
        f *= f3;
        f1 *= f3;
        float f4 = MathHelper.sin((rotationYaw * 3.141593F) / 180F);
        float f5 = MathHelper.cos((rotationYaw * 3.141593F) / 180F);
        motionX += f * f5 - f1 * f4;
        motionZ += f1 * f5 + f * f4;
    }

    public float getEntityBrightness(float f)
    {
        int i = MathHelper.convertToBlockCoord_00(posX);
        double d = (boundingBox.maxY - boundingBox.minY) * 0.66000000000000003D;
        int j = MathHelper.convertToBlockCoord_00((posY - (double)yOffset_00) + d);
        int k = MathHelper.convertToBlockCoord_00(posZ);
        return worldObj_09.getLightBrightness(i, j, k);
    }

    public void setWorld(World world)
    {
        worldObj_09 = world;
    }

    public void setPositionAndRotation(double d, double d1, double d2, float f, 
            float f1)
    {
        prevPosX = posX = d;
        prevPosY = posY = d1;
        prevPosZ = posZ = d2;
        field_603_as = rotationYaw = f;
        field_602_at = rotationPitch = f1;
        field_635_aL = 0.0F;
        double d3 = field_603_as - f;
        if(d3 < -180D)
        {
            field_603_as += 360F;
        }
        if(d3 >= 180D)
        {
            field_603_as -= 360F;
        }
        setPosition(posX, posY, posZ);
        setRotation(f, f1);
    }

    public void func_365_c(double d, double d1, double d2, float f, 
            float f1)
    {
        prevPosX = posX = d;
        prevPosY = posY = d1 + (double)yOffset_00;
        prevPosZ = posZ = d2;
        rotationYaw = f;
        rotationPitch = f1;
        setPosition(posX, posY, posZ);
    }

    public float getDistanceToEntity(Entity entity)
    {
        float f = (float)(posX - entity.posX);
        float f1 = (float)(posY - entity.posY);
        float f2 = (float)(posZ - entity.posZ);
        return MathHelper.sqrt_float(f * f + f1 * f1 + f2 * f2);
    }

    public double getDistanceSq(double d, double d1, double d2)
    {
        double d3 = posX - d;
        double d4 = posY - d1;
        double d5 = posZ - d2;
        return d3 * d3 + d4 * d4 + d5 * d5;
    }

    public double getDistance(double d, double d1, double d2)
    {
        double d3 = posX - d;
        double d4 = posY - d1;
        double d5 = posZ - d2;
        return (double)MathHelper.sqrt_double(d3 * d3 + d4 * d4 + d5 * d5);
    }

    public double getDistanceSqToEntity(Entity entity)
    {
        double d = posX - entity.posX;
        double d1 = posY - entity.posY;
        double d2 = posZ - entity.posZ;
        return d * d + d1 * d1 + d2 * d2;
    }

    public void onCollideWithPlayer(EntityPlayer entityplayer)
    {
    }

    public void applyEntityCollision(Entity entity)
    {
        if(entity.field_617_ae == this || entity.field_616_af == this)
        {
            return;
        }
        double d = entity.posX - posX;
        double d1 = entity.posZ - posZ;
        double d2 = MathHelper.abs_max(d, d1);
        if(d2 >= 0.0099999997764825821D)
        {
            d2 = MathHelper.sqrt_double(d2);
            d /= d2;
            d1 /= d2;
            double d3 = 1.0D / d2;
            if(d3 > 1.0D)
            {
                d3 = 1.0D;
            }
            d *= d3;
            d1 *= d3;
            d *= 0.05000000074505806D;
            d1 *= 0.05000000074505806D;
            d *= 1.0F - field_632_aO;
            d1 *= 1.0F - field_632_aO;
            addVelocity(-d, 0.0D, -d1);
            entity.addVelocity(d, 0.0D, d1);
        }
    }

    public void addVelocity(double d, double d1, double d2)
    {
        motionX += d;
        motionY += d1;
        motionZ += d2;
    }

    public boolean attackEntity(Entity entity, int i)
    {
        return false;
    }

    public boolean func_401_c_()
    {
        return false;
    }

    public boolean func_385_d_()
    {
        return false;
    }

    public void func_364_b(Entity entity, int i)
    {
    }

    public boolean func_390_a(Vec3D vec3d)
    {
        double d = posX - vec3d.xCoord_00;
        double d1 = posY - vec3d.yCoord_00;
        double d2 = posZ - vec3d.zCoord_00;
        double d3 = d * d + d1 * d1 + d2 * d2;
        return func_384_a(d3);
    }

    public boolean func_384_a(double d)
    {
        double d1 = boundingBox.getAverageEdgeLength();
        d1 *= 64D * field_619_ac;
        return d < d1 * d1;
    }

    public String func_6376_z()
    {
        return null;
    }

    public boolean func_358_c(NBTTagCompound nbttagcompound)
    {
        String s = func_389_H();
        if(field_646_aA || s == null)
        {
            return false;
        } else
        {
            nbttagcompound.setString("id", s);
            writeToNBT(nbttagcompound);
            return true;
        }
    }

    public void writeToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setTag("Pos", func_375_a(new double[] {
            posX, posY, posZ
        }));
        nbttagcompound.setTag("Motion", func_375_a(new double[] {
            motionX, motionY, motionZ
        }));
        nbttagcompound.setTag("Rotation", func_377_a(new float[] {
            rotationYaw, rotationPitch
        }));
        nbttagcompound.setFloat("FallDistance", fallDistance);
        nbttagcompound.setShort("Fire", (short)fire_00);
        nbttagcompound.setShort("Air", (short)air_00);
        nbttagcompound.setBoolean("OnGround", onGround_00);
        writeEntityToNBT(nbttagcompound);
    }

    public void readFromNBT_00(NBTTagCompound nbttagcompound)
    {
        NBTTagList nbttaglist = nbttagcompound.getTagList("Pos");
        NBTTagList nbttaglist1 = nbttagcompound.getTagList("Motion");
        NBTTagList nbttaglist2 = nbttagcompound.getTagList("Rotation");
        setPosition(0.0D, 0.0D, 0.0D);
        motionX = ((NBTTagDouble)nbttaglist1.tagAt(0)).doubleValue;
        motionY = ((NBTTagDouble)nbttaglist1.tagAt(1)).doubleValue;
        motionZ = ((NBTTagDouble)nbttaglist1.tagAt(2)).doubleValue;
        prevPosX = field_638_aI = posX = ((NBTTagDouble)nbttaglist.tagAt(0)).doubleValue;
        prevPosY = field_637_aJ = posY = ((NBTTagDouble)nbttaglist.tagAt(1)).doubleValue;
        prevPosZ = field_636_aK = posZ = ((NBTTagDouble)nbttaglist.tagAt(2)).doubleValue;
        field_603_as = rotationYaw = ((NBTTagFloat)nbttaglist2.tagAt(0)).floatValue;
        field_602_at = rotationPitch = ((NBTTagFloat)nbttaglist2.tagAt(1)).floatValue;
        fallDistance = nbttagcompound.getFloat("FallDistance");
        fire_00 = nbttagcompound.getShort("Fire");
        air_00 = nbttagcompound.getShort("Air");
        onGround_00 = nbttagcompound.getBoolean("OnGround");
        setPosition(posX, posY, posZ);
        readEntityFromNBT(nbttagcompound);
    }

    protected final String func_389_H()
    {
        return EntityList.func_1083_b(this);
    }

    protected abstract void readEntityFromNBT(NBTTagCompound nbttagcompound);

    protected abstract void writeEntityToNBT(NBTTagCompound nbttagcompound);

    protected NBTTagList func_375_a(double ad[])
    {
        NBTTagList nbttaglist = new NBTTagList();
        double ad1[] = ad;
        int i = ad1.length;
        for(int j = 0; j < i; j++)
        {
            double d = ad1[j];
            nbttaglist.setTag_00(new NBTTagDouble(d));
        }

        return nbttaglist;
    }

    protected NBTTagList func_377_a(float af[])
    {
        NBTTagList nbttaglist = new NBTTagList();
        float af1[] = af;
        int i = af1.length;
        for(int j = 0; j < i; j++)
        {
            float f = af1[j];
            nbttaglist.setTag_00(new NBTTagFloat(f));
        }

        return nbttaglist;
    }

    public float func_392_h_()
    {
        return height_01 / 2.0F;
    }

    public EntityItem dropItem(int i, int j)
    {
        return dropItemWithOffset(i, j, 0.0F);
    }

    public EntityItem dropItemWithOffset(int i, int j, float f)
    {
        EntityItem entityitem = new EntityItem(worldObj_09, posX, posY + (double)f, posZ, new ItemStack(i, j));
        entityitem.field_805_c = 10;
        worldObj_09.entityJoinedWorld(entityitem);
        return entityitem;
    }

    public boolean func_354_B()
    {
        return !field_646_aA;
    }

    public boolean func_345_I()
    {
        int i = MathHelper.convertToBlockCoord_00(posX);
        int j = MathHelper.convertToBlockCoord_00(posY + (double)func_373_s());
        int k = MathHelper.convertToBlockCoord_00(posZ);
        return worldObj_09.doesBlockAllowAttachment(i, j, k);
    }

    public boolean interact(EntityPlayer entityplayer)
    {
        return false;
    }

    public AxisAlignedBB func_383_b_(Entity entity)
    {
        return null;
    }

    public void func_350_p()
    {
        if(field_616_af.field_646_aA)
        {
            field_616_af = null;
            return;
        }
        motionX = 0.0D;
        motionY = 0.0D;
        motionZ = 0.0D;
        onUpdate();
        field_616_af.func_366_i_();
        field_647_e += field_616_af.rotationYaw - field_616_af.field_603_as;
        field_649_d += field_616_af.rotationPitch - field_616_af.field_602_at;
        for(; field_647_e >= 180D; field_647_e -= 360D) { }
        for(; field_647_e < -180D; field_647_e += 360D) { }
        for(; field_649_d >= 180D; field_649_d -= 360D) { }
        for(; field_649_d < -180D; field_649_d += 360D) { }
        double d = field_647_e * 0.5D;
        double d1 = field_649_d * 0.5D;
        float f = 10F;
        if(d > (double)f)
        {
            d = f;
        }
        if(d < (double)(-f))
        {
            d = -f;
        }
        if(d1 > (double)f)
        {
            d1 = f;
        }
        if(d1 < (double)(-f))
        {
            d1 = -f;
        }
        field_647_e -= d;
        field_649_d -= d1;
        rotationYaw += d;
        rotationPitch += d1;
    }

    public void func_366_i_()
    {
        field_617_ae.setPosition(posX, posY + func_402_h() + field_617_ae.func_388_v(), posZ);
    }

    public double func_388_v()
    {
        return (double)yOffset_00;
    }

    public double func_402_h()
    {
        return (double)height_01 * 0.75D;
    }

    public void func_6377_h(Entity entity)
    {
        field_649_d = 0.0D;
        field_647_e = 0.0D;
        if(entity == null)
        {
            if(field_616_af != null)
            {
                func_365_c(field_616_af.posX, field_616_af.boundingBox.minY + (double)field_616_af.height_01, field_616_af.posZ, rotationYaw, rotationPitch);
                field_616_af.field_617_ae = null;
            }
            field_616_af = null;
            return;
        }
        if(field_616_af == entity)
        {
            field_616_af.field_617_ae = null;
            field_616_af = null;
            func_365_c(entity.posX, entity.boundingBox.minY + (double)entity.height_01, entity.posZ, rotationYaw, rotationPitch);
            return;
        }
        if(field_616_af != null)
        {
            field_616_af.field_617_ae = null;
        }
        if(entity.field_617_ae != null)
        {
            entity.field_617_ae.field_616_af = null;
        }
        field_616_af = entity;
        entity.field_617_ae = this;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        setPosition(d, d1, d2);
        setRotation(f, f1);
    }

    public float func_4035_j_()
    {
        return 0.1F;
    }

    public Vec3D func_4037_H()
    {
        return null;
    }

    public void func_4039_q()
    {
    }

    public void setVelocity(double d, double d1, double d2)
    {
        motionX = d;
        motionY = d1;
        motionZ = d2;
    }

    private static int field_864_a = 0;
    public int field_620_ab;
    public double field_619_ac;
    public boolean field_618_ad;
    public Entity field_617_ae;
    public Entity field_616_af;
    public World worldObj_09;
    public double prevPosX;
    public double prevPosY;
    public double prevPosZ;
    public double posX;
    public double posY;
    public double posZ;
    public double motionX;
    public double motionY;
    public double motionZ;
    public float rotationYaw;
    public float rotationPitch;
    public float field_603_as;
    public float field_602_at;
    public final AxisAlignedBB boundingBox = AxisAlignedBB.getBoundingBox(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
    public boolean onGround_00;
    public boolean field_599_aw;
    public boolean field_598_ax;
    public boolean field_597_ay;
    public boolean field_596_az;
    public boolean field_646_aA;
    public float yOffset_00;
    public float width_00;
    public float height_01;
    public float field_4078_aJ;
    public float field_641_aF;
    protected boolean entityWalks;
    protected float fallDistance;
    private int field_653_b;
    public double field_638_aI;
    public double field_637_aJ;
    public double field_636_aK;
    public float field_635_aL;
    public float field_634_aM;
    public boolean field_633_aN;
    public float field_632_aO;
    public boolean field_4077_aU;
    protected Random rand_05;
    public int field_4076_aW;
    public int field_628_aS;
    public int fire_00;
    protected int field_4075_aZ;
    protected boolean field_4081_ba;
    public int field_4080_bb;
    public int air_00;
    private boolean field_862_c;
    public String field_622_aY;
    protected boolean field_4079_be;
    private double field_649_d;
    private double field_647_e;
    public boolean field_621_aZ;
    public int field_657_ba;
    public int field_656_bb;
    public int field_654_bc;
    public int field_652_bd;
    public int field_650_be;
    public int field_648_bf;
    public boolean field_6379_bs;

}
