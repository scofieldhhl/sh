package com.ex.ltech.hongwai;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;

public class ScreenUtil
{
  public static void fullScreen(Activity paramActivity)
  {
    paramActivity.getWindow().addFlags(67108864);
    ViewGroup localViewGroup = (ViewGroup)paramActivity.findViewById(16908290);
    View localView = localViewGroup.getChildAt(0);
    if ((localView != null) && (localView.getLayoutParams() != null) && (localView.getLayoutParams().height == getStatusBarHeight(paramActivity)))
      localViewGroup.removeView(localView);
    if (localViewGroup.getChildAt(0) != null)
      ViewCompat.setFitsSystemWindows(localViewGroup.getChildAt(0), false);
  }

  public static int getStatusBarHeight(Context paramContext)
  {
    int i = paramContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
    int j = 0;
    if (i > 0)
      j = paramContext.getResources().getDimensionPixelSize(i);
    return j;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.ScreenUtil
 * JD-Core Version:    0.6.0
 */