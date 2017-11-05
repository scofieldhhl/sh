package com.ex.ltech.onepiontfive.main.newscene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;

public class InitConditionAdapter extends BaseExpandableListAdapter
{
  private int[] childNames = { 2131100411, 2131100242, 2131100067, 2131100428 };
  private Context context;
  private int[] groupIcs = { 2130903762, 2130903731, 2130903227 };
  private int[] groupNames = { 2131100083, 2131099885, 2131100045 };
  OnSwichOrItemClickListener listener;

  public InitConditionAdapter(Context paramContext, OnSwichOrItemClickListener paramOnSwichOrItemClickListener)
  {
    this.listener = paramOnSwichOrItemClickListener;
    this.context = paramContext;
  }

  public String getBodySensorCondition(int paramInt)
  {
    return this.context.getString(this.childNames[paramInt]);
  }

  public Object getChild(int paramInt1, int paramInt2)
  {
    return null;
  }

  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }

  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    ItemHolder localItemHolder;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.context).inflate(2130968733, null);
      localItemHolder = new ItemHolder();
      localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
      localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
      localItemHolder.swich = ((ToggleButton)paramView.findViewById(2131559047));
      localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
      paramView.setTag(localItemHolder);
    }
    while (true)
    {
      localItemHolder.name.setVisibility(View.GONE);
      localItemHolder.condition.setVisibility(View.VISIBLE);
      localItemHolder.condition.setText(this.childNames[paramInt2]);
      localItemHolder.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView(paramInt2)
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          InitConditionAdapter.this.listener.onToggleSwich(InitConditionAdapter.this.context.getString(InitConditionAdapter.this.childNames[this.val$childPosition]));
        }
      });
      return paramView;
      localItemHolder = (ItemHolder)paramView.getTag();
    }
  }

  public int getChildrenCount(int paramInt)
  {
    return 0;
  }

  public Object getGroup(int paramInt)
  {
    return null;
  }

  public int getGroupCount()
  {
    return 3;
  }

  public long getGroupId(int paramInt)
  {
    return paramInt;
  }

  public View getGroupView(int paramInt, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    GroupHolder localGroupHolder;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.context).inflate(2130968732, null);
      localGroupHolder = new GroupHolder();
      localGroupHolder.name = ((TextView)paramView.findViewById(2131559008));
      localGroupHolder.ic = ((ImageView)paramView.findViewById(2131558883));
      localGroupHolder.ic_down_show = ((ImageView)paramView.findViewById(2131559110));
      paramView.setTag(localGroupHolder);
      if (paramInt != 1)
        break label132;
      localGroupHolder.ic_down_show.setVisibility(View.VISIBLE);
    }
    while (true)
    {
      localGroupHolder.ic.setBackgroundResource(this.groupIcs[paramInt]);
      localGroupHolder.name.setText(this.groupNames[paramInt]);
      return paramView;
      localGroupHolder = (GroupHolder)paramView.getTag();
      break;
      label132: localGroupHolder.ic_down_show.setVisibility(View.GONE);
    }
  }

  public boolean hasStableIds()
  {
    return true;
  }

  public boolean isChildSelectable(int paramInt1, int paramInt2)
  {
    return true;
  }

  class GroupHolder
  {
    public ImageView ic;
    public ImageView ic_down_show;
    public TextView name;

    GroupHolder()
    {
    }
  }

  class ItemHolder
  {
    public TextView condition;
    public ImageView ic;
    public TextView name;
    public ToggleButton swich;

    ItemHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.InitConditionAdapter
 * JD-Core Version:    0.6.0
 */