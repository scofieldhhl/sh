package io.xlink.wifi.sdk.g;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.c.d;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.util.MyLog;
import java.io.IOException;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class a
{
  private static a d;
  public Boolean a;
  private Thread b;
  private final BlockingQueue<g> c = new ArrayBlockingQueue(500, true);
  private XlinkTcpService e;
  private OutputStream f;
  private XlinkUdpService g;
  private DatagramSocket h;

  public static a a()
  {
    if (d == null)
      d = new a();
    return d;
  }

  private void a(String paramString)
  {
    MyLog.e(" Write", paramString);
  }

  // ERROR //
  private void e(g paramg)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 55	io/xlink/wifi/sdk/g/a:a	Ljava/lang/Boolean;
    //   4: invokevirtual 61	java/lang/Boolean:booleanValue	()Z
    //   7: ifne +29 -> 36
    //   10: aload_0
    //   11: getfield 32	io/xlink/wifi/sdk/g/a:c	Ljava/util/concurrent/BlockingQueue;
    //   14: aload_1
    //   15: invokeinterface 67 2 0
    //   20: aload_0
    //   21: getfield 32	io/xlink/wifi/sdk/g/a:c	Ljava/util/concurrent/BlockingQueue;
    //   24: astore_3
    //   25: aload_3
    //   26: monitorenter
    //   27: aload_0
    //   28: getfield 32	io/xlink/wifi/sdk/g/a:c	Ljava/util/concurrent/BlockingQueue;
    //   31: invokevirtual 70	java/lang/Object:notifyAll	()V
    //   34: aload_3
    //   35: monitorexit
    //   36: return
    //   37: astore_2
    //   38: aload_2
    //   39: invokevirtual 73	java/lang/InterruptedException:printStackTrace	()V
    //   42: return
    //   43: astore 4
    //   45: aload_3
    //   46: monitorexit
    //   47: aload 4
    //   49: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   10	20	37	java/lang/InterruptedException
    //   27	36	43	finally
    //   45	47	43	finally
  }

  private void f()
  {
    this.b = new Thread()
    {
      public void run()
      {
        a.a(a.this);
      }
    };
    this.b.setName("Packet Writer");
    this.b.setDaemon(true);
    this.a = Boolean.valueOf(false);
    this.b.start();
  }

  private g g()
  {
    g localg1 = null;
    while (!this.a.booleanValue())
    {
      localg1 = (g)this.c.poll();
      if (localg1 != null)
        break;
      try
      {
        synchronized (this.c)
        {
          this.c.wait();
        }
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
    g localg2 = null;
    if (localg1 != null)
      localg2 = localg1;
    return localg2;
  }

  private void h()
  {
    while (!this.a.booleanValue())
    {
      g localg = g();
      if (localg == null)
      {
        a("writePackets task null");
        continue;
      }
      if ((localg.g() != null) && (localg.f() != null))
      {
        e.a(localg.f(), localg);
        localg.e();
      }
      d locald = localg.b();
      if (locald == null)
      {
        a("writePackets EncodeBuffer null");
        continue;
      }
      if (locald.a() == 2)
      {
        c(localg);
        continue;
      }
      if (locald.a() != 1)
        continue;
      d(localg);
    }
    a("write therad logout done:" + this.a);
  }

  public void a(XlinkTcpService paramXlinkTcpService, OutputStream paramOutputStream)
  {
    this.e = paramXlinkTcpService;
    this.f = paramOutputStream;
  }

  public void a(XlinkUdpService paramXlinkUdpService, DatagramSocket paramDatagramSocket)
  {
    this.g = paramXlinkUdpService;
    this.h = paramDatagramSocket;
  }

  public void a(g paramg)
  {
    e(paramg);
  }

  public void b()
  {
    monitorenter;
    while (true)
    {
      try
      {
        if (this.b != null)
          continue;
        f();
        return;
        if (this.b.isAlive())
        {
          a("startup fail writerThread.isAlive() is true");
          continue;
        }
      }
      finally
      {
        monitorexit;
      }
      this.b.interrupt();
      this.b = null;
      f();
    }
  }

  public void b(g paramg)
  {
    io.xlink.wifi.sdk.util.b.b(new Runnable(paramg)
    {
      public void run()
      {
        a.a(a.this, this.a);
      }
    });
  }

  public void c(g paramg)
  {
    if (this.f == null)
      return;
    try
    {
      a("TCP send :" + paramg.b());
      this.f.write(paramg.b().b.a());
      return;
    }
    catch (IOException localIOException)
    {
      XlinkTcpService.a().a(true, -1, true);
    }
  }

  public boolean c()
  {
    return (this.b != null) && (this.b.isAlive());
  }

  public void d()
  {
    this.b = null;
    this.a = Boolean.valueOf(true);
    synchronized (this.c)
    {
      this.c.notifyAll();
      this.c.clear();
      return;
    }
  }

  public void d(g paramg)
  {
    if ((this.h == null) || (paramg == null));
    while (true)
    {
      return;
      d locald = paramg.b();
      DatagramPacket localDatagramPacket = new DatagramPacket(locald.b.a(), locald.b.a().length);
      localDatagramPacket.setAddress(locald.d());
      localDatagramPacket.setPort(locald.c());
      String str = "";
      if (paramg.a() != null)
        str = paramg.a().toString();
      a("udp sendData device:" + str + " len :" + localDatagramPacket.getLength() + " type:" + locald.e() + " addr:" + locald.d());
      try
      {
        this.h.send(localDatagramPacket);
        int i = locald.e();
        if (i == 8)
          return;
      }
      catch (IOException localIOException)
      {
        a("udp socket error");
        XlinkUdpService.b().a(true, -1);
      }
    }
  }

  public void e()
  {
    synchronized (this.c)
    {
      this.c.notifyAll();
      this.c.clear();
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.g.a
 * JD-Core Version:    0.6.0
 */