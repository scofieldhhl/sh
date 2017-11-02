package com.ex.ltech.onepiontfive.main.time;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.List;

public class TimingListAdapter extends BaseAdapter
{
  private long fleshSpaceTime;
  public boolean isLvScroll;
  private List<Timing> itemVos;
  OnListVSwichChangeListener onListVSwichChangeListener;
  private Activity pct;

  public TimingListAdapter(Activity paramActivity, List<Timing> paramList)
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
    String str;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968801, null);
      localHolder.tv_act_timing_time = ((TextView)paramView.findViewById(2131559306));
      localHolder.scene_info = ((TextView)paramView.findViewById(2131559307));
      localHolder.tv_act_timing_repeat = ((TextView)paramView.findViewById(2131559308));
      localHolder.tb_act_timing_swich = ((ToggleButton)paramView.findViewById(2131558663));
      paramView.setTag(localHolder);
      localHolder.tb_act_timing_swich.setListViewItemPosi(paramInt);
      Timing localTiming = (Timing)this.itemVos.get(paramInt);
      localHolder.tv_act_timing_time.setText(localTiming.getTime());
      TextView localTextView = localHolder.scene_info;
      StringBuilder localStringBuilder = new StringBuilder();
      if (!localTiming.isOnOff())
        break label272;
      str = this.pct.getString(2131100232);
      label170: localTextView.setText(str + "\t" + localTiming.getSeletedInfo());
      if (localTiming.getSeletedScenePosi() > 0);
      localHolder.tv_act_timing_repeat.setText(localTiming.getShotDaysStr());
      if (!localTiming.isSwich())
        break label286;
      localHolder.tb_act_timing_swich.setToggleOn();
    }
    while (true)
    {
      localHolder.tb_act_timing_swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          if ((TimingListAdapter.this.onListVSwichChangeListener != null) && (System.currentTimeMillis() - TimingListAdapter.this.fleshSpaceTime > 300L))
            TimingListAdapter.this.onListVSwichChangeListener.onListVSwichChange(paramBoolean, paramInt);
        }
      });
      this.fleshSpaceTime = System.currentTimeMillis();
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      label272: str = this.pct.getString(2131100226);
      break label170;
      label286: localHolder.tb_act_timing_swich.setToggleOff();
    }
  }

  public void setItemVos(List<Timing> paramList)
  {
    this.itemVos = paramList;
  }

  public void setOnListVSwichChangeListener(OnListVSwichChangeListener paramOnListVSwichChangeListener)
  {
    this.onListVSwichChangeListener = paramOnListVSwichChangeListener;
  }

  class Holder
  {
    TextView scene_info;
    ToggleButton tb_act_timing_swich;
    TextView tv_act_timing_repeat;
    TextView tv_act_timing_time;

    Holder()
    {
    }
  }

  public static abstract interface OnListVSwichChangeListener
  {
    public abstract void onListVSwichChange(boolean paramBoolean, int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.TimingListAdapter
 * JD-Core Version:    0.6.0
 */