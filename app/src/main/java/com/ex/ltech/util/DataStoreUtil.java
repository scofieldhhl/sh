package com.ex.ltech.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.hzy.tvmao.KookongSDK;

public class DataStoreUtil
{
  private static DataStoreUtil sDataStoreUtil;
  public final String DATASTORE = "datastore";
  protected SharedPreferences mSharedPreferences;

  public static DataStoreUtil i()
  {
    if (sDataStoreUtil == null)
    {
      sDataStoreUtil = new DataStoreUtil();
      sDataStoreUtil.setPath();
    }
    return sDataStoreUtil;
  }

  public void clear()
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.clear();
    localEditor.commit();
  }

  public boolean getBoolean(String paramString, Boolean paramBoolean)
  {
    return this.mSharedPreferences.getBoolean(paramString, paramBoolean.booleanValue());
  }

  public float getFloat(String paramString, float paramFloat)
  {
    return this.mSharedPreferences.getFloat(paramString, paramFloat);
  }

  public int getInt(String paramString, int paramInt)
  {
    return this.mSharedPreferences.getInt(paramString, paramInt);
  }

  public String getString(String paramString1, String paramString2)
  {
    return this.mSharedPreferences.getString(paramString1, paramString2);
  }

  public String[] getStringArray(String paramString)
  {
    return this.mSharedPreferences.getString(paramString, "").split("#~");
  }

  public void putBoolean(String paramString, boolean paramBoolean)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putBoolean(paramString, paramBoolean);
    localEditor.commit();
  }

  public void putFloat(String paramString, float paramFloat)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putFloat(paramString, paramFloat);
    localEditor.commit();
  }

  public void putInt(String paramString, int paramInt)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putInt(paramString, paramInt);
    localEditor.commit();
  }

  public boolean putString(String paramString1, String paramString2)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.putString(paramString1, paramString2);
    return localEditor.commit();
  }

  public void putStringArray(String paramString, String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      int i = paramArrayOfString.length;
      for (int j = 0; j < i; j++)
        localStringBuilder.append(paramArrayOfString[j]).append("#~");
      SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
      localEditor.putString(paramString, localStringBuilder.toString());
      localEditor.commit();
    }
  }

  public boolean remove(String paramString)
  {
    SharedPreferences.Editor localEditor = this.mSharedPreferences.edit();
    localEditor.remove(paramString);
    return localEditor.commit();
  }

  protected void setPath()
  {
    this.mSharedPreferences = KookongSDK.getContext().getSharedPreferences("datastore", 0);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.util.DataStoreUtil
 * JD-Core Version:    0.6.0
 */