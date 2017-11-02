package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class Home
{
  ArrayList<Room> rooms = new ArrayList();

  public ArrayList<Room> getRooms()
  {
    return this.rooms;
  }

  public void setRooms(ArrayList<Room> paramArrayList)
  {
    this.rooms.clear();
    this.rooms.addAll(paramArrayList);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.Home
 * JD-Core Version:    0.6.0
 */