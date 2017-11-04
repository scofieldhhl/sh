package io.xlink.wifi.sdk.listener;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.d.a;

public abstract class SubscribeDeviceListener extends a
{
  public final void onResponse(e parame)
  {
    io.xlink.wifi.sdk.f.b.a().c(parame.a);
    io.xlink.wifi.sdk.util.b.a(new Runnable(parame)
    {
      public void run()
      {
        SubscribeDeviceListener.this.onSubscribeDevice(this.a.a, this.a.b);
      }
    });
  }

  public abstract void onSubscribeDevice(XDevice paramXDevice, int paramInt);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.listener.SubscribeDeviceListener
 * JD-Core Version:    0.6.0
 */