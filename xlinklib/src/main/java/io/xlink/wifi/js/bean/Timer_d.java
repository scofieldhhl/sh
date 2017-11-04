package io.xlink.wifi.js.bean;

import io.xlink.wifi.js.util.XlinkUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

public class Timer_d
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private int action;
  private byte[] bs;
  private int hour;
  private boolean isExist;
  private boolean isOpen;
  private int mins;
  private int timerCId;
  private int timerPid;
  private ArrayList<Integer> week_int;
  private ArrayList<String> week_string;

  public Timer_d(int paramInt)
  {
    this.timerCId = paramInt;
    this.timerPid = (this.timerCId / 2);
  }

  public Timer_d(byte[] paramArrayOfByte)
  {
    this.timerCId = paramArrayOfByte[0];
    this.week_int = new ArrayList();
    this.week_string = new ArrayList();
    setExist(XlinkUtils.readFlagsBit(paramArrayOfByte[1], 0));
    setOpen(XlinkUtils.readFlagsBit(paramArrayOfByte[1], 1));
    int i = 0;
    int j;
    int k;
    if (i <= 6)
    {
      if (!XlinkUtils.readFlagsBit(paramArrayOfByte[2], i))
        break label179;
      this.week_int.add(Integer.valueOf(i));
      j = i + 1;
      k = i;
      if (k == 7)
        this.week_string.add("星期日");
    }
    while (true)
    {
      i = j + 1;
      break;
      this.week_string.add("星期" + k);
      continue;
      this.hour = paramArrayOfByte[3];
      this.mins = paramArrayOfByte[4];
      this.action = paramArrayOfByte[5];
      this.timerPid = (this.timerCId / 2);
      return;
      label179: j = i;
    }
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Timer_d))
    {
      Timer_d localTimer_d = (Timer_d)paramObject;
      if ((getTimerCId() != 0) && (localTimer_d.getTimerCId() != 0))
        return getTimerCId() == localTimer_d.getTimerCId();
    }
    return super.equals(paramObject);
  }

  public int getAction()
  {
    return this.action;
  }

  public byte[] getBs()
  {
    return this.bs;
  }

  public int getHour()
  {
    return this.hour;
  }

  public int getMins()
  {
    return this.mins;
  }

  public int getTimerCId()
  {
    return this.timerCId;
  }

  public int getTimerDate()
  {
    return 60 * this.hour + this.mins;
  }

  public int getTimerPid()
  {
    return this.timerPid;
  }

  public String getTimerString()
  {
    return this.hour + "：" + this.mins;
  }

  public ArrayList<String> getWeek()
  {
    return this.week_string;
  }

  public int hashCode()
  {
    if (this.timerCId != 0)
      return this.timerCId;
    return super.hashCode();
  }

  public boolean isExist()
  {
    return this.isExist;
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public void setAction(int paramInt)
  {
    this.action = paramInt;
  }

  public void setBs(byte[] paramArrayOfByte)
  {
    this.bs = paramArrayOfByte;
  }

  public void setExist(boolean paramBoolean)
  {
    this.isExist = paramBoolean;
  }

  public void setHour(int paramInt)
  {
    this.hour = paramInt;
  }

  public void setMins(int paramInt)
  {
    this.mins = paramInt;
  }

  public void setOpen(boolean paramBoolean)
  {
    this.isOpen = paramBoolean;
  }

  public void setTimerCId(int paramInt)
  {
    this.timerCId = paramInt;
  }

  public void setTimerPid(int paramInt)
  {
    this.timerPid = paramInt;
  }

  public void setWeek(ArrayList<String> paramArrayList)
  {
    this.week_string = paramArrayList;
  }

  public void setWeekInt(ArrayList<Integer> paramArrayList)
  {
    this.week_int = paramArrayList;
  }

  public byte[] toBytes()
  {
    if ((this.timerCId < 0) || (this.timerCId >= 20))
      throw new IllegalThreadStateException("定时器id错误");
    if (this.bs == null);
    this.bs[0] = 3;
    this.bs[1] = (byte)this.timerCId;
    this.bs[2] = 0;
    if (this.isExist)
      this.bs[2] = XlinkUtils.setByteBit(0, this.bs[2]);
    if (this.isOpen)
      this.bs[2] = XlinkUtils.setByteBit(1, this.bs[2]);
    this.bs[4] = (byte)this.hour;
    this.bs[5] = (byte)this.mins;
    this.bs[6] = (byte)this.action;
    return this.bs;
  }

  public String weekToString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = this.week_string.iterator();
    while (localIterator.hasNext())
      localStringBuffer.append((String)localIterator.next());
    return localStringBuffer.toString();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.Timer_d
 * JD-Core Version:    0.6.0
 */