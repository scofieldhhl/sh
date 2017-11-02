package com.ex.ltech.hongwai.view.dslv;

import android.content.Context;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView.LayoutParams;

public class DragSortItemView extends ViewGroup
{
  private int mGravity = 48;

  public DragSortItemView(Context paramContext)
  {
    super(paramContext);
    setLayoutParams(new AbsListView.LayoutParams(-1, -2));
  }

  public int getGravity()
  {
    return this.mGravity;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    View localView = getChildAt(0);
    if (localView == null)
      return;
    if (this.mGravity == 48)
    {
      localView.layout(0, 0, getMeasuredWidth(), localView.getMeasuredHeight());
      return;
    }
    localView.layout(0, getMeasuredHeight() - localView.getMeasuredHeight(), getMeasuredWidth(), getMeasuredHeight());
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    View localView = getChildAt(0);
    if (localView == null)
    {
      setMeasuredDimension(0, j);
      return;
    }
    if (localView.isLayoutRequested())
      measureChild(localView, paramInt1, View.MeasureSpec.makeMeasureSpec(0, 0));
    ViewGroup.LayoutParams localLayoutParams;
    if (k == 0)
    {
      localLayoutParams = getLayoutParams();
      if (localLayoutParams.height <= 0)
        break label90;
    }
    label90: for (i = localLayoutParams.height; ; i = localView.getMeasuredHeight())
    {
      setMeasuredDimension(j, i);
      return;
    }
  }

  public void setGravity(int paramInt)
  {
    this.mGravity = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.dslv.DragSortItemView
 * JD-Core Version:    0.6.0
 */