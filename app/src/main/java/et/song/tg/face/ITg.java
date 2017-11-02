package et.song.tg.face;

public abstract interface ITg
{
  public abstract void close()
    throws Exception;

  public abstract void ioctl(int paramInt)
    throws Exception;

  public abstract void open(IFinish paramIFinish)
    throws Exception;

  public abstract int read(byte[] paramArrayOfByte, int paramInt)
    throws Exception;

  public abstract int write(byte[] paramArrayOfByte, int paramInt)
    throws Exception;
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.tg.face.ITg
 * JD-Core Version:    0.6.0
 */