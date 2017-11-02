package com.ex.ltech.util;

import android.text.TextUtils;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil
{
  public static String byte2Hexstr(byte[] paramArrayOfByte)
  {
    StringBuilder localStringBuilder = new StringBuilder(2 * paramArrayOfByte.length);
    for (int i = 0; i < paramArrayOfByte.length; i++)
    {
      localStringBuilder.append(getHexChar(0xF & paramArrayOfByte[i] >> 4));
      localStringBuilder.append(getHexChar(0xF & paramArrayOfByte[i]));
    }
    return localStringBuilder.toString();
  }

  public static String capitalizeFirstLetter(String paramString)
  {
    if (isNull(paramString));
    char c;
    do
    {
      return paramString;
      c = paramString.charAt(0);
    }
    while ((!Character.isLetter(c)) || (Character.isUpperCase(c)));
    return paramString.length() + Character.toUpperCase(c) + paramString.substring(1);
  }

  public static boolean equals(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2 == null;
    return paramString1.equals(paramString2);
  }

  public static boolean equalsIgnoreCase(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2 == null;
    return paramString1.equalsIgnoreCase(paramString2);
  }

  public static String fullWidthToHalfWidth(String paramString)
  {
    if (isNull(paramString))
      return paramString;
    char[] arrayOfChar = paramString.toCharArray();
    int i = 0;
    if (i < arrayOfChar.length)
    {
      if (arrayOfChar[i] == '　')
        arrayOfChar[i] = ' ';
      while (true)
      {
        i++;
        break;
        if ((arrayOfChar[i] >= 65281) && (arrayOfChar[i] <= 65374))
        {
          arrayOfChar[i] = (char)(arrayOfChar[i] - 65248);
          continue;
        }
        arrayOfChar[i] = arrayOfChar[i];
      }
    }
    return new String(arrayOfChar);
  }

  public static String getBuildString(String paramString1, String paramString2)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    LinkedHashSet localLinkedHashSet = getName(paramString1, paramString2);
    if (localLinkedHashSet.size() > 0)
    {
      Iterator localIterator = localLinkedHashSet.iterator();
      while (localIterator.hasNext())
        localStringBuffer.append((String)localIterator.next() + ",");
      System.out.println("获得的上传的remotebuffer:" + localStringBuffer.toString());
      String str = localStringBuffer.toString().substring(0, localStringBuffer.toString().lastIndexOf(","));
      System.out.println("获得的上传的remote:" + str);
      return str;
    }
    return null;
  }

  private static char getHexChar(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < 10))
      return (char)(paramInt + 48);
    return (char)(65 + (paramInt - 10));
  }

  public static String getHrefInnerHtml(String paramString)
  {
    if (isNull(paramString))
      paramString = "";
    Matcher localMatcher;
    do
    {
      return paramString;
      localMatcher = Pattern.compile(".*<[\\s]*a[\\s]*.*>(.+?)<[\\s]*/a[\\s]*>.*", 2).matcher(paramString);
    }
    while (!localMatcher.matches());
    return localMatcher.group(1);
  }

  public static LinkedHashSet<String> getName(String paramString1, String paramString2)
  {
    LinkedHashSet localLinkedHashSet1 = new LinkedHashSet();
    LinkedHashSet localLinkedHashSet2 = new LinkedHashSet();
    for (int i = 0; i < paramString2.split(",").length; i++)
      localLinkedHashSet1.add(paramString2.split(",")[i]);
    for (int j = 0; j < paramString1.split(",").length; j++)
      localLinkedHashSet2.add(paramString1.split(",")[j]);
    localLinkedHashSet1.removeAll(localLinkedHashSet2);
    return localLinkedHashSet1;
  }

  public static String halfWidthToFullWidth(String paramString)
  {
    if (isNull(paramString))
      return paramString;
    char[] arrayOfChar = paramString.toCharArray();
    int i = 0;
    if (i < arrayOfChar.length)
    {
      if (arrayOfChar[i] == ' ')
        arrayOfChar[i] = '　';
      while (true)
      {
        i++;
        break;
        if ((arrayOfChar[i] >= '!') && (arrayOfChar[i] <= '~'))
        {
          arrayOfChar[i] = (char)(65248 + arrayOfChar[i]);
          continue;
        }
        arrayOfChar[i] = arrayOfChar[i];
      }
    }
    return new String(arrayOfChar);
  }

  public static byte[] hex2Bytes(String paramString)
  {
    String str = paramString.replaceAll("[^0-9,a-f,A-F]", "");
    byte[] arrayOfByte = new byte[str.length() / 2];
    for (int i = 0; i < arrayOfByte.length; i++)
      arrayOfByte[i] = (byte)Integer.parseInt(str.substring(i * 2, 2 + i * 2), 16);
    return arrayOfByte;
  }

  public static String htmlEscapeCharsToString(String paramString)
  {
    if (isNull(paramString))
      return paramString;
    return paramString.replaceAll("&lt;", "<").replaceAll("&gt;", ">").replaceAll("&amp;", "&").replaceAll("&quot;", "\"");
  }

  public static boolean isBlank(String paramString)
  {
    return (paramString == null) || (paramString.trim().length() == 0);
  }

  public static boolean isNull(String paramString)
  {
    return (paramString == null) || (paramString.length() == 0) || ("null".equals(paramString));
  }

  public static boolean isNumeric(String paramString)
  {
    return Pattern.compile("[0-9]*").matcher(paramString).matches();
  }

  public static final String md5(String paramString)
  {
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      StringBuffer localStringBuffer = new StringBuffer();
      for (int i = 0; i < arrayOfByte2.length; i++)
      {
        int j = 0xFF & arrayOfByte2[i];
        if (j < 16)
          localStringBuffer.append("0");
        localStringBuffer.append(Integer.toHexString(j));
      }
      String str = localStringBuffer.toString();
      return str;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static String nullStrToEmpty(String paramString)
  {
    if (paramString == null)
      paramString = "";
    return paramString;
  }

  public static String padStart(String paramString, int paramInt, char paramChar)
  {
    if (paramString.length() >= paramInt)
      return paramString;
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    for (int i = paramString.length(); i < paramInt; i++)
      localStringBuilder.append(paramChar);
    localStringBuilder.append(paramString);
    return localStringBuilder.toString();
  }

  public static int parseInt(String paramString)
  {
    try
    {
      int i = Integer.parseInt(paramString);
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0;
  }

  public static int[] parseIntArray(String paramString)
  {
    return parseIntArray(paramString, ",");
  }

  public static int[] parseIntArray(String paramString1, String paramString2)
  {
    int[] arrayOfInt;
    if (TextUtils.isEmpty(paramString1))
      arrayOfInt = null;
    while (true)
    {
      return arrayOfInt;
      String[] arrayOfString = paramString1.split(paramString2);
      arrayOfInt = new int[arrayOfString.length];
      for (int i = 0; i < arrayOfString.length; i++)
        arrayOfInt[i] = parseInt(arrayOfString[i]);
    }
  }

  public static long parseLong(String paramString)
  {
    try
    {
      long l = Long.parseLong(paramString);
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 0L;
  }

  public static String[] parseStringArray(String paramString1, String paramString2)
  {
    if (TextUtils.isEmpty(paramString1))
      return null;
    return paramString1.split(paramString2);
  }

  public static String replaceBlank(String paramString)
  {
    String str = "";
    if (paramString != null)
      str = Pattern.compile("\\s*|\t|\r|\n").matcher(paramString).replaceAll("");
    return str;
  }

  public static String utf8Decode(String paramString)
  {
    if (!isNull(paramString));
    try
    {
      String str = URLDecoder.decode(paramString, "UTF-8");
      paramString = str;
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UnsupportedEncodingException occurred. ", localUnsupportedEncodingException);
  }

  public static String utf8Encode(String paramString)
  {
    if ((!isNull(paramString)) && (paramString.getBytes().length != paramString.length()));
    try
    {
      String str = URLEncoder.encode(paramString, "UTF-8");
      paramString = str;
      return paramString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new RuntimeException("UnsupportedEncodingException occurred. ", localUnsupportedEncodingException);
  }

  public static String utf8Encode(String paramString1, String paramString2)
  {
    if ((!isNull(paramString1)) && (paramString1.getBytes().length != paramString1.length()))
      try
      {
        String str = URLEncoder.encode(paramString1, "UTF-8");
        return str;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        return paramString2;
      }
    return paramString1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.util.StringUtil
 * JD-Core Version:    0.6.0
 */