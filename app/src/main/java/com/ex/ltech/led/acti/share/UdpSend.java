package com.ex.ltech.led.acti.share;

import android.app.Activity;
import android.os.Build;
import android.os.SystemClock;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpSend
{
  private boolean isOpenLoopSend;
  Activity mainA = null;
  public String phoneAndIpInfo;

  public UdpSend()
  {
  }

  public UdpSend(Activity paramActivity)
  {
    this.mainA = paramActivity;
  }

  public void SendStrMsg(String paramString)
  {
    try
    {
      byte[] arrayOfByte = paramString.getBytes("utf-8");
      DatagramSocket localDatagramSocket = new DatagramSocket(2426);
      DatagramPacket localDatagramPacket = new DatagramPacket(arrayOfByte, arrayOfByte.length, InetAddress.getByName(Tools.getBroadCastIP()), 2425);
      localDatagramPacket.setData(arrayOfByte);
      localDatagramSocket.send(localDatagramPacket);
      localDatagramSocket.close();
      System.out.println("SendStrMsg        SendStrMsg");
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void loopSendMyInfo()
  {
    this.isOpenLoopSend = true;
    new LoopSend().start();
  }

  public void loopSendStop()
  {
    this.isOpenLoopSend = false;
  }

  class LoopSend extends Thread
  {
    LoopSend()
    {
    }

    public void run()
    {
      while (UdpSend.this.isOpenLoopSend)
      {
        UdpSend.this.SendStrMsg(Build.MODEL + "|" + Tools.getLocalHostIp());
        SystemClock.sleep(500L);
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.UdpSend
 * JD-Core Version:    0.6.0
 */