package com.ex.ltech.led.my_view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import com.ex.ltech.led.R.styleable;

public class MLImageView extends ImageView
{
  private int mHeight;
  private Paint mPressPaint;
  private int mShapeType;
  private int mWidth;

  public MLImageView(Context paramContext)
  {
    super(paramContext);
    init(paramContext, null);
  }

  public MLImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public MLImageView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private void drawDrawable(Canvas paramCanvas, Bitmap paramBitmap)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(-1);
    localPaint.setAntiAlias(true);
    PorterDuffXfermode localPorterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    paramCanvas.saveLayer(0.0F, 0.0F, this.mWidth, this.mHeight, null, 31);
    if (this.mShapeType == 0)
      paramCanvas.drawCircle(this.mWidth / 2, this.mHeight / 2, this.mWidth / 2, localPaint);
    localPaint.setXfermode(localPorterDuffXfermode);
    float f1 = getWidth() / paramBitmap.getWidth();
    float f2 = getHeight() / paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1, f2);
    paramCanvas.drawBitmap(Bitmap.createBitmap(paramBitmap, 0, 0, paramBitmap.getWidth(), paramBitmap.getHeight(), localMatrix, true), 0.0F, 0.0F, localPaint);
    paramCanvas.restore();
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    this.mShapeType = 1;
    if (paramAttributeSet != null)
    {
      TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.MLImageView);
      this.mShapeType = localTypedArray.getInteger(5, this.mShapeType);
      localTypedArray.recycle();
    }
    setClickable(true);
    setDrawingCacheEnabled(true);
    setWillNotDraw(false);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    Drawable localDrawable = getDrawable();
    if (localDrawable == null);
    Bitmap localBitmap;
    do
    {
      do
        return;
      while ((getWidth() == 0) || (getHeight() == 0));
      localBitmap = ((BitmapDrawable)localDrawable).getBitmap();
    }
    while (localBitmap == null);
    drawDrawable(paramCanvas, localBitmap);
  }

  protected void onSizeChanged(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onSizeChanged(paramInt1, paramInt2, paramInt3, paramInt4);
    this.mWidth = paramInt1;
    this.mHeight = paramInt2;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MLImageView
 * JD-Core Version:    0.6.0
 */