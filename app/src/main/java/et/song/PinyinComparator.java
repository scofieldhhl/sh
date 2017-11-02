package et.song;

import java.util.Comparator;
import java.util.Locale;

public class PinyinComparator
  implements Comparator<AdapterPYinItem>
{
  public int compare(AdapterPYinItem paramAdapterPYinItem1, AdapterPYinItem paramAdapterPYinItem2)
  {
    return paramAdapterPYinItem1.getPyin().toUpperCase(Locale.getDefault()).compareTo(paramAdapterPYinItem2.getPyin().toUpperCase(Locale.getDefault()));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.PinyinComparator
 * JD-Core Version:    0.6.0
 */