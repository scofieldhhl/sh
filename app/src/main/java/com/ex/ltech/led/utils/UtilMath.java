package com.ex.ltech.led.utils;

public class UtilMath
{
  public static int[] checkBit(byte paramByte)
  {
    int[] arrayOfInt = new int[8];
    byte b = 1;
    int i = 0;
    if (i < 8)
    {
      if ((paramByte & b) == 0)
        arrayOfInt[i] = 0;
      while (true)
      {
        b = (byte)(b << 1);
        i++;
        break;
        arrayOfInt[i] = 1;
      }
    }
    return arrayOfInt;
  }

  public static int fact(int paramInt1, int paramInt2)
  {
    int i = 1;
    for (int j = 0; j < paramInt2; j++)
      i *= paramInt1;
    return i;
  }

  public static int getInt(byte paramByte)
  {
    int[] arrayOfInt = checkBit(paramByte);
    int i = 0;
    for (int j = 0; j < 8; j++)
      i += arrayOfInt[j] * fact(2, j);
    return i;
  }

  public static int getInt(int[] paramArrayOfInt)
  {
    int i = 0;
    for (int j = 0; j < paramArrayOfInt.length; j++)
    {
      if (paramArrayOfInt[j] != 1)
        continue;
      i += fact(2, j);
    }
    return i;
  }

  public static String hexString2String(String paramString)
  {
    String str = "";
    for (int i = 0; i < paramString.length() / 2; i++)
      str = str + (char)Integer.valueOf(paramString.substring(i * 2, 2 + i * 2), 16).byteValue();
    return str;
  }

  public static String hexString2binaryString(String paramString)
  {
    String str1;
    if ((paramString == null) || (paramString.length() % 2 != 0))
      str1 = null;
    while (true)
    {
      return str1;
      str1 = "";
      for (int i = 0; i < paramString.length(); i++)
      {
        String str2 = "0000" + Integer.toBinaryString(Integer.parseInt(paramString.substring(i, i + 1), 16));
        str1 = str1 + str2.substring(-4 + str2.length());
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.UtilMath
 * JD-Core Version:    0.6.0
 */