package com.ex.ltech.remote.control.atYaoKongs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.mode.ModeBusiness;
import com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity;
import com.indris.material.RippleView;
import java.io.PrintStream;

public class NumFragment extends Fragment
  implements View.OnClickListener, View.OnLongClickListener
{

  @Bind({2131559112})
  RippleView k0;

  @Bind({2131558935})
  RippleView k1;

  @Bind({2131558945})
  RippleView k11;

  @Bind({2131558936})
  RippleView k2;

  @Bind({2131558937})
  RippleView k3;

  @Bind({2131558938})
  RippleView k4;

  @Bind({2131558939})
  RippleView k5;

  @Bind({2131558940})
  RippleView k6;

  @Bind({2131558941})
  RippleView k7;

  @Bind({2131558942})
  RippleView k8;

  @Bind({2131558943})
  RippleView k9;
  private View mRootView = null;
  private ModeBusiness modeBusiness;
  AtNewTvActivity pAt;

  public void initListener()
  {
    this.pAt = ((AtNewTvActivity)getActivity());
    this.k1.setOnClickListener(this);
    this.k1.setOnLongClickListener(this);
    this.k2.setOnClickListener(this);
    this.k2.setOnLongClickListener(this);
    this.k3.setOnClickListener(this);
    this.k3.setOnLongClickListener(this);
    this.k4.setOnClickListener(this);
    this.k4.setOnLongClickListener(this);
    this.k5.setOnClickListener(this);
    this.k5.setOnLongClickListener(this);
    this.k6.setOnClickListener(this);
    this.k6.setOnLongClickListener(this);
    this.k7.setOnClickListener(this);
    this.k7.setOnLongClickListener(this);
    this.k8.setOnClickListener(this);
    this.k8.setOnLongClickListener(this);
    this.k9.setOnClickListener(this);
    this.k9.setOnLongClickListener(this);
    this.k0.setOnClickListener(this);
    this.k0.setOnLongClickListener(this);
    this.k11.setOnClickListener(this);
    this.k11.setOnLongClickListener(this);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.k0)
      this.pAt.k10();
    if (paramView == this.k1)
      this.pAt.k1();
    if (paramView == this.k2)
      this.pAt.k2();
    if (paramView == this.k3)
      this.pAt.k3();
    if (paramView == this.k4)
      this.pAt.k4();
    if (paramView == this.k5)
      this.pAt.k5();
    if (paramView == this.k6)
      this.pAt.k6();
    if (paramView == this.k7)
      this.pAt.k7();
    if (paramView == this.k8)
      this.pAt.k8();
    if (paramView == this.k9)
      this.pAt.k9();
    if (paramView == this.k11)
      this.pAt.k11();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968734, paramViewGroup, false);
    System.out.println("onCreateView");
    ButterKnife.bind(this, this.mRootView);
    initListener();
    return this.mRootView;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public boolean onLongClick(View paramView)
  {
    this.pAt.onLongClick(paramView);
    return false;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.fragment.NumFragment
 * JD-Core Version:    0.6.0
 */