package et.song.etclass;

import et.song.remote.face.IR;

public class ETDeviceTV extends ETDevice
{
  private IR tv = null;

  public ETDeviceTV()
  {
    for (int i = 0; i < 25; i++)
    {
      ETKey localETKey = new ETKey();
      localETKey.SetState(1);
      localETKey.SetKey(0x2000 | 1 + i * 2);
      localETKey.SetDID(0);
      localETKey.SetBrandIndex(0);
      localETKey.SetBrandPos(0);
      localETKey.SetName("");
      localETKey.SetPos(0.0F, 0.0F);
      localETKey.SetRes(0);
      localETKey.SetRow(0);
      SetKey(localETKey);
    }
    int j = 0;
    while (true)
      if (j < 1)
      {
        ETKeyEx localETKeyEx = new ETKeyEx();
        localETKeyEx.SetKey(0x12000 | 1 + j * 2);
        try
        {
          localETKeyEx.SetValue(null);
          SetKeyEx(localETKeyEx);
          j++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public ETDeviceTV(int paramInt)
  {
    int i = 0;
    while (true)
      if (i < 25)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(3);
        localETKey.SetKey(0x2000 | 1 + i * 2);
        localETKey.SetRow(paramInt);
        try
        {
          localETKey.SetValue(this.tv.Search(paramInt, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException1)
        {
          while (true)
            localException1.printStackTrace();
        }
      }
    int j = 0;
    while (true)
      if (j < 1)
      {
        ETKeyEx localETKeyEx = new ETKeyEx();
        localETKeyEx.SetKey(0x12000 | 1 + j * 2);
        try
        {
          localETKeyEx.SetValue(null);
          SetKeyEx(localETKeyEx);
          j++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
  }

  public ETDeviceTV(int paramInt1, int paramInt2)
  {
    int i = 0;
    while (true)
      if (i < 25)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(2);
        localETKey.SetKey(0x2000 | 1 + i * 2);
        localETKey.SetBrandIndex(paramInt1);
        localETKey.SetBrandPos(paramInt2);
        try
        {
          localETKey.SetValue(this.tv.Search(paramInt1, paramInt2, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException1)
        {
          while (true)
            localException1.printStackTrace();
        }
      }
    int j = 0;
    while (true)
      if (j < 1)
      {
        ETKeyEx localETKeyEx = new ETKeyEx();
        localETKeyEx.SetKey(0x12000 | 1 + j * 2);
        try
        {
          localETKeyEx.SetValue(null);
          SetKeyEx(localETKeyEx);
          j++;
        }
        catch (Exception localException2)
        {
          while (true)
            localException2.printStackTrace();
        }
      }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETDeviceTV
 * JD-Core Version:    0.6.0
 */