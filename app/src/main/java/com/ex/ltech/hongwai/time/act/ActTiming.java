package com.ex.ltech.hongwai.time.act;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import com.ex.ltech.hongwai.time.TimingBussines;
import com.ex.ltech.hongwai.time.TimingBussines.SendCmdListener;
import com.ex.ltech.hongwai.time.TimingData;
import com.ex.ltech.hongwai.time.TimingListAdapter;
import com.ex.ltech.hongwai.time.TimingListAdapter.OnListVSwichChangeListener;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.TimingVo;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActTiming extends MyBaseActivity
  implements TimingListAdapter.OnListVSwichChangeListener
{
  private TimingListAdapter adt;
  private TimingBussines bussines;
  TimingVo cacheTimingVo;
  boolean checkJustOnce;
  ProgressDialog dialog;
  ProgressDialog getTimeDialog;
  Handler handler = new Handler()
  {
  };
  boolean isActivityResult;
  private int itemPosition = -1;
  List<TimingVo> justOnceVos = new ArrayList();
  Runnable loopCheckJustOnceTime = new Runnable()
  {
    public void run()
    {
      ActTiming.this.openCheckJustOnce();
    }
  };
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
        SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem1.setBackground(new ColorDrawable(Color.rgb(201, 201, 206)));
        localSwipeMenuItem1.setWidth(ActTiming.this.bussines.dp2px(60));
        localSwipeMenuItem1.setHeight(ActTiming.this.bussines.dp2px(85));
        localSwipeMenuItem1.setIcon(2130903553);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem2.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(2131492897)));
        localSwipeMenuItem2.setWidth(ActTiming.this.bussines.dp2px(60));
        localSwipeMenuItem2.setHeight(ActTiming.this.bussines.dp2px(85));
        localSwipeMenuItem2.setIcon(2130903532);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
      }
    });
  }

  private void init()
  {
    this.vos = new ArrayList();
    this.timingData = TimingData.getInstance(this);
    this.bussines = new TimingBussines(this);
    this.bussines.prepareLink();
    this.bussines.setActTiming(this);
    this.adt = new TimingListAdapter(this, this.vos);
    this.adt.setOnListVSwichChangeListener(this);
    this.lv_act_timing.setAdapter(this.adt);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
      }
    }
    , 1000L);
  }

  private void openCheckJustOnce()
  {
    this.handler.postDelayed(this.loopCheckJustOnceTime, 10000L);
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
      long testtime;

      public void run()
      {
        for (int i = 0; i < ActTiming.this.justOnceVos.size(); i++)
        {
          int j = ((Integer)ActTiming.this.tempPosi.get(i)).intValue();
          long l = System.currentTimeMillis();
          this.testtime = l;
          if (l <= ((TimingVo)ActTiming.this.justOnceVos.get(i)).getJustOnceCurrentTime())
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
        switch (paramInt2)
        {
        default:
          return false;
        case 0:
          if (((TimingVo)ActTiming.this.vos.get(paramInt1)).isOther())
          {
            ActTiming.this.toast(2131100210);
            return false;
          }
          ActTiming.this.bussines.isCreateNewTime = false;
          ActTiming.access$902(ActTiming.this, paramInt1);
          ActTiming.this.handler.postDelayed(new Runnable(paramInt1)
          {
            public void run()
            {
              Intent localIntent = new Intent(ActTiming.this, ActAddTiming.class);
              localIntent.putExtra("itemPosi", this.val$position);
              localIntent.putExtra("voData", new Gson().toJson(ActTiming.this.vos.get(this.val$position)));
              ActTiming.this.startActivityForResult(localIntent, 100);
            }
          }
          , 200L);
          return false;
        case 1:
        }
        ActTiming.this.bussines.delTimingItem(ActTiming.this.vos, paramInt1);
        ActTiming.this.dialog = ProgressDialog.show(ActTiming.this, "", ActTiming.this.getString(2131100432), false);
        ActTiming.this.dialog.setCancelable(true);
        ActTiming.this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt1)
        {
          public void onSendFailde()
          {
            ActTiming.this.toast(2131099854);
            ActTiming.this.dialog.dismiss();
          }

          public void onSendOk()
          {
            ActTiming.this.vos.remove(this.val$position);
            TimingData.getInstance(ActTiming.this.getApplicationContext()).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            ActTiming.this.setListTotgleBtnListenerDelay();
            ActTiming.this.adt.notifyDataSetChanged();
            ActTiming.this.dialog.dismiss();
            ActTiming.this.toast(2131100431);
            ActTiming.this.bussines.setSendCmdListener(null);
          }
        });
        return false;
      }
    });
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100433);
    setBgWhite();
    setEditImageRes(2130903273);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.isActivityResult = true;
    TimingVo localTimingVo2;
    if (paramInt2 == -1)
    {
      if (!this.bussines.isCreateNewTime)
        break label168;
      localTimingVo2 = this.bussines.getCacheTimingVo4Sd();
      if (localTimingVo2.getRcVo().getmType() >= 10)
        break label158;
      this.bussines.sendRcParams(localTimingVo2, 255);
    }
    while (true)
    {
      this.cacheTimingVo = localTimingVo2;
      if (localTimingVo2.getXuHao() == 20)
      {
        toast(2131100114);
        this.bussines.getDeviceTiming();
      }
      if (localTimingVo2.getXuHao() == 21)
      {
        toast(2131100111);
        this.bussines.getDeviceTiming();
      }
      this.dialog = ProgressDialog.show(this, "", getString(2131100432), false);
      this.dialog.setCancelable(true);
      this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener()
      {
        public void onSendFailde()
        {
          ActTiming.this.toast(2131099854);
          ActTiming.this.dialog.dismiss();
        }

        public void onSendOk()
        {
          ActTiming.this.bussines.setSendCmdListener(null);
          if (ActTiming.this.bussines.isCreateNewTime)
            ActTiming.this.vos.add(ActTiming.this.bussines.getCacheTimingVo4Sd());
          while (true)
          {
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.adt.notifyDataSetChanged();
            ActTiming.this.setListTotgleBtnListenerDelay();
            ActTiming.this.dialog.dismiss();
            ActTiming.this.toast(2131099855);
            return;
            ActTiming.this.vos.remove(ActTiming.this.itemPosition);
            ActTiming.this.vos.add(ActTiming.this.itemPosition, ActTiming.this.bussines.getCacheTimingVo4Sd());
          }
        }
      });
      return;
      label158: this.bussines.queryTimingOrder();
    }
    label168: TimingVo localTimingVo1 = this.bussines.getCacheTimingVo4Sd();
    if (localTimingVo1.getRcVo().getmType() < 10)
      this.bussines.sendRcParams(localTimingVo1, localTimingVo1.getXuHao());
    while (true)
    {
      this.cacheTimingVo = localTimingVo1;
      if (localTimingVo1.getXuHao() == 20)
      {
        toast(2131100114);
        this.bussines.getDeviceTiming();
      }
      if (localTimingVo1.getXuHao() != 21)
        break;
      toast(2131100111);
      this.bussines.getDeviceTiming();
      break;
      this.bussines.sendEditTiming(localTimingVo1, this.itemPosition);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968616);
    setTitleView();
    findView();
    init();
    setListener();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.adt.getCount() < 20)
    {
      this.bussines.isCreateNewTime = true;
      goAct4result(ActAddTiming.class, 100);
      this.itemPosition = -1;
      return;
    }
    toast(2131100429);
  }

  public void onListVSwichChange(boolean paramBoolean, int paramInt)
  {
    this.dialog = ProgressDialog.show(this, "", getString(2131100432), false);
    this.dialog.setCancelable(true);
    System.out.println("onListVSwichChange        " + paramInt);
    TimingVo localTimingVo = (TimingVo)this.vos.get(paramInt);
    localTimingVo.setSwich(paramBoolean);
    this.bussines.sendTiming(localTimingVo, paramInt, false);
    this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt, paramBoolean)
    {
      public void onSendFailde()
      {
        TimingVo localTimingVo = (TimingVo)ActTiming.this.vos.get(this.val$posi);
        if (!this.val$on);
        for (boolean bool = true; ; bool = false)
        {
          localTimingVo.setSwich(bool);
          ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
          ActTiming.this.setListTotgleBtnListenerDelay();
          ActTiming.this.adt.notifyDataSetChanged();
          ActTiming.this.toast(2131099854);
          ActTiming.this.dialog.dismiss();
          return;
        }
      }

      public void onSendOk()
      {
        ((TimingVo)ActTiming.this.vos.get(this.val$posi)).setSwich(this.val$on);
        ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
        ActTiming.this.dialog.dismiss();
        ActTiming.this.toast(2131100431);
        ActTiming.this.bussines.setSendCmdListener(null);
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
    this.handler.removeCallbacks(this.loopCheckJustOnceTime);
  }

  protected void onResume()
  {
    super.onResume();
    this.handler.post(this.loopCheckJustOnceTime);
    if (!this.isActivityResult)
    {
      this.getTimeDialog = ProgressDialog.show(this, "", getString(2131100077), false);
      this.getTimeDialog.setCancelable(true);
      this.getTimeDialog.show();
      this.bussines.getDeviceTiming();
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          ActTiming.this.bussines.synTime2Device();
          if (ActTiming.this.getTimeDialog.isShowing())
            ActTiming.this.getTimeDialog.dismiss();
        }
      }
      , 5000L);
    }
    this.isActivityResult = false;
    this.bussines.initRcData();
  }

  public void sendRcCode(int paramInt)
  {
    if (paramInt == 20)
    {
      toast(2131100114);
      this.dialog.dismiss();
      this.bussines.setSendCmdListener(null);
      return;
    }
    if (paramInt == 21)
    {
      toast(2131100111);
      this.dialog.dismiss();
      this.bussines.setSendCmdListener(null);
      return;
    }
    if (paramInt < 20)
    {
      this.cacheTimingVo.setXuHao(paramInt);
      if (this.bussines.isCreateNewTime)
      {
        this.bussines.sendTiming(this.cacheTimingVo, this.itemPosition, true);
        return;
      }
      this.bussines.sendEditTiming(this.cacheTimingVo, this.itemPosition);
      return;
    }
    toast(2131100429);
    this.bussines.getDeviceTiming();
  }

  public void upDateData()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        ActTiming.this.vos.clear();
        ActTiming.this.vos.addAll(ActTiming.this.timingData.getTimingVos4Sd());
        ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
        ActTiming.this.setListTotgleBtnListenerDelay();
        ActTiming.this.adt.notifyDataSetChanged();
        if (ActTiming.this.getTimeDialog.isShowing())
          ActTiming.this.getTimeDialog.dismiss();
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.time.act.ActTiming
 * JD-Core Version:    0.6.0
 */