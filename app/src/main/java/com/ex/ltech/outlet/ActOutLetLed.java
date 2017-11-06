package com.ex.ltech.outlet;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MyRainbowSeekBar;
import com.ex.ltech.led.my_view.MyRainbowSeekBar.onMySBtouchListener;
import com.ex.ltech.led.my_view.SeekArc;
import com.ex.ltech.led.my_view.SeekArc.OnSeekArcChangeListener;
import com.ex.ltech.led.utils.StringUtils;
import com.indris.material.RippleView;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.List;

public class ActOutLetLed extends MyBaseActivity
  implements MyRainbowSeekBar.onMySBtouchListener
{
  int b;
  private int bright = 255;

  @Bind({2131558749})
  Button bt_act_outlet_led_1;

  @Bind({2131558750})
  Button bt_act_outlet_led_2;

  @Bind({2131558751})
  Button bt_act_outlet_led_3;

  @Bind({2131558752})
  Button bt_act_outlet_led_4;

  @Bind({2131558745})
  Button bt_act_outlet_led_5;

  @Bind({2131558746})
  Button bt_act_outlet_led_6;

  @Bind({2131558747})
  Button bt_act_outlet_led_7;

  @Bind({2131558748})
  Button bt_act_outlet_led_8;

  @Bind({2131558784})
  RippleView btn_title_view_edit;

  @Bind({2131558781})
  RippleView btn_title_view_menu;
  private CmdDateBussiness cmdDateBussiness;
  int g;

  @Bind({2131558744})
  LinearLayout ll_bot_act_outlet_led;
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  int r;

  @Bind({2131558741})
  MyRainbowSeekBar sbMyRainbow;

  @Bind({2131558742})
  SeekArc sb_act_outlet_led;
  int time = 0;

  @Bind({2131558743})
  TextView tv_act_outlet_led_present;

  @Bind({2131558782})
  TextView tv_title_device_name;

  @Bind({2131558785})
  TextView tv_title_view_edit;

  @Bind({2131558783})
  TextView tv_title_view_title;

  private void resetColorBtn()
  {
    this.bt_act_outlet_led_1.setBackgroundResource(2130903425);
    this.bt_act_outlet_led_2.setBackgroundResource(2130903427);
    this.bt_act_outlet_led_3.setBackgroundResource(2130903429);
    this.bt_act_outlet_led_4.setBackgroundResource(2130903431);
    this.bt_act_outlet_led_5.setBackgroundResource(2130903433);
    this.bt_act_outlet_led_6.setBackgroundResource(2130903435);
    this.bt_act_outlet_led_7.setBackgroundResource(2130903437);
    this.bt_act_outlet_led_8.setBackgroundResource(2130903439);
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.plug_back);
    setTiTleTextRes(2131100147);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968638);
    ButterKnife.bind(this);
    setMyTitle();
    resetColorBtn();
    this.cmdDateBussiness = new CmdDateBussiness(this, "0000");
    this.sb_act_outlet_led.setProgress(100);
    this.sb_act_outlet_led.setOnSeekArcChangeListener(new SeekArc.OnSeekArcChangeListener()
    {
      public void onProgressChanged(SeekArc paramSeekArc, int paramInt, boolean paramBoolean)
      {
        ActOutLetLed localActOutLetLed = ActOutLetLed.this;
        localActOutLetLed.time = (1 + localActOutLetLed.time);
        ActOutLetLed.this.tv_act_outlet_led_present.setText(paramInt + "");
        ActOutLetLed.access$002(ActOutLetLed.this, paramInt * 255 / 100);
        if (ActOutLetLed.this.time % 5 == 0)
        {
          XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
          DeviceManage.getInstance();
          localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), ActOutLetLed.this.cmdDateBussiness.getColorCmd(209, ActOutLetLed.this.bright, ActOutLetLed.this.r, ActOutLetLed.this.g, ActOutLetLed.this.b, 255), ActOutLetLed.this.mySendPipeListener);
        }
      }

      public void onStartTrackingTouch(SeekArc paramSeekArc)
      {
      }

      public void onStopTrackingTouch(SeekArc paramSeekArc)
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), ActOutLetLed.this.cmdDateBussiness.getColorCmd(209, ActOutLetLed.this.bright, ActOutLetLed.this.r, ActOutLetLed.this.g, ActOutLetLed.this.b, 0), ActOutLetLed.this.mySendPipeListener);
      }
    });
    this.sbMyRainbow.setListener(this);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, paramInt5, paramInt2, paramInt3, paramInt4, 0), this.mySendPipeListener);
    this.sb_act_outlet_led.setProgessColor(paramInt1);
    this.r = Color.red(paramInt1);
    this.g = Color.green(paramInt1);
    this.b = Color.blue(paramInt1);
  }

  public void onMySbBrightUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void setColor1(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903424);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492938));
    this.r = 255;
    this.g = 0;
    this.b = 0;
    this.sbMyRainbow.setProgress(96);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor2(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903426);
    this.r = 255;
    this.g = 0;
    this.b = 40;
    this.sbMyRainbow.setProgress(90);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492940));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor3(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903428);
    this.r = 255;
    this.g = 91;
    this.b = 0;
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492942));
    this.sbMyRainbow.setProgress(18);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor4(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903430);
    this.r = 35;
    this.g = 255;
    this.b = 35;
    this.sbMyRainbow.setProgress(38);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492944));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor5(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903432);
    this.r = 0;
    this.g = 221;
    this.b = 255;
    this.sbMyRainbow.setProgress(55);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492946));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor6(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903434);
    this.r = 0;
    this.g = 100;
    this.b = 255;
    this.sbMyRainbow.setProgress(61);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492948));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor7(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903436);
    this.r = 180;
    this.g = 0;
    this.b = 200;
    this.sbMyRainbow.setProgress(79);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492950));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
  }

  public void setColor8(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903438);
    this.r = 255;
    this.g = 230;
    this.b = 240;
    this.sbMyRainbow.setProgress(30);
    this.sb_act_outlet_led.setProgessColor(getResources().getColor(2131492952));
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255), this.mySendPipeListener);
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
 * Qualified Name:     com.ex.ltech.outlet.ActOutLetLed
 * JD-Core Version:    0.6.0
 */