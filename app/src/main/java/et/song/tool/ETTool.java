package et.song.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import org.apache.http.conn.util.InetAddressUtils;

@SuppressLint({"DefaultLocale"})
public final class ETTool
{
  public static String BytesToHexString(byte[] paramArrayOfByte)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuilder.toString();
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
    }
  }

  public static String BytesToHexStringEx(byte[] paramArrayOfByte, String paramString, int paramInt)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length <= 0))
      return null;
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfByte.length)
        return localStringBuilder.toString();
      String str = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str.length() < 2)
        localStringBuilder.append(0);
      localStringBuilder.append(str);
      localStringBuilder.append(paramString);
      if (i % paramInt != 0)
        continue;
      localStringBuilder.append("\r\n");
    }
  }

  public static double Dice(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2)
  {
    int i = 0;
    int j;
    if (paramArrayOfByte1.length > paramArrayOfByte2.length)
      j = paramArrayOfByte2.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
      {
        return i / j;
        j = paramArrayOfByte1.length;
        break;
      }
      int m = (int)(0.2F * paramArrayOfByte1[k]);
      int n = paramArrayOfByte1[k] - m;
      int i1 = m + paramArrayOfByte1[k];
      if ((n > paramArrayOfByte2[k]) || (i1 < paramArrayOfByte2[k]))
        continue;
      i++;
    }
  }

  public static int ETHeight(View paramView)
  {
    paramView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    return paramView.getMeasuredHeight();
  }

  public static int ETWidth(View paramView)
  {
    paramView.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
    return paramView.getMeasuredWidth();
  }

  public static byte[] HexStringToBytes(String paramString)
    throws Exception
  {
    byte[] arrayOfByte;
    if ((paramString == null) || (paramString.equals("")))
      arrayOfByte = null;
    while (true)
    {
      return arrayOfByte;
      String str = paramString.toUpperCase(Locale.getDefault());
      int i = str.length() / 2;
      char[] arrayOfChar = str.toCharArray();
      arrayOfByte = new byte[i];
      for (int j = 0; j < i; j++)
      {
        int k = j * 2;
        arrayOfByte[j] = (byte)(charToByte(arrayOfChar[k]) << 4 | charToByte(arrayOfChar[(k + 1)]));
      }
    }
  }

  public static String MD5(String paramString)
  {
    while (true)
    {
      char[] arrayOfChar;
      byte[] arrayOfByte1;
      int i;
      byte[] arrayOfByte2;
      StringBuffer localStringBuffer;
      int j;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        arrayOfChar = paramString.toCharArray();
        arrayOfByte1 = new byte[arrayOfChar.length];
        i = 0;
        if (i >= arrayOfChar.length)
        {
          arrayOfByte2 = localMessageDigest.digest(arrayOfByte1);
          localStringBuffer = new StringBuffer();
          j = 0;
          if (j < arrayOfByte2.length)
            break label85;
          return localStringBuffer.toString();
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return "";
      }
      arrayOfByte1[i] = (byte)arrayOfChar[i];
      i++;
      continue;
      label85: int k = 0xFF & arrayOfByte2[j];
      if (k < 16)
        localStringBuffer.append("0");
      localStringBuffer.append(Integer.toHexString(k));
      j++;
    }
  }

  public static void MessageBox(Activity paramActivity, float paramFloat, String paramString, boolean paramBoolean)
  {
    AlertDialog localAlertDialog = new Builder(paramActivity).setMessage(paramString).create();
    Window localWindow = localAlertDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.alpha = paramFloat;
    localWindow.setAttributes(localLayoutParams);
    if (paramBoolean)
      localAlertDialog.setCanceledOnTouchOutside(true);
    while (true)
    {
      localAlertDialog.show();
      return;
      localAlertDialog.setCancelable(false);
    }
  }

  public static long T()
  {
    return SystemClock.elapsedRealtime();
  }

  public static void Vibrate(Activity paramActivity, long paramLong)
  {
    ((Vibrator)paramActivity.getSystemService("vibrator")).vibrate(paramLong);
  }

  public static void Vibrate(Activity paramActivity, long[] paramArrayOfLong, boolean paramBoolean)
  {
    Vibrator localVibrator = (Vibrator)paramActivity.getSystemService("vibrator");
    if (paramBoolean);
    for (int i = 1; ; i = -1)
    {
      localVibrator.vibrate(paramArrayOfLong, i);
      return;
    }
  }

  public static String byte2HexStr(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder("");
    int i = 0;
    if (i >= paramArrayOfByte.length)
      return localStringBuilder.toString().toUpperCase().trim();
    String str1 = Integer.toHexString(0xFF & paramArrayOfByte[i]);
    if (str1.length() == 1);
    for (String str2 = "0" + str1; ; str2 = str1)
    {
      localStringBuilder.append(str2);
      localStringBuilder.append(" ");
      i++;
      break;
    }
  }

  public static int bytesToInt(byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4)
  {
    return paramByte1 & 0xFF | 0xFF00 & paramByte2 << 8 | 0xFF0000 & paramByte3 << 16 | 0xFF000000 & paramByte4 << 24;
  }

  public static int bytesToInt(byte[] paramArrayOfByte)
  {
    return 0xFF & paramArrayOfByte[0] | 0xFF00 & paramArrayOfByte[1] << 8 | 0xFF0000 & paramArrayOfByte[2] << 16 | 0xFF000000 & paramArrayOfByte[3] << 24;
  }

  private static byte charToByte(char paramChar)
  {
    return (byte)"0123456789ABCDEF".indexOf(paramChar);
  }

  public static boolean checkNetworkInfo(Activity paramActivity)
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)paramActivity.getSystemService("connectivity");
    State localState1 = localConnectivityManager.getNetworkInfo(0).getState();
    State localState2 = localConnectivityManager.getNetworkInfo(1).getState();
    int i;
    if ((localState1 == State.CONNECTED) || (localState1 == State.CONNECTING))
      i = 1;
    State localState3;
    do
    {
      return i;
      if (localState2 == State.CONNECTED)
        break;
      localState3 = State.CONNECTING;
      i = 0;
    }
    while (localState2 != localState3);
    return true;
  }

  public static boolean checkWifiInfo(Activity paramActivity)
  {
    State localState = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getNetworkInfo(1).getState();
    return (localState == State.CONNECTED) || (localState == State.CONNECTING);
  }

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static String getHour()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(localDate).substring(11, 13);
  }

  public static String getLocalIpAddress(boolean paramBoolean)
  {
    try
    {
      while (true)
      {
        Enumeration localEnumeration1 = NetworkInterface.getNetworkInterfaces();
        while (true)
          if (localEnumeration1.hasMoreElements())
          {
            NetworkInterface localNetworkInterface = (NetworkInterface)localEnumeration1.nextElement();
            Enumeration localEnumeration2 = localNetworkInterface.getInetAddresses();
            if (!localEnumeration2.hasMoreElements())
              continue;
            InetAddress localInetAddress = (InetAddress)localEnumeration2.nextElement();
            if (paramBoolean)
            {
              if ((localInetAddress.isLoopbackAddress()) || (!localNetworkInterface.getName().equals("wlan0")) || (!InetAddressUtils.isIPv4Address(localInetAddress.getHostAddress())))
                break;
              return localInetAddress.getHostAddress().toString();
            }
            if (localInetAddress.isLoopbackAddress())
              break;
            String str = localInetAddress.getHostAddress().toString();
            return str;
          }
      }
    }
    catch (SocketException localSocketException)
    {
      localSocketException.printStackTrace();
    }
    return null;
  }

  public static String getMin()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(localDate).substring(14, 16);
  }

  public static float getRawSize(Context paramContext, int paramInt, float paramFloat)
  {
    if (paramContext == null);
    for (Resources localResources = Resources.getSystem(); ; localResources = paramContext.getResources())
      return TypedValue.applyDimension(paramInt, paramFloat, localResources.getDisplayMetrics());
  }

  public static String hexStr2Str(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte.length)
        return new String(arrayOfByte);
      arrayOfByte[i] = (byte)(0xFF & 16 * "0123456789ABCDEF".indexOf(arrayOfChar[(i * 2)]) + "0123456789ABCDEF".indexOf(arrayOfChar[(1 + i * 2)]));
    }
  }

  public static byte[] intToByte(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = (byte)(paramInt & 0xFF);
    arrayOfByte[1] = (byte)((0xFF00 & paramInt) >> 8);
    arrayOfByte[2] = (byte)((0xFF0000 & paramInt) >> 16);
    arrayOfByte[3] = (byte)((0xFF000000 & paramInt) >> 24);
    return arrayOfByte;
  }

  public static boolean isInternet(Activity paramActivity)
  {
    int i = 1;
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramActivity.getSystemService("connectivity")).getActiveNetworkInfo();
    if ((localNetworkInfo == null) || (!localNetworkInfo.isConnected()))
      i = 0;
    do
      return i;
    while (!localNetworkInfo.isRoaming());
    return i;
  }

  public static int px2dip(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat / paramContext.getResources().getDisplayMetrics().density);
  }

  public static Bitmap rotateBitmap(Bitmap paramBitmap, int paramInt)
  {
    Bitmap localBitmap;
    if ((paramInt == 0) || (paramBitmap == null))
      localBitmap = paramBitmap;
    do
    {
      return localBitmap;
      Matrix localMatrix = new Matrix();
      localMatrix.setRotate(paramInt, paramBitmap.getWidth() / 2, paramBitmap.getHeight() / 2);
      localBitmap = Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true);
    }
    while (paramBitmap == null);
    paramBitmap.recycle();
    return localBitmap;
  }

  public static String str2HexStr(String paramString)
  {
    char[] arrayOfChar = "0123456789ABCDEF".toCharArray();
    StringBuilder localStringBuilder = new StringBuilder("");
    byte[] arrayOfByte = paramString.getBytes();
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfByte.length)
        return localStringBuilder.toString().trim();
      localStringBuilder.append(arrayOfChar[((0xF0 & arrayOfByte[i]) >> 4)]);
      localStringBuilder.append(arrayOfChar[(0xF & arrayOfByte[i])]);
      localStringBuilder.append(' ');
    }
  }

  public static String strToUnicode(String paramString)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i >= paramString.length())
      return localStringBuilder.toString();
    int j = paramString.charAt(i);
    String str = Integer.toHexString(j);
    if (j > 128)
      localStringBuilder.append("\\u" + str);
    while (true)
    {
      i++;
      break;
      localStringBuilder.append("\\u00" + str);
    }
  }

  public static String toIP(int paramInt)
  {
    return (paramInt & 0xFF) + "." + (0xFF & paramInt >> 8) + "." + (0xFF & paramInt >> 16) + "." + (0xFF & paramInt >> 24);
  }

  public static String unicodeToString(String paramString)
  {
    int i = paramString.length() / 6;
    StringBuilder localStringBuilder = new StringBuilder();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuilder.toString();
      String str1 = paramString.substring(j * 6, 6 * (j + 1));
      String str2 = str1.substring(2, 4) + "00";
      String str3 = str1.substring(4);
      localStringBuilder.append(new String(Character.toChars(Integer.valueOf(str2, 16).intValue() + Integer.valueOf(str3, 16).intValue())));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETTool
 * JD-Core Version:    0.6.0
 */