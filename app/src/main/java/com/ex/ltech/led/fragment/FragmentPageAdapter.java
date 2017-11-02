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

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.fragment.FragmentPageAdapter
 * JD-Core Version:    0.6.0
 */