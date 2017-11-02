package com.ex.ltech.hongwai.vo;

import com.google.gson.annotations.SerializedName;
import com.kookong.app.data.api.IrData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyRcDevice
{
  public int acModeState;
  public String acState = "";
  public int areaId;
  public int brandId;
  public int currentWenduPosi;
  public ArrayList<DiyKey> diyKeys = new ArrayList();
  private List<IrDataListBean> irDataList;
  public List<IrData> irDatas = new ArrayList();
  public boolean isNorth;
  public boolean isShowListInfo;
  public Map<String, byte[]> learnMap = new HashMap();
  public String mName;
  public int mType;
  public NonIrDevice nonIrDevice;

  public List<IrDataListBean> getIrDataList()
  {
    return this.irDataList;
  }

  public void setIrDataList(List<IrDataListBean> paramList)
  {
    this.irDataList = paramList;
  }

  public static class IrDataListBean
  {
    private ExtsBean exts;
    private int fre;
    private List<KeysBean> keys;
    private int rid;
    private int type;

    public ExtsBean getExts()
    {
      return this.exts;
    }

    public int getFre()
    {
      return this.fre;
    }

    public List<KeysBean> getKeys()
    {
      return this.keys;
    }

    public int getRid()
    {
      return this.rid;
    }

    public int getType()
    {
      return this.type;
    }

    public void setExts(ExtsBean paramExtsBean)
    {
      this.exts = paramExtsBean;
    }

    public void setFre(int paramInt)
    {
      this.fre = paramInt;
    }

    public void setKeys(List<KeysBean> paramList)
    {
      this.keys = paramList;
    }

    public void setRid(int paramInt)
    {
      this.rid = paramInt;
    }

    public void setType(int paramInt)
    {
      this.type = paramInt;
    }

    public static class ExtsBean
    {

      @SerializedName("304")
      private String value304;

      @SerializedName("305")
      private String value305;

      @SerializedName("99999")
      private String value99999;

      public String getValue304()
      {
        return this.value304;
      }

      public String getValue305()
      {
        return this.value305;
      }

      public String getValue99999()
      {
        return this.value99999;
      }

      public void setValue304(String paramString)
      {
        this.value304 = paramString;
      }

      public void setValue305(String paramString)
      {
        this.value305 = paramString;
      }

      public void setValue99999(String paramString)
      {
        this.value99999 = paramString;
      }
    }

    public static class KeysBean
    {
      private String dcode;
      private int fid;
      private String fkey;
      private String fname;
      private int format;
      private String pulse;
      private String scode;

      public String getDcode()
      {
        return this.dcode;
      }

      public int getFid()
      {
        return this.fid;
      }

      public String getFkey()
      {
        return this.fkey;
      }

      public String getFname()
      {
        return this.fname;
      }

      public int getFormat()
      {
        return this.format;
      }

      public String getPulse()
      {
        return this.pulse;
      }

      public String getScode()
      {
        return this.scode;
      }

      public void setDcode(String paramString)
      {
        this.dcode = paramString;
      }

      public void setFid(int paramInt)
      {
        this.fid = paramInt;
      }

      public void setFkey(String paramString)
      {
        this.fkey = paramString;
      }

      public void setFname(String paramString)
      {
        this.fname = paramString;
      }

      public void setFormat(int paramInt)
      {
        this.format = paramInt;
      }

      public void setPulse(String paramString)
      {
        this.pulse = paramString;
      }

      public void setScode(String paramString)
      {
        this.scode = paramString;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.MyRcDevice
 * JD-Core Version:    0.6.0
 */