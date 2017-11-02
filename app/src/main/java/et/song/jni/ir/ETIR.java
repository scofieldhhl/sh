package et.song.jni.ir;

import et.song.remote.face.IR;
import et.song.remote.instance.AIR;
import et.song.remote.instance.AP;
import et.song.remote.instance.DC;
import et.song.remote.instance.DVD;
import et.song.remote.instance.FANS;
import et.song.remote.instance.HOT;
import et.song.remote.instance.IPTV;
import et.song.remote.instance.LIGHT;
import et.song.remote.instance.PJT;
import et.song.remote.instance.POWER;
import et.song.remote.instance.SLR;
import et.song.remote.instance.STB;
import et.song.remote.instance.TV;
import et.song.tool.ETEnv;
import et.song.tool.ETTool;

public final class ETIR
{
  private static final String libSoName = "et_jni_ir";

  static
  {
    System.loadLibrary("et_jni_ir");
  }

  public static IR Builder(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 8192:
      return new TV();
    case 8448:
      return new IPTV();
    case 16384:
      return new STB();
    case 24576:
      return new DVD();
    case 40960:
      return new PJT();
    case 57344:
      return new LIGHT();
    case 32768:
      return new FANS();
    case 49152:
      return new AIR();
    case 8960:
      return new DC();
    case 11008:
      return new POWER();
    case 11520:
      return new SLR();
    case 12032:
      return new AP();
    case 12544:
    }
    return new HOT();
  }

  private static native int ClearData(int paramInt1, int paramInt2, int paramInt3);

  public static long GetCode(int paramInt1, int paramInt2, int paramInt3)
  {
    ClearData(paramInt1, paramInt2, paramInt3);
    return ETTool.T();
  }

  private static native void Init(String paramString);

  public static void InitIR()
  {
    Init(ETEnv.getExternalStorageDirectory() + "/");
  }

  public static int SearchAirCode(int paramInt1, int paramInt2, int paramInt3)
  {
    InitIR();
    return SearchAirData(paramInt1, paramInt2, paramInt3);
  }

  private static native int SearchAirData(int paramInt1, int paramInt2, int paramInt3);

  public static int SearchCode(int paramInt1, int paramInt2)
  {
    InitIR();
    return SearchData(paramInt1, 0, paramInt2);
  }

  public static byte[] SearchCode(int paramInt1, int paramInt2, int paramInt3)
  {
    return SearchKeyData(paramInt1, paramInt2, paramInt3);
  }

  private static native int SearchData(int paramInt1, int paramInt2, int paramInt3);

  private static native byte[] SearchKeyData(int paramInt1, int paramInt2, int paramInt3);

  public static byte[] StudyCode(byte[] paramArrayOfByte, int paramInt)
  {
    if (paramArrayOfByte.length == 112)
      return StudyKeyCode(paramArrayOfByte, paramInt);
    byte[] arrayOfByte = new byte['è'];
    arrayOfByte[0] = 48;
    int i = (byte)(0 + arrayOfByte[0]);
    arrayOfByte[1] = 3;
    int j = (byte)(i + arrayOfByte[1]);
    for (int k = 1; ; k++)
    {
      if (k >= -2 + paramArrayOfByte.length)
      {
        arrayOfByte['ç'] = j;
        return arrayOfByte;
      }
      arrayOfByte[(k + 1)] = paramArrayOfByte[k];
      j = (byte)(j + paramArrayOfByte[k]);
    }
  }

  public static byte[] StudyCodeEx(String paramString, byte[] paramArrayOfByte, int paramInt)
  {
    if (paramString.equals("0"))
      return StudyKeyCode(paramArrayOfByte, paramInt);
    if (paramString.equals("1"))
    {
      byte[] arrayOfByte = new byte['è'];
      arrayOfByte[0] = 48;
      int i = (byte)(0 + arrayOfByte[0]);
      arrayOfByte[1] = 3;
      int j = (byte)(i + arrayOfByte[1]);
      for (int k = 1; ; k++)
      {
        if (k >= -2 + paramArrayOfByte.length)
        {
          arrayOfByte['ç'] = j;
          return arrayOfByte;
        }
        arrayOfByte[(k + 1)] = paramArrayOfByte[k];
        j = (byte)(j + paramArrayOfByte[k]);
      }
    }
    return null;
  }

  private static native byte[] StudyKeyCode(byte[] paramArrayOfByte, int paramInt);
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.jni.ir.ETIR
 * JD-Core Version:    0.6.0
 */