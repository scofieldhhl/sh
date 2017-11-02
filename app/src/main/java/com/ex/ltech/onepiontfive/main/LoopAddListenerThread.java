package com.ex.ltech.onepiontfive.main;

import android.content.SharedPreferences;
import android.os.SystemClock;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.List;

public class LoopAddListenerThread extends Thread
{
  AtMain atMain;
  MyBusiness business;
  boolean isOpenLoop = false;

  public void closeLoopAddListener()
  {
    this.isOpenLoop = false;
  }

  public void openLoopAddListener()
  {
    if (!this.isOpenLoop)
    {
      this.isOpenLoop = true;
      start();
    }
  }

  public void run()
  {
    super.run();
    while (this.isOpenLoop)
    {
      SystemClock.sleep(1000L);
      System.out.println("SystemClock.sleep(1000)");
      if (System.currentTimeMillis() - this.business.getter.getLong("SendCmdlLastTimeKey", 0L) <= 10000L)
        continue;
      this.atMain.runOnUiThread(new Runnable()
      {
        public void run()
        {
          XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener()
          {
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
              LoopAddListenerThread.this.atMain.receivePush();
            }

            public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
            {
            }

            public void onStart(int paramInt)
            {
            }
          });
        }
      });
    }
  }

  public void setAtMain(AtMain paramAtMain)
  {
    this.atMain = paramAtMain;
    this.business = new MyBusiness(paramAtMain);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.LoopAddListenerThread
 * JD-Core Version:    0.6.0
 */