package com.ex.ltech.remote.control.time;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ActTimingItemVo;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import com.ex.ltech.remote.control.HongWaiMain;
import com.ex.ltech.remote.control.time.act.ActTiming;
import com.ex.ltech.remote.control.vo.YaokongTimingVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETDeviceDVD;
import et.song.etclass.ETDeviceFANS;
import et.song.etclass.ETDeviceIPTV;
import et.song.etclass.ETDevicePJT;
import et.song.etclass.ETDeviceSTB;
import et.song.etclass.ETDeviceTV;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
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
  Gson gson = new Gson();
  Handler handler = new Handler();
  public boolean isCreateNewTime;
  public boolean isCreateNewTimeOk = true;
  private boolean isNewTiming;
  boolean isRespTimeOut;
  private List<ActTimingItemVo> itemVos = new ArrayList();
  private int lastClickPosi;
  public List<RepeatDayVo> longNameDayVos = new ArrayList();
  ETGroup mGroup;
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
        YaokongTimingVo localYaokongTimingVo = (YaokongTimingVo)localList.get(TimingBussines.this.timingPosi);
        localYaokongTimingVo.setOrder(Integer.parseInt(str.substring(-6 + str.length(), -4 + str.length()), 16));
        localList.remove(TimingBussines.this.timingPosi);
        localList.add(TimingBussines.this.timingPosi, localYaokongTimingVo);
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
        Toast.makeText(TimingBussines.this.pct, TimingBussines.this.pct.getString(2131099854), 0).show();
    }
  };
  private int timingPosi = 0;
  private byte[] uId = new byte[4];
  private YaokongTimingVo updateXuhaoTimingVo;
  private String userIdHexString;
  List<YaokongTimingVo> vos = null;
  private ArrayList<Integer> xuHaoList;

  public TimingBussines(Activity paramActivity)
  {
    this.pct = paramActivity;
    this.settingSetter = UserFerences.getUserFerences(this.pct);
    this.settingGetter = this.settingSetter.spFerences;
    try
    {
      this.mGroup = ((ETGroup)ETPage.getInstance(paramActivity).GetItem(0));
      this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
      for (int i = this.userIdHexString.length(); i < 8; i++)
        this.userIdHexString = ("0" + this.userIdHexString);
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
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

  private byte[] getYkCmd(YaokongTimingVo paramYaokongTimingVo)
    throws Exception
  {
    String str1 = paramYaokongTimingVo.getYkType();
    int i = -1;
    switch (str1.hashCode())
    {
    default:
    case 96586:
    case 3714:
    case 110741513:
    case -894830916:
    case 101139:
    case 3239401:
    case 99858:
    }
    while (true)
      switch (i)
      {
      default:
        return null;
        if (!str1.equals("air"))
          continue;
        i = 0;
        continue;
        if (!str1.equals("tv"))
          continue;
        i = 1;
        continue;
        if (!str1.equals("tvbox"))
          continue;
        i = 2;
        continue;
        if (!str1.equals("projector"))
          continue;
        i = 3;
        continue;
        if (!str1.equals("fan"))
          continue;
        i = 4;
        continue;
        if (!str1.equals("iptv"))
          continue;
        i = 5;
        continue;
        if (!str1.equals("dvd"))
          continue;
        i = 6;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    ETDeviceAIR localETDeviceAIR = (ETDeviceAIR)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceAIR.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
      try
      {
        localETDeviceAIR.GetKeyValue(49153);
        localETDeviceAIR.GetKeyValue(49153);
        SystemClock.sleep(50L);
        paramYaokongTimingVo.getAirMode().split(",");
        localETDeviceAIR.SetTemp(Byte.parseByte(paramYaokongTimingVo.getWendu().substring(0, paramYaokongTimingVo.getWendu().indexOf("℃"))));
        localETDeviceAIR.GetKeyValue(49165);
        SystemClock.sleep(50L);
        String str2 = this.pct.getString(2131099860);
        String str3 = this.pct.getString(2131099861);
        String str4 = this.pct.getString(2131099862);
        String str5 = this.pct.getString(2131099863);
        String str6 = this.pct.getString(2131099864);
        if (paramYaokongTimingVo.getAirMode().equals(str2))
          localETDeviceAIR.SetMode(1);
        if (paramYaokongTimingVo.getAirMode().equals(str3))
          localETDeviceAIR.SetMode(2);
        if (paramYaokongTimingVo.getAirMode().equals(str4))
          localETDeviceAIR.SetMode(3);
        if (paramYaokongTimingVo.getAirMode().equals(str5))
          localETDeviceAIR.SetMode(4);
        if (paramYaokongTimingVo.getAirMode().equals(str6))
          localETDeviceAIR.SetMode(5);
        byte[] arrayOfByte4 = localETDeviceAIR.GetKeyValue(49155);
        arrayOfByte3 = arrayOfByte4;
        localETDeviceAIR.GetKeyValue(49153);
        return arrayOfByte3;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localETDeviceAIR.GetKeyValue(49153);
          byte[] arrayOfByte3 = localETDeviceAIR.GetKeyValue(49153);
          localException.printStackTrace();
        }
      }
    byte[] arrayOfByte2 = localETDeviceAIR.GetKeyValue(49153);
    System.out.println("Tuhwerluifaweoiugyewrfg" + StringUtils.btye2Str(arrayOfByte2));
    return arrayOfByte2;
    ETDeviceTV localETDeviceTV = (ETDeviceTV)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceTV.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
    {
      localETDeviceTV.GetKeyValue(8203);
      int i3 = Integer.parseInt(paramYaokongTimingVo.getChanel().substring(0, paramYaokongTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
      if (i3 > 99)
      {
        localETDeviceTV.GetKeyValue(8207);
        localETDeviceTV.GetKeyValue(8227);
        localETDeviceTV.GetKeyValue(8227);
      }
      int i4;
      int i5;
      if ((i3 < 100) && (i3 > 9))
      {
        i4 = i3 / 10;
        i5 = i3 - i4 * 10;
      }
      switch (i4)
      {
      default:
        switch (i5)
        {
        default:
          label840: if (i3 < 10)
            switch (i3)
            {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            }
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        }
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
      while (true)
      {
        return localETDeviceTV.GetKeyValue(8203);
        localETDeviceTV.GetKeyValue(8227);
        break;
        localETDeviceTV.GetKeyValue(8207);
        break;
        localETDeviceTV.GetKeyValue(8209);
        break;
        localETDeviceTV.GetKeyValue(8211);
        break;
        localETDeviceTV.GetKeyValue(8213);
        break;
        localETDeviceTV.GetKeyValue(8215);
        break;
        localETDeviceTV.GetKeyValue(8217);
        break;
        localETDeviceTV.GetKeyValue(8219);
        break;
        localETDeviceTV.GetKeyValue(8221);
        break;
        localETDeviceTV.GetKeyValue(8223);
        break;
        localETDeviceTV.GetKeyValue(8227);
        break label840;
        localETDeviceTV.GetKeyValue(8207);
        break label840;
        localETDeviceTV.GetKeyValue(8209);
        break label840;
        localETDeviceTV.GetKeyValue(8211);
        break label840;
        localETDeviceTV.GetKeyValue(8213);
        break label840;
        localETDeviceTV.GetKeyValue(8215);
        break label840;
        localETDeviceTV.GetKeyValue(8217);
        break label840;
        localETDeviceTV.GetKeyValue(8219);
        break label840;
        localETDeviceTV.GetKeyValue(8221);
        break label840;
        localETDeviceTV.GetKeyValue(8223);
        break label840;
        localETDeviceTV.GetKeyValue(8227);
        continue;
        localETDeviceTV.GetKeyValue(8207);
        continue;
        localETDeviceTV.GetKeyValue(8209);
        continue;
        localETDeviceTV.GetKeyValue(8211);
        continue;
        localETDeviceTV.GetKeyValue(8213);
        continue;
        localETDeviceTV.GetKeyValue(8215);
        continue;
        localETDeviceTV.GetKeyValue(8217);
        continue;
        localETDeviceTV.GetKeyValue(8219);
        continue;
        localETDeviceTV.GetKeyValue(8221);
        continue;
        localETDeviceTV.GetKeyValue(8223);
      }
    }
    return localETDeviceTV.GetKeyValue(8203);
    ETDeviceSTB localETDeviceSTB = (ETDeviceSTB)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceSTB.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
    {
      localETDeviceSTB.GetKeyValue(16385);
      int n = Integer.parseInt(paramYaokongTimingVo.getChanel().substring(0, paramYaokongTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
      if (n > 99)
      {
        localETDeviceSTB.GetKeyValue(16387);
        localETDeviceSTB.GetKeyValue(16407);
        localETDeviceSTB.GetKeyValue(16407);
      }
      int i1;
      int i2;
      if ((n < 100) && (n > 9))
      {
        i1 = n / 10;
        i2 = n - i1 * 10;
      }
      switch (i1)
      {
      default:
        switch (i2)
        {
        default:
          label1532: if (n < 10)
            switch (n)
            {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            }
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        }
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
      while (true)
      {
        return localETDeviceSTB.GetKeyValue(16385);
        localETDeviceSTB.GetKeyValue(16407);
        break;
        localETDeviceSTB.GetKeyValue(16387);
        break;
        localETDeviceSTB.GetKeyValue(16389);
        break;
        localETDeviceSTB.GetKeyValue(16391);
        break;
        localETDeviceSTB.GetKeyValue(16393);
        break;
        localETDeviceSTB.GetKeyValue(16395);
        break;
        localETDeviceSTB.GetKeyValue(16397);
        break;
        localETDeviceSTB.GetKeyValue(16399);
        break;
        localETDeviceSTB.GetKeyValue(16401);
        break;
        localETDeviceSTB.GetKeyValue(16403);
        break;
        localETDeviceSTB.GetKeyValue(16407);
        break label1532;
        localETDeviceSTB.GetKeyValue(16387);
        break label1532;
        localETDeviceSTB.GetKeyValue(16389);
        break label1532;
        localETDeviceSTB.GetKeyValue(16391);
        break label1532;
        localETDeviceSTB.GetKeyValue(16393);
        break label1532;
        localETDeviceSTB.GetKeyValue(16395);
        break label1532;
        localETDeviceSTB.GetKeyValue(16397);
        break label1532;
        localETDeviceSTB.GetKeyValue(16399);
        break label1532;
        localETDeviceSTB.GetKeyValue(16401);
        break label1532;
        localETDeviceSTB.GetKeyValue(16403);
        break label1532;
        localETDeviceSTB.GetKeyValue(16407);
        continue;
        localETDeviceSTB.GetKeyValue(16387);
        continue;
        localETDeviceSTB.GetKeyValue(16389);
        continue;
        localETDeviceSTB.GetKeyValue(16391);
        continue;
        localETDeviceSTB.GetKeyValue(16393);
        continue;
        localETDeviceSTB.GetKeyValue(16395);
        continue;
        localETDeviceSTB.GetKeyValue(16397);
        continue;
        localETDeviceSTB.GetKeyValue(16399);
        continue;
        localETDeviceSTB.GetKeyValue(16401);
        continue;
        localETDeviceSTB.GetKeyValue(16403);
      }
    }
    return localETDeviceSTB.GetKeyValue(16385);
    ETDevicePJT localETDevicePJT = (ETDevicePJT)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDevicePJT.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
      return localETDevicePJT.GetKeyValue(40961);
    return localETDevicePJT.GetKeyValue(40963);
    ETDeviceFANS localETDeviceFANS = (ETDeviceFANS)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceFANS.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
    {
      byte[] arrayOfByte1 = localETDeviceFANS.GetKeyValue(32769);
      localETDeviceFANS.GetKeyValue(32769);
      return arrayOfByte1;
    }
    return localETDeviceFANS.GetKeyValue(32769);
    ETDeviceIPTV localETDeviceIPTV = (ETDeviceIPTV)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceIPTV.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
    {
      localETDeviceIPTV.GetKeyValue(8449);
      int j = Integer.parseInt(paramYaokongTimingVo.getChanel().substring(0, paramYaokongTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
      if (j > 99)
      {
        localETDeviceIPTV.GetKeyValue(8473);
        localETDeviceIPTV.GetKeyValue(8491);
        localETDeviceIPTV.GetKeyValue(8491);
      }
      int k;
      int m;
      if ((j < 100) && (j > 9))
      {
        k = j / 10;
        m = j - k * 10;
      }
      switch (k)
      {
      default:
        switch (m)
        {
        default:
          label2344: if (j < 10)
            switch (j)
            {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            }
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        }
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
      while (true)
      {
        return localETDeviceIPTV.GetKeyValue(8449);
        localETDeviceIPTV.GetKeyValue(8491);
        break;
        localETDeviceIPTV.GetKeyValue(8473);
        break;
        localETDeviceIPTV.GetKeyValue(8475);
        break;
        localETDeviceIPTV.GetKeyValue(8477);
        break;
        localETDeviceIPTV.GetKeyValue(8479);
        break;
        localETDeviceIPTV.GetKeyValue(8481);
        break;
        localETDeviceIPTV.GetKeyValue(8483);
        break;
        localETDeviceIPTV.GetKeyValue(8485);
        break;
        localETDeviceIPTV.GetKeyValue(8487);
        break;
        localETDeviceIPTV.GetKeyValue(8489);
        break;
        localETDeviceIPTV.GetKeyValue(8491);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8473);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8475);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8477);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8479);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8481);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8483);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8485);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8487);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8489);
        break label2344;
        localETDeviceIPTV.GetKeyValue(8491);
        continue;
        localETDeviceIPTV.GetKeyValue(8473);
        continue;
        localETDeviceIPTV.GetKeyValue(8475);
        continue;
        localETDeviceIPTV.GetKeyValue(8477);
        continue;
        localETDeviceIPTV.GetKeyValue(8479);
        continue;
        localETDeviceIPTV.GetKeyValue(8481);
        continue;
        localETDeviceIPTV.GetKeyValue(8483);
        continue;
        localETDeviceIPTV.GetKeyValue(8485);
        continue;
        localETDeviceIPTV.GetKeyValue(8487);
        continue;
        localETDeviceIPTV.GetKeyValue(8489);
      }
    }
    return localETDeviceIPTV.GetKeyValue(8449);
    ETDeviceDVD localETDeviceDVD = (ETDeviceDVD)this.mGroup.GetItem(paramYaokongTimingVo.getId());
    localETDeviceDVD.Load(ETDB.getInstance(this.pct));
    if (paramYaokongTimingVo.isOnOff())
    {
      localETDeviceDVD.GetKeyValue(24587);
      return localETDeviceDVD.GetKeyValue(24587);
    }
    return localETDeviceDVD.GetKeyValue(24587);
  }

  public void delTimingItem(List<YaokongTimingVo> paramList, int paramInt)
  {
    this.isRespTimeOut = true;
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getCtrlCtOtherTimingCmd(((YaokongTimingVo)paramList.get(paramInt)).getXuHao(), "del"), this.mySendPipeListener);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public int dp2px(int paramInt)
  {
    return (int)(0.5F + this.pct.getResources().getDisplayMetrics().density * paramInt);
  }

  public YaokongTimingVo getCacheTimingVo4Sd()
  {
    return (YaokongTimingVo)this.gson.fromJson(this.settingGetter.getString("cacheTimingVo", ""), YaokongTimingVo.class);
  }

  public List<String> getChanelDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 100)
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

  public YaokongTimingVo getTimingVo(int paramInt)
  {
    try
    {
      YaokongTimingVo localYaokongTimingVo = (YaokongTimingVo)getTimingVos().get(paramInt);
      return localYaokongTimingVo;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  public List<YaokongTimingVo> getTimingVos()
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

  public List<String> getWenduDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 16;
    if (i < 30)
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
    this.socketManager.ip = HongWaiMain.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(this.pct, HongWaiMain.deviceVo.getPwd());
  }

  public void reSortTimingData(List<YaokongTimingVo> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < paramList.size())
    {
      YaokongTimingVo localYaokongTimingVo = (YaokongTimingVo)paramList.get(i);
      if (localYaokongTimingVo.isOther())
      {
        localYaokongTimingVo.setIsShowMineTiming(false);
        localYaokongTimingVo.setIsShowOtherTiming(false);
        localArrayList2.add(localYaokongTimingVo);
      }
      while (true)
      {
        i++;
        break;
        localYaokongTimingVo.setIsShowMineTiming(false);
        localYaokongTimingVo.setIsShowOtherTiming(false);
        localArrayList1.add(localYaokongTimingVo);
      }
    }
    paramList.clear();
    if (localArrayList1.size() > 0)
      ((YaokongTimingVo)localArrayList1.get(0)).setIsShowMineTiming(true);
    if (localArrayList2.size() > 0)
      ((YaokongTimingVo)localArrayList2.get(0)).setIsShowOtherTiming(true);
    paramList.addAll(localArrayList1);
    paramList.addAll(localArrayList2);
  }

  public void removeXlinkListener()
  {
    XlinkAgent.getInstance().removeListener(this.myXlinkNetListener);
  }

  public void saveCacheVos(YaokongTimingVo paramYaokongTimingVo)
  {
    this.settingSetter.putValue("cacheTimingVo", this.gson.toJson(paramYaokongTimingVo));
  }

  public void sendEditTiming(YaokongTimingVo paramYaokongTimingVo, int paramInt)
  {
    this.updateXuhaoTimingVo = paramYaokongTimingVo;
    this.isRespTimeOut = true;
    this.timingPosi = paramInt;
    System.out.println(this.timingPosi);
    List localList = paramYaokongTimingVo.getLongNameDays();
    String str1 = paramYaokongTimingVo.getTime();
    String str2 = str1.substring(0, str1.indexOf(":"));
    String str3 = str1.substring(1 + str1.indexOf(":"));
    int i;
    label87: int m;
    label169: int n;
    if (paramYaokongTimingVo.getType() == 1)
    {
      if (!paramYaokongTimingVo.isSwich())
        break label217;
      i = 1;
      str4 = "0";
      boolean bool = DateFmtUtil.getTime4HHmm(paramYaokongTimingVo.getTime()) < System.currentTimeMillis();
      int j = 0;
      if (!bool)
        j = 1;
      if (!((String)paramYaokongTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label254;
      str4 = "1";
      m = DateFmtUtil.getWeekOfDate();
      if (j != 0)
      {
        if (m != 7)
          break label223;
        m = 1;
      }
      n = 7;
      label173: if (n <= 0)
        break label275;
      if (n != m)
        break label229;
    }
    label217: label223: label229: for (String str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      n--;
      break label173;
      break;
      i = 0;
      break label87;
      m++;
      break label169;
    }
    label254: if (((RepeatDayVo)localList.get(0)).isSeleted())
      str4 = "01111111";
    while (true)
    {
      label275: System.out.println("seletedDaysHex     " + str4);
      try
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getYaoKongTiming(paramYaokongTimingVo.getXuHao(), getYkCmd(paramYaokongTimingVo), str4, str2, str3, i, this.uId, paramYaokongTimingVo.isOnOff()), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
        int k = -1 + localList.size();
        if (k <= 0)
          continue;
        if (((RepeatDayVo)localList.get(k)).isSeleted());
        for (str4 = str4 + "1"; ; str4 = str4 + "0")
        {
          k--;
          break;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public void sendTiming(YaokongTimingVo paramYaokongTimingVo, int paramInt, boolean paramBoolean)
  {
    this.updateXuhaoTimingVo = paramYaokongTimingVo;
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 10000L);
    this.timingPosi = paramInt;
    System.out.println(this.timingPosi);
    this.isNewTiming = paramBoolean;
    List localList;
    String str3;
    String str4;
    int j;
    label126: int i2;
    label208: int i3;
    if (this.isNewTiming)
    {
      localList = paramYaokongTimingVo.getLongNameDays();
      String str2 = paramYaokongTimingVo.getTime();
      str3 = str2.substring(0, str2.indexOf(":"));
      str4 = str2.substring(1 + str2.indexOf(":"));
      if (paramYaokongTimingVo.getType() != 1)
        break label331;
      if (!paramYaokongTimingVo.isSwich())
        break label334;
      j = 1;
      str5 = "0";
      boolean bool = DateFmtUtil.getTime4HHmm(paramYaokongTimingVo.getTime()) < System.currentTimeMillis();
      int k = 0;
      if (!bool)
        k = 1;
      if (!((String)paramYaokongTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label371;
      str5 = "1";
      i2 = DateFmtUtil.getWeekOfDate();
      if (k != 0)
      {
        if (i2 != 7)
          break label340;
        i2 = 1;
      }
      i3 = 7;
      label212: if (i3 <= 0)
        break label393;
      if (i3 != i2)
        break label346;
    }
    label331: label334: label340: label346: for (String str5 = str5 + "1"; ; str5 = str5 + "0")
    {
      i3--;
      break label212;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice1 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness1 = this.cmdDateBussiness;
      int i = paramYaokongTimingVo.getXuHao();
      if (paramYaokongTimingVo.isSwich());
      for (String str1 = "on"; ; str1 = "off")
      {
        localXlinkAgent1.sendPipeData(localXDevice1, localCmdDateBussiness1.getCtrlCtOtherTimingCmd(i, str1), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      break;
      j = 0;
      break label126;
      i2++;
      break label208;
    }
    label371: if (((RepeatDayVo)localList.get(0)).isSeleted())
    {
      str5 = "01111111";
      System.out.println("seletedDaysHex     " + str5);
    }
    while (true)
    {
      try
      {
        label393: XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        XDevice localXDevice2 = DeviceManage.getxDevice();
        CmdDateBussiness localCmdDateBussiness2 = this.cmdDateBussiness;
        if (!this.isCreateNewTime)
          break label599;
        n = 255;
        localXlinkAgent2.sendPipeData(localXDevice2, localCmdDateBussiness2.getYaoKongTiming(n, getYkCmd(paramYaokongTimingVo), str5, str3, str4, j, this.uId, paramYaokongTimingVo.isOnOff()), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      int m = -1 + localList.size();
      label522: if (m > 0)
        if (!((RepeatDayVo)localList.get(m)).isSeleted())
          break label574;
      label574: for (str5 = str5 + "1"; ; str5 = str5 + "0")
      {
        m--;
        break label522;
        break;
      }
      label599: int i1 = paramYaokongTimingVo.getXuHao();
      int n = i1;
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
      i3 = 7;
      continue;
      System.out.println("星期一");
      i3 = 1;
      continue;
      System.out.println("星期二");
      i3 = 2;
      continue;
      System.out.println("星期三");
      i3 = 3;
      continue;
      System.out.println("星期四");
      i3 = 4;
      continue;
      System.out.println("星期五");
      i3 = 5;
      continue;
      System.out.println("星期六");
      i3 = 6;
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
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      System.out.println("hahagetXuHao      rec" + str1);
      if ((str1.length() == 106) && (str1.indexOf("3BEB") != -1))
      {
        ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        str1.substring(12, -4 + str1.length());
        String str2 = str1.substring(12, -4 + str1.length());
        int m = 0;
        if (m < 5)
        {
          String str3 = str2.substring(0, 18);
          str2 = str2.substring(18, str2.length());
          YaokongTimingVo localYaokongTimingVo3 = new YaokongTimingVo();
          boolean bool1;
          label164: String str5;
          String str6;
          String str7;
          String str10;
          label396: String str11;
          label441: boolean bool2;
          label469: String str12;
          label500: ArrayList localArrayList3;
          if (str3.substring(0, 1).equals("F"))
          {
            bool1 = true;
            localYaokongTimingVo3.setIsEnableTiming(bool1);
            String str4 = str3.substring(2, 10);
            localYaokongTimingVo3.setUserIdHexString(str4);
            str5 = Integer.parseInt(str3.substring(10, 12), 16) + "";
            str6 = Integer.parseInt(str3.substring(12, 14), 16) + "";
            if (str6.length() == 1)
              str6 = "0" + str6;
            str7 = StringUtils.hexString2binaryString(str3.substring(14, 16));
            System.out.println(" xinQiBinary        " + str4);
            String str8 = str3.substring(16, 17);
            String str9 = str3.substring(17, 18);
            localYaokongTimingVo3.setXuHao(m);
            StringBuilder localStringBuilder1 = new StringBuilder();
            if (str5.length() != 1)
              break label616;
            str10 = "0" + str5;
            StringBuilder localStringBuilder2 = localStringBuilder1.append(str10).append(":");
            if (str6.length() != 1)
              break label623;
            str11 = "0" + str6;
            localYaokongTimingVo3.setTime(str11);
            if (!str8.equals("1"))
              break label630;
            bool2 = true;
            localYaokongTimingVo3.setSwich(bool2);
            if (!str9.equals("1"))
              break label636;
            str12 = TimingBussines.this.pct.getString(2131100232);
            localYaokongTimingVo3.setLightStatus(str12);
            localArrayList3 = new ArrayList();
            if (!str7.substring(0, 1).equals("1"))
              break label653;
            localArrayList3.add(TimingBussines.this.pct.getString(2131100239));
          }
          while (true)
          {
            System.out.printf(str5 + ":" + str6, new Object[0]);
            localYaokongTimingVo3.setShotNameDays(localArrayList3);
            localArrayList1.add(localYaokongTimingVo3);
            m++;
            break;
            bool1 = false;
            break label164;
            label616: str10 = str5;
            break label396;
            label623: str11 = str6;
            break label441;
            label630: bool2 = false;
            break label469;
            label636: str12 = TimingBussines.this.pct.getString(2131100226);
            break label500;
            label653: if (str7.equals("01111111"))
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
        List localList = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
        for (int n = 0; n < localArrayList1.size(); n++)
        {
          if (!((YaokongTimingVo)localArrayList1.get(n)).isEnableTiming())
            continue;
          ((YaokongTimingVo)localArrayList1.get(n)).setIsOther(true);
          localArrayList2.add(localArrayList1.get(n));
        }
        for (int i1 = 0; i1 < localArrayList2.size(); i1++)
        {
          YaokongTimingVo localYaokongTimingVo1 = (YaokongTimingVo)localArrayList2.get(i1);
          for (int i2 = 0; i2 < localList.size(); i2++)
          {
            YaokongTimingVo localYaokongTimingVo2 = (YaokongTimingVo)localList.get(i2);
            System.out.println("YaokongTimingVoYaokongTimingVoYaokongTimingVo:" + localYaokongTimingVo2.getTime() + "       " + localYaokongTimingVo1.getTime());
            if ((!localYaokongTimingVo1.getUserIdHexString().equals(TimingBussines.this.userIdHexString)) || (localYaokongTimingVo2.isOther()) || (localYaokongTimingVo2.getXuHao() != localYaokongTimingVo1.getXuHao()) || (!localYaokongTimingVo2.getTime().equals(localYaokongTimingVo1.getTime())))
              continue;
            localArrayList2.remove(i1);
            localArrayList2.add(i1, localYaokongTimingVo2);
          }
        }
        TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localArrayList2);
        TimingBussines.this.actTiming.upDateData();
        System.out.println();
      }
      int i;
      if (str1.length() == 20)
      {
        i = 1;
        if (str1.indexOf("3CEB") == -1)
          break label1516;
      }
      label1516: for (int j = 1; ; j = 0)
      {
        if ((i | j) != 0)
        {
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
          Handler localHandler2 = TimingBussines.this.handler;
          1 local1 = new Runnable()
          {
            public void run()
            {
              TimingBussines.this.getDeviceTiming();
            }
          };
          localHandler2.postDelayed(local1, 1000L);
        }
        if ((str1.length() == 18) && (str1.indexOf("A4EB") != -1))
        {
          int k = Integer.parseInt(str1.substring(12, 14), 16);
          System.out.println("ReturnMyData         " + str1);
          TimingBussines.this.isRespTimeOut = false;
          if (TimingBussines.this.isCreateNewTime)
            TimingBussines.this.updateXuhaoTimingVo.setXuHao(k);
          TimingBussines.this.saveCacheVos(TimingBussines.this.updateXuhaoTimingVo);
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
          TimingBussines.this.isCreateNewTime = false;
          TimingBussines.this.isCreateNewTimeOk = true;
          Handler localHandler1 = TimingBussines.this.handler;
          2 local2 = new Runnable()
          {
            public void run()
            {
              TimingBussines.this.getDeviceTiming();
            }
          };
          localHandler1.postDelayed(local2, 1000L);
        }
        return;
        i = 0;
        break;
      }
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      TimingBussines.this.isRespTimeOut = false;
      System.out.println(StringUtils.btye2Str(paramArrayOfByte) + " timgResp1 ");
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
 * Qualified Name:     com.ex.ltech.remote.control.time.TimingBussines
 * JD-Core Version:    0.6.0
 */