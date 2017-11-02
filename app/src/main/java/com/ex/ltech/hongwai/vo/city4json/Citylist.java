package com.ex.ltech.hongwai.vo.city4json;

import java.util.ArrayList;
import java.util.List;

public class Citylist
{
  private List<C> c = new ArrayList();
  private String p;

  public List<C> getC()
  {
    return this.c;
  }

  public String getP()
  {
    return this.p;
  }

  public void setC(List<C> paramList)
  {
    this.c = paramList;
  }

  public void setP(String paramString)
  {
    this.p = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.city4json.Citylist
 * JD-Core Version:    0.6.0
 */