package io.xlink.wifi.sdk.listener;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import java.util.List;

public abstract interface XlinkNetListener
{
  public abstract void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt);

  public abstract void onDeviceStateChanged(XDevice paramXDevice, int paramInt);

  public abstract void onDisconnect(int paramInt);

  public abstract void onEventNotify(EventNotify paramEventNotify);

  public abstract void onLocalDisconnect(int paramInt);

  public abstract void onLogin(int paramInt);

  public abstract void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte);

  public abstract void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte);

  public abstract void onStart(int paramInt);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.listener.XlinkNetListener
 * JD-Core Version:    0.6.0
 */