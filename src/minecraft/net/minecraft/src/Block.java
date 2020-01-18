package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.util.ArrayList;
import java.util.Random;

public class Block
{

    protected Block(int i, Material material)
    {
        stepSound = soundPowderFootstep;
        field_357_bm = 1.0F;
        slipperiness = 0.6F;
        if(blocksList[i] != null)
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Slot ").append(i).append(" is already occupied by ").append(blocksList[i]).append(" when adding ").append(this).toString());
        } else
        {
            blockMaterial = material;
            blocksList[i] = this;
            blockID_00 = i;
            setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
            field_343_p[i] = allowsAttachment();
            lightOpacity[i] = allowsAttachment() ? 255 : 0;
            field_340_s[i] = unusedMethod();
            isBlockContainer[i] = false;
            return;
        }
    }

    protected Block(int i, int j, Material material)
    {
        this(i, material);
        blockIndexInTexture = j;
    }

    protected Block setStepSound(StepSound stepsound)
    {
        stepSound = stepsound;
        return this;
    }

    protected Block setLightOpacity(int i)
    {
        lightOpacity[blockID_00] = i;
        return this;
    }

    protected Block setLightValue_00(float f)
    {
        lightValue[blockID_00] = (int)(15F * f);
        return this;
    }

    protected Block setHardness(float f)
    {
        blockHardness = f * 3F;
        return this;
    }

    private boolean unusedMethod()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return true;
    }

    public int getRenderType()
    {
        return 0;
    }

    protected Block func_222_c(float f)
    {
        field_374_bd = f;
        if(blockHardness < f * 5F)
        {
            blockHardness = f * 5F;
        }
        return this;
    }

    protected void setTickOnLoad(boolean flag)
    {
        tickOnLoad[blockID_00] = flag;
    }

    public void setBlockBounds(float f, float f1, float f2, float f3, float f4, float f5)
    {
        minX = f;
        minY_00 = f1;
        minZ_00 = f2;
        maxX_00 = f3;
        maxY_00 = f4;
        maxZ_00 = f5;
    }

    public float getBlockBrightness(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return iblockaccess.getLightBrightness(i, j, k);
    }

    public boolean isSideInsideCoordinate(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        if(l == 0 && minY_00 > 0.0D)
        {
            return true;
        }
        if(l == 1 && maxY_00 < 1.0D)
        {
            return true;
        }
        if(l == 2 && minZ_00 > 0.0D)
        {
            return true;
        }
        if(l == 3 && maxZ_00 < 1.0D)
        {
            return true;
        }
        if(l == 4 && minX > 0.0D)
        {
            return true;
        }
        if(l == 5 && maxX_00 < 1.0D)
        {
            return true;
        } else
        {
            return !iblockaccess.doesBlockAllowAttachment(i, j, k);
        }
    }

    public int getBlockTexture(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return getBlockTextureFromSideAndMetadata(l, iblockaccess.getBlockMetadata_01(i, j, k));
    }

    public int getBlockTextureFromSideAndMetadata(int i, int j)
    {
        return getBlockTextureFromSide(i);
    }

    public int getBlockTextureFromSide(int i)
    {
        return blockIndexInTexture;
    }

    public AxisAlignedBB getSelectedBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return AxisAlignedBB.getBoundingBoxFromPool((double)i + minX, (double)j + minY_00, (double)k + minZ_00, (double)i + maxX_00, (double)j + maxY_00, (double)k + maxZ_00);
    }

    public void getCollidingBoundingBoxes(World world, int i, int j, int k, AxisAlignedBB axisalignedbb, ArrayList arraylist)
    {
        AxisAlignedBB axisalignedbb1 = getCollisionBoundingBoxFromPool(world, i, j, k);
        if(axisalignedbb1 != null && axisalignedbb.intersectsWith(axisalignedbb1))
        {
            arraylist.add(axisalignedbb1);
        }
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int i, int j, int k)
    {
        return AxisAlignedBB.getBoundingBoxFromPool((double)i + minX, (double)j + minY_00, (double)k + minZ_00, (double)i + maxX_00, (double)j + maxY_00, (double)k + maxZ_00);
    }

    public boolean allowsAttachment()
    {
        return true;
    }

    public boolean canCollideCheck(int i, boolean flag)
    {
        return isCollidable();
    }

    public boolean isCollidable()
    {
        return true;
    }

    public void updateTick(World world, int i, int j, int k, Random random)
    {
    }

    public void randomDisplayTick(World world, int i, int j, int k, Random random)
    {
    }

    public void onBlockDestroyedByPlayer(World world, int i, int j, int k, int l)
    {
    }

    public void onNeighborBlockChange(World world, int i, int j, int k, int l)
    {
    }

    public int tickRate()
    {
        return 10;
    }

    public void onBlockAdded(World world, int i, int j, int k)
    {
    }

    public void onBlockRemoval(World world, int i, int j, int k)
    {
    }

    public int quantityDropped(Random random)
    {
        return 1;
    }

    public int idDropped(int i, Random random)
    {
        return blockID_00;
    }

    public float func_225_a(EntityPlayer entityplayer)
    {
        if(entityplayer.instaBreak)
        {
            return 1000000000000.0F;
        }
        if(field_374_bd < 0.0F)
        {
            return 0.0F;
        }
        if(!entityplayer.func_454_b(this))
        {
            return 1.0F / field_374_bd / 100F;
        } else
        {
            return entityplayer.getCurrentPlayerStrVsBlock(this) / field_374_bd / 30F;
        }
    }

    public void dropBlockAsItem(World world, int i, int j, int k, int l)
    {
        dropBlockAsItemWithChance(world, i, j, k, l, 1.0F);
    }

    public void dropBlockAsItemWithChance(World world, int i, int j, int k, int l, float f)
    {
        if(world.multiplayerWorld)
        {
            return;
        }
        int i1 = quantityDropped(world.rand_00);
        for(int j1 = 0; j1 < i1; j1++)
        {
            if(world.rand_00.nextFloat() > f)
            {
                continue;
            }
            int k1 = idDropped(l, world.rand_00);
            if(k1 > 0)
            {
                float f1 = 0.7F;
                double d = (double)(world.rand_00.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
                double d1 = (double)(world.rand_00.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
                double d2 = (double)(world.rand_00.nextFloat() * f1) + (double)(1.0F - f1) * 0.5D;
                EntityItem entityitem = new EntityItem(world, (double)i + d, (double)j + d1, (double)k + d2, new ItemStack(k1));
                entityitem.field_805_c = 10;
                world.entityJoinedWorld(entityitem);
            }
        }

    }

    public float func_227_a(Entity entity)
    {
        return blockHardness / 5F;
    }

    public MovingObjectPosition collisionRayTrace(World world, int i, int j, int k, Vec3D vec3d, Vec3D vec3d1)
    {
        setBlockBoundsBasedOnState(world, i, j, k);
        vec3d = vec3d.addVector(-i, -j, -k);
        vec3d1 = vec3d1.addVector(-i, -j, -k);
        Vec3D vec3d2 = vec3d.getIntermediateWithXValue(vec3d1, minX);
        Vec3D vec3d3 = vec3d.getIntermediateWithXValue(vec3d1, maxX_00);
        Vec3D vec3d4 = vec3d.getIntermediateWithYValue(vec3d1, minY_00);
        Vec3D vec3d5 = vec3d.getIntermediateWithYValue(vec3d1, maxY_00);
        Vec3D vec3d6 = vec3d.getIntermediateWithZValue(vec3d1, minZ_00);
        Vec3D vec3d7 = vec3d.getIntermediateWithZValue(vec3d1, maxZ_00);
        if(!isVecInsideYZBounds(vec3d2))
        {
            vec3d2 = null;
        }
        if(!isVecInsideYZBounds(vec3d3))
        {
            vec3d3 = null;
        }
        if(!isVecInsideXZBounds(vec3d4))
        {
            vec3d4 = null;
        }
        if(!isVecInsideXZBounds(vec3d5))
        {
            vec3d5 = null;
        }
        if(!isVecInsideXYBounds(vec3d6))
        {
            vec3d6 = null;
        }
        if(!isVecInsideXYBounds(vec3d7))
        {
            vec3d7 = null;
        }
        Vec3D vec3d8 = null;
        if(vec3d2 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d2) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d2;
        }
        if(vec3d3 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d3) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d3;
        }
        if(vec3d4 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d4) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d4;
        }
        if(vec3d5 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d5) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d5;
        }
        if(vec3d6 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d6) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d6;
        }
        if(vec3d7 != null && (vec3d8 == null || vec3d.distanceTo_00(vec3d7) < vec3d.distanceTo_00(vec3d8)))
        {
            vec3d8 = vec3d7;
        }
        if(vec3d8 == null)
        {
            return null;
        }
        byte byte0 = -1;
        if(vec3d8 == vec3d2)
        {
            byte0 = 4;
        }
        if(vec3d8 == vec3d3)
        {
            byte0 = 5;
        }
        if(vec3d8 == vec3d4)
        {
            byte0 = 0;
        }
        if(vec3d8 == vec3d5)
        {
            byte0 = 1;
        }
        if(vec3d8 == vec3d6)
        {
            byte0 = 2;
        }
        if(vec3d8 == vec3d7)
        {
            byte0 = 3;
        }
        return new MovingObjectPosition(i, j, k, byte0, vec3d8.addVector(i, j, k));
    }

    private boolean isVecInsideYZBounds(Vec3D vec3d)
    {
        if(vec3d == null)
        {
            return false;
        } else
        {
            return vec3d.yCoord_00 >= minY_00 && vec3d.yCoord_00 <= maxY_00 && vec3d.zCoord_00 >= minZ_00 && vec3d.zCoord_00 <= maxZ_00;
        }
    }

    private boolean isVecInsideXZBounds(Vec3D vec3d)
    {
        if(vec3d == null)
        {
            return false;
        } else
        {
            return vec3d.xCoord_00 >= minX && vec3d.xCoord_00 <= maxX_00 && vec3d.zCoord_00 >= minZ_00 && vec3d.zCoord_00 <= maxZ_00;
        }
    }

    private boolean isVecInsideXYBounds(Vec3D vec3d)
    {
        if(vec3d == null)
        {
            return false;
        } else
        {
            return vec3d.xCoord_00 >= minX && vec3d.xCoord_00 <= maxX_00 && vec3d.yCoord_00 >= minY_00 && vec3d.yCoord_00 <= maxY_00;
        }
    }

    public void onBlockDestroyedByExplosion(World world, int i, int j, int k)
    {
    }

    public int func_234_g()
    {
        return 0;
    }

    public boolean canPlaceBlockAt(World world, int i, int j, int k)
    {
        int l = world.getBlockId(i, j, k);
        return l == 0 || blocksList[l].blockMaterial.getIsLiquid();
    }

    public boolean blockActivated(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
        return false;
    }

    public void onEntityWalking(World world, int i, int j, int k, Entity entity)
    {
    }

    public void onBlockPlaced(World world, int i, int j, int k, int l)
    {
    }

    public void onBlockClicked(World world, int i, int j, int k, EntityPlayer entityplayer)
    {
    }

    public void velocityToAddToEntity(World world, int i, int j, int k, Entity entity, Vec3D vec3d)
    {
    }

    public void setBlockBoundsBasedOnState(IBlockAccess iblockaccess, int i, int j, int k)
    {
    }

    public int colorMultiplier(IBlockAccess iblockaccess, int i, int j, int k)
    {
        return 0xffffff;
    }

    public boolean isPoweringTo(IBlockAccess iblockaccess, int i, int j, int k, int l)
    {
        return false;
    }

    public boolean canProvidePower()
    {
        return false;
    }

    public void onEntityCollidedWithBlock(World world, int i, int j, int k, Entity entity)
    {
    }

    public boolean isIndirectlyPoweringTo(World world, int i, int j, int k, int l)
    {
        return false;
    }

    public void func_237_e()
    {
    }

    public void func_220_a_(World world, int i, int j, int k, int l)
    {
        dropBlockAsItem(world, i, j, k, l);
    }

    public boolean canBlockStay(World world, int i, int j, int k)
    {
        return true;
    }

    public void onBlockPlacedBy(World world, int i, int j, int k, EntityLiving entityliving)
    {
    }

    static Class _mthclass$(String s)
    {
        try
        {
            return Class.forName(s);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new NoClassDefFoundError(classnotfoundexception.getMessage());
        }
    }

    public static final StepSound soundPowderFootstep;
    public static final StepSound field_6340_e;
    public static final StepSound field_6339_f;
    public static final StepSound field_6338_g;
    public static final StepSound field_6337_h;
    public static final StepSound field_6336_i;
    public static final StepSound field_6335_j;
    public static final StepSound field_6334_k;
    public static final StepSound field_6333_l;
    public static final Block blocksList[];
    public static final boolean tickOnLoad[] = new boolean[256];
    public static final boolean field_343_p[] = new boolean[256];
    public static final boolean isBlockContainer[] = new boolean[256];
    public static final int lightOpacity[] = new int[256];
    public static final boolean field_340_s[] = new boolean[256];
    public static final int lightValue[] = new int[256];
    public static final Block stone;
    public static final BlockGrass grass;
    public static final Block dirt;
    public static final Block cobblestone;
    public static final Block planks;
    public static final Block sapling;
    public static final Block bedrock;
    public static final Block waterStill;
    public static final Block waterMoving;
    public static final Block lavaStill;
    public static final Block lavaMoving;
    public static final Block sand_00;
    public static final Block gravel;
    public static final Block oreGold;
    public static final Block oreIron;
    public static final Block oreCoal;
    public static final Block wood_00;
    public static final BlockLeaves leaves;
    public static final Block sponge_00;
    public static final Block glass;
    public static final Block field_6353_N = null;
    public static final Block field_6352_O = null;
    public static final Block field_6351_P = null;
    public static final Block field_6350_Q = null;
    public static final Block field_6349_R = null;
    public static final Block field_6348_S = null;
    public static final Block field_6347_T = null;
    public static final Block field_6346_U = null;
    public static final Block field_6345_V = null;
    public static final Block field_6344_W = null;
    public static final Block field_6343_X = null;
    public static final Block field_6342_Y = null;
    public static final Block field_6341_Z = null;
    public static final Block field_6355_aa = null;
    public static final Block cloth;
    public static final Block field_6354_ac = null;
    public static final BlockFlower plantYellow;
    public static final BlockFlower plantRed;
    public static final BlockFlower mushroomBrown;
    public static final BlockFlower mushroomRed;
    public static final Block blockGold;
    public static final Block blockSteel;
    public static final Block stairDouble;
    public static final Block stairSingle;
    public static final Block brick;
    public static final Block tnt_00;
    public static final Block bookShelf;
    public static final Block cobblestoneMossy;
    public static final Block obsidian;
    public static final Block torchWood;
    public static final BlockFire fire_01;
    public static final Block mobSpawner;
    public static final Block field_4059_au;
    public static final Block crate;
    public static final Block redstoneWire;
    public static final Block oreDiamond;
    public static final Block blockDiamond;
    public static final Block workbench;
    public static final Block crops;
    public static final Block tilledField;
    public static final Block stoneOvenIdle;
    public static final Block stoneOvenActive;
    public static final Block pressurePlateWoodActive;
    public static final Block doorWood;
    public static final Block ladder;
    public static final Block minecartTrack;
    public static final Block field_4069_aI;
    public static final Block field_4068_aJ;
    public static final Block field_4067_aK;
    public static final Block field_4066_aL;
    public static final Block doorSteel_00;
    public static final Block field_4065_aN;
    public static final Block oreRedstone;
    public static final Block oreRedstoneGlowing;
    public static final Block torchRedstoneIdle;
    public static final Block torchRedstoneActive;
    public static final Block field_4064_aS;
    public static final Block snow_00;
    public static final Block ice;
    public static final Block blockSnow;
    public static final Block cactus_00;
    public static final Block blockClay;
    public static final Block reed_00;
    public static final Block jukebox;
    public static final Block field_4057_ba;
    public static final Block field_4055_bb;
    public static final Block field_4053_bc;
    public static final Block field_4051_bd;
    public static final Block field_4049_be;
    public static final BlockPortal field_4047_bf;
    public static final Block field_4045_bg;
    public int blockIndexInTexture;
    public final int blockID_00;
    protected float field_374_bd;
    protected float blockHardness;
    public double minX;
    public double minY_00;
    public double minZ_00;
    public double maxX_00;
    public double maxY_00;
    public double maxZ_00;
    public StepSound stepSound;
    public float field_357_bm;
    public final Material blockMaterial;
    public float slipperiness;

    static 
    {
        soundPowderFootstep = new StepSound("stone", 1.0F, 1.0F);
        field_6340_e = new StepSound("wood", 1.0F, 1.0F);
        field_6339_f = new StepSound("gravel", 1.0F, 1.0F);
        field_6338_g = new StepSound("grass", 1.0F, 1.0F);
        field_6337_h = new StepSound("stone", 1.0F, 1.0F);
        field_6336_i = new StepSound("stone", 1.0F, 1.5F);
        field_6335_j = new StepSoundStone("stone", 1.0F, 1.0F);
        field_6334_k = new StepSound("cloth", 1.0F, 1.0F);
        field_6333_l = new StepSoundSand("sand", 1.0F, 1.0F);
        blocksList = new Block[256];
        stone = (new BlockStone(1, 1)).func_222_c(1.5F).setHardness(10F).setStepSound(field_6337_h);
        grass = (BlockGrass)(new BlockGrass(2)).func_222_c(0.6F).setStepSound(field_6338_g);
        dirt = (new BlockDirt(3, 2)).func_222_c(0.5F).setStepSound(field_6339_f);
        cobblestone = (new Block(4, 16, Material.rock)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        planks = (new Block(5, 4, Material.wood)).func_222_c(2.0F).setHardness(5F).setStepSound(field_6340_e);
        sapling = (new BlockSapling(6, 15)).func_222_c(0.0F).setStepSound(field_6338_g);
        bedrock = (new Block(7, 17, Material.rock)).func_222_c(-1F).setHardness(6000000F).setStepSound(field_6337_h);
        waterStill = (new BlockFlowing(8, Material.water)).func_222_c(100F).setLightOpacity(3);
        waterMoving = (new BlockStationary(9, Material.water)).func_222_c(100F).setLightOpacity(3);
        lavaStill = (new BlockFlowing(10, Material.lava)).func_222_c(0.0F).setLightValue_00(1.0F).setLightOpacity(255);
        lavaMoving = (new BlockStationary(11, Material.lava)).func_222_c(100F).setLightValue_00(1.0F).setLightOpacity(255);
        sand_00 = (new BlockSand(12, 18)).func_222_c(0.5F).setStepSound(field_6333_l);
        gravel = (new BlockGravel(13, 19)).func_222_c(0.6F).setStepSound(field_6339_f);
        oreGold = (new BlockOre(14, 32)).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        oreIron = (new BlockOre(15, 33)).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        oreCoal = (new BlockOre(16, 34)).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        wood_00 = (new BlockLog(17)).func_222_c(2.0F).setStepSound(field_6340_e);
        leaves = (BlockLeaves)(new BlockLeaves(18, 52)).func_222_c(0.2F).setLightOpacity(1).setStepSound(field_6338_g);
        sponge_00 = (new BlockSponge(19)).func_222_c(0.6F).setStepSound(field_6338_g);
        glass = (new BlockGlass(20, 49, Material.field_4263_o, false)).func_222_c(0.3F).setStepSound(field_6335_j);
        cloth = (new Block(35, 64, Material.field_4264_k)).func_222_c(0.8F).setStepSound(field_6334_k);
        plantYellow = (BlockFlower)(new BlockFlower(37, 13)).func_222_c(0.0F).setStepSound(field_6338_g);
        plantRed = (BlockFlower)(new BlockFlower(38, 12)).func_222_c(0.0F).setStepSound(field_6338_g);
        mushroomBrown = (BlockFlower)(new BlockMushroom(39, 29)).func_222_c(0.0F).setStepSound(field_6338_g).setLightValue_00(0.125F);
        mushroomRed = (BlockFlower)(new BlockMushroom(40, 28)).func_222_c(0.0F).setStepSound(field_6338_g);
        blockGold = (new BlockOreBlock(41, 39)).func_222_c(3F).setHardness(10F).setStepSound(field_6336_i);
        blockSteel = (new BlockOreBlock(42, 38)).func_222_c(5F).setHardness(10F).setStepSound(field_6336_i);
        stairDouble = (new BlockStep(43, true)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        stairSingle = (new BlockStep(44, false)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        brick = (new Block(45, 7, Material.rock)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        tnt_00 = (new BlockTNT(46, 8)).func_222_c(0.0F).setStepSound(field_6338_g);
        bookShelf = (new BlockBookshelf(47, 35)).func_222_c(1.5F).setStepSound(field_6340_e);
        cobblestoneMossy = (new Block(48, 36, Material.rock)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        obsidian = (new BlockObsidian(49, 37)).func_222_c(10F).setHardness(2000F).setStepSound(field_6337_h);
        torchWood = (new BlockTorch(50, 80)).func_222_c(0.0F).setLightValue_00(0.9375F).setStepSound(field_6340_e);
        fire_01 = (BlockFire)(new BlockFire(51, 31)).func_222_c(0.0F).setLightValue_00(1.0F).setStepSound(field_6340_e);
        mobSpawner = (new BlockMobSpawner(52, 65)).func_222_c(5F).setStepSound(field_6336_i);
        field_4059_au = new BlockStairs(53, planks);
        crate = (new BlockChest(54)).func_222_c(2.5F).setStepSound(field_6340_e);
        redstoneWire = (new BlockRedstoneWire(55, 84)).func_222_c(0.0F).setStepSound(soundPowderFootstep);
        oreDiamond = (new BlockOre(56, 50)).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        blockDiamond = (new BlockOreBlock(57, 40)).func_222_c(5F).setHardness(10F).setStepSound(field_6336_i);
        workbench = (new BlockWorkbench(58)).func_222_c(2.5F).setStepSound(field_6340_e);
        crops = (new BlockCrops(59, 88)).func_222_c(0.0F).setStepSound(field_6338_g);
        tilledField = (new BlockSoil(60)).func_222_c(0.6F).setStepSound(field_6339_f);
        stoneOvenIdle = (new BlockFurnace(61, false)).func_222_c(3.5F).setStepSound(field_6337_h);
        stoneOvenActive = (new BlockFurnace(62, true)).func_222_c(3.5F).setStepSound(field_6337_h).setLightValue_00(0.875F);
        pressurePlateWoodActive = (new BlockSign(63, TileEntitySign.class, true)).func_222_c(1.0F).setStepSound(field_6340_e);
        doorWood = (new BlockDoor(64, Material.wood)).func_222_c(3F).setStepSound(field_6340_e);
        ladder = (new BlockLadder(65, 83)).func_222_c(0.4F).setStepSound(field_6340_e);
        minecartTrack = (new BlockMinecartTrack(66, 128)).func_222_c(0.7F).setStepSound(field_6336_i);
        field_4069_aI = new BlockStairs(67, cobblestone);
        field_4068_aJ = (new BlockSign(68, TileEntitySign.class, false)).func_222_c(1.0F).setStepSound(field_6340_e);
        field_4067_aK = (new BlockLever(69, 96)).func_222_c(0.5F).setStepSound(field_6340_e);
        field_4066_aL = (new BlockPressurePlate(70, stone.blockIndexInTexture, EnumMobType.mobs)).func_222_c(0.5F).setStepSound(field_6337_h);
        doorSteel_00 = (new BlockDoor(71, Material.iron)).func_222_c(5F).setStepSound(field_6336_i);
        field_4065_aN = (new BlockPressurePlate(72, planks.blockIndexInTexture, EnumMobType.everything)).func_222_c(0.5F).setStepSound(field_6340_e);
        oreRedstone = (new BlockRedstoneOre(73, 51, false)).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        oreRedstoneGlowing = (new BlockRedstoneOre(74, 51, true)).setLightValue_00(0.625F).func_222_c(3F).setHardness(5F).setStepSound(field_6337_h);
        torchRedstoneIdle = (new BlockRedstoneTorch(75, 115, false)).func_222_c(0.0F).setStepSound(field_6340_e);
        torchRedstoneActive = (new BlockRedstoneTorch(76, 99, true)).func_222_c(0.0F).setLightValue_00(0.5F).setStepSound(field_6340_e);
        field_4064_aS = (new BlockButton(77, stone.blockIndexInTexture)).func_222_c(0.5F).setStepSound(field_6337_h);
        snow_00 = (new BlockSnow(78, 66)).func_222_c(0.1F).setStepSound(field_6334_k);
        ice = (new BlockIce(79, 67)).func_222_c(0.5F).setLightOpacity(3).setStepSound(field_6335_j);
        blockSnow = (new BlockSnowBlock(80, 66)).func_222_c(0.2F).setStepSound(field_6334_k);
        cactus_00 = (new BlockCactus(81, 70)).func_222_c(0.4F).setStepSound(field_6334_k);
        blockClay = (new BlockClay(82, 72)).func_222_c(0.6F).setStepSound(field_6339_f);
        reed_00 = (new BlockReed(83, 73)).func_222_c(0.0F).setStepSound(field_6338_g);
        jukebox = (new BlockJukeBox(84, 74)).func_222_c(2.0F).setHardness(10F).setStepSound(field_6337_h);
        field_4057_ba = (new BlockFence(85, 4)).func_222_c(2.0F).setHardness(5F).setStepSound(field_6340_e);
        field_4055_bb = (new BlockPumpkin(86, 102, false)).func_222_c(1.0F).setStepSound(field_6340_e);
        field_4053_bc = (new BlockBloodStone(87, 103)).func_222_c(0.4F).setStepSound(field_6337_h);
        field_4051_bd = (new BlockSlowSand(88, 104)).func_222_c(0.5F).setStepSound(field_6333_l);
        field_4049_be = (new BlockLightStone(89, 105, Material.field_4263_o)).func_222_c(0.3F).setStepSound(field_6335_j).setLightValue_00(1.0F);
        field_4047_bf = (BlockPortal)(new BlockPortal(90, 14)).func_222_c(-1F).setStepSound(field_6335_j).setLightValue_00(0.75F);
        field_4045_bg = (new BlockPumpkin(91, 102, true)).func_222_c(1.0F).setStepSound(field_6340_e).setLightValue_00(1.0F);
        for(int i = 0; i < 256; i++)
        {
            if(blocksList[i] != null)
            {
                Item.itemsList[i] = new ItemBlock(i - 256);
            }
        }

    }
}
