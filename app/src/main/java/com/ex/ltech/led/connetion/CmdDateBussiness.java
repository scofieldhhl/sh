package com.ex.ltech.led.connetion;

import android.content.Context;
import android.graphics.Color;

import com.ex.ltech.hongwai.StringUtil;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;

import java.util.ArrayList;
import java.util.List;

public class CmdDateBussiness
{
  private static int cmdCount;
  private Context context;
  private int frontModeNum;
  private String frontPws;
  private byte pwd12;
  private byte pwd34;
  private String pwdHex = null;
  private String readPws;
  private int realModeNum;
  String temptest = "30000B16E9010100003C";
  String userIdHexString;

  public CmdDateBussiness(Context paramContext, String paramString)
  {
    this.context = paramContext;
    pwdToHex(paramString);
    this.userIdHexString = Integer.toHexString(MyApp.getApp().getAppid()).toUpperCase();
    for (int i = this.userIdHexString.length(); i < 8; i++)
      this.userIdHexString = ("0" + this.userIdHexString);
  }

  public CmdDateBussiness(String paramString)
  {
    pwdToHex(paramString);
  }

  public static void demoChangeStringToHex(String paramString, byte[] paramArrayOfByte)
  {
    for (int i = 0; i < paramString.length(); i++)
    {
      byte[] arrayOfByte = paramString.substring(i, i + 1).getBytes();
      String str = Integer.toHexString(0xFF & arrayOfByte[0]);
      System.out.print("r10x" + str.toUpperCase());
      paramArrayOfByte[(i + 8)] = Byte.decode("0x" + str.toUpperCase()).byteValue();
      if (arrayOfByte.length != 2)
        continue;
      Integer.toHexString(0xFF & arrayOfByte[1]);
    }
    System.out.print("eeeeeeeeeeeeeeeee-------------------------demoChangeStringToHex");
  }

  public byte[] TiminghongWaiId(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return new byte[0];
    byte[] arrayOfByte = new byte[9 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 42;
    arrayOfByte[7] = (byte)paramArrayOfByte.length;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 8)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    cmdCount = 1 + cmdCount;
    "".equals(this.temptest);
    return arrayOfByte;
  }

  public byte[] checkRgbwDeviceVersionCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 62;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("checkRgbwDeviceVersionCmd" + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] controlIrCtLight(int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4, int paramInt5)
  {
    System.out.println("int nonIrDevic eId = " + paramInt1 + "    int controlType = " + paramInt2 + "   boolean onOff=" + paramBoolean + " int brt = " + paramInt3 + " int c" + paramInt4 + " int w= " + paramInt5);
    byte[] arrayOfByte = new byte[22];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 51;
    arrayOfByte[7] = 13;
    arrayOfByte[8] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte[9] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte[10] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte[11] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    arrayOfByte[12] = 2;
    arrayOfByte[13] = (byte)paramInt1;
    arrayOfByte[14] = (byte)paramInt2;
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      arrayOfByte[15] = (byte)i;
      arrayOfByte[16] = (byte)paramInt3;
      arrayOfByte[17] = (byte)paramInt5;
      arrayOfByte[18] = (byte)paramInt4;
      arrayOfByte[19] = 0;
      arrayOfByte[20] = 0;
      arrayOfByte[21] = -21;
      cmdCount = 1 + cmdCount;
      return arrayOfByte;
    }
  }

  public byte[] delIrLight(int paramInt)
  {
    byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 49;
    arrayOfByte[7] = 7;
    arrayOfByte[8] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte[9] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte[10] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte[11] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    arrayOfByte[12] = 3;
    arrayOfByte[13] = (byte)paramInt;
    arrayOfByte[14] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] delIrPanel(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 70;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getAllOnOffCmd(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 21;
    arrayOfByte[7] = (byte)tenToSixteen(1);
    arrayOfByte[8] = (byte)tenToSixteen(paramInt);
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("全开" + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCallDeviceUpdataStatusCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 61;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("全开" + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getColorCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 17;
    arrayOfByte[7] = (byte)tenToSixteen(6);
    arrayOfByte[8] = (byte)tenToSixteen(paramInt1);
    arrayOfByte[9] = (byte)tenToSixteen(paramInt2);
    arrayOfByte[10] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[11] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[12] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[13] = (byte)tenToSixteen(paramInt6);
    arrayOfByte[14] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    Main.lastSendCmd = Main.colorCmd;
    System.out.println("getColorCmd======" + StringUtil.byte2Hexstr(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getColorTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    byte[] arrayOfByte = new byte[21];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 12;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = (byte)paramInt3;
    arrayOfByte[14] = 0;
    arrayOfByte[15] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[16] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[17] = (byte)tenToSixteen(paramInt6);
    arrayOfByte[18] = (byte)tenToSixteen(paramInt7);
    arrayOfByte[19] = (byte)tenToSixteen(paramInt8);
    arrayOfByte[20] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getColorTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[25];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 16;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = (byte)paramInt3;
    arrayOfByte[14] = 0;
    arrayOfByte[15] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[16] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[17] = (byte)tenToSixteen(paramInt6);
    arrayOfByte[18] = (byte)tenToSixteen(paramInt7);
    arrayOfByte[19] = (byte)tenToSixteen(paramInt8);
    arrayOfByte[20] = paramArrayOfByte[0];
    arrayOfByte[21] = paramArrayOfByte[1];
    arrayOfByte[22] = paramArrayOfByte[2];
    arrayOfByte[23] = paramArrayOfByte[3];
    arrayOfByte[24] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCtColorCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    System.out.println("getCtColorCmd======" + paramInt2);
    byte[] arrayOfByte = new byte[13];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 17;
    arrayOfByte[7] = 4;
    arrayOfByte[8] = (byte)tenToSixteen(paramInt1);
    arrayOfByte[9] = (byte)tenToSixteen(paramInt2);
    arrayOfByte[10] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[11] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[12] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getCtColorTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 15;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = 17;
    arrayOfByte[14] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[15] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[16] = (byte)tenToSixteen(paramInt6);
    arrayOfByte[17] = -31;
    arrayOfByte[18] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[19] = paramArrayOfByte[0];
    arrayOfByte[20] = paramArrayOfByte[1];
    arrayOfByte[21] = paramArrayOfByte[2];
    arrayOfByte[22] = paramArrayOfByte[3];
    arrayOfByte[23] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCtOffTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 15;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = 25;
    arrayOfByte[14] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[15] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[16] = (byte)tenToSixteen(paramInt6);
    arrayOfByte[17] = -31;
    arrayOfByte[18] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[19] = paramArrayOfByte[0];
    arrayOfByte[20] = paramArrayOfByte[1];
    arrayOfByte[21] = paramArrayOfByte[2];
    arrayOfByte[22] = paramArrayOfByte[3];
    arrayOfByte[23] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCtSceneColorCmd(int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
  {
    System.out.println("getCtSceneColorCmd======" + paramInt2);
    byte[] arrayOfByte1 = paramString.getBytes();
    byte[] arrayOfByte2 = new byte[16 + arrayOfByte1.length];
    arrayOfByte2[0] = 102;
    arrayOfByte2[1] = -69;
    arrayOfByte2[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte2[3] = -64;
    arrayOfByte2[4] = this.pwd12;
    arrayOfByte2[5] = this.pwd34;
    arrayOfByte2[6] = 18;
    arrayOfByte2[7] = (byte)(7 + arrayOfByte1.length);
    arrayOfByte2[8] = (byte)tenToSixteen(paramInt1);
    arrayOfByte2[9] = -127;
    arrayOfByte2[10] = (byte)arrayOfByte1.length;
    for (int i = 11; i < 11 + arrayOfByte1.length; i++)
      arrayOfByte2[i] = arrayOfByte1[(i - 11)];
    arrayOfByte2[(-1 + (12 + arrayOfByte1.length))] = -126;
    arrayOfByte2[(-1 + (13 + arrayOfByte1.length))] = (byte)paramInt2;
    arrayOfByte2[(-1 + (14 + arrayOfByte1.length))] = (byte)paramInt3;
    arrayOfByte2[(-1 + (15 + arrayOfByte1.length))] = (byte)paramInt4;
    arrayOfByte2[(-1 + (16 + arrayOfByte1.length))] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("brightness======" + StringUtils.btye2Str3(arrayOfByte2));
    return arrayOfByte2;
  }

  public byte[] getCtSceneTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, int paramInt4, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 15;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = (byte)paramInt3;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = -31;
    arrayOfByte[18] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[19] = paramArrayOfByte[0];
    arrayOfByte[20] = paramArrayOfByte[1];
    arrayOfByte[21] = paramArrayOfByte[2];
    arrayOfByte[22] = paramArrayOfByte[3];
    arrayOfByte[23] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCtTimingOffCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 15;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = 2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[19] = paramArrayOfByte[0];
    arrayOfByte[20] = paramArrayOfByte[1];
    arrayOfByte[21] = paramArrayOfByte[2];
    arrayOfByte[22] = paramArrayOfByte[3];
    arrayOfByte[23] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCtrlCtOtherTimingCmd(int paramInt, String paramString)
  {
    byte[] arrayOfByte = new byte[11];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 59;
    arrayOfByte[7] = 2;
    if (paramString.equals("del"))
      arrayOfByte[8] = 2;
    if (paramString.equals("on"))
      arrayOfByte[8] = 1;
    if (paramString.equals("off"))
      arrayOfByte[8] = 0;
    arrayOfByte[9] = (byte)paramInt;
    arrayOfByte[10] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("getCtrlCtOtherTimingCmd       " + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCustomModeCmd(int paramInt1, int paramInt2, int paramInt3, byte paramByte, int paramInt4, int paramInt5, List<Integer> paramList)
  {
    System.out.println("getCustomModeCmd       " + paramByte + "      brt   " + paramInt4 + "           colorCount      " + paramInt5);
    int i = 16 + 3 * paramList.size();
    byte[] arrayOfByte = new byte[i];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 18;
    arrayOfByte[7] = 31;
    arrayOfByte[8] = 33;
    arrayOfByte[9] = (byte)paramInt1;
    arrayOfByte[10] = (byte)paramInt2;
    arrayOfByte[11] = (byte)paramInt3;
    arrayOfByte[12] = paramByte;
    arrayOfByte[13] = (byte)paramInt4;
    arrayOfByte[14] = (byte)paramInt5;
    for (int j = 0; j < paramInt5; j++)
    {
      int k = ((Integer)paramList.get(j)).intValue();
      if (k == this.context.getResources().getColor(R.color.gray))
        k = -16777216;
      arrayOfByte[(15 + j * 3)] = (byte)tenToSixteen(Color.red(k));
      arrayOfByte[(16 + j * 3)] = (byte)tenToSixteen(Color.green(k));
      arrayOfByte[(17 + j * 3)] = (byte)tenToSixteen(Color.blue(k));
    }
    arrayOfByte[(i - 1)] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getDeviceInfoCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 33;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getDeviceOnOffInfoCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 37;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getDeviceTimingCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 58;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getHeartBeatCmd()
  {
    return new byte[] { 102, -69, -69, -69, -69, -69, -69, -69, -69, -21 };
  }

  public byte[] getIrCt1OnlineStatus(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 54;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getLearnPanelNRcCmd()
  {
    System.out.println("getReqCtSceneCmd======");
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 60;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 1;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getLittleLedOnOffInfoCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 39;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getModeTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, String paramString4, int paramInt3)
  {
    byte[] arrayOfByte = new byte[21];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 12;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    handModeNumSeleted(paramString4);
    arrayOfByte[15] = (byte)this.realModeNum;
    arrayOfByte[16] = (byte)this.frontModeNum;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getModeTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, String paramString4, int paramInt3, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[25];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 16;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    handModeNumSeleted(paramString4);
    arrayOfByte[15] = (byte)this.realModeNum;
    arrayOfByte[16] = (byte)this.frontModeNum;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = paramArrayOfByte[0];
    arrayOfByte[21] = paramArrayOfByte[1];
    arrayOfByte[22] = paramArrayOfByte[2];
    arrayOfByte[23] = paramArrayOfByte[3];
    arrayOfByte[24] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getMoreSeletedModeCmd(String paramString, List<Byte> paramList, int paramInt)
  {
    byte[] arrayOfByte = new byte[29];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 18;
    arrayOfByte[7] = 20;
    arrayOfByte[8] = 32;
    arrayOfByte[9] = (byte)paramInt;
    handModeNumSeleted(paramString);
    arrayOfByte[10] = (byte)this.realModeNum;
    arrayOfByte[11] = (byte)this.frontModeNum;

    for(int i = 12; i < 28; i++){
      if (i - 12 < paramList.size())
        arrayOfByte[i] = ((Byte)paramList.get(i - 12)).byteValue();
      else
        arrayOfByte[i] = 0;
    }
    arrayOfByte[28] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    Main.lastSendCmd = Main.modeCmd;
    return arrayOfByte;
    /*byte[] arrayOfByte = new byte[29];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 18;
    arrayOfByte[7] = 20;
    arrayOfByte[8] = 32;
    arrayOfByte[9] = (byte)paramInt;
    handModeNumSeleted(paramString);
    arrayOfByte[10] = (byte)this.realModeNum;
    arrayOfByte[11] = (byte)this.frontModeNum;
    int i = 12;
    if (i < 28)
    {
      if (i - 12 < paramList.size())
        arrayOfByte[i] = ((Byte)paramList.get(i - 12)).byteValue();
      while (true)
      {
        i++;
        break;
        arrayOfByte[i] = 0;
      }
    }
    arrayOfByte[28] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    Main.lastSendCmd = Main.modeCmd;
    return arrayOfByte;*/
  }

  public byte[] getMusicCmd(boolean paramBoolean, int paramInt)
  {
    byte[] arrayOfByte = new byte[11];
    /*arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 19;
    arrayOfByte[7] = 2;
    if (paramBoolean);
    for (int i = 65; ; i = 64)
    {
      arrayOfByte[8] = i;
      arrayOfByte[9] = (byte)tenToSixteen(paramInt);
      arrayOfByte[10] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      Main.lastSendCmd = Main.musicCmd;
      return arrayOfByte;
    }*/
    return arrayOfByte;
  }

  public byte[] getMusicCmdT(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[13];
    /*arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 19;
    arrayOfByte[7] = 4;
    if (paramBoolean);
    for (int i = 65; ; i = 64)
    {
      arrayOfByte[8] = i;
      arrayOfByte[9] = (byte)tenToSixteen(paramInt1);
      arrayOfByte[10] = (byte)tenToSixteen(paramInt2);
      arrayOfByte[11] = (byte)tenToSixteen(paramInt3);
      arrayOfByte[12] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      Main.lastSendCmd = Main.musicCmd;
      return arrayOfByte;
    }*/
    return arrayOfByte;
  }

  /*public byte[] getNonIrTiming(int paramInt1, String paramString1, String paramString2, String paramString3, int paramInt2, NonIrDevice paramNonIrDevice, int paramInt3, boolean paramBoolean)
  {
    byte[] arrayOfByte1 = new byte[0];
    int i = 1;
    switch (paramNonIrDevice.mType)
    {
    default:
    case 10:
    case 11:
    case 12:
    }
    String str;
    while (true)
    {
      str = Integer.toHexString(10 + arrayOfByte1.length).toUpperCase();
      int k = str.length();
      while (true)
        if (k < 4)
        {
          str = "0" + str;
          k++;
          continue;
          i = 1;
          arrayOfByte1 = new byte[2];
          arrayOfByte1[0] = (byte)paramNonIrDevice.nonIrDeviceId;
          if (paramNonIrDevice.irPanelSwitch1);
          for (int i2 = 226; ; i2 = 194)
          {
            arrayOfByte1[1] = (byte)i2;
            break;
          }
          i = 1;
          arrayOfByte1 = new byte[3];
          arrayOfByte1[0] = (byte)paramNonIrDevice.nonIrDeviceId;
          if ((paramNonIrDevice.irPanelSwitchSelected1) && (paramNonIrDevice.irPanelSwitchSelected2))
          {
            if ((paramNonIrDevice.irPanelSwitch1) && (paramNonIrDevice.irPanelSwitch2))
            {
              arrayOfByte1[1] = -31;
              System.out.println("CMD_K2RF_ALL_ON");
              break;
            }
            if ((!paramNonIrDevice.irPanelSwitch1) && (!paramNonIrDevice.irPanelSwitch2))
            {
              arrayOfByte1[1] = -63;
              System.out.println("CMD_K2RF_ALL_OFF");
              break;
            }
            if ((paramNonIrDevice.irPanelSwitch1) && (!paramNonIrDevice.irPanelSwitch2))
            {
              arrayOfByte1[1] = -25;
              System.out.println("CMD_K2RF_WAY1_ON_WAY2_OFF");
              break;
            }
            if ((paramNonIrDevice.irPanelSwitch1) || (!paramNonIrDevice.irPanelSwitch2))
              break;
            arrayOfByte1[1] = -24;
            System.out.println("CMD_K2RF_WAY1_OFF_WAY2_ON");
            break;
          }
          if (paramNonIrDevice.irPanelSwitchSelected1)
          {
            if (paramNonIrDevice.irPanelSwitch1);
            for (int i1 = -30; ; i1 = -62)
            {
              arrayOfByte1[1] = i1;
              if (!paramNonIrDevice.irPanelSwitch1)
                break label367;
              System.out.println("CMD_K1RF_ON");
              break;
            }
            label367: System.out.println("CMD_K1RF_OFF");
            break;
          }
          if (!paramNonIrDevice.irPanelSwitchSelected2)
            break;
          if (paramNonIrDevice.irPanelSwitch2);
          for (int n = -29; ; n = -61)
          {
            arrayOfByte1[1] = n;
            if (!paramNonIrDevice.irPanelSwitch2)
              break label432;
            System.out.println("CMD_K2RF_ON");
            break;
          }
          label432: System.out.println("CMD_K2RF_OFF");
          break;
          i = 3;
          arrayOfByte1 = new byte[11];
          arrayOfByte1[0] = 2;
          arrayOfByte1[1] = (byte)paramNonIrDevice.nonIrDeviceId;
          label478: int j;
          if (paramBoolean)
          {
            arrayOfByte1[2] = 2;
            if (!paramNonIrDevice.irCt1Onoff)
              break label570;
            j = 1;
            label489: arrayOfByte1[3] = (byte)j;
            arrayOfByte1[4] = (byte)paramNonIrDevice.irCt1Brt;
            arrayOfByte1[5] = (byte)paramNonIrDevice.irCt1W;
            arrayOfByte1[6] = (byte)paramNonIrDevice.irCt1C;
            arrayOfByte1[7] = 0;
            arrayOfByte1[8] = 0;
            if (paramInt3 <= 0)
              break label576;
            arrayOfByte1[9] = -31;
          }
          while (true)
          {
            arrayOfByte1[10] = (byte)paramInt3;
            break;
            arrayOfByte1[2] = 1;
            break label478;
            label570: j = 0;
            break label489;
            label576: arrayOfByte1[9] = -32;
          }
        }
    }
    byte[] arrayOfByte2 = new byte[20 + arrayOfByte1.length];
    arrayOfByte2[0] = 102;
    arrayOfByte2[1] = -69;
    arrayOfByte2[2] = (byte)cmdCount;
    arrayOfByte2[3] = -64;
    arrayOfByte2[4] = this.pwd12;
    arrayOfByte2[5] = this.pwd34;
    arrayOfByte2[6] = 80;
    arrayOfByte2[7] = (byte)Integer.parseInt(str.substring(0, 2), 16);
    arrayOfByte2[8] = (byte)Integer.parseInt(str.substring(2, 4), 16);
    arrayOfByte2[9] = (byte)paramInt1;
    arrayOfByte2[10] = i;
    arrayOfByte2[11] = (byte)toHex(paramString1);
    arrayOfByte2[12] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte2[13] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte2[14] = (byte)paramInt2;
    arrayOfByte2[15] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte2[16] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte2[17] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte2[18] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    for (int m = 0; m < arrayOfByte1.length; m++)
      arrayOfByte2[(m + 19)] = arrayOfByte1[m];
    arrayOfByte2[(-1 + arrayOfByte2.length)] = -21;
    cmdCount = 1 + cmdCount;
    System.out.println("       getNonIrTiming     cmd        " + StringUtils.btye2Str3(arrayOfByte2));
    return arrayOfByte2;
  }*/

  public byte[] getOffDeviceCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[21];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 12;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = 0;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getOffDeviceCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[25];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 16;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = 0;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = paramArrayOfByte[0];
    arrayOfByte[21] = paramArrayOfByte[1];
    arrayOfByte[22] = paramArrayOfByte[2];
    arrayOfByte[23] = paramArrayOfByte[3];
    arrayOfByte[24] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getOffTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[21];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 12;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = 2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getOffTimingCmd(String paramString1, int paramInt1, String paramString2, String paramString3, int paramInt2, int paramInt3, byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[25];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 16;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = 2;
    arrayOfByte[13] = 18;
    arrayOfByte[14] = 1;
    arrayOfByte[15] = 0;
    arrayOfByte[16] = 0;
    arrayOfByte[17] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[18] = (byte)tenToSixteen(0);
    arrayOfByte[19] = (byte)tenToSixteen(0);
    arrayOfByte[20] = paramArrayOfByte[0];
    arrayOfByte[21] = paramArrayOfByte[1];
    arrayOfByte[22] = paramArrayOfByte[2];
    arrayOfByte[23] = paramArrayOfByte[3];
    arrayOfByte[24] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getOneZeroFiveDeviceInfoCmd()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(90));
    localArrayList.add(Integer.valueOf(165));
    localArrayList.add(Integer.valueOf(255));
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(17));
    localArrayList.add(Integer.valueOf(255));
    localArrayList.add(Integer.valueOf(255));
    localArrayList.add(Integer.valueOf(255));
    localArrayList.add(Integer.valueOf(255));
    localArrayList.add(Integer.valueOf(24));
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(0));
    localArrayList.add(Integer.valueOf(1));
    MyBusiness localMyBusiness = new MyBusiness(this.context);
    localMyBusiness.addCheckSumData(localArrayList);
    localArrayList.add(Integer.valueOf(22));
    return localMyBusiness.getCmdData(localArrayList);
  }

  public byte[] getOneZeroFiveMusicCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    byte[] arrayOfByte = new byte[19];
    arrayOfByte[0] = 90;
    arrayOfByte[1] = -91;
    arrayOfByte[2] = -1;
    arrayOfByte[3] = 1;
    arrayOfByte[4] = 17;
    arrayOfByte[5] = -1;
    arrayOfByte[6] = -1;
    arrayOfByte[7] = -1;
    arrayOfByte[8] = -1;
    arrayOfByte[9] = 3;
    arrayOfByte[10] = 4;
    arrayOfByte[11] = (byte)paramInt1;
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = (byte)paramInt3;
    arrayOfByte[14] = (byte)paramInt4;
    arrayOfByte[15] = 1;
    int i = 0;
    for (int j = 0; j < 15; j++)
      i += arrayOfByte[j];
    String str1 = Integer.toHexString(i);
    if (str1.length() == 3)
      str1 = "0" + str1;
    if (str1.length() == 2)
      str1 = "00" + str1;
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = str1.substring(0, 2);
    arrayOfByte[16] = (byte)Integer.valueOf(str2, 16).intValue();
    arrayOfByte[17] = (byte)Integer.valueOf(str3, 16).intValue();
    arrayOfByte[18] = 16;
    return arrayOfByte;
  }

  public byte[] getOutletColorCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 17;
    arrayOfByte[7] = 6;
    arrayOfByte[8] = -46;
    arrayOfByte[9] = (byte)tenToSixteen(paramInt3);
    arrayOfByte[10] = (byte)tenToSixteen(paramInt4);
    arrayOfByte[11] = (byte)tenToSixteen(paramInt5);
    arrayOfByte[12] = -86;
    arrayOfByte[13] = (byte)tenToSixteen(255);
    arrayOfByte[14] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getOutletLed(boolean paramBoolean, int paramInt1, int paramInt2)
  {
    /*int i = 1;
    byte[] arrayOfByte = new byte[12];
    arrayOfByte[0] = 102;
    arrayOfByte[i] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 38;
    arrayOfByte[7] = 3;
    if (paramBoolean);
    while (true)
    {
      arrayOfByte[8] = i;
      arrayOfByte[9] = (byte)paramInt1;
      arrayOfByte[10] = (byte)paramInt2;
      arrayOfByte[11] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      return arrayOfByte;
      i = 2;
    }*/
    return null;
  }

  public byte[] getOutletSwich(String paramString, boolean paramBoolean)
  {
    /*byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    if (paramString.equals("usb"))
    {
      arrayOfByte[6] = 35;
      arrayOfByte[7] = 1;
      if (!paramBoolean)
        break label114;
    }
    label114: for (int i = 97; ; i = 98)
    {
      arrayOfByte[8] = i;
      arrayOfByte[9] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      return arrayOfByte;
      arrayOfByte[6] = 36;
      break;
    }*/
    return null;
  }

  public byte[] getOutletSwich(boolean paramBoolean)
  {
    /*int i = 1;
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[i] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 41;
    arrayOfByte[7] = i;
    if (paramBoolean);
    while (true)
    {
      arrayOfByte[8] = i;
      arrayOfByte[9] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      return arrayOfByte;
      i = 0;
    }*/
    return null;
  }

  public byte[] getOutletTimingCmd(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    /*byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 37;
    arrayOfByte[7] = 6;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    if (paramString4.equals("on"))
      arrayOfByte[12] = 1;
    if (paramString4.equals("off"))
      arrayOfByte[12] = 0;
    if (paramString4.equals("timingOff"))
      arrayOfByte[12] = 2;
    if (paramString5.equals("usb"))
      arrayOfByte[13] = 35;
    while (true)
    {
      arrayOfByte[14] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      return arrayOfByte;
      arrayOfByte[13] = 36;
    }*/
    return null;
  }

  public byte[] getPlugOnOffCmd(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 36;
    arrayOfByte[7] = (byte)tenToSixteen(1);
    arrayOfByte[8] = (byte)tenToSixteen(paramInt);
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("全开" + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getPlugsOnOffInfoCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 48;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getPlugsTimingCmd(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2, byte[] paramArrayOfByte)
  {
    /*byte[] arrayOfByte = new byte[24];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 40;
    arrayOfByte[7] = 15;
    arrayOfByte[8] = (byte)paramInt;
    int i;
    int j;
    label97: int k;
    label134: int m;
    label161: int n;
    label181: int i1;
    label374: int i2;
    if (paramBoolean2)
    {
      i = 36;
      arrayOfByte[9] = i;
      if (paramString2.length() <= 0)
        break label523;
      j = 209;
      arrayOfByte[10] = (byte)j;
      arrayOfByte[11] = (byte)toHex(paramString1);
      if (paramString2.length() <= 0)
        break label531;
      k = (byte)tenToSixteen(Integer.parseInt(paramString2));
      arrayOfByte[12] = k;
      if (paramString3.length() <= 0)
        break label537;
      m = (byte)tenToSixteen(Integer.parseInt(paramString3));
      arrayOfByte[13] = m;
      if (paramString4.length() <= 0)
        break label543;
      n = 225;
      arrayOfByte[14] = (byte)n;
      arrayOfByte[15] = (byte)toHex(paramString1);
      if ((paramString4.length() > 0) && (paramString2.length() > 0))
      {
        if (Integer.parseInt(paramString2) > Integer.parseInt(paramString4))
        {
          StringBuilder localStringBuilder1 = new StringBuilder();
          localStringBuilder1.append("0").append(paramString1.substring(2, 8)).append(paramString1.substring(1, 2));
          arrayOfByte[15] = (byte)toHex(localStringBuilder1.toString());
        }
        if ((Integer.parseInt(paramString2) == Integer.parseInt(paramString4)) && (Integer.parseInt(paramString3) > Integer.parseInt(paramString5)))
        {
          StringBuilder localStringBuilder2 = new StringBuilder();
          localStringBuilder2.append("0").append(paramString1.substring(2, 8)).append(paramString1.substring(1, 2));
          arrayOfByte[15] = (byte)toHex(localStringBuilder2.toString());
        }
      }
      if (paramString4.length() <= 0)
        break label551;
      i1 = (byte)tenToSixteen(Integer.parseInt(paramString4));
      arrayOfByte[16] = i1;
      if (paramString5.length() <= 0)
        break label557;
      i2 = (byte)tenToSixteen(Integer.parseInt(paramString5));
      label401: arrayOfByte[17] = i2;
      if (paramBoolean1)
        break label563;
      arrayOfByte[18] = 0;
    }
    while (true)
    {
      arrayOfByte[19] = paramArrayOfByte[0];
      arrayOfByte[20] = paramArrayOfByte[1];
      arrayOfByte[21] = paramArrayOfByte[2];
      arrayOfByte[22] = paramArrayOfByte[3];
      arrayOfByte[23] = -21;
      if (cmdCount > 255)
        cmdCount = 0;
      cmdCount = 1 + cmdCount;
      System.out.println("getPlugsTimingCmd     " + StringUtils.btye2Str(arrayOfByte));
      return arrayOfByte;
      i = 37;
      break;
      label523: j = 208;
      break label97;
      label531: k = 0;
      break label134;
      label537: m = 0;
      break label161;
      label543: n = 224;
      break label181;
      label551: i1 = 0;
      break label374;
      label557: i2 = 0;
      break label401;
      label563: arrayOfByte[18] = 1;
    }*/
    return null;
  }

  public byte[] getQeuryIrTimingOrder()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 82;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getRcTiming(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString1, String paramString2, String paramString3, int paramInt2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    /*int i = 20 + paramArrayOfByte1.length + paramArrayOfByte2.length;
    int j;
    byte[] arrayOfByte;
    int m;
    label94: int n;
    if (paramArrayOfByte2.length > 0)
    {
      j = 4;
      arrayOfByte = new byte[j + i];
      arrayOfByte[0] = 102;
      arrayOfByte[1] = -69;
      arrayOfByte[2] = (byte)cmdCount;
      arrayOfByte[3] = -64;
      arrayOfByte[4] = this.pwd12;
      arrayOfByte[5] = this.pwd34;
      arrayOfByte[6] = 20;
      int k = 11 + paramArrayOfByte1.length + paramArrayOfByte2.length;
      if (paramArrayOfByte2.length <= 0)
        break label268;
      m = 4;
      arrayOfByte[7] = (byte)(m + k);
      arrayOfByte[8] = (byte)paramInt1;
      if (paramArrayOfByte2.length <= 0)
        break label274;
      n = 3;
      label120: arrayOfByte[9] = n;
      arrayOfByte[10] = (byte)toHex(paramString1);
      arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString2));
      arrayOfByte[12] = (byte)tenToSixteen(Integer.parseInt(paramString3));
      arrayOfByte[13] = (byte)paramInt2;
      if (!paramBoolean)
        break label280;
    }
    label268: label274: label280: for (int i1 = 1; ; i1 = 0)
    {
      arrayOfByte[14] = (byte)i1;
      arrayOfByte[15] = paramArrayOfByte3[0];
      arrayOfByte[16] = paramArrayOfByte3[1];
      arrayOfByte[17] = paramArrayOfByte3[2];
      arrayOfByte[18] = paramArrayOfByte3[3];
      if (paramArrayOfByte2.length != 0)
        break label286;
      for (int i4 = 0; i4 < paramArrayOfByte1.length; i4++)
        arrayOfByte[(i4 + 19)] = paramArrayOfByte1[i4];
      j = 0;
      break;
      m = 0;
      break label94;
      n = 2;
      break label120;
    }
    label286: arrayOfByte[19] = (byte)paramArrayOfByte1.length;
    for (int i2 = 0; i2 < paramArrayOfByte1.length; i2++)
      arrayOfByte[(i2 + 20)] = paramArrayOfByte1[i2];
    if (paramArrayOfByte2.length > 0)
    {
      arrayOfByte[(20 + paramArrayOfByte1.length)] = -6;
      arrayOfByte[(21 + paramArrayOfByte1.length)] = -81;
      arrayOfByte[(22 + paramArrayOfByte1.length)] = (byte)paramArrayOfByte2.length;
      for (int i3 = 0; i3 < paramArrayOfByte2.length; i3++)
        arrayOfByte[(i3 + 23 + paramArrayOfByte2.length)] = paramArrayOfByte2[i3];
    }
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    cmdCount = 1 + cmdCount;
    System.out.println("       Timing     cmd        " + StringUtil.byte2Hexstr(arrayOfByte));
    return arrayOfByte;*/
    return null;
  }

  public byte[] getRcTimingTwice(int paramInt1, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, String paramString1, String paramString2, String paramString3, int paramInt2, byte[] paramArrayOfByte3, boolean paramBoolean)
  {
    /*int i = 20 + paramArrayOfByte1.length + paramArrayOfByte2.length;
    int j;
    byte[] arrayOfByte;
    int m;
    label91: int n;
    if (paramArrayOfByte2.length > 0)
    {
      j = 3;
      arrayOfByte = new byte[j + i];
      arrayOfByte[0] = 102;
      arrayOfByte[1] = -69;
      arrayOfByte[2] = (byte)cmdCount;
      arrayOfByte[3] = -64;
      arrayOfByte[4] = this.pwd12;
      arrayOfByte[5] = this.pwd34;
      arrayOfByte[6] = 20;
      int k = 11 + paramArrayOfByte1.length;
      if (paramArrayOfByte2.length <= 0)
        break label296;
      m = 3;
      arrayOfByte[7] = (byte)(m + k);
      arrayOfByte[8] = (byte)paramInt1;
      if (paramArrayOfByte2.length <= 0)
        break label302;
      n = 3;
      label117: arrayOfByte[9] = n;
      arrayOfByte[10] = (byte)toHex(paramString1);
      arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString2));
      arrayOfByte[12] = (byte)tenToSixteen(Integer.parseInt(paramString3));
      arrayOfByte[13] = (byte)paramInt2;
      arrayOfByte[14] = paramArrayOfByte3[0];
      arrayOfByte[15] = paramArrayOfByte3[1];
      arrayOfByte[16] = paramArrayOfByte3[2];
      arrayOfByte[17] = paramArrayOfByte3[3];
      if (!paramBoolean)
        break label308;
    }
    label296: label302: label308: for (int i1 = 1; ; i1 = 0)
    {
      arrayOfByte[14] = (byte)i1;
      arrayOfByte[15] = paramArrayOfByte3[0];
      arrayOfByte[16] = paramArrayOfByte3[1];
      arrayOfByte[17] = paramArrayOfByte3[2];
      arrayOfByte[18] = paramArrayOfByte3[3];
      for (int i2 = 0; i2 < paramArrayOfByte1.length; i2++)
        arrayOfByte[(i2 + 19)] = paramArrayOfByte1[i2];
      j = 0;
      break;
      m = 0;
      break label91;
      n = 2;
      break label117;
    }
    if (paramArrayOfByte2.length > 0)
    {
      arrayOfByte[(19 + paramArrayOfByte1.length)] = -6;
      arrayOfByte[(20 + paramArrayOfByte1.length)] = -81;
      arrayOfByte[(21 + paramArrayOfByte1.length)] = (byte)paramArrayOfByte2.length;
      for (int i3 = 0; i3 < paramArrayOfByte2.length; i3++)
        arrayOfByte[(i3 + 22 + paramArrayOfByte2.length)] = paramArrayOfByte2[i3];
    }
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    System.out.println("       Timing     cmd        " + paramInt1);
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;*/
    return null;
  }

  public byte[] getReqCtSceneCmd(boolean paramBoolean)
  {
    /*int i = 1;
    System.out.println("getReqCtSceneCmd======");
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[i] = -69;
    arrayOfByte[2] = (byte)tenToSixteen(cmdCount);
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 34;
    arrayOfByte[7] = i;
    if (paramBoolean)
      i = 0;
    arrayOfByte[8] = i;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;*/
    return null;
  }

  public byte[] getSaveDeviceNameCmd(String paramString)
  {
    byte[] arrayOfByte1 = paramString.getBytes();
    int i = 9 + arrayOfByte1.length;
    byte[] arrayOfByte2 = new byte[i];
    arrayOfByte2[0] = 102;
    arrayOfByte2[1] = -69;
    arrayOfByte2[2] = (byte)cmdCount;
    arrayOfByte2[3] = -64;
    arrayOfByte2[4] = this.pwd12;
    arrayOfByte2[5] = this.pwd34;
    arrayOfByte2[6] = 32;
    arrayOfByte2[7] = (byte)arrayOfByte1.length;
    for (int j = 0; j < arrayOfByte1.length; j++)
      arrayOfByte2[(j + 8)] = arrayOfByte1[j];
    arrayOfByte2[(i - 1)] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("getSaveDeviceNameCmd : " + StringUtils.btye2Str2(arrayOfByte2));
    return arrayOfByte2;
  }

  public byte[] getSynTimeToDeviceCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    byte[] arrayOfByte = new byte[17];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 22;
    arrayOfByte[7] = 8;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)paramInt2;
    arrayOfByte[10] = (byte)paramInt3;
    arrayOfByte[11] = (byte)paramInt4;
    arrayOfByte[12] = (byte)paramInt5;
    arrayOfByte[13] = (byte)paramInt6;
    arrayOfByte[14] = (byte)paramInt7;
    arrayOfByte[15] = (byte)paramInt8;
    arrayOfByte[16] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getSynTimeToOutletCmd(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    byte[] arrayOfByte = new byte[17];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 38;
    arrayOfByte[7] = 8;
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)paramInt2;
    arrayOfByte[10] = (byte)paramInt3;
    arrayOfByte[11] = (byte)paramInt4;
    arrayOfByte[12] = (byte)paramInt5;
    arrayOfByte[13] = (byte)paramInt6;
    arrayOfByte[14] = (byte)paramInt7;
    arrayOfByte[15] = (byte)paramInt8;
    arrayOfByte[16] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getTimingOffCmd(String paramString1, int paramInt, String paramString2, String paramString3)
  {
    byte[] arrayOfByte = new byte[13];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = 4;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getTimingRcParmas(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[11 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = (byte)(2 + paramArrayOfByte.length);
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = 1;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 10)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    System.out.println("getTimingRcParmas      " + StringUtils.btye2Str2(arrayOfByte));
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getTimingRcParmasTwice(byte[] paramArrayOfByte, int paramInt)
  {
    byte[] arrayOfByte = new byte[11 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = (byte)(2 + paramArrayOfByte.length);
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = 1;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 10)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    System.out.println("getTimingRcParmas      " + StringUtils.btye2Str2(arrayOfByte));
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] getYaoKongTiming(int paramInt1, byte[] paramArrayOfByte1, String paramString1, String paramString2, String paramString3, int paramInt2, byte[] paramArrayOfByte2, boolean paramBoolean)
  {
    /*byte[] arrayOfByte = new byte[19 + paramArrayOfByte1.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 20;
    arrayOfByte[7] = (byte)(10 + paramArrayOfByte1.length);
    arrayOfByte[8] = (byte)paramInt1;
    arrayOfByte[9] = (byte)toHex(paramString1);
    arrayOfByte[10] = (byte)tenToSixteen(Integer.parseInt(paramString2));
    arrayOfByte[11] = (byte)tenToSixteen(Integer.parseInt(paramString3));
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = paramArrayOfByte2[0];
    arrayOfByte[14] = paramArrayOfByte2[1];
    arrayOfByte[15] = paramArrayOfByte2[2];
    arrayOfByte[16] = paramArrayOfByte2[3];
    if (paramBoolean);
    for (int i = 1; ; i = 0)
    {
      arrayOfByte[13] = (byte)i;
      arrayOfByte[14] = paramArrayOfByte2[0];
      arrayOfByte[15] = paramArrayOfByte2[1];
      arrayOfByte[16] = paramArrayOfByte2[2];
      arrayOfByte[17] = paramArrayOfByte2[3];
      for (int j = 0; j < paramArrayOfByte1.length; j++)
        arrayOfByte[(j + 18)] = paramArrayOfByte1[j];
    }
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    System.out.println("testYaokong     send" + StringUtils.btye2Str(paramArrayOfByte2));
    System.out.println(StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;*/
    return null;
  }

  public void handModeNumSeleted(String paramString)
  {
    this.frontModeNum = toHex(paramString.substring(0, 8));
    this.realModeNum = toHex(paramString.substring(8));
  }

  public byte[] hongWaiId(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return new byte[0];
    byte[] arrayOfByte = new byte[9 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 42;
    arrayOfByte[7] = (byte)paramArrayOfByte.length;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 8)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    cmdCount = 1 + cmdCount;
    System.out.println("hongWaiId           " + StringUtils.btye2Str2(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] hongWaiLearn()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 41;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] hongWaiTest(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return new byte[0];
    byte[] arrayOfByte = new byte[9 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 40;
    arrayOfByte[7] = (byte)paramArrayOfByte.length;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 8)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    cmdCount = 1 + cmdCount;
    "".equals(this.temptest);
    return arrayOfByte;
  }

  public byte[] irLearnDevice(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 64;
    arrayOfByte[7] = 6;
    arrayOfByte[8] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte[9] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte[10] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte[11] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    arrayOfByte[12] = (byte)paramInt1;
    arrayOfByte[13] = (byte)paramInt2;
    arrayOfByte[14] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] irLearnLight(int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[16];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 48;
    arrayOfByte[7] = 7;
    arrayOfByte[8] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte[9] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte[10] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte[11] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    arrayOfByte[12] = (byte)paramInt2;
    arrayOfByte[13] = (byte)paramInt1;
    arrayOfByte[14] = (byte)paramInt3;
    arrayOfByte[15] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] irVersion()
  {
    byte[] arrayOfByte = new byte[9];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 45;
    arrayOfByte[7] = 0;
    arrayOfByte[8] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] learnedRcCode(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return new byte[0];
    byte[] arrayOfByte = new byte[8 + paramArrayOfByte.length];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 43;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 7)] = paramArrayOfByte[i];
    arrayOfByte[(-1 + arrayOfByte.length)] = -21;
    cmdCount = 1 + cmdCount;
    "".equals(this.temptest);
    return arrayOfByte;
  }

  public byte[] onOffK1RFAndK2RF(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[15];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 64;
    arrayOfByte[7] = 6;
    arrayOfByte[8] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    arrayOfByte[9] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    arrayOfByte[10] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    arrayOfByte[11] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    arrayOfByte[12] = (byte)paramInt1;
    arrayOfByte[13] = (byte)paramInt2;
    arrayOfByte[14] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public void pwdToHex(String paramString)
  {
    String str1 = paramString.substring(0, 2);
    String str2 = paramString.substring(2);
    this.pwd12 = (byte)strToSixteen(str1);
    this.pwd34 = (byte)strToSixteen(str2);
  }

  public byte[] queryIr$Devices()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 53;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] queryIr$LightOder()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 52;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] queryIr$PanelOder(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 69;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] resetIr$Devices(int paramInt)
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -64;
    arrayOfByte[4] = 0;
    arrayOfByte[5] = 0;
    arrayOfByte[6] = 55;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = (byte)paramInt;
    arrayOfByte[9] = -21;
    cmdCount = 1 + cmdCount;
    return arrayOfByte;
  }

  public byte[] sendUpdataWifiCmd()
  {
    byte[] arrayOfByte = new byte[10];
    arrayOfByte[0] = 102;
    arrayOfByte[1] = -69;
    arrayOfByte[2] = (byte)cmdCount;
    arrayOfByte[3] = -63;
    arrayOfByte[4] = this.pwd12;
    arrayOfByte[5] = this.pwd34;
    arrayOfByte[6] = 63;
    arrayOfByte[7] = 1;
    arrayOfByte[8] = 0;
    arrayOfByte[9] = -21;
    if (cmdCount > 255)
      cmdCount = 0;
    cmdCount = 1 + cmdCount;
    System.out.println("checkRgbwDeviceVersionCmd" + StringUtils.btye2Str(arrayOfByte));
    return arrayOfByte;
  }

  public int strToSixteen(String paramString)
  {
    return Integer.valueOf(paramString, 16).intValue();
  }

  public byte[] subZero(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    for (int i = -1 + paramArrayOfByte.length; ; i--)
    {
      arrayOfByte = null;
      if (i <= 0)
        break;
      if (paramArrayOfByte[i] == 0)
        continue;
      arrayOfByte = new byte[i + 1];
      for (int j = 0; j < i + 1; j++)
        arrayOfByte[j] = paramArrayOfByte[j];
    }
    return arrayOfByte;
  }

  public byte[] synProgram2DeviceCmd(byte[] paramArrayOfByte, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10, byte paramByte11, byte paramByte12)
  {
    byte[] arrayOfByte = new byte[9 + paramArrayOfByte.length];
    arrayOfByte[0] = 16;
    arrayOfByte[1] = -103;
    arrayOfByte[2] = 0;
    arrayOfByte[3] = (byte)paramArrayOfByte.length;
    arrayOfByte[4] = (byte)(9 + paramArrayOfByte.length);
    arrayOfByte[5] = (byte)paramArrayOfByte.length;
    for (int i = 0; i < paramArrayOfByte.length; i++)
      arrayOfByte[(i + 7)] = paramArrayOfByte[i];
    arrayOfByte[(-2 + (9 + paramArrayOfByte.length))] = this.pwd12;
    arrayOfByte[(-1 + (9 + paramArrayOfByte.length))] = 22;
    return arrayOfByte;
  }

  public int tenToSixteen(int paramInt)
  {
    return Integer.valueOf(Integer.toHexString(paramInt), 16).intValue();
  }

  public int toHex(String paramString)
  {
    return Integer.valueOf(Integer.toHexString(Integer.parseInt(paramString, 2)), 16).intValue();
  }

  public byte[] tttttttttttt(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3)
  {
    byte[] arrayOfByte = new byte[13];
    arrayOfByte[9] = (byte)tenToSixteen(paramInt1);
    return arrayOfByte;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.connetion.CmdDateBussiness
 * JD-Core Version:    0.6.0
 */