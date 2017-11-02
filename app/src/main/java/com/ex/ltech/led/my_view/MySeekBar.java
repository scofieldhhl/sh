package com.ex.ltech.led.my_view;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.SeekBar.OnSeekBarChangeListener;
import java.io.PrintStream;

public class MySeekBar extends View
{
  private int b;
  private int bMaxValue;
  private Paint bgPaint;
  private int bgY;
  private int g;
  private int gMaxValue;
  private int height;
  int i = 0;
  private boolean iosSeekBarStyle = false;
  boolean isMiddle;
  private onMySBtouchListener listener;
  private int mColor = 0;
  private Context mContext;
  private Activity mat;
  SeekBar.OnSeekBarChangeListener onSeekBarChangeListener;
  OnViewSizeChangeListener onViewSizeChangeListener;
  private int progressEnd;
  private Paint progressPaint;
  private Point progressPoint;
  private int progressStart;
  private int r;
  private int rMaxValue;
  public int strokeWidth;
  private Bitmap thumbBM;
  private int thumbEnd;
  private Paint thumbPaint;
  private Point thumbPoint;
  boolean thumbSeleted;
  private int thumbStart;
  private int thumbY;
  long timeSpace;
  private int width;

  public MySeekBar(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (isInEditMode())
      return;
    this.mContext = paramContext;
    this.mat = ((Activity)paramContext);
    init();
  }

  private void onScroll(float paramFloat)
  {
    float f = paramFloat / (this.progressEnd - this.thumbBM.getWidth() / 2);
    System.out.println("fuck   touchX " + paramFloat);
    System.out.println("fuck   progressEnd " + (this.progressEnd - this.thumbBM.getWidth() / 2));
    System.out.println("fuck   progressPercent " + f);
    if (this.listener != null)
      this.listener.onMySbBrightChange(this.r, this.g, this.b, (int)(255.0F * f));
    this.timeSpace = System.currentTimeMillis();
  }

  private void onUp(float paramFloat)
  {
    float f = paramFloat / (this.progressEnd - this.thumbBM.getWidth() / 2);
    System.out.println("fuck   touchX " + paramFloat);
    System.out.println("fuck   progressEnd " + (this.progressEnd - this.thumbBM.getWidth() / 2));
    System.out.println("fuck   progressPercent " + f);
    if (this.listener != null)
      this.listener.onMySbUp(this.r, this.g, this.b, (int)(255.0F * f));
    this.timeSpace = System.currentTimeMillis();
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
    this.thumbBM = BitmapFactory.decodeResource(this.mContext.getResources(), 2130903782);
    this.thumbPaint = new Paint();
    this.thumbPaint.setAntiAlias(true);
    this.progressPaint = new Paint();
    this.bgPaint = new Paint();
    this.progressPaint.setColor(-7829368);
    this.bgPaint.setColor(-7829368);
    this.progressPaint.setAntiAlias(true);
    this.bgPaint.setAntiAlias(true);
    this.progressPaint.setStrokeWidth(dip2px(2.0F));
    this.bgPaint.setStrokeWidth(dip2px(2.0F));
    this.progressPaint.setStyle(Paint.Style.FILL);
  }

  public boolean isIosSeekBarStyle()
  {
    return this.iosSeekBarStyle;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (isInEditMode())
      return;
    paramCanvas.drawLine(this.progressStart, this.bgY, this.progressEnd, this.bgY, this.bgPaint);
    paramCanvas.drawLine(this.progressStart, this.bgY, 5 + this.progressPoint.x, this.bgY, this.progressPaint);
    paramCanvas.drawBitmap(this.thumbBM, this.thumbPoint.x, this.thumbPoint.y, this.thumbPaint);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    try
    {
      this.width = getMeasuredWidth();
      this.height = getMeasuredHeight();
      this.progressPoint = new Point(this.width, this.height / 2);
      this.bgY = (this.height / 2);
      this.thumbY = (this.height / 2 - this.thumbBM.getHeight() / 2);
      this.thumbPoint = new Point(this.width, this.thumbY);
      this.thumbStart = 0;
      this.thumbEnd = (this.width - this.thumbBM.getWidth());
      this.progressStart = (this.thumbBM.getWidth() / 2);
      this.progressEnd = (this.width - this.thumbBM.getWidth() / 2);
      if (this.isMiddle)
        this.thumbPoint = new Point(this.width / 2, this.thumbY);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (isIosSeekBarStyle())
    {
      float f2 = paramMotionEvent.getX();
      paramMotionEvent.getY();
      int m;
      int n;
      if (paramMotionEvent.getAction() == 0)
      {
        if (f2 <= -30 + (this.thumbPoint.x - this.thumbBM.getWidth() / 2))
          break label290;
        m = 1;
        if (f2 >= 30 + (this.thumbPoint.x + this.thumbBM.getWidth() / 2))
          break label296;
        n = 1;
        label85: if ((m & n) != 0)
          this.thumbSeleted = true;
      }
      if (this.thumbSeleted)
      {
        if (f2 < this.thumbStart)
          f2 = this.thumbStart;
        if (f2 > this.thumbEnd)
          f2 = this.thumbEnd;
        if ((f2 > this.progressStart) && (f2 < this.progressEnd))
          this.progressPoint.set((int)f2, this.bgY);
        this.i = (1 + this.i);
        if (this.i % 5 == 0)
          onScroll(f2);
        if (paramMotionEvent.getAction() == 1)
          onUp(f2);
        this.thumbPoint.set((int)f2, this.thumbPoint.y);
        invalidate();
      }
      int j;
      if (paramMotionEvent.getAction() == 1)
      {
        j = 1;
        label248: if (paramMotionEvent.getAction() != 3)
          break label308;
      }
      label290: label296: label308: for (int k = 1; ; k = 0)
      {
        if ((j | k) != 0)
          this.thumbSeleted = false;
        if (this.onViewSizeChangeListener != null)
          this.onViewSizeChangeListener.onViewSizeChange();
        return true;
        m = 0;
        break;
        n = 0;
        break label85;
        j = 0;
        break label248;
      }
    }
    float f1 = paramMotionEvent.getX();
    paramMotionEvent.getY();
    if (f1 < this.thumbStart)
      f1 = this.thumbStart;
    if (f1 > this.thumbEnd)
      f1 = this.thumbEnd;
    if ((f1 > this.progressStart) && (f1 < this.progressEnd))
      this.progressPoint.set((int)f1, this.bgY);
    this.i = (1 + this.i);
    if (this.i % 5 == 0)
      onScroll(f1);
    if (paramMotionEvent.getAction() == 1)
      onUp(f1);
    this.thumbPoint.set((int)f1, this.thumbPoint.y);
    invalidate();
    return true;
  }

  public void setIosSeekBarStyle(boolean paramBoolean)
  {
    this.iosSeekBarStyle = paramBoolean;
  }

  public void setListener(onMySBtouchListener paramonMySBtouchListener)
  {
    this.listener = paramonMySBtouchListener;
  }

  public void setMiddleProgress()
  {
    this.isMiddle = true;
  }

  public void setOnSeekBarChangeListener(SeekBar.OnSeekBarChangeListener paramOnSeekBarChangeListener)
  {
    this.onSeekBarChangeListener = paramOnSeekBarChangeListener;
  }

  public void setOnViewSizeChangeListener(OnViewSizeChangeListener paramOnViewSizeChangeListener)
  {
    this.onViewSizeChangeListener = paramOnViewSizeChangeListener;
  }

  public void setProgress(int paramInt)
  {
    this.thumbPoint.set(this.thumbEnd, this.thumbY);
    this.progressPoint.set(this.progressEnd, this.bgY);
    invalidate();
  }

  public void setProgressColor(int paramInt)
  {
    this.mColor = paramInt;
    this.progressPaint.setColor(paramInt);
    this.r = Color.red(paramInt);
    this.g = Color.green(paramInt);
    this.b = Color.blue(paramInt);
    try
    {
      float f = this.progressPoint.x / (this.progressEnd - this.progressStart);
      this.rMaxValue = (int)(this.r / f);
      this.gMaxValue = (int)(this.g / f);
      this.bMaxValue = (int)(this.b / f);
      invalidate();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public static abstract interface OnViewSizeChangeListener
  {
    public abstract void onViewSizeChange();
  }

  public static abstract interface onMySBtouchListener
  {
    public abstract void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public abstract void onMySbUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MySeekBar
 * JD-Core Version:    0.6.0
 */