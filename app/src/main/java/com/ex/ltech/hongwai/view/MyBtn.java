package com.ex.ltech.hongwai.view;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.Button;
import com.ex.ltech.led.R.styleable;

public class MyBtn extends Button
{
  private Bitmap bitmap;
  private int resourceId = 0;

  public MyBtn(Context paramContext)
  {
    super(paramContext, null);
  }

  public MyBtn(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    setClickable(true);
    TypedArray localTypedArray = paramContext.getTheme().obtainStyledAttributes(paramAttributeSet, R.styleable.MyBtn, paramInt, 0);
    int i = localTypedArray.getIndexCount();
    int j = 0;
    if (j < i)
    {
      int k = localTypedArray.getIndex(j);
      switch (k)
      {
      default:
      case 0:
      }
      while (true)
      {
        j++;
        break;
        this.bitmap = BitmapFactory.decodeResource(getResources(), k);
      }
    }
    localTypedArray.recycle();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    int i = (getMeasuredWidth() - this.bitmap.getWidth()) / 2;
    paramCanvas.drawBitmap(this.bitmap, i, 0, null);
    paramCanvas.translate(0.0F, getMeasuredHeight() / 2 - (int)getTextSize());
    super.onDraw(paramCanvas);
  }

  public void setIcon(int paramInt)
  {
    this.bitmap = BitmapFactory.decodeResource(getResources(), paramInt);
    invalidate();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.MyBtn
 * JD-Core Version:    0.6.0
 */