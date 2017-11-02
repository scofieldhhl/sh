package com.ex.ltech;

import android.content.Context;
import android.content.SharedPreferences;
import com.ex.ltech.led.UserFerences;
import com.google.gson.Gson;

public class MyDb
{
  private static MyDb instance;
  private Context ct;
  private SharedPreferences getter;
  private Gson gs;
  private UserFerences setter;

  private MyDb(Context paramContext)
  {
    this.ct = paramContext;
    this.setter = UserFerences.getUserFerences(this.ct);
    this.getter = this.setter.spFerences;
    this.gs = new Gson();
  }

  public static MyDb getInstance(Context paramContext)
  {
    monitorenter;
    try
    {
      if (instance == null)
        instance = new MyDb(paramContext);
      return instance;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public Object getBean(String paramString, Class paramClass)
  {
    new StringBuilder().append(paramString).append(paramClass.getName()).toString();
    String str = this.getter.getString(paramString + paramClass.getName(), "");
    return this.gs.fromJson(str, paramClass);
  }

  public void putBean(String paramString, Object paramObject)
  {
    this.setter.putValue(paramString + paramObject.getClass().getName(), this.gs.toJson(paramObject));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.MyDb
 * JD-Core Version:    0.6.0
 */