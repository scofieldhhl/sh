package com.ex.ltech.led.acti.colors;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.StringUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.List;

public class AtPanelLearnActivity extends MyBaseActivity
{
  private boolean changeBg = true;
  Handler handler = new Handler();
  private ImageView iv;
  int ivResFront;
  int ivResReal;
  String lastRecData = "";
  XlinkNetListener mXlinkNetListener = new XlinkNetListener()
  {
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
      System.out.println(str);
      if ((str.indexOf("66BB") == -1) || (str.indexOf("EB") == -1) || (str.length() < 18))
        return;
      if ((str.substring(12, 14).equalsIgnoreCase("01")) && (str.substring(14, 16).equalsIgnoreCase("FC")) && (!AtPanelLearnActivity.this.lastRecData.equalsIgnoreCase(str)))
      {
        AtPanelLearnActivity.this.handler.removeCallbacks(AtPanelLearnActivity.this.runnable);
        AtPanelLearnActivity.this.timeHandler.removeCallbacks(AtPanelLearnActivity.this.runnableTime);
        AtPanelLearnActivity.this.startActivity(new Intent(AtPanelLearnActivity.this, AtAddResultActivity.class).putExtra("isConfigOk", true).putExtra("type", AtPanelLearnActivity.this.getIntent().getStringExtra("type")));
        AtPanelLearnActivity.this.finish();
      }
      AtPanelLearnActivity.this.lastRecData = str;
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
    }

    public void onStart(int paramInt)
    {
    }
  };
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      AtPanelLearnActivity.this.loopPic();
    }
  };
  Runnable runnableTime = new Runnable()
  {
    public void run()
    {
      if (AtPanelLearnActivity.this.timerTime > 1)
      {
        AtPanelLearnActivity localAtPanelLearnActivity = AtPanelLearnActivity.this;
        localAtPanelLearnActivity.timerTime = (-1 + localAtPanelLearnActivity.timerTime);
        AtPanelLearnActivity.this.timeHandler.postDelayed(AtPanelLearnActivity.this.runnableTime, 1000L);
        AtPanelLearnActivity.this.sec.setText(AtPanelLearnActivity.this.timerTime + "");
        return;
      }
      AtPanelLearnActivity.this.timerTime = 60;
      AtPanelLearnActivity.this.startActivity(new Intent(AtPanelLearnActivity.this, AtAddResultActivity.class).putExtra("isConfigOk", false).putExtra("type", AtPanelLearnActivity.this.getIntent().getStringExtra("type")));
      AtPanelLearnActivity.this.finish();
    }
  };
  private TextView sec;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 60;
  private TextView tv_info;

  private void loopPic()
  {
    if (this.changeBg)
    {
      this.iv.setBackgroundResource(this.ivResFront);
      if (this.changeBg)
        break label62;
    }
    label62: for (boolean bool = true; ; bool = false)
    {
      this.changeBg = bool;
      this.handler.postDelayed(this.runnable, 1000L);
      return;
      this.iv.setBackgroundResource(this.ivResReal);
      break;
    }
  }

  private void timer()
  {
    this.timerTime = 60;
    this.timeHandler.removeCallbacks(this.runnableTime);
    this.timeHandler.postDelayed(this.runnableTime, 1000L);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    SocketManager.instance().sendData(new CmdDateBussiness("0000").getLearnPanelNRcCmd());
    setContentView(2130968693);
    setViewTitle();
    setMenuBackgroundRes(2130903830);
    this.sec = ((TextView)findViewById(2131558994));
    this.iv = ((ImageView)findViewById(2131558995));
    this.tv_info = ((TextView)findViewById(2131558816));
    String str = getIntent().getStringExtra("type");
    int i = -1;
    int j;
    int k;
    switch (str.hashCode())
    {
    default:
      j = 0;
      k = 0;
      switch (i)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      case 10:
      case 11:
      case 12:
      case 13:
      case 14:
      case 15:
      }
    case -995543379:
    case -995543378:
    case -995543377:
    case -995543376:
    case -797073628:
    case -797073596:
    case -797073564:
    case -797073532:
    case -995543375:
    case -995543374:
    case 112672:
    case 112673:
    case 3492881:
    case 3492913:
    case 112674:
    case 112675:
    }
    while (true)
    {
      setTiTleTextRes(k);
      this.tv_info.setText(j);
      timer();
      loopPic();
      XlinkAgent.getInstance().addXlinkListener(this.mXlinkNetListener);
      SocketManager.instance().sendData(new CmdDateBussiness("0000").getLearnPanelNRcCmd());
      return;
      if (!str.equals("panel1"))
        break;
      i = 0;
      break;
      if (!str.equals("panel2"))
        break;
      i = 1;
      break;
      if (!str.equals("panel3"))
        break;
      i = 2;
      break;
      if (!str.equals("panel4"))
        break;
      i = 3;
      break;
      if (!str.equals("panel11"))
        break;
      i = 4;
      break;
      if (!str.equals("panel22"))
        break;
      i = 5;
      break;
      if (!str.equals("panel33"))
        break;
      i = 6;
      break;
      if (!str.equals("panel44"))
        break;
      i = 7;
      break;
      if (!str.equals("panel5"))
        break;
      i = 8;
      break;
      if (!str.equals("panel6"))
        break;
      i = 9;
      break;
      if (!str.equals("rc1"))
        break;
      i = 10;
      break;
      if (!str.equals("rc2"))
        break;
      i = 11;
      break;
      if (!str.equals("rc11"))
        break;
      i = 12;
      break;
      if (!str.equals("rc22"))
        break;
      i = 13;
      break;
      if (!str.equals("rc3"))
        break;
      i = 14;
      break;
      if (!str.equals("rc4"))
        break;
      i = 15;
      break;
      k = 2131100436;
      j = 2131099838;
      this.ivResFront = 2130903320;
      this.ivResReal = 2130903321;
      continue;
      k = 2131100438;
      j = 2131099838;
      this.ivResFront = 2130903320;
      this.ivResReal = 2130903321;
      continue;
      k = 2131100440;
      j = 2131099839;
      this.ivResFront = 2130903322;
      this.ivResReal = 2130903323;
      continue;
      k = 2131100442;
      j = 2131099839;
      this.ivResFront = 2130903322;
      this.ivResReal = 2130903323;
      continue;
      k = 2131100437;
      j = 2131099838;
      this.ivResFront = 2130903320;
      this.ivResReal = 2130903321;
      continue;
      k = 2131100439;
      j = 2131099838;
      this.ivResFront = 2130903320;
      this.ivResReal = 2130903321;
      continue;
      k = 2131100441;
      j = 2131099839;
      this.ivResFront = 2130903322;
      this.ivResReal = 2130903323;
      continue;
      k = 2131100443;
      j = 2131099839;
      this.ivResFront = 2130903322;
      this.ivResReal = 2130903323;
      continue;
      k = 2131100444;
      j = 2131099839;
      this.ivResFront = 2130903324;
      this.ivResReal = 2130903325;
      continue;
      k = 2131100445;
      j = 2131099839;
      this.ivResFront = 2130903326;
      this.ivResReal = 2130903327;
      continue;
      k = 2131100446;
      j = 2131099847;
      this.ivResFront = 2130903334;
      this.ivResReal = 2130903335;
      findViewById(2131558810).setVisibility(0);
      continue;
      k = 2131100448;
      j = 2131099848;
      this.ivResFront = 2130903342;
      this.ivResReal = 2130903343;
      findViewById(2131558810).setVisibility(0);
      continue;
      k = 2131100447;
      j = 2131099847;
      this.ivResFront = 2130903334;
      this.ivResReal = 2130903335;
      findViewById(2131558810).setVisibility(0);
      continue;
      k = 2131100449;
      j = 2131099848;
      this.ivResFront = 2130903340;
      this.ivResReal = 2130903341;
      findViewById(2131558810).setVisibility(0);
      continue;
      k = 2131100450;
      j = 2131099848;
      this.ivResFront = 2130903336;
      this.ivResReal = 2130903337;
      findViewById(2131558810).setVisibility(0);
      continue;
      k = 2131100451;
      j = 2131099848;
      this.ivResFront = 2130903338;
      this.ivResReal = 2130903339;
      findViewById(2131558810).setVisibility(0);
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    this.handler.removeCallbacks(this.runnable);
    this.timeHandler.removeCallbacks(this.runnableTime);
    XlinkAgent.getInstance().removeListener(this.mXlinkNetListener);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.colors.AtPanelLearnActivity
 * JD-Core Version:    0.6.0
 */