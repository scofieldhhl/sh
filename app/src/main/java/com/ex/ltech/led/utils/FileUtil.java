package com.ex.ltech.led.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtil
{
  public static final String CRASH = "/ltech/crash";
  public static final String HONGWAI = "/ltech/hongwai";
  public static final String HONGWAI_FILE = "data";
  public static final String IMAGE = "/ltech/led/image";
  public static final String IMAGES = "/ltech/led/mainTitle";
  public static final String ROOTFile = "/ltech/led";
  private boolean coverFlag = false;
  private String fileName;
  private String filePath = isAddSprit(paramString1);
  private WirteProgress wirteProgress;

  public FileUtil(String paramString)
  {
    this.fileName = findFileName(paramString);
  }

  public FileUtil(String paramString1, String paramString2)
  {
    this.fileName = paramString2;
    mkDirs(paramString1);
  }

  public FileUtil(String paramString1, String paramString2, boolean paramBoolean)
  {
    this.fileName = paramString2;
    this.coverFlag = paramBoolean;
    mkDirs(paramString1);
  }

  public static boolean deleteFile(File paramFile)
  {
    int i = 0;
    if (!paramFile.exists())
      return false;
    if (paramFile.isDirectory())
    {
      File[] arrayOfFile = paramFile.listFiles();
      int j = arrayOfFile.length;
      while (i < j)
      {
        deleteFile(arrayOfFile[i]);
        i++;
      }
    }
    paramFile.delete();
    return true;
  }

  public static boolean deleteFile(String paramString)
  {
    if (paramString == null)
      return false;
    return deleteFile(new File(paramString));
  }

  private String findFilePath(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0));
    int i;
    do
    {
      return paramString;
      i = paramString.lastIndexOf("/");
    }
    while (i <= 0);
    return paramString.substring(0, i);
  }

  private String isAddSprit(String paramString)
  {
    int i = paramString.length();
    if (i <= 0)
      return paramString;
    int j = paramString.charAt(i - 1);
    if ((j != 47) || (j != 92))
      paramString = paramString + "/";
    return paramString;
  }

  public static String readAssets(Context paramContext, String paramString)
  {
    InputStream localInputStream = null;
    try
    {
      localInputStream = paramContext.getAssets().open(paramString);
      localObject2 = null;
      if (localInputStream != null)
      {
        arrayOfByte = new byte[1024];
        localByteArrayOutputStream = new ByteArrayOutputStream();
        i = localInputStream.read(arrayOfByte);
        if (i != -1)
          break label86;
        localInputStream.close();
        localByteArrayOutputStream.close();
        String str = new String(localByteArrayOutputStream.toByteArray());
        localObject2 = str;
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
    }
    catch (IOException localIOException2)
    {
      label86: 
      do
      {
        byte[] arrayOfByte;
        ByteArrayOutputStream localByteArrayOutputStream;
        int i;
        localIOException2.printStackTrace();
        Object localObject2 = null;
      }
      while (localInputStream == null);
      try
      {
        localInputStream.close();
        return null;
      }
      catch (IOException localIOException3)
      {
        localIOException3.printStackTrace();
        return null;
      }
    }
    finally
    {
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      throw localObject1;
    }
    catch (IOException localIOException1)
    {
      while (true)
        localIOException1.printStackTrace();
    }
  }

  public static boolean rename(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1);
    if (!localFile.exists())
      return false;
    localFile.renameTo(new File(paramString2));
    return true;
  }

  public static void saveCrashTextTxt(String paramString)
  {
    File localFile1 = new File(Environment.getExternalStorageDirectory() + "/ltech/crash");
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(Environment.getExternalStorageDirectory() + "/ltech/crash" + "/" + "crash.txt");
    if (!localFile2.isFile());
    try
    {
      localFile2.createNewFile();
      writeFile(new ByteArrayInputStream(paramString.getBytes()), localFile2.getAbsolutePath());
      return;
    }
    catch (IOException localIOException)
    {
      while (true)
        localIOException.printStackTrace();
    }
  }

  public static void saveMyBitmap(String paramString1, Bitmap paramBitmap, String paramString2)
  {
    File localFile1 = new File(Environment.getExternalStorageDirectory() + paramString2);
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(paramString1);
    if (!localFile2.isFile());
    try
    {
      localFile2.createNewFile();
    }
    catch (IOException localIOException1)
    {
      try
      {
        FileOutputStream localFileOutputStream1 = new FileOutputStream(localFile2);
        localFileOutputStream2 = localFileOutputStream1;
        paramBitmap.compress(Bitmap.CompressFormat.PNG, 100, localFileOutputStream2);
      }
      catch (IOException localIOException1)
      {
        try
        {
          localFileOutputStream2.flush();
        }
        catch (IOException localIOException1)
        {
          try
          {
            while (true)
            {
              localFileOutputStream2.close();
              return;
              localIOException3 = localIOException3;
              localIOException3.printStackTrace();
              continue;
              localFileNotFoundException = localFileNotFoundException;
              localFileNotFoundException.printStackTrace();
              FileOutputStream localFileOutputStream2 = null;
              continue;
              localIOException1 = localIOException1;
              localIOException1.printStackTrace();
            }
          }
          catch (IOException localIOException2)
          {
            localIOException2.printStackTrace();
          }
        }
      }
    }
  }

  // ERROR //
  public static void writeFile(InputStream paramInputStream, String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: ifnonnull +4 -> 5
    //   4: return
    //   5: new 59	java/io/File
    //   8: dup
    //   9: aload_1
    //   10: invokespecial 78	java/io/File:<init>	(Ljava/lang/String;)V
    //   13: astore_2
    //   14: aconst_null
    //   15: astore_3
    //   16: aload_0
    //   17: invokevirtual 222	java/io/InputStream:available	()I
    //   20: istore 15
    //   22: iload 15
    //   24: i2l
    //   25: pop2
    //   26: new 198	java/io/FileOutputStream
    //   29: dup
    //   30: aload_2
    //   31: iconst_0
    //   32: invokespecial 225	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   35: astore 9
    //   37: sipush 5120
    //   40: newarray byte
    //   42: astore 12
    //   44: aload_0
    //   45: aload 12
    //   47: invokevirtual 136	java/io/InputStream:read	([B)I
    //   50: istore 13
    //   52: iload 13
    //   54: iconst_m1
    //   55: if_icmpeq +43 -> 98
    //   58: aload 9
    //   60: aload 12
    //   62: iconst_0
    //   63: iload 13
    //   65: invokevirtual 226	java/io/FileOutputStream:write	([BII)V
    //   68: goto -24 -> 44
    //   71: astore 4
    //   73: aload 9
    //   75: astore_3
    //   76: aload 4
    //   78: invokevirtual 154	java/io/FileNotFoundException:printStackTrace	()V
    //   81: aload_3
    //   82: ifnull -78 -> 4
    //   85: aload_3
    //   86: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   89: return
    //   90: astore 7
    //   92: aload 7
    //   94: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   97: return
    //   98: aload 9
    //   100: invokevirtual 216	java/io/FileOutputStream:flush	()V
    //   103: aload 9
    //   105: ifnull +92 -> 197
    //   108: aload 9
    //   110: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   113: return
    //   114: astore 14
    //   116: aload 14
    //   118: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   121: return
    //   122: astore 10
    //   124: aload 10
    //   126: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   129: aload_3
    //   130: ifnull -126 -> 4
    //   133: aload_3
    //   134: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   137: return
    //   138: astore 11
    //   140: aload 11
    //   142: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   145: return
    //   146: astore 5
    //   148: aload_3
    //   149: ifnull +7 -> 156
    //   152: aload_3
    //   153: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   156: aload 5
    //   158: athrow
    //   159: astore 6
    //   161: aload 6
    //   163: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   166: goto -10 -> 156
    //   169: astore 8
    //   171: goto -145 -> 26
    //   174: astore 5
    //   176: aload 9
    //   178: astore_3
    //   179: goto -31 -> 148
    //   182: astore 10
    //   184: aload 9
    //   186: astore_3
    //   187: goto -63 -> 124
    //   190: astore 4
    //   192: aconst_null
    //   193: astore_3
    //   194: goto -118 -> 76
    //   197: return
    //
    // Exception table:
    //   from	to	target	type
    //   37	44	71	java/io/FileNotFoundException
    //   44	52	71	java/io/FileNotFoundException
    //   58	68	71	java/io/FileNotFoundException
    //   98	103	71	java/io/FileNotFoundException
    //   85	89	90	java/io/IOException
    //   108	113	114	java/io/IOException
    //   16	22	122	java/io/IOException
    //   26	37	122	java/io/IOException
    //   133	137	138	java/io/IOException
    //   16	22	146	finally
    //   26	37	146	finally
    //   76	81	146	finally
    //   124	129	146	finally
    //   152	156	159	java/io/IOException
    //   16	22	169	java/lang/Exception
    //   37	44	174	finally
    //   44	52	174	finally
    //   58	68	174	finally
    //   98	103	174	finally
    //   37	44	182	java/io/IOException
    //   44	52	182	java/io/IOException
    //   58	68	182	java/io/IOException
    //   98	103	182	java/io/IOException
    //   16	22	190	java/io/FileNotFoundException
    //   26	37	190	java/io/FileNotFoundException
  }

  public static void writeToFile(String paramString1, String paramString2, Bitmap paramBitmap)
  {
    File localFile1 = new File(Environment.getExternalStorageDirectory() + "/ltech/led/image");
    if (!localFile1.exists())
      localFile1.mkdirs();
    File localFile2 = new File(paramString2);
    File localFile3 = new File(paramString1);
    if (!localFile2.isFile());
    try
    {
      localFile2.createNewFile();
      boolean bool1 = localFile3.exists();
      localObject = null;
      if (bool1)
      {
        boolean bool2 = localFile3.isFile();
        localObject = null;
        if (!bool2);
      }
    }
    catch (IOException localIOException)
    {
      try
      {
        FileInputStream localFileInputStream = new FileInputStream(localFile3);
        localObject = localFileInputStream;
        writeFile(localObject, paramString2);
        return;
        localIOException = localIOException;
        localIOException.printStackTrace();
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        while (true)
        {
          localFileNotFoundException.printStackTrace();
          Object localObject = null;
        }
      }
    }
  }

  public String findFileName(String paramString)
  {
    if ((paramString == null) || (paramString.length() <= 0))
      return "";
    int i = paramString.lastIndexOf("/");
    if (i > 0)
      return paramString.substring(i + 1);
    return "";
  }

  public void mkDirs(File paramFile)
  {
    if (!paramFile.exists())
      paramFile.mkdirs();
  }

  public void mkDirs(String paramString)
  {
    File localFile = new File(paramString);
    if (!localFile.exists())
      localFile.mkdirs();
  }

  public InputStream readFile(String paramString1, String paramString2)
  {
    File localFile = new File(paramString1 + paramString2);
    boolean bool1 = localFile.exists();
    Object localObject = null;
    if (bool1)
    {
      boolean bool2 = localFile.isFile();
      localObject = null;
      if (!bool2);
    }
    try
    {
      FileInputStream localFileInputStream = new FileInputStream(localFile);
      localObject = localFileInputStream;
      return localObject;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
    }
    return null;
  }

  // ERROR //
  public byte[] readFileToByte()
  {
    // Byte code:
    //   0: new 59	java/io/File
    //   3: dup
    //   4: new 100	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: getfield 42	com/ex/ltech/led/utils/FileUtil:filePath	Ljava/lang/String;
    //   15: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_0
    //   19: getfield 47	com/ex/ltech/led/utils/FileUtil:fileName	Ljava/lang/String;
    //   22: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokespecial 78	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: astore_1
    //   32: aconst_null
    //   33: astore_2
    //   34: aload_1
    //   35: invokevirtual 63	java/io/File:exists	()Z
    //   38: ifeq +131 -> 169
    //   41: aload_1
    //   42: invokevirtual 178	java/io/File:isFile	()Z
    //   45: ifeq +124 -> 169
    //   48: aload_1
    //   49: invokevirtual 242	java/io/File:length	()J
    //   52: l2i
    //   53: newarray byte
    //   55: astore_3
    //   56: new 230	java/io/FileInputStream
    //   59: dup
    //   60: aload_1
    //   61: invokespecial 231	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   64: astore 4
    //   66: aload 4
    //   68: aload_3
    //   69: invokevirtual 243	java/io/FileInputStream:read	([B)I
    //   72: pop
    //   73: aload 4
    //   75: ifnull +122 -> 197
    //   78: aload 4
    //   80: invokevirtual 244	java/io/FileInputStream:close	()V
    //   83: aload_3
    //   84: areturn
    //   85: astore 12
    //   87: aload 12
    //   89: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   92: aload_3
    //   93: areturn
    //   94: astore 5
    //   96: aload 5
    //   98: invokevirtual 154	java/io/FileNotFoundException:printStackTrace	()V
    //   101: aload_2
    //   102: ifnull -19 -> 83
    //   105: aload_2
    //   106: invokevirtual 244	java/io/FileInputStream:close	()V
    //   109: aload_3
    //   110: areturn
    //   111: astore 8
    //   113: aload 8
    //   115: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   118: aload_3
    //   119: areturn
    //   120: astore 9
    //   122: aload 9
    //   124: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   127: aload_2
    //   128: ifnull -45 -> 83
    //   131: aload_2
    //   132: invokevirtual 244	java/io/FileInputStream:close	()V
    //   135: aload_3
    //   136: areturn
    //   137: astore 10
    //   139: aload 10
    //   141: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   144: aload_3
    //   145: areturn
    //   146: astore 6
    //   148: aload_2
    //   149: ifnull +7 -> 156
    //   152: aload_2
    //   153: invokevirtual 244	java/io/FileInputStream:close	()V
    //   156: aload 6
    //   158: athrow
    //   159: astore 7
    //   161: aload 7
    //   163: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   166: goto -10 -> 156
    //   169: iconst_0
    //   170: newarray byte
    //   172: areturn
    //   173: astore 6
    //   175: aload 4
    //   177: astore_2
    //   178: goto -30 -> 148
    //   181: astore 9
    //   183: aload 4
    //   185: astore_2
    //   186: goto -64 -> 122
    //   189: astore 5
    //   191: aload 4
    //   193: astore_2
    //   194: goto -98 -> 96
    //   197: aload_3
    //   198: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   78	83	85	java/io/IOException
    //   56	66	94	java/io/FileNotFoundException
    //   105	109	111	java/io/IOException
    //   56	66	120	java/io/IOException
    //   131	135	137	java/io/IOException
    //   56	66	146	finally
    //   96	101	146	finally
    //   122	127	146	finally
    //   152	156	159	java/io/IOException
    //   66	73	173	finally
    //   66	73	181	java/io/IOException
    //   66	73	189	java/io/FileNotFoundException
  }

  public void setWirteProgress(WirteProgress paramWirteProgress)
  {
    this.wirteProgress = paramWirteProgress;
  }

  // ERROR //
  public void writeFile(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 59	java/io/File
    //   3: dup
    //   4: new 100	java/lang/StringBuilder
    //   7: dup
    //   8: invokespecial 101	java/lang/StringBuilder:<init>	()V
    //   11: aload_0
    //   12: getfield 42	com/ex/ltech/led/utils/FileUtil:filePath	Ljava/lang/String;
    //   15: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   18: aload_0
    //   19: getfield 47	com/ex/ltech/led/utils/FileUtil:fileName	Ljava/lang/String;
    //   22: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 109	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokespecial 78	java/io/File:<init>	(Ljava/lang/String;)V
    //   31: astore_2
    //   32: aconst_null
    //   33: astore_3
    //   34: new 198	java/io/FileOutputStream
    //   37: dup
    //   38: aload_2
    //   39: aload_0
    //   40: getfield 36	com/ex/ltech/led/utils/FileUtil:coverFlag	Z
    //   43: invokespecial 225	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   46: astore 4
    //   48: aload 4
    //   50: aload_1
    //   51: invokevirtual 250	java/io/FileOutputStream:write	([B)V
    //   54: aload 4
    //   56: invokevirtual 216	java/io/FileOutputStream:flush	()V
    //   59: aload 4
    //   61: ifnull +112 -> 173
    //   64: aload 4
    //   66: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   69: return
    //   70: astore 11
    //   72: aload 11
    //   74: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   77: return
    //   78: astore 5
    //   80: aload 5
    //   82: invokevirtual 154	java/io/FileNotFoundException:printStackTrace	()V
    //   85: aload_3
    //   86: ifnull -17 -> 69
    //   89: aload_3
    //   90: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   93: return
    //   94: astore 8
    //   96: aload 8
    //   98: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   101: return
    //   102: astore 9
    //   104: aload 9
    //   106: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   109: aload_3
    //   110: ifnull -41 -> 69
    //   113: aload_3
    //   114: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   117: return
    //   118: astore 10
    //   120: aload 10
    //   122: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   125: return
    //   126: astore 6
    //   128: aload_3
    //   129: ifnull +7 -> 136
    //   132: aload_3
    //   133: invokevirtual 217	java/io/FileOutputStream:close	()V
    //   136: aload 6
    //   138: athrow
    //   139: astore 7
    //   141: aload 7
    //   143: invokevirtual 155	java/io/IOException:printStackTrace	()V
    //   146: goto -10 -> 136
    //   149: astore 6
    //   151: aload 4
    //   153: astore_3
    //   154: goto -26 -> 128
    //   157: astore 9
    //   159: aload 4
    //   161: astore_3
    //   162: goto -58 -> 104
    //   165: astore 5
    //   167: aload 4
    //   169: astore_3
    //   170: goto -90 -> 80
    //   173: return
    //
    // Exception table:
    //   from	to	target	type
    //   64	69	70	java/io/IOException
    //   34	48	78	java/io/FileNotFoundException
    //   89	93	94	java/io/IOException
    //   34	48	102	java/io/IOException
    //   113	117	118	java/io/IOException
    //   34	48	126	finally
    //   80	85	126	finally
    //   104	109	126	finally
    //   132	136	139	java/io/IOException
    //   48	59	149	finally
    //   48	59	157	java/io/IOException
    //   48	59	165	java/io/FileNotFoundException
  }

  public static abstract interface WirteProgress
  {
    public abstract void progress(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.FileUtil
 * JD-Core Version:    0.6.0
 */