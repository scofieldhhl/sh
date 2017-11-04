package io.xlink.wifi.sdk.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.Random;

public final class b
{
  public static Context a;
  private static Handler b = null;
  private static Handler c = null;
  private static HandlerThread d = null;
  private static int e = 0;

  public static byte a(int paramInt, byte paramByte)
  {
    if (paramInt > 7)
      throw new IllegalAccessError("setByteBit error index>7!!! ");
    if (paramInt == 0)
      paramByte = (byte)(paramByte | 0x1);
    do
    {
      return paramByte;
      if (paramInt == 1)
        return (byte)(paramByte | 0x2);
      if (paramInt == 2)
        return (byte)(paramByte | 0x4);
      if (paramInt == 3)
        return (byte)(paramByte | 0x8);
      if (paramInt == 4)
        return (byte)(paramByte | 0x10);
      if (paramInt == 5)
        return (byte)(paramByte | 0x20);
      if (paramInt == 6)
        return (byte)(paramByte | 0x40);
    }
    while (paramInt != 7);
    return (byte)(paramByte | 0x80);
  }

  public static byte a(int paramInt1, int paramInt2)
  {
    return (byte)(0x3 | (byte)((byte)(paramInt1 << 4) | (byte)(0x8 & paramInt2 << 3)));
  }

  public static int a(byte paramByte)
  {
    return 0x0 | 0xF & paramByte >> 4;
  }

  public static String a(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Byte.valueOf(paramArrayOfByte[i]);
      localStringBuffer.append(String.format("%02X", arrayOfObject) + " ");
    }
    return localStringBuffer.toString();
  }

  public static short a(byte[] paramArrayOfByte, int paramInt)
  {
    return (short)((0xFF & paramArrayOfByte[paramInt]) << 8 | 0xFF & paramArrayOfByte[(paramInt + 1)]);
  }

  public static void a()
  {
    d = new HandlerThread("Xlink Worker Thread", 10);
    d.start();
    b = new Handler(d.getLooper());
    c = new Handler();
  }

  public static void a(Runnable paramRunnable)
  {
    c.post(paramRunnable);
  }

  protected static void a(Runnable paramRunnable, Boolean paramBoolean, long paramLong)
  {
    if (paramBoolean.booleanValue())
    {
      b.postAtFrontOfQueue(paramRunnable);
      return;
    }
    if (paramLong != 0L)
    {
      b.postDelayed(paramRunnable, paramLong);
      return;
    }
    b.post(paramRunnable);
  }

  public static boolean a(byte paramByte, int paramInt)
  {
    if (paramInt > 7)
      throw new IllegalAccessError("readFlagsBit error index>7!!! ");
    return (0x1 & paramByte << 7 - paramInt >> 7) == 1;
  }

  public static final byte[] a(int paramInt)
  {
    try
    {
      byte[] arrayOfByte1 = c(paramInt);
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      return arrayOfByte2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static byte[] a(int paramInt, byte[] paramArrayOfByte)
  {
    paramArrayOfByte[3] = (byte)(paramInt & 0xFF);
    paramArrayOfByte[2] = (byte)((0xFF00 & paramInt) >> 8);
    paramArrayOfByte[1] = (byte)((0xFF0000 & paramInt) >> 16);
    paramArrayOfByte[0] = (byte)((0xFF000000 & paramInt) >> 24);
    return paramArrayOfByte;
  }

  public static final byte[] a(String paramString)
  {
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      return arrayOfByte2;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static byte[] a(short paramShort)
  {
    byte[] arrayOfByte = new byte[2];
    for (int i = 0; i < 2; i++)
      arrayOfByte[i] = (byte)(0xFF & paramShort >>> 8 * (-1 + arrayOfByte.length - i));
    return arrayOfByte;
  }

  public static byte[] a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2 - paramInt1];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }

  public static int b(byte[] paramArrayOfByte)
  {
    return (0xFF & paramArrayOfByte[0]) << 24 | (0xFF & paramArrayOfByte[1]) << 16 | (0xFF & paramArrayOfByte[2]) << 8 | 0xFF & paramArrayOfByte[3];
  }

  public static String b(byte paramByte)
  {
    String str = "";
    for (byte b1 = 0; b1 < 8; b1++)
    {
      int i = 0x1 & (0x80 & paramByte << b1) >> 7;
      str = str + i;
    }
    return str;
  }

  public static InetAddress b()
  {
    try
    {
      InetAddress localInetAddress = InetAddress.getByName("255.255.255.255");
      return localInetAddress;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      localUnknownHostException.printStackTrace();
    }
    return null;
  }

  public static void b(Runnable paramRunnable)
  {
    a(paramRunnable, Boolean.valueOf(false), 0L);
  }

  public static byte[] b(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(0xFF & paramInt >> 24);
    arrayOfByte[1] = (byte)(0xFF & paramInt >> 16);
    arrayOfByte[2] = (byte)(0xFF & paramInt >> 8);
    arrayOfByte[3] = (byte)(paramInt & 0xFF);
    return arrayOfByte;
  }

  public static byte[] b(String paramString)
  {
    byte[] arrayOfByte = a(paramString);
    int i = arrayOfByte.length;
    for (int j = 0; j < i; j++)
      arrayOfByte[j] = (byte)(0x35 ^ arrayOfByte[j]);
    return arrayOfByte;
  }

  public static int c()
  {
    if ((e == 0) || (e > 64000))
      e = f();
    int i = e;
    e = i + 1;
    return i;
  }

  public static String c(byte[] paramArrayOfByte)
  {
    return new String(paramArrayOfByte, Charset.forName("UTF-8"));
  }

  public static byte[] c(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[3] = (byte)(0xFF & paramInt >> 24);
    arrayOfByte[2] = (byte)(0xFF & paramInt >> 16);
    arrayOfByte[1] = (byte)(0xFF & paramInt >> 8);
    arrayOfByte[0] = (byte)(paramInt & 0xFF);
    return arrayOfByte;
  }

  public static byte[] c(String paramString)
  {
    int i = 0;
    if ((paramString.isEmpty()) || (paramString.length() % 2 != 0))
      return null;
    paramString.replaceAll(":", "");
    String str = paramString.toLowerCase();
    byte[] arrayOfByte = new byte[str.length() / 2];
    int j = 0;
    while (i < arrayOfByte.length)
    {
      int k = (byte)(0xFF & Character.digit(str.charAt(j), 16));
      arrayOfByte[i] = (byte)((byte)(0xFF & Character.digit(str.charAt(j + 1), 16)) | k << 4);
      j += 2;
      i++;
    }
    return arrayOfByte;
  }

  public static int d(int paramInt)
  {
    if (paramInt < 0)
      paramInt += 65536;
    return paramInt;
  }

  public static String d()
  {
    WifiManager localWifiManager = (WifiManager)a.getSystemService("wifi");
    if (!localWifiManager.isWifiEnabled())
      return null;
    return e(localWifiManager.getConnectionInfo().getIpAddress());
  }

  public static String d(byte[] paramArrayOfByte)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuffer.append(0);
      localStringBuffer.append(str.toUpperCase());
    }
    return localStringBuffer.toString();
  }

  public static byte[] d(String paramString)
  {
    return paramString.getBytes(Charset.forName("UTF-8"));
  }

  private static String e(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
  }

  public static InetAddress e(String paramString)
  {
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(paramString);
      return localInetAddress;
    }
    catch (UnknownHostException localUnknownHostException)
    {
      localUnknownHostException.printStackTrace();
    }
    return null;
  }

  public static short e(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte.length < 2);
    for (int k = paramArrayOfByte.length; ; k = 2)
    {
      if (i >= k)
        break label44;
      j = (short)((short)(j << 8) | 0xFF & paramArrayOfByte[i]);
      i++;
      break;
    }
    label44: return j;
  }

  public static boolean e()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)a.getSystemService("connectivity");
    int i = 0;
    NetworkInfo[] arrayOfNetworkInfo;
    if (localConnectivityManager != null)
    {
      arrayOfNetworkInfo = localConnectivityManager.getAllNetworkInfo();
      i = 0;
      if (arrayOfNetworkInfo == null);
    }
    for (int j = 0; ; j++)
    {
      int k = arrayOfNetworkInfo.length;
      i = 0;
      if (j < k)
      {
        if ((!arrayOfNetworkInfo[j].getTypeName().equals("WIFI")) || (!arrayOfNetworkInfo[j].isConnected()))
          continue;
        i = 1;
      }
      return i;
    }
  }

  private static int f()
  {
    return 1000 + new Random().nextInt(64000) % 63001;
  }

  public static int f(byte[] paramArrayOfByte)
  {
    int i = 0;
    int j = 0;
    if (paramArrayOfByte.length < 4);
    for (int k = paramArrayOfByte.length; ; k = 4)
    {
      if (i >= k)
        break label42;
      j = j << 8 | 0xFF & paramArrayOfByte[i];
      i++;
      break;
    }
    label42: return j;
  }

  public static float g(byte[] paramArrayOfByte)
  {
    ByteBuffer localByteBuffer = ByteBuffer.allocate(4);
    localByteBuffer.put(paramArrayOfByte);
    return localByteBuffer.getFloat(0);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.util.b
 * JD-Core Version:    0.6.0
 */