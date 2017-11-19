package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

class ComplexDoubleFFT_Mixed
{
  void cfftb(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    cfftf1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, 1);
  }

  void cfftf(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    cfftf1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, -1);
  }

  void cfftf1(int paramInt1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt2)
  {
    /*int[] arrayOfInt = new int[1];
    double[] arrayOfDouble = new double[paramInt1 * 2];
    int i = paramInt1 * 2;
    int j = paramInt1 * 4;
    System.arraycopy(paramArrayOfDouble2, 0, arrayOfDouble, 0, paramInt1 * 2);
    arrayOfInt[0] = 0;
    int k = (int)paramArrayOfDouble2[(j + 1)];
    int m = 0;
    int n = 1;
    int i1 = i;
    int i2 = 2;
    int i3 = k + 1;
    if (i2 <= i3)
    {
      int i6 = (int)paramArrayOfDouble2[(i2 + j)];
      int i7 = i6 * n;
      int i8 = paramInt1 / i7;
      int i9 = i8 + i8;
      int i10 = i9 * n;
      if (i6 == 4)
        if (m == 0)
        {
          passf4(i9, n, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, i1, paramInt2);
          label137: m = 1 - m;
        }
      label424: 
      while (true)
      {
        n = i7;
        i1 += i9 * (i6 - 1);
        i2++;
        break;
        passf4(i9, n, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, i1, paramInt2);
        break label137;
        if (i6 == 2)
        {
          if (m == 0)
            passf2(i9, n, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, i1, paramInt2);
          while (true)
          {
            m = 1 - m;
            break;
            passf2(i9, n, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, i1, paramInt2);
          }
        }
        if (i6 == 3)
        {
          if (m == 0)
            passf3(i9, n, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, i1, paramInt2);
          while (true)
          {
            m = 1 - m;
            break;
            passf3(i9, n, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, i1, paramInt2);
          }
        }
        if (i6 == 5)
        {
          if (m == 0)
            passf5(i9, n, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, i1, paramInt2);
          while (true)
          {
            m = 1 - m;
            break;
            passf5(i9, n, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, i1, paramInt2);
          }
        }
        if (m == 0)
          passfg(arrayOfInt, i9, i6, n, i10, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble1, arrayOfDouble, arrayOfDouble, paramArrayOfDouble2, i1, paramInt2);
        while (true)
        {
          if (arrayOfInt[0] == 0)
            break label424;
          m = 1 - m;
          break;
          passfg(arrayOfInt, i9, i6, n, i10, arrayOfDouble, arrayOfDouble, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble2, i1, paramInt2);
        }
      }
    }
    if (m == 0)
      return;
    for (int i4 = 0; ; i4++)
    {
      int i5 = paramInt1 * 2;
      if (i4 >= i5)
        break;
      paramArrayOfDouble1[i4] = arrayOfDouble[i4];
    }*/
  }

  void cffti(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt == 1)
      return;
    cffti1(paramInt, paramArrayOfDouble);
  }

  void cffti1(int paramInt, double[] paramArrayOfDouble)
  {
    int[] arrayOfInt = { 3, 4, 2, 5 };
    int i = 0;
    int j = paramInt;
    int k = 0;
    int m = 0;
    m++;
    if (m <= 4)
      i = arrayOfInt[(m - 1)];
    label158: 
    do
    {
      while (true)
      {
        int n = j / i;
        if (j - i * n != 0)
          break;
        k++;
        paramArrayOfDouble[(k + 1 + paramInt * 4)] = i;
        j = n;
        if ((i != 2) || (k == 1))
          break label158;
        for (int i13 = 2; i13 <= k; i13++)
        {
          int i14 = 2 + (k - i13);
          paramArrayOfDouble[(i14 + 1 + paramInt * 4)] = paramArrayOfDouble[(i14 + paramInt * 4)];
        }
        i += 2;
      }
      paramArrayOfDouble[(2 + paramInt * 4)] = 2.0D;
    }
    while (j != 1);
    paramArrayOfDouble[(0 + paramInt * 4)] = paramInt;
    paramArrayOfDouble[(1 + paramInt * 4)] = k;
    double d1 = 6.283185307179586D / paramInt;
    int i1 = 1;
    int i2 = 1;
    for (int i3 = 1; i3 <= k; i3++)
    {
      int i4 = (int)paramArrayOfDouble[(i3 + 1 + paramInt * 4)];
      int i5 = 0;
      int i6 = i2 * i4;
      int i7 = paramInt / i6;
      int i8 = 2 + (i7 + i7);
      int i9 = i4 - 1;
      for (int i10 = 1; i10 <= i9; i10++)
      {
        int i11 = i1;
        paramArrayOfDouble[(i1 - 1 + paramInt * 2)] = 1.0D;
        paramArrayOfDouble[(i1 + paramInt * 2)] = 0.0D;
        i5 += i2;
        double d2 = 0.0D;
        double d3 = d1 * i5;
        for (int i12 = 4; i12 <= i8; i12 += 2)
        {
          i1 += 2;
          d2 += 1.0D;
          double d4 = d2 * d3;
          paramArrayOfDouble[(i1 - 1 + paramInt * 2)] = Math.cos(d4);
          paramArrayOfDouble[(i1 + paramInt * 2)] = Math.sin(d4);
        }
        if (i4 <= 5)
          continue;
        paramArrayOfDouble[(i11 - 1 + paramInt * 2)] = paramArrayOfDouble[(i1 - 1 + paramInt * 2)];
        paramArrayOfDouble[(i11 + paramInt * 2)] = paramArrayOfDouble[(i1 + paramInt * 2)];
      }
      i2 = i6;
    }
  }

  void passf2(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3, int paramInt4)
  {
    if (paramInt1 <= 2)
      for (int n = 0; n < paramInt2; n++)
      {
        int i1 = n * paramInt1;
        int i2 = paramInt1 * (n * 2);
        paramArrayOfDouble1[i2] += paramArrayOfDouble1[(i2 + paramInt1)];
        paramArrayOfDouble2[(i1 + paramInt1 * paramInt2)] = (paramArrayOfDouble1[i2] - paramArrayOfDouble1[(i2 + paramInt1)]);
        paramArrayOfDouble1[(i2 + 1)] += paramArrayOfDouble1[(1 + (i2 + paramInt1))];
        paramArrayOfDouble2[(1 + (i1 + paramInt1 * paramInt2))] = (paramArrayOfDouble1[(i2 + 1)] - paramArrayOfDouble1[(1 + (i2 + paramInt1))]);
      }
    for (int i = 0; i < paramInt2; i++)
      for (int j = 0; j < paramInt1 - 1; j += 2)
      {
        int k = j + i * paramInt1;
        int m = j + paramInt1 * (i * 2);
        paramArrayOfDouble1[m] += paramArrayOfDouble1[(m + paramInt1)];
        double d1 = paramArrayOfDouble1[m] - paramArrayOfDouble1[(m + paramInt1)];
        paramArrayOfDouble1[(m + 1)] += paramArrayOfDouble1[(paramInt1 + (m + 1))];
        double d2 = paramArrayOfDouble1[(m + 1)] - paramArrayOfDouble1[(paramInt1 + (m + 1))];
        paramArrayOfDouble2[(1 + (k + paramInt2 * paramInt1))] = (d2 * paramArrayOfDouble3[(j + paramInt3)] + d1 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (j + 1))]));
        paramArrayOfDouble2[(k + paramInt2 * paramInt1)] = (d1 * paramArrayOfDouble3[(j + paramInt3)] - d2 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (j + 1))]));
      }
  }

  void passf3(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3, int paramInt4)
  {
    int i = paramInt3 + paramInt1;
    if (paramInt1 == 2)
      for (int i2 = 1; i2 <= paramInt2; i2++)
      {
        int i3 = paramInt1 * (-2 + i2 * 3);
        double d11 = paramArrayOfDouble1[i3] + paramArrayOfDouble1[(i3 + paramInt1)];
        double d12 = paramArrayOfDouble1[(i3 - paramInt1)] + -0.5D * d11;
        int i4 = paramInt1 * (i2 - 1);
        paramArrayOfDouble2[i4] = (d11 + paramArrayOfDouble1[(i3 - paramInt1)]);
        double d13 = paramArrayOfDouble1[(i3 + 1)] + paramArrayOfDouble1[(1 + (i3 + paramInt1))];
        double d14 = paramArrayOfDouble1[(1 + (i3 - paramInt1))] + -0.5D * d13;
        paramArrayOfDouble2[(i4 + 1)] = (d13 + paramArrayOfDouble1[(1 + (i3 - paramInt1))]);
        double d15 = 0.866025403784439D * paramInt4 * (paramArrayOfDouble1[i3] - paramArrayOfDouble1[(i3 + paramInt1)]);
        double d16 = 0.866025403784439D * paramInt4 * (paramArrayOfDouble1[(i3 + 1)] - paramArrayOfDouble1[(1 + (i3 + paramInt1))]);
        paramArrayOfDouble2[(i4 + paramInt2 * paramInt1)] = (d12 - d16);
        paramArrayOfDouble2[(i4 + paramInt1 * (paramInt2 * 2))] = (d12 + d16);
        paramArrayOfDouble2[(1 + (i4 + paramInt2 * paramInt1))] = (d14 + d15);
        paramArrayOfDouble2[(1 + (i4 + paramInt1 * (paramInt2 * 2)))] = (d14 - d15);
      }
    for (int j = 1; j <= paramInt2; j++)
      for (int k = 0; ; k += 2)
      {
        int m = paramInt1 - 1;
        if (k >= m)
          break;
        int n = k + paramInt1 * (-2 + j * 3);
        double d1 = paramArrayOfDouble1[n] + paramArrayOfDouble1[(n + paramInt1)];
        double d2 = paramArrayOfDouble1[(n - paramInt1)] + -0.5D * d1;
        int i1 = k + paramInt1 * (j - 1);
        paramArrayOfDouble2[i1] = (d1 + paramArrayOfDouble1[(n - paramInt1)]);
        double d3 = paramArrayOfDouble1[(n + 1)] + paramArrayOfDouble1[(1 + (n + paramInt1))];
        double d4 = paramArrayOfDouble1[(1 + (n - paramInt1))] + -0.5D * d3;
        paramArrayOfDouble2[(i1 + 1)] = (d3 + paramArrayOfDouble1[(1 + (n - paramInt1))]);
        double d5 = 0.866025403784439D * paramInt4 * (paramArrayOfDouble1[n] - paramArrayOfDouble1[(n + paramInt1)]);
        double d6 = 0.866025403784439D * paramInt4 * (paramArrayOfDouble1[(n + 1)] - paramArrayOfDouble1[(1 + (n + paramInt1))]);
        double d7 = d2 - d6;
        double d8 = d2 + d6;
        double d9 = d4 + d5;
        double d10 = d4 - d5;
        paramArrayOfDouble2[(1 + (i1 + paramInt2 * paramInt1))] = (d9 * paramArrayOfDouble3[(k + paramInt3)] + d7 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (k + 1))]));
        paramArrayOfDouble2[(i1 + paramInt2 * paramInt1)] = (d7 * paramArrayOfDouble3[(k + paramInt3)] - d9 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (k + 1))]));
        paramArrayOfDouble2[(1 + (i1 + paramInt1 * (paramInt2 * 2)))] = (d10 * paramArrayOfDouble3[(k + i)] + d8 * (paramInt4 * paramArrayOfDouble3[(i + (k + 1))]));
        paramArrayOfDouble2[(i1 + paramInt1 * (paramInt2 * 2))] = (d8 * paramArrayOfDouble3[(k + i)] - d10 * (paramInt4 * paramArrayOfDouble3[(i + (k + 1))]));
      }
  }

  void passf4(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3, int paramInt4)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    if (paramInt1 == 2)
      for (int i3 = 0; i3 < paramInt2; i3++)
      {
        int i4 = 1 + paramInt1 * (i3 * 4);
        double d15 = paramArrayOfDouble1[i4] - paramArrayOfDouble1[(i4 + paramInt1 * 2)];
        double d16 = paramArrayOfDouble1[i4] + paramArrayOfDouble1[(i4 + paramInt1 * 2)];
        double d17 = paramArrayOfDouble1[(i4 + paramInt1 * 3)] - paramArrayOfDouble1[(i4 + paramInt1)];
        double d18 = paramArrayOfDouble1[(i4 + paramInt1)] + paramArrayOfDouble1[(i4 + paramInt1 * 3)];
        double d19 = paramArrayOfDouble1[(i4 - 1)] - paramArrayOfDouble1[(-1 + (i4 + paramInt1 * 2))];
        double d20 = paramArrayOfDouble1[(i4 - 1)] + paramArrayOfDouble1[(-1 + (i4 + paramInt1 * 2))];
        double d21 = paramArrayOfDouble1[(-1 + (i4 + paramInt1))] - paramArrayOfDouble1[(-1 + (i4 + paramInt1 * 3))];
        double d22 = paramArrayOfDouble1[(-1 + (i4 + paramInt1))] + paramArrayOfDouble1[(-1 + (i4 + paramInt1 * 3))];
        int i5 = i3 * paramInt1;
        paramArrayOfDouble2[i5] = (d20 + d22);
        paramArrayOfDouble2[(i5 + paramInt1 * (paramInt2 * 2))] = (d20 - d22);
        paramArrayOfDouble2[(i5 + 1)] = (d16 + d18);
        paramArrayOfDouble2[(1 + (i5 + paramInt1 * (paramInt2 * 2)))] = (d16 - d18);
        paramArrayOfDouble2[(i5 + paramInt2 * paramInt1)] = (d19 + d17 * paramInt4);
        paramArrayOfDouble2[(i5 + paramInt1 * (paramInt2 * 3))] = (d19 - d17 * paramInt4);
        paramArrayOfDouble2[(1 + (i5 + paramInt2 * paramInt1))] = (d15 + d21 * paramInt4);
        paramArrayOfDouble2[(1 + (i5 + paramInt1 * (paramInt2 * 3)))] = (d15 - d21 * paramInt4);
      }
    for (int k = 0; k < paramInt2; k++)
      for (int m = 0; ; m += 2)
      {
        int n = paramInt1 - 1;
        if (m >= n)
          break;
        int i1 = m + 1 + paramInt1 * (k * 4);
        double d1 = paramArrayOfDouble1[i1] - paramArrayOfDouble1[(i1 + paramInt1 * 2)];
        double d2 = paramArrayOfDouble1[i1] + paramArrayOfDouble1[(i1 + paramInt1 * 2)];
        double d3 = paramArrayOfDouble1[(i1 + paramInt1)] + paramArrayOfDouble1[(i1 + paramInt1 * 3)];
        double d4 = paramArrayOfDouble1[(i1 + paramInt1 * 3)] - paramArrayOfDouble1[(i1 + paramInt1)];
        double d5 = paramArrayOfDouble1[(i1 - 1)] - paramArrayOfDouble1[(-1 + (i1 + paramInt1 * 2))];
        double d6 = paramArrayOfDouble1[(i1 - 1)] + paramArrayOfDouble1[(-1 + (i1 + paramInt1 * 2))];
        double d7 = paramArrayOfDouble1[(-1 + (i1 + paramInt1))] - paramArrayOfDouble1[(-1 + (i1 + paramInt1 * 3))];
        double d8 = paramArrayOfDouble1[(-1 + (i1 + paramInt1))] + paramArrayOfDouble1[(-1 + (i1 + paramInt1 * 3))];
        int i2 = m + k * paramInt1;
        paramArrayOfDouble2[i2] = (d6 + d8);
        double d9 = d6 - d8;
        paramArrayOfDouble2[(i2 + 1)] = (d2 + d3);
        double d10 = d2 - d3;
        double d11 = d5 + d4 * paramInt4;
        double d12 = d5 - d4 * paramInt4;
        double d13 = d1 + d7 * paramInt4;
        double d14 = d1 - d7 * paramInt4;
        paramArrayOfDouble2[(i2 + paramInt2 * paramInt1)] = (d11 * paramArrayOfDouble3[(m + paramInt3)] - d13 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (m + 1))]));
        paramArrayOfDouble2[(1 + (i2 + paramInt2 * paramInt1))] = (d13 * paramArrayOfDouble3[(m + paramInt3)] + d11 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (m + 1))]));
        paramArrayOfDouble2[(i2 + paramInt1 * (paramInt2 * 2))] = (d9 * paramArrayOfDouble3[(m + i)] - d10 * (paramInt4 * paramArrayOfDouble3[(i + (m + 1))]));
        paramArrayOfDouble2[(1 + (i2 + paramInt1 * (paramInt2 * 2)))] = (d10 * paramArrayOfDouble3[(m + i)] + d9 * (paramInt4 * paramArrayOfDouble3[(i + (m + 1))]));
        paramArrayOfDouble2[(i2 + paramInt1 * (paramInt2 * 3))] = (d12 * paramArrayOfDouble3[(m + j)] - d14 * (paramInt4 * paramArrayOfDouble3[(j + (m + 1))]));
        paramArrayOfDouble2[(1 + (i2 + paramInt1 * (paramInt2 * 3)))] = (d14 * paramArrayOfDouble3[(m + j)] + d12 * (paramInt4 * paramArrayOfDouble3[(j + (m + 1))]));
      }
  }

  void passf5(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3, int paramInt4)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    int k = j + paramInt1;
    if (paramInt1 == 2)
      for (int i4 = 1; i4 <= paramInt2; i4++)
      {
        int i5 = 1 + paramInt1 * (-4 + i4 * 5);
        double d25 = paramArrayOfDouble1[i5] - paramArrayOfDouble1[(i5 + paramInt1 * 3)];
        double d26 = paramArrayOfDouble1[i5] + paramArrayOfDouble1[(i5 + paramInt1 * 3)];
        double d27 = paramArrayOfDouble1[(i5 + paramInt1)] - paramArrayOfDouble1[(i5 + paramInt1 * 2)];
        double d28 = paramArrayOfDouble1[(i5 + paramInt1)] + paramArrayOfDouble1[(i5 + paramInt1 * 2)];
        double d29 = paramArrayOfDouble1[(i5 - 1)] - paramArrayOfDouble1[(-1 + (i5 + paramInt1 * 3))];
        double d30 = paramArrayOfDouble1[(i5 - 1)] + paramArrayOfDouble1[(-1 + (i5 + paramInt1 * 3))];
        double d31 = paramArrayOfDouble1[(-1 + (i5 + paramInt1))] - paramArrayOfDouble1[(-1 + (i5 + paramInt1 * 2))];
        double d32 = paramArrayOfDouble1[(-1 + (i5 + paramInt1))] + paramArrayOfDouble1[(-1 + (i5 + paramInt1 * 2))];
        int i6 = paramInt1 * (i4 - 1);
        paramArrayOfDouble2[i6] = (d32 + (d30 + paramArrayOfDouble1[(-1 + (i5 - paramInt1))]));
        paramArrayOfDouble2[(i6 + 1)] = (d28 + (d26 + paramArrayOfDouble1[(i5 - paramInt1)]));
        double d33 = paramArrayOfDouble1[(-1 + (i5 - paramInt1))] + 0.309016994374947D * d30 + -0.809016994374947D * d32;
        double d34 = paramArrayOfDouble1[(i5 - paramInt1)] + 0.309016994374947D * d26 + -0.809016994374947D * d28;
        double d35 = paramArrayOfDouble1[(-1 + (i5 - paramInt1))] + -0.809016994374947D * d30 + 0.309016994374947D * d32;
        double d36 = paramArrayOfDouble1[(i5 - paramInt1)] + -0.809016994374947D * d26 + 0.309016994374947D * d28;
        double d37 = paramInt4 * (0.951056516295154D * d29 + 0.587785252292473D * d31);
        double d38 = paramInt4 * (0.951056516295154D * d25 + 0.587785252292473D * d27);
        double d39 = paramInt4 * (0.587785252292473D * d29 - 0.951056516295154D * d31);
        double d40 = paramInt4 * (0.587785252292473D * d25 - 0.951056516295154D * d27);
        paramArrayOfDouble2[(i6 + paramInt2 * paramInt1)] = (d33 - d38);
        paramArrayOfDouble2[(i6 + paramInt1 * (paramInt2 * 4))] = (d33 + d38);
        paramArrayOfDouble2[(1 + (i6 + paramInt2 * paramInt1))] = (d34 + d37);
        paramArrayOfDouble2[(1 + (i6 + paramInt1 * (paramInt2 * 2)))] = (d36 + d39);
        paramArrayOfDouble2[(i6 + paramInt1 * (paramInt2 * 2))] = (d35 - d40);
        paramArrayOfDouble2[(i6 + paramInt1 * (paramInt2 * 3))] = (d35 + d40);
        paramArrayOfDouble2[(1 + (i6 + paramInt1 * (paramInt2 * 3)))] = (d36 - d39);
        paramArrayOfDouble2[(1 + (i6 + paramInt1 * (paramInt2 * 4)))] = (d34 - d37);
      }
    for (int m = 1; m <= paramInt2; m++)
      for (int n = 0; ; n += 2)
      {
        int i1 = paramInt1 - 1;
        if (n >= i1)
          break;
        int i2 = n + 1 + paramInt1 * (-4 + m * 5);
        double d1 = paramArrayOfDouble1[i2] - paramArrayOfDouble1[(i2 + paramInt1 * 3)];
        double d2 = paramArrayOfDouble1[i2] + paramArrayOfDouble1[(i2 + paramInt1 * 3)];
        double d3 = paramArrayOfDouble1[(i2 + paramInt1)] - paramArrayOfDouble1[(i2 + paramInt1 * 2)];
        double d4 = paramArrayOfDouble1[(i2 + paramInt1)] + paramArrayOfDouble1[(i2 + paramInt1 * 2)];
        double d5 = paramArrayOfDouble1[(i2 - 1)] - paramArrayOfDouble1[(-1 + (i2 + paramInt1 * 3))];
        double d6 = paramArrayOfDouble1[(i2 - 1)] + paramArrayOfDouble1[(-1 + (i2 + paramInt1 * 3))];
        double d7 = paramArrayOfDouble1[(-1 + (i2 + paramInt1))] - paramArrayOfDouble1[(-1 + (i2 + paramInt1 * 2))];
        double d8 = paramArrayOfDouble1[(-1 + (i2 + paramInt1))] + paramArrayOfDouble1[(-1 + (i2 + paramInt1 * 2))];
        int i3 = n + paramInt1 * (m - 1);
        paramArrayOfDouble2[i3] = (d8 + (d6 + paramArrayOfDouble1[(-1 + (i2 - paramInt1))]));
        paramArrayOfDouble2[(i3 + 1)] = (d4 + (d2 + paramArrayOfDouble1[(i2 - paramInt1)]));
        double d9 = paramArrayOfDouble1[(-1 + (i2 - paramInt1))] + 0.309016994374947D * d6 + -0.809016994374947D * d8;
        double d10 = paramArrayOfDouble1[(i2 - paramInt1)] + 0.309016994374947D * d2 + -0.809016994374947D * d4;
        double d11 = paramArrayOfDouble1[(-1 + (i2 - paramInt1))] + -0.809016994374947D * d6 + 0.309016994374947D * d8;
        double d12 = paramArrayOfDouble1[(i2 - paramInt1)] + -0.809016994374947D * d2 + 0.309016994374947D * d4;
        double d13 = paramInt4 * (0.951056516295154D * d5 + 0.587785252292473D * d7);
        double d14 = paramInt4 * (0.951056516295154D * d1 + 0.587785252292473D * d3);
        double d15 = paramInt4 * (0.587785252292473D * d5 - 0.951056516295154D * d7);
        double d16 = paramInt4 * (0.587785252292473D * d1 - 0.951056516295154D * d3);
        double d17 = d11 - d16;
        double d18 = d11 + d16;
        double d19 = d12 + d15;
        double d20 = d12 - d15;
        double d21 = d9 + d14;
        double d22 = d9 - d14;
        double d23 = d10 - d13;
        double d24 = d10 + d13;
        paramArrayOfDouble2[(i3 + paramInt2 * paramInt1)] = (d22 * paramArrayOfDouble3[(n + paramInt3)] - d24 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (n + 1))]));
        paramArrayOfDouble2[(1 + (i3 + paramInt2 * paramInt1))] = (d24 * paramArrayOfDouble3[(n + paramInt3)] + d22 * (paramInt4 * paramArrayOfDouble3[(paramInt3 + (n + 1))]));
        paramArrayOfDouble2[(i3 + paramInt1 * (paramInt2 * 2))] = (d17 * paramArrayOfDouble3[(n + i)] - d19 * (paramInt4 * paramArrayOfDouble3[(i + (n + 1))]));
        paramArrayOfDouble2[(1 + (i3 + paramInt1 * (paramInt2 * 2)))] = (d19 * paramArrayOfDouble3[(n + i)] + d17 * (paramInt4 * paramArrayOfDouble3[(i + (n + 1))]));
        paramArrayOfDouble2[(i3 + paramInt1 * (paramInt2 * 3))] = (d18 * paramArrayOfDouble3[(n + j)] - d20 * (paramInt4 * paramArrayOfDouble3[(j + (n + 1))]));
        paramArrayOfDouble2[(1 + (i3 + paramInt1 * (paramInt2 * 3)))] = (d20 * paramArrayOfDouble3[(n + j)] + d18 * (paramInt4 * paramArrayOfDouble3[(j + (n + 1))]));
        paramArrayOfDouble2[(i3 + paramInt1 * (paramInt2 * 4))] = (d21 * paramArrayOfDouble3[(n + k)] - d23 * (paramInt4 * paramArrayOfDouble3[(k + (n + 1))]));
        paramArrayOfDouble2[(1 + (i3 + paramInt1 * (paramInt2 * 4)))] = (d23 * paramArrayOfDouble3[(n + k)] + d21 * (paramInt4 * paramArrayOfDouble3[(k + (n + 1))]));
      }
  }

  void passfg(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6, int paramInt5, int paramInt6)
  {
    /*int i = paramInt1 / 2;
    (paramInt2 * paramInt4);
    int j = (paramInt2 + 1) / 2;
    int k = paramInt2 * paramInt1;
    if (paramInt1 >= paramInt3)
    {
      for (int i33 = 1; i33 < j; i33++)
      {
        int i36 = paramInt2 - i33;
        for (int i37 = 0; i37 < paramInt3; i37++)
          for (int i38 = 0; i38 < paramInt1; i38++)
          {
            paramArrayOfDouble1[(i38 + paramInt1 * (i33 + i37 * paramInt2))] += paramArrayOfDouble1[(i38 + paramInt1 * (i36 + i37 * paramInt2))];
            paramArrayOfDouble1[(i38 + paramInt1 * (i33 + i37 * paramInt2))] -= paramArrayOfDouble1[(i38 + paramInt1 * (i36 + i37 * paramInt2))];
          }
      }
      for (int i34 = 0; i34 < paramInt3; i34++)
        for (int i35 = 0; i35 < paramInt1; i35++)
          paramArrayOfDouble4[(i35 + i34 * paramInt1)] = paramArrayOfDouble1[(i35 + paramInt1 * (i34 * paramInt2))];
    }
    for (int m = 1; m < j; m++)
    {
      int i30 = paramInt2 - m;
      for (int i31 = 0; i31 < paramInt1; i31++)
        for (int i32 = 0; i32 < paramInt3; i32++)
        {
          paramArrayOfDouble1[(i31 + paramInt1 * (m + i32 * paramInt2))] += paramArrayOfDouble1[(i31 + paramInt1 * (i30 + i32 * paramInt2))];
          paramArrayOfDouble1[(i31 + paramInt1 * (m + i32 * paramInt2))] -= paramArrayOfDouble1[(i31 + paramInt1 * (i30 + i32 * paramInt2))];
        }
    }
    for (int n = 0; n < paramInt1; n++)
      for (int i29 = 0; i29 < paramInt3; i29++)
        paramArrayOfDouble4[(n + i29 * paramInt1)] = paramArrayOfDouble1[(n + paramInt1 * (i29 * paramInt2))];
    int i1 = 2 - paramInt1;
    int i2 = 0;
    for (int i3 = 1; i3 < j; i3++)
    {
      int i21 = paramInt2 - i3;
      i1 += paramInt1;
      for (int i22 = 0; i22 < paramInt4; i22++)
      {
        paramArrayOfDouble3[(i22 + i3 * paramInt4)] = (paramArrayOfDouble5[i22] + paramArrayOfDouble6[(paramInt5 + (i1 - 2))] * paramArrayOfDouble5[(i22 + paramInt4)]);
        paramArrayOfDouble3[(i22 + i21 * paramInt4)] = (paramInt6 * paramArrayOfDouble6[(paramInt5 + (i1 - 1))] * paramArrayOfDouble5[(i22 + paramInt4 * (paramInt2 - 1))]);
      }
      int i23 = i1;
      i2 += paramInt1;
      for (int i24 = 2; i24 < j; i24++)
      {
        int i25 = paramInt2 - i24;
        i23 += i2;
        if (i23 > k)
          i23 -= k;
        double d1 = paramArrayOfDouble6[(paramInt5 + (i23 - 2))];
        double d2 = paramArrayOfDouble6[(paramInt5 + (i23 - 1))];
        for (int i26 = 0; i26 < paramInt4; i26++)
        {
          int i27 = i26 + i3 * paramInt4;
          paramArrayOfDouble3[i27] += d1 * paramArrayOfDouble5[(i26 + i24 * paramInt4)];
          int i28 = i26 + i21 * paramInt4;
          paramArrayOfDouble3[i28] += d2 * paramInt6 * paramArrayOfDouble5[(i26 + i25 * paramInt4)];
        }
      }
    }
    for (int i4 = 1; i4 < j; i4++)
      for (int i20 = 0; i20 < paramInt4; i20++)
        paramArrayOfDouble5[i20] += paramArrayOfDouble5[(i20 + i4 * paramInt4)];
    for (int i5 = 1; i5 < j; i5++)
    {
      int i18 = paramInt2 - i5;
      for (int i19 = 1; i19 < paramInt4; i19 += 2)
      {
        paramArrayOfDouble3[(i19 - 1 + i5 * paramInt4)] -= paramArrayOfDouble3[(i19 + i18 * paramInt4)];
        paramArrayOfDouble3[(i19 - 1 + i5 * paramInt4)] += paramArrayOfDouble3[(i19 + i18 * paramInt4)];
        paramArrayOfDouble3[(i19 + i5 * paramInt4)] += paramArrayOfDouble3[(i19 - 1 + i18 * paramInt4)];
        paramArrayOfDouble3[(i19 + i5 * paramInt4)] -= paramArrayOfDouble3[(i19 - 1 + i18 * paramInt4)];
      }
    }
    paramArrayOfInt[0] = 1;
    if (paramInt1 == 2);
    while (true)
    {
      return;
      paramArrayOfInt[0] = 0;
      for (int i6 = 0; i6 < paramInt4; i6++)
        paramArrayOfDouble3[i6] = paramArrayOfDouble5[i6];
      for (int i7 = 1; i7 < paramInt2; i7++)
        for (int i17 = 0; i17 < paramInt3; i17++)
        {
          paramArrayOfDouble2[(0 + paramInt1 * (i17 + i7 * paramInt3))] = paramArrayOfDouble4[(0 + paramInt1 * (i17 + i7 * paramInt3))];
          paramArrayOfDouble2[(1 + paramInt1 * (i17 + i7 * paramInt3))] = paramArrayOfDouble4[(1 + paramInt1 * (i17 + i7 * paramInt3))];
        }
      if (i <= paramInt3)
      {
        int i13 = 0;
        for (int i14 = 1; i14 < paramInt2; i14++)
        {
          i13 += 2;
          for (int i15 = 3; i15 < paramInt1; i15 += 2)
          {
            i13 += 2;
            for (int i16 = 0; i16 < paramInt3; i16++)
            {
              paramArrayOfDouble2[(i15 - 1 + paramInt1 * (i16 + i14 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i13 - 2))] * paramArrayOfDouble4[(i15 - 1 + paramInt1 * (i16 + i14 * paramInt3))] - paramInt6 * paramArrayOfDouble6[(paramInt5 + (i13 - 1))] * paramArrayOfDouble4[(i15 + paramInt1 * (i16 + i14 * paramInt3))]);
              paramArrayOfDouble2[(i15 + paramInt1 * (i16 + i14 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i13 - 2))] * paramArrayOfDouble4[(i15 + paramInt1 * (i16 + i14 * paramInt3))] + paramInt6 * paramArrayOfDouble6[(paramInt5 + (i13 - 1))] * paramArrayOfDouble4[(i15 - 1 + paramInt1 * (i16 + i14 * paramInt3))]);
            }
          }
        }
        continue;
      }
      int i8 = 2 - paramInt1;
      for (int i9 = 1; i9 < paramInt2; i9++)
      {
        i8 += paramInt1;
        for (int i10 = 0; i10 < paramInt3; i10++)
        {
          int i11 = i8;
          for (int i12 = 3; i12 < paramInt1; i12 += 2)
          {
            i11 += 2;
            paramArrayOfDouble2[(i12 - 1 + paramInt1 * (i10 + i9 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i11 - 2))] * paramArrayOfDouble4[(i12 - 1 + paramInt1 * (i10 + i9 * paramInt3))] - paramInt6 * paramArrayOfDouble6[(paramInt5 + (i11 - 1))] * paramArrayOfDouble4[(i12 + paramInt1 * (i10 + i9 * paramInt3))]);
            paramArrayOfDouble2[(i12 + paramInt1 * (i10 + i9 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i11 - 2))] * paramArrayOfDouble4[(i12 + paramInt1 * (i10 + i9 * paramInt3))] + paramInt6 * paramArrayOfDouble6[(paramInt5 + (i11 - 1))] * paramArrayOfDouble4[(i12 - 1 + paramInt1 * (i10 + i9 * paramInt3))]);
          }
        }
      }
    }*/
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.ComplexDoubleFFT_Mixed
 * JD-Core Version:    0.6.0
 */