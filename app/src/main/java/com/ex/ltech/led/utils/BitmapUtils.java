package com.ex.ltech.led.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class BitmapUtils
{
  public static byte[] Bitmap2Bytes(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(CompressFormat.PNG, 100, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  public static Bitmap ImageCenterCrop(Bitmap paramBitmap)
  {
    /*int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k;
    label28: label33: int m;
    if (i > j)
    {
      k = j;
      if (i <= j)
        break label67;
      ((i - j) / 2);
      if (i <= j)
        break label70;
      if (i != k)
        break label79;
      m = 0;
      label41: if (j != k)
        break label91;
    }
    label67: label70: label79: label91: for (int n = 0; ; n = j / 2 - k / 2)
    {
      return Bitmap.createBitmap(paramBitmap, m, n, k, k, null, false);
      k = i;
      break;
      break label28;
      ((j - i) / 2);
      break label33;
      m = i / 2 - k / 2;
      break label41;
    }*/
    return null;
  }

  public static Bitmap ImageCrop(Bitmap paramBitmap)
  {
    /*int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k;
    int m;
    if (i > j)
    {
      k = j;
      if (i <= j)
        break label55;
      m = (i - j) / 2;
      label29: if (i <= j)
        break label61;
    }
    label55: label61: for (int n = 0; ; n = (j - i) / 2)
    {
      return Bitmap.createBitmap(paramBitmap, m, n, k, k, null, false);
      k = i;
      break;
      m = 0;
      break label29;
    }*/
    return null;
  }

  public static Bitmap ImageCrop2(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    /*int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    int k;
    if (i > j)
      k = (i - j) / 2;
    while (true)
    {
      int m = 0;
      if (i > j);
      try
      {
        while (true)
        {
          Bitmap localBitmap = Bitmap.createBitmap(paramBitmap, k, m, paramInt1, paramInt2, null, false);
          return localBitmap;
          k = 0;
          break;
          m = (j - i) / 2;
        }
      }
      catch (Exception localException)
      {
      }
    }
    return paramBitmap;*/
    return null;
  }

  public static Bitmap autoZoomInBM(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public static Bitmap comp(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(CompressFormat.JPEG, 100, localByteArrayOutputStream);
    if (localByteArrayOutputStream.toByteArray().length / 1024 > 1024)
    {
      localByteArrayOutputStream.reset();
      paramBitmap.compress(CompressFormat.JPEG, 50, localByteArrayOutputStream);
    }
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(localByteArrayOutputStream.toByteArray());
    Options localOptions = new Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(localByteArrayInputStream, null, localOptions);
    localOptions.inJustDecodeBounds = false;
    int i = localOptions.outWidth;
    int j = localOptions.outHeight;
    int k = 1;
    if ((i > j) && (i > 480.0F));
    for (k = (int)(localOptions.outWidth / 480.0F); ; k = (int)(localOptions.outHeight / 800.0F))
      do
      {
        if (k <= 0)
          k = 1;
        localOptions.inSampleSize = k;
        localOptions.inPreferredConfig = Config.RGB_565;
        return compressImage(BitmapFactory.decodeStream(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()), null, localOptions));
      }
      while ((i >= j) || (j <= 800.0F));
  }

  public static Bitmap compressImage(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(CompressFormat.JPEG, 100, localByteArrayOutputStream);
    int i = 100;
    while (localByteArrayOutputStream.toByteArray().length / 1024 > 100)
    {
      localByteArrayOutputStream.reset();
      i -= 10;
      paramBitmap.compress(CompressFormat.JPEG, i, localByteArrayOutputStream);
    }
    return BitmapFactory.decodeStream(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()), null, null);
  }

  public static Bitmap compressImage(Bitmap paramBitmap, int paramInt)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(CompressFormat.PNG, 100, localByteArrayOutputStream);
    int i = 100;
    while ((i > 10) && (localByteArrayOutputStream.toByteArray().length / 1024 > paramInt))
    {
      localByteArrayOutputStream.reset();
      i -= 10;
      paramBitmap.compress(CompressFormat.PNG, i, localByteArrayOutputStream);
    }
    return BitmapFactory.decodeStream(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()), null, null);
  }

  public static int computeInitialSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    /*double d1 = paramOptions.outWidth;
    double d2 = paramOptions.outHeight;
    int i;
    int j;
    if (paramInt2 == -1)
    {
      i = 1;
      if (paramInt1 != -1)
        break label60;
      j = 128;
      label31: if (j >= i)
        break label84;
    }
    label60: label84:
    do
    {
      return i;
      i = (int)Math.ceil(Math.sqrt(d1 * d2 / paramInt2));
      break;
      j = (int)Math.min(Math.floor(d1 / paramInt1), Math.floor(d2 / paramInt1));
      break label31;
      if ((paramInt2 == -1) && (paramInt1 == -1))
        return 1;
    }
    while (paramInt1 == -1);
    return j;*/
    return -1;
  }

  public static int computeSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    /*int i = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      j = 1;
      while (j < i)
        j <<= 1;
    }
    int j = 8 * ((i + 7) / 8);
    return j;*/
    return -1;
  }

  public static int dp2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5F + paramFloat * paramContext.getResources().getDisplayMetrics().density);
  }

  public static Bitmap drawableToBitmap(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    if (paramDrawable.getOpacity() != PixelFormat.OPAQUE);
    for (Config localConfig = Config.ARGB_8888; ; localConfig = Config.RGB_565)
    {
      Bitmap localBitmap = Bitmap.createBitmap(paramInt1, paramInt2, localConfig);
      Canvas localCanvas = new Canvas(localBitmap);
      paramDrawable.setBounds(0, 0, paramInt1, paramInt2);
      paramDrawable.draw(localCanvas);
      return localBitmap;
    }
  }

  public static Bitmap getBitmapFromUri(Context paramContext, Uri paramUri)
  {
    try
    {
      Bitmap localBitmap = MediaStore.Images.Media.getBitmap(paramContext.getContentResolver(), paramUri);
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static int getExifOrientation(String paramString)
  {
    /*try
    {
      ExifInterface localExifInterface1 = new ExifInterface(paramString);
      localExifInterface2 = localExifInterface1;
      int i;
      if (localExifInterface2 != null)
      {
        i = localExifInterface2.getAttributeInt("Orientation", -1);
        if (i == -1);
      }
      switch (i)
      {
      case 4:
      case 5:
      case 7:
      default:
        return 0;
      case 6:
        return 90;
      case 3:
        return 180;
      case 8:
      }
      return 270;
    }
    catch (IOException localIOException)
    {
      while (true)
        ExifInterface localExifInterface2 = null;
    }*/
    return -1;
  }

  public static Bitmap getImageBit(String paramString)
  {
    Options localOptions = new Options();
    localOptions.inJustDecodeBounds = true;
    Object localObject = BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions, 200, 250000);
    localOptions.inJustDecodeBounds = false;
    try
    {
      Bitmap localBitmap = BitmapFactory.decodeFile(paramString, localOptions);
      localObject = localBitmap;
      return ImageCrop((Bitmap)localObject);
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      while (true)
        localOutOfMemoryError.printStackTrace();
    }
  }

  public static Bitmap readBitMap(Resources paramResources, int paramInt)
  {
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeStream(paramResources.openRawResource(paramInt), null, localOptions);
  }

  public static Bitmap squareCropRectangle(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    int i = paramBitmap.getWidth();
    float f1 = paramInt1;
    float f2 = paramInt2;
    int j = (int)(f2 / f1 * i);
    int k = i / 2 - j / 2;
    Matrix localMatrix = new Matrix();
    localMatrix.postScale(f1 / i, f2 / j);
    return Bitmap.createBitmap(paramBitmap, 0, k, i, j, localMatrix, false);
  }

  public static Bitmap zoomOutBM(Bitmap paramBitmap, double paramDouble)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    float f3 = (float)paramDouble / f1;
    localMatrix.postScale(f3, f3);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.BitmapUtils
 * JD-Core Version:    0.6.0
 */