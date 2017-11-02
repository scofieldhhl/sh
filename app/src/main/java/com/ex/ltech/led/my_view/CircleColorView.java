package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import java.util.ArrayList;
import java.util.List;

public class CircleColorView extends View
{
  private Paint circleColorPaint;
  private int circleCount = 8;
  private int circleWidth;
  private int clickPosi;
  public List<Integer> colors = new ArrayList();
  private Paint currentCircleColorPaint;
  private Paint currentCircleColorPaint2;
  private long downCurrentTime;
  private int height;
  private boolean isClickUp;
  private OnCilcleColorSeletedListner listner;
  private Context mContext;
  private int margin = 10;
  private int marginCount = 10;
  private Point point;
  private float radius;
  private int seletedPosi = -1;
  public List<Integer> tempColors = new ArrayList();
  private int width;

  public CircleColorView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public CircleColorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public CircleColorView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  public void changeCirclrColor(int paramInt1, int paramInt2)
  {
  }

  public void init()
  {
    this.circleColorPaint = new Paint();
    this.circleColorPaint.setColor(getResources().getColor(2131492927));
    this.circleColorPaint.setAntiAlias(true);
    this.circleColorPaint.setStyle(Paint.Style.FILL);
    this.currentCircleColorPaint = new Paint();
    this.currentCircleColorPaint.setColor(getResources().getColor(2131492927));
    this.currentCircleColorPaint.setAntiAlias(true);
    this.currentCircleColorPaint.setStyle(Paint.Style.FILL);
    this.currentCircleColorPaint2 = new Paint();
    this.currentCircleColorPaint2.setAntiAlias(true);
    this.currentCircleColorPaint2.setStyle(Paint.Style.FILL);
    for (int i = 0; i < this.circleCount; i++)
      this.colors.add(Integer.valueOf(-16777216));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    for (int i = 0; i < this.circleCount; i++)
      paramCanvas.drawCircle(i * (this.circleWidth + this.margin) + this.radius + this.margin, this.height / 2, this.radius, this.circleColorPaint);
    for (int j = 0; j < this.colors.size(); j++)
    {
      this.currentCircleColorPaint2.setColor(((Integer)this.colors.get(j)).intValue());
      paramCanvas.drawCircle(j * (this.circleWidth + this.margin) + this.radius + this.margin, this.height / 2, this.radius, this.currentCircleColorPaint2);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
    int i = localWindowManager.getDefaultDisplay().getHeight();
    int j = localWindowManager.getDefaultDisplay().getWidth();
    setMeasuredDimension(paramInt1, i * 100 / 1280);
    this.width = j;
    this.height = (i * 100 / 1280);
    this.circleWidth = ((this.width - this.margin * this.marginCount) / this.circleCount);
    this.radius = (this.circleWidth / 2);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default:
    case 1:
    case 0:
    }
    do
    {
      do
      {
        return true;
        this.isClickUp = true;
        if (this.listner == null)
          continue;
        this.listner.onCilcleBgTouchUp();
      }
      while ((System.currentTimeMillis() - this.downCurrentTime <= 1500L) || (this.listner == null) || (i < 0) || (j < 0) || (j > getHeight()) || (i >= getWidth() - this.margin));
      return true;
      this.isClickUp = false;
    }
    while ((this.listner == null) || (i < 0) || (j < 0) || (j > getHeight()) || (i >= getWidth() - this.margin));
    OnCilcleColorSeletedListner localOnCilcleColorSeletedListner = this.listner;
    int k = (i - this.margin) / (this.margin + this.circleWidth);
    this.clickPosi = k;
    localOnCilcleColorSeletedListner.onCilcleBgSeleted(k);
    this.downCurrentTime = System.currentTimeMillis();
    getHandler().postDelayed(new Runnable()
    {
      public void run()
      {
        if ((!CircleColorView.this.isClickUp) && (CircleColorView.this.listner != null))
          CircleColorView.this.listner.onLongClick(CircleColorView.this.clickPosi);
      }
    }
    , 1000L);
    return true;
  }

  public void saveColor(int paramInt1, int paramInt2)
  {
    this.colors.remove(paramInt2);
    this.colors.add(paramInt2, Integer.valueOf(paramInt1));
    invalidate();
  }

  public void setColors(List<Integer> paramList)
  {
    this.colors = paramList;
  }

  public void setMyListener(OnCilcleColorSeletedListner paramOnCilcleColorSeletedListner)
  {
    this.listner = paramOnCilcleColorSeletedListner;
  }

  public static abstract interface OnCilcleColorSeletedListner
  {
    public abstract void onCilcleBgSeleted(int paramInt);

    public abstract void onCilcleBgTouchUp();

    public abstract void onLongClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.CircleColorView
 * JD-Core Version:    0.6.0
 */