package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class RealDoubleFFT_Odd_Odd extends RealDoubleFFT_Even_Odd
{
  public RealDoubleFFT_Odd_Odd(int paramInt)
  {
    super(paramInt);
  }

  public void bt(double[] paramArrayOfDouble)
  {
    sinqb(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void ft(double[] paramArrayOfDouble)
  {
    sinqf(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  void sinqb(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt <= 1)
      paramArrayOfDouble1[0] = (4.0D * paramArrayOfDouble1[0]);
    while (true)
    {
      return;
      int i = paramInt / 2;
      for (int j = 1; j < paramInt; j += 2)
        paramArrayOfDouble1[j] = (-paramArrayOfDouble1[j]);
      cosqb(paramInt, paramArrayOfDouble1, paramArrayOfDouble2);
      for (int k = 0; k < i; k++)
      {
        int m = -1 + (paramInt - k);
        double d = paramArrayOfDouble1[k];
        paramArrayOfDouble1[k] = paramArrayOfDouble1[m];
        paramArrayOfDouble1[m] = d;
      }
    }
  }

  void sinqf(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt == 1);
    while (true)
    {
      return;
      int i = paramInt / 2;
      for (int j = 0; j < i; j++)
      {
        int m = -1 + (paramInt - j);
        double d = paramArrayOfDouble1[j];
        paramArrayOfDouble1[j] = paramArrayOfDouble1[m];
        paramArrayOfDouble1[m] = d;
      }
      cosqf(paramInt, paramArrayOfDouble1, paramArrayOfDouble2);
      for (int k = 1; k < paramInt; k += 2)
        paramArrayOfDouble1[k] = (-paramArrayOfDouble1[k]);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT_Odd_Odd
 * JD-Core Version:    0.6.0
 */