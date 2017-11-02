package com.ex.ltech.LogRegForget.demo;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import java.util.ArrayList;

public class GroupListView extends RelativeLayout
{
  private GroupAdapter adapter;
  private int curFirstItem;
  private InnerAdapter innerAdapter;
  private ListView lvBody;
  private OnItemClickListener oicListener;
  private AbsListView.OnScrollListener osListener;
  private int titleHeight;
  private View tvTitle;

  public GroupListView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public GroupListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public GroupListView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  private void init(Context paramContext)
  {
    this.lvBody = new ListView(paramContext);
    this.lvBody.setCacheColorHint(0);
    this.lvBody.setSelector(new ColorDrawable());
    this.lvBody.setVerticalScrollBarEnabled(false);
    this.lvBody.setOnScrollListener(new AbsListView.OnScrollListener()
    {
      public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
      {
        GroupListView.access$102(GroupListView.this, paramInt1);
        if (GroupListView.this.tvTitle != null)
          GroupListView.this.onScroll();
        if (GroupListView.this.osListener != null)
          GroupListView.this.osListener.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
      }

      public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
      {
        if (GroupListView.this.osListener != null)
          GroupListView.this.osListener.onScrollStateChanged(paramAbsListView, paramInt);
      }
    });
    this.lvBody.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        if (GroupListView.this.oicListener != null)
        {
          int i = GroupListView.this.innerAdapter.getItemGroup(paramInt);
          int j = -1 + (paramInt - ((Integer)GroupListView.InnerAdapter.access$600(GroupListView.this.innerAdapter).get(i)).intValue());
          GroupListView.this.oicListener.onItemClick(GroupListView.this, paramView, i, j);
        }
      }
    });
    this.lvBody.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
    addView(this.lvBody);
  }

  private void notifyDataSetChanged()
  {
    this.innerAdapter.notifyDataSetChanged();
    setTitle();
  }

  private void onScroll()
  {
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.tvTitle.getLayoutParams();
    if (this.innerAdapter.isLastItem(this.curFirstItem))
    {
      int j = this.innerAdapter.getItemGroup(this.curFirstItem);
      String str2 = this.adapter.getGroupTitle(j);
      this.adapter.onGroupChange(this.tvTitle, str2);
      int k = this.lvBody.getChildAt(1).getTop();
      if (k < this.titleHeight)
      {
        localLayoutParams.setMargins(0, k - this.titleHeight, 0, 0);
        this.tvTitle.setLayoutParams(localLayoutParams);
      }
    }
    do
    {
      return;
      localLayoutParams.topMargin = 0;
      this.tvTitle.setLayoutParams(localLayoutParams);
    }
    while (!this.innerAdapter.isTitle(this.curFirstItem));
    int i = this.innerAdapter.getItemGroup(this.curFirstItem);
    String str1 = this.adapter.getGroupTitle(i);
    this.adapter.onGroupChange(this.tvTitle, str1);
  }

  private void setTitle()
  {
    if (this.tvTitle != null)
      removeView(this.tvTitle);
    if (this.innerAdapter.getCount() == 0)
      return;
    int i = this.innerAdapter.getItemGroup(this.curFirstItem);
    int j = ((Integer)this.innerAdapter.titleIndex.get(i)).intValue();
    this.tvTitle = this.innerAdapter.getView(j, null, this);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-1, -2);
    localLayoutParams.addRule(9);
    localLayoutParams.addRule(10);
    addView(this.tvTitle, localLayoutParams);
    this.tvTitle.measure(0, 0);
    this.titleHeight = this.tvTitle.getMeasuredHeight();
    onScroll();
  }

  public GroupAdapter getAdapter()
  {
    return this.adapter;
  }

  public void setAdapter(GroupAdapter paramGroupAdapter)
  {
    this.adapter = paramGroupAdapter;
    this.innerAdapter = new InnerAdapter(paramGroupAdapter);
    this.lvBody.setAdapter(this.innerAdapter);
    setTitle();
  }

  public void setDivider(Drawable paramDrawable)
  {
    this.lvBody.setDivider(paramDrawable);
  }

  public void setDividerHeight(int paramInt)
  {
    this.lvBody.setDividerHeight(paramInt);
  }

  public void setOnItemClickListener(OnItemClickListener paramOnItemClickListener)
  {
    this.oicListener = paramOnItemClickListener;
  }

  public void setOnScrollListener(AbsListView.OnScrollListener paramOnScrollListener)
  {
    this.osListener = paramOnScrollListener;
  }

  public void setSelection(int paramInt)
  {
    setSelection(paramInt, -1);
  }

  public void setSelection(int paramInt1, int paramInt2)
  {
    int i = 1 + (paramInt2 + ((Integer)this.innerAdapter.titleIndex.get(paramInt1)).intValue());
    this.lvBody.setSelection(i);
  }

  public static abstract class GroupAdapter
  {
    protected final GroupListView view;

    public GroupAdapter(GroupListView paramGroupListView)
    {
      this.view = paramGroupListView;
    }

    public abstract int getCount(int paramInt);

    public abstract int getGroupCount();

    public abstract String getGroupTitle(int paramInt);

    public abstract Object getItem(int paramInt1, int paramInt2);

    public abstract View getTitleView(int paramInt, View paramView, ViewGroup paramViewGroup);

    public abstract View getView(int paramInt1, int paramInt2, View paramView, ViewGroup paramViewGroup);

    public void notifyDataSetChanged()
    {
      this.view.notifyDataSetChanged();
    }

    public abstract void onGroupChange(View paramView, String paramString);
  }

  private static class InnerAdapter extends BaseAdapter
  {
    private GroupListView.GroupAdapter adapter;
    private ArrayList<Integer> lastItemIndex;
    private ArrayList<Object> listData;
    private ArrayList<Integer> titleIndex;

    public InnerAdapter(GroupListView.GroupAdapter paramGroupAdapter)
    {
      this.adapter = paramGroupAdapter;
      this.listData = new ArrayList();
      this.titleIndex = new ArrayList();
      this.lastItemIndex = new ArrayList();
      init();
    }

    private void init()
    {
      this.listData.clear();
      this.titleIndex.clear();
      this.lastItemIndex.clear();
      int i = 0;
      int j = this.adapter.getGroupCount();
      while (i < j)
      {
        int k = this.adapter.getCount(i);
        if (k > 0)
        {
          this.titleIndex.add(Integer.valueOf(this.listData.size()));
          this.listData.add(this.adapter.getGroupTitle(i));
          for (int m = 0; m < k; m++)
            this.listData.add(this.adapter.getItem(i, m));
          this.lastItemIndex.add(Integer.valueOf(-1 + this.listData.size()));
        }
        i++;
      }
    }

    public int getCount()
    {
      return this.listData.size();
    }

    public Object getItem(int paramInt)
    {
      return this.listData.get(paramInt);
    }

    public int getItemGroup(int paramInt)
    {
      int i = this.titleIndex.size();
      for (int j = 0; j < i; j++)
        if (paramInt < ((Integer)this.titleIndex.get(j)).intValue())
          return j - 1;
      return i - 1;
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public int getItemViewType(int paramInt)
    {
      if (isTitle(paramInt))
        return 0;
      return 1;
    }

    public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      int i = getItemGroup(paramInt);
      if (isTitle(paramInt))
      {
        if (paramView != null)
          return this.adapter.getTitleView(i, paramView, paramViewGroup);
        return this.adapter.getTitleView(i, null, paramViewGroup);
      }
      int j = -1 + (paramInt - ((Integer)this.titleIndex.get(i)).intValue());
      return this.adapter.getView(i, j, paramView, paramViewGroup);
    }

    public int getViewTypeCount()
    {
      return 2;
    }

    public boolean isLastItem(int paramInt)
    {
      int i = 0;
      int j = this.lastItemIndex.size();
      while (i < j)
      {
        if (((Integer)this.lastItemIndex.get(i)).intValue() == paramInt)
          return true;
        i++;
      }
      return false;
    }

    public boolean isTitle(int paramInt)
    {
      int i = 0;
      int j = this.titleIndex.size();
      while (i < j)
      {
        if (((Integer)this.titleIndex.get(i)).intValue() == paramInt)
          return true;
        i++;
      }
      return false;
    }

    public void notifyDataSetChanged()
    {
      init();
      super.notifyDataSetChanged();
    }
  }

  public static abstract interface OnItemClickListener
  {
    public abstract void onItemClick(GroupListView paramGroupListView, View paramView, int paramInt1, int paramInt2);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.demo.GroupListView
 * JD-Core Version:    0.6.0
 */