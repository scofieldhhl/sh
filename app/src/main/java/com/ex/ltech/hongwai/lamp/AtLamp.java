package com.ex.ltech.hongwai.lamp;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.my_view.MyRainbowSeekBar;
import com.ex.ltech.led.my_view.MyRainbowSeekBar.onMySBtouchListener;
import com.ex.ltech.led.my_view.MySeekBar;
import com.ex.ltech.led.my_view.MySeekBar.onMySBtouchListener;

public class AtLamp extends MyBaseActivity
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
  private CmdDateBussiness cmdDateBussiness;
  int g;

  @Bind({2131558744})
  LinearLayout ll_bot_act_outlet_led;

  @Bind({2131558873})
  ImageButton off;

  @Bind({2131558874})
  ImageButton on;
  boolean onOff = true;
  int r;

  @Bind({2131558856})
  RelativeLayout rlOff;

  @Bind({2131558996})
  ImageView roomCoverLay;

  @Bind({2131558741})
  MyRainbowSeekBar sbMyRainbow;

  @Bind({2131558998})
  MySeekBar sb_act_outlet_led;
  int time = 0;

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
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100061);
    setBgWhite();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968696);
    ButterKnife.bind(this);
    setMyTitle();
    resetColorBtn();
    this.cmdDateBussiness = new CmdDateBussiness(this, "0000");
    this.sb_act_outlet_led.setListener(new MySeekBar.onMySBtouchListener()
    {
      public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      {
        AtLamp localAtLamp = AtLamp.this;
        localAtLamp.time = (1 + localAtLamp.time);
        if (AtLamp.this.time % 3 == 0)
          SocketManager.instance().sendData(AtLamp.this.cmdDateBussiness.getColorCmd(209, paramInt4, paramInt1, paramInt2, paramInt3, 255));
      }

      public void onMySbUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      {
        SocketManager.instance().sendData(AtLamp.this.cmdDateBussiness.getColorCmd(209, paramInt4, paramInt1, paramInt2, paramInt3, 255));
      }
    });
    this.sbMyRainbow.setListener(new MyRainbowSeekBar.onMySBtouchListener()
    {
      public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
      {
        AtLamp.this.off.setBackgroundResource(2130903599);
        AtLamp.this.roomCoverLay.setVisibility(View.VISIBLE);
        AtLamp.this.roomCoverLay.setBackgroundColor(Color.argb(80, paramInt2, paramInt3, paramInt4));
        AtLamp localAtLamp = AtLamp.this;
        localAtLamp.time = (1 + localAtLamp.time);
        if (AtLamp.this.time % 3 == 0)
          SocketManager.instance().sendData(AtLamp.this.cmdDateBussiness.getColorCmd(210, AtLamp.this.bright, paramInt2, paramInt3, paramInt4, 255));
      }

      public void onMySbBrightUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
      {
        SocketManager.instance().sendData(AtLamp.this.cmdDateBussiness.getColorCmd(210, AtLamp.this.bright, paramInt2, paramInt3, paramInt4, 255));
      }
    });
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, paramInt5, paramInt2, paramInt3, paramInt4, 0));
    this.sb_act_outlet_led.setProgressColor(paramInt1);
    this.r = Color.red(paramInt1);
    this.g = Color.green(paramInt1);
    this.b = Color.blue(paramInt1);
  }

  public void onMySbBrightUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
  }

  public void onOff(View paramView)
  {
    if (this.onOff)
    {
      this.rlOff.setVisibility(View.GONE);
      SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(209, 255, this.r, this.g, this.b, 255));
      this.roomCoverLay.setVisibility(View.VISIBLE);
      if (this.onOff)
        break label125;
    }
    label125: for (boolean bool = true; ; bool = false)
    {
      this.onOff = bool;
      return;
      SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(209, 0, this.r, this.g, this.b, 255));
      this.roomCoverLay.setVisibility(View.GONE);
      this.rlOff.setVisibility(View.VISIBLE);
      break;
    }
  }

  protected void onResume()
  {
    super.onResume();
    this.sb_act_outlet_led.setMiddleProgress();
  }

  public void setColor1(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903424);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492938));
    this.r = 255;
    this.g = 0;
    this.b = 0;
    this.sbMyRainbow.setProgress(96);
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492939));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor2(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903426);
    this.r = 255;
    this.g = 0;
    this.b = 40;
    this.sbMyRainbow.setProgress(90);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492940));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492941));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor3(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903428);
    this.r = 255;
    this.g = 91;
    this.b = 0;
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492942));
    this.sbMyRainbow.setProgress(18);
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492943));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor4(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903430);
    this.r = 35;
    this.g = 255;
    this.b = 35;
    this.sbMyRainbow.setProgress(38);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492944));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492945));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor5(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903432);
    this.r = 0;
    this.g = 221;
    this.b = 255;
    this.sbMyRainbow.setProgress(55);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492946));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492947));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor6(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903434);
    this.r = 0;
    this.g = 100;
    this.b = 255;
    this.sbMyRainbow.setProgress(61);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492948));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492949));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor7(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903436);
    this.r = 180;
    this.g = 0;
    this.b = 200;
    this.sbMyRainbow.setProgress(79);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492951));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492951));
    this.off.setBackgroundResource(2130903599);
  }

  public void setColor8(View paramView)
  {
    resetColorBtn();
    ((Button)paramView).setBackgroundResource(2130903438);
    this.r = 255;
    this.g = 230;
    this.b = 240;
    this.sbMyRainbow.setProgress(30);
    this.sb_act_outlet_led.setProgressColor(getResources().getColor(2131492952));
    SocketManager.instance().sendData(this.cmdDateBussiness.getColorCmd(210, this.bright, this.r, this.g, this.b, 255));
    this.roomCoverLay.setBackgroundColor(getResources().getColor(2131492953));
    this.off.setBackgroundResource(2130903598);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.lamp.AtLamp
 * JD-Core Version:    0.6.0
 */