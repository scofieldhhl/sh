package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;

public class Room
{
  ArrayList<Dvc> dvcVos = new ArrayList();
  ArrayList<RoomLvChildVo> expandableLvInnerItemVos = new ArrayList();
  String name;
  boolean seleted;

  public ArrayList<Dvc> getDvcVos()
  {
    return this.dvcVos;
  }

  public ArrayList<RoomLvChildVo> getExpandableLvInnerItemVos()
  {
    return this.expandableLvInnerItemVos;
  }

  public String getName()
  {
    return this.name;
  }

  public boolean isSeleted()
  {
    return this.seleted;
  }

  public void setDvcVos(ArrayList<Dvc> paramArrayList)
  {
    this.dvcVos.clear();
    this.dvcVos.addAll(paramArrayList);
  }

  public void setExpandableLvInnerItemVos(ArrayList<RoomLvChildVo> paramArrayList)
  {
    this.expandableLvInnerItemVos.clear();
    this.expandableLvInnerItemVos.addAll(paramArrayList);
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setSeleted(boolean paramBoolean)
  {
    this.seleted = paramBoolean;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.Room
 * JD-Core Version:    0.6.0
 */