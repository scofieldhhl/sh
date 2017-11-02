package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtMotorBrank extends MyBaseActivity
{
  private void setTilteView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131099816);
    setBgWhite();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    setResult(paramInt2);
    finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131558525:
      goAct4result(AtYkCfgHelpActivity.class, 0, "cfg_type", 0);
      return;
    case 2131558526:
      goAct4result(AtYkCfgHelpActivity.class, 0, "cfg_type", 1);
      return;
    case 2131558625:
    case 2131558626:
    case 2131558627:
    }
    goAct4result(AtYkCfgHelpActivity.class, 0, "cfg_type", 2);
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968621);
    setTilteView();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtMotorBrank
 * JD-Core Version:    0.6.0
 */