package com.ex.ltech.led.acti.guide;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;

public class SAppUtil
{
  public static int getAppVersionCode(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (NameNotFoundException localNameNotFoundException)
    {
      localNameNotFoundException.printStackTrace();
    }
    return -1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.guide.SAppUtil
 * JD-Core Version:    0.6.0
 */