package com.fragmentmaster.app;

import android.annotation.SuppressLint;
import android.os.Parcelable;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.view.ViewPager.PageTransformer;
import android.support.v4.view.ViewPagerCompat;
import android.view.MotionEvent;
import android.view.View;
import com.fragmentmaster.animator.PageAnimator;
import com.nineoldandroids.view.ViewHelper;

class FragmentMasterPager extends ViewPagerCompat
{
  private static final int ANIMATION_ENTER = 1;
  private static final int ANIMATION_EXIT = 2;
  private static final int ANIMATION_NONE;
  private int mAnimationState = 0;
  private FragmentMasterImpl mFragmentMasterImpl;
  private Runnable mIdleRunnable = new Runnable()
  {
    public void run()
    {
      FragmentMasterPager.access$002(FragmentMasterPager.this, FragmentMasterPager.this.getCurrentItem());
      FragmentMasterPager.this.setAnimationState(0);
    }
  };
  private int mLatestIdleItem = 0;
  private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramInt)
    {
      if (FragmentMasterPager.this.mWrappedOnPageChangeListener != null)
        FragmentMasterPager.this.mWrappedOnPageChangeListener.onPageScrollStateChanged(paramInt);
      if (paramInt == 0)
        FragmentMasterPager.this.post(FragmentMasterPager.this.mIdleRunnable);
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (FragmentMasterPager.this.mWrappedOnPageChangeListener != null)
        FragmentMasterPager.this.mWrappedOnPageChangeListener.onPageScrolled(paramInt1, paramFloat, paramInt2);
    }

    public void onPageSelected(int paramInt)
    {
      if (FragmentMasterPager.this.mWrappedOnPageChangeListener != null)
        FragmentMasterPager.this.mWrappedOnPageChangeListener.onPageSelected(paramInt);
    }
  };
  private ViewPager.PageTransformer mPageTransformer = new ViewPager.PageTransformer()
  {
    private void resetPage(View paramView, float paramFloat)
    {
      ViewHelper.setAlpha(paramView, 1.0F);
      ViewHelper.setTranslationX(paramView, 0.0F);
      ViewHelper.setTranslationY(paramView, 0.0F);
      ViewHelper.setScaleX(paramView, 1.0F);
      ViewHelper.setScaleY(paramView, 1.0F);
      ViewHelper.setRotation(paramView, 0.0F);
      ViewHelper.setRotationX(paramView, 0.0F);
      ViewHelper.setRotationY(paramView, 0.0F);
      ViewHelper.setPivotX(paramView, paramView.getWidth() / 2.0F);
      ViewHelper.setPivotY(paramView, paramView.getHeight() / 2.0F);
    }

    public void transformPage(View paramView, float paramFloat)
    {
      int i = 1;
      resetPage(paramView, paramFloat);
      if (FragmentMasterPager.this.mFragmentMasterImpl.hasPageAnimator())
      {
        if ((paramFloat < -1.0F) || (paramFloat > 1.0F))
        {
          paramView.setVisibility(4);
          return;
        }
        paramView.setVisibility(0);
        PageAnimator localPageAnimator = FragmentMasterPager.this.mFragmentMasterImpl.getPageAnimator();
        if (FragmentMasterPager.this.mAnimationState == i);
        while (true)
        {
          localPageAnimator.transformPage(paramView, paramFloat, i);
          return;
          int j = 0;
        }
      }
      paramView.setVisibility(0);
    }
  };
  private ViewPager.OnPageChangeListener mWrappedOnPageChangeListener;

  public FragmentMasterPager(FragmentMasterImpl paramFragmentMasterImpl)
  {
    super(paramFragmentMasterImpl.getActivity());
    this.mFragmentMasterImpl = paramFragmentMasterImpl;
    super.setOnPageChangeListener(this.mOnPageChangeListener);
    setPageTransformer(false, this.mPageTransformer);
  }

  private void setAnimationState(int paramInt)
  {
    this.mAnimationState = paramInt;
  }

  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent)
  {
    return (this.mFragmentMasterImpl.isSlideable()) && (!this.mFragmentMasterImpl.isScrolling()) && (super.onInterceptTouchEvent(paramMotionEvent));
  }

  protected void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    if (this.mLatestIdleItem > paramInt1)
      setAnimationState(2);
    while (true)
    {
      super.onPageScrolled(paramInt1, paramFloat, paramInt2);
      return;
      if (this.mLatestIdleItem > paramInt1)
        continue;
      setAnimationState(1);
    }
  }

  public void onRestoreInstanceState(Parcelable paramParcelable)
  {
    super.onRestoreInstanceState(paramParcelable);
    this.mLatestIdleItem = getCurrentItem();
  }

  @SuppressLint({"ClickableViewAccessibility"})
  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return (this.mFragmentMasterImpl.isSlideable()) && (!this.mFragmentMasterImpl.isScrolling()) && (super.onTouchEvent(paramMotionEvent));
  }

  public void setOnPageChangeListener(ViewPager.OnPageChangeListener paramOnPageChangeListener)
  {
    this.mWrappedOnPageChangeListener = paramOnPageChangeListener;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentMasterPager
 * JD-Core Version:    0.6.0
 */