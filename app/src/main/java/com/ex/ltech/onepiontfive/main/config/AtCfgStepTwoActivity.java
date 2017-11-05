package com.ex.ltech.onepiontfive.main.config;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfgStepTwoActivity extends MyBaseActivity
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
    startActivityForResult(new Intent(this, AtCfgStepThreeActivity.class).putExtra("cfgType", this.cfgType).putExtra("mPosition", getIntent().getIntExtra("mPosition", -1)), 200);
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
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleText("");
    this.tvLampBlink.setText(2131099884);
    this.icCenter.setBackgroundResource(2130903112);
    this.info.setText(2131100243);
    this.cfgType = getIntent().getStringExtra("cfgType");
    String str = this.cfgType;
    int i = -1;
    switch (str.hashCode())
    {
    default:
    case 3314136:
    }
    while (true)
      switch (i)
      {
      default:
        setTiTleTextRes(2131099851);
        return;
        if (!str.equals("lamp"))
          continue;
        i = 1;
      case 1:
      }
    setTiTleTextRes(2131099852);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStepTwoActivity
 * JD-Core Version:    0.6.0
 */