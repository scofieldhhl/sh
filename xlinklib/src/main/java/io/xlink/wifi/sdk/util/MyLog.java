package io.xlink.wifi.sdk.util;

import android.util.Log;
import io.xlink.wifi.sdk.listener.LogListener;

public class MyLog
{
  public static Boolean a = Boolean.valueOf(true);
  private static char b = 'v';
  private static LogListener c;

  private static void a(String paramString1, String paramString2, char paramChar)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (!a.booleanValue())
          continue;
        if (('e' != paramChar) || (('e' != b) && ('v' != b)))
          continue;
        Log.e(paramString1, paramString2);
        if (c == null)
          continue;
        c.onLog(paramChar, paramString1, paramString2 + "");
        return;
        if (('w' == paramChar) && (('w' == b) || ('v' == b)))
        {
          Log.w(paramString1, paramString2);
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if (('d' == paramChar) && (('d' == b) || ('v' == b)))
      {
        Log.d(paramString1, paramString2);
        continue;
      }
      if (('i' == paramChar) && (('d' == b) || ('v' == b)))
      {
        Log.i(paramString1, paramString2);
        continue;
      }
      Log.v(paramString1, paramString2);
    }
  }

  public static void d(String paramString, Object paramObject)
  {
    a(paramString, paramObject.toString(), 'd');
  }

  public static void d(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 'd');
  }

  public static void e(String paramString, Object paramObject)
  {
    a(paramString, paramObject.toString(), 'e');
  }

  public static void e(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 'e');
  }

  public static void i(String paramString, Object paramObject)
  {
    a(paramString, paramObject.toString(), 'i');
  }

  public static void i(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 'i');
  }

  public static void setListener(LogListener paramLogListener)
  {
    c = paramLogListener;
  }

  public static void v(String paramString, Object paramObject)
  {
    a(paramString, paramObject.toString(), 'v');
  }

  public static void v(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 'v');
  }

  public static void w(String paramString, Object paramObject)
  {
    a(paramString, paramObject.toString(), 'w');
  }

  public static void w(String paramString1, String paramString2)
  {
    a(paramString1, paramString2, 'w');
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.util.MyLog
 * JD-Core Version:    0.6.0
 */