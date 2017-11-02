package com.fragmentmaster.app;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class FragmentMasterState
  implements Parcelable
{
  public static final Creator<FragmentMasterState> CREATOR = new Creator()
  {
    public FragmentMasterState createFromParcel(Parcel paramParcel)
    {
      return new FragmentMasterState(paramParcel, null);
    }

    public FragmentMasterState[] newArray(int paramInt)
    {
      return new FragmentMasterState[paramInt];
    }
  };
  Bundle mFragments;
  boolean mHomeFragmentApplied;
  boolean mIsSlideable;

  public FragmentMasterState()
  {
  }

  private FragmentMasterState(Parcel paramParcel)
  {
    this.mFragments = paramParcel.readBundle(getClass().getClassLoader());
    boolean bool2;
    if (paramParcel.readInt() == 0)
    {
      bool2 = bool1;
      this.mIsSlideable = bool2;
      if (paramParcel.readInt() != 0)
        break label53;
    }
    while (true)
    {
      this.mHomeFragmentApplied = bool1;
      return;
      bool2 = false;
      break;
      label53: bool1 = false;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeBundle(this.mFragments);
    int i;
    int j;
    if (this.mIsSlideable)
    {
      i = 0;
      paramParcel.writeInt(i);
      boolean bool = this.mHomeFragmentApplied;
      j = 0;
      if (!bool)
        break label48;
    }
    while (true)
    {
      paramParcel.writeInt(j);
      return;
      i = 1;
      break;
      label48: j = 1;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentMasterState
 * JD-Core Version:    0.6.0
 */