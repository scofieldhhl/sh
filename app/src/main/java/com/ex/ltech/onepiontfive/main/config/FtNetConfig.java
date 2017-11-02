package com.ex.ltech.onepiontfive.main.config;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.fragmentmaster.app.Request;

public class FtNetConfig extends MyBaseFt
{

  @Bind({2131558736})
  Button btnActiNetConfigDeviceConnet;

  @Bind({2131558732})
  EditText etActiNetConfigWifiName;

  @Bind({2131559209})
  ImageView ivActiNetConfigGo;

  @Bind({2131559211})
  ProgressBar pbActiNetConfigLoading;

  @Bind({2131559208})
  RelativeLayout tvActiNetConfigDeviceStatus;

  @Bind({2131559210})
  TextView tvActiNetConfigLoading;

  @Bind({2131558733})
  EditText tvActiNetConfigPwd;

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968765, null);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        FtNetConfig.this.startFragment(new Request(FtCfg4.class));
      }
    }
    , 1000L);
    ButterKnife.bind(this, localView);
    return localView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtNetConfig
 * JD-Core Version:    0.6.0
 */