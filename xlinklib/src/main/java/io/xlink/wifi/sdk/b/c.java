package io.xlink.wifi.sdk.b;

import android.text.TextUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.XlinkTcpService;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.c.d;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.listener.GetSubscribeKeyListener;
import io.xlink.wifi.sdk.util.MyLog;
import java.net.InetAddress;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.List;

public class c
{
  private static String a = "Packet Decoder";
  private GetSubscribeKeyListener b = new GetSubscribeKeyListener()
  {
    public void onGetSubscribekey(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
      paramXDevice.setSubKey(paramInt2);
      io.xlink.wifi.sdk.f.b.a().c(paramXDevice);
      io.xlink.wifi.sdk.d.c.a(paramXDevice);
    }
  };

  public static c a()
  {
    return a.a();
  }

  private List<DataPoint> a(byte[] paramArrayOfByte)
  {
    io.xlink.wifi.sdk.a.a locala = new io.xlink.wifi.sdk.a.a(paramArrayOfByte, 0);
    ArrayList localArrayList = new ArrayList();
    label18: if (locala.b().length - locala.c() >= 3);
    while (true)
    {
      try
      {
        int i = locala.d();
        int j = locala.g();
        int k = 0xF & j >> 12;
        byte[] arrayOfByte = locala.a(j & 0xFFF);
        switch (k)
        {
        case 0:
          DataPoint localDataPoint = DataPoint.parseDataPoint(i, m, arrayOfByte);
          if (localDataPoint == null)
            break label18;
          localArrayList.add(localDataPoint);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
        case 9:
        case 10:
        }
      }
      catch (Exception localException)
      {
        a("parse datapoint error!");
      }
      return localArrayList;
      int m = 2;
      continue;
      m = 3;
      continue;
      m = 4;
      continue;
      m = 5;
      continue;
      m = 6;
      continue;
      m = 7;
      continue;
      m = 0;
    }
  }

  private void a(String paramString)
  {
    MyLog.e(a, paramString);
  }

  private void b(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.f();
    int j = locala.g();
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().a(i);
    g localg = e.a(j);
    int k = locala.d();
    if (localg == null)
      a("received probe packets  no task ");
    while (true)
    {
      if ((k == 0) && (localXDevice != null))
      {
        byte b1 = locala.d();
        if (io.xlink.wifi.sdk.util.b.a(b1, 0))
          localXDevice.setDeviceName(io.xlink.wifi.sdk.util.b.c(locala.a(locala.g())));
        if ((io.xlink.wifi.sdk.util.b.a(b1, 1)) && (io.xlink.wifi.sdk.util.b.a(b1, 2)))
          io.xlink.wifi.sdk.d.c.a(localXDevice, a(locala.e()), 0);
      }
      return;
      localg.c();
      e locale = new e(k);
      locale.a = localg.a();
      localg.g().onResponse(locale);
    }
  }

  private void c(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    locala.f();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg != null)
    {
      localg.c();
      e locale = new e(j);
      locale.a = localg.a();
      locale.c = i;
      localg.g().onResponse(locale);
    }
  }

  private void d(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.f();
    int j = locala.g();
    int k = locala.d();
    a("parse subscription deviceid:" + i + "  code：" + k);
    g localg = e.b(j + "");
    if (localg != null)
    {
      localg.c();
      e locale = e.b(k);
      locale.a = localg.b().b();
      if ((i > 0) && (k == 0))
        locale.a = io.xlink.wifi.sdk.a.a().b(locale.a, i);
      localg.g().onResponse(locale);
      return;
    }
    a("error:subscription task is null");
  }

  private void e(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.f();
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().a(i);
    if (localXDevice == null)
    {
      a("cloud sync fail deviceID =" + i + " device not found");
      return;
    }
    locala.g();
    byte b1 = locala.d();
    if (io.xlink.wifi.sdk.util.b.a(b1, 0))
      localXDevice.setDeviceName(io.xlink.wifi.sdk.util.b.c(locala.a(locala.g())));
    if (io.xlink.wifi.sdk.util.b.a(b1, 2))
      io.xlink.wifi.sdk.d.c.a(localXDevice, a(locala.e()), 1);
    a("cloud sync   device :" + localXDevice.getMacAddress());
  }

  private void f(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    locala.f();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg != null)
    {
      localg.c();
      e locale = e.b(j);
      locale.a = localg.b().b();
      locale.c = i;
      localg.g().onResponse(locale);
    }
    a("outer set  code :" + j + ";messageId:" + i);
  }

  private void g(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.d();
    locala.d();
    g localg = e.b("1");
    if (localg != null)
    {
      localg.c();
      localg.g().onResponse(e.b(i));
    }
    a("parse Login response  code :" + i);
  }

  private void h(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    locala.f();
    int i = locala.g();
    int j = locala.d();
    g localg = e.b(i + "");
    if (localg != null)
    {
      localg.c();
      e locale = e.b(j);
      locale.a = localg.b().b();
      locale.c = i;
      localg.g().onResponse(locale);
    }
    a("receive send Pipe response code :" + j);
  }

  private void i(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.f();
    short s = locala.g();
    byte b1 = locala.d();
    byte[] arrayOfByte = locala.e();
    XDevice localXDevice1 = io.xlink.wifi.sdk.f.b.a().a(i);
    if (localXDevice1 == null)
    {
      XDevice localXDevice2 = io.xlink.wifi.sdk.a.a().a("");
      localXDevice1 = io.xlink.wifi.sdk.a.a().b(localXDevice2, i);
    }
    io.xlink.wifi.sdk.d.c.a(false, s, localXDevice1, b1, arrayOfByte);
    if ((XlinkTcpService.c()) && (XlinkAgent.getUseDefaultSyncResponse()))
      io.xlink.wifi.sdk.g.c.a().a(i, s, 0, io.xlink.wifi.sdk.e.a.m);
    a("receive push pipe1 packet  deviceId :" + i + " dataLength :" + arrayOfByte.length);
  }

  private void j(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.f();
    short s = locala.g();
    byte b1 = locala.d();
    byte[] arrayOfByte = locala.e();
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().a(i);
    if (localXDevice == null)
      return;
    io.xlink.wifi.sdk.d.c.a(true, s, localXDevice, b1, arrayOfByte);
    if ((XlinkTcpService.c()) && (XlinkAgent.getUseDefaultSyncResponse()))
      io.xlink.wifi.sdk.g.c.a().a(i, s, 0, io.xlink.wifi.sdk.e.a.m);
    a("receive push sync pipe2 packet  deviceId :" + i + " dataLength :" + arrayOfByte.length);
  }

  private void k(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    if (locala.d() == 10)
    {
      EventNotify localEventNotify = new EventNotify();
      localEventNotify.notyfyFlags = locala.d();
      localEventNotify.formId = locala.f();
      localEventNotify.messageId = locala.g();
      localEventNotify.messageType = locala.g();
      localEventNotify.notifyData = locala.e();
      io.xlink.wifi.sdk.d.c.a(localEventNotify);
      a("cloud event notify: " + localEventNotify.toString());
    }
  }

  private void l(a parama)
  {
    switch (parama.b().d())
    {
    default:
      return;
    case 3:
    }
    XlinkTcpService.a().a(true, 3, false);
  }

  private void m(a parama)
  {
    a("UDP read packet : " + parama.toString());
    if (((parama.c() != 1) || (parama.c() != 2) || (parama.c() != 9)) && (!XlinkUdpService.b().c()))
      XlinkUdpService.b().d();
    switch (parama.c())
    {
    case 6:
    case 10:
    case 12:
    default:
      a("Receive udp error type of package:" + parama);
      return;
    case 1:
      x(parama);
      return;
    case 2:
      w(parama);
      return;
    case 13:
      v(parama);
      return;
    case 3:
      s(parama);
      return;
    case 4:
      u(parama);
      return;
    case 5:
      t(parama);
      return;
    case 8:
      if (parama.d().e())
      {
        r(parama);
        return;
      }
      q(parama);
      return;
    case 9:
      p(parama);
      return;
    case 11:
      n(parama);
      return;
    case 7:
    }
    o(parama);
  }

  private void n(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg == null)
    {
      a("receive setAccessKey  task not found ,code:" + j);
      return;
    }
    localg.c();
    e locale = e.b(j);
    locale.a = localg.b().b();
    if (locale.a != null)
    {
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(locale.a.getMacAddress());
      if (localXDevice != null);
      for (locale.a = io.xlink.wifi.sdk.a.a().a(true, localXDevice); ; locale.a = io.xlink.wifi.sdk.a.a().a(true, locale.a))
      {
        locale.a.setLocalAddress(parama.e());
        locale.c = i;
        localg.g().onResponse(locale);
        return;
      }
    }
    a("error:set device accesskey fail device not found  。");
  }

  private void o(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.g();
    int j = locala.d();
    int k = 0;
    if (j == 0)
      k = locala.f();
    g localg = e.a(i);
    if (localg == null)
    {
      a("receive getSubKey  task not found ,code:" + j);
      return;
    }
    localg.c();
    e locale = e.b(j);
    locale.a = localg.b().b();
    if (locale.a != null)
    {
      XDevice localXDevice1 = io.xlink.wifi.sdk.f.b.a().b(locale.a.getMacAddress());
      if (localXDevice1 != null)
      {
        XDevice localXDevice2 = io.xlink.wifi.sdk.a.a().a(true, localXDevice1);
        localXDevice2.setSubKey(k);
        locale.a = localXDevice2;
      }
      while (true)
      {
        locale.c = i;
        localg.g().onResponse(locale);
        return;
        io.xlink.wifi.sdk.a.a().a(true, localXDevice1).setSubKey(k);
      }
    }
    a("error:set device getSubKey fail device not found  。");
  }

  private void p(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg == null)
    {
      a("receive setPw  task not found ,code:" + j);
      return;
    }
    localg.c();
    e locale = e.b(j);
    locale.a = localg.b().b();
    if (locale.a != null)
    {
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(locale.a.getMacAddress());
      if (localXDevice != null);
      for (locale.a = io.xlink.wifi.sdk.a.a().a(true, localXDevice); ; locale.a = io.xlink.wifi.sdk.a.a().a(true, locale.a))
      {
        locale.a.setLocalAddress(parama.e());
        locale.c = i;
        localg.g().onResponse(locale);
        return;
      }
    }
    a("error:set device pw fail device not found  。");
  }

  private void q(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = 6;
    if (parama.d().b() >= 3)
      i = locala.g();
    String str = io.xlink.wifi.sdk.util.b.d(locala.a(i));
    XDevice localXDevice1 = io.xlink.wifi.sdk.f.b.a().b(str);
    if (localXDevice1 == null)
      a("error udp push Pipe device not found mac:" + str);
    short s;
    XDevice localXDevice2;
    do
    {
      return;
      s = locala.g();
      byte b1 = locala.d();
      byte[] arrayOfByte = locala.e();
      localXDevice2 = io.xlink.wifi.sdk.a.a().a(0, localXDevice1);
      localXDevice2.setLocalAddress(parama.e());
      io.xlink.wifi.sdk.d.c.a(false, s, localXDevice2, b1, arrayOfByte);
    }
    while (!XlinkUdpService.a());
    io.xlink.wifi.sdk.h.b.a().a(s, 0, localXDevice2.getAddress());
  }

  private void r(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg != null)
    {
      if (j == 1)
      {
        a("parseLocalPipe::code==1；ssid ==" + localg.a().getSessionId() + "error! reconnectDevice");
        io.xlink.wifi.sdk.a.a().a(localg.a());
      }
      e locale = e.b(j);
      io.xlink.wifi.sdk.f.b.a().a(localg.a().getMacAddress());
      locale.a = localg.a();
      locale.a.setLocalAddress(parama.e());
      locale.c = i;
      localg.c();
      localg.g().onResponse(locale);
    }
  }

  private void s(a parama)
  {
    int i = parama.b().d();
    a("local probe fail errorCode:" + i);
  }

  private void t(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = 6;
    if (parama.d().b() >= 3)
      i = locala.g();
    String str = io.xlink.wifi.sdk.util.b.d(locala.a(i));
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(str);
    if (localXDevice == null)
      a("local sync device not found,MAC :" + str);
    byte b1;
    do
    {
      return;
      localXDevice.setLocalAddress(parama.e());
      a("receive local sync deviceMac " + str);
      b1 = locala.d();
      if (!io.xlink.wifi.sdk.util.b.a(b1, 0))
        continue;
      localXDevice.setDeviceName(io.xlink.wifi.sdk.util.b.c(locala.a(locala.g())));
    }
    while (!io.xlink.wifi.sdk.util.b.a(b1, 2));
    io.xlink.wifi.sdk.d.c.a(localXDevice, a(locala.e()), 0);
  }

  private void u(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = locala.g();
    int j = locala.d();
    g localg = e.a(i);
    if (localg != null)
    {
      localg.c();
      e locale = e.b(j);
      locale.a = localg.b().b();
      locale.a.setLocalAddress(parama.e());
      locale.c = i;
      localg.g().onResponse(locale);
      return;
    }
    a("recerve local set datapoint task not found :");
  }

  private void v(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = parama.b();
    int i = 6;
    if (parama.d().b() >= 3)
      i = locala.g();
    String str = io.xlink.wifi.sdk.util.b.d(locala.a(i));
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(str);
    if (localXDevice == null)
    {
      a("local ping device not found MAC: " + str);
      return;
    }
    localXDevice.setLocalAddress(parama.e());
    a("receive device Mac " + str + " ping ");
    io.xlink.wifi.sdk.a.a().b(localXDevice);
  }

  private void w(a parama)
  {
    io.xlink.wifi.sdk.a.a locala = new io.xlink.wifi.sdk.a.a(parama.b().b(), 5);
    int i = locala.d();
    byte b1 = locala.d();
    byte[] arrayOfByte;
    Object localObject;
    String str1;
    g localg;
    if (b1 >= 3)
    {
      String str2 = locala.g() + "";
      arrayOfByte = locala.a(locala.g());
      localObject = io.xlink.wifi.sdk.util.b.d(arrayOfByte).trim();
      str1 = str2;
      localg = e.b(str1);
      if (localg != null)
        break label144;
      a("receive handshake device not found mac:" + (String)localObject);
    }
    label144: e locale;
    XDevice localXDevice1;
    do
    {
      return;
      arrayOfByte = locala.a(6);
      str1 = io.xlink.wifi.sdk.util.b.d(arrayOfByte).trim();
      localObject = str1;
      break;
      localg.c();
      locale = e.b(i);
      localXDevice1 = io.xlink.wifi.sdk.f.b.a().b((String)localObject);
    }
    while (localXDevice1 == null);
    localXDevice1.setLocalAddress(parama.e());
    localXDevice1.setVersion(b1);
    if (i != 0)
    {
      locale.a = localXDevice1;
      io.xlink.wifi.sdk.a.a().a(localXDevice1, (String)localObject);
      localg.g().onResponse(locale);
      return;
    }
    int j = locala.f();
    io.xlink.wifi.sdk.a.a().b(localXDevice1, j);
    localXDevice1.setMcuSoftVersion(locala.g());
    int k = io.xlink.wifi.sdk.util.b.d(locala.g());
    XDevice localXDevice2 = io.xlink.wifi.sdk.a.a().a(localXDevice1, parama.a(), arrayOfByte, localXDevice1.getProductId(), localXDevice1.getPort());
    XDevice localXDevice3 = io.xlink.wifi.sdk.a.a().a(localXDevice2, k);
    locala.d();
    io.xlink.wifi.sdk.f.b.a().c(localXDevice3);
    locale.a = localXDevice3;
    localg.g().onResponse(locale);
    XlinkUdpService.b().d();
    a("receive device MAC: " + (String)localObject + " HandShake  sessionId :" + localXDevice3.getSessionId() + " deviceId:" + localXDevice3.getDeviceId());
  }

  private void x(a parama)
  {
    while (true)
    {
      io.xlink.wifi.sdk.a.a locala;
      String str1;
      XDevice localXDevice1;
      XDevice localXDevice2;
      StringBuilder localStringBuilder;
      try
      {
        a("reciver message--->" + parama.toString());
        locala = parama.b();
        byte b1 = locala.d();
        a("version--->" + b1);
        if (b1 >= 3)
        {
          int i = locala.g();
          if (i > 0)
            continue;
          a("scanDevice error,v3 mac length invalid");
          return;
          arrayOfByte = locala.a(i);
          str1 = io.xlink.wifi.sdk.util.b.d(arrayOfByte);
          localXDevice1 = io.xlink.wifi.sdk.a.a().a(str1);
          localXDevice1.setVersion(b1);
          String str2 = io.xlink.wifi.sdk.util.b.c(locala.a(locala.g()));
          localXDevice1.setMcuHardVersion(locala.d());
          localXDevice1.setMcuSoftVersion(locala.g());
          int j = io.xlink.wifi.sdk.util.b.d(locala.g());
          if (b1 < 2)
            continue;
          int k = io.xlink.wifi.sdk.util.b.d(locala.g());
          localXDevice1 = io.xlink.wifi.sdk.a.a().c(localXDevice1, k);
          if (b1 < 3)
            break label732;
          byte b2 = locala.d();
          byte b3 = locala.d();
          b4 = b2;
          b5 = b3;
          a("scanFlag---->" + b4);
          a("dataFlag--->" + b5);
          if (!io.xlink.wifi.sdk.util.b.a(b5, 0))
            continue;
          localXDevice1.setDeviceName(io.xlink.wifi.sdk.util.b.c(locala.a(locala.g())));
          if ((io.xlink.wifi.sdk.util.b.a(b5, 1)) && (b1 < 3))
            break label764;
          if (!io.xlink.wifi.sdk.util.b.a(b4, 0))
            break label749;
          m = 1;
          if (m != 0)
            continue;
          if (b1 < 2)
            break label799;
          if (!io.xlink.wifi.sdk.util.b.a(b5, 2))
            break label779;
          localXDevice1 = io.xlink.wifi.sdk.a.a().a(true, localXDevice1);
          localXDevice1.setAccessKey(locala.f());
          localXDevice2 = io.xlink.wifi.sdk.a.a().a(localXDevice1, parama.a(), arrayOfByte, str2, j);
          XDevice localXDevice3 = io.xlink.wifi.sdk.f.b.a().b(localXDevice2.getMacAddress());
          if (localXDevice3 == null)
            break label1067;
          if (m != 0)
            continue;
          localXDevice3 = io.xlink.wifi.sdk.a.a().a(localXDevice2.isInit(), localXDevice3);
          localXDevice3.setAccessKey(localXDevice2.getAccessKey());
          XDevice localXDevice5 = io.xlink.wifi.sdk.a.a().a(localXDevice3, parama.a(), arrayOfByte, str2, j);
          localXDevice4 = io.xlink.wifi.sdk.a.a().c(localXDevice5, localXDevice2.getProductType());
          localXDevice4.setVersion(b1);
          if (TextUtils.isEmpty(localXDevice2.getDeviceName()))
            continue;
          localXDevice4.setDeviceName(localXDevice2.getDeviceName());
          localXDevice4.setLocalAddress(parama.e());
          io.xlink.wifi.sdk.f.b.a().c(localXDevice4);
          localStringBuilder = new StringBuilder();
          if (m != 1)
            break label834;
          localStringBuilder.append("read device: " + str1);
          g localg = e.b(str1 + 1);
          if (localg == null)
            break label822;
          e locale = new e(0);
          locale.a = localXDevice4;
          localg.c();
          localg.g().onResponse(locale);
          localStringBuilder.append(" Scaning by mac ");
          localStringBuilder.append(" port " + localXDevice4.getPort());
          localStringBuilder.append(" deviceId:" + localXDevice4.getDeviceId());
          localStringBuilder.append(" productId:" + localXDevice4.getProductId());
          a(localStringBuilder.toString());
          return;
        }
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        localIndexOutOfBoundsException.printStackTrace();
        return;
      }
      byte[] arrayOfByte = locala.a(6);
      continue;
      label732: byte b6 = locala.d();
      byte b5 = b6;
      byte b4 = b6;
      continue;
      label749: if (io.xlink.wifi.sdk.util.b.a(b4, 1))
      {
        m = 0;
        continue;
        label764: if (io.xlink.wifi.sdk.util.b.a(b5, 4))
        {
          m = 1;
          continue;
          localXDevice1.setAccessKey(-1);
          localXDevice1 = io.xlink.wifi.sdk.a.a().a(false, localXDevice1);
          continue;
          if (!io.xlink.wifi.sdk.util.b.a(b5, 2))
            continue;
          localXDevice1 = io.xlink.wifi.sdk.a.a().a(true, localXDevice1);
          continue;
          localStringBuilder.append(" Scaning by mac task is null");
          continue;
          localStringBuilder.append("read device: " + str1);
          localStringBuilder.append(" Scaning by productId ");
          localStringBuilder.append("port " + localXDevice4.getPort());
          localStringBuilder.append("deviceId:" + localXDevice4.getDeviceId());
          localStringBuilder.append(" productId:" + localXDevice4.getProductId());
          if (localXDevice4.getVersion() >= 3)
          {
            if ((localXDevice4.getSubKey() <= 0) && (localXDevice4.getAccessKey() > 0))
            {
              if (XlinkAgent.getInstance().getDeviceSubscribeKey(localXDevice4, localXDevice4.getAccessKey(), this.b) >= 0)
                continue;
              io.xlink.wifi.sdk.d.c.a(localXDevice4);
              continue;
            }
            io.xlink.wifi.sdk.f.b.a().c(localXDevice4);
            io.xlink.wifi.sdk.d.c.a(localXDevice4);
            continue;
          }
          localXDevice4.setSubKey(localXDevice4.getAccessKey());
          io.xlink.wifi.sdk.f.b.a().c(localXDevice4);
          io.xlink.wifi.sdk.d.c.a(localXDevice4);
          continue;
        }
      }
      else
      {
        m = 0;
        continue;
      }
      label779: label799: label822: label834: int m = 0;
      continue;
      label1067: XDevice localXDevice4 = localXDevice2;
    }
  }

  public void a(a parama)
  {
    try
    {
      if (!parama.d().e())
        break label178;
      switch (parama.c())
      {
      case 2:
      case 4:
      case 6:
      case 11:
      default:
        a("Receive TCP error type of package:" + parama);
        return;
      case 1:
        g(parama);
        return;
      case 3:
      case 7:
      case 9:
      case 10:
      case 5:
      case 12:
      case 8:
      }
    }
    catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
    {
      a("read Exception tcp packet:" + parama.toString() + " IndexOutOfBoundsException error");
      return;
    }
    f(parama);
    return;
    h(parama);
    return;
    d(parama);
    return;
    b(parama);
    return;
    c(parama);
    return;
    k(parama);
    return;
    label178: switch (parama.c())
    {
    case 5:
    case 6:
    case 9:
    case 10:
    case 11:
    case 13:
    default:
      a("Received is not the response TCP error type of package:" + parama);
      return;
    case 4:
      e(parama);
      return;
    case 7:
      i(parama);
      return;
    case 8:
      j(parama);
      return;
    case 14:
      l(parama);
      return;
    case 12:
    }
    k(parama);
  }

  public void a(InetAddress paramInetAddress, io.xlink.wifi.sdk.a.a parama, int paramInt, SocketAddress paramSocketAddress)
  {
    if (parama.b().length <= 5)
    {
      a("error data length:" + parama.b().length + " buf:" + io.xlink.wifi.sdk.util.b.a(parama.b()));
      return;
    }
    b localb = new b(parama.b());
    if ((localb.c()) && (localb.a() <= -5 + parama.b().length) && (localb.a() != 0))
    {
      a locala = new a(new io.xlink.wifi.sdk.a.a(parama.b(), 5), localb);
      locala.a(paramInetAddress);
      locala.a = paramInt;
      locala.a(paramSocketAddress);
      try
      {
        m(locala);
        return;
      }
      catch (IndexOutOfBoundsException localIndexOutOfBoundsException)
      {
        a("udp dispatchPacket error  :" + localIndexOutOfBoundsException.toString() + "   dataBuff：" + locala.toString());
        return;
      }
    }
    a("parse data error header length：" + localb.a() + " read data length：" + parama.b().length + " type:" + localb.d());
  }

  private static class a
  {
    private static c a = new c(null);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.b.c
 * JD-Core Version:    0.6.0
 */