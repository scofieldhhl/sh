package com.ex.ltech.led.vo;

public class TouchArea
{
  int maxX;
  int maxY;
  int minX;
  int minY;

  public int getMaxX()
  {
    return this.maxX;
  }

  public int getMaxY()
  {
    return this.maxY;
  }

  public int getMinX()
  {
    return this.minX;
  }

  public int getMinY()
  {
    return this.minY;
  }

  public void setMaxX(int paramInt)
  {
    this.maxX = paramInt;
  }

  public void setMaxY(int paramInt)
  {
    this.maxY = paramInt;
  }

  public void setMinX(int paramInt)
  {
    this.minX = paramInt;
  }

  public void setMinY(int paramInt)
  {
    this.minY = paramInt;
  }

  public String toString()
  {
    return "TouchArea{maxX=" + this.maxX + ", minX=" + this.minX + ", minY=" + this.minY + ", maxY=" + this.maxY + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.TouchArea
 * JD-Core Version:    0.6.0
 */