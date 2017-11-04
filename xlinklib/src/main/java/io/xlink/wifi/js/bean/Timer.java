package io.xlink.wifi.js.bean;

import java.io.Serializable;
import java.util.ArrayList;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.XlinkUtils;

public class Timer
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private boolean action;
  private byte[] bs;
  private int hour;
  private int id;
  private boolean isExist;
  private boolean isOpen;
  private int mins;
  private ArrayList<Integer> week_int;

  public Timer(int paramInt)
  {
    this.id = paramInt;
    this.isExist = true;
    this.isOpen = true;
  }

  public Timer(byte[] paramArrayOfByte)
  {
    update(paramArrayOfByte);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Timer))
      return getId() == ((Timer)paramObject).getId();
    return super.equals(paramObject);
  }

  public byte[] getBs()
  {
    return this.bs;
  }

  public int getDateSum()
  {
    return 60 * this.hour + this.mins;
  }

  public int getHour()
  {
    return this.hour;
  }

  public int getId()
  {
    return this.id;
  }

  public int getMins()
  {
    return this.mins;
  }

  public String getStringDate()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.hour);
    arrayOfObject[1] = Integer.valueOf(this.mins);
    return String.format("%02d:%02d", arrayOfObject);
  }

  public ArrayList<Integer> getWeek_int()
  {
    return this.week_int;
  }

  public int hashCode()
  {
    return this.id;
  }

  public boolean isAction()
  {
    return this.action;
  }

  public boolean isExist()
  {
    return this.isExist;
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public boolean isRepeat()
  {
    return (this.week_int != null) && (this.week_int.size() > 0);
  }

  public void setAction(boolean paramBoolean)
  {
    this.action = paramBoolean;
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

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setMins(int paramInt)
  {
    this.mins = paramInt;
  }

  public void setOpen(boolean paramBoolean)
  {
    this.isOpen = paramBoolean;
  }

  public void setWeek_int(ArrayList<Integer> paramArrayList)
  {
    this.week_int = paramArrayList;
  }

  public byte[] toBytes()
  {
    if ((this.id < 0) || (this.id >= 20))
      throw new IllegalThreadStateException("定时器id错误");
    this.bs = new byte[7];
    this.bs[0] = 3;
    this.bs[1] = (byte)this.id;
    this.bs[2] = 0;
    if (this.isExist)
      this.bs[2] = XlinkUtils.setByteBit(0, this.bs[2]);
    if (this.isOpen)
      this.bs[2] = XlinkUtils.setByteBit(1, this.bs[2]);
    this.bs[3] = DeviceManage.weekToByte(this.week_int);
    this.bs[4] = (byte)this.hour;
    this.bs[5] = (byte)this.mins;
    if (this.action)
      this.bs[6] = 1;
    else
      this.bs[6] = 0;
    return bs;
  }

  public void update(byte[] paramArrayOfByte)
  {
    int i = 1;
    this.bs = paramArrayOfByte;
    this.id = paramArrayOfByte[0];
    if (this.week_int == null)
      this.week_int = new ArrayList();
    this.week_int.clear();
    setExist(XlinkUtils.readFlagsBit(paramArrayOfByte[i], 0));
    setOpen(XlinkUtils.readFlagsBit(paramArrayOfByte[i], i));
    for (int k = 0; k <= 6; k++)
    {
      if (!XlinkUtils.readFlagsBit(this.bs[2], k))
        continue;
      this.week_int.add(Integer.valueOf(k));
    }
    this.hour = paramArrayOfByte[3];
    this.mins = paramArrayOfByte[4];
    /*if (this.bs[5] != 0);
    while (true)
    {

      return;
      int j = 0;
    }*/
  }
}
