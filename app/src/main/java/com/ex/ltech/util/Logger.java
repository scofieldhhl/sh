package com.ex.ltech.util;

import android.annotation.TargetApi;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger
{
  public static boolean DEBUG;
  public static boolean allowD;
  public static boolean allowE;
  public static boolean allowI;
  public static boolean allowV;
  public static boolean allowW;
  public static boolean allowWrite;
  public static boolean allowWtf;
  public static CustomLogger customLogger;
  public static String customTagPrefix = "";
  private static FileWriter mFileWriter;
  private static String mLogPath = null;

  static
  {
    if (Environment.getExternalStorageState().equals("mounted"))
    {
      File localFile = Environment.getExternalStorageDirectory();
      mLogPath = localFile.getAbsolutePath() + File.separator + "log.txt";
    }
    DEBUG = true;
    allowD = DEBUG;
    allowE = DEBUG;
    allowI = DEBUG;
    allowV = DEBUG;
    allowW = DEBUG;
    allowWtf = DEBUG;
    allowWrite = DEBUG;
  }

  public static void d(String paramString)
  {
    if (!allowD)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.d(str, paramString);
      return;
    }
    Log.d(str, paramString);
  }

  public static void d(String paramString, Throwable paramThrowable)
  {
    if (!allowD)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.d(str, paramString, paramThrowable);
      return;
    }
    Log.d(str, paramString, paramThrowable);
  }

  public static void e(String paramString)
  {
    if (!allowE)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.e(str, paramString);
      return;
    }
    Log.e(str, paramString);
  }

  public static void e(String paramString, Throwable paramThrowable)
  {
    if (!allowE)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.e(str, paramString, paramThrowable);
      return;
    }
    Log.e(str, paramString, paramThrowable);
  }

  private static String generateTag(StackTraceElement paramStackTraceElement)
  {
    String str1 = paramStackTraceElement.getClassName();
    String str2 = str1.substring(1 + str1.lastIndexOf("."));
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = str2;
    arrayOfObject[1] = paramStackTraceElement.getMethodName();
    arrayOfObject[2] = Integer.valueOf(paramStackTraceElement.getLineNumber());
    String str3 = String.format("%s.%s(L:%d)", arrayOfObject);
    if (TextUtils.isEmpty(customTagPrefix))
      return str3;
    return customTagPrefix + ":" + str3;
  }

  public static StackTraceElement getCallerStackTraceElement()
  {
    return java.lang.Thread.currentThread().getStackTrace()[4];
  }

  public static void i(String paramString)
  {
    if (!allowI)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.i(str, paramString);
      return;
    }
    Log.i(str, paramString);
  }

  public static void i(String paramString, Throwable paramThrowable)
  {
    if (!allowI)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.i(str, paramString, paramThrowable);
      return;
    }
    Log.i(str, paramString, paramThrowable);
  }

  public static void v(String paramString)
  {
    if (!allowV)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.v(str, paramString);
      return;
    }
    Log.v(str, paramString);
  }

  public static void v(String paramString, Throwable paramThrowable)
  {
    if (!allowV)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.v(str, paramString, paramThrowable);
      return;
    }
    Log.v(str, paramString, paramThrowable);
  }

  public static void w(String paramString)
  {
    if (!allowW)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.w(str, paramString);
      return;
    }
    Log.w(str, paramString);
  }

  public static void w(String paramString, Throwable paramThrowable)
  {
    if (!allowW)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.w(str, paramString, paramThrowable);
      return;
    }
    Log.w(str, paramString, paramThrowable);
  }

  public static void w(Throwable paramThrowable)
  {
    if (!allowW)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.w(str, paramThrowable);
      return;
    }
    Log.w(str, paramThrowable);
  }

  public static void write(String paramString)
  {
    if (!allowWrite)
      return;
    Log.e(generateTag(getCallerStackTraceElement()), paramString);
    writeLog(paramString);
  }

  private static void writeLog(String paramString)
  {
    try
    {
      if (mFileWriter == null)
        mFileWriter = new FileWriter(new File(mLogPath));
      mFileWriter.write(paramString);
      mFileWriter.flush();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  @TargetApi(8)
  public static void wtf(String paramString)
  {
    if (!allowWtf)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.wtf(str, paramString);
      return;
    }
    Log.wtf(str, paramString);
  }

  @TargetApi(8)
  public static void wtf(String paramString, Throwable paramThrowable)
  {
    if (!allowWtf)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.wtf(str, paramString, paramThrowable);
      return;
    }
    Log.wtf(str, paramString, paramThrowable);
  }

  @TargetApi(8)
  public static void wtf(Throwable paramThrowable)
  {
    if (!allowWtf)
      return;
    String str = generateTag(getCallerStackTraceElement());
    if (customLogger != null)
    {
      customLogger.wtf(str, paramThrowable);
      return;
    }
    Log.wtf(str, paramThrowable);
  }

  public static abstract interface CustomLogger
  {
    public abstract void d(String paramString1, String paramString2);

    public abstract void d(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void e(String paramString1, String paramString2);

    public abstract void e(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void i(String paramString1, String paramString2);

    public abstract void i(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void v(String paramString1, String paramString2);

    public abstract void v(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void w(String paramString1, String paramString2);

    public abstract void w(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void w(String paramString, Throwable paramThrowable);

    public abstract void wtf(String paramString1, String paramString2);

    public abstract void wtf(String paramString1, String paramString2, Throwable paramThrowable);

    public abstract void wtf(String paramString, Throwable paramThrowable);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.util.Logger
 * JD-Core Version:    0.6.0
 */