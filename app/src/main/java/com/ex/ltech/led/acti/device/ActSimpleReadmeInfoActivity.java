package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ex.ltech.led.acti.MyBaseActivity;

public class ActSimpleReadmeInfoActivity extends MyBaseActivity
{
  public void onBackPressed()
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968622);
    int i = getIntent().getIntExtra("page", 0);
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    if (i != 7)
      setTiTleTextRes(2131100082);
    while (true)
      switch (i)
      {
      default:
        return;
        setTiTleTextRes(2131099810);
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      }
    findViewById(2131558628).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558629).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558630).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558631).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558632).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558633).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558634).setVisibility(View.VISIBLE);
    return;
    findViewById(2131558635).setVisibility(View.VISIBLE);
    setTiTleTextRes(2131099810);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ActSimpleReadmeInfoActivity
 * JD-Core Version:    0.6.0
 */