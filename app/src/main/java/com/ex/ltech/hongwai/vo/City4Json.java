package com.ex.ltech.hongwai.vo;

import java.util.List;

public class City4Json
{
  private List<CitylistBean> citylist;

  public List<CitylistBean> getCitylist()
  {
    return this.citylist;
  }

  public void setCitylist(List<CitylistBean> paramList)
  {
    this.citylist = paramList;
  }

  public static class CitylistBean
  {
    private List<CBean> c;
    private String p;

    public List<CBean> getC()
    {
      return this.c;
    }

    public String getP()
    {
      return this.p;
    }

    public void setC(List<CBean> paramList)
    {
      this.c = paramList;
    }

    public void setP(String paramString)
    {
      this.p = paramString;
    }

    public static class CBean
    {
      private String n;

      public String getN()
      {
        return this.n;
      }

      public void setN(String paramString)
      {
        this.n = paramString;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.City4Json
 * JD-Core Version:    0.6.0
 */