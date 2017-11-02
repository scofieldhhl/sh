package com.ex.ltech.hongwai.vo;

import java.io.Serializable;

public class NonIrDevice
  implements Serializable
{
  public int irCt1Brt = 255;
  public int irCt1BtrProgrees = 100;
  public int irCt1C = 255;
  public boolean irCt1Online;
  public boolean irCt1Onoff;
  public int irCt1W = 255;
  public int irCt1X = 255;
  public int irCt1Y = 255;
  public boolean irPanelSwitch1;
  public boolean irPanelSwitch2;
  public boolean irPanelSwitch3;
  public boolean irPanelSwitch4;
  public String irPanelSwitchName1;
  public String irPanelSwitchName2;
  public String irPanelSwitchName3;
  public String irPanelSwitchName4;
  public boolean irPanelSwitchSelected1;
  public boolean irPanelSwitchSelected2;
  public int mType;
  public int nonIrDeviceId = -1;

  public String toString()
  {
    return "NonIrDevice{irCt1C=" + this.irCt1C + ", irCt1W=" + this.irCt1W + ", irCt1Brt=" + this.irCt1Brt + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.NonIrDevice
 * JD-Core Version:    0.6.0
 */