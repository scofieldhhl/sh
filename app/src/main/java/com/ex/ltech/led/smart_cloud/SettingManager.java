package com.ex.ltech.led.smart_cloud;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.provider.Settings.Secure;

public class SettingManager
{
  static String filter = "=====";
  private final String HIDE_TOKEN = "hidetoken";
  private final String HIDE_UID = "hideuid";
  private final String PASSWORD = "password";
  private final String PHONE_NUM = "phonenumber";
  private final String SEVER_NAME = "server";
  private final String SHARE_PREFERENCES = "set";
  private final String TOKEN = "token";
  private final String UID = "uid";
  private final String USER_NAME = "username";
  private Context c;
  SharedPreferences spf;

  public SettingManager(Context paramContext)
  {
    this.c = paramContext;
    this.spf = paramContext.getSharedPreferences("set", 0);
  }

  public void DownLoadProduct_key(String paramString)
  {
    String str = this.spf.getString("keys", "");
    if (str.contains(paramString))
      return;
    synchronized (this.spf)
    {
      this.spf.edit().putString("keys", str + paramString + filter).commit();
      return;
    }
  }

  public void clean()
  {
    setHideToken("");
    setHideUid("");
    setUid("");
    setToken("");
    setPhoneNumber("");
    setPassword("");
    setUserName("");
  }

  public String getDownLoadProduct_key()
  {
    String str1 = this.spf.getString("keys", "");
    String[] arrayOfString = str1.split(filter);
    if (!arrayOfString[0].equals(""))
    {
      String str2 = str1.replace(arrayOfString[0] + filter, "");
      synchronized (this.spf)
      {
        this.spf.edit().putString("keys", str2).commit();
        return arrayOfString[0];
      }
    }
    return null;
  }

  public String getHideToken()
  {
    return this.spf.getString("hidetoken", "");
  }

  public String getHideUid()
  {
    return this.spf.getString("hideuid", "");
  }

  public String getPassword()
  {
    return this.spf.getString("password", "");
  }

  public String getPhoneId()
  {
    return Settings.Secure.getString(this.c.getContentResolver(), "android_id");
  }

  public String getPhoneNumber()
  {
    return this.spf.getString("phonenumber", "");
  }

  public String getServerName()
  {
    return this.spf.getString("server", "api.gizwits.com");
  }

  public String getToken()
  {
    return this.spf.getString("token", "");
  }

  public String getUid()
  {
    return this.spf.getString("uid", "");
  }

  public String getUserName()
  {
    return this.spf.getString("username", "");
  }

  public void setHideToken(String paramString)
  {
    this.spf.edit().putString("hidetoken", paramString).commit();
  }

  public void setHideUid(String paramString)
  {
    this.spf.edit().putString("hideuid", paramString).commit();
  }

  public void setPassword(String paramString)
  {
    this.spf.edit().putString("password", paramString).commit();
  }

  public void setPhoneNumber(String paramString)
  {
    this.spf.edit().putString("phonenumber", paramString).commit();
  }

  public void setServerName(String paramString)
  {
    this.spf.edit().putString("server", paramString).commit();
  }

  public void setToken(String paramString)
  {
    this.spf.edit().putString("token", paramString).commit();
  }

  public void setUid(String paramString)
  {
    this.spf.edit().putString("uid", paramString).commit();
  }

  public void setUserName(String paramString)
  {
    this.spf.edit().putString("username", paramString).commit();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.smart_cloud.SettingManager
 * JD-Core Version:    0.6.0
 */