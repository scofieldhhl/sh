package com.ex.ltech.remote.control.vo;

import java.util.List;

public class YaoKongVo
{
  public static final int addBtnType = 2;
  public static final int insideModeType = 1;
  public static final int newCreateModeType = 3;
  private int brt;
  private int colorCount;
  private List<Integer> colors;
  boolean isAddBtn;
  private boolean isNewCreateMode;
  int ivLeftRes;
  int ivNumRes;
  int ivSeletedRes;
  private String newCreateModeBitmapPath;
  private String newCreateModeName;
  boolean seleted;
  private boolean singleSeleted;
  private int speed;
  private int transformation;
  String tvName;
  private int type = 1;
  private int w;

  public int getBrt()
  {
    return this.brt;
  }

  public int getColorCount()
  {
    return this.colorCount;
  }

  public List<Integer> getColors()
  {
    return this.colors;
  }

  public int getIvLeftRes()
  {
    return this.ivLeftRes;
  }

  public int getIvNumRes()
  {
    return this.ivNumRes;
  }

  public int getIvSeletedRes()
  {
    return this.ivSeletedRes;
  }

  public String getNewCreateModeBitmapPath()
  {
    return this.newCreateModeBitmapPath;
  }

  public String getNewCreateModeName()
  {
    return this.newCreateModeName;
  }

  public int getSpeed()
  {
    return this.speed;
  }

  public int getTransformation()
  {
    return this.transformation;
  }

  public String getTvName()
  {
    return this.tvName;
  }

  public int getType()
  {
    return this.type;
  }

  public int getW()
  {
    return this.w;
  }

  public boolean isAddBtn()
  {
    return this.isAddBtn;
  }

  public boolean isNewCreateMode()
  {
    return this.isNewCreateMode;
  }

  public boolean isSeleted()
  {
    return this.seleted;
  }

  public boolean isSingleSeleted()
  {
    return this.singleSeleted;
  }

  public void setBrt(int paramInt)
  {
    this.brt = paramInt;
  }

  public void setColorCount(int paramInt)
  {
    this.colorCount = paramInt;
  }

  public void setColors(List<Integer> paramList)
  {
    this.colors = paramList;
  }

  public void setIsAddBtn(boolean paramBoolean)
  {
    this.isAddBtn = paramBoolean;
  }

  public void setIsNewCreateMode(boolean paramBoolean)
  {
    this.isNewCreateMode = paramBoolean;
  }

  public void setIvLeftRes(int paramInt)
  {
    this.ivLeftRes = paramInt;
  }

  public void setIvNumRes(int paramInt)
  {
    this.ivNumRes = paramInt;
  }

  public void setIvSeletedRes(int paramInt)
  {
    this.ivSeletedRes = paramInt;
  }

  public void setNewCreateModeBitmapPath(String paramString)
  {
    this.newCreateModeBitmapPath = paramString;
  }

  public void setNewCreateModeName(String paramString)
  {
    this.newCreateModeName = paramString;
  }

  public void setSeleted(boolean paramBoolean)
  {
    this.seleted = paramBoolean;
  }

  public void setSingleSeleted(boolean paramBoolean)
  {
    this.singleSeleted = paramBoolean;
  }

  public void setSpeed(int paramInt)
  {
    this.speed = paramInt;
  }

  public void setTransformation(int paramInt)
  {
    this.transformation = paramInt;
  }

  public void setTvName(String paramString)
  {
    this.tvName = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setW(int paramInt)
  {
    this.w = paramInt;
  }

  public String toString()
  {
    return "ModeVo{brt=" + this.brt + ", type=" + this.type + ", ivLeftRes=" + this.ivLeftRes + ", ivNumRes=" + this.ivNumRes + ", ivSeletedRes=" + this.ivSeletedRes + ", tvName='" + this.tvName + '\'' + ", seleted=" + this.seleted + ", isAddBtn=" + this.isAddBtn + ", isNewCreateMode=" + this.isNewCreateMode + ", colors=" + this.colors + ", w=" + this.w + ", transformation=" + this.transformation + ", newCreateModeBitmapPath='" + this.newCreateModeBitmapPath + '\'' + ", newCreateModeName='" + this.newCreateModeName + '\'' + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.vo.YaoKongVo
 * JD-Core Version:    0.6.0
 */