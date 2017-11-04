package io.xlink.wifi.sdk.a;

import java.nio.ByteBuffer;

public class b
{
  private byte[] a;
  private int b;

  public b(int paramInt)
  {
    this.a = new byte[paramInt];
    this.b = 0;
  }

  public void a(byte paramByte)
  {
    this.a[this.b] = paramByte;
    this.b = (1 + this.b);
  }

  public void a(float paramFloat)
  {
    a(ByteBuffer.allocate(4).putFloat(paramFloat).array());
  }

  public void a(int paramInt)
  {
    a((short)paramInt);
  }

  public void a(short paramShort)
  {
    a(io.xlink.wifi.sdk.util.b.a(paramShort));
  }

  public void a(byte[] paramArrayOfByte)
  {
    int i = paramArrayOfByte.length;
    System.arraycopy(paramArrayOfByte, 0, this.a, this.b, i);
    this.b = (i + this.b);
  }

  public byte[] a()
  {
    return this.a;
  }

  public int b()
  {
    return this.b;
  }

  public void b(int paramInt)
  {
    a(io.xlink.wifi.sdk.util.b.b(paramInt));
  }

  public void c(int paramInt)
  {
    a((byte)paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.a.b
 * JD-Core Version:    0.6.0
 */