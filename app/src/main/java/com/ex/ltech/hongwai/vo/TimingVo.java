package com.ex.ltech.hongwai.vo;

import com.ex.ltech.led.vo.ModeVo;
import com.ex.ltech.led.vo.RepeatDayVo;
import java.util.List;

public class TimingVo
{
  public static final int ModeType = 0;
  public static final int colorType = 1;
  public static final int offDevice = 3;
  public static final int offType = 2;
  private String airMode;
  private String airWendu;
  private int b;
  private int brt;
  private String chanel;
  private int color;
  private String endTime = "";
  private int g;
  private int gradientMins = 0;
  private int id;
  private boolean isEnableTiming;
  private boolean isJustOnce;
  boolean isOffDevice;
  private boolean isOther;
  private boolean isShowMineTiming;
  private boolean isShowOtherTiming;
  private long justOnceCurrentTime;
  List<RepeatDayVo> longNameDays;
  private String modeName;
  private boolean onOff;
  private int order = 255;
  private long outletLatelyTime;
  private int r;
  InnerRcVo rcVo;
  private List<ModeVo> seletedModes;
  List<String> shotNameDays;
  private int speed = 17;
  private String startTime = "";
  private String status = "";
  private boolean swich;
  private String time;
  private int type;
  private String userIdHexString = "00000000";
  private int w;
  private String wendu;
  private int xuHao = -1;
  private String yaoKongName;
  private String ykType = "";

  public static int getColorMode()
  {
    return 0;
  }

  public static int getColorType()
  {
    return 1;
  }

  public String getAirMode()
  {
    return this.airMode;
  }

  public String getAirWendu()
  {
    return this.airWendu;
  }

  public int getB()
  {
    return this.b;
  }

  public int getBrt()
  {
    return this.brt;
  }

  public String getChanel()
  {
    return this.chanel;
  }

  public int getColor()
  {
    return this.color;
  }

  public String getEndTime()
  {
    return this.endTime;
  }

  public int getG()
  {
    return this.g;
  }

  public int getGradientMins()
  {
    return this.gradientMins;
  }

  public int getId()
  {
    return this.id;
  }

  public long getJustOnceCurrentTime()
  {
    return this.justOnceCurrentTime;
  }

  public List<RepeatDayVo> getLongNameDays()
  {
    return this.longNameDays;
  }

  public String getModeName()
  {
    return this.modeName;
  }

  public int getOrder()
  {
    return this.order;
  }

  public long getOutletLatelyTime()
  {
    return this.outletLatelyTime;
  }

  public int getR()
  {
    return this.r;
  }

  public InnerRcVo getRcVo()
  {
    return this.rcVo;
  }

  public List<ModeVo> getSeletedModes()
  {
    return this.seletedModes;
  }

  public List<String> getShotNameDays()
  {
    return this.shotNameDays;
  }

  public int getSpeed()
  {
    return this.speed;
  }

  public String getStartTime()
  {
    return this.startTime;
  }

  public String getStatus()
  {
    return this.status;
  }

  public String getTime()
  {
    return this.time;
  }

  public int getType()
  {
    return this.type;
  }

  public String getUserIdHexString()
  {
    return this.userIdHexString;
  }

  public int getW()
  {
    return this.w;
  }

  public String getWendu()
  {
    return this.wendu;
  }

  public int getXuHao()
  {
    return this.xuHao;
  }

  public String getYaoKongName()
  {
    return this.yaoKongName;
  }

  public String getYkType()
  {
    return this.ykType;
  }

  public boolean isEnableTiming()
  {
    return this.isEnableTiming;
  }

  public boolean isJustOnce()
  {
    return this.isJustOnce;
  }

  public boolean isOffDevice()
  {
    return this.isOffDevice;
  }

  public boolean isOnOff()
  {
    return this.onOff;
  }

  public boolean isOther()
  {
    return this.isOther;
  }

  public boolean isShowMineTiming()
  {
    return this.isShowMineTiming;
  }

  public boolean isShowOtherTiming()
  {
    return this.isShowOtherTiming;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setAirMode(String paramString)
  {
    this.airMode = paramString;
  }

  public void setAirWendu(String paramString)
  {
    this.airWendu = paramString;
  }

  public void setB(int paramInt)
  {
    this.b = paramInt;
  }

  public void setBrt(int paramInt)
  {
    this.brt = paramInt;
  }

  public void setChanel(String paramString)
  {
    this.chanel = paramString;
  }

  public void setColor(int paramInt)
  {
    this.color = paramInt;
  }

  public void setEndTime(String paramString)
  {
    this.endTime = paramString;
  }

  public void setG(int paramInt)
  {
    this.g = paramInt;
  }

  public void setGradientMins(int paramInt)
  {
    this.gradientMins = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setIsEnableTiming(boolean paramBoolean)
  {
    this.isEnableTiming = paramBoolean;
  }

  public void setIsJustOnce(boolean paramBoolean)
  {
    this.isJustOnce = paramBoolean;
  }

  public void setIsOffDevice(boolean paramBoolean)
  {
    this.isOffDevice = paramBoolean;
  }

  public void setIsOther(boolean paramBoolean)
  {
    this.isOther = paramBoolean;
  }

  public void setIsShowMineTiming(boolean paramBoolean)
  {
    this.isShowMineTiming = paramBoolean;
  }

  public void setIsShowOtherTiming(boolean paramBoolean)
  {
    this.isShowOtherTiming = paramBoolean;
  }

  public void setJustOnceCurrentTime(long paramLong)
  {
    this.justOnceCurrentTime = paramLong;
  }

  public void setLongNameDays(List<RepeatDayVo> paramList)
  {
    this.longNameDays = paramList;
  }

  public void setModeName(String paramString)
  {
    this.modeName = paramString;
  }

  public void setOnOff(boolean paramBoolean)
  {
    this.onOff = paramBoolean;
  }

  public void setOrder(int paramInt)
  {
    this.order = paramInt;
  }

  public void setOutletLatelyTime(long paramLong)
  {
    this.outletLatelyTime = paramLong;
  }

  public void setR(int paramInt)
  {
    this.r = paramInt;
  }

  public void setRcVo(InnerRcVo paramInnerRcVo)
  {
    this.rcVo = paramInnerRcVo;
  }

  public void setSeletedModes(List<ModeVo> paramList)
  {
    this.seletedModes = paramList;
  }

  public void setShotNameDays(List<String> paramList)
  {
    this.shotNameDays = paramList;
  }

  public void setSpeed(int paramInt)
  {
    this.speed = paramInt;
  }

  public void setStartTime(String paramString)
  {
    this.startTime = paramString;
  }

  public void setStatus(String paramString)
  {
    this.status = paramString;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public void setUserIdHexString(String paramString)
  {
    this.userIdHexString = paramString;
  }

  public void setW(int paramInt)
  {
    this.w = paramInt;
  }

  public void setWendu(String paramString)
  {
    this.wendu = paramString;
  }

  public void setXuHao(int paramInt)
  {
    this.xuHao = paramInt;
  }

  public void setYaoKongName(String paramString)
  {
    this.yaoKongName = paramString;
  }

  public void setYkType(String paramString)
  {
    this.ykType = paramString;
  }

  public String toString()
  {
    return "YaokongTimingVo{airMode='" + this.airMode + '\'' + ", order=" + this.order + ", yaoKongName='" + this.yaoKongName + '\'' + ", wendu='" + this.wendu + '\'' + ", chanel='" + this.chanel + '\'' + ", id=" + this.id + ", ykType='" + this.ykType + '\'' + ", airWendu='" + this.airWendu + '\'' + ", onOff=" + this.onOff + ", xuHao=" + this.xuHao + ", type=" + this.type + ", modeName='" + this.modeName + '\'' + ", time='" + this.time + '\'' + ", swich=" + this.swich + ", color=" + this.color + ", r=" + this.r + ", g=" + this.g + ", b=" + this.b + ", brt=" + this.brt + ", w=" + this.w + ", speed=" + this.speed + ", isOffDevice=" + this.isOffDevice + ", isJustOnce=" + this.isJustOnce + ", outletLatelyTime=" + this.outletLatelyTime + ", startTime='" + this.startTime + '\'' + ", endTime='" + this.endTime + '\'' + ", justOnceCurrentTime=" + this.justOnceCurrentTime + ", longNameDays=" + this.longNameDays + ", seletedModes=" + this.seletedModes + ", shotNameDays=" + this.shotNameDays + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.TimingVo
 * JD-Core Version:    0.6.0
 */