package com.ex.ltech.util;

public class IrUtil
{
  public static short[] getPulseArray(int paramInt, int[] paramArrayOfInt)
  {
    short[] arrayOfShort = new short[paramArrayOfInt.length];
    int i = 1000000 / paramInt;
    for (int j = 0; j < paramArrayOfInt.length; j++)
      arrayOfShort[j] = (short)(paramArrayOfInt[j] / i);
    return arrayOfShort;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.util.IrUtil
 * JD-Core Version:    0.6.0
 */