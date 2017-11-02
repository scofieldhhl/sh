package com.ex.ltech.led;

import java.io.PrintStream;

public class T
{
  static long startTime;

  public static void e()
  {
    System.out.println("      代码       片段       耗时          毫秒：      " + (System.currentTimeMillis() - startTime));
  }

  public static void s()
  {
    System.out.println("       开始      运行      代码       片段      。");
    startTime = System.currentTimeMillis();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.T
 * JD-Core Version:    0.6.0
 */