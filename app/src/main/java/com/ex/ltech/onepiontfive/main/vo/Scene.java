package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class Scene
{
  String condition;
  int delayTouch;
  int mNum;
  String name;
  ArrayList<String> roomNames = new ArrayList();
  SceneSteps sceneSteps;
  boolean swich;
  SceneTouchSensor touchSensors;

  public String getCondition()
  {
    return this.condition;
  }

  public int getDelayTouch()
  {
    return this.delayTouch;
  }

  public String getName()
  {
    return this.name;
  }

  public ArrayList<String> getRoomNames()
  {
    return this.roomNames;
  }

  public SceneSteps getSceneSteps()
  {
    return this.sceneSteps;
  }

  public SceneTouchSensor getTouchSensors()
  {
    return this.touchSensors;
  }

  public int getmNum()
  {
    return this.mNum;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setCondition(String paramString)
  {
    this.condition = paramString;
  }

  public void setDelayTouch(int paramInt)
  {
    this.delayTouch = paramInt;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setRoomNames(ArrayList<String> paramArrayList)
  {
    this.roomNames.clear();
    this.roomNames.addAll(paramArrayList);
  }

  public void setSceneSteps(SceneSteps paramSceneSteps)
  {
    this.sceneSteps = paramSceneSteps;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setTouchSensors(SceneTouchSensor paramSceneTouchSensor)
  {
    this.touchSensors = paramSceneTouchSensor;
  }

  public void setmNum(int paramInt)
  {
    this.mNum = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.Scene
 * JD-Core Version:    0.6.0
 */