package io.xlink.wifi.sdk.listener;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.d.a;
import io.xlink.wifi.sdk.util.b;

public abstract class ConnectDeviceListener extends a
{
  public abstract void onConnectDevice(XDevice paramXDevice, int paramInt);

  public final void onResponse(e parame)
  {
    b.a(new Runnable(parame)
    {
      public void run()
      {
        ConnectDeviceListener.this.onConnectDevice(this.a.a, this.a.b);
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.listener.ConnectDeviceListener
 * JD-Core Version:    0.6.0
 */