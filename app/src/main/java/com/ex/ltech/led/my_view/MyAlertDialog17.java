package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAlertDialog17 extends AlertDialog
{
  private OnListener mOnListener;

  public MyAlertDialog17(Context paramContext)
  {
    super(paramContext);
  }

  private void init()
  {
    setContentView(2130968844);
    ButterKnife.bind(this);
    getWindow().setGravity(80);
    getWindow().setLayout(-1, -2);
    getWindow().setBackgroundDrawableResource(2131492996);
    setCanceledOnTouchOutside(false);
  }

  @OnClick({2131559028})
  public void cancel()
  {
    dismiss();
    if (this.mOnListener != null)
      this.mOnListener.cancel();
  }

  @OnClick({2131559410})
  public void effect()
  {
    dismiss();
    if (this.mOnListener != null)
      this.mOnListener.effect();
  }

  @OnClick({2131559409})
  public void off()
  {
    dismiss();
    if (this.mOnListener != null)
      this.mOnListener.off();
  }

  @OnClick({2131558964})
  public void on()
  {
    dismiss();
    if (this.mOnListener != null)
      this.mOnListener.on();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setOnListener(OnListener paramOnListener)
  {
    this.mOnListener = paramOnListener;
  }

  public static abstract interface OnListener
  {
    public abstract void cancel();

    public abstract void effect();

    public abstract void off();

    public abstract void on();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog17
 * JD-Core Version:    0.6.0
 */