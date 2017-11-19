package com.ex.ltech.led.acti.timing;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ex.ltech.led.acti.mode.ColorSeletedView;
import com.ex.ltech.led.vo.TimingVo;

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
    /*Holder localHolder;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968831, null);
      localHolder.tv_act_timing_time = ((TextView)paramView.findViewById(2131559306));
      localHolder.tv_act_timing_swich_status = ((TextView)paramView.findViewById(2131559376));
      localHolder.tv_act_timing_mode = ((TextView)paramView.findViewById(2131559377));
      localHolder.tv_act_timing_repeat = ((TextView)paramView.findViewById(2131559308));
      localHolder.csv_act_timing = ((ColorSeletedView)paramView.findViewById(2131559378));
      localHolder.tb_act_timing_swich = ((ToggleButton)paramView.findViewById(2131558663));
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
      if ((localList != null) && (localList.size() > 1));
      while (true)
      {
        int i = localList.size();
        if (paramInt < i)
        {
          str = str + (String)localList.get(paramInt) + "\t\t";
          paramInt++;
          continue;
          localHolder = (Holder)paramView.getTag();
          break;
          str = (String)localList.get(0);
        }
      }
    }
    localHolder.tv_act_timing_repeat.setText(str);
    if (localTimingVo.isSwich())
    {
      localHolder.tb_act_timing_swich.setToggleOn();
      if (!localTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
        break label445;
      localHolder.tv_act_timing_swich_status.setText(this.pct.getString(R.string.on));
      label335: localHolder.tb_act_timing_swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          TimingListAdapter.this.onListVSwichChangeListener.onListVSwichChange(paramBoolean, paramInt);
        }
      });
      System.out.println("fleshSpaceTime" + (System.currentTimeMillis() - this.fleshSpaceTime));
      if (localTimingVo.getColor() == 0)
        break label465;
      localHolder.csv_act_timing.setVisibility(View.VISIBLE);
      localHolder.tv_act_timing_mode.setVisibility(View.GONE);
      localHolder.csv_act_timing.setColor(localTimingVo.getColor());
    }
    while (true)
    {
      this.fleshSpaceTime = System.currentTimeMillis();
      return paramView;
      localHolder.tb_act_timing_swich.setToggleOff();
      break;
      label445: localHolder.tv_act_timing_swich_status.setText(this.pct.getString(2131100226));
      break label335;
      label465: localHolder.tv_act_timing_mode.setVisibility(View.VISIBLE);
      localHolder.csv_act_timing.setVisibility(View.GONE);
      if (!localTimingVo.isOffDevice())
        localHolder.tv_act_timing_mode.setText(localTimingVo.getModeName());
      System.out.println("");
    }*/
    return null;
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
 * Qualified Name:     com.ex.ltech.led.acti.timing.TimingListAdapter
 * JD-Core Version:    0.6.0
 */