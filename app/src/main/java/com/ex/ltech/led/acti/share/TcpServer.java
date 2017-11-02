package com.ex.ltech.led.acti.share;

import android.app.Activity;
import com.google.gson.Gson;
import et.song.db.ETDB;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer
{
  private Object data;
  private ETGroup group;
  private OnSendTcpDataListener listener;
  private OutputStream os;
  private ServerSocket serverSocket;
  private Socket socket;
  private UdpSend udpSend;

  public TcpServer(Activity paramActivity, UdpSend paramUdpSend)
  {
    this.udpSend = paramUdpSend;
    ETPage.getInstance(paramActivity).Load(ETDB.getInstance(paramActivity));
    this.group = ((ETGroup)ETPage.getInstance(paramActivity).GetItem(0));
    this.group.LoadEnableDevice(ETDB.getInstance(paramActivity));
  }

  public TcpServer(Object paramObject, UdpSend paramUdpSend)
  {
    this.data = paramObject;
    this.udpSend = paramUdpSend;
  }

  public void closeTcpServer()
  {
    try
    {
      if (this.serverSocket != null)
      {
        this.serverSocket.close();
        this.serverSocket = null;
      }
    }
    catch (IOException localIOException2)
    {
      try
      {
        if (this.socket != null)
        {
          this.socket.close();
          this.socket = null;
        }
      }
      catch (IOException localIOException2)
      {
        try
        {
          while (true)
          {
            if (this.os != null)
            {
              this.os.close();
              this.os = null;
            }
            return;
            localIOException1 = localIOException1;
            localIOException1.printStackTrace();
            continue;
            localIOException2 = localIOException2;
            localIOException2.printStackTrace();
          }
        }
        catch (IOException localIOException3)
        {
          localIOException3.printStackTrace();
        }
      }
    }
  }

  public void setListener(OnSendTcpDataListener paramOnSendTcpDataListener)
  {
    this.listener = paramOnSendTcpDataListener;
  }

  public void start()
  {
    new server().start();
  }

  public static abstract interface OnSendTcpDataListener
  {
    public abstract void onSendTcpDataOk();
  }

  class server extends Thread
  {
    server()
    {
    }

    public void run()
    {
      String str = new Gson().toJson(TcpServer.this.data);
      try
      {
        byte[] arrayOfByte2 = str.getBytes("utf-8");
        arrayOfByte1 = arrayOfByte2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        try
        {
          while (true)
          {
            TcpServer.access$102(TcpServer.this, new ServerSocket(9999));
            TcpServer.access$202(TcpServer.this, TcpServer.this.serverSocket.accept());
            TcpServer.this.udpSend.loopSendStop();
            System.out.println("SendStrMsg        serverSocket.accept");
            TcpServer.access$402(TcpServer.this, TcpServer.this.socket.getOutputStream());
            TcpServer.this.os.write(arrayOfByte1);
            TcpServer.this.os.flush();
            TcpServer.this.os.close();
            TcpServer.this.socket.close();
            TcpServer.this.serverSocket.close();
            if (TcpServer.this.listener != null)
              TcpServer.this.listener.onSendTcpDataOk();
            return;
            localUnsupportedEncodingException = localUnsupportedEncodingException;
            localUnsupportedEncodingException.printStackTrace();
            byte[] arrayOfByte1 = str.getBytes();
          }
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.TcpServer
 * JD-Core Version:    0.6.0
 */