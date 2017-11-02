package com.ex.ltech.hongwai.view.pickerview.lib;

import android.os.Handler;
import java.util.TimerTask;

final class SmoothScrollTimerTask extends TimerTask
{
  final WheelView loopView;
  int offset;
  int realOffset;
  int realTotalOffset;

  SmoothScrollTimerTask(WheelView paramWheelView, int paramInt)
  {
    this.loopView = paramWheelView;
    this.offset = paramInt;
    this.realTotalOffset = 2147483647;
    this.realOffset = 0;
  }

  public final void run()
  {
    if (this.realTotalOffset == 2147483647)
      this.realTotalOffset = this.offset;
    this.realOffset = (int)(0.1F * this.realTotalOffset);
    if (this.realOffset == 0)
      if (this.realTotalOffset >= 0)
        break label82;
    label82: for (this.realOffset = -1; Math.abs(this.realTotalOffset) <= 1; this.realOffset = 1)
    {
      this.loopView.cancelFuture();
      this.loopView.handler.sendEmptyMessage(3000);
      return;
    }
    this.loopView.totalScrollY += this.realOffset;
    if (!this.loopView.isLoop)
    {
      float f1 = this.loopView.itemHeight;
      float f2 = f1 * -this.loopView.initPosition;
      float f3 = f1 * (-1 + this.loopView.getItemsCount() - this.loopView.initPosition);
      if ((this.loopView.totalScrollY <= f2) || (this.loopView.totalScrollY >= f3))
      {
        this.loopView.totalScrollY -= this.realOffset;
        this.loopView.cancelFuture();
        this.loopView.handler.sendEmptyMessage(3000);
        return;
      }
    }
    this.loopView.handler.sendEmptyMessage(1000);
    this.realTotalOffset -= this.realOffset;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.SmoothScrollTimerTask
 * JD-Core Version:    0.6.0
 */