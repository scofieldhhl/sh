package com.ex.ltech.led.acti.timing.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.vo.ModeVo;
import java.util.List;

public class MyAdapter extends BaseAdapter
{
  private List<Bitmap> bms;
  private List<ModeVo> itemVos;
  private Activity pct;

  public MyAdapter(Activity paramActivity, List<ModeVo> paramList, List<Bitmap> paramList1)
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
    Holder localHolder;
    ModeVo localModeVo;
    label120: int i;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968836, null);
      localHolder.iv_acti_timing_list_item_1 = ((MLImageView)paramView.findViewById(2131559393));
      localHolder.tv_acti_timing_list_item_2 = ((TextView)paramView.findViewById(2131559394));
      localHolder.iv_acti_timing_list_item_3 = ((ImageView)paramView.findViewById(2131559402));
      paramView.setTag(localHolder);
      localModeVo = (ModeVo)this.itemVos.get(paramInt);
      if (localModeVo.getType() != 1)
        break label209;
      localHolder.tv_acti_timing_list_item_2.setText(localModeVo.getTvName());
      localHolder.iv_acti_timing_list_item_1.setImageBitmap((Bitmap)this.bms.get(paramInt));
      ImageView localImageView = localHolder.iv_acti_timing_list_item_3;
      if (!localModeVo.isSeleted())
        break label225;
      i = 2130903724;
      label160: localImageView.setBackgroundResource(i);
      if (!localModeVo.isSeleted())
        break label232;
    }
    label209: label225: label232: for (int j = this.pct.getResources().getColor(2131492927); ; j = this.pct.getResources().getColor(2131492997))
    {
      paramView.setBackgroundColor(j);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      localHolder.tv_acti_timing_list_item_2.setText(localModeVo.getNewCreateModeName());
      break label120;
      i = 2130903595;
      break label160;
    }
  }

  static class Holder
  {
    MLImageView iv_acti_timing_list_item_1;
    ImageView iv_acti_timing_list_item_3;
    TextView tv_acti_timing_list_item_2;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.fragment.MyAdapter
 * JD-Core Version:    0.6.0
 */