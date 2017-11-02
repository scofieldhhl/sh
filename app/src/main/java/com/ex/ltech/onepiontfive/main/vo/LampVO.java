package com.ex.ltech.onepiontfive.main.vo;

public class LampVO
{
  private boolean isConnect;
  private String lampConnect;
  private String lampName;

  public String getLampConnect()
  {
    return this.lampConnect;
  }

  public String getLampName()
  {
    return this.lampName;
  }

  public boolean isConnect()
  {
    return this.isConnect;
  }

  public void setIsConnect(boolean paramBoolean)
  {
    this.isConnect = paramBoolean;
  }

  public void setLampConnect(String paramString)
  {
    this.lampConnect = paramString;
  }

  public void setLampName(String paramString)
  {
    this.lampName = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.LampVO
 * JD-Core Version:    0.6.0
 */