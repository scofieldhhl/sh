package com.ex.ltech.led.vo;

import java.util.List;

public class RealtimeTemp
{
  private List<HeWeather5Bean> HeWeather5;

  public List<HeWeather5Bean> getHeWeather5()
  {
    return this.HeWeather5;
  }

  public void setHeWeather5(List<HeWeather5Bean> paramList)
  {
    this.HeWeather5 = paramList;
  }

  public static class HeWeather5Bean
  {
    private BasicBean basic;
    private NowBean now;
    private String status;

    public BasicBean getBasic()
    {
      return this.basic;
    }

    public NowBean getNow()
    {
      return this.now;
    }

    public String getStatus()
    {
      return this.status;
    }

    public void setBasic(BasicBean paramBasicBean)
    {
      this.basic = paramBasicBean;
    }

    public void setNow(NowBean paramNowBean)
    {
      this.now = paramNowBean;
    }

    public void setStatus(String paramString)
    {
      this.status = paramString;
    }

    public static class BasicBean
    {
      private String city;
      private String cnty;
      private String id;
      private String lat;
      private String lon;
      private UpdateBean update;

      public String getCity()
      {
        return this.city;
      }

      public String getCnty()
      {
        return this.cnty;
      }

      public String getId()
      {
        return this.id;
      }

      public String getLat()
      {
        return this.lat;
      }

      public String getLon()
      {
        return this.lon;
      }

      public UpdateBean getUpdate()
      {
        return this.update;
      }

      public void setCity(String paramString)
      {
        this.city = paramString;
      }

      public void setCnty(String paramString)
      {
        this.cnty = paramString;
      }

      public void setId(String paramString)
      {
        this.id = paramString;
      }

      public void setLat(String paramString)
      {
        this.lat = paramString;
      }

      public void setLon(String paramString)
      {
        this.lon = paramString;
      }

      public void setUpdate(UpdateBean paramUpdateBean)
      {
        this.update = paramUpdateBean;
      }

      public static class UpdateBean
      {
        private String loc;
        private String utc;

        public String getLoc()
        {
          return this.loc;
        }

        public String getUtc()
        {
          return this.utc;
        }

        public void setLoc(String paramString)
        {
          this.loc = paramString;
        }

        public void setUtc(String paramString)
        {
          this.utc = paramString;
        }
      }
    }

    public static class NowBean
    {
      private CondBean cond;
      private String fl;
      private String hum;
      private String pcpn;
      private String pres;
      private String tmp;
      private String vis;
      private WindBean wind;

      public CondBean getCond()
      {
        return this.cond;
      }

      public String getFl()
      {
        return this.fl;
      }

      public String getHum()
      {
        return this.hum;
      }

      public String getPcpn()
      {
        return this.pcpn;
      }

      public String getPres()
      {
        return this.pres;
      }

      public String getTmp()
      {
        return this.tmp;
      }

      public String getVis()
      {
        return this.vis;
      }

      public WindBean getWind()
      {
        return this.wind;
      }

      public void setCond(CondBean paramCondBean)
      {
        this.cond = paramCondBean;
      }

      public void setFl(String paramString)
      {
        this.fl = paramString;
      }

      public void setHum(String paramString)
      {
        this.hum = paramString;
      }

      public void setPcpn(String paramString)
      {
        this.pcpn = paramString;
      }

      public void setPres(String paramString)
      {
        this.pres = paramString;
      }

      public void setTmp(String paramString)
      {
        this.tmp = paramString;
      }

      public void setVis(String paramString)
      {
        this.vis = paramString;
      }

      public void setWind(WindBean paramWindBean)
      {
        this.wind = paramWindBean;
      }

      public static class CondBean
      {
        private String code;
        private String txt;

        public String getCode()
        {
          return this.code;
        }

        public String getTxt()
        {
          return this.txt;
        }

        public void setCode(String paramString)
        {
          this.code = paramString;
        }

        public void setTxt(String paramString)
        {
          this.txt = paramString;
        }
      }

      public static class WindBean
      {
        private String deg;
        private String dir;
        private String sc;
        private String spd;

        public String getDeg()
        {
          return this.deg;
        }

        public String getDir()
        {
          return this.dir;
        }

        public String getSc()
        {
          return this.sc;
        }

        public String getSpd()
        {
          return this.spd;
        }

        public void setDeg(String paramString)
        {
          this.deg = paramString;
        }

        public void setDir(String paramString)
        {
          this.dir = paramString;
        }

        public void setSc(String paramString)
        {
          this.sc = paramString;
        }

        public void setSpd(String paramString)
        {
          this.spd = paramString;
        }
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.RealtimeTemp
 * JD-Core Version:    0.6.0
 */