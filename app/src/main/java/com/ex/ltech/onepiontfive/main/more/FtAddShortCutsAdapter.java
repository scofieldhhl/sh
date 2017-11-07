package com.ex.ltech.onepiontfive.main.more;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.onepiontfive.main.Constant;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import java.util.List;

public class FtAddShortCutsAdapter extends BaseAdapter
{
  private Context context;
  private List<Dvc> devices;

  public FtAddShortCutsAdapter(Activity paramActivity, List<Dvc> paramList)
  {
    this.context = paramActivity;
    this.devices = paramList;
  }

  public int getCount()
  {
    return this.devices.size();
  }

  public Object getItem(int paramInt)
  {
    return this.devices.get(paramInt);
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
      paramView = LayoutInflater.from(this.context).inflate(2130968763, null);
      localHolder.devicesName = ((TextView)paramView.findViewById(R.id.tv_device_name));
      localHolder.deviceIcon = ((ImageView)paramView.findViewById(2131559205));
      paramView.setTag(localHolder);
      localHolder.devicesName.setTag(((Dvc)this.devices.get(paramInt)).getId());
      localHolder.devicesName.setText(((Dvc)this.devices.get(paramInt)).getName());
      i = ((Dvc)this.devices.get(paramInt)).getType();
      if (i != 8)
        break label159;
      localHolder.deviceIcon.setBackgroundResource(2130903699);
    }
    label159: 
    do
    {
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      if (i == 9)
      {
        localHolder.deviceIcon.setBackgroundResource(2130903804);
        return paramView;
      }
      if (i != 10)
        continue;
      localHolder.deviceIcon.setBackgroundResource(2130903756);
      return paramView;
    }
    while (i != Constant.ADD);
    localHolder.deviceIcon.setBackgroundResource(2130903195);
    return paramView;
  }

  class Holder
  {
    ImageView deviceIcon;
    TextView devicesName;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.FtAddShortCutsAdapter
 * JD-Core Version:    0.6.0
 */