package com.ex.ltech.hongwai.view.pickerview.lib;

import com.ex.ltech.hongwai.view.pickerview.listener.OnItemSelectedListener;

final class OnItemSelectedRunnable
  implements Runnable
{
  final WheelView loopView;

  OnItemSelectedRunnable(WheelView paramWheelView)
  {
    this.loopView = paramWheelView;
  }

  public final void run()
  {
    this.loopView.onItemSelectedListener.onItemSelected(this.loopView.getCurrentItem());
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.OnItemSelectedRunnable
 * JD-Core Version:    0.6.0
 */