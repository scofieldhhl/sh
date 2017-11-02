package com.ex.ltech.led.vo;

import java.util.ArrayList;
import java.util.List;

public class SongRecord
{
  int percent;
  int posi;
  public List<SongVo> songs = new ArrayList();

  public int getPercent()
  {
    return this.percent;
  }

  public int getPosi()
  {
    return this.posi;
  }

  public void setPercent(int paramInt)
  {
    this.percent = paramInt;
  }

  public void setPosi(int paramInt)
  {
    this.posi = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.SongRecord
 * JD-Core Version:    0.6.0
 */