package io.xlink.wifi.sdk.f;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.util.MyLog;

public class a
{
  private static a c;
  boolean a = true;
  BroadcastReceiver b = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      if (TextUtils.equals(paramIntent.getAction(), "android.net.conn.CONNECTIVITY_CHANGE"))
      {
        NetworkInfo localNetworkInfo = a.this.c();
        if ((localNetworkInfo != null) && (localNetworkInfo.isAvailable()))
        {
          MyLog.e("NetWrok", "NetworkInfo：：" + localNetworkInfo.getType());
          switch (localNetworkInfo.getType())
          {
          default:
          case 1:
          case 0:
          }
        }
      }
      while (true)
      {
        if (!XlinkTcpService.c())
          XlinkAgent.getInstance().login(XlinkTcpService.a, XlinkTcpService.b);
        b.a().e();
        return;
        if ((XlinkUdpService.a()) || (XlinkUdpService.b() == null))
          continue;
        Intent localIntent = new Intent(io.xlink.wifi.sdk.util.b.a, XlinkUdpService.class);
        io.xlink.wifi.sdk.util.b.a.startService(localIntent);
        continue;
        b.a().e();
        if (XlinkUdpService.b() == null)
          continue;
        XlinkUdpService.b().a(false, 0);
      }
    }
  };

  public static a a()
  {
    if (c == null)
      c = new a();
    return c;
  }

  private void f()
  {
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
    localIntentFilter.setPriority(1000);
    io.xlink.wifi.sdk.util.b.a.registerReceiver(this.b, localIntentFilter);
  }

  public void b()
  {
    if (!this.a)
      return;
    this.a = false;
    f();
  }

  public NetworkInfo c()
  {
    return ((ConnectivityManager)io.xlink.wifi.sdk.util.b.a.getSystemService("connectivity")).getActiveNetworkInfo();
  }

  public boolean d()
  {
    NetworkInfo localNetworkInfo = c();
    if (localNetworkInfo == null)
      return false;
    return localNetworkInfo.isAvailable();
  }

  public void e()
  {
    if (!this.a)
    {
      this.a = true;
      io.xlink.wifi.sdk.util.b.a.unregisterReceiver(this.b);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.f.a
 * JD-Core Version:    0.6.0
 */