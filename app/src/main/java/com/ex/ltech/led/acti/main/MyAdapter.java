package com.ex.ltech.led.acti.main;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.vo.DeviceVo;
import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends BaseAdapter
{
  private List<Bitmap> bms;
  private int bmsIndex = 0;
  private List<Integer> deviceIndeList;
  private List<DeviceVo> itemVos;
  private List<Integer> ledPosiList = new ArrayList();
  private Activity pct;
  private List<Integer> plugPosiList = new ArrayList();

  public MyAdapter(Activity paramActivity, List<DeviceVo> paramList)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.deviceIndeList = new ArrayList();
    int i = 0;
    int j = 0;
    int k = 0;
    if (k < this.itemVos.size())
    {
      switch (Integer.parseInt(((DeviceVo)this.itemVos.get(k)).getType(), 16))
      {
      default:
      case 0:
      case 1:
      }
      while (true)
      {
        k++;
        break;
        i++;
        this.deviceIndeList.add(Integer.valueOf(i));
        continue;
        j++;
        this.deviceIndeList.add(Integer.valueOf(j));
      }
    }
  }

  public MyAdapter(Activity paramActivity, List<DeviceVo> paramList, List<Bitmap> paramList1)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.bms = paramList1;
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
    DeviceVo localDeviceVo;
    if (paramView == null)
    {
      localHolder = new Holder();
      this.pct.getLayoutInflater();
      paramView = LayoutInflater.from(this.pct).inflate(2130968828, null);
      localHolder.iv_device = ((ImageView)paramView.findViewById(2131558808));
      localHolder.tv_status = ((TextView)paramView.findViewById(2131559382));
      localHolder.iv_mode = ((ImageView)paramView.findViewById(2131559233));
      localHolder.tv_device_name = ((TextView)paramView.findViewById(2131559206));
      paramView.setTag(localHolder);
      localDeviceVo = (DeviceVo)this.itemVos.get(paramInt);
      String str1 = localDeviceVo.getType();
      String str2 = localDeviceVo.getStatus();
      switch (Integer.parseInt(str1, 16))
      {
      default:
        label156: switch (Integer.parseInt(str2, 16))
        {
        case 210:
        case 211:
        default:
        case 209:
        case 212:
        }
      case 0:
      case 1:
      }
    }
    while (true)
    {
      localHolder.tv_device_name.setText(localDeviceVo.getDeviceName());
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      localHolder.iv_device.setBackgroundResource(R.mipmap.device_light);
      break label156;
      localHolder.iv_device.setBackgroundResource(R.mipmap.device_plug);
      break label156;
      localHolder.tv_status.setText(this.pct.getString(2131099970));
      paramView.setBackgroundColor(this.pct.getResources().getColor(2131492997));
      continue;
      localHolder.tv_status.setText(this.pct.getString(2131100038));
      paramView.setBackgroundColor(this.pct.getResources().getColor(2131492954));
    }
  }

  class Holder
  {
    ImageView iv_device;
    ImageView iv_mode;
    TextView tv_device_name;
    TextView tv_status;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.main.MyAdapter
 * JD-Core Version:    0.6.0
 */