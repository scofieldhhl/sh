package com.ex.ltech.hongwai.time;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import android.widget.Toast;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.NewHongWaiMain;
import com.ex.ltech.hongwai.time.act.ActTiming;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.TimingVo;
import com.ex.ltech.led.T;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.ActTimingItemVo;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.KKNonACManager;
import com.hzy.tvmao.ir.ac.ACModelV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
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
  byte[] channelRcGeBit;
  private CmdDateBussiness cmdDateBussiness;
  private List<SceneSysCustomItemVo> customVos;
  Gson gson = new Gson();
  Handler handler = new Handler();
  public boolean isCreateNewTime;
  public boolean isCreateNewTimeOk = true;
  private boolean isNewTiming;
  boolean isRespTimeOut;
  private List<ActTimingItemVo> itemVos = new ArrayList();
  KKACManagerV2 kkACManager;
  KKNonACManager kkNonACManager;
  private int lastClickPosi;
  String lastRecCmd = "";
  public List<RepeatDayVo> longNameDayVos = new ArrayList();
  ETGroup mGroup;
  private int mcuSorfVersion = 1;
  MySendPipeListener mySendPipeListener = new MySendPipeListener();
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  private Activity pct;
  private MyRcDevices rcDevices;
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
        if (TimingBussines.this.actTiming != null)
          TimingBussines.this.actTiming.upDateData();
      }
      TimingBussines.access$102(TimingBussines.this, false);
    }
  };
  byte[] resend = null;
  private List<Integer> seletedDays = new ArrayList();
  private SendCmdListener sendCmdListener;
  int sendParamsReturnXuhao = -1;
  private SharedPreferences settingGetter;
  private UserFerences settingSetter;
  private SocketManager socketManager;
  Runnable timeOutThread = new Runnable()
  {
    public void run()
    {
      if (TimingBussines.this.isRespTimeOut)
      {
        Toast.makeText(TimingBussines.this.pct, TimingBussines.this.pct.getString(2131099854), 0).show();
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
      this.cmdDateBussiness = new CmdDateBussiness(this.pct, NewHongWaiMain.deviceVo.getPwd());
      XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    }
  }

  private byte[] getRcCmd(TimingVo paramTimingVo)
  {
    this.channelRcGeBit = new byte[0];
    InnerRcVo localInnerRcVo = paramTimingVo.getRcVo();
    MyRcDevice localMyRcDevice = (MyRcDevice)this.rcDevices.myRcDevices.get(localInnerRcVo.getmSaveRcListPosi());
    if (localInnerRcVo.getmType() == 5)
    {
      this.kkACManager = new KKACManagerV2((IrData)localMyRcDevice.irDatas.get(0));
      this.kkACManager.setACStateV2FromString("");
    }
    while (true)
      switch (localInnerRcVo.getmType())
      {
      case 6:
      case 7:
      default:
        if ((localInnerRcVo.getStatus().equals(this.pct.getString(2131100232))) || (localInnerRcVo.getStatus().equals(this.pct.getString(2131100226))))
        {
          arrayOfByte = getRcCodeByChineseName("电源");
          return arrayOfByte;
          this.kkNonACManager = new KKNonACManager((IrData)localMyRcDevice.irDatas.get(0));
        }
        break;
      case 5:
        if (!localInnerRcVo.getStatus().equals(this.pct.getString(2131100226)))
        {
          this.kkACManager.changePowerState();
          String[] arrayOfString = localInnerRcVo.getStatus().split(",");
          String str1 = this.pct.getString(2131099860);
          String str2 = this.pct.getString(2131099861);
          String str3 = this.pct.getString(2131099862);
          String str4 = this.pct.getString(2131099863);
          String str5 = this.pct.getString(2131099864);
          if (arrayOfString[0].equals(str1))
            this.kkACManager.getAcStateV2().changeToTargetModel(0);
          if (arrayOfString[0].equals(str2))
            this.kkACManager.getAcStateV2().changeToTargetModel(1);
          if (arrayOfString[0].equals(str3))
            this.kkACManager.getAcStateV2().changeToTargetModel(2);
          if (arrayOfString[0].equals(str4))
            this.kkACManager.getAcStateV2().changeToTargetModel(3);
          if (arrayOfString[0].equals(str5))
            this.kkACManager.getAcStateV2().changeToTargetModel(4);
          this.kkACManager.getCurrentACModel().setTemperature(Integer.parseInt(arrayOfString[1].substring(0, arrayOfString[1].indexOf("℃"))));
        }
        while (true)
        {
          return this.kkACManager.getACKeyIr();
          this.kkACManager.changePowerState();
          this.kkACManager.changePowerState();
        }
      case 8:
        return localInnerRcVo.getRcCodes();
      }
    int i = Integer.parseInt(localInnerRcVo.getStatus().substring(0, localInnerRcVo.getStatus().indexOf(this.pct.getString(2131099929))));
    byte[] arrayOfByte = null;
    if (i > 99)
    {
      getRcCodeByChineseName("1");
      SystemClock.sleep(500L);
      getRcCodeByChineseName("0");
      SystemClock.sleep(500L);
      arrayOfByte = getRcCodeByChineseName("0");
    }
    int j;
    if ((i < 100) && (i > 9))
    {
      j = i / 10;
      int k = i - j * 10;
      switch (j)
      {
      default:
        label628: SystemClock.sleep(500L);
        switch (k)
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
    }
    while (i < 10)
      switch (i)
      {
      default:
        return arrayOfByte;
      case 0:
        return getRcCodeByChineseName("0");
        arrayOfByte = getRcCodeByChineseName("0");
        break label628;
        arrayOfByte = getRcCodeByChineseName("1");
        break label628;
        arrayOfByte = getRcCodeByChineseName("2");
        break label628;
        arrayOfByte = getRcCodeByChineseName("3");
        break label628;
        arrayOfByte = getRcCodeByChineseName("4");
        break label628;
        arrayOfByte = getRcCodeByChineseName("5");
        break label628;
        arrayOfByte = getRcCodeByChineseName("6");
        break label628;
        arrayOfByte = getRcCodeByChineseName("7");
        break label628;
        arrayOfByte = getRcCodeByChineseName("8");
        break label628;
        arrayOfByte = getRcCodeByChineseName("9");
        break label628;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("0");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("0");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("1");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("1");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("2");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("2");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("3");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("3");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("4");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("4");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("5");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("5");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("6");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("6");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("7");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("7");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("8");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("8");
        continue;
        if (j != 0)
          this.channelRcGeBit = getRcCodeByChineseName("9");
        if (j != 0)
          continue;
        arrayOfByte = getRcCodeByChineseName("9");
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
    return getRcCodeByChineseName("1");
    return getRcCodeByChineseName("2");
    return getRcCodeByChineseName("3");
    return getRcCodeByChineseName("4");
    return getRcCodeByChineseName("5");
    return getRcCodeByChineseName("6");
    return getRcCodeByChineseName("7");
    return getRcCodeByChineseName("8");
    return getRcCodeByChineseName("9");
  }

  private byte[] getRcCodeByChineseName(String paramString)
  {
    for (int i = 0; i < this.kkNonACManager.getAllKeys().size(); i++)
      if (((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fname.equals(paramString))
        return this.kkNonACManager.getKeyIr(((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fkey);
    return new byte[0];
  }

  private int getTime(String paramString)
  {
    Date localDate = new Date();
    return Integer.parseInt(new SimpleDateFormat(paramString).format(localDate));
  }

  private byte[] getYkCmd(TimingVo paramTimingVo)
    throws Exception
  {
    String str1 = paramTimingVo.getYkType();
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
    ETDeviceAIR localETDeviceAIR = (ETDeviceAIR)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceAIR.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
      try
      {
        localETDeviceAIR.GetKeyValue(49153);
        localETDeviceAIR.GetKeyValue(49153);
        SystemClock.sleep(50L);
        paramTimingVo.getAirMode().split(",");
        localETDeviceAIR.SetTemp(Byte.parseByte(paramTimingVo.getWendu().substring(0, paramTimingVo.getWendu().indexOf("℃"))));
        localETDeviceAIR.GetKeyValue(49165);
        SystemClock.sleep(50L);
        String str2 = this.pct.getString(2131099860);
        String str3 = this.pct.getString(2131099861);
        String str4 = this.pct.getString(2131099862);
        String str5 = this.pct.getString(2131099863);
        String str6 = this.pct.getString(2131099864);
        if (paramTimingVo.getAirMode().equals(str2))
          localETDeviceAIR.SetMode(1);
        if (paramTimingVo.getAirMode().equals(str3))
          localETDeviceAIR.SetMode(2);
        if (paramTimingVo.getAirMode().equals(str4))
          localETDeviceAIR.SetMode(3);
        if (paramTimingVo.getAirMode().equals(str5))
          localETDeviceAIR.SetMode(4);
        if (paramTimingVo.getAirMode().equals(str6))
          localETDeviceAIR.SetMode(5);
        byte[] arrayOfByte3 = localETDeviceAIR.GetKeyValue(49155);
        arrayOfByte2 = arrayOfByte3;
        localETDeviceAIR.GetKeyValue(49153);
        return arrayOfByte2;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localETDeviceAIR.GetKeyValue(49153);
          byte[] arrayOfByte2 = localETDeviceAIR.GetKeyValue(49153);
          localException.printStackTrace();
        }
      }
    return localETDeviceAIR.GetKeyValue(49153);
    ETDeviceTV localETDeviceTV = (ETDeviceTV)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceTV.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
    {
      localETDeviceTV.GetKeyValue(8203);
      int i3 = Integer.parseInt(paramTimingVo.getChanel().substring(0, paramTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
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
          label804: if (i3 < 10)
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
        break label804;
        localETDeviceTV.GetKeyValue(8207);
        break label804;
        localETDeviceTV.GetKeyValue(8209);
        break label804;
        localETDeviceTV.GetKeyValue(8211);
        break label804;
        localETDeviceTV.GetKeyValue(8213);
        break label804;
        localETDeviceTV.GetKeyValue(8215);
        break label804;
        localETDeviceTV.GetKeyValue(8217);
        break label804;
        localETDeviceTV.GetKeyValue(8219);
        break label804;
        localETDeviceTV.GetKeyValue(8221);
        break label804;
        localETDeviceTV.GetKeyValue(8223);
        break label804;
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
    ETDeviceSTB localETDeviceSTB = (ETDeviceSTB)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceSTB.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
    {
      localETDeviceSTB.GetKeyValue(16385);
      int n = Integer.parseInt(paramTimingVo.getChanel().substring(0, paramTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
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
          label1496: if (n < 10)
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
        break label1496;
        localETDeviceSTB.GetKeyValue(16387);
        break label1496;
        localETDeviceSTB.GetKeyValue(16389);
        break label1496;
        localETDeviceSTB.GetKeyValue(16391);
        break label1496;
        localETDeviceSTB.GetKeyValue(16393);
        break label1496;
        localETDeviceSTB.GetKeyValue(16395);
        break label1496;
        localETDeviceSTB.GetKeyValue(16397);
        break label1496;
        localETDeviceSTB.GetKeyValue(16399);
        break label1496;
        localETDeviceSTB.GetKeyValue(16401);
        break label1496;
        localETDeviceSTB.GetKeyValue(16403);
        break label1496;
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
    ETDevicePJT localETDevicePJT = (ETDevicePJT)this.mGroup.GetItem(paramTimingVo.getId());
    localETDevicePJT.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
      return localETDevicePJT.GetKeyValue(40961);
    return localETDevicePJT.GetKeyValue(40963);
    ETDeviceFANS localETDeviceFANS = (ETDeviceFANS)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceFANS.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
    {
      byte[] arrayOfByte1 = localETDeviceFANS.GetKeyValue(32769);
      localETDeviceFANS.GetKeyValue(32769);
      return arrayOfByte1;
    }
    return localETDeviceFANS.GetKeyValue(32769);
    ETDeviceIPTV localETDeviceIPTV = (ETDeviceIPTV)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceIPTV.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
    {
      localETDeviceIPTV.GetKeyValue(8449);
      int j = Integer.parseInt(paramTimingVo.getChanel().substring(0, paramTimingVo.getChanel().indexOf(this.pct.getString(2131099929))));
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
          label2308: if (j < 10)
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
        break label2308;
        localETDeviceIPTV.GetKeyValue(8473);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8475);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8477);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8479);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8481);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8483);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8485);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8487);
        break label2308;
        localETDeviceIPTV.GetKeyValue(8489);
        break label2308;
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
    ETDeviceDVD localETDeviceDVD = (ETDeviceDVD)this.mGroup.GetItem(paramTimingVo.getId());
    localETDeviceDVD.Load(ETDB.getInstance(this.pct));
    if (paramTimingVo.isOnOff())
    {
      localETDeviceDVD.GetKeyValue(24587);
      return localETDeviceDVD.GetKeyValue(24587);
    }
    return localETDeviceDVD.GetKeyValue(24587);
  }

  private void postTimeoutThread()
  {
    this.isRespTimeOut = true;
    this.handler.removeCallbacks(this.timeOutThread);
    this.handler.postDelayed(this.timeOutThread, 8000L);
  }

  private void sendRcParmas(byte[] paramArrayOfByte, int paramInt)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    XDevice localXDevice = DeviceManage.getxDevice();
    byte[] arrayOfByte = this.cmdDateBussiness.getTimingRcParmas(paramArrayOfByte, paramInt);
    this.resend = arrayOfByte;
    localXlinkAgent.sendPipeData(localXDevice, arrayOfByte, new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), TimingBussines.this.resend, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
      }
    }
    , 50L);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), TimingBussines.this.resend, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
      }
    }
    , 50L);
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public void delTimingItem(List<TimingVo> paramList, int paramInt)
  {
    this.isRespTimeOut = true;
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getCtrlCtOtherTimingCmd(((TimingVo)paramList.get(paramInt)).getXuHao(), "del"), this.mySendPipeListener);
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

  public List<String> getChanelDate()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 100; i++)
      localArrayList.add("" + i);
    return localArrayList;
  }

  public Ct1ScenesVo getCt1SceneVos()
  {
    Ct1ScenesVo localCt1ScenesVo = (Ct1ScenesVo)MyDb.getInstance(this.pct).getBean(DeviceListActivity.deviceMacAddress, Ct1ScenesVo.class);
    if (localCt1ScenesVo == null)
    {
      localCt1ScenesVo = new Ct1ScenesVo();
      localCt1ScenesVo.initCt1SceneVos();
    }
    return localCt1ScenesVo;
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
    T.s();
    this.socketManager.postTask(this.cmdDateBussiness.getDeviceTimingCmd());
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public List<String> getHourDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 12)
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

  public MyRcDevices getRcDevices()
  {
    return this.rcDevices;
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

  public List<String> getTimeRange(Context paramContext)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(paramContext.getResources().getString(2131099875));
    localArrayList.add(paramContext.getResources().getString(2131100274));
    return localArrayList;
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

  public void initRcData()
  {
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(this.pct).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
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
    this.socketManager.ip = NewHongWaiMain.deviceVo.getIp();
  }

  public void queryTimingOrder()
  {
    this.socketManager.postTask(this.cmdDateBussiness.getQeuryIrTimingOrder());
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
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
    postTimeoutThread();
    this.updateXuhaoTimingVo = paramTimingVo;
    this.isRespTimeOut = true;
    this.timingPosi = paramInt;
    List localList = paramTimingVo.getLongNameDays();
    String str1 = paramTimingVo.getTime();
    String str2 = str1.substring(0, str1.indexOf(":"));
    String str3 = str1.substring(1 + str1.indexOf(":"));
    int i;
    label81: int m;
    label163: int n;
    if (paramTimingVo.getType() == 1)
    {
      if (!paramTimingVo.isSwich())
        break label211;
      i = 1;
      str4 = "0";
      boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
      int j = 0;
      if (!bool)
        j = 1;
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label248;
      str4 = "1";
      m = DateFmtUtil.getWeekOfDate();
      if (j != 0)
      {
        if (m != 7)
          break label217;
        m = 1;
      }
      n = 7;
      label167: if (n <= 0)
        break label269;
      if (n != m)
        break label223;
    }
    label211: label217: label223: for (String str4 = str4 + "1"; ; str4 = str4 + "0")
    {
      n--;
      break label167;
      break;
      i = 0;
      break label81;
      m++;
      break label163;
    }
    label248: if (((RepeatDayVo)localList.get(0)).isSeleted())
      str4 = "01111111";
    try
    {
      label269: if (paramTimingVo.getRcVo().getmType() < 10)
      {
        XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        XDevice localXDevice2 = DeviceManage.getxDevice();
        byte[] arrayOfByte2 = this.cmdDateBussiness.getRcTiming(paramTimingVo.getXuHao(), getRcCmd(paramTimingVo), this.channelRcGeBit, str4, str2, str3, i, this.uId, paramTimingVo.isOnOff());
        this.resend = arrayOfByte2;
        localXlinkAgent2.sendPipeData(localXDevice2, arrayOfByte2, this.mySendPipeListener);
      }
      while (true)
      {
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
            DeviceManage.getInstance();
            localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), TimingBussines.this.resend, TimingBussines.this.mySendPipeListener);
          }
        }
        , 50L);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
        int k = -1 + localList.size();
        if (k <= 0)
          break;
        if (((RepeatDayVo)localList.get(k)).isSeleted());
        for (str4 = str4 + "1"; ; str4 = str4 + "0")
        {
          k--;
          break;
        }
        XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        XDevice localXDevice1 = DeviceManage.getxDevice();
        byte[] arrayOfByte1 = this.cmdDateBussiness.getNonIrTiming(paramTimingVo.getXuHao(), str4, str2, str3, i, paramTimingVo.getRcVo().nonIrDevice, paramTimingVo.getGradientMins(), paramTimingVo.getStatus().equalsIgnoreCase(this.pct.getString(2131100054)));
        this.resend = arrayOfByte1;
        localXlinkAgent1.sendPipeData(localXDevice1, arrayOfByte1, this.mySendPipeListener);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sendRcParams(TimingVo paramTimingVo, int paramInt)
  {
    InnerRcVo localInnerRcVo = paramTimingVo.getRcVo();
    MyRcDevice localMyRcDevice = (MyRcDevice)this.rcDevices.myRcDevices.get(localInnerRcVo.getmSaveRcListPosi());
    if (localInnerRcVo.getmType() == 5)
    {
      this.kkACManager = new KKACManagerV2((IrData)localMyRcDevice.irDatas.get(0));
      this.kkACManager.setACStateV2FromString("");
      sendRcParmas(this.kkACManager.getAcParams(), paramInt);
      return;
    }
    this.kkNonACManager = new KKNonACManager((IrData)localMyRcDevice.irDatas.get(0));
    sendRcParmas(this.kkNonACManager.getParams(), paramInt);
  }

  public void sendTiming(TimingVo paramTimingVo, int paramInt, boolean paramBoolean)
  {
    postTimeoutThread();
    this.updateXuhaoTimingVo = paramTimingVo;
    this.timingPosi = paramInt;
    this.isNewTiming = paramBoolean;
    List localList;
    String str3;
    String str4;
    int j;
    label89: int i1;
    label171: int i2;
    if (this.isNewTiming)
    {
      localList = paramTimingVo.getLongNameDays();
      String str2 = paramTimingVo.getTime();
      str3 = str2.substring(0, str2.indexOf(":"));
      str4 = str2.substring(1 + str2.indexOf(":"));
      if (paramTimingVo.getType() != 1)
        break label294;
      if (!paramTimingVo.isSwich())
        break label297;
      j = 1;
      str5 = "0";
      boolean bool = DateFmtUtil.getTime4HHmm(paramTimingVo.getTime()) < System.currentTimeMillis();
      int k = 0;
      if (!bool)
        k = 1;
      if (!((String)paramTimingVo.getShotNameDays().get(0)).equals(this.pct.getString(2131100239)))
        break label334;
      str5 = "1";
      i1 = DateFmtUtil.getWeekOfDate();
      if (k != 0)
      {
        if (i1 != 7)
          break label303;
        i1 = 1;
      }
      i2 = 7;
      label175: if (i2 <= 0)
        break label356;
      if (i2 != i1)
        break label309;
    }
    label294: label297: label303: label309: for (String str5 = str5 + "1"; ; str5 = str5 + "0")
    {
      i2--;
      break label175;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice1 = DeviceManage.getxDevice();
      CmdDateBussiness localCmdDateBussiness1 = this.cmdDateBussiness;
      int i = paramTimingVo.getXuHao();
      if (paramTimingVo.isSwich());
      for (String str1 = "on"; ; str1 = "off")
      {
        localXlinkAgent1.sendPipeData(localXDevice1, localCmdDateBussiness1.getCtrlCtOtherTimingCmd(i, str1), this.mySendPipeListener);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      break;
      j = 0;
      break label89;
      i1++;
      break label171;
    }
    label334: if (((RepeatDayVo)localList.get(0)).isSeleted())
      str5 = "01111111";
    while (true)
    {
      try
      {
        label356: if (paramTimingVo.getRcVo().getmType() >= 10)
          break label590;
        XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        XDevice localXDevice3 = DeviceManage.getxDevice();
        CmdDateBussiness localCmdDateBussiness2 = this.cmdDateBussiness;
        if (!this.isCreateNewTime)
          break label581;
        n = this.sendParamsReturnXuhao;
        byte[] arrayOfByte2 = localCmdDateBussiness2.getRcTiming(n, getRcCmd(paramTimingVo), this.channelRcGeBit, str5, str3, str4, j, this.uId, paramTimingVo.isOnOff());
        this.resend = arrayOfByte2;
        localXlinkAgent3.sendPipeData(localXDevice3, arrayOfByte2, this.mySendPipeListener);
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
            DeviceManage.getInstance();
            localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), TimingBussines.this.resend, TimingBussines.this.mySendPipeListener);
          }
        }
        , 50L);
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      int m = -1 + localList.size();
      label504: if (m > 0)
        if (!((RepeatDayVo)localList.get(m)).isSeleted())
          break label556;
      label556: for (str5 = str5 + "1"; ; str5 = str5 + "0")
      {
        m--;
        break label504;
        break;
      }
      label581: int n = paramTimingVo.getXuHao();
      continue;
      label590: XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      XDevice localXDevice2 = DeviceManage.getxDevice();
      byte[] arrayOfByte1 = this.cmdDateBussiness.getNonIrTiming(this.sendParamsReturnXuhao, str5, str3, str4, j, paramTimingVo.getRcVo().nonIrDevice, paramTimingVo.getGradientMins(), paramTimingVo.getStatus().equalsIgnoreCase(this.pct.getString(2131100054)));
      this.resend = arrayOfByte1;
      localXlinkAgent2.sendPipeData(localXDevice2, arrayOfByte1, this.mySendPipeListener);
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
      i3 = 7;
      continue;
      i3 = 1;
      continue;
      i3 = 2;
      continue;
      i3 = 3;
      continue;
      i3 = 4;
      continue;
      i3 = 5;
      continue;
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
      if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0));
      label40: label175: label246: 
      do
      {
        return;
        int i;
        ArrayList localArrayList1;
        ArrayList localArrayList2;
        int i1;
        int i2;
        TimingVo localTimingVo3;
        boolean bool1;
        String str4;
        String str5;
        String str6;
        String str9;
        String str10;
        boolean bool2;
        String str11;
        ArrayList localArrayList3;
        if (!DeviceListActivity.deviceMacAddress.equals(paramXDevice.getMacAddress()))
        {
          i = 1;
          if ((i | TimingBussines.this.lastRecCmd.equals(str1)) != 0)
            break label666;
          System.out.println("onRecvPipeData      rec" + str1);
          if ((str1.length() < 198) || (str1.indexOf("3BEB") == -1))
            continue;
          localArrayList1 = new ArrayList();
          localArrayList2 = new ArrayList();
          str1.substring(12, -4 + str1.length());
          String str2 = str1.substring(12, -4 + str1.length());
          i1 = 0;
          if (str1.length() <= 198)
            break label668;
          i2 = 20;
          if (i1 >= i2)
            break label1004;
          String str3 = str2.substring(0, 18);
          str2 = str2.substring(18, str2.length());
          localTimingVo3 = new TimingVo();
          if (!(str3.substring(0, 1).equals("F") | str3.substring(0, 1).equals("D")))
            break label675;
          bool1 = true;
          localTimingVo3.setIsEnableTiming(bool1);
          localTimingVo3.setUserIdHexString(str3.substring(2, 10));
          str4 = Integer.parseInt(str3.substring(10, 12), 16) + "";
          str5 = Integer.parseInt(str3.substring(12, 14), 16) + "";
          if (str5.length() == 1)
            str5 = "0" + str5;
          str6 = StringUtils.hexString2binaryString(str3.substring(14, 16));
          String str7 = str3.substring(16, 17);
          String str8 = str3.substring(17, 18);
          localTimingVo3.setXuHao(i1);
          StringBuilder localStringBuilder1 = new StringBuilder();
          if (str4.length() != 1)
            break label681;
          str9 = "0" + str4;
          StringBuilder localStringBuilder2 = localStringBuilder1.append(str9).append(":");
          if (str5.length() != 1)
            break label688;
          str10 = "0" + str5;
          localTimingVo3.setTime(str10);
          if (!str7.equals("1"))
            break label695;
          bool2 = true;
          localTimingVo3.setSwich(bool2);
          if (!str8.equals("1"))
            break label701;
          str11 = TimingBussines.this.pct.getString(2131100232);
          localTimingVo3.setStatus(str11);
          localArrayList3 = new ArrayList();
          if (!str6.substring(0, 1).equals("1"))
            break label718;
          localArrayList3.add(TimingBussines.this.pct.getString(2131100239));
        }
        while (true)
        {
          System.out.printf(str4 + ":" + str5, new Object[0]);
          localTimingVo3.setShotNameDays(localArrayList3);
          localArrayList1.add(localTimingVo3);
          i1++;
          break label160;
          i = 0;
          break label40;
          break;
          i2 = 10;
          break label175;
          bool1 = false;
          break label246;
          str9 = str4;
          break label448;
          str10 = str5;
          break label493;
          bool2 = false;
          break label521;
          str11 = TimingBussines.this.pct.getString(2131100226);
          break label552;
          if (str6.equals("01111111"))
          {
            localArrayList3.add(TimingBussines.this.pct.getString(2131100055));
            continue;
          }
          if (str6.substring(7, 8).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100240));
          if (str6.substring(6, 7).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100467));
          if (str6.substring(5, 6).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100430));
          if (str6.substring(4, 5).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100071));
          if (str6.substring(3, 4).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100066));
          if (str6.substring(2, 3).equals("1"))
            localArrayList3.add(TimingBussines.this.pct.getString(2131100404));
          if (!str6.substring(1, 2).equals("1"))
            continue;
          localArrayList3.add(TimingBussines.this.pct.getString(2131100394));
        }
        List localList = TimingData.getInstance(TimingBussines.this.pct).getTimingVos4Sd();
        for (int i3 = 0; i3 < localArrayList1.size(); i3++)
        {
          if (!((TimingVo)localArrayList1.get(i3)).isEnableTiming())
            continue;
          ((TimingVo)localArrayList1.get(i3)).setIsOther(true);
          localArrayList2.add(localArrayList1.get(i3));
        }
        for (int i4 = 0; i4 < localArrayList2.size(); i4++)
        {
          TimingVo localTimingVo1 = (TimingVo)localArrayList2.get(i4);
          for (int i5 = 0; i5 < localList.size(); i5++)
          {
            TimingVo localTimingVo2 = (TimingVo)localList.get(i5);
            if ((!localTimingVo1.getUserIdHexString().equals(TimingBussines.this.userIdHexString)) || (localTimingVo2.isOther()) || (localTimingVo2.getXuHao() != localTimingVo1.getXuHao()) || (!localTimingVo2.getTime().equals(localTimingVo1.getTime())))
              continue;
            localArrayList2.remove(i4);
            localArrayList2.add(i4, localTimingVo2);
          }
        }
        TimingData.getInstance(TimingBussines.this.pct).saveTimingVos2Sd(localArrayList2);
        if (TimingBussines.this.actTiming != null)
          TimingBussines.this.actTiming.upDateData();
        T.e();
      }
      while (TimingBussines.this.sendCmdListener == null);
      label160: label448: label493: int j;
      label521: label552: label681: label688: label695: label701: label718: label1004: int k;
      label666: label668: label675: int m;
      if (str1.length() == 20)
      {
        j = 1;
        if (str1.indexOf("3CEB") == -1)
          break label1827;
        k = 1;
        label1312: if ((j | k) != 0)
        {
          TimingBussines.this.isRespTimeOut = false;
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
          Handler localHandler2 = TimingBussines.this.handler;
          1 local1 = new Runnable()
          {
            public void run()
            {
            }
          };
          localHandler2.postDelayed(local1, 1000L);
        }
        if (str1.length() != 18)
          break label1833;
        m = 1;
        label1393: if (str1.indexOf("E0EB") == -1)
          break label1839;
      }
      label1827: label1833: label1839: for (int n = 1; ; n = 0)
      {
        if ((m & n) != 0)
        {
          TimingBussines.this.isRespTimeOut = false;
          if (TimingBussines.this.sendCmdListener != null)
            TimingBussines.this.sendCmdListener.onSendOk();
        }
        if ((str1.length() == 18) && (str1.indexOf("E2EB") != -1))
        {
          TimingBussines.this.sendParamsReturnXuhao = Integer.parseInt(str1.substring(12, 14), 16);
          if (TimingBussines.this.actTiming != null)
            TimingBussines.this.actTiming.sendRcCode(TimingBussines.this.sendParamsReturnXuhao);
          if (TimingBussines.this.isCreateNewTime)
            TimingBussines.this.updateXuhaoTimingVo.setXuHao(TimingBussines.this.sendParamsReturnXuhao);
          TimingBussines.this.saveCacheVos(TimingBussines.this.updateXuhaoTimingVo);
        }
        if ((str1.length() == 18) && (str1.indexOf("A4EB") != -1))
        {
          TimingBussines.this.isRespTimeOut = false;
          if (Integer.parseInt(str1.substring(12, 13), 16) == 0)
          {
            TimingBussines.this.sendParamsReturnXuhao = Integer.parseInt(str1.substring(12, 14), 16);
            System.out.println("sendParamsReturnXuhao = " + TimingBussines.this.sendParamsReturnXuhao);
            if (TimingBussines.this.actTiming != null)
              TimingBussines.this.actTiming.sendRcCode(TimingBussines.this.sendParamsReturnXuhao);
          }
          if (Integer.parseInt(str1.substring(12, 13), 16) == 1)
          {
            if (TimingBussines.this.isCreateNewTime)
              TimingBussines.this.updateXuhaoTimingVo.setXuHao(TimingBussines.this.sendParamsReturnXuhao);
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
              }
            };
            localHandler1.postDelayed(local2, 1000L);
          }
        }
        TimingBussines.this.lastRecCmd = str1;
        return;
        j = 0;
        break;
        k = 0;
        break label1312;
        m = 0;
        break label1393;
      }
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
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.time.TimingBussines
 * JD-Core Version:    0.6.0
 */