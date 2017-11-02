package com.ex.ltech.onepiontfive.main.config;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfgStepThreeActivity extends MyBaseActivity
{
  String cfgType;

  @Bind({2131558814})
  ImageView icCenter;

  @Bind({2131558815})
  ImageView icMatchParent;

  @Bind({2131558816})
  TextView info;
  boolean isLampBlinkSeleted;

  @Bind({2131558795})
  TextView next;

  @Bind({2131558818})
  TextView tvLampBlink;

  @Bind({2131558817})
  TextView tvLampBlinkSeleted;

  public void cancel(View paramView)
  {
    finish();
  }

  public void lampBlinkSeleted(View paramView)
  {
    if (!this.isLampBlinkSeleted);
    for (boolean bool = true; ; bool = false)
    {
      this.isLampBlinkSeleted = bool;
      if (!this.isLampBlinkSeleted)
        break;
      this.tvLampBlinkSeleted.setBackgroundResource(2130903786);
      return;
    }
    this.tvLampBlinkSeleted.setBackgroundResource(2130903784);
  }

  public void next(View paramView)
  {
    if (!this.isLampBlinkSeleted)
      return;
    int i = getIntent().getIntExtra("mPosition", -1);
    startActivityForResult(new Intent(this, AtCfgStepFourActivity.class).putExtra("from105", true).putExtra("mPosition", i).putExtra("cfgType", this.cfgType), 200);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1205)
    {
      setResult(1205);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968653);
    ButterKnife.bind(this);
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    this.tvLampBlink.setText(2131100064);
    this.cfgType = getIntent().getStringExtra("cfgType");
    String str = this.cfgType;
    int i = -1;
    switch (str.hashCode())
    {
    default:
    case 106767525:
    case 3633:
    case -905948230:
    case -995543379:
    case -995543378:
    case -995543377:
    case 106433028:
    case 3314136:
    }
    while (true)
      switch (i)
      {
      case 0:
      case 1:
      default:
        return;
        if (!str.equals("plugs"))
          continue;
        i = 0;
        continue;
        if (!str.equals("rc"))
          continue;
        i = 1;
        continue;
        if (!str.equals("sensor"))
          continue;
        i = 2;
        continue;
        if (!str.equals("panel1"))
          continue;
        i = 3;
        continue;
        if (!str.equals("panel2"))
          continue;
        i = 4;
        continue;
        if (!str.equals("panel3"))
          continue;
        i = 5;
        continue;
        if (!str.equals("panel"))
          continue;
        i = 6;
        continue;
        if (!str.equals("lamp"))
          continue;
        i = 7;
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      }
    setTiTleTextRes(2131099851);
    this.info.setText(2131100246);
    this.icMatchParent.setBackgroundResource(2130903128);
    this.next.setText(2131099892);
    return;
    setTiTleTextRes(2131099851);
    this.info.setText(2131100245);
    this.icMatchParent.setBackgroundResource(2130903108);
    this.next.setText(2131099892);
    return;
    setTiTleTextRes(2131099851);
    this.info.setText(2131100245);
    this.icMatchParent.setBackgroundResource(2130903110);
    this.next.setText(2131099892);
    return;
    setTiTleTextRes(2131099851);
    this.info.setText(2131100245);
    this.icMatchParent.setBackgroundResource(2130903111);
    this.next.setText(2131099892);
    return;
    setTiTleTextRes(2131099851);
    this.info.setText(2131100245);
    this.icMatchParent.setBackgroundResource(2130903113);
    this.next.setText(2131099892);
    return;
    setTiTleTextRes(2131099852);
    this.info.setText(2131100244);
    this.icMatchParent.setBackgroundResource(2130903447);
    this.next.setText(2131100379);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStepThreeActivity
 * JD-Core Version:    0.6.0
 */