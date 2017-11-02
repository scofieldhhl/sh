package com.ex.ltech.led.vo;

import java.util.List;

public class TimingVo
{
  public static final int ModeType = 0;
  public static final int colorType = 1;
  public static final int offDevice = 3;
  public static final int offType = 2;
  private int b;
  private int brt;
  private int c;
  private int color;
  private int ctGradualTime;
  private String endTime = "";
  private int g;
  private boolean isEnableTiming;
  private boolean isJustOnce;
  boolean isOffDevice;
  private boolean isOther;
  private boolean isShowMineTiming;
  private boolean isShowOtherTiming;
  private long justOnceCurrentTime;
  private String lightStatus = "";
  List<RepeatDayVo> longNameDays;
  private String modeName;
  private int order = 255;
  private long outletLatelyTime;
  private int r;
  private List<CtSceneVo> seletedCtScenes;
  private List<ModeVo> seletedModes;
  List<String> shotNameDays;
  private int speed = 21;
  private String startTime = "";
  private boolean swich;
  private String time;
  private int type;
  private String userIdHexString = "00000000";
  private int w;
  private int xuHao = -1;

  public static int getColorMode()
  {
    return 0;
  }

  public static int getColorType()
  {
    return 1;
  }

  public int getB()
  {
    return this.b;
  }

  public int getBrt()
  {
    return this.brt;
  }

  public int getC()
  {
    return this.c;
  }

  public int getColor()
  {
    return this.color;
  }

  public int getCtGradualTime()
  {
    return this.ctGradualTime;
  }

  public String getEndTime()
  {
    return this.endTime;
  }

  public int getG()
  {
    return this.g;
  }

  public long getJustOnceCurrentTime()
  {
    return this.justOnceCurrentTime;
  }

  public String getLightStatus()
  {
    return this.lightStatus;
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

  public List<CtSceneVo> getSeletedCtScenes()
  {
    return this.seletedCtScenes;
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

  public int getXuHao()
  {
    return this.xuHao;
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

  public void setB(int paramInt)
  {
    this.b = paramInt;
  }

  public void setBrt(int paramInt)
  {
    this.brt = paramInt;
  }

  public void setC(int paramInt)
  {
    this.c = paramInt;
  }

  public void setColor(int paramInt)
  {
    this.color = paramInt;
  }

  public void setCtGradualTime(int paramInt)
  {
    this.ctGradualTime = paramInt;
  }

  public void setEndTime(String paramString)
  {
    this.endTime = paramString;
  }

  public void setG(int paramInt)
  {
    this.g = paramInt;
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

  public void setLightStatus(String paramString)
  {
    this.lightStatus = paramString;
  }

  public void setLongNameDays(List<RepeatDayVo> paramList)
  {
    this.longNameDays = paramList;
  }

  public void setModeName(String paramString)
  {
    this.modeName = paramString;
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

  public void setSeletedCtScenes(List<CtSceneVo> paramList)
  {
    this.seletedCtScenes = paramList;
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

  public void setXuHao(int paramInt)
  {
    this.xuHao = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.TimingVo
 * JD-Core Version:    0.6.0
 */