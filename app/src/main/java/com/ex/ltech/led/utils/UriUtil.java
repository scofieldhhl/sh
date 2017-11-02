package com.ex.ltech.led.utils;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class UriUtil
{
  public static String getRealFilePath(Context paramContext, Uri paramUri)
  {
    if (paramUri == null)
      return null;
    String str1 = paramUri.getScheme();
    String str2;
    if (str1 == null)
      str2 = paramUri.getPath();
    while (true)
    {
      return str2;
      if ("file".equals(str1))
      {
        str2 = paramUri.getPath();
        continue;
      }
      boolean bool1 = "content".equals(str1);
      str2 = null;
      if (!bool1)
        continue;
      Cursor localCursor = paramContext.getContentResolver().query(paramUri, new String[] { "_data" }, null, null, null);
      str2 = null;
      if (localCursor == null)
        continue;
      boolean bool2 = localCursor.moveToFirst();
      str2 = null;
      if (bool2)
      {
        int i = localCursor.getColumnIndex("_data");
        str2 = null;
        if (i > -1)
          str2 = localCursor.getString(i);
      }
      localCursor.close();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.utils.UriUtil
 * JD-Core Version:    0.6.0
 */