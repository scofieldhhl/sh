package com.ex.ltech.hongwai.vo;

import java.io.Serializable;

public class InnerRcVo
  implements Serializable
{
  byte[] icData;
  int id;
  boolean isAdd;
  int mSaveRcListPosi;
  int mType;
  String name;
  public NonIrDevice nonIrDevice;
  byte[] rcCodes;
  int spaceTime = 3;
  String status;
  String type;

  public byte[] getIcData()
  {
    return this.icData;
  }

  public int getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public byte[] getRcCodes()
  {
    return this.rcCodes;
  }

  public int getSpaceTime()
  {
    return this.spaceTime;
  }

  public String getStatus()
  {
    return this.status;
  }

  public String getTypeStr()
  {
    return this.type;
  }

  public int getmSaveRcListPosi()
  {
    return this.mSaveRcListPosi;
  }

  public int getmType()
  {
    return this.mType;
  }

  public boolean isAdd()
  {
    return this.isAdd;
  }

  public void setIcData(byte[] paramArrayOfByte)
  {
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

  public void setRcCodes(byte[] paramArrayOfByte)
  {
    this.rcCodes = paramArrayOfByte;
  }

  public void setSpaceTime(int paramInt)
  {
    this.spaceTime = paramInt;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setTypeStr(String paramString)
  {
    this.type = paramString;
  }

  public void setmSaveRcListPosi(int paramInt)
  {
    this.mSaveRcListPosi = paramInt;
  }

  public void setmType(int paramInt)
  {
    this.mType = paramInt;
  }

  public String toString()
  {
    return "YkVo{id=" + this.id + ", name='" + this.name + '\'' + ", status='" + this.status + '\'' + ", spaceTime=" + this.spaceTime + ", type='" + this.type + '\'' + ", isAdd=" + this.isAdd + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.InnerRcVo
 * JD-Core Version:    0.6.0
 */