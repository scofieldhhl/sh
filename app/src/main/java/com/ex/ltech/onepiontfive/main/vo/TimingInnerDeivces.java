package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class TimingInnerDeivces
{
  String condition;
  String name;
  ArrayList<String> roomNames = new ArrayList();
  ArrayList<Room> rooms = new ArrayList();
  boolean swich;

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

  public boolean isSwich()
  {
    return this.swich;
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
    this.rooms.clear();
    this.rooms.addAll(paramArrayList);
  }

  public void setSwich(boolean paramBoolean)
  {
    this.swich = paramBoolean;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.TimingInnerDeivces
 * JD-Core Version:    0.6.0
 */