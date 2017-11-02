package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ex.ltech.led.Global;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfg1Activity extends MyBaseActivity
{
  public void bulb(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "bulb");
    startActivityForResult(localIntent, 200);
  }

  public void ct(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "ct");
    startActivityForResult(localIntent, 200);
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
    setContentView(2130968666);
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131099932);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onePiontFive(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "onePiontFive");
    startActivityForResult(localIntent, 200);
  }

  public void plugs(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "plugs");
    startActivityForResult(localIntent, 200);
  }

  public void remote(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "remote");
    startActivityForResult(localIntent, 200);
  }

  public void rgb(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "rgb");
    startActivityForResult(localIntent, 200);
  }

  public void rgbHV(View paramView)
  {
    Intent localIntent = new Intent(this, AtCfg2Activity.class);
    localIntent.putExtra("cfgType", "rgbhv");
    startActivityForResult(localIntent, 200);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.AtCfg1Activity
 * JD-Core Version:    0.6.0
 */