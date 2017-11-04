package io.xlink.wifi.js.util;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.ex.ltech.led.MyApp;

public class SharedPreferencesUtil
{
  public static boolean deleteAllValue()
  {
    return MyApp.sharedPreferences.edit().clear().commit();
  }

  public static void deleteValue(String paramString)
  {
    MyApp.sharedPreferences.edit().remove(paramString).commit();
  }

  public static void keepShared(String paramString, int paramInt)
  {
    Editor localEditor = MyApp.sharedPreferences.edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public static void keepShared(String paramString, long paramLong)
  {
    Editor localEditor = MyApp.sharedPreferences.edit();
    localEditor.putLong(paramString, paramLong);
    localEditor.commit();
  }

  public static void keepShared(String paramString, Integer paramInteger)
  {
    Editor localEditor = MyApp.sharedPreferences.edit();
    localEditor.putInt(paramString, paramInteger.intValue());
    localEditor.commit();
  }

  public static void keepShared(String paramString1, String paramString2)
  {
    Editor localEditor = MyApp.sharedPreferences.edit();
    localEditor.putString(paramString1, paramString2);
    localEditor.commit();
  }

  public static void keepShared(String paramString, boolean paramBoolean)
  {
    Editor localEditor = MyApp.sharedPreferences.edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public static boolean queryBooleanValue(String paramString)
  {
    return MyApp.sharedPreferences.getBoolean(paramString, false);
  }

  public static Integer queryIntValue(String paramString)
  {
    return Integer.valueOf(MyApp.sharedPreferences.getInt(paramString, 0));
  }

  public static long queryLongValue(String paramString)
  {
    return MyApp.sharedPreferences.getLong(paramString, 0L);
  }

  public static String queryValue(String paramString)
  {
    String str = MyApp.sharedPreferences.getString(paramString, "");
    if ("".equals(str))
      str = "";
    return str;
  }

  public static String queryValue(String paramString1, String paramString2)
  {
    return MyApp.sharedPreferences.getString(paramString1, paramString2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.util.SharedPreferencesUtil
 * JD-Core Version:    0.6.0
 */