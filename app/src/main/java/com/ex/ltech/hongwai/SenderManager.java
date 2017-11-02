package com.ex.ltech.hongwai;

import android.os.Handler;
import android.widget.Toast;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.utils.StringUtils;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.util.List;

public class SenderManager
{
  private static final SenderManager instence = new SenderManager();
  CheckTimeOutThread checkTimeOutThread = new CheckTimeOutThread();
  Handler handler = new Handler();
  boolean isSended;
  private byte[] lastCmd;
  private int loopSendTime = 0;
  MySendListener mySendListener;
  MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
  private int recDataLenth;
  private String recFunction;
  SendPipeListener sendPipeListener = new SendPipeListener()
  {
    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  };

  public static SenderManager instance()
  {
    return instence;
  }

  private void sendCmd(byte[] paramArrayOfByte)
  {
    this.loopSendTime = (1 + this.loopSendTime);
    this.lastCmd = paramArrayOfByte;
    XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    if (localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), paramArrayOfByte, this.sendPipeListener) < 0)
    {
      Toast.makeText(MyApp.getApp(), 2131099989, 0).show();
      XlinkAgent.getInstance().connectDevice(DeviceManage.getxDevice(), "8888", new ConnectDeviceListener()
      {
        public void onConnectDevice(XDevice paramXDevice, int paramInt)
        {
          int i = 1;
          int j;
          if (paramInt == 0)
          {
            j = i;
            if (paramInt != i)
              break label40;
          }
          while (true)
          {
            if ((i | j) != 0)
              Toast.makeText(MyApp.getApp(), 2131099988, 0).show();
            return;
            j = 0;
            break;
            label40: i = 0;
          }
        }
      });
    }
    this.isSended = true;
    this.handler.removeCallbacks(this.checkTimeOutThread);
    this.handler.postDelayed(this.checkTimeOutThread, 2000L);
  }

  public void end()
  {
    this.recDataLenth = 0;
    this.recFunction = "";
    this.mySendListener = null;
  }

  public void prepare(int paramInt, String paramString)
  {
    this.recDataLenth = paramInt;
    this.recFunction = paramString;
  }

  public void sendCmd(byte[] paramArrayOfByte, MySendListener paramMySendListener)
  {
    this.mySendListener = paramMySendListener;
    sendCmd(paramArrayOfByte);
  }

  public void setMySendListener(MySendListener paramMySendListener)
  {
    this.mySendListener = paramMySendListener;
  }

  public void setRecDataLenth(int paramInt)
  {
    this.recDataLenth = paramInt;
  }

  public void setRecFunction(String paramString)
  {
    this.recFunction = paramString;
  }

  class CheckTimeOutThread
    implements Runnable
  {
    CheckTimeOutThread()
    {
    }

    public void run()
    {
      if (SenderManager.this.loopSendTime < 3)
        SenderManager.this.sendCmd(SenderManager.this.lastCmd);
      do
        return;
      while (SenderManager.this.mySendListener == null);
      SenderManager.this.mySendListener.onFailde();
    }
  }

  public static abstract interface MySendListener
  {
    public abstract void onFailde();

    public abstract void onOk(byte[] paramArrayOfByte);
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
      if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0));
      do
        return;
      while (!DeviceListActivity.deviceMacAddress.equals(paramXDevice.getMacAddress()));
      String str = StringUtils.btye2Str(paramArrayOfByte);
      if ((SenderManager.this.mySendListener != null) && (str.length() == SenderManager.this.recDataLenth) && (str.substring(12, 14).equalsIgnoreCase(SenderManager.this.recFunction)))
      {
        SenderManager.this.handler.removeCallbacks(SenderManager.this.checkTimeOutThread);
        SenderManager.this.mySendListener.onOk(paramArrayOfByte);
      }
      SenderManager.this.isSended = false;
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
    }

    public void onStart(int paramInt)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.SenderManager
 * JD-Core Version:    0.6.0
 */