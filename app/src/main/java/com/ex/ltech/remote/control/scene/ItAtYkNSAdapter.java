package com.ex.ltech.remote.control.scene;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.T;
import com.ex.ltech.remote.control.vo.YkVo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ItAtYkNSAdapter extends BaseAdapter
{
  private AtYkNewSceneActivity context;
  private LayoutInflater layoutInflater;
  private List<T> objects = new ArrayList();
  int[] picRes = { 2130903055, 2130903790, 2130903792, 2130903636, 2130903254, 2130903401, 2130903228, 2130903228 };
  List<YkVo> yks;

  public ItAtYkNSAdapter(AtYkNewSceneActivity paramAtYkNewSceneActivity, List<YkVo> paramList)
  {
    this.context = paramAtYkNewSceneActivity;
    this.layoutInflater = LayoutInflater.from(paramAtYkNewSceneActivity);
    this.yks = paramList;
  }

  private void initializeViews(List<YkVo> paramList, ViewHolder paramViewHolder, int paramInt)
  {
    YkVo localYkVo = (YkVo)paramList.get(paramInt);
    System.out.println(localYkVo.toString() + "798987987978");
    if (localYkVo.isAdd())
    {
      paramViewHolder.yaoKongStatus.setVisibility(View.GONE);
      paramViewHolder.down_vertical_line.setVisibility(View.GONE);
      paramViewHolder.icon.setImageResource(2130903753);
      paramViewHolder.yaoKongName.setText(2131099949);
      paramViewHolder.time.setVisibility(View.GONE);
      paramViewHolder.icon.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          ItAtYkNSAdapter.this.context.onItemClick(this.val$position);
        }
      });
      paramViewHolder.time.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          ItAtYkNSAdapter.this.context.showSecond(this.val$position);
        }
      });
      return;
    }
    paramViewHolder.yaoKongStatus.setVisibility(View.VISIBLE);
    paramViewHolder.down_vertical_line.setVisibility(View.VISIBLE);
    label171: String str;
    int i;
    if ((getCount() > 1) && (-2 + getCount() == paramInt))
    {
      paramViewHolder.time.setVisibility(View.GONE);
      paramViewHolder.yaoKongName.setText(localYkVo.getName());
      paramViewHolder.time.setText(localYkVo.getSpaceTime() + ".0" + this.context.getString(R.string.sec));
      str = localYkVo.getType();
      i = -1;
      switch (str.hashCode())
      {
      default:
        label308: switch (i)
        {
        default:
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      case 96586:
      case 3714:
      case 110741513:
      case -894830916:
      case 101139:
      case 3239401:
      case 99858:
      }
    }
    while (true)
    {
      paramViewHolder.yaoKongStatus.setText(localYkVo.getStatus());
      break;
      paramViewHolder.time.setVisibility(View.VISIBLE);
      break label171;
      if (!str.equals("air"))
        break label308;
      i = 0;
      break label308;
      if (!str.equals("tv"))
        break label308;
      i = 1;
      break label308;
      if (!str.equals("tvbox"))
        break label308;
      i = 2;
      break label308;
      if (!str.equals("projector"))
        break label308;
      i = 3;
      break label308;
      if (!str.equals("fan"))
        break label308;
      i = 4;
      break label308;
      if (!str.equals("iptv"))
        break label308;
      i = 5;
      break label308;
      if (!str.equals("dvd"))
        break label308;
      i = 6;
      break label308;
      paramViewHolder.icon.setImageResource(this.picRes[0]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[1]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[2]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[3]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[4]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[5]);
      continue;
      paramViewHolder.icon.setImageResource(this.picRes[6]);
    }
  }

  public int getCount()
  {
    return this.yks.size();
  }

  public T getItem(int paramInt)
  {
    return (T)this.objects.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968793, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559285));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131558468));
      ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131558981));
      ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131559286));
      ViewHolder.access$402(localViewHolder, (TextView)paramView.findViewById(2131559287));
      paramView.setTag(localViewHolder);
    }
    initializeViews(this.yks, (ViewHolder)paramView.getTag(), paramInt);
    return paramView;
  }

  protected class ViewHolder
  {
    private ImageView down_vertical_line;
    private ImageView icon;
    private TextView time;
    private TextView yaoKongName;
    private TextView yaoKongStatus;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.ItAtYkNSAdapter
 * JD-Core Version:    0.6.0
 */