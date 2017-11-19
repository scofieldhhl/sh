package com.ex.ltech;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class WifiReceiver extends BroadcastReceiver
{
  long lastTime = 0L;

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    /*if (paramIntent.getAction().equals("android.net.wifi.RSSI_CHANGED"));
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
          if (localNetworkInfo.getState().equals(State.DISCONNECTED))
          {
            if (System.currentTimeMillis() - this.lastTime > 3000L)
              Toast.makeText(paramContext, R.string.disconnet_wifi, Toast.LENGTH_SHORT).show();
            this.lastTime = System.currentTimeMillis();
            return;
          }
          if (!localNetworkInfo.getState().equals(State.CONNECTED))
            continue;
          WifiInfo localWifiInfo = ((WifiManager)paramContext.getSystemService(Context.WIFI_SERVICE)).getConnectionInfo();
          if (System.currentTimeMillis() - this.lastTime > 3000L)
            Toast.makeText(paramContext, paramContext.getString(R.string.connet_to) + localWifiInfo.getSSID(), Toast.LENGTH_SHORT).show();;
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
    System.out.println("系统开启wifi");*/
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.WifiReceiver
 * JD-Core Version:    0.6.0
 */