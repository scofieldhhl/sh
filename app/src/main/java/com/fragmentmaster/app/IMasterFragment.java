package com.fragmentmaster.app;

import android.view.KeyEvent;
import android.view.KeyEvent.Callback;
import android.view.MotionEvent;
import com.fragmentmaster.animator.PageAnimator;

public abstract interface IMasterFragment extends IFragmentWrapper, EventDispatcher, Callback
{
  public static final int RESULT_CANCELED = 0;
  public static final int RESULT_OK = -1;

  public abstract void finish();

  public abstract FragmentMaster getFragmentMaster();

  public abstract Request getRequest();

  public abstract int getSoftInputMode();

  public abstract IMasterFragment getTargetChildFragment();

  public abstract boolean hasStateSaved();

  public abstract boolean isActive();

  public abstract boolean isFinishing();

  public abstract boolean isPrimary();

  public abstract boolean isSlideable();

  public abstract void onActivate();

  public abstract void onBackPressed();

  public abstract PageAnimator onCreatePageAnimator();

  public abstract void onDeactivate();

  public abstract void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest);

  public abstract boolean onGenericMotionEvent(MotionEvent paramMotionEvent);

  public abstract boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent);

  public abstract boolean onTouchEvent(MotionEvent paramMotionEvent);

  public abstract boolean onTrackballEvent(MotionEvent paramMotionEvent);

  public abstract void setPrimary(boolean paramBoolean);

  public abstract void setRequest(Request paramRequest);

  public abstract void setResult(int paramInt);

  public abstract void setResult(int paramInt, Request paramRequest);

  public abstract void setSlideable(boolean paramBoolean);

  public abstract void setSoftInputMode(int paramInt);

  public abstract void setTargetChildFragment(IMasterFragment paramIMasterFragment);

  public abstract void startFragment(Request paramRequest);

  public abstract void startFragment(Class<? extends IMasterFragment> paramClass);

  public abstract void startFragmentForResult(Request paramRequest, int paramInt);

  public abstract void startFragmentForResult(Class<? extends IMasterFragment> paramClass, int paramInt);

  public abstract void startFragmentFromChild(IMasterFragment paramIMasterFragment, Request paramRequest, int paramInt);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.IMasterFragment
 * JD-Core Version:    0.6.0
 */