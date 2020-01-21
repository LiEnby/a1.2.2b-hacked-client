package net.minecraft.src;
// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) braces deadcode 

import java.net.HttpURLConnection;
import java.net.URL;
import javax.imageio.ImageIO;

class ThreadDownloadImage extends Thread
{

    ThreadDownloadImage(ThreadDownloadImageData threaddownloadimagedata, String s, ImageBuffer imagebuffer)
    {
        imageData_00 = threaddownloadimagedata;
        location = s;
        buffer_01 = imagebuffer;
    }

    public void run()
    {
        HttpURLConnection httpurlconnection = null;
        try
        {
            URL url = new URL(SkinFixes.getSkinUrl(location));
            httpurlconnection = (HttpURLConnection)url.openConnection();
            httpurlconnection.setDoInput(true);
            httpurlconnection.setDoOutput(false);
            httpurlconnection.connect();
            if(httpurlconnection.getResponseCode() == 404)
            {
                return;
            }
            if(buffer_01 == null)
            {
                imageData_00.image = ImageIO.read(httpurlconnection.getInputStream());
            } else
            {
                imageData_00.image = buffer_01.func_883_a(ImageIO.read(httpurlconnection.getInputStream()));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }
        finally
        {
            httpurlconnection.disconnect();
        }
    }

    final String location; /* synthetic field */
    final ImageBuffer buffer_01; /* synthetic field */
    final ThreadDownloadImageData imageData_00; /* synthetic field */
}
