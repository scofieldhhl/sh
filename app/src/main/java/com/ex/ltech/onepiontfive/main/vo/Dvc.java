package com.ex.ltech.onepiontfive.main.vo;

import et.song.etclass.ETGroup;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class Dvc
{
  boolean allOnOff;
  int b;
  int brt;
  int brtProgress;
  int g;
  int groupId = -1;
  String groupName;
  String id;
  public ArrayList<Dvc> innerDvcVos = new ArrayList();
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
  private List<PanelLampVO> panelLampVO;
  int r;
  ETGroup remoteDevices;
  int roomIndex;
  SensorVo sensorVo;
  SmartSensorsVo smartSensorsVo;
  int type = 8;
  int w;
  int xPercent;
  int yPercent;

  public static int getTypeLed()
  {
    return 8;
  }

  public int getB()
  {
    return this.b;
  }

  public int getBrt()
  {
    return this.brt;
  }

  public int getBrtProgress()
  {
    return this.brtProgress;
  }

  public int getG()
  {
    return this.g;
  }

  public int getGroupId()
  {
    return this.groupId;
  }

  public String getGroupName()
  {
    return this.groupName;
  }

  public String getId()
  {
    return this.id;
  }

  public ArrayList<Dvc> getInnerDvcVos()
  {
    return this.innerDvcVos;
  }

  public String getName()
  {
    return this.name;
  }

  public List<PanelLampVO> getPanelLampVO()
  {
    return this.panelLampVO;
  }

  public int getR()
  {
    return this.r;
  }

  public ETGroup getRemoteDevices()
  {
    return this.remoteDevices;
  }

  public int getRoomIndex()
  {
    return this.roomIndex;
  }

  public SensorVo getSensorVo()
  {
    return this.sensorVo;
  }

  public SmartSensorsVo getSmartSensorsVo()
  {
    return this.smartSensorsVo;
  }

  public int getType()
  {
    return this.type;
  }

  public int getW()
  {
    return this.w;
  }

  public int getmIndex()
  {
    return this.mIndex;
  }

  public int getxPercent()
  {
    return this.xPercent;
  }

  public int getyPercent()
  {
    return this.yPercent;
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

  public void setB(int paramInt)
  {
    this.b = paramInt;
  }

  public void setBrt(int paramInt)
  {
    this.brt = paramInt;
  }

  public void setBrtProgress(int paramInt)
  {
    this.brtProgress = paramInt;
  }

  public void setG(int paramInt)
  {
    this.g = paramInt;
  }

  public void setGroupId(int paramInt)
  {
    this.groupId = paramInt;
  }

  public void setGroupName(String paramString)
  {
    this.groupName = paramString;
  }

  public void setId(String paramString)
  {
    this.id = paramString;
  }

  public void setInnerDvcVos(ArrayList<Dvc> paramArrayList)
  {
    this.innerDvcVos.clear();
    this.innerDvcVos.addAll(paramArrayList);
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
    if (!paramBoolean)
      System.out.println("");
    this.onOff = paramBoolean;
  }

  public void setPanelLampVO(List<PanelLampVO> paramList)
  {
    this.panelLampVO = paramList;
  }

  public void setR(int paramInt)
  {
    this.r = paramInt;
  }

  public void setRemoteDevices(ETGroup paramETGroup)
  {
    this.remoteDevices = paramETGroup;
  }

  public void setRoomIndex(int paramInt)
  {
    this.roomIndex = paramInt;
  }

  public void setSensorVo(SensorVo paramSensorVo)
  {
    this.sensorVo = paramSensorVo;
  }

  public void setSmartSensorsVo(SmartSensorsVo paramSmartSensorsVo)
  {
    this.smartSensorsVo = paramSmartSensorsVo;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setW(int paramInt)
  {
    this.w = paramInt;
  }

  public void setmIndex(int paramInt)
  {
    this.mIndex = paramInt;
  }

  public void setxPercent(int paramInt)
  {
    this.xPercent = paramInt;
  }

  public void setyPercent(int paramInt)
  {
    this.yPercent = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.Dvc
 * JD-Core Version:    0.6.0
 */