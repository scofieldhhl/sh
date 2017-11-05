package com.ex.ltech.hongwai.yaokong.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.ex.ltech.hongwai.yaokong.AtYkLightMode;
import com.ex.ltech.led.my_view.MySeekBar;
import com.ex.ltech.led.my_view.MySeekBar.onMySBtouchListener;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.ex.ltech.led.my_view.SimpleColorPickerView.OnColorChangedListener;
import com.ex.ltech.led.my_view.SimpleColorPickerView.OnColorPickerViewOk;
import java.io.PrintStream;

public class AtYkLightColorFragment extends Fragment
  implements SimpleColorPickerView.OnColorChangedListener, SeekBar.OnSeekBarChangeListener, MySeekBar.onMySBtouchListener, SimpleColorPickerView.OnColorPickerViewOk
{
  public int b;
  public int brt = 255;
  public int c = 125;
  public int color;
  private SimpleColorPickerView cp_acti_timing_fragment_col;
  public int g;
  public View mRootView = null;
  private MySeekBar msb_color_acti_timing_fragment_col;
  public int r;
  private SeekBar sb_acti_timing_fragment_col;
  public int w = 130;

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.sb_acti_timing_fragment_col = ((SeekBar)this.mRootView.findViewById(2131559124));
    this.msb_color_acti_timing_fragment_col = ((MySeekBar)this.mRootView.findViewById(2131559123));
    this.cp_acti_timing_fragment_col = ((SimpleColorPickerView)this.mRootView.findViewById(2131559125));
    this.cp_acti_timing_fragment_col.setViewBgFullScreen(2130903079, false);
    this.cp_acti_timing_fragment_col.setListener(this);
    this.sb_acti_timing_fragment_col.setOnSeekBarChangeListener(this);
    this.msb_color_acti_timing_fragment_col.setListener(this);
    this.cp_acti_timing_fragment_col.setOnColorPickerViewOk(this);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AtYkLightColorFragment.this.color = AtYkLightColorFragment.this.cp_acti_timing_fragment_col.nowColor;
        AtYkLightColorFragment.this.r = AtYkLightColorFragment.this.cp_acti_timing_fragment_col.strR;
        AtYkLightColorFragment.this.g = AtYkLightColorFragment.this.cp_acti_timing_fragment_col.strG;
        AtYkLightColorFragment.this.b = AtYkLightColorFragment.this.cp_acti_timing_fragment_col.strB;
        AtYkLightColorFragment.this.msb_color_acti_timing_fragment_col.setProgressColor(AtYkLightColorFragment.this.color);
      }
    }
    , 500L);
    this.mRootView.findViewById(2131558871).setVisibility(View.GONE);
    this.mRootView.findViewById(2131558863).setVisibility(View.VISIBLE);
    saveColorData();
  }

  public void onColorFragSeleted()
  {
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968737, paramViewGroup, false);
    System.out.println("onCreateView");
    return this.mRootView;
  }

  public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.brt = paramInt4;
    System.out.println(this.brt);
  }

  public void onMySbUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  public void onPikerTouchUp(int paramInt)
  {
  }

  public void onPikerXYChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.msb_color_acti_timing_fragment_col.setProgressColor(paramInt1);
    this.color = paramInt1;
    this.r = Color.red(paramInt1);
    this.g = Color.green(paramInt1);
    this.b = Color.blue(paramInt1);
    this.b = Color.blue(paramInt1);
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.brt = (255 * paramSeekBar.getProgress() / 100);
    saveColorData();
  }

  public void onProgressPercent(float paramFloat)
  {
    if (100 * (int)(255.0F * paramFloat) / 100 < 256)
      this.w = (100 * (int)(255.0F * paramFloat) / 100);
    if (100 * (255 - this.w) / 100 < 256)
      this.c = (100 * (255 - this.w) / 100);
    saveColorData();
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onViewOn()
  {
  }

  public void saveColorData()
  {
    ((AtYkLightMode)getActivity()).ctTimingAction.c = this.c;
    ((AtYkLightMode)getActivity()).ctTimingAction.w = this.w;
    ((AtYkLightMode)getActivity()).ctTimingAction.brt = this.brt;
  }

  public void setPactHeight(int paramInt)
  {
    this.cp_acti_timing_fragment_col.setpActHeight(paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.fragment.AtYkLightColorFragment
 * JD-Core Version:    0.6.0
 */