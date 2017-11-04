package io.xlink.wifi.js.bean;

@Deprecated
public class TimerSet
{
  private Timer t1;
  private Timer t2;
  private int tid = -1;

  public TimerSet(int paramInt)
  {
    this.tid = paramInt;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof TimerSet))
      return this.tid == ((TimerSet)paramObject).tid;
    return super.equals(paramObject);
  }

  public int getTimerPid()
  {
    return this.tid;
  }

  public int hashCode()
  {
    return this.tid;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.TimerSet
 * JD-Core Version:    0.6.0
 */