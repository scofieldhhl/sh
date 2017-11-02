package com.ex.ltech.led;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.TabsFragmentAdapter;
import com.ex.ltech.onepiontfive.main.room.RoomFragment;
import com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs;
import java.util.LinkedList;
import java.util.List;

public class FtTest extends MyBaseFt
{
  public static final String[] titles = { "Any", "Bay", "Cue", "Dam" };
  private TabsFragmentAdapter adapter;

  @Bind({2131559256})
  EasySlidingTabs easySlidingTabs;

  @Bind({2131559258})
  NoScrollViewPager easyVp;
  List<Fragment> fragments;

  public void onActivityCreated(Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        FtTest.this.fragments = new LinkedList();
        FtTest.this.fragments.add(new RoomFragment());
        FtTest.this.fragments.add(new RoomFragment());
        FtTest.this.fragments.add(new RoomFragment());
        FtTest.this.fragments.add(new RoomFragment());
        FtTest.this.easyVp.setAdapter(FtTest.this.adapter);
        FtTest.this.easySlidingTabs.setViewPager(FtTest.this.easyVp);
      }
    }
    , 2000L);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968783, null);
    ButterKnife.bind(this, localView);
    return localView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.FtTest
 * JD-Core Version:    0.6.0
 */