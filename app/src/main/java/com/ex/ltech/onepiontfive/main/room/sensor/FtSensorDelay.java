package com.ex.ltech.onepiontfive.main.room.sensor;

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
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.vo.SensorDelay;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class FtSensorDelay extends MyBaseFt
{
  DelayAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  String delay;
  public List<SensorDelay> delayDayVos = new ArrayList();

  @Bind({2131559265})
  ListView delayList;
  SensorBiz sensorBiz;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;
  SensorVo vo;

  public void initDelayDayVos()
  {
    this.delayDayVos.clear();
    SensorDelay localSensorDelay1 = new SensorDelay();
    localSensorDelay1.setName(getActivity().getString(2131100017));
    this.delayDayVos.add(localSensorDelay1);
    SensorDelay localSensorDelay2 = new SensorDelay();
    localSensorDelay2.setName(getActivity().getString(2131100018));
    this.delayDayVos.add(localSensorDelay2);
    SensorDelay localSensorDelay3 = new SensorDelay();
    localSensorDelay3.setName(getActivity().getString(2131100019));
    this.delayDayVos.add(localSensorDelay3);
    SensorDelay localSensorDelay4 = new SensorDelay();
    localSensorDelay4.setName(getActivity().getString(2131100020));
    this.delayDayVos.add(localSensorDelay4);
    SensorDelay localSensorDelay5 = new SensorDelay();
    localSensorDelay5.setName(getActivity().getString(2131100021));
    this.delayDayVos.add(localSensorDelay5);
    SensorDelay localSensorDelay6 = new SensorDelay();
    localSensorDelay6.setName(getActivity().getString(2131100022));
    this.delayDayVos.add(localSensorDelay6);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968779, null);
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099957);
    this.sensorBiz = new SensorBiz(getActivity());
    this.vo = this.sensorBiz.getCacheSensorVo();
    if (this.vo == null)
      this.vo = new SensorVo();
    initDelayDayVos();
    setSeletedVos(this.vo.getDelayType());
    this.adapter = new DelayAdapter(getActivity(), this.delayDayVos);
    this.delayList.setAdapter(this.adapter);
    this.delayList.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        for (int i = 0; i < FtSensorDelay.this.delayDayVos.size(); i++)
          ((SensorDelay)FtSensorDelay.this.delayDayVos.get(i)).setSeleted(false);
        ((SensorDelay)FtSensorDelay.this.delayDayVos.get(paramInt)).setSeleted(true);
        FtSensorDelay.this.delay = ((SensorDelay)FtSensorDelay.this.delayDayVos.get(paramInt)).getName();
        FtSensorDelay.this.vo.setDelayType(paramInt + 1);
        if (paramInt == 5)
          FtSensorDelay.this.vo.setDelayType(0);
        FtSensorDelay.this.adapter.notifyDataSetChanged();
      }
    });
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtSensorDelay.this.vo.setDelay(FtSensorDelay.this.delay);
        FtSensorDelay.this.sensorBiz.saveCacheData(FtSensorDelay.this.vo);
        FtSensorDelay.this.setResult(10000);
        FtSensorDelay.this.finish();
      }
    });
  }

  public void setSeletedVos(int paramInt)
  {
    if (paramInt < this.delayDayVos.size())
    {
      if (paramInt != 0)
        ((SensorDelay)this.delayDayVos.get(paramInt - 1)).setSeleted(true);
    }
    else
      return;
    ((SensorDelay)this.delayDayVos.get(5)).setSeleted(true);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensorDelay
 * JD-Core Version:    0.6.0
 */