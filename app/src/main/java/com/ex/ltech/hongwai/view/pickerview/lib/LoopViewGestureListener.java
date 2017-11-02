package com.ex.ltech.hongwai.view.pickerview.lib;

import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;

final class LoopViewGestureListener extends GestureDetector.SimpleOnGestureListener
{
  final WheelView loopView;

  LoopViewGestureListener(WheelView paramWheelView)
  {
    this.loopView = paramWheelView;
  }

  public final boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    this.loopView.scrollBy(paramFloat2);
    return true;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.LoopViewGestureListener
 * JD-Core Version:    0.6.0
 */