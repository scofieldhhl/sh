package com.ex.ltech.led.acti.timing.newRgb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.acti.timing.act.ActAddTiming;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.TimingVo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActNewRgbTiming extends MyBaseActivity
  implements NewTimingListAdapter.OnListVSwichChangeListener
{
  private NewTimingListAdapter adt;
  private NewRgbTimingBussines bussines;
  boolean checkJustOnce;
  ProgressDialog getTimeDialog;
  Handler handler = new Handler()
  {
  };
  boolean isActivityResult;
  private int itemPosition = -1;
  List<TimingVo> justOnceVos = new ArrayList();
  private SwipeMenuListView lv_act_timing;
  List<Integer> tempPosi = new ArrayList();
  private TimingData timingData;
  List<TimingVo> timingVos;
  private List<TimingVo> vos;

  private void findView()
  {
    this.lv_act_timing = ((SwipeMenuListView)findViewById(2131558611));
    this.lv_act_timing.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(ActNewRgbTiming.this.getApplicationContext());
        localSwipeMenuItem1.setBackground(new ColorDrawable(Color.rgb(201, 201, 206)));
        localSwipeMenuItem1.setWidth(ActNewRgbTiming.this.bussines.dp2px(60));
        localSwipeMenuItem1.setIcon(2130903303);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActNewRgbTiming.this.getApplicationContext());
        localSwipeMenuItem2.setBackground(new ColorDrawable(ActNewRgbTiming.this.getResources().getColor(2131492896)));
        localSwipeMenuItem2.setWidth(ActNewRgbTiming.this.bussines.dp2px(70));
        localSwipeMenuItem2.setIcon(2130903138);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
      }
    });
  }

  private void init()
  {
    this.timingData = TimingData.getInstance(this);
    this.bussines = new NewRgbTimingBussines(this);
    this.bussines.prepareLink();
    this.bussines.setActNewRgbTiming(this);
    this.vos = this.timingData.getTimingVos4Sd();
    this.adt = new NewTimingListAdapter(this, this.vos);
    this.lv_act_timing.setAdapter(this.adt);
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
        for (int i = 0; i < ActNewRgbTiming.this.justOnceVos.size(); i++)
        {
          int j = ((Integer)ActNewRgbTiming.this.tempPosi.get(i)).intValue();
          if (System.currentTimeMillis() <= ((TimingVo)ActNewRgbTiming.this.justOnceVos.get(i)).getJustOnceCurrentTime())
            continue;
          ActNewRgbTiming.this.timingVos.remove(j);
          TimingVo localTimingVo = (TimingVo)ActNewRgbTiming.this.justOnceVos.get(i);
          localTimingVo.setSwich(false);
          ActNewRgbTiming.this.timingVos.add(j, localTimingVo);
          TimingData.getInstance(ActNewRgbTiming.this).saveTimingVos2Sd(ActNewRgbTiming.this.timingVos);
          if (ActNewRgbTiming.this.justOnceVos.size() == 1)
            ActNewRgbTiming.this.checkJustOnce = false;
          ActNewRgbTiming.this.justOnceVos.remove(i);
        }
        ActNewRgbTiming.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            ActNewRgbTiming.this.vos.clear();
            ActNewRgbTiming.this.vos.addAll(ActNewRgbTiming.this.timingData.getTimingVos4Sd());
            ActNewRgbTiming.this.adt.notifyDataSetChanged();
          }
        });
      }
    }
    .start();
  }

  private void setListener()
  {
    this.lv_act_timing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        switch (paramInt2)
        {
        default:
          return false;
        case 0:
          if (((TimingVo)ActNewRgbTiming.this.vos.get(paramInt1)).isOther())
          {
            ActNewRgbTiming.this.toast(R.string.no_edit_other);
            return false;
          }
          ActNewRgbTiming.this.bussines.isCreateNewTime = false;
          ActNewRgbTiming.access$802(ActNewRgbTiming.this, paramInt1);
          ActNewRgbTiming.this.handler.postDelayed(new Runnable(paramInt1)
          {
            public void run()
            {
              Intent localIntent = new Intent(ActNewRgbTiming.this, ActAddTiming.class);
              localIntent.putExtra("itemPosi", this.val$position);
              ActNewRgbTiming.this.startActivityForResult(localIntent, 100);
            }
          }
          , 200L);
          return false;
        case 1:
        }
        ActNewRgbTiming.this.bussines.delTimingItem(ActNewRgbTiming.this.vos, paramInt1);
        ProgressDialog localProgressDialog = ProgressDialog.show(ActNewRgbTiming.this, "", ActNewRgbTiming.this.getString(2131100432), true);
        localProgressDialog.setCancelable(true);
        localProgressDialog.show();
        ActNewRgbTiming.this.bussines.setSendCmdListener(new NewRgbTimingBussines.SendCmdListener(paramInt1, localProgressDialog)
        {
          public void onSendFailde()
          {
            ActNewRgbTiming.this.toast(R.string.add_time_no_ok);
            this.val$dialog.dismiss();
          }

          public void onSendOk()
          {
            ActNewRgbTiming.this.vos.remove(this.val$position);
            TimingData.getInstance(ActNewRgbTiming.this.getApplicationContext()).saveTimingVos2Sd(ActNewRgbTiming.this.vos);
            ActNewRgbTiming.this.bussines.reSortTimingData(ActNewRgbTiming.this.vos);
            ActNewRgbTiming.this.adt.notifyDataSetChanged();
            this.val$dialog.dismiss();
            ActNewRgbTiming.this.toast(R.string.time_ctrl_ok);
            ActNewRgbTiming.this.bussines.setSendCmdListener(null);
          }

          public void onTimingFull()
          {
          }
        });
        return false;
      }
    });
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.device_ic);
    setTiTleTextRes(2131100433);
    setEditImageRes(2130903589);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.isActivityResult = true;
    if (paramInt2 == -1)
    {
      if (!this.bussines.isCreateNewTime)
        break label90;
      this.bussines.sendTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition, true);
    }
    while (true)
    {
      ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100432), true);
      localProgressDialog.setCancelable(true);
      localProgressDialog.show();
      this.bussines.setSendCmdListener(new NewRgbTimingBussines.SendCmdListener(localProgressDialog)
      {
        public void onSendFailde()
        {
          ActNewRgbTiming.this.toast(R.string.add_time_no_ok);
          this.val$dialog.dismiss();
        }

        public void onSendOk()
        {
          if (ActNewRgbTiming.this.bussines.isCreateNewTime)
            ActNewRgbTiming.this.vos.add(ActNewRgbTiming.this.bussines.getCacheTimingVo4Sd());
          while (true)
          {
            ActNewRgbTiming.this.bussines.reSortTimingData(ActNewRgbTiming.this.vos);
            TimingData.getInstance(ActNewRgbTiming.this).saveTimingVos2Sd(ActNewRgbTiming.this.vos);
            ActNewRgbTiming.this.adt.notifyDataSetChanged();
            this.val$dialog.dismiss();
            ActNewRgbTiming.this.toast(R.string.time_ctrl_ok);
            ActNewRgbTiming.this.bussines.setSendCmdListener(null);
            return;
            ActNewRgbTiming.this.vos.remove(ActNewRgbTiming.this.itemPosition);
            ActNewRgbTiming.this.vos.add(ActNewRgbTiming.this.itemPosition, ActNewRgbTiming.this.bussines.getCacheTimingVo4Sd());
          }
        }

        public void onTimingFull()
        {
          ActNewRgbTiming.this.toast(R.string.ten_timing);
          this.val$dialog.dismiss();
        }
      });
      return;
      label90: this.bussines.sendTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition, true);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968623);
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
      goAct4result(ActAddTiming.class, 100);
      this.itemPosition = this.adt.getCount();
      this.bussines.isCreateNewTime = true;
      return;
    }
    toast(R.string.ten_timing);
  }

  public void onListVSwichChange(boolean paramBoolean, int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100432), true);
    localProgressDialog.setCancelable(true);
    System.out.println("onListVSwichChange        " + paramInt);
    TimingVo localTimingVo = (TimingVo)this.vos.get(paramInt);
    localTimingVo.setSwich(paramBoolean);
    this.bussines.sendTiming(localTimingVo, paramInt, false);
    this.bussines.setSendCmdListener(new NewRgbTimingBussines.SendCmdListener(paramInt, paramBoolean, localProgressDialog)
    {
      public void onSendFailde()
      {
        TimingVo localTimingVo = (TimingVo)ActNewRgbTiming.this.vos.get(this.val$posi);
        if (!this.val$on);
        for (boolean bool = true; ; bool = false)
        {
          localTimingVo.setSwich(bool);
          ActNewRgbTiming.this.timingData.saveTimingVos2Sd(ActNewRgbTiming.this.vos);
          ActNewRgbTiming.this.adt.notifyDataSetChanged();
          ActNewRgbTiming.this.toast(R.string.add_time_no_ok);
          this.val$dialog.dismiss();
          return;
        }
      }

      public void onSendOk()
      {
        ((TimingVo)ActNewRgbTiming.this.vos.get(this.val$posi)).setSwich(this.val$on);
        ActNewRgbTiming.this.timingData.saveTimingVos2Sd(ActNewRgbTiming.this.vos);
        this.val$dialog.dismiss();
        ActNewRgbTiming.this.toast(R.string.time_ctrl_ok);
        ActNewRgbTiming.this.bussines.setSendCmdListener(null);
      }

      public void onTimingFull()
      {
      }
    });
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
    if (!this.isActivityResult)
    {
      this.bussines.synTime2Device();
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          ActNewRgbTiming.this.getTimeDialog = ProgressDialog.show(ActNewRgbTiming.this, "", ActNewRgbTiming.this.getString(2131100077), false);
          ActNewRgbTiming.this.getTimeDialog.setCancelable(true);
          ActNewRgbTiming.this.getTimeDialog.show();
          ActNewRgbTiming.this.bussines.getDeviceTiming();
          ActNewRgbTiming.this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              ActNewRgbTiming.this.bussines.getDeviceTiming();
            }
          }
          , 200L);
          ActNewRgbTiming.this.bussines.setSendCmdListener(new NewRgbTimingBussines.SendCmdListener()
          {
            public void onSendFailde()
            {
              ActNewRgbTiming.this.toast(R.string.add_time_no_ok);
              ActNewRgbTiming.this.getTimeDialog.dismiss();
            }

            public void onSendOk()
            {
            }

            public void onTimingFull()
            {
            }
          });
        }
      }
      , 200L);
    }
    this.isActivityResult = false;
    openCheckJustOnce();
  }

  public void upDateData()
  {
    this.vos.clear();
    this.vos.addAll(this.timingData.getTimingVos4Sd());
    this.bussines.reSortTimingData(this.vos);
    TimingData.getInstance(this).saveTimingVos2Sd(this.vos);
    this.adt.notifyDataSetChanged();
    if (this.getTimeDialog.isShowing())
      this.getTimeDialog.dismiss();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.newRgb.ActNewRgbTiming
 * JD-Core Version:    0.6.0
 */