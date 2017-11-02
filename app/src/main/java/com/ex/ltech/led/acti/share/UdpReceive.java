package com.ex.ltech.led.acti.share;

import android.os.SystemClock;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UdpReceive
{
  private boolean isOpenLoopRec;
  private UdpRecListener listener;
  private boolean runStatus = true;

  private void resume()
  {
    this.runStatus = true;
  }

  public void receiveMsg()
  {
    try
    {
      DatagramSocket localDatagramSocket = new DatagramSocket(2425);
      byte[] arrayOfByte1 = new byte[4096];
      DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte1, arrayOfByte1.length);
      localDatagramPacket.setData(arrayOfByte1);
      localDatagramSocket.receive(localDatagramPacket);
      byte[] arrayOfByte2 = new byte[localDatagramPacket.getLength()];
      System.arraycopy(arrayOfByte1, 0, arrayOfByte2, 0, arrayOfByte2.length);
      String str = new String(arrayOfByte2, "UTF-8");
      String[] arrayOfString = str.split("\\|");
      if (this.listener != null)
      {
        System.out.println("MessageGet         " + str + "     弹出手机列表");
        this.listener.onUdpRec(arrayOfString[0], arrayOfString[1]);
        this.runStatus = false;
        localDatagramSocket.close();
        localDatagramSocket.disconnect();
      }
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void setListener(UdpRecListener paramUdpRecListener)
  {
    this.listener = paramUdpRecListener;
  }

  public void start()
  {
    if (!this.isOpenLoopRec)
    {
      this.isOpenLoopRec = true;
      new FReceive().start();
    }
  }

  public void stop()
  {
    this.isOpenLoopRec = false;
  }

  class FReceive extends Thread
  {
    FReceive()
    {
    }

    public void run()
    {
      while (UdpReceive.this.isOpenLoopRec)
      {
        System.out.println("MessageGet               接收报文");
        UdpReceive.this.receiveMsg();
        SystemClock.sleep(500L);
      }
    }
  }

  public static abstract interface UdpRecListener
  {
    public abstract void onUdpRec(String paramString1, String paramString2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.UdpReceive
 * JD-Core Version:    0.6.0
 */