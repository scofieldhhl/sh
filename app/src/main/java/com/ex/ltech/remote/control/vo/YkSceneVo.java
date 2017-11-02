package com.ex.ltech.remote.control.vo;

import java.util.List;

public class YkSceneVo
{
  int bgCol;
  boolean isEdit;
  String name = "";
  String picPath = "";
  int picRes;
  List<YkVo> yks;

  public int getBgCol()
  {
    return this.bgCol;
  }

  public String getName()
  {
    return this.name;
  }

  public String getPicPath()
  {
    return this.picPath;
  }

  public int getPicRes()
  {
    return this.picRes;
  }

  public List<YkVo> getYks()
  {
    return this.yks;
  }

  public boolean isEdit()
  {
    return this.isEdit;
  }

  public void setBgCol(int paramInt)
  {
    this.bgCol = paramInt;
  }

  public void setIsEdit(boolean paramBoolean)
  {
    this.isEdit = paramBoolean;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setPicPath(String paramString)
  {
    this.picPath = paramString;
  }

  public void setPicRes(int paramInt)
  {
    this.picRes = paramInt;
  }

  public void setYks(List<YkVo> paramList)
  {
    this.yks = paramList;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.vo.YkSceneVo
 * JD-Core Version:    0.6.0
 */