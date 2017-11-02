package com.ex.ltech.led.my_view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import java.io.PrintStream;

public class MyRainbowSeekBar extends View
{
  private int b;
  private int bMaxValue;
  private int ballColor;
  private Paint bgPaint;
  private int bgY;
  private int g;
  private int gMaxValue;
  private Paint garyP;
  private int height;
  int i = 0;
  private onMySBtouchListener listener;
  private int mColor = 0;
  private Context mContext;
  private Activity mat;
  private int progressEnd;
  private Paint progressPaint;
  private Point progressPoint;
  private int progressStart;
  private int r;
  private int rMaxValue;
  private Bitmap rainBow;
  public int strokeWidth;
  private Bitmap thumbBM;
  private int thumbEnd;
  private Paint thumbPaint;
  private Point thumbPoint;
  private int thumbStart;
  private int thumbY;
  private Paint whiteP;
  private int width;

  public MyRainbowSeekBar(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public MyRainbowSeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public MyRainbowSeekBar(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  private void onScroll(int paramInt)
  {
    float f = paramInt / (this.progressEnd - this.progressStart);
    System.out.println("fuck   progressPercent " + f);
    try
    {
      this.ballColor = this.rainBow.getPixel(paramInt - this.thumbBM.getWidth() / 2, this.rainBow.getHeight() / 2);
      this.r = Color.red(this.ballColor);
      this.g = Color.green(this.ballColor);
      this.b = Color.blue(this.ballColor);
      this.thumbPaint.setColor(this.ballColor);
      if (this.listener != null)
        this.listener.onMySbBrightChange(this.ballColor, this.r, this.g, this.b, 255);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  private void onUp(int paramInt)
  {
    float f = paramInt / (this.progressEnd - this.progressStart);
    System.out.println("fuck   progressPercent " + f);
    try
    {
      this.ballColor = this.rainBow.getPixel(paramInt - this.thumbBM.getWidth() / 2, this.rainBow.getHeight() / 2);
      this.r = Color.red(this.ballColor);
      this.g = Color.green(this.ballColor);
      this.b = Color.blue(this.ballColor);
      this.thumbPaint.setColor(this.ballColor);
      if (this.listener != null)
        this.listener.onMySbBrightUp(this.ballColor, this.r, this.g, this.b, 255);
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public int dip2px(float paramFloat)
  {
    return (int)(0.5F + paramFloat * this.mContext.getResources().getDisplayMetrics().density);
  }

  public onMySBtouchListener getListener()
  {
    return this.listener;
  }

  public void init()
  {
    this.rainBow = BitmapFactory.decodeResource(this.mContext.getResources(), 2130903643);
    float f = dip2px(250.0F) / this.rainBow.getWidth();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f, 1.0F);
    this.rainBow = Bitmap.createBitmap(this.rainBow, 0, 0, this.rainBow.getWidth(), this.rainBow.getHeight(), localMatrix, false);
    this.thumbBM = BitmapFactory.decodeResource(this.mContext.getResources(), 2130903782);
    this.thumbPaint = new Paint();
    this.whiteP = new Paint();
    this.garyP = new Paint();
    this.thumbPaint.setAntiAlias(true);
    this.whiteP.setAntiAlias(true);
    this.whiteP.setColor(-1);
    this.garyP.setAntiAlias(true);
    this.garyP.setColor(getResources().getColor(2131492927));
    this.progressPaint = new Paint();
    this.bgPaint = new Paint();
    this.progressPaint.setColor(-7829368);
    this.bgPaint.setColor(-7829368);
    this.progressPaint.setAntiAlias(true);
    this.bgPaint.setAntiAlias(true);
    this.progressPaint.setStrokeWidth(dip2px(6.0F));
    this.bgPaint.setStrokeWidth(dip2px(6.0F));
    this.progressPaint.setStyle(Paint.Style.FILL);
    this.thumbPaint.setColor(this.rainBow.getPixel(this.rainBow.getWidth() / 2, this.rainBow.getHeight() / 2));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (isInEditMode())
      return;
    paramCanvas.drawBitmap(this.rainBow, this.progressStart, -10 + this.bgY, this.thumbPaint);
    paramCanvas.drawCircle(this.thumbPoint.x, -5 + this.bgY, dip2px(10.0F), this.garyP);
    paramCanvas.drawCircle(this.thumbPoint.x, -5 + this.bgY, -1 + dip2px(10.0F), this.whiteP);
    paramCanvas.drawCircle(this.thumbPoint.x, -5 + this.bgY, dip2px(7.0F), this.thumbPaint);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.width = getMeasuredWidth();
    this.height = getMeasuredHeight();
    this.progressPoint = new Point(this.width / 2, this.height / 2);
    this.bgY = (this.height / 2);
    this.thumbY = (this.height / 2 - this.thumbBM.getHeight() / 2);
    this.thumbPoint = new Point(this.width / 2, this.thumbY);
    this.thumbStart = (8 + this.thumbBM.getWidth() / 2);
    this.thumbEnd = (8 + (this.width - this.thumbBM.getWidth() / 2));
    this.progressStart = (this.thumbBM.getWidth() / 2);
    this.progressEnd = (this.width - this.thumbBM.getWidth() / 2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int j = (int)paramMotionEvent.getX();
    (int)paramMotionEvent.getY();
    if (j < this.thumbStart)
      j = this.thumbStart;
    if (j > this.thumbEnd)
      j = this.thumbEnd;
    if ((j > this.progressStart) && (j < this.progressEnd))
      this.progressPoint.set(j, this.bgY);
    this.i = (1 + this.i);
    if (this.i % 10 == 0)
      onScroll(j);
    if (paramMotionEvent.getAction() == 1)
      onUp(j);
    this.thumbPoint.set(j, this.thumbPoint.y);
    invalidate();
    return true;
  }

  public void setListener(onMySBtouchListener paramonMySBtouchListener)
  {
    this.listener = paramonMySBtouchListener;
  }

  public void setProgress(int paramInt)
  {
    try
    {
      this.ballColor = this.rainBow.getPixel(paramInt * this.width / 100, this.rainBow.getHeight() / 2);
      this.r = Color.red(this.ballColor);
      this.g = Color.green(this.ballColor);
      this.b = Color.blue(this.ballColor);
      if (paramInt == 30)
        this.ballColor = -1;
      this.thumbPaint.setColor(this.ballColor);
      this.thumbPoint.set(paramInt * this.width / 100, this.bgY);
      invalidate();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static abstract interface onMySBtouchListener
  {
    public abstract void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);

    public abstract void onMySbBrightUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyRainbowSeekBar
 * JD-Core Version:    0.6.0
 */