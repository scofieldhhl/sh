package com.ex.ltech.remote.control;

import java.util.Arrays;

public class Cosine
{
  protected static double dotProduct(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    int i = Math.max(paramArrayOfInt1.length, paramArrayOfInt2.length);
    int[] arrayOfInt1 = Arrays.copyOf(paramArrayOfInt1, i);
    int[] arrayOfInt2 = Arrays.copyOf(paramArrayOfInt2, i);
    double d = 0.0D;
    for (int j = 0; j < i; j++)
      d += arrayOfInt1[j] * arrayOfInt2[j];
    return d;
  }

  protected static double norm(int[] paramArrayOfInt)
  {
    double d = 0.0D;
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfInt[j];
      d += k * k;
    }
    return Math.sqrt(d);
  }

  public double distance(String paramString1, String paramString2)
  {
    return 1.0D - similarity(paramString1, paramString2);
  }

  public double similarity(String paramString1, String paramString2)
  {
    KShingling localKShingling = new KShingling(2);
    int[] arrayOfInt1 = localKShingling.getArrayProfile(paramString1);
    int[] arrayOfInt2 = localKShingling.getArrayProfile(paramString2);
    return dotProduct(arrayOfInt1, arrayOfInt2) / (norm(arrayOfInt1) * norm(arrayOfInt2));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.Cosine
 * JD-Core Version:    0.6.0
 */