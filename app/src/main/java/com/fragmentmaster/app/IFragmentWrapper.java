package com.fragmentmaster.app;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;

abstract interface IFragmentWrapper
{
  public abstract Activity getActivity();

  public abstract FragmentManager getChildFragmentManager();

  public abstract Fragment getFragment();

  public abstract Fragment getParentFragment();

  public abstract Fragment getTargetFragment();

  public abstract int getTargetRequestCode();

  public abstract View getView();

  public abstract boolean isResumed();

  public abstract void setMenuVisibility(boolean paramBoolean);

  public abstract void setTargetFragment(Fragment paramFragment, int paramInt);

  public abstract void setUserVisibleHint(boolean paramBoolean);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.IFragmentWrapper
 * JD-Core Version:    0.6.0
 */