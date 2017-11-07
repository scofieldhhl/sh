package com.ex.ltech.led.acti.colors;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.provider.MediaStore;

import com.ex.ltech.led.UserFerences;

public class Business
{
  public static final String pikerONOffStr = "pikerONOff";
  public static final String pikerXStr = "pikerX";
  public static final String pikerYStr = "pikerY";
  private UserFerences ferences;
  private Context pCt;

  public Business(Context paramContext)
  {
    this.pCt = paramContext;
    this.ferences = UserFerences.getUserFerences(this.pCt);
  }

  public static Bitmap zoomImage(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public Bitmap autoZoomInBM(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public String getBgPath()
  {
    return this.ferences.spFerences.getString("bgPath", "");
  }

  public String getBgType()
  {
    return this.ferences.spFerences.getString("bgType", "");
  }

  public Bitmap getBitmapFromUri(Uri paramUri)
  {
    try
    {
      Bitmap localBitmap = MediaStore.Images.Media.getBitmap(this.pCt.getContentResolver(), paramUri);
      return localBitmap;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public boolean getPikerONOff4Xml(String paramString)
  {
    return this.ferences.spFerences.getBoolean(paramString + "pikerONOff", false);
  }

  public int getPikerX4Xml(String paramString)
  {
    return this.ferences.spFerences.getInt(paramString + "pikerX", -1);
  }

  public int getPikerY4Xml(String paramString)
  {
    return this.ferences.spFerences.getInt(paramString + "pikerY", -1);
  }

  public void saveBgPath(String paramString)
  {
    this.ferences.putValue("bgPath", paramString);
  }

  public void saveBgType(String paramString)
  {
    this.ferences.putValue("bgType", paramString);
  }

  public void savePikerONOff2Xml(String paramString, boolean paramBoolean)
  {
    this.ferences.putValue(paramString + "pikerONOff", Boolean.valueOf(paramBoolean));
  }

  public void savePikerX2Xml(String paramString, int paramInt)
  {
    this.ferences.putValue(paramString + "pikerX", Integer.valueOf(paramInt));
  }

  public void savePikerY2Xml(String paramString, int paramInt)
  {
    this.ferences.putValue(paramString + "pikerY", Integer.valueOf(paramInt));
  }
}
