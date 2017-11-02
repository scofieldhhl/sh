package com.ex.ltech.hongwai.scene;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.led.T;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class NewSceneAdapter extends BaseAdapter
{
  private AtRcNewSceneActivity context;
  private LayoutInflater layoutInflater;
  private List<T> objects = new ArrayList();
  int[] picRes = { 2130903523, 2130903574, 2130903572, 2130903636, 2130903557, 2130903526, 2130903384, 2130903383, 2130903379 };
  List<InnerRcVo> yks;

  public NewSceneAdapter(AtRcNewSceneActivity paramAtRcNewSceneActivity, List<InnerRcVo> paramList)
  {
    this.context = paramAtRcNewSceneActivity;
    this.layoutInflater = LayoutInflater.from(paramAtRcNewSceneActivity);
    this.yks = paramList;
  }

  private void initializeViews(List<InnerRcVo> paramList, ViewHolder paramViewHolder, int paramInt)
  {
    InnerRcVo localInnerRcVo = (InnerRcVo)paramList.get(paramInt);
    System.out.println(localInnerRcVo.toString() + "798987987978");
    if (localInnerRcVo.isAdd())
    {
      paramViewHolder.yaoKongStatus.setVisibility(8);
      paramViewHolder.down_vertical_line.setVisibility(8);
      paramViewHolder.icon.setImageResource(2130903753);
      paramViewHolder.yaoKongName.setText(2131099936);
      paramViewHolder.time.setVisibility(8);
      paramViewHolder.icon.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          NewSceneAdapter.this.context.onItemClick(this.val$position);
        }
      });
      paramViewHolder.time.setOnClickListener(new View.OnClickListener(paramInt, localInnerRcVo)
      {
        public void onClick(View paramView)
        {
          NewSceneAdapter.this.context.showSecond(this.val$position, this.val$vo.getSpaceTime() + "");
        }
      });
      if (paramInt >= -1 + getCount())
        break label525;
      paramViewHolder.edit.setVisibility(0);
      paramViewHolder.del.setVisibility(0);
    }
    while (true)
    {
      paramViewHolder.edit.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          NewSceneAdapter.this.context.onInnerRcEdit(this.val$position);
        }
      });
      paramViewHolder.del.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          NewSceneAdapter.this.context.onInnerRcDel(this.val$position);
        }
      });
      return;
      paramViewHolder.yaoKongStatus.setVisibility(0);
      paramViewHolder.down_vertical_line.setVisibility(0);
      if ((getCount() > 1) && (-2 + getCount() == paramInt))
      {
        paramViewHolder.time.setVisibility(8);
        label231: paramViewHolder.yaoKongName.setText(localInnerRcVo.getName());
        paramViewHolder.time.setText(localInnerRcVo.getSpaceTime() + ".0" + this.context.getString(2131100382));
        switch (localInnerRcVo.getmType())
        {
        case 4:
        case 7:
        case 9:
        default:
        case 5:
        case 2:
        case 1:
        case 6:
        case 8:
        case 3:
        case 10:
        case 11:
        case 12:
        }
      }
      while (true)
      {
        paramViewHolder.yaoKongStatus.setText(localInnerRcVo.getStatus());
        break;
        paramViewHolder.time.setVisibility(0);
        break label231;
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
        continue;
        paramViewHolder.icon.setImageResource(this.picRes[7]);
        continue;
        paramViewHolder.icon.setImageResource(this.picRes[8]);
      }
      label525: paramViewHolder.edit.setVisibility(8);
      paramViewHolder.del.setVisibility(8);
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
      paramView = this.layoutInflater.inflate(2130968807, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559285));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131558468));
      ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131558981));
      ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131559288));
      ViewHolder.access$402(localViewHolder, (TextView)paramView.findViewById(2131558953));
      ViewHolder.access$502(localViewHolder, (TextView)paramView.findViewById(2131559286));
      ViewHolder.access$602(localViewHolder, (TextView)paramView.findViewById(2131559287));
      paramView.setTag(localViewHolder);
    }
    initializeViews(this.yks, (ViewHolder)paramView.getTag(), paramInt);
    return paramView;
  }

  protected class ViewHolder
  {
    private TextView del;
    private ImageView down_vertical_line;
    private TextView edit;
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
 * Qualified Name:     com.ex.ltech.hongwai.scene.NewSceneAdapter
 * JD-Core Version:    0.6.0
 */