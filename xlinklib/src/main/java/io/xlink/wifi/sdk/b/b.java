package io.xlink.wifi.sdk.b;

public class b
{
  private int a = -1;
  private boolean b = false;
  private byte c;
  private int d = -1;
  private int e = 0;
  private byte[] f;

  public b(byte[] paramArrayOfByte)
  {
    this.f = paramArrayOfByte;
    this.c = paramArrayOfByte[0];
    this.a = a(this.c);
    this.b = c(this.c);
    this.e = b(this.c);
    this.d = a(paramArrayOfByte);
  }

  private int a(byte paramByte)
  {
    return 0x0 | 0xF & paramByte >> 4;
  }

  private int a(byte[] paramArrayOfByte)
  {
    return 0x0 | 0xFF & paramArrayOfByte[4] | (0xFF & paramArrayOfByte[3]) << 8 | (0xFF & paramArrayOfByte[2]) << 16 | (0xFF & paramArrayOfByte[1]) << 24;
  }

  private int b(byte paramByte)
  {
    return 0x0 | paramByte & 0x7;
  }

  private boolean c(byte paramByte)
  {
    return (0x0 | 0x1 & paramByte >> 3) == 1;
  }

  public int a()
  {
    return this.d;
  }

  public int b()
  {
    return this.e;
  }

  public boolean c()
  {
    return (this.a > 0) && (this.a < 16);
  }

  public int d()
  {
    return this.a;
  }

  public boolean e()
  {
    return this.b;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = io.xlink.wifi.sdk.util.b.b(this.c);
    arrayOfObject[1] = Integer.valueOf(this.a);
    if (this.b);
    for (String str = "true"; ; str = "false")
    {
      arrayOfObject[2] = str;
      return String.format("Fix message header %s; type: %d; response: %s;", arrayOfObject);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.b.b
 * JD-Core Version:    0.6.0
 */