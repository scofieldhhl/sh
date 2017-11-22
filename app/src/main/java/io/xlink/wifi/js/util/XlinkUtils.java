package io.xlink.wifi.js.util;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Base64;
import android.view.View;

import com.ex.ltech.led.MyApp;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class XlinkUtils
{
  private static final int bitValue0 = 1;
  private static final int bitValue1 = 2;
  private static final int bitValue2 = 4;
  private static final int bitValue3 = 8;
  private static final int bitValue4 = 16;
  private static final int bitValue5 = 32;
  private static final int bitValue6 = 64;
  private static final int bitValue7 = 128;

  public static final String MD5(String paramString)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      int i = arrayOfByte2.length;
      char[] arrayOfChar2 = new char[i * 2];
      int j = 0;
      int k = 0;
      while (j < i)
      {
        int m = arrayOfByte2[j];
        int n = k + 1;
        arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
        k = n + 1;
        arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
        j++;
      }
      String str = new String(arrayOfChar2);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static byte[] base64Decrypt(String paramString)
  {
    byte[] arrayOfByte = Base64.decode(paramString, 0);
    if ((arrayOfByte == null) || (arrayOfByte.length == 0))
      arrayOfByte = paramString.getBytes();
    return arrayOfByte;
  }

  public static String base64Encrypt(byte[] paramArrayOfByte)
  {
    return new String(Base64.encode(paramArrayOfByte, 0));
  }

  public static String base64EncryptUTF(byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    return new String(Base64.encode(paramArrayOfByte, 0), "UTF-8");
  }

  public static <T extends View> T getAdapterView(View paramView, int paramInt)
  {
    /*SparseArray localSparseArray = (SparseArray)paramView.getTag();
    if (localSparseArray == null)
    {
      localSparseArray = new SparseArray();
      paramView.setTag(localSparseArray);
    }
    View localView = (View)localSparseArray.get(paramInt);
    if (localView == null)
    {
      localView = paramView.findViewById(paramInt);
      localSparseArray.put(paramInt, localView);
    }
    return localView;*/
    return null;
  }

  public static String getBinString(byte paramByte)
  {
    String str = "";
    for (byte b = 0; b < 8; b++)
    {
      int i = 0x1 & (0x80 & paramByte << b) >> 7;
      str = str + i;
    }
    return str;
  }

  public static String getHexBinString(byte[] paramArrayOfByte)
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

  public static JSONObject getJsonObject(Map<String, Object> paramMap)
  {
    JSONObject localJSONObject = new JSONObject();
    Iterator localIterator = paramMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      try
      {
        localJSONObject.put((String)localEntry.getKey(), localEntry.getValue());
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    return localJSONObject;
  }

  public static boolean isConnected()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)MyApp.getApp().
            getSystemService(Context.CONNECTIVITY_SERVICE);
    if (localConnectivityManager != null)
    {
      NetworkInfo localNetworkInfo = localConnectivityManager.getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
        return true;
    }
    return false;
  }

  public static boolean isWifi()
  {
    /*int i = 1;
    ConnectivityManager localConnectivityManager = (ConnectivityManager)MyApp.getApp().getSystemService("connectivity");
    if ((localConnectivityManager == null) || (localConnectivityManager.getActiveNetworkInfo() == null))
      i = 0;
    do
      return i;
    while (localConnectivityManager.getActiveNetworkInfo().getType() == i);
    return false;*/
    return true;
  }

  public static void longTips(String paramString)
  {
  }

  public static void openSetting(Activity paramActivity)
  {
    /*Intent localIntent;
    if (Build.VERSION.SDK_INT > 10)
      localIntent = new Intent("android.settings.WIFI_SETTINGS");
    while (true)
    {
      paramActivity.startActivityForResult(localIntent, 0);
      return;
      localIntent = new Intent("/");
      localIntent.setComponent(new ComponentName("com.android.settings", "com.android.settings.WirelessSettings"));
      localIntent.setAction("android.intent.action.VIEW");
    }*/
  }

  public static boolean readFlagsBit(byte paramByte, int paramInt)
  {
    if (paramInt > 7)
      throw new IllegalAccessError("readFlagsBit error index>7!!! ");
    return (0x1 & paramByte << 7 - paramInt >> 7) == 1;
  }

  public static byte setByteBit(int paramInt, byte paramByte)
  {
    /*if (paramInt > 7)
      throw new IllegalAccessError("setByteBit error index>7!!! ");
    int i = paramByte;
    if (paramInt == 0)
      i = (byte)(i | 0x1);
    do
    {
      return i;
      if (paramInt == 1)
        return (byte)(i | 0x2);
      if (paramInt == 2)
        return (byte)(i | 0x4);
      if (paramInt == 3)
        return (byte)(i | 0x8);
      if (paramInt == 4)
        return (byte)(i | 0x10);
      if (paramInt == 5)
        return (byte)(i | 0x20);
      if (paramInt == 6)
        return (byte)(i | 0x40);
    }
    while (paramInt != 7);
    return (byte)(i | 0x80);*/
    return -1;
  }

  public static void shortTips(String paramString)
  {
  }

  public static byte[] shortToByteArray(short paramShort)
  {
    byte[] arrayOfByte = new byte[2];
    for (int i = 0; i < 2; i++)
      arrayOfByte[i] = (byte)(0xFF & paramShort >>> 8 * (-1 + arrayOfByte.length - i));
    return arrayOfByte;
  }

  public static byte[] subBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    System.arraycopy(paramArrayOfByte, paramInt1, arrayOfByte, 0, paramInt2);
    return arrayOfByte;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.util.XlinkUtils
 * JD-Core Version:    0.6.0
 */