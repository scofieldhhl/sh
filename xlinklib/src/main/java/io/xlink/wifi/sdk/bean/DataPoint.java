package io.xlink.wifi.sdk.bean;

import java.io.Serializable;

public class DataPoint
  implements Serializable
{
  private int a;
  private Object b;
  private int c;
  private int d;
  private int e;
  private String f;
  private String g;
  private String h;

  private DataPoint()
  {
  }

  public DataPoint(int paramInt1, int paramInt2)
  {
    this.a = paramInt1;
    this.c = paramInt2;
    switch (paramInt2)
    {
    default:
      return;
    case 1:
      this.b = Boolean.valueOf(false);
      return;
    case 2:
      this.b = Byte.valueOf(0);
      return;
    case 3:
      this.b = Short.valueOf(0);
      return;
    case 4:
      this.b = Integer.valueOf(0);
      return;
    case 5:
      this.b = Float.valueOf(0.0F);
      return;
    case 6:
      this.b = "";
      return;
    case 7:
    }
    this.b = new byte[] { 0 };
  }

  public static long getSerialVersionUID()
  {
    return 1L;
  }

  public static DataPoint parseDataPoint(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    DataPoint localDataPoint = new DataPoint(paramInt1, paramInt2);
    switch (paramInt2)
    {
    default:
      return localDataPoint;
    case 1:
      int i = paramArrayOfByte[0];
      boolean bool = false;
      if (i == 0);
      while (true)
      {
        localDataPoint.setValueOfBool(bool);
        return localDataPoint;
        bool = true;
      }
    case 2:
      localDataPoint.setValueOfByte(paramArrayOfByte[0]);
      return localDataPoint;
    case 3:
      localDataPoint.setValueOfShort(io.xlink.wifi.sdk.util.b.e(paramArrayOfByte));
      return localDataPoint;
    case 4:
      localDataPoint.setValueOfInt(io.xlink.wifi.sdk.util.b.f(paramArrayOfByte));
      return localDataPoint;
    case 5:
      localDataPoint.setValueOfFloat(io.xlink.wifi.sdk.util.b.g(paramArrayOfByte));
      return localDataPoint;
    case 6:
      localDataPoint.setValueOfString(new String(paramArrayOfByte));
      return localDataPoint;
    case 7:
    }
    localDataPoint.setValueOfByteArray(paramArrayOfByte);
    return localDataPoint;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    DataPoint localDataPoint;
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass()))
        return false;
      localDataPoint = (DataPoint)paramObject;
    }
    while (this.a == localDataPoint.a);
    return false;
  }

  public byte[] getByteValue()
  {
    io.xlink.wifi.sdk.a.b localb = new io.xlink.wifi.sdk.a.b(1000);
    localb.c(this.a);
    switch (this.c)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    while (true)
    {
      byte[] arrayOfByte = new byte[localb.b()];
      System.arraycopy(localb.a(), 0, arrayOfByte, 0, localb.b());
      return arrayOfByte;
      if (!(this.b instanceof Boolean))
        continue;
      localb.a((short)1);
      if (((Boolean)this.b).booleanValue());
      for (int i = 1; ; i = 0)
      {
        localb.c(i);
        break;
      }
      if (!(this.b instanceof Byte))
        continue;
      localb.a((short)1);
      localb.a(((Byte)this.b).byteValue());
      continue;
      if (!(this.b instanceof Short))
        continue;
      localb.a(8194);
      localb.a(((Short)this.b).shortValue());
      continue;
      if (!(this.b instanceof Integer))
        continue;
      localb.a(16388);
      localb.b(((Integer)this.b).intValue());
      continue;
      if (!(this.b instanceof Float))
        continue;
      localb.a(28676);
      localb.a(((Float)this.b).floatValue());
      continue;
      if (!(this.b instanceof String))
        continue;
      localb.a((short)(36864 + ((String)this.b).getBytes().length));
      localb.a(((String)this.b).getBytes());
      continue;
      if (!(this.b instanceof byte[]))
        continue;
      localb.a((short)(40960 + ((byte[])(byte[])this.b).length));
      localb.a((byte[])(byte[])this.b);
    }
  }

  public String getDescription()
  {
    return this.g;
  }

  public int getIndex()
  {
    return this.a;
  }

  public int getMax()
  {
    return this.e;
  }

  public int getMin()
  {
    return this.d;
  }

  public String getName()
  {
    return this.h;
  }

  public String getSymbol()
  {
    return this.f;
  }

  public int getType()
  {
    return this.c;
  }

  public Object getValue()
  {
    return this.b;
  }

  public Object getValueOfUnsigned()
  {
    switch (this.c)
    {
    default:
      return null;
    case 1:
      return this.b;
    case 2:
      return Integer.valueOf(0xFF & ((Byte)this.b).byteValue());
    case 3:
      return Integer.valueOf(0xFFFF & ((Short)this.b).shortValue());
    case 4:
      return Integer.valueOf(0xFFFFFFFF & ((Integer)this.b).intValue());
    case 5:
      return this.b;
    case 6:
      return this.b;
    case 7:
    }
    return this.b;
  }

  public void setDescription(String paramString)
  {
    this.g = paramString;
  }

  public void setIndex(int paramInt)
  {
    this.a = paramInt;
  }

  public void setMax(int paramInt)
  {
    this.e = paramInt;
  }

  public void setMin(int paramInt)
  {
    this.d = paramInt;
  }

  public void setName(String paramString)
  {
    this.h = paramString;
  }

  public void setSymbol(String paramString)
  {
    this.f = paramString;
  }

  public void setType(int paramInt)
  {
    this.c = paramInt;
  }

  public void setValue(Object paramObject)
  {
    switch (this.c)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    }
    do
    {
      do
      {
        do
        {
          do
          {
            do
            {
              do
              {
                do
                {
                  return;
                  if (!(paramObject instanceof Boolean))
                    continue;
                  setValueOfBool(((Boolean)paramObject).booleanValue());
                }
                while (!(paramObject instanceof Byte));
                if (((Byte)paramObject).byteValue() != 0);
                for (boolean bool = true; ; bool = false)
                {
                  setValueOfBool(bool);
                  return;
                }
              }
              while (!(paramObject instanceof Byte));
              setValueOfByte(((Byte)paramObject).byteValue());
              return;
            }
            while (!(paramObject instanceof Short));
            setValueOfShort(((Short)paramObject).shortValue());
            return;
          }
          while (!(paramObject instanceof Integer));
          setValueOfInt(((Integer)paramObject).intValue());
          return;
        }
        while (!(paramObject instanceof Float));
        setValueOfFloat(((Float)paramObject).floatValue());
        return;
      }
      while (!(paramObject instanceof String));
      setValueOfString((String)paramObject);
      return;
    }
    while (!(paramObject instanceof byte[]));
    setValueOfByteArray((byte[])(byte[])paramObject);
  }

  public void setValueOfBool(boolean paramBoolean)
  {
    if (this.c == 1)
      this.b = Boolean.valueOf(paramBoolean);
  }

  public void setValueOfByte(byte paramByte)
  {
    if (this.c == 2)
      this.b = Byte.valueOf(paramByte);
  }

  public void setValueOfByteArray(byte[] paramArrayOfByte)
  {
    if (this.c == 7)
      this.b = paramArrayOfByte;
  }

  public void setValueOfFloat(float paramFloat)
  {
    if (this.c == 5)
      this.b = Float.valueOf(paramFloat);
  }

  public void setValueOfInt(int paramInt)
  {
    if (this.c == 4)
      this.b = Integer.valueOf(paramInt);
  }

  public void setValueOfShort(short paramShort)
  {
    if (this.c == 3)
      this.b = Short.valueOf(paramShort);
  }

  public void setValueOfString(String paramString)
  {
    if (this.c == 6)
      this.b = paramString;
  }

  public String toString()
  {
    return "index :" + this.a + " type:" + this.c + " value:" + this.b;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.bean.DataPoint
 * JD-Core Version:    0.6.0
 */