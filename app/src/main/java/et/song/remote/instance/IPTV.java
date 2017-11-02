package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class IPTV
  implements IR
{
  public IPTV()
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
    if ((0xFF00 & paramInt2) != 8448);
    do
      return null;
    while ((paramInt2 < 8449) || (paramInt2 > 8493));
    return ETIR.SearchCode(8448, Index.GetIndex(paramInt1), paramInt2 & 0xFF);
  }

  public byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    if ((0xFF00 & paramInt3) != 8448);
    do
      return null;
    while ((paramInt3 < 8449) || (paramInt3 > 8493));
    return ETIR.SearchCode(8448, Index.GetIndex(paramInt1, paramInt2), paramInt3 & 0xFF);
  }

  private static final class Index
  {
    private static int[][] mTables;
    private static int[][] mTablesType;

    static
    {
      int[][] arrayOfInt1 = new int[26][];
      arrayOfInt1[0] = new int[1];
      arrayOfInt1[1] = { 1 };
      arrayOfInt1[2] = { 1 };
      arrayOfInt1[3] = { 2 };
      arrayOfInt1[4] = { 3 };
      arrayOfInt1[5] = { 4 };
      arrayOfInt1[6] = { 5 };
      arrayOfInt1[7] = { 6 };
      arrayOfInt1[8] = { 7 };
      arrayOfInt1[9] = { 8 };
      arrayOfInt1[10] = { 8 };
      arrayOfInt1[11] = { 9 };
      arrayOfInt1[12] = { 9 };
      arrayOfInt1[13] = { 10 };
      arrayOfInt1[14] = { 11 };
      arrayOfInt1[15] = { 12 };
      arrayOfInt1[16] = { 12 };
      arrayOfInt1[17] = { 13 };
      arrayOfInt1[18] = { 15 };
      arrayOfInt1[19] = { 17 };
      arrayOfInt1[20] = { 19 };
      arrayOfInt1[21] = { 20 };
      arrayOfInt1[22] = { 22 };
      arrayOfInt1[23] = { 23 };
      arrayOfInt1[24] = { 24 };
      arrayOfInt1[25] = { 25 };
      mTablesType = arrayOfInt1;
      int[][] arrayOfInt2 = new int[7][];
      int[] arrayOfInt = new int[5];
      arrayOfInt[1] = 33;
      arrayOfInt[2] = 56;
      arrayOfInt[3] = 61;
      arrayOfInt[4] = 17;
      arrayOfInt2[0] = arrayOfInt;
      arrayOfInt2[1] = { 50, 51, 52, 53, 54, 1, 2, 3, 4, 5, 19, 20 };
      arrayOfInt2[2] = { 6, 7 };
      arrayOfInt2[3] = { 50, 51, 52, 53, 54, 8, 22, 23 };
      arrayOfInt2[4] = { 50, 51, 52, 53, 54, 9, 10, 11, 24, 25 };
      arrayOfInt2[5] = { 12, 13, 15 };
      arrayOfInt2[6] = { 13, 50, 51, 52, 53, 54 };
      mTables = arrayOfInt2;
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
 * Qualified Name:     et.song.remote.instance.IPTV
 * JD-Core Version:    0.6.0
 */