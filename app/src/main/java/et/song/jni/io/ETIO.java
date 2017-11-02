package et.song.jni.io;

import et.song.tg.face.IFinish;
import et.song.tg.face.ITg;

public final class ETIO
  implements ITg
{
  static
  {
    System.loadLibrary("et_jni_io");
  }

  private static native int IOClose();

  private static native int IOOpen();

  private static native int IORead(byte[] paramArrayOfByte, int paramInt);

  private static native int IOWrite(byte[] paramArrayOfByte, int paramInt);

  public void close()
    throws Exception
  {
    IOClose();
  }

  public void ioctl(int paramInt)
    throws Exception
  {
  }

  public void open(IFinish paramIFinish)
    throws Exception
  {
    paramIFinish.OpenCallbk(IOOpen());
  }

  public int read(byte[] paramArrayOfByte, int paramInt)
    throws Exception
  {
    int i = IORead(paramArrayOfByte, paramInt);
    if ((i == -1) || (i == 0))
      i = -1001;
    return i;
  }

  public int write(byte[] paramArrayOfByte, int paramInt)
    throws Exception
  {
    return IOWrite(paramArrayOfByte, paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.jni.io.ETIO
 * JD-Core Version:    0.6.0
 */