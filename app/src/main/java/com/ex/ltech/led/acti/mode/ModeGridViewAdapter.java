package com.ex.ltech.led.acti.mode;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.ModeVo;
import java.util.List;

public class ModeGridViewAdapter extends BaseAdapter
{
  private List<Bitmap> bms;
  private int bmsIndex = 0;
  private List<ModeVo> itemVos;
  ActMode.MoreSeletedListener moreSeletedListener;
  private Activity pct;
  ActMode.SingleSeletedListener singleSeletedListener;

  public ModeGridViewAdapter(Activity paramActivity, List<ModeVo> paramList, List<Bitmap> paramList1)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.bms = paramList1;
  }

  public int getCount()
  {
    return this.itemVos.size();
  }

  public Object getItem(int paramInt)
  {
    return this.itemVos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int i = 2130903724;
    Holder localHolder;
    ModeVo localModeVo;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968835, null);
      localHolder.iv_acti_scene_list_item_1 = ((ImageView)paramView.findViewById(2131559401));
      localHolder.tv_acti_scene_list_item_2 = ((TextView)paramView.findViewById(2131559312));
      localHolder.iv_acti_scene_list_item_4 = ((ImageView)paramView.findViewById(2131559311));
      localHolder.iv_acti_scene_list_item_5 = ((MLImageView)paramView.findViewById(2131559310));
      paramView.setTag(localHolder);
      localModeVo = (ModeVo)this.itemVos.get(paramInt);
    }
    switch (localModeVo.getType())
    {
    default:
    case 1:
    case 2:
      while (true)
      {
        localHolder.iv_acti_scene_list_item_4.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            ModeGridViewAdapter.this.moreSeletedListener.onMoreSeleted(this.val$i);
          }
        });
        localHolder.iv_acti_scene_list_item_5.setOnClickListener(new View.OnClickListener(paramInt)
        {
          public void onClick(View paramView)
          {
            ModeGridViewAdapter.this.singleSeletedListener.onSingleSeleted(this.val$i);
          }
        });
        localHolder.iv_acti_scene_list_item_5.setOnLongClickListener(new View.OnLongClickListener(paramInt)
        {
          public boolean onLongClick(View paramView)
          {
            ModeGridViewAdapter.this.singleSeletedListener.onLongClick(this.val$i);
            return false;
          }
        });
        if (paramInt == -1 + getCount())
          localHolder.iv_acti_scene_list_item_5.setOnLongClickListener(null);
        return paramView;
        localHolder = (Holder)paramView.getTag();
        break;
        localHolder.iv_acti_scene_list_item_4.setVisibility(View.VISIBLE);
        localHolder.iv_acti_scene_list_item_1.setBackgroundResource(localModeVo.getIvLeftRes());
        localHolder.tv_acti_scene_list_item_2.setText(localModeVo.getTvName());
        ImageView localImageView2 = localHolder.iv_acti_scene_list_item_4;
        int k;
        if (localModeVo.isSeleted())
        {
          k = i;
          label286: localImageView2.setBackgroundResource(k);
          if (!localModeVo.isSingleSeleted())
            break label331;
        }
        label331: for (int m = this.pct.getResources().getColor(2131492927); ; m = this.pct.getResources().getColor(2131492997))
        {
          paramView.setBackgroundColor(m);
          break;
          k = 2130903595;
          break label286;
        }
        localHolder.iv_acti_scene_list_item_1.setBackgroundResource(2130903702);
        localHolder.tv_acti_scene_list_item_2.setText("");
        localHolder.iv_acti_scene_list_item_4.setVisibility(View.GONE);
        paramView.setBackgroundColor(this.pct.getResources().getColor(2131492997));
      }
    case 3:
    }
    localHolder.iv_acti_scene_list_item_4.setVisibility(View.VISIBLE);
    localHolder.tv_acti_scene_list_item_2.setText(localModeVo.getNewCreateModeName());
    localHolder.iv_acti_scene_list_item_5.setImageBitmap((Bitmap)this.bms.get(paramInt));
    ImageView localImageView1 = localHolder.iv_acti_scene_list_item_4;
    if (localModeVo.isSeleted())
    {
      label455: localImageView1.setBackgroundResource(i);
      if (!localModeVo.isSingleSeleted())
        break label510;
    }
    label510: for (int j = this.pct.getResources().getColor(2131492927); ; j = this.pct.getResources().getColor(2131492997))
    {
      paramView.setBackgroundColor(j);
      this.bmsIndex = (1 + this.bmsIndex);
      break;
      i = 2130903595;
      break label455;
    }
  }

  public void setMoreSeletedListener(ActMode.MoreSeletedListener paramMoreSeletedListener)
  {
    this.moreSeletedListener = paramMoreSeletedListener;
  }

  public void setSingleSeletedListener(ActMode.SingleSeletedListener paramSingleSeletedListener)
  {
    this.singleSeletedListener = paramSingleSeletedListener;
  }

  class Holder
  {
    ImageView iv_acti_scene_list_item_1;
    ImageView iv_acti_scene_list_item_4;
    MLImageView iv_acti_scene_list_item_5;
    TextView tv_acti_scene_list_item_2;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.mode.ModeGridViewAdapter
 * JD-Core Version:    0.6.0
 */