package io.xlink.wifi.sdk;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.c.d;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.c.f;
import io.xlink.wifi.sdk.c.g;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.GetSubscribeKeyListener;
import io.xlink.wifi.sdk.listener.RenameDeviceListener;
import io.xlink.wifi.sdk.listener.ScanDeviceListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.SetDataPointListener;
import io.xlink.wifi.sdk.listener.SetDeviceAccessKeyListener;
import io.xlink.wifi.sdk.listener.SubscribeDeviceListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import io.xlink.wifi.sdk.util.MyLog;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public final class XlinkAgent
{
  protected static boolean b;
  private static boolean e = true;
  public boolean a = false;
  boolean c = false;
  private boolean d = false;

  static
  {
    b = false;
  }

  public static XDevice JsonToDevice(JSONObject paramJSONObject)
  {
    if (paramJSONObject == null)
      return null;
    return a.a().a(paramJSONObject);
  }

  private int a(XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte, int paramInt, SendPipeListener paramSendPipeListener)
  {
    if ((paramArrayOfByte == null) || (paramSendPipeListener == null))
      return -8;
    if (!b)
      return -1;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    if (paramXDevice == null)
      return -6;
    if (!paramXDevice.isLAN())
      return -2;
    return io.xlink.wifi.sdk.h.b.a().a(paramXDevice, paramByte, paramArrayOfByte, paramInt, paramSendPipeListener);
  }

  private int a(XDevice paramXDevice, int paramInt, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    if (!b)
      return -1;
    if ((paramInt <= 0) || (paramInt > 999999999))
      return -8;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    return io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramInt, new SetDeviceAccessKeyListener(paramInt, paramSetDeviceAccessKeyListener)
    {
      public void onSetLocalDeviceAccessKey(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
        if (paramInt1 == 0)
        {
          paramXDevice.setAccessKey(this.a);
          paramXDevice.setAuthkey(this.a + "");
          if (paramXDevice.getVersion() >= 3)
          {
            if (XlinkAgent.getInstance().getDeviceSubscribeKey(paramXDevice, paramXDevice.getAccessKey(), new GetSubscribeKeyListener(paramXDevice, paramInt1, paramInt2)
            {
              public void onGetSubscribekey(XDevice paramXDevice, int paramInt1, int paramInt2)
              {
                this.a.setSubKey(paramInt2);
                XlinkAgent.1.this.b.onSetLocalDeviceAccessKey(this.a, this.b, this.c);
              }
            }) < 0)
              this.b.onSetLocalDeviceAccessKey(paramXDevice, paramInt1, paramInt2);
            return;
          }
          paramXDevice.setSubKey(this.a);
          this.b.onSetLocalDeviceAccessKey(paramXDevice, paramInt1, paramInt2);
          return;
        }
        this.b.onSetLocalDeviceAccessKey(paramXDevice, paramInt1, paramInt2);
      }
    });
  }

  @Deprecated
  private int a(XDevice paramXDevice, String paramString1, String paramString2, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    if (!b)
      return -1;
    if ((TextUtils.isEmpty(paramString1)) || (TextUtils.isEmpty(paramString2)) || (paramSetDeviceAccessKeyListener == null))
      return -8;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    paramXDevice.setAuthkey(paramString2);
    try
    {
      paramXDevice.setAccessKey(Integer.parseInt(paramString2));
      label76: return io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramString1, paramString2, paramSetDeviceAccessKeyListener);
    }
    catch (Exception localException)
    {
      break label76;
    }
  }

  private void a()
  {
    XlinkUdpService.c = false;
    Intent localIntent = new Intent(io.xlink.wifi.sdk.util.b.a, XlinkUdpService.class);
    io.xlink.wifi.sdk.util.b.a.startService(localIntent);
    b("start inner service");
  }

  private void a(int paramInt)
  {
    if (XlinkTcpService.c())
      io.xlink.wifi.sdk.g.c.a().a(paramInt);
    try
    {
      Thread.sleep(100L);
      XlinkTcpService.h = true;
      if (XlinkTcpService.a() != null)
        XlinkTcpService.a().a(false, 0, false);
      Intent localIntent = new Intent(io.xlink.wifi.sdk.util.b.a, XlinkTcpService.class);
      io.xlink.wifi.sdk.util.b.a.stopService(localIntent);
      b("stop cloud Service");
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }

  private void a(int paramInt, String paramString)
  {
    XlinkTcpService.a = paramInt;
    XlinkTcpService.b = paramString;
    XlinkTcpService.h = false;
    Intent localIntent = new Intent(io.xlink.wifi.sdk.util.b.a, XlinkTcpService.class);
    io.xlink.wifi.sdk.util.b.a.startService(localIntent);
    b("start cloud Service");
  }

  private void a(XDevice paramXDevice)
  {
    d locald = f.a().b(paramXDevice);
    if (locald != null)
    {
      g localg = new g(locald);
      io.xlink.wifi.sdk.g.a.a().d(localg);
    }
  }

  private void b()
  {
    if (this.c)
      return;
    this.c = true;
    XlinkUdpService.c = true;
    io.xlink.wifi.sdk.util.b.b(new Runnable()
    {
      public void run()
      {
        Iterator localIterator = io.xlink.wifi.sdk.f.b.a().c().iterator();
        while (localIterator.hasNext())
        {
          XDevice localXDevice = (XDevice)localIterator.next();
          XlinkAgent.a(XlinkAgent.this, localXDevice);
          try
          {
            Thread.sleep(30L);
          }
          catch (InterruptedException localInterruptedException)
          {
          }
        }
        if (XlinkUdpService.b() != null)
          XlinkUdpService.b().a(false, 0);
        Intent localIntent = new Intent(io.xlink.wifi.sdk.util.b.a, XlinkUdpService.class);
        io.xlink.wifi.sdk.util.b.a.stopService(localIntent);
        XlinkAgent.this.c = false;
        XlinkAgent.a("stop inner service");
        io.xlink.wifi.sdk.f.b.a().b();
      }
    });
  }

  private static void b(String paramString)
  {
    MyLog.e("XlinkAgent", paramString);
  }

  public static void debug(boolean paramBoolean)
  {
    MyLog.a = Boolean.valueOf(paramBoolean);
  }

  public static JSONObject deviceToJson(XDevice paramXDevice)
  {
    if (paramXDevice == null)
      return null;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      localXDevice = paramXDevice;
    localXDevice.setDeviceName(paramXDevice.getDeviceName());
    try
    {
      JSONObject localJSONObject2 = a.a().c(paramXDevice);
      localJSONObject1 = localJSONObject2;
      return localJSONObject1;
    }
    catch (JSONException localJSONException)
    {
      while (true)
      {
        localJSONException.printStackTrace();
        JSONObject localJSONObject1 = null;
      }
    }
  }

  public static XlinkAgent getInstance()
  {
    return a.a();
  }

  public static boolean getUseDefaultSyncResponse()
  {
    return e;
  }

  public static void init(Context paramContext)
  {
    io.xlink.wifi.sdk.util.b.a = paramContext;
    io.xlink.wifi.sdk.util.b.a();
    b = true;
  }

  public static int scanByMac(XDevice paramXDevice, io.xlink.wifi.sdk.d.a parama)
  {
    if (!b)
      return -1;
    if (!XlinkUdpService.a())
      return -4;
    if (io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress()) == null)
      return -6;
    io.xlink.wifi.sdk.h.b.a().a(paramXDevice, parama);
    return 0;
  }

  public static void setCMServer(String paramString, int paramInt)
  {
    io.xlink.wifi.sdk.e.a.a = paramString;
    io.xlink.wifi.sdk.e.a.b = paramInt;
  }

  public static void setTcpType(int paramInt)
  {
    io.xlink.wifi.sdk.e.a.j = paramInt;
  }

  public static void setUseDefaultSyncResponse(boolean paramBoolean)
  {
    e = paramBoolean;
  }

  protected int a(XDevice paramXDevice, io.xlink.wifi.sdk.d.a parama)
  {
    int i = -4;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      i = -10;
    do
    {
      do
        return i;
      while ((!XlinkTcpService.c()) && (!XlinkUdpService.a()));
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      if ((localXDevice.isLAN()) && (XlinkUdpService.a()))
      {
        f.a().a(localXDevice, parama);
        return 0;
      }
      if (!localXDevice.isValidId())
        return -9;
    }
    while (!XlinkTcpService.c());
    io.xlink.wifi.sdk.g.c.a().a(paramXDevice, 0, parama, 7);
    return 0;
  }

  protected int a(XDevice paramXDevice, String paramString, io.xlink.wifi.sdk.d.b paramb, int paramInt)
  {
    if ((TextUtils.isEmpty(paramString)) || (paramb == null))
      return -8;
    if (!b)
      return -1;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    if (!XlinkUdpService.a())
      return -4;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramString, paramb, paramInt);
    return 0;
  }

  public void addXlinkListener(XlinkNetListener paramXlinkNetListener)
  {
    io.xlink.wifi.sdk.d.c.a(paramXlinkNetListener.getClass().getName(), paramXlinkNetListener);
  }

  public void clearPacketQueue()
  {
    monitorenter;
    try
    {
      boolean bool = b;
      if (!bool);
      while (true)
      {
        return;
        io.xlink.wifi.sdk.g.a.a().e();
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject;
  }

  public int connectDevice(XDevice paramXDevice, int paramInt1, int paramInt2, ConnectDeviceListener paramConnectDeviceListener)
  {
    if (!b)
      return -1;
    if (paramXDevice.getVersion() >= 3)
    {
      io.xlink.wifi.sdk.c.c localc1 = new io.xlink.wifi.sdk.c.c(paramXDevice, paramInt1, paramInt2, paramConnectDeviceListener);
      localc1.a(this.d);
      return localc1.a();
    }
    if (paramXDevice.getVersion() >= 2)
    {
      io.xlink.wifi.sdk.c.c localc2 = new io.xlink.wifi.sdk.c.c(paramXDevice, paramInt1, paramConnectDeviceListener);
      localc2.a(this.d);
      return localc2.a();
    }
    io.xlink.wifi.sdk.c.b localb = new io.xlink.wifi.sdk.c.b(paramXDevice, paramInt1 + "", paramConnectDeviceListener);
    localb.a(this.d);
    return localb.a();
  }

  @Deprecated
  public int connectDevice(XDevice paramXDevice, int paramInt, ConnectDeviceListener paramConnectDeviceListener)
  {
    if (!b)
      return -1;
    if (paramXDevice.getVersion() >= 2)
    {
      io.xlink.wifi.sdk.c.c localc = new io.xlink.wifi.sdk.c.c(paramXDevice, paramInt, paramConnectDeviceListener);
      localc.a(this.d);
      return localc.a();
    }
    io.xlink.wifi.sdk.c.b localb = new io.xlink.wifi.sdk.c.b(paramXDevice, paramInt + "", paramConnectDeviceListener);
    localb.a(this.d);
    return localb.a();
  }

  public int connectDevice(XDevice paramXDevice, ConnectDeviceListener paramConnectDeviceListener)
  {
    if (!b)
      return -1;
    if (paramXDevice.getVersion() >= 2)
    {
      io.xlink.wifi.sdk.c.c localc = new io.xlink.wifi.sdk.c.c(paramXDevice, paramXDevice.getAccessKey(), paramXDevice.getSubKey(), paramConnectDeviceListener);
      localc.a(this.d);
      return localc.a();
    }
    io.xlink.wifi.sdk.c.b localb = new io.xlink.wifi.sdk.c.b(paramXDevice, paramXDevice.getAccessKey() + "", paramConnectDeviceListener);
    localb.a(this.d);
    return localb.a();
  }

  @Deprecated
  public int connectDevice(XDevice paramXDevice, String paramString, ConnectDeviceListener paramConnectDeviceListener)
  {
    if (!b)
      return -1;
    io.xlink.wifi.sdk.c.b localb = new io.xlink.wifi.sdk.c.b(paramXDevice, paramString, paramConnectDeviceListener);
    localb.a(this.d);
    return localb.a();
  }

  public int getDeviceSubscribeKey(XDevice paramXDevice, int paramInt, GetSubscribeKeyListener paramGetSubscribeKeyListener)
  {
    int i = -8;
    if (paramXDevice.getVersion() >= 3)
    {
      if (io.xlink.wifi.sdk.f.a.a().d())
        break label28;
      i = -10;
    }
    label28: 
    do
      return i;
    while ((paramInt < 0) || (paramInt > 999999999));
    if (!b)
      return -1;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    return io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramInt, paramGetSubscribeKeyListener);
  }

  public int handshakeWithDevice(XDevice paramXDevice, int paramInt1, io.xlink.wifi.sdk.d.b paramb, int paramInt2)
  {
    if ((paramInt1 < 0) || (paramInt1 > 999999999))
      return -8;
    if (!b)
      return -1;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    if (!XlinkUdpService.a())
      return -4;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramInt1, paramb, paramInt2);
    return 0;
  }

  public int initDevice(XDevice paramXDevice)
  {
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    paramXDevice.a(-1);
    paramXDevice.b(-1);
    io.xlink.wifi.sdk.f.b.a().b(paramXDevice);
    return 0;
  }

  public boolean isConnectedLocal()
  {
    return XlinkUdpService.a();
  }

  public boolean isConnectedOuterNet()
  {
    return XlinkTcpService.c();
  }

  public int login(int paramInt, String paramString)
  {
    if ((paramInt == 0) || (TextUtils.isEmpty(paramString)));
    do
    {
      return -8;
      if (!b)
        return -1;
      if ((XlinkTcpService.c()) || (XlinkTcpService.d))
        return -7;
    }
    while ((paramInt == 0) || (TextUtils.isEmpty(paramString)));
    io.xlink.wifi.sdk.f.a.a().b();
    a(paramInt, paramString);
    return 0;
  }

  public void removeAllDevice()
  {
    io.xlink.wifi.sdk.f.b.a().b();
  }

  public int removeDevice(XDevice paramXDevice)
  {
    if ((paramXDevice == null) || (paramXDevice.getMacAddress() == null))
      return -8;
    removeDevice(paramXDevice.getMacAddress());
    return 0;
  }

  public int removeDevice(String paramString)
  {
    if (paramString == null)
      return -8;
    io.xlink.wifi.sdk.f.b.a().c(paramString);
    return 0;
  }

  public void removeListener(XlinkNetListener paramXlinkNetListener)
  {
    io.xlink.wifi.sdk.d.c.a(paramXlinkNetListener.getClass().getName());
  }

  public int renameDevice(XDevice paramXDevice, String paramString, RenameDeviceListener paramRenameDeviceListener)
  {
    if ((paramXDevice == null) || (TextUtils.isEmpty(paramString)));
    byte[] arrayOfByte;
    do
    {
      return -8;
      arrayOfByte = io.xlink.wifi.sdk.util.b.d(paramString);
    }
    while (arrayOfByte.length > 16);
    if (!io.xlink.wifi.sdk.f.a.a().d())
      return -10;
    if (!b)
      return -1;
    if ((!XlinkTcpService.c()) && (!XlinkUdpService.a()))
      return -4;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    if ((localXDevice.isLAN()) && (XlinkUdpService.a()))
      return io.xlink.wifi.sdk.h.b.a().a(paramXDevice, arrayOfByte, paramRenameDeviceListener);
    if (!localXDevice.isValidId())
      return -9;
    if (XlinkTcpService.c())
      return io.xlink.wifi.sdk.g.c.a().a(paramXDevice, arrayOfByte, paramRenameDeviceListener);
    return -4;
  }

  public int scanDeviceByProductId(String paramString, ScanDeviceListener paramScanDeviceListener)
  {
    int i = -10;
    if (!b)
      i = -1;
    do
    {
      do
        return i;
      while (!io.xlink.wifi.sdk.f.a.a().d());
      if (!XlinkUdpService.a())
        return -4;
      if (TextUtils.isEmpty(paramString))
        return -8;
    }
    while (TextUtils.isEmpty(io.xlink.wifi.sdk.util.b.d()));
    io.xlink.wifi.sdk.d.c.a(paramScanDeviceListener);
    io.xlink.wifi.sdk.h.b.a().a(paramString);
    return 0;
  }

  public int sendPipe(int paramInt, byte[] paramArrayOfByte, SendPipeListener paramSendPipeListener)
  {
    if ((paramArrayOfByte == null) || (paramSendPipeListener == null))
      return -8;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      return -10;
    if (!b)
      return -1;
    if (XlinkTcpService.c())
      return io.xlink.wifi.sdk.g.c.a().a(paramInt, 0, paramArrayOfByte, paramSendPipeListener, io.xlink.wifi.sdk.e.a.m);
    return -4;
  }

  public int sendPipeData(XDevice paramXDevice, byte[] paramArrayOfByte, int paramInt, SendPipeListener paramSendPipeListener)
  {
    if ((paramArrayOfByte == null) || (paramSendPipeListener == null))
      return -8;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      return -10;
    if (!b)
      return -1;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    if ((XlinkUdpService.a()) && (localXDevice.getDevcieConnectStates() == 0))
      return a(localXDevice, 0, paramArrayOfByte, paramInt, paramSendPipeListener);
    if (XlinkTcpService.c())
    {
      if (!localXDevice.isValidId())
        return -9;
      return io.xlink.wifi.sdk.g.c.a().a(localXDevice, 0, paramArrayOfByte, paramSendPipeListener, paramInt);
    }
    return -4;
  }

  public int sendPipeData(XDevice paramXDevice, byte[] paramArrayOfByte, SendPipeListener paramSendPipeListener)
  {
    return sendPipeData(paramXDevice, paramArrayOfByte, 7, paramSendPipeListener);
  }

  public int sendPipeResponse(int paramInt, short paramShort, byte paramByte)
  {
    if (e)
      return -8;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      return -10;
    if (!b)
      return -1;
    if ((XlinkTcpService.c()) && (!getUseDefaultSyncResponse()))
      return io.xlink.wifi.sdk.g.c.a().a(paramInt, paramShort, paramByte, io.xlink.wifi.sdk.e.a.m);
    return -4;
  }

  public int sendProbe(XDevice paramXDevice)
  {
    int i = -4;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      i = -10;
    do
    {
      do
        return i;
      while ((!XlinkTcpService.c()) && (!XlinkUdpService.a()));
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      if ((localXDevice.isLAN()) && (XlinkUdpService.a()))
      {
        f.a().a(localXDevice, null);
        return 0;
      }
      if (!localXDevice.isValidId())
        return -9;
    }
    while (!XlinkTcpService.c());
    io.xlink.wifi.sdk.g.c.a().a(paramXDevice, 0, null, 7);
    return 0;
  }

  public int setDataPoint(XDevice paramXDevice, List<DataPoint> paramList, SetDataPointListener paramSetDataPointListener)
  {
    int i = -4;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      i = -10;
    XDevice localXDevice;
    do
    {
      do
        return i;
      while ((!XlinkTcpService.c()) && (!XlinkUdpService.a()));
      localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      if (paramList == null)
        return -11;
      if ((localXDevice.isLAN()) && (XlinkUdpService.a()))
        return io.xlink.wifi.sdk.h.b.a().a(localXDevice, paramList, paramSetDataPointListener);
      if (!localXDevice.isValidId())
        return -9;
    }
    while (!XlinkTcpService.c());
    return io.xlink.wifi.sdk.g.c.a().a(localXDevice, paramList, paramSetDataPointListener);
  }

  public int setDeviceAccessKey(XDevice paramXDevice, int paramInt, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    if (paramXDevice.getVersion() >= 2)
    {
      if (!io.xlink.wifi.sdk.f.a.a().d())
        return -10;
      if ((paramInt <= 0) || (paramInt > 999999999))
        return -8;
      if (!b)
        return -1;
      if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
        return -3;
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      return a(localXDevice, paramInt, paramSetDeviceAccessKeyListener);
    }
    return setDeviceAuthorizeCode(paramXDevice, paramInt + "", paramInt + "", paramSetDeviceAccessKeyListener);
  }

  @Deprecated
  public int setDeviceAuthorizeCode(XDevice paramXDevice, String paramString1, String paramString2, SetDeviceAccessKeyListener paramSetDeviceAccessKeyListener)
  {
    int i = -1;
    if (!io.xlink.wifi.sdk.f.a.a().d())
      i = -10;
    do
    {
      return i;
      if ((TextUtils.isEmpty(paramString2)) || (paramString2.length() < 3) || (paramSetDeviceAccessKeyListener == null))
        return -8;
    }
    while (!b);
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    if ((XlinkUdpService.a()) && (localXDevice.isLAN()))
      return a(localXDevice, paramString1, paramString2, paramSetDeviceAccessKeyListener);
    if ((!XlinkUdpService.a()) && (!XlinkTcpService.c()))
      return -4;
    if ((!XlinkUdpService.a()) && (XlinkTcpService.c()))
    {
      if (!localXDevice.isValidId())
        return -9;
      return io.xlink.wifi.sdk.g.c.a().a(localXDevice, paramString1, paramString2, paramSetDeviceAccessKeyListener, io.xlink.wifi.sdk.e.a.m);
    }
    if ((XlinkUdpService.a()) && (!XlinkTcpService.c()));
    try
    {
      int j = Integer.parseInt(paramString2);
      i = j;
      label182: paramXDevice.setAccessKey(i);
      paramXDevice.setAuthkey(i + "");
      paramXDevice.setSubKey(i);
      return scanByMac(localXDevice, new io.xlink.wifi.sdk.d.a(paramString1, paramString2, paramSetDeviceAccessKeyListener)
      {
        public void onResponse(e parame)
        {
          switch (parame.b)
          {
          default:
          case 0:
          case -100:
          }
          while (true)
          {
            return;
            if (XlinkAgent.a(XlinkAgent.this, parame.a, this.a, this.b, this.c) >= 0)
              continue;
            parame.b = -100;
            this.c.onResponse(parame);
            return;
            if (!XlinkTcpService.c())
            {
              if (XlinkAgent.a(XlinkAgent.this, parame.a, this.a, this.b, this.c) >= 0)
                continue;
              parame.b = -100;
              this.c.onResponse(parame);
              return;
            }
            if (parame.a.isValidId())
              break label196;
            if (!XlinkAgent.this.isConnectedLocal())
              break;
            if (XlinkAgent.a(XlinkAgent.this, parame.a, this.a, this.b, this.c) >= 0)
              continue;
            parame.b = -100;
            this.c.onResponse(parame);
            return;
          }
          parame.b = 3;
          this.c.onResponse(parame);
          return;
          label196: io.xlink.wifi.sdk.g.c.a().a(parame.a, this.a, this.b, this.c, io.xlink.wifi.sdk.e.a.m);
        }
      });
    }
    catch (Exception localException)
    {
      break label182;
    }
  }

  public int setDeviceTimeOut(int paramInt)
  {
    if (paramInt < 0)
      return -8;
    io.xlink.wifi.sdk.e.a.i = paramInt;
    return 0;
  }

  public void setPreInnerServiceMode(boolean paramBoolean)
  {
    this.d = paramBoolean;
  }

  public void setSSL(String paramString1, String paramString2)
  {
    XlinkTcpService.a(paramString1);
    XlinkTcpService.b(paramString2);
  }

  public void setTcpConnectTimeOut(int paramInt)
  {
    io.xlink.wifi.sdk.e.a.g = paramInt;
  }

  public int start()
  {
    this.a = false;
    if (!b)
      return -1;
    io.xlink.wifi.sdk.f.a.a().b();
    if (XlinkUdpService.a())
      return -7;
    a();
    return 0;
  }

  // ERROR //
  public void stop()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 16	io/xlink/wifi/sdk/XlinkAgent:b	Z
    //   5: istore_2
    //   6: iload_2
    //   7: ifne +6 -> 13
    //   10: aload_0
    //   11: monitorexit
    //   12: return
    //   13: aload_0
    //   14: iconst_1
    //   15: putfield 23	io/xlink/wifi/sdk/XlinkAgent:a	Z
    //   18: iconst_0
    //   19: putstatic 168	io/xlink/wifi/sdk/XlinkTcpService:a	I
    //   22: aconst_null
    //   23: putstatic 171	io/xlink/wifi/sdk/XlinkTcpService:b	Ljava/lang/String;
    //   26: invokestatic 273	io/xlink/wifi/sdk/f/a:a	()Lio/xlink/wifi/sdk/f/a;
    //   29: invokevirtual 480	io/xlink/wifi/sdk/f/a:e	()V
    //   32: aload_0
    //   33: invokespecial 481	io/xlink/wifi/sdk/XlinkAgent:b	()V
    //   36: ldc2_w 482
    //   39: invokestatic 146	java/lang/Thread:sleep	(J)V
    //   42: aload_0
    //   43: iconst_0
    //   44: invokespecial 484	io/xlink/wifi/sdk/XlinkAgent:a	(I)V
    //   47: invokestatic 192	io/xlink/wifi/sdk/g/a:a	()Lio/xlink/wifi/sdk/g/a;
    //   50: invokevirtual 486	io/xlink/wifi/sdk/g/a:d	()V
    //   53: goto -43 -> 10
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    //   61: astore_3
    //   62: aload_3
    //   63: invokevirtual 164	java/lang/InterruptedException:printStackTrace	()V
    //   66: goto -24 -> 42
    //
    // Exception table:
    //   from	to	target	type
    //   2	6	56	finally
    //   13	36	56	finally
    //   36	42	56	finally
    //   42	53	56	finally
    //   62	66	56	finally
    //   36	42	61	java/lang/InterruptedException
  }

  public int subscribeDevice(XDevice paramXDevice, int paramInt, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    if (paramXDevice.getVersion() >= 2)
    {
      if (!b)
        return -1;
      if ((paramInt < 0) || (paramInt > 999999999))
        return -8;
      if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
        return -3;
      if (!XlinkTcpService.c())
        return -4;
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      io.xlink.wifi.sdk.g.c.a().a(localXDevice, paramInt, paramSubscribeDeviceListener);
      return 0;
    }
    return subscribeDevice(paramXDevice, paramInt + "", paramSubscribeDeviceListener);
  }

  @Deprecated
  public int subscribeDevice(XDevice paramXDevice, String paramString, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    if (!b)
      return -1;
    if (TextUtils.isEmpty(paramString))
      return -8;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    if (!XlinkTcpService.c())
      return -4;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    io.xlink.wifi.sdk.g.c.a().a(localXDevice, paramString, paramSubscribeDeviceListener);
    return 0;
  }

  @Deprecated
  public int unsubscribeDevice(XDevice paramXDevice, int paramInt, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    if (paramXDevice.getVersion() >= 2)
    {
      if (!b)
        return -1;
      if ((paramInt < 0) || (paramInt > 999999999))
        return -8;
      if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
        return -3;
      if (!XlinkTcpService.c())
        return -4;
      XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
      if (localXDevice == null)
        return -6;
      io.xlink.wifi.sdk.g.c.a().b(localXDevice, paramInt, paramSubscribeDeviceListener);
      return 0;
    }
    return unsubscribeDevice(paramXDevice, paramInt + "", paramSubscribeDeviceListener);
  }

  @Deprecated
  public int unsubscribeDevice(XDevice paramXDevice, String paramString, SubscribeDeviceListener paramSubscribeDeviceListener)
  {
    if (!b)
      return -1;
    if (TextUtils.isEmpty(paramString))
      return -8;
    if (io.xlink.wifi.sdk.f.b.a().a(paramXDevice))
      return -3;
    if (!XlinkTcpService.c())
      return -4;
    XDevice localXDevice = io.xlink.wifi.sdk.f.b.a().b(paramXDevice.getMacAddress());
    if (localXDevice == null)
      return -6;
    io.xlink.wifi.sdk.g.c.a().b(localXDevice, paramString, paramSubscribeDeviceListener);
    return 0;
  }

  private static class a
  {
    private static XlinkAgent a = new XlinkAgent(null);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.XlinkAgent
 * JD-Core Version:    0.6.0
 */