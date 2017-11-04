package io.xlink.wifi.js.bean;

import io.xlink.wifi.sdk.XDevice;
import java.io.Serializable;
import java.util.ArrayList;

public class Device
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  boolean isOnline = false;
  boolean isShowOffline;
  boolean isShowOnline;
  private String password;
  private int state = -1;
  private boolean switch_;
  private short th;
  private ArrayList<Timer> timers;
  private Timer timing;
  private int wind;
  private XDevice xDevice;

  public Device(XDevice paramXDevice)
  {
    this.xDevice = paramXDevice;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof Device))
    {
      Device localDevice = (Device)paramObject;
      return this.xDevice.equals(localDevice.getXDevice());
    }
    if ((paramObject instanceof XDevice))
      return this.xDevice.equals(paramObject);
    return super.equals(paramObject);
  }

  public String getMacAddress()
  {
    try
    {
      String str = this.xDevice.getMacAddress();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return "";
  }

  public String getName()
  {
    return this.xDevice.getDeviceName();
  }

  public String getPassword()
  {
    return this.password;
  }

  public int getState()
  {
    return this.state;
  }

  public short getTh()
  {
    return this.th;
  }

  public ArrayList<Timer> getTimers()
  {
    if (this.timers == null)
      this.timers = new ArrayList();
    return this.timers;
  }

  public Timer getTiming()
  {
    return this.timing;
  }

  public int getWind()
  {
    return this.wind;
  }

  public XDevice getXDevice()
  {
    return this.xDevice;
  }

  public boolean isConnect()
  {
    return this.xDevice.getDevcieConnectStates() == -1;
  }

  public boolean isOnline()
  {
    return this.isOnline;
  }

  public boolean isShowOffline()
  {
    return this.isShowOffline;
  }

  public boolean isShowOnline()
  {
    return this.isShowOnline;
  }

  public boolean isSwitch_()
  {
    return this.switch_;
  }

  public void setIsOnline(boolean paramBoolean)
  {
    this.isOnline = paramBoolean;
  }

  public void setIsShowOffline(boolean paramBoolean)
  {
    this.isShowOffline = paramBoolean;
  }

  public void setIsShowOnline(boolean paramBoolean)
  {
    this.isShowOnline = paramBoolean;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setState(int paramInt)
  {
    this.state = paramInt;
  }

  public void setSwitch_(boolean paramBoolean)
  {
    this.switch_ = paramBoolean;
  }

  public void setTh(short paramShort)
  {
    this.th = paramShort;
  }

  public void setTimers(ArrayList<Timer> paramArrayList)
  {
    this.timers = paramArrayList;
  }

  public void setTiming(Timer paramTimer)
  {
    this.timing = paramTimer;
  }

  public void setWind(int paramInt)
  {
    this.wind = paramInt;
  }

  public void setxDevice(XDevice paramXDevice)
  {
    this.xDevice = paramXDevice;
  }

  public boolean timingIsOpen()
  {
    return (this.timing != null) && (this.timing.isOpen()) && (this.timing.isExist()) && (!this.timing.isRepeat());
  }

  public String toString()
  {
    return this.xDevice.toString() + " pwd:" + this.password;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.Device
 * JD-Core Version:    0.6.0
 */