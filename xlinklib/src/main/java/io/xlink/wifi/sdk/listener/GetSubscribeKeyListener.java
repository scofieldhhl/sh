package io.xlink.wifi.sdk.listener;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.c.e;
import io.xlink.wifi.sdk.d.a;

public abstract class GetSubscribeKeyListener extends a
{
  public abstract void onGetSubscribekey(XDevice paramXDevice, int paramInt1, int paramInt2);

  public final void onResponse(e parame)
  {
    io.xlink.wifi.sdk.f.b.a().c(parame.a);
    io.xlink.wifi.sdk.util.b.a(new Runnable(parame)
    {
      public void run()
      {
        GetSubscribeKeyListener.this.onGetSubscribekey(this.a.a, this.a.b, this.a.a.getSubKey());
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.listener.GetSubscribeKeyListener
 * JD-Core Version:    0.6.0
 */