package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;

public class ClipImageLayout extends RelativeLayout
{
  private ClipImageBorderView mClipImageView;
  private int mHorizontalPadding = 20;
  public ClipZoomImageView mZoomImageView;

  public ClipImageLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mZoomImageView = new ClipZoomImageView(paramContext);
    this.mClipImageView = new ClipImageBorderView(paramContext);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -1);
    addView(this.mZoomImageView, localLayoutParams);
    addView(this.mClipImageView, localLayoutParams);
    this.mHorizontalPadding = (int)TypedValue.applyDimension(1, this.mHorizontalPadding, getResources().getDisplayMetrics());
    this.mZoomImageView.setHorizontalPadding(this.mHorizontalPadding);
    this.mClipImageView.setHorizontalPadding(this.mHorizontalPadding);
  }

  public Bitmap clip()
  {
    return this.mZoomImageView.clip();
  }

  public void setHorizontalPadding(int paramInt)
  {
    this.mHorizontalPadding = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ClipImageLayout
 * JD-Core Version:    0.6.0
 */