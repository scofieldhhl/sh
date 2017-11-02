package com.ex.ltech.hongwai.view.pickerview.adapter;

import java.util.ArrayList;

public class ArrayWheelAdapter<T>
  implements WheelAdapter
{
  public static final int DEFAULT_LENGTH = 4;
  private ArrayList<T> items;
  private int length;

  public ArrayWheelAdapter(ArrayList<T> paramArrayList)
  {
    this(paramArrayList, 4);
  }

  public ArrayWheelAdapter(ArrayList<T> paramArrayList, int paramInt)
  {
    this.items = paramArrayList;
    this.length = paramInt;
  }

  public Object getItem(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < this.items.size()))
      return this.items.get(paramInt);
    return "";
  }

  public int getItemsCount()
  {
    return this.items.size();
  }

  public int indexOf(Object paramObject)
  {
    return this.items.indexOf(paramObject);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.adapter.ArrayWheelAdapter
 * JD-Core Version:    0.6.0
 */