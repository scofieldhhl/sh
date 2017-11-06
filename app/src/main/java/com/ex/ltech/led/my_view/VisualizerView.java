package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class VisualizerView extends View
{
  private int buildCount = 6;
  boolean clear;
  private boolean isPlaying = false;
  private boolean isRecording = false;
  private Paint mForePaint = new Paint();
  private int maringLeft;
  private short[] mshort;
  private int[] rateArr = new int[6];
  private int[] rectColors = { R.color.color1, R.color.color1, 2131492893, 2131492893, 2131492894, 2131492894, 2131492895, 2131492895, R.color.color5, R.color.color5 };
  private int[] rectColors2 = { 2131492886, 2131492886, 2131492886, 2131492886, 2131492886, 2131492886, 2131492886, 2131492886, 2131492886, 2131492886 };
  private int rectCount = 10;
  private int rectHei = 30 * this.screenHeight / 1280;
  private int rectMarginX = this.rectWid / 2;
  private int rectMarginY = this.rectHei / 2;
  private Paint rectPaint = new Paint();
  private int rectWid = 60 * this.screenWidth / 800;
  private int screenHeight = this.wm.getDefaultDisplay().getHeight();
  private int screenWidth = this.wm.getDefaultDisplay().getWidth();
  WindowManager wm = (WindowManager)getContext().getSystemService("window");

  public VisualizerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void drawRectProgress(Canvas paramCanvas)
  {
    for (int i = 0; i < 6; i++)
    {
      int j = 0;
      if (j >= -1 + this.rateArr[i])
        continue;
      if (this.clear)
        this.rectPaint.setColor(getResources().getColor(this.rectColors2[j]));
      while (true)
      {
        paramCanvas.drawRect(this.maringLeft + i * (this.rectWid + this.rectMarginX), 3 * this.screenHeight / 5 - (this.rectHei + j * (this.rectHei + this.rectMarginY)), this.maringLeft + this.rectWid + i * (this.rectWid + this.rectMarginX), 3 * this.screenHeight / 5 - j * (this.rectHei + this.rectMarginY), this.rectPaint);
        j++;
        break;
        this.rectPaint.setColor(getResources().getColor(this.rectColors[j]));
      }
    }
  }

  private void init()
  {
    this.mshort = null;
    this.mForePaint.setStrokeWidth(1.0F);
    this.mForePaint.setAntiAlias(true);
    this.mForePaint.setColor(Color.rgb(0, 128, 255));
    this.rectPaint.setStrokeWidth(1.0F);
    this.rectPaint.setAntiAlias(true);
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
    this.screenHeight = localWindowManager.getDefaultDisplay().getHeight();
    this.screenWidth = localWindowManager.getDefaultDisplay().getWidth();
    this.maringLeft = ((this.screenWidth - 6 * (this.rectWid + this.rectMarginX)) / 2);
    this.rectHei = (30 * this.screenHeight / 1280);
    this.rectWid = (60 * this.screenWidth / 800);
    this.rectMarginX = (this.rectWid / 2);
    this.rectMarginY = (this.rectHei / 2);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    drawRectProgress(paramCanvas);
  }

  public void setNullArr()
  {
    this.clear = true;
    invalidate();
  }

  public void setRateArr(int[] paramArrayOfInt)
  {
    this.clear = false;
    this.rateArr = paramArrayOfInt;
    invalidate();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.VisualizerView
 * JD-Core Version:    0.6.0
 */