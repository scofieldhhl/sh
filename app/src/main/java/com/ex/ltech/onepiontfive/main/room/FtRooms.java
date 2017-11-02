package com.ex.ltech.onepiontfive.main.room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.TabsFragmentAdapter;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs;
import com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs.MyPageChangeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class FtRooms extends MyBaseFt
  implements View.OnClickListener
{
  public static int[] bgRes;
  public static FtRooms ftRoomsInstance;
  public static Dvc seletedDvc;
  public static int tagPagePosi = 0;
  Runnable BanFastClickTabsRunnable = new Runnable()
  {
    public void run()
    {
      if (FtRooms.this.banFastClickTabsLayout != null)
        FtRooms.this.banFastClickTabsLayout.setVisibility(8);
    }
  };
  private TabsFragmentAdapter adapter;
  Button back;

  @Bind({2131559257})
  RelativeLayout banFastClickTabsLayout;
  RoomsBusiness business;

  @Bind({2131559259})
  TextView dvcOkOperea;
  private EasySlidingTabs easySlidingTabs;
  private NoScrollViewPager easyVp;
  RoomFragment fragment = null;
  List<RoomFragment> fragments;
  Handler handler = new Handler()
  {
  };
  private FrameLayout idContent;
  int lastPageSeletedPosi = 0;
  Button menu;
  Runnable test = new Runnable()
  {
    public void run()
    {
      String str;
      boolean bool;
      if (FtRooms.this.testtime < 10000)
      {
        if (!((RoomFragment)FtRooms.this.fragments.get(FtRooms.tagPagePosi)).listViewShow)
          break label241;
        str = "HideListviewBroadCast";
        Intent localIntent = new Intent(str);
        LocalBroadcastManager.getInstance(FtRooms.this.getActivity()).sendBroadcast(localIntent);
        RoomFragment localRoomFragment = (RoomFragment)FtRooms.this.fragments.get(FtRooms.tagPagePosi);
        if (((RoomFragment)FtRooms.this.fragments.get(FtRooms.tagPagePosi)).listViewShow)
          break label247;
        bool = true;
        label133: localRoomFragment.listViewShow = bool;
        if (!((RoomFragment)FtRooms.this.fragments.get(FtRooms.tagPagePosi)).listViewShow)
          break label253;
        FtRooms.this.view.setBackgroundResource(2130903464);
      }
      while (true)
      {
        FtRooms localFtRooms = FtRooms.this;
        localFtRooms.testtime = (1 + localFtRooms.testtime);
        System.out.println("发发发发 time = " + FtRooms.this.testtime);
        FtRooms.this.handler.postDelayed(this, 5L);
        return;
        label241: str = "ShowListviewBroadCast";
        break;
        label247: bool = false;
        break label133;
        label253: FtRooms.this.view.setBackgroundResource(2130903466);
      }
    }
  };
  int testtime = 0;
  View view;

  static
  {
    bgRes = new int[] { 2130903077, 2130903665, 2130903078, 2130903413, 2130903683, 2130903143, 2130903684, 2130903789 };
  }

  private void initData()
  {
    this.dvcOkOperea.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtRooms.this.menu.setVisibility(0);
        FtRooms.this.dvcOkOperea.setVisibility(8);
        ((RoomFragment)FtRooms.this.fragments.get(FtRooms.tagPagePosi)).hideDelBtn();
      }
    });
    this.fragments = new LinkedList();
    this.business = new RoomsBusiness(getActivity());
    ArrayList localArrayList = new ArrayList();
    Home localHome = this.business.getHome();
    for (int i = 0; i < localHome.getRooms().size(); i++)
    {
      this.fragment = new RoomFragment();
      this.fragment.roomIndex(i);
      this.fragment.setmPosi(i);
      this.fragment.setRoom((Room)localHome.getRooms().get(i));
      localArrayList.add(((Room)localHome.getRooms().get(i)).getName());
      this.fragments.add(this.fragment);
    }
    this.adapter = new TabsFragmentAdapter(getChildFragmentManager(), localArrayList, this.fragments);
    this.easyVp.setAdapter(this.adapter);
    this.easySlidingTabs.setViewPager(this.easyVp);
    this.easySlidingTabs.setMyPageChangeListener(new EasySlidingTabs.MyPageChangeListener()
    {
      public void onPageChange(int paramInt)
      {
        if (FtRooms.this.lastPageSeletedPosi != paramInt)
        {
          FtRooms.this.fragment.setRoom((Room)FtRooms.this.business.getHome().getRooms().get(paramInt));
          ((RoomFragment)FtRooms.this.fragments.get(FtRooms.this.lastPageSeletedPosi)).onMimeInvisiable();
          ((RoomFragment)FtRooms.this.fragments.get(paramInt)).onMimeSeleted();
          FtRooms.this.menu.setVisibility(0);
          FtRooms.this.dvcOkOperea.setVisibility(8);
        }
        FtRooms.this.lastPageSeletedPosi = paramInt;
        FtRooms.this.handler.removeCallbacks(FtRooms.this.BanFastClickTabsRunnable);
        FtRooms.this.banFastClickTabsLayout.setVisibility(0);
        FtRooms.this.handler.postDelayed(FtRooms.this.BanFastClickTabsRunnable, 400L);
      }
    });
    this.menu.setOnClickListener(this);
    getActivity().runOnUiThread(new Runnable()
    {
      public void run()
      {
      }
    });
  }

  public void canScroll(boolean paramBoolean)
  {
    NoScrollViewPager localNoScrollViewPager = this.easyVp;
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      localNoScrollViewPager.setNoScroll(bool);
      return;
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131558858:
      getActivity().finish();
      return;
    case 2131558970:
    }
    String str;
    RoomFragment localRoomFragment;
    if (((RoomFragment)this.fragments.get(tagPagePosi)).listViewShow)
    {
      str = "HideListviewBroadCast";
      Intent localIntent = new Intent(str);
      LocalBroadcastManager.getInstance(getActivity()).sendBroadcast(localIntent);
      localRoomFragment = (RoomFragment)this.fragments.get(tagPagePosi);
      if (((RoomFragment)this.fragments.get(tagPagePosi)).listViewShow)
        break label192;
    }
    label192: for (boolean bool = true; ; bool = false)
    {
      localRoomFragment.listViewShow = bool;
      if (!((RoomFragment)this.fragments.get(tagPagePosi)).listViewShow)
        break label198;
      ((Button)paramView).setBackgroundResource(2130903464);
      return;
      str = "ShowListviewBroadCast";
      break;
    }
    label198: ((Button)paramView).setBackgroundResource(2130903466);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968775, null);
      this.back = ((Button)this.view.findViewById(2131558858));
      this.menu = ((Button)this.view.findViewById(2131558970));
      ButterKnife.bind(this, this.view);
      this.easySlidingTabs = ((EasySlidingTabs)this.view.findViewById(2131559256));
      this.easyVp = ((NoScrollViewPager)this.view.findViewById(2131559258));
      this.easyVp.setNoScroll(false);
      this.view.findViewById(2131558858).setOnClickListener(this);
      this.view.findViewById(2131558970).setOnClickListener(this);
      this.idContent = ((FrameLayout)this.view.findViewById(2131559260));
      initData();
    }
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
    tagPagePosi = 0;
  }

  public void onDeviceEditShow()
  {
    this.menu.setVisibility(8);
    this.dvcOkOperea.setVisibility(0);
  }

  public void onPause()
  {
    super.onPause();
    ((RoomFragment)this.fragments.get(tagPagePosi)).hideDelBtn();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    ftRoomsInstance = this;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.FtRooms
 * JD-Core Version:    0.6.0
 */