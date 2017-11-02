package com.fragmentmaster.app;

import android.app.Activity;
import android.support.v4.view.KeyEventCompat2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

class ActivityEventDispatcher
  implements EventDispatcher
{
  private Activity mActivity;

  ActivityEventDispatcher(Activity paramActivity)
  {
    this.mActivity = paramActivity;
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.onGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    View localView = this.mActivity.getWindow().getDecorView();
    Activity localActivity = this.mActivity;
    if (localView != null);
    for (Object localObject = KeyEventCompat2.getKeyDispatcherState(localView); ; localObject = null)
      return KeyEventCompat2.dispatch(paramKeyEvent, localActivity, localObject, this.mActivity);
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mActivity.onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.onTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mActivity.onTrackballEvent(paramMotionEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.ActivityEventDispatcher
 * JD-Core Version:    0.6.0
 */