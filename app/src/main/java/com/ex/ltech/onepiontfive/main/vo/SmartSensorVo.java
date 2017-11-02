package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class SmartSensorVo
{
  String channel;
  String condition;
  String name;
  ArrayList<String> roomNames = new ArrayList();
  ArrayList<Room> rooms;
  boolean swich;
  String type;

  public String getChannel()
  {
    return this.channel;
  }

  public String getCondition()
  {
    return this.condition;
  }

  public String getName()
  {
    return this.name;
  }

  public ArrayList<String> getRoomNames()
  {
    return this.roomNames;
  }

  public ArrayList<Room> getRooms()
  {
    return this.rooms;
  }

  public String getType()
  {
    return this.type;
  }

  public boolean isSwich()
  {
    return this.swich;
  }

  public void setChannel(String paramString)
  {
    this.channel = paramString;
  }

  public void setCondition(String paramString)
  {
    this.condition = paramString;
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

  public void setRooms(ArrayList<Room> paramArrayList)
  {
    this.rooms = paramArrayList;
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SmartSensorVo
 * JD-Core Version:    0.6.0
 */