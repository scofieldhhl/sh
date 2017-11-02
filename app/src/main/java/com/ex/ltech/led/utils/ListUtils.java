package com.ex.ltech.led.utils;

import java.util.Collections;
import java.util.List;

public class ListUtils
{
  public static <T extends Comparable<T>> boolean compare(List<T> paramList1, List<T> paramList2)
  {
    if (paramList1.size() != paramList2.size())
      return false;
    Collections.sort(paramList1);
    Collections.sort(paramList2);
    for (int i = 0; i < paramList1.size(); i++)
      if (!((Comparable)paramList1.get(i)).equals(paramList2.get(i)))
        return false;
    return true;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.ListUtils
 * JD-Core Version:    0.6.0
 */