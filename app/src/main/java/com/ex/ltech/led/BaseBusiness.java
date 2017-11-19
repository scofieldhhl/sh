package com.ex.ltech.led;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class BaseBusiness
{
  public Context ct;
  public SharedPreferences getter;
  public Gson gs;
  public UserFerences setter;

  public BaseBusiness(Context paramContext)
  {
    this.ct = paramContext;
    this.setter = UserFerences.getUserFerences(this.ct);
    this.getter = this.setter.spFerences;
    this.gs = new Gson();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.BaseBusiness
 * JD-Core Version:    0.6.0
 */