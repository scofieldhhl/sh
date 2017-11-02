package com.ex.ltech.led.acti.share;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;

public class TcpClient
{
  String ip;
  private OnRecTcpDataListener listener;
  String path = null;

  public String getIp()
  {
    return this.ip;
  }

  public void recTcpData()
  {
    Socket localSocket;
    InputStream localInputStream;
    StringBuffer localStringBuffer;
    try
    {
      localSocket = new Socket(this.ip, 9999);
      localInputStream = localSocket.getInputStream();
      localStringBuffer = new StringBuffer();
      byte[] arrayOfByte = new byte[102400];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1)
          break;
        localStringBuffer.append(new String(arrayOfByte, 0, i));
        System.out.println("read:" + i);
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    if (this.listener != null)
      this.listener.onRecTcpDataOk(localStringBuffer.toString());
    localSocket.close();
    localInputStream.close();
    System.out.println(localStringBuffer.toString());
  }

  public void setIp(String paramString)
  {
    this.ip = paramString;
  }

  public void setListener(OnRecTcpDataListener paramOnRecTcpDataListener)
  {
    this.listener = paramOnRecTcpDataListener;
  }

  public void start()
  {
    new Client().start();
  }

  class Client extends Thread
  {
    Client()
    {
    }

    public void run()
    {
      try
      {
        TcpClient.this.recTcpData();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public static abstract interface OnRecTcpDataListener
  {
    public abstract void onRecTcpDataOk(String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.TcpClient
 * JD-Core Version:    0.6.0
 */