package com.ex.ltech.led.vo;

public class DeviceRespVo
{
  private int cmd;
  private Entity0Entity entity0;
  private double version;

  public int getCmd()
  {
    return this.cmd;
  }

  public Entity0Entity getEntity0()
  {
    return this.entity0;
  }

  public double getVersion()
  {
    return this.version;
  }

  public void setCmd(int paramInt)
  {
    this.cmd = paramInt;
  }

  public void setEntity0(Entity0Entity paramEntity0Entity)
  {
    this.entity0 = paramEntity0Entity;
  }

  public void setVersion(double paramDouble)
  {
    this.version = paramDouble;
  }

  public static class Entity0Entity
  {
    private String data;
    private String tt;

    public String getData()
    {
      return this.data;
    }

    public String getTt()
    {
      return this.tt;
    }

    public void setData(String paramString)
    {
      this.data = paramString;
    }

    public void setTt(String paramString)
    {
      this.tt = paramString;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.DeviceRespVo
 * JD-Core Version:    0.6.0
 */