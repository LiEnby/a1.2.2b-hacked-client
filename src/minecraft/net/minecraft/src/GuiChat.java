package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GuiChat extends GuiScreen
{

    public GuiChat()
    {
        message_01 = "";
        updateCounter_05 = 0;
    }
    public static boolean showCoords = false;
    
    public void func_6448_a()
    {
        Keyboard.enableRepeatEvents(true);
    }

    public void func_6449_h()
    {
        Keyboard.enableRepeatEvents(false);
    }

    public void updateScreen()
    {
        updateCounter_05++;
    }

    protected void keyTyped(char c, int i)
    {
        if(i == 1)
        {
            mc_06.func_6272_a(null);
            return;
        }
        if(i == 28)
        {
            if(message_01.startsWith(".")) {
                System.out.println(("Executing Command: " + message_01));
                String[] args = message_01.split("\\s+");

                mc_06.func_6272_a(null);
                if (args[0].equals(".help")) {
                    mc_06.field_6308_u.func_552_a(".help - This screen");
                    mc_06.field_6308_u.func_552_a(".showcoords - Toggle showing coordinates");
                    mc_06.field_6308_u.func_552_a(".seed - Get current world seed");
                    mc_06.field_6308_u.func_552_a(".god - Toggle God mode");
                    mc_06.field_6308_u.func_552_a(".fly - Toggle Fly mode");
                    mc_06.field_6308_u.func_552_a(".instamine - Toggle Instant Block-Break");
                    mc_06.field_6308_u.func_552_a(".sun - Makes it daytime");
                    mc_06.field_6308_u.func_552_a(".nick <name> - Change your name");
                    mc_06.field_6308_u.func_552_a(".speed <mul> - Speed multiplier (default: 1.00)");
                    mc_06.field_6308_u.func_552_a(".tp <x> <z> - TP to location");
                    mc_06.field_6308_u.func_552_a(".give <id> - Give yourself an itemid");
                } else if (args[0].equals(".showcoords")) {
                    if (showCoords) {
                        showCoords = false;
                        mc_06.field_6308_u.func_552_a("No longer showing coordinates.");
                    } else {
                        showCoords = true;
                        mc_06.field_6308_u.func_552_a("Now showing coordinates.");
                    }

                } else if (args[0].equals(".seed")) {
                    mc_06.field_6308_u.func_552_a("Seed: " + String.valueOf(mc_06.field_6324_e.randomSeed));
                } else if (args[0].equals(".god")) {
                    if (mc_06.field_6322_g.godmode) {
                        mc_06.field_6322_g.godmode = false;
                        mc_06.field_6308_u.func_552_a("God mode Disabled");
                    } else {
                        mc_06.field_6322_g.godmode = true;
                        mc_06.field_6308_u.func_552_a("God mode Enabled");
                    }
                } else if (args[0].equals(".fly")) {
                    if (mc_06.field_6322_g.fly) {
                        mc_06.field_6322_g.fly = false;
                        mc_06.field_6308_u.func_552_a("Fly mode Disabled");
                    } else {
                        mc_06.field_6322_g.fly = true;
                        mc_06.field_6308_u.func_552_a("Fly mode Enabled");
                    }
                } else if (args[0].equals(".instamine")) {
                    if (mc_06.field_6322_g.instaBreak) {
                        mc_06.field_6322_g.instaBreak = false;
                        mc_06.field_6308_u.func_552_a("Instant Break Disabled");
                    } else {
                        mc_06.field_6322_g.instaBreak = true;
                        mc_06.field_6308_u.func_552_a("Instant Break Enabled");
                    }
                }
                else if (args[0].equals(".speed")) {
                    if(args.length >= 2) {
                        mc_06.field_6322_g.spd = Double.parseDouble(args[1]);
                    }
                    else {
                        mc_06.field_6308_u.func_552_a(".speed <mul>");
                    }
                } else if (args[0].equals(".sun")) {
                    mc_06.field_6324_e.setWorldTime(0);
                } else if (args[0].equals(".nick"))
                {
                    if(args.length >= 2) {
                        mc_06.field_6322_g.field_771_i = args[1];
                    }
                    else {
                        mc_06.field_6308_u.func_552_a(".nick <name>");
                    }

                }
                else if(args[0].equals(".tp"))
                {
                    if(args.length >= 3) {
                        int posX = Integer.parseInt(args[1]);
                        int posZ = Integer.parseInt(args[2]);

                        int _spawnX = mc_06.field_6324_e.spawnX;
                        int _spawnZ = mc_06.field_6324_e.spawnZ;

                        mc_06.field_6324_e.spawnZ = posZ;
                        mc_06.field_6324_e.spawnX = posX;

                        mc_06.tp();

                        mc_06.field_6324_e.spawnZ = _spawnZ;
                        mc_06.field_6324_e.spawnX = _spawnX;
                    }
                    else {
                        mc_06.field_6308_u.func_552_a(".tp <x> <z>");
                    }

                }
                else if(args[0].equals(".give"))
                {
                    int id = 0;
                    int count = 0;
                    if(args.length >= 2) {
                        if (args.length == 3) {
                            id = Integer.parseInt(args[1]);
                            count = Integer.parseInt(args[2]);
                        } else if (args.length == 2) {
                            id = Integer.parseInt(args[1]);
                            count = 1;
                        }
                        mc_06.field_6322_g.func_444_a(new ItemStack(id, count), false);
                    }
                    else
                    {
                        mc_06.field_6308_u.func_552_a(".give <id> [amount]");
                    }
                }
                else
                {
                    mc_06.field_6308_u.func_552_a("Unknown Command! try .help for a list of commands!");
                }

                return;
            }


            String s = message_01.trim();
            if(s.length() > 0)
            {

                if(!mc_06.field_6324_e.multiplayerWorld)
                {
                    mc_06.field_6308_u.func_552_a("<"+mc_06.field_6322_g.field_771_i+"> "+ message_01.trim());
                }
                else {
                    mc_06.field_6322_g.func_461_a(message_01.trim());
                }
            }
            mc_06.func_6272_a(null);
            return;
        }
        if(i == 14 && message_01.length() > 0)
        {
            message_01 = message_01.substring(0, message_01.length() - 1);
        }
        if(" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\307\374\351\342\344\340\345\347\352\353\350\357\356\354\304\305\311\346\306\364\366\362\373\371\377\326\334\370\243\330\327\u0192\341\355\363\372\361\321\252\272\277\256\254\275\274\241\253\273".indexOf(c) >= 0 && message_01.length() < 100)
        {
        	message_01 += c;
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        drawRect(2, height_02 - 14, width_02 - 2, height_02 - 2, 0x80000000);
        drawString(field_6451_g, (new StringBuilder()).append("> ").append(message_01).append((updateCounter_05 / 6) % 2 != 0 ? "" : "_").toString(), 4, height_02 - 12, 0xe0e0e0);
    }

    protected void mouseClicked(int i, int j, int k)
    {
        if(k != 0 || mc_06.field_6308_u.field_933_a == null)
        {
            return;
        }
        if(message_01.length() > 0 && !message_01.endsWith(" "))
        {
        	message_01 += " ";
        }
        message_01 += mc_06.field_6308_u.field_933_a;
        byte byte0 = 100;
        if(message_01.length() > byte0)
        {
            message_01 = message_01.substring(0, byte0);
        }
    }

    private String message_01;
    private int updateCounter_05;
}
