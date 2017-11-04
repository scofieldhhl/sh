package io.xlink.wifi.sdk.listener;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.d.a;
import io.xlink.wifi.sdk.util.b;

public abstract class SetDeviceAuthorizeListener extends a
{
  public final void onResponse(e parame)
  {
    b.a(new Runnable(parame)
    {
      public void run()
      {
        SetDeviceAuthorizeListener.this.onSetLocalDeviceAuthorizeCode(this.a.a, this.a.b, Math.abs(this.a.c));
      }
    });
  }

  public abstract void onSetLocalDeviceAuthorizeCode(XDevice paramXDevice, int paramInt1, int paramInt2);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.listener.SetDeviceAuthorizeListener
 * JD-Core Version:    0.6.0
 */