package io.xlink.wifi.sdk.c;

import io.xlink.wifi.sdk.XDevice;
import java.util.HashMap;

public class e
{
  private static final HashMap<String, g> d = new HashMap();
  public XDevice a;
  public int b;
  public int c;

  public e(int paramInt)
  {
    this.b = paramInt;
  }

  public static g a(int paramInt)
  {
    return (g)d.get(paramInt + "");
  }

  public static void a(String paramString)
  {
    d.remove(paramString);
  }

  public static void a(String paramString, g paramg)
  {
    d.put(paramString, paramg);
  }

  public static e b(int paramInt)
  {
    return new e(paramInt);
  }

  public static g b(String paramString)
  {
    return (g)d.get(paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.c.e
 * JD-Core Version:    0.6.0
 */