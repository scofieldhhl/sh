package com.ex.ltech.onepiontfive.main.config;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.SecondArc;
import com.ex.ltech.onepiontfive.main.MyBaseFt;

public class FtCfg4 extends MyBaseFt
{

  @Bind({2131558877})
  Button deviceConnet;

  @Bind({2131558816})
  TextView info;

  @Bind({2131558879})
  ImageView ivCfgNoOk;

  @Bind({2131558878})
  ImageView ivCfgOk;

  @Bind({2131558742})
  SecondArc sbActOutletLed;

  @Bind({2131558875})
  TextView second;

  @Bind({2131558876})
  TextView smallSecond;

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968746, null);
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
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtCfg4
 * JD-Core Version:    0.6.0
 */