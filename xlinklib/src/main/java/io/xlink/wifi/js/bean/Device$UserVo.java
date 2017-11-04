package io.xlink.wifi.js.bean;

import java.util.List;

public class Device$UserVo
{
  private String count;
  private List<ListEntity> list;

  public String getCount()
  {
    return this.count;
  }

  public List<ListEntity> getList()
  {
    return this.list;
  }

  public void setCount(String paramString)
  {
    this.count = paramString;
  }

  public void setList(List<ListEntity> paramList)
  {
    this.list = paramList;
  }

  public static class ListEntity
  {
    private String from_id;
    private String nickname;
    private String role;
    private String user_id;

    public String getFrom_id()
    {
      return this.from_id;
    }

    public String getNickname()
    {
      return this.nickname;
    }

    public String getRole()
    {
      return this.role;
    }

    public String getUser_id()
    {
      return this.user_id;
    }

    public void setFrom_id(String paramString)
    {
      this.from_id = paramString;
    }

    public void setNickname(String paramString)
    {
      this.nickname = paramString;
    }

    public void setRole(String paramString)
    {
      this.role = paramString;
    }

    public void setUser_id(String paramString)
    {
      this.user_id = paramString;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.Device.UserVo
 * JD-Core Version:    0.6.0
 */