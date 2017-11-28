package com.ex.ltech.led.my_view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class NoScrollViewPager extends ViewPager
{
  private boolean noScroll = false;

  public NoScrollViewPager(Context paramContext)
  {
    super(paramContext);
  }

  public NoScrollViewPager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.noScroll)
      return false;
    return super.onInterceptTouchEvent(paramMotionEvent);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.noScroll)
      return false;
    return super.onTouchEvent(paramMotionEvent);
  }

  public void scrollTo(int paramInt1, int paramInt2)
  {
    super.scrollTo(paramInt1, paramInt2);
  }

  public void setCurrentItem(int paramInt)
  {
    super.setCurrentItem(paramInt);
  }

  public void setCurrentItem(int paramInt, boolean paramBoolean)
  {
    super.setCurrentItem(paramInt, paramBoolean);
  }

  public void setNoScroll(boolean paramBoolean)
  {
    this.noScroll = paramBoolean;
  }
}
