package com.ex.ltech.util;

import android.content.Context;
import android.widget.Toast;

public class TipsUtil
{
  public static void toast(Context paramContext, String paramString)
  {
    toast(paramContext, paramString, 0);
  }

  public static void toast(Context paramContext, String paramString, int paramInt)
  {
    Toast.makeText(paramContext, paramString, paramInt).show();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.util.TipsUtil
 * JD-Core Version:    0.6.0
 */