package io.xlink.wifi.sdk.d;

import android.os.Handler;
import android.os.Message;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.bean.a;
import io.xlink.wifi.sdk.listener.ScanDeviceListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import io.xlink.wifi.sdk.util.MyLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static HashMap<String, XlinkNetListener> a = new HashMap();
  private static final Handler b = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      if ((paramMessage.obj instanceof a));
      for (a locala = (a)paramMessage.obj; ; locala = null)
      {
        switch (paramMessage.what)
        {
        default:
        case 1:
        case 2:
        case 3:
        case 4:
        case 6:
        case 5:
        }
        while (true)
        {
          return;
          if ((c.a() == null) || (paramMessage.obj == null) || (!(paramMessage.obj instanceof XDevice)))
            continue;
          c.a().onGotDeviceByScan((XDevice)paramMessage.obj);
          return;
          Iterator localIterator5 = c.b().iterator();
          while (localIterator5.hasNext())
            ((XlinkNetListener)localIterator5.next()).onDataPointUpdate(locala.a, (List)locala.b, locala.c);
          continue;
          Iterator localIterator4 = c.b().iterator();
          while (localIterator4.hasNext())
          {
            XlinkNetListener localXlinkNetListener2 = (XlinkNetListener)localIterator4.next();
            if (locala.f)
            {
              localXlinkNetListener2.onRecvPipeSyncData(locala.g, locala.a, locala.d);
              continue;
            }
            localXlinkNetListener2.onRecvPipeData(locala.g, locala.a, locala.d);
          }
          continue;
          XDevice localXDevice = (XDevice)paramMessage.obj;
          Iterator localIterator3 = c.b().iterator();
          while (localIterator3.hasNext())
            ((XlinkNetListener)localIterator3.next()).onDeviceStateChanged(localXDevice, paramMessage.arg1);
          continue;
          EventNotify localEventNotify = (EventNotify)paramMessage.obj;
          Iterator localIterator2 = c.b().iterator();
          while (localIterator2.hasNext())
            ((XlinkNetListener)localIterator2.next()).onEventNotify(localEventNotify);
          continue;
          if (c.c().size() == 0)
          {
            MyLog.e("XlinkNetDispatcher", "XlinkNetListener not found");
            return;
          }
          int i = paramMessage.arg1;
          int j = paramMessage.arg2;
          Iterator localIterator1 = c.b().iterator();
          while (localIterator1.hasNext())
          {
            XlinkNetListener localXlinkNetListener1 = (XlinkNetListener)localIterator1.next();
            switch (i)
            {
            case 2:
            default:
              break;
            case 1:
              localXlinkNetListener1.onStart(j);
              break;
            case 3:
              localXlinkNetListener1.onLocalDisconnect(j);
              break;
            case 4:
              localXlinkNetListener1.onLogin(0);
              break;
            case 6:
              localXlinkNetListener1.onDisconnect(j);
              break;
            case 5:
              localXlinkNetListener1.onLogin(j);
            }
          }
        }
      }
    }
  };
  private static ScanDeviceListener c;
  private static ArrayList<XlinkNetListener> d = new ArrayList();

  public static void a(int paramInt1, int paramInt2)
  {
    Message localMessage = new Message();
    localMessage.what = 5;
    localMessage.arg1 = paramInt1;
    localMessage.arg2 = paramInt2;
    b.sendMessage(localMessage);
  }

  public static void a(int paramInt, XDevice paramXDevice)
  {
    MyLog.e("XlinkNetDispatcher", "device :" + paramXDevice.getMacAddress() + " status:" + paramInt);
    Message localMessage = new Message();
    localMessage.what = 4;
    localMessage.arg1 = paramInt;
    localMessage.obj = paramXDevice;
    b.sendMessage(localMessage);
  }

  public static void a(XDevice paramXDevice)
  {
    if (c != null)
    {
      Message localMessage = new Message();
      localMessage.what = 1;
      localMessage.obj = paramXDevice;
      b.sendMessage(localMessage);
    }
  }

  public static void a(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
  {
    Message localMessage = new Message();
    localMessage.what = 2;
    a locala = new a();
    locala.a = paramXDevice;
    locala.b = paramList;
    locala.c = paramInt;
    localMessage.obj = locala;
    b.sendMessage(localMessage);
  }

  public static void a(EventNotify paramEventNotify)
  {
    MyLog.e("XlinkNetDispatcher", "eventNotify :" + paramEventNotify.toString());
    Message localMessage = new Message();
    localMessage.what = 6;
    localMessage.obj = paramEventNotify;
    b.sendMessage(localMessage);
  }

  public static void a(ScanDeviceListener paramScanDeviceListener)
  {
    c = paramScanDeviceListener;
  }

  public static void a(String paramString)
  {
    if (a.get(paramString) == null)
      return;
    d.remove(a.get(paramString));
    a.remove(paramString);
  }

  public static void a(String paramString, XlinkNetListener paramXlinkNetListener)
  {
    if (a.get(paramString) != null)
    {
      d.remove(a.get(paramString));
      a.put(paramString, paramXlinkNetListener);
      d.add(paramXlinkNetListener);
      return;
    }
    a.put(paramString, paramXlinkNetListener);
    d.add(paramXlinkNetListener);
  }

  public static void a(boolean paramBoolean, short paramShort, XDevice paramXDevice, byte paramByte, byte[] paramArrayOfByte)
  {
    Message localMessage = new Message();
    localMessage.what = 3;
    a locala = new a();
    locala.f = paramBoolean;
    locala.a = paramXDevice;
    locala.d = paramArrayOfByte;
    locala.e = paramByte;
    locala.g = paramShort;
    localMessage.obj = locala;
    b.sendMessage(localMessage);
  }

  private static ArrayList<XlinkNetListener> d()
  {
    monitorenter;
    try
    {
      synchronized (d)
      {
        ArrayList localArrayList2 = d;
        monitorexit;
        return localArrayList2;
      }
    }
    finally
    {
      monitorexit;
    }
    throw localObject1;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.d.c
 * JD-Core Version:    0.6.0
 */