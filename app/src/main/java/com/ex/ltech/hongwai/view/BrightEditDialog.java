package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.widget.SeekBar;

import com.ex.ltech.led.R;

import butterknife.Bind;
import butterknife.OnClick;

public class BrightEditDialog extends BaseTipsDialog
{
  private OnListener mListener;

  @Bind({R.id.sb})
  SeekBar mSeekBar;

  public BrightEditDialog(Context paramContext)
  {
    super(paramContext, 1.0F, (int)(0.5F + 200.0F * paramContext.getResources().getDisplayMetrics().density), 80);
  }

  @OnClick({2131559094})
  public void cancel()
  {
    dismiss();
  }

  protected int getLayoutId()
  {
    return 2130968724;
  }

  protected void initView()
  {
    this.mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
      {
        if (BrightEditDialog.this.mListener != null)
          BrightEditDialog.this.mListener.onBrightProgressChanged(paramInt, false);
      }

      public void onStartTrackingTouch(SeekBar paramSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramSeekBar)
      {
        if (BrightEditDialog.this.mListener != null)
          BrightEditDialog.this.mListener.onBrightProgressChanged(paramSeekBar.getProgress(), true);
      }
    });
  }

  public BrightEditDialog setBrightMax(int paramInt)
  {
    this.mSeekBar.setMax(paramInt);
    return this;
  }

  public BrightEditDialog setBrightProgress(int paramInt)
  {
    this.mSeekBar.setProgress(paramInt);
    return this;
  }

  public BrightEditDialog setOnListener(OnListener paramOnListener)
  {
    this.mListener = paramOnListener;
    return this;
  }

  public static abstract interface OnListener
  {
    public abstract void onBrightProgressChanged(int paramInt, boolean paramBoolean);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.BrightEditDialog
 * JD-Core Version:    0.6.0
 */