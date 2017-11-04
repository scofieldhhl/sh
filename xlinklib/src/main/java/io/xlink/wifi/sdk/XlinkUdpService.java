package io.xlink.wifi.sdk;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.d.c;
import io.xlink.wifi.sdk.util.MyLog;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public final class XlinkUdpService extends Service
{
  public static boolean b;
  public static boolean c;
  private static String g = io.xlink.wifi.sdk.util.b.a.getPackageName() + "-upd-keep";
  private static XlinkUdpService h;
  public io.xlink.wifi.sdk.h.a a;
  public long d;
  public Timer e;
  private DatagramSocket f;
  private boolean i = false;
  private boolean j = false;
  private long k;
  private TimerTask l;
  private BroadcastReceiver m = new BroadcastReceiver()
  {
    public void onReceive(Context paramContext, Intent paramIntent)
    {
      String str = paramIntent.getAction();
      MyLog.e("UDPService", "UDP mReceiver " + new Date() + "  " + System.currentTimeMillis());
      if (str.equals(XlinkUdpService.g()))
        io.xlink.wifi.sdk.f.b.a().d();
    }
  };
  private final String n = "UDPService";

  static
  {
    b = false;
    c = false;
  }

  public static boolean a()
  {
    if (b() == null)
      return false;
    return b().i;
  }

  public static XlinkUdpService b()
  {
    return h;
  }

  private void h()
  {
    if ((b) || (this.i))
    {
      a("upd connecting bind ...return ");
      return;
    }
    b = true;
    io.xlink.wifi.sdk.util.b.b(new Runnable()
    {
      public void run()
      {
        try
        {
          XlinkUdpService.this.a("bind udp ...");
          XlinkUdpService.a(XlinkUdpService.this, new DatagramSocket(null));
          XlinkUdpService.a(XlinkUdpService.this).setBroadcast(true);
          XlinkUdpService.a(XlinkUdpService.this).bind(new InetSocketAddress(0));
          XlinkUdpService.this.f();
          io.xlink.wifi.sdk.e.a.h = XlinkUdpService.a(XlinkUdpService.this).getLocalPort();
          XlinkUdpService.this.a("bind udp prot:" + io.xlink.wifi.sdk.e.a.h);
          return;
        }
        catch (SocketException localSocketException)
        {
          localSocketException.printStackTrace();
          XlinkUdpService.this.a("bind udp  fail ");
          c.a(2, -1);
          return;
        }
        finally
        {
          XlinkUdpService.b = false;
        }
        throw localObject;
      }
    });
  }

  public void a(g paramg)
  {
    io.xlink.wifi.sdk.g.a.a().b(paramg);
  }

  public void a(String paramString)
  {
    MyLog.e("UDPService", paramString);
  }

  public void a(boolean paramBoolean, int paramInt)
  {
    if (this.i)
    {
      this.i = false;
      if (this.f != null)
        this.f.close();
      if (this.a != null)
        this.a.c();
      if (paramBoolean)
      {
        a("upd bind close");
        c.a(3, paramInt);
      }
    }
  }

  public boolean c()
  {
    if (System.currentTimeMillis() - this.k > 15000L)
      this.j = false;
    return this.j;
  }

  public void d()
  {
    if (c())
      return;
    this.j = true;
    MyLog.e("UDPService", "local start KeepAlive ");
    this.d = System.currentTimeMillis();
    if (this.l != null)
    {
      this.l.cancel();
      this.l = null;
    }
    this.k = System.currentTimeMillis();
    this.l = new TimerTask()
    {
      public void run()
      {
        io.xlink.wifi.sdk.f.b.a().d();
        XlinkUdpService.a(XlinkUdpService.this, System.currentTimeMillis());
      }
    };
    this.e.schedule(this.l, 10000L, 10000L);
  }

  public void e()
  {
    if (this.l != null)
    {
      this.l.cancel();
      this.l = null;
    }
  }

  public void f()
  {
    if (!io.xlink.wifi.sdk.g.a.a().c())
      io.xlink.wifi.sdk.g.a.a().b();
    io.xlink.wifi.sdk.g.a.a().a(this, this.f);
    this.a = new io.xlink.wifi.sdk.h.a(h, this.f);
    this.a.b();
    this.i = true;
    a("udp bind succeed");
    c.a(1, 0);
  }

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    a("UPD service onCreate");
    h = this;
    this.e = new Timer();
  }

  public void onDestroy()
  {
    a(true, -2);
    if (this.m != null);
    try
    {
      unregisterReceiver(this.m);
      label22: this.e.cancel();
      this.e = null;
      a("upd service onDestroy");
      if (!c)
      {
        startService(new Intent(this, XlinkUdpService.class));
        a("upd RestartService... on onDestroy");
      }
      super.onDestroy();
      return;
    }
    catch (Exception localException)
    {
      break label22;
    }
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    a("UdpService onStartCommand connected:" + this.i);
    if (!this.i)
      h();
    return 1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.XlinkUdpService
 * JD-Core Version:    0.6.0
 */