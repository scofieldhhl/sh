package com.ex.ltech.onepiontfive.main.room.sensor;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.time.FtRepeatDay;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.indris.material.RippleView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class FtSensorTiming extends MyBaseFt
  implements View.OnClickListener
{

  @Bind({2131558781})
  RippleView btnTitleViewMenu;

  @Bind({2131559473})
  TextView every_day;
  String hour;

  @Bind({2131559455})
  MyTimePickerView hourPiker;

  @Bind({2131558594})
  ImageView ivActOutletAddTimingGo1;

  @Bind({2131558598})
  ImageView ivActOutletAddTimingGo2;

  @Bind({2131558602})
  ImageView ivActOutletAddTimingGo3;
  String min;

  @Bind({2131558903})
  MyTimePickerView minPiker;

  @Bind({2131559472})
  TextView offTime;

  @Bind({2131559471})
  TextView openTime;

  @Bind({2131558590})
  RelativeLayout rlActOutletAddTiming1;

  @Bind({2131558596})
  RelativeLayout rlActOutletAddTiming2;

  @Bind({2131558600})
  RelativeLayout rlActOutletAddTiming3;
  TextView seletedTv;
  SensorBiz sensorBiz;
  private int[] shotDay = { 2131100240, 2131100240, 2131100467, 2131100430, 2131100071, 2131100066, 2131100404, 2131100394 };

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;
  SensorVo vo;

  private void init()
  {
    if ((this.vo.getStartHour() == null) && (this.vo.getStartMin() == null))
    {
      this.vo.setStartHour("00");
      this.vo.setStartMin("00");
      this.vo.setEndHout("23");
      this.vo.setEndMin("59");
    }
  }

  public List<String> getHourDate()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 24; i++)
      localArrayList.add("" + i);
    return localArrayList;
  }

  public List<String> getMinDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 60)
    {
      if (i < 10);
      for (String str = "0" + i; ; str = "" + i)
      {
        localArrayList.add(str);
        i++;
        break;
      }
    }
    return localArrayList;
  }

  public void onClick(View paramView)
  {
    if (paramView == this.btnTitleViewMenu)
    {
      this.sensorBiz.saveCacheData(this.vo);
      setResult(10000);
      finish();
    }
    if ((paramView == this.rlActOutletAddTiming1) || (paramView == this.ivActOutletAddTimingGo1))
    {
      this.rlActOutletAddTiming1.setBackgroundColor(Color.parseColor("#F8F8FF"));
      this.rlActOutletAddTiming2.setBackgroundColor(-1);
      this.rlActOutletAddTiming3.setBackgroundColor(-1);
      this.seletedTv = this.openTime;
    }
    if ((paramView == this.rlActOutletAddTiming2) || (paramView == this.ivActOutletAddTimingGo2))
    {
      this.rlActOutletAddTiming2.setBackgroundColor(Color.parseColor("#F8F8FF"));
      this.rlActOutletAddTiming1.setBackgroundColor(-1);
      this.rlActOutletAddTiming3.setBackgroundColor(-1);
      this.seletedTv = this.offTime;
    }
    if ((paramView == this.rlActOutletAddTiming3) || (paramView == this.ivActOutletAddTimingGo3))
    {
      this.rlActOutletAddTiming3.setBackgroundColor(Color.parseColor("#F8F8FF"));
      this.rlActOutletAddTiming1.setBackgroundColor(-1);
      this.rlActOutletAddTiming2.setBackgroundColor(-1);
      startFragmentForResult(new Request(FtRepeatDay.class).putExtra("SensorForwardKey", true), 0);
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968884, null);
      ButterKnife.bind(this, this.view);
      this.sensorBiz = new SensorBiz(getActivity());
      this.vo = this.sensorBiz.getCacheSensorVo();
      if (this.vo.getRepeatDayVos() != null)
        showShotDay(this.vo.getRepeatDayVos());
      init();
    }
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 203)
    {
      String str = paramRequest.getStringExtra("TimingRepeatDayResultKey");
      showShotDay((ArrayList)new Gson().fromJson(str, new TypeToken()
      {
      }
      .getType()));
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btnTitleViewMenu.setOnClickListener(this);
    this.rlActOutletAddTiming1.setOnClickListener(this);
    this.rlActOutletAddTiming2.setOnClickListener(this);
    this.rlActOutletAddTiming3.setOnClickListener(this);
    this.ivActOutletAddTimingGo1.setOnClickListener(this);
    this.ivActOutletAddTimingGo2.setOnClickListener(this);
    this.ivActOutletAddTimingGo3.setOnClickListener(this);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100454);
    this.hourPiker.setData(getHourDate());
    this.minPiker.setData(getMinDate());
    this.hourPiker.setTextCol(-16777216);
    this.minPiker.setTextCol(-16777216);
    this.hourPiker.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        FtSensorTiming.this.hour = paramString;
        TextView localTextView;
        if (FtSensorTiming.this.seletedTv != null)
        {
          localTextView = FtSensorTiming.this.seletedTv;
          if (Integer.parseInt(FtSensorTiming.this.hour) >= 10)
            break label139;
        }
        label139: for (String str = "0" + FtSensorTiming.this.hour + ":" + FtSensorTiming.this.min; ; str = FtSensorTiming.this.hour + ":" + FtSensorTiming.this.min)
        {
          localTextView.setText(str);
          if (FtSensorTiming.this.seletedTv != FtSensorTiming.this.openTime)
            break;
          FtSensorTiming.this.vo.setStartHour(FtSensorTiming.this.hour);
          FtSensorTiming.this.vo.setStartMin(FtSensorTiming.this.min);
          return;
        }
        FtSensorTiming.this.vo.setEndHout(FtSensorTiming.this.hour);
        FtSensorTiming.this.vo.setEndMin(FtSensorTiming.this.min);
      }
    });
    this.minPiker.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        FtSensorTiming.this.min = paramString;
        if (FtSensorTiming.this.seletedTv != null)
        {
          FtSensorTiming.this.seletedTv.setText(FtSensorTiming.this.hour + ":" + FtSensorTiming.this.min);
          if (FtSensorTiming.this.seletedTv == FtSensorTiming.this.openTime)
          {
            FtSensorTiming.this.vo.setStartHour(FtSensorTiming.this.hour);
            FtSensorTiming.this.vo.setStartMin(FtSensorTiming.this.min);
          }
        }
        else
        {
          return;
        }
        FtSensorTiming.this.vo.setEndHout(FtSensorTiming.this.hour);
        FtSensorTiming.this.vo.setEndMin(FtSensorTiming.this.min);
      }
    });
    String str4;
    TextView localTextView1;
    if (this.vo.getStartHour() != null)
    {
      TextView localTextView2 = this.openTime;
      if (Integer.parseInt(this.vo.getStartHour()) < 10)
      {
        str4 = "0" + this.vo.getStartHour() + ":" + this.vo.getStartMin();
        localTextView2.setText(str4);
      }
    }
    else if (this.vo.getEndHout() != null)
    {
      localTextView1 = this.offTime;
      if (Integer.parseInt(this.vo.getEndHout()) >= 10)
        break label442;
    }
    label442: for (String str3 = "0" + this.vo.getEndHout() + ":" + this.vo.getEndMin(); ; str3 = this.vo.getEndHout() + ":" + this.vo.getEndMin())
    {
      localTextView1.setText(str3);
      MyTimePickerView localMyTimePickerView1 = this.hourPiker;
      String str1 = new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()));
      this.hour = str1;
      localMyTimePickerView1.setSelected(Integer.parseInt(str1));
      MyTimePickerView localMyTimePickerView2 = this.minPiker;
      String str2 = new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()));
      this.min = str2;
      localMyTimePickerView2.setSelected(Integer.parseInt(str2));
      return;
      str4 = this.vo.getStartHour() + ":" + this.vo.getStartMin();
      break;
    }
  }

  public void showShotDay(ArrayList<RepeatDayVo> paramArrayList)
  {
    this.vo.setRepeatDayVos(paramArrayList);
    String str = "";
    int i = 0;
    if (i < paramArrayList.size())
    {
      if (((RepeatDayVo)paramArrayList.get(i)).isSeleted())
        if (i != 0)
          break label80;
      label80: for (str = str + ((RepeatDayVo)paramArrayList.get(i)).getDay() + "\t\t"; ; str = str + getString(this.shotDay[i]) + "\t\t")
      {
        i++;
        break;
      }
    }
    try
    {
      this.every_day.setText(str.substring(0, -2 + str.length()));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensorTiming
 * JD-Core Version:    0.6.0
 */