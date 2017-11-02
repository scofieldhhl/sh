package com.ex.ltech.remote.control;

import java.io.PrintStream;
import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.Locale;

public class Similarity
{
  public static double SimilarDegree(String paramString1, String paramString2)
  {
    String str1 = removeSign(paramString1);
    String str2 = removeSign(paramString2);
    int i = Math.max(str1.length(), str2.length());
    return 1.0D * longestCommonSubstring(str1, str2).length() / i;
  }

  private static boolean charReg(char paramChar)
  {
    return ((paramChar >= '一') && (paramChar <= 40869)) || ((paramChar >= 'a') && (paramChar <= 'z')) || ((paramChar >= 'A') && (paramChar <= 'Z')) || ((paramChar >= '0') && (paramChar <= '9'));
  }

  private static String longestCommonSubstring(String paramString1, String paramString2)
  {
    char[] arrayOfChar1 = paramString1.toCharArray();
    char[] arrayOfChar2 = paramString2.toCharArray();
    int i = arrayOfChar1.length;
    int j = arrayOfChar2.length;
    int[] arrayOfInt = { i + 1, j + 1 };
    int[][] arrayOfInt1 = (int[][])Array.newInstance(Integer.TYPE, arrayOfInt);
    for (int k = 1; k <= i; k++)
    {
      int n = 1;
      if (n > j)
        continue;
      if (arrayOfChar1[(k - 1)] == arrayOfChar2[(n - 1)])
        arrayOfInt1[k][n] = (1 + arrayOfInt1[(k - 1)][(n - 1)]);
      while (true)
      {
        n++;
        break;
        arrayOfInt1[k][n] = Math.max(arrayOfInt1[k][(n - 1)], arrayOfInt1[(k - 1)][n]);
      }
    }
    char[] arrayOfChar3 = new char[arrayOfInt1[i][j]];
    int m = -1 + arrayOfChar3.length;
    while (arrayOfInt1[i][j] != 0)
    {
      if (arrayOfInt1[j] == arrayOfInt1[(j - 1)])
      {
        j--;
        continue;
      }
      if (arrayOfInt1[i][j] == arrayOfInt1[(i - 1)][j])
      {
        i--;
        continue;
      }
      arrayOfChar3[m] = arrayOfChar1[(i - 1)];
      m--;
      j--;
      i--;
    }
    return new String(arrayOfChar3);
  }

  public static void main(String[] paramArrayOfString)
  {
    double d = SimilarDegree("我是中国人", "你是中国人");
    if (d >= 0.7D)
      System.out.println("相似度很高！" + similarityResult(d) + d);
    while (true)
    {
      System.out.println();
      return;
      System.out.println("相似度不高" + similarityResult(d) + d);
    }
  }

  private static String removeSign(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    for (char c : paramString.toCharArray())
    {
      if (!charReg(c))
        continue;
      localStringBuffer.append(c);
    }
    return localStringBuffer.toString();
  }

  public static String similarityResult(double paramDouble)
  {
    return NumberFormat.getPercentInstance(new Locale("en ", "US ")).format(paramDouble);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.Similarity
 * JD-Core Version:    0.6.0
 */