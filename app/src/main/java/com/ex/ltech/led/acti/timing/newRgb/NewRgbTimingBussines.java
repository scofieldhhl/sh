package com.ex.ltech.led.acti.timing.newRgb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.acti.timing.act.ActTiming;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ActTimingItemVo;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.ModeVo;
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

public class NewRgbTimingBussines
{
  public static final int daysResultCode = 1000;
  public static final int modeResultCode = 2000;
  public static final String tag = "timingVo";
  private List<SceneSysInsideItemVo> InsideVos;
  ActNewRgbTiming actNewRgbTiming;
  private ActTiming actTiming;
  private CmdDateBussiness cmdDateBussiness;
  private List<SceneSysCustomItemVo> customVos;
  Gson gson = new Gson();
  Handler handler = new Handler();
  public boolean isCreateNewTime;
  public boolean isCreateNewTimeOk = true;
  public boolean isNewRgbProduct;
  private boolean isNewTiming;
  boolean isRespTimeOut;
  private List<ActTimingItemVo> itemVos = new ArrayList();
  private int lastClickPosi;
  public List<RepeatDayVo> longNameDayVos = new ArrayList();
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  private Activity pct;
  Handler recDataHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      NewRgbTimingBussines.this.socketManager.closeReadThread();
      String str = paramMessage.obj.toString();
      if (NewRgbTimingBussines.this.isNewTiming)
      {
        List localList = TimingData.getInstance(NewRgbTimingBussines.this.pct).getTimingVos4Sd();
        TimingVo localTimingVo = (TimingVo)localList.get(NewRgbTimingBussines.this.timingPosi);
        localTimingVo.setOrder(Integer.parseInt(str.substring(-6 + str.length(), -4 + str.length()), 16));
        localList.remove(NewRgbTimingBussines.this.timingPosi);
        localList.add(NewRgbTimingBussines.this.timingPosi, localTimingVo);
        TimingData.getInstance(NewRgbTimingBussines.this.pct).saveTimingVos2Sd(localList);
        NewRgbTimingBussines.this.actTiming.upDateData();
      }
      NewRgbTimingBussines.access$102(NewRgbTimingBussines.this, false);
    }
  };
  byte[] resendCmd;
  private List<Integer> seletedDays = new ArrayList();
  private SendCmdListener sendCmdListener;
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;
  private SocketManager socketManager;
  Runnable timeOutThread = new Runnable()
  {
    public void run()
    {
      if ((NewRgbTimingBussines.this.isRespTimeOut) && (NewRgbTimingBussines.this.sendCmdListener != null))
        NewRgbTimingBussines.this.sendCmdListener.onSendFailde();
    }
  };
  private int timingPosi = 0;
  private byte[] uId = new byte[4];
  private TimingVo updateXuhaoTimingVo = new TimingVo();
  private String userIdHexString;
  List<TimingVo> vos = null;
  private ArrayList<Integer> xuHaoList;

  public NewRgbTimingBussines(Activity paramActivity)
  {
    this.pct = paramActivity;
    this.settingSetter = UserFerences.getUserFerences(this.pct);
    this.settingGetter = this.settingSetter.spFerences;
    try
    {
      DeviceManage.getInstance();
      if (DeviceManage.getxDevice().getProductId() == "160fa2af1948f800160fa2af1948f801");
      for (this.isNewRgbProduct = true; ; this.isNewRgbProduct = false)
      {
        this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
        for (int i = this.userIdHexString.length(); i < 8; i++)
          this.userIdHexString = ("0" + this.userIdHexString);
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        this.isNewRgbProduct = false;
      }
      this.uId[0] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
      this.uId[1] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
      this.uId[2] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
      this.uId[3] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
    }
  }

  private int getTime(String paramString)
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat(paramString).format(localDate));
  }

  public void delTimingItem(List<TimingVo> paramList, int paramInt)
  {
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice = DeviceManage.getxDevice();
    byte[] arrayOfByte = this.cmdDateBussiness.getCtrlCtOtherTimingCmd(((TimingVo)paramList.get(paramInt)).getXuHao(), "del");
    this.resendCmd = arrayOfByte;
    localXlinkAgent.sendPipeData(localXDevice, arrayOfByte, this.mySendPipeListener);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), NewRgbTimingBussines.this.resendCmd, NewRgbTimingBussines.this.mySendPipeListener);
      }
    }
    , 50L);
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
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
    this.socketManager.postTask(this.cmdDateBussiness.getDeviceTimingCmd());
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
    localRepeatDayVo1.setSeleted(true);
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

  public void sendTiming(TimingVo paramTimingVo, int paramInt, boolean paramBoolean)
  {
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 3000L);
    this.timingPosi = paramInt;
    this.isNewTiming = paramBoolean;
    List localList1;
    String str3;
    String str4;
    int j;
    int k;
    label140: String str5;
    label222: String str1;
    if (this.isNewTiming)
    {
      localList1 = paramTimingVo.getLongNameDays();
      String str2 = paramTimingVo.getTime();
      str3 = str2.substring(0, str2.indexOf(":"));
      str4 = str2.substring(1 + str2.indexOf(":"));
      if (paramTimingVo.getType() == 1)
      {
        j = 17;
        if (!paramTimingVo.isSwich())
          break label319;
        if (!paramTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
          break label313;
        k = 1;
        str5 = "0";
        if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(R.string.once)))
          break label350;
        str5 = "1";
        for (int i7 = 7; ; i7--)
        {
          if (i7 <= 0)
            break label372;
          int i8 = DateFmtUtil.getWeekOfDate();
          if (i7 != i8)
            break;
          str5 = str5 + "1";
        }
      }
    }
    else
    {
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice1 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness1 = this.cmdDateBussiness;
      int i = paramTimingVo.getXuHao();
      if (paramTimingVo.isSwich())
      {
        str1 = "on";
        label266: localXlinkAgent1.sendPipeData(localXDevice1, localCmdDateBussiness1.getCtrlCtOtherTimingCmd(i, str1), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      }
    }
    label313: label319: label350: label372: int i4;
    label522: label574: label599: label608: 
    do
    {
      return;
      str1 = "off";
      break label266;
      j = 18;
      break;
      k = 0;
      break label140;
      k = 2;
      break label140;
      str5 = str5 + "0";
      break label222;
      XlinkAgent localXlinkAgent5;
      XDevice localXDevice5;
      CmdDateBussiness localCmdDateBussiness5;
      if (((RepeatDayVo)localList1.get(0)).isSeleted())
      {
        str5 = "01111111";
        this.isCreateNewTimeOk = false;
        if ((!paramTimingVo.isSwich()) || (!paramTimingVo.isOffDevice()))
          break label608;
        localXlinkAgent5 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXDevice5 = DeviceManage.getxDevice();
        localCmdDateBussiness5 = this.cmdDateBussiness;
        if (!this.isCreateNewTime)
          break label599;
      }
      for (int i6 = 255; ; i6 = paramTimingVo.getXuHao())
      {
        localXlinkAgent5.sendPipeData(localXDevice5, localCmdDateBussiness5.getOffDeviceCmd(str5, i6, str3, str4, k, paramTimingVo.getSpeed(), this.uId), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        System.out.println("sendTiming-----关机----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
        return;
        int m = -1 + localList1.size();
        if (m > 0)
          if (!((RepeatDayVo)localList1.get(m)).isSeleted())
            break label574;
        for (str5 = str5 + "1"; ; str5 = str5 + "0")
        {
          m--;
          break label522;
          break;
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
          localXlinkAgent4.sendPipeData(localXDevice4, localCmdDateBussiness4.getOffTimingCmd(str5, i5, str3, str4, k, paramTimingVo.getSpeed(), this.uId), this.mySendPipeListener);
          XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
          System.out.println("sendTiming----定时关-----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
          return;
        }
      }
      if (paramTimingVo.getType() != 1)
        continue;
      System.out.println("sendTiming----颜色开-----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
      XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice3 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness3 = this.cmdDateBussiness;
      if (!this.isCreateNewTime)
        break label981;
      i4 = 255;
      localXlinkAgent3.sendPipeData(localXDevice3, localCmdDateBussiness3.getColorTimingCmd(str5, i4, str3, str4, k, j, paramTimingVo.getBrt(), paramTimingVo.getR(), paramTimingVo.getG(), paramTimingVo.getB(), paramTimingVo.getW(), this.uId), this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    }
    while (paramTimingVo.getType() != 0);
    String str6 = "";
    List localList2 = paramTimingVo.getSeletedModes();
    int n = 15;
    label911: if (n > -1)
    {
      int i3 = -1 + localList2.size();
      if ((n > i3) || (!((ModeVo)localList2.get(n)).isSeleted()));
      for (str6 = str6 + "0"; ; str6 = str6 + "1")
      {
        n--;
        break label911;
        label981: i4 = paramTimingVo.getXuHao();
        break;
      }
    }
    System.out.println("sendTiming---模式开-----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
    XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice2 = DeviceManage.getxDevice();
    CmdDateBussiness localCmdDateBussiness2 = this.cmdDateBussiness;
    if (this.isCreateNewTime);
    for (int i1 = 255; ; i1 = paramTimingVo.getXuHao())
    {
      int i2 = paramTimingVo.getSpeed();
      byte[] arrayOfByte = this.uId;
      localXlinkAgent2.sendPipeData(localXDevice2, localCmdDateBussiness2.getModeTimingCmd(str5, i1, str3, str4, k, str6, i2, arrayOfByte), this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      return;
    }
  }

  public void setActNewRgbTiming(ActNewRgbTiming paramActNewRgbTiming)
  {
    this.actNewRgbTiming = paramActNewRgbTiming;
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
      String str = StringUtils.btye2Str(paramArrayOfByte);
      NewRgbTimingBussines.this.pct.runOnUiThread(new Runnable(str)
      {
        public void run()
        {
          System.out.println("hahagetXuHao      rec" + this.val$s);
          if ((this.val$s.equals("00")) && (NewRgbTimingBussines.this.sendCmdListener != null))
            NewRgbTimingBussines.this.sendCmdListener.onSendOk();
          if ((this.val$s.length() == 196) && (this.val$s.indexOf("3BEB") != -1))
          {
            ArrayList localArrayList1 = new ArrayList();
            ArrayList localArrayList2 = new ArrayList();
            this.val$s.substring(12, -4 + this.val$s.length());
            String str1 = this.val$s.substring(12, -4 + this.val$s.length());
            int i1 = 0;
            if (i1 < 10)
            {
              String str2 = str1.substring(0, 18);
              str1 = str1.substring(18, str1.length());
              TimingVo localTimingVo3 = new TimingVo();
              boolean bool1;
              label214: String str3;
              String str4;
              String str5;
              String str8;
              label514: boolean bool2;
              label542: String str9;
              label576: ArrayList localArrayList3;
              if (str2.substring(0, 1).equals("F"))
              {
                bool1 = true;
                localTimingVo3.setIsEnableTiming(bool1);
                localTimingVo3.setUserIdHexString(str2.substring(2, 10));
                str3 = Integer.parseInt(str2.substring(10, 12), 16) + "";
                str4 = Integer.parseInt(str2.substring(12, 14), 16) + "";
                if (str3.length() == 1)
                  str3 = "0" + str3;
                if (str4.length() == 1)
                  str4 = "0" + str4;
                if (str4.length() == 1)
                  str4 = "0" + str4;
                str5 = StringUtils.hexString2binaryString(str2.substring(14, 16));
                System.out.println(" xinQiBinary        " + str5);
                String str6 = str2.substring(16, 17);
                String str7 = str2.substring(17, 18);
                localTimingVo3.setXuHao(i1);
                StringBuilder localStringBuilder = new StringBuilder().append(str3).append(":");
                if (str4.length() != 1)
                  break label695;
                str8 = "0" + str4;
                localTimingVo3.setTime(str8);
                if (!str6.equals("1"))
                  break label702;
                bool2 = true;
                localTimingVo3.setSwich(bool2);
                if (!str7.equals("1"))
                  break label708;
                str9 = NewRgbTimingBussines.this.pct.getString(R.string.on);
                localTimingVo3.setLightStatus(str9);
                localArrayList3 = new ArrayList();
                if (!str5.substring(0, 1).equals("1"))
                  break label728;
                localArrayList3.add(NewRgbTimingBussines.this.pct.getString(R.string.once));
              }
              while (true)
              {
                System.out.printf(str3 + ":" + str4, new Object[0]);
                localTimingVo3.setShotNameDays(localArrayList3);
                localArrayList1.add(localTimingVo3);
                i1++;
                break;
                bool1 = false;
                break label214;
                label695: str8 = str4;
                break label514;
                label702: bool2 = false;
                break label542;
                label708: str9 = NewRgbTimingBussines.this.pct.getString(2131100226);
                break label576;
                label728: if (str5.equals("01111111"))
                {
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100055));
                  continue;
                }
                if (str5.substring(7, 8).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100240));
                if (str5.substring(6, 7).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100467));
                if (str5.substring(5, 6).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100430));
                if (str5.substring(4, 5).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100071));
                if (str5.substring(3, 4).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100066));
                if (str5.substring(2, 3).equals("1"))
                  localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100404));
                if (!str5.substring(1, 2).equals("1"))
                  continue;
                localArrayList3.add(NewRgbTimingBussines.this.pct.getString(2131100394));
              }
            }
            NewRgbTimingBussines.this.isRespTimeOut = false;
            List localList = TimingData.getInstance(NewRgbTimingBussines.this.pct).getTimingVos4Sd();
            for (int i2 = 0; i2 < localArrayList1.size(); i2++)
            {
              if (!((TimingVo)localArrayList1.get(i2)).isEnableTiming())
                continue;
              ((TimingVo)localArrayList1.get(i2)).setIsOther(true);
              localArrayList2.add(localArrayList1.get(i2));
            }
            for (int i3 = 0; i3 < localArrayList2.size(); i3++)
            {
              TimingVo localTimingVo1 = (TimingVo)localArrayList2.get(i3);
              System.out.println("getUserIdHexString = " + localTimingVo1.getUserIdHexString() + "  enableVo.getTime() " + localTimingVo1.getTime() + "   xuhaoSame =" + localTimingVo1.getXuHao());
              for (int i4 = 0; i4 < localList.size(); i4++)
              {
                TimingVo localTimingVo2 = (TimingVo)localList.get(i4);
                if ((!localTimingVo1.getUserIdHexString().equals(NewRgbTimingBussines.this.userIdHexString)) || (localTimingVo2.isOther()) || (localTimingVo2.getXuHao() != localTimingVo1.getXuHao()) || (!localTimingVo2.getTime().equals(localTimingVo1.getTime())))
                  continue;
                localArrayList2.remove(i3);
                localArrayList2.add(i3, localTimingVo2);
              }
            }
            TimingData.getInstance(NewRgbTimingBussines.this.pct).saveTimingVos2Sd(localArrayList2);
            ActNewRgbTiming localActNewRgbTiming = NewRgbTimingBussines.this.actNewRgbTiming;
            1 local1 = new Runnable()
            {
              public void run()
              {
                NewRgbTimingBussines.this.actNewRgbTiming.upDateData();
              }
            };
            localActNewRgbTiming.runOnUiThread(local1);
            System.out.println();
          }
          int i;
          int j;
          label1425: int k;
          if (this.val$s.length() == 20)
          {
            i = 1;
            if (this.val$s.indexOf("3CEB") == -1)
              break label1611;
            j = 1;
            if ((i | j) != 0)
            {
              NewRgbTimingBussines.this.isRespTimeOut = false;
              if (NewRgbTimingBussines.this.sendCmdListener != null)
                NewRgbTimingBussines.this.sendCmdListener.onSendOk();
              Handler localHandler2 = NewRgbTimingBussines.this.handler;
              2 local2 = new Runnable()
              {
                public void run()
                {
                }
              };
              localHandler2.postDelayed(local2, 300L);
            }
            if (this.val$s.length() != 18)
              break label1616;
            k = 1;
            label1517: if (this.val$s.indexOf("A4EB") == -1)
              break label1621;
          }
          int n;
          label1611: label1616: label1621: for (int m = 1; ; m = 0)
          {
            if ((k | m) != 0)
            {
              n = Integer.parseInt(this.val$s.substring(12, 14), 16);
              NewRgbTimingBussines.this.isRespTimeOut = false;
              if (n != 10)
                break label1627;
              if (NewRgbTimingBussines.this.sendCmdListener != null)
                NewRgbTimingBussines.this.sendCmdListener.onTimingFull();
            }
            return;
            i = 0;
            break;
            j = 0;
            break label1425;
            k = 0;
            break label1517;
          }
          label1627: System.out.println("ReturnMyData         " + n);
          if (NewRgbTimingBussines.this.isCreateNewTime)
            NewRgbTimingBussines.this.updateXuhaoTimingVo.setXuHao(n);
          NewRgbTimingBussines.this.saveCacheVos(NewRgbTimingBussines.this.updateXuhaoTimingVo);
          if (NewRgbTimingBussines.this.sendCmdListener != null)
            NewRgbTimingBussines.this.sendCmdListener.onSendOk();
          Handler localHandler1 = NewRgbTimingBussines.this.handler;
          3 local3 = new Runnable()
          {
            public void run()
            {
            }
          };
          localHandler1.postDelayed(local3, 1000L);
          NewRgbTimingBussines.this.isCreateNewTime = false;
          NewRgbTimingBussines.this.isCreateNewTimeOk = true;
        }
      });
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
    }

    public void onStart(int paramInt)
    {
    }
  }

  public static abstract interface SendCmdListener
  {
    public abstract void onSendFailde();

    public abstract void onSendOk();

    public abstract void onTimingFull();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.newRgb.NewRgbTimingBussines
 * JD-Core Version:    0.6.0
 */