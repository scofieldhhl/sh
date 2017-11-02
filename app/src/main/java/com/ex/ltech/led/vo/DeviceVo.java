package com.ex.ltech.led.vo;

import java.io.Serializable;

public class DeviceVo
  implements Serializable
{
  int deviceBgRes;
  String deviceName;
  int id;
  String ip = "192.168.0.1";
  String macAddress;
  int modeRes;
  String name;
  String pwd = "0000";
  String ssid;
  String status;
  String type;

  public int getDeviceBgRes()
  {
    return this.deviceBgRes;
  }

  public String getDeviceName()
  {
    return this.deviceName;
  }

  public int getId()
  {
    return this.id;
  }

  public String getIp()
  {
    return this.ip;
  }

  public String getMacAddress()
  {
    return this.macAddress;
  }

  public int getModeRes()
  {
    return this.modeRes;
  }

  public String getPwd()
  {
    return this.pwd;
  }

  public String getSsid()
  {
    return this.ssid;
  }

  public String getStatus()
  {
    return this.status;
  }

  public String getType()
  {
    return this.type;
  }

  public void setDeviceBgRes(int paramInt)
  {
    this.deviceBgRes = paramInt;
  }

  public void setDeviceName(String paramString)
  {
    this.deviceName = paramString;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIp(String paramString)
  {
    this.ip = paramString;
  }

  public void setMacAddress(String paramString)
  {
    this.macAddress = paramString;
  }

  public void setModeRes(int paramInt)
  {
    this.modeRes = paramInt;
  }

  public void setPwd(String paramString)
  {
    this.pwd = paramString;
  }

  public void setSsid(String paramString)
  {
    this.ssid = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public String toString()
  {
    return "DeviceVo{deviceBgRes=" + this.deviceBgRes + ", id=" + this.id + ", modeRes=" + this.modeRes + ", type='" + this.type + '\'' + ", status='" + this.status + '\'' + ", deviceName='" + this.deviceName + '\'' + ", ip='" + this.ip + '\'' + ", ssid='" + this.ssid + '\'' + ", name='" + this.name + '\'' + ", pwd='" + this.pwd + '\'' + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.DeviceVo
 * JD-Core Version:    0.6.0
 */