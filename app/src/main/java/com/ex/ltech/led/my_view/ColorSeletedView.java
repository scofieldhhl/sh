package com.ex.ltech.led.my_view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import com.ex.ltech.led.R;

import java.util.ArrayList;
import java.util.List;

public class ColorSeletedView extends View
{
  private Paint circleColorPaint;
  public int circleWidth = 50;
  private List<Integer> colors;
  private int height;
  private Context mContext;
  private Point point;
  private int width;

  public ColorSeletedView(Context paramContext)
  {
    super(paramContext);
    this.mContext = paramContext;
    init();
  }

  public ColorSeletedView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.mContext = paramContext;
    init();
  }

  public ColorSeletedView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    this.mContext = paramContext;
    init();
  }

  public void clearCanvas()
  {
    this.colors.clear();
    invalidate();
  }

  public void init()
  {
    setColors(new ArrayList());
    this.circleColorPaint = new Paint();
    this.circleColorPaint.setAntiAlias(true);
    this.circleColorPaint.setStyle(Style.FILL);
    this.circleColorPaint.setColor(getResources().getColor(R.color.c4));
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.colors.size() != 0)
      for (int i = 0; i < this.colors.size(); i++)
      {
        this.circleColorPaint.setColor(getResources().getColor(R.color.c4));
        paramCanvas.drawCircle(20 + i * this.circleWidth, 20.0F, 20.0F, this.circleColorPaint);
        this.circleColorPaint.setColor(((Integer)this.colors.get(i)).intValue());
        paramCanvas.drawCircle(20 + i * this.circleWidth, 20.0F, 18.0F, this.circleColorPaint);
      }
    paramCanvas.restore();
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    setMeasuredDimension(paramInt1, paramInt2);
    this.width = getWidth();
    this.height = getHeight();
  }

  public void setColor(int paramInt)
  {
    this.colors.clear();
    this.colors.add(Integer.valueOf(paramInt));
    invalidate();
  }

  public void setColors(List<Integer> paramList)
  {
    this.colors = paramList;
    invalidate();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ColorSeletedView
 * JD-Core Version:    0.6.0
 */