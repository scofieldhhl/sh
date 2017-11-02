package com.fragmentmaster.app;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import com.fragmentmaster.R.id;
import java.util.ArrayList;
import java.util.List;

class FragmentMasterImpl extends FragmentMaster
{
  public static final int FRAGMENT_CONTAINER_ID = R.id.internal_fragment_container;
  private FragmentsAdapter mAdapter;
  private Runnable mCleanUpRunnable = new Runnable()
  {
    public void run()
    {
      FragmentMasterImpl.this.cleanUp();
    }
  };
  private ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener()
  {
    public void onPageScrollStateChanged(int paramInt)
    {
      FragmentMasterImpl.access$002(FragmentMasterImpl.this, paramInt);
      if (paramInt == 0)
      {
        FragmentMasterImpl.this.mViewPager.post(new Runnable()
        {
          public void run()
          {
            FragmentMasterImpl.this.setUpAnimator(FragmentMasterImpl.this.getPrimaryFragment());
          }
        });
        FragmentMasterImpl.this.onScrollIdle();
      }
    }

    public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
    {
      if (FragmentMasterImpl.this.mState == 0)
        FragmentMasterImpl.this.setUpAnimator(FragmentMasterImpl.this.getPrimaryFragment());
    }

    public void onPageSelected(int paramInt)
    {
    }
  };
  private boolean mScrolling = false;
  private int mState = 0;
  private FragmentMasterPager mViewPager;

  FragmentMasterImpl(FragmentActivity paramFragmentActivity)
  {
    super(paramFragmentActivity);
  }

  private void cleanUp()
  {
    ArrayList localArrayList = new ArrayList(getFragments());
    IMasterFragment localIMasterFragment1 = getPrimaryFragment();
    int i = 1;
    int j = -1 + localArrayList.size();
    if (j >= 0)
    {
      IMasterFragment localIMasterFragment2 = (IMasterFragment)localArrayList.get(j);
      if (localIMasterFragment2 == localIMasterFragment1)
        i = 0;
      if (i != 0)
        if (isInFragmentMaster(localIMasterFragment2))
        {
          if (!isFinishPending(localIMasterFragment2))
            break label89;
          doFinishFragment(localIMasterFragment2);
        }
      while (true)
      {
        j--;
        break;
        label89: localIMasterFragment2.finish();
        continue;
        if ((!isFinishPending(localIMasterFragment2)) || (this.mScrolling))
          continue;
        doFinishFragment(localIMasterFragment2);
      }
    }
  }

  private void onScrollIdle()
  {
    this.mScrolling = false;
    this.mViewPager.removeCallbacks(this.mCleanUpRunnable);
    this.mViewPager.post(this.mCleanUpRunnable);
  }

  protected int getFragmentContainerId()
  {
    return FRAGMENT_CONTAINER_ID;
  }

  boolean isScrolling()
  {
    return this.mScrolling;
  }

  protected void onFinishFragment(IMasterFragment paramIMasterFragment, int paramInt, Request paramRequest)
  {
    int i = getFragments().indexOf(paramIMasterFragment);
    int j = this.mViewPager.getCurrentItem();
    if ((hasPageAnimator()) && (j == i) && (i != 0))
    {
      this.mViewPager.setCurrentItem(i - 1, true);
      this.mScrolling = true;
    }
    if (this.mScrolling)
    {
      deliverFragmentResult(paramIMasterFragment, paramInt, paramRequest);
      return;
    }
    super.onFinishFragment(paramIMasterFragment, paramInt, paramRequest);
  }

  protected void onFragmentFinished(IMasterFragment paramIMasterFragment)
  {
    this.mAdapter.notifyDataSetChanged();
  }

  protected void onFragmentStarted(IMasterFragment paramIMasterFragment)
  {
    this.mAdapter.notifyDataSetChanged();
    int i = -1 + this.mAdapter.getCount();
    if ((hasPageAnimator()) && (i > 0));
    for (boolean bool = true; ; bool = false)
    {
      this.mViewPager.setCurrentItem(i, bool);
      if (bool)
        this.mScrolling = true;
      return;
    }
  }

  protected void performInstall(ViewGroup paramViewGroup)
  {
    this.mAdapter = new FragmentsAdapter(null);
    this.mViewPager = new FragmentMasterPager(this);
    this.mViewPager.setId(FRAGMENT_CONTAINER_ID);
    this.mViewPager.setOffscreenPageLimit(2147483647);
    this.mViewPager.setAdapter(this.mAdapter);
    this.mViewPager.setOnPageChangeListener(this.mOnPageChangeListener);
    paramViewGroup.addView(this.mViewPager);
  }

  private class FragmentsAdapter extends PagerAdapter
  {
    private FragmentsAdapter()
    {
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
    }

    public int getCount()
    {
      return FragmentMasterImpl.this.getFragments().size();
    }

    public int getItemPosition(Object paramObject)
    {
      IMasterFragment localIMasterFragment = (IMasterFragment)paramObject;
      int i = FragmentMasterImpl.this.getFragments().indexOf(localIMasterFragment);
      if (i == -1)
        i = -2;
      return i;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      return FragmentMasterImpl.this.getFragments().get(paramInt);
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return ((IMasterFragment)paramObject).getView() == paramView;
    }

    public void setPrimaryItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      FragmentMasterImpl.this.setPrimaryFragment((IMasterFragment)paramObject);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentMasterImpl
 * JD-Core Version:    0.6.0
 */