package et.song.tool;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class ETDateTool
{
  public static int getDay()
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat("dd", Locale.getDefault()).format(localDate));
  }

  public static int getDays(int paramInt1, int paramInt2)
  {
    int i = 28;
    if (isLeap(paramInt1))
      i = 29;
    switch (paramInt2)
    {
    default:
      return 0;
    case 1:
    case 3:
    case 5:
    case 7:
    case 8:
    case 10:
    case 12:
      return 31;
    case 4:
    case 6:
    case 9:
    case 11:
      return 30;
    case 2:
    }
    return i;
  }

  public static int getHour()
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat("hh", Locale.getDefault()).format(localDate));
  }

  public static int getMin()
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat("mm", Locale.getDefault()).format(localDate));
  }

  public static int getMonth()
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat("MM", Locale.getDefault()).format(localDate));
  }

  public static String getNowDate()
  {
    Date localDate = new Date();
    return new SimpleDateFormat("yyy-MM-dd", Locale.getDefault()).format(localDate);
  }

  public static int getSundays(int paramInt1, int paramInt2)
  {
    int i = 0;
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEEE", Locale.getDefault());
    Calendar localCalendar = Calendar.getInstance();
    for (int j = 1; ; j++)
    {
      if (j > getDays(paramInt1, paramInt2))
        return i;
      localCalendar.set(5, j);
      if (!localSimpleDateFormat.format(localCalendar.getTime()).equals("星期日"))
        continue;
      i++;
    }
  }

  public static int getYear()
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat("yyyy", Locale.getDefault()).format(localDate));
  }

  public static boolean isLeap(int paramInt)
  {
    return ((paramInt % 100 == 0) && (paramInt % 400 == 0)) || ((paramInt % 100 != 0) && (paramInt % 4 == 0));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETDateTool
 * JD-Core Version:    0.6.0
 */