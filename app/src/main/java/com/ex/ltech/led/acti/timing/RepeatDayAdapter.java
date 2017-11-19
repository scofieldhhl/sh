package com.ex.ltech.led.acti.timing;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
    /*Holder localHolder;
    RepeatDayVo localRepeatDayVo;
    int k;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968830, null);
      localHolder.tv_act_repeat_day = ((TextView)paramView.findViewById(2131559392));
      localHolder.iv_act_repeat_day = ((ImageView)paramView.findViewById(2131559391));
      paramView.setTag(localHolder);
      localRepeatDayVo = (RepeatDayVo)this.itemVos.get(paramInt);
      localHolder.tv_act_repeat_day.setText(localRepeatDayVo.getDay());
      if (this.isNewFrament)
        break label180;
      ImageView localImageView2 = localHolder.iv_act_repeat_day;
      if (!localRepeatDayVo.isSeleted())
        break label173;
      k = 2130903276;
      label124: localImageView2.setBackgroundResource(k);
      if (!localRepeatDayVo.isSeleted())
        break label216;
    }
    label173: label180: label216: for (int j = this.pct.getResources().getColor(R.color.gray); ; j = this.pct.getResources().getColor(R.color.white))
    {
      paramView.setBackgroundColor(j);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      k = 2130903277;
      break label124;
      ImageView localImageView1 = localHolder.iv_act_repeat_day;
      if (localRepeatDayVo.isSeleted());
      for (int i = 2130903786; ; i = 2130903784)
      {
        localImageView1.setBackgroundResource(i);
        break;
      }
    }*/
    return null;
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

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.RepeatDayAdapter
 * JD-Core Version:    0.6.0
 */