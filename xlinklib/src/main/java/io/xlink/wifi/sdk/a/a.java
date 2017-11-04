package io.xlink.wifi.sdk.a;

import io.xlink.wifi.sdk.util.b;

public class a
{
  private int a;
  private byte[] b;

  public a(byte[] paramArrayOfByte, int paramInt)
  {
    this.b = paramArrayOfByte;
    this.a = paramInt;
  }

  private void b(int paramInt)
  {
    this.a = (paramInt + this.a);
    if (this.b.length == this.a);
  }

  public void a()
  {
    this.b = null;
    this.a = 0;
  }

  public byte[] a(int paramInt)
  {
    byte[] arrayOfByte = new byte[paramInt];
    System.arraycopy(this.b, this.a, arrayOfByte, 0, arrayOfByte.length);
    b(paramInt);
    return arrayOfByte;
  }

  public byte[] b()
  {
    return this.b;
  }

  public int c()
  {
    return this.a;
  }

  public byte d()
  {
    int i = this.b[this.a];
    b(1);
    return i;
  }

  public byte[] e()
  {
    byte[] arrayOfByte = new byte[this.b.length - this.a];
    System.arraycopy(this.b, this.a, arrayOfByte, 0, arrayOfByte.length);
    this.a += arrayOfByte.length;
    a();
    return arrayOfByte;
  }

  public int f()
  {
    byte[] arrayOfByte = new byte[4];
    System.arraycopy(this.b, this.a, arrayOfByte, 0, arrayOfByte.length);
    b(arrayOfByte.length);
    return b.b(arrayOfByte);
  }

  public short g()
  {
    int i = b.a(this.b, this.a);
    b(2);
    return i;
  }

  public String toString()
  {
    return "当前索引: " + this.a + " 数据总长度 ：" + this.b.length + " " + this.b;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.a.a
 * JD-Core Version:    0.6.0
 */