package com.ex.ltech.hongwai.vo;

import java.io.Serializable;

public class DiyKey
  implements Serializable
{
  public static final int Custom = 17;
  byte[] icData;
  boolean isEdit;
  byte[] keyCode;
  String name;
  int res;
  boolean showDel;
  int textColor = -1;

  public byte[] getIcData()
  {
    return this.icData;
  }

  public byte[] getKeyCode()
  {
    return this.keyCode;
  }

  public String getName()
  {
    return this.name;
  }

  public int getRes()
  {
    return this.res;
  }

  public int getTextColor()
  {
    return this.textColor;
  }

  public boolean isEdit()
  {
    return this.isEdit;
  }

  public boolean isShowDel()
  {
    return this.showDel;
  }

  public void setEdit(boolean paramBoolean)
  {
    this.isEdit = paramBoolean;
  }

  @Deprecated
  public void setIcData(byte[] paramArrayOfByte)
  {
  }

  public void setKeyCode(byte[] paramArrayOfByte)
  {
    this.keyCode = paramArrayOfByte;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRes(int paramInt)
  {
    this.res = paramInt;
  }

  public void setShowDel(boolean paramBoolean)
  {
    this.showDel = paramBoolean;
  }

  public void setTextColor(int paramInt)
  {
    this.textColor = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.DiyKey
 * JD-Core Version:    0.6.0
 */