package com.ex.ltech.onepiontfive.main.vo;

import com.ex.ltech.led.vo.RepeatDayVo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Timing
  implements Serializable
{
  int hour;
  private boolean isJustOnce;
  private long justOnceCurrentTime;
  int min;
  boolean onOff;
  int order = 255;
  List<RepeatDayVo> repeatDayVos;
  ArrayList<Integer> selectedDevicesmIndex;
  String seletedInfo;
  int seletedScenePosi = -1;
  String shotDaysStr;
  private boolean swich;
  private String time;
//  TimingInnerDeivces timingInnerDeivces;

  public int getHour()
  {
    return this.hour;
  }

  public long getJustOnceCurrentTime()
  {
    return this.justOnceCurrentTime;
  }

  public int getMin()
  {
    return this.min;
  }

  public int getOrder()
  {
    return this.order;
  }

  public List<RepeatDayVo> getRepeatDayVos()
  {
    return this.repeatDayVos;
  }

  public ArrayList<Integer> getSelectedDevicesmIndex()
  {
    return this.selectedDevicesmIndex;
  }

  public String getSeletedInfo()
  {
    return this.seletedInfo;
  }

  public int getSeletedScenePosi()
  {
    return this.seletedScenePosi;
  }

  public String getShotDaysStr()
  {
    return this.shotDaysStr;
  }

  public String getTime()
  {
    return this.time;
  }

//  public TimingInnerDeivces getTimingInnerDeivces()
//  {
//    return this.timingInnerDeivces;
//  }

  public boolean isJustOnce()
  {
    return this.isJustOnce;
  }

  public boolean isOnOff()
  {
    return this.onOff;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setHour(int paramInt)
  {
    this.hour = paramInt;
  }

  public void setIsJustOnce(boolean paramBoolean)
  {
    this.isJustOnce = paramBoolean;
  }

  public void setJustOnceCurrentTime(long paramLong)
  {
    this.justOnceCurrentTime = paramLong;
  }

  public void setMin(int paramInt)
  {
    this.min = paramInt;
  }

  public void setOnOff(boolean paramBoolean)
  {
    this.onOff = paramBoolean;
  }

  public void setOrder(int paramInt)
  {
    this.order = paramInt;
  }

  public void setRepeatDayVos(List<RepeatDayVo> paramList)
  {
    this.repeatDayVos = paramList;
  }

  public void setSelectedDevicesmIndex(ArrayList<Integer> paramArrayList)
  {
    this.selectedDevicesmIndex = paramArrayList;
  }

  public void setSeletedInfo(String paramString)
  {
    this.seletedInfo = paramString;
  }

  public void setSeletedScenePosi(int paramInt)
  {
    this.seletedScenePosi = paramInt;
  }

  public void setShotDaysStr(String paramString)
  {
    this.shotDaysStr = paramString;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

//  public void setTimingInnerDeivces(TimingInnerDeivces paramTimingInnerDeivces)
//  {
//    this.timingInnerDeivces = paramTimingInnerDeivces;
//  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.Timing
 * JD-Core Version:    0.6.0
 */