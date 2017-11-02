package com.ex.ltech.led.my_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class ClipImageBorderView extends View
{
  private int mBorderColor = Color.parseColor("#FFFFFF");
  private int mBorderWidth = 1;
  private int mHorizontalPadding;
  private Paint mPaint = new Paint();
  private int mVerticalPadding;
  private int mWidth;

  public ClipImageBorderView(Context paramContext)
  {
    this(paramContext, null);
  }

  public ClipImageBorderView(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public ClipImageBorderView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mPaint.setAntiAlias(true);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    this.mWidth = (getWidth() - 2 * this.mHorizontalPadding);
    this.mVerticalPadding = ((getHeight() - this.mWidth) / 2);
    this.mPaint.setColor(Color.parseColor("#aa000000"));
    this.mPaint.setStyle(Paint.Style.FILL);
    paramCanvas.drawRect(0.0F, 0.0F, this.mHorizontalPadding, getHeight(), this.mPaint);
    paramCanvas.drawRect(getWidth() - this.mHorizontalPadding, 0.0F, getWidth(), getHeight(), this.mPaint);
    paramCanvas.drawRect(this.mHorizontalPadding, 0.0F, getWidth() - this.mHorizontalPadding, this.mVerticalPadding, this.mPaint);
    paramCanvas.drawRect(this.mHorizontalPadding, getHeight() - this.mVerticalPadding, getWidth() - this.mHorizontalPadding, getHeight(), this.mPaint);
    this.mPaint.setColor(this.mBorderColor);
    this.mPaint.setStrokeWidth(this.mBorderWidth);
    this.mPaint.setStyle(Paint.Style.STROKE);
    paramCanvas.drawRect(this.mHorizontalPadding, this.mVerticalPadding, getWidth() - this.mHorizontalPadding, getHeight() - this.mVerticalPadding, this.mPaint);
  }

  public void setHorizontalPadding(int paramInt)
  {
    this.mHorizontalPadding = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ClipImageBorderView
 * JD-Core Version:    0.6.0
 */