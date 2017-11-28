package com.ex.ltech.led.acti.timing.fragment;

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

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.timing.act.ActMode;
import com.ex.ltech.led.my_view.MySeekBar;
import com.ex.ltech.led.my_view.SimpleColorPickerView;

public class ColorFragment extends Fragment
  implements SimpleColorPickerView.OnColorChangedListener, OnSeekBarChangeListener, MySeekBar.onMySBtouchListener, SimpleColorPickerView.OnColorPickerViewOk
{
  public int b;
  public int brt = 64;
  public int color;
  private SimpleColorPickerView cp_acti_timing_fragment_col;
  public int g;
  public View mRootView = null;
  private MySeekBar msb_color_acti_timing_fragment_col;
  public int r;
  private SeekBar sb_acti_timing_fragment_col;
  public int w;

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.sb_acti_timing_fragment_col = ((SeekBar)this.mRootView.findViewById(R.id.sb_acti_timing_fragment_col));
    this.msb_color_acti_timing_fragment_col = ((MySeekBar)this.mRootView.findViewById(R.id.msb_color_acti_timing_fragment_col));
    this.cp_acti_timing_fragment_col = ((SimpleColorPickerView)this.mRootView.findViewById(R.id.cp_acti_timing_fragment_col));
    this.cp_acti_timing_fragment_col.setOpenType("rgbTimingColorFragment");
    this.cp_acti_timing_fragment_col.setListener(this);
    this.sb_acti_timing_fragment_col.setOnSeekBarChangeListener(this);
    this.msb_color_acti_timing_fragment_col.setListener(this);
    this.cp_acti_timing_fragment_col.setOnColorPickerViewOk(this);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        ColorFragment.this.color = ColorFragment.this.cp_acti_timing_fragment_col.nowColor;
        ColorFragment.this.r = ColorFragment.this.cp_acti_timing_fragment_col.strR;
        ColorFragment.this.g = ColorFragment.this.cp_acti_timing_fragment_col.strG;
        ColorFragment.this.b = ColorFragment.this.cp_acti_timing_fragment_col.strB;
        ColorFragment.this.w = (12 + 243 * ColorFragment.this.sb_acti_timing_fragment_col.getProgress() / 100);
        ColorFragment.this.brt = 64;
        ColorFragment.this.msb_color_acti_timing_fragment_col.setProgressColor(ColorFragment.this.color);
        ColorFragment.this.msb_color_acti_timing_fragment_col.setProgress(100);
      }
    }
    , 500L);
  }

  public void onColorFragSeleted()
  {
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(R.layout.fragment_timing_color, paramViewGroup, false);
    System.out.println("onCreateView");
    return this.mRootView;
  }

  public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 < 13)
      return;
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
    this.w = (12 + 243 * paramSeekBar.getProgress() / 100);
  }

  public void onProgressPercent(float paramFloat)
  {
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

  public void setPactHeight()
  {
    this.cp_acti_timing_fragment_col.setPactHeight(ActMode.sonActHeightWithouTitle);
  }
}
