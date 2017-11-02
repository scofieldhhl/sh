package com.ex.ltech.hongwai.view.pickerview.lib;

import android.os.Handler;
import java.util.TimerTask;

final class InertiaTimerTask extends TimerTask
{
  float a;
  final WheelView loopView;
  final float velocityY;

  InertiaTimerTask(WheelView paramWheelView, float paramFloat)
  {
    this.loopView = paramWheelView;
    this.velocityY = paramFloat;
    this.a = 2.147484E+009F;
  }

  public final void run()
  {
    if (this.a == 2.147484E+009F)
    {
      if (Math.abs(this.velocityY) <= 2000.0F)
        break label94;
      if (this.velocityY <= 0.0F)
        break label85;
      this.a = 2000.0F;
    }
    while ((Math.abs(this.a) >= 0.0F) && (Math.abs(this.a) <= 20.0F))
    {
      this.loopView.cancelFuture();
      this.loopView.handler.sendEmptyMessage(2000);
      return;
      label85: this.a = -2000.0F;
      continue;
      label94: this.a = this.velocityY;
    }
    int i = (int)(10.0F * this.a / 1000.0F);
    this.loopView.totalScrollY -= i;
    float f1;
    float f3;
    if (!this.loopView.isLoop)
    {
      f1 = this.loopView.itemHeight;
      float f2 = f1 * -this.loopView.initPosition;
      f3 = f1 * (-1 + this.loopView.getItemsCount() - this.loopView.initPosition);
      if (this.loopView.totalScrollY - 0.3D * f1 < f2)
      {
        f2 = i + this.loopView.totalScrollY;
        if (this.loopView.totalScrollY > f2)
          break label322;
        this.a = 40.0F;
        this.loopView.totalScrollY = (int)f2;
      }
    }
    else
    {
      label250: if (this.a >= 0.0F)
        break label355;
      this.a = (20.0F + this.a);
    }
    while (true)
    {
      this.loopView.handler.sendEmptyMessage(1000);
      return;
      if (this.loopView.totalScrollY + 0.3D * f1 <= f3)
        break;
      f3 = i + this.loopView.totalScrollY;
      break;
      label322: if (this.loopView.totalScrollY < f3)
        break label250;
      this.loopView.totalScrollY = (int)f3;
      this.a = -40.0F;
      break label250;
      label355: this.a -= 20.0F;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.InertiaTimerTask
 * JD-Core Version:    0.6.0
 */