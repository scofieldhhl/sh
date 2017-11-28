package com.ex.ltech.led.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class FragmentPageAdapter extends FragmentPagerAdapter
{
  private List<Fragment> mFragments = null;

  public FragmentPageAdapter(FragmentManager paramFragmentManager, List<Fragment> paramList)
  {
    super(paramFragmentManager);
    this.mFragments = paramList;
  }

  public int getCount()
  {
    return this.mFragments.size();
  }

  public Fragment getItem(int paramInt)
  {
    return (Fragment)this.mFragments.get(paramInt);
  }
}
