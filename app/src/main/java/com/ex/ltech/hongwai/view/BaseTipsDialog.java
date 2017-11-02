package com.ex.ltech.hongwai.view;

import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import butterknife.ButterKnife;

public abstract class BaseTipsDialog extends Dialog
{
  protected Context mContext;

  public BaseTipsDialog(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initView();
  }

  public BaseTipsDialog(Context paramContext, float paramFloat1, float paramFloat2, int paramInt)
  {
    super(paramContext);
    this.mContext = paramContext;
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initView();
    initWindow(paramFloat1, paramFloat2, paramInt);
  }

  public BaseTipsDialog(Context paramContext, float paramFloat, int paramInt1, int paramInt2)
  {
    super(paramContext);
    this.mContext = paramContext;
    requestWindowFeature(1);
    setContentView(getLayoutId());
    ButterKnife.bind(this);
    initView();
    initWindow(paramFloat, paramInt1, paramInt2);
  }

  private void initWindow(float paramFloat1, float paramFloat2, int paramInt)
  {
    WindowManager localWindowManager = (WindowManager)this.mContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    Window localWindow = getWindow();
    localWindow.setBackgroundDrawableResource(2131492996);
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = (int)(paramFloat1 * localDisplayMetrics.widthPixels);
    localLayoutParams.height = (int)(paramFloat2 * localDisplayMetrics.heightPixels);
    localWindow.setGravity(paramInt);
    setCanceledOnTouchOutside(false);
  }

  private void initWindow(float paramFloat, int paramInt1, int paramInt2)
  {
    WindowManager localWindowManager = (WindowManager)this.mContext.getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    Window localWindow = getWindow();
    localWindow.setBackgroundDrawableResource(2131492996);
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = (int)(paramFloat * localDisplayMetrics.widthPixels);
    localLayoutParams.height = paramInt1;
    localWindow.setGravity(paramInt2);
    setCanceledOnTouchOutside(false);
  }

  protected abstract int getLayoutId();

  protected abstract void initView();
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.BaseTipsDialog
 * JD-Core Version:    0.6.0
 */