package com.ex.ltech.led;

public class MyBean
{
  private int errNum;
  private RetDataEntity retData;
  private String retMsg;

  public int getErrNum()
  {
    return this.errNum;
  }

  public RetDataEntity getRetData()
  {
    return this.retData;
  }

  public String getRetMsg()
  {
    return this.retMsg;
  }

  public void setErrNum(int paramInt)
  {
    this.errNum = paramInt;
  }

  public void setRetData(RetDataEntity paramRetDataEntity)
  {
    this.retData = paramRetDataEntity;
  }

  public void setRetMsg(String paramString)
  {
    this.retMsg = paramString;
  }

  public static class RetDataEntity
  {
    private String cityCode;
    private String cityName;
    private String provinceName;
    private String telAreaCode;
    private String zipCode;

    public String getCityCode()
    {
      return this.cityCode;
    }

    public String getCityName()
    {
      return this.cityName;
    }

    public String getProvinceName()
    {
      return this.provinceName;
    }

    public String getTelAreaCode()
    {
      return this.telAreaCode;
    }

    public String getZipCode()
    {
      return this.zipCode;
    }

    public void setCityCode(String paramString)
    {
      this.cityCode = paramString;
    }

    public void setCityName(String paramString)
    {
      this.cityName = paramString;
    }

    public void setProvinceName(String paramString)
    {
      this.provinceName = paramString;
    }

    public void setTelAreaCode(String paramString)
    {
      this.telAreaCode = paramString;
    }

    public void setZipCode(String paramString)
    {
      this.zipCode = paramString;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.MyBean
 * JD-Core Version:    0.6.0
 */