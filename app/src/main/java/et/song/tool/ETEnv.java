package et.song.tool;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.os.Environment;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public final class ETEnv
{
  public static String getExternalStorageDirectory()
  {
    return Environment.getExternalStorageDirectory().getPath();
  }

  public static String getFilePath(Context paramContext)
  {
    return paramContext.getFilesDir().getPath();
  }

  public static String getPackageName(Context paramContext)
  {
    return paramContext.getPackageName();
  }

  public static int getSDKVersion()
  {
    try
    {
      int i = Build.VERSION.SDK_INT;
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return 0;
  }

  private static String getTopActivityName(Context paramContext)
  {
    List localList = ((ActivityManager)paramContext.getSystemService("activity")).getRunningTasks(1);
    String str = null;
    if (localList != null)
      str = ((RunningTaskInfo)localList.get(0)).topActivity.getClassName();
    return str;
  }

  public static String getUpdataVerJSON(String paramString)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder();
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    HttpParams localHttpParams = localDefaultHttpClient.getParams();
    HttpConnectionParams.setConnectionTimeout(localHttpParams, 3000);
    HttpConnectionParams.setSoTimeout(localHttpParams, 5000);
    HttpEntity localHttpEntity = localDefaultHttpClient.execute(new HttpGet(paramString)).getEntity();
    BufferedReader localBufferedReader;
    if (localHttpEntity != null)
      localBufferedReader = new BufferedReader(new InputStreamReader(localHttpEntity.getContent(), "UTF-8"), 8192);
    while (true)
    {
      String str = localBufferedReader.readLine();
      if (str == null)
      {
        localBufferedReader.close();
        return localStringBuilder.toString();
      }
      localStringBuilder.append(str + "\n");
    }
  }

  public static int getVerCode(Context paramContext, String paramString)
    throws NameNotFoundException
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode;
      return i;
    }
    catch (Exception localException)
    {
    }
    return -1;
  }

  public static String getVerName(Context paramContext, String paramString)
  {
    try
    {
      String str = paramContext.getPackageManager().getPackageInfo(paramString, 0).versionName;
      return str;
    }
    catch (Exception localException)
    {
    }
    return "";
  }

  public static boolean isBackground(Context paramContext)
  {
    Iterator localIterator = ((ActivityManager)paramContext.getSystemService("activity")).getRunningAppProcesses().iterator();
    if (!localIterator.hasNext());
    RunningAppProcessInfo localRunningAppProcessInfo;
    do
    {
      return false;
      localRunningAppProcessInfo = (RunningAppProcessInfo)localIterator.next();
      if (!localRunningAppProcessInfo.processName.equals(paramContext.getPackageName()))
        break;
    }
    while (localRunningAppProcessInfo.importance == 100);
    return true;
  }

  public static boolean isForeground(Context paramContext)
  {
    String str1 = getPackageName(paramContext);
    String str2 = getTopActivityName(paramContext);
    System.out.println("packageName=" + str1 + ",topActivityClassName=" + str2);
    return (str1 != null) && (str2 != null) && (str2.startsWith(str1));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETEnv
 * JD-Core Version:    0.6.0
 */