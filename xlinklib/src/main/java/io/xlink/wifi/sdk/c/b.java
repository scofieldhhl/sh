package io.xlink.wifi.sdk.c;

import android.text.TextUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;
import java.util.HashMap;

public class b extends a
{
  private static final HashMap<String, b> d = new HashMap();
  private static final HashMap<String, b> e = new HashMap();
  protected int a;
  protected int b;
  protected e c;
  private long f;
  private XDevice g;
  private String h;
  private ConnectDeviceListener i;
  private boolean j;
  private io.xlink.wifi.sdk.d.b k = new io.xlink.wifi.sdk.d.b()
  {
    public void a(XDevice paramXDevice, int paramInt)
    {
      e locale = new e(0);
      b.this.a("shakehand callback code " + paramInt);
      switch (paramInt)
      {
      default:
        locale.b = paramInt;
        locale.a = paramXDevice;
        b.this.b(locale);
        b.this.a("handshake error code:" + paramInt);
      case 0:
        do
        {
          return;
          if (!b.a(b.this))
            continue;
          locale.a = paramXDevice;
          locale.a = io.xlink.wifi.sdk.a.a().a(0, locale.a);
          io.xlink.wifi.sdk.f.b.a().c(locale.a);
          b.this.a(locale);
        }
        while (!b.b(b.this));
        if (!paramXDevice.isValidId())
        {
          b.this.b = XlinkAgent.getInstance().subscribeDevice(paramXDevice, b.c(b.this), b.d(b.this));
          b.e(b.this);
          return;
        }
        b.this.b = io.xlink.wifi.sdk.a.a().a(paramXDevice, b.f(b.this));
        b.e(b.this);
        return;
      case 2:
        locale.b = 102;
        locale.a = paramXDevice;
        b.this.b(locale);
        return;
      case -100:
      }
      locale.b = 200;
      locale.a = paramXDevice;
      b.this.b(locale);
    }
  };
  private SubscribeDeviceListener l = new SubscribeDeviceListener()
  {
    public void onSubscribeDevice(XDevice paramXDevice, int paramInt)
    {
      e locale = new e(paramInt);
      locale.a = paramXDevice;
      switch (paramInt)
      {
      case 1:
      default:
        b.this.a("subscribe fail code:" + paramInt);
        locale.b = paramInt;
        locale.a = paramXDevice;
        b.this.c(locale);
      case 0:
        do
          return;
        while (!b.a(b.this));
        if (paramXDevice.getDeviceId() == 0)
          locale.b = 104;
        while (true)
        {
          b.this.a(locale);
          return;
          locale.b = 1;
          locale.a = io.xlink.wifi.sdk.a.a().a(1, locale.a);
          io.xlink.wifi.sdk.f.b.a().c(locale.a);
          XlinkAgent.getInstance().sendProbe(locale.a);
        }
      case 2:
        locale.b = 102;
        locale.a = paramXDevice;
        b.this.c(locale);
        return;
      case 3:
      }
      locale.b = 5;
      locale.a = paramXDevice;
      b.this.c(locale);
    }
  };
  private io.xlink.wifi.sdk.d.a m = new io.xlink.wifi.sdk.d.a()
  {
    public void onResponse(e parame)
    {
      switch (parame.b)
      {
      default:
        b.this.a("cloud probe error code:" + parame.b);
        parame.b = 110;
        b.this.c(parame);
      case 0:
      case 5:
        do
        {
          do
          {
            return;
            b.this.a("cloud probe succeed  device state online ");
          }
          while (!b.a(b.this));
          parame.b = 1;
          parame.a = io.xlink.wifi.sdk.a.a().a(1, parame.a);
          io.xlink.wifi.sdk.f.b.a().c(parame.a);
          b.this.a(parame);
          return;
          b.this.a("cloud probe device not subscribe " + parame.a.getDeviceId());
        }
        while (XlinkAgent.getInstance().subscribeDevice(parame.a, b.c(b.this), b.d(b.this)) >= 0);
        parame.b = 200;
        b.this.c(parame);
        return;
      case -100:
        b.this.a("cloud probe packet timeout");
        parame.b = 200;
        b.this.c(parame);
        return;
      case 2:
        parame.b = 102;
        b.this.c(parame);
        return;
      case 3:
      }
      parame.b = 109;
      b.this.c(parame);
    }
  };
  private io.xlink.wifi.sdk.d.a n = new io.xlink.wifi.sdk.d.a()
  {
    public void onResponse(e parame)
    {
      int i = -1;
      switch (parame.b)
      {
      default:
      case 0:
      case -100:
      }
      while (true)
      {
        if (i < 0)
        {
          b.this.a("call handshakeWithDevice fail code:" + i);
          parame.a = b.h(b.this);
          parame.b = 200;
          b.this.b(parame);
        }
        return;
        b.this.a("scan by mac succeed :" + parame.a.getAddress());
        i = io.xlink.wifi.sdk.a.a().a(parame.a, b.c(b.this), b.g(b.this), io.xlink.wifi.sdk.e.a.n);
        continue;
        b.this.a("scan time out");
        i = io.xlink.wifi.sdk.a.a().a(b.h(b.this), b.c(b.this), b.g(b.this), io.xlink.wifi.sdk.e.a.n);
      }
    }
  };

  public b(XDevice paramXDevice, String paramString, ConnectDeviceListener paramConnectDeviceListener)
  {
    this.h = paramString;
    this.i = paramConnectDeviceListener;
    this.g = paramXDevice;
    this.f = System.currentTimeMillis();
    this.a = b();
  }

  private void c()
  {
    if (this.a == 0)
      e.put(this.g.getMacAddress(), this);
  }

  private void d()
  {
    if (this.b == 0)
      d.put(this.g.getMacAddress(), this);
  }

  private boolean e()
  {
    return (d.containsKey(this.g.getMacAddress())) || (e.containsKey(this.g.getMacAddress()));
  }

  public int a()
  {
    if (this.a == 0)
    {
      if (((!XlinkUdpService.a()) || (!io.xlink.wifi.sdk.util.b.e())) && (!XlinkTcpService.c()))
        break label204;
      if ((!XlinkUdpService.a()) || (!io.xlink.wifi.sdk.util.b.e()))
        break label140;
      this.a = XlinkAgent.scanByMac(this.g, this.n);
      c();
      if (!this.j)
      {
        if (this.g.isValidId())
          break label115;
        this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.l);
        d();
      }
    }
    while ((this.a == 0) || (this.b == 0))
    {
      return 0;
      label115: this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.m);
      d();
      continue;
      label140: if (!this.g.isValidId())
      {
        this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.l);
        d();
        continue;
      }
      this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.m);
      d();
      continue;
      label204: this.a = -4;
      this.b = -4;
    }
    return this.b;
  }

  public void a(e parame)
  {
    if ((d.containsKey(this.g.getMacAddress())) || (e.containsKey(this.g.getMacAddress())))
    {
      d.remove(this.g.getMacAddress());
      e.remove(this.g.getMacAddress());
      this.i.onResponse(parame);
    }
  }

  public void a(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public int b()
  {
    if ((TextUtils.isEmpty(this.h)) || (this.i == null))
    {
      this.a = -8;
      return -8;
    }
    if (!io.xlink.wifi.sdk.f.a.a().d())
    {
      this.a = -10;
      return -10;
    }
    if (io.xlink.wifi.sdk.f.b.a().a(this.g))
    {
      this.a = -3;
      return -3;
    }
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(this.g.getMacAddress());
    if (localXDevice == null)
    {
      this.a = -6;
      return -6;
    }
    this.g = localXDevice;
    this.g.setAuthkey(this.h);
    return 0;
  }

  public void b(e parame)
  {
    if ((d.containsKey(this.g.getMacAddress())) || (e.containsKey(this.g.getMacAddress())))
    {
      e.remove(this.g.getMacAddress());
      if (!d.containsKey(this.g.getMacAddress()))
      {
        if (!this.j)
          break label129;
        if (this.g.isValidId())
          break label106;
        this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.l);
        d();
      }
    }
    return;
    label106: this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.m);
    d();
    return;
    label129: if (this.c != null)
    {
      this.i.onResponse(this.c);
      return;
    }
    this.i.onResponse(parame);
  }

  public void c(e parame)
  {
    if ((d.containsKey(this.g.getMacAddress())) || (e.containsKey(this.g.getMacAddress())))
    {
      d.remove(this.g.getMacAddress());
      if (!e.containsKey(this.g.getMacAddress()))
        this.i.onResponse(parame);
    }
    else
    {
      return;
    }
    this.c = parame;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.c.b
 * JD-Core Version:    0.6.0
 */