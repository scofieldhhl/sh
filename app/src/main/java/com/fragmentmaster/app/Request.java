package com.fragmentmaster.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.io.Serializable;
import java.util.ArrayList;

public class Request
  implements Parcelable, Cloneable
{
  public static final Creator<Request> CREATOR = new Creator()
  {
    public Request createFromParcel(Parcel paramParcel)
    {
      return new Request(paramParcel);
    }

    public Request[] newArray(int paramInt)
    {
      return new Request[paramInt];
    }
  };
  private Bundle mExtras;
  private String mFragmentName;

  public Request()
  {
  }

  public Request(Parcel paramParcel)
  {
    readFromParcel(paramParcel);
  }

  public Request(Request paramRequest)
  {
    this.mFragmentName = paramRequest.mFragmentName;
    if (paramRequest.mExtras != null)
      this.mExtras = new Bundle(paramRequest.mExtras);
  }

  public Request(Class<? extends IMasterFragment> paramClass)
  {
    this.mFragmentName = paramClass.getName();
  }

  private void readFromParcel(Parcel paramParcel)
  {
    this.mFragmentName = paramParcel.readString();
    this.mExtras = paramParcel.readBundle(getClass().getClassLoader());
  }

  protected Object clone()
  {
    return new Request(this);
  }

  public int describeContents()
  {
    if (this.mExtras != null)
      return this.mExtras.describeContents();
    return 0;
  }

  public boolean[] getBooleanArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getBooleanArray(paramString);
  }

  public boolean getBooleanExtra(String paramString, boolean paramBoolean)
  {
    if (this.mExtras == null)
      return paramBoolean;
    return this.mExtras.getBoolean(paramString, paramBoolean);
  }

  public Bundle getBundleExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getBundle(paramString);
  }

  public byte[] getByteArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getByteArray(paramString);
  }

  public byte getByteExtra(String paramString, byte paramByte)
  {
    if (this.mExtras == null)
      return paramByte;
    return this.mExtras.getByte(paramString, paramByte).byteValue();
  }

  public char[] getCharArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getCharArray(paramString);
  }

  public char getCharExtra(String paramString, char paramChar)
  {
    if (this.mExtras == null)
      return paramChar;
    return this.mExtras.getChar(paramString, paramChar);
  }

  public CharSequence getCharSequenceExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getCharSequence(paramString);
  }

  public String getClassName()
  {
    return this.mFragmentName;
  }

  public double[] getDoubleArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getDoubleArray(paramString);
  }

  public double getDoubleExtra(String paramString, double paramDouble)
  {
    if (this.mExtras == null)
      return paramDouble;
    return this.mExtras.getDouble(paramString, paramDouble);
  }

  public float[] getFloatArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getFloatArray(paramString);
  }

  public float getFloatExtra(String paramString, float paramFloat)
  {
    if (this.mExtras == null)
      return paramFloat;
    return this.mExtras.getFloat(paramString, paramFloat);
  }

  public int[] getIntArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getIntArray(paramString);
  }

  public int getIntExtra(String paramString, int paramInt)
  {
    if (this.mExtras == null)
      return paramInt;
    return this.mExtras.getInt(paramString, paramInt);
  }

  public ArrayList<Integer> getIntegerArrayListExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getIntegerArrayList(paramString);
  }

  public long[] getLongArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getLongArray(paramString);
  }

  public long getLongExtra(String paramString, long paramLong)
  {
    if (this.mExtras == null)
      return paramLong;
    return this.mExtras.getLong(paramString, paramLong);
  }

  public Parcelable[] getParcelableArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getParcelableArray(paramString);
  }

  public <T extends Parcelable> ArrayList<T> getParcelableArrayListExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getParcelableArrayList(paramString);
  }

  public <T extends Parcelable> T getParcelableExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getParcelable(paramString);
  }

  public Serializable getSerializableExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getSerializable(paramString);
  }

  public short[] getShortArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getShortArray(paramString);
  }

  public short getShortExtra(String paramString, short paramShort)
  {
    if (this.mExtras == null)
      return paramShort;
    return this.mExtras.getShort(paramString, paramShort);
  }

  public String[] getStringArrayExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getStringArray(paramString);
  }

  public ArrayList<String> getStringArrayListExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getStringArrayList(paramString);
  }

  public String getStringExtra(String paramString)
  {
    if (this.mExtras == null)
      return null;
    return this.mExtras.getString(paramString);
  }

  public Request putExtra(String paramString, byte paramByte)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putByte(paramString, paramByte);
    return this;
  }

  public Request putExtra(String paramString, char paramChar)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putChar(paramString, paramChar);
    return this;
  }

  public Request putExtra(String paramString, double paramDouble)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putDouble(paramString, paramDouble);
    return this;
  }

  public Request putExtra(String paramString, float paramFloat)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putFloat(paramString, paramFloat);
    return this;
  }

  public Request putExtra(String paramString, int paramInt)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putInt(paramString, paramInt);
    return this;
  }

  public Request putExtra(String paramString, long paramLong)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putLong(paramString, paramLong);
    return this;
  }

  public Request putExtra(String paramString, Bundle paramBundle)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putBundle(paramString, paramBundle);
    return this;
  }

  public Request putExtra(String paramString, Parcelable paramParcelable)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putParcelable(paramString, paramParcelable);
    return this;
  }

  public Request putExtra(String paramString, Serializable paramSerializable)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putSerializable(paramString, paramSerializable);
    return this;
  }

  public Request putExtra(String paramString, CharSequence paramCharSequence)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putCharSequence(paramString, paramCharSequence);
    return this;
  }

  public Request putExtra(String paramString1, String paramString2)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putString(paramString1, paramString2);
    return this;
  }

  public Request putExtra(String paramString, short paramShort)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putShort(paramString, paramShort);
    return this;
  }

  public Request putExtra(String paramString, boolean paramBoolean)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putBoolean(paramString, paramBoolean);
    return this;
  }

  public Request putExtra(String paramString, byte[] paramArrayOfByte)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putByteArray(paramString, paramArrayOfByte);
    return this;
  }

  public Request putExtra(String paramString, char[] paramArrayOfChar)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putCharArray(paramString, paramArrayOfChar);
    return this;
  }

  public Request putExtra(String paramString, double[] paramArrayOfDouble)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putDoubleArray(paramString, paramArrayOfDouble);
    return this;
  }

  public Request putExtra(String paramString, float[] paramArrayOfFloat)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putFloatArray(paramString, paramArrayOfFloat);
    return this;
  }

  public Request putExtra(String paramString, int[] paramArrayOfInt)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putIntArray(paramString, paramArrayOfInt);
    return this;
  }

  public Request putExtra(String paramString, long[] paramArrayOfLong)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putLongArray(paramString, paramArrayOfLong);
    return this;
  }

  public Request putExtra(String paramString, Parcelable[] paramArrayOfParcelable)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putParcelableArray(paramString, paramArrayOfParcelable);
    return this;
  }

  public Request putExtra(String paramString, String[] paramArrayOfString)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putStringArray(paramString, paramArrayOfString);
    return this;
  }

  public Request putExtra(String paramString, short[] paramArrayOfShort)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putShortArray(paramString, paramArrayOfShort);
    return this;
  }

  public Request putExtra(String paramString, boolean[] paramArrayOfBoolean)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putBooleanArray(paramString, paramArrayOfBoolean);
    return this;
  }

  public Request putExtras(Bundle paramBundle)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putAll(paramBundle);
    return this;
  }

  public Request putExtras(Request paramRequest)
  {
    if (paramRequest.mExtras != null)
    {
      if (this.mExtras == null)
        this.mExtras = new Bundle(paramRequest.mExtras);
    }
    else
      return this;
    this.mExtras.putAll(paramRequest.mExtras);
    return this;
  }

  public Request putIntegerArrayListExtra(String paramString, ArrayList<Integer> paramArrayList)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putIntegerArrayList(paramString, paramArrayList);
    return this;
  }

  public Request putParcelableArrayListExtra(String paramString, ArrayList<? extends Parcelable> paramArrayList)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putParcelableArrayList(paramString, paramArrayList);
    return this;
  }

  public Request putStringArrayListExtra(String paramString, ArrayList<String> paramArrayList)
  {
    if (this.mExtras == null)
      this.mExtras = new Bundle();
    this.mExtras.putStringArrayList(paramString, paramArrayList);
    return this;
  }

  public void removeExtra(String paramString)
  {
    if (this.mExtras != null)
    {
      this.mExtras.remove(paramString);
      if (this.mExtras.size() == 0)
        this.mExtras = null;
    }
  }

  public Request replaceExtras(Bundle paramBundle)
  {
    if (paramBundle != null);
    for (Bundle localBundle = new Bundle(paramBundle); ; localBundle = null)
    {
      this.mExtras = localBundle;
      return this;
    }
  }

  public Request replaceExtras(Request paramRequest)
  {
    if (paramRequest.mExtras != null);
    for (Bundle localBundle = new Bundle(paramRequest.mExtras); ; localBundle = null)
    {
      this.mExtras = localBundle;
      return this;
    }
  }

  public void setClass(Class<? extends IMasterFragment> paramClass)
  {
    this.mFragmentName = paramClass.getName();
  }

  public void setClassName(String paramString)
  {
    if (paramString == null)
      throw new NullPointerException("class name is null");
    this.mFragmentName = paramString;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    try
    {
      paramParcel.writeString(this.mFragmentName);
      paramParcel.writeBundle(this.mExtras);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.Request
 * JD-Core Version:    0.6.0
 */