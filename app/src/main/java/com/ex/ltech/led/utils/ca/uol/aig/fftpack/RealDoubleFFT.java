package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

public class RealDoubleFFT extends RealDoubleFFT_Mixed
{
  private int ndim;
  public double norm_factor;
  private double[] wavetable;

  public RealDoubleFFT(int paramInt)
  {
    this.ndim = paramInt;
    this.norm_factor = paramInt;
    if ((this.wavetable == null) || (this.wavetable.length != 15 + 2 * this.ndim))
      this.wavetable = new double[15 + 2 * this.ndim];
    rffti(this.ndim, this.wavetable);
  }

  public void bt(Complex1D paramComplex1D, double[] paramArrayOfDouble)
  {
    if (this.ndim % 2 == 0)
    {
      if (paramComplex1D.x.length != 1 + this.ndim / 2)
        throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    }
    else if (paramComplex1D.x.length != (1 + this.ndim) / 2)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    paramArrayOfDouble[0] = paramComplex1D.x[0];
    for (int i = 1; i < (1 + this.ndim) / 2; i++)
    {
      paramArrayOfDouble[(-1 + i * 2)] = paramComplex1D.x[i];
      paramArrayOfDouble[(i * 2)] = paramComplex1D.y[i];
    }
    if (this.ndim % 2 == 0)
      paramArrayOfDouble[(-1 + this.ndim)] = paramComplex1D.x[(this.ndim / 2)];
    rfftb(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void bt(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    rfftb(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void ft(double[] paramArrayOfDouble)
  {
    if (paramArrayOfDouble.length != this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    rfftf(this.ndim, paramArrayOfDouble, this.wavetable);
  }

  public void ft(double[] paramArrayOfDouble, Complex1D paramComplex1D)
  {
    /*if (paramArrayOfDouble.length != this.ndim)
      throw new IllegalArgumentException("The length of data can not match that of the wavetable");
    rfftf(this.ndim, paramArrayOfDouble, this.wavetable);
    if (this.ndim % 2 == 0)
      paramComplex1D.x = new double[1 + this.ndim / 2];
    for (paramComplex1D.y = new double[1 + this.ndim / 2]; ; paramComplex1D.y = new double[(1 + this.ndim) / 2])
    {
      paramComplex1D.x[0] = paramArrayOfDouble[0];
      paramComplex1D.y[0] = 0.0D;
      for (int i = 1; i < (1 + this.ndim) / 2; i++)
      {
        paramComplex1D.x[i] = paramArrayOfDouble[(-1 + i * 2)];
        paramComplex1D.y[i] = paramArrayOfDouble[(i * 2)];
      }
      paramComplex1D.x = new double[(1 + this.ndim) / 2];
    }
    if (this.ndim % 2 == 0)
    {
      paramComplex1D.x[(this.ndim / 2)] = paramArrayOfDouble[(-1 + this.ndim)];
      paramComplex1D.y[(this.ndim / 2)] = 0.0D;
    }*/
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT
 * JD-Core Version:    0.6.0
 */