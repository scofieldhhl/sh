package com.ex.ltech.hongwai.time;

import android.content.Context;
import android.content.SharedPreferences;
import com.ex.ltech.hongwai.vo.TimingVo;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TimingData
{
  private static TimingData data;
  static String timingDataKey = "YaoKongTimingDataK";
  static String timingDataXuHao = "YaoKongTimingDataXuHao";
  Gson gson = new Gson();
  private Context pct;
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;

  public TimingData(Context paramContext)
  {
    this.pct = paramContext;
    this.settingSetter = UserFerences.getUserFerences(paramContext);
    this.settingGetter = this.settingSetter.spFerences;
  }

  public static TimingData getInstance(Context paramContext)
  {
    if (data == null)
      data = new TimingData(paramContext);
    return data;
  }

  public TimingVo getCacheTimingVo4Sd()
  {
    System.out.println("getCacheTimingVo4Sd=" + this.settingGetter.getString("cacheTimingVo", ""));
    return (TimingVo)this.gson.fromJson(this.settingGetter.getString("cacheTimingVo", ""), TimingVo.class);
  }

  public ArrayList<Integer> getTimingDataXuHao4Sd()
  {
    String str = this.settingGetter.getString(DeviceListActivity.deviceMacAddress + timingDataXuHao, "");
    System.out.println("getXuHao " + str);
    try
    {
      ArrayList localArrayList = (ArrayList)this.gson.fromJson(str, new TypeToken()
      {
      }
      .getType());
      return localArrayList;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      localJsonSyntaxException.printStackTrace();
    }
    return null;
  }

  public List<TimingVo> getTimingVos4Sd()
  {
    try
    {
      String str = this.settingGetter.getString(DeviceListActivity.deviceMacAddress + timingDataKey, "");
      localObject = (List)this.gson.fromJson(str, new TypeToken()
      {
      }
      .getType());
      if (localObject == null)
        localObject = new ArrayList();
      return localObject;
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        Object localObject = null;
      }
    }
  }

  public void saveCacheVos(TimingVo paramTimingVo)
  {
    System.out.println("saveCacheVos=" + this.gson.toJson(paramTimingVo));
    this.settingSetter.putValue("cacheTimingVo", this.gson.toJson(paramTimingVo));
  }

  public void saveTimingDataXuHao2Sd(ArrayList<Integer> paramArrayList)
  {
    String str = this.gson.toJson(paramArrayList);
    this.settingSetter.putValue(DeviceListActivity.deviceMacAddress + timingDataXuHao, str);
    System.out.println("saveXuHao " + str);
  }

  public void saveTimingVos2Sd(List<TimingVo> paramList)
  {
    String str = this.gson.toJson(paramList);
    this.settingSetter.putValue(DeviceListActivity.deviceMacAddress + timingDataKey, str);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.time.TimingData
 * JD-Core Version:    0.6.0
 */