package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.vo.TouchArea;
import java.io.PrintStream;

public class SimpleColorPickerView extends View
{
  private int circlePosi;
  private int colorAreaHeiht;
  private Bitmap colorBM;
  private Paint colorP;
  private TouchArea currentTouchArea = new TouchArea();
  private Paint generalP;
  int i = 0;
  private boolean isCirclePosiShow;
  private OnColorChangedListener listener;
  public int nowColor;
  OnColorPickerViewOk onColorPickerViewOk;
  private OnXYChangedListener onXYChangedListener;
  String openType = "";
  private Paint ovalP;
  private int pActHeight;
  private Point pickerPonit;
  private Bitmap pikerBM;
  private boolean rgbShow = true;
  private int screenHeight;
  private int screenWidth;
  private boolean show;
  public int strB;
  public int strG;
  public int strR;
  private Paint textP;
  private int topBarHeight;
  private int touchX;
  private int touchY;

  public SimpleColorPickerView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public SimpleColorPickerView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public SimpleColorPickerView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  private void drawColorArea(Canvas paramCanvas)
  {
    int j = 1;
    int k;
    if (this.colorBM == null)
    {
      k = j;
      if (this.pikerBM != null)
        break label30;
    }
    while (true)
    {
      if ((k | j) == 0)
        break label35;
      return;
      k = 0;
      break;
      label30: j = 0;
    }
    label35: paramCanvas.drawBitmap(this.colorBM, 0.0F, 0.0F, this.generalP);
    paramCanvas.drawBitmap(this.pikerBM, this.pickerPonit.x, this.pickerPonit.y, this.generalP);
  }

  private void init()
  {
    initGlobal();
    initTopBar();
    initColorArea();
  }

  private void initColorArea()
  {
    this.pikerBM = BitmapFactory.decodeResource(getResources(), 2130903073);
    this.colorAreaHeiht = this.colorBM.getHeight();
    this.pickerPonit = new Point(this.screenWidth / 2, this.topBarHeight + this.colorAreaHeiht / 3);
    this.nowColor = this.colorBM.getPixel(this.pickerPonit.x, this.pickerPonit.y - this.topBarHeight);
    this.strR = Color.red(this.nowColor);
    this.strG = Color.green(this.nowColor);
    this.strB = Color.blue(this.nowColor);
  }

  private void initGlobal()
  {
    this.generalP = new Paint();
    this.generalP.setAntiAlias(true);
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
    this.screenHeight = localWindowManager.getDefaultDisplay().getHeight();
    this.screenWidth = localWindowManager.getDefaultDisplay().getWidth();
    this.colorBM = BitmapFactory.decodeResource(getResources(), 2130903616);
    float f1 = 1.0F;
    float f2 = 1.0F;
    if (this.screenWidth > this.colorBM.getWidth())
      f1 = this.screenWidth / this.colorBM.getWidth();
    if (this.screenHeight > this.pActHeight)
      f2 = (this.pActHeight - dip2px(super.getContext(), 100.0F)) / this.colorBM.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    this.colorBM = Bitmap.createBitmap(this.colorBM, 0, 0, this.colorBM.getWidth(), this.colorBM.getHeight(), localMatrix, true);
  }

  private void initTopBar()
  {
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

  public void drawCircleColorPosi(int paramInt)
  {
    this.isCirclePosiShow = true;
    this.circlePosi = paramInt;
    invalidate();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (isInEditMode())
      return;
    drawColorArea(paramCanvas);
    System.out.println("onDraw");
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.touchX = (int)paramMotionEvent.getX();
    this.touchY = (int)paramMotionEvent.getY();
    if ((this.touchX >= 0) && (this.touchY >= this.topBarHeight) && (this.touchY < -10 + (this.topBarHeight + this.colorAreaHeiht) - this.pikerBM.getHeight()) && (this.touchX < getWidth()))
      switch (paramMotionEvent.getAction())
      {
      default:
        this.pickerPonit.set(this.touchX - this.pikerBM.getWidth() / 2, this.touchY - this.pikerBM.getHeight());
        this.nowColor = this.colorBM.getPixel(this.touchX, this.touchY - this.topBarHeight);
        this.strR = Color.red(this.nowColor);
        this.strG = Color.green(this.nowColor);
        this.strB = Color.blue(this.nowColor);
        if (this.listener != null)
          this.listener.onPikerXYChange(this.nowColor, this.strR, this.strG, this.strB);
        if (this.onXYChangedListener != null)
          this.onXYChangedListener.onPikerXYChange(this.touchX, this.touchY, (paramMotionEvent.getY() - 20.0F) / (90 * this.colorBM.getHeight() / 100), false);
        invalidate();
      case 1:
      }
    while (true)
    {
      this.i = (1 + this.i);
      if ((this.i % 10 == 0) && (this.listener != null))
        this.listener.onProgressPercent((paramMotionEvent.getX() - 20.0F) / (90 * this.colorBM.getWidth() / 100));
      return true;
      if (this.listener != null)
        this.listener.onProgressPercent((paramMotionEvent.getX() - 20.0F) / (90 * this.colorBM.getWidth() / 100));
      if (this.onXYChangedListener == null)
        continue;
      this.onXYChangedListener.onPikerXYChange(this.touchX, this.touchY, (paramMotionEvent.getY() - 20.0F) / (90 * this.colorBM.getHeight() / 100), true);
    }
  }

  public void setListener(OnColorChangedListener paramOnColorChangedListener)
  {
    this.listener = paramOnColorChangedListener;
  }

  public void setOnColorPickerViewOk(OnColorPickerViewOk paramOnColorPickerViewOk)
  {
    this.onColorPickerViewOk = paramOnColorPickerViewOk;
  }

  public void setOnXYChangedListener(OnXYChangedListener paramOnXYChangedListener)
  {
    this.onXYChangedListener = paramOnXYChangedListener;
  }

  public void setOpenType(String paramString)
  {
    this.openType = paramString;
  }

  public void setPactHeight(int paramInt)
  {
    this.pActHeight = paramInt;
    this.show = true;
    init();
    this.colorAreaHeiht = this.colorBM.getHeight();
    invalidate();
  }

  public void setPactHeight(int paramInt1, int paramInt2)
  {
    this.pActHeight = paramInt2;
    this.show = true;
    init();
    this.colorAreaHeiht = this.colorBM.getHeight();
    invalidate();
  }

  public void setPikerXY(int paramInt1, int paramInt2)
  {
    this.pickerPonit.set(paramInt1, paramInt2);
    invalidate();
  }

  public void setPikerXy(int paramInt)
  {
    this.pickerPonit = new Point(paramInt * this.screenWidth / 100, this.topBarHeight + this.colorAreaHeiht / 3);
    invalidate();
  }

  public void setPikerXy2(int paramInt)
  {
    this.pickerPonit = new Point(paramInt * this.screenWidth / 100, this.topBarHeight + this.colorAreaHeiht / 3);
    invalidate();
  }

  public void setRgbShow(boolean paramBoolean)
  {
    this.rgbShow = paramBoolean;
  }

  public void setViewBgFullScreen(int paramInt, boolean paramBoolean)
  {
    this.pActHeight = this.pActHeight;
    this.show = true;
    if (paramBoolean);
    for (int j = this.pActHeight; ; j = this.pActHeight / 2)
    {
      this.colorAreaHeiht = j;
      this.generalP = new Paint();
      this.generalP.setAntiAlias(true);
      WindowManager localWindowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
      this.screenHeight = localWindowManager.getDefaultDisplay().getHeight();
      this.screenWidth = localWindowManager.getDefaultDisplay().getWidth();
      this.colorBM = BitmapFactory.decodeResource(getResources(), paramInt);
      float f1 = 1.0F;
      if (this.screenWidth > this.colorBM.getWidth())
        f1 = this.screenWidth / this.colorBM.getWidth();
      float f2 = this.screenHeight / this.colorBM.getHeight();
      Matrix localMatrix = new Matrix();
      localMatrix.postScale(f1, f2);
      this.colorBM = Bitmap.createBitmap(this.colorBM, 0, 0, this.colorBM.getWidth(), this.colorBM.getHeight(), localMatrix, true);
      initColorArea();
      invalidate();
      return;
    }
  }

  public void setViewBgRes(int paramInt, boolean paramBoolean)
  {
    this.pActHeight = this.pActHeight;
    this.show = true;
    if (paramBoolean);
    for (int j = this.pActHeight; ; j = this.pActHeight / 2)
    {
      this.colorAreaHeiht = j;
      this.generalP = new Paint();
      this.generalP.setAntiAlias(true);
      WindowManager localWindowManager = (WindowManager)getContext().getSystemService(Context.WINDOW_SERVICE);
      this.screenHeight = localWindowManager.getDefaultDisplay().getHeight();
      this.screenWidth = localWindowManager.getDefaultDisplay().getWidth();
      this.colorBM = BitmapFactory.decodeResource(getResources(), paramInt);
      float f1 = 1.0F;
      if (this.screenWidth > this.colorBM.getWidth())
        f1 = this.screenWidth / this.colorBM.getWidth();
      float f2 = BitmapUtils.dp2px(super.getContext(), 480.0F) / this.colorBM.getHeight();
      Matrix localMatrix = new Matrix();
      localMatrix.postScale(f1, f2);
      this.colorBM = Bitmap.createBitmap(this.colorBM, 0, 0, this.colorBM.getWidth(), this.colorBM.getHeight(), localMatrix, true);
      initColorArea();
      invalidate();
      return;
    }
  }

  public void setpActHeight(int paramInt)
  {
    this.pActHeight = paramInt;
    invalidate();
  }

  public static abstract interface OnColorChangedListener
  {
    public abstract void onPikerTouchUp(int paramInt);

    public abstract void onPikerXYChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    public abstract void onProgressPercent(float paramFloat);
  }

  public static abstract interface OnColorPickerViewOk
  {
    public abstract void onViewOn();
  }

  public static abstract interface OnXYChangedListener
  {
    public abstract void onPikerXYChange(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean);
  }
}
