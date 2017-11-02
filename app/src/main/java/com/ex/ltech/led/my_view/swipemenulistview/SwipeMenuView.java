package com.ex.ltech.led.my_view.swipemenulistview;

import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.Iterator;
import java.util.List;

public class SwipeMenuView extends LinearLayout
  implements View.OnClickListener
{
  private SwipeMenuLayout mLayout;
  private SwipeMenuListView mListView;
  private SwipeMenu mMenu;
  private OnSwipeItemClickListener onItemClickListener;
  private int position;

  public SwipeMenuView(SwipeMenu paramSwipeMenu, SwipeMenuListView paramSwipeMenuListView)
  {
    super(paramSwipeMenu.getContext());
    this.mListView = paramSwipeMenuListView;
    this.mMenu = paramSwipeMenu;
    List localList = paramSwipeMenu.getMenuItems();
    int i = 0;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      SwipeMenuItem localSwipeMenuItem = (SwipeMenuItem)localIterator.next();
      int j = i + 1;
      addItem(localSwipeMenuItem, i);
      i = j;
    }
  }

  private void addItem(SwipeMenuItem paramSwipeMenuItem, int paramInt)
  {
    if (paramSwipeMenuItem.getHeight() > 0)
      setGravity(80);
    for (LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(paramSwipeMenuItem.getWidth(), paramSwipeMenuItem.getHeight()); ; localLayoutParams = new LinearLayout.LayoutParams(paramSwipeMenuItem.getWidth(), -1))
    {
      LinearLayout localLinearLayout = new LinearLayout(getContext());
      localLinearLayout.setId(paramInt);
      localLinearLayout.setGravity(17);
      localLinearLayout.setOrientation(1);
      localLinearLayout.setLayoutParams(localLayoutParams);
      localLinearLayout.setBackgroundDrawable(paramSwipeMenuItem.getBackground());
      localLinearLayout.setOnClickListener(this);
      addView(localLinearLayout);
      if (paramSwipeMenuItem.getIcon() != null)
        localLinearLayout.addView(createIcon(paramSwipeMenuItem));
      if (!TextUtils.isEmpty(paramSwipeMenuItem.getTitle()))
        localLinearLayout.addView(createTitle(paramSwipeMenuItem));
      return;
    }
  }

  private ImageView createIcon(SwipeMenuItem paramSwipeMenuItem)
  {
    ImageView localImageView = new ImageView(getContext());
    localImageView.setImageDrawable(paramSwipeMenuItem.getIcon());
    return localImageView;
  }

  private TextView createTitle(SwipeMenuItem paramSwipeMenuItem)
  {
    TextView localTextView = new TextView(getContext());
    localTextView.setText(paramSwipeMenuItem.getTitle());
    localTextView.setGravity(17);
    localTextView.setTextSize(paramSwipeMenuItem.getTitleSize());
    localTextView.setTextColor(paramSwipeMenuItem.getTitleColor());
    return localTextView;
  }

  public OnSwipeItemClickListener getOnSwipeItemClickListener()
  {
    return this.onItemClickListener;
  }

  public int getPosition()
  {
    return this.position;
  }

  public void onClick(View paramView)
  {
    if ((this.onItemClickListener != null) && (this.mLayout.isOpen()))
      this.onItemClickListener.onItemClick(this, this.mMenu, paramView.getId());
  }

  public void setLayout(SwipeMenuLayout paramSwipeMenuLayout)
  {
    this.mLayout = paramSwipeMenuLayout;
  }

  public void setOnSwipeItemClickListener(OnSwipeItemClickListener paramOnSwipeItemClickListener)
  {
    this.onItemClickListener = paramOnSwipeItemClickListener;
  }

  public void setPosition(int paramInt)
  {
    this.position = paramInt;
  }

  public static abstract interface OnSwipeItemClickListener
  {
    public abstract void onItemClick(SwipeMenuView paramSwipeMenuView, SwipeMenu paramSwipeMenu, int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuView
 * JD-Core Version:    0.6.0
 */