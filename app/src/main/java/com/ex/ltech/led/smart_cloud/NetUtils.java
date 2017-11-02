package com.ex.ltech.led.smart_cloud;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.util.List;

public class NetUtils
{
  public static int getConnectedType(Context paramContext)
  {
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getActiveNetworkInfo();
      if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
        return localNetworkInfo.getType();
    }
    return -1;
  }

  public static String getCurentWifiSSID(Context paramContext)
  {
    String str = "";
    if (paramContext != null)
    {
      str = ((WifiManager)paramContext.getSystemService("wifi")).getConnectionInfo().getSSID();
      if ((str.substring(0, 1).equals("\"")) && (str.substring(-1 + str.length()).equals("\"")))
        str = str.substring(1, -1 + str.length());
    }
    return str;
  }

  public static List<ScanResult> getCurrentWifiScanResult(Context paramContext)
  {
    WifiManager localWifiManager = (WifiManager)paramContext.getSystemService("wifi");
    localWifiManager.startScan();
    return localWifiManager.getScanResults();
  }

  public static boolean isMobileConnected(Context paramContext)
  {
    boolean bool1 = false;
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(0);
      bool1 = false;
      if (localNetworkInfo != null)
      {
        boolean bool2 = localNetworkInfo.isAvailable();
        bool1 = false;
        if (bool2)
          bool1 = localNetworkInfo.isConnected();
      }
    }
    return bool1;
  }

  public static boolean isWifiConnected(Context paramContext)
  {
    boolean bool1 = false;
    if (paramContext != null)
    {
      NetworkInfo localNetworkInfo = ((ConnectivityManager)paramContext.getSystemService("connectivity")).getNetworkInfo(1);
      bool1 = false;
      if (localNetworkInfo != null)
      {
        boolean bool2 = localNetworkInfo.isAvailable();
        bool1 = false;
        if (bool2)
          bool1 = localNetworkInfo.isConnected();
      }
    }
    return bool1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.smart_cloud.NetUtils
 * JD-Core Version:    0.6.0
 */