package com.ex.ltech.hongwai.view.dslv;

import android.content.Context;
import android.view.View;
import android.widget.Checkable;

public class DragSortItemViewCheckable extends DragSortItemView
  implements Checkable
{
  public DragSortItemViewCheckable(Context paramContext)
  {
    super(paramContext);
  }

  public boolean isChecked()
  {
    View localView = getChildAt(0);
    boolean bool1 = localView instanceof Checkable;
    boolean bool2 = false;
    if (bool1)
      bool2 = ((Checkable)localView).isChecked();
    return bool2;
  }

  public void setChecked(boolean paramBoolean)
  {
    View localView = getChildAt(0);
    if ((localView instanceof Checkable))
      ((Checkable)localView).setChecked(paramBoolean);
  }

  public void toggle()
  {
    View localView = getChildAt(0);
    if ((localView instanceof Checkable))
      ((Checkable)localView).toggle();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.dslv.DragSortItemViewCheckable
 * JD-Core Version:    0.6.0
 */