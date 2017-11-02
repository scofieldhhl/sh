package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class FANS
  implements IR
{
  public FANS()
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
    if ((0xFF00 & paramInt2) != 32768);
    do
      return null;
    while ((paramInt2 < 32769) || (paramInt2 > 32811));
    return ETIR.SearchCode(32768, Index.GetIndex(paramInt1), paramInt2 & 0xFF);
  }

  public byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    if ((0xFF00 & paramInt3) != 32768);
    do
      return null;
    while ((paramInt3 < 32769) || (paramInt3 > 32811));
    return ETIR.SearchCode(32768, Index.GetIndex(paramInt1, paramInt2), paramInt3 & 0xFF);
  }

  private static final class Index
  {
    private static int[][] mTables;
    private static int[][] mTablesType = new int[0][];

    static
    {
      int[][] arrayOfInt = new int[22][];
      int[] arrayOfInt1 = new int[38];
      arrayOfInt1[0] = 147;
      arrayOfInt1[1] = 148;
      arrayOfInt1[2] = 149;
      arrayOfInt1[3] = 150;
      arrayOfInt1[5] = 1;
      arrayOfInt1[6] = 2;
      arrayOfInt1[7] = 3;
      arrayOfInt1[8] = 4;
      arrayOfInt1[9] = 5;
      arrayOfInt1[10] = 6;
      arrayOfInt1[11] = 7;
      arrayOfInt1[12] = 8;
      arrayOfInt1[13] = 9;
      arrayOfInt1[14] = 10;
      arrayOfInt1[15] = 96;
      arrayOfInt1[16] = 123;
      arrayOfInt1[17] = 124;
      arrayOfInt1[18] = 97;
      arrayOfInt1[19] = 98;
      arrayOfInt1[20] = 99;
      arrayOfInt1[21] = 100;
      arrayOfInt1[22] = 101;
      arrayOfInt1[23] = 102;
      arrayOfInt1[24] = 103;
      arrayOfInt1[25] = 104;
      arrayOfInt1[26] = 105;
      arrayOfInt1[27] = 106;
      arrayOfInt1[28] = 107;
      arrayOfInt1[29] = 108;
      arrayOfInt1[30] = 109;
      arrayOfInt1[31] = 110;
      arrayOfInt1[32] = 111;
      arrayOfInt1[33] = 112;
      arrayOfInt1[34] = 113;
      arrayOfInt1[35] = 114;
      arrayOfInt1[36] = 115;
      arrayOfInt1[37] = 116;
      arrayOfInt[0] = arrayOfInt1;
      arrayOfInt[1] = { 141, 11, 12, 13, 14, 15, 16, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75 };
      arrayOfInt[2] = { 143, 144, 17, 18, 19, 20, 21, 22, 23, 24, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 91, 92, 93, 94, 95, 96, 110 };
      arrayOfInt[3] = { 138, 139, 118, 119, 25, 26, 27, 118, 28, 29, 30, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101 };
      arrayOfInt[4] = { 145, 31, 32, 33, 34, 35, 36, 37, 38, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111 };
      arrayOfInt[5] = { 39, 40, 41, 42, 43, 44, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86 };
      arrayOfInt[6] = { 45, 46, 47, 48, 49, 50, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 94, 95, 96, 97, 98 };
      arrayOfInt[7] = { 122, 51, 52, 53, 54, 55, 56, 57, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106 };
      arrayOfInt[8] = { 58, 59, 60, 61, 62, 63, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16 };
      arrayOfInt[9] = { 138, 147, 148, 149, 139, 140, 125, 126, 64, 65, 66, 67, 68, 69, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36 };
      arrayOfInt[10] = { 70, 71, 72, 73, 74, 75, 108, 109, 110, 111, 112 };
      arrayOfInt[11] = { 76, 77, 78, 79, 80, 81, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26 };
      arrayOfInt[12] = { 128, 117, 118, 119, 134, 135, 136, 125, 126, 82, 83, 84, 85, 86, 87, 88, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54 };
      arrayOfInt[13] = { 112, 113, 114, 24, 25, 26, 27, 28, 29, 125, 126, 89, 90, 91, 92, 93, 94, 102, 103, 127, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116 };
      arrayOfInt[14] = { 151, 146, 89, 90, 91, 92, 93, 94, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116 };
      int[] arrayOfInt2 = new int[28];
      arrayOfInt2[0] = 95;
      arrayOfInt2[1] = 96;
      arrayOfInt2[2] = 97;
      arrayOfInt2[3] = 98;
      arrayOfInt2[4] = 99;
      arrayOfInt2[5] = 100;
      arrayOfInt2[6] = 101;
      arrayOfInt2[7] = 102;
      arrayOfInt2[8] = 103;
      arrayOfInt2[9] = 104;
      arrayOfInt2[11] = 1;
      arrayOfInt2[12] = 2;
      arrayOfInt2[13] = 3;
      arrayOfInt2[14] = 4;
      arrayOfInt2[15] = 5;
      arrayOfInt2[16] = 6;
      arrayOfInt2[17] = 7;
      arrayOfInt2[18] = 8;
      arrayOfInt2[19] = 9;
      arrayOfInt2[20] = 10;
      arrayOfInt2[21] = 11;
      arrayOfInt2[22] = 12;
      arrayOfInt2[23] = 13;
      arrayOfInt2[24] = 14;
      arrayOfInt2[25] = 15;
      arrayOfInt2[26] = 16;
      arrayOfInt2[27] = 17;
      arrayOfInt[15] = arrayOfInt2;
      int[] arrayOfInt3 = new int[28];
      arrayOfInt3[0] = 95;
      arrayOfInt3[1] = 96;
      arrayOfInt3[2] = 97;
      arrayOfInt3[3] = 98;
      arrayOfInt3[4] = 99;
      arrayOfInt3[5] = 100;
      arrayOfInt3[6] = 101;
      arrayOfInt3[7] = 102;
      arrayOfInt3[8] = 103;
      arrayOfInt3[9] = 104;
      arrayOfInt3[11] = 1;
      arrayOfInt3[12] = 2;
      arrayOfInt3[13] = 3;
      arrayOfInt3[14] = 4;
      arrayOfInt3[15] = 5;
      arrayOfInt3[16] = 6;
      arrayOfInt3[17] = 7;
      arrayOfInt3[18] = 8;
      arrayOfInt3[19] = 9;
      arrayOfInt3[20] = 10;
      arrayOfInt3[21] = 11;
      arrayOfInt3[22] = 12;
      arrayOfInt3[23] = 13;
      arrayOfInt3[24] = 14;
      arrayOfInt3[25] = 15;
      arrayOfInt3[26] = 16;
      arrayOfInt3[27] = 17;
      arrayOfInt[16] = arrayOfInt3;
      arrayOfInt[17] = { 105, 106, 107, 108, 109, 110, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38 };
      arrayOfInt[18] = { 105, 106, 107, 108, 109, 110, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38 };
      arrayOfInt[19] = { 111, 112, 113, 114, 115, 116, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95 };
      arrayOfInt[20] = { 111, 112, 113, 114, 115, 116, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95 };
      int[] arrayOfInt4 = new int[''];
      arrayOfInt4[0] = 97;
      arrayOfInt4[1] = 98;
      arrayOfInt4[2] = 99;
      arrayOfInt4[3] = 100;
      arrayOfInt4[4] = 101;
      arrayOfInt4[5] = 102;
      arrayOfInt4[6] = 103;
      arrayOfInt4[7] = 104;
      arrayOfInt4[8] = 105;
      arrayOfInt4[9] = 106;
      arrayOfInt4[10] = 107;
      arrayOfInt4[11] = 108;
      arrayOfInt4[12] = 109;
      arrayOfInt4[13] = 110;
      arrayOfInt4[14] = 111;
      arrayOfInt4[15] = 112;
      arrayOfInt4[16] = 113;
      arrayOfInt4[17] = 114;
      arrayOfInt4[18] = 115;
      arrayOfInt4[19] = 116;
      arrayOfInt4[20] = 117;
      arrayOfInt4[21] = 118;
      arrayOfInt4[22] = 119;
      arrayOfInt4[23] = 120;
      arrayOfInt4[24] = 128;
      arrayOfInt4[25] = 129;
      arrayOfInt4[26] = 130;
      arrayOfInt4[27] = 131;
      arrayOfInt4[28] = 132;
      arrayOfInt4[29] = 133;
      arrayOfInt4[30] = 134;
      arrayOfInt4[31] = 135;
      arrayOfInt4[32] = 136;
      arrayOfInt4[33] = 137;
      arrayOfInt4[34] = 138;
      arrayOfInt4[35] = 139;
      arrayOfInt4[36] = 140;
      arrayOfInt4[37] = 141;
      arrayOfInt4[38] = 142;
      arrayOfInt4[39] = 143;
      arrayOfInt4[40] = 144;
      arrayOfInt4[41] = 145;
      arrayOfInt4[42] = 146;
      arrayOfInt4[43] = 147;
      arrayOfInt4[44] = 148;
      arrayOfInt4[45] = 149;
      arrayOfInt4[46] = 150;
      arrayOfInt4[48] = 35;
      arrayOfInt4[49] = 36;
      arrayOfInt4[50] = 37;
      arrayOfInt4[51] = 38;
      arrayOfInt4[52] = 39;
      arrayOfInt4[53] = 40;
      arrayOfInt4[54] = 41;
      arrayOfInt4[55] = 42;
      arrayOfInt4[56] = 43;
      arrayOfInt4[57] = 44;
      arrayOfInt4[58] = 45;
      arrayOfInt4[59] = 46;
      arrayOfInt4[60] = 47;
      arrayOfInt4[61] = 48;
      arrayOfInt4[62] = 49;
      arrayOfInt4[63] = 50;
      arrayOfInt4[64] = 51;
      arrayOfInt4[65] = 52;
      arrayOfInt4[66] = 53;
      arrayOfInt4[67] = 54;
      arrayOfInt4[68] = 55;
      arrayOfInt4[69] = 56;
      arrayOfInt4[70] = 57;
      arrayOfInt4[71] = 58;
      arrayOfInt4[72] = 59;
      arrayOfInt4[73] = 60;
      arrayOfInt4[74] = 61;
      arrayOfInt4[75] = 62;
      arrayOfInt4[76] = 63;
      arrayOfInt4[77] = 64;
      arrayOfInt4[78] = 65;
      arrayOfInt4[79] = 1;
      arrayOfInt4[80] = 2;
      arrayOfInt4[81] = 3;
      arrayOfInt4[82] = 4;
      arrayOfInt4[83] = 5;
      arrayOfInt4[84] = 6;
      arrayOfInt4[85] = 7;
      arrayOfInt4[86] = 8;
      arrayOfInt4[87] = 9;
      arrayOfInt4[88] = 10;
      arrayOfInt4[89] = 11;
      arrayOfInt4[90] = 12;
      arrayOfInt4[91] = 13;
      arrayOfInt4[92] = 14;
      arrayOfInt4[93] = 15;
      arrayOfInt4[94] = 16;
      arrayOfInt4[95] = 17;
      arrayOfInt4[96] = 18;
      arrayOfInt4[97] = 19;
      arrayOfInt4[98] = 20;
      arrayOfInt4[99] = 21;
      arrayOfInt4[100] = 22;
      arrayOfInt4[101] = 23;
      arrayOfInt4[102] = 24;
      arrayOfInt4[103] = 25;
      arrayOfInt4[104] = 26;
      arrayOfInt4[105] = 27;
      arrayOfInt4[106] = 28;
      arrayOfInt4[107] = 29;
      arrayOfInt4[108] = 30;
      arrayOfInt4[109] = 31;
      arrayOfInt4[110] = 32;
      arrayOfInt4[111] = 33;
      arrayOfInt4[112] = 34;
      arrayOfInt4[113] = 66;
      arrayOfInt4[114] = 67;
      arrayOfInt4[115] = 68;
      arrayOfInt4[116] = 69;
      arrayOfInt4[117] = 70;
      arrayOfInt4[118] = 71;
      arrayOfInt4[119] = 72;
      arrayOfInt4[120] = 73;
      arrayOfInt4[121] = 74;
      arrayOfInt4[122] = 75;
      arrayOfInt4[123] = 76;
      arrayOfInt4[124] = 77;
      arrayOfInt4[125] = 78;
      arrayOfInt4[126] = 79;
      arrayOfInt4[127] = 80;
      arrayOfInt4[''] = 81;
      arrayOfInt4[''] = 82;
      arrayOfInt4[''] = 83;
      arrayOfInt4[''] = 84;
      arrayOfInt4[''] = 85;
      arrayOfInt4[''] = 86;
      arrayOfInt4[''] = 87;
      arrayOfInt4[''] = 88;
      arrayOfInt4[''] = 89;
      arrayOfInt4[''] = 90;
      arrayOfInt4[''] = 91;
      arrayOfInt4[''] = 92;
      arrayOfInt4[''] = 93;
      arrayOfInt4[''] = 94;
      arrayOfInt4[''] = 95;
      arrayOfInt4[''] = 96;
      arrayOfInt4[''] = 121;
      arrayOfInt4[''] = 122;
      arrayOfInt4[''] = 123;
      arrayOfInt4[''] = 124;
      arrayOfInt[21] = arrayOfInt4;
      mTables = arrayOfInt;
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
 * Qualified Name:     et.song.remote.instance.FANS
 * JD-Core Version:    0.6.0
 */