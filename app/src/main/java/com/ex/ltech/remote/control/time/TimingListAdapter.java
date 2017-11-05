package com.ex.ltech.remote.control.time;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.remote.control.vo.YaokongTimingVo;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.io.PrintStream;
import java.util.List;

public class TimingListAdapter extends BaseAdapter
{
  private long fleshSpaceTime;
  private Handler handler;
  public boolean isLvScroll;
  private List<YaokongTimingVo> itemVos;
  OnListVSwichChangeListener onListVSwichChangeListener;
  private Activity pct;
  public int swichOff = 2;
  public int swichOn = 1;

  public TimingListAdapter(Activity paramActivity, List<YaokongTimingVo> paramList)
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
    YaokongTimingVo localYaokongTimingVo;
    String str1;
    while (true)
    {
      localHolder.tb_act_timing_swich.setListViewItemPosi(paramInt);
      localYaokongTimingVo = (YaokongTimingVo)this.itemVos.get(paramInt);
      localHolder.tv_act_timing_time.setText(localYaokongTimingVo.getTime());
      str1 = "";
      List localList = localYaokongTimingVo.getShotNameDays();
      if ((localList == null) || (localList.size() <= 1))
        break;
      while (paramInt < localList.size())
      {
        str1 = str1 + (String)localList.get(paramInt) + "\t";
        paramInt++;
      }
      localHolder = (Holder)paramView.getTag();
    }
    localHolder.tv_act_timing_repeat.setText(str1);
    label358: String str2;
    int j;
    label500: TextView localTextView;
    if (localYaokongTimingVo.isSwich())
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
      if (!localYaokongTimingVo.isOther())
      {
        if (!localYaokongTimingVo.getLightStatus().equals(this.pct.getString(R.string.on)))
          break label606;
        localHolder.tv_act_timing_swich_status.setText(this.pct.getString(R.string.on));
        localHolder.tv_act_timing_swich_status.setText(localYaokongTimingVo.getYaoKongName());
        if (localYaokongTimingVo.getColor() == 0)
          break label626;
        localHolder.csv_act_timing.setVisibility(View.VISIBLE);
        localHolder.tv_act_timing_mode.setVisibility(View.GONE);
        localHolder.csv_act_timing.setColor(localYaokongTimingVo.getColor());
        this.fleshSpaceTime = System.currentTimeMillis();
        str2 = localYaokongTimingVo.getYkType();
        j = -1;
      }
      switch (str2.hashCode())
      {
      default:
        switch (j)
        {
        default:
          label544: if (!(localYaokongTimingVo.isShowMineTiming() | localYaokongTimingVo.isShowOtherTiming()))
            break label1391;
          localHolder.who_timing.setVisibility(View.VISIBLE);
          localTextView = localHolder.who_timing;
          if (!localYaokongTimingVo.isShowMineTiming());
        case 0:
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        }
      case 96586:
      case 3714:
      case -862082418:
      case -1969960369:
      case 101139:
      case -1934426507:
      case 99858:
      }
    }
    for (int i = 2131100161; ; i = 2131100255)
    {
      while (true)
      {
        localTextView.setText(i);
        return paramView;
        localHolder.tb_act_timing_swich.setToggleOff();
        break;
        label606: localHolder.tv_act_timing_swich_status.setText(this.pct.getString(2131100226));
        break label358;
        label626: localHolder.tv_act_timing_mode.setVisibility(View.VISIBLE);
        localHolder.csv_act_timing.setVisibility(View.GONE);
        if (!localYaokongTimingVo.isOffDevice())
          localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getModeName());
        try
        {
          if (localYaokongTimingVo.getYaoKongName().equals(this.pct.getString(2131099858)))
            localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getAirMode() + "\t" + localYaokongTimingVo.getWendu());
          while (true)
          {
            System.out.println("");
            break;
            if (!localYaokongTimingVo.getYaoKongName().equals(this.pct.getString(2131100457)))
              break label808;
            localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getChanel() + this.pct.getString(2131099929));
          }
        }
        catch (Exception localException)
        {
          while (true)
          {
            localException.printStackTrace();
            continue;
            label808: localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getLightStatus());
          }
        }
      }
      if (!str2.equals("air"))
        break label500;
      j = 0;
      break label500;
      if (!str2.equals("tv"))
        break label500;
      j = 1;
      break label500;
      if (!str2.equals("tv_box"))
        break label500;
      j = 2;
      break label500;
      if (!str2.equals("projection"))
        break label500;
      j = 3;
      break label500;
      if (!str2.equals("fan"))
        break label500;
      j = 4;
      break label500;
      if (!str2.equals("iptv_box"))
        break label500;
      j = 5;
      break label500;
      if (!str2.equals("dvd"))
        break label500;
      j = 6;
      break label500;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getAirMode() + "\t" + localYaokongTimingVo.getWendu());
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getChanel() + "\t" + this.pct.getString(2131099929));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getChanel() + "\t" + this.pct.getString(2131099929));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(this.pct.getString(R.string.on));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(this.pct.getString(R.string.on));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(localYaokongTimingVo.getChanel() + "\t" + this.pct.getString(2131099929));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
      if (localYaokongTimingVo.isOnOff())
      {
        localHolder.tv_act_timing_mode.setText(this.pct.getString(R.string.on));
        break label544;
      }
      localHolder.tv_act_timing_mode.setText(this.pct.getString(2131100226));
      break label544;
    }
    label1391: localHolder.who_timing.setVisibility(View.GONE);
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
 * Qualified Name:     com.ex.ltech.remote.control.time.TimingListAdapter
 * JD-Core Version:    0.6.0
 */