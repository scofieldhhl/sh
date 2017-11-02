package com.ex.ltech.onepiontfive.main.vo;

import et.song.etclass.ETGroup;

public class InnerLight
{
  boolean allOnOff;
  int brtProgress;
  boolean isClickSeleted;
  boolean isGroup;
  boolean isOnLine = true;
  boolean isRelation;
  boolean isSeleted;
  boolean isSeletedRelation;
  boolean isShowDelBtn;
  boolean isShowDeviceTitle;
  boolean isShowLightTitle;
  boolean isShowRelationControl;
  int mIndex;
  String name;
  boolean onOff = true;
  ETGroup remoteDevices;
  int type = 8;

  public static int getTypeLed()
  {
    return 8;
  }

  public int getBrtProgress()
  {
    return this.brtProgress;
  }

  public String getName()
  {
    return this.name;
  }

  public ETGroup getRemoteDevices()
  {
    return this.remoteDevices;
  }

  public int getType()
  {
    return this.type;
  }

  public int getmIndex()
  {
    return this.mIndex;
  }

  public boolean isAllOnOff()
  {
    return this.allOnOff;
  }

  public boolean isClickSeleted()
  {
    return this.isClickSeleted;
  }

  public boolean isGroup()
  {
    return this.isGroup;
  }

  public boolean isOnLine()
  {
    return this.isOnLine;
  }

  public boolean isOnOff()
  {
    return this.onOff;
  }

  public boolean isRelation()
  {
    return this.isRelation;
  }

  public boolean isSeleted()
  {
    return this.isSeleted;
  }

  public boolean isSeletedRelation()
  {
    return this.isSeletedRelation;
  }

  public boolean isShowDelBtn()
  {
    return this.isShowDelBtn;
  }

  public boolean isShowDeviceTitle()
  {
    return this.isShowDeviceTitle;
  }

  public boolean isShowLightTitle()
  {
    return this.isShowLightTitle;
  }

  public boolean isShowRelationControl()
  {
    return this.isShowRelationControl;
  }

  public void setAllOnOff(boolean paramBoolean)
  {
    this.allOnOff = paramBoolean;
  }

  public void setBrtProgress(int paramInt)
  {
    this.brtProgress = paramInt;
  }

  public void setIsClickSeleted(boolean paramBoolean)
  {
    this.isClickSeleted = paramBoolean;
  }

  public void setIsGroup(boolean paramBoolean)
  {
    this.isGroup = paramBoolean;
  }

  public void setIsOnLine(boolean paramBoolean)
  {
    this.isOnLine = paramBoolean;
  }

  public void setIsRelation(boolean paramBoolean)
  {
    this.isRelation = paramBoolean;
  }

  public void setIsSeleted(boolean paramBoolean)
  {
    this.isSeleted = paramBoolean;
  }

  public void setIsSeletedRelation(boolean paramBoolean)
  {
    this.isSeletedRelation = paramBoolean;
  }

  public void setIsShowDelBtn(boolean paramBoolean)
  {
    this.isShowDelBtn = paramBoolean;
  }

  public void setIsShowDeviceTitle(boolean paramBoolean)
  {
    this.isShowDeviceTitle = paramBoolean;
  }

  public void setIsShowLightTitle(boolean paramBoolean)
  {
    this.isShowLightTitle = paramBoolean;
  }

  public void setIsShowRelationControl(boolean paramBoolean)
  {
    this.isShowRelationControl = paramBoolean;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setOnOff(boolean paramBoolean)
  {
    this.onOff = paramBoolean;
  }

  public void setRemoteDevices(ETGroup paramETGroup)
  {
    this.remoteDevices = paramETGroup;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setmIndex(int paramInt)
  {
    this.mIndex = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.InnerLight
 * JD-Core Version:    0.6.0
 */