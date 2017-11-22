package io.xlink.wifi.js.http.vos;

public class ShareDeviceVo
{
  private int device_id;
  private int expire;
  private String mode;
  private String user;

  public int getDevice_id()
  {
    return this.device_id;
  }

  public int getExpire()
  {
    return this.expire;
  }

  public String getMode()
  {
    return this.mode;
  }

  public String getUser()
  {
    return this.user;
  }

  public void setDevice_id(int paramInt)
  {
    this.device_id = paramInt;
  }

  public void setExpire(int paramInt)
  {
    this.expire = paramInt;
  }

  public void setMode(String paramString)
  {
    this.mode = paramString;
  }

  public void setUser(String paramString)
  {
    this.user = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.vos.ShareDeviceVo
 * JD-Core Version:    0.6.0
 */