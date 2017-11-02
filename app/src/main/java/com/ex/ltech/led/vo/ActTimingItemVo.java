package com.ex.ltech.led.vo;

import java.util.List;

public class ActTimingItemVo
{
  List<Integer> colors;
  String mode;
  String repeatDay;
  String rgb;
  boolean swich;
  String swichStatus;
  String time;
  String whiteLighe;

  public List<Integer> getColors()
  {
    return this.colors;
  }

  public String getMode()
  {
    return this.mode;
  }

  public String getRepeatDay()
  {
    return this.repeatDay;
  }

  public String getRgb()
  {
    return this.rgb;
  }

  public String getSwichStatus()
  {
    return this.swichStatus;
  }

  public String getTime()
  {
    return this.time;
  }

  public String getWhiteLighe()
  {
    return this.whiteLighe;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setColors(List<Integer> paramList)
  {
    this.colors = paramList;
  }

  public void setMode(String paramString)
  {
    this.mode = paramString;
  }

  public void setRepeatDay(String paramString)
  {
    this.repeatDay = paramString;
  }

  public void setRgb(String paramString)
  {
    this.rgb = paramString;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setSwichStatus(String paramString)
  {
    this.swichStatus = paramString;
  }

  public void setTime(String paramString)
  {
    this.time = paramString;
  }

  public void setWhiteLighe(String paramString)
  {
    this.whiteLighe = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.ActTimingItemVo
 * JD-Core Version:    0.6.0
 */