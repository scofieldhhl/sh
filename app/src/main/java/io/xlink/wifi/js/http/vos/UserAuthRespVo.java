package io.xlink.wifi.js.http.vos;

public class UserAuthRespVo
{
  private String access_token;
  private String authorize;
  private int expire_in;
  private String refresh_token;
  private int user_id;

  public String getAccess_token()
  {
    return this.access_token;
  }

  public String getAuthorize()
  {
    return this.authorize;
  }

  public int getExpire_in()
  {
    return this.expire_in;
  }

  public String getRefresh_token()
  {
    return this.refresh_token;
  }

  public int getUser_id()
  {
    return this.user_id;
  }

  public void setAccess_token(String paramString)
  {
    this.access_token = paramString;
  }

  public void setAuthorize(String paramString)
  {
    this.authorize = paramString;
  }

  public void setExpire_in(int paramInt)
  {
    this.expire_in = paramInt;
  }

  public void setRefresh_token(String paramString)
  {
    this.refresh_token = paramString;
  }

  public void setUser_id(int paramInt)
  {
    this.user_id = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.vos.UserAuthRespVo
 * JD-Core Version:    0.6.0
 */