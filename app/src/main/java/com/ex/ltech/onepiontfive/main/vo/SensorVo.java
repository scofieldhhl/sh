package com.ex.ltech.onepiontfive.main.vo;

import com.ex.ltech.led.vo.RepeatDayVo;
import java.util.ArrayList;
import java.util.List;

public class SensorVo
{
  String delay;
  int delayType;
  ArrayList<Integer> deviceIndexs;
  String endHout;
  String endMin;
  boolean goHomeMode = false;
  boolean isOpen;
  boolean outHomeMode = false;
  boolean push;
  ArrayList<RepeatDayVo> repeatDayVos;
  ArrayList<Room> rooms;
  private List<SmsLogVo> smsLogs = new ArrayList();
  String startHour;
  String startMin;
  String touchCondition;
  String touchSceneName;
  int touchScenePosi = -1;
  String touchTime;
  String touchType = "SensorTouchDeviceType";

  public String getDelay()
  {
    return this.delay;
  }

  public int getDelayType()
  {
    return this.delayType;
  }

  public ArrayList<Integer> getDeviceIndexs()
  {
    return this.deviceIndexs;
  }

  public String getEndHout()
  {
    return this.endHout;
  }

  public String getEndMin()
  {
    return this.endMin;
  }

  public ArrayList<RepeatDayVo> getRepeatDayVos()
  {
    return this.repeatDayVos;
  }

  public ArrayList<Room> getRooms()
  {
    return this.rooms;
  }

  public List<SmsLogVo> getSmsLogs()
  {
    return this.smsLogs;
  }

  public String getStartHour()
  {
    return this.startHour;
  }

  public String getStartMin()
  {
    return this.startMin;
  }

  public String getTouchCondition()
  {
    return this.touchCondition;
  }

  public String getTouchSceneName()
  {
    return this.touchSceneName;
  }

  public int getTouchScenePosi()
  {
    return this.touchScenePosi;
  }

  public String getTouchTime()
  {
    return this.touchTime;
  }

  public String getTouchType()
  {
    return this.touchType;
  }

  public boolean isGoHomeMode()
  {
    return this.goHomeMode;
  }

  public boolean isOpen()
  {
    return this.isOpen;
  }

  public boolean isOutHomeMode()
  {
    return this.outHomeMode;
  }

  public boolean isPush()
  {
    return this.push;
  }

  public void setDelay(String paramString)
  {
    this.delay = paramString;
  }

  public void setDelayType(int paramInt)
  {
    this.delayType = paramInt;
  }

  public void setDeviceIndexs(ArrayList<Integer> paramArrayList)
  {
    this.deviceIndexs = paramArrayList;
  }

  public void setEndHout(String paramString)
  {
    this.endHout = paramString;
  }

  public void setEndMin(String paramString)
  {
    this.endMin = paramString;
  }

  public void setGoHomeMode(boolean paramBoolean)
  {
    this.goHomeMode = paramBoolean;
  }

  public void setOpen(boolean paramBoolean)
  {
    this.isOpen = paramBoolean;
  }

  public void setOutHomeMode(boolean paramBoolean)
  {
    this.outHomeMode = paramBoolean;
  }

  public void setPush(boolean paramBoolean)
  {
    this.push = paramBoolean;
  }

  public void setRepeatDayVos(ArrayList<RepeatDayVo> paramArrayList)
  {
    this.repeatDayVos = paramArrayList;
  }

  public void setRooms(ArrayList<Room> paramArrayList)
  {
    this.rooms = paramArrayList;
  }

  public void setSmsLogs()
  {
  }

  public void setSmsLogs(List<SmsLogVo> paramList)
  {
    this.smsLogs = paramList;
  }

  public void setStartHour(String paramString)
  {
    this.startHour = paramString;
  }

  public void setStartMin(String paramString)
  {
    this.startMin = paramString;
  }

  public void setTouchCondition(String paramString)
  {
    this.touchCondition = paramString;
  }

  public void setTouchSceneName(String paramString)
  {
    this.touchSceneName = paramString;
  }

  public void setTouchScenePosi(int paramInt)
  {
    this.touchScenePosi = paramInt;
  }

  public void setTouchTime(String paramString)
  {
    this.touchTime = paramString;
  }

  public void setTouchType(String paramString)
  {
    this.touchType = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SensorVo
 * JD-Core Version:    0.6.0
 */