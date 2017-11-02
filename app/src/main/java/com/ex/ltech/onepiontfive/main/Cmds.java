package com.ex.ltech.onepiontfive.main;

public class Cmds
{
  public static final int appSender = 1;
  public static final int checksumMethod = 1;
  public static final int del = 255;
  public static final int edit = 68;
  public static final int end = 22;
  public static final int head1 = 90;
  public static final int head2 = 165;
  public static final int newCreate = 17;
  public static final int order = 255;
  public static final int run = 34;
  public static final int scene = 48;
  public static final int sceneSend = 49;
  public static final int stop = 51;

  public static class DeviceType
  {
    public static final int bodySensor = 81;
    public static final int bulb = 79;
    public static final int ctBulb = 70;
    public static final int ctCtrler = 66;
    public static final int dimBulb = 69;
    public static final int dimCtrler = 65;
    public static final int doorSensor = 84;
    public static final int fourWayPanel = 34;
    public static final int mainCtrler = 17;
    public static final int mutiAreaRc = 50;
    public static final int mutiWayPlug = 99;
    public static final int oneAreaRc = 49;
    public static final int oneWayPanel = 37;
    public static final int oneWayPlug = 98;
    public static final int panelCtrler = 33;
    public static final int rc = 97;
    public static final int rgbBulb = 71;
    public static final int rgbCtrler = 67;
    public static final int rgbwBulb = 72;
    public static final int rgbwCtrler = 68;
    public static final int smokeSensor = 83;
    public static final int sunshineSensor = 82;
    public static final int tempTest = 153;
    public static final int threeWayPanel = 35;
    public static final int twoWayPanel = 36;
  }

  public static class ReqCtrlCode
  {
    public static final int DeivceNum = 18;
    public static final int apswitchOnOff = 34;
    public static final int bulbScene = 10;
    public static final int changeName = 45;
    public static final int del = 136;
    public static final int delay = 12;
    public static final int deleteAll = 28;
    public static final int deviceGroupOnoff = 22;
    public static final int deviceOnoff = 21;
    public static final int editMode = 7;
    public static final int groupBright = 54;
    public static final int groupColor = 2;
    public static final int groupMode = 6;
    public static final int groupMusic = 4;
    public static final int groupMutiMode = 9;
    public static final int groupNum = 18;
    public static final int headBeat = 0;
    public static final int moDel = 255;
    public static final int moEdit = 17;
    public static final int moPlay = 1;
    public static final int moStop = 2;
    public static final int mode = 5;
    public static final int music = 3;
    public static final int mutiMode = 8;
    public static final int panelRelationLamp = 41;
    public static final int rc = 97;
    public static final int rcCmd = 20;
    public static final int rcScene = 11;
    public static final int rgbwitchOnOff = 67;
    public static final int roomNum = 17;
    public static final int scan = 16;
    public static final int sensorOnOff = 26;
    public static final int set = 85;
    public static final int singleBright = 53;
    public static final int singleColor = 1;
    public static final int switchOnOff = 22;
    public static final int synTime = 25;
    public static final int timing = 19;
  }

  public static class RespCtrlCode
  {
    public static final int DeivceNum = 146;
    public static final int bulbScene = 138;
    public static final int delay = 140;
    public static final int deviceOnoff = 149;
    public static final int editMode = 135;
    public static final int groupColor = 130;
    public static final int groupMode = 134;
    public static final int groupMusic = 132;
    public static final int groupMutiMode = 137;
    public static final int groupNum = 146;
    public static final int headBeat = 128;
    public static final int mode = 133;
    public static final int music = 131;
    public static final int mutiMode = 136;
    public static final int rcCmd = 148;
    public static final int rcScene = 139;
    public static final int roomNum = 145;
    public static final int scan = 144;
    public static final int singleColor = 129;
    public static final int switchOnOff = 150;
    public static final int timing = 147;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.Cmds
 * JD-Core Version:    0.6.0
 */