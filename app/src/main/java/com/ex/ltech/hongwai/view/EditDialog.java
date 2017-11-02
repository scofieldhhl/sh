package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;

public class EditDialog extends BaseTipsDialog
{

  @Bind({2131559091})
  EditText mEditContent;
  private OnListener mListener;

  @Bind({2131559088})
  TextView mTitle;

  public EditDialog(Context paramContext)
  {
    super(paramContext, 0.8F, (int)(0.5F + 230.0F * paramContext.getResources().getDisplayMetrics().density), 17);
  }

  @OnClick({2131559090})
  public void clearText()
  {
    this.mEditContent.setText("");
  }

  protected int getLayoutId()
  {
    return 2130968723;
  }

  protected void initView()
  {
  }

  @OnClick({2131558890})
  public void onCancelBtnClick()
  {
    dismiss();
    if (this.mListener != null)
      this.mListener.onCancelBtnClick();
  }

  @OnClick({2131559093})
  public void onOkBtnClick()
  {
    dismiss();
    if (this.mListener != null)
      this.mListener.onOkBtnClick(this.mEditContent.getText().toString());
  }

  public EditDialog setEditContent(String paramString)
  {
    this.mEditContent.setText(paramString);
    return this;
  }

  public EditDialog setListener(OnListener paramOnListener)
  {
    this.mListener = paramOnListener;
    return this;
  }

  public EditDialog setTitle(String paramString)
  {
    this.mTitle.setText(paramString);
    return this;
  }

  public static abstract interface OnListener
  {
    public abstract void onCancelBtnClick();

    public abstract void onOkBtnClick(String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.EditDialog
 * JD-Core Version:    0.6.0
 */