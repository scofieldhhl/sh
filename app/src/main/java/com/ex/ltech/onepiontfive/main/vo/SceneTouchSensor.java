package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class SceneTouchSensor
{
  int delayTouch = 0;
  ArrayList<Dvc> sensors;

  public int getDelayTouch()
  {
    return this.delayTouch;
  }

  public ArrayList<Dvc> getSensors()
  {
    return this.sensors;
  }

  public void setDelayTouch(int paramInt)
  {
    this.delayTouch = paramInt;
  }

  public void setSensors(ArrayList<Dvc> paramArrayList)
  {
    this.sensors = paramArrayList;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SceneTouchSensor
 * JD-Core Version:    0.6.0
 */