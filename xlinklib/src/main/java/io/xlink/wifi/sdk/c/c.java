package io.xlink.wifi.sdk.c;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;
import java.util.HashMap;

public class c extends a
{
  protected int a;
  protected int b;
  protected e c;
  private HashMap<String, c> d = new HashMap();
  private HashMap<String, c> e = new HashMap();
  private long f;
  private XDevice g;
  private int h;
  private int i;
  private ConnectDeviceListener j;
  private boolean k;
  private io.xlink.wifi.sdk.d.b l = new io.xlink.wifi.sdk.d.b()
  {
    public void a(XDevice paramXDevice, int paramInt)
    {
      e locale = new e(0);
      c.this.a("shakeHand callback code-->" + paramInt);
      switch (paramInt)
      {
      default:
        locale.b = paramInt;
        locale.a = paramXDevice;
        c.this.b(locale);
        c.this.a("handshake error code:" + paramInt);
      case 0:
        do
        {
          do
            return;
          while (!c.a(c.this));
          locale.a = paramXDevice;
          locale.a = io.xlink.wifi.sdk.a.a().a(0, locale.a);
          io.xlink.wifi.sdk.f.b.a().c(locale.a);
          c.this.a(locale);
        }
        while (!c.b(c.this));
        if (!paramXDevice.isValidId())
        {
          if (paramXDevice.getVersion() >= 3)
          {
            c.this.b = XlinkAgent.getInstance().subscribeDevice(paramXDevice, c.c(c.this), c.d(c.this));
            return;
          }
          c.this.b = XlinkAgent.getInstance().subscribeDevice(paramXDevice, c.e(c.this), c.d(c.this));
          return;
        }
        c.this.b = io.xlink.wifi.sdk.a.a().a(paramXDevice, c.f(c.this));
        return;
      case 2:
        locale.b = 102;
        locale.a = paramXDevice;
        c.this.b(locale);
        return;
      case -100:
      }
      locale.b = 200;
      locale.a = paramXDevice;
      c.this.b(locale);
    }
  };
  private SubscribeDeviceListener m = new SubscribeDeviceListener()
  {
    public void onSubscribeDevice(XDevice paramXDevice, int paramInt)
    {
      e locale = new e(paramInt);
      locale.a = paramXDevice;
      switch (paramInt)
      {
      case 1:
      default:
        c.this.a("subscribe fail code:" + paramInt);
        locale.b = paramInt;
        locale.a = paramXDevice;
        c.this.c(locale);
      case 0:
        do
          return;
        while (!c.a(c.this));
        if (paramXDevice.getDeviceId() == 0)
          locale.b = 104;
        while (true)
        {
          c.this.a(locale);
          return;
          locale.b = 1;
          locale.a = io.xlink.wifi.sdk.a.a().a(1, locale.a);
          io.xlink.wifi.sdk.f.b.a().c(locale.a);
          XlinkAgent.getInstance().sendProbe(locale.a);
        }
      case 2:
        locale.b = 102;
        locale.a = paramXDevice;
        c.this.c(locale);
        return;
      case 3:
      }
      locale.b = 5;
      locale.a = paramXDevice;
      c.this.c(locale);
    }
  };
  private io.xlink.wifi.sdk.d.a n = new io.xlink.wifi.sdk.d.a()
  {
    public void onResponse(e parame)
    {
      switch (parame.b)
      {
      default:
        c.this.a("cloud probe error code:" + parame.b);
        parame.b = 110;
        c.this.c(parame);
      case 0:
        do
        {
          return;
          c.this.a("cloud probe succeed  device state online ");
        }
        while (!c.a(c.this));
        parame.b = 1;
        parame.a = io.xlink.wifi.sdk.a.a().a(1, parame.a);
        io.xlink.wifi.sdk.f.b.a().c(parame.a);
        c.this.a(parame);
        return;
      case 5:
        c.this.a("cloud probe device not subscribe " + parame.a.getDeviceId());
        if (c.g(c.this).getVersion() >= 3);
        for (int i = XlinkAgent.getInstance().subscribeDevice(parame.a, c.c(c.this), c.d(c.this)); i < 0; i = XlinkAgent.getInstance().subscribeDevice(parame.a, c.e(c.this), c.d(c.this)))
        {
          parame.b = 200;
          c.this.c(parame);
          return;
        }
      case -100:
        c.this.a("cloud probe packet timeout");
        parame.b = 200;
        c.this.c(parame);
        return;
      case 2:
        parame.b = 102;
        c.this.c(parame);
        return;
      case 3:
      }
      parame.b = 109;
      c.this.c(parame);
    }
  };
  private io.xlink.wifi.sdk.d.a o = new io.xlink.wifi.sdk.d.a()
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
          c.this.a("call handshakeWithDevice fail code:" + i);
          parame.a = c.g(c.this);
          parame.b = 200;
          c.this.b(parame);
        }
        return;
        c.this.a("scan by mac succeed :" + parame.a.getAddress());
        i = io.xlink.wifi.sdk.a.a().a(parame.a, c.e(c.this), c.h(c.this), io.xlink.wifi.sdk.e.a.n);
        continue;
        c.this.a("scan timeout");
        i = io.xlink.wifi.sdk.a.a().a(c.g(c.this), c.e(c.this), c.h(c.this), io.xlink.wifi.sdk.e.a.n);
      }
    }
  };

  public c(XDevice paramXDevice, int paramInt1, int paramInt2, ConnectDeviceListener paramConnectDeviceListener)
  {
    this.h = paramInt1;
    this.i = paramInt2;
    this.j = paramConnectDeviceListener;
    this.g = paramXDevice;
    this.f = System.currentTimeMillis();
    this.a = b();
    this.b = this.a;
  }

  public c(XDevice paramXDevice, int paramInt, ConnectDeviceListener paramConnectDeviceListener)
  {
    this.h = paramInt;
    this.i = paramInt;
    this.j = paramConnectDeviceListener;
    this.g = paramXDevice;
    this.f = System.currentTimeMillis();
    this.a = b();
    this.b = this.a;
  }

  private void c()
  {
    if (this.a == 0)
      this.e.put(this.g.getMacAddress(), this);
  }

  private void d()
  {
    if (this.b == 0)
      this.d.put(this.g.getMacAddress(), this);
  }

  private boolean e()
  {
    return (this.d.containsKey(this.g.getMacAddress())) || (this.e.containsKey(this.g.getMacAddress()));
  }

  public int a()
  {
    if (this.a == 0)
    {
      if (((!XlinkUdpService.a()) || (!io.xlink.wifi.sdk.util.b.e())) && (!XlinkTcpService.c()))
        break label276;
      if ((!XlinkUdpService.a()) || (!io.xlink.wifi.sdk.util.b.e()))
        break label176;
      this.a = XlinkAgent.scanByMac(this.g, this.o);
      c();
      if (!this.k)
      {
        if (this.g.isValidId())
          break label151;
        if (this.g.getVersion() < 3)
          break label126;
        this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.i, this.m);
        d();
      }
    }
    while (true)
    {
      if ((this.a != 0) && (this.b != 0))
        break label291;
      return 0;
      label126: this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.m);
      break;
      label151: this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.n);
      d();
      continue;
      label176: if (!this.g.isValidId())
      {
        if (this.g.getVersion() >= 3);
        for (this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.i, this.m); ; this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.m))
        {
          d();
          break;
        }
      }
      this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.n);
      d();
      continue;
      label276: this.a = -4;
      this.b = -4;
    }
    label291: return this.b;
  }

  public void a(e parame)
  {
    if ((this.d.containsKey(this.g.getMacAddress())) || (this.e.containsKey(this.g.getMacAddress())))
    {
      this.d.remove(this.g.getMacAddress());
      this.e.remove(this.g.getMacAddress());
      this.j.onResponse(parame);
    }
  }

  public void a(boolean paramBoolean)
  {
    this.k = paramBoolean;
  }

  public int b()
  {
    if ((this.h < 0) || (this.h > 999999999))
    {
      this.a = -8;
      return -8;
    }
    if (this.j == null)
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
    return 0;
  }

  public void b(e parame)
  {
    if ((this.d.containsKey(this.g.getMacAddress())) || (this.e.containsKey(this.g.getMacAddress())))
    {
      this.e.remove(this.g.getMacAddress());
      if (!this.d.containsKey(this.g.getMacAddress()))
      {
        if (!this.k)
          break label169;
        if (this.g.isValidId())
          break label146;
        if (this.g.getVersion() < 3)
          break label121;
      }
    }
    label121: for (this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.i, this.m); ; this.b = XlinkAgent.getInstance().subscribeDevice(this.g, this.h, this.m))
    {
      d();
      return;
    }
    label146: this.b = io.xlink.wifi.sdk.a.a().a(this.g, this.n);
    d();
    return;
    label169: if (this.c != null)
    {
      this.j.onResponse(this.c);
      return;
    }
    this.j.onResponse(parame);
  }

  public void c(e parame)
  {
    if ((this.d.containsKey(this.g.getMacAddress())) || (this.e.containsKey(this.g.getMacAddress())))
    {
      this.d.remove(this.g.getMacAddress());
      if (!this.e.containsKey(this.g.getMacAddress()))
        this.j.onResponse(parame);
    }
    else
    {
      return;
    }
    this.c = parame;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.c.c
 * JD-Core Version:    0.6.0
 */