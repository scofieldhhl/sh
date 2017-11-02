package com.fragmentmaster.app;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import com.fragmentmaster.animator.PageAnimator;

public class MasterFragment extends Fragment
  implements IMasterFragment
{
  private MasterFragmentDelegate mImpl = new MasterFragmentDelegate(this);

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return this.mImpl.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mImpl.dispatchKeyShortcutEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.dispatchTrackballEvent(paramMotionEvent);
  }

  public void finish()
  {
    this.mImpl.finish();
  }

  public Fragment getFragment()
  {
    return this;
  }

  public FragmentMaster getFragmentMaster()
  {
    return this.mImpl.getFragmentMaster();
  }

  public LayoutInflater getLayoutInflater(Bundle paramBundle)
  {
    return this.mImpl.getLayoutInflater();
  }

  public Request getRequest()
  {
    return this.mImpl.getRequest();
  }

  public int getSoftInputMode()
  {
    return this.mImpl.getSoftInputMode();
  }

  public IMasterFragment getTargetChildFragment()
  {
    return this.mImpl.getTargetChildFragment();
  }

  public boolean hasStateSaved()
  {
    return this.mImpl.hasStateSaved();
  }

  public boolean isActive()
  {
    return this.mImpl.isUserActive();
  }

  public boolean isFinishing()
  {
    return this.mImpl.isFinishing();
  }

  public boolean isPrimary()
  {
    return this.mImpl.isPrimary();
  }

  public boolean isSlideable()
  {
    return this.mImpl.isSlideable();
  }

  public void onActivate()
  {
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.mImpl.onActivityCreated(paramBundle);
  }

  public void onAttach(Activity paramActivity)
  {
    super.onAttach(paramActivity);
    this.mImpl.onAttach(paramActivity);
  }

  public void onBackPressed()
  {
    this.mImpl.onBackPressed();
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.mImpl.onCreate(paramBundle);
  }

  public PageAnimator onCreatePageAnimator()
  {
    return this.mImpl.onCreatePageAnimator();
  }

  public void onDeactivate()
  {
  }

  public void onDestroy()
  {
    super.onDestroy();
    this.mImpl.onDestroy();
  }

  public void onDetach()
  {
    super.onDetach();
    this.mImpl.onDetach();
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
  }

  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.onGenericMotionEvent(paramMotionEvent);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mImpl.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mImpl.onKeyLongPress(paramInt, paramKeyEvent);
  }

  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    return this.mImpl.onKeyMultiple(paramInt1, paramInt2, paramKeyEvent);
  }

  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mImpl.onKeyShortcut(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    return this.mImpl.onKeyUp(paramInt, paramKeyEvent);
  }

  public void onPause()
  {
    super.onPause();
    this.mImpl.onPause();
  }

  public void onResume()
  {
    super.onResume();
    this.mImpl.onResume();
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    this.mImpl.onSaveInstanceState(paramBundle);
  }

  public void onStart()
  {
    super.onStart();
    this.mImpl.onStart();
  }

  public void onStop()
  {
    super.onStop();
    this.mImpl.onStop();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.onTouchEvent(paramMotionEvent);
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mImpl.onTrackballEvent(paramMotionEvent);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.mImpl.onViewCreated(paramView, paramBundle);
  }

  public void setPrimary(boolean paramBoolean)
  {
    this.mImpl.setPrimary(paramBoolean);
  }

  public void setRequest(Request paramRequest)
  {
    this.mImpl.setRequest(paramRequest);
  }

  public final void setResult(int paramInt)
  {
    this.mImpl.setResult(paramInt);
  }

  public final void setResult(int paramInt, Request paramRequest)
  {
    this.mImpl.setResult(paramInt, paramRequest);
  }

  public void setSlideable(boolean paramBoolean)
  {
    this.mImpl.setSlideable(paramBoolean);
  }

  public void setSoftInputMode(int paramInt)
  {
    this.mImpl.setSoftInputMode(paramInt);
  }

  public void setTargetChildFragment(IMasterFragment paramIMasterFragment)
  {
    this.mImpl.setTargetChildFragment(paramIMasterFragment);
  }

  public void startFragment(Request paramRequest)
  {
    this.mImpl.startFragment(paramRequest);
  }

  public void startFragment(Class<? extends IMasterFragment> paramClass)
  {
    this.mImpl.startFragment(paramClass);
  }

  public void startFragmentForResult(Request paramRequest, int paramInt)
  {
    this.mImpl.startFragmentForResult(paramRequest, paramInt);
  }

  public void startFragmentForResult(Class<? extends IMasterFragment> paramClass, int paramInt)
  {
    this.mImpl.startFragmentForResult(paramClass, paramInt);
  }

  public void startFragmentFromChild(IMasterFragment paramIMasterFragment, Request paramRequest, int paramInt)
  {
    this.mImpl.startFragmentFromChild(paramIMasterFragment, paramRequest, paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterFragment
 * JD-Core Version:    0.6.0
 */