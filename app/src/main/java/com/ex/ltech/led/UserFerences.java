package com.ex.ltech.led;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class UserFerences
{
  public static String SHARE_TOKEN = "shareToken";
  private static UserFerences uferences;
  private Editor editor;
  public SharedPreferences spFerences;

  private UserFerences(Context paramContext)
  {
    this.spFerences = PreferenceManager.getDefaultSharedPreferences(paramContext);
  }

  public static UserFerences getUserFerences(Context paramContext)
  {
    try
    {
      if (uferences == null)
          uferences = new UserFerences(paramContext);
      UserFerences localUserFerences = uferences;
      return localUserFerences;
    }
    finally
    {
    }
  }

  public String getValue(String paramString)
  {
    return this.spFerences.getString(paramString, "");
  }

  public void putValue(String paramString, Object paramObject)
  {
      try
      {
        /*if (!"ACCF23C03200ModeDataKey".equals(paramString))
          continue;*/
        this.editor = this.spFerences.edit();
        if ((paramObject instanceof Boolean))
          this.editor.putBoolean(paramString, ((Boolean)paramObject).booleanValue());
        else if ((paramObject instanceof Float))
        {
          this.editor.putFloat(paramString, ((Float)paramObject).floatValue());
        }
        else if ((paramObject instanceof Integer))
        {
          this.editor.putInt(paramString, ((Integer)paramObject).intValue());
        }
        else if ((paramObject instanceof Long))
        {
          this.editor.putLong(paramString, ((Long)paramObject).longValue());
        }else {
          this.editor.putString(paramString, (String)paramObject);
        }

      }
      finally
      {
        this.editor.commit();
      }
  }
}