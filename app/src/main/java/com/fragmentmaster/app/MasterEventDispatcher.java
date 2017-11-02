package com.fragmentmaster.app;

import android.app.Activity;
import android.view.KeyEvent;
import android.view.MotionEvent;

class MasterEventDispatcher
  implements EventDispatcher
{
  private Activity mActivity;
  private ActivityEventDispatcher mActivityEventDispatcher;
  private EventDispatcher mEventInterceptor;
  private WindowEventDispatcher mWindowEventDispatcher;

  MasterEventDispatcher(Activity paramActivity)
  {
    this.mActivity = paramActivity;
    this.mWindowEventDispatcher = new WindowEventDispatcher(paramActivity);
    this.mActivityEventDispatcher = new ActivityEventDispatcher(paramActivity);
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    this.mActivity.onUserInteraction();
    return (this.mWindowEventDispatcher.dispatchGenericMotionEvent(paramMotionEvent)) || ((hasInterceptor()) && (this.mEventInterceptor.dispatchGenericMotionEvent(paramMotionEvent))) || (this.mActivityEventDispatcher.dispatchGenericMotionEvent(paramMotionEvent));
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    this.mActivity.onUserInteraction();
    return (this.mWindowEventDispatcher.dispatchKeyEvent(paramKeyEvent)) || ((hasInterceptor()) && (this.mEventInterceptor.dispatchKeyEvent(paramKeyEvent))) || (this.mActivityEventDispatcher.dispatchKeyEvent(paramKeyEvent));
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    this.mActivity.onUserInteraction();
    return (this.mWindowEventDispatcher.dispatchKeyShortcutEvent(paramKeyEvent)) || ((hasInterceptor()) && (this.mEventInterceptor.dispatchKeyShortcutEvent(paramKeyEvent))) || (this.mActivityEventDispatcher.dispatchKeyShortcutEvent(paramKeyEvent));
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if (paramMotionEvent.getAction() == 0)
      this.mActivity.onUserInteraction();
    return (this.mWindowEventDispatcher.dispatchTouchEvent(paramMotionEvent)) || ((hasInterceptor()) && (this.mEventInterceptor.dispatchTouchEvent(paramMotionEvent))) || (this.mActivityEventDispatcher.dispatchTouchEvent(paramMotionEvent));
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    this.mActivity.onUserInteraction();
    return (this.mWindowEventDispatcher.dispatchTrackballEvent(paramMotionEvent)) || ((hasInterceptor()) && (this.mEventInterceptor.dispatchTrackballEvent(paramMotionEvent))) || (this.mActivityEventDispatcher.dispatchTrackballEvent(paramMotionEvent));
  }

  public EventDispatcher getInterceptor()
  {
    return this.mEventInterceptor;
  }

  public boolean hasInterceptor()
  {
    return this.mEventInterceptor != null;
  }

  public void setInterceptor(EventDispatcher paramEventDispatcher)
  {
    this.mEventInterceptor = paramEventDispatcher;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterEventDispatcher
 * JD-Core Version:    0.6.0
 */