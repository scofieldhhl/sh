package com.ex.ltech.outlet.timing;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.vo.TimingVo;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.io.PrintStream;
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
    TimingVo localTimingVo;
    int i;
    label191: int j;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968829, null);
      localHolder.who_timing = ((TextView)paramView.findViewById(2131559375));
      localHolder.tv_act_outlet_timing_time = ((TextView)paramView.findViewById(2131559384));
      localHolder.tv_act_outlet_timing_swich_status = ((TextView)paramView.findViewById(2131559385));
      localHolder.tv_act_outlet_timing_mode = ((TextView)paramView.findViewById(2131559386));
      localHolder.tv_act_outlet_timing_repeat = ((TextView)paramView.findViewById(2131559387));
      localHolder.csv_act_outlet_timing = ((ColorSeletedView)paramView.findViewById(2131559388));
      localHolder.tb_act_outlet_timing_swich = ((ToggleButton)paramView.findViewById(2131559390));
      paramView.setTag(localHolder);
      localHolder.tb_act_outlet_timing_swich.setListViewItemPosi(paramInt);
      localTimingVo = (TimingVo)this.itemVos.get(paramInt);
      localHolder.tv_act_outlet_timing_time.setText(localTimingVo.getTime());
      if (localTimingVo.getStartTime().length() <= 0)
        break label360;
      i = 1;
      if (localTimingVo.getEndTime().length() <= 0)
        break label366;
      j = 1;
      label205: if ((i & j) == 0)
        break label372;
      localHolder.tv_act_outlet_timing_time.setText(localTimingVo.getStartTime() + "-" + localTimingVo.getEndTime());
      localHolder.tv_act_outlet_timing_swich_status.setText(this.pct.getString(2131100252));
    }
    String str;
    while (true)
    {
      str = "";
      List localList = localTimingVo.getShotNameDays();
      if (localList.size() <= 0)
        break label460;
      for (int m = 0; m < localList.size(); m++)
        str = str + (String)localList.get(m) + "\t\t";
      localHolder = (Holder)paramView.getTag();
      break;
      label360: i = 0;
      break label191;
      label366: j = 0;
      break label205;
      label372: if (localTimingVo.getStartTime().length() > 0)
      {
        localHolder.tv_act_outlet_timing_time.setText(localTimingVo.getStartTime());
        localHolder.tv_act_outlet_timing_swich_status.setText(this.pct.getString(2131100248));
        continue;
      }
      if (localTimingVo.getEndTime().length() <= 0)
        continue;
      localHolder.tv_act_outlet_timing_time.setText(localTimingVo.getEndTime());
      localHolder.tv_act_outlet_timing_swich_status.setText(this.pct.getString(2131099950));
    }
    label460: localHolder.tv_act_outlet_timing_repeat.setText(str);
    int k;
    if (localTimingVo.isSwich())
    {
      localHolder.tb_act_outlet_timing_swich.setToggleOn();
      if (!localTimingVo.isShowOtherTiming())
        break label644;
      localHolder.who_timing.setVisibility(0);
      TextView localTextView = localHolder.who_timing;
      if (!localTimingVo.isShowMineTiming())
        break label637;
      k = 2131100161;
      label522: localTextView.setText(k);
      label529: localHolder.tb_act_outlet_timing_swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          TimingListAdapter.this.onListVSwichChangeListener.onListVSwichChange(paramBoolean, paramInt);
        }
      });
      System.out.println("bababaab" + (System.currentTimeMillis() - this.fleshSpaceTime));
      if (localTimingVo.getColor() == 0)
        break label657;
      localHolder.csv_act_outlet_timing.setVisibility(0);
      localHolder.tv_act_outlet_timing_mode.setVisibility(8);
      localHolder.csv_act_outlet_timing.setColor(localTimingVo.getColor());
    }
    while (true)
    {
      this.fleshSpaceTime = System.currentTimeMillis();
      return paramView;
      localHolder.tb_act_outlet_timing_swich.setToggleOff();
      break;
      label637: k = 2131100255;
      break label522;
      label644: localHolder.who_timing.setVisibility(8);
      break label529;
      label657: localHolder.tv_act_outlet_timing_mode.setVisibility(0);
      localHolder.csv_act_outlet_timing.setVisibility(8);
      if (!localTimingVo.isOffDevice())
        localHolder.tv_act_outlet_timing_mode.setText(localTimingVo.getModeName());
      System.out.println("");
    }
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
    ColorSeletedView csv_act_outlet_timing;
    ToggleButton tb_act_outlet_timing_swich;
    TextView tv_act_outlet_timing_mode;
    TextView tv_act_outlet_timing_repeat;
    TextView tv_act_outlet_timing_swich_status;
    TextView tv_act_outlet_timing_time;
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
 * Qualified Name:     com.ex.ltech.outlet.timing.TimingListAdapter
 * JD-Core Version:    0.6.0
 */