package com.ex.ltech.remote.control;

import java.lang.reflect.Array;

public class Levenshtein
{
  private int compare(String paramString1, String paramString2)
  {
    int i = paramString1.length();
    int j = paramString2.length();
    if (i == 0)
      return j;
    if (j == 0)
      return i;
    int[] arrayOfInt = { i + 1, j + 1 };
    int[][] arrayOfInt1 = (int[][])Array.newInstance(Integer.TYPE, arrayOfInt);
    for (int k = 0; k <= i; k++)
      arrayOfInt1[k][0] = k;
    for (int m = 0; m <= j; m++)
      arrayOfInt1[0][m] = m;
    for (int n = 1; n <= i; n++)
    {
      int i1 = paramString1.charAt(n - 1);
      int i2 = 1;
      if (i2 > j)
        continue;
      if (i1 == paramString2.charAt(i2 - 1));
      for (int i3 = 0; ; i3 = 1)
      {
        arrayOfInt1[n][i2] = min(1 + arrayOfInt1[(n - 1)][i2], 1 + arrayOfInt1[n][(i2 - 1)], i3 + arrayOfInt1[(n - 1)][(i2 - 1)]);
        i2++;
        break;
      }
    }
    return arrayOfInt1[i][j];
  }

  private int min(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 < paramInt2);
    while (paramInt1 < paramInt3)
    {
      return paramInt1;
      paramInt1 = paramInt2;
    }
    return paramInt3;
  }

  public float getSimilarityRatio(String paramString1, String paramString2)
  {
    return 1.0F - compare(paramString1, paramString2) / Math.max(paramString1.length(), paramString2.length());
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.Levenshtein
 * JD-Core Version:    0.6.0
 */