package com.ex.ltech.onepiontfive.main.room.sensor;

import android.app.Activity;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import java.util.List;

public class SeletedPanelAdapter extends BaseAdapter
{
  FtSeletedPanel ftSeletedPanel;
  private List<PanelLampVO> itemVos;
  private Activity pct;

  public SeletedPanelAdapter(Activity paramActivity, List<PanelLampVO> paramList, FtSeletedPanel paramFtSeletedPanel)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.ftSeletedPanel = paramFtSeletedPanel;
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
    label156: int i;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968830, null);
      localHolder.tv_act_repeat_day = ((TextView)paramView.findViewById(2131559392));
      localHolder.iv_act_repeat_day = ((ImageView)paramView.findViewById(2131559391));
      localHolder.ic = ((ImageView)paramView.findViewById(2131558883));
      paramView.setTag(localHolder);
      PanelLampVO localPanelLampVO = (PanelLampVO)this.itemVos.get(paramInt);
      localHolder.tv_act_repeat_day.setText(localPanelLampVO.getName());
      localHolder.ic.setVisibility(View.VISIBLE);
      switch (localPanelLampVO.getType())
      {
      default:
        ImageView localImageView = localHolder.iv_act_repeat_day;
        if (!localPanelLampVO.isSeleted())
          break;
        i = 2130903786;
        label175: localImageView.setBackgroundResource(i);
        if (!localPanelLampVO.isSeleted());
      case 8:
      case 9:
      case 11:
      case 10:
      }
    }
    for (int j = this.pct.getResources().getColor(2131492927); ; j = this.pct.getResources().getColor(2131492997))
    {
      paramView.setBackgroundColor(j);
      localHolder.iv_act_repeat_day.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          SeletedPanelAdapter.this.ftSeletedPanel.onSeletedBlub(this.val$i);
        }
      });
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      localHolder.ic.setBackgroundResource(2130903700);
      break label156;
      localHolder.ic.setBackgroundResource(2130903805);
      break label156;
      localHolder.ic.setBackgroundResource(2130903085);
      break label156;
      localHolder.ic.setBackgroundResource(2130903142);
      break label156;
      i = 2130903595;
      break label175;
    }
  }

  class Holder
  {
    ImageView ic;
    ImageView iv_act_repeat_day;
    TextView tv_act_repeat_day;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.SeletedPanelAdapter
 * JD-Core Version:    0.6.0
 */