package com.ex.ltech.led.my_view.gallery_view;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.graphics.Bitmap;

public abstract class CoverFlowAdapter
{
  private final DataSetObservable mDataSetObservable = new DataSetObservable();

  public abstract int getCount();

  public abstract Bitmap getImage(int paramInt);

  public int getItemViewType(int paramInt)
  {
    return 0;
  }

  public int getViewTypeCount()
  {
    return 1;
  }

  public void notifyDataSetChanged()
  {
    this.mDataSetObservable.notifyChanged();
  }

  public void notifyDataSetInvalidated()
  {
    this.mDataSetObservable.notifyInvalidated();
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mDataSetObservable.registerObserver(paramDataSetObserver);
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mDataSetObservable.unregisterObserver(paramDataSetObserver);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.gallery_view.CoverFlowAdapter
 * JD-Core Version:    0.6.0
 */