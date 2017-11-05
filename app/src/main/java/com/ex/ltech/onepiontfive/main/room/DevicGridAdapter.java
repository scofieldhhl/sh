package com.ex.ltech.onepiontfive.main.room;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import java.util.ArrayList;
import java.util.List;

public class DevicGridAdapter extends BaseAdapter
{
  private Context context;
  public List<Dvc> dvcVos;
  private LayoutInflater layoutInflater;
  OnMyDelBtnListener onMyDelBtnListener;

  public DevicGridAdapter(Context paramContext, List<Dvc> paramList, OnMyDelBtnListener paramOnMyDelBtnListener)
  {
    this.context = paramContext;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.dvcVos = paramList;
    this.onMyDelBtnListener = paramOnMyDelBtnListener;
  }

  private void initializeViews(Dvc paramDvc, ViewHolder paramViewHolder, int paramInt)
  {
    int i = 1;
    paramViewHolder.bt_del.setOnClickListener(new View.OnClickListener(paramDvc, paramInt)
    {
      public void onClick(View paramView)
      {
        DevicGridAdapter.this.onMyDelBtnListener.onDeivceEdit(this.val$vo, this.val$posi);
      }
    });
    paramViewHolder.rl_off_light_layer.setVisibility(View.GONE);
    paramViewHolder.name.setTextColor(-16777216);
    paramViewHolder.name.setVisibility(View.VISIBLE);
    ArrayList localArrayList;
    int i5;
    label267: int i6;
    label282: int i7;
    label330: ImageView localImageView2;
    label388: int i3;
    if ((paramDvc.isGroup()) && (paramDvc.innerDvcVos.size() > i))
    {
      paramViewHolder.name.setText(paramDvc.getGroupName());
      paramViewHolder.ivIc.setVisibility(View.GONE);
      paramViewHolder.groupBg.setVisibility(View.VISIBLE);
      int i4 = paramDvc.getType();
      paramViewHolder.group_inner1.setVisibility(View.GONE);
      paramViewHolder.group_inner2.setVisibility(View.GONE);
      paramViewHolder.group_inner3.setVisibility(View.GONE);
      switch (i4)
      {
      default:
        localArrayList = paramDvc.innerDvcVos;
        if (localArrayList.size() == i)
          paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
        if (localArrayList.size() == 2)
        {
          paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
          paramViewHolder.group_inner2.setVisibility(View.VISIBLE);
        }
        if (localArrayList.size() == 3)
        {
          paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
          paramViewHolder.group_inner2.setVisibility(View.VISIBLE);
          paramViewHolder.group_inner3.setVisibility(View.VISIBLE);
        }
        if (localArrayList.size() != 4)
          break;
        i5 = i;
        if (localArrayList.size() > 4)
        {
          i6 = i;
          if ((i5 | i6) != 0)
          {
            paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
            paramViewHolder.group_inner2.setVisibility(View.VISIBLE);
            paramViewHolder.group_inner3.setVisibility(View.VISIBLE);
          }
          ImageView localImageView3 = paramViewHolder.ligth_status_bg;
          if (!paramDvc.isClickSeleted())
            break label578;
          i7 = 0;
          localImageView3.setVisibility(i7);
          if (paramDvc.isOnLine())
            break label584;
          paramViewHolder.ivIc.setVisibility(View.VISIBLE);
          paramViewHolder.group_inner1.setVisibility(View.GONE);
          paramViewHolder.group_inner2.setVisibility(View.GONE);
          paramViewHolder.group_inner3.setVisibility(View.GONE);
          paramViewHolder.ivIc.setBackgroundResource(2130903597);
          localImageView2 = paramViewHolder.bt_del;
          boolean bool = paramDvc.isShowDelBtn();
          i3 = 0;
          if (!bool)
            break label1196;
        }
      case 11:
      case 9:
      case 10:
      case 8:
      case 12:
      }
    }
    while (true)
    {
      localImageView2.setVisibility(i3);
      return;
      paramViewHolder.group_inner1.setBackgroundResource(2130903085);
      paramViewHolder.group_inner2.setBackgroundResource(2130903085);
      paramViewHolder.group_inner3.setBackgroundResource(2130903085);
      break;
      paramViewHolder.group_inner1.setBackgroundResource(2130903805);
      paramViewHolder.group_inner2.setBackgroundResource(2130903805);
      paramViewHolder.group_inner3.setBackgroundResource(2130903805);
      break;
      paramViewHolder.group_inner1.setBackgroundResource(2130903142);
      paramViewHolder.group_inner2.setBackgroundResource(2130903142);
      paramViewHolder.group_inner3.setBackgroundResource(2130903142);
      break;
      paramViewHolder.group_inner1.setBackgroundResource(2130903700);
      paramViewHolder.group_inner2.setBackgroundResource(2130903700);
      paramViewHolder.group_inner3.setBackgroundResource(2130903700);
      break;
      paramViewHolder.group_inner1.setBackgroundResource(2130903701);
      paramViewHolder.group_inner2.setBackgroundResource(2130903701);
      paramViewHolder.group_inner3.setBackgroundResource(2130903701);
      break;
      i5 = 0;
      break label267;
      i6 = 0;
      break label282;
      label578: i7 = 4;
      break label330;
      label584: if (!paramDvc.isOnOff())
      {
        paramViewHolder.group_inner1.setVisibility(View.GONE);
        paramViewHolder.group_inner2.setVisibility(View.GONE);
        paramViewHolder.group_inner3.setVisibility(View.GONE);
        paramViewHolder.ivIc.setVisibility(View.VISIBLE);
        paramViewHolder.ivIc.setBackgroundResource(2130903596);
        break label388;
      }
      paramViewHolder.ivIc.setVisibility(View.GONE);
      if (localArrayList.size() == i)
        paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
      if (localArrayList.size() == 2)
      {
        paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
        paramViewHolder.group_inner2.setVisibility(View.VISIBLE);
      }
      if (localArrayList.size() != 3)
        break label388;
      paramViewHolder.group_inner1.setVisibility(View.VISIBLE);
      paramViewHolder.group_inner2.setVisibility(View.VISIBLE);
      paramViewHolder.group_inner3.setVisibility(View.VISIBLE);
      break label388;
      paramViewHolder.name.setText(paramDvc.getName());
      paramViewHolder.groupBg.setVisibility(View.GONE);
      paramViewHolder.group_inner1.setVisibility(View.GONE);
      paramViewHolder.group_inner2.setVisibility(View.GONE);
      paramViewHolder.group_inner3.setVisibility(View.GONE);
      paramViewHolder.ivIc.setVisibility(View.VISIBLE);
      ImageView localImageView1 = paramViewHolder.ligth_status_bg;
      int j;
      label803: int k;
      label896: label909: int m;
      label922: int i1;
      label942: int i2;
      if (paramDvc.isClickSeleted())
      {
        j = 0;
        localImageView1.setVisibility(j);
        if (!paramDvc.isOnOff())
          break label1160;
        switch (paramDvc.getType())
        {
        case 19:
        case 20:
        default:
          if (paramDvc.getType() != 10)
            break;
          k = i;
          if (paramDvc.getType() == 11)
          {
            m = i;
            int n = m | k;
            if (paramDvc.getType() != 9)
              break label1184;
            i1 = i;
            i2 = i1 | n;
            if (paramDvc.getType() != 8)
              break label1190;
          }
        case 12:
        case 8:
        case 9:
        case 11:
        case 10:
        case 14:
        case 13:
        case 21:
        case 15:
        case 16:
        case 17:
        case 18:
        case 22:
        }
      }
      while (true)
      {
        if (((i2 | i) == 0) || (!paramDvc.isOnLine()))
          paramViewHolder.ivIc.setBackgroundResource(2130903597);
        paramDvc.getType();
        break;
        j = 4;
        break label803;
        paramViewHolder.ivIc.setBackgroundResource(2130903701);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903700);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903805);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903085);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903142);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903731);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903621);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903679);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903607);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903780);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903800);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903600);
        break label896;
        paramViewHolder.ivIc.setBackgroundResource(2130903045);
        paramViewHolder.ligth_status_bg.setVisibility(4);
        break label896;
        label1160: paramViewHolder.ivIc.setBackgroundResource(2130903596);
        break label896;
        k = 0;
        break label909;
        m = 0;
        break label922;
        label1184: i1 = 0;
        break label942;
        label1190: i = 0;
      }
      label1196: i3 = 8;
    }
  }

  public int getCount()
  {
    return this.dvcVos.size();
  }

  public Dvc getItem(int paramInt)
  {
    return (Dvc)this.dvcVos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
    {
      paramView = this.layoutInflater.inflate(2130968798, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559290));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131559302));
      ViewHolder.access$202(localViewHolder, (TextView)paramView.findViewById(2131559008));
      ViewHolder.access$302(localViewHolder, (ImageView)paramView.findViewById(2131559294));
      ViewHolder.access$402(localViewHolder, (ImageView)paramView.findViewById(2131559301));
      ViewHolder.access$502(localViewHolder, (ImageView)paramView.findViewById(2131559288));
      ViewHolder.access$602(localViewHolder, (ImageView)paramView.findViewById(2131559303));
      ViewHolder.access$702(localViewHolder, (ImageView)paramView.findViewById(2131559304));
      ViewHolder.access$802(localViewHolder, (ImageView)paramView.findViewById(2131559305));
      paramView.setTag(localViewHolder);
    }
    initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
    View localView = paramView.findViewById(2131559300);
    if (getItem(paramInt).isSeleted());
    for (int i = 8; ; i = 0)
    {
      localView.setVisibility(i);
      return paramView;
    }
  }

  public void setDvcVos(List<Dvc> paramList)
  {
    this.dvcVos.clear();
    this.dvcVos.addAll(paramList);
  }

  public static abstract interface OnMyDelBtnListener
  {
    public abstract void onDeivceEdit(Dvc paramDvc, int paramInt);
  }

  protected class ViewHolder
  {
    private ImageView bt_del;
    private ImageView groupBg;
    private ImageView group_inner1;
    private ImageView group_inner2;
    private ImageView group_inner3;
    private ImageView group_inner4;
    private ImageView ivIc;
    private ImageView ligth_status_bg;
    private TextView name;
    private ImageView rl_off_light_layer;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.DevicGridAdapter
 * JD-Core Version:    0.6.0
 */