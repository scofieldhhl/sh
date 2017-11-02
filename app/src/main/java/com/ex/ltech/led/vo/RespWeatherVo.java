package com.ex.ltech.led.vo;

import java.util.List;

public class RespWeatherVo
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
    private List<DailyForecastBean> daily_forecast;
    private String status;

    public BasicBean getBasic()
    {
      return this.basic;
    }

    public List<DailyForecastBean> getDaily_forecast()
    {
      return this.daily_forecast;
    }

    public String getStatus()
    {
      return this.status;
    }

    public void setBasic(BasicBean paramBasicBean)
    {
      this.basic = paramBasicBean;
    }

    public void setDaily_forecast(List<DailyForecastBean> paramList)
    {
      this.daily_forecast = paramList;
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

    public static class DailyForecastBean
    {
      private AstroBean astro;
      private CondBean cond;
      private String date;
      private String hum;
      private String pcpn;
      private String pop;
      private String pres;
      private TmpBean tmp;
      private String uv;
      private String vis;
      private WindBean wind;

      public AstroBean getAstro()
      {
        return this.astro;
      }

      public CondBean getCond()
      {
        return this.cond;
      }

      public String getDate()
      {
        return this.date;
      }

      public String getHum()
      {
        return this.hum;
      }

      public String getPcpn()
      {
        return this.pcpn;
      }

      public String getPop()
      {
        return this.pop;
      }

      public String getPres()
      {
        return this.pres;
      }

      public TmpBean getTmp()
      {
        return this.tmp;
      }

      public String getUv()
      {
        return this.uv;
      }

      public String getVis()
      {
        return this.vis;
      }

      public WindBean getWind()
      {
        return this.wind;
      }

      public void setAstro(AstroBean paramAstroBean)
      {
        this.astro = paramAstroBean;
      }

      public void setCond(CondBean paramCondBean)
      {
        this.cond = paramCondBean;
      }

      public void setDate(String paramString)
      {
        this.date = paramString;
      }

      public void setHum(String paramString)
      {
        this.hum = paramString;
      }

      public void setPcpn(String paramString)
      {
        this.pcpn = paramString;
      }

      public void setPop(String paramString)
      {
        this.pop = paramString;
      }

      public void setPres(String paramString)
      {
        this.pres = paramString;
      }

      public void setTmp(TmpBean paramTmpBean)
      {
        this.tmp = paramTmpBean;
      }

      public void setUv(String paramString)
      {
        this.uv = paramString;
      }

      public void setVis(String paramString)
      {
        this.vis = paramString;
      }

      public void setWind(WindBean paramWindBean)
      {
        this.wind = paramWindBean;
      }

      public static class AstroBean
      {
        private String mr;
        private String ms;
        private String sr;
        private String ss;

        public String getMr()
        {
          return this.mr;
        }

        public String getMs()
        {
          return this.ms;
        }

        public String getSr()
        {
          return this.sr;
        }

        public String getSs()
        {
          return this.ss;
        }

        public void setMr(String paramString)
        {
          this.mr = paramString;
        }

        public void setMs(String paramString)
        {
          this.ms = paramString;
        }

        public void setSr(String paramString)
        {
          this.sr = paramString;
        }

        public void setSs(String paramString)
        {
          this.ss = paramString;
        }
      }

      public static class CondBean
      {
        private String code_d;
        private String code_n;
        private String txt_d;
        private String txt_n;

        public String getCode_d()
        {
          return this.code_d;
        }

        public String getCode_n()
        {
          return this.code_n;
        }

        public String getTxt_d()
        {
          return this.txt_d;
        }

        public String getTxt_n()
        {
          return this.txt_n;
        }

        public void setCode_d(String paramString)
        {
          this.code_d = paramString;
        }

        public void setCode_n(String paramString)
        {
          this.code_n = paramString;
        }

        public void setTxt_d(String paramString)
        {
          this.txt_d = paramString;
        }

        public void setTxt_n(String paramString)
        {
          this.txt_n = paramString;
        }
      }

      public static class TmpBean
      {
        private String max;
        private String min;

        public String getMax()
        {
          return this.max;
        }

        public String getMin()
        {
          return this.min;
        }

        public void setMax(String paramString)
        {
          this.max = paramString;
        }

        public void setMin(String paramString)
        {
          this.min = paramString;
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
 * Qualified Name:     com.ex.ltech.led.vo.RespWeatherVo
 * JD-Core Version:    0.6.0
 */