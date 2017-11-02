package com.ex.ltech.hongwai.vo;

public class IrSetting
{
  boolean on;
  String version = "v0.0.1";

  public String getVersion()
  {
    return this.version;
  }

  public boolean isOn()
  {
    return this.on;
  }

  public void setOn(boolean paramBoolean)
  {
    this.on = paramBoolean;
  }

  public void setVersion(String paramString)
  {
    this.version = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.IrSetting
 * JD-Core Version:    0.6.0
 */