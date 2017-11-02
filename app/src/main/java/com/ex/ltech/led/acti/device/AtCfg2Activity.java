package com.ex.ltech.led.acti.device;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.Global;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfg2Activity extends MyBaseActivity
{
  private ImageView center;
  private boolean changeBg = true;
  Handler handler = new Handler();
  private TextView indicatori_light_status;
  private TextView info1;
  private TextView info2;
  private TextView info3;
  boolean isLampBlinkSeleted;
  private ImageView ivDevice;
  ImageView ivDeviceTop;
  private int res1;
  private int res2;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      AtCfg2Activity.this.loopPic();
    }
  };
  Runnable runnable2 = new Runnable()
  {
    public void run()
    {
      AtCfg2Activity.this.loopRcPic();
    }
  };
  private TextView tv_lamp_blink;
  private TextView tv_lamp_blink_seleted;

  @TargetApi(17)
  private double getScreenSizeOfDevice2()
  {
    Point localPoint = new Point();
    getWindowManager().getDefaultDisplay().getRealSize(localPoint);
    DisplayMetrics localDisplayMetrics = getResources().getDisplayMetrics();
    double d = Math.sqrt(Math.pow(localPoint.x / localDisplayMetrics.xdpi, 2.0D) + Math.pow(localPoint.y / localDisplayMetrics.ydpi, 2.0D));
    Log.d("ContentValues", "Screen inches : " + d);
    return d;
  }

  private void loopPic()
  {
    if ((getIntent().getStringExtra("cfgType").equals("rgbhv") | getIntent().getStringExtra("cfgType").equals("rgb") | getIntent().getStringExtra("cfgType").equals("ct")))
      if (this.changeBg)
      {
        this.ivDeviceTop.setBackgroundResource(this.res1);
        if (this.changeBg)
          break label144;
      }
    label144: for (boolean bool = true; ; bool = false)
    {
      this.changeBg = bool;
      this.handler.postDelayed(this.runnable, 1000L);
      return;
      this.ivDeviceTop.setBackgroundResource(this.res2);
      break;
      if (this.changeBg)
      {
        this.ivDevice.setBackgroundResource(this.res1);
        break;
      }
      this.ivDevice.setBackgroundResource(this.res2);
      break;
    }
  }

  private void loopRcPic()
  {
    if (this.changeBg)
    {
      findViewById(2131558883).setBackgroundResource(this.res1);
      if (this.changeBg)
        break label66;
    }
    label66: for (boolean bool = true; ; bool = false)
    {
      this.changeBg = bool;
      this.handler.postDelayed(this.runnable2, 1000L);
      return;
      findViewById(2131558883).setBackgroundResource(this.res2);
      break;
    }
  }

  public void cancel(View paramView)
  {
    finish();
  }

  public void goReset(View paramView)
  {
    startActivityForResult(new Intent(this, AtCfg2Activity.class).putExtra("cfgType", getIntent().getStringExtra("cfgType")).putExtra("isReset", true), 0);
  }

  public void lampBlinkSeleted(View paramView)
  {
    if (!this.isLampBlinkSeleted);
    ImageView localImageView;
    for (boolean bool = true; ; bool = false)
    {
      this.isLampBlinkSeleted = bool;
      localImageView = (ImageView)findViewById(2131558812);
      if (!this.isLampBlinkSeleted)
        break;
      localImageView.setBackgroundResource(2130903594);
      this.tv_lamp_blink_seleted.setBackgroundResource(2130903786);
      return;
    }
    localImageView.setBackgroundResource(2130903592);
    this.tv_lamp_blink_seleted.setBackgroundResource(2130903784);
  }

  public void next(View paramView)
  {
    if (!this.isLampBlinkSeleted)
      return;
    startActivityForResult(new Intent(this, AtCfg3Activity.class).putExtra("cfgType", getIntent().getStringExtra("cfgType")), 200);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == Global.net_config_ok)
    {
      setResult(Global.net_config_ok);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    ImageView localImageView2;
    TextView localTextView2;
    String str3;
    int j;
    if (getIntent().getBooleanExtra("isReset", false))
    {
      setContentView(2130968663);
      setViewTitle();
      setMenuBackgroundRes(2130903074);
      setTiTleTextRes(2131100453);
      localImageView2 = (ImageView)findViewById(2131558883);
      localTextView2 = (TextView)findViewById(2131558882);
      this.tv_lamp_blink_seleted = ((TextView)findViewById(2131558817));
      this.tv_lamp_blink = ((TextView)findViewById(2131558818));
      this.tv_lamp_blink.setText(2131099883);
      str3 = getIntent().getStringExtra("cfgType");
      switch (str3.hashCode())
      {
      default:
        j = -1;
        label179: switch (j)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
      case 108447387:
      case 112845:
      case 3185:
      case 106767525:
      case -934610874:
      case 1410339560:
      }
    }
    do
    {
      return;
      if (!str3.equals("rgbhv"))
        break;
      j = 0;
      break label179;
      if (!str3.equals("rgb"))
        break;
      j = 1;
      break label179;
      if (!str3.equals("ct"))
        break;
      j = 2;
      break label179;
      if (!str3.equals("plugs"))
        break;
      j = 3;
      break label179;
      if (!str3.equals("remote"))
        break;
      j = 4;
      break label179;
      if (!str3.equals("onePiontFive"))
        break;
      j = 5;
      break label179;
      localImageView2.setBackgroundResource(2130903440);
      localTextView2.setText(2131099998);
      return;
      localImageView2.setBackgroundResource(2130903440);
      localTextView2.setText(2131099998);
      return;
      localImageView2.setBackgroundResource(2130903440);
      localTextView2.setText(2131099998);
      return;
      this.res1 = 2130903633;
      this.res2 = 2130903634;
      loopRcPic();
      localTextView2.setText(2131100000);
      return;
      localTextView2.setText(2131100001);
      this.res1 = 2130903661;
      this.res2 = 2130903662;
      loopRcPic();
      return;
      ((TextView)(TextView)findViewById(2131558881)).setText(2131100342);
      localTextView2.setText(2131099999);
      localImageView2.setBackgroundResource(2130903112);
      return;
      if (getIntent().getBooleanExtra("isReset2", false))
      {
        setContentView(2130968664);
        TextView localTextView1 = (TextView)findViewById(2131558884);
        setViewTitle();
        setMenuBackgroundRes(2130903074);
        setTiTleTextRes(2131100452);
        ImageView localImageView1 = (ImageView)findViewById(2131558883);
        String str2 = getIntent().getStringExtra("cfgType");
        int i = -1;
        switch (str2.hashCode())
        {
        default:
        case 108447387:
        case 112845:
        case 3185:
        case 106767525:
        case -934610874:
        }
        while (true)
          switch (i)
          {
          default:
            return;
          case 0:
            localImageView1.setBackgroundResource(2130903440);
            localTextView1.setText(2131099998);
            return;
            if (!str2.equals("rgbhv"))
              continue;
            i = 0;
            continue;
            if (!str2.equals("rgb"))
              continue;
            i = 1;
            continue;
            if (!str2.equals("ct"))
              continue;
            i = 2;
            continue;
            if (!str2.equals("plugs"))
              continue;
            i = 3;
            continue;
            if (!str2.equals("remote"))
              continue;
            i = 4;
          case 1:
          case 2:
          case 3:
          case 4:
          }
        localImageView1.setBackgroundResource(2130903440);
        localTextView1.setText(2131099998);
        return;
        localImageView1.setBackgroundResource(2130903440);
        localTextView1.setText(2131099998);
        return;
        this.res1 = 2130903633;
        this.res2 = 2130903634;
        loopRcPic();
        localTextView1.setText(2131100000);
        return;
        this.res1 = 2130903661;
        this.res2 = 2130903662;
        loopRcPic();
        localTextView1.setText(2131100001);
        return;
      }
      setContentView(2130968662);
      setViewTitle();
      setMenuBackgroundRes(2130903074);
      this.ivDeviceTop = ((ImageView)findViewById(2131558807));
      this.ivDevice = ((ImageView)findViewById(2131558808));
      this.info1 = ((TextView)findViewById(2131558809));
      this.info2 = ((TextView)findViewById(2131558810));
      this.info3 = ((TextView)findViewById(2131558813));
      this.indicatori_light_status = ((TextView)findViewById(2131558880));
      this.tv_lamp_blink_seleted = ((TextView)findViewById(2131558817));
      this.tv_lamp_blink = ((TextView)findViewById(2131558818));
      this.center = ((ImageView)findViewById(2131558457));
      String str1 = getIntent().getStringExtra("cfgType");
      if (str1.equals("ct"))
      {
        this.res1 = 2130903117;
        this.res2 = 2130903118;
        this.ivDeviceTop.setVisibility(0);
        this.info3.setVisibility(0);
        setTiTleTextRes(2131099899);
        this.info2.setText(2131099896);
        this.tv_lamp_blink.setText(2131100123);
        this.indicatori_light_status.setVisibility(0);
      }
      if (str1.equals("plugs"))
      {
        this.res1 = 2130903123;
        this.res2 = 2130903124;
        setTiTleTextRes(2131099900);
        this.info2.setText(2131099893);
        this.tv_lamp_blink.setText(2131099883);
        this.indicatori_light_status.setVisibility(0);
        this.ivDeviceTop.setVisibility(0);
        this.info3.setVisibility(0);
      }
      if (str1.equals("bulb"))
      {
        this.res1 = 2130903117;
        this.res2 = 2130903118;
        setTiTleTextRes(2131099901);
        this.info2.setText(2131099894);
      }
      if (str1.equals("remote"))
      {
        this.res1 = 2130903132;
        this.res2 = 2130903133;
        setTiTleTextRes(2131099902);
        this.info2.setText(2131100196);
        this.tv_lamp_blink.setText(2131099883);
        this.indicatori_light_status.setVisibility(0);
        this.ivDeviceTop.setVisibility(0);
        this.info3.setVisibility(0);
      }
      if (str1.equals("rgb"))
      {
        this.res1 = 2130903125;
        this.res2 = 2130903126;
        this.ivDeviceTop.setVisibility(0);
        setTiTleTextRes(2131099903);
        this.info2.setText(2131099896);
        this.info3.setVisibility(0);
        this.tv_lamp_blink.setText(2131100123);
        this.indicatori_light_status.setVisibility(0);
      }
      if (str1.equals("rgbhv"))
      {
        this.res1 = 2130903125;
        this.res2 = 2130903126;
        this.ivDeviceTop.setVisibility(0);
        setTiTleTextRes(2131099904);
        this.info2.setText(2131099896);
        this.info3.setVisibility(0);
        this.tv_lamp_blink.setText(2131100123);
        this.indicatori_light_status.setVisibility(0);
      }
      if (str1.equals("onePiontFive"))
      {
        this.res1 = 2130903107;
        this.res2 = 2130903109;
        this.ivDeviceTop.setVisibility(0);
        setTiTleTextRes(2131099910);
        this.tv_lamp_blink.setText(2131099883);
        this.info2.setText(2131099897);
      }
      loopPic();
    }
    while (getScreenSizeOfDevice2() >= 4.0D);
    this.indicatori_light_status.setVisibility(8);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.handler.removeCallbacks(this.runnable);
    this.handler.removeCallbacks(this.runnable2);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.AtCfg2Activity
 * JD-Core Version:    0.6.0
 */