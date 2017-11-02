package com.ex.ltech.onepiontfive.main.vo;

import com.ex.ltech.remote.control.vo.YkVo;
import et.song.etclass.ETDevice;
import java.util.List;

public class RoomLvChildVo
{
  int dvcIndex;
  String innerDeviceName;
  String innerDeviceStatus;
  int innerItemSeletedType;
  int innerItemType;
  boolean isSeted;
  String othersRC;
  int othersRCLen;
  int othersRCNum;
  private List<PanelLampVO> panelLampVO;
  String parentRoomName;
  ETDevice remoteDevice;
  int spaceTime = 2;
  boolean swich;
  String ykType;
  YkVo ykVo;
  List<YkVo> ykVos;

  public RoomLvChildVo()
  {
  }

  public RoomLvChildVo(String paramString, ETDevice paramETDevice)
  {
    this.innerDeviceName = paramString;
    this.remoteDevice = paramETDevice;
  }

  public int getDvcIndex()
  {
    return this.dvcIndex;
  }

  public String getInnerDeviceName()
  {
    return this.innerDeviceName;
  }

  public String getInnerDeviceStatus()
  {
    return this.innerDeviceStatus;
  }

  public int getInnerItemSeletedType()
  {
    return this.innerItemSeletedType;
  }

  public int getInnerItemType()
  {
    return this.innerItemType;
  }

  public String getOthersRC()
  {
    return this.othersRC;
  }

  public int getOthersRCLen()
  {
    return this.othersRCLen;
  }

  public int getOthersRCNum()
  {
    return this.othersRCNum;
  }

  public List<PanelLampVO> getPanelLampVO()
  {
    return this.panelLampVO;
  }

  public String getParentRoomName()
  {
    return this.parentRoomName;
  }

  public ETDevice getRemoteDevice()
  {
    return this.remoteDevice;
  }

  public int getSpaceTime()
  {
    return this.spaceTime;
  }

  public String getYkType()
  {
    return this.ykType;
  }

  public YkVo getYkVo()
  {
    return this.ykVo;
  }

  public List<YkVo> getYkVos()
  {
    return this.ykVos;
  }

  public boolean isSeted()
  {
    return this.isSeted;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setDvcIndex(int paramInt)
  {
    this.dvcIndex = paramInt;
  }

  public void setInnerDeviceName(String paramString)
  {
    this.innerDeviceName = paramString;
  }

  public void setInnerDeviceStatus(String paramString)
  {
    this.innerDeviceStatus = paramString;
  }

  public void setInnerItemSeletedType(int paramInt)
  {
    this.innerItemSeletedType = paramInt;
  }

  public void setInnerItemType(int paramInt)
  {
    this.innerItemType = paramInt;
  }

  public void setIsSeted(boolean paramBoolean)
  {
    this.isSeted = paramBoolean;
  }

  public void setOthersRC(String paramString)
  {
    this.othersRC = paramString;
  }

  public void setOthersRCLen(int paramInt)
  {
    this.othersRCLen = paramInt;
  }

  public void setOthersRCNum(int paramInt)
  {
    this.othersRCNum = paramInt;
  }

  public void setPanelLampVO(List<PanelLampVO> paramList)
  {
    this.panelLampVO = paramList;
  }

  public void setParentRoomName(String paramString)
  {
    this.parentRoomName = paramString;
  }

  public void setRemoteDevice(ETDevice paramETDevice)
  {
    this.remoteDevice = paramETDevice;
  }

  public void setSeted(boolean paramBoolean)
  {
    this.isSeted = paramBoolean;
  }

  public void setSpaceTime(int paramInt)
  {
    this.spaceTime = paramInt;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setYkType(String paramString)
  {
    this.ykType = paramString;
  }

  public void setYkVo(YkVo paramYkVo)
  {
    this.ykVo = paramYkVo;
  }

  public void setYkVos(List<YkVo> paramList)
  {
    this.ykVos = paramList;
    this.ykVo = ((YkVo)paramList.get(-1 + paramList.size()));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo
 * JD-Core Version:    0.6.0
 */