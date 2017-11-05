package com.ex.ltech.hongwai.time;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.TimingVo;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.List;

public class TimingListAdapter extends BaseAdapter
{
  private long fleshSpaceTime;
  private Handler handler;
  public boolean isLvScroll;
  private List<TimingVo> itemVos;
  OnListVSwichChangeListener onListVSwichChangeListener;
  private Activity pct;
  public int swichOff = 2;
  public int swichOn = 1;

  public TimingListAdapter(Activity paramActivity, List<TimingVo> paramList)
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
      paramView = LayoutInflater.from(this.pct).inflate(2130968826, null);
      localHolder.tv_act_timing_time = ((TextView)paramView.findViewById(2131559306));
      localHolder.tv_act_timing_swich_status = ((TextView)paramView.findViewById(2131559376));
      localHolder.tv_act_timing_mode = ((TextView)paramView.findViewById(2131559377));
      localHolder.tv_act_timing_repeat = ((TextView)paramView.findViewById(2131559308));
      localHolder.csv_act_timing = ((ColorSeletedView)paramView.findViewById(2131559378));
      localHolder.tb_act_timing_swich = ((ToggleButton)paramView.findViewById(2131558663));
      localHolder.who_timing = ((TextView)paramView.findViewById(2131559375));
      paramView.setTag(localHolder);
    }
    TimingVo localTimingVo;
    String str;
    while (true)
    {
      localHolder.tb_act_timing_swich.setListViewItemPosi(paramInt);
      localTimingVo = (TimingVo)this.itemVos.get(paramInt);
      localHolder.tv_act_timing_time.setText(localTimingVo.getTime());
      str = "";
      List localList = localTimingVo.getShotNameDays();
      if (localList == null)
        break;
      for (int j = 0; j < localList.size(); j++)
        str = str + (String)localList.get(j) + "\t";
      localHolder = (Holder)paramView.getTag();
    }
    localHolder.tv_act_timing_repeat.setText(str);
    label366: TextView localTextView;
    if (localTimingVo.isSwich())
    {
      localHolder.tb_act_timing_swich.setToggleOn();
      localHolder.tb_act_timing_swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          if ((TimingListAdapter.this.onListVSwichChangeListener != null) && (System.currentTimeMillis() - TimingListAdapter.this.fleshSpaceTime > 300L))
            TimingListAdapter.this.onListVSwichChangeListener.onListVSwichChange(paramBoolean, paramInt);
        }
      });
      if (localTimingVo.isOther())
        break label497;
      localHolder.tv_act_timing_swich_status.setText(localTimingVo.getYaoKongName());
      if (localTimingVo.getColor() == 0)
        break label459;
      localHolder.csv_act_timing.setVisibility(View.VISIBLE);
      localHolder.tv_act_timing_mode.setVisibility(View.GONE);
      localHolder.csv_act_timing.setColor(localTimingVo.getColor());
      this.fleshSpaceTime = System.currentTimeMillis();
      if (localTimingVo.getRcVo() == null)
        break label481;
      localHolder.tv_act_timing_mode.setText(localTimingVo.getRcVo().getStatus());
      label397: if (!(localTimingVo.isShowMineTiming() | localTimingVo.isShowOtherTiming()))
        break label530;
      localHolder.who_timing.setVisibility(View.VISIBLE);
      localTextView = localHolder.who_timing;
      if (!localTimingVo.isShowMineTiming())
        break label523;
    }
    label523: for (int i = 2131100161; ; i = 2131100255)
    {
      localTextView.setText(i);
      return paramView;
      localHolder.tb_act_timing_swich.setToggleOff();
      break;
      label459: localHolder.tv_act_timing_mode.setVisibility(View.VISIBLE);
      localHolder.csv_act_timing.setVisibility(View.GONE);
      break label366;
      label481: localHolder.tv_act_timing_mode.setText(localTimingVo.getStatus());
      break label397;
      label497: localHolder.tv_act_timing_mode.setVisibility(View.GONE);
      localHolder.tv_act_timing_swich_status.setText(localTimingVo.getStatus());
      break label397;
    }
    label530: localHolder.who_timing.setVisibility(View.GONE);
    return paramView;
  }

  public void setHandler(Handler paramHandler)
  {
    this.handler = paramHandler;
  }

  public void setOnListVSwichChangeListener(OnListVSwichChangeListener paramOnListVSwichChangeListener)
  {
    this.onListVSwichChangeListener = paramOnListVSwichChangeListener;
  }

  class Holder
  {
    ColorSeletedView csv_act_timing;
    ToggleButton tb_act_timing_swich;
    TextView tv_act_timing_mode;
    TextView tv_act_timing_repeat;
    TextView tv_act_timing_swich_status;
    TextView tv_act_timing_time;
    TextView who_timing;

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
 * Qualified Name:     com.ex.ltech.hongwai.time.TimingListAdapter
 * JD-Core Version:    0.6.0
 */