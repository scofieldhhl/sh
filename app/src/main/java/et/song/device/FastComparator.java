package et.song.device;

import java.util.Comparator;

public class FastComparator
  implements Comparator<FastItem>
{
  public int compare(FastItem paramFastItem1, FastItem paramFastItem2)
  {
    if (paramFastItem1.value > paramFastItem2.value)
      return -1;
    if (paramFastItem1.value == paramFastItem2.value)
      return 0;
    return 1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.device.FastComparator
 * JD-Core Version:    0.6.0
 */