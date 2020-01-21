package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.io.*;
import java.net.*;
import java.util.Random;
import net.minecraft.client.Minecraft;

public class NetClientHandler extends NetHandler
{

    public NetClientHandler(Minecraft minecraft, String s, int i) throws IOException
    {
        disconnected = false;
        field_1210_g = false;
        rand_06 = new Random();
        mc_08 = minecraft;
        Hostname = s;
        Port = i;
        Socket socket = new Socket(InetAddress.getByName(s), i);
        netManager_02 = new NetworkManager(socket, "Client", this);
    }

    public void func_848_a()
    {
        if(disconnected)
        {
            return;
        } else
        {
            netManager_02.processReadPackets();
            return;
        }
    }

    public void func_4115_a(Packet1Login packet1login)
    {
        mc_08.field_6327_b = new PlayerControllerMP(mc_08, this);
        field_1211_f = new WorldClient(this, packet1login.field_4074_d, packet1login.field_4073_e);
        field_1211_f.multiplayerWorld = true;
        mc_08.func_6261_a(field_1211_f);
        mc_08.func_6272_a(new GuiDownloadTerrain(this));
        mc_08.field_6322_g.field_620_ab = packet1login.protocolVersion;
        System.out.println((new StringBuilder()).append("clientEntityId: ").append(packet1login.protocolVersion).toString());
    }

    public void handlePickupSpawn(Packet21PickupSpawn packet21pickupspawn)
    {
        double d = (double)packet21pickupspawn.xPosition_02 / 32D;
        double d1 = (double)packet21pickupspawn.yPosition_02 / 32D;
        double d2 = (double)packet21pickupspawn.zPosition_01 / 32D;
        EntityItem entityitem = new EntityItem(field_1211_f, d, d1, d2, new ItemStack(packet21pickupspawn.itemId, packet21pickupspawn.count));
        entityitem.motionX = (double)packet21pickupspawn.rotation / 128D;
        entityitem.motionY = (double)packet21pickupspawn.pitch_03 / 128D;
        entityitem.motionZ = (double)packet21pickupspawn.roll / 128D;
        entityitem.field_652_bd = packet21pickupspawn.xPosition_02;
        entityitem.field_650_be = packet21pickupspawn.yPosition_02;
        entityitem.field_648_bf = packet21pickupspawn.zPosition_01;
        field_1211_f.func_712_a(packet21pickupspawn.entityId_01, entityitem);
    }

    public void handleVehicleSpawn(Packet23VehicleSpawn packet23vehiclespawn)
    {
        double d = (double)packet23vehiclespawn.xPosition_05 / 32D;
        double d1 = (double)packet23vehiclespawn.yPosition_13 / 32D;
        double d2 = (double)packet23vehiclespawn.zPosition_13 / 32D;
        Entity obj = null;
        if(packet23vehiclespawn.type_02 == 10)
        {
            obj = new EntityMinecart(field_1211_f, d, d1, d2, 0);
        }
        if(packet23vehiclespawn.type_02 == 11)
        {
            obj = new EntityMinecart(field_1211_f, d, d1, d2, 1);
        }
        if(packet23vehiclespawn.type_02 == 12)
        {
            obj = new EntityMinecart(field_1211_f, d, d1, d2, 2);
        }
        if(packet23vehiclespawn.type_02 == 90)
        {
            obj = new EntityFish(field_1211_f, d, d1, d2);
        }
        if(packet23vehiclespawn.type_02 == 1)
        {
            obj = new EntityBoat(field_1211_f, d, d1, d2);
        }
        if(obj != null)
        {
            obj.field_652_bd = packet23vehiclespawn.xPosition_05;
            obj.field_650_be = packet23vehiclespawn.yPosition_13;
            obj.field_648_bf = packet23vehiclespawn.zPosition_13;
            obj.rotationYaw = 0.0F;
            obj.rotationPitch = 0.0F;
            obj.field_620_ab = packet23vehiclespawn.entityId_05;
            field_1211_f.func_712_a(packet23vehiclespawn.entityId_05, ((Entity) (obj)));
        }
    }

    public void func_6498_a(Packet28 packet28)
    {
        Entity entity = field_1211_f.func_709_b(packet28.field_6367_a);
        if(entity == null)
        {
            return;
        } else
        {
            entity.setVelocity((double)packet28.field_6366_b / 32000D, (double)packet28.field_6369_c / 32000D, (double)packet28.field_6368_d / 32000D);
            return;
        }
    }

    public void handleNamedEntitySpawn(Packet20NamedEntitySpawn packet20namedentityspawn)
    {
        double d = (double)packet20namedentityspawn.xPosition_15 / 32D;
        double d1 = (double)packet20namedentityspawn.yPosition_03 / 32D;
        double d2 = (double)packet20namedentityspawn.zPosition_02 / 32D;
        float f = (float)(packet20namedentityspawn.rotation_00 * 360) / 256F;
        float f1 = (float)(packet20namedentityspawn.pitch_00 * 360) / 256F;
        EntityOtherPlayerMP entityotherplayermp = new EntityOtherPlayerMP(mc_08.field_6324_e, packet20namedentityspawn.name_00);
        entityotherplayermp.field_652_bd = packet20namedentityspawn.xPosition_15;
        entityotherplayermp.field_650_be = packet20namedentityspawn.yPosition_03;
        entityotherplayermp.field_648_bf = packet20namedentityspawn.zPosition_02;
        int i = packet20namedentityspawn.currentItem;
        if(i == 0)
        {
            entityotherplayermp.inventory.mainInventory[entityotherplayermp.inventory.currentItem_00] = null;
        } else
        {
            entityotherplayermp.inventory.mainInventory[entityotherplayermp.inventory.currentItem_00] = new ItemStack(i);
        }
        entityotherplayermp.setPositionAndRotation(d, d1, d2, f, f1);
        field_1211_f.func_712_a(packet20namedentityspawn.entityId_00, entityotherplayermp);
    }

    public void handleEntityTeleport(Packet34EntityTeleport packet34entityteleport)
    {
        Entity entity = field_1211_f.func_709_b(packet34entityteleport.entityId_06);
        if(entity == null)
        {
            return;
        } else
        {
            entity.field_652_bd = packet34entityteleport.xPosition_14;
            entity.field_650_be = packet34entityteleport.yPosition_01;
            entity.field_648_bf = packet34entityteleport.zPosition_00;
            double d = (double)entity.field_652_bd / 32D;
            double d1 = (double)entity.field_650_be / 32D + 0.015625D;
            double d2 = (double)entity.field_648_bf / 32D;
            float f = (float)(packet34entityteleport.yaw * 360) / 256F;
            float f1 = (float)(packet34entityteleport.pitch * 360) / 256F;
            entity.setPositionAndRotation2(d, d1, d2, f, f1, 3);
            return;
        }
    }

    public void handleEntity(Packet30Entity packet30entity)
    {
        Entity entity = field_1211_f.func_709_b(packet30entity.entityId_02);
        if(entity == null)
        {
            return;
        } else
        {
            entity.field_652_bd += packet30entity.xPosition_08;
            entity.field_650_be += packet30entity.yPosition_05;
            entity.field_648_bf += packet30entity.zPosition_07;
            double d = (double)entity.field_652_bd / 32D;
            double d1 = (double)entity.field_650_be / 32D + 0.015625D;
            double d2 = (double)entity.field_648_bf / 32D;
            float f = packet30entity.rotating ? (float)(packet30entity.yaw_00 * 360) / 256F : entity.rotationYaw;
            float f1 = packet30entity.rotating ? (float)(packet30entity.pitch_01 * 360) / 256F : entity.rotationPitch;
            entity.setPositionAndRotation2(d, d1, d2, f, f1, 3);
            return;
        }
    }

    public void handleDestroyEntity(Packet29DestroyEntity packet29destroyentity)
    {
        field_1211_f.func_710_c(packet29destroyentity.entityId_04);
    }

    public void handleFlying(Packet10Flying packet10flying)
    {
        EntityPlayerSP entityplayersp = mc_08.field_6322_g;
        double d = ((EntityPlayer) (entityplayersp)).posX;
        double d1 = ((EntityPlayer) (entityplayersp)).posY;
        double d2 = ((EntityPlayer) (entityplayersp)).posZ;
        float f = ((EntityPlayer) (entityplayersp)).rotationYaw;
        float f1 = ((EntityPlayer) (entityplayersp)).rotationPitch;
        if(packet10flying.moving)
        {
            d = packet10flying.xPosition_00;
            d1 = packet10flying.yPosition;
            d2 = packet10flying.zPosition_10;
        }
        if(packet10flying.rotating_00)
        {
            f = packet10flying.yaw_01;
            f1 = packet10flying.pitch_02;
        }
        entityplayersp.field_635_aL = 0.0F;
        entityplayersp.motionX = entityplayersp.motionY = entityplayersp.motionZ = 0.0D;
        entityplayersp.setPositionAndRotation(d, d1, d2, f, f1);
        packet10flying.xPosition_00 = ((EntityPlayer) (entityplayersp)).posX;
        packet10flying.yPosition = ((EntityPlayer) (entityplayersp)).boundingBox.minY;
        packet10flying.zPosition_10 = ((EntityPlayer) (entityplayersp)).posZ;
        packet10flying.stance = ((EntityPlayer) (entityplayersp)).posY;
        netManager_02.addToSendQueue(packet10flying);
        if(!field_1210_g)
        {
            mc_08.field_6322_g.prevPosX = mc_08.field_6322_g.posX;
            mc_08.field_6322_g.prevPosY = mc_08.field_6322_g.posY;
            mc_08.field_6322_g.prevPosZ = mc_08.field_6322_g.posZ;
            field_1210_g = true;
            mc_08.func_6272_a(null);
        }
    }

    public void handlePreChunk(Packet50PreChunk packet50prechunk)
    {
        field_1211_f.func_713_a(packet50prechunk.xPosition_13, packet50prechunk.yPosition_12, packet50prechunk.mode);
    }

    public void handleMultiBlockChange(Packet52MultiBlockChange packet52multiblockchange)
    {
        Chunk chunk = field_1211_f.getChunkFromChunkCoords(packet52multiblockchange.xPosition_06, packet52multiblockchange.zPosition_05);
        int i = packet52multiblockchange.xPosition_06 * 16;
        int j = packet52multiblockchange.zPosition_05 * 16;
        for(int k = 0; k < packet52multiblockchange.size; k++)
        {
            short word0 = packet52multiblockchange.coordinateArray[k];
            int l = packet52multiblockchange.typeArray[k] & 0xff;
            byte byte0 = packet52multiblockchange.metadataArray[k];
            int i1 = word0 >> 12 & 0xf;
            int j1 = word0 >> 8 & 0xf;
            int k1 = word0 & 0xff;
            chunk.setBlockIDWithMetadata(i1, k1, j1, l, byte0);
            field_1211_f.func_711_c(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
            field_1211_f.func_701_b(i1 + i, k1, j1 + j, i1 + i, k1, j1 + j);
        }

    }

    public void handleMapChunk(Packet51MapChunk packet51mapchunk)
    {
        field_1211_f.func_711_c(packet51mapchunk.xPosition_12, packet51mapchunk.yPosition_11, packet51mapchunk.zPosition_12, (packet51mapchunk.xPosition_12 + packet51mapchunk.xSize) - 1, (packet51mapchunk.yPosition_11 + packet51mapchunk.ySize) - 1, (packet51mapchunk.zPosition_12 + packet51mapchunk.zSize) - 1);
        field_1211_f.func_693_a(packet51mapchunk.xPosition_12, packet51mapchunk.yPosition_11, packet51mapchunk.zPosition_12, packet51mapchunk.xSize, packet51mapchunk.ySize, packet51mapchunk.zSize, packet51mapchunk.chunk);
    }

    public void handleBlockChange(Packet53BlockChange packet53blockchange)
    {
        field_1211_f.func_714_c(packet53blockchange.xPosition_04, packet53blockchange.yPosition_04, packet53blockchange.zPosition_04, packet53blockchange.type, packet53blockchange.metadata);
    }

    public void handleKickDisconnect(Packet255KickDisconnect packet255kickdisconnect)
    {
        netManager_02.networkShutdown("Got kicked");
        disconnected = true;
        mc_08.func_6261_a(null);
        mc_08.func_6272_a(new GuiConnectFailed("Disconnected by server", packet255kickdisconnect.reason));
    }

    public void handleErrorMessage(String s)
    {
        if(disconnected)
        {
            return;
        } else
        {
            disconnected = true;
            mc_08.func_6261_a(null);
            mc_08.func_6272_a(new GuiConnectFailed("Connection lost", s));
            return;
        }
    }

    public void func_847_a(Packet packet)
    {
        if(disconnected)
        {
            return;
        } else
        {
            netManager_02.addToSendQueue(packet);
            return;
        }
    }

    public void handleCollect(Packet22Collect packet22collect)
    {
        EntityItem entityitem = (EntityItem)field_1211_f.func_709_b(packet22collect.collectedEntityId);
        Object obj = (EntityLiving)field_1211_f.func_709_b(packet22collect.collectorEntityId);
        if(obj == null)
        {
            obj = mc_08.field_6322_g;
        }
        if(entityitem != null)
        {
            field_1211_f.playSoundAtEntity(entityitem, "random.pop", 0.2F, ((rand_06.nextFloat() - rand_06.nextFloat()) * 0.7F + 1.0F) * 2.0F);
            mc_08.field_6321_h.func_1192_a(new EntityPickupFX(mc_08.field_6324_e, entityitem, ((Entity) (obj)), -0.5F));
            field_1211_f.func_710_c(packet22collect.collectedEntityId);
        }
    }

    public void handleBlockItemSwitch(Packet16BlockItemSwitch packet16blockitemswitch)
    {
        Entity entity = field_1211_f.func_709_b(packet16blockitemswitch.unused);
        if(entity == null)
        {
            return;
        }
        EntityPlayer entityplayer = (EntityPlayer)entity;
        int i = packet16blockitemswitch.id_00;
        if(i == 0)
        {
            entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem_00] = null;
        } else
        {
            entityplayer.inventory.mainInventory[entityplayer.inventory.currentItem_00] = new ItemStack(i);
        }
    }

    public void func_4113_a(Packet3Chat packet3chat)
    {
        mc_08.field_6308_u.func_552_a(packet3chat.message);
    }

    public void handleArmAnimation(Packet18ArmAnimation packet18armanimation)
    {
        Entity entity = field_1211_f.func_709_b(packet18armanimation.entityId);
        if(entity == null)
        {
            return;
        }
        if(packet18armanimation.animate == 1)
        {
            EntityPlayer entityplayer = (EntityPlayer)entity;
            entityplayer.func_457_w();
        } else
        if(packet18armanimation.animate == 2)
        {
            entity.field_6379_bs = true;
        } else
        if(packet18armanimation.animate == 3)
        {
            entity.field_6379_bs = false;
        }
    }

    public void handleAddToInventory(Packet17AddToInventory packet17addtoinventory)
    {
        mc_08.field_6322_g.inventory.addItemStackToInventory(new ItemStack(packet17addtoinventory.id_01, packet17addtoinventory.count_00, packet17addtoinventory.durability));
    }

    public void handleHandshake(Packet2Handshake packet2handshake)
    {
        if(packet2handshake.username_00.equals("-"))
        {
            func_847_a(new Packet1Login(mc_08.field_6320_i.field_1666_b, "Password", 4));
        } else
        {
            try
            {
                URL url = new URL((new StringBuilder()).append("http://www.minecraft.net/game/joinserver.jsp?user=").append(mc_08.field_6320_i.field_1666_b).append("&sessionId=").append(mc_08.field_6320_i.field_6543_c).append("&serverId=").append(packet2handshake.username_00).toString());
                BufferedReader bufferedreader = new BufferedReader(new InputStreamReader(url.openStream()));
                String s = bufferedreader.readLine();
                bufferedreader.close();
                if(s.equalsIgnoreCase("ok"))
                {
                    func_847_a(new Packet1Login(mc_08.field_6320_i.field_1666_b, "Password", 4));
                } else
                {
                    netManager_02.networkShutdown((new StringBuilder()).append("Failed to login: ").append(s).toString());
                }
            }
            catch(Exception exception)
            {
                exception.printStackTrace();
                netManager_02.networkShutdown((new StringBuilder()).append("Internal client error: ").append(exception.toString()).toString());
            }
        }
    }

    public void disconnect()
    {
        disconnected = true;
        netManager_02.networkShutdown("Closed");
    }

    public void handleMobSpawn(Packet24MobSpawn packet24mobspawn)
    {
        double d = (double)packet24mobspawn.xPosition_09 / 32D;
        double d1 = (double)packet24mobspawn.yPosition_07 / 32D;
        double d2 = (double)packet24mobspawn.zPosition_08 / 32D;
        float f = (float)(packet24mobspawn.yaw_02 * 360) / 256F;
        float f1 = (float)(packet24mobspawn.pitch_04 * 360) / 256F;
        EntityLiving entityliving = (EntityLiving)EntityList.func_1084_a(packet24mobspawn.type_00, mc_08.field_6324_e);
        entityliving.field_652_bd = packet24mobspawn.xPosition_09;
        entityliving.field_650_be = packet24mobspawn.yPosition_07;
        entityliving.field_648_bf = packet24mobspawn.zPosition_08;
        entityliving.setPositionAndRotation(d, d1, d2, f, f1);
        entityliving.field_720_B = true;
        field_1211_f.func_712_a(packet24mobspawn.entityId_03, entityliving);
    }

    public void handleUpdateTime(Packet4UpdateTime packet4updatetime)
    {
        mc_08.field_6324_e.setWorldTime(packet4updatetime.time);
    }

    public void handlePlayerInventory(Packet5PlayerInventory packet5playerinventory)
    {
        EntityPlayerSP entityplayersp = mc_08.field_6322_g;
        if(packet5playerinventory.type_01 == -1)
        {
            ((EntityPlayer) (entityplayersp)).inventory.mainInventory = packet5playerinventory.stacks;
        }
        if(packet5playerinventory.type_01 == -2)
        {
            ((EntityPlayer) (entityplayersp)).inventory.craftingInventory = packet5playerinventory.stacks;
        }
        if(packet5playerinventory.type_01 == -3)
        {
            ((EntityPlayer) (entityplayersp)).inventory.armorInventory = packet5playerinventory.stacks;
        }
    }

    public void handleComplexEntity(Packet59ComplexEntity packet59complexentity)
    {
        if(packet59complexentity.entityNBT.getInteger("x") != packet59complexentity.xPosition_07)
        {
            return;
        }
        if(packet59complexentity.entityNBT.getInteger("y") != packet59complexentity.yPosition_06)
        {
            return;
        }
        if(packet59complexentity.entityNBT.getInteger("z") != packet59complexentity.zPosition_06)
        {
            return;
        }
        TileEntity tileentity = field_1211_f.func_603_b(packet59complexentity.xPosition_07, packet59complexentity.yPosition_06, packet59complexentity.zPosition_06);
        if(tileentity != null)
        {
            try
            {
                tileentity.readFromNBT_01(packet59complexentity.entityNBT);
            }
            catch(Exception exception) { }
            field_1211_f.func_701_b(packet59complexentity.xPosition_07, packet59complexentity.yPosition_06, packet59complexentity.zPosition_06, packet59complexentity.xPosition_07, packet59complexentity.yPosition_06, packet59complexentity.zPosition_06);
        }
    }

    public void handleSpawnPosition(Packet6SpawnPosition packet6spawnposition)
    {
        field_1211_f.spawnX = packet6spawnposition.xPosition_01;
        field_1211_f.field_4210_n = packet6spawnposition.yPosition_00;
        field_1211_f.spawnZ = packet6spawnposition.zPosition;
    }

    public void func_6497_a(Packet39 packet39)
    {
        Object obj = field_1211_f.func_709_b(packet39.field_6365_a);
        Entity entity = field_1211_f.func_709_b(packet39.field_6364_b);
        if(packet39.field_6365_a == mc_08.field_6322_g.field_620_ab)
        {
            obj = mc_08.field_6322_g;
        }
        if(obj == null)
        {
            return;
        } else
        {
            ((Entity) (obj)).func_6377_h(entity);
            return;
        }
    }

    private boolean disconnected;
    private NetworkManager netManager_02;
    public String Hostname;
    public int Port;
    public String field_1209_a;
    private Minecraft mc_08;
    private WorldClient field_1211_f;
    private boolean field_1210_g;
    Random rand_06;
}
