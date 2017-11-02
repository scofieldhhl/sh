package et.song.remote.face;

public abstract interface IR
{
  public abstract int GetBrandCount()
    throws Exception;

  public abstract int GetBrandCount(int paramInt)
    throws Exception;

  public abstract int GetBrandTotalCount()
    throws Exception;

  public abstract byte[] Search(int paramInt1, int paramInt2)
    throws Exception;

  public abstract byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception;
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.remote.face.IR
 * JD-Core Version:    0.6.0
 */