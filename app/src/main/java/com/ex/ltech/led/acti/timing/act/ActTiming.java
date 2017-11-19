package com.ex.ltech.led.acti.timing.act;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.timing.TimingBussines;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.acti.timing.TimingListAdapter;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.vo.TimingVo;

import java.util.ArrayList;
import java.util.List;

public class ActTiming extends MyBaseActivity
  implements TimingListAdapter.OnListVSwichChangeListener
{
  private TimingListAdapter adt;
  private TimingBussines bussines;
  boolean checkJustOnce;
  Handler handler = new Handler()
  {
  };
  private int itemPosition = -1;
  List<TimingVo> justOnceVos = new ArrayList();
  private SwipeMenuListView lv_act_timing;
  List<Integer> tempPosi = new ArrayList();
  private TimingData timingData;
  List<TimingVo> timingVos;
  private List<TimingVo> vos;

  private void findView()
  {
    this.lv_act_timing = ((SwipeMenuListView)findViewById(R.id.lv_act_timing));
    this.lv_act_timing.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem1.setBackground(new ColorDrawable(Color.rgb(201, 201, 206)));
        localSwipeMenuItem1.setWidth(ActTiming.this.bussines.dp2px(60));
//        localSwipeMenuItem1.setIcon(2130903303);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem2.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(R.color.color5)));
        localSwipeMenuItem2.setWidth(ActTiming.this.bussines.dp2px(70));
        localSwipeMenuItem2.setIcon(2130903138);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
      }
    });
  }

  private void init()
  {
    this.timingData = TimingData.getInstance(this);
    this.bussines = new TimingBussines(this);
    this.bussines.prepareLink();
    this.bussines.setActTiming(this);
    this.vos = this.timingData.getTimingVos4Sd();
    this.adt = new TimingListAdapter(this, this.vos);
    this.lv_act_timing.setAdapter(this.adt);
    setListTotgleBtnListenerDelay();
    this.bussines.synTime2Device();
    this.adt.setOnListVSwichChangeListener(this);
    this.lv_act_timing.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
      {
        if (paramInt != 0)
          return;
        System.out.println("adt.isLvScroll = false;");
      }
    });
  }

  private void openCheckJustOnce()
  {
    this.timingVos = TimingData.getInstance(this).getTimingVos4Sd();
    this.justOnceVos.clear();
    this.tempPosi.clear();
    this.checkJustOnce = true;
    for (int i = 0; i < this.timingVos.size(); i++)
    {
      if ((!((TimingVo)this.timingVos.get(i)).isJustOnce()) || (!((TimingVo)this.timingVos.get(i)).isSwich()))
        continue;
      this.justOnceVos.add(this.timingVos.get(i));
      this.tempPosi.add(Integer.valueOf(i));
    }
    if (this.justOnceVos.size() == 0)
      return;
    new Thread()
    {
      public void run()
      {
        for (int i = 0; i < ActTiming.this.justOnceVos.size(); i++)
        {
          int j = ((Integer)ActTiming.this.tempPosi.get(i)).intValue();
          if (System.currentTimeMillis() <= ((TimingVo)ActTiming.this.justOnceVos.get(i)).getJustOnceCurrentTime())
            continue;
          ActTiming.this.timingVos.remove(j);
          TimingVo localTimingVo = (TimingVo)ActTiming.this.justOnceVos.get(i);
          localTimingVo.setSwich(false);
          ActTiming.this.timingVos.add(j, localTimingVo);
          TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.timingVos);
          if (ActTiming.this.justOnceVos.size() == 1)
            ActTiming.this.checkJustOnce = false;
          ActTiming.this.justOnceVos.remove(i);
        }
        ActTiming.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            ActTiming.this.upDateData();
            ActTiming.this.adt.notifyDataSetChanged();
            ActTiming.this.setListTotgleBtnListenerDelay();
          }
        });
      }
    }
    .start();
  }

  private void setListTotgleBtnListenerDelay()
  {
  }

  private void setListener()
  {
    this.lv_act_timing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        /*switch (paramInt2)
        {
        default:
        case 0:
        case 1:
        }
        while (true)
        {
          return false;
          ActTiming.access$102(ActTiming.this, paramInt1);
          ActTiming.this.handler.postDelayed(new Runnable(paramInt1)
          {
            public void run()
            {
              Intent localIntent = new Intent(ActTiming.this, ActAddTiming.class);
              localIntent.putExtra("itemPosi", this.val$position);
              ActTiming.this.startActivityForResult(localIntent, 100);
            }
          }
          , 200L);
          continue;
          ActTiming.this.bussines.delTimingItem(ActTiming.this.vos, paramInt1);
          ActTiming.this.adt.notifyDataSetChanged();
          ActTiming.this.setListTotgleBtnListenerDelay();
        }*/
        return true;
      }
    });
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.device_ic);
    setTiTleTextRes(R.string.timing);
    setEditImageRes(R.mipmap.new_add);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    /*if (paramInt2 == -1)
    {
      this.vos.clear();
      this.vos.addAll(this.timingData.getTimingVos4Sd());
      if (this.itemPosition != -1)
        break label96;
      this.bussines.sendTiming((TimingVo)this.vos.get(-1 + this.vos.size()), -1 + this.vos.size(), true);
    }
    while (true)
    {
      this.adt.notifyDataSetChanged();
      return;
      label96: this.bussines.sendTiming((TimingVo)this.vos.get(this.itemPosition), this.itemPosition, false);
    }*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_timing);
    setTitleView();
    findView();
    init();
    setListener();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.adt.getCount() < 10)
    {
//      goAct4result(ActAddTiming.class, 100);
      this.itemPosition = -1;
      return;
    }
    toast(R.string.ten_timing);
  }

  public void onListVSwichChange(boolean paramBoolean, int paramInt)
  {
    System.out.println("onListVSwichChange        " + paramInt);
    TimingVo localTimingVo = (TimingVo)this.vos.get(paramInt);
    localTimingVo.setSwich(paramBoolean);
    this.bussines.sendTiming(localTimingVo, paramInt, false);
    this.vos.remove(paramInt);
    this.vos.add(paramInt, localTimingVo);
    this.timingData.saveTimingVos2Sd(this.vos);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    this.bussines.removeXlinkListener();
  }

  protected void onResume()
  {
    super.onResume();
    this.bussines.synTime2Device();
    openCheckJustOnce();
  }

  public void upDateData()
  {
    this.vos.clear();
    this.vos.addAll(this.timingData.getTimingVos4Sd());
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.act.ActTiming
 * JD-Core Version:    0.6.0
 */