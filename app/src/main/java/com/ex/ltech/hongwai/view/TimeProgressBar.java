package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.ex.ltech.led.R.styleable;

public class TimeProgressBar extends View
{
  private int currentProgress;
  private int firstProgressColor;
  private float radius;
  private Paint ringPaint;
  private int secondProgressColor;
  private float strokeWidth;
  private int textColor;
  private float textHeight;
  private Paint textPaint;
  private float textSize;
  private float textWidth;
  private int totalProgress;

  public TimeProgressBar(Context paramContext)
  {
    this(paramContext, null);
  }

  public TimeProgressBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }

  public TimeProgressBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    initAttr(paramContext, paramAttributeSet);
    init();
  }

  private void init()
  {
    this.ringPaint = new Paint();
    this.ringPaint.setAntiAlias(true);
    this.ringPaint.setDither(true);
    this.ringPaint.setStyle(Paint.Style.STROKE);
    this.ringPaint.setStrokeCap(Paint.Cap.ROUND);
    this.ringPaint.setStrokeWidth(this.strokeWidth);
    this.textPaint = new Paint();
    this.textPaint.setAntiAlias(true);
    this.textPaint.setTextSize(this.textSize);
    this.textPaint.setStyle(Paint.Style.FILL);
    this.textPaint.setColor(this.textColor);
    Paint.FontMetrics localFontMetrics = this.textPaint.getFontMetrics();
    this.textHeight = (localFontMetrics.descent + Math.abs(localFontMetrics.ascent));
  }

  private void initAttr(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.TimeProgressBar);
    this.textColor = localTypedArray.getColor(0, getResources().getColor(2131492938));
    this.firstProgressColor = localTypedArray.getColor(1, getResources().getColor(2131492942));
    this.secondProgressColor = localTypedArray.getColor(2, getResources().getColor(2131492940));
    this.textSize = (int)localTypedArray.getDimension(4, sp2px(20));
    this.strokeWidth = (int)localTypedArray.getDimension(3, dp2px(8));
    this.currentProgress = localTypedArray.getInt(5, 0);
    this.totalProgress = localTypedArray.getInt(6, 60);
    localTypedArray.recycle();
  }

  protected int dp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(1, paramInt, getResources().getDisplayMetrics());
  }

  protected void onDraw(Canvas paramCanvas)
  {
    if (this.currentProgress >= 0)
    {
      RectF localRectF = new RectF(getWidth() / 2 - this.radius, getHeight() / 2 - this.radius, getWidth() / 2 + this.radius, getHeight() / 2 + this.radius);
      this.ringPaint.setColor(this.secondProgressColor);
      paramCanvas.drawArc(localRectF, 360.0F * (this.currentProgress / this.totalProgress) - 90.0F, 360.0F * ((this.totalProgress - this.currentProgress) / this.totalProgress), false, this.ringPaint);
      this.ringPaint.setColor(this.firstProgressColor);
      paramCanvas.drawArc(localRectF, -90.0F, 360.0F * (this.currentProgress / this.totalProgress), false, this.ringPaint);
      String str = this.currentProgress + getResources().getString(R.string.sec);
      this.textWidth = this.textPaint.measureText(str, 0, str.length());
      paramCanvas.drawText(str, getWidth() / 2 - this.textWidth / 2.0F, getHeight() / 2 + this.textHeight / 3.0F, this.textPaint);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.radius = (getWidth() / 2 - this.strokeWidth);
  }

  public void setProgress(int paramInt)
  {
    this.currentProgress = paramInt;
    postInvalidate();
  }

  public void setTotalProgress(int paramInt)
  {
    this.totalProgress = paramInt;
  }

  protected int sp2px(int paramInt)
  {
    return (int)TypedValue.applyDimension(2, paramInt, getResources().getDisplayMetrics());
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.TimeProgressBar
 * JD-Core Version:    0.6.0
 */