package com.ex.ltech.led.vo;

import java.util.List;

public class SceneSysCustomItemVo
{
  private String blingName;
  private List<Integer> colors;
  private boolean isSeleted;
  private String modeName;

  public String getBlingName()
  {
    return this.blingName;
  }

  public List<Integer> getColors()
  {
    return this.colors;
  }

  public String getModeName()
  {
    return this.modeName;
  }

  public boolean isSeleted()
  {
    return this.isSeleted;
  }

  public void setBlingName(String paramString)
  {
    this.blingName = paramString;
  }

  public void setColors(List<Integer> paramList)
  {
    this.colors = paramList;
  }

  public void setModeName(String paramString)
  {
    this.modeName = paramString;
  }

  public void setSeleted(boolean paramBoolean)
  {
    this.isSeleted = paramBoolean;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.SceneSysCustomItemVo
 * JD-Core Version:    0.6.0
 */