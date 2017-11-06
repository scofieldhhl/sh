package com.ex.ltech.outlet;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.TimingVo;
import com.ex.ltech.outlet.timing.TimingData;
import com.ex.ltech.outlet.timing.act.ActTiming;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActOutLet extends MyBaseActivity
{
  boolean checkJustOnce;
  private CmdDateBussiness cmdDateBussiness;
  private boolean isOpen;
  ImageView iv_act_outlet_little_light;
  List<TimingVo> justOnceVos = new ArrayList();
  boolean littleLightSwitch;
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  long[] outletTimesOrder;
  long[] outletTimesReverse;
  List<Integer> tempPosi = new ArrayList();
  List<TimingVo> tempTimingVos;
  List<TimingVo> timingVos;
  TextView tvTimer;

  private void openCheckJustOnce()
  {
    this.tempTimingVos = TimingData.getInstance(this).getTimingVos4Sd();
    this.justOnceVos.clear();
    this.tempPosi.clear();
    this.checkJustOnce = true;
    for (int i = 0; i < this.tempTimingVos.size(); i++)
    {
      if ((!((TimingVo)this.tempTimingVos.get(i)).isJustOnce()) || (!((TimingVo)this.tempTimingVos.get(i)).isSwich()))
        continue;
      this.justOnceVos.add(this.tempTimingVos.get(i));
      this.tempPosi.add(Integer.valueOf(i));
    }
    if (this.justOnceVos.size() == 0)
      return;
    new Thread()
    {
      public void run()
      {
        for (int i = 0; i < ActOutLet.this.justOnceVos.size(); i++)
        {
          int j = ((Integer)ActOutLet.this.tempPosi.get(i)).intValue();
          if (System.currentTimeMillis() <= ((TimingVo)ActOutLet.this.justOnceVos.get(i)).getOutletLatelyTime())
            continue;
          ActOutLet.this.tempTimingVos.remove(j);
          TimingVo localTimingVo = (TimingVo)ActOutLet.this.justOnceVos.get(i);
          localTimingVo.setSwich(false);
          ActOutLet.this.tempTimingVos.add(j, localTimingVo);
          TimingData.getInstance(ActOutLet.this).saveTimingVos2Sd(ActOutLet.this.tempTimingVos);
          if (ActOutLet.this.justOnceVos.size() == 1)
            ActOutLet.this.checkJustOnce = false;
          ActOutLet.this.justOnceVos.remove(i);
        }
      }
    }
    .start();
  }

  private void orderSort(long[] paramArrayOfLong)
  {
    int i = paramArrayOfLong.length;
    for (int j = 0; j < i; j++)
    {
      int k = j;
      for (int m = i - 1; m > j; m--)
      {
        if (paramArrayOfLong[m] >= paramArrayOfLong[k])
          continue;
        k = m;
      }
      long l = paramArrayOfLong[j];
      paramArrayOfLong[j] = paramArrayOfLong[k];
      paramArrayOfLong[k] = l;
    }
  }

  private void reverseSort(long[] paramArrayOfLong)
  {
    int i = paramArrayOfLong.length;
    for (int j = 0; j < i; j++)
    {
      int k = j;
      for (int m = i - 1; m > j; m--)
      {
        if (paramArrayOfLong[m] <= paramArrayOfLong[k])
          continue;
        k = m;
      }
      long l = paramArrayOfLong[j];
      paramArrayOfLong[j] = paramArrayOfLong[k];
      paramArrayOfLong[k] = l;
    }
  }

  private void setMyTitle()
  {
    String str = UserFerences.getUserFerences(this).spFerences.getString("dName" + DeviceListActivity.deviceMacAddress, "");
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.plug_back);
    setEditImageRes(2130903627);
    setTiTleText(str);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968637);
    setMyTitle();
    this.cmdDateBussiness = new CmdDateBussiness(this, "0000");
    this.tvTimer = ((TextView)findViewById(2131558738));
    this.iv_act_outlet_little_light = ((ImageView)findViewById(2131558740));
  }

  protected void onEdit()
  {
    super.onEdit();
    this.littleLightSwitch = true;
    this.iv_act_outlet_little_light.setBackgroundResource(2130903628);
    goAct(ActOutLetLed.class);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onResume()
  {
    int i = 1;
    super.onResume();
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getPlugsOnOffInfoCmd(), this.mySendPipeListener);
    openCheckJustOnce();
    this.timingVos = TimingData.getInstance(this).getTimingVos4Sd();
    if (this.timingVos.size() > 0)
    {
      long[] arrayOfLong = new long[this.timingVos.size()];
      for (int j = 0; j < this.timingVos.size(); j++)
      {
        if (!((TimingVo)this.timingVos.get(j)).isSwich())
          continue;
        arrayOfLong[j] = ((TimingVo)this.timingVos.get(j)).getOutletLatelyTime();
      }
      orderSort(arrayOfLong);
      int k = 0;
      for (int m = 0; m < this.timingVos.size(); m++)
      {
        if (((TimingVo)this.timingVos.get(m)).getOutletLatelyTime() != arrayOfLong[0])
          continue;
        k = m;
      }
      TimingVo localTimingVo = (TimingVo)this.timingVos.get(k);
      if (System.currentTimeMillis() < arrayOfLong[0])
      {
        int n;
        if (localTimingVo.getStartTime().length() > 0)
        {
          n = i;
          if (localTimingVo.getEndTime().length() <= 0)
            break label322;
          label261: if ((n & i) == 0)
            break label327;
          this.tvTimer.setText(DateFmtUtil.formatTime(arrayOfLong[0] - System.currentTimeMillis()) + "\t\t" + getString(2131100248));
        }
        label322: label327: 
        do
        {
          return;
          n = 0;
          break;
          i = 0;
          break label261;
          if (localTimingVo.getStartTime().length() <= 0)
            continue;
          this.tvTimer.setText(DateFmtUtil.formatTime(arrayOfLong[0] - System.currentTimeMillis()) + "\t\t" + getString(2131100248));
          return;
        }
        while (localTimingVo.getEndTime().length() <= 0);
        this.tvTimer.setText(DateFmtUtil.formatTime(arrayOfLong[0] - System.currentTimeMillis()) + "\t\t" + getString(2131099950));
        return;
      }
      this.tvTimer.setText(getString(2131100213));
      return;
    }
    this.tvTimer.setText(getString(2131100213));
  }

  protected void onStop()
  {
    super.onStop();
    XlinkAgent.getInstance().removeListener(this.myXlinkNetListener);
  }

  public void outlet(View paramView)
  {
    if (this.isOpen)
    {
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getPlugOnOffCmd(96), this.mySendPipeListener);
      paramView.setBackgroundResource(2130903603);
      this.isOpen = false;
      return;
    }
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getPlugOnOffCmd(97), this.mySendPipeListener);
    paramView.setBackgroundResource(2130903604);
    this.isOpen = true;
  }

  public void outletLed(View paramView)
  {
    if (this.littleLightSwitch)
    {
      this.littleLightSwitch = false;
      this.iv_act_outlet_little_light.setBackgroundResource(2130903629);
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getOutletSwich(false), this.mySendPipeListener);
      return;
    }
    this.littleLightSwitch = true;
    this.iv_act_outlet_little_light.setBackgroundResource(2130903628);
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getOutletSwich(true), this.mySendPipeListener);
  }

  public void outletSwitch(View paramView)
  {
    outlet(findViewById(2131558737));
  }

  public void outletTime(View paramView)
  {
    goAct(ActTiming.class);
  }

  class MySendPipeListener extends SendPipeListener
  {
    MySendPipeListener()
    {
    }

    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  }

  class MyXlinkNetListener
    implements XlinkNetListener
  {
    MyXlinkNetListener()
    {
    }

    public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
    {
    }

    public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
    {
    }

    public void onDisconnect(int paramInt)
    {
    }

    public void onEventNotify(EventNotify paramEventNotify)
    {
    }

    public void onLocalDisconnect(int paramInt)
    {
    }

    public void onLogin(int paramInt)
    {
    }

    public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      ActOutLet.this.runOnUiThread(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          String str = StringUtils.btye2Str(this.val$bytes);
          if ((this.val$bytes.length == 15) && (str.indexOf("D0EB") != -1))
          {
            System.out.println("onRecvPipeData      " + StringUtils.btye2Str(this.val$bytes) + "             " + str.substring(16, 18));
            if (str.substring(16, 18).equals("01"))
            {
              ActOutLet.this.findViewById(2131558737).setBackgroundResource(2130903604);
              ActOutLet.access$002(ActOutLet.this, true);
            }
            if (str.substring(16, 18).equals("00"))
            {
              ActOutLet.this.findViewById(2131558737).setBackgroundResource(2130903603);
              ActOutLet.access$002(ActOutLet.this, false);
            }
            if (str.substring(24, 26).equals("01"))
            {
              ActOutLet.this.littleLightSwitch = true;
              ActOutLet.this.iv_act_outlet_little_light.setBackgroundResource(2130903628);
            }
            if (str.substring(24, 26).equals("00"))
            {
              ActOutLet.this.littleLightSwitch = false;
              ActOutLet.this.iv_act_outlet_little_light.setBackgroundResource(2130903629);
            }
          }
          if ((str.length() == 18) && (str.indexOf("AEEB") != -1));
        }
      });
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
      System.out.println("onRecvPipeSyncData      " + StringUtils.btye2Str(paramArrayOfByte));
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.ActOutLet
 * JD-Core Version:    0.6.0
 */