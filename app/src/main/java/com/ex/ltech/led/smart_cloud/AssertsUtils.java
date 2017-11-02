package com.ex.ltech.led.smart_cloud;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class AssertsUtils
{
  public static boolean copyAllAssertToCacheFolder(Context paramContext)
    throws IOException
  {
    String[] arrayOfString1 = paramContext.getAssets().list("Devices");
    String str = paramContext.getFilesDir().toString();
    File localFile = new File(str + "/Devices/");
    localFile.mkdirs();
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      if (new File(str + "/Devices/" + arrayOfString1[i]).exists())
        continue;
      copyFileTo(paramContext, "Devices/" + arrayOfString1[i], str + "/Devices/" + arrayOfString1[i]);
    }
    String[] arrayOfString2 = localFile.list();
    for (int j = 0; j < arrayOfString2.length; j++);
    return true;
  }

  public static boolean copyFileTo(Context paramContext, String paramString1, String paramString2)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramString2);
    InputStream localInputStream = paramContext.getAssets().open(paramString1);
    byte[] arrayOfByte = new byte[1024];
    for (int i = localInputStream.read(arrayOfByte); i > 0; i = localInputStream.read(arrayOfByte))
      localFileOutputStream.write(arrayOfByte, 0, i);
    localFileOutputStream.flush();
    localInputStream.close();
    localFileOutputStream.close();
    return true;
  }

  public static String getTextByName(Context paramContext, String paramString)
  {
    Object localObject = "";
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramContext.getResources().getAssets().open(paramString)));
      while (true)
      {
        String str1 = localBufferedReader.readLine();
        if (str1 == null)
          break;
        String str2 = (String)localObject + str1;
        localObject = str2;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return (String)localObject;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.smart_cloud.AssertsUtils
 * JD-Core Version:    0.6.0
 */