package com.ex.ltech.led.acti.timing;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.vo.RepeatDayVo;

import java.util.List;

public class RepeatDayAdapter extends BaseAdapter
{
  boolean isNewFrament = true;
  private List<RepeatDayVo> itemVos;
  private Activity pct;

  public RepeatDayAdapter(Activity paramActivity, List<RepeatDayVo> paramList)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
  }

  public RepeatDayAdapter(Activity paramActivity, List<RepeatDayVo> paramList, boolean paramBoolean)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.isNewFrament = paramBoolean;
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
    Holder localHolder;
    RepeatDayVo localRepeatDayVo;
    int k;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(R.layout.lv_item_act_repeat_day, null);
      localHolder.tv_act_repeat_day = ((TextView)paramView.findViewById(R.id.tv_act_repeat_day));
      localHolder.iv_act_repeat_day = ((ImageView)paramView.findViewById(R.id.iv_act_repeat_day));
      paramView.setTag(localHolder);

    }else{
      localHolder = (Holder)paramView.getTag();
    }
    localRepeatDayVo = (RepeatDayVo)this.itemVos.get(paramInt);
    localHolder.tv_act_repeat_day.setText(localRepeatDayVo.getDay());
    /*if (!this.isNewFrament)
      break label180;*/
    if (localRepeatDayVo.isSeleted())
      localHolder.iv_act_repeat_day.setBackgroundResource(R.mipmap.h_timing_choose_h);
    else
      localHolder.iv_act_repeat_day.setBackgroundResource(R.mipmap.h_timing_choose_n);
    if (!localRepeatDayVo.isSeleted())
      paramView.setBackgroundColor(this.pct.getResources().getColor(R.color.gray));
    else
      paramView.setBackgroundColor(this.pct.getResources().getColor(R.color.white));

    if (localRepeatDayVo.isSeleted())
      localHolder.iv_act_repeat_day.setBackgroundResource(R.mipmap.time_seleted);
    else
      localHolder.iv_act_repeat_day.setBackgroundResource(R.mipmap.time_no_seleted);
    return paramView;
  }

  class Holder
  {
    ImageView iv_act_repeat_day;
    TextView tv_act_repeat_day;

    Holder()
    {
    }
  }
}