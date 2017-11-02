package com.ex.ltech.led.acti.main;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.StringUtils;
import com.google.gson.Gson;
import java.io.PrintStream;

public class Business
{
  CmdDateBussiness cmdDateBussiness;
  private UserFerences ferences;
  public boolean flag = true;
  Gson gs;
  private String ipStr = "";
  private Context pct;
  String recDate;
  private SocketManager socketManager;

  public Business(Context paramContext)
  {
    this.pct = paramContext;
    this.ferences = UserFerences.getUserFerences(paramContext);
    this.gs = new Gson();
  }

  public void savaOneZeroFiveData(String paramString1, String paramString2)
  {
    try
    {
      if (paramString1.length() < 20)
        return;
      if (paramString1.substring(18, 20).equalsIgnoreCase("98"))
      {
        String str1 = paramString1.substring(20, paramString1.length());
        int i = Integer.parseInt(str1.substring(0, 2), 16);
        String str2 = str1.substring(4, 12);
        String str3 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(str1.substring(12, 12 + 2 * (-1 + (i - 4)))));
        UserFerences.getUserFerences(this.pct).putValue("GateWayIdKey" + paramString2, str2);
        UserFerences.getUserFerences(this.pct).putValue("dName" + paramString2, str3);
        System.out.println("savaOneZeroFiveData   Rec      deviceName  =  " + str3 + "         deviceId = " + str2);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void saveDeviceInfo4Device(String paramString1, String paramString2)
  {
    String str1 = paramString1.substring(12);
    String str2 = str1.substring(0, 2);
    UserFerences.getUserFerences(this.pct).putValue("dType" + paramString2, str2);
    String str3 = str1.substring(2, 4);
    int i = Integer.parseInt(str1.substring(4, 6), 16);
    System.out.println("TestLoopResult" + paramString1 + "  " + paramString2);
    int j = 6 + i * 2;
    try
    {
      String str4 = str1.substring(6, j);
      UserFerences.getUserFerences(this.pct).putValue("dName" + paramString2, StringUtils.bytesStr2WordStr(str4));
      String str5 = str1.substring(6 + i * 2, 2 + (6 + i * 2));
      System.out.println("testLoopGetDeviceInfo   Rec      deviceName  =  " + StringUtils.bytesStr2WordStr(str4) + "         deviceMac = " + paramString2);
      String str6 = str1.substring(2 + (10 + i * 2), 4 + (10 + i * 2));
      UserFerences.getUserFerences(this.pct).putValue("plugSwitch" + paramString2, str6);
      UserFerences.getUserFerences(this.pct).putValue("runType" + paramString2, str5);
      if (str5.equals("D2"))
      {
        if (str2.equals("00"))
        {
          String str14 = str1.substring(4 + (6 + i * 2), 6 + (6 + i * 2));
          String str15 = str1.substring(6 + (6 + i * 2), 8 + (6 + i * 2));
          String str16 = str1.substring(8 + (6 + i * 2), 10 + (6 + i * 2));
          UserFerences.getUserFerences(this.pct).putValue("dR" + paramString2, str14);
          UserFerences.getUserFerences(this.pct).putValue("dG" + paramString2, str15);
          UserFerences.getUserFerences(this.pct).putValue("dB" + paramString2, str16);
        }
        if (str2.equals("02"))
        {
          String str11 = str1.substring(2 + (6 + i * 2), 4 + (6 + i * 2));
          String str12 = str1.substring(4 + (6 + i * 2), 6 + (6 + i * 2));
          String str13 = str1.substring(6 + (6 + i * 2), 8 + (6 + i * 2));
          UserFerences.getUserFerences(this.pct).putValue("ctBrt" + paramString2, str11);
          UserFerences.getUserFerences(this.pct).putValue("ctC" + paramString2, str12);
          UserFerences.getUserFerences(this.pct).putValue("ctW" + paramString2, str13);
          UserFerences.getUserFerences(this.pct).putValue("dCtScenePosi" + paramString2, Integer.valueOf(-1));
        }
      }
      if (str5.equals("D3"))
      {
        if (str2.equals("00"))
        {
          String str8 = str1.substring(2 + (6 + i * 2), 4 + (6 + i * 2));
          String str9 = str1.substring(5 + (6 + i * 2), 6 + (6 + i * 2));
          String str10 = str1.substring(7 + (6 + i * 2), 8 + (6 + i * 2));
          UserFerences.getUserFerences(this.pct).putValue("dModeRunStatus" + paramString2, str8);
          UserFerences.getUserFerences(this.pct).putValue("dModeInsidePosi" + paramString2, str9);
          UserFerences.getUserFerences(this.pct).putValue("dModeCustomPosi" + paramString2, str10);
        }
        if (str2.equals("02"))
        {
          UserFerences.getUserFerences(this.pct).putValue("ctBrt" + paramString2, "");
          UserFerences.getUserFerences(this.pct).putValue("dCtScenePosi" + paramString2, str1.substring(2 + (6 + i * 2), 4 + (6 + i * 2)));
        }
      }
      if ((!str5.equals("D4")) || (str5.equals("C5")))
      {
        String str7 = str1.substring(12 + i * 2, 2 + (12 + i * 2));
        UserFerences.getUserFerences(this.pct).putValue("plugSwitch" + paramString2, str7);
      }
      UserFerences.getUserFerences(this.pct).putValue("runType" + paramString2, str5);
      UserFerences.getUserFerences(this.pct).putValue("dName" + paramString2, StringUtils.bytesStr2WordStr(str4));
      UserFerences.getUserFerences(this.pct).putValue("dStatus" + paramString2, str3);
      if (str2.equals("01"));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.main.Business
 * JD-Core Version:    0.6.0
 */