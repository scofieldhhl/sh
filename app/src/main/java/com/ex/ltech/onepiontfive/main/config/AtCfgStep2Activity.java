package com.ex.ltech.onepiontfive.main.config;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfgStep2Activity extends MyBaseActivity
{
  private ImageView center;
  private boolean changeBg = true;
  private int chosenType = 0;
  Handler handler = new Handler();
  private TextView info1;
  private TextView info2;
  private ImageView ivDevice;

  @Bind({2131558807})
  ImageView ivDeviceTop;
  private int res1;
  private int res2;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      AtCfgStep2Activity.this.loopPic();
    }
  };

  private void loopPic()
  {
    if ((getIntent().getStringExtra("cfgType").equals("rgb") | getIntent().getStringExtra("cfgType").equals("ct")))
      if (this.changeBg)
      {
        this.ivDeviceTop.setBackgroundResource(this.res1);
        if (this.changeBg)
          break label129;
      }
    label129: for (boolean bool = true; ; bool = false)
    {
      this.changeBg = bool;
      this.handler.postDelayed(this.runnable, 2000L);
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

  public void cancel(View paramView)
  {
    finish();
  }

  public void next(View paramView)
  {
    int i = getIntent().getIntExtra("mPosition", -1);
    startActivityForResult(new Intent(this, AtCfgStepFourActivity.class).putExtra("from105", true).putExtra("mPosition", i).putExtra("type", this.chosenType), 200);
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
    setContentView(2130968652);
    ButterKnife.bind(this);
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleText("");
    this.ivDevice = ((ImageView)findViewById(2131558808));
    this.info1 = ((TextView)findViewById(2131558809));
    this.info2 = ((TextView)findViewById(2131558810));
    this.center = ((ImageView)findViewById(2131558457));
    getIntent().getStringExtra("ds");
    String str = getIntent().getStringExtra("cfgType");
    if (str.equals("rlPlug"))
    {
      this.res1 = 2130903123;
      this.res2 = 2130903124;
      this.info1.setText(2131099900);
      this.info2.setText(2131099893);
      this.chosenType = 98;
    }
    if (str.equals("rlRc"))
    {
      this.res1 = 2130903132;
      this.res2 = 2130903133;
      this.info1.setText(2131099902);
      this.info2.setText(2131099895);
      this.chosenType = 97;
    }
    if (str.equals("rlSensor"))
    {
      this.res1 = 2130903127;
      this.res2 = 2130903129;
      this.info2.setText(2131099897);
      this.chosenType = 81;
    }
    if (str.equals("rlPanel"))
    {
      this.res1 = 2130903121;
      this.res2 = 2130903122;
      this.info2.setText(2131099898);
      this.chosenType = 34;
    }
    loopPic();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    this.handler.removeCallbacks(this.runnable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStep2Activity
 * JD-Core Version:    0.6.0
 */