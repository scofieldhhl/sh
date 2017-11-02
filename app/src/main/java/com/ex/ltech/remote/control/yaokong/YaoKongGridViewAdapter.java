package com.ex.ltech.remote.control.yaokong;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.remote.control.vo.YaoKongVo;
import java.util.List;

public class YaoKongGridViewAdapter extends BaseAdapter
{
  private List<YaoKongVo> itemVos;
  private Activity pct;

  public YaoKongGridViewAdapter(Activity paramActivity, List<YaoKongVo> paramList)
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
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968835, null);
      localHolder.iv_acti_scene_list_item_1 = ((ImageView)paramView.findViewById(2131559401));
      localHolder.tv_acti_scene_list_item_2 = ((TextView)paramView.findViewById(2131559312));
      paramView.setTag(localHolder);
    }
    while (true)
    {
      YaoKongVo localYaoKongVo = (YaoKongVo)this.itemVos.get(paramInt);
      localHolder.iv_acti_scene_list_item_1.setBackgroundResource(localYaoKongVo.getIvLeftRes());
      localHolder.tv_acti_scene_list_item_2.setText(localYaoKongVo.getTvName());
      return paramView;
      localHolder = (Holder)paramView.getTag();
    }
  }

  class Holder
  {
    ImageView iv_acti_scene_list_item_1;
    TextView tv_acti_scene_list_item_2;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.YaoKongGridViewAdapter
 * JD-Core Version:    0.6.0
 */