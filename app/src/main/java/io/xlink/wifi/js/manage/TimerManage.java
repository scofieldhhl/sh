package io.xlink.wifi.js.manage;

import android.util.SparseArray;
import io.xlink.wifi.js.bean.TimerSet;
import java.util.HashMap;

@Deprecated
public class TimerManage
{
  private static TimerManage instance;
  private static HashMap<String, SparseArray<TimerSet>> map;

  private TimerManage()
  {
    map = new HashMap();
  }

  public static TimerManage getInstance()
  {
    if (instance == null)
      instance = new TimerManage();
    return instance;
  }

  public byte[] sendGetStatus()
  {
    return new byte[] { 4 };
  }

  public byte[] sendGetTimerPacket()
  {
    return new byte[] { 2 };
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.manage.TimerManage
 * JD-Core Version:    0.6.0
 */