package et.song.etclass;

public class ETDeviceCustom extends ETDevice
{
  public byte[] GetKeyValue(int paramInt)
    throws Exception
  {
    ETKey localETKey = GetKeyByValue(paramInt);
    if (localETKey.GetState() == 1)
      return Study(localETKey.GetValue());
    return null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETDeviceCustom
 * JD-Core Version:    0.6.0
 */