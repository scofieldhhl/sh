package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;

public class OkTipsDialog extends BaseTipsDialog
{
  private OnListener mListener;

  @Bind({2131558886})
  TextView mTextViewContent;

  public OkTipsDialog(Context paramContext)
  {
    super(paramContext, 0.8F, (int)(0.5F + 250.0F * paramContext.getResources().getDisplayMetrics().density), 17);
  }

  protected int getLayoutId()
  {
    return 2130968727;
  }

  protected void initView()
  {
  }

  @OnClick({2131559099, 2131559100, 2131559101})
  public void onViewClicked(View paramView)
  {
    if (this.mListener != null);
    switch (paramView.getId())
    {
    default:
      return;
    case 2131559099:
      this.mListener.onOnoff();
      return;
    case 2131559100:
      this.mListener.onConfigOk();
      return;
    case 2131559101:
    }
    this.mListener.onRepeatStep();
  }

  public OkTipsDialog setContent(int paramInt)
  {
    this.mTextViewContent.setText(paramInt);
    return this;
  }

  public OkTipsDialog setContent(String paramString)
  {
    this.mTextViewContent.setText(paramString);
    return this;
  }

  public OkTipsDialog setListener(OnListener paramOnListener)
  {
    this.mListener = paramOnListener;
    return this;
  }

  public static abstract interface OnListener
  {
    public abstract void onConfigOk();

    public abstract void onOnoff();

    public abstract void onRepeatStep();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.OkTipsDialog
 * JD-Core Version:    0.6.0
 */