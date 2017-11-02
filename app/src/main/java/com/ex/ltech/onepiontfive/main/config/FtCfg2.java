package com.ex.ltech.onepiontfive.main.config;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.fragmentmaster.app.Request;

public class FtCfg2 extends MyBaseFt
{

  @Bind({2131558457})
  ImageView center;

  @Bind({2131558809})
  TextView info1;

  @Bind({2131558810})
  TextView info2;

  @Bind({2131558808})
  ImageView ivDevice;

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968745, null);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        FtCfg2.this.startFragment(new Request(FtNetConfig.class));
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
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtCfg2
 * JD-Core Version:    0.6.0
 */