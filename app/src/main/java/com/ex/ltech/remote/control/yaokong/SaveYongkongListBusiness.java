package com.ex.ltech.remote.control.yaokong;

import android.content.Context;
import android.content.SharedPreferences;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.remote.control.vo.YkVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;

public class SaveYongkongListBusiness extends BaseBusiness
{
  List<YkVo> data = null;

  public SaveYongkongListBusiness(Context paramContext)
  {
    super(paramContext);
  }

  public List<YkVo> getData()
  {
    if (this.data == null);
    try
    {
      this.data = ((List)this.gs.fromJson(this.getter.getString("saveYaoKongList", ""), new TypeToken()
      {
      }
      .getType()));
      label45: if (this.data != null)
        return this.data;
      return new ArrayList();
      return this.data;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      break label45;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.SaveYongkongListBusiness
 * JD-Core Version:    0.6.0
 */