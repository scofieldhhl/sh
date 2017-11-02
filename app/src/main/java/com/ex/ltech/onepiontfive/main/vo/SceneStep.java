package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class SceneStep
{
  public String roomName;
  public String seletedDvcNames;
  public ArrayList<RoomLvChildVo> seletedDvcs;
  public int spaceTime = 5;

  public String getRoomName()
  {
    return this.roomName;
  }

  public String getSeletedDvcNames()
  {
    return this.seletedDvcNames;
  }

  public ArrayList<RoomLvChildVo> getSeletedDvcs()
  {
    return this.seletedDvcs;
  }

  public int getSpaceTime()
  {
    return this.spaceTime;
  }

  public void setRoomName(String paramString)
  {
    this.roomName = paramString;
  }

  public void setSeletedDvcNames(String paramString)
  {
    this.seletedDvcNames = paramString;
  }

  public void setSeletedDvcs(ArrayList<RoomLvChildVo> paramArrayList)
  {
    this.seletedDvcs = paramArrayList;
  }

  public void setSpaceTime(int paramInt)
  {
    this.spaceTime = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SceneStep
 * JD-Core Version:    0.6.0
 */