package com.ex.ltech.led.utils;


import java.util.regex.Pattern;

public class StringUtils
{
  private static String hexString = "0123456789ABCDEF";

  public static String btye2Str(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(hexString.charAt((0xF0 & paramArrayOfByte[i]) >> 4));
      localStringBuilder.append(hexString.charAt((0xF & paramArrayOfByte[i]) >> 0));
    }
    return localStringBuilder.toString();
  }

  public static String btye2Str2(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(hexString.charAt((0xF0 & paramArrayOfByte[i]) >> 4));
      localStringBuilder.append(hexString.charAt((0xF & paramArrayOfByte[i]) >> 0));
      localStringBuilder.append(" ");
    }
    return localStringBuilder.toString();
  }

  public static String btye2Str3(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(hexString.charAt((0xF0 & paramArrayOfByte[i]) >> 4));
      localStringBuilder.append(hexString.charAt((0xF & paramArrayOfByte[i]) >> 0));
      if ((i == 0) || ((i + 1) % 4 != 0))
        continue;
      localStringBuilder.append(" ");
    }
    return localStringBuilder.toString();
  }

  public static String bytesStr2WordStr(String paramString)
  {
    byte[] arrayOfByte = new byte[paramString.length() / 2];
    /*int i = 0;
    while (true)
      if (i < arrayOfByte.length)
      {
        int j = i * 2;
        int k = 2 + i * 2;
        try
        {
          arrayOfByte[i] = (byte)(0xFF & Integer.parseInt(paramString.substring(j, k), 16));
          i++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
    try
    {
      String str = new String(arrayOfByte, "utf-8");
      return str;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }*/
    return paramString;
  }

  public static boolean checkSSID(String paramString)
  {
    return Pattern.compile("^[a-zA-Z0-9\\-]+$").matcher(paramString).find();
  }

  public static String getPinYin(String paramString)
  {
    /*char[] arrayOfChar = paramString.toCharArray();
    new String[arrayOfChar.length];
    HanyuPinyinOutputFormat localHanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
    localHanyuPinyinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
    localHanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
    localHanyuPinyinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
    Object localObject = "";
    int i = arrayOfChar.length;
    for (int j = 0; ; j++)
      if (j < i)
        try
        {
          if (Character.toString(arrayOfChar[j]).matches("[\\u4E00-\\u9FA5]+"))
          {
            String[] arrayOfString = PinyinHelper.toHanyuPinyinStringArray(arrayOfChar[j], localHanyuPinyinOutputFormat);
            localObject = (String)localObject + arrayOfString[0] + "";
            continue;
          }
          String str = (String)localObject + Character.toString(arrayOfChar[j]);
          localObject = str;
        }
        catch (BadHanyuPinyinOutputFormatCombination localBadHanyuPinyinOutputFormatCombination)
        {
          localBadHanyuPinyinOutputFormatCombination.printStackTrace();
        }
      else
        return localObject;*/
    return null;
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
    /*String str1;
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
    }*/
    return null;
  }

  public static boolean isBlank(String paramString)
  {
    /*if ((paramString == null) || ("".equals(paramString)));
    while (true)
    {
      return true;
      for (int i = 0; i < paramString.length(); i++)
      {
        int j = paramString.charAt(i);
        if ((j != 32) && (j != 9) && (j != 13) && (j != 10))
          return false;
      }
    }*/
    return false;
  }

  public static String reverse(String paramString)
  {
    int i = paramString.length();
    String str = "";
    for (int j = 0; j < i; j++)
      str = paramString.charAt(j) + str;
    return str;
  }

  public static String subEndZero(String paramString)
  {
    String str = "";
    for (int i = -1 + paramString.length(); ; i--)
    {
      if (i > -1)
      {
        if ((paramString.charAt(i) + "").equals("0"))
          continue;
        str = paramString.substring(0, i + 1);
      }
      if (str.length() % 2 == 1)
        str = str + "0";
      return str;
    }
  }

  public static String toHexString(byte[] paramArrayOfByte, String paramString)
  {
    String str1 = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str2 = Integer.toHexString(0xFF & paramArrayOfByte[i]);
      if (str2.length() == 1)
        str1 = str1 + "0";
      String str3 = str1 + str2;
      str1 = str3 + paramString;
    }
    return str1;
  }

  public static String toString(byte[] paramArrayOfByte, String paramString)
  {
    String str1 = "";
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      String str2 = String.valueOf((char)paramArrayOfByte[i]);
      String str3 = str1 + str2;
      str1 = str3 + paramString;
    }
    return str1;
  }

  public static String toStringHex1(String paramString)
  {
    /*byte[] arrayOfByte = new byte[paramString.length() / 2];
    int i = 0;
    while (true)
      if (i < arrayOfByte.length)
      {
        int j = i * 2;
        int k = 2 + i * 2;
        try
        {
          arrayOfByte[i] = (byte)(0xFF & Integer.parseInt(paramString.substring(j, k), 16));
          i++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
    try
    {
      String str = new String(arrayOfByte, "utf-8");
      return str;
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }*/
    return paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.StringUtils
 * JD-Core Version:    0.6.0
 */