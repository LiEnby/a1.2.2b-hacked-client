package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.awt.event.KeyEvent;
import java.io.*;
import java.security.Key;

import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;

public class GameSettings
{

    public GameSettings(Minecraft minecraft, File file)
    {
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 0;
        viewBobbing = true;
        anaglyph = false;
        limitFramerate = false;
        fancyGraphics = true;
        field_6524_j = "Default";
        keyBindForward = new KeyBinding("Forward", 17);
        keyBindLeft = new KeyBinding("Left", 30);
        keyBindBack = new KeyBinding("Back", 31);
        keyBindRight = new KeyBinding("Right", 32);
        keyBindJump = new KeyBinding("Jump", 57);
        keyBindInventory = new KeyBinding("Inventory", 23);
        keyBindCreativeInventory = new KeyBinding("Creative",48);
        field_6523_q = new KeyBinding("Drop", 16);
        field_6521_r = new KeyBinding("Chat", 20);
        field_6520_s = new KeyBinding("Toggle fog", 33);
        keyBindSneak = new KeyBinding("Sneak", 42);
        keyBindFly = new KeyBinding("Fly", 44);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, keyBindFly, field_6523_q, keyBindInventory,keyBindCreativeInventory, field_6521_r, field_6520_s
        });
        numberOfOptions = 10;
        difficulty = 2;
        thirdPersonView = false;
        mc = minecraft;
        field_6522_B = new File(file, "options.txt");
        loadOptions();
    }

    public GameSettings()
    {
        musicVolume = 1.0F;
        soundVolume = 1.0F;
        mouseSensitivity = 0.5F;
        invertMouse = false;
        renderDistance = 0;
        viewBobbing = true;
        anaglyph = false;
        limitFramerate = false;
        fancyGraphics = true;
        field_6524_j = "Default";
        keyBindForward = new KeyBinding("Forward", 17);
        keyBindLeft = new KeyBinding("Left", 30);
        keyBindBack = new KeyBinding("Back", 31);
        keyBindRight = new KeyBinding("Right", 32);
        keyBindJump = new KeyBinding("Jump", 57);
        keyBindInventory = new KeyBinding("Inventory", 23);
        field_6523_q = new KeyBinding("Drop", 16);
        field_6521_r = new KeyBinding("Chat", 20);
        field_6520_s = new KeyBinding("Toggle fog", 33);
        keyBindSneak = new KeyBinding("Sneak", 42);
        keyBindings = (new KeyBinding[] {
            keyBindForward, keyBindLeft, keyBindBack, keyBindRight, keyBindJump, keyBindSneak, field_6523_q, keyBindInventory, field_6521_r, field_6520_s
        });
        numberOfOptions = 10;
        difficulty = 2;
        thirdPersonView = false;
    }

    public String getKeyBinding(int i)
    {
        return (new StringBuilder()).append(keyBindings[i].keyDescription).append(": ").append(Keyboard.getKeyName(keyBindings[i].keyCode)).toString();
    }

    public void setKeyBinding(int i, int j)
    {
        keyBindings[i].keyCode = j;
        saveOptions();
    }

    public void setOptionFloatValue(int i, float f)
    {
        if(i == 0)
        {
            musicVolume = f;
            mc.field_6301_A.func_335_a();
        }
        if(i == 1)
        {
            soundVolume = f;
            mc.field_6301_A.func_335_a();
        }
        if(i == 3)
        {
            mouseSensitivity = f;
        }
    }

    public void setOptionValue(int i, int j)
    {
        if(i == 2)
        {
            invertMouse = !invertMouse;
        }
        if(i == 4)
        {
            renderDistance = renderDistance + j & 3;
        }
        if(i == 5)
        {
            viewBobbing = !viewBobbing;
        }
        if(i == 6)
        {
            anaglyph = !anaglyph;
            mc.field_6315_n.func_1065_b();
        }
        if(i == 7)
        {
            limitFramerate = !limitFramerate;
        }
        if(i == 8)
        {
            difficulty = difficulty + j & 3;
        }
        if(i == 9)
        {
            fancyGraphics = !fancyGraphics;
            mc.field_6323_f.func_958_a();
        }
        saveOptions();
    }

    public int getOptionControlType(int i)
    {
        if(i == 0)
        {
            return 1;
        }
        if(i == 1)
        {
            return 1;
        }
        return i != 3 ? 0 : 1;
    }

    public float getOptionFloatValue(int i)
    {
        if(i == 0)
        {
            return musicVolume;
        }
        if(i == 1)
        {
            return soundVolume;
        }
        if(i == 3)
        {
            return mouseSensitivity;
        } else
        {
            return 0.0F;
        }
    }

    public String getOptionDisplayString(int i)
    {
        if(i == 0)
        {
            return (new StringBuilder()).append("Music: ").append(musicVolume <= 0.0F ? "OFF" : (new StringBuilder()).append((int)(musicVolume * 100F)).append("%").toString()).toString();
        }
        if(i == 1)
        {
            return (new StringBuilder()).append("Sound: ").append(soundVolume <= 0.0F ? "OFF" : (new StringBuilder()).append((int)(soundVolume * 100F)).append("%").toString()).toString();
        }
        if(i == 2)
        {
            return (new StringBuilder()).append("Invert mouse: ").append(invertMouse ? "ON" : "OFF").toString();
        }
        if(i == 3)
        {
            if(mouseSensitivity == 0.0F)
            {
                return "Sensitivity: *yawn*";
            }
            if(mouseSensitivity == 1.0F)
            {
                return "Sensitivity: HYPERSPEED!!!";
            } else
            {
                return (new StringBuilder()).append("Sensitivity: ").append((int)(mouseSensitivity * 200F)).append("%").toString();
            }
        }
        if(i == 4)
        {
            return (new StringBuilder()).append("Render distance: ").append(RENDER_DISTANCES[renderDistance]).toString();
        }
        if(i == 5)
        {
            return (new StringBuilder()).append("View bobbing: ").append(viewBobbing ? "ON" : "OFF").toString();
        }
        if(i == 6)
        {
            return (new StringBuilder()).append("3d anaglyph: ").append(anaglyph ? "ON" : "OFF").toString();
        }
        if(i == 7)
        {
            return (new StringBuilder()).append("Limit framerate: ").append(limitFramerate ? "ON" : "OFF").toString();
        }
        if(i == 8)
        {
            return (new StringBuilder()).append("Difficulty: ").append(DIFFICULTY_LEVELS[difficulty]).toString();
        }
        if(i == 9)
        {
            return (new StringBuilder()).append("Graphics: ").append(fancyGraphics ? "FANCY" : "FAST").toString();
        } else
        {
            return "";
        }
    }

    public void loadOptions()
    {
        try
        {
            if(!field_6522_B.exists())
            {
                return;
            }
            BufferedReader bufferedreader = new BufferedReader(new FileReader(field_6522_B));
            for(String s = ""; (s = bufferedreader.readLine()) != null;)
            {
                String as[] = s.split(":");
                if(as[0].equals("music"))
                {
                    musicVolume = parseFloat(as[1]);
                }
                if(as[0].equals("sound"))
                {
                    soundVolume = parseFloat(as[1]);
                }
                if(as[0].equals("mouseSensitivity"))
                {
                    mouseSensitivity = parseFloat(as[1]);
                }
                if(as[0].equals("invertYMouse"))
                {
                    invertMouse = as[1].equals("true");
                }
                if(as[0].equals("viewDistance"))
                {
                    renderDistance = Integer.parseInt(as[1]);
                }
                if(as[0].equals("bobView"))
                {
                    viewBobbing = as[1].equals("true");
                }
                if(as[0].equals("anaglyph3d"))
                {
                    anaglyph = as[1].equals("true");
                }
                if(as[0].equals("limitFramerate"))
                {
                    limitFramerate = as[1].equals("true");
                }
                if(as[0].equals("difficulty"))
                {
                    difficulty = Integer.parseInt(as[1]);
                }
                if(as[0].equals("fancyGraphics"))
                {
                    fancyGraphics = as[1].equals("true");
                }
                if(as[0].equals("skin"))
                {
                    field_6524_j = as[1];
                }
                int i = 0;
                while(i < keyBindings.length) 
                {
                    if(as[0].equals((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).toString()))
                    {
                        keyBindings[i].keyCode = Integer.parseInt(as[1]);
                    }
                    i++;
                }
            }

            bufferedreader.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to load options");
            exception.printStackTrace();
        }
    }

    private float parseFloat(String s)
    {
        if(s.equals("true"))
        {
            return 1.0F;
        }
        if(s.equals("false"))
        {
            return 0.0F;
        } else
        {
            return Float.parseFloat(s);
        }
    }

    public void saveOptions()
    {
        try
        {
            PrintWriter printwriter = new PrintWriter(new FileWriter(field_6522_B));
            printwriter.println((new StringBuilder()).append("music:").append(musicVolume).toString());
            printwriter.println((new StringBuilder()).append("sound:").append(soundVolume).toString());
            printwriter.println((new StringBuilder()).append("invertYMouse:").append(invertMouse).toString());
            printwriter.println((new StringBuilder()).append("mouseSensitivity:").append(mouseSensitivity).toString());
            printwriter.println((new StringBuilder()).append("viewDistance:").append(renderDistance).toString());
            printwriter.println((new StringBuilder()).append("bobView:").append(viewBobbing).toString());
            printwriter.println((new StringBuilder()).append("anaglyph3d:").append(anaglyph).toString());
            printwriter.println((new StringBuilder()).append("limitFramerate:").append(limitFramerate).toString());
            printwriter.println((new StringBuilder()).append("difficulty:").append(difficulty).toString());
            printwriter.println((new StringBuilder()).append("fancyGraphics:").append(fancyGraphics).toString());
            printwriter.println((new StringBuilder()).append("skin:").append(field_6524_j).toString());
            for(int i = 0; i < keyBindings.length; i++)
            {
                printwriter.println((new StringBuilder()).append("key_").append(keyBindings[i].keyDescription).append(":").append(keyBindings[i].keyCode).toString());
            }

            printwriter.close();
        }
        catch(Exception exception)
        {
            System.out.println("Failed to save options");
            exception.printStackTrace();
        }
    }

    private static final String RENDER_DISTANCES[] = {
        "FAR", "NORMAL", "SHORT", "TINY"
    };
    private static final String DIFFICULTY_LEVELS[] = {
        "Peaceful", "Easy", "Normal", "Hard"
    };
    public float musicVolume;
    public float soundVolume;
    public float mouseSensitivity;
    public boolean invertMouse;
    public int renderDistance;
    public boolean viewBobbing;
    public boolean anaglyph;
    public boolean limitFramerate;
    public boolean fancyGraphics;
    public String field_6524_j;
    public KeyBinding keyBindForward;
    public KeyBinding keyBindLeft;
    public KeyBinding keyBindBack;
    public KeyBinding keyBindRight;
    public KeyBinding keyBindJump;
    public KeyBinding keyBindInventory;
    public KeyBinding keyBindCreativeInventory;
    public KeyBinding field_6523_q;
    public KeyBinding field_6521_r;
    public KeyBinding field_6520_s;
    public KeyBinding keyBindSneak;
    public KeyBinding keyBindFly;
    public KeyBinding keyBindings[];
    protected Minecraft mc;
    private File field_6522_B;
    public int numberOfOptions;
    public int difficulty;
    public boolean thirdPersonView;

}
