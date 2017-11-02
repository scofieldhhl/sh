package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.Global;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.SecondArc;
import com.hiflying.smartlink.ISmartLinker;
import com.hiflying.smartlink.OnSmartLinkListener;
import com.hiflying.smartlink.SmartLinkedModule;
import com.hiflying.smartlink.v7.MulticastSmartLinker;

public class AtCfg4Activity extends MyBaseActivity
  implements View.OnClickListener
{
  String cfgState = "cfging";

  @Bind({2131558877})
  Button deviceConnet;

  @Bind({2131558816})
  TextView info;
  private boolean isconncting = false;

  @Bind({2131558811})
  ImageView ivBottomLeft;

  @Bind({2131558812})
  ImageView ivBottomRight;

  @Bind({2131558879})
  ImageView ivCfgNoOk;

  @Bind({2131558878})
  ImageView ivCfgOk;
  protected ISmartLinker mSnifferSmartLinker;
  String psd;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (AtCfg4Activity.this.timerTime > 1)
      {
        AtCfg4Activity localAtCfg4Activity = AtCfg4Activity.this;
        localAtCfg4Activity.timerTime = (-1 + localAtCfg4Activity.timerTime);
        AtCfg4Activity.this.timeHandler.postDelayed(AtCfg4Activity.this.runnable, 1000L);
        AtCfg4Activity.this.second.setText(AtCfg4Activity.this.timerTime + "");
      }
      while (true)
      {
        AtCfg4Activity.this.sbActOutletLed.setProgressSweep(360 * (30 - AtCfg4Activity.this.timerTime) / 30);
        return;
        AtCfg4Activity.this.timerTime = 30;
        AtCfg4Activity.this.second.setText(AtCfg4Activity.this.timerTime + "");
        AtCfg4Activity.this.info.setText(2131099923);
        AtCfg4Activity.this.cfgState = "timeout";
        AtCfg4Activity.this.deviceConnet.setVisibility(8);
        AtCfg4Activity.this.ivBottomLeft.setVisibility(0);
        AtCfg4Activity.this.ivBottomRight.setVisibility(0);
        AtCfg4Activity.access$002(AtCfg4Activity.this, false);
        AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
        AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
        AtCfg4Activity.this.ivCfgOk.setVisibility(8);
        AtCfg4Activity.this.smallSsecond.setVisibility(8);
        AtCfg4Activity.this.second.setVisibility(8);
        AtCfg4Activity.this.ivCfgNoOk.setVisibility(0);
      }
    }
  };
  private SecondArc sbActOutletLed;
  private TextView second;
  private TextView smallSsecond;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 30;
  String wifiName;

  private void timer()
  {
    this.timerTime = 30;
    this.timeHandler.removeCallbacks(this.runnable);
    this.timeHandler.postDelayed(this.runnable, 1000L);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558877:
      do
      {
        return;
        if (this.cfgState.equals("cfging"))
        {
          this.mSnifferSmartLinker.stop();
          finish();
        }
        if (!this.cfgState.equals("timeout"))
          continue;
        startSmartLink();
        startNewSL();
        this.cfgState = "cfging";
        this.deviceConnet.setBackgroundResource(2130903131);
        this.ivCfgOk.setVisibility(8);
        this.smallSsecond.setVisibility(0);
        this.second.setVisibility(0);
        this.ivCfgNoOk.setVisibility(8);
      }
      while (!this.cfgState.equals("ok"));
      setResult(Global.net_config_ok);
      finish();
      return;
    case 2131558811:
      finish();
      return;
    case 2131558812:
    }
    this.smallSsecond.setVisibility(0);
    this.second.setVisibility(0);
    this.ivCfgNoOk.setVisibility(8);
    startSmartLink();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968661);
    ButterKnife.bind(this);
    this.sbActOutletLed = ((SecondArc)findViewById(2131558742));
    this.second = ((TextView)findViewById(2131558875));
    this.smallSsecond = ((TextView)findViewById(2131558876));
    findViewById(2131558877).setOnClickListener(this);
    findViewById(2131558811).setOnClickListener(this);
    findViewById(2131558812).setOnClickListener(this);
    this.wifiName = getIntent().getStringExtra("wifi");
    this.psd = getIntent().getStringExtra("psd");
    startSmartLink();
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleText("");
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.mSnifferSmartLinker.setOnSmartLinkListener(null);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    this.timeHandler.removeCallbacks(this.runnable);
  }

  public void startNewSL()
  {
    this.mSnifferSmartLinker = MulticastSmartLinker.getInstance();
    this.mSnifferSmartLinker.setOnSmartLinkListener(new OnSmartLinkListener()
    {
      public void onCompleted()
      {
        AtCfg4Activity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            AtCfg4Activity.this.info.setText(2131099922);
            AtCfg4Activity.this.cfgState = "ok";
            AtCfg4Activity.this.deviceConnet.setBackgroundResource(2130903587);
            AtCfg4Activity.access$002(AtCfg4Activity.this, false);
            AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
            AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
            AtCfg4Activity.this.ivCfgOk.setVisibility(0);
            AtCfg4Activity.this.ivCfgNoOk.setVisibility(8);
            AtCfg4Activity.this.smallSsecond.setVisibility(8);
            AtCfg4Activity.this.second.setVisibility(8);
          }
        });
      }

      public void onLinked(SmartLinkedModule paramSmartLinkedModule)
      {
      }

      public void onTimeOut()
      {
        AtCfg4Activity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            AtCfg4Activity.this.info.setText(2131099924);
            AtCfg4Activity.this.cfgState = "timeout";
            AtCfg4Activity.this.deviceConnet.setVisibility(8);
            AtCfg4Activity.this.ivBottomLeft.setVisibility(0);
            AtCfg4Activity.this.ivBottomRight.setVisibility(0);
            AtCfg4Activity.access$002(AtCfg4Activity.this, false);
            AtCfg4Activity.this.timeHandler.removeCallbacks(AtCfg4Activity.this.runnable);
            AtCfg4Activity.this.sbActOutletLed.setProgressSweep(0);
            AtCfg4Activity.this.ivCfgOk.setVisibility(8);
            AtCfg4Activity.this.smallSsecond.setVisibility(8);
            AtCfg4Activity.this.second.setVisibility(8);
            AtCfg4Activity.this.ivCfgNoOk.setVisibility(0);
          }
        });
      }
    });
    try
    {
      ISmartLinker localISmartLinker = this.mSnifferSmartLinker;
      String str = this.psd;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = this.wifiName;
      localISmartLinker.start(this, str, arrayOfString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void startSmartLink()
  {
    this.info.setText(2131099920);
    timer();
    if (!this.isconncting)
    {
      this.isconncting = true;
      startNewSL();
      return;
    }
    this.mSnifferSmartLinker.stop();
    this.isconncting = false;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.AtCfg4Activity
 * JD-Core Version:    0.6.0
 */