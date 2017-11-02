package com.fragmentmaster.app;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

final class MasterFragmentState
  implements Parcelable
{
  public static final Creator<MasterFragmentState> CREATOR = new Creator()
  {
    public MasterFragmentState createFromParcel(Parcel paramParcel)
    {
      return new MasterFragmentState(paramParcel);
    }

    public MasterFragmentState[] newArray(int paramInt)
    {
      return new MasterFragmentState[paramInt];
    }
  };
  boolean mIsSlideable;
  Request mRequest;
  int mSoftInputMode;

  public MasterFragmentState(Parcel paramParcel)
  {
    this.mRequest = ((Request)paramParcel.readParcelable(MasterFragmentState.class.getClassLoader()));
    this.mSoftInputMode = paramParcel.readInt();
    if (paramParcel.readInt() != 0);
    for (boolean bool = true; ; bool = false)
    {
      this.mIsSlideable = bool;
      return;
    }
  }

  public MasterFragmentState(MasterFragmentDelegate paramMasterFragmentDelegate)
  {
    this.mRequest = paramMasterFragmentDelegate.mRequest;
    this.mSoftInputMode = paramMasterFragmentDelegate.mSoftInputMode;
    this.mIsSlideable = paramMasterFragmentDelegate.mIsSlideable;
  }

  public int describeContents()
  {
    return 0;
  }

  public void restore(MasterFragmentDelegate paramMasterFragmentDelegate)
  {
    paramMasterFragmentDelegate.mRequest = this.mRequest;
    paramMasterFragmentDelegate.mSoftInputMode = this.mSoftInputMode;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeParcelable(this.mRequest, paramInt);
    paramParcel.writeInt(this.mSoftInputMode);
    if (this.mIsSlideable);
    for (int i = 1; ; i = 0)
    {
      paramParcel.writeInt(i);
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.MasterFragmentState
 * JD-Core Version:    0.6.0
 */