package et.song.tool;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public final class ETWindow
{
  public static void CutTitle(Activity paramActivity, int paramInt)
  {
    paramActivity.requestWindowFeature(1);
    paramActivity.getWindow().setFormat(-3);
    paramActivity.setRequestedOrientation(paramInt);
  }

  public static void FullWindow(Activity paramActivity, int paramInt)
  {
    paramActivity.requestWindowFeature(1);
    paramActivity.getWindow().setFlags(1024, 1024);
    paramActivity.getWindow().setFormat(-3);
    paramActivity.setRequestedOrientation(paramInt);
    paramActivity.getWindow().setFlags(512, 512);
  }

  public static int GetWindowHeight(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.heightPixels;
  }

  public static int GetWindowWidth(Activity paramActivity)
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    paramActivity.getWindowManager().getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics.widthPixels;
  }

  public static void ScreenOFF(Activity paramActivity)
  {
    paramActivity.getWindow().clearFlags(128);
  }

  public static void ScreenON(Activity paramActivity)
  {
    paramActivity.getWindow().addFlags(128);
  }

  public static void SendKeyCode(int paramInt)
    throws Exception
  {
    new Thread(paramInt)
    {
      public void run()
      {
        new Instrumentation().sendKeyDownUpSync(this.val$keyCode);
        try
        {
          Thread.sleep(100L);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
    }
    .start();
  }

  public static int dip2px(Context paramContext, float paramFloat)
  {
    return (int)(0.5D + paramFloat * getScreenDensity(paramContext));
  }

  public static float getScreenDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }

  public static int getScreenHeight(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().heightPixels;
  }

  public static int getScreenWidth(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().widthPixels;
  }

  public static String run(String[] paramArrayOfString)
  {
    String str = "";
    try
    {
      InputStream localInputStream = Runtime.getRuntime().exec(paramArrayOfString).getInputStream();
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(localInputStream));
      do
        str = localBufferedReader.readLine();
      while (!str.startsWith("User"));
      str = localBufferedReader.readLine();
      if (localInputStream != null)
      {
        localBufferedReader.close();
        localInputStream.close();
      }
      return str;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return str;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tool.ETWindow
 * JD-Core Version:    0.6.0
 */