package com.ex.ltech.outlet;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.List;

public class ActOutLetInfo extends MyBaseActivity
{
  private CmdDateBussiness cmdDateBussiness;

  @Bind({2131558754})
  ImageView iv_act_outlet_led_menu_led;
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  boolean plugLedSwitch;

  @Bind({2131558753})
  ToggleButton tb_act_outlet_led_menu;

  @Bind({2131558755})
  TextView tv_act_outlet_led_menu_led;

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.setting);
  }

  public void goXiaoLed(View paramView)
  {
    if (!this.plugLedSwitch)
      return;
    goAct(ActOutLetLed.class);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968639);
    ButterKnife.bind(this);
    setMyTitle();
    this.cmdDateBussiness = new CmdDateBussiness(this, "0000");
    this.tb_act_outlet_led_menu.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
    {
      public void onToggleInListView(boolean paramBoolean, int paramInt)
      {
        ActOutLetInfo.this.plugLedSwitch = paramBoolean;
        UserFerences.getUserFerences(ActOutLetInfo.this).putValue("plugLedSwitch", Boolean.valueOf(paramBoolean));
        if (paramBoolean)
        {
          ActOutLetInfo.this.iv_act_outlet_led_menu_led.setBackgroundResource(2130903740);
          ActOutLetInfo.this.tv_act_outlet_led_menu_led.setTextColor(-16777216);
          return;
        }
        ActOutLetInfo.this.iv_act_outlet_led_menu_led.setBackgroundResource(2130903601);
        ActOutLetInfo.this.tv_act_outlet_led_menu_led.setTextColor(-7829368);
      }
    });
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onResume()
  {
    super.onResume();
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getLittleLedOnOffInfoCmd(), this.mySendPipeListener);
  }

  protected void onStop()
  {
    super.onStop();
    XlinkAgent.getInstance().removeListener(this.myXlinkNetListener);
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
      String str = StringUtils.btye2Str(paramArrayOfByte);
      System.out.println("onRecvPipeData      " + StringUtils.btye2Str(paramArrayOfByte));
      if ((str.length() == 18) && (str.indexOf("AEEB") != -1))
      {
        if (str.substring(12, 14).equals("01"))
          ActOutLetInfo.this.tb_act_outlet_led_menu.toggleOn();
        if (str.substring(12, 14).equals("00"))
          ActOutLetInfo.this.tb_act_outlet_led_menu.toggleOff();
      }
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      System.out.println("onRecvPipeSyncData      " + StringUtils.btye2Str(paramArrayOfByte));
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.ActOutLetInfo
 * JD-Core Version:    0.6.0
 */