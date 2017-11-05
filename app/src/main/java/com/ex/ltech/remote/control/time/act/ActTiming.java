package com.ex.ltech.remote.control.time.act;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView.OnMenuItemClickListener;
import com.ex.ltech.remote.control.time.TimingBussines;
import com.ex.ltech.remote.control.time.TimingBussines.SendCmdListener;
import com.ex.ltech.remote.control.time.TimingData;
import com.ex.ltech.remote.control.time.TimingListAdapter;
import com.ex.ltech.remote.control.time.TimingListAdapter.OnListVSwichChangeListener;
import com.ex.ltech.remote.control.vo.YaokongTimingVo;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActTiming extends MyBaseActivity
  implements TimingListAdapter.OnListVSwichChangeListener
{
  List<YaokongTimingVo> YaokongTimingVos;
  private TimingListAdapter adt;
  private TimingBussines bussines;
  boolean checkJustOnce;
  ProgressDialog getTimeDialog;
  Handler handler = new Handler()
  {
  };
  private int itemPosition = -1;
  List<YaokongTimingVo> justOnceVos = new ArrayList();
  private SwipeMenuListView lv_act_timing;
  List<Integer> tempPosi = new ArrayList();
  private TimingData timingData;
  private List<YaokongTimingVo> vos;

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
        localSwipeMenuItem1.setIcon(2130903303);
        localSwipeMenuItem1.setTitleColor(-1);
        paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
        SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(ActTiming.this.getApplicationContext());
        localSwipeMenuItem2.setBackground(new ColorDrawable(ActTiming.this.getResources().getColor(2131492897)));
        localSwipeMenuItem2.setWidth(ActTiming.this.bussines.dp2px(70));
        localSwipeMenuItem2.setIcon(2130903138);
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
    setListTotgleBtnListenerDelay();
    this.lv_act_timing.setAdapter(this.adt);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActTiming.this.bussines.synTime2Device();
      }
    }
    , 1000L);
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
    this.lv_act_timing.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener()
    {
      public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2)
      {
        switch (paramInt2)
        {
        default:
          return false;
        case 0:
          if (((YaokongTimingVo)ActTiming.this.vos.get(paramInt1)).isOther())
          {
            ActTiming.this.toast(R.string.no_edit_other);
            return false;
          }
          ActTiming.this.bussines.isCreateNewTime = false;
          ActTiming.access$802(ActTiming.this, paramInt1);
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
        ProgressDialog localProgressDialog = ProgressDialog.show(ActTiming.this, "", ActTiming.this.getString(R.string.get_d_info), false);
        localProgressDialog.setCancelable(true);
        localProgressDialog.show();
        ActTiming.this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt1, localProgressDialog)
        {
          public void onSendFailde()
          {
            ActTiming.this.toast(R.string.add_time_no_ok);
            this.val$dialog.dismiss();
          }

          public void onSendOk()
          {
            ActTiming.this.vos.remove(this.val$position);
            TimingData.getInstance(ActTiming.this.getApplicationContext()).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            ActTiming.this.setListTotgleBtnListenerDelay();
            ActTiming.this.adt.notifyDataSetChanged();
            this.val$dialog.dismiss();
            ActTiming.this.toast(R.string.time_ctrl_ok);
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
    setMenuBackgroundRes(R.mipmap.device_ic);
    setTiTleTextRes(2131100433);
    setEditImageRes(2130903589);
    setDeviceTextRes(UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, ""), 2131492890);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == -1)
    {
      if (!this.bussines.isCreateNewTime)
        break label85;
      this.bussines.sendTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition, true);
    }
    while (true)
    {
      ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(R.string.get_d_info), false);
      localProgressDialog.setCancelable(true);
      localProgressDialog.show();
      this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(localProgressDialog)
      {
        public void onSendFailde()
        {
          ActTiming.this.toast(R.string.add_time_no_ok);
          this.val$dialog.dismiss();
        }

        public void onSendOk()
        {
          if (ActTiming.this.bussines.isCreateNewTime)
            ActTiming.this.vos.add(ActTiming.this.bussines.getCacheTimingVo4Sd());
          while (true)
          {
            ActTiming.this.bussines.getCacheTimingVo4Sd().getXuHao();
            ActTiming.this.bussines.reSortTimingData(ActTiming.this.vos);
            TimingData.getInstance(ActTiming.this).saveTimingVos2Sd(ActTiming.this.vos);
            ActTiming.this.adt.notifyDataSetChanged();
            ActTiming.this.setListTotgleBtnListenerDelay();
            this.val$dialog.dismiss();
            ActTiming.this.toast(2131099855);
            ActTiming.this.bussines.setSendCmdListener(null);
            return;
            ActTiming.this.vos.remove(ActTiming.this.itemPosition);
            ActTiming.this.vos.add(ActTiming.this.itemPosition, ActTiming.this.bussines.getCacheTimingVo4Sd());
          }
        }
      });
      return;
      label85: this.bussines.sendEditTiming(this.bussines.getCacheTimingVo4Sd(), this.itemPosition);
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

  protected void onEdit()
  {
    super.onEdit();
    if (this.adt.getCount() < 5)
    {
      this.bussines.isCreateNewTime = true;
      goAct4result(ActAddTiming.class, 100);
      this.itemPosition = -1;
      return;
    }
    toast(2131100068);
  }

  public void onListVSwichChange(boolean paramBoolean, int paramInt)
  {
    ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(R.string.get_d_info), false);
    localProgressDialog.setCancelable(true);
    System.out.println("onListVSwichChange        " + paramInt);
    YaokongTimingVo localYaokongTimingVo = (YaokongTimingVo)this.vos.get(paramInt);
    localYaokongTimingVo.setSwich(paramBoolean);
    this.bussines.sendTiming(localYaokongTimingVo, paramInt, false);
    this.bussines.setSendCmdListener(new TimingBussines.SendCmdListener(paramInt, paramBoolean, localProgressDialog)
    {
      public void onSendFailde()
      {
        YaokongTimingVo localYaokongTimingVo = (YaokongTimingVo)ActTiming.this.vos.get(this.val$posi);
        if (!this.val$on);
        for (boolean bool = true; ; bool = false)
        {
          localYaokongTimingVo.setSwich(bool);
          ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
          ActTiming.this.setListTotgleBtnListenerDelay();
          ActTiming.this.adt.notifyDataSetChanged();
          ActTiming.this.toast(R.string.add_time_no_ok);
          this.val$dialog.dismiss();
          return;
        }
      }

      public void onSendOk()
      {
        ((YaokongTimingVo)ActTiming.this.vos.get(this.val$posi)).setSwich(this.val$on);
        ActTiming.this.timingData.saveTimingVos2Sd(ActTiming.this.vos);
        this.val$dialog.dismiss();
        ActTiming.this.toast(R.string.time_ctrl_ok);
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
    if (!this.bussines.isCreateNewTime)
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
        }
      }
      , 4000L);
    }
  }

  public void upDateData()
  {
    this.vos.clear();
    this.vos.addAll(this.timingData.getTimingVos4Sd());
    this.bussines.reSortTimingData(this.vos);
    setListTotgleBtnListenerDelay();
    this.adt.notifyDataSetChanged();
    if (this.getTimeDialog.isShowing())
      this.getTimeDialog.dismiss();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.time.act.ActTiming
 * JD-Core Version:    0.6.0
 */