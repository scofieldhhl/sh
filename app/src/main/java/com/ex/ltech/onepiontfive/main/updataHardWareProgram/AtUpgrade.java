package com.ex.ltech.onepiontfive.main.updataHardWareProgram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import io.xlink.wifi.js.manage.DeviceManage;

public class AtUpgrade extends MyBaseActivity
  implements SynProgram2Device.SynListener
{

  @Bind({2131558800})
  RelativeLayout rlLoad;
  SynProgram2Device synProgram2Device;

  @Bind({2131559042})
  TextView tvVs1;

  @Bind({2131559043})
  TextView tvVs2;

  @Bind({2131559044})
  TextView tvVs3;

  private void setTitleView()
  {
    setViewTitle();
    setTiTleTextRes(2131100470);
    setMenuBackgroundRes(2130903274);
  }

  public void cancel(View paramView)
  {
    setResult(SynProgram2Device.UPDATA_CANCEL);
    finish();
  }

  public void failed()
  {
    this.rlLoad.setVisibility(8);
    Toast.makeText(this, "Upgrade Failed", 0).show();
  }

  public void ok()
  {
    this.rlLoad.setVisibility(8);
    Toast.makeText(this, "Upgrade Ok", 0).show();
    setResult(127);
    finish();
  }

  public void onBackPressed()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    DeviceManage.getInstance();
    this.synProgram2Device = new SynProgram2Device(this, DeviceManage.getxDevice(), getIntent().getStringExtra(SynProgram2Device.UPDATA_TYPE_KEY));
    this.synProgram2Device.setListener(this);
    setContentView(2130968707);
    ButterKnife.bind(this);
    setTitleView();
    try
    {
      if (!getIntent().getStringExtra(SynProgram2Device.UPDATA_VS_NAME).equalsIgnoreCase(""))
      {
        this.tvVs1.setText(this.tvVs1.getText().toString().substring(0, 1 + this.tvVs1.getText().toString().indexOf("V")) + getIntent().getStringExtra(SynProgram2Device.UPDATA_VS_NAME));
        this.tvVs2.setText(this.tvVs2.getText().toString().substring(0, 1 + this.tvVs2.getText().toString().indexOf("V")) + getIntent().getStringExtra(SynProgram2Device.UPDATA_VS_NAME));
        this.tvVs3.setText(this.tvVs3.getText().toString().substring(0, 1 + this.tvVs3.getText().toString().indexOf("V")) + getIntent().getStringExtra(SynProgram2Device.UPDATA_VS_NAME));
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.synProgram2Device.close();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onPercent(int paramInt)
  {
  }

  public void upgrade(View paramView)
  {
    this.rlLoad.setVisibility(0);
    this.synProgram2Device.syn();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.updataHardWareProgram.AtUpgrade
 * JD-Core Version:    0.6.0
 */