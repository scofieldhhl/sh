package com.ex.ltech.onepiontfive.main.vo;

import com.ex.ltech.led.vo.RepeatDayVo;
import java.util.ArrayList;

public class GeoSpaceVo
{
  boolean isGoHome;
  boolean isOutHome;
  double lat = 0.0D;
  double lng = -1.0D;
  String name;
  String repeatDay;
  ArrayList<RepeatDayVo> repeatDayVos;
  String sceneName;
  boolean start;
  int touchSceneIndex = -1;

  public double getLat()
  {
    return this.lat;
  }

  public double getLng()
  {
    return this.lng;
  }

  public String getName()
  {
    return this.name;
  }

  public String getRepeatDay()
  {
    return this.repeatDay;
  }

  public ArrayList<RepeatDayVo> getRepeatDayVos()
  {
    return this.repeatDayVos;
  }

  public String getSceneName()
  {
    return this.sceneName;
  }

  public int getTouchSceneIndex()
  {
    return this.touchSceneIndex;
  }

  public boolean isGoHome()
  {
    return this.isGoHome;
  }

  public boolean isOutHome()
  {
    return this.isOutHome;
  }

  public boolean isStart()
  {
    return this.start;
  }

  public void setIsGoHome(boolean paramBoolean)
  {
    this.isGoHome = paramBoolean;
  }

  public void setIsOutHome(boolean paramBoolean)
  {
    this.isOutHome = paramBoolean;
  }

  public void setLat(double paramDouble)
  {
    this.lat = paramDouble;
  }

  public void setLng(double paramDouble)
  {
    this.lng = paramDouble;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRepeatDay(String paramString)
  {
    this.repeatDay = paramString;
  }

  public void setRepeatDayVos(ArrayList<RepeatDayVo> paramArrayList)
  {
    this.repeatDayVos = paramArrayList;
  }

  public void setSceneName(String paramString)
  {
    this.sceneName = paramString;
  }

  public void setStart(boolean paramBoolean)
  {
    this.start = paramBoolean;
  }

  public void setTouchSceneIndex(int paramInt)
  {
    this.touchSceneIndex = paramInt;
  }

  public String toString()
  {
    return "GeoSpaceVo{name='" + this.name + '\'' + ", start=" + this.start + ", isGoHome=" + this.isGoHome + ", isOutHome=" + this.isOutHome + ", lat=" + this.lat + ", lng=" + this.lng + ", sceneName='" + this.sceneName + '\'' + ", touchSceneIndex=" + this.touchSceneIndex + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo
 * JD-Core Version:    0.6.0
 */