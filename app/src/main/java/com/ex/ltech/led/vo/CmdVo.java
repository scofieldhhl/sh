package com.ex.ltech.led.vo;

public class CmdVo
{
  public static final String CmdAss = "EB";
  public static int CmdCursor = 0;
  public static final String CmdHead = "66BB";
  public static final String CmdHead0x66 = "66";
  public static final String CmdHead0xBB = "BB";
  public static final String bling = "03";
  public static final String colReq = "11";
  public static final String colRsp = "A1";
  public static final String colorBrightnessType = "D1";
  public static final String colorGreyType = "D3";
  public static final String colorRGBType = "D2";
  public static final String custom = "21";
  public static int dateUnitContent = 0;
  public static int dateUnitLong = 0;
  public static final String dateUnitOff = "0";
  public static final String dateUnitOn = "1";
  public static final String dayNoSeleted = "0";
  public static final String daySeleted = "1";
  public static int deviceFunction = 0;
  public static final String gradient = "01";
  public static final String inside = "20";
  public static final String modeReq = "12";
  public static final String modeRsp = "A2";
  public static final String musOff = "40";
  public static final String musOn = "41";
  public static final String musReq = "13";
  public static final String musRsp = "A3";
  public static final String noSetPwd = "c0";
  public static int pwd = 0;
  public static final String s1 = "20";
  public static final String setPwd = "c1";
  public static final String specielDeviceConnetReq = "17";
  public static final String specielDeviceConnetRspKO = "A8";
  public static final String specielDeviceConnetRspOK = "A7";
  public static final String specielSetEncodeReq = "18";
  public static final String specielSetEncodeRsp = "A9";
  public static final String specielTimeReq = "16";
  public static final String specielTimeRsp = "A6";
  public static final String speed1 = "11";
  public static final String speed2 = "12";
  public static final String speed3 = "13";
  public static final String speed4 = "14";
  public static final String speed5 = "15";
  public static final String speed6 = "16";
  public static final String speed7 = "17";
  public static final String speed8 = "18";
  public static final String swichReq = "15";
  public static final String swichRsp = "A5";
  public static final String tiaoBian = "02";
  public static final String timRsp = "A4";
  public static final String timeOff = "0";
  public static final String timeOn = "1";
  public static final String timeReq = "14";

  public static int getCmdCursor()
  {
    return CmdCursor;
  }

  public static int getDateUnitContent()
  {
    return dateUnitContent;
  }

  public static int getDateUnitLong()
  {
    return dateUnitLong;
  }

  public static int getDeviceFunction()
  {
    return deviceFunction;
  }

  public static int getPwd()
  {
    return pwd;
  }

  public static void setCmdCursor(int paramInt)
  {
    CmdCursor = paramInt;
  }

  public static void setDateUnitContent(int paramInt)
  {
    dateUnitContent = paramInt;
  }

  public static void setDateUnitLong(int paramInt)
  {
    dateUnitLong = paramInt;
  }

  public static void setDeviceFunction(int paramInt)
  {
    deviceFunction = paramInt;
  }

  public static void setPwd(int paramInt)
  {
    pwd = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.CmdVo
 * JD-Core Version:    0.6.0
 */