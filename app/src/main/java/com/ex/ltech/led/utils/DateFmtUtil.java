package com.ex.ltech.led.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFmtUtil
{
  public static String dateDiff(Date paramDate1, Date paramDate2, String[] paramArrayOfString)
  {
    long l1 = (paramDate1.getTime() - paramDate2.getTime()) / 1000L;
    if (l1 < 60L)
      return l1 + paramArrayOfString[0];
    long l2 = l1 / 60L;
    if (l2 < 60L)
      return l2 + paramArrayOfString[1];
    long l3 = l2 / 60L;
    if (l3 < 24L)
      return l3 + paramArrayOfString[2];
    long l4 = l3 / 24L;
    if (l4 < 30L)
      return l4 + paramArrayOfString[3];
    long l5 = l4 / 30L;
    if (l5 < 12L)
      return l5 + paramArrayOfString[4];
    return l5 + paramArrayOfString[5];
  }

  public static String formatTime(long paramLong)
  {
    int i = 1000 * 60;
    int j = i * 60;
    int k = j * 24;
    long l1 = paramLong / k;
    long l2 = (paramLong - l1 * k) / j;
    long l3 = (paramLong - l1 * k - l2 * j) / i;
    long l4 = (paramLong - l1 * k - l2 * j - l3 * i) / 1000;
    long l5 = paramLong - l1 * k - l2 * j - l3 * i - l4 * 1000;
    String str1;
    label175: String str2;
    label206: label236: String str3;
    if (l1 < 10L)
    {
      new StringBuilder().append("0").append(l1).toString();
      if (l2 >= 10L)
        break label352;
      str1 = "0" + l2;
      if (l3 >= 10L)
        break label377;
      str2 = "0" + l3;
      if (l4 >= 10L)
        break label402;
      new StringBuilder().append("0").append(l4).toString();
      if (l5 >= 10L)
        break label426;
      str3 = "0" + l5;
      label267: if (l5 >= 100L)
        break label451;
      new StringBuilder().append("0").append(str3).toString();
    }
    while (true)
    {
      if (!str1.equals("00"))
        break label475;
      return str2 + "分钟后";
      new StringBuilder().append("").append(l1).toString();
      break;
      label352: str1 = "" + l2;
      break label175;
      label377: str2 = "" + l3;
      break label206;
      label402: new StringBuilder().append("").append(l4).toString();
      break label236;
      label426: str3 = "" + l5;
      break label267;
      label451: new StringBuilder().append("").append(str3).toString();
    }
    label475: return str1 + "小时" + str2 + "分钟后";
  }

  public static String getDD()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("dd").format(localDate);
  }

  public static int getDayOfMonth()
  {
    return Calendar.getInstance().get(5);
  }

  public static String getHH()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("HH").format(localDate);
  }

  public static String getHHMM(long paramLong)
  {
    return new SimpleDateFormat("HH:mm").format(Long.valueOf(paramLong));
  }

  public static int getHourOfDay()
  {
    return Calendar.getInstance().get(11);
  }

  public static int getLastDayOfMonth()
  {
    return Calendar.getInstance().getActualMaximum(5);
  }

  public static String getMMSS(long paramLong)
  {
    return new SimpleDateFormat("mm:ss").format(Long.valueOf(paramLong));
  }

  public static String getMin()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("mm").format(localDate);
  }

  public static String getMonthNow()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("MM").format(localDate);
  }

  public static String getSS()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("ss").format(localDate);
  }

  public static String getSSS()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("SSS").format(localDate);
  }

  public static long getTime4HHmm(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat localSimpleDateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    try
    {
      long l = localSimpleDateFormat2.parse(localSimpleDateFormat1.format(new Date()).toString() + " " + paramString).getTime();
      return l;
    }
    catch (ParseException localParseException)
    {
      localParseException.printStackTrace();
    }
    return -1L;
  }

  public static String getTimestampForDate(String paramString)
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    try
    {
      Date localDate = localSimpleDateFormat.parse(paramString);
      return localDate.getTime() + "";
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public static int getWeekOfDate()
  {
    int[] arrayOfInt = { 7, 1, 2, 3, 4, 5, 6 };
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date());
    int i = -1 + localCalendar.get(7);
    if (i < 0)
      i = 0;
    return arrayOfInt[i];
  }

  public static String getYY()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yy").format(localDate);
  }

  public static String getYYMMDDHHMMSS(long paramLong)
  {
    return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Long.valueOf(paramLong));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.DateFmtUtil
 * JD-Core Version:    0.6.0
 */