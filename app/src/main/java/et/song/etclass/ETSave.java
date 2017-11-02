package et.song.etclass;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class ETSave
{
  private static ETSave instance = null;
  private SharedPreferences mSave;

  private ETSave(Context paramContext)
  {
    this.mSave = paramContext.getSharedPreferences("info", 0);
  }

  public static final ETSave getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new ETSave(paramContext);
    return instance;
  }

  public String get(String paramString)
  {
    return this.mSave.getString(paramString, "");
  }

  public void put(String paramString1, String paramString2)
  {
    this.mSave.edit().putString(paramString1, paramString2).commit();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETSave
 * JD-Core Version:    0.6.0
 */