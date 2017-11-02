package com.ex.ltech.LogRegForget.ShareSdk;

import java.util.HashMap;

public abstract interface OnLoginListener
{
  public abstract boolean onLogin(String paramString, HashMap<String, Object> paramHashMap);

  public abstract boolean onRegister(UserInfo paramUserInfo);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ShareSdk.OnLoginListener
 * JD-Core Version:    0.6.0
 */