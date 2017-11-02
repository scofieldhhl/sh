package com.ex.ltech.ct.timing;

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
import java.util.ArrayList;
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
  private List<TimingVo> titleDataVos = new ArrayList();

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
      paramView = LayoutInflater.from(this.pct).inflate(2130968827, null);
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
      int j;
      if ((localList != null) && (localList.size() > 0))
        j = 0;
      while (true)
        if (j < localList.size())
        {
          str = str + (String)localList.get(j) + "\t\t";
          j++;
          continue;
          localHolder = (Holder)paramView.getTag();
          break;
          str = (String)localList.get(0);
        }
    }
    localHolder.tv_act_timing_repeat.setText(str);
    label349: int i;
    if (localTimingVo.isSwich())
    {
      localHolder.tb_act_timing_swich.setToggleOn();
      if (!localTimingVo.getLightStatus().equals(this.pct.getString(2131100232)))
        break label507;
      localHolder.tv_act_timing_swich_status.setText(this.pct.getString(2131100232));
      if (localTimingVo.isOther())
        break label578;
      localHolder.csv_act_timing.setVisibility(0);
      localHolder.tv_act_timing_mode.setVisibility(0);
      if (localTimingVo.getColor() == 0)
        break label527;
      localHolder.csv_act_timing.setVisibility(0);
      localHolder.tv_act_timing_mode.setVisibility(8);
      localHolder.csv_act_timing.setColor(localTimingVo.getColor());
      label415: this.fleshSpaceTime = System.currentTimeMillis();
      label422: if (!(localTimingVo.isShowMineTiming() | localTimingVo.isShowOtherTiming()))
        break label606;
      localHolder.who_timing.setVisibility(0);
      TextView localTextView = localHolder.who_timing;
      if (!localTimingVo.isShowMineTiming())
        break label599;
      i = 2131100161;
      label464: localTextView.setText(i);
    }
    while (true)
    {
      localHolder.tb_act_timing_swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
          if (TimingListAdapter.this.onListVSwichChangeListener != null)
            TimingListAdapter.this.onListVSwichChangeListener.onListVSwichChange(paramBoolean, paramInt);
        }
      });
      this.fleshSpaceTime = System.currentTimeMillis();
      return paramView;
      localHolder.tb_act_timing_swich.setToggleOff();
      break;
      label507: localHolder.tv_act_timing_swich_status.setText(this.pct.getString(2131100226));
      break label349;
      label527: localHolder.tv_act_timing_mode.setVisibility(0);
      localHolder.csv_act_timing.setVisibility(8);
      if (!localTimingVo.isOffDevice())
        localHolder.tv_act_timing_mode.setText(localTimingVo.getModeName());
      System.out.println("");
      break label415;
      label578: localHolder.tv_act_timing_mode.setVisibility(4);
      localHolder.csv_act_timing.setVisibility(4);
      break label422;
      label599: i = 2131100255;
      break label464;
      label606: localHolder.who_timing.setVisibility(8);
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
 * Qualified Name:     com.ex.ltech.ct.timing.TimingListAdapter
 * JD-Core Version:    0.6.0
 */