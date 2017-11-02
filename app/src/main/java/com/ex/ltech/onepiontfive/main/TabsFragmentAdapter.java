package com.ex.ltech.onepiontfive.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.TextUtils;
import com.ex.ltech.onepiontfive.main.room.RoomFragment;
import com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs.TabsTitleInterface;
import java.util.List;

public class TabsFragmentAdapter extends FragmentPagerAdapter
  implements EasySlidingTabs.TabsTitleInterface
{
  private List<RoomFragment> fragments;
  private List<String> titles;

  public TabsFragmentAdapter(FragmentManager paramFragmentManager, List<String> paramList, List<RoomFragment> paramList1)
  {
    super(paramFragmentManager);
    this.fragments = paramList1;
    this.titles = paramList;
  }

  public int getCount()
  {
    return this.fragments.size();
  }

  public Fragment getItem(int paramInt)
  {
    if ((Fragment)this.fragments.get(paramInt) != null)
      return (Fragment)this.fragments.get(paramInt);
    return null;
  }

  public CharSequence getPageTitle(int paramInt)
  {
    if (paramInt < this.titles.size())
      return (CharSequence)this.titles.get(paramInt);
    return "";
  }

  public int getTabDrawableBottom(int paramInt)
  {
    return 0;
  }

  public int getTabDrawableLeft(int paramInt)
  {
    return 0;
  }

  public int getTabDrawableRight(int paramInt)
  {
    return 0;
  }

  public int getTabDrawableTop(int paramInt)
  {
    return 0;
  }

  public SpannableString getTabTitle(int paramInt)
  {
    CharSequence localCharSequence = getPageTitle(paramInt);
    if (TextUtils.isEmpty(localCharSequence))
      return new SpannableString("");
    return new SpannableString(localCharSequence);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.TabsFragmentAdapter
 * JD-Core Version:    0.6.0
 */