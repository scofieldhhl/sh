package com.ex.ltech.remote.control.vo;

public class YkVo
{
  int id;
  boolean isAdd;
  String name;
  int spaceTime = 2;
  String status;
  String type;

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public int getSpaceTime()
  {
    return this.spaceTime;
  }

  public String getStatus()
  {
    return this.status;
  }

  public String getType()
  {
    return this.type;
  }

  public boolean isAdd()
  {
    return this.isAdd;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIsAdd(boolean paramBoolean)
  {
    this.isAdd = paramBoolean;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setSpaceTime(int paramInt)
  {
    this.spaceTime = paramInt;
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
    return "YkVo{id=" + this.id + ", name='" + this.name + '\'' + ", status='" + this.status + '\'' + ", spaceTime=" + this.spaceTime + ", type='" + this.type + '\'' + ", isAdd=" + this.isAdd + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.vo.YkVo
 * JD-Core Version:    0.6.0
 */