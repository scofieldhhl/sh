package com.ex.ltech.remote.control;

public class NGram
{
  private final int n;

  public NGram()
  {
    this.n = 2;
  }

  public NGram(int paramInt)
  {
    this.n = paramInt;
  }

  public double distance(String paramString1, String paramString2)
  {
    int i = paramString1.length();
    int j = paramString2.length();
    if ((i == 0) || (j == 0))
    {
      if (i == j)
        return 1.0D;
      return 0.0D;
    }
    int k = 0;
    if ((i < this.n) || (j < this.n))
    {
      int m = 0;
      int i1 = Math.min(i, j);
      while (m < i1)
      {
        if (paramString1.charAt(m) == paramString2.charAt(m))
          k++;
        m++;
      }
      return k / Math.max(i, j);
    }
    char[] arrayOfChar1 = new char[-1 + (i + this.n)];
    int i2 = 0;
    if (i2 < arrayOfChar1.length)
    {
      if (i2 < -1 + this.n)
        arrayOfChar1[i2] = '\n';
      while (true)
      {
        i2++;
        break;
        arrayOfChar1[i2] = paramString1.charAt(1 + (i2 - this.n));
      }
    }
    Object localObject1 = new float[i + 1];
    Object localObject2 = new float[i + 1];
    char[] arrayOfChar2 = new char[this.n];
    for (int i3 = 0; i3 <= i; i3++)
      localObject1[i3] = i3;
    for (int i4 = 1; i4 <= j; i4++)
    {
      if (i4 < this.n)
      {
        for (int i9 = 0; i9 < this.n - i4; i9++)
          arrayOfChar2[i9] = '\n';
        for (int i10 = this.n - i4; i10 < this.n; i10++)
          arrayOfChar2[i10] = paramString2.charAt(i10 - (this.n - i4));
      }
      arrayOfChar2 = paramString2.substring(i4 - this.n, i4).toCharArray();
      localObject2[0] = i4;
      for (int i5 = 1; i5 <= i; i5++)
      {
        int i6 = 0;
        int i7 = this.n;
        int i8 = 0;
        if (i8 < this.n)
        {
          if (arrayOfChar1[(i8 + (i5 - 1))] != arrayOfChar2[i8])
            i6++;
          while (true)
          {
            i8++;
            break;
            if (arrayOfChar1[(i8 + (i5 - 1))] != '\n')
              continue;
            i7--;
          }
        }
        float f = i6 / i7;
        localObject2[i5] = Math.min(Math.min(1.0F + localObject2[(i5 - 1)], 1.0F + localObject1[i5]), f + localObject1[(i5 - 1)]);
      }
      Object localObject3 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
    return 1.0D - localObject1[i] / Math.max(j, i);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.NGram
 * JD-Core Version:    0.6.0
 */