package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class PJT
  implements IR
{
  public PJT()
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
      int[][] arrayOfInt2 = new int[31][];
      int[] arrayOfInt3 = new int[20];
      arrayOfInt3[0] = 83;
      arrayOfInt3[1] = 84;
      arrayOfInt3[2] = 85;
      arrayOfInt3[4] = 1;
      arrayOfInt3[5] = 2;
      arrayOfInt3[6] = 50;
      arrayOfInt3[7] = 45;
      arrayOfInt3[8] = 46;
      arrayOfInt3[9] = 47;
      arrayOfInt3[10] = 48;
      arrayOfInt3[11] = 49;
      arrayOfInt3[12] = 3;
      arrayOfInt3[13] = 4;
      arrayOfInt3[14] = 6;
      arrayOfInt3[15] = 7;
      arrayOfInt3[16] = 8;
      arrayOfInt3[17] = 9;
      arrayOfInt3[18] = 10;
      arrayOfInt3[19] = 11;
      arrayOfInt2[0] = arrayOfInt3;
      int[] arrayOfInt4 = new int[19];
      arrayOfInt4[1] = 14;
      arrayOfInt4[2] = 15;
      arrayOfInt4[3] = 16;
      arrayOfInt4[4] = 1;
      arrayOfInt4[5] = 2;
      arrayOfInt4[6] = 45;
      arrayOfInt4[7] = 46;
      arrayOfInt4[8] = 47;
      arrayOfInt4[9] = 48;
      arrayOfInt4[10] = 49;
      arrayOfInt4[11] = 3;
      arrayOfInt4[12] = 4;
      arrayOfInt4[13] = 6;
      arrayOfInt4[14] = 7;
      arrayOfInt4[15] = 8;
      arrayOfInt4[16] = 9;
      arrayOfInt4[17] = 10;
      arrayOfInt4[18] = 11;
      arrayOfInt2[1] = arrayOfInt4;
      int[] arrayOfInt5 = new int[18];
      arrayOfInt5[1] = 24;
      arrayOfInt5[2] = 25;
      arrayOfInt5[3] = 1;
      arrayOfInt5[4] = 2;
      arrayOfInt5[5] = 45;
      arrayOfInt5[6] = 46;
      arrayOfInt5[7] = 47;
      arrayOfInt5[8] = 48;
      arrayOfInt5[9] = 49;
      arrayOfInt5[10] = 3;
      arrayOfInt5[11] = 4;
      arrayOfInt5[12] = 6;
      arrayOfInt5[13] = 7;
      arrayOfInt5[14] = 8;
      arrayOfInt5[15] = 9;
      arrayOfInt5[16] = 10;
      arrayOfInt5[17] = 11;
      arrayOfInt2[2] = arrayOfInt5;
      arrayOfInt2[3] = { 112, 100, 101, 102, 103, 104, 3, 4, 6, 14, 52, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 };
      arrayOfInt2[4] = { 3, 4, 6, 14, 15, 16, 17, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 };
      arrayOfInt2[5] = { 3, 4, 6, 9, 11, 14, 15, 16, 17, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34 };
      arrayOfInt2[6] = { 69, 70, 7, 8, 9, 10, 11, 26, 27, 33, 34, 18, 19, 20, 21, 28, 29, 30, 31, 32 };
      arrayOfInt2[7] = { 105, 106, 107, 108, 109, 110, 33, 34, 18, 19, 20, 21, 7, 8, 9, 10, 11, 26, 27, 28, 29, 30, 31, 32 };
      arrayOfInt2[8] = { 97, 99, 98, 12, 13, 14, 15, 16, 21, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
      arrayOfInt2[9] = { 86, 6, 7, 12, 13, 14, 15, 16, 21, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44 };
      arrayOfInt2[10] = { 5, 6, 7, 51, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
      int[] arrayOfInt6 = new int[19];
      arrayOfInt6[0] = 53;
      arrayOfInt6[2] = 1;
      arrayOfInt6[3] = 17;
      arrayOfInt6[4] = 18;
      arrayOfInt6[5] = 19;
      arrayOfInt6[6] = 20;
      arrayOfInt6[7] = 21;
      arrayOfInt6[8] = 22;
      arrayOfInt6[9] = 23;
      arrayOfInt6[10] = 24;
      arrayOfInt6[11] = 25;
      arrayOfInt6[12] = 26;
      arrayOfInt6[13] = 27;
      arrayOfInt6[14] = 28;
      arrayOfInt6[15] = 29;
      arrayOfInt6[16] = 30;
      arrayOfInt6[17] = 31;
      arrayOfInt6[18] = 32;
      arrayOfInt2[11] = arrayOfInt6;
      arrayOfInt2[12] = { 87, 88, 89, 90, 91, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32 };
      int[] arrayOfInt7 = new int[42];
      arrayOfInt7[0] = 53;
      arrayOfInt7[1] = 52;
      arrayOfInt7[2] = 51;
      arrayOfInt7[3] = 50;
      arrayOfInt7[4] = 49;
      arrayOfInt7[5] = 48;
      arrayOfInt7[6] = 47;
      arrayOfInt7[7] = 46;
      arrayOfInt7[8] = 45;
      arrayOfInt7[9] = 44;
      arrayOfInt7[10] = 23;
      arrayOfInt7[11] = 24;
      arrayOfInt7[12] = 25;
      arrayOfInt7[13] = 26;
      arrayOfInt7[14] = 27;
      arrayOfInt7[15] = 28;
      arrayOfInt7[16] = 4;
      arrayOfInt7[17] = 6;
      arrayOfInt7[18] = 7;
      arrayOfInt7[19] = 8;
      arrayOfInt7[20] = 9;
      arrayOfInt7[21] = 10;
      arrayOfInt7[22] = 11;
      arrayOfInt7[23] = 12;
      arrayOfInt7[24] = 13;
      arrayOfInt7[25] = 14;
      arrayOfInt7[26] = 15;
      arrayOfInt7[28] = 1;
      arrayOfInt7[29] = 29;
      arrayOfInt7[30] = 30;
      arrayOfInt7[31] = 31;
      arrayOfInt7[32] = 32;
      arrayOfInt7[33] = 12;
      arrayOfInt7[34] = 13;
      arrayOfInt7[35] = 14;
      arrayOfInt7[36] = 15;
      arrayOfInt7[37] = 16;
      arrayOfInt7[38] = 17;
      arrayOfInt7[39] = 22;
      arrayOfInt7[40] = 23;
      arrayOfInt7[41] = 24;
      arrayOfInt2[13] = arrayOfInt7;
      arrayOfInt2[14] = { 16, 17, 4, 6, 7, 8, 9, 10, 11, 23, 24, 25, 26, 27, 28, 12, 13, 14, 15 };
      arrayOfInt2[15] = { 23, 8, 9, 10, 11, 12, 24, 25, 26, 27, 28, 4, 6, 7, 13, 14, 15 };
      arrayOfInt2[16] = { 2, 3, 4, 29, 30, 31, 32, 12, 13, 14, 15, 16, 17, 22, 23, 24 };
      arrayOfInt2[17] = { 13, 14, 15, 16, 29, 30, 31, 32, 12, 17, 22, 23, 24 };
      arrayOfInt2[18] = { 92, 93, 94, 95, 96, 7, 8, 9, 29, 30, 31, 32, 12, 13, 14, 15, 16, 17, 22, 23, 24 };
      arrayOfInt2[19] = { 33, 34, 35, 36, 37, 38, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
      arrayOfInt2[20] = { 111, 5, 33, 34, 35, 36, 37, 38, 7, 8, 9, 10, 11, 12, 13, 14, 15 };
      int[] arrayOfInt8 = new int[25];
      arrayOfInt8[1] = 39;
      arrayOfInt8[2] = 40;
      arrayOfInt8[3] = 41;
      arrayOfInt8[4] = 42;
      arrayOfInt8[5] = 16;
      arrayOfInt8[6] = 17;
      arrayOfInt8[7] = 22;
      arrayOfInt8[8] = 23;
      arrayOfInt8[9] = 24;
      arrayOfInt8[10] = 25;
      arrayOfInt8[11] = 26;
      arrayOfInt8[12] = 27;
      arrayOfInt8[13] = 28;
      arrayOfInt8[14] = 29;
      arrayOfInt8[15] = 30;
      arrayOfInt8[16] = 31;
      arrayOfInt8[17] = 32;
      arrayOfInt8[18] = 33;
      arrayOfInt8[19] = 34;
      arrayOfInt8[20] = 18;
      arrayOfInt8[21] = 19;
      arrayOfInt8[22] = 20;
      arrayOfInt8[23] = 21;
      arrayOfInt8[24] = 35;
      arrayOfInt2[21] = arrayOfInt8;
      arrayOfInt2[22] = { 8, 9, 39, 40, 41, 42, 16, 17, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 18, 19, 20, 21, 35 };
      arrayOfInt2[23] = { 39, 40, 41, 42, 16, 17, 30, 31, 32, 33, 34, 18, 19, 20, 21, 35, 22, 23, 24, 25, 26, 27, 28, 29 };
      arrayOfInt2[24] = { 43, 44, 45, 47, 48, 49, 3, 4, 6, 7, 8, 9, 10, 11, 12 };
      arrayOfInt2[25] = { 3, 7, 8, 43, 44, 6, 7, 8, 9, 10, 11, 12, 45, 47, 48, 49, 3, 4 };
      arrayOfInt2[26] = { 43, 44, 45, 47, 48, 49, 15, 16, 17, 18, 3, 4, 6, 7, 8, 9, 10, 11, 12 };
      arrayOfInt2[27] = { 46, 47, 48, 49, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 18, 19, 20, 21, 35, 36 };
      arrayOfInt2[28] = { 6, 8, 46, 47, 48, 49, 24, 30, 31, 32, 33, 34, 25, 26, 27, 28, 29, 18, 19, 20, 21, 35, 36 };
      int[] arrayOfInt9 = new int[111];
      arrayOfInt9[0] = 48;
      arrayOfInt9[1] = 47;
      arrayOfInt9[2] = 46;
      arrayOfInt9[3] = 45;
      arrayOfInt9[4] = 44;
      arrayOfInt9[5] = 43;
      arrayOfInt9[6] = 42;
      arrayOfInt9[7] = 41;
      arrayOfInt9[8] = 40;
      arrayOfInt9[9] = 39;
      arrayOfInt9[10] = 38;
      arrayOfInt9[11] = 37;
      arrayOfInt9[12] = 36;
      arrayOfInt9[13] = 35;
      arrayOfInt9[14] = 34;
      arrayOfInt9[15] = 33;
      arrayOfInt9[16] = 32;
      arrayOfInt9[17] = 31;
      arrayOfInt9[18] = 30;
      arrayOfInt9[19] = 29;
      arrayOfInt9[20] = 28;
      arrayOfInt9[21] = 27;
      arrayOfInt9[22] = 26;
      arrayOfInt9[23] = 25;
      arrayOfInt9[24] = 24;
      arrayOfInt9[25] = 23;
      arrayOfInt9[26] = 22;
      arrayOfInt9[27] = 21;
      arrayOfInt9[28] = 20;
      arrayOfInt9[29] = 19;
      arrayOfInt9[30] = 18;
      arrayOfInt9[31] = 17;
      arrayOfInt9[32] = 16;
      arrayOfInt9[33] = 110;
      arrayOfInt9[34] = 109;
      arrayOfInt9[35] = 108;
      arrayOfInt9[36] = 107;
      arrayOfInt9[37] = 106;
      arrayOfInt9[38] = 105;
      arrayOfInt9[39] = 104;
      arrayOfInt9[40] = 103;
      arrayOfInt9[41] = 102;
      arrayOfInt9[42] = 101;
      arrayOfInt9[43] = 100;
      arrayOfInt9[44] = 99;
      arrayOfInt9[45] = 98;
      arrayOfInt9[46] = 97;
      arrayOfInt9[47] = 96;
      arrayOfInt9[48] = 95;
      arrayOfInt9[49] = 94;
      arrayOfInt9[50] = 93;
      arrayOfInt9[51] = 92;
      arrayOfInt9[52] = 91;
      arrayOfInt9[53] = 90;
      arrayOfInt9[54] = 89;
      arrayOfInt9[55] = 88;
      arrayOfInt9[56] = 87;
      arrayOfInt9[57] = 86;
      arrayOfInt9[58] = 85;
      arrayOfInt9[59] = 84;
      arrayOfInt9[60] = 83;
      arrayOfInt9[61] = 82;
      arrayOfInt9[62] = 81;
      arrayOfInt9[63] = 80;
      arrayOfInt9[64] = 79;
      arrayOfInt9[65] = 78;
      arrayOfInt9[66] = 77;
      arrayOfInt9[67] = 76;
      arrayOfInt9[68] = 75;
      arrayOfInt9[69] = 74;
      arrayOfInt9[70] = 73;
      arrayOfInt9[71] = 72;
      arrayOfInt9[72] = 71;
      arrayOfInt9[73] = 70;
      arrayOfInt9[74] = 69;
      arrayOfInt9[75] = 68;
      arrayOfInt9[76] = 67;
      arrayOfInt9[77] = 66;
      arrayOfInt9[78] = 65;
      arrayOfInt9[79] = 64;
      arrayOfInt9[80] = 63;
      arrayOfInt9[81] = 62;
      arrayOfInt9[82] = 61;
      arrayOfInt9[83] = 60;
      arrayOfInt9[84] = 59;
      arrayOfInt9[85] = 58;
      arrayOfInt9[86] = 57;
      arrayOfInt9[87] = 56;
      arrayOfInt9[88] = 55;
      arrayOfInt9[89] = 54;
      arrayOfInt9[90] = 53;
      arrayOfInt9[91] = 52;
      arrayOfInt9[92] = 51;
      arrayOfInt9[93] = 50;
      arrayOfInt9[94] = 49;
      arrayOfInt9[95] = 15;
      arrayOfInt9[96] = 14;
      arrayOfInt9[97] = 13;
      arrayOfInt9[98] = 12;
      arrayOfInt9[99] = 11;
      arrayOfInt9[100] = 10;
      arrayOfInt9[101] = 9;
      arrayOfInt9[102] = 8;
      arrayOfInt9[103] = 7;
      arrayOfInt9[104] = 6;
      arrayOfInt9[105] = 5;
      arrayOfInt9[106] = 4;
      arrayOfInt9[107] = 3;
      arrayOfInt9[108] = 2;
      arrayOfInt9[109] = 1;
      arrayOfInt2[29] = arrayOfInt9;
      arrayOfInt2[30] = { 113 };
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
 * Qualified Name:     et.song.remote.instance.PJT
 * JD-Core Version:    0.6.0
 */