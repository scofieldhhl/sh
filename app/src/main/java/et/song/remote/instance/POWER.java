package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class POWER
  implements IR
{
  public POWER()
  {
    Index.Init();
  }

  public int GetBrandCount()
    throws Exception
  {
    return Index.GetTotalCount();
  }

  public int GetBrandCount(int paramInt)
    throws Exception
  {
    return Index.GetBrandCount(paramInt);
  }

  public int GetBrandTotalCount()
    throws Exception
  {
    return Index.GetBrandTotalCount();
  }

  public byte[] Search(int paramInt1, int paramInt2)
    throws Exception
  {
    if ((0xFF00 & paramInt2) != 11008);
    do
      return null;
    while ((paramInt2 < 11009) || (paramInt2 > 11009));
    return ETIR.SearchCode(11008, Index.GetIndex(paramInt1), paramInt2 & 0xFF);
  }

  public byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    if ((0xFF00 & paramInt3) != 11008);
    do
      return null;
    while ((paramInt3 < 11009) || (paramInt3 > 11009));
    return ETIR.SearchCode(11008, Index.GetIndex(paramInt1, paramInt2), paramInt3 & 0xFF);
  }

  private static final class Index
  {
    private static int[][] mTables;
    private static int[][] mTablesType = new int[0][];

    static
    {
      mTables = new int[0][];
    }

    public static int GetBrandCount(int paramInt)
      throws Exception
    {
      return mTables[paramInt].length;
    }

    public static int GetBrandTotalCount()
      throws Exception
    {
      int i = 0;
      for (int j = 0; ; j++)
      {
        if (j >= mTables.length)
          return i;
        i += GetBrandCount(j);
      }
    }

    public static int GetIndex(int paramInt)
      throws Exception
    {
      return mTablesType[paramInt][0];
    }

    public static int GetIndex(int paramInt1, int paramInt2)
      throws Exception
    {
      return mTables[paramInt1][paramInt2];
    }

    public static int GetTotalCount()
      throws Exception
    {
      return mTables.length;
    }

    public static void Init()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.remote.instance.POWER
 * JD-Core Version:    0.6.0
 */