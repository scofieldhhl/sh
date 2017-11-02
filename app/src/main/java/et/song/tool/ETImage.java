package et.song.tool;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.ThumbnailUtils;
import android.widget.ImageView;
import java.io.FileInputStream;
import java.io.InputStream;

public final class ETImage
{
  public static Bitmap clipBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return Bitmap.createBitmap(paramBitmap, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  private static int computeInitialSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    double d1 = paramOptions.outWidth;
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
    return j;
  }

  private static int computeSampleSize(Options paramOptions, int paramInt1, int paramInt2)
  {
    int i = computeInitialSampleSize(paramOptions, paramInt1, paramInt2);
    if (i <= 8)
    {
      int j = 1;
      while (true)
      {
        if (j >= i)
          return j;
        j <<= 1;
      }
    }
    return 8 * ((i + 7) / 8);
  }

  public static Bitmap getImageThumbnail(String paramString, int paramInt1, int paramInt2)
  {
    Options localOptions = new Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inJustDecodeBounds = false;
    localOptions.inSampleSize = computeSampleSize(localOptions, -1, paramInt1 * paramInt2);
    return ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(paramString, localOptions), paramInt1, paramInt2, 2);
  }

  public static Bitmap getNetImageThumbnail(InputStream paramInputStream, ImageView paramImageView, int paramInt1, int paramInt2)
  {
    return new ETAsync().loadDrawable(paramInputStream, (String)paramImageView.getTag(), new ETAsync.ImageCallback(paramInt1, paramInt2, paramImageView)
    {
      public void imageLoaded(byte[] paramArrayOfByte)
      {
        Options localOptions = new Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
        localOptions.inJustDecodeBounds = false;
        localOptions.inSampleSize = ETImage.access$0(localOptions, -1, this.val$width * this.val$height);
        Bitmap localBitmap = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions), this.val$width, this.val$height, 2);
        if (this.val$view != null)
          this.val$view.setImageBitmap(localBitmap);
      }
    });
  }

  public static Bitmap matrixBitmap(Bitmap paramBitmap, int paramInt1, int paramInt2)
    throws Exception
  {
    Matrix localMatrix = new Matrix();
    int i = paramBitmap.getWidth();
    int j = paramBitmap.getHeight();
    float f1 = (float)(1.0D * paramInt1 / i);
    float f2 = (float)(1.0D * paramInt2 / j);
    localMatrix.postScale(1.0F * f1, 1.0F * f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, i, j, localMatrix, true);
  }

  public static Bitmap readBitmap(Context paramContext, int paramInt)
    throws Exception
  {
    InputStream localInputStream = paramContext.getResources().openRawResource(paramInt);
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeStream(localInputStream, null, localOptions);
  }

  public static Bitmap readBitmap(Context paramContext, int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    InputStream localInputStream = paramContext.getResources().openRawResource(paramInt1);
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(localInputStream, null, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions, -1, paramInt2 * paramInt3);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeStream(localInputStream, null, localOptions);
  }

  public static Bitmap readBitmap(Context paramContext, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws Exception
  {
    if (paramBoolean)
    {
      InputStream localInputStream2 = paramContext.getResources().openRawResource(paramInt1);
      Options localOptions2 = new Options();
      localOptions2.inPreferredConfig = Config.RGB_565;
      localOptions2.inPurgeable = true;
      localOptions2.inInputShareable = true;
      localOptions2.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(localInputStream2, null, localOptions2);
      localOptions2.inSampleSize = computeSampleSize(localOptions2, -1, paramInt2 * paramInt3);
      localOptions2.inJustDecodeBounds = false;
      return matrixBitmap(BitmapFactory.decodeStream(localInputStream2, null, localOptions2), paramInt2, paramInt3);
    }
    InputStream localInputStream1 = paramContext.getResources().openRawResource(paramInt1);
    Options localOptions1 = new Options();
    localOptions1.inPreferredConfig = Config.RGB_565;
    localOptions1.inPurgeable = true;
    localOptions1.inInputShareable = true;
    localOptions1.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(localInputStream1, null, localOptions1);
    localOptions1.inSampleSize = computeSampleSize(localOptions1, -1, paramInt2 * paramInt3);
    localOptions1.inJustDecodeBounds = false;
    return BitmapFactory.decodeStream(localInputStream1, null, localOptions1);
  }

  public static Bitmap readBitmap(Resources paramResources, int paramInt)
    throws Exception
  {
    InputStream localInputStream = paramResources.openRawResource(paramInt);
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    return BitmapFactory.decodeStream(localInputStream, null, localOptions);
  }

  public static Bitmap readBitmap(Resources paramResources, int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    InputStream localInputStream = paramResources.openRawResource(paramInt1);
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(localInputStream, null, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions, -1, paramInt2 * paramInt3);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeStream(localInputStream, null, localOptions);
  }

  public static Bitmap readBitmap(Resources paramResources, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    throws Exception
  {
    if (paramBoolean)
    {
      InputStream localInputStream2 = paramResources.openRawResource(paramInt1);
      Options localOptions2 = new Options();
      localOptions2.inPreferredConfig = Config.RGB_565;
      localOptions2.inPurgeable = true;
      localOptions2.inInputShareable = true;
      localOptions2.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(localInputStream2, null, localOptions2);
      localOptions2.inSampleSize = computeSampleSize(localOptions2, -1, paramInt2 * paramInt3);
      localOptions2.inJustDecodeBounds = false;
      return matrixBitmap(BitmapFactory.decodeStream(localInputStream2, null, localOptions2), paramInt2, paramInt3);
    }
    InputStream localInputStream1 = paramResources.openRawResource(paramInt1);
    Options localOptions1 = new Options();
    localOptions1.inPreferredConfig = Config.RGB_565;
    localOptions1.inPurgeable = true;
    localOptions1.inInputShareable = true;
    localOptions1.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(localInputStream1, null, localOptions1);
    localOptions1.inSampleSize = computeSampleSize(localOptions1, -1, paramInt2 * paramInt3);
    localOptions1.inJustDecodeBounds = false;
    return BitmapFactory.decodeStream(localInputStream1, null, localOptions1);
  }

  public static Bitmap readBitmap(String paramString, int paramInt1, int paramInt2)
    throws Exception
  {
    Options localOptions = new Options();
    localOptions.inPreferredConfig = Config.RGB_565;
    localOptions.inPurgeable = true;
    localOptions.inInputShareable = true;
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeFile(paramString, localOptions);
    localOptions.inSampleSize = computeSampleSize(localOptions, -1, paramInt1 * paramInt2);
    localOptions.inJustDecodeBounds = false;
    return BitmapFactory.decodeStream(new FileInputStream(paramString), new Rect(0, 0, 0, 0), localOptions);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETImage
 * JD-Core Version:    0.6.0
 */