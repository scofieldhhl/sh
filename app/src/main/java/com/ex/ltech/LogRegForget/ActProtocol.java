package com.ex.ltech.LogRegForget;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;

public class ActProtocol extends MyBaseActivity
{
  boolean isZh;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968613);
    ((TextView)findViewById(2131558604)).setMovementMethod(ScrollingMovementMethod.getInstance());
    ((TextView)findViewById(2131558605)).setMovementMethod(ScrollingMovementMethod.getInstance());
    setTitleView();
    this.isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
    if (this.isZh)
    {
      findViewById(2131558604).setVisibility(0);
      return;
    }
    findViewById(2131558605).setVisibility(0);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(2131100493);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActProtocol
 * JD-Core Version:    0.6.0
 */