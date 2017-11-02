package com.ex.ltech.remote.control.yaokong;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class YaoKongAdapter extends BaseAdapter
{
  int[] nameRes = { 2131099858, 2131100457, 2131100459, 2131100276, 2131100058, 2131100109, 2131100046, 2131100285 };
  private Activity pct;
  int[] picRes = { 2130903055, 2130903790, 2130903792, 2130903636, 2130903254, 2130903401, 2130903228, 2130903406 };

  public YaoKongAdapter(Activity paramActivity)
  {
    this.pct = paramActivity;
  }

  public int getCount()
  {
    return 8;
  }

  public Object getItem(int paramInt)
  {
    return Integer.valueOf(0);
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
      paramView = LayoutInflater.from(this.pct).inflate(2130968825, null);
      localHolder.name = ((TextView)paramView.findViewById(2131559008));
      localHolder.ic = ((ImageView)paramView.findViewById(2131558883));
      paramView.setTag(localHolder);
    }
    while (true)
    {
      localHolder.name.setText(this.pct.getString(this.nameRes[paramInt]));
      localHolder.ic.setBackgroundResource(this.picRes[paramInt]);
      return paramView;
      localHolder = (Holder)paramView.getTag();
    }
  }

  class Holder
  {
    ImageView ic;
    TextView name;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.YaoKongAdapter
 * JD-Core Version:    0.6.0
 */