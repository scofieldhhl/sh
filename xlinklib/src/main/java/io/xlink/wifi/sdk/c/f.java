package io.xlink.wifi.sdk.c;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkUdpService;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.util.MyLog;
import java.net.InetAddress;
import java.util.Iterator;
import java.util.List;

public class f
{
  private static f a;

  public static io.xlink.wifi.sdk.a.b a(List<DataPoint> paramList)
  {
    io.xlink.wifi.sdk.a.b localb1 = new io.xlink.wifi.sdk.a.b(1000);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localb1.a(((DataPoint)localIterator.next()).getByteValue());
    io.xlink.wifi.sdk.a.b localb2 = new io.xlink.wifi.sdk.a.b(localb1.b());
    byte[] arrayOfByte = new byte[localb1.b()];
    System.arraycopy(localb1.a(), 0, arrayOfByte, 0, localb1.b());
    localb2.a(arrayOfByte);
    return localb2;
  }

  public static f a()
  {
    if (a == null)
      a = new f();
    return a;
  }

  public d a(byte paramByte)
  {
    a("tcp sendDisconnect  reason :" + paramByte);
    d locald = new d(1, 14, false);
    locald.b.a(paramByte);
    return locald;
  }

  public d a(int paramInt1, byte paramByte, byte[] paramArrayOfByte, io.xlink.wifi.sdk.d.a parama, int paramInt2)
  {
    d locald = new d(7 + paramArrayOfByte.length, 7, false);
    locald.b.b(paramInt1);
    locald.h();
    locald.b.a(paramByte);
    locald.b.a(paramArrayOfByte);
    return locald;
  }

  public d a(int paramInt1, int paramInt2, byte paramByte, int paramInt3)
  {
    d locald = new d(7, 7, true);
    locald.b.b(paramInt1);
    locald.b.a(paramInt2);
    locald.a(paramInt2 + "");
    locald.b.a(paramByte);
    return locald;
  }

  public d a(int paramInt1, int paramInt2, InetAddress paramInetAddress)
  {
    d locald = new d(3, 8, true);
    locald.a(paramInetAddress);
    locald.b.a(paramInt1);
    locald.b.c(paramInt2);
    return locald;
  }

  public d a(int paramInt, String paramString)
  {
    byte[] arrayOfByte;
    d locald;
    byte b;
    if (paramInt == 1)
    {
      arrayOfByte = io.xlink.wifi.sdk.util.b.c(paramString);
      locald = new d(4 + arrayOfByte.length, 1, false);
      locald.a(io.xlink.wifi.sdk.util.b.b());
      locald.b.c(3);
      locald.b.a(io.xlink.wifi.sdk.e.a.h);
      if (paramInt != 1)
        break label99;
      b = io.xlink.wifi.sdk.util.b.a(4, io.xlink.wifi.sdk.util.b.a(0, 0));
    }
    while (true)
    {
      locald.b.a(b);
      locald.b.a(arrayOfByte);
      return locald;
      arrayOfByte = io.xlink.wifi.sdk.util.b.d(paramString);
      break;
      label99: b = 0;
      if (paramInt != 2)
        continue;
      b = io.xlink.wifi.sdk.util.b.a(1, 0);
    }
  }

  public d a(XDevice paramXDevice)
  {
    d locald = new d(2, 13, false);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice);
    locald.b.a(paramXDevice.getSessionId());
    return locald;
  }

  public d a(XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte)
  {
    d locald = new d(5 + paramArrayOfByte.length, 8, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.b.a(paramXDevice.getSessionId());
    locald.h();
    locald.b.a(paramByte);
    locald.b.a(paramArrayOfByte);
    return locald;
  }

  public d a(XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte, io.xlink.wifi.sdk.d.a parama, int paramInt)
  {
    d locald = new d(7 + paramArrayOfByte.length, 7, false);
    locald.a(paramXDevice);
    locald.b.b(paramXDevice.getDeviceId());
    locald.h();
    locald.b.a(paramByte);
    locald.b.a(paramArrayOfByte);
    return locald;
  }

  public d a(XDevice paramXDevice, int paramInt)
  {
    int i = 22;
    if (paramXDevice.getVersion() >= 3)
      i = 24;
    d locald = new d(i, 2, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.b.a(paramXDevice.getVersion());
    if (paramXDevice.getVersion() >= 3)
      locald.h();
    while (true)
    {
      locald.b.a(io.xlink.wifi.sdk.util.b.a(paramInt));
      locald.b.a(io.xlink.wifi.sdk.e.a.h);
      locald.b.c(0);
      locald.b.a(paramXDevice.getTimeout());
      return locald;
      locald.a(paramXDevice.getMacAddress());
    }
  }

  public d a(XDevice paramXDevice, int paramInt, boolean paramBoolean, io.xlink.wifi.sdk.d.a parama)
  {
    byte b = 3;
    byte[] arrayOfByte1 = io.xlink.wifi.sdk.util.b.d(paramXDevice.getProductId());
    byte[] arrayOfByte2 = io.xlink.wifi.sdk.util.b.c(paramXDevice.getMacAddress());
    byte[] arrayOfByte3 = io.xlink.wifi.sdk.util.b.a(paramInt);
    d locald = new d(2 + (53 + arrayOfByte2.length), 9, false);
    locald.a(paramXDevice);
    locald.b.a(arrayOfByte1.length);
    locald.b.a(arrayOfByte1);
    locald.b.a(arrayOfByte2.length);
    locald.b.a(arrayOfByte2);
    locald.b.a(arrayOfByte3);
    locald.h();
    if (paramXDevice.getVersion() >= b)
      if (paramBoolean)
        b = 5;
    while (true)
    {
      locald.b.a(b);
      return locald;
      b = 4;
      continue;
      if (paramBoolean)
        continue;
      b = 2;
    }
  }

  public d a(XDevice paramXDevice, String paramString)
  {
    d locald = new d(22, 2, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice.getMacAddress());
    locald.b.a(paramXDevice.getVersion());
    locald.b.a(io.xlink.wifi.sdk.util.b.b(paramString));
    locald.b.a(io.xlink.wifi.sdk.e.a.h);
    locald.b.c(0);
    locald.b.a(paramXDevice.getTimeout());
    return locald;
  }

  public d a(XDevice paramXDevice, String paramString1, String paramString2)
  {
    d locald = new d(37, 9, false);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice);
    locald.h();
    locald.b.c(0);
    locald.b.a(io.xlink.wifi.sdk.e.a.h);
    locald.b.a(io.xlink.wifi.sdk.util.b.b(paramString1));
    locald.b.a(io.xlink.wifi.sdk.util.b.b(paramString2));
    return locald;
  }

  public d a(XDevice paramXDevice, String paramString, boolean paramBoolean, io.xlink.wifi.sdk.d.a parama)
  {
    byte b = 3;
    byte[] arrayOfByte1 = io.xlink.wifi.sdk.util.b.d(paramXDevice.getProductId());
    byte[] arrayOfByte2 = io.xlink.wifi.sdk.util.b.c(paramXDevice.getMacAddress());
    byte[] arrayOfByte3 = io.xlink.wifi.sdk.util.b.b(paramString);
    d locald = new d(2 + (53 + arrayOfByte2.length), 9, false);
    locald.a(paramXDevice);
    locald.b.a(arrayOfByte1.length);
    locald.b.a(arrayOfByte1);
    locald.b.a(arrayOfByte2.length);
    locald.b.a(arrayOfByte2);
    locald.b.a(arrayOfByte3);
    locald.h();
    if (paramXDevice.getVersion() >= b)
      if (paramBoolean)
        b = 5;
    while (true)
    {
      locald.b.a(b);
      return locald;
      b = 4;
      continue;
      if (paramBoolean)
        continue;
      b = 2;
    }
  }

  public d a(XDevice paramXDevice, List<DataPoint> paramList)
  {
    io.xlink.wifi.sdk.a.b localb = a(paramList);
    d locald = new d(5 + localb.a().length, 4, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.b.a(paramXDevice.getSessionId());
    locald.h();
    locald.b.a(6);
    locald.b.a(localb.a());
    return locald;
  }

  public d a(XDevice paramXDevice, byte[] paramArrayOfByte)
  {
    d locald = new d(7 + paramArrayOfByte.length, 4, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.b.a(paramXDevice.getSessionId());
    locald.h();
    locald.b.a(io.xlink.wifi.sdk.util.b.a(0, 0));
    locald.b.a(paramArrayOfByte.length);
    locald.b.a(paramArrayOfByte);
    return locald;
  }

  public d a(io.xlink.wifi.sdk.d.a parama)
  {
    byte[] arrayOfByte = io.xlink.wifi.sdk.util.b.d(io.xlink.wifi.sdk.e.a.a());
    io.xlink.wifi.sdk.a.b localb = new io.xlink.wifi.sdk.a.b(arrayOfByte.length);
    localb.a(arrayOfByte);
    d locald = new d(localb);
    locald.a("999");
    return locald;
  }

  public void a(XDevice paramXDevice, io.xlink.wifi.sdk.d.a parama)
  {
    d locald = new d(3, 3, false);
    locald.a(paramXDevice);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.b.a(paramXDevice.getSessionId());
    locald.b.c(0);
    g localg = new g(locald, parama);
    XlinkUdpService.b().a(localg);
  }

  public void a(String paramString)
  {
    MyLog.e("PacketEncoder", paramString + "");
  }

  public d b()
  {
    return new d(0, 13, false);
  }

  public d b(int paramInt, String paramString)
  {
    a("   doLogin  --   id :" + paramInt + " authorize :" + paramString);
    byte[] arrayOfByte = io.xlink.wifi.sdk.util.b.d(paramString);
    d locald = new d(10 + arrayOfByte.length, 1, false);
    locald.b.c(3);
    locald.b.b(paramInt);
    locald.b.a(arrayOfByte.length);
    locald.b.a(arrayOfByte);
    locald.b.c(0);
    locald.b.a(io.xlink.wifi.sdk.e.a.e);
    return locald;
  }

  public d b(XDevice paramXDevice)
  {
    if (paramXDevice.getSessionId() < 0)
      return null;
    d locald = new d(2, 14, false);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice);
    locald.b.a(paramXDevice.getSessionId());
    return locald;
  }

  public d b(XDevice paramXDevice, int paramInt)
  {
    d locald = new d(9, 11, false);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice);
    locald.h();
    locald.b.c(0);
    locald.b.a(io.xlink.wifi.sdk.e.a.h);
    locald.b.b(paramInt);
    return locald;
  }

  public d b(XDevice paramXDevice, String paramString1, String paramString2)
  {
    d locald = new d(39, 5, false);
    locald.a(paramXDevice);
    locald.b.b(paramXDevice.getDeviceId());
    locald.h();
    locald.b.c(0);
    locald.b.a(io.xlink.wifi.sdk.util.b.b(paramString1));
    locald.b.a(io.xlink.wifi.sdk.util.b.b(paramString2));
    return locald;
  }

  public d b(XDevice paramXDevice, List<DataPoint> paramList)
  {
    io.xlink.wifi.sdk.a.b localb = a(paramList);
    d locald = new d(7 + localb.a().length, 3, false);
    locald.a(paramXDevice);
    locald.b.b(paramXDevice.getDeviceId());
    locald.h();
    locald.b.a(6);
    locald.b.a(localb.a());
    return locald;
  }

  public d b(XDevice paramXDevice, byte[] paramArrayOfByte)
  {
    d locald = new d(9 + paramArrayOfByte.length, 3, false);
    locald.a(paramXDevice);
    locald.b.b(paramXDevice.getDeviceId());
    locald.h();
    locald.b.a(io.xlink.wifi.sdk.util.b.a(0, 0));
    locald.b.a(paramArrayOfByte.length);
    locald.b.a(paramArrayOfByte);
    return locald;
  }

  public d c(XDevice paramXDevice, int paramInt)
  {
    d locald = new d(20, 7, false);
    locald.a(paramXDevice.getAddress());
    locald.a(paramXDevice.getPort());
    locald.a(paramXDevice);
    locald.b.a(paramXDevice.getVersion());
    locald.h();
    locald.b.a(io.xlink.wifi.sdk.util.b.a(paramInt));
    locald.b.c(0);
    return locald;
  }

  public d d(XDevice paramXDevice, int paramInt)
  {
    byte[] arrayOfByte;
    d locald;
    byte b;
    if (paramInt == 1)
    {
      arrayOfByte = io.xlink.wifi.sdk.util.b.c(paramXDevice.getMacAddress());
      int i = 4 + arrayOfByte.length;
      if (paramXDevice.getVersion() >= 3)
        i += 2;
      locald = new d(i, 1, false);
      locald.a(io.xlink.wifi.sdk.util.b.b());
      locald.b.c(3);
      locald.b.a(io.xlink.wifi.sdk.e.a.h);
      if (paramInt != 1)
        break label138;
      b = io.xlink.wifi.sdk.util.b.a(4, io.xlink.wifi.sdk.util.b.a(0, 0));
    }
    while (true)
    {
      locald.b.a(b);
      if (paramXDevice.getVersion() >= 3)
        locald.b.a(arrayOfByte.length);
      locald.b.a(arrayOfByte);
      return locald;
      arrayOfByte = io.xlink.wifi.sdk.util.b.d(paramXDevice.getMacAddress());
      break;
      label138: if (paramInt == 2)
      {
        b = io.xlink.wifi.sdk.util.b.a(1, 0);
        continue;
      }
      b = 0;
    }
  }

  public d e(XDevice paramXDevice, int paramInt)
  {
    d locald = new d(7, 10, false);
    locald.a(paramXDevice);
    locald.b.b(paramXDevice.getDeviceId());
    locald.h();
    locald.b.c(paramInt);
    return locald;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.c.f
 * JD-Core Version:    0.6.0
 */