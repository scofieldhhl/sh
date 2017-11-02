package com.ex.ltech.hongwai.view.pickerview.lib;

import android.os.Handler;
import android.os.Message;

final class MessageHandler extends Handler
{
  public static final int WHAT_INVALIDATE_LOOP_VIEW = 1000;
  public static final int WHAT_ITEM_SELECTED = 3000;
  public static final int WHAT_SMOOTH_SCROLL = 2000;
  final WheelView loopview;

  MessageHandler(WheelView paramWheelView)
  {
    this.loopview = paramWheelView;
  }

  public final void handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
      return;
    case 1000:
      this.loopview.invalidate();
      return;
    case 2000:
      this.loopview.smoothScroll(WheelView.ACTION.FLING);
      return;
    case 3000:
    }
    this.loopview.onItemSelected();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.lib.MessageHandler
 * JD-Core Version:    0.6.0
 */