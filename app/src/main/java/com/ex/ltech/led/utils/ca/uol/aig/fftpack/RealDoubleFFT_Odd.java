package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class RealDoubleFFT_Odd extends RealDoubleFFT_Mixed
{
  private int ndim;
  public double norm_factor;
  private double[] wavetable;

  public RealDoubleFFT_Odd(int paramInt)
  {
    this.ndim = paramInt;
    this.norm_factor = (2 * (paramInt + 1));
    int i = 15 + (3 + (2 * this.ndim + this.ndim / 2));
    if ((this.wavetable == null) || (this.wavetable.length != i))
      this.wavetable = new double[i];
    sinti(this.ndim, this.wavetable);
  }

  public void bt(double[] paramArrayOfDouble)
  {
    sint(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void ft(double[] paramArrayOfDouble)
  {
    sint(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  void sint(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    sint1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2);
  }

  void sint1(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    double[] arrayOfDouble1 = new double[15 + 2 * (paramInt + 1)];
    int i = paramInt / 2;
    int j = 1 + (i + paramInt);
    int k = 1 + (j + paramInt);
    double[] arrayOfDouble2 = new double[paramInt + 1];
    for (int m = 0; m < paramInt; m++)
    {
      paramArrayOfDouble2[(m + i)] = paramArrayOfDouble1[m];
      paramArrayOfDouble1[m] = paramArrayOfDouble2[(m + j)];
    }
    if (paramInt < 2)
    {
      int i7 = i + 0;
      paramArrayOfDouble2[i7] += paramArrayOfDouble2[(i + 0)];
    }
    while (true)
    {
      for (int i5 = 0; i5 < paramInt; i5++)
      {
        paramArrayOfDouble2[(i5 + j)] = paramArrayOfDouble1[i5];
        paramArrayOfDouble1[i5] = paramArrayOfDouble2[(i5 + i)];
      }
      if (paramInt == 2)
      {
        double d3 = 1.73205080756888D * (paramArrayOfDouble2[(i + 0)] + paramArrayOfDouble2[(i + 1)]);
        paramArrayOfDouble2[(i + 1)] = (1.73205080756888D * (paramArrayOfDouble2[(i + 0)] - paramArrayOfDouble2[(i + 1)]));
        paramArrayOfDouble2[(i + 0)] = d3;
        continue;
      }
      int n = paramInt + 1;
      int i1 = paramInt / 2;
      paramArrayOfDouble2[(j + 0)] = 0.0D;
      for (int i2 = 0; i2 < i1; i2++)
      {
        int i6 = -1 + (paramInt - i2);
        double d1 = paramArrayOfDouble2[(i2 + i)] - paramArrayOfDouble2[(i6 + i)];
        double d2 = paramArrayOfDouble2[i2] * (paramArrayOfDouble2[(i2 + i)] + paramArrayOfDouble2[(i6 + i)]);
        paramArrayOfDouble2[(j + (i2 + 1))] = (d1 + d2);
        paramArrayOfDouble2[(j + (i6 + 1))] = (d2 - d1);
      }
      int i3 = paramInt % 2;
      if (i3 != 0)
        paramArrayOfDouble2[(j + (i1 + 1))] = (4.0D * paramArrayOfDouble2[(i1 + i)]);
      System.arraycopy(paramArrayOfDouble2, i, arrayOfDouble1, 0, paramInt + 1);
      System.arraycopy(paramArrayOfDouble1, 0, arrayOfDouble1, paramInt + 1, paramInt);
      System.arraycopy(paramArrayOfDouble2, k, arrayOfDouble1, 2 * (paramInt + 1), 15);
      System.arraycopy(paramArrayOfDouble2, j, arrayOfDouble2, 0, paramInt + 1);
      rfftf1(n, arrayOfDouble2, arrayOfDouble1, 0);
      System.arraycopy(arrayOfDouble2, 0, paramArrayOfDouble2, j, paramInt + 1);
      paramArrayOfDouble2[(i + 0)] = (0.5D * paramArrayOfDouble2[(j + 0)]);
      for (int i4 = 2; i4 < paramInt; i4 += 2)
      {
        paramArrayOfDouble2[(i + (i4 - 1))] = (-paramArrayOfDouble2[(i4 + j)]);
        paramArrayOfDouble2[(i4 + i)] = (paramArrayOfDouble2[(i + (i4 - 2))] + paramArrayOfDouble2[(j + (i4 - 1))]);
      }
      if (i3 != 0)
        continue;
      paramArrayOfDouble2[(i + (paramInt - 1))] = (-paramArrayOfDouble2[(paramInt + j)]);
    }
  }

  void sinti(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt <= 1)
      return;
    int i = paramInt / 2;
    double d = 3.141592653589793D / (paramInt + 1);
    for (int j = 0; j < i; j++)
      paramArrayOfDouble[j] = (2.0D * Math.sin(d * (j + 1)));
    rffti1(paramInt + 1, paramArrayOfDouble, i);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT_Odd
 * JD-Core Version:    0.6.0
 */