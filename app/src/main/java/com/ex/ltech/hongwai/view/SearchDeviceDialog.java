package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import com.indris.material.RippleView;

public class SearchDeviceDialog extends BaseTipsDialog
{
  private static final int TIME_BEGIN;
  private int cur = 0;
  private Handler handler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      if (SearchDeviceDialog.this.isShowing())
      {
        SearchDeviceDialog.access$008(SearchDeviceDialog.this);
        if (SearchDeviceDialog.this.cur > SearchDeviceDialog.this.total)
          break label73;
        SearchDeviceDialog.this.mTimeProgressBar.setProgress(SearchDeviceDialog.this.cur);
        SearchDeviceDialog.this.handler.sendEmptyMessageDelayed(0, 1000L);
      }
      while (true)
      {
        super.handleMessage(paramMessage);
        return;
        label73: SearchDeviceDialog.this.dismiss();
        if (SearchDeviceDialog.this.mOnListener == null)
          continue;
        SearchDeviceDialog.this.mOnListener.onTimeOut();
      }
    }
  };
  private OnListener mOnListener;

  @Bind({2131559102})
  TimeProgressBar mTimeProgressBar;

  @Bind({2131559103})
  RippleView mTvCancelBtn;

  @Bind({2131558886})
  TextView mTvContent;
  private int total = 0;

  public SearchDeviceDialog(Context paramContext)
  {
    super(paramContext, 0.8F, (int)(0.5F + 200.0F * paramContext.getResources().getDisplayMetrics().density), 17);
  }

  @OnClick({2131559103})
  public void cancel()
  {
    dismiss();
    if (this.mOnListener != null)
      this.mOnListener.onCancel();
  }

  public void dismiss()
  {
    super.dismiss();
    this.cur = 0;
    if (this.handler.hasMessages(0))
      this.handler.removeMessages(0);
  }

  protected int getLayoutId()
  {
    return 2130968728;
  }

  protected void initView()
  {
  }

  public SearchDeviceDialog setOnListener(OnListener paramOnListener)
  {
    this.mOnListener = paramOnListener;
    return this;
  }

  public SearchDeviceDialog setProgress(int paramInt)
  {
    this.cur = paramInt;
    this.mTimeProgressBar.setProgress(this.cur);
    return this;
  }

  public SearchDeviceDialog setTextTip(int paramInt)
  {
    this.mTvContent.setText(paramInt);
    return this;
  }

  public SearchDeviceDialog setTextTip(String paramString)
  {
    this.mTvContent.setText(paramString);
    return this;
  }

  public SearchDeviceDialog setTotalProgress(int paramInt)
  {
    this.total = paramInt;
    this.mTimeProgressBar.setTotalProgress(paramInt);
    return this;
  }

  public void show()
  {
    super.show();
    this.handler.sendEmptyMessage(0);
  }

  public static abstract interface OnListener
  {
    public abstract void onCancel();

    public abstract void onTimeOut();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SearchDeviceDialog
 * JD-Core Version:    0.6.0
 */