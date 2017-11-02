package com.ex.ltech.led.my_view.swipemenulistview;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.WrapperListAdapter;

public class SwipeMenuAdapter
  implements WrapperListAdapter, SwipeMenuView.OnSwipeItemClickListener
{
  private ListAdapter mAdapter;
  private Context mContext;
  private SwipeMenuListView.OnMenuItemClickListener onMenuItemClickListener;

  public SwipeMenuAdapter(Context paramContext, ListAdapter paramListAdapter)
  {
    this.mAdapter = paramListAdapter;
    this.mContext = paramContext;
  }

  public boolean areAllItemsEnabled()
  {
    return this.mAdapter.areAllItemsEnabled();
  }

  public void createMenu(SwipeMenu paramSwipeMenu)
  {
    SwipeMenuItem localSwipeMenuItem1 = new SwipeMenuItem(this.mContext);
    localSwipeMenuItem1.setTitle("Item 1");
    localSwipeMenuItem1.setBackground(new ColorDrawable(-7829368));
    localSwipeMenuItem1.setWidth(300);
    paramSwipeMenu.addMenuItem(localSwipeMenuItem1);
    SwipeMenuItem localSwipeMenuItem2 = new SwipeMenuItem(this.mContext);
    localSwipeMenuItem2.setTitle("Item 2");
    localSwipeMenuItem2.setBackground(new ColorDrawable(-65536));
    localSwipeMenuItem2.setWidth(300);
    paramSwipeMenu.addMenuItem(localSwipeMenuItem2);
  }

  public int getCount()
  {
    return this.mAdapter.getCount();
  }

  public Object getItem(int paramInt)
  {
    return this.mAdapter.getItem(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return this.mAdapter.getItemId(paramInt);
  }

  public int getItemViewType(int paramInt)
  {
    return this.mAdapter.getItemViewType(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      View localView = this.mAdapter.getView(paramInt, paramView, paramViewGroup);
      SwipeMenu localSwipeMenu = new SwipeMenu(this.mContext);
      localSwipeMenu.setViewType(this.mAdapter.getItemViewType(paramInt));
      createMenu(localSwipeMenu);
      SwipeMenuView localSwipeMenuView = new SwipeMenuView(localSwipeMenu, (SwipeMenuListView)paramViewGroup);
      localSwipeMenuView.setOnSwipeItemClickListener(this);
      SwipeMenuListView localSwipeMenuListView = (SwipeMenuListView)paramViewGroup;
      SwipeMenuLayout localSwipeMenuLayout2 = new SwipeMenuLayout(localView, localSwipeMenuView, localSwipeMenuListView.getCloseInterpolator(), localSwipeMenuListView.getOpenInterpolator());
      localSwipeMenuLayout2.setPosition(paramInt);
      return localSwipeMenuLayout2;
    }
    SwipeMenuLayout localSwipeMenuLayout1 = (SwipeMenuLayout)paramView;
    localSwipeMenuLayout1.closeMenu();
    localSwipeMenuLayout1.setPosition(paramInt);
    this.mAdapter.getView(paramInt, localSwipeMenuLayout1.getContentView(), paramViewGroup);
    return localSwipeMenuLayout1;
  }

  public int getViewTypeCount()
  {
    return this.mAdapter.getViewTypeCount();
  }

  public ListAdapter getWrappedAdapter()
  {
    return this.mAdapter;
  }

  public boolean hasStableIds()
  {
    return this.mAdapter.hasStableIds();
  }

  public boolean isEmpty()
  {
    return this.mAdapter.isEmpty();
  }

  public boolean isEnabled(int paramInt)
  {
    return this.mAdapter.isEnabled(paramInt);
  }

  public void onItemClick(SwipeMenuView paramSwipeMenuView, SwipeMenu paramSwipeMenu, int paramInt)
  {
    if (this.onMenuItemClickListener != null)
      this.onMenuItemClickListener.onMenuItemClick(paramSwipeMenuView.getPosition(), paramSwipeMenu, paramInt);
  }

  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mAdapter.registerDataSetObserver(paramDataSetObserver);
  }

  public void setOnMenuItemClickListener(SwipeMenuListView.OnMenuItemClickListener paramOnMenuItemClickListener)
  {
    this.onMenuItemClickListener = paramOnMenuItemClickListener;
  }

  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this.mAdapter.unregisterDataSetObserver(paramDataSetObserver);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuAdapter
 * JD-Core Version:    0.6.0
 */