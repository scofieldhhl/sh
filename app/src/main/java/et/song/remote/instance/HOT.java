package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class HOT
  implements IR
{
  public HOT()
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
    if ((0xFF00 & paramInt2) != 40960);
    do
      return null;
    while ((paramInt2 < 40961) || (paramInt2 > 41003));
    return ETIR.SearchCode(40960, Index.GetIndex(paramInt1), paramInt2 & 0xFF);
  }

  public byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    if ((0xFF00 & paramInt3) != 40960);
    do
      return null;
    while ((paramInt3 < 40961) || (paramInt3 > 41003));
    return ETIR.SearchCode(40960, Index.GetIndex(paramInt1, paramInt2), paramInt3 & 0xFF);
  }

  private static final class Index
  {
    private static int[][] mTables;
    private static int[][] mTablesType;

    static
    {
      int[][] arrayOfInt1 = new int[1][];
      arrayOfInt1[0] = new int[1];
      mTablesType = arrayOfInt1;
      int[][] arrayOfInt2 = new int[11][];
      int[] arrayOfInt = new int[3];
      arrayOfInt[1] = 1;
      arrayOfInt[2] = 2;
      arrayOfInt2[0] = arrayOfInt;
      arrayOfInt2[1] = { 3 };
      arrayOfInt2[2] = { 4 };
      arrayOfInt2[3] = { 5 };
      arrayOfInt2[4] = { 6 };
      arrayOfInt2[5] = { 7 };
      arrayOfInt2[6] = { 8, 9, 10 };
      arrayOfInt2[7] = { 11 };
      arrayOfInt2[8] = { 12 };
      arrayOfInt2[9] = { 13 };
      arrayOfInt2[10] = { 14 };
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
 * Qualified Name:     et.song.remote.instance.HOT
 * JD-Core Version:    0.6.0
 */