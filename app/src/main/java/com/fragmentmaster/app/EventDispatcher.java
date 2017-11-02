package com.fragmentmaster.app;

import android.view.KeyEvent;
import android.view.MotionEvent;

abstract interface EventDispatcher
{
  public abstract boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent);

  public abstract boolean dispatchKeyEvent(KeyEvent paramKeyEvent);

  public abstract boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent);

  public abstract boolean dispatchTouchEvent(MotionEvent paramMotionEvent);

  public abstract boolean dispatchTrackballEvent(MotionEvent paramMotionEvent);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.EventDispatcher
 * JD-Core Version:    0.6.0
 */