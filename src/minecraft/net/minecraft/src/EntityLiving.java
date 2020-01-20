package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public class EntityLiving extends Entity
{

    public EntityLiving(World world)
    {
        super(world);
        field_739_j = 20;
        field_735_n = 0.0F;
        field_734_o = 0.0F;
        field_6410_y = true;
        field_6409_z = "/mob/char.png";
        field_6408_A = true;
        field_6407_B = 0.0F;
        field_6406_C = null;
        field_6405_D = 1.0F;
        field_723_z = 0;
        field_6403_F = 0.0F;
        field_720_B = false;
        field_6399_N = 0.0F;
        deathTime = 0;
        attackTime = 0;
        field_708_N = false;
        field_6396_T = -1;
        field_6395_U = (float)(Math.random() * 0.89999997615814209D + 0.10000000149011612D);
        field_6404_ae = 0.0F;
        field_4120_b = 0;
        field_701_U = 0;
        field_697_Y = false;
        field_696_Z = 0.0F;
        field_6401_al = 0.7F;
        field_740_i = 0;
        health_00 = 10;
        field_618_ad = true;
        field_6412_r = (float)(Math.random() + 1.0D) * 0.01F;
        setPosition(posX, posY, posZ);
        field_6414_p = (float)Math.random() * 12398F;
        rotationYaw = (float)(Math.random() * 3.1415927410125732D * 2D);
        field_6413_q = 1.0F;
        field_634_aM = 0.5F;
    }

    public boolean func_420_c(Entity entity)
    {
        return worldObj_09.func_645_a(Vec3D.createVector(posX, posY + (double)func_373_s(), posZ), Vec3D.createVector(entity.posX, entity.posY + (double)entity.func_373_s(), entity.posZ)) == null;
    }

    public String func_6376_z()
    {
        return field_6409_z;
    }

    public boolean func_401_c_()
    {
        return !field_646_aA;
    }

    public boolean func_385_d_()
    {
        return !field_646_aA;
    }

    public float func_373_s()
    {
        return height_01 * 0.85F;
    }

    public int func_421_b()
    {
        return 80;
    }

    public void func_391_y()
    {
        field_719_C = field_718_D;
        super.func_391_y();
        if(rand_05.nextInt(1000) < field_4121_a++)
        {
            field_4121_a = -func_421_b();
            String s = func_6389_d();
            if(s != null)
            {
                worldObj_09.playSoundAtEntity(this, s, func_6393_h(), (rand_05.nextFloat() - rand_05.nextFloat()) * 0.2F + 1.0F);
            }
        }
        if(func_354_B() && func_345_I())
        {
            attackEntity(null, 1);
        }
        if(field_4079_be)
        {
            fire_00 = 0;
        }
        if(func_354_B() && isInsideOfMaterial(Material.water))
        {
            air_00--;
            if(air_00 == -20)
            {
                air_00 = 0;
                for(int i = 0; i < 8; i++)
                {
                    float f = rand_05.nextFloat() - rand_05.nextFloat();
                    float f1 = rand_05.nextFloat() - rand_05.nextFloat();
                    float f2 = rand_05.nextFloat() - rand_05.nextFloat();
                    worldObj_09.spawnParticle_00("bubble", posX + (double)f, posY + (double)f1, posZ + (double)f2, motionX, motionY, motionZ);
                }

                attackEntity(null, 2);
            }
            fire_00 = 0;
        } else
        {
            air_00 = field_4075_aZ;
        }
        field_6398_Q = field_6397_R;
        if(attackTime > 0)
        {
            attackTime--;
        }
        if(hurtTime > 0)
        {
            hurtTime--;
        }
        if(field_4080_bb > 0)
        {
            field_4080_bb--;
        }
        if(health_00 <= 0)
        {
            deathTime++;
            if(deathTime > 20)
            {
                func_6392_F();
                setEntityDead();
                for(int j = 0; j < 20; j++)
                {
                    double d = rand_05.nextGaussian() * 0.02D;
                    double d1 = rand_05.nextGaussian() * 0.02D;
                    double d2 = rand_05.nextGaussian() * 0.02D;
                    worldObj_09.spawnParticle_00("explode", (posX + (double)(rand_05.nextFloat() * width_00 * 2.0F)) - (double)width_00, posY + (double)(rand_05.nextFloat() * height_01), (posZ + (double)(rand_05.nextFloat() * width_00 * 2.0F)) - (double)width_00, d, d1, d2);
                }

            }
        }
        field_6411_x = field_731_r;
        field_734_o = field_735_n;
        field_603_as = rotationYaw;
        field_602_at = rotationPitch;
    }

    public void func_415_z()
    {
        for(int i = 0; i < 20; i++)
        {
            double d = rand_05.nextGaussian() * 0.02D;
            double d1 = rand_05.nextGaussian() * 0.02D;
            double d2 = rand_05.nextGaussian() * 0.02D;
            double d3 = 10D;
            worldObj_09.spawnParticle_00("explode", (posX + (double)(rand_05.nextFloat() * width_00 * 2.0F)) - (double)width_00 - d * d3, (posY + (double)(rand_05.nextFloat() * height_01)) - d1 * d3, (posZ + (double)(rand_05.nextFloat() * width_00 * 2.0F)) - (double)width_00 - d2 * d3, d, d1, d2);
        }

    }

    public void func_350_p()
    {
        super.func_350_p();
        field_733_p = field_732_q;
        field_732_q = 0.0F;
    }

    public void setPositionAndRotation2(double d, double d1, double d2, float f, 
            float f1, int i)
    {
        yOffset_00 = 0.0F;
        field_746_c = d;
        field_745_d = d1;
        field_744_e = d2;
        field_743_f = f;
        field_742_g = f1;
        field_747_b = i;
    }

    public void onUpdate()
    {
        super.onUpdate();
        onLivingUpdate();
        double d = posX - prevPosX;
        double d1 = posZ - prevPosZ;
        float f = MathHelper.sqrt_double(d * d + d1 * d1);
        float f1 = field_735_n;
        float f2 = 0.0F;
        field_733_p = field_732_q;
        float f3 = 0.0F;
        if(f > 0.05F)
        {
            f3 = 1.0F;
            f2 = f * 3F;
            f1 = ((float)Math.atan2(d1, d) * 180F) / 3.141593F - 90F;
        }
        if(field_718_D > 0.0F)
        {
            f1 = rotationYaw;
        }
        if(!onGround_00)
        {
            f3 = 0.0F;
        }
        field_732_q = field_732_q + (f3 - field_732_q) * 0.3F;
        float f4;
        for(f4 = f1 - field_735_n; f4 < -180F; f4 += 360F) { }
        for(; f4 >= 180F; f4 -= 360F) { }
        field_735_n += f4 * 0.3F;
        float f5;
        for(f5 = rotationYaw - field_735_n; f5 < -180F; f5 += 360F) { }
        for(; f5 >= 180F; f5 -= 360F) { }
        boolean flag = f5 < -90F || f5 >= 90F;
        if(f5 < -75F)
        {
            f5 = -75F;
        }
        if(f5 >= 75F)
        {
            f5 = 75F;
        }
        field_735_n = rotationYaw - f5;
        if(f5 * f5 > 2500F)
        {
            field_735_n += f5 * 0.2F;
        }
        if(flag)
        {
            f2 *= -1F;
        }
        for(; rotationYaw - field_603_as < -180F; field_603_as -= 360F) { }
        for(; rotationYaw - field_603_as >= 180F; field_603_as += 360F) { }
        for(; field_735_n - field_734_o < -180F; field_734_o -= 360F) { }
        for(; field_735_n - field_734_o >= 180F; field_734_o += 360F) { }
        for(; rotationPitch - field_602_at < -180F; field_602_at -= 360F) { }
        for(; rotationPitch - field_602_at >= 180F; field_602_at += 360F) { }
        field_731_r += f2;
    }

    protected void setSize(float f, float f1)
    {
        super.setSize(f, f1);
    }

    public void heal(int i)
    {
        if(health_00 <= 0)
        {
            return;
        }
        health_00 += i;
        if(health_00 > 20)
        {
            health_00 = 20;
        }
        field_4080_bb = field_739_j / 2;
    }
    public boolean godmode = false;

    public boolean attackEntity(Entity entity, int i)
    {
        if(worldObj_09.multiplayerWorld)
        {
            i = 0;
        }
        field_701_U = 0;
        if(health_00 <= 0)
        {
            return false;
        }
        field_704_R = 1.5F;
        boolean flag = true;
        if((float)field_4080_bb > (float)field_739_j / 2.0F)
        {
            if(i <= field_4120_b)
            {
                return false;
            }
            func_4044_a(i - field_4120_b);
            field_4120_b = i;
            flag = false;
        } else
        {
            field_4120_b = i;
            field_6402_K = health_00;
            field_4080_bb = field_739_j;
            func_4044_a(i);
            hurtTime = field_6400_M = 10;
        }
        field_6399_N = 0.0F;
        if(flag)
        {
            if(entity != null)
            {
                double d = entity.posX - posX;
                double d1;
                for(d1 = entity.posZ - posZ; d * d + d1 * d1 < 0.0001D; d1 = (Math.random() - Math.random()) * 0.01D)
                {
                    d = (Math.random() - Math.random()) * 0.01D;
                }

                field_6399_N = (float)((Math.atan2(d1, d) * 180D) / 3.1415927410125732D) - rotationYaw;
                func_434_a(entity, i, d, d1);
            } else
            {
                field_6399_N = (int)(Math.random() * 2D) * 180;
            }
        }
        if(health_00 <= 0)
        {
            if(flag)
            {
                worldObj_09.playSoundAtEntity(this, func_6390_f(), func_6393_h(), (rand_05.nextFloat() - rand_05.nextFloat()) * 0.2F + 1.0F);
            }
            onDeath(entity);
        } else
        if(flag)
        {
            worldObj_09.playSoundAtEntity(this, func_6394_f_(), func_6393_h(), (rand_05.nextFloat() - rand_05.nextFloat()) * 0.2F + 1.0F);
        }
        return true;
    }

    protected void func_4044_a(int i)
    {
        if(!godmode) {
            health_00 -= i;
        }
    }

    protected float func_6393_h()
    {
        return 1.0F;
    }

    protected String func_6389_d()
    {
        return null;
    }

    protected String func_6394_f_()
    {
        return "random.hurt";
    }

    protected String func_6390_f()
    {
        return "random.hurt";
    }

    public void func_434_a(Entity entity, int i, double d, double d1)
    {
        float f = MathHelper.sqrt_double(d * d + d1 * d1);
        float f1 = 0.4F;
        motionX /= 2D;
        motionY /= 2D;
        motionZ /= 2D;
        motionX -= (d / (double)f) * (double)f1;
        motionY += 0.40000000596046448D;
        motionZ -= (d1 / (double)f) * (double)f1;
        if(motionY > 0.40000000596046448D)
        {
            motionY = 0.40000000596046448D;
        }
    }

    public void onDeath(Entity entity)
    {
        if(field_723_z > 0 && entity != null)
        {
            entity.func_364_b(this, field_723_z);
        }
        field_708_N = true;
        int i = getDropItemId();
        if(i > 0)
        {
            int j = rand_05.nextInt(3);
            for(int k = 0; k < j; k++)
            {
                dropItem(i, 1);
            }

        }
    }

    protected int getDropItemId()
    {
        return 0;
    }

    protected void fall(float f)
    {
        int i = (int)Math.ceil(f - 3F);
        if(i > 0)
        {
            attackEntity(null, i);
            int j = worldObj_09.getBlockId(MathHelper.convertToBlockCoord_00(posX), MathHelper.convertToBlockCoord_00(posY - 0.20000000298023224D - (double)yOffset_00), MathHelper.convertToBlockCoord_00(posZ));
            if(j > 0)
            {
                StepSound stepsound = Block.blocksList[j].stepSound;
                worldObj_09.playSoundAtEntity(this, stepsound.func_1145_d(), stepsound.func_1147_b() * 0.5F, stepsound.func_1144_c() * 0.75F);
            }
        }
    }
    
    public double spd = 1.0;
    public void func_435_b(float f, float f1)
    {
        if(handleWaterMovement())
        {
            double d = posY;
            func_351_a(f, f1, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.80000001192092896D;
            motionY *= 0.80000001192092896D;
            motionZ *= 0.80000001192092896D;
            motionY -= 0.02D;
            if(field_599_aw && func_403_b(motionX, ((motionY + 0.60000002384185791D) - posY) + d, motionZ))
            {
                motionY = 0.30000001192092896D;
            }
        } else
        if(func_359_G())
        {
            double d1 = posY;
            func_351_a(f, f1, 0.02F);
            moveEntity(motionX, motionY, motionZ);
            motionX *= 0.5D;
            motionY *= 0.5D;
            motionZ *= 0.5D;
            motionY -= 0.02D;
            if(field_599_aw && func_403_b(motionX, ((motionY + 0.60000002384185791D) - posY) + d1, motionZ))
            {
                motionY = 0.30000001192092896D;
            }
        } else
        {
            float f2 = 0.91F;
            if(onGround_00)
            {
                f2 = 0.5460001F;
                int i = worldObj_09.getBlockId(MathHelper.convertToBlockCoord_00(posX), MathHelper.convertToBlockCoord_00(boundingBox.minY) - 1, MathHelper.convertToBlockCoord_00(posZ));
                if(i > 0)
                {
                    f2 = Block.blocksList[i].slipperiness * 0.91F;
                }
            }
            float f3 = 0.1627714F / (f2 * f2 * f2);
            func_351_a(f, f1, onGround_00 ? 0.1F * f3 : 0.02F);
            f2 = 0.91F;
            if(onGround_00)
            {
                f2 = 0.5460001F;
                int j = worldObj_09.getBlockId(MathHelper.convertToBlockCoord_00(posX), MathHelper.convertToBlockCoord_00(boundingBox.minY) - 1, MathHelper.convertToBlockCoord_00(posZ));
                if(j > 0)
                {
                    f2 = Block.blocksList[j].slipperiness * 0.91F;
                }
            }
            if(func_429_A())
            {
                fallDistance = 0.0F;
                if(motionY < -0.14999999999999999D)
                {
                    motionY = -0.14999999999999999D;
                }
            }
            moveEntity(motionX, motionY, motionZ);
            if(field_599_aw && func_429_A())
            {
                motionY = 0.20000000000000001D;
            }
            
            if(!fly) {
                motionY -= 0.080000000000000002D;
                motionY *= 0.98000001907348633D;
            }
            else {
                motionY = 0;
            }
            motionX *= (f2 * spd);
            motionZ *= (f2 * spd);
        }
        field_705_Q = field_704_R;
        double d2 = posX - prevPosX;
        double d3 = posZ - prevPosZ;
        float f4 = MathHelper.sqrt_double(d2 * d2 + d3 * d3) * 4F;
        if(f4 > 1.0F)
        {
            f4 = 1.0F;
        }
        field_704_R += (f4 - field_704_R) * 0.4F;
        field_703_S += field_704_R;
    }

    public boolean func_429_A()
    {
        int i = MathHelper.convertToBlockCoord_00(posX);
        int j = MathHelper.convertToBlockCoord_00(boundingBox.minY);
        int k = MathHelper.convertToBlockCoord_00(posZ);
        return worldObj_09.getBlockId(i, j, k) == Block.ladder.blockID_00 || worldObj_09.getBlockId(i, j + 1, k) == Block.ladder.blockID_00;
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        nbttagcompound.setShort("Health", (short)health_00);
        nbttagcompound.setShort("HurtTime", (short)hurtTime);
        nbttagcompound.setShort("DeathTime", (short)deathTime);
        nbttagcompound.setShort("AttackTime", (short)attackTime);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        health_00 = nbttagcompound.getShort("Health");
        if(!nbttagcompound.hasKey("Health"))
        {
            health_00 = 10;
        }
        hurtTime = nbttagcompound.getShort("HurtTime");
        deathTime = nbttagcompound.getShort("DeathTime");
        attackTime = nbttagcompound.getShort("AttackTime");
    }

    public boolean func_354_B()
    {
        return !field_646_aA && health_00 > 0;
    }

    public void onLivingUpdate()
    {
        if(field_747_b > 0)
        {
            double d = posX + (field_746_c - posX) / (double)field_747_b;
            double d1 = posY + (field_745_d - posY) / (double)field_747_b;
            double d2 = posZ + (field_744_e - posZ) / (double)field_747_b;
            double d3;
            for(d3 = field_743_f - (double)rotationYaw; d3 < -180D; d3 += 360D) { }
            for(; d3 >= 180D; d3 -= 360D) { }
            rotationYaw += d3 / (double)field_747_b;
            rotationPitch += (field_742_g - (double)rotationPitch) / (double)field_747_b;
            field_747_b--;
            setPosition(d, d1, d2);
            setRotation(rotationYaw, rotationPitch);
        }
        if(health_00 <= 0)
        {
            field_697_Y = false;
            field_700_V = 0.0F;
            field_699_W = 0.0F;
            field_698_X = 0.0F;
        } else
        if(!field_720_B)
        {
            func_418_b_();
        }
        boolean flag = handleWaterMovement();
        boolean flag1 = func_359_G();
        if(field_697_Y)
        {
            if(flag)
            {
                motionY += 0.039999999105930328D;
            } else
            if(flag1)
            {
                motionY += 0.039999999105930328D;
            } else
            if(onGround_00)
            {
                func_424_C();
            }
        }
        field_700_V *= 0.98F;
        field_699_W *= 0.98F;
        field_698_X *= 0.9F;
        func_435_b(field_700_V, field_699_W);
        List list = worldObj_09.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expands(0.20000000298023224D, 0.0D, 0.20000000298023224D));
        if(list != null && list.size() > 0)
        {
            for(int i = 0; i < list.size(); i++)
            {
                Entity entity = (Entity)list.get(i);
                if(entity.func_385_d_())
                {
                    entity.applyEntityCollision(this);
                }
            }

        }
    }

    protected void func_424_C()
    {
        motionY = 0.41999998688697815D;
    }

    protected void func_418_b_()
    {
        field_701_U++;
        EntityPlayer entityplayer = worldObj_09.getClosestPlayerToEntity(this, -1D);
        if(entityplayer != null)
        {
            double d = ((Entity) (entityplayer)).posX - posX;
            double d1 = ((Entity) (entityplayer)).posY - posY;
            double d2 = ((Entity) (entityplayer)).posZ - posZ;
            double d3 = d * d + d1 * d1 + d2 * d2;
            if(d3 > 16384D)
            {
                setEntityDead();
            }
            if(field_701_U > 600 && rand_05.nextInt(800) == 0)
            {
                if(d3 < 1024D)
                {
                    field_701_U = 0;
                } else
                {
                    setEntityDead();
                }
            }
        }
        field_700_V = 0.0F;
        field_699_W = 0.0F;
        float f = 8F;
        if(rand_05.nextFloat() < 0.02F)
        {
            EntityPlayer entityplayer1 = worldObj_09.getClosestPlayerToEntity(this, f);
            if(entityplayer1 != null)
            {
                field_741_h = entityplayer1;
                field_740_i = 10 + rand_05.nextInt(20);
            } else
            {
                field_698_X = (rand_05.nextFloat() - 0.5F) * 20F;
            }
        }
        if(field_741_h != null)
        {
            func_426_b(field_741_h, 10F);
            if(field_740_i-- <= 0 || field_741_h.field_646_aA || field_741_h.getDistanceSqToEntity(this) > (double)(f * f))
            {
                field_741_h = null;
            }
        } else
        {
            if(rand_05.nextFloat() < 0.05F)
            {
                field_698_X = (rand_05.nextFloat() - 0.5F) * 20F;
            }
            rotationYaw += field_698_X;
            rotationPitch = field_696_Z;
        }
        boolean flag = handleWaterMovement();
        boolean flag1 = func_359_G();
        if(flag || flag1)
        {
            field_697_Y = rand_05.nextFloat() < 0.8F;
        }
    }

    public void func_426_b(Entity entity, float f)
    {
        double d = entity.posX - posX;
        double d2 = entity.posZ - posZ;
        double d1;
        if(entity instanceof EntityLiving)
        {
            EntityLiving entityliving = (EntityLiving)entity;
            d1 = (entityliving.posY + (double)entityliving.func_373_s()) - (posY + (double)func_373_s());
        } else
        {
            d1 = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2D - (posY + (double)func_373_s());
        }
        double d3 = MathHelper.sqrt_double(d * d + d2 * d2);
        float f1 = (float)((Math.atan2(d2, d) * 180D) / 3.1415927410125732D) - 90F;
        float f2 = (float)((Math.atan2(d1, d3) * 180D) / 3.1415927410125732D);
        rotationPitch = func_417_b(rotationPitch, f2, f);
        rotationYaw = func_417_b(rotationYaw, f1, f);
    }

    private float func_417_b(float f, float f1, float f2)
    {
        float f3;
        for(f3 = f1 - f; f3 < -180F; f3 += 360F) { }
        for(; f3 >= 180F; f3 -= 360F) { }
        if(f3 > f2)
        {
            f3 = f2;
        }
        if(f3 < -f2)
        {
            f3 = -f2;
        }
        return f + f3;
    }

    public void func_6392_F()
    {
    }

    public boolean getCanSpawnHere()
    {
        return worldObj_09.func_604_a(boundingBox) && worldObj_09.getCollidingBoundingBoxes_00(this, boundingBox).size() == 0 && !worldObj_09.getIsAnyLiquid(boundingBox);
    }

    protected void func_4034_G()
    {
        attackEntity(null, 4);
    }

    public float func_431_d(float f)
    {
        float f1 = field_718_D - field_719_C;
        if(f1 < 0.0F)
        {
            f1++;
        }
        return field_719_C + f1 * f;
    }

    public Vec3D func_427_e(float f)
    {
        if(f == 1.0F)
        {
            return Vec3D.createVector(posX, posY, posZ);
        } else
        {
            double d = prevPosX + (posX - prevPosX) * (double)f;
            double d1 = prevPosY + (posY - prevPosY) * (double)f;
            double d2 = prevPosZ + (posZ - prevPosZ) * (double)f;
            return Vec3D.createVector(d, d1, d2);
        }
    }

    public Vec3D func_4037_H()
    {
        return func_430_f(1.0F);
    }

    public Vec3D func_430_f(float f)
    {
        if(f == 1.0F)
        {
            float f1 = MathHelper.cos_00(-rotationYaw * 0.01745329F - 3.141593F);
            float f3 = MathHelper.sin_00(-rotationYaw * 0.01745329F - 3.141593F);
            float f5 = -MathHelper.cos_00(-rotationPitch * 0.01745329F);
            float f7 = MathHelper.sin_00(-rotationPitch * 0.01745329F);
            return Vec3D.createVector(f3 * f5, f7, f1 * f5);
        } else
        {
            float f2 = field_602_at + (rotationPitch - field_602_at) * f;
            float f4 = field_603_as + (rotationYaw - field_603_as) * f;
            float f6 = MathHelper.cos_00(-f4 * 0.01745329F - 3.141593F);
            float f8 = MathHelper.sin_00(-f4 * 0.01745329F - 3.141593F);
            float f9 = -MathHelper.cos_00(-f2 * 0.01745329F);
            float f10 = MathHelper.sin_00(-f2 * 0.01745329F);
            return Vec3D.createVector(f8 * f9, f10, f6 * f9);
        }
    }

    public MovingObjectPosition func_416_a(double d, float f)
    {
        Vec3D vec3d = func_427_e(f);
        Vec3D vec3d1 = func_430_f(f);
        Vec3D vec3d2 = vec3d.addVector(vec3d1.xCoord_00 * d, vec3d1.yCoord_00 * d, vec3d1.zCoord_00 * d);
        return worldObj_09.func_645_a(vec3d, vec3d2);
    }

    public int func_6391_i()
    {
        return 4;
    }

    public ItemStack func_4045_l()
    {
        return null;
    }

    public int field_739_j;
    public float field_6414_p;
    public float field_6413_q;
    public float field_6412_r;
    public float field_735_n;
    public float field_734_o;
    protected float field_733_p;
    protected float field_732_q;
    protected float field_731_r;
    protected float field_6411_x;
    protected boolean field_6410_y;
    protected String field_6409_z;
    protected boolean field_6408_A;
    protected float field_6407_B;
    protected String field_6406_C;
    protected float field_6405_D;
    protected int field_723_z;
    protected float field_6403_F;
    public boolean field_720_B;
    public float field_719_C;
    public float field_718_D;
    public int health_00;
    public int field_6402_K;
    private int field_4121_a;
    public int hurtTime;
    public int field_6400_M;
    public float field_6399_N;
    public int deathTime;
    public int attackTime;
    public float field_6398_Q;
    public float field_6397_R;
    protected boolean field_708_N;
    public int field_6396_T;
    public float field_6395_U;
    public float field_705_Q;
    public float field_704_R;
    public float field_703_S;
    protected int field_747_b;
    protected double field_746_c;
    protected double field_745_d;
    protected double field_744_e;
    protected double field_743_f;
    protected double field_742_g;
    float field_6404_ae;
    private int field_4120_b;
    protected int field_701_U;
    protected float field_700_V;
    protected float field_699_W;
    protected float field_698_X;
    protected boolean field_697_Y;
    protected float field_696_Z;
    protected float field_6401_al;
    private Entity field_741_h;
    private int field_740_i;
}
