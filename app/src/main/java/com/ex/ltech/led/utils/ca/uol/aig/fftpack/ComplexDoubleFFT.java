package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class ComplexDoubleFFT extends ComplexDoubleFFT_Mixed
{
  private int ndim;
  public double norm_factor;
  private double[] wavetable;

  public ComplexDoubleFFT(int paramInt)
  {
    this.ndim = paramInt;
    this.norm_factor = paramInt;
    if ((this.wavetable == null) || (this.wavetable.length != 15 + 4 * this.ndim))
      this.wavetable = new double[15 + 4 * this.ndim];
    cffti(this.ndim, this.wavetable);
  }

  public void bt(Complex1D paramComplex1D)
  {
    if (paramComplex1D.x.length != this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    double[] arrayOfDouble = new double[2 * this.ndim];
    for (int i = 0; i < this.ndim; i++)
    {
      arrayOfDouble[(i * 2)] = paramComplex1D.x[i];
      arrayOfDouble[(1 + i * 2)] = paramComplex1D.y[i];
    }
    cfftb(this.ndim, arrayOfDouble, this.wavetable);
    for (int j = 0; j < this.ndim; j++)
    {
      paramComplex1D.x[j] = arrayOfDouble[(j * 2)];
      paramComplex1D.y[j] = arrayOfDouble[(1 + j * 2)];
    }
  }

  public void bt(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 2 * this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    cfftb(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void ft(Complex1D paramComplex1D)
  {
    if (paramComplex1D.x.length != this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    double[] arrayOfDouble = new double[2 * this.ndim];
    for (int i = 0; i < this.ndim; i++)
    {
      arrayOfDouble[(i * 2)] = paramComplex1D.x[i];
      arrayOfDouble[(1 + i * 2)] = paramComplex1D.y[i];
    }
    cfftf(this.ndim, arrayOfDouble, this.wavetable);
    for (int j = 0; j < this.ndim; j++)
    {
      paramComplex1D.x[j] = arrayOfDouble[(j * 2)];
      paramComplex1D.y[j] = arrayOfDouble[(1 + j * 2)];
    }
  }

  public void ft(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != 2 * this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    cfftf(this.ndim, paramArrayOfDouble, this.wavetable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.ComplexDoubleFFT
 * JD-Core Version:    0.6.0
 */