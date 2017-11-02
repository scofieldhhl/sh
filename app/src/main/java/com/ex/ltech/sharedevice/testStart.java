package com.ex.ltech.sharedevice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import com.ex.ltech.led.acti.MyBaseActivity;

public class testStart extends MyBaseActivity
{
  public static final String ACCOUNT = "account";
  public static final String ACCOUNT_MAIN = "308584004@qq.com";
  public static final String ACCOUNT_SLAVE = "13751753381";
  public static final int MAIN = 0;
  public static final int SLAVE = 1;
  public static final String TYPE = "type";

  public void onClick(View paramView)
  {
    Intent localIntent = new Intent(this, testMain.class);
    switch (paramView.getId())
    {
    default:
    case 2131559511:
    case 2131559512:
    }
    while (true)
    {
      startActivity(localIntent);
      return;
      localIntent.putExtra("type", 0);
      localIntent.putExtra("account", "308584004@qq.com");
      localIntent = new Intent(this, testMain.class);
      continue;
      localIntent.putExtra("type", 1);
      localIntent.putExtra("account", "13751753381");
      localIntent = new Intent(this, testSLAVE.class);
    }
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968894);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.sharedevice.testStart
 * JD-Core Version:    0.6.0
 */