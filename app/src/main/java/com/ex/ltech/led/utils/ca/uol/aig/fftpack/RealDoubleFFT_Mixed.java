package com.ex.ltech.led.utils.ca.uol.aig.fftpack;

class RealDoubleFFT_Mixed
{
  void radb2(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    for (int i = 0; i < paramInt2; i++)
    {
      paramArrayOfDouble2[(i * paramInt1)] = (paramArrayOfDouble1[(paramInt1 * (i * 2))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + i * 2))]);
      paramArrayOfDouble2[(paramInt1 * (i + paramInt2))] = (paramArrayOfDouble1[(paramInt1 * (i * 2))] - paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + i * 2))]);
    }
    if (paramInt1 < 2);
    while (true)
    {
      return;
      if (paramInt1 != 2)
      {
        for (int k = 0; k < paramInt2; k++)
          for (int m = 2; m < paramInt1; m += 2)
          {
            int n = paramInt1 - m;
            paramArrayOfDouble2[(m - 1 + k * paramInt1)] = (paramArrayOfDouble1[(m - 1 + paramInt1 * (k * 2))] + paramArrayOfDouble1[(n - 1 + paramInt1 * (1 + k * 2))]);
            double d1 = paramArrayOfDouble1[(m - 1 + paramInt1 * (k * 2))] - paramArrayOfDouble1[(n - 1 + paramInt1 * (1 + k * 2))];
            paramArrayOfDouble2[(m + k * paramInt1)] = (paramArrayOfDouble1[(m + paramInt1 * (k * 2))] - paramArrayOfDouble1[(n + paramInt1 * (1 + k * 2))]);
            double d2 = paramArrayOfDouble1[(m + paramInt1 * (k * 2))] + paramArrayOfDouble1[(n + paramInt1 * (1 + k * 2))];
            paramArrayOfDouble2[(m - 1 + paramInt1 * (k + paramInt2))] = (d1 * paramArrayOfDouble3[(paramInt3 + (m - 2))] - d2 * paramArrayOfDouble3[(paramInt3 + (m - 1))]);
            paramArrayOfDouble2[(m + paramInt1 * (k + paramInt2))] = (d2 * paramArrayOfDouble3[(paramInt3 + (m - 2))] + d1 * paramArrayOfDouble3[(paramInt3 + (m - 1))]);
          }
        if (paramInt1 % 2 == 1)
          continue;
      }
      for (int j = 0; j < paramInt2; j++)
      {
        paramArrayOfDouble2[(paramInt1 - 1 + j * paramInt1)] = (2.0D * paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (j * 2))]);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (j + paramInt2))] = (-2.0D * paramArrayOfDouble1[(paramInt1 * (1 + j * 2))]);
      }
    }
  }

  void radb3(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    for (int j = 0; j < paramInt2; j++)
    {
      double d11 = 2.0D * paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + j * 3))];
      double d12 = paramArrayOfDouble1[(paramInt1 * (j * 3))] + -0.5D * d11;
      paramArrayOfDouble2[(j * paramInt1)] = (d11 + paramArrayOfDouble1[(paramInt1 * (j * 3))]);
      double d13 = 1.732050807568878D * paramArrayOfDouble1[(paramInt1 * (2 + j * 3))];
      paramArrayOfDouble2[(paramInt1 * (j + paramInt2))] = (d12 - d13);
      paramArrayOfDouble2[(paramInt1 * (j + paramInt2 * 2))] = (d12 + d13);
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      for (int k = 0; k < paramInt2; k++)
        for (int m = 2; m < paramInt1; m += 2)
        {
          int n = paramInt1 - m;
          double d1 = paramArrayOfDouble1[(m - 1 + paramInt1 * (2 + k * 3))] + paramArrayOfDouble1[(n - 1 + paramInt1 * (1 + k * 3))];
          double d2 = paramArrayOfDouble1[(m - 1 + paramInt1 * (k * 3))] + -0.5D * d1;
          paramArrayOfDouble2[(m - 1 + k * paramInt1)] = (d1 + paramArrayOfDouble1[(m - 1 + paramInt1 * (k * 3))]);
          double d3 = paramArrayOfDouble1[(m + paramInt1 * (2 + k * 3))] - paramArrayOfDouble1[(n + paramInt1 * (1 + k * 3))];
          double d4 = paramArrayOfDouble1[(m + paramInt1 * (k * 3))] + -0.5D * d3;
          paramArrayOfDouble2[(m + k * paramInt1)] = (d3 + paramArrayOfDouble1[(m + paramInt1 * (k * 3))]);
          double d5 = 0.866025403784439D * (paramArrayOfDouble1[(m - 1 + paramInt1 * (2 + k * 3))] - paramArrayOfDouble1[(n - 1 + paramInt1 * (1 + k * 3))]);
          double d6 = 0.866025403784439D * (paramArrayOfDouble1[(m + paramInt1 * (2 + k * 3))] + paramArrayOfDouble1[(n + paramInt1 * (1 + k * 3))]);
          double d7 = d2 - d6;
          double d8 = d2 + d6;
          double d9 = d4 + d5;
          double d10 = d4 - d5;
          paramArrayOfDouble2[(m - 1 + paramInt1 * (k + paramInt2))] = (d7 * paramArrayOfDouble3[(paramInt3 + (m - 2))] - d9 * paramArrayOfDouble3[(paramInt3 + (m - 1))]);
          paramArrayOfDouble2[(m + paramInt1 * (k + paramInt2))] = (d9 * paramArrayOfDouble3[(paramInt3 + (m - 2))] + d7 * paramArrayOfDouble3[(paramInt3 + (m - 1))]);
          paramArrayOfDouble2[(m - 1 + paramInt1 * (k + paramInt2 * 2))] = (d8 * paramArrayOfDouble3[(i + (m - 2))] - d10 * paramArrayOfDouble3[(i + (m - 1))]);
          paramArrayOfDouble2[(m + paramInt1 * (k + paramInt2 * 2))] = (d10 * paramArrayOfDouble3[(i + (m - 2))] + d8 * paramArrayOfDouble3[(i + (m - 1))]);
        }
    }
  }

  void radb4(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    for (int k = 0; k < paramInt2; k++)
    {
      double d19 = paramArrayOfDouble1[(paramInt1 * (k * 4))] - paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (3 + k * 4))];
      double d20 = paramArrayOfDouble1[(paramInt1 * (k * 4))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (3 + k * 4))];
      double d21 = paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + k * 4))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + k * 4))];
      double d22 = paramArrayOfDouble1[(paramInt1 * (2 + k * 4))] + paramArrayOfDouble1[(paramInt1 * (2 + k * 4))];
      paramArrayOfDouble2[(k * paramInt1)] = (d20 + d21);
      paramArrayOfDouble2[(paramInt1 * (k + paramInt2))] = (d19 - d22);
      paramArrayOfDouble2[(paramInt1 * (k + paramInt2 * 2))] = (d20 - d21);
      paramArrayOfDouble2[(paramInt1 * (k + paramInt2 * 3))] = (d19 + d22);
    }
    if (paramInt1 < 2);
    while (true)
    {
      return;
      if (paramInt1 != 2)
      {
        for (int n = 0; n < paramInt2; n++)
          for (int i1 = 2; i1 < paramInt1; i1 += 2)
          {
            int i2 = paramInt1 - i1;
            double d5 = paramArrayOfDouble1[(i1 + paramInt1 * (n * 4))] + paramArrayOfDouble1[(i2 + paramInt1 * (3 + n * 4))];
            double d6 = paramArrayOfDouble1[(i1 + paramInt1 * (n * 4))] - paramArrayOfDouble1[(i2 + paramInt1 * (3 + n * 4))];
            double d7 = paramArrayOfDouble1[(i1 + paramInt1 * (2 + n * 4))] - paramArrayOfDouble1[(i2 + paramInt1 * (1 + n * 4))];
            double d8 = paramArrayOfDouble1[(i1 + paramInt1 * (2 + n * 4))] + paramArrayOfDouble1[(i2 + paramInt1 * (1 + n * 4))];
            double d9 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n * 4))] - paramArrayOfDouble1[(i2 - 1 + paramInt1 * (3 + n * 4))];
            double d10 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n * 4))] + paramArrayOfDouble1[(i2 - 1 + paramInt1 * (3 + n * 4))];
            double d11 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (2 + n * 4))] - paramArrayOfDouble1[(i2 - 1 + paramInt1 * (1 + n * 4))];
            double d12 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (2 + n * 4))] + paramArrayOfDouble1[(i2 - 1 + paramInt1 * (1 + n * 4))];
            paramArrayOfDouble2[(i1 - 1 + n * paramInt1)] = (d10 + d12);
            double d13 = d10 - d12;
            paramArrayOfDouble2[(i1 + n * paramInt1)] = (d6 + d7);
            double d14 = d6 - d7;
            double d15 = d9 - d8;
            double d16 = d9 + d8;
            double d17 = d5 + d11;
            double d18 = d5 - d11;
            paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2))] = (d15 * paramArrayOfDouble3[(paramInt3 + (i1 - 2))] - d17 * paramArrayOfDouble3[(paramInt3 + (i1 - 1))]);
            paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2))] = (d17 * paramArrayOfDouble3[(paramInt3 + (i1 - 2))] + d15 * paramArrayOfDouble3[(paramInt3 + (i1 - 1))]);
            paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))] = (d13 * paramArrayOfDouble3[(i + (i1 - 2))] - d14 * paramArrayOfDouble3[(i + (i1 - 1))]);
            paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2 * 2))] = (d14 * paramArrayOfDouble3[(i + (i1 - 2))] + d13 * paramArrayOfDouble3[(i + (i1 - 1))]);
            paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))] = (d16 * paramArrayOfDouble3[(j + (i1 - 2))] - d18 * paramArrayOfDouble3[(j + (i1 - 1))]);
            paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2 * 3))] = (d18 * paramArrayOfDouble3[(j + (i1 - 2))] + d16 * paramArrayOfDouble3[(j + (i1 - 1))]);
          }
        if (paramInt1 % 2 == 1)
          continue;
      }
      for (int m = 0; m < paramInt2; m++)
      {
        double d1 = paramArrayOfDouble1[(paramInt1 * (1 + m * 4))] + paramArrayOfDouble1[(paramInt1 * (3 + m * 4))];
        double d2 = paramArrayOfDouble1[(paramInt1 * (3 + m * 4))] - paramArrayOfDouble1[(paramInt1 * (1 + m * 4))];
        double d3 = paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m * 4))] - paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (2 + m * 4))];
        double d4 = paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m * 4))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (2 + m * 4))];
        paramArrayOfDouble2[(paramInt1 - 1 + m * paramInt1)] = (d4 + d4);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (m + paramInt2))] = (1.414213562373095D * (d3 - d1));
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 2))] = (d2 + d2);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 3))] = (-1.414213562373095D * (d3 + d1));
      }
    }
  }

  void radb5(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    int k = j + paramInt1;
    for (int m = 0; m < paramInt2; m++)
    {
      double d25 = 2.0D * paramArrayOfDouble1[(paramInt1 * (2 + m * 5))];
      double d26 = 2.0D * paramArrayOfDouble1[(paramInt1 * (4 + m * 5))];
      double d27 = 2.0D * paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (1 + m * 5))];
      double d28 = 2.0D * paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (3 + m * 5))];
      paramArrayOfDouble2[(m * paramInt1)] = (d28 + (d27 + paramArrayOfDouble1[(paramInt1 * (m * 5))]));
      double d29 = paramArrayOfDouble1[(paramInt1 * (m * 5))] + 0.309016994374947D * d27 + -0.809016994374947D * d28;
      double d30 = paramArrayOfDouble1[(paramInt1 * (m * 5))] + -0.809016994374947D * d27 + 0.309016994374947D * d28;
      double d31 = 0.951056516295154D * d25 + 0.587785252292473D * d26;
      double d32 = 0.587785252292473D * d25 - 0.951056516295154D * d26;
      paramArrayOfDouble2[(paramInt1 * (m + paramInt2))] = (d29 - d31);
      paramArrayOfDouble2[(paramInt1 * (m + paramInt2 * 2))] = (d30 - d32);
      paramArrayOfDouble2[(paramInt1 * (m + paramInt2 * 3))] = (d30 + d32);
      paramArrayOfDouble2[(paramInt1 * (m + paramInt2 * 4))] = (d29 + d31);
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      for (int n = 0; n < paramInt2; n++)
        for (int i1 = 2; i1 < paramInt1; i1 += 2)
        {
          int i2 = paramInt1 - i1;
          double d1 = paramArrayOfDouble1[(i1 + paramInt1 * (2 + n * 5))] + paramArrayOfDouble1[(i2 + paramInt1 * (1 + n * 5))];
          double d2 = paramArrayOfDouble1[(i1 + paramInt1 * (2 + n * 5))] - paramArrayOfDouble1[(i2 + paramInt1 * (1 + n * 5))];
          double d3 = paramArrayOfDouble1[(i1 + paramInt1 * (4 + n * 5))] + paramArrayOfDouble1[(i2 + paramInt1 * (3 + n * 5))];
          double d4 = paramArrayOfDouble1[(i1 + paramInt1 * (4 + n * 5))] - paramArrayOfDouble1[(i2 + paramInt1 * (3 + n * 5))];
          double d5 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (2 + n * 5))] - paramArrayOfDouble1[(i2 - 1 + paramInt1 * (1 + n * 5))];
          double d6 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (2 + n * 5))] + paramArrayOfDouble1[(i2 - 1 + paramInt1 * (1 + n * 5))];
          double d7 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (4 + n * 5))] - paramArrayOfDouble1[(i2 - 1 + paramInt1 * (3 + n * 5))];
          double d8 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (4 + n * 5))] + paramArrayOfDouble1[(i2 - 1 + paramInt1 * (3 + n * 5))];
          paramArrayOfDouble2[(i1 - 1 + n * paramInt1)] = (d8 + (d6 + paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n * 5))]));
          paramArrayOfDouble2[(i1 + n * paramInt1)] = (d4 + (d2 + paramArrayOfDouble1[(i1 + paramInt1 * (n * 5))]));
          double d9 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n * 5))] + 0.309016994374947D * d6 + -0.809016994374947D * d8;
          double d10 = paramArrayOfDouble1[(i1 + paramInt1 * (n * 5))] + 0.309016994374947D * d2 + -0.809016994374947D * d4;
          double d11 = paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n * 5))] + -0.809016994374947D * d6 + 0.309016994374947D * d8;
          double d12 = paramArrayOfDouble1[(i1 + paramInt1 * (n * 5))] + -0.809016994374947D * d2 + 0.309016994374947D * d4;
          double d13 = 0.951056516295154D * d5 + 0.587785252292473D * d7;
          double d14 = 0.951056516295154D * d1 + 0.587785252292473D * d3;
          double d15 = 0.587785252292473D * d5 - 0.951056516295154D * d7;
          double d16 = 0.587785252292473D * d1 - 0.951056516295154D * d3;
          double d17 = d11 - d16;
          double d18 = d11 + d16;
          double d19 = d12 + d15;
          double d20 = d12 - d15;
          double d21 = d9 + d14;
          double d22 = d9 - d14;
          double d23 = d10 - d13;
          double d24 = d10 + d13;
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2))] = (d22 * paramArrayOfDouble3[(paramInt3 + (i1 - 2))] - d24 * paramArrayOfDouble3[(paramInt3 + (i1 - 1))]);
          paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2))] = (d24 * paramArrayOfDouble3[(paramInt3 + (i1 - 2))] + d22 * paramArrayOfDouble3[(paramInt3 + (i1 - 1))]);
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))] = (d17 * paramArrayOfDouble3[(i + (i1 - 2))] - d19 * paramArrayOfDouble3[(i + (i1 - 1))]);
          paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2 * 2))] = (d19 * paramArrayOfDouble3[(i + (i1 - 2))] + d17 * paramArrayOfDouble3[(i + (i1 - 1))]);
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))] = (d18 * paramArrayOfDouble3[(j + (i1 - 2))] - d20 * paramArrayOfDouble3[(j + (i1 - 1))]);
          paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2 * 3))] = (d20 * paramArrayOfDouble3[(j + (i1 - 2))] + d18 * paramArrayOfDouble3[(j + (i1 - 1))]);
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n + paramInt2 * 4))] = (d21 * paramArrayOfDouble3[(k + (i1 - 2))] - d23 * paramArrayOfDouble3[(k + (i1 - 1))]);
          paramArrayOfDouble2[(i1 + paramInt1 * (n + paramInt2 * 4))] = (d23 * paramArrayOfDouble3[(k + (i1 - 2))] + d21 * paramArrayOfDouble3[(k + (i1 - 1))]);
        }
    }
  }

  void radbg(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6, int paramInt5)
  {
    double d1 = 6.283185307179586D / paramInt2;
    double d2 = Math.cos(d1);
    double d3 = Math.sin(d1);
    int i = (paramInt1 - 1) / 2;
    int j = (paramInt2 + 1) / 2;
    if (paramInt1 >= paramInt3)
      for (int i48 = 0; i48 < paramInt3; i48++)
        for (int i49 = 0; i49 < paramInt1; i49++)
          paramArrayOfDouble4[(i49 + i48 * paramInt1)] = paramArrayOfDouble1[(i49 + paramInt1 * (i48 * paramInt2))];
    for (int k = 0; k < paramInt1; k++)
      for (int i47 = 0; i47 < paramInt3; i47++)
        paramArrayOfDouble4[(k + i47 * paramInt1)] = paramArrayOfDouble1[(k + paramInt1 * (i47 * paramInt2))];
    for (int m = 1; m < j; m++)
    {
      int i44 = paramInt2 - m;
      int i45 = m * 2;
      for (int i46 = 0; i46 < paramInt3; i46++)
      {
        paramArrayOfDouble4[(paramInt1 * (i46 + m * paramInt3))] = (paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (i45 - 1 + i46 * paramInt2))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (i45 - 1 + i46 * paramInt2))]);
        paramArrayOfDouble1[(paramInt1 * (i45 + i46 * paramInt2))] += paramArrayOfDouble1[(paramInt1 * (i45 + i46 * paramInt2))];
      }
    }
    if (paramInt1 != 1)
    {
      if (i >= paramInt3)
        for (int i39 = 1; i39 < j; i39++)
        {
          int i40 = paramInt2 - i39;
          for (int i41 = 0; i41 < paramInt3; i41++)
            for (int i42 = 2; i42 < paramInt1; i42 += 2)
            {
              int i43 = paramInt1 - i42;
              paramArrayOfDouble4[(i42 - 1 + paramInt1 * (i41 + i39 * paramInt3))] = (paramArrayOfDouble1[(i42 - 1 + paramInt1 * (i39 * 2 + i41 * paramInt2))] + paramArrayOfDouble1[(i43 - 1 + paramInt1 * (-1 + i39 * 2 + i41 * paramInt2))]);
              paramArrayOfDouble4[(i42 - 1 + paramInt1 * (i41 + i40 * paramInt3))] = (paramArrayOfDouble1[(i42 - 1 + paramInt1 * (i39 * 2 + i41 * paramInt2))] - paramArrayOfDouble1[(i43 - 1 + paramInt1 * (-1 + i39 * 2 + i41 * paramInt2))]);
              paramArrayOfDouble4[(i42 + paramInt1 * (i41 + i39 * paramInt3))] = (paramArrayOfDouble1[(i42 + paramInt1 * (i39 * 2 + i41 * paramInt2))] - paramArrayOfDouble1[(i43 + paramInt1 * (-1 + i39 * 2 + i41 * paramInt2))]);
              paramArrayOfDouble4[(i42 + paramInt1 * (i41 + i40 * paramInt3))] = (paramArrayOfDouble1[(i42 + paramInt1 * (i39 * 2 + i41 * paramInt2))] + paramArrayOfDouble1[(i43 + paramInt1 * (-1 + i39 * 2 + i41 * paramInt2))]);
            }
        }
      for (int i34 = 1; i34 < j; i34++)
      {
        int i35 = paramInt2 - i34;
        for (int i36 = 2; i36 < paramInt1; i36 += 2)
        {
          int i37 = paramInt1 - i36;
          for (int i38 = 0; i38 < paramInt3; i38++)
          {
            paramArrayOfDouble4[(i36 - 1 + paramInt1 * (i38 + i34 * paramInt3))] = (paramArrayOfDouble1[(i36 - 1 + paramInt1 * (i34 * 2 + i38 * paramInt2))] + paramArrayOfDouble1[(i37 - 1 + paramInt1 * (-1 + i34 * 2 + i38 * paramInt2))]);
            paramArrayOfDouble4[(i36 - 1 + paramInt1 * (i38 + i35 * paramInt3))] = (paramArrayOfDouble1[(i36 - 1 + paramInt1 * (i34 * 2 + i38 * paramInt2))] - paramArrayOfDouble1[(i37 - 1 + paramInt1 * (-1 + i34 * 2 + i38 * paramInt2))]);
            paramArrayOfDouble4[(i36 + paramInt1 * (i38 + i34 * paramInt3))] = (paramArrayOfDouble1[(i36 + paramInt1 * (i34 * 2 + i38 * paramInt2))] - paramArrayOfDouble1[(i37 + paramInt1 * (-1 + i34 * 2 + i38 * paramInt2))]);
            paramArrayOfDouble4[(i36 + paramInt1 * (i38 + i35 * paramInt3))] = (paramArrayOfDouble1[(i36 + paramInt1 * (i34 * 2 + i38 * paramInt2))] + paramArrayOfDouble1[(i37 + paramInt1 * (-1 + i34 * 2 + i38 * paramInt2))]);
          }
        }
      }
    }
    double d4 = 1.0D;
    double d5 = 0.0D;
    for (int n = 1; n < j; n++)
    {
      int i27 = paramInt2 - n;
      double d6 = d2 * d4 - d3 * d5;
      d5 = d2 * d5 + d3 * d4;
      d4 = d6;
      for (int i28 = 0; i28 < paramInt4; i28++)
      {
        paramArrayOfDouble3[(i28 + n * paramInt4)] = (paramArrayOfDouble5[i28] + d4 * paramArrayOfDouble5[(i28 + paramInt4)]);
        paramArrayOfDouble3[(i28 + i27 * paramInt4)] = (d5 * paramArrayOfDouble5[(i28 + paramInt4 * (paramInt2 - 1))]);
      }
      double d7 = d4;
      double d8 = d5;
      double d9 = d4;
      double d10 = d5;
      for (int i29 = 2; i29 < j; i29++)
      {
        int i30 = paramInt2 - i29;
        double d11 = d7 * d9 - d8 * d10;
        d10 = d7 * d10 + d8 * d9;
        d9 = d11;
        for (int i31 = 0; i31 < paramInt4; i31++)
        {
          int i32 = i31 + n * paramInt4;
          paramArrayOfDouble3[i32] += d9 * paramArrayOfDouble5[(i31 + i29 * paramInt4)];
          int i33 = i31 + i27 * paramInt4;
          paramArrayOfDouble3[i33] += d10 * paramArrayOfDouble5[(i31 + i30 * paramInt4)];
        }
      }
    }
    for (int i1 = 1; i1 < j; i1++)
      for (int i26 = 0; i26 < paramInt4; i26++)
        paramArrayOfDouble5[i26] += paramArrayOfDouble5[(i26 + i1 * paramInt4)];
    for (int i2 = 1; i2 < j; i2++)
    {
      int i24 = paramInt2 - i2;
      for (int i25 = 0; i25 < paramInt3; i25++)
      {
        paramArrayOfDouble2[(paramInt1 * (i25 + i2 * paramInt3))] -= paramArrayOfDouble2[(paramInt1 * (i25 + i24 * paramInt3))];
        paramArrayOfDouble2[(paramInt1 * (i25 + i2 * paramInt3))] += paramArrayOfDouble2[(paramInt1 * (i25 + i24 * paramInt3))];
      }
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      if (i >= paramInt3)
        for (int i20 = 1; i20 < j; i20++)
        {
          int i21 = paramInt2 - i20;
          for (int i22 = 0; i22 < paramInt3; i22++)
            for (int i23 = 2; i23 < paramInt1; i23 += 2)
            {
              paramArrayOfDouble2[(i23 - 1 + paramInt1 * (i22 + i20 * paramInt3))] -= paramArrayOfDouble2[(i23 + paramInt1 * (i22 + i21 * paramInt3))];
              paramArrayOfDouble2[(i23 - 1 + paramInt1 * (i22 + i20 * paramInt3))] += paramArrayOfDouble2[(i23 + paramInt1 * (i22 + i21 * paramInt3))];
              paramArrayOfDouble2[(i23 + paramInt1 * (i22 + i20 * paramInt3))] += paramArrayOfDouble2[(i23 - 1 + paramInt1 * (i22 + i21 * paramInt3))];
              paramArrayOfDouble2[(i23 + paramInt1 * (i22 + i20 * paramInt3))] -= paramArrayOfDouble2[(i23 - 1 + paramInt1 * (i22 + i21 * paramInt3))];
            }
        }
      for (int i3 = 1; i3 < j; i3++)
      {
        int i17 = paramInt2 - i3;
        for (int i18 = 2; i18 < paramInt1; i18 += 2)
          for (int i19 = 0; i19 < paramInt3; i19++)
          {
            paramArrayOfDouble2[(i18 - 1 + paramInt1 * (i19 + i3 * paramInt3))] -= paramArrayOfDouble2[(i18 + paramInt1 * (i19 + i17 * paramInt3))];
            paramArrayOfDouble2[(i18 - 1 + paramInt1 * (i19 + i3 * paramInt3))] += paramArrayOfDouble2[(i18 + paramInt1 * (i19 + i17 * paramInt3))];
            paramArrayOfDouble2[(i18 + paramInt1 * (i19 + i3 * paramInt3))] += paramArrayOfDouble2[(i18 - 1 + paramInt1 * (i19 + i17 * paramInt3))];
            paramArrayOfDouble2[(i18 + paramInt1 * (i19 + i3 * paramInt3))] -= paramArrayOfDouble2[(i18 - 1 + paramInt1 * (i19 + i17 * paramInt3))];
          }
      }
      for (int i4 = 0; i4 < paramInt4; i4++)
        paramArrayOfDouble3[i4] = paramArrayOfDouble5[i4];
      for (int i5 = 1; i5 < paramInt2; i5++)
        for (int i16 = 0; i16 < paramInt3; i16++)
          paramArrayOfDouble2[(paramInt1 * (i16 + i5 * paramInt3))] = paramArrayOfDouble4[(paramInt1 * (i16 + i5 * paramInt3))];
      if (i <= paramInt3)
      {
        int i11 = -paramInt1;
        for (int i12 = 1; i12 < paramInt2; i12++)
        {
          i11 += paramInt1;
          int i13 = i11 - 1;
          for (int i14 = 2; i14 < paramInt1; i14 += 2)
          {
            i13 += 2;
            for (int i15 = 0; i15 < paramInt3; i15++)
            {
              paramArrayOfDouble2[(i14 - 1 + paramInt1 * (i15 + i12 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i13 - 1))] * paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i15 + i12 * paramInt3))] - paramArrayOfDouble6[(i13 + paramInt5)] * paramArrayOfDouble4[(i14 + paramInt1 * (i15 + i12 * paramInt3))]);
              paramArrayOfDouble2[(i14 + paramInt1 * (i15 + i12 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i13 - 1))] * paramArrayOfDouble4[(i14 + paramInt1 * (i15 + i12 * paramInt3))] + paramArrayOfDouble6[(i13 + paramInt5)] * paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i15 + i12 * paramInt3))]);
            }
          }
        }
        continue;
      }
      int i6 = -paramInt1;
      for (int i7 = 1; i7 < paramInt2; i7++)
      {
        i6 += paramInt1;
        for (int i8 = 0; i8 < paramInt3; i8++)
        {
          int i9 = i6 - 1;
          for (int i10 = 2; i10 < paramInt1; i10 += 2)
          {
            i9 += 2;
            paramArrayOfDouble2[(i10 - 1 + paramInt1 * (i8 + i7 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i9 - 1))] * paramArrayOfDouble4[(i10 - 1 + paramInt1 * (i8 + i7 * paramInt3))] - paramArrayOfDouble6[(i9 + paramInt5)] * paramArrayOfDouble4[(i10 + paramInt1 * (i8 + i7 * paramInt3))]);
            paramArrayOfDouble2[(i10 + paramInt1 * (i8 + i7 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i9 - 1))] * paramArrayOfDouble4[(i10 + paramInt1 * (i8 + i7 * paramInt3))] + paramArrayOfDouble6[(i9 + paramInt5)] * paramArrayOfDouble4[(i10 - 1 + paramInt1 * (i8 + i7 * paramInt3))]);
          }
        }
      }
    }
  }

  void radf2(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    for (int i = 0; i < paramInt2; i++)
    {
      paramArrayOfDouble2[(paramInt1 * (i * 2))] = (paramArrayOfDouble1[(i * paramInt1)] + paramArrayOfDouble1[(paramInt1 * (i + paramInt2))]);
      paramArrayOfDouble2[(-1 + (paramInt1 + paramInt1 * (1 + i * 2)))] = (paramArrayOfDouble1[(i * paramInt1)] - paramArrayOfDouble1[(paramInt1 * (i + paramInt2))]);
    }
    if (paramInt1 < 2);
    while (true)
    {
      return;
      if (paramInt1 != 2)
      {
        for (int k = 0; k < paramInt2; k++)
          for (int m = 2; m < paramInt1; m += 2)
          {
            int n = paramInt1 - m;
            double d1 = paramArrayOfDouble3[(paramInt3 + (m - 2))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2))] + paramArrayOfDouble3[(paramInt3 + (m - 1))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2))];
            double d2 = paramArrayOfDouble3[(paramInt3 + (m - 2))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2))] - paramArrayOfDouble3[(paramInt3 + (m - 1))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2))];
            paramArrayOfDouble2[(m + paramInt1 * (k * 2))] = (d2 + paramArrayOfDouble1[(m + k * paramInt1)]);
            paramArrayOfDouble2[(n + paramInt1 * (1 + k * 2))] = (d2 - paramArrayOfDouble1[(m + k * paramInt1)]);
            paramArrayOfDouble2[(m - 1 + paramInt1 * (k * 2))] = (d1 + paramArrayOfDouble1[(m - 1 + k * paramInt1)]);
            paramArrayOfDouble2[(n - 1 + paramInt1 * (1 + k * 2))] = (paramArrayOfDouble1[(m - 1 + k * paramInt1)] - d1);
          }
        if (paramInt1 % 2 == 1)
          continue;
      }
      for (int j = 0; j < paramInt2; j++)
      {
        paramArrayOfDouble2[(paramInt1 * (1 + j * 2))] = (-paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (j + paramInt2))]);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (j * 2))] = paramArrayOfDouble1[(paramInt1 - 1 + j * paramInt1)];
      }
    }
  }

  void radf3(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    for (int j = 0; j < paramInt2; j++)
    {
      double d11 = paramArrayOfDouble1[(paramInt1 * (j + paramInt2))] + paramArrayOfDouble1[(paramInt1 * (j + paramInt2 * 2))];
      paramArrayOfDouble2[(paramInt1 * (j * 3))] = (d11 + paramArrayOfDouble1[(j * paramInt1)]);
      paramArrayOfDouble2[(paramInt1 * (2 + j * 3))] = (0.866025403784439D * (paramArrayOfDouble1[(paramInt1 * (j + paramInt2 * 2))] - paramArrayOfDouble1[(paramInt1 * (j + paramInt2))]));
      paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (1 + j * 3))] = (paramArrayOfDouble1[(j * paramInt1)] + -0.5D * d11);
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      for (int k = 0; k < paramInt2; k++)
        for (int m = 2; m < paramInt1; m += 2)
        {
          int n = paramInt1 - m;
          double d1 = paramArrayOfDouble3[(paramInt3 + (m - 2))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2))] + paramArrayOfDouble3[(paramInt3 + (m - 1))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2))];
          double d2 = paramArrayOfDouble3[(paramInt3 + (m - 2))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2))] - paramArrayOfDouble3[(paramInt3 + (m - 1))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2))];
          double d3 = paramArrayOfDouble3[(i + (m - 2))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2 * 2))] + paramArrayOfDouble3[(i + (m - 1))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2 * 2))];
          double d4 = paramArrayOfDouble3[(i + (m - 2))] * paramArrayOfDouble1[(m + paramInt1 * (k + paramInt2 * 2))] - paramArrayOfDouble3[(i + (m - 1))] * paramArrayOfDouble1[(m - 1 + paramInt1 * (k + paramInt2 * 2))];
          double d5 = d1 + d3;
          double d6 = d2 + d4;
          paramArrayOfDouble2[(m - 1 + paramInt1 * (k * 3))] = (d5 + paramArrayOfDouble1[(m - 1 + k * paramInt1)]);
          paramArrayOfDouble2[(m + paramInt1 * (k * 3))] = (d6 + paramArrayOfDouble1[(m + k * paramInt1)]);
          double d7 = paramArrayOfDouble1[(m - 1 + k * paramInt1)] + -0.5D * d5;
          double d8 = paramArrayOfDouble1[(m + k * paramInt1)] + -0.5D * d6;
          double d9 = 0.866025403784439D * (d2 - d4);
          double d10 = 0.866025403784439D * (d3 - d1);
          paramArrayOfDouble2[(m - 1 + paramInt1 * (2 + k * 3))] = (d7 + d9);
          paramArrayOfDouble2[(n - 1 + paramInt1 * (1 + k * 3))] = (d7 - d9);
          paramArrayOfDouble2[(m + paramInt1 * (2 + k * 3))] = (d8 + d10);
          paramArrayOfDouble2[(n + paramInt1 * (1 + k * 3))] = (d10 - d8);
        }
    }
  }

  void radf4(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    for (int k = 0; k < paramInt2; k++)
    {
      double d17 = paramArrayOfDouble1[(paramInt1 * (k + paramInt2))] + paramArrayOfDouble1[(paramInt1 * (k + paramInt2 * 3))];
      double d18 = paramArrayOfDouble1[(k * paramInt1)] + paramArrayOfDouble1[(paramInt1 * (k + paramInt2 * 2))];
      paramArrayOfDouble2[(paramInt1 * (k * 4))] = (d17 + d18);
      paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (3 + k * 4))] = (d18 - d17);
      paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (1 + k * 4))] = (paramArrayOfDouble1[(k * paramInt1)] - paramArrayOfDouble1[(paramInt1 * (k + paramInt2 * 2))]);
      paramArrayOfDouble2[(paramInt1 * (2 + k * 4))] = (paramArrayOfDouble1[(paramInt1 * (k + paramInt2 * 3))] - paramArrayOfDouble1[(paramInt1 * (k + paramInt2))]);
    }
    if (paramInt1 < 2);
    while (true)
    {
      return;
      if (paramInt1 != 2)
      {
        for (int n = 0; n < paramInt2; n++)
          for (int i1 = 2; i1 < paramInt1; i1 += 2)
          {
            int i2 = paramInt1 - i1;
            double d3 = paramArrayOfDouble3[(paramInt3 + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2))] + paramArrayOfDouble3[(paramInt3 + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2))];
            double d4 = paramArrayOfDouble3[(paramInt3 + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2))] - paramArrayOfDouble3[(paramInt3 + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2))];
            double d5 = paramArrayOfDouble3[(i + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))] + paramArrayOfDouble3[(i + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 2))];
            double d6 = paramArrayOfDouble3[(i + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 2))] - paramArrayOfDouble3[(i + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))];
            double d7 = paramArrayOfDouble3[(j + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))] + paramArrayOfDouble3[(j + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 3))];
            double d8 = paramArrayOfDouble3[(j + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 3))] - paramArrayOfDouble3[(j + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))];
            double d9 = d3 + d7;
            double d10 = d7 - d3;
            double d11 = d4 + d8;
            double d12 = d4 - d8;
            double d13 = d6 + paramArrayOfDouble1[(i1 + n * paramInt1)];
            double d14 = paramArrayOfDouble1[(i1 + n * paramInt1)] - d6;
            double d15 = d5 + paramArrayOfDouble1[(i1 - 1 + n * paramInt1)];
            double d16 = paramArrayOfDouble1[(i1 - 1 + n * paramInt1)] - d5;
            paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n * 4))] = (d9 + d15);
            paramArrayOfDouble2[(i2 - 1 + paramInt1 * (3 + n * 4))] = (d15 - d9);
            paramArrayOfDouble2[(i1 + paramInt1 * (n * 4))] = (d11 + d13);
            paramArrayOfDouble2[(i2 + paramInt1 * (3 + n * 4))] = (d11 - d13);
            paramArrayOfDouble2[(i1 - 1 + paramInt1 * (2 + n * 4))] = (d12 + d16);
            paramArrayOfDouble2[(i2 - 1 + paramInt1 * (1 + n * 4))] = (d16 - d12);
            paramArrayOfDouble2[(i1 + paramInt1 * (2 + n * 4))] = (d10 + d14);
            paramArrayOfDouble2[(i2 + paramInt1 * (1 + n * 4))] = (d10 - d14);
          }
        if (paramInt1 % 2 == 1)
          continue;
      }
      for (int m = 0; m < paramInt2; m++)
      {
        double d1 = -0.7071067811865475D * (paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2))] + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 3))]);
        double d2 = 0.7071067811865475D * (paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2))] - paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 3))]);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (m * 4))] = (d2 + paramArrayOfDouble1[(paramInt1 - 1 + m * paramInt1)]);
        paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (2 + m * 4))] = (paramArrayOfDouble1[(paramInt1 - 1 + m * paramInt1)] - d2);
        paramArrayOfDouble2[(paramInt1 * (1 + m * 4))] = (d1 - paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 2))]);
        paramArrayOfDouble2[(paramInt1 * (3 + m * 4))] = (d1 + paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (m + paramInt2 * 2))]);
      }
    }
  }

  void radf5(int paramInt1, int paramInt2, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, int paramInt3)
  {
    int i = paramInt3 + paramInt1;
    int j = i + paramInt1;
    int k = j + paramInt1;
    for (int m = 0; m < paramInt2; m++)
    {
      double d25 = paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 4))] + paramArrayOfDouble1[(paramInt1 * (m + paramInt2))];
      double d26 = paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 4))] - paramArrayOfDouble1[(paramInt1 * (m + paramInt2))];
      double d27 = paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 3))] + paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 2))];
      double d28 = paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 3))] - paramArrayOfDouble1[(paramInt1 * (m + paramInt2 * 2))];
      paramArrayOfDouble2[(paramInt1 * (m * 5))] = (d27 + (d25 + paramArrayOfDouble1[(m * paramInt1)]));
      paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (1 + m * 5))] = (paramArrayOfDouble1[(m * paramInt1)] + 0.309016994374947D * d25 + -0.809016994374947D * d27);
      paramArrayOfDouble2[(paramInt1 * (2 + m * 5))] = (0.951056516295154D * d26 + 0.587785252292473D * d28);
      paramArrayOfDouble2[(paramInt1 - 1 + paramInt1 * (3 + m * 5))] = (paramArrayOfDouble1[(m * paramInt1)] + -0.809016994374947D * d25 + 0.309016994374947D * d27);
      paramArrayOfDouble2[(paramInt1 * (4 + m * 5))] = (0.587785252292473D * d26 - 0.951056516295154D * d28);
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      for (int n = 0; n < paramInt2; n++)
        for (int i1 = 2; i1 < paramInt1; i1 += 2)
        {
          int i2 = paramInt1 - i1;
          double d1 = paramArrayOfDouble3[(paramInt3 + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2))] + paramArrayOfDouble3[(paramInt3 + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2))];
          double d2 = paramArrayOfDouble3[(paramInt3 + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2))] - paramArrayOfDouble3[(paramInt3 + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2))];
          double d3 = paramArrayOfDouble3[(i + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))] + paramArrayOfDouble3[(i + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 2))];
          double d4 = paramArrayOfDouble3[(i + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 2))] - paramArrayOfDouble3[(i + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 2))];
          double d5 = paramArrayOfDouble3[(j + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))] + paramArrayOfDouble3[(j + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 3))];
          double d6 = paramArrayOfDouble3[(j + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 3))] - paramArrayOfDouble3[(j + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 3))];
          double d7 = paramArrayOfDouble3[(k + (i1 - 2))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 4))] + paramArrayOfDouble3[(k + (i1 - 1))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 4))];
          double d8 = paramArrayOfDouble3[(k + (i1 - 2))] * paramArrayOfDouble1[(i1 + paramInt1 * (n + paramInt2 * 4))] - paramArrayOfDouble3[(k + (i1 - 1))] * paramArrayOfDouble1[(i1 - 1 + paramInt1 * (n + paramInt2 * 4))];
          double d9 = d1 + d7;
          double d10 = d7 - d1;
          double d11 = d2 - d8;
          double d12 = d2 + d8;
          double d13 = d3 + d5;
          double d14 = d5 - d3;
          double d15 = d4 - d6;
          double d16 = d4 + d6;
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (n * 5))] = (d13 + (d9 + paramArrayOfDouble1[(i1 - 1 + n * paramInt1)]));
          paramArrayOfDouble2[(i1 + paramInt1 * (n * 5))] = (d16 + (d12 + paramArrayOfDouble1[(i1 + n * paramInt1)]));
          double d17 = paramArrayOfDouble1[(i1 - 1 + n * paramInt1)] + 0.309016994374947D * d9 + -0.809016994374947D * d13;
          double d18 = paramArrayOfDouble1[(i1 + n * paramInt1)] + 0.309016994374947D * d12 + -0.809016994374947D * d16;
          double d19 = paramArrayOfDouble1[(i1 - 1 + n * paramInt1)] + -0.809016994374947D * d9 + 0.309016994374947D * d13;
          double d20 = paramArrayOfDouble1[(i1 + n * paramInt1)] + -0.809016994374947D * d12 + 0.309016994374947D * d16;
          double d21 = 0.951056516295154D * d11 + 0.587785252292473D * d15;
          double d22 = 0.951056516295154D * d10 + 0.587785252292473D * d14;
          double d23 = 0.587785252292473D * d11 - 0.951056516295154D * d15;
          double d24 = 0.587785252292473D * d10 - 0.951056516295154D * d14;
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (2 + n * 5))] = (d17 + d21);
          paramArrayOfDouble2[(i2 - 1 + paramInt1 * (1 + n * 5))] = (d17 - d21);
          paramArrayOfDouble2[(i1 + paramInt1 * (2 + n * 5))] = (d18 + d22);
          paramArrayOfDouble2[(i2 + paramInt1 * (1 + n * 5))] = (d22 - d18);
          paramArrayOfDouble2[(i1 - 1 + paramInt1 * (4 + n * 5))] = (d19 + d23);
          paramArrayOfDouble2[(i2 - 1 + paramInt1 * (3 + n * 5))] = (d19 - d23);
          paramArrayOfDouble2[(i1 + paramInt1 * (4 + n * 5))] = (d20 + d24);
          paramArrayOfDouble2[(i2 + paramInt1 * (3 + n * 5))] = (d24 - d20);
        }
    }
  }

  void radfg(int paramInt1, int paramInt2, int paramInt3, int paramInt4, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, double[] paramArrayOfDouble3, double[] paramArrayOfDouble4, double[] paramArrayOfDouble5, double[] paramArrayOfDouble6, int paramInt5)
  {
    double d1 = 6.283185307179586D / paramInt2;
    double d2 = Math.cos(d1);
    double d3 = Math.sin(d1);
    int i = (paramInt2 + 1) / 2;
    int j = (paramInt1 - 1) / 2;
    if (paramInt1 != 1)
    {
      for (int i32 = 0; i32 < paramInt4; i32++)
        paramArrayOfDouble5[i32] = paramArrayOfDouble3[i32];
      for (int i33 = 1; i33 < paramInt2; i33++)
        for (int i52 = 0; i52 < paramInt3; i52++)
          paramArrayOfDouble4[(paramInt1 * (i52 + i33 * paramInt3))] = paramArrayOfDouble2[(paramInt1 * (i52 + i33 * paramInt3))];
      if (j <= paramInt3)
      {
        int i47 = -paramInt1;
        for (int i48 = 1; i48 < paramInt2; i48++)
        {
          i47 += paramInt1;
          int i49 = i47 - 1;
          for (int i50 = 2; i50 < paramInt1; i50 += 2)
          {
            i49 += 2;
            for (int i51 = 0; i51 < paramInt3; i51++)
            {
              paramArrayOfDouble4[(i50 - 1 + paramInt1 * (i51 + i48 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i49 - 1))] * paramArrayOfDouble2[(i50 - 1 + paramInt1 * (i51 + i48 * paramInt3))] + paramArrayOfDouble6[(i49 + paramInt5)] * paramArrayOfDouble2[(i50 + paramInt1 * (i51 + i48 * paramInt3))]);
              paramArrayOfDouble4[(i50 + paramInt1 * (i51 + i48 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i49 - 1))] * paramArrayOfDouble2[(i50 + paramInt1 * (i51 + i48 * paramInt3))] - paramArrayOfDouble6[(i49 + paramInt5)] * paramArrayOfDouble2[(i50 - 1 + paramInt1 * (i51 + i48 * paramInt3))]);
            }
          }
        }
      }
      int i34 = -paramInt1;
      for (int i35 = 1; i35 < paramInt2; i35++)
      {
        i34 += paramInt1;
        for (int i44 = 0; i44 < paramInt3; i44++)
        {
          int i45 = i34 - 1;
          for (int i46 = 2; i46 < paramInt1; i46 += 2)
          {
            i45 += 2;
            paramArrayOfDouble4[(i46 - 1 + paramInt1 * (i44 + i35 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i45 - 1))] * paramArrayOfDouble2[(i46 - 1 + paramInt1 * (i44 + i35 * paramInt3))] + paramArrayOfDouble6[(i45 + paramInt5)] * paramArrayOfDouble2[(i46 + paramInt1 * (i44 + i35 * paramInt3))]);
            paramArrayOfDouble4[(i46 + paramInt1 * (i44 + i35 * paramInt3))] = (paramArrayOfDouble6[(paramInt5 + (i45 - 1))] * paramArrayOfDouble2[(i46 + paramInt1 * (i44 + i35 * paramInt3))] - paramArrayOfDouble6[(i45 + paramInt5)] * paramArrayOfDouble2[(i46 - 1 + paramInt1 * (i44 + i35 * paramInt3))]);
          }
        }
      }
      if (j >= paramInt3)
        for (int i40 = 1; i40 < i; i40++)
        {
          int i41 = paramInt2 - i40;
          for (int i42 = 0; i42 < paramInt3; i42++)
            for (int i43 = 2; i43 < paramInt1; i43 += 2)
            {
              paramArrayOfDouble4[(i43 - 1 + paramInt1 * (i42 + i40 * paramInt3))] += paramArrayOfDouble4[(i43 - 1 + paramInt1 * (i42 + i41 * paramInt3))];
              paramArrayOfDouble2[(i43 - 1 + paramInt1 * (i42 + i41 * paramInt3))] = (paramArrayOfDouble4[(i43 + paramInt1 * (i42 + i40 * paramInt3))] - paramArrayOfDouble4[(i43 + paramInt1 * (i42 + i41 * paramInt3))]);
              paramArrayOfDouble4[(i43 + paramInt1 * (i42 + i40 * paramInt3))] += paramArrayOfDouble4[(i43 + paramInt1 * (i42 + i41 * paramInt3))];
              paramArrayOfDouble2[(i43 + paramInt1 * (i42 + i41 * paramInt3))] = (paramArrayOfDouble4[(i43 - 1 + paramInt1 * (i42 + i41 * paramInt3))] - paramArrayOfDouble4[(i43 - 1 + paramInt1 * (i42 + i40 * paramInt3))]);
            }
        }
      for (int i36 = 1; i36 < i; i36++)
      {
        int i37 = paramInt2 - i36;
        for (int i38 = 2; i38 < paramInt1; i38 += 2)
          for (int i39 = 0; i39 < paramInt3; i39++)
          {
            paramArrayOfDouble4[(i38 - 1 + paramInt1 * (i39 + i36 * paramInt3))] += paramArrayOfDouble4[(i38 - 1 + paramInt1 * (i39 + i37 * paramInt3))];
            paramArrayOfDouble2[(i38 - 1 + paramInt1 * (i39 + i37 * paramInt3))] = (paramArrayOfDouble4[(i38 + paramInt1 * (i39 + i36 * paramInt3))] - paramArrayOfDouble4[(i38 + paramInt1 * (i39 + i37 * paramInt3))]);
            paramArrayOfDouble4[(i38 + paramInt1 * (i39 + i36 * paramInt3))] += paramArrayOfDouble4[(i38 + paramInt1 * (i39 + i37 * paramInt3))];
            paramArrayOfDouble2[(i38 + paramInt1 * (i39 + i37 * paramInt3))] = (paramArrayOfDouble4[(i38 - 1 + paramInt1 * (i39 + i37 * paramInt3))] - paramArrayOfDouble4[(i38 - 1 + paramInt1 * (i39 + i36 * paramInt3))]);
          }
      }
    }
    for (int k = 0; k < paramInt4; k++)
      paramArrayOfDouble3[k] = paramArrayOfDouble5[k];
    for (int m = 1; m < i; m++)
    {
      int i30 = paramInt2 - m;
      for (int i31 = 0; i31 < paramInt3; i31++)
      {
        paramArrayOfDouble4[(paramInt1 * (i31 + m * paramInt3))] += paramArrayOfDouble4[(paramInt1 * (i31 + i30 * paramInt3))];
        paramArrayOfDouble4[(paramInt1 * (i31 + i30 * paramInt3))] -= paramArrayOfDouble4[(paramInt1 * (i31 + m * paramInt3))];
      }
    }
    double d4 = 1.0D;
    double d5 = 0.0D;
    for (int n = 1; n < i; n++)
    {
      int i23 = paramInt2 - n;
      double d6 = d2 * d4 - d3 * d5;
      d5 = d2 * d5 + d3 * d4;
      d4 = d6;
      for (int i24 = 0; i24 < paramInt4; i24++)
      {
        paramArrayOfDouble5[(i24 + n * paramInt4)] = (paramArrayOfDouble3[i24] + d4 * paramArrayOfDouble3[(i24 + paramInt4)]);
        paramArrayOfDouble5[(i24 + i23 * paramInt4)] = (d5 * paramArrayOfDouble3[(i24 + paramInt4 * (paramInt2 - 1))]);
      }
      double d7 = d4;
      double d8 = d5;
      double d9 = d4;
      double d10 = d5;
      for (int i25 = 2; i25 < i; i25++)
      {
        int i26 = paramInt2 - i25;
        double d11 = d7 * d9 - d8 * d10;
        d10 = d7 * d10 + d8 * d9;
        d9 = d11;
        for (int i27 = 0; i27 < paramInt4; i27++)
        {
          int i28 = i27 + n * paramInt4;
          paramArrayOfDouble5[i28] += d9 * paramArrayOfDouble3[(i27 + i25 * paramInt4)];
          int i29 = i27 + i23 * paramInt4;
          paramArrayOfDouble5[i29] += d10 * paramArrayOfDouble3[(i27 + i26 * paramInt4)];
        }
      }
    }
    for (int i1 = 1; i1 < i; i1++)
      for (int i22 = 0; i22 < paramInt4; i22++)
        paramArrayOfDouble5[i22] += paramArrayOfDouble3[(i22 + i1 * paramInt4)];
    if (paramInt1 >= paramInt3)
      for (int i20 = 0; i20 < paramInt3; i20++)
        for (int i21 = 0; i21 < paramInt1; i21++)
          paramArrayOfDouble1[(i21 + paramInt1 * (i20 * paramInt2))] = paramArrayOfDouble4[(i21 + i20 * paramInt1)];
    for (int i2 = 0; i2 < paramInt1; i2++)
      for (int i19 = 0; i19 < paramInt3; i19++)
        paramArrayOfDouble1[(i2 + paramInt1 * (i19 * paramInt2))] = paramArrayOfDouble4[(i2 + i19 * paramInt1)];
    for (int i3 = 1; i3 < i; i3++)
    {
      int i16 = paramInt2 - i3;
      int i17 = i3 * 2;
      for (int i18 = 0; i18 < paramInt3; i18++)
      {
        paramArrayOfDouble1[(paramInt1 - 1 + paramInt1 * (i17 - 1 + i18 * paramInt2))] = paramArrayOfDouble4[(paramInt1 * (i18 + i3 * paramInt3))];
        paramArrayOfDouble1[(paramInt1 * (i17 + i18 * paramInt2))] = paramArrayOfDouble4[(paramInt1 * (i18 + i16 * paramInt3))];
      }
    }
    if (paramInt1 == 1);
    while (true)
    {
      return;
      if (j >= paramInt3)
      {
        for (int i10 = 1; i10 < i; i10++)
        {
          int i11 = paramInt2 - i10;
          int i12 = i10 * 2;
          for (int i13 = 0; i13 < paramInt3; i13++)
            for (int i14 = 2; i14 < paramInt1; i14 += 2)
            {
              int i15 = paramInt1 - i14;
              paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i13 + i10 * paramInt3))] += paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i13 + i11 * paramInt3))];
              paramArrayOfDouble1[(i15 - 1 + paramInt1 * (i12 - 1 + i13 * paramInt2))] = (paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i13 + i10 * paramInt3))] - paramArrayOfDouble4[(i14 - 1 + paramInt1 * (i13 + i11 * paramInt3))]);
              paramArrayOfDouble4[(i14 + paramInt1 * (i13 + i10 * paramInt3))] += paramArrayOfDouble4[(i14 + paramInt1 * (i13 + i11 * paramInt3))];
              paramArrayOfDouble1[(i15 + paramInt1 * (i12 - 1 + i13 * paramInt2))] = (paramArrayOfDouble4[(i14 + paramInt1 * (i13 + i11 * paramInt3))] - paramArrayOfDouble4[(i14 + paramInt1 * (i13 + i10 * paramInt3))]);
            }
        }
        continue;
      }
      for (int i4 = 1; i4 < i; i4++)
      {
        int i5 = paramInt2 - i4;
        int i6 = i4 * 2;
        for (int i7 = 2; i7 < paramInt1; i7 += 2)
        {
          int i8 = paramInt1 - i7;
          for (int i9 = 0; i9 < paramInt3; i9++)
          {
            paramArrayOfDouble4[(i7 - 1 + paramInt1 * (i9 + i4 * paramInt3))] += paramArrayOfDouble4[(i7 - 1 + paramInt1 * (i9 + i5 * paramInt3))];
            paramArrayOfDouble1[(i8 - 1 + paramInt1 * (i6 - 1 + i9 * paramInt2))] = (paramArrayOfDouble4[(i7 - 1 + paramInt1 * (i9 + i4 * paramInt3))] - paramArrayOfDouble4[(i7 - 1 + paramInt1 * (i9 + i5 * paramInt3))]);
            paramArrayOfDouble4[(i7 + paramInt1 * (i9 + i4 * paramInt3))] += paramArrayOfDouble4[(i7 + paramInt1 * (i9 + i5 * paramInt3))];
            paramArrayOfDouble1[(i8 + paramInt1 * (i6 - 1 + i9 * paramInt2))] = (paramArrayOfDouble4[(i7 + paramInt1 * (i9 + i5 * paramInt3))] - paramArrayOfDouble4[(i7 + paramInt1 * (i9 + i4 * paramInt3))]);
          }
        }
      }
    }
  }

  void rfftb(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt == 1)
      return;
    rfftb1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, 0);
  }

  void rfftb1(int paramInt1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt2)
  {
    double[] arrayOfDouble = new double[paramInt1];
    System.arraycopy(paramArrayOfDouble2, paramInt2, arrayOfDouble, 0, paramInt1);
    int i = (int)paramArrayOfDouble2[(paramInt2 + (1 + paramInt1 * 2))];
    int j = 0;
    int k = 1;
    int m = paramInt1 + paramInt2;
    int n = 1;
    if (n <= i)
    {
      int i2 = (int)paramArrayOfDouble2[(paramInt2 + (n + 1 + paramInt1 * 2))];
      int i3 = i2 * k;
      int i4 = paramInt1 / i3;
      int i5 = i4 * k;
      if (i2 == 4)
        if (j == 0)
        {
          radb4(i4, k, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
          label111: j = 1 - j;
        }
      label375: 
      while (true)
      {
        k = i3;
        m += i4 * (i2 - 1);
        n++;
        break;
        radb4(i4, k, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
        break label111;
        if (i2 == 2)
        {
          if (j == 0)
            radb2(i4, k, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
          while (true)
          {
            j = 1 - j;
            break;
            radb2(i4, k, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          }
        }
        if (i2 == 3)
        {
          if (j == 0)
            radb3(i4, k, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
          while (true)
          {
            j = 1 - j;
            break;
            radb3(i4, k, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          }
        }
        if (i2 == 5)
        {
          if (j == 0)
            radb5(i4, k, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
          while (true)
          {
            j = 1 - j;
            break;
            radb5(i4, k, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          }
        }
        if (j == 0)
          radbg(i4, i2, k, i5, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble1, arrayOfDouble, arrayOfDouble, paramArrayOfDouble2, m);
        while (true)
        {
          if (i4 != 1)
            break label375;
          j = 1 - j;
          break;
          radbg(i4, i2, k, i5, arrayOfDouble, arrayOfDouble, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble2, m);
        }
      }
    }
    if (j == 0);
    while (true)
    {
      return;
      for (int i1 = 0; i1 < paramInt1; i1++)
        paramArrayOfDouble1[i1] = arrayOfDouble[i1];
    }
  }

  void rfftf(int paramInt, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2)
  {
    if (paramInt == 1)
      return;
    rfftf1(paramInt, paramArrayOfDouble1, paramArrayOfDouble2, 0);
  }

  void rfftf1(int paramInt1, double[] paramArrayOfDouble1, double[] paramArrayOfDouble2, int paramInt2)
  {
    double[] arrayOfDouble = new double[paramInt1];
    System.arraycopy(paramArrayOfDouble2, paramInt2, arrayOfDouble, 0, paramInt1);
    int i = (int)paramArrayOfDouble2[(paramInt2 + (1 + paramInt1 * 2))];
    int j = 1;
    int k = paramInt1;
    int m = paramInt2 + (paramInt1 + (paramInt1 - 1));
    int n = 1;
    if (n <= i)
    {
      int i2 = (int)paramArrayOfDouble2[(paramInt2 + (2 + (i - n) + paramInt1 * 2))];
      int i3 = k / i2;
      int i4 = paramInt1 / k;
      int i5 = i4 * i3;
      m -= i4 * (i2 - 1);
      j = 1 - j;
      if (i2 == 4)
        if (j == 0)
          radf4(i4, i3, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
      while (true)
      {
        k = i3;
        n++;
        break;
        radf4(i4, i3, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
        continue;
        if (i2 == 2)
        {
          if (j == 0)
          {
            radf2(i4, i3, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
            continue;
          }
          radf2(i4, i3, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          continue;
        }
        if (i2 == 3)
        {
          if (j == 0)
          {
            radf3(i4, i3, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
            continue;
          }
          radf3(i4, i3, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          continue;
        }
        if (i2 == 5)
        {
          if (j == 0)
          {
            radf5(i4, i3, paramArrayOfDouble1, arrayOfDouble, paramArrayOfDouble2, m);
            continue;
          }
          radf5(i4, i3, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble2, m);
          continue;
        }
        if (i4 == 1)
          j = 1 - j;
        if (j == 0)
        {
          radfg(i4, i2, i3, i5, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble1, arrayOfDouble, arrayOfDouble, paramArrayOfDouble2, m);
          j = 1;
          continue;
        }
        radfg(i4, i2, i3, i5, arrayOfDouble, arrayOfDouble, arrayOfDouble, paramArrayOfDouble1, paramArrayOfDouble1, paramArrayOfDouble2, m);
        j = 0;
      }
    }
    if (j == 1);
    while (true)
    {
      return;
      for (int i1 = 0; i1 < paramInt1; i1++)
        paramArrayOfDouble1[i1] = arrayOfDouble[i1];
    }
  }

  void rffti(int paramInt, double[] paramArrayOfDouble)
  {
    if (paramInt == 1)
      return;
    rffti1(paramInt, paramArrayOfDouble, 0);
  }

  void rffti1(int paramInt1, double[] paramArrayOfDouble, int paramInt2)
  {
    int[] arrayOfInt = { 4, 2, 3, 5 };
    int i = 0;
    int j = paramInt1;
    int k = 0;
    int m = 0;
    m++;
    if (m <= 4)
      i = arrayOfInt[(m - 1)];
    label168: 
    do
    {
      while (true)
      {
        int n = j / i;
        if (j - i * n != 0)
          break;
        k++;
        paramArrayOfDouble[(paramInt2 + (k + 1 + paramInt1 * 2))] = i;
        j = n;
        if ((i != 2) || (k == 1))
          break label168;
        for (int i13 = 2; i13 <= k; i13++)
        {
          int i14 = 2 + (k - i13);
          paramArrayOfDouble[(paramInt2 + (i14 + 1 + paramInt1 * 2))] = paramArrayOfDouble[(paramInt2 + (i14 + paramInt1 * 2))];
        }
        i += 2;
      }
      paramArrayOfDouble[(paramInt2 + (2 + paramInt1 * 2))] = 2.0D;
    }
    while (j != 1);
    paramArrayOfDouble[(paramInt2 + (0 + paramInt1 * 2))] = paramInt1;
    paramArrayOfDouble[(paramInt2 + (1 + paramInt1 * 2))] = k;
    double d1 = 6.283185307179586D / paramInt1;
    int i1 = 0;
    int i2 = k - 1;
    int i3 = 1;
    if (i2 == 0);
    while (true)
    {
      return;
      for (int i4 = 1; i4 <= i2; i4++)
      {
        int i5 = (int)paramArrayOfDouble[(paramInt2 + (i4 + 1 + paramInt1 * 2))];
        int i6 = 0;
        int i7 = i3 * i5;
        int i8 = paramInt1 / i7;
        int i9 = i5 - 1;
        for (int i10 = 1; i10 <= i9; i10++)
        {
          i6 += i3;
          int i11 = i1;
          double d2 = d1 * i6;
          double d3 = 0.0D;
          for (int i12 = 3; i12 <= i8; i12 += 2)
          {
            i11 += 2;
            d3 += 1.0D;
            double d4 = d3 * d2;
            paramArrayOfDouble[(paramInt2 + (paramInt1 + (i11 - 2)))] = Math.cos(d4);
            paramArrayOfDouble[(paramInt2 + (paramInt1 + (i11 - 1)))] = Math.sin(d4);
          }
          i1 += i8;
        }
        i3 = i7;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ca.uol.aig.fftpack.RealDoubleFFT_Mixed
 * JD-Core Version:    0.6.0
 */