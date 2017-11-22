package io.xlink.wifi.js.util;

import android.annotation.SuppressLint;
import android.content.Context;

public class CrashHandler
  implements Thread.UncaughtExceptionHandler
{
  private static CrashHandler crashHandler;
  Context context;
  private Thread.UncaughtExceptionHandler handler;

  private CrashHandler(Context paramContext)
  {
    this.context = paramContext;
    this.handler = Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
  }

  public static String getCurrentNetType(Context paramContext)
  {
    /*String str = "";
    NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
    if (localNetworkInfo == null)
      str = "null";
    int i;
    do
    {
      do
      {
        return str;
        if (localNetworkInfo.getType() == 1)
          return "wifi";
      }
      while (localNetworkInfo.getType() != 0);
      i = localNetworkInfo.getSubtype();
      if ((i == 4) || (i == 1) || (i == 2))
        return "2g";
      if ((i == 3) || (i == 8) || (i == 6) || (i == 5) || (i == 12))
        return "3g";
    }
    while (i != 13);
    return "4g";*/
    return null;
  }

  public static CrashHandler init(Context paramContext)
  {
    if (crashHandler == null)
      crashHandler = new CrashHandler(paramContext);
    return crashHandler;
  }

  private void write2ErrorLog(String paramString)
  {
    /*Mail localMail = new Mail("L-Home@ltech.cn", "Lt201511");
    localMail.set_debuggable(false);
    localMail.set_to(new String[] { "2629312117@qq.com" });
    localMail.set_from("L-Home@ltech.cn");
    localMail.set_subject("BUG");
    localMail.setBody(paramString);
    FileUtil.saveCrashTextTxt(paramString);
    try
    {
      boolean bool = localMail.send();
      if (bool);
      return;
    }
    catch (Exception localException)
    {
    }*/
  }

  @SuppressLint({"SimpleDateFormat"})
  public void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    /*SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Date: ").append(localSimpleDateFormat.format(new Date()));
    localStringBuilder.append("\n");
    localStringBuilder.append("APP.VERSION:");
    localStringBuilder.append(this.context.getString(2131100494));
    localStringBuilder.append("\n");
    localStringBuilder.append("Build.MANUFACTURER:");
    localStringBuilder.append(Build.MANUFACTURER);
    localStringBuilder.append("\n");
    localStringBuilder.append("Build.MODEL:");
    localStringBuilder.append(Build.MODEL);
    localStringBuilder.append("\n");
    localStringBuilder.append("VERSION:");
    localStringBuilder.append(VERSION.SDK_INT);
    localStringBuilder.append("\n");
    localStringBuilder.append("Build.CPU_ABI:");
    localStringBuilder.append(Build.CPU_ABI);
    localStringBuilder.append("\n");
    localStringBuilder.append("NetWork:");
    localStringBuilder.append(getCurrentNetType(this.context));
    localStringBuilder.append("===========\n");
    localStringBuilder.append("Stacktrace:\n\n");
    StringWriter localStringWriter = new StringWriter();
    PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
    paramThrowable.printStackTrace(localPrintWriter);
    localStringBuilder.append(localStringWriter.toString());
    localStringBuilder.append("===========\n");
    localPrintWriter.close();
    new Thread(localStringBuilder)
    {
      public void run()
      {
        CrashHandler.this.write2ErrorLog(this.val$buff.toString());
      }
    }
    .start();
    try
    {
      Thread.sleep(6000L);
      label286: if (this.handler != null)
        this.handler.uncaughtException(paramThread, paramThrowable);
      XlinkAgent.getInstance().stop();
      return;
    }
    catch (Exception localException)
    {
      break label286;
    }*/
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.util.CrashHandler
 * JD-Core Version:    0.6.0
 */