package et.song.remote.instance;

import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;

public final class DVD
  implements IR
{
  public DVD()
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
    if ((0xFF00 & paramInt2) != 24576);
    do
      return null;
    while ((paramInt2 < 24577) || (paramInt2 > 24613));
    return ETIR.SearchCode(24576, Index.GetIndex(paramInt1), paramInt2 & 0xFF);
  }

  public byte[] Search(int paramInt1, int paramInt2, int paramInt3)
    throws Exception
  {
    if ((0xFF00 & paramInt3) != 24576);
    do
      return null;
    while ((paramInt3 < 24577) || (paramInt3 > 24613));
    return ETIR.SearchCode(24576, Index.GetIndex(paramInt1, paramInt2), paramInt3 & 0xFF);
  }

  private static final class Index
  {
    private static int[][] mTablesType = { { 566 }, { 608 }, { 609 }, { 566 }, { 567 }, { 567 }, { 567 }, { 568 }, { 568 }, { 569 }, { 569 }, { 570 }, { 570 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 571 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 572 }, { 573 }, { 574 }, { 575 }, { 575 }, { 575 }, { 576 }, { 576 }, { 576 }, { 577 }, { 578 }, { 578 }, { 578 }, { 578 }, { 578 }, { 578 }, { 578 }, { 579 }, { 579 }, { 579 }, { 579 }, { 579 }, { 579 }, { 579 }, { 580 }, { 581 }, { 581 }, { 582 }, { 582 }, { 582 }, { 582 }, { 582 }, { 582 }, { 582 }, { 583 }, { 584 }, { 585 }, { 585 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 586 }, { 587 }, { 587 }, { 588 }, { 589 }, { 589 }, { 589 }, { 589 }, { 589 }, { 589 }, { 589 }, { 589 }, { 589 }, { 590 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 591 }, { 592 }, { 592 }, { 593 }, { 593 }, { 594 }, { 594 }, { 595 }, { 596 }, { 597 }, { 598 }, { 598 }, { 599 }, { 599 }, { 600 }, { 600 }, { 1304 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 601 }, { 602 }, { 602 }, { 602 }, { 603 }, { 603 }, { 603 }, { 604 }, { 604 }, { 605 }, { 605 }, { 606 }, { 606 }, { 607 } };

    public static int GetBrandCount(int paramInt)
      throws Exception
    {
      if (paramInt < DVD_Tab1.mTables.length)
        return DVD_Tab1.mTables[paramInt].length;
      if (paramInt < DVD_Tab1.mTables.length + DVD_Tab2.mTables.length)
        return DVD_Tab2.mTables[(paramInt - DVD_Tab1.mTables.length)].length;
      return 0;
    }

    public static int GetBrandTotalCount()
      throws Exception
    {
      int i = 0;
      int j = 0;
      if (j >= DVD_Tab1.mTables.length);
      for (int k = 0; ; k++)
      {
        if (k >= DVD_Tab2.mTables.length)
        {
          return i;
          i += GetBrandCount(j);
          j++;
          break;
        }
        i += GetBrandCount(k);
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
      if (paramInt1 < DVD_Tab1.mTables.length)
        return DVD_Tab1.mTables[paramInt1][paramInt2];
      if (paramInt1 < DVD_Tab1.mTables.length + DVD_Tab2.mTables.length)
        return DVD_Tab2.mTables[(paramInt1 - DVD_Tab1.mTables.length)][paramInt2];
      return 0;
    }

    public static int GetTotalCount()
      throws Exception
    {
      return DVD_Tab1.mTables.length + DVD_Tab2.mTables.length;
    }

    public static void Init()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.remote.instance.DVD
 * JD-Core Version:    0.6.0
 */