package com.ex.ltech.onepiontfive.main.config;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;

public class FtAddDeviceList extends MyBaseFt
  implements View.OnClickListener
{

  @Bind({2131558781})
  RippleView btnTitleViewMenu;

  @Bind({2131559141})
  RelativeLayout rlPanel;

  @Bind({2131559139})
  RelativeLayout rlPlug;

  @Bind({2131558538})
  RelativeLayout rlRc;

  @Bind({2131559140})
  RelativeLayout rlSensor;

  @Bind({2131558783})
  TextView tvTitleViewTitle;

  private void goConfigNext(String paramString)
  {
    Intent localIntent = new Intent(getActivity(), AtCfgStepTwoActivity.class);
    localIntent.putExtra("cfgType", paramString);
    localIntent.putExtra("mPosition", getRequest().getIntExtra("mPosition", -1));
    startActivityForResult(localIntent, 1);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1205)
    {
      setResult(1205);
      finish();
    }
  }

  public void onClick(View paramView)
  {
    if (paramView == this.btnTitleViewMenu)
      finish();
    if (paramView == this.rlPlug)
      goConfigNext("plugs");
    if (paramView == this.rlRc)
      goConfigNext("rc");
    if (paramView == this.rlSensor)
      goConfigNext("sensor");
    if (paramView == this.rlPanel)
      goConfigNext("panel");
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    View localView = paramLayoutInflater.inflate(2130968742, null);
    ButterKnife.bind(this, localView);
    return localView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
  }

  @OnClick({2131559142, 2131559143, 2131559144})
  public void onViewClicked(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131559142:
      goConfigNext("panel3");
      return;
    case 2131559143:
      goConfigNext("panel2");
      return;
    case 2131559144:
    }
    goConfigNext("panel1");
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btnTitleViewMenu.setOnClickListener(this);
    this.rlPlug.setOnClickListener(this);
    this.rlRc.setOnClickListener(this);
    this.rlSensor.setOnClickListener(this);
    this.rlPanel.setOnClickListener(this);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099932);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtAddDeviceList
 * JD-Core Version:    0.6.0
 */