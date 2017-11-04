package io.xlink.wifi.sdk.b;

import java.net.InetAddress;
import java.net.SocketAddress;

public class a
{
  public int a;
  private b b;
  private io.xlink.wifi.sdk.a.a c;
  private InetAddress d;
  private SocketAddress e;

  public a(io.xlink.wifi.sdk.a.a parama, b paramb)
  {
    this.c = parama;
    this.b = paramb;
  }

  public InetAddress a()
  {
    return this.d;
  }

  public void a(InetAddress paramInetAddress)
  {
    this.d = paramInetAddress;
  }

  public void a(SocketAddress paramSocketAddress)
  {
    this.e = paramSocketAddress;
  }

  public io.xlink.wifi.sdk.a.a b()
  {
    return this.c;
  }

  public int c()
  {
    return this.b.d();
  }

  public b d()
  {
    return this.b;
  }

  public SocketAddress e()
  {
    return this.e;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    if (this.d != null)
    {
      localStringBuffer.append("address :");
      localStringBuffer.append(this.d + " port" + this.a);
    }
    localStringBuffer.append(" length : ");
    localStringBuffer.append(this.b.a());
    localStringBuffer.append(" type:");
    localStringBuffer.append(this.b.d());
    return localStringBuffer.toString();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.b.a
 * JD-Core Version:    0.6.0
 */