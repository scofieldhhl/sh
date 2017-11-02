package com.ex.ltech.hongwai.view.pickerview.adapter;

public class NumericWheelAdapter
  implements WheelAdapter
{
  public static final int DEFAULT_MAX_VALUE = 9;
  private static final int DEFAULT_MIN_VALUE;
  private int maxValue;
  private int minValue;

  public NumericWheelAdapter()
  {
    this(0, 9);
  }

  public NumericWheelAdapter(int paramInt1, int paramInt2)
  {
    this.minValue = paramInt1;
    this.maxValue = paramInt2;
  }

  public Object getItem(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < getItemsCount()))
      return Integer.valueOf(paramInt + this.minValue);
    return Integer.valueOf(0);
  }

  public int getItemsCount()
  {
    return 1 + (this.maxValue - this.minValue);
  }

  public int indexOf(Object paramObject)
  {
    return ((Integer)paramObject).intValue() - this.minValue;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.pickerview.adapter.NumericWheelAdapter
 * JD-Core Version:    0.6.0
 */