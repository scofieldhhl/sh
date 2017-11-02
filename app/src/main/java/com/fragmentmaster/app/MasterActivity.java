package com.fragmentmaster.app;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

public abstract class MasterActivity extends FragmentActivity
  implements IMasterActivity
{
  private final MasterActivityDelegate mImpl = new MasterActivityDelegate(this);

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(@NonNull KeyEvent paramKeyEvent)
  {
    return this.mImpl.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyShortcutEvent(@NonNull KeyEvent paramKeyEvent)
  {
    return this.mImpl.dispatchKeyShortcutEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(@NonNull MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchTrackballEvent(paramMotionEvent);
  }

  public FragmentMaster getFragmentMaster()
  {
    return this.mImpl.getFragmentMaster();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mImpl.onCreate(paramBundle);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mImpl.onSaveInstanceState(paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterActivity
 * JD-Core Version:    0.6.0
 */