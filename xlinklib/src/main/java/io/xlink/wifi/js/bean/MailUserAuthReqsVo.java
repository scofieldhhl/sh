package io.xlink.wifi.js.bean;

public class MailUserAuthReqsVo
{
  private String corp_id;
  private String email;
  private String password;

  public String getCorp_id()
  {
    return this.corp_id;
  }

  public String getPassword()
  {
    return this.password;
  }

  public String getPhone()
  {
    return this.email;
  }

  public void setCorp_id(String paramString)
  {
    this.corp_id = paramString;
  }

  public void setPassword(String paramString)
  {
    this.password = paramString;
  }

  public void setPhone(String paramString)
  {
    this.email = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.MailUserAuthReqsVo
 * JD-Core Version:    0.6.0
 */