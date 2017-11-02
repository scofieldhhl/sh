package com.fragmentmaster.app;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.fragmentmaster.animator.PageAnimator;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class FragmentMaster
{
  private static final String TAG = "FragmentMaster";
  private final FragmentActivity mActivity;
  private ViewGroup mContainer;
  private int mContainerResID = 0;
  private final MasterEventDispatcher mEventDispatcher;
  private final HashSet<IMasterFragment> mFinishPendingFragments = new HashSet();
  private final ArrayList<FragmentLifecycleCallbacks> mFragmentLifecycleCallbacks = new ArrayList();
  private final FragmentManager mFragmentManager;
  private boolean mHomeFragmentApplied = false;
  private boolean mIsInstalled = false;
  private boolean mIsSlideable = false;
  private PageAnimator mPageAnimator = null;
  private IMasterFragment mPrimaryFragment = null;
  private final Records mRecords = new Records();
  private boolean mSticky = false;

  FragmentMaster(FragmentActivity paramFragmentActivity)
  {
    this.mActivity = paramFragmentActivity;
    this.mFragmentManager = paramFragmentActivity.getSupportFragmentManager();
    this.mEventDispatcher = new MasterEventDispatcher(paramFragmentActivity);
  }

  private void applyHomeFragment(Request paramRequest, boolean paramBoolean)
  {
    this.mSticky = paramBoolean;
    if (!this.mHomeFragmentApplied)
    {
      startFragmentForResult(null, paramRequest, -1);
      this.mHomeFragmentApplied = true;
    }
  }

  private void checkInstallProperties()
  {
    View localView = this.mActivity.findViewById(this.mContainerResID);
    if (localView == null)
      throw new RuntimeException("No view found for id 0x" + Integer.toHexString(this.mContainerResID));
    this.mContainer = ((ViewGroup)localView);
  }

  private Object[] collectFragmentLifecycleCallbacks()
  {
    synchronized (this.mFragmentLifecycleCallbacks)
    {
      int i = this.mFragmentLifecycleCallbacks.size();
      Object[] arrayOfObject = null;
      if (i > 0)
        arrayOfObject = this.mFragmentLifecycleCallbacks.toArray();
      return arrayOfObject;
    }
  }

  private void dispatchFragmentResult(IMasterFragment paramIMasterFragment, int paramInt1, int paramInt2, Request paramRequest)
  {
    if (paramIMasterFragment.isFinishing())
      return;
    if (paramIMasterFragment.getTargetChildFragment() == null)
      paramIMasterFragment.onFragmentResult(paramInt1, paramInt2, paramRequest);
    while (true)
    {
      paramIMasterFragment.setTargetChildFragment(null);
      return;
      dispatchFragmentResult(paramIMasterFragment.getTargetChildFragment(), paramInt1, paramInt2, paramRequest);
    }
  }

  private void ensureInstalled()
  {
    if (!isInstalled())
      throw new IllegalStateException("Haven't installed.");
  }

  private void logState()
  {
  }

  private IMasterFragment newFragment(String paramString)
  {
    try
    {
      IMasterFragment localIMasterFragment = (IMasterFragment)Fragment.instantiate(getActivity(), paramString, new Bundle());
      return localIMasterFragment;
    }
    catch (Exception localException)
    {
    }
    throw new RuntimeException("No fragment found : { className=" + paramString + " }");
  }

  private void throwIfNotInFragmentMaster(IMasterFragment paramIMasterFragment)
  {
    if (!isInFragmentMaster(paramIMasterFragment))
      throw new IllegalStateException("Fragment {" + paramIMasterFragment + "} not currently in FragmentMaster.");
  }

  protected void deliverFragmentResult(IMasterFragment paramIMasterFragment, int paramInt, Request paramRequest)
  {
    Fragment localFragment = paramIMasterFragment.getTargetFragment();
    if ((paramIMasterFragment.getTargetRequestCode() != -1) && ((localFragment instanceof IMasterFragment)))
      dispatchFragmentResult((IMasterFragment)localFragment, paramIMasterFragment.getTargetRequestCode(), paramInt, paramRequest);
  }

  void dispatchFragmentActivated(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentActivated(paramIMasterFragment);
    }
  }

  void dispatchFragmentAttached(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentAttached(paramIMasterFragment);
    }
  }

  void dispatchFragmentCreated(IMasterFragment paramIMasterFragment, Bundle paramBundle)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentCreated(paramIMasterFragment, paramBundle);
    }
  }

  void dispatchFragmentDeactivated(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentDeactivated(paramIMasterFragment);
    }
  }

  void dispatchFragmentDestroyed(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentDestroyed(paramIMasterFragment);
    }
  }

  void dispatchFragmentDetached(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentDetached(paramIMasterFragment);
    }
  }

  void dispatchFragmentPaused(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentPaused(paramIMasterFragment);
    }
  }

  void dispatchFragmentResumed(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentResumed(paramIMasterFragment);
    }
  }

  void dispatchFragmentSaveInstanceState(IMasterFragment paramIMasterFragment, Bundle paramBundle)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentSaveInstanceState(paramIMasterFragment, paramBundle);
    }
  }

  void dispatchFragmentStarted(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentStarted(paramIMasterFragment);
    }
  }

  void dispatchFragmentStopped(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentStopped(paramIMasterFragment);
    }
  }

  void dispatchFragmentViewCreated(IMasterFragment paramIMasterFragment)
  {
    Object[] arrayOfObject = collectFragmentLifecycleCallbacks();
    if (arrayOfObject != null)
    {
      int i = arrayOfObject.length;
      for (int j = 0; j < i; j++)
        ((FragmentLifecycleCallbacks)arrayOfObject[j]).onFragmentViewCreated(paramIMasterFragment);
    }
  }

  boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchGenericMotionEvent(paramMotionEvent);
  }

  boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return this.mEventDispatcher.dispatchKeyEvent(paramKeyEvent);
  }

  boolean dispatchKeyShortcutEvent(KeyEvent paramKeyEvent)
  {
    return this.mEventDispatcher.dispatchKeyShortcutEvent(paramKeyEvent);
  }

  boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchTouchEvent(paramMotionEvent);
  }

  boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    return this.mEventDispatcher.dispatchTrackballEvent(paramMotionEvent);
  }

  public final void doFinishCurrentFragment(IMasterFragment paramIMasterFragment)
  {
    this.mFragmentManager.beginTransaction().remove(paramIMasterFragment.getFragment()).commit();
  }

  protected final void doFinishFragment(IMasterFragment paramIMasterFragment)
  {
    if ((this.mRecords.indexOf(paramIMasterFragment) == 0) && (this.mSticky))
    {
      this.mActivity.finish();
      return;
    }
    this.mFragmentManager.beginTransaction().remove(paramIMasterFragment.getFragment()).commit();
    this.mFragmentManager.executePendingTransactions();
    this.mRecords.remove(paramIMasterFragment);
    this.mFinishPendingFragments.remove(paramIMasterFragment);
    onFragmentFinished(paramIMasterFragment);
  }

  public final void finishFragment(IMasterFragment paramIMasterFragment, int paramInt, Request paramRequest)
  {
    ensureInstalled();
    throwIfNotInFragmentMaster(paramIMasterFragment);
    if (!isFinishPending(paramIMasterFragment))
      this.mFinishPendingFragments.add(paramIMasterFragment);
    onFinishFragment(paramIMasterFragment, paramInt, paramRequest);
  }

  public FragmentActivity getActivity()
  {
    return this.mActivity;
  }

  public int getContainerResId()
  {
    return this.mContainerResID;
  }

  protected int getFragmentContainerId()
  {
    return getContainerResId();
  }

  public FragmentManager getFragmentManager()
  {
    return this.mFragmentManager;
  }

  public List<IMasterFragment> getFragments()
  {
    return this.mRecords.getFragments();
  }

  public PageAnimator getPageAnimator()
  {
    return this.mPageAnimator;
  }

  public IMasterFragment getPrimaryFragment()
  {
    return this.mPrimaryFragment;
  }

  public boolean hasPageAnimator()
  {
    return this.mPageAnimator != null;
  }

  public final void install(int paramInt)
  {
    if (isInstalled())
      throw new IllegalStateException("Already installed!");
    this.mContainerResID = paramInt;
    checkInstallProperties();
    performInstall(this.mContainer);
    this.mIsInstalled = true;
  }

  public final void install(int paramInt, Request paramRequest, boolean paramBoolean)
  {
    if (isInstalled())
      throw new IllegalStateException("Already installed!");
    this.mContainerResID = paramInt;
    checkInstallProperties();
    performInstall(this.mContainer);
    this.mIsInstalled = true;
    if (paramRequest != null)
      applyHomeFragment(paramRequest, paramBoolean);
  }

  public boolean isFinishPending(IMasterFragment paramIMasterFragment)
  {
    return this.mFinishPendingFragments.contains(paramIMasterFragment);
  }

  public boolean isInFragmentMaster(IMasterFragment paramIMasterFragment)
  {
    return this.mRecords.has(paramIMasterFragment);
  }

  public boolean isInstalled()
  {
    return this.mIsInstalled;
  }

  public boolean isSlideable()
  {
    return (hasPageAnimator()) && (this.mIsSlideable);
  }

  protected void onFinishFragment(IMasterFragment paramIMasterFragment, int paramInt, Request paramRequest)
  {
    doFinishFragment(paramIMasterFragment);
    deliverFragmentResult(paramIMasterFragment, paramInt, paramRequest);
  }

  protected abstract void onFragmentFinished(IMasterFragment paramIMasterFragment);

  protected abstract void onFragmentStarted(IMasterFragment paramIMasterFragment);

  protected abstract void performInstall(ViewGroup paramViewGroup);

  public void registerFragmentLifecycleCallbacks(FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    synchronized (this.mFragmentLifecycleCallbacks)
    {
      this.mFragmentLifecycleCallbacks.add(paramFragmentLifecycleCallbacks);
      return;
    }
  }

  void restoreAllState(Parcelable paramParcelable)
  {
    if (paramParcelable != null)
    {
      FragmentMasterState localFragmentMasterState = (FragmentMasterState)paramParcelable;
      this.mRecords.restore(this.mFragmentManager, localFragmentMasterState.mFragments);
      setSlideable(localFragmentMasterState.mIsSlideable);
      this.mHomeFragmentApplied = localFragmentMasterState.mHomeFragmentApplied;
    }
  }

  Parcelable saveAllState()
  {
    FragmentMasterState localFragmentMasterState = new FragmentMasterState();
    localFragmentMasterState.mFragments = this.mRecords.save(this.mFragmentManager);
    localFragmentMasterState.mIsSlideable = this.mIsSlideable;
    localFragmentMasterState.mHomeFragmentApplied = this.mHomeFragmentApplied;
    logState();
    return localFragmentMasterState;
  }

  protected void setPageAnimator(PageAnimator paramPageAnimator)
  {
    this.mPageAnimator = paramPageAnimator;
  }

  protected final void setPrimaryFragment(IMasterFragment paramIMasterFragment)
  {
    if (paramIMasterFragment != this.mPrimaryFragment)
    {
      if (this.mPrimaryFragment != null)
        this.mPrimaryFragment.setPrimary(false);
      if (paramIMasterFragment != null)
        paramIMasterFragment.setPrimary(true);
      this.mPrimaryFragment = paramIMasterFragment;
      this.mEventDispatcher.setInterceptor(paramIMasterFragment);
    }
  }

  public final void setSlideable(boolean paramBoolean)
  {
    this.mIsSlideable = paramBoolean;
  }

  protected void setUpAnimator(IMasterFragment paramIMasterFragment)
  {
    if (paramIMasterFragment != null);
    for (PageAnimator localPageAnimator = paramIMasterFragment.onCreatePageAnimator(); ; localPageAnimator = null)
    {
      setPageAnimator(localPageAnimator);
      return;
    }
  }

  public final void startFragmentForResult(IMasterFragment paramIMasterFragment, Request paramRequest, int paramInt)
  {
    ensureInstalled();
    IMasterFragment localIMasterFragment = newFragment(paramRequest.getClassName());
    localIMasterFragment.setRequest((Request)paramRequest.clone());
    this.mFragmentManager.beginTransaction().add(getFragmentContainerId(), localIMasterFragment.getFragment()).commitAllowingStateLoss();
    this.mFragmentManager.executePendingTransactions();
    this.mRecords.add(localIMasterFragment, paramIMasterFragment, paramInt);
    localIMasterFragment.setPrimary(false);
    setUpAnimator(localIMasterFragment);
    onFragmentStarted(localIMasterFragment);
  }

  public void unregisterFragmentLifecycleCallbacks(FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    synchronized (this.mFragmentLifecycleCallbacks)
    {
      this.mFragmentLifecycleCallbacks.remove(paramFragmentLifecycleCallbacks);
      return;
    }
  }

  public static abstract interface FragmentLifecycleCallbacks
  {
    public abstract void onFragmentActivated(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentAttached(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentCreated(IMasterFragment paramIMasterFragment, Bundle paramBundle);

    public abstract void onFragmentDeactivated(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentDestroyed(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentDetached(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentPaused(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentResumed(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentSaveInstanceState(IMasterFragment paramIMasterFragment, Bundle paramBundle);

    public abstract void onFragmentStarted(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentStopped(IMasterFragment paramIMasterFragment);

    public abstract void onFragmentViewCreated(IMasterFragment paramIMasterFragment);
  }

  public static class SimpleFragmentLifecycleCallbacks
    implements FragmentLifecycleCallbacks
  {
    public void onFragmentActivated(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentAttached(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentCreated(IMasterFragment paramIMasterFragment, Bundle paramBundle)
    {
    }

    public void onFragmentDeactivated(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentDestroyed(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentDetached(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentPaused(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentResumed(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentSaveInstanceState(IMasterFragment paramIMasterFragment, Bundle paramBundle)
    {
    }

    public void onFragmentStarted(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentStopped(IMasterFragment paramIMasterFragment)
    {
    }

    public void onFragmentViewCreated(IMasterFragment paramIMasterFragment)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentMaster
 * JD-Core Version:    0.6.0
 */