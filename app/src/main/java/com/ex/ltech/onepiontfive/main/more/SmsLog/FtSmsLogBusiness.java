package com.ex.ltech.onepiontfive.main.more.SmsLog;

import android.content.Context;
import android.os.SystemClock;
import android.util.Log;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.ex.ltech.onepiontfive.main.vo.SmsLogVo;
import com.ex.ltech.onepiontfive.main.vo.SmsLogsVo;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.util.ArrayList;
import java.util.List;

public class FtSmsLogBusiness extends MyBusiness
{
  ArrayList<Integer> cmd = new ArrayList();
  public Home home = null;
  private String lastResturnData = "";
  private String lastReturnData = "";
  private String mac;
  ArrayList<Room> roomList;
  private List<Dvc> sensorDvcList = new ArrayList();
  private List<SmsLogsVo> smsLogs;
  private List<Integer> useToQuerySmsLogs = new ArrayList();

  public FtSmsLogBusiness(Context paramContext, ArrayList<Room> paramArrayList)
  {
    super(paramContext);
    this.roomList = paramArrayList;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
  }

  private void operatingData(String paramString)
  {
    boolean bool = addCheckSumData(paramString);
    String str = paramString.substring(18, 20);
    int i = Integer.parseInt(paramString.substring(20, 22), 16);
    int m;
    int n;
    int i1;
    int i2;
    Object localObject;
    int i4;
    if ((str.equalsIgnoreCase("A0")) && (bool) && (i == 5))
    {
      responseMessage(null, paramString.substring(4, 6), "22");
      if (!this.lastReturnData.equalsIgnoreCase(paramString))
      {
        this.lastReturnData = paramString;
        Integer.parseInt(paramString.substring(18, 20), 16);
        int j = Integer.parseInt(paramString.substring(22, 24), 16);
        int k = Integer.parseInt(paramString.substring(24, 26), 16);
        m = Integer.parseInt(paramString.substring(26, 28), 16);
        n = Integer.parseInt(paramString.substring(28, 30), 16);
        i1 = Integer.parseInt(paramString.substring(30, 32), 16);
        ArrayList localArrayList = ((Room)this.roomList.get(j - 1)).getDvcVos();
        i2 = 0;
        int i3 = localArrayList.size();
        Dvc localDvc = null;
        if (i2 < i3)
        {
          if (((Dvc)localArrayList.get(i2)).getmIndex() != k)
            break label421;
          localDvc = (Dvc)localArrayList.get(i2);
        }
        if (localDvc.getSensorVo() == null)
          localDvc.setSensorVo(new SensorVo());
        localObject = localDvc.getSensorVo().getSmsLogs();
        if (localObject == null)
          localObject = new ArrayList();
        if (((List)localObject).size() <= 0)
          break label427;
        i4 = -1 + ((List)localObject).size();
        label302: if (((List)localObject).size() != 0)
          break label433;
      }
    }
    label421: label427: label433: for (SmsLogVo localSmsLogVo1 = new SmsLogVo(); ; localSmsLogVo1 = (SmsLogVo)((List)localObject).get(i4))
    {
      if ((((List)localObject).size() == 0) || ((((List)localObject).size() > 0) && (localSmsLogVo1.getTriggerOrder() != m)))
      {
        SmsLogVo localSmsLogVo2 = new SmsLogVo();
        localSmsLogVo2.setTriggerOrder(m);
        localSmsLogVo2.setTriggerHour(String.valueOf(i1));
        localSmsLogVo2.setTriggerMin(String.valueOf(n));
        ((List)localObject).add(localSmsLogVo2);
      }
      this.home.setRooms(this.roomList);
      putData4ClassName(this.mac, this.home);
      return;
      i2++;
      break;
      i4 = 0;
      break label302;
    }
  }

  private void refreshHome()
  {
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    responseMessage(null, paramString1, paramString2);
  }

  public boolean addCheckSumData(String paramString)
  {
    String str = paramString.substring(0, -6 + paramString.length());
    int i = str.length();
    int j = 0;
    for (int k = 0; k < i / 2; k++)
      j += Integer.parseInt(str.substring(k * 2, 2 + k * 2), 16);
    int m = Integer.parseInt(paramString.substring(-4 + paramString.length(), -2 + paramString.length()) + paramString.substring(-6 + paramString.length(), -4 + paramString.length()), 16);
    int n = 0;
    if (m == j)
      n = 1;
    return n;
  }

  public int compareWithReturnInfo(String paramString, Integer paramInteger)
  {
    String str1 = paramString.substring(18, 20);
    boolean bool = addCheckSumData(paramString);
    int i = -1;
    if ((bool) && (str1.equalsIgnoreCase("A2")))
    {
      String str2 = paramString.substring(22, -8 + paramString.length());
      int j = Integer.parseInt(paramString.substring(20, 22), 16);
      int k = str2.length() / 2;
      new ArrayList();
      Integer.parseInt(paramString.substring(24, 26), 16);
      if ((k == j) && (Integer.parseInt(paramString.substring(24, 26), 16) == paramInteger.intValue()))
      {
        if (this.lastResturnData.equalsIgnoreCase(paramString))
          break label180;
        this.lastResturnData = paramString;
        i = Integer.parseInt(str2.substring(6, 8) + str2.substring(4, 6), 16);
      }
    }
    return i;
    label180: return -2;
  }

  public SmsLogsVo getSmsLogsVo(int paramInt)
  {
    return null;
  }

  public void loadImagesByThread(int paramInt, String[] paramArrayOfString)
  {
    Log.e("当前线程：", "" + Thread.currentThread().getName());
    querySmsLogInfo(new MyBusiness.MySendListener(paramInt, paramArrayOfString)
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        int i = FtSmsLogBusiness.this.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte), Integer.valueOf(this.val$useToQuerySmsLogsMIndex));
        if (i == -1)
          return;
        if (i == -2)
        {
          FtSmsLogBusiness.this.responseMessage(str.substring(4, 6), "25");
          return;
        }
        FtSmsLogBusiness.this.responseMessage(str.substring(4, 6), "25");
        FtSmsLogBusiness.this.syncSceneInfo(this.val$useToQuerySmsLogsMIndex, 50, this.val$sensorIndex);
      }

      public void onTimeOut()
      {
      }
    }
    , paramInt);
  }

  public void querySmsLogInfo(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(6));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void responseMessage(MyBusiness.MySendListener paramMySendListener, String paramString1, String paramString2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public int saveSmsLogData(String paramString, byte[] paramArrayOfByte, int paramInt, String[] paramArrayOfString)
  {
    paramString.length();
    String str1 = paramString.substring(18, 20);
    boolean bool = addCheckSumData(paramString);
    int i = Integer.parseInt(paramString.substring(20, 22), 16);
    String str2 = paramString.substring(22, -8 + paramString.length());
    int j = str2.length() / 2;
    if (str1.equalsIgnoreCase("A8"))
    {
      if ((bool) && (j == i))
      {
        if (Integer.parseInt(paramString.substring(24, 26), 16) == paramInt)
        {
          if (!this.lastReturnData.equalsIgnoreCase(paramString))
          {
            this.lastReturnData = paramString;
            int k = Integer.parseInt(str2.substring(8, 12) + str2.substring(6, 8), 16);
            for (int m = 0; m < (i - 5) / 2; m++)
            {
              SmsLogVo localSmsLogVo = new SmsLogVo();
              localSmsLogVo.setTriggerOrder(k);
              localSmsLogVo.setTriggerMin(String.valueOf(Integer.parseInt(str2.substring(10 + m * 4, 12 + m * 4), 16)));
              localSmsLogVo.setTriggerHour(String.valueOf(Integer.parseInt(str2.substring(12 + m * 4, 14 + m * 4), 16)));
              k++;
              if (((Dvc)((Room)this.home.getRooms().get(Integer.parseInt(paramArrayOfString[0]))).getDvcVos().get(Integer.parseInt(paramArrayOfString[1]))).getSensorVo() == null)
                ((Dvc)((Room)this.home.getRooms().get(Integer.parseInt(paramArrayOfString[0]))).getDvcVos().get(Integer.parseInt(paramArrayOfString[1]))).setSensorVo(new SensorVo());
              ((Dvc)((Room)this.home.getRooms().get(Integer.parseInt(paramArrayOfString[0]))).getDvcVos().get(Integer.parseInt(paramArrayOfString[1]))).getSensorVo().getSmsLogs().add(localSmsLogVo);
            }
            putData4ClassName(this.mac, this.home);
          }
          return 1;
        }
        return -3;
      }
      return -2;
    }
    return -1;
  }

  public void smsLogListener()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        while (true)
        {
          XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener()
          {
            public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
            {
            }

            public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
            {
              Log.i("", "");
            }

            public void onDisconnect(int paramInt)
            {
              Log.i("", "");
            }

            public void onEventNotify(EventNotify paramEventNotify)
            {
            }

            public void onLocalDisconnect(int paramInt)
            {
              Log.i("", "");
            }

            public void onLogin(int paramInt)
            {
              Log.i("", "");
            }

            public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
            {
              String str = StringUtils.btye2Str(paramArrayOfByte);
              FtSmsLogBusiness.this.operatingData(str);
            }

            public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
            {
              StringUtils.btye2Str(paramArrayOfByte);
            }

            public void onStart(int paramInt)
            {
              Log.i("", "");
            }
          });
          try
          {
            Thread.sleep(10000L);
          }
          catch (InterruptedException localInterruptedException)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
    }).start();
  }

  public void syncSceneInfo(int paramInt1, int paramInt2, String[] paramArrayOfString)
  {
    SystemClock.sleep(200L);
    int i = paramInt2 % 50;
    int j = paramInt2 / 50;
    for (int k = 0; k < j + i; k++)
    {
      this.useToQuerySmsLogs.indexOf(Integer.valueOf(paramInt1));
      for (int m = 0; m < this.sensorDvcList.size(); m++)
        if (((Dvc)this.sensorDvcList.get(m)).getmIndex() != paramInt1)
          continue;
      syncSmsLogInfo(new MyBusiness.MySendListener(paramInt1, paramArrayOfString)
      {
        public void onFail()
        {
          Log.i("", "");
        }

        public void onOk(byte[] paramArrayOfByte)
        {
          String str = StringUtils.btye2Str(paramArrayOfByte);
          FtSmsLogBusiness.this.saveSmsLogData(str, paramArrayOfByte, this.val$serniorId, this.val$sensorIndex);
          if (((Dvc)((Room)FtSmsLogBusiness.this.home.getRooms().get(Integer.parseInt(this.val$sensorIndex[0]))).getDvcVos().get(Integer.parseInt(this.val$sensorIndex[1]))).getSensorVo() != null)
            ((Dvc)((Room)FtSmsLogBusiness.this.home.getRooms().get(Integer.parseInt(this.val$sensorIndex[0]))).getDvcVos().get(Integer.parseInt(this.val$sensorIndex[1]))).getSensorVo().getSmsLogs().size();
          Log.i("", "");
        }

        public void onTimeOut()
        {
          Log.i("", "");
        }
      }
      , paramInt1, 1 + k * 50);
    }
  }

  public void syncSmsLogInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(40));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(6));
    this.cmd.add(Integer.valueOf(paramInt1));
    String str1 = Integer.toHexString(paramInt2);
    if (str1.length() < 4)
    {
      if (str1.length() == 1)
        str1 = "000" + str1;
    }
    else
    {
      if (str1.length() <= 2)
        break label289;
      String str3 = str1.substring(2, str1.length());
      String str4 = str1.substring(0, 2);
      this.cmd.add(Integer.valueOf(Integer.parseInt(str3, 16)));
      this.cmd.add(Integer.valueOf(Integer.parseInt(str4, 16)));
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      if (str1.length() == 2)
      {
        str1 = "00" + str1;
        break;
      }
      if (str1.length() != 3)
        break;
      str1 = "0" + str1;
      break;
      label289: String str2 = str1.substring(0, str1.length());
      this.cmd.add(Integer.valueOf(Integer.parseInt(str2, 16)));
      this.cmd.add(Integer.valueOf(0));
    }
  }

  public void useToSaveList(List<Integer> paramList, List<Dvc> paramList1)
  {
    this.useToQuerySmsLogs = paramList;
    this.sensorDvcList = paramList1;
    Log.i("", "");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.SmsLog.FtSmsLogBusiness
 * JD-Core Version:    0.6.0
 */