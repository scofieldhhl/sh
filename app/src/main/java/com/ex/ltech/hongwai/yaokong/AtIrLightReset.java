package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.StringUtil;
import com.ex.ltech.hongwai.atRcs.AtRcSet;
import com.ex.ltech.hongwai.view.SearchDeviceDialog;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.List;

public class AtIrLightReset extends MyBaseActivity
{
  public static final String CFG_TYPE = "cfg_type";
  public static final int LIGHT = 3;
  public static final int RF2_SWITCH = 1;
  public static final int RF_SWITCH = 0;
  public static final int WALL_SWITCH = 2;
  CmdDateBussiness cmdDateBussiness;
  Handler handler;
  private final int ir$LightMaxOder = 23;
  private final int ir$PanelMinOder = 12;
  private final int ir$devicesMaxOder = 11;
  private boolean isSelected = false;
  String lastRecCode = "";
  private final String lightGotoLearnStatusOk = "00";
  private final String lightLearnStatusOk = "01";
  private final String lightOderRespFunctionCode = "C4";

  @Bind({2131558919})
  ImageView mImageViewHelp;

  @Bind({2131558886})
  TextView mTextViewContent;

  @Bind({2131558869})
  TextView mTextViewNext;

  @Bind({2131558867})
  TextView mTextViewSelected;

  @Bind({2131558889})
  TextView mTextViewTip;
  MyLearnListener myLearnListener = new MyLearnListener();
  int nonIrDeviceId;
  private final String okRespFunctionCode = "C7";
  private final String panelLearnOkRespFunctionCode = "D0";
  public MyRcDevices rcDevices;
  Bundle savedInstanceState;
  SearchDeviceDialog searchDeviceDialog;
  private boolean testCtLightOnoff;
  private int type = 0;

  private void handleRecCode(byte[] paramArrayOfByte)
  {
    String str1 = StringUtil.byte2Hexstr(paramArrayOfByte);
    if (this.lastRecCode.equals(str1.substring(6, str1.length())));
    String str2;
    do
    {
      do
      {
        return;
        this.lastRecCode = str1.substring(6, str1.length());
      }
      while (str1.length() != 18);
      str2 = str1.substring(14, 16);
      System.out.println("13326660525     function " + str2);
    }
    while (!str2.equals("C7"));
    this.mTextViewNext.setBackgroundResource(2130837655);
    this.mTextViewNext.setTextColor(getResources().getColor(R.color.white));
  }

  private void learn()
  {
    sendCmd(this.cmdDateBussiness.resetIr$Devices(this.nonIrDeviceId));
  }

  private void sendCmd(byte[] paramArrayOfByte)
  {
    System.out.println("onSendLocalPipeData");
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), paramArrayOfByte, new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
  }

  private void setTitleView()
  {
    setViewTitle();
    setTitle(2131100341);
    setMenuBackgroundRes(R.mipmap.plug_back);
    setDeviceTextRes(getResources().getString(2131099880), 2131492935);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.savedInstanceState = paramBundle;
    setContentView(2130968679);
    ButterKnife.bind(this);
    setTitleView();
    this.type = getIntent().getIntExtra("cfg_type", 0);
    this.nonIrDeviceId = getIntent().getIntExtra(AtRcSet.IR_LIGHT_D_ID_KEY, 0);
    this.cmdDateBussiness = new CmdDateBussiness(getApplicationContext(), "0000");
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    this.mImageViewHelp.setBackgroundResource(2130903447);
    XlinkAgent.getInstance().addXlinkListener(this.myLearnListener);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    XlinkAgent.getInstance().removeListener(this.myLearnListener);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onNext(View paramView)
  {
    if (this.isSelected)
    {
      setResult(12);
      finish();
    }
  }

  public void onSeleted(View paramView)
  {
    boolean bool;
    if (!this.isSelected)
    {
      bool = true;
      this.isSelected = bool;
      if (!this.isSelected)
        break label52;
      this.mTextViewSelected.setBackgroundResource(2130903786);
    }
    while (true)
    {
      sendCmd(this.cmdDateBussiness.resetIr$Devices(this.nonIrDeviceId));
      return;
      bool = false;
      break;
      label52: this.mTextViewSelected.setBackgroundResource(2130903827);
    }
  }

  class MyLearnListener
    implements XlinkNetListener
  {
    MyLearnListener()
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
      AtIrLightReset.this.runOnUiThread(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          AtIrLightReset.this.handleRecCode(this.val$bytes);
        }
      });
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtIrLightReset
 * JD-Core Version:    0.6.0
 */