package com.fragmentmaster.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;

class MasterActivityDelegate
{
  private static final String FRAGMENTS_TAG = "FragmentMaster:fragments";
  private FragmentMaster mFragmentMaster;

  public MasterActivityDelegate(FragmentActivity paramFragmentActivity)
  {
    this.mFragmentMaster = new FragmentMasterImpl(paramFragmentActivity);
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mFragmentMaster.dispatchGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(@NonNull KeyEvent paramKeyEvent)
  {
    return this.mFragmentMaster.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyShortcutEvent(@NonNull KeyEvent paramKeyEvent)
  {
    return this.mFragmentMaster.dispatchKeyShortcutEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(@NonNull MotionEvent paramMotionEvent)
  {
    return this.mFragmentMaster.dispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mFragmentMaster.dispatchTrackballEvent(paramMotionEvent);
  }

  public FragmentMaster getFragmentMaster()
  {
    return this.mFragmentMaster;
  }

  public void onCreate(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Parcelable localParcelable = paramBundle.getParcelable("FragmentMaster:fragments");
      this.mFragmentMaster.restoreAllState(localParcelable);
    }
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    Parcelable localParcelable = this.mFragmentMaster.saveAllState();
    if (localParcelable != null)
      paramBundle.putParcelable("FragmentMaster:fragments", localParcelable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterActivityDelegate
 * JD-Core Version:    0.6.0
 */