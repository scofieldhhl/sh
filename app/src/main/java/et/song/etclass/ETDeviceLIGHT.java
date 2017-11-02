package et.song.etclass;

import et.song.remote.face.IR;

public class ETDeviceLIGHT extends ETDevice
{
  private IR light = null;

  public ETDeviceLIGHT()
  {
    int i = 0;
    while (true)
      if (i < 23)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(1);
        localETKey.SetKey(0xE000 | 1 + i * 2);
        try
        {
          localETKey.SetValue("".getBytes());
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public ETDeviceLIGHT(int paramInt)
  {
    int i = 0;
    while (true)
      if (i < 23)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(3);
        localETKey.SetKey(0xE000 | 1 + i * 2);
        localETKey.SetRow(paramInt);
        try
        {
          localETKey.SetValue(this.light.Search(paramInt, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }

  public ETDeviceLIGHT(int paramInt1, int paramInt2)
  {
    int i = 0;
    while (true)
      if (i < 23)
      {
        ETKey localETKey = new ETKey();
        localETKey.SetState(2);
        localETKey.SetKey(0xE000 | 1 + i * 2);
        localETKey.SetBrandIndex(paramInt1);
        localETKey.SetBrandPos(paramInt2);
        try
        {
          localETKey.SetValue(this.light.Search(paramInt1, paramInt2, localETKey.GetKey()));
          SetKey(localETKey);
          i++;
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETDeviceLIGHT
 * JD-Core Version:    0.6.0
 */