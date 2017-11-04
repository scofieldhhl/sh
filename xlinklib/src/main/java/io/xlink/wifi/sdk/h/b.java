package io.xlink.wifi.sdk.h;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.c.d;
import io.xlink.wifi.sdk.c.f;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.d.a;
import io.xlink.wifi.sdk.listener.GetSubscribeKeyListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.SetDataPointListener;
import io.xlink.wifi.sdk.listener.SetDeviceAccessKeyListener;
import java.net.InetAddress;
import java.util.List;

public class b
{
  private static b a;

  public static b a()
  {
    if (a == null)
      a = new b();
    return a;
  }

  public int a(XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte, int paramInt, SendPipeListener paramSendPipeListener)
  {
    d locald = f.a().a(paramXDevice, paramByte, paramArrayOfByte);
    g localg = new g(locald, paramSendPipeListener, paramInt);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, int paramInt, GetSubscribeKeyListener paramGetSubscribeKeyListener)
  {
    d locald = f.a().c(paramXDevice, paramInt);
    g localg = new g(locald, paramGetSubscribeKeyListener);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, int paramInt, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    d locald = f.a().b(paramXDevice, paramInt);
    g localg = new g(locald, paramSetDeviceAccessKeyListener);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, String paramString1, String paramString2, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    d locald = f.a().a(paramXDevice, paramString1, paramString2);
    g localg = new g(locald, paramSetDeviceAccessKeyListener);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, List<DataPoint> paramList, SetDataPointListener paramSetDataPointListener)
  {
    d locald = f.a().a(paramXDevice, paramList);
    g localg = new g(locald, paramSetDataPointListener);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(XDevice paramXDevice, byte[] paramArrayOfByte, a parama)
  {
    d locald = f.a().a(paramXDevice, paramArrayOfByte);
    g localg = new g(locald, parama);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public int a(short paramShort, byte paramByte, InetAddress paramInetAddress)
  {
    d locald = f.a().a(paramShort, paramByte, paramInetAddress);
    g localg = new g(locald, null);
    XlinkUdpService.b().a(localg);
    return locald.g();
  }

  public void a(XDevice paramXDevice)
  {
    g localg = new g(f.a().a(paramXDevice));
    XlinkUdpService.b().a(localg);
  }

  public void a(XDevice paramXDevice, int paramInt1, io.xlink.wifi.sdk.d.b paramb, int paramInt2)
  {
    g localg = new g(f.a().a(paramXDevice, paramInt1), paramb, paramInt2);
    XlinkUdpService.b().a(localg);
  }

  public void a(XDevice paramXDevice, a parama)
  {
    d locald = f.a().d(paramXDevice, 1);
    g localg = new g(locald, parama, 2);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getMacAddress() + 1);
    XlinkUdpService.b().a(localg);
  }

  public void a(XDevice paramXDevice, String paramString, io.xlink.wifi.sdk.d.b paramb, int paramInt)
  {
    g localg = new g(f.a().a(paramXDevice, paramString), paramb, paramInt);
    XlinkUdpService.b().a(localg);
  }

  public void a(String paramString)
  {
    d locald = f.a().a(2, paramString);
    locald.a = true;
    g localg = new g(locald);
    XlinkUdpService.b().a(localg);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.h.b
 * JD-Core Version:    0.6.0
 */