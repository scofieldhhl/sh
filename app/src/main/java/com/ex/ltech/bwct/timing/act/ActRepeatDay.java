package com.ex.ltech.bwct.timing.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.timing.RepeatDayAdapter;
import com.ex.ltech.led.acti.timing.TimingBussines;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.vo.TimingVo;
import com.google.gson.Gson;

public class ActRepeatDay extends MyBaseActivity
{
  private RepeatDayAdapter adt;
  private TimingBussines bussines;
  Gson gs = new Gson();
  private ListView lv;
  private TimingVo vo = null;

  private void findView()
  {
    this.lv = ((ListView)findViewById(R.id.lv_act_repeat_day));
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
      this.vo = ((TimingVo)this.gs.fromJson(localIntent.getStringExtra("timingVo"), TimingVo.class));
  }

  private void init()
  {
    this.bussines = new TimingBussines(this);
    this.adt = new RepeatDayAdapter(this, this.bussines.getInitRepeatDayVos());
    this.lv.setAdapter(this.adt);
  }

  private void setListener()
  {
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        ActRepeatDay.this.bussines.onRepeatDayItemClick(paramInt);
        ActRepeatDay.this.adt.notifyDataSetChanged();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_repeat_day);
    findView();
    setTitleView();
    getMyIntent();
    init();
    setListener();
  }

  protected void onMenu()
  {
    super.onMenu();
    this.vo.setLongNameDays(this.bussines.longNameDayVos);
    this.vo.setShotNameDays(this.bussines.getShotNameDays());
    TimingData.getInstance(this).saveCacheVos(this.vo);
    setResult(1000);
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.repeat);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.bwct.timing.act.ActRepeatDay
 * JD-Core Version:    0.6.0
 */