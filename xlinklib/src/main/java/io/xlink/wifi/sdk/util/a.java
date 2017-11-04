package io.xlink.wifi.sdk.util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class a extends Thread
{
  private InetAddress a;
  private String b;

  public a(String paramString)
  {
    this.b = paramString;
  }

  private void a(InetAddress paramInetAddress)
  {
    monitorenter;
    try
    {
      this.a = paramInetAddress;
      monitorexit;
      return;
    }
    finally
    {
      localObject = finally;
      monitorexit;
    }
    throw localObject;
  }

  public InetAddress a()
  {
    monitorenter;
    try
    {
      if (this.a != null)
      {
        localInetAddress = this.a;
        return localInetAddress;
      }
      InetAddress localInetAddress = null;
    }
    finally
    {
      monitorexit;
    }
  }

  public void run()
  {
    try
    {
      a(InetAddress.getByName(this.b));
      return;
    }
    catch (UnknownHostException localUnknownHostException)
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.util.a
 * JD-Core Version:    0.6.0
 */