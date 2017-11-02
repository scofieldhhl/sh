package com.fragmentmaster.animator;

import android.view.View;
import com.nineoldandroids.view.ViewHelper;

public class DefaultPageAnimator extends PageAnimator
{
  public static final DefaultPageAnimator INSTANCE = new DefaultPageAnimator();
  private static final float MIN_ALPHA = 0.5F;
  private static final float MIN_SCALE = 0.85F;

  protected void transformBackgroundPage(View paramView, float paramFloat, boolean paramBoolean)
  {
    if (paramFloat == -1.0F);
    for (int i = 4; ; i = 0)
    {
      paramView.setVisibility(i);
      ViewHelper.setTranslationX(paramView, paramView.getWidth() * -paramFloat);
      ViewHelper.setAlpha(paramView, 0.5F + 0.5F * (1.0F + paramFloat));
      float f = 0.85F + 0.15F * (1.0F + paramFloat);
      ViewHelper.setScaleX(paramView, f);
      ViewHelper.setScaleY(paramView, f);
      return;
    }
  }

  protected void transformForegroundPage(View paramView, float paramFloat, boolean paramBoolean)
  {
    ViewHelper.setTranslationX(paramView, 0.0F);
    ViewHelper.setAlpha(paramView, 1.0F);
    ViewHelper.setScaleX(paramView, 1.0F);
    ViewHelper.setScaleY(paramView, 1.0F);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.animator.DefaultPageAnimator
 * JD-Core Version:    0.6.0
 */