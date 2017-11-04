package io.xlink.wifi.sdk.g;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.c.d;
import io.xlink.wifi.sdk.c.f;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.SetDataPointListener;
import io.xlink.wifi.sdk.listener.SetDeviceAccessKeyListener;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;
import io.xlink.wifi.sdk.util.b;
import java.util.List;

public class c
{
  private static c a;

  public static c a()
  {
    if (a == null)
      a = new c();
    return a;
  }

  public int a(int paramInt1, byte paramByte, byte[] paramArrayOfByte, SendPipeListener paramSendPipeListener, int paramInt2)
  {
    d locald = f.a().a(paramInt1, paramByte, paramArrayOfByte, paramSendPipeListener, paramInt2);
    g localg = new g(locald, paramSendPipeListener);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public int a(int paramInt1, short paramShort, byte paramByte, int paramInt2)
  {
    d locald = f.a().a(paramInt1, paramShort, paramByte, paramInt2);
    g localg = new g(locald, null);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte, SendPipeListener paramSendPipeListener, int paramInt)
  {
    d locald = f.a().a(paramXDevice, paramByte, paramArrayOfByte, paramSendPipeListener, paramInt);
    g localg = new g(locald, paramSendPipeListener, paramInt);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, String paramString1, String paramString2, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener, int paramInt)
  {
    d locald = f.a().b(paramXDevice, paramString1, paramString2);
    g localg = new g(locald, paramSetDeviceAccessKeyListener, paramInt);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, List<DataPoint> paramList, SetDataPointListener paramSetDataPointListener)
  {
    d locald = f.a().b(paramXDevice, paramList);
    g localg = new g(locald, paramSetDataPointListener);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, byte[] paramArrayOfByte, io.xlink.wifi.sdk.d.a parama)
  {
    d locald = f.a().b(paramXDevice, paramArrayOfByte);
    g localg = new g(locald, parama);
    XlinkTcpService.a().a(localg);
    return locald.g();
  }

  public void a(int paramInt)
  {
    b.b(new Runnable(new g(f.a().a((byte)paramInt)))
    {
      public void run()
      {
        a.a().c(this.a);
      }
    });
  }

  public void a(int paramInt1, String paramString, io.xlink.wifi.sdk.d.a parama, int paramInt2)
  {
    if ((paramString == null) || ("".equals(paramString)))
      return;
    d locald = f.a().b(paramInt1, paramString);
    locald.a("1");
    g localg = new g(locald, parama, paramInt2);
    a.a().a(localg);
  }

  public void a(XDevice paramXDevice, int paramInt1, io.xlink.wifi.sdk.d.a parama, int paramInt2)
  {
    g localg = new g(f.a().e(paramXDevice, paramInt1), parama, paramInt2);
    XlinkTcpService.a().a(localg);
  }

  public void a(XDevice paramXDevice, int paramInt, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    g localg = new g(f.a().a(paramXDevice, paramInt, true, paramSubscribeDeviceListener), paramSubscribeDeviceListener);
    XlinkTcpService.a().a(localg);
  }

  public void a(XDevice paramXDevice, String paramString, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    g localg = new g(f.a().a(paramXDevice, paramString, true, paramSubscribeDeviceListener), paramSubscribeDeviceListener);
    XlinkTcpService.a().a(localg);
  }

  public g b()
  {
    return new g(f.a().b());
  }

  public void b(XDevice paramXDevice, int paramInt, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    g localg = new g(f.a().a(paramXDevice, paramInt, false, paramSubscribeDeviceListener), paramSubscribeDeviceListener);
    XlinkTcpService.a().a(localg);
  }

  public void b(XDevice paramXDevice, String paramString, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    g localg = new g(f.a().a(paramXDevice, paramString, false, paramSubscribeDeviceListener), paramSubscribeDeviceListener);
    XlinkTcpService.a().a(localg);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.g.c
 * JD-Core Version:    0.6.0
 */