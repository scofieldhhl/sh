package com.ex.ltech.led.acti.scene;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import java.util.List;

public class SysInsideAdapter extends BaseAdapter
{
  private List<SceneSysInsideItemVo> itemVos;
  private Activity pct;

  public SysInsideAdapter(Activity paramActivity, List<SceneSysInsideItemVo> paramList)
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
      paramView = LayoutInflater.from(this.pct).inflate(2130968835, null);
      localHolder.iv_acti_scene_list_item_1 = ((ImageView)paramView.findViewById(2131559401));
      localHolder.tv_acti_scene_list_item_2 = ((TextView)paramView.findViewById(2131559312));
      localHolder.iv_acti_scene_list_item_4 = ((ImageView)paramView.findViewById(2131559311));
      paramView.setTag(localHolder);
      SceneSysInsideItemVo localSceneSysInsideItemVo = (SceneSysInsideItemVo)this.itemVos.get(paramInt);
      localHolder.iv_acti_scene_list_item_1.setBackgroundResource(localSceneSysInsideItemVo.getIvLeftRes());
      localHolder.tv_acti_scene_list_item_2.setText(localSceneSysInsideItemVo.getTvName());
      ImageView localImageView = localHolder.iv_acti_scene_list_item_4;
      if (!localSceneSysInsideItemVo.isSeleted())
        break label193;
      i = 2130903724;
      label144: localImageView.setBackgroundResource(i);
      if (!localSceneSysInsideItemVo.isSeleted())
        break label200;
    }
    label193: label200: for (int j = this.pct.getResources().getColor(R.color.gray); ; j = this.pct.getResources().getColor(R.color.white))
    {
      paramView.setBackgroundColor(j);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      i = 2130903595;
      break label144;
    }
  }

  class Holder
  {
    ImageView iv_acti_scene_list_item_1;
    ImageView iv_acti_scene_list_item_4;
    TextView tv_acti_scene_list_item_2;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.scene.SysInsideAdapter
 * JD-Core Version:    0.6.0
 */