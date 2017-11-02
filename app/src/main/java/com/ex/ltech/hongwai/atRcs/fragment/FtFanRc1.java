package com.ex.ltech.hongwai.atRcs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.view.ImageTextButton;
import com.zhy.android.percent.support.PercentRelativeLayout;
import java.util.List;

public class FtFanRc1 extends Fragment
  implements View.OnClickListener
{
  private ImageTextButton btOff;
  private ImageTextButton btQuiet;
  private ImageTextButton btSpeed;
  private ImageTextButton btSweep;
  private ImageTextButton btTime;
  private ImageTextButton btType;
  AtFan pat;
  private PercentRelativeLayout rlAtFanYk;

  public void onClick(View paramView)
  {
    if (((ImageTextButton)paramView).isUnableBtn);
    do
    {
      return;
      if (paramView == this.btOff)
        this.pat.on();
      if (paramView == this.btTime)
        this.pat.time();
      if (paramView == this.btSpeed)
        this.pat.speed();
      if (paramView == this.btQuiet)
        this.pat.mute();
      if (paramView != this.btType)
        continue;
      this.pat.type();
    }
    while (paramView != this.btSweep);
    this.pat.shake();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968750, null);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.pat = ((AtFan)getActivity());
    this.rlAtFanYk = ((PercentRelativeLayout)paramView.findViewById(2131559152));
    this.btOff = ((ImageTextButton)paramView.findViewById(2131558847));
    this.btTime = ((ImageTextButton)paramView.findViewById(2131559153));
    this.btSpeed = ((ImageTextButton)paramView.findViewById(2131558848));
    this.btQuiet = ((ImageTextButton)paramView.findViewById(2131559154));
    this.btType = ((ImageTextButton)paramView.findViewById(2131559155));
    this.btSweep = ((ImageTextButton)paramView.findViewById(2131558854));
    this.btOff.setOnClickListener(this);
    this.btTime.setOnClickListener(this);
    this.btSpeed.setOnClickListener(this);
    this.btQuiet.setOnClickListener(this);
    this.btType.setOnClickListener(this);
    this.btSweep.setOnClickListener(this);
    this.btOff.setImageTextButtonLongClickListener(this.pat);
    this.btTime.setImageTextButtonLongClickListener(this.pat);
    this.btSpeed.setImageTextButtonLongClickListener(this.pat);
    this.btQuiet.setImageTextButtonLongClickListener(this.pat);
    this.btType.setImageTextButtonLongClickListener(this.pat);
    this.btSweep.setImageTextButtonLongClickListener(this.pat);
  }

  public void showUnavailableFanKey(List<String> paramList)
  {
    this.btOff.reset();
    this.btSpeed.reset();
    this.btType.reset();
    this.btTime.reset();
    this.btQuiet.reset();
    this.btSweep.reset();
    int i = 0;
    if (i < paramList.size())
    {
      String str = (String)paramList.get(i);
      label128: int j;
      switch (str.hashCode())
      {
      default:
        j = -1;
        label131: switch (j)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        }
      case 958459:
      case 1249553:
      case 1244525:
      case 753052:
      case 1239994:
      case 834888:
      }
      while (true)
      {
        i++;
        break;
        if (!str.equals("电源"))
          break label128;
        j = 0;
        break label131;
        if (!str.equals("风速"))
          break label128;
        j = 1;
        break label131;
        if (!str.equals("风类"))
          break label128;
        j = 2;
        break label131;
        if (!str.equals("定时"))
          break label128;
        j = 3;
        break label131;
        if (!str.equals("静音"))
          break label128;
        j = 4;
        break label131;
        if (!str.equals("摆风"))
          break label128;
        j = 5;
        break label131;
        this.btOff.setUnableBtn(2130903248);
        continue;
        this.btSpeed.setUnableBtn(2130903249);
        continue;
        this.btType.setUnableBtn(2130903250);
        continue;
        this.btTime.setUnableBtn(2130903251);
        continue;
        this.btQuiet.setUnableBtn(2130903252);
        continue;
        this.btSweep.setUnableBtn(2130903253);
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.fragment.FtFanRc1
 * JD-Core Version:    0.6.0
 */