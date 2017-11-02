package com.ex.ltech.led;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class UserFerences
{
  public static String SHARE_TOKEN = "shareToken";
  private static UserFerences uferences;
  private SharedPreferences.Editor editor;
  public SharedPreferences spFerences;

  private UserFerences(Context paramContext)
  {
    this.spFerences = PreferenceManager.getDefaultSharedPreferences(paramContext);
  }

  public static UserFerences getUserFerences(Context paramContext)
  {
    monitorenter;
    try
    {
      if (uferences == null)
        uferences = new UserFerences(paramContext);
      UserFerences localUserFerences = uferences;
      return localUserFerences;
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public String getValue(String paramString)
  {
    return this.spFerences.getString(paramString, "");
  }

  public void putValue(String paramString, Object paramObject)
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (!"ACCF23C03200ModeDataKey".equals(paramString))
          continue;
        this.editor = this.spFerences.edit();
        if (!(paramObject instanceof Boolean))
          continue;
        this.editor.putBoolean(paramString, ((Boolean)paramObject).booleanValue());
        this.editor.commit();
        return;
        if ((paramObject instanceof Float))
        {
          this.editor.putFloat(paramString, ((Float)paramObject).floatValue());
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      if ((paramObject instanceof Integer))
      {
        this.editor.putInt(paramString, ((Integer)paramObject).intValue());
        continue;
      }
      if ((paramObject instanceof Long))
      {
        this.editor.putLong(paramString, ((Long)paramObject).longValue());
        continue;
      }
      this.editor.putString(paramString, (String)paramObject);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.UserFerences
 * JD-Core Version:    0.6.0
 */