package com.ex.ltech.led.my_view.gallery_view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader.TileMode;

public class BitmapUtils
{
  public static Bitmap createReflectedBitmap(Bitmap paramBitmap, float paramFloat)
  {
    if (paramBitmap == null)
      return null;
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k = paramBitmap.getWidth();
    if (paramFloat == 0.0F);
    for (int m = j / 3; (i == 0) || (j == 0); m = (int)(paramFloat * j))
      return null;
    Matrix localMatrix = new Matrix();
    localMatrix.preScale(1.0F, -1.0F);
    int n = j - m;
    try
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, 0, n, k, m, localMatrix, false);
      if (localBitmap == null)
        return null;
      Canvas localCanvas = new Canvas(localBitmap);
      Paint localPaint = new Paint();
      localPaint.setAntiAlias(true);
      localPaint.setShader(new LinearGradient(0.0F, 0.0F, 0.0F, localBitmap.getHeight(), 1895825407, 16777215, Shader.TileMode.MIRROR));
      localPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
      localCanvas.drawRect(0.0F, 0.0F, localBitmap.getWidth(), localBitmap.getHeight(), localPaint);
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static final Bitmap grey(Bitmap paramBitmap, float paramFloat)
  {
    Bitmap localBitmap = Bitmap.createBitmap(paramBitmap.getWidth(), paramBitmap.getHeight(), Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    Paint localPaint = new Paint();
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(1.0F - paramFloat);
    localPaint.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    localCanvas.drawBitmap(paramBitmap, 0.0F, 0.0F, localPaint);
    return localBitmap;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.gallery_view.BitmapUtils
 * JD-Core Version:    0.6.0
 */