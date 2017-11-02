package com.ex.ltech.bwct.timing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;
import com.ex.ltech.bwct.timing.act.ActTiming;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ActTimingItemVo;
import com.ex.ltech.led.vo.CtSceneVo;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import com.ex.ltech.led.vo.TimingVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimingBussines
{
  public static final int daysResultCode = 1000;
  public static final int jianbianSecResultCode = 3000;
  public static final int modeResultCode = 2000;
  public static final String tag = "timingVo";
  private List<SceneSysInsideItemVo> InsideVos;
  private ActTiming actTiming;
  private CmdDateBussiness cmdDateBussiness;
  private List<SceneSysCustomItemVo> customVos;
  Gson gson = new Gson();
  Handler handler = new Handler();
  public boolean isCreateNewTime;
  public boolean isCreateNewTimeOk = true;
  private boolean isNewTiming;
  boolean isRespTimeOut;
  private List<ActTimingItemVo> itemVos = new ArrayList();
  private int lastClickPosi;
  byte[] lastTimeCmd;
  public List<RepeatDayVo> longNameDayVos = new ArrayList();
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  private Activity pct;
  private List<Integer> seletedDays = new ArrayList();
  private SendCmdListener sendCmdListener;
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;
  private SocketManager socketManager;
  String tTag = "FuckYourMother   ";
  Runnable timeOutThread = new Runnable()
  {
    public void run()
    {
      if (TimingBussines.this.isRespTimeOut)
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), TimingBussines.this.lastTimeCmd, TimingBussines.this.mySendPipeListener);
        TimingBussines.this.handler.removeCallbacks(TimingBussines.this.timeOutThread);
        TimingBussines.this.handler.postDelayed(TimingBussines.this.timeOutThread, 2000L);
      }
    }
  };
  private int timingPosi = 0;
  private byte[] uId = new byte[4];
  private TimingVo updateXuhaoTimingVo;
  private String userIdHexString;
  List<TimingVo> vos = null;
  private ArrayList<Integer> xuHaoList;

  public TimingBussines(Activity paramActivity)
  {
    this.pct = paramActivity;
    this.cmdDateBussiness = new CmdDateBussiness("0000");
    this.settingSetter = UserFerences.getUserFerences(this.pct);
    this.settingGetter = this.settingSetter.spFerences;
    this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
    for (int i = this.userIdHexString.length(); i < 8; i++)
      this.userIdHexString = ("0" + this.userIdHexString);
    this.uId[0] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    this.uId[1] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    this.uId[2] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    this.uId[3] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
  }

  private int getTime(String paramString)
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat(paramString).format(localDate));
  }

  public void closeReSend()
  {
    this.handler.removeCallbacks(this.timeOutThread);
  }

  public void delTimingItem(List<TimingVo> paramList, int paramInt)
  {
    this.isRespTimeOut = true;
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice = DeviceManage.getxDevice();
    byte[] arrayOfByte = this.cmdDateBussiness.getCtrlCtOtherTimingCmd(((TimingVo)paramList.get(paramInt)).getXuHao(), "del");
    this.lastTimeCmd = arrayOfByte;
    localXlinkAgent.sendPipeData(localXDevice, arrayOfByte, this.mySendPipeListener);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public int dp2px(int paramInt)
  {
    return (int)(0.5F + this.pct.getResources().getDisplayMetrics().density * paramInt);
  }

  public TimingVo getCacheTimingVo4Sd()
  {
    return (TimingVo)this.gson.fromJson(this.settingGetter.getString("cacheTimingVo", ""), TimingVo.class);
  }

  public String getDaysHex(List<String> paramList)
  {
    String str1 = "";
    int i = -1 + paramList.size();
    String str2;
    if (i > 0)
    {
      str2 = (String)paramList.get(i);
      if (str2.indexOf(this.pct.getString(2131100055)) != -1)
        str1 = "0111111";
    }
    else
    {
      return str1;
    }
    if (str2.indexOf(this.pct.getString(2131100525)) != -1);
    for (str1 = str1 + "1"; ; str1 = str1 + "0")
    {
      i--;
      break;
    }
  }

  public void getDeviceTiming()
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getDeviceTimingCmd(), this.mySendPipeListener);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public List<String> getHourDate()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 24; i++)
      localArrayList.add("" + i);
    return localArrayList;
  }

  public List<RepeatDayVo> getInitRepeatDayVos()
  {
    initRepeatDayVos();
    return this.longNameDayVos;
  }

  public List<ActTimingItemVo> getItemVos()
  {
    return this.itemVos;
  }

  public List<String> getMinDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 60)
    {
      if (i < 10);
      for (String str = "0" + i; ; str = "" + i)
      {
        localArrayList.add(str);
        i++;
        break;
      }
    }
    return localArrayList;
  }

  public List<String> getShotNameDays()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < this.longNameDayVos.size())
    {
      RepeatDayVo localRepeatDayVo = (RepeatDayVo)this.longNameDayVos.get(i);
      if (localRepeatDayVo.isSeleted())
      {
        if (localRepeatDayVo.getDay().indexOf("星期") == -1)
          break label79;
        localArrayList.add(localRepeatDayVo.getDay().substring(2));
      }
      while (true)
      {
        i++;
        break;
        label79: localArrayList.add(localRepeatDayVo.getDay());
      }
    }
    return localArrayList;
  }

  public List<SceneSysCustomItemVo> getSysCustomListVos()
  {
    this.customVos = new ArrayList();
    SceneSysCustomItemVo localSceneSysCustomItemVo1 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo1.setModeName("11111111");
    localSceneSysCustomItemVo1.setBlingName("非常亮");
    localSceneSysCustomItemVo1.setSeleted(false);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492955)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492981)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492979)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492970)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492889)));
    localSceneSysCustomItemVo1.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo1);
    SceneSysCustomItemVo localSceneSysCustomItemVo2 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo2.setModeName("2222222");
    localSceneSysCustomItemVo2.setBlingName("非常亮");
    localSceneSysCustomItemVo2.setSeleted(false);
    localSceneSysCustomItemVo2.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo2);
    SceneSysCustomItemVo localSceneSysCustomItemVo3 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo3.setModeName("3333333");
    localSceneSysCustomItemVo3.setBlingName("非常亮");
    localSceneSysCustomItemVo3.setSeleted(false);
    localSceneSysCustomItemVo3.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo3);
    SceneSysCustomItemVo localSceneSysCustomItemVo4 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo4.setModeName("3333333");
    localSceneSysCustomItemVo4.setBlingName("非常亮");
    localSceneSysCustomItemVo4.setSeleted(false);
    localSceneSysCustomItemVo4.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo4);
    return this.customVos;
  }

  public int getTimgItemXuHao()
  {
    this.xuHaoList = TimingData.getInstance(this.pct).getTimingDataXuHao4Sd();
    if (this.xuHaoList == null)
    {
      this.xuHaoList = new ArrayList();
      for (int j = 0; j < 10; j++)
        this.xuHaoList.add(Integer.valueOf(-1));
    }
    for (int i = 0; i < 10; i++)
    {
      if (((Integer)this.xuHaoList.get(i)).intValue() != -1)
        continue;
      this.xuHaoList.remove(i);
      this.xuHaoList.add(i, Integer.valueOf(i));
      TimingData.getInstance(this.pct).saveTimingDataXuHao2Sd(this.xuHaoList);
      return i;
    }
    return 0;
  }

  public TimingVo getTimingVo(int paramInt)
  {
    try
    {
      TimingVo localTimingVo = (TimingVo)getTimingVos().get(paramInt);
      return localTimingVo;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public List<TimingVo> getTimingVos()
  {
    try
    {
      this.vos = ((List)this.gson.fromJson(this.settingGetter.getString("timingVo", ""), new TypeToken()
      {
      }
      .getType()));
      return this.vos;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      while (true)
        localJsonSyntaxException.printStackTrace();
    }
  }

  public void initRepeatDayVos()
  {
    this.longNameDayVos.clear();
    RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
    localRepeatDayVo1.setDay(this.pct.getString(2131100055));
    this.longNameDayVos.add(localRepeatDayVo1);
    RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
    localRepeatDayVo2.setDay(this.pct.getString(2131100003));
    this.longNameDayVos.add(localRepeatDayVo2);
    RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
    localRepeatDayVo3.setDay(this.pct.getString(2131100004));
    this.longNameDayVos.add(localRepeatDayVo3);
    RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
    localRepeatDayVo4.setDay(this.pct.getString(2131100005));
    this.longNameDayVos.add(localRepeatDayVo4);
    RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
    localRepeatDayVo5.setDay(this.pct.getString(2131100006));
    this.longNameDayVos.add(localRepeatDayVo5);
    RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
    localRepeatDayVo6.setDay(this.pct.getString(2131100007));
    this.longNameDayVos.add(localRepeatDayVo6);
    RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
    localRepeatDayVo7.setDay(this.pct.getString(2131100008));
    this.longNameDayVos.add(localRepeatDayVo7);
    RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
    localRepeatDayVo8.setDay(this.pct.getString(2131100009));
    this.longNameDayVos.add(localRepeatDayVo8);
  }

  public void onCustomItemClick(int paramInt)
  {
    SceneSysCustomItemVo localSceneSysCustomItemVo = (SceneSysCustomItemVo)this.customVos.get(paramInt);
    if (!localSceneSysCustomItemVo.isSeleted());
    for (boolean bool = true; ; bool = false)
    {
      localSceneSysCustomItemVo.setSeleted(bool);
      this.customVos.remove(paramInt);
      this.customVos.add(paramInt, localSceneSysCustomItemVo);
      return;
    }
  }

  public void onRepeatDayItemClick(int paramInt)
  {
    RepeatDayVo localRepeatDayVo1 = (RepeatDayVo)this.longNameDayVos.get(paramInt);
    boolean bool;
    if (!localRepeatDayVo1.isSeleted())
    {
      bool = true;
      localRepeatDayVo1.setSeleted(bool);
      if (paramInt != 0)
        break label69;
      initRepeatDayVos();
    }
    while (true)
    {
      this.longNameDayVos.remove(paramInt);
      this.longNameDayVos.add(paramInt, localRepeatDayVo1);
      this.lastClickPosi = paramInt;
      return;
      bool = false;
      break;
      label69: if (!((RepeatDayVo)this.longNameDayVos.get(0)).isSeleted())
        continue;
      this.longNameDayVos.remove(0);
      RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
      localRepeatDayVo2.setSeleted(false);
      localRepeatDayVo2.setDay(this.pct.getString(2131100055));
      this.longNameDayVos.add(0, localRepeatDayVo2);
    }
  }

  public void prepareLink()
  {
    this.cmdDateBussiness = new CmdDateBussiness("0000");
  }

  public void reSortTimingData(List<TimingVo> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < paramList.size())
    {
      TimingVo localTimingVo = (TimingVo)paramList.get(i);
      if (localTimingVo.isOther())
      {
        localTimingVo.setIsShowMineTiming(false);
        localTimingVo.setIsShowOtherTiming(false);
        localArrayList2.add(localTimingVo);
      }
      while (true)
      {
        i++;
        break;
        localTimingVo.setIsShowMineTiming(false);
        localTimingVo.setIsShowOtherTiming(false);
        localArrayList1.add(localTimingVo);
      }
    }
    paramList.clear();
    if (localArrayList1.size() > 0)
      ((TimingVo)localArrayList1.get(0)).setIsShowMineTiming(true);
    if (localArrayList2.size() > 0)
      ((TimingVo)localArrayList2.get(0)).setIsShowOtherTiming(true);
    paramList.addAll(localArrayList1);
    paramList.addAll(localArrayList2);
  }

  public void removeXlinkListener()
  {
    XlinkAgent.getInstance().removeListener(this.myXlinkNetListener);
  }

  public void saveCacheVos(TimingVo paramTimingVo)
  {
    this.settingSetter.putValue("cacheTimingVo", this.gson.toJson(paramTimingVo));
  }

  public void sendEditTiming(TimingVo paramTimingVo, int paramInt)
  {
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    this.timingPosi = paramInt;
    System.out.println(this.timingPosi);
    List localList1 = paramTimingVo.getLongNameDays();
    String str1 = paramTimingVo.getTime();
    String str2 = str1.substring(0, str1.indexOf(":"));
    String str3 = str1.substring(1 + str1.indexOf(":"));
    boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
    int i = 0;
    if (!bool)
      i = 1;
    int j;
    label131: int i2;
    label189: int i3;
    if (paramTimingVo.getType() == 1)
    {
      if (!paramTimingVo.isSwich())
        break label243;
      if (!paramTimingVo.getLightStatus().equals(this.pct.getString(2131100232)))
        break label237;
      j = 1;
      str4 = "0";
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label464;
      str4 = "1";
      i2 = DateFmtUtil.getWeekOfDate();
      if (i != 0)
      {
        if (i2 != 7)
          break label249;
        i2 = 1;
      }
      i3 = 7;
      label193: if (i3 <= 0)
        break label280;
      if (i3 != i2)
        break label255;
    }
    label237: label243: label249: label255: for (String str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      i3--;
      break label193;
      break;
      j = 0;
      break label131;
      j = 2;
      break label131;
      i2++;
      break label189;
    }
    label280: System.out.printf("", new Object[0]);
    this.isCreateNewTimeOk = false;
    System.out.println("seletedDaysHex  " + str4);
    if ((paramTimingVo.isSwich()) && (paramTimingVo.isOffDevice()))
    {
      XlinkAgent localXlinkAgent4 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice4 = DeviceManage.getxDevice();
      byte[] arrayOfByte4 = this.cmdDateBussiness.getCtOffTimingCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, paramTimingVo.getCtGradualTime(), paramTimingVo.getBrt(), paramTimingVo.getC(), paramTimingVo.getW(), this.uId);
      this.lastTimeCmd = arrayOfByte4;
      localXlinkAgent4.sendPipeData(localXDevice4, arrayOfByte4, this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      System.out.println("sendTiming-----设备关机----hour    " + str2 + "      min   " + str3);
    }
    label464: label498: 
    do
    {
      return;
      if (((RepeatDayVo)localList1.get(0)).isSeleted())
      {
        str4 = "01111111";
        break;
      }
      int k = -1 + localList1.size();
      if (k > 0)
        if (!((RepeatDayVo)localList1.get(k)).isSeleted())
          break label549;
      for (str4 = str4 + "1"; ; str4 = str4 + "0")
      {
        k--;
        break label498;
        break;
      }
      if (!paramTimingVo.isSwich())
      {
        XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        XDevice localXDevice3 = DeviceManage.getxDevice();
        byte[] arrayOfByte3 = this.cmdDateBussiness.getCtTimingOffCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, paramTimingVo.getSpeed(), this.uId);
        this.lastTimeCmd = arrayOfByte3;
        localXlinkAgent3.sendPipeData(localXDevice3, arrayOfByte3, this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        System.out.println("sendTiming----关定时-----hour    " + str2 + "      min   " + str3);
        return;
      }
      if (paramTimingVo.getType() != 1)
        continue;
      System.out.println("sendTiming----颜色-----hour    " + str2 + "      min   " + str3);
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice2 = DeviceManage.getxDevice();
      byte[] arrayOfByte2 = this.cmdDateBussiness.getCtColorTimingCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, paramTimingVo.getCtGradualTime(), paramTimingVo.getBrt(), paramTimingVo.getC(), paramTimingVo.getW(), this.uId);
      this.lastTimeCmd = arrayOfByte2;
      localXlinkAgent2.sendPipeData(localXDevice2, arrayOfByte2, this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    }
    while (paramTimingVo.getType() != 0);
    label549: String str5 = "";
    List localList2 = paramTimingVo.getSeletedCtScenes();
    int m = 0;
    for (int n = 0; n < 8; n++)
    {
      if (!((CtSceneVo)localList2.get(n)).isEdit())
        continue;
      m = n + 1;
    }
    int i1 = 15;
    if (i1 > -1)
    {
      if ((i1 > -1 + localList2.size()) || (!((CtSceneVo)localList2.get(i1)).isEdit()));
      for (str5 = str5 + "0"; ; str5 = str5 + "1")
      {
        i1--;
        break;
      }
    }
    System.out.println("sendTiming---模式-----hour    " + str2 + "      min   " + str3);
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice1 = DeviceManage.getxDevice();
    byte[] arrayOfByte1 = this.cmdDateBussiness.getCtSceneTimingCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, m, paramTimingVo.getCtGradualTime(), this.uId);
    this.lastTimeCmd = arrayOfByte1;
    localXlinkAgent1.sendPipeData(localXDevice1, arrayOfByte1, this.mySendPipeListener);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public void sendTiming(TimingVo paramTimingVo, int paramInt, boolean paramBoolean)
  {
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    this.timingPosi = paramInt;
    System.out.println(this.timingPosi);
    this.isNewTiming = paramBoolean;
    List localList1;
    String str3;
    String str4;
    int k;
    label144: int i7;
    label202: int i8;
    if (this.isNewTiming)
    {
      localList1 = paramTimingVo.getLongNameDays();
      String str2 = paramTimingVo.getTime();
      str3 = str2.substring(0, str2.indexOf(":"));
      str4 = str2.substring(1 + str2.indexOf(":"));
      boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
      int j = 0;
      if (!bool)
        j = 1;
      if (paramTimingVo.getType() != 1)
        break label335;
      if (!paramTimingVo.isSwich())
        break label344;
      if (!paramTimingVo.getLightStatus().equals(this.pct.getString(2131100232)))
        break label338;
      k = 1;
      str5 = "0";
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label579;
      str5 = "1";
      i7 = DateFmtUtil.getWeekOfDate();
      if (j != 0)
      {
        if (i7 != 7)
          break label350;
        i7 = 1;
      }
      i8 = 7;
      label206: if (i8 <= 0)
        break label381;
      if (i8 != i7)
        break label356;
    }
    label335: label338: label344: label350: label356: for (String str5 = str5 + "1"; ; str5 = str5 + "0")
    {
      i8--;
      break label206;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice1 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness1 = this.cmdDateBussiness;
      int i = paramTimingVo.getXuHao();
      if (paramTimingVo.isSwich());
      for (String str1 = "on"; ; str1 = "off")
      {
        byte[] arrayOfByte1 = localCmdDateBussiness1.getCtrlCtOtherTimingCmd(i, str1);
        this.lastTimeCmd = arrayOfByte1;
        localXlinkAgent1.sendPipeData(localXDevice1, arrayOfByte1, this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      break;
      k = 0;
      break label144;
      k = 2;
      break label144;
      i7++;
      break label202;
    }
    label381: System.out.printf("", new Object[0]);
    this.isCreateNewTimeOk = false;
    System.out.println("seletedDaysHex  " + str5);
    if ((paramTimingVo.isSwich()) && (paramTimingVo.isOffDevice()))
    {
      XlinkAgent localXlinkAgent5 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice5 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness5 = this.cmdDateBussiness;
      if (this.isCreateNewTime);
      for (int i6 = 255; ; i6 = paramTimingVo.getXuHao())
      {
        byte[] arrayOfByte5 = localCmdDateBussiness5.getCtOffTimingCmd(str5, i6, str3, str4, k, paramTimingVo.getCtGradualTime(), paramTimingVo.getBrt(), paramTimingVo.getC(), paramTimingVo.getW(), this.uId);
        this.lastTimeCmd = arrayOfByte5;
        localXlinkAgent5.sendPipeData(localXDevice5, arrayOfByte5, this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        System.out.println("sendTiming-----设备关机----hour    " + str3 + "      min   " + str4);
        return;
        label579: if (((RepeatDayVo)localList1.get(0)).isSeleted())
        {
          str5 = "01111111";
          break;
        }
        int m = -1 + localList1.size();
        label615: if (m > 0)
          if (!((RepeatDayVo)localList1.get(m)).isSeleted())
            break label667;
        label667: for (str5 = str5 + "1"; ; str5 = str5 + "0")
        {
          m--;
          break label615;
          break;
        }
      }
    }
    if (!paramTimingVo.isSwich())
    {
      XlinkAgent localXlinkAgent4 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice4 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness4 = this.cmdDateBussiness;
      if (this.isCreateNewTime);
      for (int i5 = 255; ; i5 = paramTimingVo.getXuHao())
      {
        byte[] arrayOfByte4 = localCmdDateBussiness4.getCtTimingOffCmd(str5, i5, str3, str4, k, paramTimingVo.getSpeed(), this.uId);
        this.lastTimeCmd = arrayOfByte4;
        localXlinkAgent4.sendPipeData(localXDevice4, arrayOfByte4, this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        System.out.println("sendTiming----关定时-----hour    " + str3 + "      min   " + str4);
        return;
      }
    }
    XlinkAgent localXlinkAgent3;
    XDevice localXDevice3;
    CmdDateBussiness localCmdDateBussiness3;
    if (paramTimingVo.getType() == 1)
    {
      System.out.println("sendTiming----颜色-----hour    " + str3 + "      min   " + str4);
      localXlinkAgent3 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXDevice3 = DeviceManage.getxDevice();
      localCmdDateBussiness3 = this.cmdDateBussiness;
      if (!this.isCreateNewTime)
        break label1049;
    }
    String str6;
    List localList2;
    int n;
    label1049: for (int i4 = 255; ; i4 = paramTimingVo.getXuHao())
    {
      byte[] arrayOfByte3 = localCmdDateBussiness3.getCtColorTimingCmd(str5, i4, str3, str4, k, paramTimingVo.getCtGradualTime(), paramTimingVo.getBrt(), paramTimingVo.getC(), paramTimingVo.getW(), this.uId);
      this.lastTimeCmd = arrayOfByte3;
      localXlinkAgent3.sendPipeData(localXDevice3, arrayOfByte3, this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      if (paramTimingVo.getType() != 0)
        break;
      str6 = "";
      localList2 = paramTimingVo.getSeletedCtScenes();
      n = 0;
      for (int i1 = 0; i1 < 8; i1++)
      {
        if (!((CtSceneVo)localList2.get(i1)).isEdit())
          continue;
        n = i1 + 1;
      }
    }
    int i2 = 15;
    if (i2 > -1)
    {
      if ((i2 > -1 + localList2.size()) || (!((CtSceneVo)localList2.get(i2)).isEdit()));
      for (str6 = str6 + "0"; ; str6 = str6 + "1")
      {
        i2--;
        break;
      }
    }
    System.out.println("sendTiming---模式-----hour    " + str3 + "      min   " + str4);
    XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice2 = DeviceManage.getxDevice();
    CmdDateBussiness localCmdDateBussiness2 = this.cmdDateBussiness;
    if (this.isCreateNewTime);
    for (int i3 = 255; ; i3 = paramTimingVo.getXuHao())
    {
      byte[] arrayOfByte2 = localCmdDateBussiness2.getCtSceneTimingCmd(str5, i3, str3, str4, k, n, paramTimingVo.getCtGradualTime(), this.uId);
      this.lastTimeCmd = arrayOfByte2;
      localXlinkAgent2.sendPipeData(localXDevice2, arrayOfByte2, this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      return;
    }
  }

  public void setActTiming(ActTiming paramActTiming)
  {
    this.actTiming = paramActTiming;
  }

  public void setSendCmdListener(SendCmdListener paramSendCmdListener)
  {
    this.sendCmdListener = paramSendCmdListener;
  }

  public void synTime2Device()
  {
    new String[] { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
    int i = getTime("yyyy") / 100;
    int j = getTime("yyyy") - i * 100;
    int k = getTime("MM");
    int m = getTime("dd");
    int n = getTime("HH");
    int i1 = getTime("mm");
    int i2 = getTime("ss");
    int i3 = 1;
    Calendar localCalendar = Calendar.getInstance();
    localCalendar.setTime(new Date(System.currentTimeMillis()));
    switch (localCalendar.get(7))
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice = DeviceManage.getxDevice();
      byte[] arrayOfByte = this.cmdDateBussiness.getSynTimeToDeviceCmd(i, j, k, m, i3, n, i1, i2);
      this.lastTimeCmd = arrayOfByte;
      localXlinkAgent.sendPipeData(localXDevice, arrayOfByte, this.mySendPipeListener);
      return;
      System.out.println("星期日");
      i3 = 1;
      continue;
      System.out.println("星期一");
      i3 = 2;
      continue;
      System.out.println("星期二");
      i3 = 3;
      continue;
      System.out.println("星期三");
      i3 = 4;
      continue;
      System.out.println("星期四");
      i3 = 5;
      continue;
      System.out.println("星期五");
      i3 = 6;
      continue;
      System.out.println("星期六");
      i3 = 7;
    }
  }

  public void upDateTimgItemXuHao(int paramInt)
  {
    this.xuHaoList = TimingData.getInstance(this.pct).getTimingDataXuHao4Sd();
    for (int i = 0; i < 10; i++)
    {
      if (paramInt != ((Integer)this.xuHaoList.get(i)).intValue())
        continue;
      this.xuHaoList.remove(i);
      this.xuHaoList.add(i, Integer.valueOf(-1));
      System.out.println("#$%^&*()    ------------------    " + paramInt);
      TimingData.getInstance(this.pct).saveTimingDataXuHao2Sd(this.xuHaoList);
    }
  }

  class MySendPipeListener extends SendPipeListener
  {
    MySendPipeListener()
    {
    }

    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  }

  class MyXlinkNetListener
    implements XlinkNetListener
  {
    MyXlinkNetListener()
    {
    }

    public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
    {
    }

    public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
    {
    }

    public void onDisconnect(int paramInt)
    {
      if (TimingBussines.this.sendCmdListener != null)
        TimingBussines.this.sendCmdListener.onSendFailde();
    }

    public void onEventNotify(EventNotify paramEventNotify)
    {
    }

    public void onLocalDisconnect(int paramInt)
    {
    }

    public void onLogin(int paramInt)
    {
    }

    public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      System.out.println(str1 + "   midData");
      System.out.println();
      if ((str1.length() == 196) && (str1.indexOf("3BEB") != -1) && (TimingBussines.this.isCreateNewTimeOk))
      {
        String str2 = str1.substring(12, -4 + str1.length());
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        int i1 = 0;
        if (i1 < 10)
        {
          String str3 = str2.substring(0, 18);
          str2 = str2.substring(18, str2.length());
          TimingVo localTimingVo3 = new TimingVo();
          boolean bool1;
          label166: String str5;
          String str6;
          String str7;
          String str10;
          label439: boolean bool2;
          label467: String str11;
          label498: ArrayList localArrayList3;
          if (str3.substring(0, 1).equals("F"))
          {
            bool1 = true;
            localTimingVo3.setIsEnableTiming(bool1);
            String str4 = str3.substring(2, 10);
            System.out.println(str3 + " 7654321");
            str5 = Integer.parseInt(str3.substring(10, 12), 16) + "";
            str6 = Integer.parseInt(str3.substring(12, 14), 16) + "";
            if (str5.length() == 1)
              str5 = "0" + str5;
            if (str6.length() == 1)
              str6 = "0" + str6;
            str7 = StringUtils.hexString2binaryString(str3.substring(14, 16));
            String str8 = str3.substring(16, 17);
            String str9 = str3.substring(17, 18);
            localTimingVo3.setXuHao(i1);
            localTimingVo3.setUserIdHexString(str4);
            StringBuilder localStringBuilder = new StringBuilder().append(str5).append(":");
            if (str6.length() != 1)
              break label644;
            str10 = "0" + str6;
            localTimingVo3.setTime(str10);
            if (!str8.equals("1"))
              break label651;
            bool2 = true;
            localTimingVo3.setSwich(bool2);
            if (!str9.equals("1"))
              break label657;
            str11 = TimingBussines.this.pct.getString(2131100232);
            localTimingVo3.setLightStatus(str11);
            localArrayList3 = new ArrayList();
            if (!str7.substring(0, 1).equals("1"))
              break label674;
            localArrayList3.add(TimingBussines.this.pct.getString(2131100239));
          }
          while (true)
          {
            if (str7.equals("00000000"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100240));
            System.out.printf(str5 + ":" + str6, new Object[0]);
            localTimingVo3.setShotNameDays(localArrayList3);
            localArrayList1.add(localTimingVo3);
            i1++;
            break;
            bool1 = false;
            break label166;
            label644: str10 = str6;
            break label439;
            label651: bool2 = false;
            break label467;
            label657: str11 = TimingBussines.this.pct.getString(2131100226);
            break label498;
            label674: if (str7.equals("01111111"))
            {
              localArrayList3.add(TimingBussines.this.pct.getString(2131100055));
              continue;
            }
            if (str7.substring(7, 8).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100240));
            if (str7.substring(6, 7).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100467));
            if (str7.substring(5, 6).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100430));
            if (str7.substring(4, 5).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100071));
            if (str7.substring(3, 4).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100066));
            if (str7.substring(2, 3).equals("1"))
              localArrayList3.add(TimingBussines.this.pct.getString(2131100404));
            if (!str7.substring(1, 2).equals("1"))
              continue;
            localArrayList3.add(TimingBussines.this.pct.getString(2131100394));
          }
        }
        List localList2 = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
        for (int i2 = 0; i2 < localList2.size(); i2++)
          System.out.println(TimingBussines.this.tTag + "MyVo = ---------------     " + ((TimingVo)localList2.get(i2)).getXuHao() + "     ------------" + ((TimingVo)localList2.get(i2)).isOther());
        for (int i3 = 0; i3 < localArrayList1.size(); i3++)
        {
          if (!((TimingVo)localArrayList1.get(i3)).isEnableTiming())
            continue;
          ((TimingVo)localArrayList1.get(i3)).setIsOther(true);
          localArrayList2.add(localArrayList1.get(i3));
        }
        for (int i4 = 0; i4 < localArrayList2.size(); i4++)
          System.out.println(TimingBussines.this.tTag + "enableVo = ---------------     " + ((TimingVo)localArrayList2.get(i4)).getUserIdHexString() + "     ------------");
        for (int i5 = 0; i5 < localArrayList2.size(); i5++)
        {
          TimingVo localTimingVo1 = (TimingVo)localArrayList2.get(i5);
          System.out.println(" getTime" + localTimingVo1.getTime());
          for (int i6 = 0; i6 < localList2.size(); i6++)
          {
            TimingVo localTimingVo2 = (TimingVo)localList2.get(i6);
            if ((!localTimingVo1.getUserIdHexString().equals(TimingBussines.this.userIdHexString)) || (localTimingVo2.isOther()) || (localTimingVo2.getXuHao() != localTimingVo1.getXuHao()) || (!localTimingVo2.getTime().equals(localTimingVo1.getTime())))
              continue;
            localTimingVo2.setSwich(localTimingVo1.isSwich());
            localArrayList2.remove(i5);
            localArrayList2.add(i5, localTimingVo2);
          }
        }
        TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localArrayList2);
        TimingBussines.this.actTiming.upDateData();
        System.out.println();
      }
      int i;
      int j;
      label1459: int k;
      if (str1.length() == 20)
      {
        i = 1;
        if (str1.indexOf("3CEB") == -1)
          break label1716;
        j = 1;
        if ((i | j) != 0)
        {
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
          TimingBussines.this.getDeviceTiming();
        }
        if (str1.length() != 18)
          break label1722;
        k = 1;
        label1509: if (str1.indexOf("A4EB") == -1)
          break label1728;
      }
      label1716: label1722: label1728: for (int m = 1; ; m = 0)
      {
        if ((k | m) != 0)
        {
          int n = Integer.parseInt(str1.substring(12, 14), 16);
          System.out.println("ReturnMyData         " + n);
          TimingBussines.this.isRespTimeOut = false;
          if (TimingBussines.this.isCreateNewTime)
          {
            TimingBussines.this.updateXuhaoTimingVo.setXuHao(n);
            TimingBussines.this.saveCacheVos(TimingBussines.this.updateXuhaoTimingVo);
            List localList1 = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
            localList1.add(TimingBussines.this.updateXuhaoTimingVo);
            TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localList1);
          }
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
          TimingBussines.this.getDeviceTiming();
          TimingBussines.this.isCreateNewTime = false;
          TimingBussines.this.isCreateNewTimeOk = true;
        }
        return;
        i = 0;
        break;
        j = 0;
        break label1459;
        k = 0;
        break label1509;
      }
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
    }

    public void onStart(int paramInt)
    {
    }
  }

  public static abstract interface SendCmdListener
  {
    public abstract void onSendFailde();

    public abstract void onSendOk();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.bwct.timing.TimingBussines
 * JD-Core Version:    0.6.0
 */