package com.ex.ltech.led.my_view.pullfresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ListView;

public class PullableListView extends ListView
  implements Pullable
{
  public PullableListView(Context paramContext)
  {
    super(paramContext);
  }

  public PullableListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public PullableListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public boolean canPullDown()
  {
    int i = 1;
    try
    {
      if (getCount() == 0)
        return i;
      if (getFirstVisiblePosition() == 0)
      {
        int j = getChildAt(0).getTop();
        if (j >= 0);
      }
      else
      {
        return false;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      i = 0;
    }
    return i;
  }

  public boolean canPullUp()
  {
    return false;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.pullfresh.PullableListView
 * JD-Core Version:    0.6.0
 */