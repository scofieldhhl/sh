package com.fragmentmaster.app;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;

class WindowEventDispatcher
  implements EventDispatcher
{
  private Activity mActivity;

  WindowEventDispatcher(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.getWindow().superDispatchGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return this.mActivity.getWindow().superDispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mActivity.getWindow().superDispatchKeyShortcutEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.getWindow().superDispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.getWindow().superDispatchTrackballEvent(paramMotionEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.WindowEventDispatcher
 * JD-Core Version:    0.6.0
 */