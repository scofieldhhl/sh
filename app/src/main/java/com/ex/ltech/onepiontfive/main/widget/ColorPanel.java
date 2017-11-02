package com.ex.ltech.onepiontfive.main.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.ex.ltech.onepiontfive.main.Constant;

public class ColorPanel extends View
{
  private String bgType = "ColorPanelType";
  private Bitmap blackWhiteBM;
  private Bitmap coldWhiteBM;
  int i = 0;
  private OnPikerMoveListener listener;
  private Paint paint;
  private Point pickerPonit;
  private Bitmap pikerBM;
  private Bitmap rgbBM;
  int time = 0;
  private int touchX;
  private int touchY;
  private Bitmap warnWhiteBM;

  public ColorPanel(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public ColorPanel(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private void init()
  {
    this.paint = new Paint();
    this.paint.setAntiAlias(true);
    this.pikerBM = BitmapFactory.decodeResource(getResources(), 2130903071);
    this.pickerPonit = new Point();
    this.pickerPonit.set(100, 100);
  }

  private void initBM(int paramInt1, int paramInt2)
  {
    int j;
    int k;
    label17: int n;
    label32: int i1;
    if (Constant.rgbBM != null)
    {
      j = 1;
      if (Constant.warnWhiteBM == null)
        break label90;
      k = 1;
      int m = k & j;
      if (Constant.blackWhiteBM == null)
        break label96;
      n = 1;
      i1 = m & n;
      if (Constant.coldWhiteBM == null)
        break label102;
    }
    label90: label96: label102: for (int i2 = 1; ; i2 = 0)
    {
      if ((i2 & i1) == 0)
        break label108;
      this.rgbBM = Constant.rgbBM;
      this.warnWhiteBM = Constant.warnWhiteBM;
      this.blackWhiteBM = Constant.blackWhiteBM;
      this.coldWhiteBM = Constant.coldWhiteBM;
      return;
      j = 0;
      break;
      k = 0;
      break label17;
      n = 0;
      break label32;
    }
    label108: this.rgbBM = BitmapFactory.decodeResource(getResources(), 2130903698);
    this.warnWhiteBM = BitmapFactory.decodeResource(getResources(), 2130903803);
    this.blackWhiteBM = BitmapFactory.decodeResource(getResources(), 2130903084);
    this.coldWhiteBM = BitmapFactory.decodeResource(getResources(), 2130903141);
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(paramInt1 / this.rgbBM.getWidth(), paramInt2 / this.rgbBM.getHeight());
    this.rgbBM = Bitmap.createBitmap(this.rgbBM, 0, 0, this.rgbBM.getWidth(), this.rgbBM.getHeight(), localMatrix, true);
    localMatrix.reset();
    localMatrix.postScale(paramInt1 / this.warnWhiteBM.getWidth(), paramInt2 / this.warnWhiteBM.getHeight());
    this.warnWhiteBM = Bitmap.createBitmap(this.warnWhiteBM, 0, 0, this.warnWhiteBM.getWidth(), this.warnWhiteBM.getHeight(), localMatrix, true);
    localMatrix.reset();
    localMatrix.postScale(paramInt1 / this.blackWhiteBM.getWidth(), paramInt2 / this.blackWhiteBM.getHeight());
    this.blackWhiteBM = Bitmap.createBitmap(this.blackWhiteBM, 0, 0, this.blackWhiteBM.getWidth(), this.blackWhiteBM.getHeight(), localMatrix, true);
    localMatrix.reset();
    localMatrix.postScale(paramInt1 / this.coldWhiteBM.getWidth(), paramInt2 / this.coldWhiteBM.getHeight());
    this.coldWhiteBM = Bitmap.createBitmap(this.coldWhiteBM, 0, 0, this.coldWhiteBM.getWidth(), this.coldWhiteBM.getHeight(), localMatrix, true);
    Constant.rgbBM = this.rgbBM;
    Constant.warnWhiteBM = this.warnWhiteBM;
    Constant.blackWhiteBM = this.blackWhiteBM;
    Constant.coldWhiteBM = this.coldWhiteBM;
  }

  public String getBgType()
  {
    return this.bgType;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    String str = this.bgType;
    int j = -1;
    switch (str.hashCode())
    {
    default:
      switch (j)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      }
    case 1036044123:
    case -692461275:
    case 878674825:
    case 1462224095:
    }
    while (true)
    {
      paramCanvas.drawBitmap(this.pikerBM, this.pickerPonit.x, this.pickerPonit.y, this.paint);
      return;
      if (!str.equals("ColorPanelType"))
        break;
      j = 0;
      break;
      if (!str.equals("ColorPanelTypeBlackWhite"))
        break;
      j = 1;
      break;
      if (!str.equals("ColorPanelTypeWarmWhite"))
        break;
      j = 2;
      break;
      if (!str.equals("ColorPanelTypeCold"))
        break;
      j = 3;
      break;
      paramCanvas.drawBitmap(this.rgbBM, 0.0F, 0.0F, this.paint);
      continue;
      paramCanvas.drawBitmap(this.blackWhiteBM, 0.0F, 0.0F, this.paint);
      continue;
      paramCanvas.drawBitmap(this.warnWhiteBM, 0.0F, 0.0F, this.paint);
      continue;
      paramCanvas.drawBitmap(this.coldWhiteBM, 0.0F, 0.0F, this.paint);
    }
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int j = 1;
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    int k;
    int m;
    label32: int i1;
    label49: int i2;
    if (Constant.rgbBM == null)
    {
      k = j;
      if (Constant.warnWhiteBM != null)
        break label83;
      m = j;
      int n = m & k;
      if (Constant.blackWhiteBM != null)
        break label89;
      i1 = j;
      i2 = i1 & n;
      if (Constant.coldWhiteBM != null)
        break label95;
    }
    while (true)
    {
      if ((i2 & j) == 0)
        break label101;
      initBM(paramInt1, paramInt2);
      return;
      k = 0;
      break;
      label83: m = 0;
      break label32;
      label89: i1 = 0;
      break label49;
      label95: j = 0;
    }
    label101: this.rgbBM = Constant.rgbBM;
    this.warnWhiteBM = Constant.warnWhiteBM;
    this.blackWhiteBM = Constant.blackWhiteBM;
    this.coldWhiteBM = Constant.coldWhiteBM;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    this.time = (1 + this.time);
    this.touchX = (int)paramMotionEvent.getX();
    this.touchY = (int)paramMotionEvent.getY();
    this.pickerPonit.set(this.touchX - this.pikerBM.getWidth() / 2, this.touchY - this.pikerBM.getHeight());
    if ((this.touchY > this.pikerBM.getHeight() / 2) && (this.touchY < this.warnWhiteBM.getHeight()))
    {
      invalidate();
      try
      {
        String str = this.bgType;
        switch (str.hashCode())
        {
        case 1036044123:
          int m = getMeasuredWidth();
          int n = 100 * (-20 + this.touchX) / (m * 90 / 100);
          if ((this.time % 5 == 0) && (this.listener != null) && (paramMotionEvent.getAction() == 2))
            this.listener.onXChange(n, this.touchX, this.touchY, false);
          if ((this.listener == null) || (paramMotionEvent.getAction() != 1))
            break label408;
          this.listener.onXChange(n, this.touchX, this.touchY, true);
          return true;
          if (str.equals("ColorPanelType"))
          {
            j = 0;
            break label387;
            int k = this.rgbBM.getPixel(this.touchX, this.touchY);
            if ((this.time % 5 == 0) && (this.listener != null) && (paramMotionEvent.getAction() == 2))
              this.listener.onColorChange(k, Color.red(k), Color.green(k), Color.blue(k), this.touchX, this.touchY, false);
            if ((this.listener == null) || (paramMotionEvent.getAction() != 1))
              break label408;
            this.listener.onColorChange(k, Color.red(k), Color.green(k), Color.blue(k), this.touchX, this.touchY, true);
            return true;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return true;
      }
      int j = -1;
      label387: switch (j)
      {
      default:
      case 0:
      }
    }
    label408: return true;
  }

  public void setBgType(String paramString)
  {
    this.bgType = paramString;
    invalidate();
  }

  public void setListener(OnPikerMoveListener paramOnPikerMoveListener)
  {
    this.listener = paramOnPikerMoveListener;
  }

  public void setPikerXy(int paramInt1, int paramInt2)
  {
    this.pickerPonit.set(paramInt1 - this.pikerBM.getWidth() / 2, paramInt2 - this.pikerBM.getHeight());
    invalidate();
  }

  public static abstract interface OnPikerMoveListener
  {
    public abstract void onColorChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean);

    public abstract void onXChange(float paramFloat, int paramInt1, int paramInt2, boolean paramBoolean);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.ColorPanel
 * JD-Core Version:    0.6.0
 */