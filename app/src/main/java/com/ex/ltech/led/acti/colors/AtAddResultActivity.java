package com.ex.ltech.led.acti.colors;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtAddResultActivity extends MyBaseActivity
{
  public void ok(View paramView)
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968655);
    setViewTitle();
    setMenuBackgroundRes(2130903830);
    if (getIntent().getBooleanExtra("isConfigOk", false))
    {
      findViewById(2131558802).setVisibility(0);
      setTiTleTextRes(2131099823);
      return;
    }
    setTiTleTextRes(2131099817);
    findViewById(2131558820).setVisibility(0);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void reConfig(View paramView)
  {
    startActivity(new Intent(this, AtPanelLearnActivity.class).putExtra("type", getIntent().getStringExtra("type")));
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.colors.AtAddResultActivity
 * JD-Core Version:    0.6.0
 */