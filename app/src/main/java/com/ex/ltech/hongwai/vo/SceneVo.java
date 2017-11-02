package com.ex.ltech.hongwai.vo;

import java.util.ArrayList;
import java.util.List;

public class SceneVo
{
  public List<InnerRcVo> innerRcVos = new ArrayList();
  String name = "";
  String picPath = "";
  int picRes;
  String senceIcType = "";

  public List<InnerRcVo> getInnerRcVos()
  {
    return this.innerRcVos;
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

  public String getSenceIcType()
  {
    return this.senceIcType;
  }

  public void setInnerRcVos(List<InnerRcVo> paramList)
  {
    this.innerRcVos = paramList;
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

  public void setSenceIcType(String paramString)
  {
    this.senceIcType = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.SceneVo
 * JD-Core Version:    0.6.0
 */