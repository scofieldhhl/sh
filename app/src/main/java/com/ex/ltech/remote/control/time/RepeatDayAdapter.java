package com.ex.ltech.remote.control.time;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.vo.RepeatDayVo;
import java.util.List;

public class RepeatDayAdapter extends BaseAdapter
{
  private List<RepeatDayVo> itemVos;
  private Activity pct;

  public RepeatDayAdapter(Activity paramActivity, List<RepeatDayVo> paramList)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
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
    int i;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968830, null);
      localHolder.tv_act_repeat_day = ((TextView)paramView.findViewById(2131559392));
      localHolder.iv_act_repeat_day = ((ImageView)paramView.findViewById(2131559391));
      paramView.setTag(localHolder);
      RepeatDayVo localRepeatDayVo = (RepeatDayVo)this.itemVos.get(paramInt);
      localHolder.tv_act_repeat_day.setText(localRepeatDayVo.getDay());
      ImageView localImageView = localHolder.iv_act_repeat_day;
      if (!localRepeatDayVo.isSeleted())
        break label166;
      i = 2130903724;
      label117: localImageView.setBackgroundResource(i);
      if (!localRepeatDayVo.isSeleted())
        break label173;
    }
    label166: label173: for (int j = this.pct.getResources().getColor(2131492927); ; j = this.pct.getResources().getColor(2131492997))
    {
      paramView.setBackgroundColor(j);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      i = 2130903595;
      break label117;
    }
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
 * Qualified Name:     com.ex.ltech.remote.control.time.RepeatDayAdapter
 * JD-Core Version:    0.6.0
 */