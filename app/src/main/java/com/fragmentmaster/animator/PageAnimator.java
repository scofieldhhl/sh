package com.fragmentmaster.animator;

import android.view.View;

public abstract class PageAnimator
{
  protected abstract void transformBackgroundPage(View paramView, float paramFloat, boolean paramBoolean);

  protected abstract void transformForegroundPage(View paramView, float paramFloat, boolean paramBoolean);

  public void transformPage(View paramView, float paramFloat, boolean paramBoolean)
  {
    if (paramFloat <= 0.0F)
      transformBackgroundPage(paramView, paramFloat, paramBoolean);
    do
      return;
    while (paramFloat > 1.0F);
    transformForegroundPage(paramView, paramFloat, paramBoolean);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.animator.PageAnimator
 * JD-Core Version:    0.6.0
 */