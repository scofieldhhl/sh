package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.StringUtil;
import com.ex.ltech.hongwai.atRcs.AtRcSet;
import com.ex.ltech.hongwai.view.FailTipsDialog;
import com.ex.ltech.hongwai.view.FailTipsDialog.OnListener;
import com.ex.ltech.hongwai.view.OkTipsDialog;
import com.ex.ltech.hongwai.view.OkTipsDialog.OnListener;
import com.ex.ltech.hongwai.view.SearchDeviceDialog;
import com.ex.ltech.hongwai.view.SearchDeviceDialog.OnListener;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
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

public class AtYkCfgHelpActivity extends MyBaseActivity
{
  public static final String CFG_TYPE = "cfg_type";
  public static final int LIGHT = 3;
  public static final int RF2_SWITCH = 1;
  public static final int RF_SWITCH = 0;
  public static final int WALL_SWITCH = 2;
  CmdDateBussiness cmdDateBussiness;
  private final String delIrLightRespFunctionCode = "C1";
  private final String delIrPanelRespFunctionCode = "D6";
  Handler handler;
  private final int ir$LightMaxOder = 23;
  private final int ir$PanelMinOder = 12;
  private final int ir$devicesMaxOder = 11;
  private boolean isSelected = false;
  String lastRecCode = "";
  private final String lightGotoLearnStatusOk = "00";
  private final String lightLearnOkRespFunctionCode = "C0";
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
  OkTipsDialog okTipsDialog;
  private final String panelLearnOkRespFunctionCode = "D0";
  private final String panelOderRespFunctionCode = "D5";
  public MyRcDevices rcDevices;
  Bundle savedInstanceState;
  SearchDeviceDialog searchDeviceDialog;
  private boolean testCtLightOnoff = true;
  private int type = 0;
  Runnable waitLearnRunnable = new Runnable()
  {
    public void run()
    {
      AtYkCfgHelpActivity.this.sendCmd(AtYkCfgHelpActivity.this.cmdDateBussiness.irLearnLight(AtYkCfgHelpActivity.this.nonIrDeviceId, 2, 0));
      AtYkCfgHelpActivity.this.handler.postDelayed(this, 2000L);
    }
  };

  private void getDeviceOder()
  {
    this.nonIrDeviceId = getIntent().getIntExtra(AtRcSet.IR_LIGHT_D_ID_KEY, -1);
    if (this.nonIrDeviceId == -1)
      switch (this.type)
      {
      case 2:
      default:
      case 0:
      case 1:
      case 3:
      }
    while (true)
    {
      XlinkAgent.getInstance().addXlinkListener(this.myLearnListener);
      return;
      sendCmd(this.cmdDateBussiness.queryIr$PanelOder(0));
      continue;
      sendCmd(this.cmdDateBussiness.queryIr$PanelOder(1));
      continue;
      sendCmd(this.cmdDateBussiness.queryIr$LightOder());
      continue;
      this.handler.post(this.waitLearnRunnable);
    }
  }

  private void handleRecCode(byte[] paramArrayOfByte)
  {
    String str1 = StringUtil.byte2Hexstr(paramArrayOfByte);
    if (this.lastRecCode.equals(str1.substring(6, str1.length())));
    String str3;
    do
    {
      do
      {
        return;
        this.lastRecCode = str1.substring(6, str1.length());
      }
      while (str1.length() != 18);
      String str2 = str1.substring(14, 16);
      str3 = str1.substring(12, 14);
      System.out.println("13326660525     function " + str2);
      int i = -1;
      switch (str2.hashCode())
      {
      default:
      case 2161:
      case 2129:
      case 2162:
      case 2126:
      case 2156:
      case 2125:
      }
      while (true)
        switch (i)
        {
        default:
          return;
        case 0:
          handlerDeviceId(Integer.parseInt(str1.substring(12, 14), 16));
        case 1:
          handlerDeviceId(Integer.parseInt(str1.substring(12, 14), 16));
          return;
          if (!str2.equals("D5"))
            continue;
          i = 0;
          continue;
          if (!str2.equals("C4"))
            continue;
          i = 1;
          continue;
          if (!str2.equals("D6"))
            continue;
          i = 2;
          continue;
          if (!str2.equals("C1"))
            continue;
          i = 3;
          continue;
          if (!str2.equals("D0"))
            continue;
          i = 4;
          continue;
          if (!str2.equals("C0"))
            continue;
          i = 5;
        case 2:
        case 3:
        case 4:
        case 5:
        }
      finish();
      return;
      finish();
      return;
      showSearchDeviceDialog();
      return;
    }
    while (str3.equalsIgnoreCase("00"));
    showSearchDeviceDialog();
  }

  private void handlerDeviceId(int paramInt)
  {
    this.nonIrDeviceId = paramInt;
    if (this.nonIrDeviceId > 11)
    {
      Toast.makeText(this, 2131100110, 0).show();
      finish();
    }
    do
      return;
    while (this.type != 3);
    this.handler.post(this.waitLearnRunnable);
  }

  private void learn()
  {
    switch (this.type)
    {
    case 2:
    default:
      return;
    case 0:
      sendCmd(this.cmdDateBussiness.irLearnDevice(this.nonIrDeviceId, 226));
      return;
    case 1:
      sendCmd(this.cmdDateBussiness.irLearnDevice(this.nonIrDeviceId, 227));
      return;
    case 3:
    }
    sendCmd(this.cmdDateBussiness.irLearnLight(this.nonIrDeviceId, 2, 1));
  }

  private void sendCmd(byte[] paramArrayOfByte)
  {
    System.out.println("sendCmd = " + StringUtils.btye2Str3(paramArrayOfByte));
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
    setMenuBackgroundRes(R.mipmap.plug_back);
    setDeviceTextRes(getResources().getString(2131099880), 2131492935);
  }

  private void showSearchDeviceDialog()
  {
    if ((this.searchDeviceDialog != null) && (this.searchDeviceDialog.isShowing()))
      this.searchDeviceDialog.dismiss();
    if ((this.okTipsDialog != null) && (this.okTipsDialog.isShowing()))
      return;
    this.okTipsDialog = new OkTipsDialog(this);
    this.okTipsDialog.show();
    this.okTipsDialog.setListener(new OkTipsDialog.OnListener()
    {
      public void onConfigOk()
      {
        if (AtYkCfgHelpActivity.this.okTipsDialog.isShowing())
          AtYkCfgHelpActivity.this.okTipsDialog.dismiss();
        MyRcDevice localMyRcDevice = new MyRcDevice();
        localMyRcDevice.nonIrDevice = new NonIrDevice();
        switch (AtYkCfgHelpActivity.this.type)
        {
        case 2:
        default:
        case 0:
        case 1:
        case 3:
        }
        while (true)
        {
          localMyRcDevice.nonIrDevice.nonIrDeviceId = AtYkCfgHelpActivity.this.nonIrDeviceId;
          AtYkCfgHelpActivity.this.rcDevices.myRcDevices.add(localMyRcDevice);
          MyDb.getInstance(AtYkCfgHelpActivity.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, AtYkCfgHelpActivity.this.rcDevices);
          AtYkCfgHelpActivity.this.finish();
          return;
          localMyRcDevice.mType = 10;
          localMyRcDevice.nonIrDevice.mType = 10;
          localMyRcDevice.isShowListInfo = true;
          localMyRcDevice.mName = AtYkCfgHelpActivity.this.getString(2131100423);
          AtYkCfgHelpActivity.this.setResult(10);
          continue;
          localMyRcDevice.mType = 11;
          localMyRcDevice.nonIrDevice.mType = 11;
          localMyRcDevice.isShowListInfo = true;
          localMyRcDevice.mName = AtYkCfgHelpActivity.this.getString(2131100424);
          AtYkCfgHelpActivity.this.setResult(11);
          continue;
          localMyRcDevice.mType = 12;
          localMyRcDevice.nonIrDevice.mType = 12;
          localMyRcDevice.isShowListInfo = false;
          localMyRcDevice.nonIrDevice.irCt1Online = true;
          localMyRcDevice.nonIrDevice.irCt1Onoff = true;
          localMyRcDevice.mName = AtYkCfgHelpActivity.this.getString(2131100037);
          AtYkCfgHelpActivity.this.setResult(12);
        }
      }

      public void onOnoff()
      {
        int i = 225;
        AtYkCfgHelpActivity localAtYkCfgHelpActivity1 = AtYkCfgHelpActivity.this;
        if (!AtYkCfgHelpActivity.this.testCtLightOnoff);
        for (boolean bool = true; ; bool = false)
        {
          AtYkCfgHelpActivity.access$002(localAtYkCfgHelpActivity1, bool);
          switch (AtYkCfgHelpActivity.this.type)
          {
          case 2:
          default:
            return;
          case 0:
          case 1:
          case 3:
          }
        }
        AtYkCfgHelpActivity localAtYkCfgHelpActivity3 = AtYkCfgHelpActivity.this;
        CmdDateBussiness localCmdDateBussiness2 = AtYkCfgHelpActivity.this.cmdDateBussiness;
        int k = AtYkCfgHelpActivity.this.nonIrDeviceId;
        if (AtYkCfgHelpActivity.this.testCtLightOnoff);
        for (int m = i; ; m = 193)
        {
          localAtYkCfgHelpActivity3.sendCmd(localCmdDateBussiness2.onOffK1RFAndK2RF(k, m));
          return;
        }
        AtYkCfgHelpActivity localAtYkCfgHelpActivity2 = AtYkCfgHelpActivity.this;
        CmdDateBussiness localCmdDateBussiness1 = AtYkCfgHelpActivity.this.cmdDateBussiness;
        int j = AtYkCfgHelpActivity.this.nonIrDeviceId;
        if (AtYkCfgHelpActivity.this.testCtLightOnoff);
        while (true)
        {
          localAtYkCfgHelpActivity2.sendCmd(localCmdDateBussiness1.onOffK1RFAndK2RF(j, i));
          return;
          i = 193;
        }
        AtYkCfgHelpActivity.this.sendCmd(AtYkCfgHelpActivity.this.cmdDateBussiness.controlIrCtLight(AtYkCfgHelpActivity.this.nonIrDeviceId, 1, AtYkCfgHelpActivity.this.testCtLightOnoff, 255, 255, 255));
      }

      public void onRepeatStep()
      {
        AtYkCfgHelpActivity.this.okTipsDialog.dismiss();
        AtYkCfgHelpActivity.access$302(AtYkCfgHelpActivity.this, false);
        switch (AtYkCfgHelpActivity.this.type)
        {
        default:
          AtYkCfgHelpActivity.this.sendCmd(AtYkCfgHelpActivity.this.cmdDateBussiness.delIrPanel(AtYkCfgHelpActivity.this.nonIrDeviceId));
          return;
        case 3:
        }
        AtYkCfgHelpActivity.this.sendCmd(AtYkCfgHelpActivity.this.cmdDateBussiness.delIrLight(AtYkCfgHelpActivity.this.nonIrDeviceId));
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.savedInstanceState = paramBundle;
    setContentView(2130968711);
    ButterKnife.bind(this);
    setTitleView();
    this.type = getIntent().getIntExtra("cfg_type", 0);
    this.cmdDateBussiness = new CmdDateBussiness(getApplicationContext(), "0000");
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    switch (this.type)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      this.handler = new Handler(Looper.getMainLooper());
      getDeviceOder();
      return;
      this.mImageViewHelp.setBackgroundResource(2130903397);
      this.mTextViewContent.setText(2131100117);
      this.mTextViewTip.setText(2131100095);
      this.mTextViewNext.setText(2131100126);
      setTiTleTextRes(2131100029);
      continue;
      this.mImageViewHelp.setBackgroundResource(2130903396);
      this.mTextViewContent.setText(2131100116);
      this.mTextViewTip.setText(2131100095);
      this.mTextViewNext.setText(2131100126);
      setTiTleTextRes(2131100029);
      continue;
      this.mImageViewHelp.setBackgroundResource(2130903393);
      this.mTextViewContent.setText(2131100118);
      this.mTextViewTip.setText(2131099884);
      this.mTextViewNext.setText(2131100206);
      setTiTleTextRes(2131100029);
      continue;
      this.mImageViewHelp.setBackgroundResource(2130903447);
      this.mTextViewContent.setText(2131100112);
      this.mTextViewTip.setText(2131099987);
      this.mTextViewNext.setText(2131099892);
      setTiTleTextRes(2131100037);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.handler.removeCallbacks(this.waitLearnRunnable);
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
      this.handler.removeCallbacks(this.waitLearnRunnable);
      this.searchDeviceDialog = new SearchDeviceDialog(this).setTextTip(getString(2131100381)).setTotalProgress(20).setProgress(0);
      this.searchDeviceDialog.show();
      this.searchDeviceDialog.setOnListener(new SearchDeviceDialog.OnListener()
      {
        public void onCancel()
        {
        }

        public void onTimeOut()
        {
          FailTipsDialog localFailTipsDialog = new FailTipsDialog(AtYkCfgHelpActivity.this);
          localFailTipsDialog.show();
          localFailTipsDialog.setListener(new FailTipsDialog.OnListener()
          {
            public void onNegativeClick()
            {
              AtYkCfgHelpActivity.this.finish();
            }

            public void onPositiveClick()
            {
              AtYkCfgHelpActivity.this.onNext(null);
            }
          });
        }
      });
      learn();
    }
  }

  public void onSeleted(View paramView)
  {
    if (!this.isSelected);
    for (boolean bool = true; ; bool = false)
    {
      this.isSelected = bool;
      if (!this.isSelected)
        break;
      this.mTextViewNext.setBackgroundResource(2130837634);
      this.mTextViewNext.setTextColor(getResources().getColor(2131492935));
      this.mTextViewSelected.setBackgroundResource(2130903786);
      return;
    }
    this.mTextViewNext.setBackgroundResource(2130837625);
    this.mTextViewNext.setTextColor(getResources().getColor(2131492934));
    this.mTextViewSelected.setBackgroundResource(2130903784);
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
      AtYkCfgHelpActivity.this.runOnUiThread(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          AtYkCfgHelpActivity.this.handleRecCode(this.val$bytes);
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
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkCfgHelpActivity
 * JD-Core Version:    0.6.0
 */