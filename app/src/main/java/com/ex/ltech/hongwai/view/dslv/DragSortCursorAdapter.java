package com.ex.ltech.hongwai.view.dslv;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;

public abstract class DragSortCursorAdapter extends CursorAdapter
  implements DragSortListView.DragSortListener
{
  public static final int REMOVED = -1;
  private SparseIntArray mListMapping = new SparseIntArray();
  private ArrayList<Integer> mRemovedCursorPositions = new ArrayList();

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor)
  {
    super(paramContext, paramCursor);
  }

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor, int paramInt)
  {
    super(paramContext, paramCursor, paramInt);
  }

  public DragSortCursorAdapter(Context paramContext, Cursor paramCursor, boolean paramBoolean)
  {
    super(paramContext, paramCursor, paramBoolean);
  }

  private void cleanMapping()
  {
    ArrayList localArrayList = new ArrayList();
    int i = this.mListMapping.size();
    for (int j = 0; j < i; j++)
    {
      if (this.mListMapping.keyAt(j) != this.mListMapping.valueAt(j))
        continue;
      localArrayList.add(Integer.valueOf(this.mListMapping.keyAt(j)));
    }
    int k = localArrayList.size();
    for (int m = 0; m < k; m++)
      this.mListMapping.delete(((Integer)localArrayList.get(m)).intValue());
  }

  private void resetMappings()
  {
    this.mListMapping.clear();
    this.mRemovedCursorPositions.clear();
  }

  public void changeCursor(Cursor paramCursor)
  {
    super.changeCursor(paramCursor);
    resetMappings();
  }

  public void drag(int paramInt1, int paramInt2)
  {
  }

  public void drop(int paramInt1, int paramInt2)
  {
    if (paramInt1 != paramInt2)
    {
      int i = this.mListMapping.get(paramInt1, paramInt1);
      if (paramInt1 > paramInt2)
        for (int k = paramInt1; k > paramInt2; k--)
          this.mListMapping.put(k, this.mListMapping.get(k - 1, k - 1));
      for (int j = paramInt1; j < paramInt2; j++)
        this.mListMapping.put(j, this.mListMapping.get(j + 1, j + 1));
      this.mListMapping.put(paramInt2, i);
      cleanMapping();
      notifyDataSetChanged();
    }
  }

  public int getCount()
  {
    return super.getCount() - this.mRemovedCursorPositions.size();
  }

  public int getCursorPosition(int paramInt)
  {
    return this.mListMapping.get(paramInt, paramInt);
  }

  public ArrayList<Integer> getCursorPositions()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < getCount(); i++)
      localArrayList.add(Integer.valueOf(this.mListMapping.get(i, i)));
    return localArrayList;
  }

  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getDropDownView(this.mListMapping.get(paramInt, paramInt), paramView, paramViewGroup);
  }

  public Object getItem(int paramInt)
  {
    return super.getItem(this.mListMapping.get(paramInt, paramInt));
  }

  public long getItemId(int paramInt)
  {
    return super.getItemId(this.mListMapping.get(paramInt, paramInt));
  }

  public int getListPosition(int paramInt)
  {
    if (this.mRemovedCursorPositions.contains(Integer.valueOf(paramInt)))
      paramInt = -1;
    int i;
    do
    {
      return paramInt;
      i = this.mListMapping.indexOfValue(paramInt);
    }
    while (i < 0);
    return this.mListMapping.keyAt(i);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return super.getView(this.mListMapping.get(paramInt, paramInt), paramView, paramViewGroup);
  }

  public void remove(int paramInt)
  {
    int i = this.mListMapping.get(paramInt, paramInt);
    if (!this.mRemovedCursorPositions.contains(Integer.valueOf(i)))
      this.mRemovedCursorPositions.add(Integer.valueOf(i));
    int j = getCount();
    for (int k = paramInt; k < j; k++)
      this.mListMapping.put(k, this.mListMapping.get(k + 1, k + 1));
    this.mListMapping.delete(j);
    cleanMapping();
    notifyDataSetChanged();
  }

  public void reset()
  {
    resetMappings();
    notifyDataSetChanged();
  }

  public Cursor swapCursor(Cursor paramCursor)
  {
    Cursor localCursor = super.swapCursor(paramCursor);
    resetMappings();
    return localCursor;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.dslv.DragSortCursorAdapter
 * JD-Core Version:    0.6.0
 */