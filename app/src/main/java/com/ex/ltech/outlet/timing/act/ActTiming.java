package com.ex.ltech.outlet.timing.act;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.led.vo.TimingVo;
import com.ex.ltech.outlet.timing.TimingBussines;
import com.ex.ltech.outlet.timing.TimingBussines.SendCmdListener;
import com.ex.ltech.outlet.timing.TimingListAdapter;
import com.ex.ltech.outlet.timing.TimingListAdapter.OnListVSwichChangeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActTiming extends MyBaseActivity
  implements TimingListAdapter.OnListVSwichChangeListener
{
  Runnable GetDeviceTimingRunnable = new Runnable()
  {
    public void run()
    {
      if (ActTiming.this.getTimeDialog.isShowing())
        ActTiming.this.bussines.getDeviceTiming();
    }
  };
  Runnable SynTime2DeviceRunnable = new Runnable()
  {
    public void run()
    {
      ActTiming.this.bussines.synTime2Device();
    }
  };
  private TimingListAdapter adt;
  private TimingBussines bussines;
  boolean checkJustOnce;
  ProgressDialog getTimeDialog;
  Handler handler = new Handler()
  {
  };
  private int itemPosition = -1;
  List<TimingVo> justOnceVos = new ArrayList();
  private SwipeMenuListView lv_act_outlet_timing;
  List<Integer> tempPosi = new ArrayList();
  private com.ex.ltech.outlet.timing.TimingData timingData;
  List<TimingVo> timingVos;
  private List<TimingVo> vos;

  private void findView()
  {
    this.lv_act_outlet_timing = ((SwipeMenuListView)findViewById(2131558603));
    this.lv_act_outlet_timing.setMenuCreator(new SwipeMenuCreator()
    {
      public void create(SwipeMenu paramSwipeMenu)
      {
        SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem1.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(2131492927)));
        localSwipeMenuItem1.setWidth(ActTiming.this.bussines.dp2px(60));
        localSwipeMenuItem1.setIcon(2130903626);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem2.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(2131492964)));
        localSwipeMenuItem2.setWidth(ActTiming.this.bussines.dp2px(70));
        localSwipeMenuItem2.setIcon(2130903625);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
      }
    });
  }

  private void init()
  {
    this.getTimeDialog = ProgressDialog.show(this, "", getString(2131100077), false);
    this.getTimeDialog.setCancelable(true);
    this.getTimeDialog.show();
    this.vos = new ArrayList();
    this.timingData = com.ex.ltech.outlet.timing.TimingData.getInstance(this);
    this.bussines = new TimingBussines(this);
    this.bussines.prepareLink();
    this.bussines.setActTiming(this);
    this.adt = new TimingListAdapter(this, this.vos);
    this.lv_act_outlet_timing.setAdapter(this.adt);
    setListTotgleBtnListenerDelay();
    this.bussines.getDeviceTiming();
    this.handler.postDelayed(this.GetDeviceTimingRunnable, 5000L);
    this.handler.postDelayed(this.SynTime2DeviceRunnable, 10000L);
  }

  private void openCheckJustOnce()
  {
    this.timingVos = com.ex.ltech.outlet.timing.TimingData.getInstance(this).getTimingVos4Sd();
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
          if (System.currentTimeMillis() <= ((TimingVo)ActTiming.this.justOnceVos.get(i)).getOutletLatelyTime())
            continue;
          ActTiming.this.timingVos.remove(j);
          TimingVo localTimingVo = (TimingVo)ActTiming.this.justOnceVos.get(i);
          localTimingVo.setSwich(false);
          ActTiming.this.timingVos.add(j, localTimingVo);
          com.ex.ltech.outlet.timing.TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.timingVos);
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
          }
        });
      }
    }
    .start();
  }

  private void setListTotgleBtnListenerDelay()
  {
    this.adt.setOnListVSwichChangeListener(null);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActTiming.this.adt.setOnListVSwichChangeListener(ActTiming.this);
      }
    }
    , 700L);
  }

  private void setListener()
  {
    this.lv_act_outlet_timing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
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
          ActTiming.access$702(ActTiming.this, paramInt1);
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
          return false;
        case 1:
        }
        ProgressDialog localProgressDialog = ProgressDialog.show(ActTiming.this, "", ActTiming.this.getString(2131100075), false);
        localProgressDialog.setCancelable(true);
        localProgressDialog.show();
        ActTiming.this.bussines.delTimingItem(ActTiming.this.vos, paramInt1);
        ActTiming.this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt1, localProgressDialog)
        {
          public void onSendFailde()
          {
            ActTiming.this.toast(2131099854);
            this.val$dialog.dismiss();
            ActTiming.this.bussines.setSendCmdListener(null);
          }

          public void onSendOk()
          {
            ActTiming.this.vos.remove(this.val$position);
            com.ex.ltech.led.acti.timing.TimingData.getInstance(ActTiming.this.getApplicationContext()).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            ActTiming.this.adt.notifyDataSetChanged();
            this.val$dialog.dismiss();
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
    String str = UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, "");
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(2131100433);
    setEditImageRes(2130903622);
    setDeviceTextRes(str);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      if (!this.bussines.isCreateNewTime)
        break label86;
      this.bussines.sendTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition, true);
    }
    while (true)
    {
      ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100432), false);
      localProgressDialog.setCancelable(true);
      localProgressDialog.show();
      this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(localProgressDialog)
      {
        public void onSendFailde()
        {
          ActTiming.this.toast(2131099854);
          this.val$dialog.dismiss();
        }

        public void onSendOk()
        {
          if (ActTiming.this.bussines.isCreateNewTime)
            ActTiming.this.vos.add(ActTiming.this.bussines.getCacheTimingVo4Sd());
          while (true)
          {
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            com.ex.ltech.outlet.timing.TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.adt.notifyDataSetChanged();
            this.val$dialog.dismiss();
            ActTiming.this.toast(2131100431);
            ActTiming.this.bussines.setSendCmdListener(null);
            return;
            ActTiming.this.vos.remove(ActTiming.this.itemPosition);
            ActTiming.this.vos.add(ActTiming.this.itemPosition, ActTiming.this.bussines.getCacheTimingVo4Sd());
          }
        }
      });
      return;
      label86: this.bussines.sendEditTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968612);
    setTitleView();
    findView();
    init();
    setListener();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.handler.removeCallbacks(this.GetDeviceTimingRunnable);
    this.handler.removeCallbacks(this.SynTime2DeviceRunnable);
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.adt.getCount() < 10)
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
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(2131100075), false);
    System.out.println("onListVSwichChange        " + paramInt);
    TimingVo localTimingVo = (TimingVo)this.vos.get(paramInt);
    localTimingVo.setSwich(paramBoolean);
    localProgressDialog.setCancelable(true);
    this.bussines.sendTiming(localTimingVo, paramInt, false);
    this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt, paramBoolean, localProgressDialog)
    {
      public void onSendFailde()
      {
        TimingVo localTimingVo = (TimingVo)ActTiming.this.vos.get(this.val$posi);
        if (!this.val$on);
        for (boolean bool = true; ; bool = false)
        {
          localTimingVo.setSwich(bool);
          ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
          ActTiming.this.adt.notifyDataSetChanged();
          ActTiming.this.toast(2131099854);
          this.val$dialog.dismiss();
          return;
        }
      }

      public void onSendOk()
      {
        ((TimingVo)ActTiming.this.vos.get(this.val$posi)).setSwich(this.val$on);
        ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
        this.val$dialog.dismiss();
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
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void sendTimeOut()
  {
    Toast.makeText(this, getString(2131099854), 0).show();
    if (this.getTimeDialog.isShowing())
      this.getTimeDialog.dismiss();
  }

  public void upDateData()
  {
    this.vos.clear();
    this.vos.addAll(this.timingData.getTimingVos4Sd());
    this.bussines.reSortTimingData(this.vos);
    this.adt.notifyDataSetChanged();
    if (this.getTimeDialog.isShowing())
      this.getTimeDialog.dismiss();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.timing.act.ActTiming
 * JD-Core Version:    0.6.0
 */