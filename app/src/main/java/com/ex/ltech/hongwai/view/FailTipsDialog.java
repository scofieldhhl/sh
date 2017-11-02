package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.indris.material.RippleView;

public class FailTipsDialog extends BaseTipsDialog
{

  @Bind({2131559095})
  ImageView mImageViewIcon;
  private OnListener mListener;

  @Bind({2131559096})
  RippleView mNegativeBtn;

  @Bind({2131559097})
  RippleView mPositiveBtn;

  @Bind({2131558886})
  TextView mTextViewContent;

  public FailTipsDialog(Context paramContext)
  {
    super(paramContext, 0.8F, (int)(0.5F + 200.0F * paramContext.getResources().getDisplayMetrics().density), 17);
  }

  protected int getLayoutId()
  {
    return 2130968725;
  }

  protected void initView()
  {
  }

  @OnClick({2131559096})
  public void negativeClick(View paramView)
  {
    dismiss();
    if (this.mListener != null)
      this.mListener.onNegativeClick();
  }

  @OnClick({2131559097})
  public void positiveClick(View paramView)
  {
    dismiss();
    if (this.mListener != null)
      this.mListener.onPositiveClick();
  }

  public FailTipsDialog setContent(int paramInt)
  {
    this.mTextViewContent.setText(paramInt);
    return this;
  }

  public FailTipsDialog setContent(String paramString)
  {
    this.mTextViewContent.setText(paramString);
    return this;
  }

  public FailTipsDialog setListener(OnListener paramOnListener)
  {
    this.mListener = paramOnListener;
    return this;
  }

  public FailTipsDialog setNegativeBtnInfo(int paramInt)
  {
    this.mNegativeBtn.setText(paramInt);
    return this;
  }

  public FailTipsDialog setNegativeBtnInfo(String paramString)
  {
    this.mNegativeBtn.setText(paramString);
    return this;
  }

  public FailTipsDialog setPositiveBtnInfo(int paramInt)
  {
    this.mPositiveBtn.setText(paramInt);
    return this;
  }

  public FailTipsDialog setPositiveBtnInfo(String paramString)
  {
    this.mPositiveBtn.setText(paramString);
    return this;
  }

  public static abstract interface OnListener
  {
    public abstract void onNegativeClick();

    public abstract void onPositiveClick();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.FailTipsDialog
 * JD-Core Version:    0.6.0
 */