package com.ex.ltech.onepiontfive.main;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.google.gson.Gson;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MyBusiness
{
  CheckTimeOutThread checkTimeOutThread = new CheckTimeOutThread();
  ArrayList<Integer> cmd = new ArrayList();
  public int cmdCount;
  Runnable crasySendThread = new Runnable()
  {
    public void run()
    {
      if (MyBusiness.this.crasySendTime < 3)
      {
        MyBusiness localMyBusiness = MyBusiness.this;
        localMyBusiness.crasySendTime = (1 + localMyBusiness.crasySendTime);
        MyBusiness.this.crasySend();
        return;
      }
      MyBusiness.this.crasySendTime = 0;
    }
  };
  int crasySendTime = 0;
  public Context ct;
  byte[] currentSendDatas = new byte[0];
  XDevice currentxDevice;
  DvcStatusListener dvcStatusListener;
  private ArrayList<Dvc> dvcs = null;
  public SharedPreferences getter;
  public Gson gs;
  Handler handler;
  boolean isBusy = false;
  boolean isSended;
  boolean isSendting;
  private String key = "";
  private String lastReturnData = "";
  public String mac = "";
  Handler myMainThreadHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      byte[] arrayOfByte = (byte[])(byte[])paramMessage.obj;
      if (arrayOfByte != null)
      {
        if (MyBusiness.this.mySendListener != null)
          MyBusiness.this.mySendListener.onOk(arrayOfByte);
        if (MyBusiness.this.dvcStatusListener != null)
          MyBusiness.this.dvcStatusListener.onOk(arrayOfByte);
      }
    }
  };
  MySendListener mySendListener;
  public MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  public List<PanelLampVO> panelLampVOs = new ArrayList();
  private int panelPostion = 0;
  SendStatusThread sendStatusThread = new SendStatusThread();
  public UserFerences setter;

  public MyBusiness(Context paramContext)
  {
    this.ct = paramContext;
    this.setter = UserFerences.getUserFerences(this.ct);
    this.getter = this.setter.spFerences;
    this.gs = new Gson();
    this.handler = new Handler()
    {
    };
    this.cmdCount = (int)(100.0D * Math.random());
  }

  private void crasySend()
  {
    SocketManager.instance().sendData(this.currentSendDatas);
    this.handler.removeCallbacks(this.crasySendThread);
    this.handler.postDelayed(this.crasySendThread, 100L);
  }

  public static String hexString2binaryString(String paramString)
  {
    String str1;
    if ((paramString == null) || (paramString.length() % 2 != 0))
      str1 = null;
    while (true)
    {
      return str1;
      str1 = "";
      for (int i = 0; i < paramString.length(); i++)
      {
        String str2 = "0000" + Integer.toBinaryString(Integer.parseInt(paramString.substring(i, i + 1), 16));
        str1 = str1 + str2.substring(-4 + str2.length());
      }
    }
  }

  public void addCheckSumData(List<Integer> paramList)
  {
    int i = 0;
    int j = 0;
    if (j < paramList.size())
    {
      if (((Integer)paramList.get(j)).intValue() < 0)
        i += (0xFF & ((Integer)paramList.get(j)).intValue());
      while (true)
      {
        j++;
        break;
        i += ((Integer)paramList.get(j)).intValue();
      }
    }
    String str1 = Integer.toHexString(i);
    if (str1.length() == 3)
      str1 = "0" + str1;
    if (str1.length() == 2)
      str1 = "00" + str1;
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = str1.substring(0, 2);
    paramList.add(Integer.valueOf(str2, 16));
    paramList.add(Integer.valueOf(str3, 16));
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

  public void addDeviceId2Cmd(ArrayList<Integer> paramArrayList, String paramString)
  {
    paramArrayList.add(Integer.valueOf(Integer.parseInt(paramString.substring(6, 8), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(paramString.substring(4, 6), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(paramString.substring(2, 4), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(paramString.substring(0, 2), 16)));
  }

  public void addNormalHeadData(List<Integer> paramList)
  {
    if (this.cmdCount > 255)
      this.cmdCount = 0;
    this.cmdCount = (1 + this.cmdCount);
    paramList.clear();
    paramList.add(Integer.valueOf(90));
    paramList.add(Integer.valueOf(165));
    paramList.add(Integer.valueOf(this.cmdCount));
    paramList.add(Integer.valueOf(1));
    paramList.add(Integer.valueOf(17));
    String str = this.getter.getString("GateWayIdKey" + this.getter.getString("GateWayMacIdKey", "11223344"), "FFFFFFFF");
    paramList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 2), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt(str.substring(2, 4), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt(str.substring(4, 6), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt(str.substring(6, 8), 16)));
  }

  public void addNormalHeadData2(List<Integer> paramList)
  {
    if (this.cmdCount > 255)
      this.cmdCount = 0;
    this.cmdCount = (1 + this.cmdCount);
    paramList.clear();
    paramList.add(Integer.valueOf(90));
    paramList.add(Integer.valueOf(165));
    paramList.add(Integer.valueOf(this.cmdCount));
    paramList.add(Integer.valueOf(1));
    paramList.add(Integer.valueOf(17));
    paramList.add(Integer.valueOf(Integer.parseInt("FFFFFFFF".substring(0, 2), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt("FFFFFFFF".substring(2, 4), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt("FFFFFFFF".substring(4, 6), 16)));
    paramList.add(Integer.valueOf(Integer.parseInt("FFFFFFFF".substring(6, 8), 16)));
  }

  public void addUserId2Cmd(ArrayList<Integer> paramArrayList)
  {
    String str = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
    for (int i = str.length(); i < 8; i++)
      str = "0" + str;
    paramArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(6, 8), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(4, 6), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(2, 4), 16)));
    paramArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(0, 2), 16)));
  }

  public void changeName(MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    setMySendListener(paramMySendListener);
    System.out.println("changeName nameType= " + paramInt1 + "   dType= " + paramInt2 + "     index= " + paramInt3);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(45));
    this.cmd.add(Integer.valueOf(28));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt3));
    int i = 0;
    if (i < 24)
    {
      if (i < paramArrayOfByte.length)
        this.cmd.add(Integer.valueOf(paramArrayOfByte[i]));
      while (true)
      {
        i++;
        break;
        this.cmd.add(Integer.valueOf(0));
      }
    }
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void crasySendCmdNoResponse(ArrayList<Integer> paramArrayList)
  {
    DeviceManage.getInstance();
    this.currentxDevice = DeviceManage.getxDevice();
    this.currentSendDatas = getCmdData(paramArrayList);
    crasySend();
  }

  @Deprecated
  public Object getBean4ClassName(String paramString, Class paramClass)
  {
    String str = this.getter.getString(paramString + paramClass.getName(), "");
    return this.gs.fromJson(str, paramClass);
  }

  public Object getCacheBean(Class paramClass)
  {
    String str = this.getter.getString("cacheData", "");
    return this.gs.fromJson(str, paramClass);
  }

  public byte[] getCmdData(ArrayList<Integer> paramArrayList)
  {
    byte[] arrayOfByte = new byte[paramArrayList.size()];
    for (int i = 0; i < paramArrayList.size(); i++)
      arrayOfByte[i] = (byte)((Integer)paramArrayList.get(i)).intValue();
    System.out.println("getCmdData==" + StringUtils.btye2Str3(arrayOfByte));
    return arrayOfByte;
  }

  public byte[] getCmdData2(ArrayList<Byte> paramArrayList)
  {
    byte[] arrayOfByte = new byte[paramArrayList.size()];
    for (int i = 0; i < paramArrayList.size(); i++)
      arrayOfByte[i] = ((Byte)paramArrayList.get(i)).byteValue();
    return arrayOfByte;
  }

  @Deprecated
  public Object getData(String paramString, Class paramClass)
  {
    String str = this.getter.getString(paramString, "");
    return this.gs.fromJson(str, paramClass);
  }

  public ArrayList<Dvc> listenerCmd(ArrayList<Dvc> paramArrayList, String paramString)
  {
    return this.dvcs;
  }

  public List<PanelLampVO> listenerCmd(List<PanelLampVO> paramList, String paramString, int paramInt)
  {
    return this.panelLampVOs;
  }

  public ArrayList<Dvc> listenerRemoteCmd(String paramString)
  {
    return this.dvcs;
  }

  public void putCacheData(Object paramObject)
  {
    this.setter.putValue("cacheData", this.gs.toJson(paramObject));
  }

  @Deprecated
  public void putData(String paramString, Object paramObject)
  {
    this.setter.putValue(paramString, this.gs.toJson(paramObject));
  }

  @Deprecated
  public void putData4ClassName(String paramString, Object paramObject)
  {
    this.setter.putValue(paramString + paramObject.getClass().getName(), this.gs.toJson(paramObject));
  }

  public void queryName(int paramInt1, int paramInt2, int paramInt3, byte[] paramArrayOfByte, boolean paramBoolean)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(45));
    this.cmd.add(Integer.valueOf(4 + paramArrayOfByte.length));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt3));
    for (int i = 0; i < paramArrayOfByte.length; i++)
      this.cmd.add(Integer.valueOf(paramArrayOfByte[i]));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void respMessage(String paramString1, String paramString2)
  {
    ArrayList localArrayList = new ArrayList();
    addNormalHeadData(localArrayList);
    localArrayList.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    localArrayList.add(Integer.valueOf(1));
    addCheckSumData(localArrayList);
    localArrayList.add(Integer.valueOf(22));
    sendCmd(localArrayList);
  }

  public void sendCmd(ArrayList<Integer> paramArrayList)
  {
    DeviceManage.getInstance();
    this.currentxDevice = DeviceManage.getxDevice();
    this.currentSendDatas = getCmdData(paramArrayList);
    crasySend();
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    this.isSended = true;
    this.isBusy = true;
    this.handler.removeCallbacks(this.checkTimeOutThread);
    this.handler.postDelayed(this.checkTimeOutThread, 5000L);
    this.handler.removeCallbacks(this.sendStatusThread);
    this.handler.postDelayed(this.sendStatusThread, 5000L);
    this.setter.putValue("SendCmdlLastTimeKey", Long.valueOf(System.currentTimeMillis()));
  }

  public void sendCmd(ArrayList<Integer> paramArrayList, XDevice paramXDevice)
  {
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    this.currentxDevice = paramXDevice;
    this.currentSendDatas = getCmdData(paramArrayList);
    crasySend();
    this.isSended = true;
    this.isBusy = true;
    this.handler.removeCallbacks(this.checkTimeOutThread);
    this.handler.postDelayed(this.checkTimeOutThread, 5000L);
    this.handler.removeCallbacks(this.sendStatusThread);
    this.handler.postDelayed(this.sendStatusThread, 5000L);
    this.setter.putValue("SendCmdlLastTimeKey", Long.valueOf(System.currentTimeMillis()));
  }

  public void sendCmd(byte[] paramArrayOfByte)
  {
    DeviceManage.getInstance();
    this.currentxDevice = DeviceManage.getxDevice();
    this.currentSendDatas = paramArrayOfByte;
    crasySend();
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    this.isSended = true;
    this.handler.removeCallbacks(this.checkTimeOutThread);
    this.handler.postDelayed(this.checkTimeOutThread, 5000L);
    this.handler.removeCallbacks(this.sendStatusThread);
    this.handler.postDelayed(this.sendStatusThread, 5000L);
    this.setter.putValue("SendCmdlLastTimeKey", Long.valueOf(System.currentTimeMillis()));
  }

  public void sendCmdNoResponse(ArrayList<Integer> paramArrayList)
  {
    System.out.println("sendCmdNoResponse");
    SocketManager.instance().sendData(getCmdData(paramArrayList));
  }

  public void sendCmdWithoutCrasySend(ArrayList<Integer> paramArrayList)
  {
    SocketManager.instance().sendData(getCmdData(paramArrayList));
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    this.isSended = true;
    this.isBusy = true;
    this.handler.removeCallbacks(this.checkTimeOutThread);
    this.handler.postDelayed(this.checkTimeOutThread, 5000L);
    this.handler.removeCallbacks(this.sendStatusThread);
    this.handler.postDelayed(this.sendStatusThread, 5000L);
    this.setter.putValue("SendCmdlLastTimeKey", Long.valueOf(System.currentTimeMillis()));
  }

  public void sendHeartbeatCmd(ArrayList<Integer> paramArrayList)
  {
    if (!this.isBusy)
      SocketManager.instance().sendData(getCmdData(paramArrayList));
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
  }

  public void setDvcStatusListener(DvcStatusListener paramDvcStatusListener)
  {
    this.dvcStatusListener = paramDvcStatusListener;
  }

  public void setMySendListener(MySendListener paramMySendListener)
  {
    this.mySendListener = paramMySendListener;
  }

  class CheckTimeOutThread
    implements Runnable
  {
    CheckTimeOutThread()
    {
    }

    public void run()
    {
      if (MyBusiness.this.mySendListener != null)
      {
        MyBusiness.this.mySendListener.onTimeOut();
        MyBusiness.this.mySendListener = null;
      }
    }
  }

  public static abstract interface DvcStatusListener
  {
    public abstract void onOk(byte[] paramArrayOfByte);
  }

  public static abstract interface MySendListener
  {
    public abstract void onFail();

    public abstract void onOk(byte[] paramArrayOfByte);

    public abstract void onTimeOut();
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
      if (DeviceListActivity.netStats != 0);
      String str;
      do
      {
        return;
        StringUtils.btye2Str3(paramArrayOfByte);
        str = paramXDevice.getMacAddress();
      }
      while ((str == null) || (str.length() == 0) || (!DeviceListActivity.deviceMacAddress.equals(paramXDevice.getMacAddress())));
      MyBusiness.this.handler.removeCallbacks(MyBusiness.this.checkTimeOutThread);
      Message localMessage = new Message();
      localMessage.obj = paramArrayOfByte;
      MyBusiness.this.myMainThreadHandler.sendMessage(localMessage);
      MyBusiness.this.isSended = false;
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      if (DeviceListActivity.netStats != 1);
      String str;
      do
      {
        return;
        StringUtils.btye2Str3(paramArrayOfByte);
        str = paramXDevice.getMacAddress();
      }
      while ((str == null) || (str.length() == 0) || (!DeviceListActivity.deviceMacAddress.equals(paramXDevice.getMacAddress())));
      System.out.println("4G return = ");
      MyBusiness.this.handler.removeCallbacks(MyBusiness.this.checkTimeOutThread);
      Message localMessage = new Message();
      localMessage.obj = paramArrayOfByte;
      MyBusiness.this.myMainThreadHandler.sendMessage(localMessage);
      MyBusiness.this.isSended = false;
    }

    public void onStart(int paramInt)
    {
    }
  }

  class SendStatusThread
    implements Runnable
  {
    SendStatusThread()
    {
    }

    public void run()
    {
      MyBusiness.this.isBusy = false;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.MyBusiness
 * JD-Core Version:    0.6.0
 */