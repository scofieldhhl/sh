package com.fragmentmaster.app;

import android.support.v4.view.KeyEventCompat2;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;

class FragmentEventDispatcher
  implements EventDispatcher
{
  private IMasterFragment mMasterFragment;

  FragmentEventDispatcher(IMasterFragment paramIMasterFragment)
  {
    this.mMasterFragment = paramIMasterFragment;
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mMasterFragment.onGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    View localView = this.mMasterFragment.getView();
    IMasterFragment localIMasterFragment = this.mMasterFragment;
    if (localView != null);
    for (Object localObject = KeyEventCompat2.getKeyDispatcherState(localView); ; localObject = null)
      return KeyEventCompat2.dispatch(paramKeyEvent, localIMasterFragment, localObject, this);
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mMasterFragment.onKeyShortcut(paramKeyEvent.getKeyCode(), paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mMasterFragment.onTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mMasterFragment.onTrackballEvent(paramMotionEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentEventDispatcher
 * JD-Core Version:    0.6.0
 */