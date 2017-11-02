package com.ex.ltech.led.connetion;

import android.os.AsyncTask;
import android.os.Handler;
import com.ex.ltech.led.utils.StringUtils;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;

public class SocketManager
{
  private static SocketManager socketManager;
  String TAG = "SocketManager";
  private final int TIME_OUT = 12800;
  private int connet = 0;
  private int disConnet = 1;
  private InputStream inputStream;
  public String ip;
  private boolean isOpenReadThread;
  private OutputStream outputStream;
  Handler pHandler;
  public int port = 8899;
  private int send = 2;
  SendPipeListener sendPipeListener = new SendPipeListener()
  {
    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  };
  private Socket socket;
  public int status = this.disConnet;

  public static SocketManager instance()
  {
    if (socketManager == null)
      socketManager = new SocketManager();
    return socketManager;
  }

  private void tryToGetInputStream()
    throws IOException, IndexOutOfBoundsException
  {
  }

  public void close()
  {
    try
    {
      if (this.socket == null)
        return;
      this.outputStream = null;
      this.inputStream = null;
      if (!this.socket.isClosed())
        this.socket.close();
      this.socket = null;
      this.status = this.disConnet;
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void closeReadThread()
  {
    this.isOpenReadThread = false;
  }

  public void connect(String paramString, int paramInt)
  {
  }

  public void jsutSendData(byte[] paramArrayOfByte)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), paramArrayOfByte, this.sendPipeListener);
  }

  public void openReadThread()
  {
    this.isOpenReadThread = true;
  }

  public void postTask(byte[] paramArrayOfByte)
  {
    sendData(paramArrayOfByte);
  }

  public void postTaskNoReturn(byte[] paramArrayOfByte)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), paramArrayOfByte, this.sendPipeListener);
  }

  public void sendData(byte[] paramArrayOfByte)
  {
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    int i = localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), paramArrayOfByte, this.sendPipeListener);
    System.out.println("sendData = " + StringUtils.btye2Str3(paramArrayOfByte));
    if (i < 0)
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
              break label28;
          }
          while (true)
          {
            if ((i | j) != 0);
            return;
            j = 0;
            break;
            label28: i = 0;
          }
        }
      });
  }

  public void setpHandler(Handler paramHandler)
  {
    this.pHandler = paramHandler;
  }

  private class MyTask extends AsyncTask<byte[], Integer, String>
  {
    private MyTask()
    {
    }

    protected String doInBackground(byte[][] paramArrayOfByte)
    {
      try
      {
        SocketManager.this.sendData(paramArrayOfByte[0]);
        label10: return null;
      }
      catch (Exception localException)
      {
        break label10;
      }
    }

    protected void onCancelled()
    {
    }

    protected void onPostExecute(String paramString)
    {
    }

    protected void onProgressUpdate(Integer[] paramArrayOfInteger)
    {
    }
  }

  public static abstract interface SocketConnectListener
  {
    public abstract void connect(String paramString);

    public abstract void disconnect(String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.connetion.SocketManager
 * JD-Core Version:    0.6.0
 */