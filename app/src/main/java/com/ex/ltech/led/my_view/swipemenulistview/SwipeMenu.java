package com.ex.ltech.led.my_view.swipemenulistview;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class SwipeMenu
{
  private Context mContext;
  private List<SwipeMenuItem> mItems;
  private int mViewType;

  public SwipeMenu(Context paramContext)
  {
    this.mContext = paramContext;
    this.mItems = new ArrayList();
  }

  public void addMenuItem(SwipeMenuItem paramSwipeMenuItem)
  {
    this.mItems.add(paramSwipeMenuItem);
  }

  public Context getContext()
  {
    return this.mContext;
  }

  public SwipeMenuItem getMenuItem(int paramInt)
  {
    return (SwipeMenuItem)this.mItems.get(paramInt);
  }

  public List<SwipeMenuItem> getMenuItems()
  {
    return this.mItems;
  }

  public int getViewType()
  {
    return this.mViewType;
  }

  public void removeMenuItem(SwipeMenuItem paramSwipeMenuItem)
  {
    this.mItems.remove(paramSwipeMenuItem);
  }

  public void setViewType(int paramInt)
  {
    this.mViewType = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu
 * JD-Core Version:    0.6.0
 */