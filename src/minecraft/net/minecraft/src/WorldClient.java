package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.File;
import java.util.*;

public class WorldClient extends World
{

    public WorldClient(NetClientHandler netclienthandler, long l, int i)
    {
        super("MpServer", WorldProvider.func_4101_a(i), l);
        field_1057_z = new LinkedList();
        field_1056_C = false;
        field_1055_D = new MCHashTable();
        field_1054_E = new HashSet();
        field_1053_F = new HashSet();
        field_1052_A = netclienthandler;
        spawnX = 8;
        field_4210_n = 64;
        spawnZ = 8;
    }

    public void tick()
    {
        worldTime++;
        int i = calculateSkylightSubtracted(1.0F);
        if(i != skylightSubtracted)
        {
            skylightSubtracted = i;
            for(int j = 0; j < field_1032_s.size(); j++)
            {
                ((IWorldAccess)field_1032_s.get(j)).func_936_e();
            }

        }
        for(int k = 0; k < 10 && !field_1053_F.isEmpty(); k++)
        {
            Entity entity = (Entity)field_1053_F.iterator().next();
            entityJoinedWorld(entity);
        }

        field_1052_A.func_848_a();
        for(int l = 0; l < field_1057_z.size(); l++)
        {
            WorldBlockPositionType worldblockpositiontype = (WorldBlockPositionType)field_1057_z.get(l);
            if(--worldblockpositiontype.field_1206_d == 0)
            {
                super.func_643_a(worldblockpositiontype.field_1202_a, worldblockpositiontype.field_1201_b, worldblockpositiontype.field_1207_c, worldblockpositiontype.field_1205_e, worldblockpositiontype.field_1204_f);
                super.func_665_h(worldblockpositiontype.field_1202_a, worldblockpositiontype.field_1201_b, worldblockpositiontype.field_1207_c);
                field_1057_z.remove(l--);
            }
        }

    }

    public void func_711_c(int i, int j, int k, int l, int i1, int j1)
    {
        for(int k1 = 0; k1 < field_1057_z.size(); k1++)
        {
            WorldBlockPositionType worldblockpositiontype = (WorldBlockPositionType)field_1057_z.get(k1);
            if(worldblockpositiontype.field_1202_a >= i && worldblockpositiontype.field_1201_b >= j && worldblockpositiontype.field_1207_c >= k && worldblockpositiontype.field_1202_a <= l && worldblockpositiontype.field_1201_b <= i1 && worldblockpositiontype.field_1207_c <= j1)
            {
                field_1057_z.remove(k1--);
            }
        }

    }

    protected IChunkProvider func_4081_a(File file)
    {
        field_1058_B = new ChunkProviderClient(this);
        return field_1058_B;
    }

    public void func_4076_b()
    {
        spawnX = 8;
        field_4210_n = 64;
        spawnZ = 8;
    }

    protected void func_4080_j()
    {
    }

    public void scheduleBlockUpdate(int i, int j, int k, int l)
    {
    }

    public boolean TickUpdates(boolean flag)
    {
        return false;
    }

    public void func_713_a(int i, int j, boolean flag)
    {
        if(flag)
        {
            field_1058_B.func_538_d(i, j);
        } else
        {
            field_1058_B.func_539_c(i, j);
        }
        if(!flag)
        {
            func_701_b(i * 16, 0, j * 16, i * 16 + 15, 128, j * 16 + 15);
        }
    }

    public boolean entityJoinedWorld(Entity entity)
    {
        boolean flag = super.entityJoinedWorld(entity);
        if(entity instanceof EntityPlayerSP)
        {
            field_1054_E.add(entity);
        }
        return flag;
    }

    public void func_607_d(Entity entity)
    {
        super.func_607_d(entity);
        if(entity instanceof EntityPlayerSP)
        {
            field_1054_E.remove(entity);
        }
    }

    protected void func_606_b(Entity entity)
    {
        super.func_606_b(entity);
        if(field_1053_F.contains(entity))
        {
            field_1053_F.remove(entity);
        }
    }

    protected void func_678_c(Entity entity)
    {
        super.func_678_c(entity);
        if(field_1054_E.contains(entity))
        {
            field_1053_F.add(entity);
        }
    }

    public void func_712_a(int i, Entity entity)
    {
        field_1054_E.add(entity);
        if(!entityJoinedWorld(entity))
        {
            field_1053_F.add(entity);
        }
        field_1055_D.addKey(i, entity);
    }

    public Entity func_709_b(int i)
    {
        return (Entity)field_1055_D.lookup(i);
    }

    public Entity func_710_c(int i)
    {
        Entity entity = (Entity)field_1055_D.removeObject(i);
        if(entity != null)
        {
            field_1054_E.remove(entity);
            func_607_d(entity);
        }
        return entity;
    }

    public boolean setBlockMetadata(int i, int j, int k, int l)
    {
        int i1 = getBlockId(i, j, k);
        int j1 = getBlockMetadata_01(i, j, k);
        if(super.setBlockMetadata(i, j, k, l))
        {
            field_1057_z.add(new WorldBlockPositionType(this, i, j, k, i1, j1));
            return true;
        } else
        {
            return false;
        }
    }

    public boolean func_643_a(int i, int j, int k, int l, int i1)
    {
        int j1 = getBlockId(i, j, k);
        int k1 = getBlockMetadata_01(i, j, k);
        if(super.func_643_a(i, j, k, l, i1))
        {
            field_1057_z.add(new WorldBlockPositionType(this, i, j, k, j1, k1));
            return true;
        } else
        {
            return false;
        }
    }

    public boolean setBlock(int i, int j, int k, int l)
    {
        int i1 = getBlockId(i, j, k);
        int j1 = getBlockMetadata_01(i, j, k);
        if(super.setBlock(i, j, k, l))
        {
            field_1057_z.add(new WorldBlockPositionType(this, i, j, k, i1, j1));
            return true;
        } else
        {
            return false;
        }
    }

    public boolean func_714_c(int i, int j, int k, int l, int i1)
    {
        func_711_c(i, j, k, i, j, k);
        if(super.func_643_a(i, j, k, l, i1))
        {
            notifyBlockChange(i, j, k, l);
            return true;
        } else
        {
            return false;
        }
    }

    public void func_698_b(int i, int j, int k, TileEntity tileentity)
    {
        if(field_1056_C)
        {
            return;
        } else
        {
            field_1052_A.func_847_a(new Packet59ComplexEntity(i, j, k, tileentity));
            return;
        }
    }

    public void func_660_k()
    {
        field_1052_A.func_847_a(new Packet255KickDisconnect("Quitting"));
    }

    private LinkedList field_1057_z;
    public NetClientHandler field_1052_A;
    private ChunkProviderClient field_1058_B;
    private boolean field_1056_C;
    private MCHashTable field_1055_D;
    private Set field_1054_E;
    private Set field_1053_F;
}
