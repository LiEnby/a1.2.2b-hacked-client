package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.PrintStream;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class EntityPlayerSP extends EntityPlayer
{

    public EntityPlayerSP(Minecraft minecraft, World world, Session session, int i)
    {
        super(world);
        field_4131_b = 20;
        field_4132_bn = false;
        field_788_bg = minecraft;
        field_4129_m = i;
        if(session != null && session.field_1666_b != null && session.field_1666_b.length() > 0)
        {
            field_622_aY = session.field_1666_b;
            System.out.println((new StringBuilder()).append("Loading texture ").append(field_622_aY).toString());
        }
        field_771_i = session.field_1666_b;
    }

    public void func_418_b_()
    {
        super.func_418_b_();
        field_700_V = field_787_a.field_1174_a;
        field_699_W = field_787_a.field_1173_b;
        field_697_Y = field_787_a.field_1176_d;
    }

    public void onLivingUpdate()
    {
        field_4133_d = field_4134_c;
        if(field_4132_bn)
        {
            if(field_4134_c == 0.0F)
            {
                field_788_bg.field_6301_A.func_337_a("portal.trigger", 1.0F, rand_05.nextFloat() * 0.4F + 0.8F);
            }
            field_4134_c += 0.0125F;
            if(field_4134_c >= 1.0F)
            {
                field_4134_c = 1.0F;
                field_4131_b = 10;
                field_788_bg.field_6301_A.func_337_a("portal.travel", 1.0F, rand_05.nextFloat() * 0.4F + 0.8F);
                field_788_bg.func_6237_k();
            }
            field_4132_bn = false;
        } else
        {
            if(field_4134_c > 0.0F)
            {
                field_4134_c -= 0.05F;
            }
            if(field_4134_c < 0.0F)
            {
                field_4134_c = 0.0F;
            }
        }
        if(field_4131_b > 0)
        {
            field_4131_b--;
        }
        field_787_a.func_797_a(this);
        if(fly && field_787_a.field_1175_e) {
            motionY -= 0.20000000000000001D;
        }
        else if(field_787_a.field_1175_e && field_635_aL < 0.2F)
        {
            field_635_aL = 0.2F;
        }
        super.onLivingUpdate();
    }

    public void func_458_k()
    {
        field_787_a.func_798_a();
    }

    public void func_460_a(int i, boolean flag)
    {
        field_787_a.func_796_a(i, flag);
    }

    public void writeEntityToNBT(NBTTagCompound nbttagcompound)
    {
        super.writeEntityToNBT(nbttagcompound);
        nbttagcompound.setInteger("Score", field_776_d);
    }

    public void readEntityFromNBT(NBTTagCompound nbttagcompound)
    {
        super.readEntityFromNBT(nbttagcompound);
        field_776_d = nbttagcompound.getInteger("Score");
    }

    public void func_452_a(IInventory iinventory)
    {
        field_788_bg.func_6272_a(new GuiChest(inventory, iinventory));
    }

    public void func_4052_a(TileEntitySign tileentitysign)
    {
        field_788_bg.func_6272_a(new GuiEditSign(tileentitysign));
    }

    public void func_445_l()
    {
        field_788_bg.func_6272_a(new GuiCrafting(inventory));
    }

    public void func_453_a(TileEntityFurnace tileentityfurnace)
    {
        field_788_bg.func_6272_a(new GuiFurnace(inventory, tileentityfurnace));
    }

    public void func_443_a_(Entity entity, int i)
    {
        field_788_bg.field_6321_h.func_1192_a(new EntityPickupFX(field_788_bg.field_6324_e, entity, this, -0.5F));
    }

    public int func_6419_n()
    {
        return inventory.getTotalArmorValue();
    }

    public void func_6415_a_(Entity entity)
    {
        if(entity.interact(this))
        {
            return;
        }
        ItemStack itemstack = func_6416_v();
        if(itemstack != null && (entity instanceof EntityLiving))
        {
            itemstack.useItemOnEntity((EntityLiving)entity);
            if(itemstack.stackSize <= 0)
            {
                itemstack.func_1097_a(this);
                func_448_u();
            }
        }
    }

    public void func_461_a(String s)
    {
    }

    public void func_6420_o()
    {
    }

    public boolean func_381_o()
    {
        return field_787_a.field_1175_e;
    }

    public void func_4039_q()
    {
        if(field_4131_b > 0)
        {
            field_4131_b = 10;
            return;
        } else
        {
            field_4132_bn = true;
            return;
        }
    }

    public MovementInput field_787_a;
    private Minecraft field_788_bg;
    public int field_4131_b;
    private boolean field_4132_bn;
    public float field_4134_c;
    public float field_4133_d;
}
