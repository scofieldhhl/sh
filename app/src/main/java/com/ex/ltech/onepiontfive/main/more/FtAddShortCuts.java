package com.ex.ltech.onepiontfive.main.more;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.onepiontfive.main.Constant;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomsBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;

public class FtAddShortCuts extends MyBaseFt
{
  FtAddShortCutsAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private RoomsBusiness business;
  private Home home;
  private String mac;
  private ArrayList<Dvc> result;
  private Room room;
  ShortCutInner shortCutInner;
  private SwipeMenuListView shortcuts_list = null;
  private ArrayList<Dvc> tempResult;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void init()
  {
    this.result = new ArrayList();
    this.tempResult = new ArrayList();
    this.business = new RoomsBusiness(getActivity());
    this.home = this.business.getHome();
    this.room = ((Room)this.home.getRooms().get(0));
    int i = 0;
    this.room.getDvcVos().size();
    Iterator localIterator = this.room.getDvcVos().iterator();
    while (localIterator.hasNext())
    {
      Dvc localDvc1 = (Dvc)localIterator.next();
      this.tempResult.add(localDvc1);
      this.result.add(localDvc1);
      i++;
      if ((localDvc1.getName() != null) && (!localDvc1.getName().equals("添加")))
      {
        if (i != this.room.getDvcVos().size())
          continue;
        Dvc localDvc3 = new Dvc();
        localDvc3.setName("添加");
        localDvc3.setType(Constant.ADD);
        this.tempResult.add(localDvc3);
        continue;
      }
      if (i != this.room.getDvcVos().size())
        continue;
      Dvc localDvc2 = new Dvc();
      localDvc2.setName("添加");
      localDvc2.setType(Constant.ADD);
      this.tempResult.add(localDvc2);
    }
    this.tvTitleViewTitle.setText(2131100183);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100148);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtAddShortCuts.this.finish();
      }
    });
  }

  private void initValue()
  {
    this.adapter = new FtAddShortCutsAdapter(getActivity(), this.tempResult);
    this.shortcuts_list.setAdapter(this.adapter);
  }

  private void initView()
  {
    this.shortcuts_list = ((SwipeMenuListView)this.view.findViewById(2131559204));
    this.shortcuts_list.setDividerHeight(10);
    this.shortcuts_list.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem = new SwipeMenuItem(FtAddShortCuts.this.getActivity().getApplication());
        localSwipeMenuItem.setBackground(new ColorDrawable(FtAddShortCuts.this.getResources().getColor(2131492896)));
        localSwipeMenuItem.setWidth(150);
        localSwipeMenuItem.setBackground(2130903193);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem);
      }
    });
    this.shortcuts_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (paramInt == FtAddShortCuts.this.tempResult.size())
          Log.i("", "");
      }
    });
    this.shortcuts_list.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        FtAddShortCuts.this.tempResult.remove(paramInt1);
        FtAddShortCuts.this.result.remove(paramInt1);
        FtAddShortCuts.this.room.setDvcVos(FtAddShortCuts.this.result);
        FtAddShortCuts.this.shortCutInner.putData4ClassName(FtAddShortCuts.this.mac, FtAddShortCuts.this.home);
        FtAddShortCuts.this.adapter.notifyDataSetChanged();
        paramSwipeMenu.getMenuItem(paramInt1).getTitle();
        return false;
      }
    });
  }

  public void delShortCus(View paramView)
  {
    ((Integer)paramView.getTag()).intValue();
    Log.i("", "");
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968762, null);
      ButterKnife.bind(this, this.view);
    }
    this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
    this.shortCutInner = new ShortCutInner(getActivity());
    init();
    initView();
    initValue();
    setSlideable(false);
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public class ShortCutInner extends MyBusiness
  {
    public ShortCutInner(Context arg2)
    {
      super();
      FtAddShortCuts.access$402(FtAddShortCuts.this, (Home)getBean4ClassName(this.mac, Home.class));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.FtAddShortCuts
 * JD-Core Version:    0.6.0
 */