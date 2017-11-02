package com.ex.ltech.led.acti.guide;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import android.preference.PreferenceManager;

public class PrefConstants
{
  public static int getAppPrefInt(Context paramContext, String paramString)
  {
    int i = -1;
    if (paramContext != null)
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
      if (localSharedPreferences != null)
        i = localSharedPreferences.getInt(paramString, -1);
    }
    return i;
  }

  public static void putAppPrefInt(Context paramContext, String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor;
    if (paramContext != null)
    {
      localEditor = PreferenceManager.getDefaultSharedPreferences(paramContext).edit();
      localEditor.putInt(paramString, paramInt);
      if (Build.VERSION.SDK_INT >= 9)
        localEditor.apply();
    }
    else
    {
      return;
    }
    localEditor.commit();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.guide.PrefConstants
 * JD-Core Version:    0.6.0
 */