package com.ex.ltech.led.acti.mode;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import java.util.List;

public class SysCustomAdapter extends BaseAdapter
{
  private List<SceneSysCustomItemVo> itemVos;
  private Activity pct;

  public SysCustomAdapter(Activity paramActivity, List<SceneSysCustomItemVo> paramList)
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
      paramView = LayoutInflater.from(this.pct).inflate(2130968834, null);
      localHolder.tv_acti_scene_custom_list_item_1 = ((TextView)paramView.findViewById(2131559396));
      localHolder.iv_acti_scene_custom_list_item_2 = ((TextView)paramView.findViewById(2131559397));
      localHolder.cs__scene_custom_list_item_3 = ((ColorSeletedView)paramView.findViewById(2131559400));
      localHolder.iv_acti_scene_custom_list_item_4 = ((ImageView)paramView.findViewById(2131559398));
      paramView.setTag(localHolder);
      SceneSysCustomItemVo localSceneSysCustomItemVo = (SceneSysCustomItemVo)this.itemVos.get(paramInt);
      localHolder.tv_acti_scene_custom_list_item_1.setText(localSceneSysCustomItemVo.getModeName());
      localHolder.iv_acti_scene_custom_list_item_2.setText(localSceneSysCustomItemVo.getBlingName());
      localHolder.cs__scene_custom_list_item_3.setColors(localSceneSysCustomItemVo.getColors());
      ImageView localImageView = localHolder.iv_acti_scene_custom_list_item_4;
      if (!localSceneSysCustomItemVo.isSeleted())
        break label220;
      i = 2130903724;
      label171: localImageView.setBackgroundResource(i);
      if (!localSceneSysCustomItemVo.isSeleted())
        break label227;
    }
    label220: label227: for (int j = this.pct.getResources().getColor(R.color.gray); ; j = this.pct.getResources().getColor(R.color.white))
    {
      paramView.setBackgroundColor(j);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      i = 2130903595;
      break label171;
    }
  }

  class Holder
  {
    ColorSeletedView cs__scene_custom_list_item_3;
    TextView iv_acti_scene_custom_list_item_2;
    ImageView iv_acti_scene_custom_list_item_4;
    TextView tv_acti_scene_custom_list_item_1;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.mode.SysCustomAdapter
 * JD-Core Version:    0.6.0
 */