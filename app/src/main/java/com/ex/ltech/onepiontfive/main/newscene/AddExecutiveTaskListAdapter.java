package com.ex.ltech.onepiontfive.main.newscene;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.RoomLvData;
import java.util.ArrayList;

public class AddExecutiveTaskListAdapter extends BaseExpandableListAdapter
{
  private Context context;
  private RoomLvData home;
  OnSwichOrItemClickListener listener;
  private final String room1;
  private final String room2;
  private final String room3;
  private final String room4;
  private final String room5;
  private final String room6;
  private final String room7;
  private final String room8;

  public AddExecutiveTaskListAdapter(Context paramContext, OnSwichOrItemClickListener paramOnSwichOrItemClickListener, RoomLvData paramRoomLvData)
  {
    this.context = paramContext;
    this.room1 = paramContext.getString(2131100349);
    this.room2 = paramContext.getString(2131100350);
    this.room3 = paramContext.getString(2131100351);
    this.room4 = paramContext.getString(2131100352);
    this.room5 = paramContext.getString(2131100353);
    this.room6 = paramContext.getString(2131100354);
    this.room7 = paramContext.getString(2131100355);
    this.room8 = paramContext.getString(2131100356);
    this.listener = paramOnSwichOrItemClickListener;
    this.home = paramRoomLvData;
  }

  public Object getChild(int paramInt1, int paramInt2)
  {
    return ((Room)this.home.getRooms().get(paramInt1)).getDvcVos().get(paramInt2);
  }

  public long getChildId(int paramInt1, int paramInt2)
  {
    return paramInt2;
  }

  public View getChildView(int paramInt1, int paramInt2, boolean paramBoolean, View paramView, ViewGroup paramViewGroup)
  {
    ItemHolder localItemHolder;
    label127: RoomLvChildVo localRoomLvChildVo;
    int i;
    if (paramView == null)
    {
      paramView = LayoutInflater.from(this.context).inflate(2130968792, null);
      localItemHolder = new ItemHolder();
      localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
      localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
      localItemHolder.icNext = ((TextView)paramView.findViewById(2131558971));
      localItemHolder.status = ((TextView)paramView.findViewById(2131559169));
      localItemHolder.selected = ((TextView)paramView.findViewById(2131559284));
      localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
      paramView.setTag(localItemHolder);
      localRoomLvChildVo = (RoomLvChildVo)((Room)this.home.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2);
      localItemHolder.name.setVisibility(View.VISIBLE);
      localItemHolder.name.setText(localRoomLvChildVo.getInnerDeviceName());
      localItemHolder.condition.setVisibility(View.GONE);
      paramView.setOnClickListener(null);
      if (localRoomLvChildVo.getInnerItemType() == 21)
        break label447;
      localItemHolder.status.setVisibility(View.GONE);
      TextView localTextView = localItemHolder.selected;
      if (localRoomLvChildVo.getInnerItemType() != 21)
        break label428;
      i = 8;
      label232: localTextView.setVisibility(i);
      localItemHolder.icNext.setVisibility(View.GONE);
      localItemHolder.selected.setOnClickListener(new View.OnClickListener(paramInt1, paramInt2, localRoomLvChildVo)
      {
        public void onClick(View paramView)
        {
          OnSwichOrItemClickListener localOnSwichOrItemClickListener = AddExecutiveTaskListAdapter.this.listener;
          int i = this.val$groupPosition;
          int j = this.val$childPosition;
          if (!this.val$vo.isSwich());
          for (boolean bool = true; ; bool = false)
          {
            localOnSwichOrItemClickListener.onSwich(i, j, bool);
            return;
          }
        }
      });
      paramView.setOnClickListener(new View.OnClickListener(paramInt1, paramInt2, localRoomLvChildVo)
      {
        public void onClick(View paramView)
        {
          OnSwichOrItemClickListener localOnSwichOrItemClickListener = AddExecutiveTaskListAdapter.this.listener;
          int i = this.val$groupPosition;
          int j = this.val$childPosition;
          if (!this.val$vo.isSwich());
          for (boolean bool = true; ; bool = false)
          {
            localOnSwichOrItemClickListener.onSwich(i, j, bool);
            return;
          }
        }
      });
      if (!localRoomLvChildVo.isSwich())
        break label434;
      localItemHolder.selected.setBackgroundResource(2130903786);
    }
    while (true)
      switch (localRoomLvChildVo.getInnerItemType())
      {
      case 19:
      case 20:
      default:
        return paramView;
        localItemHolder = (ItemHolder)paramView.getTag();
        break label127;
        label428: i = 0;
        break label232;
        label434: localItemHolder.selected.setBackgroundResource(2130903784);
      case 11:
      case 14:
      case 15:
      case 16:
      case 17:
      case 18:
      case 10:
      case 8:
      case 12:
      case 13:
      case 21:
      case 9:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      }
    label447: localItemHolder.selected.setVisibility(View.GONE);
    if (localRoomLvChildVo.isSeted())
      localItemHolder.status.setText(localRoomLvChildVo.getInnerDeviceStatus());
    while (true)
    {
      if (localRoomLvChildVo.getYkVo() != null);
      localItemHolder.status.setVisibility(View.VISIBLE);
      localItemHolder.icNext.setVisibility(View.VISIBLE);
      paramView.setOnClickListener(new View.OnClickListener(paramInt1, paramInt2, localRoomLvChildVo)
      {
        public void onClick(View paramView)
        {
          AddExecutiveTaskListAdapter.this.listener.onItemClick(this.val$groupPosition, this.val$childPosition, this.val$vo.getInnerItemType());
        }
      });
      break;
      localItemHolder.status.setText(2131100220);
    }
    localItemHolder.ic.setBackgroundResource(2130903085);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903731);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903607);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903780);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903800);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903600);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903142);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903700);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903701);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903621);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903679);
    return paramView;
    localItemHolder.ic.setBackgroundResource(2130903805);
    return paramView;
    localItemHolder.name.setText(2131100457);
    localItemHolder.ic.setBackgroundResource(2130903790);
    paramView.setOnClickListener(new View.OnClickListener(paramInt1, paramInt2, localRoomLvChildVo)
    {
      public void onClick(View paramView)
      {
        AddExecutiveTaskListAdapter.this.listener.onItemClick(this.val$groupPosition, this.val$childPosition, this.val$vo.getInnerItemType());
      }
    });
    return paramView;
    localItemHolder.name.setText(2131100109);
    localItemHolder.ic.setBackgroundResource(2130903401);
    paramView.setOnClickListener(new View.OnClickListener(paramInt1, paramInt2, localRoomLvChildVo)
    {
      public void onClick(View paramView)
      {
        AddExecutiveTaskListAdapter.this.listener.onItemClick(this.val$groupPosition, this.val$childPosition, this.val$vo.getInnerItemType());
      }
    });
    return paramView;
    localItemHolder.name.setText(2131100459);
    localItemHolder.ic.setBackgroundResource(2130903792);
    return paramView;
    localItemHolder.name.setText(2131100046);
    localItemHolder.ic.setBackgroundResource(2130903228);
    return paramView;
    localItemHolder.name.setText(2131100058);
    localItemHolder.ic.setBackgroundResource(2130903254);
    return paramView;
    localItemHolder.name.setText(2131100276);
    localItemHolder.ic.setBackgroundResource(2130903636);
    return paramView;
    localItemHolder.name.setText(2131099858);
    localItemHolder.ic.setBackgroundResource(2130903055);
    return paramView;
    localItemHolder.name.setText(2131100285);
    localItemHolder.ic.setBackgroundResource(2130903406);
    return paramView;
  }

  public int getChildrenCount(int paramInt)
  {
    return ((Room)this.home.getRooms().get(paramInt)).getExpandableLvInnerItemVos().size();
  }

  public Object getGroup(int paramInt)
  {
    return this.home.getRooms().get(paramInt);
  }

  public int getGroupCount()
  {
    return this.home.getRooms().size();
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
      paramView.setTag(localGroupHolder);
    }
    while (true)
    {
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room1))
        localGroupHolder.ic.setBackgroundResource(2130903345);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room2))
        localGroupHolder.ic.setBackgroundResource(2130903346);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room3))
        localGroupHolder.ic.setBackgroundResource(2130903347);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room4))
        localGroupHolder.ic.setBackgroundResource(2130903348);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room5))
        localGroupHolder.ic.setBackgroundResource(2130903349);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room6))
        localGroupHolder.ic.setBackgroundResource(2130903350);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room7))
        localGroupHolder.ic.setBackgroundResource(2130903351);
      if (((Room)this.home.getRooms().get(paramInt)).getName().equals(this.room8))
        localGroupHolder.ic.setBackgroundResource(2130903352);
      localGroupHolder.name.setText(((Room)this.home.getRooms().get(paramInt)).getName());
      return paramView;
      localGroupHolder = (GroupHolder)paramView.getTag();
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

  public void setHome(RoomLvData paramRoomLvData)
  {
    this.home = paramRoomLvData;
  }

  class GroupHolder
  {
    public ImageView ic;
    public TextView name;

    GroupHolder()
    {
    }
  }

  class ItemHolder
  {
    public TextView condition;
    public ImageView ic;
    public TextView icNext;
    public TextView name;
    public TextView selected;
    public TextView status;

    ItemHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.AddExecutiveTaskListAdapter
 * JD-Core Version:    0.6.0
 */