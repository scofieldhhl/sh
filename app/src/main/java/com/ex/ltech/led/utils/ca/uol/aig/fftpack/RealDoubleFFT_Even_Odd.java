package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class RealDoubleFFT_Even_Odd extends RealDoubleFFT_Mixed
{
  protected int ndim;
  public double norm_factor;
  protected double[] wavetable;

  public RealDoubleFFT_Even_Odd(int paramInt)
  {
    this.ndim = paramInt;
    this.norm_factor = (paramInt * 4);
    if ((this.wavetable == null) || (this.wavetable.length != 15 + 3 * this.ndim))
      this.wavetable = new double[15 + 3 * this.ndim];
    cosqi(this.ndim, this.wavetable);
  }

  public void bt(double[] paramArrayOfDouble)
  {
    cosqb(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  void cosqb(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt < 2)
    {
      paramArrayOfDouble1[0] = (4.0D * paramArrayOfDouble1[0]);
      return;
    }
    if (paramInt == 2)
    {
      double d = 4.0D * (paramArrayOfDouble1[0] + paramArrayOfDouble1[1]);
      paramArrayOfDouble1[1] = (2.82842712474619D * (paramArrayOfDouble1[0] - paramArrayOfDouble1[1]));
      paramArrayOfDouble1[0] = d;
      return;
    }
    cosqb1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2);
  }

  void cosqb1(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    int i = (paramInt + 1) / 2;
    for (int j = 2; j < paramInt; j += 2)
    {
      double d = paramArrayOfDouble1[(j - 1)] + paramArrayOfDouble1[j];
      paramArrayOfDouble1[j] -= paramArrayOfDouble1[(j - 1)];
      paramArrayOfDouble1[(j - 1)] = d;
    }
    paramArrayOfDouble1[0] += paramArrayOfDouble1[0];
    int k = paramInt % 2;
    if (k == 0)
    {
      int i3 = paramInt - 1;
      paramArrayOfDouble1[i3] += paramArrayOfDouble1[(paramInt - 1)];
    }
    rfftb1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, paramInt);
    for (int m = 1; m < i; m++)
    {
      int i2 = paramInt - m;
      paramArrayOfDouble2[(m + paramInt)] = (paramArrayOfDouble2[(m - 1)] * paramArrayOfDouble1[i2] + paramArrayOfDouble2[(i2 - 1)] * paramArrayOfDouble1[m]);
      paramArrayOfDouble2[(i2 + paramInt)] = (paramArrayOfDouble2[(m - 1)] * paramArrayOfDouble1[m] - paramArrayOfDouble2[(i2 - 1)] * paramArrayOfDouble1[i2]);
    }
    if (k == 0)
      paramArrayOfDouble1[i] = (paramArrayOfDouble2[(i - 1)] * (paramArrayOfDouble1[i] + paramArrayOfDouble1[i]));
    for (int n = 1; n < i; n++)
    {
      int i1 = paramInt - n;
      paramArrayOfDouble1[n] = (paramArrayOfDouble2[(n + paramInt)] + paramArrayOfDouble2[(i1 + paramInt)]);
      paramArrayOfDouble1[i1] = (paramArrayOfDouble2[(n + paramInt)] - paramArrayOfDouble2[(i1 + paramInt)]);
    }
    paramArrayOfDouble1[0] += paramArrayOfDouble1[0];
  }

  void cosqf(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt < 2)
      return;
    if (paramInt == 2)
    {
      double d = 1.4142135623731D * paramArrayOfDouble1[1];
      paramArrayOfDouble1[1] = (paramArrayOfDouble1[0] - d);
      paramArrayOfDouble1[0] = (d + paramArrayOfDouble1[0]);
      return;
    }
    cosqf1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2);
  }

  void cosqf1(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    /*int i = (paramInt + 1) / 2;
    (paramInt + 2);
    for (int j = 1; j < i; j++)
    {
      int i2 = paramInt - j;
      paramArrayOfDouble2[(j + paramInt)] = (paramArrayOfDouble1[j] + paramArrayOfDouble1[i2]);
      paramArrayOfDouble2[(i2 + paramInt)] = (paramArrayOfDouble1[j] - paramArrayOfDouble1[i2]);
    }
    int k = paramInt % 2;
    if (k == 0)
      paramArrayOfDouble2[(i + paramInt)] = (paramArrayOfDouble1[i] + paramArrayOfDouble1[i]);
    for (int m = 1; m < i; m++)
    {
      int i1 = paramInt - m;
      paramArrayOfDouble1[m] = (paramArrayOfDouble2[(m - 1)] * paramArrayOfDouble2[(i1 + paramInt)] + paramArrayOfDouble2[(i1 - 1)] * paramArrayOfDouble2[(m + paramInt)]);
      paramArrayOfDouble1[i1] = (paramArrayOfDouble2[(m - 1)] * paramArrayOfDouble2[(m + paramInt)] - paramArrayOfDouble2[(i1 - 1)] * paramArrayOfDouble2[(i1 + paramInt)]);
    }
    if (k == 0)
      paramArrayOfDouble1[i] = (paramArrayOfDouble2[(i - 1)] * paramArrayOfDouble2[(i + paramInt)]);
    rfftf1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, paramInt);
    for (int n = 2; n < paramInt; n += 2)
    {
      double d = paramArrayOfDouble1[(n - 1)] - paramArrayOfDouble1[n];
      paramArrayOfDouble1[n] = (paramArrayOfDouble1[(n - 1)] + paramArrayOfDouble1[n]);
      paramArrayOfDouble1[(n - 1)] = d;
    }*/
  }

  void cosqi(int paramInt, double[] paramArrayOfDouble)
  {
    double d = 1.570796326794897D / paramInt;
    for (int i = 0; i < paramInt; i++)
      paramArrayOfDouble[i] = Math.cos(d * (i + 1));
    rffti1(paramInt, paramArrayOfDouble, paramInt);
  }

  public void ft(double[] paramArrayOfDouble)
  {
    cosqf(this.ndim, paramArrayOfDouble, this.wavetable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT_Even_Odd
 * JD-Core Version:    0.6.0
 */