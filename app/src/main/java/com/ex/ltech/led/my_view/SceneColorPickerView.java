package com.ex.ltech.led.my_view;

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
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.vo.TouchArea;
import java.io.PrintStream;

public class SceneColorPickerView extends View
{
  private int actionMoveTime;
  private int circlePosi;
  private int colorAreaHeiht;
  private Bitmap colorBM;
  private Paint colorP;
  private TouchArea currentTouchArea = new TouchArea();
  private int flag = 1;
  private Paint generalP;
  private int in = 0;
  private boolean isActionMove = true;
  private boolean isCirclePosiShow;
  private float k;
  private int lastX;
  private int lastY;
  private OnColorChangedListener listener;
  private int nowColor;
  private OnXYChangedListener onXYChangedListener;
  private Paint ovalP;
  private int pActHeight = -1;
  private Point pickerPonit;
  private Bitmap pikerBM;
  private boolean rgbShow = true;
  private int screenHeight;
  private int screenWidth;
  private boolean show;
  private final int step = 20;
  private int strB;
  private int strG;
  private int strR;
  private int targetX;
  private int targetY;
  private Paint textP;
  private int thumbX;
  private int thumbY;
  int time;
  private int topBarHeight;
  private int touchX;
  private int touchY;

  public SceneColorPickerView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public SceneColorPickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public SceneColorPickerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void drawColorArea(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(this.colorBM, 0.0F, this.topBarHeight, this.generalP);
    if (this.isActionMove)
    {
      paramCanvas.drawBitmap(this.pikerBM, this.pickerPonit.x, this.pickerPonit.y, this.generalP);
      return;
    }
    moveThumb(paramCanvas, this.targetX, this.targetY);
  }

  private void drawTopBar(Canvas paramCanvas)
  {
    paramCanvas.drawRect(0.0F, 0.0F, getWidth(), this.topBarHeight, this.colorP);
    int i = this.screenWidth / 8;
    int j = 6 * this.topBarHeight / 10;
    paramCanvas.drawRoundRect(new RectF(i * 1 + i / 2, 1 * this.topBarHeight / 5, i * 2 + i / 2, j + 1 * this.topBarHeight / 5), 30.0F, 30.0F, this.ovalP);
    paramCanvas.drawRoundRect(new RectF(i * 3 + i / 2, 1 * this.topBarHeight / 5, i * 4 + i / 2, j + 1 * this.topBarHeight / 5), 30.0F, 30.0F, this.ovalP);
    paramCanvas.drawRoundRect(new RectF(i * 5 + i / 2, 1 * this.topBarHeight / 5, i * 6 + i / 2, j + 1 * this.topBarHeight / 5), 30.0F, 30.0F, this.ovalP);
    paramCanvas.drawText("R", i * 1, 3 * this.topBarHeight / 5, this.textP);
    paramCanvas.drawText("G", i * 3, 3 * this.topBarHeight / 5, this.textP);
    paramCanvas.drawText("B", i * 5, 3 * this.topBarHeight / 5, this.textP);
    paramCanvas.drawText(this.strR + "", i * 2 - i / 3, 3 * this.topBarHeight / 5, this.textP);
    paramCanvas.drawText(this.strG + "", i * 4 - i / 3, 3 * this.topBarHeight / 5, this.textP);
    paramCanvas.drawText(this.strB + "", i * 6 - i / 3, 3 * this.topBarHeight / 5, this.textP);
  }

  private void init()
  {
    if (this.show)
    {
      initGlobal();
      initTopBar();
      initColorArea();
      invalidate();
    }
  }

  private void initColorArea()
  {
    this.pikerBM = BitmapFactory.decodeResource(getResources(), 2130903071);
    this.colorAreaHeiht = this.colorBM.getHeight();
    this.pickerPonit = new Point(this.screenWidth / 2, this.topBarHeight + this.colorAreaHeiht / 2);
    this.thumbX = this.pickerPonit.x;
    this.thumbY = this.pickerPonit.y;
  }

  private void initGlobal()
  {
    this.generalP = new Paint();
    this.generalP.setAntiAlias(true);
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
    this.screenHeight = localWindowManager.getDefaultDisplay().getHeight();
    this.screenWidth = localWindowManager.getDefaultDisplay().getWidth();
    this.colorBM = BitmapFactory.decodeResource(getResources(), 2130903617);
    float f1;
    int i;
    float f2;
    if (this.screenWidth > this.colorBM.getWidth())
    {
      f1 = this.screenWidth / this.colorBM.getWidth();
      i = 55 * this.pActHeight / 100 - dip2px(30);
      if (i <= this.colorBM.getHeight())
        break label209;
      f2 = i / this.colorBM.getHeight();
    }
    while (true)
    {
      Matrix localMatrix = new Matrix();
      localMatrix.postScale(f1, f2);
      this.colorBM = Bitmap.createBitmap(this.colorBM, 0, 0, this.colorBM.getWidth(), this.colorBM.getHeight(), localMatrix, true);
      return;
      f1 = this.colorBM.getWidth() / this.screenWidth;
      break;
      label209: f2 = this.colorBM.getHeight() / i;
    }
  }

  private void initTopBar()
  {
    this.colorP = new Paint();
    this.colorP.setAntiAlias(true);
    this.topBarHeight = BitmapUtils.dp2px(getContext(), 30.0F);
    this.colorP.setColor(this.colorBM.getPixel(this.screenWidth / 2, this.topBarHeight + this.colorAreaHeiht / 2));
    this.textP = new Paint();
    this.textP.setAntiAlias(true);
    this.textP.setColor(-16777216);
    this.textP.setTextSize(30.0F);
    this.ovalP = new Paint();
    this.ovalP.setStyle(Paint.Style.FILL);
    this.ovalP.setColor(-1);
    this.ovalP.setAntiAlias(true);
  }

  private void moveThumb(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    float f = this.targetX - this.thumbX;
    if (Math.abs(f) < 20.0F)
    {
      paramCanvas.drawBitmap(this.pikerBM, this.targetX, this.targetY, this.generalP);
      if (this.in >= 2)
      {
        this.in = 0;
        return;
      }
      this.in = (1 + this.in);
      invalidate();
      return;
    }
    if (f > 0.0F);
    for (this.flag = 1; ; this.flag = -1)
    {
      this.thumbX = (int)(this.thumbX + 20 * this.flag / Math.floor(1.0F + this.k * this.k));
      this.thumbY = ((int)(this.k * (this.thumbX - this.lastX)) + this.lastY);
      paramCanvas.drawBitmap(this.pikerBM, this.thumbX, this.thumbY, this.generalP);
      invalidate();
      return;
    }
  }

  private void upDateTouchArea()
  {
    this.currentTouchArea.setMinX(this.pickerPonit.x);
    this.currentTouchArea.setMaxX(this.pickerPonit.x + this.pikerBM.getWidth());
    this.currentTouchArea.setMinY(this.pickerPonit.y);
    this.currentTouchArea.setMaxY(this.pickerPonit.y + this.pikerBM.getHeight());
  }

  public void clearCircleColorPosi(int paramInt)
  {
    this.isCirclePosiShow = false;
    invalidate();
  }

  public int dip2px(int paramInt)
  {
    return (int)(0.5F + getResources().getDisplayMetrics().density * paramInt);
  }

  public void drawCircleColorPosi(int paramInt)
  {
    this.isCirclePosiShow = true;
    this.circlePosi = paramInt;
    invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.pActHeight == -1);
    do
      return;
    while (isInEditMode());
    drawColorArea(paramCanvas);
    System.out.println("onDraw");
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.touchX = (int)paramMotionEvent.getX();
    this.touchY = (int)paramMotionEvent.getY();
    if (this.onXYChangedListener != null)
      this.onXYChangedListener.onXYChange(100.0F * paramMotionEvent.getX() / getWidth(), 100.0F * paramMotionEvent.getY() / getHeight());
    if ((this.touchX >= 0) && (this.touchY >= this.topBarHeight - this.pikerBM.getHeight() / 2) && (this.touchY < this.topBarHeight + this.colorAreaHeiht - this.pikerBM.getHeight()) && (this.touchX < getWidth()))
      switch (paramMotionEvent.getAction())
      {
      default:
        this.pickerPonit.set(this.touchX - this.pikerBM.getWidth() / 2, this.touchY);
        this.nowColor = this.colorBM.getPixel(this.touchX, this.touchY - this.topBarHeight + this.pikerBM.getHeight());
        this.strR = Color.red(this.nowColor);
        this.strG = Color.green(this.nowColor);
        this.strB = Color.blue(this.nowColor);
        this.colorP.setColor(this.nowColor);
        this.time = (1 + this.time);
        if ((this.time % 8 == 0) && (this.listener != null))
        {
          this.listener.onPikerXYChange(this.nowColor);
          this.listener.onRgbChange(this.strR, this.strG, this.strB);
        }
        if (paramMotionEvent.getAction() == 2)
        {
          this.actionMoveTime = (1 + this.actionMoveTime);
          if (this.actionMoveTime > 5)
            this.isActionMove = true;
        }
        if (paramMotionEvent.getAction() == 0)
        {
          this.actionMoveTime = 0;
          this.isActionMove = false;
          onTouchPoint(this.touchX, this.touchY);
        }
        invalidate();
      case 1:
      }
    do
    {
      return true;
      if (this.listener != null)
        this.listener.onPikerTouchUp(this.nowColor);
      this.strR = Color.red(this.nowColor);
      this.strG = Color.green(this.nowColor);
      this.strB = Color.blue(this.nowColor);
      this.colorP.setColor(this.nowColor);
    }
    while (this.listener == null);
    this.listener.onPikerXYChange(this.nowColor);
    this.listener.onRgbChange(this.strR, this.strG, this.strB);
    return true;
  }

  protected void onTouchPoint(float paramFloat1, float paramFloat2)
  {
    this.targetX = (int)paramFloat1;
    this.targetY = (int)paramFloat2;
    this.lastX = this.thumbX;
    this.lastY = this.thumbY;
    float f = this.targetX - this.thumbX;
    if (f == 0.0F)
    {
      this.k = 1.0F;
      return;
    }
    this.k = ((this.targetY - this.lastY) / f);
  }

  public void setListener(OnColorChangedListener paramOnColorChangedListener)
  {
    this.listener = paramOnColorChangedListener;
  }

  public void setPactHeight(int paramInt)
  {
    if ((paramInt < 1) || (this.pActHeight != -1))
      return;
    this.pActHeight = paramInt;
    this.show = true;
    init();
    this.colorAreaHeiht = this.colorBM.getHeight();
    invalidate();
    System.out.println("709394setPactHeight");
  }

  public void setRgbShow(boolean paramBoolean)
  {
    this.rgbShow = paramBoolean;
  }

  public void setXYChangedListener(OnXYChangedListener paramOnXYChangedListener)
  {
    this.onXYChangedListener = paramOnXYChangedListener;
  }

  public static abstract interface OnColorChangedListener
  {
    public abstract void onPikerTouchUp(int paramInt);

    public abstract void onPikerXYChange(int paramInt);

    public abstract void onRgbChange(int paramInt1, int paramInt2, int paramInt3);
  }

  public static abstract interface OnXYChangedListener
  {
    public abstract void onXYChange(float paramFloat1, float paramFloat2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.SceneColorPickerView
 * JD-Core Version:    0.6.0
 */