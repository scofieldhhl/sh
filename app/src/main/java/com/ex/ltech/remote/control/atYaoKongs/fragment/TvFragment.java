package com.ex.ltech.remote.control.atYaoKongs.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity;
import com.indris.material.RippleView;
import java.io.PrintStream;

public class TvFragment extends Fragment
  implements View.OnClickListener, View.OnLongClickListener
{

  @Bind({2131558858})
  RippleView back;

  @Bind({2131559115})
  RippleView centerMenu;

  @Bind({2131559116})
  RippleView chAdd;

  @Bind({2131559117})
  RippleView chSub;
  public View mRootView = null;

  @Bind({2131558970})
  RippleView menu;

  @Bind({2131558874})
  RippleView on;
  AtNewTvActivity pAt;

  @Bind({2131559113})
  RippleView quiet;

  @Bind({2131559114})
  RippleView res;

  @Bind({2131558991})
  TextView tvZoom;
  private String type;

  @Bind({2131558988})
  TextView vol;

  @Bind({2131558974})
  RippleView voladd;

  @Bind({2131558975})
  RippleView volsub;

  private void initListener()
  {
    this.pAt = ((AtNewTvActivity)getActivity());
    this.quiet.setOnClickListener(this);
    this.quiet.setOnLongClickListener(this);
    this.res.setOnClickListener(this);
    this.res.setOnLongClickListener(this);
    this.centerMenu.setOnClickListener(this);
    this.centerMenu.setOnLongClickListener(this);
    this.menu.setOnClickListener(this);
    this.menu.setOnLongClickListener(this);
    this.on.setOnClickListener(this);
    this.on.setOnLongClickListener(this);
    this.voladd.setOnClickListener(this);
    this.voladd.setOnLongClickListener(this);
    this.volsub.setOnClickListener(this);
    this.volsub.setOnLongClickListener(this);
    this.back.setOnClickListener(this);
    this.back.setOnLongClickListener(this);
    this.chAdd.setOnClickListener(this);
    this.chAdd.setOnLongClickListener(this);
    this.chSub.setOnClickListener(this);
    this.chSub.setOnLongClickListener(this);
  }

  public void onClick(View paramView)
  {
    if (paramView == this.quiet)
      this.pAt.queit();
    if (paramView == this.res)
      this.pAt.res();
    if (paramView == this.centerMenu)
      this.pAt.menu();
    if (paramView == this.menu)
      this.pAt.menu();
    if (paramView == this.on)
      this.pAt.onOff();
    if (paramView == this.voladd)
      this.pAt.volAdd();
    if (paramView == this.volsub)
      this.pAt.volStub();
    if (paramView == this.back)
      this.pAt.back();
    if (paramView == this.chAdd)
      this.pAt.chAdd();
    if (paramView == this.chSub)
      this.pAt.chStub();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968735, paramViewGroup, false);
    System.out.println("onCreateView");
    ButterKnife.bind(this, this.mRootView);
    if (this.type.equals("tv"))
      this.centerMenu.setVisibility(4);
    while (true)
    {
      if (this.type.equals("tvbox"))
        this.quiet.setBackgroundResource(2130903225);
      initListener();
      return this.mRootView;
      this.res.setVisibility(4);
      this.menu.setVisibility(4);
    }
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

  public void onResume()
  {
    super.onResume();
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.fragment.TvFragment
 * JD-Core Version:    0.6.0
 */