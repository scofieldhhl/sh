package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class RealDoubleFFT_Even extends RealDoubleFFT_Mixed
{
  private int ndim;
  public double norm_factor;
  private double[] wavetable;

  public RealDoubleFFT_Even(int paramInt)
  {
    this.ndim = paramInt;
    this.norm_factor = (2 * (paramInt - 1));
    if ((this.wavetable == null) || (this.wavetable.length != 15 + 3 * this.ndim))
      this.wavetable = new double[15 + 3 * this.ndim];
    costi(this.ndim, this.wavetable);
  }

  public void bt(double[] paramArrayOfDouble)
  {
    cost(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  void cost(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    int i = paramInt - 1;
    int j = paramInt / 2;
    if (paramInt - 2 < 0);
    int m;
    double d2;
    do
    {
      return;
      if (paramInt == 2)
      {
        double d9 = paramArrayOfDouble1[0] + paramArrayOfDouble1[1];
        paramArrayOfDouble1[1] = (paramArrayOfDouble1[0] - paramArrayOfDouble1[1]);
        paramArrayOfDouble1[0] = d9;
        return;
      }
      if (paramInt == 3)
      {
        double d7 = paramArrayOfDouble1[0] + paramArrayOfDouble1[2];
        double d8 = paramArrayOfDouble1[1] + paramArrayOfDouble1[1];
        paramArrayOfDouble1[1] = (paramArrayOfDouble1[0] - paramArrayOfDouble1[2]);
        paramArrayOfDouble1[0] = (d7 + d8);
        paramArrayOfDouble1[2] = (d7 - d8);
        return;
      }
      double d1 = paramArrayOfDouble1[0] - paramArrayOfDouble1[(paramInt - 1)];
      paramArrayOfDouble1[0] += paramArrayOfDouble1[(paramInt - 1)];
      for (int k = 1; k < j; k++)
      {
        int i1 = i - k;
        double d4 = paramArrayOfDouble1[k] + paramArrayOfDouble1[i1];
        double d5 = paramArrayOfDouble1[k] - paramArrayOfDouble1[i1];
        d1 += d5 * paramArrayOfDouble2[i1];
        double d6 = d5 * paramArrayOfDouble2[k];
        paramArrayOfDouble1[k] = (d4 - d6);
        paramArrayOfDouble1[i1] = (d4 + d6);
      }
      m = paramInt % 2;
      if (m != 0)
        paramArrayOfDouble1[j] += paramArrayOfDouble1[j];
      rfftf1(i, paramArrayOfDouble1, paramArrayOfDouble2, paramInt);
      d2 = paramArrayOfDouble1[1];
      paramArrayOfDouble1[1] = d1;
      for (int n = 3; n < paramInt; n += 2)
      {
        double d3 = paramArrayOfDouble1[n];
        paramArrayOfDouble1[n] = (paramArrayOfDouble1[(n - 2)] - paramArrayOfDouble1[(n - 1)]);
        paramArrayOfDouble1[(n - 1)] = d2;
        d2 = d3;
      }
    }
    while (m == 0);
    paramArrayOfDouble1[(paramInt - 1)] = d2;
  }

  void costi(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt <= 3)
      return;
    int i = paramInt / 2;
    double d = 3.141592653589793D / (paramInt - 1);
    for (int j = 1; j < i; j++)
    {
      int k = -1 + (paramInt - j);
      paramArrayOfDouble[j] = (2.0D * Math.sin(d * j));
      paramArrayOfDouble[k] = (2.0D * Math.cos(d * j));
    }
    rffti1(paramInt - 1, paramArrayOfDouble, paramInt);
  }

  public void ft(double[] paramArrayOfDouble)
  {
    cost(this.ndim, paramArrayOfDouble, this.wavetable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT_Even
 * JD-Core Version:    0.6.0
 */