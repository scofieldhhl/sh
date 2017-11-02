package com.ex.ltech.remote.control;

import java.io.PrintStream;

public class StringSimilarity
{
  public static int editDistance(String paramString1, String paramString2)
  {
    String str1 = paramString1.toLowerCase();
    String str2 = paramString2.toLowerCase();
    int[] arrayOfInt = new int[1 + str2.length()];
    for (int i = 0; i <= str1.length(); i++)
    {
      int j = i;
      int k = 0;
      if (k <= str2.length())
      {
        if (i == 0)
          arrayOfInt[k] = k;
        while (true)
        {
          k++;
          break;
          if (k <= 0)
            continue;
          int m = arrayOfInt[(k - 1)];
          if (str1.charAt(i - 1) != str2.charAt(k - 1))
            m = 1 + Math.min(Math.min(m, j), arrayOfInt[k]);
          arrayOfInt[(k - 1)] = j;
          j = m;
        }
      }
      if (i <= 0)
        continue;
      arrayOfInt[str2.length()] = j;
    }
    return arrayOfInt[str2.length()];
  }

  public static void printSimilarity(String paramString1, String paramString2)
  {
    PrintStream localPrintStream = System.out;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Double.valueOf(similarity(paramString1, paramString2));
    arrayOfObject[1] = paramString1;
    arrayOfObject[2] = paramString2;
    localPrintStream.println(String.format("%.3f is the similarity between \"%s\" and \"%s\"", arrayOfObject));
  }

  public static double similarity(String paramString1, String paramString2)
  {
    String str1 = paramString1;
    String str2 = paramString2;
    if (paramString1.length() < paramString2.length())
    {
      str1 = paramString2;
      str2 = paramString1;
    }
    int i = str1.length();
    if (i == 0)
      return 1.0D;
    return (i - editDistance(str1, str2)) / i;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.StringSimilarity
 * JD-Core Version:    0.6.0
 */