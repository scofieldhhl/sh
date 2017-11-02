package com.ex.ltech.onepiontfive.main.more.SmsLog;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FtSmsLogAdapter extends BaseAdapter
{
  private Activity context;
  private List<String> list = new ArrayList();
  private Map<String, String> resultMap = new HashMap();

  public FtSmsLogAdapter(Activity paramActivity, List<String> paramList)
  {
    this.context = paramActivity;
    this.list = paramList;
  }

  public int getCount()
  {
    return this.list.size();
  }

  public Object getItem(int paramInt)
  {
    return this.list.get(paramInt);
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
      paramView = LayoutInflater.from(this.context).inflate(2130968833, null);
      localHolder.tv_sensor_room = ((TextView)paramView.findViewById(2131559395));
      paramView.setTag(localHolder);
    }
    while (true)
    {
      localHolder.tv_sensor_room.setText((CharSequence)this.list.get(paramInt));
      return paramView;
      localHolder = (Holder)paramView.getTag();
    }
  }

  class Holder
  {
    TextView tv_sensor_room;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.SmsLog.FtSmsLogAdapter
 * JD-Core Version:    0.6.0
 */