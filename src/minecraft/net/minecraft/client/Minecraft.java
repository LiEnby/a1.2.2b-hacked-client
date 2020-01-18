// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

package net.minecraft.client;


import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.BlockGrass;
import net.minecraft.src.EffectRenderer;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EntityPlayerSP;
import net.minecraft.src.EntityRenderer;
import net.minecraft.src.EnumOS2;
import net.minecraft.src.EnumOSMappingHelper;
import net.minecraft.src.FontRenderer;
import net.minecraft.src.GLAllocation;
import net.minecraft.src.GameSettings;
import net.minecraft.src.GameWindowListener;
import net.minecraft.src.GuiChat;
import net.minecraft.src.GuiConflictWarning;
import net.minecraft.src.GuiConnecting;
import net.minecraft.src.GuiGameOver;
import net.minecraft.src.GuiIngame;
import net.minecraft.src.GuiIngameMenu;
import net.minecraft.src.GuiInventory;
import net.minecraft.src.GuiMainMenu;
import net.minecraft.src.GuiScreen;
import net.minecraft.src.GuiUnused;
import net.minecraft.src.InventoryPlayer;
import net.minecraft.src.ItemRenderer;
import net.minecraft.src.ItemStack;
import net.minecraft.src.KeyBinding;
import net.minecraft.src.LoadingScreenRenderer;
import net.minecraft.src.MathHelper;
import net.minecraft.src.MinecraftError;
import net.minecraft.src.MinecraftException;
import net.minecraft.src.MinecraftImpl;
import net.minecraft.src.ModelBiped;
import net.minecraft.src.MouseHelper;
import net.minecraft.src.MovementInputFromOptions;
import net.minecraft.src.MovingObjectPosition;
import net.minecraft.src.OpenGlCapsChecker;
import net.minecraft.src.PlayerController;
import net.minecraft.src.PlayerControllerTest;
import net.minecraft.src.RenderEngine;
import net.minecraft.src.RenderGlobal;
import net.minecraft.src.RenderManager;
import net.minecraft.src.ScaledResolution;
import net.minecraft.src.ScreenShotHelper;
import net.minecraft.src.Session;
import net.minecraft.src.SoundManager;
import net.minecraft.src.Teleporter;
import net.minecraft.src.Tessellator;
import net.minecraft.src.TextureCompassFX;
import net.minecraft.src.TextureFlamesFX;
import net.minecraft.src.TextureLavaFX;
import net.minecraft.src.TextureLavaFlowFX;
import net.minecraft.src.TexturePackList;
import net.minecraft.src.TexturePortalFX;
import net.minecraft.src.TextureWatchFX;
import net.minecraft.src.TextureWaterFX;
import net.minecraft.src.TexureWaterFlowFX;
import net.minecraft.src.ThreadDownloadResources;
import net.minecraft.src.ThreadSleepForever;
import net.minecraft.src.Timer;
import net.minecraft.src.UnexpectedThrowable;
import net.minecraft.src.Vec3D;
import net.minecraft.src.World;
import net.minecraft.src.WorldProvider;
import net.minecraft.src.WorldProviderHell;
import net.minecraft.src.WorldRenderer;
import java.awt.*;
import java.io.*;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import org.lwjgl.util.glu.GLU;

// Referenced classes of package net.minecraft.client:
//            MinecraftApplet

public abstract class Minecraft
    implements Runnable
{

    public Minecraft(Component component, Canvas canvas, MinecraftApplet minecraftapplet, int i, int j, boolean flag)
    {
        mainFrame = false;
        field_6285_P = new Timer(20F);
        field_6320_i = null;
        field_6317_l = true;
        field_6316_m = false;
        field_6313_p = null;
        field_6312_q = new LoadingScreenRenderer(this);
        field_6311_r = new EntityRenderer(this);
        field_6283_R = 0;
        field_6282_S = 0;
        field_6310_s = null;
        field_6309_t = 0;
        field_6307_v = false;
        field_6306_w = new ModelBiped(0.0F);
        field_6305_x = null;
        field_6301_A = new SoundManager();
        field_6277_X = new TextureWaterFX();
        field_6276_Y = new TextureLavaFX();
        field_6293_H = true;
        field_6292_I = "";
        field_6291_J = false;
        field_6290_K = -1L;
        field_6289_L = false;
        field_6302_aa = 0;
        field_6288_M = false;
        field_6287_N = System.currentTimeMillis();
        field_6300_ab = 0;
        field_6281_T = i;
        field_6280_U = j;
        mainFrame = flag;
        field_6303_z = minecraftapplet;
        new ThreadSleepForever(this, "Timer hack thread");
        field_6318_k = canvas;
        field_6326_c = i;
        field_6325_d = j;
        mainFrame = flag;
    }

    public abstract void func_4007_a(UnexpectedThrowable unexpectedthrowable);

    public void func_6258_a(String s, int i)
    {
        field_6279_V = s;
        field_6278_W = i;
    }

    public void func_6271_a() throws LWJGLException
    {
        if(field_6318_k != null)
        {
            Graphics g = field_6318_k.getGraphics();
            if(g != null)
            {
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, field_6326_c, field_6325_d);
                g.dispose();
            }
            Display.setParent(field_6318_k);
        } else
        if(mainFrame)
        {
            Display.setFullscreen(true);
            field_6326_c = Display.getDisplayMode().getWidth();
            field_6325_d = Display.getDisplayMode().getHeight();
            if(field_6326_c <= 0)
            {
                field_6326_c = 1;
            }
            if(field_6325_d <= 0)
            {
                field_6325_d = 1;
            }
        } else
        {
            Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(field_6326_c, field_6325_d));
        }
        Display.setTitle("Minecraft Minecraft Alpha v1.2.2");
        try
        {
            Display.create();
        }
        catch(LWJGLException lwjglexception)
        {
            lwjglexception.printStackTrace();
            try
            {
                Thread.sleep(1000L);
            }
            catch(InterruptedException interruptedexception) { }
            Display.create();
        }
        RenderManager.field_1233_a.field_4236_f = new ItemRenderer(this);
        field_6297_D = func_6240_b();
        field_6304_y = new GameSettings(this, field_6297_D);
        field_6298_C = new TexturePackList(this, field_6297_D);
        field_6315_n = new RenderEngine(field_6298_C, field_6304_y);
        field_6314_o = new FontRenderer(field_6304_y, "/font/default.png", field_6315_n);
        func_6257_q();
        Keyboard.create();
        Mouse.create();
        field_6299_B = new MouseHelper(field_6318_k);
        try
        {
            Controllers.create();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        func_6250_c("Pre startup");
        GL11.glEnable(3553);
        GL11.glShadeModel(7425);
        GL11.glClearDepth(1.0D);
        GL11.glEnable(2929);
        GL11.glDepthFunc(515);
        GL11.glEnable(3008);
        GL11.glAlphaFunc(516, 0.1F);
        GL11.glCullFace(1029);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glMatrixMode(5888);
        func_6250_c("Startup");
        field_6286_O = new OpenGlCapsChecker();
        field_6301_A.func_340_a(field_6304_y);
        field_6315_n.func_1066_a(field_6276_Y);
        field_6315_n.func_1066_a(field_6277_X);
        field_6315_n.func_1066_a(new TexturePortalFX());
        field_6315_n.func_1066_a(new TextureCompassFX(this));
        field_6315_n.func_1066_a(new TextureWatchFX(this));
        field_6315_n.func_1066_a(new TexureWaterFlowFX());
        field_6315_n.func_1066_a(new TextureLavaFlowFX());
        field_6315_n.func_1066_a(new TextureFlamesFX(0));
        field_6315_n.func_1066_a(new TextureFlamesFX(1));
        field_6323_f = new RenderGlobal(this, field_6315_n);
        GL11.glViewport(0, 0, field_6326_c, field_6325_d);
        field_6321_h = new EffectRenderer(field_6324_e, field_6315_n);
        try
        {
            field_6284_Q = new ThreadDownloadResources(field_6297_D, this);
            field_6284_Q.start();
        }
        catch(Exception exception1) { }
        func_6250_c("Post startup");
        field_6308_u = new GuiIngame(this);
        if(field_6279_V != null)
        {
            func_6272_a(new GuiConnecting(this, field_6279_V, field_6278_W));
        } else
        {
            func_6272_a(new GuiMainMenu());
        }
    }

    private void func_6257_q() throws LWJGLException
    {
        ScaledResolution scaledresolution = new ScaledResolution(field_6326_c, field_6325_d);
        int i = scaledresolution.getScaledWidth();
        int j = scaledresolution.getScaledHeight();
        GL11.glClear(16640);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, i, j, 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
        GL11.glViewport(0, 0, field_6326_c, field_6325_d);
        GL11.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
        Tessellator tessellator = Tessellator.instance;
        GL11.glDisable(2896);
        GL11.glEnable(3553);
        GL11.glDisable(2912);
        GL11.glBindTexture(3553, field_6315_n.getTexture("/title/mojang.png"));
        tessellator.startDrawingQuads();
        tessellator.setColorOpaque_I(0xffffff);
        tessellator.addVertexWithUV(0.0D, field_6325_d, 0.0D, 0.0D, 0.0D);
        tessellator.addVertexWithUV(field_6326_c, field_6325_d, 0.0D, 0.0D, 0.0D);
        tessellator.addVertexWithUV(field_6326_c, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.addVertexWithUV(0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
        tessellator.draw();
        char c = '\u0100';
        char c1 = '\u0100';
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        tessellator.setColorOpaque_I(0xffffff);
        func_6274_a((field_6326_c / 2 - c) / 2, (field_6325_d / 2 - c1) / 2, 0, 0, c, c1);
        GL11.glDisable(2896);
        GL11.glDisable(2912);
        GL11.glEnable(3008);
        GL11.glAlphaFunc(516, 0.1F);
        Display.swapBuffers();
    }

    public void func_6274_a(int i, int j, int k, int l, int i1, int j1)
    {
        float f = 0.00390625F;
        float f1 = 0.00390625F;
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(i + 0, j + j1, 0.0D, (float)(k + 0) * f, (float)(l + j1) * f1);
        tessellator.addVertexWithUV(i + i1, j + j1, 0.0D, (float)(k + i1) * f, (float)(l + j1) * f1);
        tessellator.addVertexWithUV(i + i1, j + 0, 0.0D, (float)(k + i1) * f, (float)(l + 0) * f1);
        tessellator.addVertexWithUV(i + 0, j + 0, 0.0D, (float)(k + 0) * f, (float)(l + 0) * f1);
        tessellator.draw();
    }

    public static File func_6240_b()
    {
        if(field_6275_Z == null)
        {
            field_6275_Z = func_6264_a("minecraft");
        }
        return field_6275_Z;
    }

    public static File func_6264_a(String s)
    {
        String s1 = System.getProperty("user.home", ".");
        File file;
        switch(EnumOSMappingHelper.field_1585_a[func_6267_r().ordinal()])
        {
        case 1: // '\001'
        case 2: // '\002'
            file = new File(s1, (new StringBuilder()).append('.').append(s).append('/').toString());
            break;

        case 3: // '\003'
            String s2 = System.getenv("APPDATA");
            if(s2 != null)
            {
                file = new File(s2, (new StringBuilder()).append(".").append(s).append('/').toString());
            } else
            {
                file = new File(s1, (new StringBuilder()).append('.').append(s).append('/').toString());
            }
            break;

        case 4: // '\004'
            file = new File(s1, (new StringBuilder()).append("Library/Application Support/").append(s).toString());
            break;

        default:
            file = new File(s1, (new StringBuilder()).append(s).append('/').toString());
            break;
        }
        if(!file.exists() && !file.mkdirs())
        {
            throw new RuntimeException((new StringBuilder()).append("The working directory could not be created: ").append(file).toString());
        } else
        {
            return file;
        }
    }

    private static EnumOS2 func_6267_r()
    {
        String s = System.getProperty("os.name").toLowerCase();
        if(s.contains("win"))
        {
            return EnumOS2.windows;
        }
        if(s.contains("mac"))
        {
            return EnumOS2.macos;
        }
        if(s.contains("solaris"))
        {
            return EnumOS2.solaris;
        }
        if(s.contains("sunos"))
        {
            return EnumOS2.solaris;
        }
        if(s.contains("linux"))
        {
            return EnumOS2.linux;
        }
        if(s.contains("unix"))
        {
            return EnumOS2.linux;
        } else
        {
            return EnumOS2.unknown;
        }
    }

    public void func_6272_a(GuiScreen guiscreen)
    {
        if(field_6313_p instanceof GuiUnused)
        {
            return;
        }
        if(field_6313_p != null)
        {
            field_6313_p.func_6449_h();
        }
        if(guiscreen == null && field_6324_e == null)
        {
            guiscreen = new GuiMainMenu();
        } else
        if(guiscreen == null && field_6322_g.health_00 <= 0)
        {
            guiscreen = new GuiGameOver();
        }
        field_6313_p = guiscreen;
        if(guiscreen != null)
        {
            func_6273_f();
            ScaledResolution scaledresolution = new ScaledResolution(field_6326_c, field_6325_d);
            int i = scaledresolution.getScaledWidth();
            int j = scaledresolution.getScaledHeight();
            guiscreen.func_6447_a(this, i, j);
            field_6307_v = false;
        } else
        {
            func_6259_e();
        }
    }

    private void func_6250_c(String s)
    {
        int i = GL11.glGetError();
        if(i != 0)
        {
            String s1 = GLU.gluErrorString(i);
            System.out.println("########## GL ERROR ##########");
            System.out.println((new StringBuilder()).append("@ ").append(s).toString());
            System.out.println((new StringBuilder()).append(i).append(": ").append(s1).toString());
            System.exit(0);
        }
    }

    public void func_6266_c()
    {
        if(field_6303_z != null)
        {
            field_6303_z.func_6231_c();
        }
        try
        {
            if(field_6284_Q != null)
            {
                field_6284_Q.closeMinecraft();
            }
        }
        catch(Exception exception) { }
        try
        {
            System.out.println("Stopping!");
            func_6261_a(null);
            try
            {
                GLAllocation.deleteTexturesAndDisplayLists();
            }
            catch(Exception exception1) { }
            field_6301_A.closeMinecraft_00();
            Mouse.destroy();
            Keyboard.destroy();
        }
        finally
        {
            Display.destroy();
        }
        System.gc();
    }

    public void run()
    {
        field_6293_H = true;
        try
        {
            func_6271_a();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
            func_4007_a(new UnexpectedThrowable("Failed to start game", exception));
            return;
        }
        try
        {
            long l = System.currentTimeMillis();
            int i = 0;
            while(field_6293_H && (field_6303_z == null || field_6303_z.isActive())) 
            {
                AxisAlignedBB.clearBoundingBoxPool();
                Vec3D.initialize();
                if(field_6318_k == null && Display.isCloseRequested())
                {
                    func_6244_d();
                }
                if(field_6316_m && field_6324_e != null)
                {
                    float f = field_6285_P.field_1378_c;
                    field_6285_P.func_904_a();
                    field_6285_P.field_1378_c = f;
                } else
                {
                    field_6285_P.func_904_a();
                }
                long l1 = System.nanoTime();
                for(int j = 0; j < field_6285_P.field_1379_b; j++)
                {
                    field_6283_R++;
                    try
                    {
                        func_6246_i();
                        continue;
                    }
                    catch(MinecraftException minecraftexception)
                    {
                        field_6324_e = null;
                    }
                    func_6261_a(null);
                    func_6272_a(new GuiConflictWarning());
                }

                long l2 = System.nanoTime() - l1;
                func_6250_c("Pre render");
                field_6301_A.func_338_a(field_6322_g, field_6285_P.field_1378_c);
                GL11.glEnable(3553);
                if(field_6324_e != null && !field_6324_e.multiplayerWorld)
                {
                    while(field_6324_e.func_6465_g()) ;
                }
                if(field_6324_e != null && field_6324_e.multiplayerWorld)
                {
                    field_6324_e.func_6465_g();
                }
                if(field_6304_y.limitFramerate)
                {
                    Thread.sleep(5L);
                }
                if(!Keyboard.isKeyDown(65))
                {
                    Display.update();
                }
                if(!field_6307_v)
                {
                    if(field_6327_b != null)
                    {
                        field_6327_b.func_6467_a(field_6285_P.field_1378_c);
                    }
                    field_6311_r.func_4136_b(field_6285_P.field_1378_c);
                }
                if(!Display.isActive())
                {
                    if(mainFrame)
                    {
                        func_6270_h();
                    }
                    Thread.sleep(10L);
                }
                if(Keyboard.isKeyDown(61))
                {
                    func_6238_a(l2);
                } else
                {
                    field_6290_K = System.nanoTime();
                }
                Thread.yield();
                if(Keyboard.isKeyDown(65))
                {
                    Display.update();
                }
                func_6248_s();
                if(field_6318_k != null && !mainFrame && (field_6318_k.getWidth() != field_6326_c || field_6318_k.getHeight() != field_6325_d))
                {
                    field_6326_c = field_6318_k.getWidth();
                    field_6325_d = field_6318_k.getHeight();
                    if(field_6326_c <= 0)
                    {
                        field_6326_c = 1;
                    }
                    if(field_6325_d <= 0)
                    {
                        field_6325_d = 1;
                    }
                    func_6249_a(field_6326_c, field_6325_d);
                }
                func_6250_c("Post render");
                i++;
                field_6316_m = !func_6260_j() && field_6313_p != null && field_6313_p.func_6450_b();
                while(System.currentTimeMillis() >= l + 1000L) 
                {
                    field_6292_I = (new StringBuilder()).append(i).append(" fps, ").append(WorldRenderer.field_1762_b).append(" chunk updates").toString();
                    WorldRenderer.field_1762_b = 0;
                    l += 1000L;
                    i = 0;
                }
            }
        }
        catch(MinecraftError minecrafterror) { }
        catch(Throwable throwable)
        {
            field_6324_e = null;
            throwable.printStackTrace();
            func_4007_a(new UnexpectedThrowable("Unexpected error", throwable));
        }
        finally { }
    }

    private void func_6248_s()
    {
        if(Keyboard.isKeyDown(60))
        {
            if(!field_6291_J)
            {
                if(Keyboard.isKeyDown(59))
                {
                    field_6308_u.func_552_a(ScreenShotHelper.func_4148_a(field_6275_Z, field_6326_c, field_6325_d));
                }
                field_6291_J = true;
            }
        } else
        {
            field_6291_J = false;
        }
    }

    private void func_6238_a(long l)
    {
        long l1 = 0xfe502aL;
        if(field_6290_K == -1L)
        {
            field_6290_K = System.nanoTime();
        }
        long l2 = System.nanoTime();
        field_6295_F[field_6294_G & field_6296_E.length - 1] = l;
        field_6296_E[field_6294_G++ & field_6296_E.length - 1] = l2 - field_6290_K;
        field_6290_K = l2;
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0D, field_6326_c, field_6325_d, 0.0D, 1000D, 3000D);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0.0F, 0.0F, -2000F);
        GL11.glLineWidth(1.0F);
        GL11.glDisable(3553);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawing(7);
        int i = (int)(l1 / 0x30d40L);
        tessellator.setColorOpaque_I(0x20000000);
        tessellator.addVertex(0.0D, field_6325_d - i, 0.0D);
        tessellator.addVertex(0.0D, field_6325_d, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d - i, 0.0D);
        tessellator.setColorOpaque_I(0x20200000);
        tessellator.addVertex(0.0D, field_6325_d - i * 2, 0.0D);
        tessellator.addVertex(0.0D, field_6325_d - i, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d - i, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d - i * 2, 0.0D);
        tessellator.draw();
        long l3 = 0L;
        for(int j = 0; j < field_6296_E.length; j++)
        {
            l3 += field_6296_E[j];
        }

        int k = (int)(l3 / 0x30d40L / (long)field_6296_E.length);
        tessellator.startDrawing(7);
        tessellator.setColorOpaque_I(0x20400000);
        tessellator.addVertex(0.0D, field_6325_d - k, 0.0D);
        tessellator.addVertex(0.0D, field_6325_d, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d, 0.0D);
        tessellator.addVertex(field_6296_E.length, field_6325_d - k, 0.0D);
        tessellator.draw();
        tessellator.startDrawing(1);
        for(int i1 = 0; i1 < field_6296_E.length; i1++)
        {
            int j1 = ((i1 - field_6294_G & field_6296_E.length - 1) * 255) / field_6296_E.length;
            int k1 = (j1 * j1) / 255;
            k1 = (k1 * k1) / 255;
            int i2 = (k1 * k1) / 255;
            i2 = (i2 * i2) / 255;
            if(field_6296_E[i1] > l1)
            {
                tessellator.setColorOpaque_I(0xff000000 + k1 * 0x10000);
            } else
            {
                tessellator.setColorOpaque_I(0xff000000 + k1 * 256);
            }
            long l4 = field_6296_E[i1] / 0x30d40L;
            long l5 = field_6295_F[i1] / 0x30d40L;
            tessellator.addVertex((float)i1 + 0.5F, (float)((long)field_6325_d - l4) + 0.5F, 0.0D);
            tessellator.addVertex((float)i1 + 0.5F, (float)field_6325_d + 0.5F, 0.0D);
            tessellator.setColorOpaque_I(0xff000000 + k1 * 0x10000 + k1 * 256 + k1 * 1);
            tessellator.addVertex((float)i1 + 0.5F, (float)((long)field_6325_d - l4) + 0.5F, 0.0D);
            tessellator.addVertex((float)i1 + 0.5F, (float)((long)field_6325_d - (l4 - l5)) + 0.5F, 0.0D);
        }

        tessellator.draw();
        GL11.glEnable(3553);
    }

    public void func_6244_d()
    {
        field_6293_H = false;
    }

    public void func_6259_e()
    {
        if(!Display.isActive())
        {
            return;
        }
        if(field_6289_L)
        {
            return;
        } else
        {
            field_6289_L = true;
            field_6299_B.func_774_a();
            func_6272_a(null);
            field_6302_aa = field_6283_R + 10000;
            return;
        }
    }

    public void func_6273_f()
    {
        if(!field_6289_L)
        {
            return;
        }
        if(field_6322_g != null)
        {
            field_6322_g.func_458_k();
        }
        field_6289_L = false;
        field_6299_B.func_773_b();
    }

    public void func_6252_g()
    {
        if(field_6313_p != null)
        {
            return;
        } else
        {
            func_6272_a(new GuiIngameMenu());
            return;
        }
    }

    private void func_6254_a(int i, boolean flag)
    {
        if(field_6327_b.field_1064_b)
        {
            return;
        }
        if(i == 0 && field_6282_S > 0)
        {
            return;
        }
        if(flag && field_6305_x != null && field_6305_x.typeOfHit == 0 && i == 0)
        {
            int j = field_6305_x.blockX;
            int k = field_6305_x.blockY;
            int l = field_6305_x.blockZ;
            field_6327_b.func_6470_c(j, k, l, field_6305_x.sideHit);
            field_6321_h.func_1191_a(j, k, l, field_6305_x.sideHit);
        } else
        {
            field_6327_b.func_6468_a();
        }
    }

    private void func_6243_a(int i)
    {
        if(i == 0 && field_6282_S > 0)
        {
            return;
        }
        if(i == 0)
        {
            field_6322_g.func_457_w();
        }
        if(field_6305_x == null)
        {
            if(i == 0 && !(field_6327_b instanceof PlayerControllerTest))
            {
                field_6282_S = 10;
            }
        } else
        if(field_6305_x.typeOfHit == 1)
        {
            if(i == 0)
            {
                field_6327_b.func_6472_b(field_6322_g, field_6305_x.entityHit);
            }
            if(i == 1)
            {
                field_6327_b.func_6475_a(field_6322_g, field_6305_x.entityHit);
            }
        } else
        if(field_6305_x.typeOfHit == 0)
        {
            int j = field_6305_x.blockX;
            int k = field_6305_x.blockY;
            int l = field_6305_x.blockZ;
            int i1 = field_6305_x.sideHit;
            Block block = Block.blocksList[field_6324_e.getBlockId(j, k, l)];
            if(i == 0)
            {
                field_6324_e.func_612_i(j, k, l, field_6305_x.sideHit);
                if(block != Block.bedrock || field_6322_g.field_6418_f >= 100)
                {
                    field_6327_b.func_719_a(j, k, l, field_6305_x.sideHit);
                }
            } else
            {
                ItemStack itemstack1 = field_6322_g.inventory.getCurrentItem();
                int j1 = itemstack1 == null ? 0 : itemstack1.stackSize;
                if(field_6327_b.func_722_a(field_6322_g, field_6324_e, itemstack1, j, k, l, i1))
                {
                    field_6322_g.func_457_w();
                }
                if(itemstack1 == null)
                {
                    return;
                }
                if(itemstack1.stackSize == 0)
                {
                    field_6322_g.inventory.mainInventory[field_6322_g.inventory.currentItem_00] = null;
                } else
                if(itemstack1.stackSize != j1)
                {
                    field_6311_r.field_1395_a.func_6505_b();
                }
            }
        }
        if(i == 1)
        {
            ItemStack itemstack = field_6322_g.inventory.getCurrentItem();
            if(itemstack != null && field_6327_b.func_6471_a(field_6322_g, field_6324_e, itemstack))
            {
                field_6311_r.field_1395_a.func_6506_c();
            }
        }
    }

    public void func_6270_h()
    {
        try
        {
            mainFrame = !mainFrame;
            System.out.println("Toggle fullscreen!");
            if(mainFrame)
            {
                Display.setDisplayMode(Display.getDesktopDisplayMode());
                field_6326_c = Display.getDisplayMode().getWidth();
                field_6325_d = Display.getDisplayMode().getHeight();
                if(field_6326_c <= 0)
                {
                    field_6326_c = 1;
                }
                if(field_6325_d <= 0)
                {
                    field_6325_d = 1;
                }
            } else
            {
                if(field_6318_k != null)
                {
                    field_6326_c = field_6318_k.getWidth();
                    field_6325_d = field_6318_k.getHeight();
                } else
                {
                    field_6326_c = field_6281_T;
                    field_6325_d = field_6280_U;
                }
                if(field_6326_c <= 0)
                {
                    field_6326_c = 1;
                }
                if(field_6325_d <= 0)
                {
                    field_6325_d = 1;
                }
                Display.setDisplayMode(new org.lwjgl.opengl.DisplayMode(field_6281_T, field_6280_U));
            }
            func_6273_f();
            Display.setFullscreen(mainFrame);
            Display.update();
            Thread.sleep(1000L);
            if(mainFrame)
            {
                func_6259_e();
            }
            if(field_6313_p != null)
            {
                func_6273_f();
                func_6249_a(field_6326_c, field_6325_d);
            }
            System.out.println((new StringBuilder()).append("Size: ").append(field_6326_c).append(", ").append(field_6325_d).toString());
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
    }

    private void func_6249_a(int i, int j)
    {
        if(i <= 0)
        {
            i = 1;
        }
        if(j <= 0)
        {
            j = 1;
        }
        field_6326_c = i;
        field_6325_d = j;
        if(field_6313_p != null)
        {
            ScaledResolution scaledresolution = new ScaledResolution(i, j);
            int k = scaledresolution.getScaledWidth();
            int l = scaledresolution.getScaledHeight();
            field_6313_p.func_6447_a(this, k, l);
        }
    }

    private void func_6265_t()
    {
        if(field_6305_x != null)
        {
            int i = field_6324_e.getBlockId(field_6305_x.blockX, field_6305_x.blockY, field_6305_x.blockZ);
            if(i == Block.grass.blockID_00)
            {
                i = Block.dirt.blockID_00;
            }
            if(i == Block.stairDouble.blockID_00)
            {
                i = Block.stairSingle.blockID_00;
            }
            if(i == Block.bedrock.blockID_00)
            {
                i = Block.stone.blockID_00;
            }
            field_6322_g.inventory.func_496_a(i, field_6327_b instanceof PlayerControllerTest);
        }
    }

    public void func_6246_i()
    {
        field_6308_u.func_555_a();
        field_6311_r.func_910_a(1.0F);
        if(field_6322_g != null)
        {
            field_6322_g.func_6420_o();
        }
        if(!field_6316_m && field_6324_e != null)
        {
            field_6327_b.func_6474_c();
        }
        GL11.glBindTexture(3553, field_6315_n.getTexture("/terrain.png"));
        if(!field_6316_m)
        {
            field_6315_n.func_1067_a();
        }
        if(field_6313_p == null && field_6322_g != null && field_6322_g.health_00 <= 0)
        {
            func_6272_a(null);
        }
        if(field_6313_p != null)
        {
            field_6302_aa = field_6283_R + 10000;
        }
        if(field_6313_p != null)
        {
            field_6313_p.handleInput();
            if(field_6313_p != null)
            {
                field_6313_p.updateScreen();
            }
        }
        if(field_6313_p == null || field_6313_p.field_948_f)
        {
            do
            {
                if(!Mouse.next())
                {
                    break;
                }
                long l = System.currentTimeMillis() - field_6287_N;
                if(l <= 200L)
                {
                    int j = Mouse.getEventDWheel();
                    if(j != 0)
                    {
                        field_6322_g.inventory.func_498_a(j);
                    }
                    if(field_6313_p == null)
                    {
                        if(!field_6289_L && Mouse.getEventButtonState())
                        {
                            func_6259_e();
                        } else
                        {
                            if(Mouse.getEventButton() == 0 && Mouse.getEventButtonState())
                            {
                                func_6243_a(0);
                                field_6302_aa = field_6283_R;
                            }
                            if(Mouse.getEventButton() == 1 && Mouse.getEventButtonState())
                            {
                                func_6243_a(1);
                                field_6302_aa = field_6283_R;
                            }
                            if(Mouse.getEventButton() == 2 && Mouse.getEventButtonState())
                            {
                                func_6265_t();
                            }
                        }
                    } else
                    if(field_6313_p != null)
                    {
                        field_6313_p.handleMouseInput();
                    }
                }
            } while(true);
            if(field_6282_S > 0)
            {
                field_6282_S--;
            }
            do
            {
                if(!Keyboard.next())
                {
                    break;
                }
                field_6322_g.func_460_a(Keyboard.getEventKey(), Keyboard.getEventKeyState());
                if(Keyboard.getEventKeyState())
                {
                    if(Keyboard.getEventKey() == 87)
                    {
                        func_6270_h();
                    } else
                    {
                        if(field_6313_p != null)
                        {
                            field_6313_p.handleKeyboardInput();
                        } else
                        {
                            if(Keyboard.getEventKey() == 1)
                            {
                                func_6252_g();
                            }
                            if(Keyboard.getEventKey() == 31 && Keyboard.isKeyDown(61))
                            {
                                func_6242_u();
                            }
                            if(Keyboard.getEventKey() == 63)
                            {
                                field_6304_y.thirdPersonView = !field_6304_y.thirdPersonView;
                            }
                            if(Keyboard.getEventKey() == field_6304_y.keyBindInventory.keyCode)
                            {
                                func_6272_a(new GuiInventory(field_6322_g.inventory, field_6322_g.inventory.craftingInventory));
                            }
                            if(Keyboard.getEventKey() == field_6304_y.field_6523_q.keyCode)
                            {
                                field_6322_g.func_444_a(field_6322_g.inventory.decrStackSize(field_6322_g.inventory.currentItem_00, 1), false);
                            }
                            if(/*func_6260_j() &&*/ Keyboard.getEventKey() == field_6304_y.field_6521_r.keyCode)
                            {
                                func_6272_a(new GuiChat());
                            }
                        }
                        for(int i = 0; i < 9; i++)
                        {
                            if(Keyboard.getEventKey() == 2 + i)
                            {
                                field_6322_g.inventory.currentItem_00 = i;
                            }
                        }

                        if(Keyboard.getEventKey() == field_6304_y.field_6520_s.keyCode)
                        {
                            field_6304_y.setOptionValue(4, !Keyboard.isKeyDown(42) && !Keyboard.isKeyDown(54) ? 1 : -1);
                        }
                    }
                }
            } while(true);
            if(field_6313_p == null)
            {
                if(Mouse.isButtonDown(0) && (float)(field_6283_R - field_6302_aa) >= field_6285_P.field_1380_a / 4F && field_6289_L)
                {
                    func_6243_a(0);
                    field_6302_aa = field_6283_R;
                }
                if(Mouse.isButtonDown(1) && (float)(field_6283_R - field_6302_aa) >= field_6285_P.field_1380_a / 4F && field_6289_L)
                {
                    func_6243_a(1);
                    field_6302_aa = field_6283_R;
                }
            }
            func_6254_a(0, field_6313_p == null && Mouse.isButtonDown(0) && field_6289_L);
        }
        if(field_6324_e != null)
        {
            if(field_6322_g != null)
            {
                field_6300_ab++;
                if(field_6300_ab == 30)
                {
                    field_6300_ab = 0;
                    field_6324_e.func_705_f(field_6322_g);
                }
            }
            field_6324_e.field_1039_l = field_6304_y.difficulty;
            if(!field_6316_m)
            {
                field_6311_r.func_911_a();
            }
            if(!field_6316_m)
            {
                field_6323_f.func_945_d();
            }
            if(!field_6316_m)
            {
                field_6324_e.func_633_c();
            }
            if(!field_6316_m || func_6260_j())
            {
                field_6324_e.tick();
            }
            if(!field_6316_m && field_6324_e != null)
            {
                field_6324_e.randomDisplayUpdates(MathHelper.convertToBlockCoord_00(field_6322_g.posX), MathHelper.convertToBlockCoord_00(field_6322_g.posY), MathHelper.convertToBlockCoord_00(field_6322_g.posZ));
            }
            if(!field_6316_m)
            {
                field_6321_h.func_1193_a();
            }
        }
        field_6287_N = System.currentTimeMillis();
    }

    private void func_6242_u()
    {
        System.out.println("FORCING RELOAD!");
        field_6301_A = new SoundManager();
        field_6301_A.func_340_a(field_6304_y);
        field_6284_Q.reloadResources();
    }

    public boolean func_6260_j()
    {
        return field_6324_e != null && field_6324_e.multiplayerWorld;
    }

    public void func_6247_b(String s)
    {
        func_6261_a(null);
        System.gc();
        World world = new World(new File(func_6240_b(), "saves"), s);
        if(world.field_1033_r)
        {
            func_6263_a(world, "Generating level");
        } else
        {
            func_6263_a(world, "Loading level");
        }
    }

    public void func_6237_k()
    {
        if(field_6322_g.field_4129_m == -1)
        {
            field_6322_g.field_4129_m = 0;
        } else
        {
            field_6322_g.field_4129_m = -1;
        }
        field_6324_e.func_607_d(field_6322_g);
        field_6322_g.field_646_aA = false;
        double d = field_6322_g.posX;
        double d1 = field_6322_g.posZ;
        double d2 = 8D;
        if(field_6322_g.field_4129_m == -1)
        {
            d /= d2;
            d1 /= d2;
            field_6322_g.func_365_c(d, field_6322_g.posY, d1, field_6322_g.rotationYaw, field_6322_g.rotationPitch);
            field_6324_e.func_4084_a(field_6322_g, false);
            World world = new World(field_6324_e, new WorldProviderHell());
            func_6256_a(world, "Entering the Nether", field_6322_g);
        } else
        {
            d *= d2;
            d1 *= d2;
            field_6322_g.func_365_c(d, field_6322_g.posY, d1, field_6322_g.rotationYaw, field_6322_g.rotationPitch);
            field_6324_e.func_4084_a(field_6322_g, false);
            World world1 = new World(field_6324_e, new WorldProvider());
            func_6256_a(world1, "Leaving the Nether", field_6322_g);
        }
        field_6322_g.worldObj_09 = field_6324_e;
        field_6322_g.func_365_c(d, field_6322_g.posY, d1, field_6322_g.rotationYaw, field_6322_g.rotationPitch);
        field_6324_e.func_4084_a(field_6322_g, false);
        (new Teleporter()).func_4107_a(field_6324_e, field_6322_g);
    }

    public void func_6261_a(World world)
    {
        func_6263_a(world, "");
    }

    public void func_6263_a(World world, String s)
    {
        func_6256_a(world, s, null);
    }

    public void func_6256_a(World world, String s, EntityPlayer entityplayer)
    {
        field_6312_q.func_596_a(s);
        field_6312_q.func_595_d("");
        field_6301_A.func_331_a(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
        if(field_6324_e != null)
        {
            field_6324_e.func_651_a(field_6312_q);
        }
        field_6324_e = world;
        System.out.println((new StringBuilder()).append("Player is ").append(field_6322_g).toString());
        if(world != null)
        {
            field_6327_b.func_717_a(world);
            if(!func_6260_j())
            {
                if(entityplayer == null)
                {
                    field_6322_g = (EntityPlayerSP)world.func_4085_a(EntityPlayerSP.class);
                }
            } else
            if(field_6322_g != null)
            {
                field_6322_g.func_374_q();
                if(world != null)
                {
                    world.entityJoinedWorld(field_6322_g);
                }
            }
            if(!world.multiplayerWorld)
            {
                func_6255_d(s);
            }
            System.out.println((new StringBuilder()).append("Player is now ").append(field_6322_g).toString());
            if(field_6322_g == null)
            {
                field_6322_g = (EntityPlayerSP)field_6327_b.func_4087_b(world);
                field_6322_g.func_374_q();
                field_6327_b.func_6476_a(field_6322_g);
            }
            field_6322_g.field_787_a = new MovementInputFromOptions(field_6304_y);
            if(field_6323_f != null)
            {
                field_6323_f.func_946_a(world);
            }
            if(field_6321_h != null)
            {
                field_6321_h.func_1188_a(world);
            }
            field_6327_b.func_6473_b(field_6322_g);
            if(entityplayer != null)
            {
                world.func_6464_c();
            }
            world.func_608_a(field_6322_g);
            if(world.field_1033_r)
            {
                world.func_651_a(field_6312_q);
            }
        } else
        {
            field_6322_g = null;
        }
        System.gc();
        field_6287_N = 0L;
    }

    private void func_6255_d(String s)
    {
        field_6312_q.func_596_a(s);
        field_6312_q.func_595_d("Building terrain");
        char c = '\200';
        int i = 0;
        int j = (c * 2) / 16 + 1;
        j *= j;
        for(int k = -c; k <= c; k += 16)
        {
            int l = field_6324_e.spawnX;
            int i1 = field_6324_e.spawnZ;
            if(field_6322_g != null)
            {
                l = (int)field_6322_g.posX;
                i1 = (int)field_6322_g.posZ;
            }
            for(int j1 = -c; j1 <= c; j1 += 16)
            {
                field_6312_q.func_593_a((i++ * 100) / j);
                field_6324_e.getBlockId(l + k, 64, i1 + j1);
                while(field_6324_e.func_6465_g()) ;
            }

        }

        field_6312_q.func_595_d("Simulating world for a bit");
        j = 2000;
        field_6324_e.func_656_j();
    }

    public void func_6268_a(String s, File file)
    {
        int i = s.indexOf("/");
        String s1 = s.substring(0, i);
        s = s.substring(i + 1);
        if(s1.equalsIgnoreCase("sound"))
        {
            field_6301_A.func_6372_a(s, file);
        } else
        if(s1.equalsIgnoreCase("newsound"))
        {
            field_6301_A.func_6372_a(s, file);
        } else
        if(s1.equalsIgnoreCase("streaming"))
        {
            field_6301_A.func_6373_b(s, file);
        } else
        if(s1.equalsIgnoreCase("music"))
        {
            field_6301_A.func_6374_c(s, file);
        } else
        if(s1.equalsIgnoreCase("newmusic"))
        {
            field_6301_A.func_6374_c(s, file);
        }
    }

    public OpenGlCapsChecker func_6251_l()
    {
        return field_6286_O;
    }

    public String func_6241_m()
    {
        return field_6323_f.func_953_b();
    }

    public String func_6262_n()
    {
        return field_6323_f.func_957_c();
    }

    public String func_6245_o()
    {
        return (new StringBuilder()).append("P: ").append(field_6321_h.func_1190_b()).append(". T: ").append(field_6324_e.func_687_d()).toString();
    }

    public void func_6239_p()
    {
        if(!field_6324_e.field_4209_q.func_6477_d())
        {
            func_6237_k();
        }
        field_6324_e.func_4076_b();
        if(field_6322_g != null)
        {
            field_6324_e.func_607_d(field_6322_g);
        }
        field_6322_g = (EntityPlayerSP)field_6327_b.func_4087_b(field_6324_e);
        field_6322_g.func_374_q();
        field_6327_b.func_6476_a(field_6322_g);
        field_6324_e.func_608_a(field_6322_g);
        field_6322_g.field_787_a = new MovementInputFromOptions(field_6304_y);
        field_6327_b.func_6473_b(field_6322_g);
        func_6255_d("Respawning");
    }

    public static void func_6269_a(String s, String s1)
    {
        func_6253_a(s, s1, null);
    }

    public static void func_6253_a(String s, String s1, String s2)
    {
        boolean flag = false;
        String s3 = s;
        Frame frame = new Frame("Minecraft");
        Canvas canvas = new Canvas();
        frame.setLayout(new BorderLayout());
        frame.add(canvas, "Center");
        canvas.setPreferredSize(new Dimension(854, 480));
        frame.pack();
        frame.setLocationRelativeTo(null);
        MinecraftImpl minecraftimpl = new MinecraftImpl(frame, canvas, null, 854, 480, flag, frame);
        Thread thread = new Thread(minecraftimpl, "Minecraft main thread");
        thread.setPriority(10);
        minecraftimpl.field_6317_l = false;
        minecraftimpl.field_6319_j = "www.minecraft.net";
        if(s3 != null && s1 != null)
        {
            minecraftimpl.field_6320_i = new Session(s3, s1);
        } else
        {
            minecraftimpl.field_6320_i = new Session((new StringBuilder()).append("Player").append(System.currentTimeMillis() % 1000L).toString(), "");
        }
        if(s2 != null)
        {
            String as[] = s2.split(":");
            minecraftimpl.func_6258_a(as[0], Integer.parseInt(as[1]));
        }
        frame.setVisible(true);
        frame.addWindowListener(new GameWindowListener(minecraftimpl, thread));
        thread.start();
    }

    public static void main(String args[])
    {
        String s = (new StringBuilder()).append("Player").append(System.currentTimeMillis() % 1000L).toString();
        if(args.length > 0)
        {
            s = args[0];
        }
        String s1 = "-";
        if(args.length > 1)
        {
            s1 = args[1];
        }
        s = (new StringBuilder()).append("Player").append(System.currentTimeMillis() % 1000L).toString();
        func_6269_a(s, s1);
    }

    public PlayerController field_6327_b;
    private boolean mainFrame;
    public int field_6326_c;
    public int field_6325_d;
    private OpenGlCapsChecker field_6286_O;
    private Timer field_6285_P;
    public World field_6324_e;
    public RenderGlobal field_6323_f;
    public EntityPlayerSP field_6322_g;
    public EffectRenderer field_6321_h;
    public Session field_6320_i;
    public String field_6319_j;
    public Canvas field_6318_k;
    public boolean field_6317_l;
    public volatile boolean field_6316_m;
    public RenderEngine field_6315_n;
    public FontRenderer field_6314_o;
    public GuiScreen field_6313_p;
    public LoadingScreenRenderer field_6312_q;
    public EntityRenderer field_6311_r;
    private ThreadDownloadResources field_6284_Q;
    private int field_6283_R;
    private int field_6282_S;
    private int field_6281_T;
    private int field_6280_U;
    public String field_6310_s;
    public int field_6309_t;
    public GuiIngame field_6308_u;
    public boolean field_6307_v;
    public ModelBiped field_6306_w;
    public MovingObjectPosition field_6305_x;
    public GameSettings field_6304_y;
    protected MinecraftApplet field_6303_z;
    public SoundManager field_6301_A;
    public MouseHelper field_6299_B;
    public TexturePackList field_6298_C;
    public File field_6297_D;
    public static long field_6296_E[] = new long[512];
    public static long field_6295_F[] = new long[512];
    public static int field_6294_G = 0;
    private String field_6279_V;
    private int field_6278_W;
    private TextureWaterFX field_6277_X;
    private TextureLavaFX field_6276_Y;
    private static File field_6275_Z = null;
    public volatile boolean field_6293_H;
    public String field_6292_I;
    boolean field_6291_J;
    long field_6290_K;
    public boolean field_6289_L;
    private int field_6302_aa;
    public boolean field_6288_M;
    long field_6287_N;
    private int field_6300_ab;

}
