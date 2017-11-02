package com.ex.ltech.onepiontfive.main.time;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.timing.RepeatDayAdapter;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.room.sensor.SensorBiz;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class FtRepeatDay extends MyBaseFt
{
  RepeatDayAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  TimingBusiness business;
  boolean isAllSeleted;

  @Bind({2131558610})
  ListView lvActRepeatDay;
  public List<RepeatDayVo> repeatDayVos = new ArrayList();

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtRepeatDay.this.finish();
      }
    });
    this.tvTitleViewTitle.setText(2131100333);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtRepeatDay.this.setResult(203, new Request().putExtra("TimingRepeatDayResultKey", FtRepeatDay.this.business.gs.toJson(FtRepeatDay.this.repeatDayVos)));
        FtRepeatDay.this.finish();
      }
    });
    if (getRequest().getBooleanExtra("SensorForwardKey", false))
    {
      GeoSpaceVo localGeoSpaceVo = (GeoSpaceVo)this.business.getCacheBean(GeoSpaceVo.class);
      if ((localGeoSpaceVo != null) && (localGeoSpaceVo.getRepeatDayVos() != null))
      {
        this.repeatDayVos.clear();
        this.repeatDayVos.addAll(localGeoSpaceVo.getRepeatDayVos());
      }
    }
  }

  public void initRepeatDayVos()
  {
    this.repeatDayVos.clear();
    RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
    localRepeatDayVo1.setDay(getActivity().getString(2131100055));
    this.repeatDayVos.add(localRepeatDayVo1);
    RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
    localRepeatDayVo2.setDay(getActivity().getString(2131100003));
    this.repeatDayVos.add(localRepeatDayVo2);
    RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
    localRepeatDayVo3.setDay(getActivity().getString(2131100004));
    this.repeatDayVos.add(localRepeatDayVo3);
    RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
    localRepeatDayVo4.setDay(getActivity().getString(2131100005));
    this.repeatDayVos.add(localRepeatDayVo4);
    RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
    localRepeatDayVo5.setDay(getActivity().getString(2131100006));
    this.repeatDayVos.add(localRepeatDayVo5);
    RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
    localRepeatDayVo6.setDay(getActivity().getString(2131100007));
    this.repeatDayVos.add(localRepeatDayVo6);
    RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
    localRepeatDayVo7.setDay(getActivity().getString(2131100008));
    this.repeatDayVos.add(localRepeatDayVo7);
    RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
    localRepeatDayVo8.setDay(getActivity().getString(2131100009));
    this.repeatDayVos.add(localRepeatDayVo8);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    Timing localTiming;
    if (this.view == null)
    {
      this.business = new TimingBusiness(getActivity());
      this.view = paramLayoutInflater.inflate(2130968618, null);
      ButterKnife.bind(this, this.view);
      SensorVo localSensorVo = new SensorBiz(getActivity()).getCacheSensorVo();
      localTiming = this.business.getCacheData();
      if (localSensorVo.getRepeatDayVos() == null)
        break label152;
      this.repeatDayVos.addAll(localSensorVo.getRepeatDayVos());
    }
    while (true)
    {
      this.adapter = new RepeatDayAdapter(getActivity(), this.repeatDayVos, true);
      this.lvActRepeatDay.setAdapter(this.adapter);
      this.lvActRepeatDay.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
        {
          boolean bool1 = true;
          if (paramInt == 0)
          {
            RepeatDayVo localRepeatDayVo3 = (RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(0);
            if (!((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(0)).isSeleted());
            for (boolean bool3 = bool1; ; bool3 = false)
            {
              localRepeatDayVo3.setSeleted(bool3);
              for (int m = 1; m < FtRepeatDay.this.repeatDayVos.size(); m++)
                ((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(m)).setSeleted(false);
            }
          }
          ((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(0)).setSeleted(false);
          RepeatDayVo localRepeatDayVo1 = (RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(paramInt);
          if (!((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(paramInt)).isSeleted());
          int i;
          for (boolean bool2 = bool1; ; bool2 = false)
          {
            localRepeatDayVo1.setSeleted(bool2);
            i = 0;
            for (int j = 0; j < FtRepeatDay.this.repeatDayVos.size(); j++)
            {
              if (!((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(j)).isSeleted())
                continue;
              i++;
            }
          }
          if (i == 7)
          {
            RepeatDayVo localRepeatDayVo2 = (RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(0);
            if (!((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(0)).isSeleted());
            while (true)
            {
              localRepeatDayVo2.setSeleted(bool1);
              for (int k = 1; k < FtRepeatDay.this.repeatDayVos.size(); k++)
                ((RepeatDayVo)FtRepeatDay.this.repeatDayVos.get(k)).setSeleted(false);
              bool1 = false;
            }
          }
          FtRepeatDay.this.adapter.notifyDataSetChanged();
        }
      });
      initTitle();
      ButterKnife.bind(this, this.view);
      return this.view;
      label152: if (localTiming.getRepeatDayVos() != null)
      {
        this.repeatDayVos.addAll(localTiming.getRepeatDayVos());
        continue;
      }
      initRepeatDayVos();
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtRepeatDay
 * JD-Core Version:    0.6.0
 */