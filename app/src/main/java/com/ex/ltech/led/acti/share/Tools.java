package com.ex.ltech.led.acti.share;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.conn.util.InetAddressUtils;

public class Tools
{
  static final String CFGPATH = Environment.getExternalStorageDirectory() + "/ltech/hongwai";
  private static final String[][] MIME_MapTable;
  public static Map<String, String> MsgEx;
  public static int byteSize;
  public static float chatFirst;
  public static String fileName;
  public static long fileSize;
  public static float fontSize;
  public static final String savePath = CFGPATH;
  public static double sendProgress;
  public static String sign;

  static
  {
    fileName = null;
    fileSize = 0L;
    byteSize = 5120;
    sign = ":";
    MsgEx = new HashMap();
    sendProgress = -1.0D;
    fontSize = 0.0F;
    chatFirst = 0.0F;
    MIME_MapTable = new String[][] { { ".3gp", "video/3gpp" }, { ".apk", "application/vnd.android.package-archive" }, { ".asf", "video/x-ms-asf" }, { ".avi", "video/x-msvideo" }, { ".bin", "application/octet-stream" }, { ".bmp", "image/bmp" }, { ".c", "text/plain" }, { ".class", "application/octet-stream" }, { ".conf", "text/plain" }, { ".cpp", "text/plain" }, { ".doc", "application/msword" }, { ".docx", "application/vnd.openxmlformats-officedocument.wordprocessingml.document" }, { ".xls", "application/vnd.ms-excel" }, { ".xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" }, { ".exe", "application/octet-stream" }, { ".gif", "image/gif" }, { ".gtar", "application/x-gtar" }, { ".gz", "application/x-gzip" }, { ".h", "text/plain" }, { ".htm", "text/html" }, { ".html", "text/html" }, { ".jar", "application/java-archive" }, { ".java", "text/plain" }, { ".jpeg", "image/jpeg" }, { ".jpg", "image/jpeg" }, { ".js", "application/x-javascript" }, { ".log", "text/plain" }, { ".m3u", "audio/x-mpegurl" }, { ".m4a", "audio/mp4a-latm" }, { ".m4b", "audio/mp4a-latm" }, { ".m4p", "audio/mp4a-latm" }, { ".m4u", "video/vnd.mpegurl" }, { ".m4v", "video/x-m4v" }, { ".mov", "video/quicktime" }, { ".mp2", "audio/x-mpeg" }, { ".mp3", "audio/x-mpeg" }, { ".mp4", "video/mp4" }, { ".mpc", "application/vnd.mpohun.certificate" }, { ".mpe", "video/mpeg" }, { ".mpeg", "video/mpeg" }, { ".mpg", "video/mpeg" }, { ".mpg4", "video/mp4" }, { ".mpga", "audio/mpeg" }, { ".msg", "application/vnd.ms-outlook" }, { ".ogg", "audio/ogg" }, { ".pdf", "application/pdf" }, { ".png", "image/png" }, { ".pps", "application/vnd.ms-powerpoint" }, { ".ppt", "application/vnd.ms-powerpoint" }, { ".pptx", "application/vnd.openxmlformats-officedocument.presentationml.presentation" }, { ".prop", "text/plain" }, { ".rc", "text/plain" }, { ".rmvb", "audio/x-pn-realaudio" }, { ".rtf", "application/rtf" }, { ".sh", "text/plain" }, { ".tar", "application/x-tar" }, { ".tgz", "application/x-compressed" }, { ".txt", "text/plain" }, { ".wav", "audio/x-wav" }, { ".wma", "audio/x-ms-wma" }, { ".wmv", "audio/x-ms-wmv" }, { ".wps", "application/vnd.ms-works" }, { ".xml", "text/plain" }, { ".z", "application/x-compress" }, { ".zip", "application/x-zip-compressed" }, { "", "*/*" } };
  }

  public static void chatRecord(String paramString1, String paramString2)
  {
    String str = (String)MsgEx.get(paramString1) + paramString2;
    MsgEx.put(paramString1, str);
  }

  public static String getBroadCastIP()
  {
    return getLocalHostIp().substring(0, 1 + getLocalHostIp().lastIndexOf(".")) + "255";
  }

  public static String getChangeTime(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(paramLong));
  }

  public static String getChatRecord(String paramString)
  {
    if ((MsgEx == null) || (MsgEx.isEmpty()))
      return "";
    return (String)MsgEx.get(paramString);
  }

  public static String getLocalHostIp()
  {
    Object localObject = "";
    try
    {
      while (true)
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        while (true)
          if (localEnumeration1.hasMoreElements())
          {
            Enumeration localEnumeration2 = ((NetworkInterface)localEnumeration1.nextElement()).getInetAddresses();
            if (!localEnumeration2.hasMoreElements())
              continue;
            InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
            if ((localInetAddress.isLoopbackAddress()) || (!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress())))
              break;
            String str = localInetAddress.getHostAddress();
            localObject = str;
          }
      }
      return localObject;
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return (String)localObject;
  }

  private static String getMIMEType(File paramFile)
  {
    String str1 = "*/*";
    String str2 = paramFile.getName();
    int i = str2.lastIndexOf(".");
    if (i < 0)
      return str1;
    String str3 = str2.substring(i, str2.length()).toLowerCase();
    if (str3 == "")
      return str1;
    for (int j = 0; j < MIME_MapTable.length; j++)
    {
      if (!str3.equals(MIME_MapTable[j][0]))
        continue;
      str1 = MIME_MapTable[j][1];
    }
    return str1;
  }

  public static String getMachineInfo()
  {
    return Build.MODEL;
  }

  // ERROR //
  public static String getName()
  {
    // Byte code:
    //   0: new 399	java/io/File
    //   3: dup
    //   4: getstatic 51	com/ex/ltech/led/acti/share/Tools:CFGPATH	Ljava/lang/String;
    //   7: invokespecial 422	java/io/File:<init>	(Ljava/lang/String;)V
    //   10: astore_0
    //   11: new 399	java/io/File
    //   14: dup
    //   15: new 27	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   22: getstatic 51	com/ex/ltech/led/acti/share/Tools:CFGPATH	Ljava/lang/String;
    //   25: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   28: ldc_w 424
    //   31: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   37: invokespecial 422	java/io/File:<init>	(Ljava/lang/String;)V
    //   40: astore_1
    //   41: aconst_null
    //   42: astore_2
    //   43: invokestatic 427	android/os/Environment:getExternalStorageState	()Ljava/lang/String;
    //   46: ldc_w 429
    //   49: invokevirtual 413	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   52: ifne +26 -> 78
    //   55: new 27	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 30	java/lang/StringBuilder:<init>	()V
    //   62: invokestatic 431	com/ex/ltech/led/acti/share/Tools:getMachineInfo	()Ljava/lang/String;
    //   65: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: ldc_w 300
    //   71: invokevirtual 45	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   74: invokevirtual 49	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   77: areturn
    //   78: aload_0
    //   79: invokevirtual 434	java/io/File:exists	()Z
    //   82: ifne +30 -> 112
    //   85: aload_0
    //   86: invokevirtual 437	java/io/File:mkdirs	()Z
    //   89: pop
    //   90: new 439	java/io/OutputStreamWriter
    //   93: dup
    //   94: new 441	java/io/FileOutputStream
    //   97: dup
    //   98: aload_1
    //   99: invokespecial 444	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   102: invokespecial 447	java/io/OutputStreamWriter:<init>	(Ljava/io/OutputStream;)V
    //   105: astore 6
    //   107: aload 6
    //   109: invokevirtual 452	java/io/Writer:close	()V
    //   112: new 454	java/io/BufferedReader
    //   115: dup
    //   116: new 456	java/io/InputStreamReader
    //   119: dup
    //   120: new 458	java/io/FileInputStream
    //   123: dup
    //   124: aload_1
    //   125: invokespecial 459	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   128: invokespecial 462	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   131: invokespecial 465	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   134: astore 4
    //   136: aload 4
    //   138: invokevirtual 468	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   141: astore_2
    //   142: aload 4
    //   144: invokevirtual 469	java/io/BufferedReader:close	()V
    //   147: aload_2
    //   148: areturn
    //   149: astore_3
    //   150: aload_3
    //   151: invokevirtual 470	java/lang/Exception:printStackTrace	()V
    //   154: goto -7 -> 147
    //   157: astore_3
    //   158: aconst_null
    //   159: astore_2
    //   160: goto -10 -> 150
    //   163: astore_3
    //   164: goto -14 -> 150
    //
    // Exception table:
    //   from	to	target	type
    //   55	78	149	java/lang/Exception
    //   78	107	149	java/lang/Exception
    //   112	136	149	java/lang/Exception
    //   107	112	157	java/lang/Exception
    //   136	147	163	java/lang/Exception
  }

  public static String getStartPath(String paramString)
  {
    File localFile = new File(paramString);
    if (localFile.isFile())
      paramString = localFile.getParent();
    return paramString;
  }

  public static long getTimel()
  {
    return new Date().getTime();
  }

  public static void openFile(Activity paramActivity, File paramFile)
  {
    Intent localIntent = new Intent();
    localIntent.addFlags(268435456);
    localIntent.setAction("android.intent.action.VIEW");
    String str = getMIMEType(paramFile);
    localIntent.setDataAndType(Uri.fromFile(paramFile), str);
    paramActivity.startActivity(localIntent);
  }

  public static byte[] toByteArray(Object paramObject)
  {
    byte[] arrayOfByte = null;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      arrayOfByte = localByteArrayOutputStream.toByteArray();
      localObjectOutputStream.close();
      localByteArrayOutputStream.close();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return arrayOfByte;
  }

  public static Object toObject(byte[] paramArrayOfByte)
  {
    Object localObject = null;
    try
    {
      ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
      ObjectInputStream localObjectInputStream = new ObjectInputStream(localByteArrayInputStream);
      localObject = localObjectInputStream.readObject();
      localObjectInputStream.close();
      localByteArrayInputStream.close();
      return localObject;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      return localObject;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      localClassNotFoundException.printStackTrace();
    }
    return localObject;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.Tools
 * JD-Core Version:    0.6.0
 */