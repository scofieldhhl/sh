package io.xlink.wifi.js.manage;

import android.content.Intent;
import android.util.SparseArray;
import com.ex.ltech.led.MyApp;
import com.google.gson.Gson;
import io.xlink.wifi.js.Constant;
import io.xlink.wifi.js.bean.Device;
import io.xlink.wifi.js.bean.Timer;
import io.xlink.wifi.js.util.XTGlobals;
import io.xlink.wifi.js.util.XlinkUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class DeviceManage
{
  private static String PASSWORD;
  private static final String TAG = "DeviceManage";
  public static ConcurrentHashMap<String, Device> deviceMap = new ConcurrentHashMap();
  private static DeviceManage instance;
  public static final ArrayList<Device> listDev;
  public static XDevice xDevice;

  static
  {
    PASSWORD = "password";
    Iterator localIterator = XTGlobals.getAllProperty().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      try
      {
        JSONObject localJSONObject = new JSONObject((String)localEntry.getValue());
        XDevice localXDevice = XlinkAgent.JsonToDevice(localJSONObject);
        if (localXDevice == null)
          continue;
        Device localDevice = new Device(localXDevice);
        if (!localJSONObject.isNull(PASSWORD))
          localDevice.setPassword(localJSONObject.getString(PASSWORD));
        deviceMap.put(localXDevice.getMacAddress(), localDevice);
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    }
    listDev = new ArrayList();
  }

  public static DeviceManage getInstance()
  {
    if (instance == null)
      instance = new DeviceManage();
    return instance;
  }

  public static XDevice getxDevice()
  {
    return xDevice;
  }

  public static byte weekToByte(ArrayList<Integer> paramArrayList)
  {
    byte b = 0;
    if ((paramArrayList == null) || (paramArrayList.size() == 0))
      return 0;
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      if (localInteger.intValue() > 6)
        continue;
      b = XlinkUtils.setByteBit(localInteger.intValue(), b);
    }
    if (b == 0)
      return b;
    return XlinkUtils.setByteBit(7, b);
  }

  public void addDevice(Device paramDevice)
  {
    deviceMap.put(paramDevice.getMacAddress(), paramDevice);
    saveDevice(paramDevice);
  }

  public void addDevice(XDevice paramXDevice)
  {
    Device localDevice1 = (Device)deviceMap.get(paramXDevice.getMacAddress());
    if (localDevice1 != null)
    {
      localDevice1.setxDevice(paramXDevice);
      deviceMap.put(paramXDevice.getMacAddress(), localDevice1);
      saveDevice(localDevice1);
      return;
    }
    Device localDevice2 = new Device(paramXDevice);
    deviceMap.put(paramXDevice.getMacAddress(), localDevice2);
    saveDevice(localDevice2);
  }

  public void clearAllDevice()
  {
    monitorenter;
    try
    {
      Iterator localIterator = getDevices().iterator();
      while (localIterator.hasNext())
        XTGlobals.deleteProperty(((Device)localIterator.next()).getMacAddress());
    }
    finally
    {
      monitorexit;
    }
    deviceMap.clear();
    monitorexit;
  }

  public Timer createTimer(String paramString)
  {
    ArrayList localArrayList = getDevice(paramString).getTimers();
    if (localArrayList.size() >= 19);
    int i;
    do
    {
      return null;
      if (localArrayList.size() == 0)
        return new Timer(1);
      i = -1;
      SparseArray localSparseArray = new SparseArray();
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        Timer localTimer = (Timer)localIterator.next();
        localSparseArray.put(localTimer.getId(), localTimer);
      }
      for (int j = 1; j < 19; j++)
      {
        if (localSparseArray.get(j) != null)
          continue;
        i = j;
      }
    }
    while (i == -1);
    return new Timer(i);
  }

  public Device getDevice(int paramInt)
  {
    Iterator localIterator = getDevices().iterator();
    Object localObject;
    while (true)
    {
      boolean bool = localIterator.hasNext();
      localObject = null;
      if (!bool)
        break;
      Device localDevice = (Device)localIterator.next();
      if (localDevice.getXDevice().getDeviceId() != paramInt)
        continue;
      localObject = localDevice;
    }
    return localObject;
  }

  public Device getDevice(XDevice paramXDevice)
  {
    return (Device)deviceMap.get(paramXDevice.getMacAddress());
  }

  public Device getDevice(String paramString)
  {
    return (Device)deviceMap.get(paramString);
  }

  public ArrayList<Device> getDevices()
  {
    monitorenter;
    try
    {
      listDev.clear();
      Iterator localIterator = deviceMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Entry localEntry = (Entry)localIterator.next();
        listDev.add(localEntry.getValue());
      }
    }
    finally
    {
      monitorexit;
    }
    ArrayList localArrayList = listDev;
    monitorexit;
    return localArrayList;
  }

  public String getWeekList(ArrayList<Integer> paramArrayList)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    Iterator localIterator = paramArrayList.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      localStringBuffer.append(localInteger + " ");
    }
    return localStringBuffer.toString();
  }

  public boolean isRepeatDate(String paramString, int paramInt)
  {
    Iterator localIterator = getDevice(paramString).getTimers().iterator();
    while (localIterator.hasNext())
      if (paramInt == ((Timer)localIterator.next()).getDateSum())
        return true;
    return false;
  }

  public boolean isRepeatDate(String paramString, Timer paramTimer)
  {
    Iterator localIterator = getDevice(paramString).getTimers().iterator();
    while (localIterator.hasNext())
    {
      Timer localTimer = (Timer)localIterator.next();
      if ((paramTimer.getId() != localTimer.getId()) && (paramTimer.getDateSum() == localTimer.getDateSum()))
        return true;
    }
    return false;
  }

  public void notificationSocket(Device paramDevice, int paramInt)
  {
    Intent localIntent = new Intent(Constant.BROADCAST_SOCKET_STATUS);
    localIntent.putExtra("device-mac", paramDevice.getMacAddress());
    localIntent.putExtra("status", paramInt);
    MyApp.getApp().sendBroadcast(localIntent);
  }

  public void notificationTimer(Device paramDevice)
  {
    Intent localIntent = new Intent(Constant.BROADCAST_TIMER_UPDATE);
    localIntent.putExtra("device-mac", paramDevice.getMacAddress());
    MyApp.getApp().sendBroadcast(localIntent);
  }

  public void parseByteTimer(Device paramDevice, byte[] paramArrayOfByte)
  {
    ArrayList localArrayList;
    int i;
    int j;
    if ((paramArrayOfByte[0] == 2) || (paramArrayOfByte[0] == 3))
    {
      localArrayList = getDevice(paramDevice.getMacAddress()).getTimers();
      if (localArrayList == null)
        localArrayList = new ArrayList();
      i = 1;
      j = 0;
      if ((0 == 0) && (paramArrayOfByte.length > i));
    }
    label262: 
    do
    {
      do
      {
        if (j != 0)
        {
          paramDevice.setTimers(localArrayList);
          updateNoSaveDevice(paramDevice);
          notificationTimer(paramDevice);
        }
        return;
      }
      while (paramArrayOfByte.length < i + 6);
      byte[] arrayOfByte = XlinkUtils.subBytes(paramArrayOfByte, i, 6);
      i += 6;
      int k = arrayOfByte[0];
      if (k > 20)
        break;
      if (k == 0)
      {
        if (paramDevice.getTiming() == null)
          paramDevice.setTiming(new Timer(arrayOfByte));
        while (true)
        {
          j = 1;
          break;
          paramDevice.getTiming().update(arrayOfByte);
        }
      }
      Iterator localIterator = localArrayList.iterator();
      Object localObject;
      while (true)
      {
        boolean bool = localIterator.hasNext();
        localObject = null;
        if (!bool)
          break;
        Timer localTimer = (Timer)localIterator.next();
        if (localTimer.getId() != k)
          continue;
        localObject = localTimer;
      }
      if (localObject == null)
      {
        localObject = new Timer(arrayOfByte);
        localArrayList.add(localObject);
        j = 1;
      }
      while (true)
      {
        if (((Timer)localObject).isExist())
          break label262;
        localArrayList.remove(localObject);
        break;
        j = 1;
        ((Timer)localObject).update(arrayOfByte);
      }
      break;
    }
    while (((paramArrayOfByte[0] != 4) && (paramArrayOfByte[0] != 1)) || (paramArrayOfByte.length != 2));
    notificationSocket(paramDevice, paramArrayOfByte[1]);
  }

  public void removeDevice(XDevice paramXDevice)
  {
    removeDevice(paramXDevice.getMacAddress());
  }

  public void removeDevice(String paramString)
  {
    deviceMap.remove(paramString);
    XlinkAgent.getInstance().removeDevice(paramString);
    XTGlobals.deleteProperty(paramString);
  }

  public void saveDevice(Device paramDevice)
  {
    new Gson().toJson(paramDevice);
    JSONObject localJSONObject = XlinkAgent.deviceToJson(paramDevice.getXDevice());
    if (localJSONObject == null)
      return;
    if (paramDevice.getPassword() != null);
    try
    {
      localJSONObject.put(PASSWORD, paramDevice.getPassword());
      XTGlobals.setProperty(paramDevice.getMacAddress(), localJSONObject.toString());
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        localJSONException.printStackTrace();
    }
  }

  public void saveDevices(List<Device> paramList)
  {
    for (int i = 0; i < paramList.size(); i++)
      saveDevice((Device)paramList.get(i));
  }

  public void setAuth(String paramString1, String paramString2)
  {
    Device localDevice = (Device)deviceMap.get(paramString1);
    if (localDevice != null)
    {
      localDevice.setPassword(paramString2);
      updateDevice(localDevice);
    }
  }

  public void updateDevice(Device paramDevice)
  {
    deviceMap.remove(paramDevice.getMacAddress());
    deviceMap.put(paramDevice.getMacAddress(), paramDevice);
    saveDevice(paramDevice);
  }

  public void updateDevice(XDevice paramXDevice)
  {
    xDevice = paramXDevice;
    Device localDevice = (Device)deviceMap.get(paramXDevice.getMacAddress());
    if (localDevice == null)
      return;
    localDevice.setxDevice(paramXDevice);
    updateDevice(localDevice);
  }

  public void updateNoSaveDevice(Device paramDevice)
  {
    deviceMap.remove(paramDevice.getMacAddress());
    deviceMap.put(paramDevice.getMacAddress(), paramDevice);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.manage.DeviceManage
 * JD-Core Version:    0.6.0
 */