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
import java.util.List;

public class GroupAdapter extends BaseAdapter
{
  private Context context;
  public List<Dvc> dvcVos;
  private LayoutInflater layoutInflater;
  OnMyDelBtnListener onMyDelBtnListener;

  public GroupAdapter(Context paramContext, List<Dvc> paramList, OnMyDelBtnListener paramOnMyDelBtnListener)
  {
    this.context = paramContext;
    this.layoutInflater = LayoutInflater.from(paramContext);
    this.dvcVos = paramList;
    this.onMyDelBtnListener = paramOnMyDelBtnListener;
  }

  private void initializeViews(Dvc paramDvc, ViewHolder paramViewHolder, int paramInt)
  {
    paramViewHolder.name.setText(paramDvc.getName());
    label84: ImageView localImageView;
    int j;
    if (paramDvc.isClickSeleted())
    {
      paramViewHolder.iv_seleted.setVisibility(0);
      int i = paramDvc.getType();
      if (!paramDvc.isOnLine())
        break label200;
      if (!paramDvc.isOnOff())
        break label188;
      switch (i)
      {
      case 10:
      default:
        localImageView = paramViewHolder.del;
        boolean bool = paramDvc.isShowDelBtn();
        j = 0;
        if (!bool)
          break;
      case 11:
      case 9:
      case 8:
      case 12:
      }
    }
    while (true)
    {
      localImageView.setVisibility(j);
      paramViewHolder.del.setOnClickListener(new View.OnClickListener(paramDvc, paramInt)
      {
        public void onClick(View paramView)
        {
          GroupAdapter.this.onMyDelBtnListener.onGroudInnerDeviceEdit(this.val$vo, this.val$position);
        }
      });
      return;
      paramViewHolder.iv_seleted.setVisibility(4);
      break;
      paramViewHolder.ivIc.setBackgroundResource(2130903085);
      break label84;
      paramViewHolder.ivIc.setBackgroundResource(2130903805);
      break label84;
      paramViewHolder.ivIc.setBackgroundResource(2130903700);
      break label84;
      paramViewHolder.ivIc.setBackgroundResource(2130903701);
      break label84;
      label188: paramViewHolder.ivIc.setBackgroundResource(2130903596);
      break label84;
      label200: paramViewHolder.ivIc.setBackgroundResource(2130903597);
      break label84;
      j = 8;
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
      paramView = this.layoutInflater.inflate(2130968802, null);
      ViewHolder localViewHolder = new ViewHolder();
      ViewHolder.access$002(localViewHolder, (ImageView)paramView.findViewById(2131559288));
      ViewHolder.access$102(localViewHolder, (ImageView)paramView.findViewById(2131559309));
      ViewHolder.access$202(localViewHolder, (ImageView)paramView.findViewById(2131559290));
      ViewHolder.access$302(localViewHolder, (TextView)paramView.findViewById(2131559008));
      paramView.setTag(localViewHolder);
    }
    initializeViews(getItem(paramInt), (ViewHolder)paramView.getTag(), paramInt);
    return paramView;
  }

  public void setDvcVos(List<Dvc> paramList)
  {
    this.dvcVos.clear();
    this.dvcVos.addAll(paramList);
  }

  public static abstract interface OnMyDelBtnListener
  {
    public abstract void onGroudInnerDeviceEdit(Dvc paramDvc, int paramInt);
  }

  protected class ViewHolder
  {
    private ImageView del;
    private ImageView ivIc;
    private ImageView iv_seleted;
    private TextView name;

    protected ViewHolder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.GroupAdapter
 * JD-Core Version:    0.6.0
 */