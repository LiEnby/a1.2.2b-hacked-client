package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.List;
import java.util.Random;

public class EntityPlayer extends EntityLiving
{
    public boolean instaBreak = false;

    public EntityPlayer(World world)
    {
        super(world);
        inventory = new InventoryPlayer(this);
        field_6418_f = 0;
        field_776_d = 0;
        field_773_g = false;
        field_772_h = 0;
        field_781_a = 0;
        field_4128_n = null;
        yOffset_00 = 1.62F;
        func_365_c((double)world.spawnX + 0.5D, world.field_4210_n + 1, (double)world.spawnZ + 0.5D, 0.0F, 0.0F);
        health_00 = 20;
        field_6406_C = "humanoid";
        field_6407_B = 180F;
        field_628_aS = 20;
        field_6409_z = "/mob/char.png";
    }

    public void func_350_p()
    {
        super.func_350_p();
        field_775_e = field_774_f;
        field_774_f = 0.0F;
    }

    public void func_374_q()
    {
        yOffset_00 = 1.62F;
        setSize(0.6F, 1.8F);
        super.func_374_q();
        health_00 = 20;
        deathTime = 0;
    }

    protected void func_418_b_()
    {
        if(field_773_g)
        {
            field_772_h++;
            if(field_772_h == 8)
            {
                field_772_h = 0;
                field_773_g = false;
            }
        } else
        {
            field_772_h = 0;
        }
        field_718_D = (float)field_772_h / 8F;
    }

    public void onLivingUpdate()
    {
        if(worldObj_09.field_1039_l == 0 && health_00 < 20 && (field_4076_aW % 20) * 4 == 0)
        {
            heal(1);
        }
        inventory.decrementAnimations();
        field_775_e = field_774_f;
        super.onLivingUpdate();
        float f = MathHelper.sqrt_double(motionX * motionX + motionZ * motionZ);
        float f1 = (float)Math.atan(-motionY * 0.20000000298023224D) * 15F;
        if(f > 0.1F)
        {
            f = 0.1F;
        }
        if(!onGround_00 || health_00 <= 0)
        {
            f = 0.0F;
        }
        if(onGround_00 || health_00 <= 0)
        {
            f1 = 0.0F;
        }
        field_774_f += (f - field_774_f) * 0.4F;
        field_6397_R += (f1 - field_6397_R) * 0.8F;
        if(health_00 > 0)
        {
            List list = worldObj_09.getEntitiesWithinAABBExcludingEntity(this, boundingBox.expands(1.0D, 0.0D, 1.0D));
            if(list != null)
            {
                for(int i = 0; i < list.size(); i++)
                {
                    func_451_h((Entity)list.get(i));
                }

            }
        }
    }

    private void func_451_h(Entity entity)
    {
        entity.onCollideWithPlayer(this);
    }

    public int func_6417_t()
    {
        return field_776_d;
    }

    public void onDeath(Entity entity)
    {
        setSize(0.2F, 0.2F);
        setPosition(posX, posY, posZ);
        motionY = 0.10000000149011612D;
        if(field_771_i.equals("Notch"))
        {
            func_444_a(new ItemStack(Item.appleRed, 1), true);
        }
        inventory.dropAllItems();
        if(entity != null)
        {
            motionX = -MathHelper.cos_00(((field_6399_N + rotationYaw) * 3.141593F) / 180F) * 0.1F;
            motionZ = -MathHelper.sin_00(((field_6399_N + rotationYaw) * 3.141593F) / 180F) * 0.1F;
        } else
        {
            motionX = motionZ = 0.0D;
        }
        yOffset_00 = 0.1F;
    }

    public void func_364_b(Entity entity, int i)
    {
        field_776_d += i;
    }

    public void func_449_a(ItemStack itemstack)
    {
        func_444_a(itemstack, false);
    }

    public void func_444_a(ItemStack itemstack, boolean flag)
    {
        if(itemstack == null)
        {
            return;
        }
        EntityItem entityitem = new EntityItem(worldObj_09, posX, (posY - 0.30000001192092896D) + (double)func_373_s(), posZ, itemstack);
        entityitem.field_805_c = 40;
        float f = 0.1F;
        if(flag)
        {
            float f2 = rand_05.nextFloat() * 0.5F;
            float f4 = rand_05.nextFloat() * 3.141593F * 2.0F;
            entityitem.motionX = -MathHelper.sin_00(f4) * f2;
            entityitem.motionZ = MathHelper.cos_00(f4) * f2;
            entityitem.motionY = 0.20000000298023224D;
        } else
        {
            float f1 = 0.3F;
            entityitem.motionX = -MathHelper.sin_00((rotationYaw / 180F) * 3.141593F) * MathHelper.cos_00((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionZ = MathHelper.cos_00((rotationYaw / 180F) * 3.141593F) * MathHelper.cos_00((rotationPitch / 180F) * 3.141593F) * f1;
            entityitem.motionY = -MathHelper.sin_00((rotationPitch / 180F) * 3.141593F) * f1 + 0.1F;
            f1 = 0.02F;
            float f3 = rand_05.nextFloat() * 3.141593F * 2.0F;
            f1 *= rand_05.nextFloat();
            entityitem.motionX += Math.cos(f3) * (double)f1;
            entityitem.motionY += (rand_05.nextFloat() - rand_05.nextFloat()) * 0.1F;
            entityitem.motionZ += Math.sin(f3) * (double)f1;
        }
        func_446_a(entityitem);
    }

    protected void func_446_a(EntityItem entityitem)
    {
        worldObj_09.entityJoinedWorld(entityitem);
    }

    public float getCurrentPlayerStrVsBlock(Block block)
    {
        float f = inventory.getStrVsBlock(block);
        if(isInsideOfMaterial(Material.water))
        {
            f /= 5F;
        }
        if(!onGround_00)
        {
            f /= 5F;
        }
        return f;
    }

    public boolean func_454_b(Block block)
    {
        return inventory.canHarvestBlock(block);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        NBTTagList nbttaglist = nbttagcompound.getTagList("Inventory");
        inventory.readFromNBT(nbttaglist);
        field_4129_m = nbttagcompound.getInteger("Dimension");
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setTag("Inventory", inventory.writeToNBT_00(new NBTTagList()));
        nbttagcompound.setInteger("Dimension", field_4129_m);
    }

    public void func_452_a(IInventory iinventory)
    {
    }

    public void func_445_l()
    {
    }

    public void func_443_a_(Entity entity, int i)
    {
    }

    public float func_373_s()
    {
        return 0.12F;
    }

    public boolean attackEntity(Entity entity, int i)
    {
        field_701_U = 0;
        if(health_00 <= 0)
        {
            return false;
        }
        if((entity instanceof EntityMobs) || (entity instanceof EntityArrow))
        {
            if(worldObj_09.field_1039_l == 0)
            {
                i = 0;
            }
            if(worldObj_09.field_1039_l == 1)
            {
                i = i / 3 + 1;
            }
            if(worldObj_09.field_1039_l == 3)
            {
                i = (i * 3) / 2;
            }
        }
        if(i == 0)
        {
            return false;
        } else
        {
            return super.attackEntity(entity, i);
        }
    }

    protected void func_4044_a(int i)
    {
        int j = 25 - inventory.getTotalArmorValue();
        int k = i * j + field_781_a;
        inventory.damageArmor(i);
        i = k / 25;
        field_781_a = k % 25;
        super.func_4044_a(i);
    }

    public void func_453_a(TileEntityFurnace tileentityfurnace)
    {
    }

    public void func_4052_a(TileEntitySign tileentitysign)
    {
    }

    public void func_6415_a_(Entity entity)
    {
    }

    public ItemStack func_6416_v()
    {
        return inventory.getCurrentItem();
    }

    public void func_448_u()
    {
        inventory.setInventorySlotContents(inventory.currentItem_00, null);
    }

    public double func_388_v()
    {
        return (double)(yOffset_00 - 0.5F);
    }

    public void func_457_w()
    {
        field_772_h = -1;
        field_773_g = true;
    }

    public void func_463_a(Entity entity)
    {
        int i = inventory.func_502_a(entity);
        if(i > 0)
        {
            entity.attackEntity(this, i);
            ItemStack itemstack = func_6416_v();
            if(itemstack != null && (entity instanceof EntityLiving))
            {
                itemstack.hitEntity((EntityLiving)entity);
                if(itemstack.stackSize <= 0)
                {
                    itemstack.func_1097_a(this);
                    func_448_u();
                }
            }
        }
    }

    public InventoryPlayer inventory;
    public byte field_6418_f;
    public int field_776_d;
    public float field_775_e;
    public float field_774_f;
    public boolean field_773_g;
    public int field_772_h;
    public String field_771_i;
    public int field_4129_m;
    private int field_781_a;
    public EntityFish field_4128_n;
}
