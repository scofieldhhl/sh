package io.xlink.wifi.sdk;

import io.xlink.wifi.sdk.d.c;
import io.xlink.wifi.sdk.e.a;
import io.xlink.wifi.sdk.util.MyLog;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.SocketAddress;

public final class XDevice
  implements Serializable
{
  public int a;
  private int b = a.i;
  private int c;
  private long d;
  private int e = -1;
  private InetAddress f;
  private byte g = 0;
  private String h;
  private int i;
  private int j;
  private String k;
  private String l;
  private byte m = 0;
  private int n = 0;
  private int o = -1;
  private boolean p = false;
  private String q;
  private int r = -1;
  private int s = -1;
  private int t;
  private SocketAddress u;
  private int v = 0;
  private long w = 0L;
  private long x = 0L;

  protected XDevice(String paramString)
  {
    this.h = paramString;
    this.b = a.i;
    this.c = (1000 * this.b);
  }

  private void c(String paramString)
  {
    MyLog.e("Device", paramString);
  }

  private boolean c()
  {
    if (System.currentTimeMillis() - this.d > 1000 * this.b)
      b(-1);
    return this.e > 0;
  }

  private boolean d()
  {
    if (this.v > 3)
    {
      c("device mac :" + getMacAddress() + "count>3----offline");
      return true;
    }
    if (System.currentTimeMillis() - this.d > this.c)
    {
      c("mac :" + getMacAddress() + "sessionId :" + this.e + " offline");
      return true;
    }
    return false;
  }

  private void e()
  {
    this.d = System.currentTimeMillis();
    b(0);
  }

  protected void a(int paramInt)
  {
    this.o = paramInt;
  }

  protected void a(String paramString)
  {
    this.l = paramString;
  }

  protected void a(InetAddress paramInetAddress)
  {
    this.f = paramInetAddress;
  }

  protected void a(boolean paramBoolean)
  {
    this.p = paramBoolean;
  }

  protected void a(byte[] paramArrayOfByte)
  {
    this.h = io.xlink.wifi.sdk.util.b.d(paramArrayOfByte);
  }

  protected boolean a()
  {
    long l1 = System.currentTimeMillis() - this.d;
    if (this.w == 0L)
      this.w = (1000 * (-1 + this.b / 4));
    if (l1 >= this.w)
    {
      this.d = System.currentTimeMillis();
      return true;
    }
    return false;
  }

  protected void b()
  {
    this.v = 0;
    this.d = System.currentTimeMillis();
  }

  protected void b(int paramInt)
  {
    this.d = System.currentTimeMillis();
    if (this.e == paramInt);
    while (true)
    {
      return;
      this.e = paramInt;
      if (paramInt > 0)
        break;
      if (this.o != 0)
        continue;
      c.a(-2, this);
      a(-1);
      return;
    }
    b();
    a(0);
  }

  protected void b(String paramString)
  {
    this.h = paramString;
  }

  protected void c(int paramInt)
  {
    this.t = paramInt;
  }

  public void checkKeepAlive()
  {
    if (d())
      e();
    do
      return;
    while (!a());
    io.xlink.wifi.sdk.h.b.a().a(this);
    this.v = (1 + this.v);
  }

  protected void d(int paramInt)
  {
    this.i = paramInt;
  }

  protected void e(int paramInt)
  {
    this.j = paramInt;
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof XDevice))
    {
      XDevice localXDevice = (XDevice)paramObject;
      if ((localXDevice.getMacAddress() != null) && (getMacAddress() != null))
        return localXDevice.getMacAddress().equals(getMacAddress());
    }
    return super.equals(paramObject);
  }

  public int getAccessKey()
  {
    return this.r;
  }

  public InetAddress getAddress()
  {
    return this.f;
  }

  public String getAuthkey()
  {
    return this.q;
  }

  public int getDevcieConnectStates()
  {
    if ((this.o == 0) && (!c()))
      this.o = -1;
    return this.o;
  }

  public int getDeviceId()
  {
    return this.j;
  }

  public String getDeviceName()
  {
    return this.k;
  }

  public SocketAddress getLocalAddress()
  {
    return this.u;
  }

  public String getMacAddress()
  {
    return this.h;
  }

  public byte getMcuHardVersion()
  {
    return this.m;
  }

  public int getMcuSoftVersion()
  {
    return this.n;
  }

  public int getPort()
  {
    return this.i;
  }

  public String getProductId()
  {
    return this.l;
  }

  public int getProductType()
  {
    return this.t;
  }

  public int getSessionId()
  {
    return this.e;
  }

  public int getSubKey()
  {
    return this.s;
  }

  public int getTimeout()
  {
    return this.b;
  }

  public byte getVersion()
  {
    return this.g;
  }

  public int hashCode()
  {
    if (getMacAddress() != null)
      return getMacAddress().hashCode();
    return super.hashCode();
  }

  public boolean isInit()
  {
    return this.p;
  }

  public boolean isLAN()
  {
    return getDevcieConnectStates() == 0;
  }

  public boolean isValidId()
  {
    return this.j > 0;
  }

  public void setAccessKey(int paramInt)
  {
    this.r = paramInt;
  }

  public void setAuthkey(String paramString)
  {
    this.q = paramString;
  }

  public void setDeviceName(String paramString)
  {
    this.k = paramString;
  }

  public void setLocalAddress(SocketAddress paramSocketAddress)
  {
    this.u = paramSocketAddress;
  }

  public void setMcuHardVersion(byte paramByte)
  {
    this.m = paramByte;
  }

  public void setMcuSoftVersion(int paramInt)
  {
    this.n = paramInt;
  }

  public void setSubKey(int paramInt)
  {
    this.s = paramInt;
  }

  public void setVersion(byte paramByte)
  {
    this.g = paramByte;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MacAddress:" + getMacAddress());
    localStringBuilder.append(" Did:" + getDeviceId());
    localStringBuilder.append(" IP:" + getAddress());
    localStringBuilder.append(" Ssid :" + getSessionId());
    return localStringBuilder.toString();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.XDevice
 * JD-Core Version:    0.6.0
 */