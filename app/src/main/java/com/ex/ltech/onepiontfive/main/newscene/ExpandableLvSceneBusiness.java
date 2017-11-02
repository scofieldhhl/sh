package com.ex.ltech.onepiontfive.main.newscene;

import android.content.Context;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.RoomLvData;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.SceneStep;
import com.ex.ltech.onepiontfive.main.vo.SceneSteps;
import com.ex.ltech.onepiontfive.main.vo.SceneTouchSensor;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.ex.ltech.onepiontfive.main.vo.SendStep;
import com.ex.ltech.remote.control.vo.YkVo;
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
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ExpandableLvSceneBusiness extends MyBusiness
{
  int bigStep = 0;
  ArrayList<String> blubSeleted2Hex = new ArrayList();
  ArrayList<Integer> cmd = new ArrayList();
  int cmdHeadDataLenth = 11;
  String condition;
  private Context context;
  int dataLenthPosi = 10;
  Runnable exceptionRunnable = new Runnable()
  {
    public void run()
    {
      ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.loopRunnable);
      ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.resendRunnable);
      ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.loopDelRunnable);
      ExpandableLvSceneBusiness.this.setMySendListener(null);
      if (ExpandableLvSceneBusiness.this.ftFinishAddScene != null)
        ExpandableLvSceneBusiness.this.ftFinishAddScene.onCreateSceneFailed();
    }
  };
  FtFinishAddScene ftFinishAddScene;
  FtScene ftScene;
  Handler handler = new Handler()
  {
  };
  Home home;
  public boolean isCreateNewScene;
  public boolean isSceneOnOff;
  private String lastReturnData = "";
  LoopDelRunnable loopDelRunnable;
  LoopRunnable loopRunnable;
  int loopTime = 0;
  ETGroup mGroup;
  ArrayList<RoomLvChildVo> mVos = null;
  String mac;
  ResendRunnable resendRunnable;
  int resendTime;
  RoomLvData roomLvData;
  String roomName = "";
  byte[] sceneNameByte;
  public int sceneOperea = 1;
  SceneSteps sceneSteps = new SceneSteps();
  String seletedDvcNames = "";
  int seletedDvcType = -1;
  int seletedSceneNum;
  int sendRcCodeOrder = 10;
  ArrayList<SendStep> sendSteps = new ArrayList();
  ArrayList<String> sensorSeleted2Hex = new ArrayList();
  public Scenes smartScenes;
  ArrayList<Scene> smartScenesList = new ArrayList();
  int stepInnerSendTime = 0;
  ArrayList<Integer> stepInnerSendTimes = new ArrayList();
  int stepTime = 0;
  int stepsCount;
  private byte[] uId = new byte[4];
  public String userIdHexString;

  public ExpandableLvSceneBusiness(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.roomLvData = new RoomLvData();
    ETPage.getInstance(paramContext).Load(ETDB.getInstance(paramContext));
    this.mGroup = ((ETGroup)ETPage.getInstance(paramContext).GetItem(0));
    this.loopRunnable = new LoopRunnable();
    this.loopDelRunnable = new LoopDelRunnable();
    this.resendRunnable = new ResendRunnable();
    this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
    for (int i = this.userIdHexString.length(); i < 8; i++)
      this.userIdHexString = ("0" + this.userIdHexString);
    this.uId[0] = (byte)Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    this.uId[1] = (byte)Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    this.uId[2] = (byte)Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    this.uId[3] = (byte)Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
  }

  private String StrToBinstr(String paramString)
  {
    char[] arrayOfChar = paramString.toCharArray();
    String str = "";
    for (int i = 0; i < arrayOfChar.length; i++)
      str = str + Integer.toBinaryString(arrayOfChar[i]) + " ";
    return str;
  }

  private void handleOtherRcCode(RoomLvChildVo paramRoomLvChildVo)
  {
    String str = paramRoomLvChildVo.getOthersRC();
    String[] arrayOfString = new String[str.length() / 2];
    for (int i = 0; i < str.length() / 2; i++)
      arrayOfString[i] = str.substring(i + i * 1, 2 + i * 2);
    for (int j = 0; j < arrayOfString.length; j++)
    {
      arrayOfString[j].getBytes();
      Log.i("", "");
    }
    int k = str.length() / 2;
    byte[] arrayOfByte = new byte[k];
    for (int m = 0; m < k; m++)
      arrayOfByte[m] = Integer.valueOf(str.substring(m * 2, 2 + m * 2), 16).byteValue();
    this.sendSteps.add(new SendStep(arrayOfByte));
    Log.i("", "");
  }

  private void handleRcCode(YkVo paramYkVo)
    throws Exception
  {
    String str1;
    int i;
    if (paramYkVo != null)
    {
      str1 = paramYkVo.getType();
      i = -1;
      switch (str1.hashCode())
      {
      default:
      case 96586:
      case 3714:
      case 110741513:
      case 3239401:
      case -894830916:
      case 101139:
      case 99858:
      }
    }
    while (true)
      switch (i)
      {
      default:
        return;
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
        if (!str1.equals("iptv"))
          continue;
        i = 3;
        continue;
        if (!str1.equals("projector"))
          continue;
        i = 4;
        continue;
        if (!str1.equals("fan"))
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
    ETDeviceAIR localETDeviceAIR = (ETDeviceAIR)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceAIR.Load(ETDB.getInstance(this.ct));
    if (!paramYkVo.getStatus().equals(this.ct.getString(2131100226)))
      try
      {
        localETDeviceAIR.GetKeyValue(49153);
        localETDeviceAIR.GetKeyValue(49153);
        String[] arrayOfString = paramYkVo.getStatus().split(",");
        localETDeviceAIR.SetTemp(Byte.parseByte(arrayOfString[1].substring(0, arrayOfString[1].indexOf("℃"))));
        localETDeviceAIR.GetKeyValue(49165);
        String str2 = this.ct.getString(2131099860);
        String str3 = this.ct.getString(2131099861);
        String str4 = this.ct.getString(2131099862);
        String str5 = this.ct.getString(2131099863);
        String str6 = this.ct.getString(2131099864);
        if (arrayOfString[0].equals(str2))
          localETDeviceAIR.SetMode(1);
        if (arrayOfString[0].equals(str3))
          localETDeviceAIR.SetMode(2);
        if (arrayOfString[0].equals(str4))
          localETDeviceAIR.SetMode(3);
        if (arrayOfString[0].equals(str5))
          localETDeviceAIR.SetMode(4);
        if (arrayOfString[0].equals(str6))
          localETDeviceAIR.SetMode(5);
        byte[] arrayOfByte15 = localETDeviceAIR.GetKeyValue(49155);
        ArrayList localArrayList18 = this.sendSteps;
        SendStep localSendStep18 = new SendStep(arrayOfByte15);
        localArrayList18.add(localSendStep18);
        System.out.println("Tuhwerluifaweoiugyewrfg" + StringUtils.btye2Str(arrayOfByte15));
        localETDeviceAIR.GetKeyValue(49153);
        return;
      }
      catch (Exception localException)
      {
        while (true)
        {
          localETDeviceAIR.GetKeyValue(49153);
          byte[] arrayOfByte14 = localETDeviceAIR.GetKeyValue(49153);
          ArrayList localArrayList17 = this.sendSteps;
          SendStep localSendStep17 = new SendStep(arrayOfByte14);
          localArrayList17.add(localSendStep17);
          localException.printStackTrace();
        }
      }
    byte[] arrayOfByte13 = localETDeviceAIR.GetKeyValue(49153);
    ArrayList localArrayList16 = this.sendSteps;
    SendStep localSendStep16 = new SendStep(arrayOfByte13);
    localArrayList16.add(localSendStep16);
    return;
    ETDeviceTV localETDeviceTV = (ETDeviceTV)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceTV.Load(ETDB.getInstance(this.ct));
    byte[] arrayOfByte10;
    if ((paramYkVo.getStatus().equals(this.ct.getString(2131100232))) || (paramYkVo.getStatus().equals(this.ct.getString(2131100226))))
    {
      boolean bool3 = paramYkVo.getStatus().equals(this.ct.getString(2131100232));
      arrayOfByte10 = null;
      if (bool3)
        arrayOfByte10 = localETDeviceTV.GetKeyValue(8203);
      if (paramYkVo.getStatus().equals(this.ct.getString(2131100226)))
      {
        localETDeviceTV.GetKeyValue(8203);
        arrayOfByte10 = localETDeviceTV.GetKeyValue(8203);
      }
    }
    while (true)
    {
      ArrayList localArrayList12 = this.sendSteps;
      SendStep localSendStep12 = new SendStep(arrayOfByte10);
      localArrayList12.add(localSendStep12);
      return;
      int i3 = Integer.parseInt(paramYkVo.getStatus().substring(0, paramYkVo.getStatus().indexOf(this.ct.getString(2131099929))));
      arrayOfByte10 = null;
      if (i3 > 99)
      {
        byte[] arrayOfByte11 = localETDeviceTV.GetKeyValue(8207);
        ArrayList localArrayList14 = this.sendSteps;
        SendStep localSendStep14 = new SendStep(arrayOfByte11);
        localArrayList14.add(localSendStep14);
        SystemClock.sleep(500L);
        byte[] arrayOfByte12 = localETDeviceTV.GetKeyValue(8227);
        ArrayList localArrayList15 = this.sendSteps;
        SendStep localSendStep15 = new SendStep(arrayOfByte12);
        localArrayList15.add(localSendStep15);
        SystemClock.sleep(500L);
        arrayOfByte10 = localETDeviceTV.GetKeyValue(8227);
      }
      if ((i3 < 100) && (i3 > 9))
      {
        int i4 = i3 / 10;
        int i5 = i3 - i4 * 10;
        switch (i4)
        {
        default:
          label1048: ArrayList localArrayList13 = this.sendSteps;
          SendStep localSendStep13 = new SendStep(arrayOfByte10);
          localArrayList13.add(localSendStep13);
          SystemClock.sleep(500L);
          switch (i5)
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
      while (i3 < 10)
        switch (i3)
        {
        default:
          break;
        case 0:
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8227);
          break;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8227);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8207);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8209);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8211);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8213);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8215);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8217);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8219);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8221);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8223);
          break label1048;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8227);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8207);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8209);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8211);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8213);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8215);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8217);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8219);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8221);
          continue;
          arrayOfByte10 = localETDeviceTV.GetKeyValue(8223);
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
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8207);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8209);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8211);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8213);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8215);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8217);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8219);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8221);
      continue;
      arrayOfByte10 = localETDeviceTV.GetKeyValue(8223);
    }
    ETDeviceSTB localETDeviceSTB = (ETDeviceSTB)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceSTB.Load(ETDB.getInstance(this.ct));
    byte[] arrayOfByte7;
    if ((paramYkVo.getStatus().equals(this.ct.getString(2131100232))) || (paramYkVo.getStatus().equals(this.ct.getString(2131100226))))
    {
      boolean bool2 = paramYkVo.getStatus().equals(this.ct.getString(2131100232));
      arrayOfByte7 = null;
      if (bool2)
        arrayOfByte7 = localETDeviceSTB.GetKeyValue(16385);
      if (paramYkVo.getStatus().equals(this.ct.getString(2131100226)))
      {
        localETDeviceSTB.GetKeyValue(16385);
        arrayOfByte7 = localETDeviceSTB.GetKeyValue(16385);
      }
    }
    while (true)
    {
      ArrayList localArrayList8 = this.sendSteps;
      SendStep localSendStep8 = new SendStep(arrayOfByte7);
      localArrayList8.add(localSendStep8);
      return;
      int n = Integer.parseInt(paramYkVo.getStatus().substring(0, paramYkVo.getStatus().indexOf(this.ct.getString(2131099929))));
      arrayOfByte7 = null;
      if (n > 99)
      {
        byte[] arrayOfByte8 = localETDeviceSTB.GetKeyValue(16387);
        ArrayList localArrayList10 = this.sendSteps;
        SendStep localSendStep10 = new SendStep(arrayOfByte8);
        localArrayList10.add(localSendStep10);
        SystemClock.sleep(500L);
        byte[] arrayOfByte9 = localETDeviceSTB.GetKeyValue(16407);
        ArrayList localArrayList11 = this.sendSteps;
        SendStep localSendStep11 = new SendStep(arrayOfByte9);
        localArrayList11.add(localSendStep11);
        SystemClock.sleep(500L);
        arrayOfByte7 = localETDeviceSTB.GetKeyValue(16407);
      }
      if ((n < 100) && (n > 9))
      {
        int i1 = n / 10;
        int i2 = n - i1 * 10;
        switch (i1)
        {
        default:
          label1984: ArrayList localArrayList9 = this.sendSteps;
          SendStep localSendStep9 = new SendStep(arrayOfByte7);
          localArrayList9.add(localSendStep9);
          SystemClock.sleep(500L);
          switch (i2)
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
      while (n < 10)
        switch (n)
        {
        default:
          break;
        case 0:
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16407);
          break;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16407);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16387);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16389);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16391);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16393);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16395);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16397);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16399);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16401);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16403);
          break label1984;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16407);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16387);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16389);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16391);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16393);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16395);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16397);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16399);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16401);
          continue;
          arrayOfByte7 = localETDeviceSTB.GetKeyValue(16403);
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
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16387);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16389);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16391);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16393);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16395);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16397);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16399);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16401);
      continue;
      arrayOfByte7 = localETDeviceSTB.GetKeyValue(16403);
    }
    ETDeviceIPTV localETDeviceIPTV = (ETDeviceIPTV)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceIPTV.Load(ETDB.getInstance(this.ct));
    byte[] arrayOfByte4;
    if ((paramYkVo.getStatus().equals(this.ct.getString(2131100232))) || (paramYkVo.getStatus().equals(this.ct.getString(2131100226))))
    {
      boolean bool1 = paramYkVo.getStatus().equals(this.ct.getString(2131100232));
      arrayOfByte4 = null;
      if (bool1)
        arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8449);
      if (paramYkVo.getStatus().equals(this.ct.getString(2131100226)))
      {
        localETDeviceIPTV.GetKeyValue(8449);
        arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8449);
      }
    }
    while (true)
    {
      ArrayList localArrayList4 = this.sendSteps;
      SendStep localSendStep4 = new SendStep(arrayOfByte4);
      localArrayList4.add(localSendStep4);
      return;
      int j = Integer.parseInt(paramYkVo.getStatus().substring(0, paramYkVo.getStatus().indexOf(this.ct.getString(2131099929))));
      arrayOfByte4 = null;
      if (j > 99)
      {
        byte[] arrayOfByte5 = localETDeviceIPTV.GetKeyValue(8473);
        ArrayList localArrayList6 = this.sendSteps;
        SendStep localSendStep6 = new SendStep(arrayOfByte5);
        localArrayList6.add(localSendStep6);
        SystemClock.sleep(500L);
        byte[] arrayOfByte6 = localETDeviceIPTV.GetKeyValue(8491);
        ArrayList localArrayList7 = this.sendSteps;
        SendStep localSendStep7 = new SendStep(arrayOfByte6);
        localArrayList7.add(localSendStep7);
        SystemClock.sleep(500L);
        arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8491);
      }
      if ((j < 100) && (j > 9))
      {
        int k = j / 10;
        int m = j - k * 10;
        switch (k)
        {
        default:
          label2920: ArrayList localArrayList5 = this.sendSteps;
          SendStep localSendStep5 = new SendStep(arrayOfByte4);
          localArrayList5.add(localSendStep5);
          SystemClock.sleep(500L);
          switch (m)
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
      while (j < 10)
        switch (j)
        {
        default:
          break;
        case 0:
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8491);
          break;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8491);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8473);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8475);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8477);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8479);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8481);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8483);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8485);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8487);
          break label2920;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8489);
          break label2920;
          localETDeviceIPTV.GetKeyValue(8491);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8473);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8475);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8477);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8479);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8481);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8483);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8485);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8487);
          continue;
          arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8489);
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
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8473);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8475);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8477);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8479);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8481);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8483);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8485);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8487);
      continue;
      arrayOfByte4 = localETDeviceIPTV.GetKeyValue(8489);
    }
    ETDevicePJT localETDevicePJT = (ETDevicePJT)this.mGroup.GetItem(paramYkVo.getId());
    localETDevicePJT.Load(ETDB.getInstance(this.ct));
    if (paramYkVo.getStatus().equals(this.ct.getString(2131100232)));
    for (byte[] arrayOfByte3 = localETDevicePJT.GetKeyValue(40961); ; arrayOfByte3 = localETDevicePJT.GetKeyValue(40963))
    {
      ArrayList localArrayList3 = this.sendSteps;
      SendStep localSendStep3 = new SendStep(arrayOfByte3);
      localArrayList3.add(localSendStep3);
      return;
    }
    ETDeviceFANS localETDeviceFANS = (ETDeviceFANS)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceFANS.Load(ETDB.getInstance(this.ct));
    if (paramYkVo.getStatus().equals(this.ct.getString(2131100232)))
      localETDeviceFANS.GetKeyValue(32769);
    for (byte[] arrayOfByte2 = localETDeviceFANS.GetKeyValue(32769); ; arrayOfByte2 = localETDeviceFANS.GetKeyValue(32769))
    {
      ArrayList localArrayList2 = this.sendSteps;
      SendStep localSendStep2 = new SendStep(arrayOfByte2);
      localArrayList2.add(localSendStep2);
      return;
    }
    ETDeviceDVD localETDeviceDVD = (ETDeviceDVD)this.mGroup.GetItem(paramYkVo.getId());
    localETDeviceDVD.Load(ETDB.getInstance(this.ct));
    if (paramYkVo.getStatus().equals(this.ct.getString(2131100232)))
      localETDeviceDVD.GetKeyValue(24587);
    for (byte[] arrayOfByte1 = localETDeviceDVD.GetKeyValue(24587); ; arrayOfByte1 = localETDeviceDVD.GetKeyValue(24587))
    {
      ArrayList localArrayList1 = this.sendSteps;
      SendStep localSendStep1 = new SendStep(arrayOfByte1);
      localArrayList1.add(localSendStep1);
      return;
    }
  }

  private void sendRcCode(ArrayList<Integer> paramArrayList, byte[] paramArrayOfByte)
  {
    if (this.sendRcCodeOrder > 255)
      this.sendRcCodeOrder = 1;
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramArrayList);
    localArrayList.add(Integer.valueOf(this.sendRcCodeOrder));
    localArrayList.add(Integer.valueOf(paramArrayOfByte.length));
    for (int i = 0; i < paramArrayOfByte.length; i++)
      localArrayList.add(Integer.valueOf(Integer.parseInt(toHex(paramArrayOfByte[i]), 16)));
    localArrayList.add(this.dataLenthPosi, Integer.valueOf(localArrayList.size() - this.dataLenthPosi));
    localArrayList.add(Integer.valueOf(1));
    addCheckSumData(localArrayList);
    localArrayList.add(Integer.valueOf(22));
    sendCmd(localArrayList);
    this.sendRcCodeOrder = (1 + this.sendRcCodeOrder);
  }

  private String toHex(byte paramByte)
  {
    String str = Integer.toHexString(paramByte & 0xFF);
    if (str.length() == 1)
      str = '0' + str;
    return str;
  }

  public void clearDeviceSeleted()
  {
    for (int i = 0; i < this.roomLvData.getRooms().size(); i++)
      for (int j = 0; j < ((Room)this.roomLvData.getRooms().get(i)).getExpandableLvInnerItemVos().size(); j++)
        ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(i)).getExpandableLvInnerItemVos().get(j)).setSwich(false);
  }

  public void clearSceneSeleted()
  {
  }

  // ERROR //
  public List compareWithReturnInfo(String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 233	java/lang/String:length	()I
    //   4: istore_3
    //   5: aload_1
    //   6: bipush 18
    //   8: bipush 20
    //   10: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   13: ldc_w 500
    //   16: invokevirtual 504	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   19: istore 4
    //   21: aconst_null
    //   22: astore 5
    //   24: iload 4
    //   26: ifeq +136 -> 162
    //   29: aload_1
    //   30: iconst_0
    //   31: iload_3
    //   32: bipush 6
    //   34: isub
    //   35: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   38: pop
    //   39: aload_0
    //   40: aload_1
    //   41: invokevirtual 506	com/ex/ltech/onepiontfive/main/newscene/ExpandableLvSceneBusiness:addCheckSumData	(Ljava/lang/String;)Z
    //   44: istore 7
    //   46: aload_1
    //   47: bipush 20
    //   49: bipush 22
    //   51: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   54: bipush 16
    //   56: invokestatic 253	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   59: istore 8
    //   61: new 77	java/util/ArrayList
    //   64: dup
    //   65: invokespecial 80	java/util/ArrayList:<init>	()V
    //   68: astore 9
    //   70: iload 7
    //   72: ifeq +86 -> 158
    //   75: iload 8
    //   77: iconst_2
    //   78: if_icmple +80 -> 158
    //   81: aload_1
    //   82: bipush 26
    //   84: bipush 248
    //   86: aload_1
    //   87: invokevirtual 233	java/lang/String:length	()I
    //   90: iadd
    //   91: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   94: astore 10
    //   96: iconst_0
    //   97: istore 11
    //   99: iload 11
    //   101: iload 8
    //   103: iconst_2
    //   104: isub
    //   105: if_icmpge +53 -> 158
    //   108: aload 9
    //   110: aload 10
    //   112: iload 11
    //   114: iconst_2
    //   115: imul
    //   116: iconst_2
    //   117: iload 11
    //   119: iconst_2
    //   120: imul
    //   121: iadd
    //   122: invokevirtual 249	java/lang/String:substring	(II)Ljava/lang/String;
    //   125: bipush 16
    //   127: invokestatic 253	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   130: invokestatic 457	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   133: invokeinterface 509 2 0
    //   138: pop
    //   139: iinc 11 1
    //   142: goto -43 -> 99
    //   145: astore_2
    //   146: aload_2
    //   147: invokevirtual 409	java/lang/Exception:printStackTrace	()V
    //   150: new 77	java/util/ArrayList
    //   153: dup
    //   154: invokespecial 80	java/util/ArrayList:<init>	()V
    //   157: areturn
    //   158: aload 9
    //   160: astore 5
    //   162: aload 5
    //   164: areturn
    //   165: astore_2
    //   166: goto -20 -> 146
    //
    // Exception table:
    //   from	to	target	type
    //   0	21	145	java/lang/Exception
    //   29	70	145	java/lang/Exception
    //   81	96	165	java/lang/Exception
    //   108	139	165	java/lang/Exception
  }

  public String copyZero(String paramString)
  {
    int i = paramString.length();
    for (int j = 0; j < 8 - i; j++)
      paramString = "0" + paramString;
    return paramString;
  }

  public SceneStep createSceneStep(int paramInt, String paramString)
  {
    SceneStep localSceneStep = new SceneStep();
    ArrayList localArrayList1 = new ArrayList();
    String str1 = StringUtils.reverse(hexString2binaryString(paramString.substring(6, 8)) + hexString2binaryString(paramString.substring(4, 6)) + hexString2binaryString(paramString.substring(2, 4)) + hexString2binaryString(paramString.substring(0, 2)));
    ArrayList localArrayList2 = this.home.getRooms();
    for (int i = 0; i < str1.length(); i++)
    {
      if (!str1.substring(i, i + 1).equals("1"))
        continue;
      RoomLvChildVo localRoomLvChildVo = new RoomLvChildVo();
      if (paramInt == 47)
      {
        localRoomLvChildVo.setDvcIndex(142 + (i + 1));
        localRoomLvChildVo.setInnerDeviceName(this.context.getString(2131100345));
      }
      for (int k = 0; ; k++)
      {
        if (k >= localArrayList2.size())
          break label779;
        ArrayList localArrayList3 = new ArrayList();
        int m = 0;
        label193: if (m < ((Room)localArrayList2.get(k)).getDvcVos().size())
        {
          Dvc localDvc = (Dvc)((Room)localArrayList2.get(k)).getDvcVos().get(m);
          if (!localDvc.isGroup())
            if (localRoomLvChildVo.getDvcIndex() == localDvc.getmIndex())
            {
              localRoomLvChildVo.setInnerItemType(localDvc.getType());
              localRoomLvChildVo.setInnerDeviceName(localDvc.getName());
              switch (m)
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
              }
            }
          int n;
          do
          {
            while (true)
            {
              m++;
              break label193;
              localRoomLvChildVo.setDvcIndex(i + 1);
              break;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100349));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100350));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100351));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100352));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100353));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100354));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100355));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100356));
            }
            n = 0;
          }
          while (n >= localDvc.innerDvcVos.size());
          if (localRoomLvChildVo.getDvcIndex() == ((Dvc)localDvc.innerDvcVos.get(n)).getmIndex())
          {
            localRoomLvChildVo.setInnerItemType(localDvc.getType());
            localRoomLvChildVo.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(n)).getName());
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
            }
          }
          while (true)
          {
            n++;
            break;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100349));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100350));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100351));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100352));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100353));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100354));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100355));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100356));
          }
        }
        ((Room)localArrayList2.get(k)).setExpandableLvInnerItemVos(localArrayList3);
      }
      label779: localArrayList1.add(localRoomLvChildVo);
    }
    String str2 = "";
    for (int j = 0; j < localArrayList1.size(); j++)
      str2 = str2 + ((RoomLvChildVo)localArrayList1.get(j)).getInnerDeviceName() + "\t";
    localSceneStep.setSeletedDvcNames(str2);
    localSceneStep.setRoomName(((RoomLvChildVo)localArrayList1.get(0)).getParentRoomName());
    localSceneStep.setSeletedDvcs(localArrayList1);
    return localSceneStep;
  }

  public SceneStep createSceneStep(String paramString)
  {
    SceneStep localSceneStep = new SceneStep();
    ArrayList localArrayList1 = new ArrayList();
    String str1 = StringUtils.reverse(hexString2binaryString(paramString.substring(6, 8)) + hexString2binaryString(paramString.substring(4, 6)) + hexString2binaryString(paramString.substring(2, 4)) + hexString2binaryString(paramString.substring(0, 2)));
    ArrayList localArrayList2 = this.home.getRooms();
    for (int i = 0; i < str1.length(); i++)
    {
      if (!str1.substring(i, i + 1).equals("1"))
        continue;
      RoomLvChildVo localRoomLvChildVo = new RoomLvChildVo();
      localRoomLvChildVo.setDvcIndex(i + 1);
      localRoomLvChildVo.setInnerDeviceName(this.context.getString(2131100345));
      for (int k = 0; k < localArrayList2.size(); k++)
      {
        ArrayList localArrayList3 = new ArrayList();
        int m = 0;
        if (m < ((Room)localArrayList2.get(k)).getDvcVos().size())
        {
          Dvc localDvc = (Dvc)((Room)localArrayList2.get(k)).getDvcVos().get(m);
          if (!localDvc.isGroup())
            if (localRoomLvChildVo.getDvcIndex() == localDvc.getmIndex())
            {
              localRoomLvChildVo.setInnerItemType(localDvc.getType());
              localRoomLvChildVo.setInnerDeviceName(localDvc.getName());
              switch (m)
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
              }
            }
          int n;
          do
          {
            while (true)
            {
              m++;
              break;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100349));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100350));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100351));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100352));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100353));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100354));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100355));
              continue;
              localRoomLvChildVo.setParentRoomName(this.context.getString(2131100356));
            }
            n = 0;
          }
          while (n >= localDvc.innerDvcVos.size());
          if (localRoomLvChildVo.getDvcIndex() == ((Dvc)localDvc.innerDvcVos.get(n)).getmIndex())
          {
            localRoomLvChildVo.setInnerItemType(localDvc.getType());
            localRoomLvChildVo.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(n)).getName());
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
            }
          }
          while (true)
          {
            n++;
            break;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100349));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100350));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100351));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100352));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100353));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100354));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100355));
            continue;
            localRoomLvChildVo.setParentRoomName(this.context.getString(2131100356));
          }
        }
        ((Room)localArrayList2.get(k)).setExpandableLvInnerItemVos(localArrayList3);
      }
      localArrayList1.add(localRoomLvChildVo);
    }
    String str2 = "";
    for (int j = 0; j < localArrayList1.size(); j++)
      str2 = str2 + ((RoomLvChildVo)localArrayList1.get(j)).getInnerDeviceName() + "\t";
    localSceneStep.setSeletedDvcNames(str2);
    localSceneStep.setRoomName(((RoomLvChildVo)localArrayList1.get(0)).getParentRoomName());
    localSceneStep.setSeletedDvcs(localArrayList1);
    return localSceneStep;
  }

  public void delMyScene(int paramInt)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
    this.loopTime = (1 + this.loopTime);
  }

  public void delPreprea(int paramInt, Scene paramScene)
  {
    this.handler.removeCallbacks(this.exceptionRunnable);
    this.handler.postDelayed(this.exceptionRunnable, 80000L);
    this.seletedSceneNum = paramInt;
    this.sendSteps.clear();
    int i = 0;
    ArrayList localArrayList;
    if (i < paramScene.getSceneSteps().getSteps().size())
    {
      localArrayList = ((SceneStep)paramScene.getSceneSteps().getSteps().get(i)).getSeletedDvcs();
      if (localArrayList == null);
    }
    for (int j = 0; ; j++)
      if (j < localArrayList.size())
      {
        RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)localArrayList.get(j);
        if (localRoomLvChildVo.getInnerItemType() == 21)
        {
          try
          {
            if (localRoomLvChildVo.getYkVo() != null)
              handleRcCode(localRoomLvChildVo.getYkVo());
            while (true)
            {
              j = localArrayList.size();
              break;
              handleOtherRcCode(localRoomLvChildVo);
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        else
        {
          this.sendSteps.add(new SendStep(null));
          j = localArrayList.size();
        }
      }
      else
      {
        i++;
        break;
        this.stepTime = this.sendSteps.size();
        setMySendListener(new MyBusiness.MySendListener(paramScene)
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            try
            {
              if (paramArrayOfByte.length < 15)
                return;
              String str1 = StringUtils.btye2Str(paramArrayOfByte);
              if (!ExpandableLvSceneBusiness.this.lastReturnData.equalsIgnoreCase(str1))
              {
                ExpandableLvSceneBusiness.access$002(ExpandableLvSceneBusiness.this, str1);
                String str2 = Integer.toBinaryString(Integer.valueOf(str1.substring(24, 26), 16).intValue());
                int i = Integer.valueOf(str2.substring(-4 + str2.length(), str2.length()), 2).intValue();
                this.val$scene.setmNum(i);
                System.out.println("loopTimeloopTimeloopTime      loopTime      = " + ExpandableLvSceneBusiness.this.loopTime + "stepTime" + ExpandableLvSceneBusiness.this.stepTime);
                if (ExpandableLvSceneBusiness.this.loopTime < ExpandableLvSceneBusiness.this.stepTime)
                {
                  ExpandableLvSceneBusiness.this.loopDelRunnable.scene = this.val$scene;
                  ExpandableLvSceneBusiness.this.handler.postDelayed(ExpandableLvSceneBusiness.this.loopDelRunnable, 1000L);
                }
                if (ExpandableLvSceneBusiness.this.loopTime == ExpandableLvSceneBusiness.this.stepTime)
                {
                  ExpandableLvSceneBusiness.this.loopTime = 0;
                  if (ExpandableLvSceneBusiness.this.ftFinishAddScene != null)
                    ExpandableLvSceneBusiness.this.ftFinishAddScene.onCreateSceneOk(i);
                  if (ExpandableLvSceneBusiness.this.ftScene != null)
                    ExpandableLvSceneBusiness.this.ftScene.onDelSceneOk();
                  System.out.println("onCreateSceneOk     sceneNum = " + ExpandableLvSceneBusiness.this.loopTime);
                  ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.loopDelRunnable);
                  ExpandableLvSceneBusiness.this.setMySendListener(null);
                  return;
                }
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }

          public void onTimeOut()
          {
          }
        });
        return;
      }
  }

  public void delScene(int paramInt)
  {
    this.smartScenes.smartScenes.remove(paramInt);
    putData4ClassName(this.mac, this.smartScenes);
    ((Scenes)getBean4ClassName(this.mac, Scenes.class));
  }

  public RoomLvData getAddBlubListData()
  {
    ArrayList localArrayList1 = this.home.getRooms();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      label397: for (int j = 0; j < ((Room)localArrayList1.get(i)).getDvcVos().size(); j++)
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        int k;
        int m;
        label95: int i1;
        label115: int i2;
        if (localDvc.getType() == 8)
        {
          k = 1;
          if (localDvc.getType() != 12)
            break label312;
          m = 1;
          int n = m | k;
          if (localDvc.getType() != 9)
            break label318;
          i1 = 1;
          i2 = n | i1;
          if (localDvc.getType() != 11)
            break label324;
        }
        label312: label318: label324: for (int i3 = 1; ; i3 = 0)
        {
          if ((i3 | i2) == 0)
            break label397;
          if ((!localDvc.isGroup()) || (localDvc.innerDvcVos.size() <= 0))
            break label330;
          for (int i4 = 0; i4 < localDvc.innerDvcVos.size(); i4++)
          {
            RoomLvChildVo localRoomLvChildVo2 = new RoomLvChildVo();
            localRoomLvChildVo2.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(i4)).getName());
            localRoomLvChildVo2.setInnerItemType(((Dvc)localDvc.innerDvcVos.get(i4)).getType());
            localRoomLvChildVo2.setPanelLampVO(((Dvc)localDvc.innerDvcVos.get(i4)).getPanelLampVO());
            localRoomLvChildVo2.setDvcIndex(((Dvc)localDvc.innerDvcVos.get(i4)).getmIndex());
            localArrayList2.add(localRoomLvChildVo2);
            System.out.println(((Dvc)localDvc.innerDvcVos.get(i4)).getmIndex());
          }
          k = 0;
          break;
          m = 0;
          break label95;
          i1 = 0;
          break label115;
        }
        label330: RoomLvChildVo localRoomLvChildVo1 = new RoomLvChildVo();
        localRoomLvChildVo1.setInnerDeviceName(localDvc.getName());
        localRoomLvChildVo1.setInnerItemType(localDvc.getType());
        localRoomLvChildVo1.setPanelLampVO(localDvc.getPanelLampVO());
        localRoomLvChildVo1.setDvcIndex(localDvc.getmIndex());
        localArrayList2.add(localRoomLvChildVo1);
        System.out.println(localDvc.getmIndex());
      }
      ((Room)localArrayList1.get(i)).setExpandableLvInnerItemVos(localArrayList2);
    }
    for (int i5 = 0; i5 < localArrayList1.size(); i5++)
    {
      if (((Room)localArrayList1.get(i5)).getExpandableLvInnerItemVos().size() >= 1)
        continue;
      localArrayList1.remove(i5);
      i5--;
    }
    this.roomLvData.setRooms(localArrayList1);
    return this.roomLvData;
  }

  public ArrayList<Dvc> getAddSensorListData()
  {
    ArrayList localArrayList1 = this.home.getRooms();
    ArrayList localArrayList2 = new ArrayList();
    for (int i = 0; i < localArrayList1.size(); i++)
      for (int j = 0; j < ((Room)localArrayList1.get(i)).getDvcVos().size(); j++)
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        if (localDvc.getType() != 14)
          continue;
        localDvc.setIsClickSeleted(false);
        localDvc.innerDvcVos.clear();
        localArrayList2.add(localDvc);
      }
    return localArrayList2;
  }

  public RoomLvData getAddTaskListData()
  {
    ArrayList localArrayList1 = this.home.getRooms();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      int j = 0;
      if (j < ((Room)localArrayList1.get(i)).getDvcVos().size())
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        int m;
        label90: int n;
        label103: int i2;
        label123: int i4;
        label143: int i6;
        label163: int i8;
        label183: int i10;
        label203: int i12;
        label223: int i14;
        label243: RoomLvChildVo localRoomLvChildVo2;
        int i15;
        label310: int i16;
        label323: int i18;
        label343: int i20;
        if (!localDvc.isGroup())
          if (localDvc.getType() == 21)
          {
            m = 1;
            if (localDvc.getType() != 8)
              break label390;
            n = 1;
            int i1 = n | m;
            if (localDvc.getType() != 12)
              break label396;
            i2 = 1;
            int i3 = i1 | i2;
            if (localDvc.getType() != 9)
              break label402;
            i4 = 1;
            int i5 = i3 | i4;
            if (localDvc.getType() != 11)
              break label408;
            i6 = 1;
            int i7 = i5 | i6;
            if (localDvc.getType() != 15)
              break label414;
            i8 = 1;
            int i9 = i7 | i8;
            if (localDvc.getType() != 16)
              break label420;
            i10 = 1;
            int i11 = i9 | i10;
            if (localDvc.getType() != 17)
              break label426;
            i12 = 1;
            int i13 = i11 | i12;
            if (localDvc.getType() != 18)
              break label432;
            i14 = 1;
            if ((i14 | i13) != 0)
            {
              localRoomLvChildVo2 = new RoomLvChildVo();
              localRoomLvChildVo2.setInnerDeviceName(localDvc.getName());
              localRoomLvChildVo2.setInnerItemType(localDvc.getType());
              localRoomLvChildVo2.setDvcIndex(localDvc.getmIndex());
              localArrayList2.add(localRoomLvChildVo2);
              if (localDvc.getType() != 8)
                break label438;
              i15 = 1;
              if (localDvc.getType() != 12)
                break label444;
              i16 = 1;
              int i17 = i16 | i15;
              if (localDvc.getType() != 9)
                break label450;
              i18 = 1;
              int i19 = i17 | i18;
              if (localDvc.getType() != 11)
                break label456;
              i20 = 1;
              label363: if ((i20 | i19) == 0)
                break label462;
              localRoomLvChildVo2.setInnerItemSeletedType(8);
            }
          }
        while (true)
        {
          j++;
          break;
          m = 0;
          break label90;
          label390: n = 0;
          break label103;
          label396: i2 = 0;
          break label123;
          label402: i4 = 0;
          break label143;
          label408: i6 = 0;
          break label163;
          label414: i8 = 0;
          break label183;
          label420: i10 = 0;
          break label203;
          label426: i12 = 0;
          break label223;
          label432: i14 = 0;
          break label243;
          label438: i15 = 0;
          break label310;
          label444: i16 = 0;
          break label323;
          label450: i18 = 0;
          break label343;
          label456: i20 = 0;
          break label363;
          label462: localRoomLvChildVo2.setInnerItemSeletedType(localDvc.getType());
          continue;
          for (int k = 0; k < localDvc.innerDvcVos.size(); k++)
          {
            RoomLvChildVo localRoomLvChildVo1 = new RoomLvChildVo();
            localRoomLvChildVo1.setInnerItemSeletedType(8);
            localRoomLvChildVo1.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(k)).getName());
            localRoomLvChildVo1.setInnerItemType(((Dvc)localDvc.innerDvcVos.get(k)).getType());
            localRoomLvChildVo1.setDvcIndex(((Dvc)localDvc.innerDvcVos.get(k)).getmIndex());
            localArrayList2.add(localRoomLvChildVo1);
          }
        }
      }
      ((Room)localArrayList1.get(i)).setExpandableLvInnerItemVos(localArrayList2);
    }
    for (int i21 = 0; i21 < localArrayList1.size(); i21++)
    {
      if (((Room)localArrayList1.get(i21)).getExpandableLvInnerItemVos().size() >= 1)
        continue;
      localArrayList1.remove(i21);
      i21--;
    }
    this.roomLvData.setRooms(localArrayList1);
    return this.roomLvData;
  }

  public RoomLvData getAddTaskListData(ArrayList<Room> paramArrayList)
  {
    this.roomLvData.setRooms(paramArrayList);
    return this.roomLvData;
  }

  public RoomLvData getCacheData()
  {
    return (RoomLvData)getCacheBean(RoomLvData.class);
  }

  public ArrayList<RoomLvChildVo> getEnableAddTaskInnerItemVos()
  {
    while (true)
    {
      int i;
      int j;
      RoomLvChildVo localRoomLvChildVo;
      int m;
      try
      {
        ArrayList localArrayList1 = new ArrayList();
        this.mVos = new ArrayList();
        ArrayList localArrayList2 = getCacheData().getRooms();
        i = 0;
        if (i >= localArrayList2.size())
          break label557;
        ArrayList localArrayList3 = ((Room)localArrayList2.get(i)).getExpandableLvInnerItemVos();
        j = 0;
        if (j >= localArrayList3.size())
          break label551;
        boolean bool1 = ((RoomLvChildVo)localArrayList3.get(j)).isSwich();
        if (((RoomLvChildVo)localArrayList3.get(j)).getInnerDeviceStatus() == null)
          break label608;
        bool2 = true;
        if (!(bool2 | bool1))
          continue;
        this.mVos.add(localArrayList3.get(j));
        localArrayList1.add(((Room)localArrayList2.get(i)).getName());
        if (((RoomLvChildVo)localArrayList3.get(j)).getYkVos() == null)
          break label545;
        int k = 0;
        if (k >= -1 + ((RoomLvChildVo)localArrayList3.get(j)).getYkVos().size())
          break label545;
        YkVo localYkVo = (YkVo)((RoomLvChildVo)localArrayList3.get(j)).getYkVos().get(k);
        localRoomLvChildVo = new RoomLvChildVo();
        localRoomLvChildVo.setYkType(localYkVo.getType());
        localRoomLvChildVo.setInnerDeviceStatus(localYkVo.getStatus());
        localRoomLvChildVo.setInnerDeviceName(localYkVo.getName());
        String str = localYkVo.getType();
        m = -1;
        switch (str.hashCode())
        {
        case 96586:
          this.mVos.add(localRoomLvChildVo);
          k++;
          continue;
          if (!str.equals("air"))
            break label562;
          m = 0;
          break;
        case 3714:
          if (!str.equals("tv"))
            break label562;
          m = 1;
          break;
        case 110741513:
          if (!str.equals("tvbox"))
            break label562;
          m = 2;
          break;
        case -894830916:
          if (!str.equals("projector"))
            break label562;
          m = 3;
          break;
        case 101139:
          if (!str.equals("fan"))
            break label562;
          m = 4;
          break;
        case 3239401:
          if (!str.equals("iptv"))
            break label562;
          m = 5;
          break;
        case 99858:
          if (!str.equals("dvd"))
            break label562;
          m = 6;
          break label562;
          localRoomLvChildVo.setInnerItemType(6);
          continue;
        }
      }
      catch (Exception localException)
      {
        return new ArrayList();
      }
      localRoomLvChildVo.setInnerItemType(0);
      continue;
      localRoomLvChildVo.setInnerItemType(2);
      continue;
      localRoomLvChildVo.setInnerItemType(5);
      continue;
      localRoomLvChildVo.setInnerItemType(4);
      continue;
      localRoomLvChildVo.setInnerItemType(1);
      continue;
      localRoomLvChildVo.setInnerItemType(3);
      continue;
      label545: j++;
      continue;
      label551: i++;
      continue;
      label557: return this.mVos;
      label562: switch (m)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
      label608: boolean bool2 = false;
    }
  }

  public RoomLvData getRoomExpandableLvInnerItem()
  {
    return this.roomLvData;
  }

  public Scene getScene(String paramString, int paramInt)
  {
    Scene localScene = new Scene();
    localScene.setmNum(paramInt);
    localScene.setName(paramString);
    localScene.setCondition(this.condition);
    localScene.setSwich(true);
    localScene.setSceneSteps(this.sceneSteps);
    return localScene;
  }

  public SceneSteps getSceneSteps()
  {
    SceneSteps localSceneSteps = (SceneSteps)getBean4ClassName(this.mac, SceneSteps.class);
    if (localSceneSteps == null)
      localSceneSteps = new SceneSteps();
    this.sceneSteps = localSceneSteps;
    return localSceneSteps;
  }

  public SceneSteps getSceneSteps(int paramInt)
  {
    if (paramInt == -1)
      return getSceneSteps();
    this.smartScenes = ((Scenes)getBean4ClassName(this.mac, Scenes.class));
    this.sceneSteps = ((Scene)this.smartScenes.smartScenes.get(paramInt)).getSceneSteps();
    return this.sceneSteps;
  }

  public ArrayList<RoomLvChildVo> getSendStepBlubs(Scene paramScene, int paramInt1, int paramInt2)
  {
    this.smartScenes = ((Scenes)getBean4ClassName(this.mac, Scenes.class));
    ArrayList localArrayList1 = ((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt2)).getSeletedDvcs();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < localArrayList1.size())
    {
      RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)localArrayList1.get(i);
      int j;
      label84: int k;
      label97: int n;
      label117: int i2;
      label137: int i4;
      label157: int i6;
      label177: int i7;
      if (localRoomLvChildVo.getInnerItemType() == 8)
      {
        j = 1;
        if (localRoomLvChildVo.getInnerItemType() != 9)
          break label225;
        k = 1;
        int m = k | j;
        if (localRoomLvChildVo.getInnerItemType() != 11)
          break label231;
        n = 1;
        int i1 = m | n;
        if (localRoomLvChildVo.getInnerItemType() != 15)
          break label237;
        i2 = 1;
        int i3 = i1 | i2;
        if (localRoomLvChildVo.getInnerItemType() != 16)
          break label243;
        i4 = 1;
        int i5 = i3 | i4;
        if (localRoomLvChildVo.getInnerItemType() != 17)
          break label249;
        i6 = 1;
        i7 = i5 | i6;
        if (localRoomLvChildVo.getInnerItemType() != 18)
          break label255;
      }
      label225: label231: label237: label243: label249: label255: for (int i8 = 1; ; i8 = 0)
      {
        if ((i8 | i7) != 0)
          localArrayList2.add(localRoomLvChildVo);
        i++;
        break;
        j = 0;
        break label84;
        k = 0;
        break label97;
        n = 0;
        break label117;
        i2 = 0;
        break label137;
        i4 = 0;
        break label157;
        i6 = 0;
        break label177;
      }
    }
    return localArrayList2;
  }

  public ArrayList<RoomLvChildVo> getSendStepRc(Scene paramScene, int paramInt)
  {
    this.smartScenes = ((Scenes)getBean4ClassName(this.mac, Scenes.class));
    ArrayList localArrayList1 = ((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt)).getSeletedDvcs();
    ArrayList localArrayList2 = new ArrayList();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)localArrayList1.get(i);
      if (localRoomLvChildVo.getInnerItemType() != 21)
        continue;
      localArrayList2.add(localRoomLvChildVo);
    }
    localArrayList1.clear();
    localArrayList1.addAll(localArrayList2);
    return localArrayList1;
  }

  public Scenes getSmartScenes()
  {
    if (this.smartScenes == null)
    {
      this.smartScenes = ((Scenes)getBean4ClassName(this.mac, Scenes.class));
      if (this.smartScenes == null)
        this.smartScenes = new Scenes();
    }
    this.smartScenesList.addAll(this.smartScenes.smartScenes);
    return this.smartScenes;
  }

  public ArrayList<Scene> getSmartScenesList()
  {
    if (this.smartScenes == null)
    {
      this.smartScenes = ((Scenes)getBean4ClassName(this.mac, Scenes.class));
      if (this.smartScenes == null)
        this.smartScenes = new Scenes();
    }
    return this.smartScenes.smartScenes;
  }

  public ArrayList<RoomLvChildVo> getStepInnerDvc()
  {
    ArrayList localArrayList1 = new ArrayList();
    this.seletedDvcNames = "";
    while (true)
    {
      ArrayList localArrayList2;
      ArrayList localArrayList3;
      int i;
      ArrayList localArrayList4;
      int j;
      RoomLvChildVo localRoomLvChildVo;
      int k;
      try
      {
        localArrayList2 = new ArrayList();
        localArrayList3 = this.roomLvData.getRooms();
        i = 0;
        if (i < localArrayList3.size())
        {
          localArrayList4 = ((Room)localArrayList3.get(i)).getExpandableLvInnerItemVos();
          j = 0;
          if (j >= localArrayList4.size())
            break label740;
          if (((RoomLvChildVo)localArrayList4.get(j)).getYkVo() == null)
            break label547;
          YkVo localYkVo = ((RoomLvChildVo)localArrayList4.get(j)).getYkVo();
          localRoomLvChildVo = new RoomLvChildVo();
          localRoomLvChildVo.setYkType(localYkVo.getType());
          localRoomLvChildVo.setInnerDeviceStatus(localYkVo.getStatus());
          localRoomLvChildVo.setInnerDeviceName(localYkVo.getName());
          localRoomLvChildVo.setDvcIndex(((RoomLvChildVo)localArrayList4.get(j)).getDvcIndex());
          localRoomLvChildVo.setYkVo(localYkVo);
          this.seletedDvcNames = (this.seletedDvcNames + "\t" + localYkVo.getName() + "\t" + localYkVo.getStatus() + "\t" + localYkVo.getStatus());
          String str = localYkVo.getType();
          k = -1;
          switch (str.hashCode())
          {
          case 96586:
            this.roomName = ((Room)localArrayList3.get(i)).getName();
            localRoomLvChildVo.setInnerItemType(21);
            localArrayList1.add(localRoomLvChildVo);
            break label740;
            if (!str.equals("air"))
              break;
            k = 0;
            break;
          case 3714:
            if (!str.equals("tv"))
              break;
            k = 1;
            break;
          case 110741513:
            if (!str.equals("tvbox"))
              break;
            k = 2;
            break;
          case -894830916:
            if (!str.equals("projector"))
              break;
            k = 3;
            break;
          case 101139:
            if (!str.equals("fan"))
              break;
            k = 4;
            break;
          case 3239401:
            if (!str.equals("iptv"))
              break;
            k = 5;
            break;
          case 99858:
            if (!str.equals("dvd"))
              break;
            k = 6;
            break;
            localRoomLvChildVo.setInnerItemType(6);
            continue;
          }
        }
      }
      catch (Exception localException)
      {
        localArrayList1 = new ArrayList();
      }
      return localArrayList1;
      localRoomLvChildVo.setInnerItemType(0);
      continue;
      localRoomLvChildVo.setInnerItemType(2);
      continue;
      localRoomLvChildVo.setInnerItemType(5);
      continue;
      localRoomLvChildVo.setInnerItemType(4);
      continue;
      localRoomLvChildVo.setInnerItemType(1);
      continue;
      localRoomLvChildVo.setInnerItemType(3);
      continue;
      label547: boolean bool1 = ((RoomLvChildVo)localArrayList4.get(j)).isSwich();
      if (((RoomLvChildVo)localArrayList4.get(j)).getInnerDeviceStatus() != null);
      for (boolean bool2 = true; ; bool2 = false)
      {
        if ((bool2 | bool1))
        {
          localArrayList1.add(localArrayList4.get(j));
          localArrayList2.add(((Room)localArrayList3.get(i)).getName());
          this.roomName = ((Room)localArrayList3.get(i)).getName();
          this.seletedDvcNames = (this.seletedDvcNames + ((RoomLvChildVo)localArrayList4.get(j)).getInnerDeviceName() + "\t");
        }
        j++;
        break;
      }
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
      }
      label740: i++;
    }
  }

  public List<YkVo> getYK4SceneInnerDvc(int paramInt1, int paramInt2)
  {
    return ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).getYkVos();
  }

  public void onDataResult(int paramInt1, int paramInt2, String paramString1, String paramString2, boolean paramBoolean)
  {
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setSwich(paramBoolean);
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setIsSeted(true);
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setInnerItemType(21);
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setParentRoomName(((Room)this.roomLvData.getRooms().get(paramInt1)).getName());
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setInnerDeviceStatus(paramString2);
  }

  public boolean onDeviceSwich(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    int i = 1;
    for (int j = 0; j < this.roomLvData.getRooms().size(); j++)
      for (int k = 0; k < ((Room)this.roomLvData.getRooms().get(j)).getExpandableLvInnerItemVos().size(); k++)
      {
        if (!((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).isSwich())
          continue;
        i = 0;
        this.seletedDvcType = ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).getInnerItemSeletedType();
      }
    if ((i == 0) && (((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).getInnerItemSeletedType() != this.seletedDvcType))
      return false;
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setSwich(paramBoolean);
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setParentRoomName(((Room)this.roomLvData.getRooms().get(paramInt1)).getName());
    this.seletedDvcType = ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).getInnerItemSeletedType();
    return true;
  }

  public boolean onDeviceSwich(int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2)
  {
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setSwich(paramBoolean1);
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setParentRoomName(((Room)this.roomLvData.getRooms().get(paramInt1)).getName());
    return true;
  }

  public void onSpaceTimeClick(int paramInt, String paramString)
  {
    ((SceneStep)this.sceneSteps.getSteps().get(paramInt)).setSpaceTime(Integer.parseInt(paramString));
  }

  public void pFragmentDestroy()
  {
    this.handler.removeCallbacks(this.loopRunnable);
    this.handler.removeCallbacks(this.resendRunnable);
    this.handler.removeCallbacks(this.loopDelRunnable);
    this.handler.removeCallbacks(this.exceptionRunnable);
  }

  public void querySceneInfo()
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void querySceneInfo(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void queryTimeInfo(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void reSortScenes()
  {
  }

  public void responseMessage(String paramString1, String paramString2)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void saveCacheData()
  {
    putCacheData(this.roomLvData);
  }

  public boolean saveData(String paramString, byte[] paramArrayOfByte)
  {
    if (!paramString.substring(18, 20).equalsIgnoreCase("a5"))
      return false;
    if (Integer.parseInt(paramString.substring(20, 22), 16) == 10);
    return true;
  }

  public void savePanelData(int paramInt1, int paramInt2, RoomLvChildVo paramRoomLvChildVo)
  {
    ((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().remove(paramInt2);
    ((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().add(paramInt2, paramRoomLvChildVo);
  }

  public SceneStep saveSceneData(String paramString, byte[] paramArrayOfByte)
  {
    int i = paramString.length();
    int j;
    if (paramString.substring(18, 20).equalsIgnoreCase("a5"))
    {
      paramString.substring(0, i - 6);
      boolean bool = addCheckSumData(paramString);
      j = Integer.parseInt(paramString.substring(20, 22), 16);
      if ((bool) && (j > 1) && (!this.lastReturnData.equalsIgnoreCase(paramString)));
    }
    else
    {
      return null;
    }
    this.lastReturnData = paramString;
    if (j == 10);
    for (SceneStep localSceneStep = syncDevice(paramString, j); ; localSceneStep = syncRemote(paramString, j, paramArrayOfByte))
      return localSceneStep;
  }

  public void saveSmartScene(String paramString, int paramInt1, int paramInt2)
  {
    Scenes localScenes = (Scenes)getBean4ClassName(this.mac, Scenes.class);
    if (localScenes == null)
      localScenes = new Scenes();
    Scene localScene = new Scene();
    localScene.setmNum(paramInt1);
    localScene.setName(paramString);
    localScene.setCondition(this.condition);
    localScene.setSwich(true);
    localScene.setSceneSteps(this.sceneSteps);
    if (this.isCreateNewScene)
      localScenes.smartScenes.add(localScene);
    while (true)
    {
      putData4ClassName(this.mac, localScenes);
      putCacheData(new RoomLvData());
      putCacheData(new SceneSteps());
      return;
      localScenes.smartScenes.remove(paramInt2);
      localScenes.smartScenes.add(paramInt2, localScene);
    }
  }

  public void saveStep()
  {
    putCacheData(this.roomLvData);
    SceneSteps localSceneSteps = (SceneSteps)getBean4ClassName(this.mac, SceneSteps.class);
    if (localSceneSteps == null)
      localSceneSteps = new SceneSteps();
    SceneStep localSceneStep = new SceneStep();
    localSceneStep.setSeletedDvcs(getStepInnerDvc());
    if (localSceneStep.getSeletedDvcs().size() == 0)
      return;
    localSceneStep.setRoomName(this.roomName);
    localSceneStep.setSeletedDvcNames(this.seletedDvcNames);
    localSceneSteps.getSteps().add(localSceneStep);
    putData4ClassName(this.mac, localSceneSteps);
  }

  public void saveStep(SceneSteps paramSceneSteps)
  {
    putData4ClassName(this.mac, paramSceneSteps);
  }

  public void saveYK2SceneInnerDvc(int paramInt1, int paramInt2, List<YkVo> paramList)
  {
    ((RoomLvChildVo)((Room)this.roomLvData.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setYkVos(paramList);
  }

  public void sendCreateBlubScene(Scene paramScene, int paramInt1, int paramInt2)
  {
    System.out.println("sendCreateBlubScene = " + paramInt2);
    byte[] arrayOfByte = new byte[24];
    for (int i = 0; i < 24; i++)
    {
      arrayOfByte[i] = 0;
      if (i >= this.sceneNameByte.length)
        continue;
      arrayOfByte[i] = this.sceneNameByte[i];
    }
    this.sensorSeleted2Hex.clear();
    for (int j = 0; j < 8; j++)
      this.sensorSeleted2Hex.add("0");
    this.blubSeleted2Hex.clear();
    for (int k = 0; k < 32; k++)
      this.blubSeleted2Hex.add("0");
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    String str6;
    if (this.isCreateNewScene)
      if (paramInt2 == 0)
      {
        this.cmd.add(Integer.valueOf(17));
        if (!this.isCreateNewScene)
          break label591;
        if (paramInt2 != 0)
          break label512;
        str6 = Integer.toBinaryString(paramInt2 + 1) + "1111";
        label225: this.cmd.add(Integer.valueOf(str6, 2));
        label239: int n = ((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt2)).getSpaceTime();
        System.out.println("getSpaceTime = " + n);
        this.cmd.add(Integer.valueOf(((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt2)).getSpaceTime()));
        if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131100083))))
          break label684;
        this.cmd.add(Integer.valueOf(0));
      }
    while (true)
    {
      if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131099885))))
        break label706;
      this.cmd.add(Integer.valueOf(81));
      for (int i21 = 0; i21 < paramScene.getTouchSensors().getSensors().size(); i21++)
      {
        this.sensorSeleted2Hex.remove(-1 + (-32 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i21)).getmIndex()));
        this.sensorSeleted2Hex.add(-1 + (-32 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i21)).getmIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(34));
      break;
      this.cmd.add(Integer.valueOf(34));
      break;
      label512: String str5 = Integer.toBinaryString(paramScene.getmNum());
      for (int i22 = str5.length(); i22 < 4; i22++)
        str5 = "0" + str5;
      str6 = Integer.toBinaryString(paramInt2 + 1) + str5;
      break label225;
      label591: String str1 = Integer.toBinaryString(paramScene.getmNum());
      for (int m = str1.length(); m < 4; m++)
        str1 = "0" + str1;
      String str2 = Integer.toBinaryString(paramInt2 + 1) + str1;
      this.cmd.add(Integer.valueOf(str2, 2));
      break label239;
      label684: if (paramScene.getCondition() != null)
        continue;
      this.cmd.add(Integer.valueOf(0));
    }
    label706: if ((paramScene.getCondition() != null) && (paramScene.getCondition().equals(this.ct.getString(2131100045))))
      this.cmd.add(Integer.valueOf(84));
    String str3 = "";
    for (int i1 = 7; i1 > 0; i1--)
      str3 = str3 + (String)this.sensorSeleted2Hex.get(i1);
    this.cmd.add(Integer.valueOf(str3, 2));
    int i2;
    ArrayList localArrayList1;
    int i3;
    label859: int i11;
    label894: int i12;
    label904: int i14;
    label921: int i15;
    label987: int i16;
    label997: int i18;
    label1014: int i19;
    if ((paramScene.getTouchSensors() != null) && (paramScene.getTouchSensors() != null))
    {
      this.cmd.add(Integer.valueOf(paramScene.getTouchSensors().getDelayTouch()));
      i2 = 8;
      localArrayList1 = getSendStepBlubs(paramScene, paramInt1, paramInt2);
      i3 = 0;
      if (i3 >= localArrayList1.size())
        break label1158;
      i2 = ((RoomLvChildVo)localArrayList1.get(i3)).getInnerItemType();
      if (i2 != 8)
        break label1116;
      i11 = 1;
      if (i2 != 11)
        break label1122;
      i12 = 1;
      int i13 = i11 | i12;
      if (i2 != 9)
        break label1128;
      i14 = 1;
      if ((i14 | i13) != 0)
      {
        this.blubSeleted2Hex.remove(-1 + ((RoomLvChildVo)localArrayList1.get(i3)).getDvcIndex());
        this.blubSeleted2Hex.add(-1 + ((RoomLvChildVo)localArrayList1.get(i3)).getDvcIndex(), "1");
      }
      if (i2 != 15)
        break label1134;
      i15 = 1;
      if (i2 != 16)
        break label1140;
      i16 = 1;
      int i17 = i15 | i16;
      if (i2 != 17)
        break label1146;
      i18 = 1;
      i19 = i17 | i18;
      if (i2 != 18)
        break label1152;
    }
    label1152: for (int i20 = 1; ; i20 = 0)
    {
      if ((i20 | i19) != 0)
      {
        this.blubSeleted2Hex.remove(-142 + (-1 + ((RoomLvChildVo)localArrayList1.get(i3)).getDvcIndex()));
        this.blubSeleted2Hex.add(-142 + (-1 + ((RoomLvChildVo)localArrayList1.get(i3)).getDvcIndex()), "1");
      }
      i3++;
      break label859;
      this.cmd.add(Integer.valueOf(0));
      break;
      label1116: i11 = 0;
      break label894;
      label1122: i12 = 0;
      break label904;
      label1128: i14 = 0;
      break label921;
      label1134: i15 = 0;
      break label987;
      label1140: i16 = 0;
      break label997;
      label1146: i18 = 0;
      break label1014;
    }
    label1158: String str4 = "";
    for (int i4 = -1 + this.blubSeleted2Hex.size(); i4 > -1; i4--)
      str4 = str4 + (String)this.blubSeleted2Hex.get(i4);
    ArrayList localArrayList2 = this.cmd;
    int i5;
    int i6;
    label1243: int i8;
    if (i2 == 8)
    {
      i5 = 1;
      if (i2 != 11)
        break label1473;
      i6 = 1;
      int i7 = i5 | i6;
      if (i2 != 9)
        break label1479;
      i8 = 1;
      label1260: if ((i8 | i7) == 0)
        break label1485;
    }
    label1473: label1479: label1485: for (int i9 = 79; ; i9 = 47)
    {
      localArrayList2.add(Integer.valueOf(i9));
      this.cmd.add(Integer.valueOf(str4.substring(24, 32), 2));
      this.cmd.add(Integer.valueOf(str4.substring(16, 24), 2));
      this.cmd.add(Integer.valueOf(str4.substring(8, 16), 2));
      this.cmd.add(Integer.valueOf(str4.substring(0, 8), 2));
      this.cmd.add(Integer.valueOf(this.uId[0]));
      this.cmd.add(Integer.valueOf(this.uId[1]));
      this.cmd.add(Integer.valueOf(this.uId[2]));
      this.cmd.add(Integer.valueOf(this.uId[3]));
      for (int i10 = 0; i10 < arrayOfByte.length; i10++)
        this.cmd.add(Integer.valueOf(arrayOfByte[i10]));
      i5 = 0;
      break;
      i6 = 0;
      break label1243;
      i8 = 0;
      break label1260;
    }
    this.cmd.add(this.dataLenthPosi, Integer.valueOf(this.cmd.size() - this.dataLenthPosi));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
    this.loopTime = (1 + this.loopTime);
  }

  public void sendCreateRcScene(Scene paramScene, int paramInt, byte[] paramArrayOfByte)
    throws Exception
  {
    this.sensorSeleted2Hex.clear();
    for (int i = 0; i < 8; i++)
      this.sensorSeleted2Hex.add("0");
    this.blubSeleted2Hex.clear();
    for (int j = 0; j < 32; j++)
      this.blubSeleted2Hex.add("0");
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    String str6;
    if (this.isCreateNewScene)
      if (paramInt == 0)
      {
        this.cmd.add(Integer.valueOf(17));
        if (!this.isCreateNewScene)
          break label461;
        if (paramInt != 0)
          break label382;
        str6 = Integer.toBinaryString(paramInt + 1) + "1111";
        label149: this.cmd.add(Integer.valueOf(str6, 2));
      }
    while (true)
    {
      this.cmd.add(Integer.valueOf(((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt)).getSpaceTime()));
      if ((paramScene.getCondition() == null) || (paramScene.getCondition().equals(this.ct.getString(2131100083))))
        this.cmd.add(Integer.valueOf(0));
      if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131099885))))
        break label563;
      this.cmd.add(Integer.valueOf(81));
      for (int i2 = 0; i2 < 8; i2++)
      {
        this.sensorSeleted2Hex.remove(-1 + (-33 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i2)).getmIndex()));
        this.sensorSeleted2Hex.add(-1 + (-33 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i2)).getmIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(34));
      break;
      this.cmd.add(Integer.valueOf(85));
      break;
      label382: String str5 = Integer.toBinaryString(paramScene.getmNum());
      for (int i3 = str5.length(); i3 < 4; i3++)
        str5 = "0" + str5;
      str6 = Integer.toBinaryString(paramInt + 1) + str5;
      break label149;
      label461: String str1 = Integer.toBinaryString(paramScene.getmNum());
      for (int k = str1.length(); k < 4; k++)
        str1 = "0" + str1;
      String str2 = Integer.toBinaryString(paramInt + 1) + str1;
      this.cmd.add(Integer.valueOf(str2, 2));
    }
    this.sensorSeleted2Hex.remove(1);
    label563: if ((paramScene.getCondition() != null) && (paramScene.getCondition().equals(this.ct.getString(2131100045))))
      this.cmd.add(Integer.valueOf(84));
    String str3 = "";
    for (int m = 7; m > 0; m--)
      str3 = str3 + (String)this.sensorSeleted2Hex.get(m);
    this.cmd.add(Integer.valueOf(str3, 2));
    if (paramScene.getTouchSensors() != null)
      this.cmd.add(Integer.valueOf(paramScene.getTouchSensors().getDelayTouch()));
    while (true)
    {
      this.cmd.add(Integer.valueOf(97));
      ArrayList localArrayList = getSendStepRc(paramScene, paramInt);
      for (int n = 0; n < localArrayList.size(); n++)
      {
        this.blubSeleted2Hex.remove(-1 + (-79 + ((RoomLvChildVo)localArrayList.get(n)).getDvcIndex()));
        this.blubSeleted2Hex.add(-1 + (-79 + ((RoomLvChildVo)localArrayList.get(n)).getDvcIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(0));
    }
    String str4 = "";
    for (int i1 = -1 + this.blubSeleted2Hex.size(); i1 > -1; i1--)
      str4 = str4 + (String)this.blubSeleted2Hex.get(i1);
    this.cmd.add(Integer.valueOf(str4.substring(24, 32), 2));
    this.cmd.add(Integer.valueOf(str4.substring(16, 24), 2));
    this.cmd.add(Integer.valueOf(str4.substring(8, 16), 2));
    this.cmd.add(Integer.valueOf(str4.substring(0, 8), 2));
    sendRcCode(this.cmd, paramArrayOfByte);
    this.loopTime = (1 + this.loopTime);
  }

  public void sendDelBlubScene(Scene paramScene, int paramInt1, int paramInt2)
  {
    System.out.println("sendDelBlubScene = " + paramInt2);
    this.sensorSeleted2Hex.clear();
    for (int i = 0; i < 8; i++)
      this.sensorSeleted2Hex.add("0");
    this.blubSeleted2Hex.clear();
    for (int j = 0; j < 32; j++)
      this.blubSeleted2Hex.add("0");
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    String str6;
    if (this.isCreateNewScene)
      if (paramInt2 == 0)
      {
        this.cmd.add(Integer.valueOf(17));
        if (!this.isCreateNewScene)
          break label496;
        if (paramInt2 != 0)
          break label417;
        str6 = Integer.toBinaryString(paramInt2 + 1) + "1111";
        label175: this.cmd.add(Integer.valueOf(str6, 2));
        label189: this.cmd.add(Integer.valueOf(((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt2)).getSpaceTime()));
        if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131100083))))
          break label589;
        this.cmd.add(Integer.valueOf(0));
      }
    while (true)
    {
      if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131099885))))
        break label611;
      this.cmd.add(Integer.valueOf(81));
      for (int i18 = 0; i18 < paramScene.getTouchSensors().getSensors().size(); i18++)
      {
        this.sensorSeleted2Hex.remove(-1 + (-32 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i18)).getmIndex()));
        this.sensorSeleted2Hex.add(-1 + (-32 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i18)).getmIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(34));
      break;
      this.cmd.add(Integer.valueOf(255));
      break;
      label417: String str5 = Integer.toBinaryString(paramScene.getmNum());
      for (int i19 = str5.length(); i19 < 4; i19++)
        str5 = "0" + str5;
      str6 = Integer.toBinaryString(paramInt2 + 1) + str5;
      break label175;
      label496: String str1 = Integer.toBinaryString(paramScene.getmNum());
      for (int k = str1.length(); k < 4; k++)
        str1 = "0" + str1;
      String str2 = Integer.toBinaryString(paramInt2 + 1) + str1;
      this.cmd.add(Integer.valueOf(str2, 2));
      break label189;
      label589: if (paramScene.getCondition() != null)
        continue;
      this.cmd.add(Integer.valueOf(0));
    }
    label611: if ((paramScene.getCondition() != null) && (paramScene.getCondition().equals(this.ct.getString(2131100045))))
      this.cmd.add(Integer.valueOf(84));
    String str3 = "";
    for (int m = 7; m > 0; m--)
      str3 = str3 + (String)this.sensorSeleted2Hex.get(m);
    this.cmd.add(Integer.valueOf(str3, 2));
    int n;
    ArrayList localArrayList1;
    int i1;
    label764: int i8;
    label799: int i9;
    label809: int i11;
    label826: int i12;
    label892: int i13;
    label902: int i15;
    label919: int i16;
    if ((paramScene.getTouchSensors() != null) && (paramScene.getTouchSensors() != null))
    {
      this.cmd.add(Integer.valueOf(paramScene.getTouchSensors().getDelayTouch()));
      n = 8;
      localArrayList1 = getSendStepBlubs(paramScene, paramInt1, paramInt2);
      i1 = 0;
      if (i1 >= localArrayList1.size())
        break label1063;
      n = ((RoomLvChildVo)localArrayList1.get(i1)).getInnerItemType();
      if (n != 8)
        break label1021;
      i8 = 1;
      if (n != 11)
        break label1027;
      i9 = 1;
      int i10 = i8 | i9;
      if (n != 9)
        break label1033;
      i11 = 1;
      if ((i11 | i10) != 0)
      {
        this.blubSeleted2Hex.remove(-1 + ((RoomLvChildVo)localArrayList1.get(i1)).getDvcIndex());
        this.blubSeleted2Hex.add(-1 + ((RoomLvChildVo)localArrayList1.get(i1)).getDvcIndex(), "1");
      }
      if (n != 15)
        break label1039;
      i12 = 1;
      if (n != 16)
        break label1045;
      i13 = 1;
      int i14 = i12 | i13;
      if (n != 17)
        break label1051;
      i15 = 1;
      i16 = i14 | i15;
      if (n != 18)
        break label1057;
    }
    label1027: label1033: label1039: label1045: label1051: label1057: for (int i17 = 1; ; i17 = 0)
    {
      if ((i17 | i16) != 0)
      {
        this.blubSeleted2Hex.remove(-142 + (-1 + ((RoomLvChildVo)localArrayList1.get(i1)).getDvcIndex()));
        this.blubSeleted2Hex.add(-142 + (-1 + ((RoomLvChildVo)localArrayList1.get(i1)).getDvcIndex()), "1");
      }
      i1++;
      break label764;
      this.cmd.add(Integer.valueOf(0));
      break;
      label1021: i8 = 0;
      break label799;
      i9 = 0;
      break label809;
      i11 = 0;
      break label826;
      i12 = 0;
      break label892;
      i13 = 0;
      break label902;
      i15 = 0;
      break label919;
    }
    label1063: String str4 = "";
    for (int i2 = -1 + this.blubSeleted2Hex.size(); i2 > -1; i2--)
      str4 = str4 + (String)this.blubSeleted2Hex.get(i2);
    ArrayList localArrayList2 = this.cmd;
    int i3;
    int i4;
    label1148: int i6;
    if (n == 8)
    {
      i3 = 1;
      if (n != 11)
        break label1355;
      i4 = 1;
      int i5 = i3 | i4;
      if (n != 9)
        break label1361;
      i6 = 1;
      label1165: if ((i6 | i5) == 0)
        break label1367;
    }
    label1355: label1361: label1367: for (int i7 = 79; ; i7 = 47)
    {
      localArrayList2.add(Integer.valueOf(i7));
      this.cmd.add(Integer.valueOf(str4.substring(24, 32), 2));
      this.cmd.add(Integer.valueOf(str4.substring(16, 24), 2));
      this.cmd.add(Integer.valueOf(str4.substring(8, 16), 2));
      this.cmd.add(Integer.valueOf(str4.substring(0, 8), 2));
      this.cmd.add(this.dataLenthPosi, Integer.valueOf(this.cmd.size() - this.dataLenthPosi));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      this.loopTime = (1 + this.loopTime);
      return;
      i3 = 0;
      break;
      i4 = 0;
      break label1148;
      i6 = 0;
      break label1165;
    }
  }

  public void sendDelRcScene(Scene paramScene, int paramInt, byte[] paramArrayOfByte)
    throws Exception
  {
    this.sensorSeleted2Hex.clear();
    for (int i = 0; i < 8; i++)
      this.sensorSeleted2Hex.add("0");
    this.blubSeleted2Hex.clear();
    for (int j = 0; j < 32; j++)
      this.blubSeleted2Hex.add("0");
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    String str6;
    if (this.isCreateNewScene)
      if (paramInt == 0)
      {
        this.cmd.add(Integer.valueOf(17));
        if (!this.isCreateNewScene)
          break label462;
        if (paramInt != 0)
          break label383;
        str6 = Integer.toBinaryString(paramInt + 1) + "1111";
        label149: this.cmd.add(Integer.valueOf(str6, 2));
      }
    while (true)
    {
      this.cmd.add(Integer.valueOf(((SceneStep)paramScene.getSceneSteps().getSteps().get(paramInt)).getSpaceTime()));
      if ((paramScene.getCondition() == null) || (paramScene.getCondition().equals(this.ct.getString(2131100083))))
        this.cmd.add(Integer.valueOf(0));
      if ((paramScene.getCondition() == null) || (!paramScene.getCondition().equals(this.ct.getString(2131099885))))
        break label564;
      this.cmd.add(Integer.valueOf(81));
      for (int i2 = 0; i2 < 8; i2++)
      {
        this.sensorSeleted2Hex.remove(-1 + (-33 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i2)).getmIndex()));
        this.sensorSeleted2Hex.add(-1 + (-33 + ((Dvc)paramScene.getTouchSensors().getSensors().get(i2)).getmIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(34));
      break;
      this.cmd.add(Integer.valueOf(255));
      break;
      label383: String str5 = Integer.toBinaryString(paramScene.getmNum());
      for (int i3 = str5.length(); i3 < 4; i3++)
        str5 = "0" + str5;
      str6 = Integer.toBinaryString(paramInt + 1) + str5;
      break label149;
      label462: String str1 = Integer.toBinaryString(paramScene.getmNum());
      for (int k = str1.length(); k < 4; k++)
        str1 = "0" + str1;
      String str2 = Integer.toBinaryString(paramInt + 1) + str1;
      this.cmd.add(Integer.valueOf(str2, 2));
    }
    this.sensorSeleted2Hex.remove(1);
    label564: if ((paramScene.getCondition() != null) && (paramScene.getCondition().equals(this.ct.getString(2131100045))))
      this.cmd.add(Integer.valueOf(84));
    String str3 = "";
    for (int m = 7; m > 0; m--)
      str3 = str3 + (String)this.sensorSeleted2Hex.get(m);
    this.cmd.add(Integer.valueOf(str3, 2));
    if (paramScene.getTouchSensors() != null)
      this.cmd.add(Integer.valueOf(paramScene.getTouchSensors().getDelayTouch()));
    while (true)
    {
      this.cmd.add(Integer.valueOf(97));
      ArrayList localArrayList = getSendStepRc(paramScene, paramInt);
      for (int n = 0; n < localArrayList.size(); n++)
      {
        this.blubSeleted2Hex.remove(-1 + (-79 + ((RoomLvChildVo)localArrayList.get(n)).getDvcIndex()));
        this.blubSeleted2Hex.add(-1 + (-79 + ((RoomLvChildVo)localArrayList.get(n)).getDvcIndex()), "1");
      }
      this.cmd.add(Integer.valueOf(0));
    }
    String str4 = "";
    for (int i1 = -1 + this.blubSeleted2Hex.size(); i1 > -1; i1--)
      str4 = str4 + (String)this.blubSeleted2Hex.get(i1);
    this.cmd.add(Integer.valueOf(str4.substring(24, 32), 2));
    this.cmd.add(Integer.valueOf(str4.substring(16, 24), 2));
    this.cmd.add(Integer.valueOf(str4.substring(8, 16), 2));
    this.cmd.add(Integer.valueOf(str4.substring(0, 8), 2));
    sendRcCode(this.cmd, paramArrayOfByte);
    this.loopTime = (1 + this.loopTime);
  }

  public void sendDelScene(int paramInt)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(48));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(255));
    String str1 = Integer.toBinaryString(paramInt);
    for (int i = str1.length(); i < 4; i++)
      str1 = "0" + str1;
    System.out.println("");
    String str2 = "0001" + str1;
    System.out.println("sendDelScene = " + str2);
    this.cmd.add(Integer.valueOf(str2, 2));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendExitsScene(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(49));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendPreprea(int paramInt, Scene paramScene)
  {
    this.handler.removeCallbacks(this.exceptionRunnable);
    this.handler.postDelayed(this.exceptionRunnable, 80000L);
    this.seletedSceneNum = paramInt;
    this.sendSteps.clear();
    int i = 0;
    ArrayList localArrayList;
    if (i < paramScene.getSceneSteps().getSteps().size())
    {
      localArrayList = ((SceneStep)paramScene.getSceneSteps().getSteps().get(i)).getSeletedDvcs();
      if (localArrayList == null);
    }
    for (int j = 0; ; j++)
      if (j < localArrayList.size())
      {
        RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)localArrayList.get(j);
        if (localRoomLvChildVo.getInnerItemType() == 21)
        {
          try
          {
            if (localRoomLvChildVo.getYkVo() != null)
              handleRcCode(localRoomLvChildVo.getYkVo());
            while (true)
            {
              j = localArrayList.size();
              break;
              handleOtherRcCode(localRoomLvChildVo);
            }
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
        else
        {
          this.sendSteps.add(new SendStep(null));
          j = localArrayList.size();
        }
      }
      else
      {
        i++;
        break;
        this.stepTime = this.sendSteps.size();
        setMySendListener(new MyBusiness.MySendListener(paramScene)
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            try
            {
              if (paramArrayOfByte.length < 15)
                return;
              String str1 = StringUtils.btye2Str(paramArrayOfByte);
              if (!ExpandableLvSceneBusiness.this.lastReturnData.equalsIgnoreCase(str1))
              {
                ExpandableLvSceneBusiness.access$002(ExpandableLvSceneBusiness.this, str1);
                String str2 = Integer.toBinaryString(Integer.valueOf(str1.substring(24, 26), 16).intValue());
                int i = Integer.valueOf(str2.substring(-4 + str2.length(), str2.length()), 2).intValue();
                if (str1.substring(18, 20).equalsIgnoreCase("B0"))
                {
                  System.out.println("onCreateSceneOkdata= " + StringUtils.btye2Str3(paramArrayOfByte));
                  this.val$scene.setmNum(i);
                  ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.resendRunnable);
                  if (ExpandableLvSceneBusiness.this.loopTime < ExpandableLvSceneBusiness.this.stepTime)
                  {
                    ExpandableLvSceneBusiness.this.loopRunnable.scene = this.val$scene;
                    ExpandableLvSceneBusiness.this.handler.postDelayed(ExpandableLvSceneBusiness.this.loopRunnable, 500L);
                  }
                  System.out.println("onCreateSceneOk     loopTime = " + ExpandableLvSceneBusiness.this.loopTime + "     stepTime = " + ExpandableLvSceneBusiness.this.stepTime);
                  if (ExpandableLvSceneBusiness.this.loopTime == ExpandableLvSceneBusiness.this.stepTime)
                  {
                    ExpandableLvSceneBusiness.this.loopTime = 0;
                    if (ExpandableLvSceneBusiness.this.ftFinishAddScene != null)
                      ExpandableLvSceneBusiness.this.ftFinishAddScene.onCreateSceneOk(i);
                    if (ExpandableLvSceneBusiness.this.ftScene != null)
                      ExpandableLvSceneBusiness.this.ftScene.onCreateSceneOk(i);
                    ExpandableLvSceneBusiness.this.handler.removeCallbacks(ExpandableLvSceneBusiness.this.loopRunnable);
                    return;
                  }
                }
              }
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }

          public void onTimeOut()
          {
          }
        });
        return;
      }
  }

  public void setFtFinishAddScene(FtFinishAddScene paramFtFinishAddScene)
  {
    this.ftFinishAddScene = paramFtFinishAddScene;
  }

  public void setFtScene(com.ex.ltech.onepiontfive.main.backupScene.FtScene paramFtScene)
  {
  }

  public void setFtScene(FtScene paramFtScene)
  {
    this.ftScene = paramFtScene;
  }

  public void setSceneCondition(String paramString)
  {
    this.condition = paramString;
  }

  public void setSceneNameByte(byte[] paramArrayOfByte)
  {
    StringUtils.btye2Str3(paramArrayOfByte);
    this.sceneNameByte = paramArrayOfByte;
  }

  public void startLoopCreateScene(Scene paramScene)
  {
    try
    {
      if ((this.sendSteps.size() > 0) && (((SendStep)this.sendSteps.get(this.loopTime)).getRcCode() == null))
        sendCreateBlubScene(paramScene, this.seletedSceneNum, this.loopTime);
      while (true)
      {
        this.resendRunnable.scene = paramScene;
        this.handler.removeCallbacks(this.resendRunnable);
        this.handler.postDelayed(this.resendRunnable, 2000L);
        return;
        sendCreateRcScene(paramScene, this.loopTime, ((SendStep)this.sendSteps.get(this.loopTime)).getRcCode());
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void startLoopDelScene(Scene paramScene)
  {
    try
    {
      if ((this.sendSteps.size() > 0) && (((SendStep)this.sendSteps.get(this.loopTime)).getRcCode() == null))
      {
        sendDelBlubScene(paramScene, this.seletedSceneNum, this.loopTime);
        return;
      }
      sendDelRcScene(paramScene, this.loopTime, ((SendStep)this.sendSteps.get(this.loopTime)).getRcCode());
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public SceneStep syncDevice(String paramString, int paramInt)
  {
    new SceneSteps();
    this.blubSeleted2Hex.clear();
    for (int i = 0; i < 32; i++)
      this.blubSeleted2Hex.add("0");
    paramString.length();
    String str1 = paramString.substring(22, -8 + paramString.length());
    new ArrayList((paramInt - 1) / 9);
    SceneStep localSceneStep = null;
    for (int j = 0; j < (paramInt - 1) / 9; j++)
    {
      ArrayList localArrayList = new ArrayList();
      localSceneStep = new SceneStep();
      Integer.parseInt(str1.substring(6 + j * 18, 8 + j * 18), 16);
      int k = Integer.parseInt(str1.substring(8 + j * 18, 10 + j * 18), 16);
      Integer.parseInt(str1.substring(10 + j * 18, 12 + j * 18), 16);
      localSceneStep.setSpaceTime(k);
      str1.substring(12 + j * 18, 14 + j * 18);
      copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(12 + j * 18, 14 + j * 18), 16).intValue()));
      String str2 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(12 + j * 18, 14 + j * 18), 16).intValue()));
      String str3 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(14 + j * 18, 16 + j * 18), 16).intValue())) + str2;
      String str4 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(16 + j * 18, 18 + j * 18), 16).intValue())) + str3;
      String str5 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(18 + j * 18, 20 + j * 18), 16).intValue())) + str4;
      if (str5.contains("1"))
        for (int m = -1 + str5.length(); m >= 0; m--)
        {
          if (str5.charAt(m) != '1')
            continue;
          RoomLvChildVo localRoomLvChildVo = new RoomLvChildVo();
          localRoomLvChildVo.setInnerItemType(8);
          localRoomLvChildVo.setDvcIndex(m + 1);
          localArrayList.add(localRoomLvChildVo);
        }
      localSceneStep.setSeletedDvcs(localArrayList);
    }
    return localSceneStep;
  }

  public SceneStep syncRemote(String paramString, int paramInt, byte[] paramArrayOfByte)
  {
    new SceneSteps();
    this.blubSeleted2Hex.clear();
    for (int i = 0; i < 32; i++)
      this.blubSeleted2Hex.add("0");
    paramString.length();
    String str1 = paramString.substring(22, -8 + paramString.length());
    new ArrayList((paramInt - 1) / 9);
    ArrayList localArrayList = new ArrayList();
    SceneStep localSceneStep = new SceneStep();
    localSceneStep.setSpaceTime(Integer.parseInt(str1.substring(8, 10), 16));
    str1.substring(12, 14);
    copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(12, 14), 16).intValue()));
    String str2 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(12, 14), 16).intValue()));
    String str3 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(14, 16), 16).intValue())) + str2;
    String str4 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(16, 18), 16).intValue())) + str3;
    String str5 = copyZero(Integer.toBinaryString(Integer.valueOf(str1.substring(18, 20), 16).intValue())) + str4;
    int j = Integer.valueOf(str1.substring(20, 22), 16).intValue();
    int k = Integer.valueOf(str1.substring(22, 24), 16).intValue();
    String str6 = str1.substring(24);
    if (str5.contains("1"))
      for (int m = -1 + str5.length(); m >= 0; m--)
      {
        if (str5.charAt(m) != '1')
          continue;
        RoomLvChildVo localRoomLvChildVo = new RoomLvChildVo();
        localRoomLvChildVo.setInnerItemType(21);
        localRoomLvChildVo.setDvcIndex(80);
        localRoomLvChildVo.setOthersRC(str6);
        localRoomLvChildVo.setOthersRCLen(k);
        localRoomLvChildVo.setOthersRCNum(j);
        localArrayList.add(localRoomLvChildVo);
      }
    localSceneStep.setSeletedDvcs(localArrayList);
    return localSceneStep;
  }

  public void syncSceneStep(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(37));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void updataScenes(Scenes paramScenes)
  {
    this.smartScenes = paramScenes;
  }

  class LoopDelRunnable
    implements Runnable
  {
    public Scene scene;

    LoopDelRunnable()
    {
    }

    public void run()
    {
      ExpandableLvSceneBusiness.this.startLoopDelScene(this.scene);
    }
  }

  class LoopRunnable
    implements Runnable
  {
    public Scene scene;

    LoopRunnable()
    {
    }

    public void run()
    {
      ExpandableLvSceneBusiness.this.startLoopCreateScene(this.scene);
    }
  }

  class ResendRunnable
    implements Runnable
  {
    public Scene scene;

    ResendRunnable()
    {
    }

    public void run()
    {
      if (ExpandableLvSceneBusiness.this.resendTime < 4)
      {
        ExpandableLvSceneBusiness localExpandableLvSceneBusiness = ExpandableLvSceneBusiness.this;
        localExpandableLvSceneBusiness.loopTime = (-1 + localExpandableLvSceneBusiness.loopTime);
      }
      while (true)
      {
        ExpandableLvSceneBusiness.this.sendCmdWithoutCrasySend(ExpandableLvSceneBusiness.this.cmd);
        return;
        ExpandableLvSceneBusiness.this.resendTime = 0;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness
 * JD-Core Version:    0.6.0
 */