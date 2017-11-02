package com.ex.ltech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;
import java.io.PrintStream;

public class WifiReceiver extends BroadcastReceiver
{
  long lastTime = 0L;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if (paramIntent.getAction().equals("android.net.wifi.RSSI_CHANGED"));
    int i;
    do
    {
      do
        while (true)
        {
          return;
          if (!paramIntent.getAction().equals("android.net.wifi.STATE_CHANGE"))
            break;
          NetworkInfo localNetworkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
          if (localNetworkInfo.getState().equals(NetworkInfo.State.DISCONNECTED))
          {
            if (System.currentTimeMillis() - this.lastTime > 3000L)
              Toast.makeText(paramContext, 2131100039, 0).show();
            this.lastTime = System.currentTimeMillis();
            return;
          }
          if (!localNetworkInfo.getState().equals(NetworkInfo.State.CONNECTED))
            continue;
          WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo();
          if (System.currentTimeMillis() - this.lastTime > 3000L)
            Toast.makeText(paramContext, paramContext.getString(2131099971) + localWifiInfo.getSSID(), 0).show();
          this.lastTime = System.currentTimeMillis();
          return;
        }
      while (!paramIntent.getAction().equals("android.net.wifi.WIFI_STATE_CHANGED"));
      i = paramIntent.getIntExtra("wifi_state", 1);
      if (i != 1)
        continue;
      System.out.println("系统关闭wifi");
      return;
    }
    while (i != 3);
    System.out.println("系统开启wifi");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.WifiReceiver
 * JD-Core Version:    0.6.0
 */