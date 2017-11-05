package com.ex.ltech.outlet.timing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ActTimingItemVo;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import com.ex.ltech.led.vo.TimingVo;
import com.ex.ltech.outlet.timing.act.ActTiming;
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
  public static final int modeResultCode = 2000;
  public static final String tag = "timingVo";
  private List<SceneSysInsideItemVo> InsideVos;
  private ActTiming actTiming;
  private CmdDateBussiness cmdDateBussiness;
  private List<SceneSysCustomItemVo> customVos;
  public List<RepeatDayVo> days = new ArrayList();
  Gson gson = new Gson();
  Handler handler = new Handler();
  public boolean isCreateNewTime;
  private boolean isNewTiming;
  boolean isRespTimeOut = true;
  private List<ActTimingItemVo> itemVos = new ArrayList();
  private int lastClickPosi;
  byte[] lastTimeCmd;
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  public List<RepeatDayVo> otherDays = new ArrayList();
  private Activity pct;
  Handler recDataHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      TimingBussines.this.socketManager.closeReadThread();
      String str = paramMessage.obj.toString();
      if (TimingBussines.this.isNewTiming)
      {
        List localList = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
        TimingVo localTimingVo = (TimingVo)localList.get(TimingBussines.this.timingPosi);
        localTimingVo.setOrder(Integer.parseInt(str.substring(-6 + str.length(), -4 + str.length()), 16));
        localList.remove(TimingBussines.this.timingPosi);
        localList.add(TimingBussines.this.timingPosi, localTimingVo);
        TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localList);
        TimingBussines.this.actTiming.upDateData();
      }
      TimingBussines.access$102(TimingBussines.this, false);
    }
  };
  private List<Integer> seletedDays = new ArrayList();
  private SendCmdListener sendCmdListener;
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;
  private SocketManager socketManager;
  Runnable timeOutThread = new Runnable()
  {
    public void run()
    {
      if (TimingBussines.this.isRespTimeOut)
      {
        if (TimingBussines.this.actTiming != null)
          TimingBussines.this.actTiming.sendTimeOut();
        if (TimingBussines.this.sendCmdListener != null)
          TimingBussines.this.sendCmdListener.onSendFailde();
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
    this.settingSetter = UserFerences.getUserFerences(this.pct);
    this.settingGetter = this.settingSetter.spFerences;
    initRepeatDayVos();
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

  private void resetOtherDays()
  {
    ((RepeatDayVo)this.otherDays.get(0)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(1)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(2)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(3)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(4)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(5)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(6)).setSeleted(false);
    ((RepeatDayVo)this.otherDays.get(7)).setSeleted(false);
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
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
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
    this.socketManager.postTask(this.cmdDateBussiness.getDeviceTimingCmd());
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
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
    return this.days;
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
    if (i < this.days.size())
    {
      RepeatDayVo localRepeatDayVo = (RepeatDayVo)this.days.get(i);
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
    this.days.clear();
    this.otherDays.clear();
    RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
    localRepeatDayVo1.setDay(this.pct.getString(2131100055));
    this.days.add(localRepeatDayVo1);
    this.otherDays.add(localRepeatDayVo1);
    RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
    localRepeatDayVo2.setDay(this.pct.getString(2131100240));
    this.days.add(localRepeatDayVo2);
    this.otherDays.add(localRepeatDayVo2);
    RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
    localRepeatDayVo3.setDay(this.pct.getString(2131100467));
    this.days.add(localRepeatDayVo3);
    this.otherDays.add(localRepeatDayVo3);
    RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
    localRepeatDayVo4.setDay(this.pct.getString(2131100430));
    this.days.add(localRepeatDayVo4);
    this.otherDays.add(localRepeatDayVo4);
    RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
    localRepeatDayVo5.setDay(this.pct.getString(2131100071));
    this.days.add(localRepeatDayVo5);
    this.otherDays.add(localRepeatDayVo5);
    RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
    localRepeatDayVo6.setDay(this.pct.getString(2131100066));
    this.days.add(localRepeatDayVo6);
    this.otherDays.add(localRepeatDayVo6);
    RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
    localRepeatDayVo7.setDay(this.pct.getString(2131100404));
    this.days.add(localRepeatDayVo7);
    this.otherDays.add(localRepeatDayVo7);
    RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
    localRepeatDayVo8.setDay(this.pct.getString(2131100394));
    this.days.add(localRepeatDayVo8);
    this.otherDays.add(localRepeatDayVo8);
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
    RepeatDayVo localRepeatDayVo1 = (RepeatDayVo)this.days.get(paramInt);
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
      this.days.remove(paramInt);
      this.days.add(paramInt, localRepeatDayVo1);
      this.lastClickPosi = paramInt;
      return;
      bool = false;
      break;
      label69: if (!((RepeatDayVo)this.days.get(0)).isSeleted())
        continue;
      this.days.remove(0);
      RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
      localRepeatDayVo2.setSeleted(false);
      localRepeatDayVo2.setDay(this.pct.getString(2131100055));
      this.days.add(0, localRepeatDayVo2);
    }
  }

  public void prepareLink()
  {
    this.socketManager = SocketManager.instance();
    this.socketManager.ip = Main.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(this.pct, Main.deviceVo.getPwd());
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
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
    boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
    int i = 0;
    if (!bool)
      i = 1;
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    this.timingPosi = paramInt;
    System.out.println(paramTimingVo.getXuHao() + "sendTiming     sendTiming" + paramTimingVo.isSwich());
    List localList = paramTimingVo.getLongNameDays();
    String str1 = paramTimingVo.getStartTime();
    String str2;
    String str3;
    label162: String str5;
    label192: String str6;
    label217: label244: int k;
    label302: int m;
    if (str1.length() > 0)
    {
      str2 = str1.substring(0, str1.indexOf(":"));
      if (str1.length() <= 0)
        break label355;
      str3 = str1.substring(1 + str1.indexOf(":"));
      String str4 = paramTimingVo.getEndTime();
      if (str4.length() <= 0)
        break label363;
      str5 = str4.substring(0, str4.indexOf(":"));
      if (str4.length() <= 0)
        break label371;
      str6 = str4.substring(1 + str4.indexOf(":"));
      if (!paramTimingVo.isSwich())
        break label382;
      if (!paramTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
        break label379;
      str7 = "0";
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(R.string.once)))
        break label493;
      str7 = "1";
      k = DateFmtUtil.getWeekOfDate();
      if (i != 0)
      {
        if (k != 7)
          break label385;
        k = 1;
      }
      m = 7;
      label306: if (m <= 0)
        break label416;
      if (m != k)
        break label391;
    }
    label385: label391: for (String str7 = str7 + "1"; ; str7 = str7 + "0")
    {
      m--;
      break label306;
      str2 = "";
      break;
      label355: str3 = "";
      break label162;
      label363: str5 = "";
      break label192;
      label371: str6 = "";
      break label217;
      label379: break label244;
      label382: break label244;
      k++;
      break label302;
    }
    label416: System.out.printf("", new Object[0]);
    while (true)
    {
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getPlugsTimingCmd(str7, paramTimingVo.getXuHao(), str2, str3, str5, str6, paramTimingVo.isSwich(), true, this.uId), this.mySendPipeListener);
      return;
      label493: if (!((RepeatDayVo)localList.get(0)).isSeleted())
        break;
      str7 = "01111111";
    }
    int j = -1 + localList.size();
    label529: if (j > 0)
      if (!((RepeatDayVo)localList.get(j)).isSeleted())
        break label581;
    label581: for (str7 = str7 + "1"; ; str7 = str7 + "0")
    {
      j--;
      break label529;
      break;
    }
  }

  public void sendTiming(TimingVo paramTimingVo, int paramInt, boolean paramBoolean)
  {
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
    this.isNewTiming = paramBoolean;
    boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
    int i = 0;
    if (!bool)
      i = 1;
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    List localList;
    String str3;
    String str4;
    label179: String str6;
    label209: String str7;
    label234: int m;
    label261: label319: int n;
    if (this.isNewTiming)
    {
      this.timingPosi = paramInt;
      System.out.println(paramTimingVo.getXuHao() + "sendTiming     sendTiming" + paramTimingVo.isSwich());
      this.isNewTiming = paramBoolean;
      localList = paramTimingVo.getLongNameDays();
      String str2 = paramTimingVo.getStartTime();
      if (str2.length() <= 0)
        break label452;
      str3 = str2.substring(0, str2.indexOf(":"));
      if (str2.length() <= 0)
        break label460;
      str4 = str2.substring(1 + str2.indexOf(":"));
      String str5 = paramTimingVo.getEndTime();
      if (str5.length() <= 0)
        break label468;
      str6 = str5.substring(0, str5.indexOf(":"));
      if (str5.length() <= 0)
        break label476;
      str7 = str5.substring(1 + str5.indexOf(":"));
      if (!paramTimingVo.isSwich())
        break label487;
      if (!paramTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
        break label484;
      str8 = "0";
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(R.string.once)))
        break label598;
      str8 = "1";
      m = DateFmtUtil.getWeekOfDate();
      if (i != 0)
      {
        if (m != 7)
          break label490;
        m = 1;
      }
      n = 7;
      label323: if (n <= 0)
        break label521;
      if (n != m)
        break label496;
    }
    label452: label460: label468: label476: label484: label487: label490: label496: for (String str8 = str8 + "1"; ; str8 = str8 + "0")
    {
      n--;
      break label323;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness = this.cmdDateBussiness;
      int j = paramTimingVo.getXuHao();
      if (paramTimingVo.isSwich());
      for (String str1 = "on"; ; str1 = "off")
      {
        byte[] arrayOfByte = localCmdDateBussiness.getCtrlCtOtherTimingCmd(j, str1);
        this.lastTimeCmd = arrayOfByte;
        localXlinkAgent1.sendPipeData(localXDevice, arrayOfByte, this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      str3 = "";
      break;
      str4 = "";
      break label179;
      str6 = "";
      break label209;
      str7 = "";
      break label234;
      break label261;
      break label261;
      m++;
      break label319;
    }
    label521: System.out.printf("", new Object[0]);
    while (true)
    {
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getPlugsTimingCmd(str8, paramTimingVo.getXuHao(), str3, str4, str6, str7, paramTimingVo.isSwich(), true, this.uId), this.mySendPipeListener);
      return;
      label598: if (!((RepeatDayVo)localList.get(0)).isSeleted())
        break;
      str8 = "01111111";
    }
    int k = -1 + localList.size();
    label634: if (k > 0)
      if (!((RepeatDayVo)localList.get(k)).isSeleted())
        break label686;
    label686: for (str8 = str8 + "1"; ; str8 = str8 + "0")
    {
      k--;
      break label634;
      break;
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
      this.socketManager.postTask(this.cmdDateBussiness.getSynTimeToDeviceCmd(i, j, k, m, i3, n, i1, i2));
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
      String str1;
      ArrayList localArrayList1;
      ArrayList localArrayList2;
      boolean bool2;
      boolean bool3;
      try
      {
        str1 = StringUtils.btye2Str(paramArrayOfByte);
        if ((str1.length() == 256) && (str1.indexOf("3BEB") != -1))
        {
          str1.substring(12, -4 + str1.length());
          String str2 = str1.substring(12, -4 + str1.length());
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
          int i1 = 0;
          if (i1 < 10)
          {
            String str3 = str2.substring(0, 24);
            str2 = str2.substring(24, str2.length());
            TimingVo localTimingVo1 = new TimingVo();
            if (!str3.substring(0, 1).equals("F"))
              break label1968;
            bool2 = true;
            localTimingVo1.setIsEnableTiming(bool2);
            localTimingVo1.setUserIdHexString(str3.substring(2, 10));
            Object localObject = "00000000";
            String str4 = StringUtils.hexString2binaryString(str3.substring(14, 16));
            if (str4.substring(0, 1).equals("1"))
            {
              String str11 = Integer.parseInt(str3.substring(10, 12), 16) + "";
              String str12 = Integer.parseInt(str3.substring(12, 14), 16) + "";
              if (str12.length() == 1)
                str12 = "0" + str12;
              localTimingVo1.setTime(str11 + ":" + str12);
              localObject = str4;
              StringBuilder localStringBuilder2 = new StringBuilder().append(str11).append(":");
              if (str12.length() == 1)
                str12 = "0" + str12;
              localTimingVo1.setStartTime(str12);
            }
            String str5 = StringUtils.hexString2binaryString(str3.substring(20, 22));
            if (str5.substring(0, 1).equals("1"))
            {
              String str9 = Integer.parseInt(str3.substring(16, 18), 16) + "";
              String str10 = Integer.parseInt(str3.substring(18, 20), 16) + "";
              localObject = str5;
              StringBuilder localStringBuilder1 = new StringBuilder().append(str9).append(":");
              if (str10.length() == 1)
                str10 = "0" + str10;
              localTimingVo1.setEndTime(str10);
            }
            String str6 = str3.substring(22, 23);
            String str7 = str3.substring(23, 24);
            localTimingVo1.setXuHao(i1);
            if (!str6.equals("1"))
              break label1974;
            bool3 = true;
            label599: localTimingVo1.setSwich(bool3);
            String str8;
            label630: ArrayList localArrayList3;
            if (str7.equals("1"))
            {
              str8 = TimingBussines.this.pct.getString(R.string.on);
              localTimingVo1.setLightStatus(str8);
              localArrayList3 = new ArrayList();
              TimingBussines.this.resetOtherDays();
              if (!((String)localObject).equals("11111111"))
                break label735;
              ((RepeatDayVo)TimingBussines.this.otherDays.get(0)).setSeleted(true);
            }
            while (true)
            {
              localTimingVo1.setShotNameDays(localArrayList3);
              localArrayList1.add(localTimingVo1);
              localTimingVo1.setLongNameDays(TimingBussines.this.otherDays);
              i1++;
              break;
              str8 = TimingBussines.this.pct.getString(2131100226);
              break label630;
              label735: if (((String)localObject).substring(7, 8).equals("1"))
              {
                localArrayList3.add(TimingBussines.this.pct.getString(2131100240));
                ((RepeatDayVo)TimingBussines.this.otherDays.get(1)).setSeleted(true);
              }
              if (((String)localObject).substring(6, 7).equals("1"))
              {
                localArrayList3.add(TimingBussines.this.pct.getString(2131100467));
                ((RepeatDayVo)TimingBussines.this.otherDays.get(2)).setSeleted(true);
              }
              if (((String)localObject).substring(5, 6).equals("1"))
              {
                ((RepeatDayVo)TimingBussines.this.otherDays.get(3)).setSeleted(true);
                localArrayList3.add(TimingBussines.this.pct.getString(2131100430));
              }
              if (((String)localObject).substring(4, 5).equals("1"))
              {
                localArrayList3.add(TimingBussines.this.pct.getString(2131100071));
                ((RepeatDayVo)TimingBussines.this.otherDays.get(4)).setSeleted(true);
              }
              if (((String)localObject).substring(3, 4).equals("1"))
              {
                localArrayList3.add(TimingBussines.this.pct.getString(2131100066));
                ((RepeatDayVo)TimingBussines.this.otherDays.get(5)).setSeleted(true);
              }
              if (((String)localObject).substring(2, 3).equals("1"))
              {
                localArrayList3.add(TimingBussines.this.pct.getString(2131100404));
                ((RepeatDayVo)TimingBussines.this.otherDays.get(6)).setSeleted(true);
              }
              if (!((String)localObject).substring(1, 2).equals("1"))
                continue;
              localArrayList3.add(TimingBussines.this.pct.getString(2131100394));
              ((RepeatDayVo)TimingBussines.this.otherDays.get(7)).setSeleted(true);
            }
          }
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
      label1174: label1249: label1512: label1774: label2032: 
      while (true)
      {
        return;
        List localList = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
        int i2 = 0;
        int i3;
        int i4;
        int i5;
        boolean bool4;
        int i;
        label1661: int j;
        int k;
        while (i2 < localList.size())
        {
          i2++;
          continue;
          if (i3 < localArrayList1.size())
          {
            if (!((TimingVo)localArrayList1.get(i3)).isEnableTiming())
              break label1986;
            ((TimingVo)localArrayList1.get(i3)).setIsOther(true);
            localArrayList2.add(localArrayList1.get(i3));
            break label1986;
          }
          System.out.println();
          i4 = 0;
          if (i4 < localArrayList2.size())
          {
            TimingVo localTimingVo2 = (TimingVo)localArrayList2.get(i4);
            System.out.println(" getTime" + localTimingVo2.getTime());
            i5 = 0;
            if (i5 >= localList.size())
              break label2004;
            TimingVo localTimingVo3 = (TimingVo)localList.get(i5);
            String str13 = localTimingVo3.getStartTime() + localTimingVo3.getEndTime();
            String str14 = localTimingVo2.getStartTime() + localTimingVo2.getEndTime();
            System.out.println("enableVo   " + localTimingVo2.getTime() + "       " + "myVo" + "   " + localTimingVo3.getTime());
            PrintStream localPrintStream = System.out;
            StringBuilder localStringBuilder3 = new StringBuilder().append("getUserIdHexString = ").append(localTimingVo2.getUserIdHexString().equals(TimingBussines.this.userIdHexString)).append("  isother ").append(localTimingVo3.isOther()).append("   xuhaoSame =");
            if (localTimingVo3.getXuHao() != localTimingVo2.getXuHao())
              break label1998;
            bool4 = true;
            localPrintStream.println(bool4);
            if ((!localTimingVo2.getUserIdHexString().equals(TimingBussines.this.userIdHexString)) || (localTimingVo3.isOther()) || (localTimingVo3.getXuHao() != localTimingVo2.getXuHao()) || (!str13.equals(str14)))
              break label1992;
            localArrayList2.remove(i4);
            localArrayList2.add(i4, localTimingVo3);
            break label1992;
          }
          TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localArrayList2);
          TimingBussines.this.actTiming.upDateData();
          System.out.println();
          TimingBussines.this.handler.removeCallbacks(TimingBussines.this.timeOutThread);
          if (str1.length() != 20)
            break label2010;
          i = 1;
          if (str1.indexOf("3CEB") == -1)
            break label2016;
          j = 1;
          if ((j | i) != 0)
          {
            TimingBussines.this.isRespTimeOut = false;
            if (TimingBussines.this.sendCmdListener != null)
              TimingBussines.this.sendCmdListener.onSendOk();
            Handler localHandler1 = TimingBussines.this.handler;
            1 local1 = new Runnable()
            {
              public void run()
              {
              }
            };
            localHandler1.postDelayed(local1, 1000L);
            TimingBussines.this.handler.removeCallbacks(TimingBussines.this.timeOutThread);
          }
          if (str1.length() != 18)
            break label2022;
          k = 1;
          if (str1.indexOf("A4EB") == -1)
            break label2028;
        }
        for (int m = 1; ; m = 0)
        {
          if ((m | k) == 0)
            break label2032;
          int n = Integer.parseInt(str1.substring(12, 14), 16);
          System.out.println("ReturnMyData         " + n);
          boolean bool1 = TimingBussines.this.isCreateNewTime;
          if (bool1);
          try
          {
            TimingBussines.this.updateXuhaoTimingVo.setXuHao(n);
            TimingBussines.this.saveCacheVos(TimingBussines.this.updateXuhaoTimingVo);
            if (TimingBussines.this.sendCmdListener != null)
              TimingBussines.this.sendCmdListener.onSendOk();
            Handler localHandler2 = TimingBussines.this.handler;
            2 local2 = new Runnable()
            {
              public void run()
              {
              }
            };
            localHandler2.postDelayed(local2, 1000L);
            TimingBussines.this.isCreateNewTime = false;
            TimingBussines.this.handler.removeCallbacks(TimingBussines.this.timeOutThread);
            return;
          }
          catch (Exception localException2)
          {
            while (true)
              localException2.printStackTrace();
          }
          bool2 = false;
          break;
          bool3 = false;
          break label599;
          i3 = 0;
          break label1174;
          i3++;
          break label1174;
          i5++;
          break label1307;
          bool4 = false;
          break label1512;
          i4++;
          break label1249;
          i = 0;
          break label1661;
          j = 0;
          break label1676;
          k = 0;
          break label1774;
        }
      }
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      StringUtils.btye2Str(paramArrayOfByte);
      try
      {
        onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
        TimingBussines.this.isRespTimeOut = false;
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
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
 * Qualified Name:     com.ex.ltech.outlet.timing.TimingBussines
 * JD-Core Version:    0.6.0
 */