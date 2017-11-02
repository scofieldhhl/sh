package com.fragmentmaster.app;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentManager;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import com.fragmentmaster.animator.DefaultPageAnimator;
import com.fragmentmaster.animator.PageAnimator;

class MasterFragmentDelegate
{
  private static final String BUNDLE_KEY_STATE = "FragmentMaster:MASTER_FRAGMENT_STATE";
  private static final String BUNDLE_KEY_TARGET_CHILD_FRAGMENT = "FragmentMaster:TARGET_CHILD_FRAGMENT";
  private static final int MSG_USER_ACTIVE = 1;
  private Activity mActivity;
  private FragmentEventDispatcher mEventDispatcher;
  private boolean mFinished = false;
  private FragmentContext mFragmentContext;
  private final Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        super.handleMessage(paramMessage);
        return;
      case 1:
      }
      MasterFragmentDelegate.this.performActivate();
    }
  };
  private boolean mIsPrimary = false;
  boolean mIsSlideable = true;
  private boolean mIsUserActive = false;
  private IMasterActivity mMasterActivity;
  IMasterFragment mMasterFragment;
  Request mRequest = null;
  private int mResultCode = 0;
  private Request mResultData = null;
  int mSoftInputMode = 0;
  private boolean mStateSaved = false;
  private IMasterFragment mTargetChildFragment;

  MasterFragmentDelegate(IMasterFragment paramIMasterFragment)
  {
    this.mMasterFragment = paramIMasterFragment;
    this.mEventDispatcher = new FragmentEventDispatcher(paramIMasterFragment);
  }

  private void checkState()
  {
    if (this.mMasterActivity == null)
      throw new IllegalStateException("Can not perform this action. Fragment " + this.mMasterFragment + " not attached to MasterActivity!");
  }

  private void onSetPrimary(boolean paramBoolean)
  {
    boolean bool = this.mIsPrimary;
    this.mIsPrimary = paramBoolean;
    if ((!bool) && (paramBoolean))
    {
      invalidateWindowConfiguration();
      invalidateMasterConfiguration();
      if (this.mMasterFragment.isResumed())
        performActivate();
    }
    do
      return;
    while ((!bool) || (paramBoolean) || (!this.mMasterFragment.isResumed()));
    performDeactivate();
  }

  private void performActivate()
  {
    this.mIsUserActive = true;
    this.mMasterFragment.onActivate();
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentActivated(this.mMasterFragment);
  }

  private void performDeactivate()
  {
    this.mIsUserActive = false;
    this.mMasterFragment.onDeactivate();
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentDeactivated(this.mMasterFragment);
  }

  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchGenericMotionEvent(paramMotionEvent);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return this.mEventDispatcher.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mEventDispatcher.dispatchKeyShortcutEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchTrackballEvent(paramMotionEvent);
  }

  public void finish()
  {
    checkState();
    monitorenter;
    try
    {
      int i = this.mResultCode;
      Request localRequest = this.mResultData;
      monitorexit;
      getFragmentMaster().finishFragment(this.mMasterFragment, i, localRequest);
      this.mFinished = true;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public FragmentContext getFragmentContext()
  {
    return this.mFragmentContext;
  }

  public FragmentMaster getFragmentMaster()
  {
    if (this.mMasterActivity == null)
      return null;
    return this.mMasterActivity.getFragmentMaster();
  }

  public LayoutInflater getLayoutInflater()
  {
    return (LayoutInflater)this.mFragmentContext.getSystemService("layout_inflater");
  }

  public Request getRequest()
  {
    return this.mRequest;
  }

  public int getSoftInputMode()
  {
    return this.mSoftInputMode;
  }

  public IMasterFragment getTargetChildFragment()
  {
    return this.mTargetChildFragment;
  }

  public boolean hasStateSaved()
  {
    return this.mStateSaved;
  }

  void invalidateMasterConfiguration()
  {
    checkState();
    getFragmentMaster().setSlideable(this.mIsSlideable);
  }

  void invalidateWindowConfiguration()
  {
    if (this.mMasterFragment.getActivity() != null)
      this.mMasterFragment.getActivity().getWindow().setSoftInputMode(this.mSoftInputMode);
  }

  public boolean isFinishing()
  {
    return this.mFinished;
  }

  public boolean isPrimary()
  {
    return this.mIsPrimary;
  }

  public boolean isSlideable()
  {
    return this.mIsSlideable;
  }

  public boolean isUserActive()
  {
    return this.mIsUserActive;
  }

  public void onActivityCreated(Bundle paramBundle)
  {
    this.mStateSaved = false;
    if (paramBundle != null)
      this.mTargetChildFragment = ((IMasterFragment)this.mMasterFragment.getChildFragmentManager().getFragment(paramBundle, "FragmentMaster:TARGET_CHILD_FRAGMENT"));
  }

  public void onAttach(Activity paramActivity)
  {
    if ((paramActivity instanceof IMasterActivity))
      this.mMasterActivity = ((IMasterActivity)paramActivity);
    this.mActivity = paramActivity;
    this.mFragmentContext = new FragmentContext(this.mMasterFragment);
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentAttached(this.mMasterFragment);
  }

  public void onBackPressed()
  {
    this.mMasterFragment.finish();
  }

  public void onCreate(Bundle paramBundle)
  {
    this.mStateSaved = false;
    if (paramBundle != null)
      ((MasterFragmentState)paramBundle.getParcelable("FragmentMaster:MASTER_FRAGMENT_STATE")).restore(this);
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentCreated(this.mMasterFragment, paramBundle);
  }

  public PageAnimator onCreatePageAnimator()
  {
    return DefaultPageAnimator.INSTANCE;
  }

  public void onDestroy()
  {
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentDestroyed(this.mMasterFragment);
  }

  public void onDetach()
  {
    this.mMasterActivity = null;
    this.mActivity = null;
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentDetached(this.mMasterFragment);
  }

  public boolean onGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (Build.VERSION.SDK_INT < 5)
    {
      if ((paramInt == 4) && (paramKeyEvent.getRepeatCount() == 0))
      {
        this.mMasterFragment.onBackPressed();
        return true;
      }
    }
    else
      paramKeyEvent.startTracking();
    return false;
  }

  public boolean onKeyLongPress(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyMultiple(int paramInt1, int paramInt2, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyShortcut(int paramInt, KeyEvent paramKeyEvent)
  {
    return false;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if ((Build.VERSION.SDK_INT >= 5) && (paramInt == 4) && (paramKeyEvent.isTracking()) && (!paramKeyEvent.isCanceled()))
    {
      this.mMasterFragment.onBackPressed();
      return true;
    }
    return false;
  }

  public void onPause()
  {
    if (this.mHandler.hasMessages(1))
    {
      this.mHandler.removeMessages(1);
      performActivate();
    }
    if (isPrimary())
      performDeactivate();
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentPaused(this.mMasterFragment);
  }

  public void onResume()
  {
    this.mStateSaved = false;
    if (isPrimary())
      this.mHandler.sendEmptyMessage(1);
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentResumed(this.mMasterFragment);
  }

  public void onSaveInstanceState(Bundle paramBundle)
  {
    if (this.mTargetChildFragment != null)
      this.mMasterFragment.getChildFragmentManager().putFragment(paramBundle, "FragmentMaster:TARGET_CHILD_FRAGMENT", this.mTargetChildFragment.getFragment());
    paramBundle.putParcelable("FragmentMaster:MASTER_FRAGMENT_STATE", new MasterFragmentState(this));
    this.mStateSaved = true;
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentSaveInstanceState(this.mMasterFragment, paramBundle);
  }

  public void onStart()
  {
    this.mStateSaved = false;
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentStarted(this.mMasterFragment);
  }

  public void onStop()
  {
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentStopped(this.mMasterFragment);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onTrackballEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    TypedValue localTypedValue = new TypedValue();
    getFragmentContext().getTheme().resolveAttribute(16842836, localTypedValue, true);
    paramView.setClickable(true);
    if (getFragmentMaster() != null)
      getFragmentMaster().dispatchFragmentViewCreated(this.mMasterFragment);
  }

  public void setPrimary(boolean paramBoolean)
  {
    this.mMasterFragment.setMenuVisibility(paramBoolean);
    this.mMasterFragment.setUserVisibleHint(paramBoolean);
    onSetPrimary(paramBoolean);
  }

  public void setRequest(Request paramRequest)
  {
    this.mRequest = paramRequest;
  }

  public final void setResult(int paramInt)
  {
    monitorenter;
    try
    {
      this.mResultCode = paramInt;
      this.mResultData = null;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public final void setResult(int paramInt, Request paramRequest)
  {
    monitorenter;
    try
    {
      this.mResultCode = paramInt;
      this.mResultData = paramRequest;
      return;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public void setSlideable(boolean paramBoolean)
  {
    if (this.mIsSlideable != paramBoolean)
    {
      this.mIsSlideable = paramBoolean;
      invalidateMasterConfiguration();
    }
  }

  public void setSoftInputMode(int paramInt)
  {
    if (this.mSoftInputMode != paramInt)
    {
      this.mSoftInputMode = paramInt;
      invalidateWindowConfiguration();
    }
  }

  public void setTargetChildFragment(IMasterFragment paramIMasterFragment)
  {
    this.mTargetChildFragment = paramIMasterFragment;
  }

  public void startFragment(Request paramRequest)
  {
    startFragmentForResult(paramRequest, -1);
  }

  public void startFragment(Class<? extends IMasterFragment> paramClass)
  {
    startFragmentForResult(new Request(paramClass), -1);
  }

  public void startFragmentForResult(Request paramRequest, int paramInt)
  {
    checkState();
    if ((this.mMasterFragment.getParentFragment() instanceof IMasterFragment))
    {
      ((IMasterFragment)this.mMasterFragment.getParentFragment()).startFragmentFromChild(this.mMasterFragment, paramRequest, paramInt);
      return;
    }
    getFragmentMaster().startFragmentForResult(this.mMasterFragment, paramRequest, paramInt);
  }

  public void startFragmentForResult(Class<? extends IMasterFragment> paramClass, int paramInt)
  {
    startFragmentForResult(new Request(paramClass), paramInt);
  }

  public void startFragmentFromChild(IMasterFragment paramIMasterFragment, Request paramRequest, int paramInt)
  {
    if (paramInt != -1)
      this.mTargetChildFragment = paramIMasterFragment;
    startFragmentForResult(paramRequest, paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterFragmentDelegate
 * JD-Core Version:    0.6.0
 */