package com.ex.ltech.led.acti.timing;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
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
  Gson gson = new Gson();
  Handler handler = new Handler();
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
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;
  private SocketManager socketManager;
  Runnable timeOutThread = new Runnable()
  {
    public void run()
    {
      if (TimingBussines.this.isRespTimeOut)
        Toast.makeText(TimingBussines.this.pct, TimingBussines.this.pct.getString(R.string.add_time_no_ok), 0).show();
    }
  };
  private int timingPosi = 0;
  List<TimingVo> vos = null;
  private ArrayList<Integer> xuHaoList;

  public TimingBussines(Activity paramActivity)
  {
    this.pct = paramActivity;
    this.settingSetter = UserFerences.getUserFerences(this.pct);
    this.settingGetter = this.settingSetter.spFerences;
  }

  private int getTime(String paramString)
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat(paramString).format(localDate));
  }

  public void delTimingItem(List<TimingVo> paramList, int paramInt)
  {
    TimingVo localTimingVo = (TimingVo)TimingData.getInstance(this.pct).getTimingVos4Sd().get(paramInt);
    upDateTimgItemXuHao(localTimingVo.getXuHao());
    paramList.remove(paramInt);
    TimingData.getInstance(this.pct).saveTimingVos2Sd(paramList);
    List localList = localTimingVo.getLongNameDays();
    String str1 = localTimingVo.getTime();
    String str2 = str1.substring(0, str1.indexOf(":"));
    String str3 = str1.substring(1 + str1.indexOf(":"));
    int i;
    label108: int k;
    if (localTimingVo.getType() == 1)
    {
      if (!localTimingVo.isSwich())
        break label192;
      i = 1;
      str4 = "0";
      if (!((String)localTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(R.string.once)))
        break label223;
      str4 = "1";
      k = 7;
      label148: if (k <= 0)
        break label245;
      if (k != DateFmtUtil.getWeekOfDate())
        break label198;
    }
    label192: label198: for (String str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      k--;
      break label148;
      break;
      i = 0;
      break label108;
    }
    label223: if (((RepeatDayVo)localList.get(0)).isSeleted())
    {
      str4 = "01111111";
      label245: if (localTimingVo.getLightStatus().equals(this.pct.getString(2131100226)))
        this.socketManager.openReadThread();
      this.isRespTimeOut = true;
      this.handler.removeCallbacks(this.timeOutThread);
      this.handler.postDelayed(this.timeOutThread, 10000L);
      XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getOffDeviceCmd(str4, localTimingVo.getXuHao(), str2, str3, i, localTimingVo.getSpeed()), this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      return;
    }
    int j = -1 + paramList.size();
    label369: if (j > 0)
      if (!((RepeatDayVo)localList.get(j)).isSeleted())
        break label420;
    label420: for (str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      j--;
      break label369;
      break;
    }
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

  public List<String> getHourDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 24)
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
    this.socketManager = SocketManager.instance();
    this.socketManager.ip = Main.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(this.pct, Main.deviceVo.getPwd());
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
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 10000L);
    this.timingPosi = paramInt;
    this.isNewTiming = paramBoolean;
    if (this.isNewTiming);
    List localList1 = paramTimingVo.getLongNameDays();
    String str1 = paramTimingVo.getTime();
    String str2 = str1.substring(0, str1.indexOf(":"));
    String str3 = str1.substring(1 + str1.indexOf(":"));
    int i;
    int j;
    label133: int i3;
    if (paramTimingVo.getType() == 1)
    {
      i = 17;
      if (!paramTimingVo.isSwich())
        break label231;
      if (!paramTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
        break label225;
      j = 1;
      str4 = "0";
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(R.string.once)))
        break label262;
      str4 = "1";
      i3 = 7;
      label173: if (i3 <= 0)
        break label284;
      int i4 = DateFmtUtil.getWeekOfDate();
      if (i3 != i4)
        break label237;
    }
    label225: label231: label237: for (String str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      i3--;
      break label173;
      i = 18;
      break;
      j = 0;
      break label133;
      j = 2;
      break label133;
    }
    label262: if (((RepeatDayVo)localList1.get(0)).isSeleted())
    {
      str4 = "01111111";
      label284: if ((!paramTimingVo.isSwich()) || (!paramTimingVo.isOffDevice()))
        break label483;
      XlinkAgent localXlinkAgent4 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent4.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getOffDeviceCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, paramTimingVo.getSpeed()), this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
      System.out.println("sendTiming-----关机----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
    }
    label407: label458: label483: 
    do
    {
      return;
      int k = -1 + localList1.size();
      if (k > 0)
        if (!((RepeatDayVo)localList1.get(k)).isSeleted())
          break label458;
      for (str4 = str4 + "1"; ; str4 = str4 + "0")
      {
        k--;
        break label407;
        break;
      }
      if (!paramTimingVo.isSwich())
      {
        XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent3.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getOffTimingCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, paramTimingVo.getSpeed()), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        System.out.println("sendTiming----定时关-----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
        return;
      }
      if (paramTimingVo.getType() != 1)
        continue;
      System.out.println("sendTiming----颜色开-----" + paramTimingVo.getXuHao() + "         " + paramTimingVo.toString());
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getColorTimingCmd(str4, paramTimingVo.getXuHao(), str2, str3, j, i, paramTimingVo.getBrt(), paramTimingVo.getR(), paramTimingVo.getG(), paramTimingVo.getB(), paramTimingVo.getG()), this.mySendPipeListener);
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    }
    while (paramTimingVo.getType() != 0);
    String str5 = "";
    List localList2 = paramTimingVo.getSeletedModes();
    int m = 15;
    if (m > -1)
    {
      int i2 = -1 + localList2.size();
      if ((m > i2) || (!((ModeVo)localList2.get(m)).isSeleted()));
      for (str5 = str5 + "0"; ; str5 = str5 + "1")
      {
        m--;
        break;
      }
    }
    System.out.println("sendTiming---模式开-----" + str5 + "         " + paramTimingVo.toString());
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice = DeviceManage.getxDevice();
    CmdDateBussiness localCmdDateBussiness = this.cmdDateBussiness;
    int n = paramTimingVo.getXuHao();
    int i1 = paramTimingVo.getSpeed();
    localXlinkAgent1.sendPipeData(localXDevice, localCmdDateBussiness.getModeTimingCmd(str4, n, str2, str3, j, str5, i1), this.mySendPipeListener);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public void setActTiming(ActTiming paramActTiming)
  {
    this.actTiming = paramActTiming;
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
      System.out.println("time  onRecvPipeData    " + StringUtils.btye2Str(paramArrayOfByte));
      TimingBussines.this.isRespTimeOut = false;
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      System.out.println("time  onRecvPipeSyncData    " + StringUtils.btye2Str(paramArrayOfByte));
      TimingBussines.this.isRespTimeOut = false;
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.TimingBussines
 * JD-Core Version:    0.6.0
 */