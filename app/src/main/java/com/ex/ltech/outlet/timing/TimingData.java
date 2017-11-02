package com.ex.ltech.outlet.timing;

import android.content.Context;
import android.content.SharedPreferences;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.vo.TimingVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class TimingData
{
  private static TimingData data;
  static String timingDataKey = "outletTimingDataK";
  static String timingDataXuHao = "outletTimingDataXuHao";
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
    return (TimingVo)this.gson.fromJson(this.settingGetter.getString("cacheOutletTimingVo", ""), TimingVo.class);
  }

  public ArrayList<Integer> getTimingDataXuHao4Sd()
  {
    String str = this.settingGetter.getString(DeviceListActivity.deviceMacAddress + timingDataXuHao, "");
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
    String str = this.settingGetter.getString(DeviceListActivity.deviceMacAddress + timingDataKey, "");
    try
    {
      localObject = (List)this.gson.fromJson(str, new TypeToken()
      {
      }
      .getType());
      if (localObject == null)
        localObject = new ArrayList();
      return localObject;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      while (true)
      {
        localJsonSyntaxException.printStackTrace();
        Object localObject = null;
      }
    }
  }

  public void saveCacheVos(TimingVo paramTimingVo)
  {
    this.settingSetter.putValue("cacheOutletTimingVo", this.gson.toJson(paramTimingVo));
  }

  public void saveTimingDataXuHao2Sd(ArrayList<Integer> paramArrayList)
  {
    String str = this.gson.toJson(paramArrayList);
    this.settingSetter.putValue(DeviceListActivity.deviceMacAddress + timingDataXuHao, str);
  }

  public void saveTimingVos2Sd(List<TimingVo> paramList)
  {
    String str = this.gson.toJson(paramList);
    this.settingSetter.putValue(DeviceListActivity.deviceMacAddress + timingDataKey, str);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.timing.TimingData
 * JD-Core Version:    0.6.0
 */