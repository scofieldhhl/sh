package com.ex.ltech.outlet.timing.act;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.led.vo.TimingVo;
import com.ex.ltech.outlet.timing.TimingBussines;
import com.ex.ltech.outlet.timing.TimingData;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActAddTiming extends MyBaseActivity
  implements View.OnClickListener
{
  private TimingBussines bussines;
  private ColorSeletedView cv_act_outlet_add_timing_mode;
  String[] days;
  private UserFerences ferences;
  private Gson gson = new Gson();
  GridView gv_act_outlet_add_timing;
  private String hour = "12";
  private int lastActItemPosi;
  private String lightStatus;
  private String min = "30";
  private RelativeLayout rl_act_outlet_add_timing_1;
  private RelativeLayout rl_act_outlet_add_timing_2;
  TextView seletedText;
  MyTimePickerView tp_act_outlet_add_timing_hour;
  MyTimePickerView tp_act_outlet_add_timing_min;
  private TextView tv_act_outlet_add_timing_end_time;
  private TextView tv_act_outlet_add_timing_mode_status;
  private TextView tv_act_outlet_add_timing_start_time;
  private TimingVo vo = null;
  private List<TimingVo> vos;

  private void findView()
  {
    this.tp_act_outlet_add_timing_hour = ((MyTimePickerView)findViewById(2131558587));
    this.tp_act_outlet_add_timing_hour.setmColorText(-16777216);
    this.tp_act_outlet_add_timing_min = ((MyTimePickerView)findViewById(2131558588));
    this.tp_act_outlet_add_timing_min.setmColorText(-16777216);
    this.rl_act_outlet_add_timing_1 = ((RelativeLayout)findViewById(2131558590));
    this.rl_act_outlet_add_timing_2 = ((RelativeLayout)findViewById(2131558596));
    this.cv_act_outlet_add_timing_mode = ((ColorSeletedView)findViewById(2131558592));
    this.tv_act_outlet_add_timing_mode_status = ((TextView)findViewById(2131558595));
    this.tv_act_outlet_add_timing_start_time = ((TextView)findViewById(2131558593));
    this.tv_act_outlet_add_timing_end_time = ((TextView)findViewById(2131558599));
    this.gv_act_outlet_add_timing = ((GridView)findViewById(2131558589));
    this.gv_act_outlet_add_timing.setAdapter(new BaseAdapter()
    {
      public int getCount()
      {
        return 8;
      }

      public Object getItem(int paramInt)
      {
        return null;
      }

      public long getItemId(int paramInt)
      {
        return 0L;
      }

      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        ActAddTiming.this.getLayoutInflater();
        View localView = LayoutInflater.from(ActAddTiming.this).inflate(2130968808, null);
        TextView localTextView = (TextView)localView.findViewById(2131559315);
        if (((RepeatDayVo)ActAddTiming.this.bussines.days.get(paramInt)).isSeleted())
        {
          localTextView.setBackgroundResource(2130903674);
          localTextView.setTextColor(-1);
        }
        while (true)
        {
          localTextView.setText(((RepeatDayVo)ActAddTiming.this.bussines.days.get(paramInt)).getDay());
          if (((RepeatDayVo)ActAddTiming.this.bussines.days.get(paramInt)).isSeleted())
            localTextView.setBackgroundResource(2130903674);
          localTextView.setOnClickListener(new View.OnClickListener(paramInt)
          {
            public void onClick(View paramView)
            {
              ActAddTiming.this.bussines.onRepeatDayItemClick(this.val$i);
              ActAddTiming.1.this.notifyDataSetChanged();
            }
          });
          localTextView.setText(ActAddTiming.this.days[paramInt]);
          return localView;
          localTextView.setBackgroundColor(ActAddTiming.this.getResources().getColor(2131492996));
          localTextView.setTextColor(-16777216);
        }
      }
    });
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      this.lastActItemPosi = localIntent.getIntExtra("itemPosi", -1);
      if (this.lastActItemPosi == -1)
        break label495;
      this.vo = ((TimingVo)TimingData.getInstance(this).getTimingVos4Sd().get(this.lastActItemPosi));
      this.bussines.days.clear();
      this.bussines.days.addAll(this.vo.getLongNameDays());
      this.tv_act_outlet_add_timing_start_time.setText(this.vo.getStartTime());
      this.tv_act_outlet_add_timing_end_time.setText(this.vo.getEndTime());
      new String[0];
      try
      {
        String[] arrayOfString = this.vo.getTime().split(":");
        this.hour = arrayOfString[0];
        this.min = arrayOfString[1];
      }
      catch (Exception localException2)
      {
        try
        {
          while (true)
          {
            this.tp_act_outlet_add_timing_hour.setSelected(Integer.parseInt(this.hour));
            this.tp_act_outlet_add_timing_min.setSelected(Integer.parseInt(this.min));
            List localList = this.vo.getShotNameDays();
            String str5 = "";
            for (int i = 0; i < localList.size(); i++)
              str5 = str5 + (String)localList.get(i) + "\t\t";
            localException2 = localException2;
            localException2.printStackTrace();
          }
        }
        catch (NumberFormatException localNumberFormatException)
        {
          while (true)
            localNumberFormatException.printStackTrace();
          this.tv_act_outlet_add_timing_mode_status.setText(this.vo.getModeName());
          if (this.vo.getType() == 1)
          {
            this.tv_act_outlet_add_timing_mode_status.setVisibility(View.GONE);
            this.cv_act_outlet_add_timing_mode.setVisibility(View.VISIBLE);
            this.cv_act_outlet_add_timing_mode.setColor(this.vo.getColor());
          }
          if (this.vo.getType() == 0)
          {
            this.cv_act_outlet_add_timing_mode.setVisibility(View.GONE);
            this.tv_act_outlet_add_timing_mode_status.setVisibility(View.VISIBLE);
          }
          if (this.vo.isOffDevice())
            break label487;
        }
      }
      open(null);
    }
    while (true)
    {
      this.seletedText = this.tv_act_outlet_add_timing_start_time;
      ((TextView)findViewById(2131558591)).setTextColor(getResources().getColor(2131492964));
      ((TextView)findViewById(2131558597)).setTextColor(getResources().getColor(2131492877));
      try
      {
        TextView localTextView = this.seletedText;
        StringBuilder localStringBuilder = new StringBuilder();
        if (this.hour.substring(0, 1).equals("0"));
        for (String str1 = this.hour.substring(1, 2); ; str1 = this.hour)
        {
          localTextView.setText(str1 + ":" + this.min);
          return;
          label487: close(null);
          break;
          label495: MyTimePickerView localMyTimePickerView1 = this.tp_act_outlet_add_timing_hour;
          String str2 = new SimpleDateFormat("HH").format(Long.valueOf(60000L + System.currentTimeMillis()));
          this.hour = str2;
          localMyTimePickerView1.setSelected(Integer.parseInt(str2));
          if (this.hour.substring(0, 1).equals("0"));
          for (String str3 = this.hour.substring(1, 2); ; str3 = this.hour)
          {
            this.hour = str3;
            MyTimePickerView localMyTimePickerView2 = this.tp_act_outlet_add_timing_min;
            String str4 = new SimpleDateFormat("mm").format(Long.valueOf(60000L + System.currentTimeMillis()));
            this.min = str4;
            localMyTimePickerView2.setSelected(Integer.parseInt(str4));
            this.vo = new TimingVo();
            ArrayList localArrayList = new ArrayList();
            localArrayList.add(getString(R.string.once));
            this.vo.setShotNameDays(localArrayList);
            this.vo.setSwich(true);
            open(null);
            this.bussines.initRepeatDayVos();
            break;
          }
        }
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
      }
    }
  }

  private void init()
  {
    this.bussines = new TimingBussines(this);
    this.tp_act_outlet_add_timing_hour.setData(this.bussines.getHourDate());
    this.tp_act_outlet_add_timing_min.setData(this.bussines.getMinDate());
    String[] arrayOfString = new String[8];
    arrayOfString[0] = getString(2131100055);
    arrayOfString[1] = getString(2131100240);
    arrayOfString[2] = getString(2131100467);
    arrayOfString[3] = getString(2131100430);
    arrayOfString[4] = getString(2131100071);
    arrayOfString[5] = getString(2131100066);
    arrayOfString[6] = getString(2131100404);
    arrayOfString[7] = getString(2131100394);
    this.days = arrayOfString;
  }

  private void setListener()
  {
    this.rl_act_outlet_add_timing_1.setOnClickListener(this);
    this.rl_act_outlet_add_timing_2.setOnClickListener(this);
    this.tp_act_outlet_add_timing_hour.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$102(ActAddTiming.this, paramString);
        if (ActAddTiming.this.seletedText != null)
          ActAddTiming.this.seletedText.setText(ActAddTiming.this.hour + ":" + ActAddTiming.this.min);
      }
    });
    this.tp_act_outlet_add_timing_min.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$202(ActAddTiming.this, paramString);
        if (ActAddTiming.this.seletedText != null)
          ActAddTiming.this.seletedText.setText(ActAddTiming.this.hour + ":" + ActAddTiming.this.min);
      }
    });
  }

  public void clearTime1(View paramView)
  {
    this.tv_act_outlet_add_timing_start_time.setText("");
  }

  public void clearTime2(View paramView)
  {
    this.tv_act_outlet_add_timing_end_time.setText("");
  }

  public void close(View paramView)
  {
    findViewById(2131558590).setVisibility(View.GONE);
    this.vo.setType(2);
    this.lightStatus = getString(R.string.off_device);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      TimingVo localTimingVo2 = TimingData.getInstance(this).getCacheTimingVo4Sd();
      this.vo.setShotNameDays(localTimingVo2.getShotNameDays());
      this.vo.setLongNameDays(localTimingVo2.getLongNameDays());
      List localList = this.vo.getShotNameDays();
      String str2 = "";
      for (int i = 0; i < localList.size(); i++)
        str2 = str2 + (String)localList.get(i) + "\t\t";
    }
    String str1;
    if (paramInt2 == 2000)
    {
      TimingVo localTimingVo1 = TimingData.getInstance(this).getCacheTimingVo4Sd();
      this.vo.setModeName(localTimingVo1.getModeName());
      this.vo.setSeletedModes(localTimingVo1.getSeletedModes());
      this.vo.setColor(localTimingVo1.getColor());
      this.vo.setR(localTimingVo1.getR());
      this.vo.setG(localTimingVo1.getG());
      this.vo.setB(localTimingVo1.getB());
      this.vo.setBrt(localTimingVo1.getBrt());
      this.vo.setW(localTimingVo1.getW());
      this.vo.setType(localTimingVo1.getType());
      str1 = this.vo.getModeName();
      if (this.vo.getColor() == 0)
        break label340;
      this.cv_act_outlet_add_timing_mode.setColor(this.vo.getColor());
    }
    while (true)
    {
      if (this.vo.getType() == 1)
      {
        this.tv_act_outlet_add_timing_mode_status.setVisibility(View.GONE);
        this.cv_act_outlet_add_timing_mode.setVisibility(View.VISIBLE);
      }
      if (this.vo.getType() == 0)
      {
        this.cv_act_outlet_add_timing_mode.setVisibility(View.GONE);
        this.tv_act_outlet_add_timing_mode_status.setVisibility(View.VISIBLE);
      }
      this.tv_act_outlet_add_timing_mode_status.setText(str1);
      return;
      label340: this.cv_act_outlet_add_timing_mode.setColor(-1);
    }
  }

  public void onClick(View paramView)
  {
    if (paramView == this.rl_act_outlet_add_timing_1)
    {
      this.seletedText = this.tv_act_outlet_add_timing_start_time;
      ((TextView)findViewById(2131558591)).setTextColor(getResources().getColor(2131492964));
      ((TextView)findViewById(2131558597)).setTextColor(getResources().getColor(2131492877));
      this.rl_act_outlet_add_timing_1.setBackgroundColor(Color.parseColor("#ffe5e5e5"));
      this.rl_act_outlet_add_timing_2.setBackgroundColor(-1);
    }
    if (paramView == this.rl_act_outlet_add_timing_2)
    {
      this.seletedText = this.tv_act_outlet_add_timing_end_time;
      ((TextView)findViewById(2131558597)).setTextColor(getResources().getColor(2131492964));
      ((TextView)findViewById(2131558591)).setTextColor(getResources().getColor(2131492877));
      this.rl_act_outlet_add_timing_1.setBackgroundColor(-1);
      this.rl_act_outlet_add_timing_2.setBackgroundColor(Color.parseColor("#ffe5e5e5"));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968611);
    findView();
    init();
    setTitleView();
    setListener();
    getMyIntent();
  }

  protected void onEdit()
  {
    boolean bool1 = true;
    super.onEdit();
    this.vo.setSwich(bool1);
    this.vo.setTime(this.tv_act_outlet_add_timing_start_time.getText().toString());
    if (this.vo.getXuHao() == -1)
      this.vo.setXuHao(255);
    this.vo.setLongNameDays(this.bussines.days);
    this.vo.setShotNameDays(this.bussines.getShotNameDays());
    this.vo.setStartTime(this.tv_act_outlet_add_timing_start_time.getText().toString());
    this.vo.setEndTime(this.tv_act_outlet_add_timing_end_time.getText().toString());
    boolean bool2;
    if (this.tv_act_outlet_add_timing_start_time.getText().toString().length() == 0)
    {
      bool2 = bool1;
      if (this.tv_act_outlet_add_timing_end_time.getText().toString().length() != 0)
        break label179;
    }
    label179: for (boolean bool3 = bool1; ; bool3 = false)
    {
      if (!(bool2 & bool3))
        break label184;
      toast(2131099947);
      return;
      bool2 = false;
      break;
    }
    label184: boolean bool4;
    if (this.vo.getStartTime().length() > 0)
    {
      bool4 = bool1;
      if (this.vo.getEndTime().length() <= 0)
        break label288;
      label213: if (!(bool4 & bool1))
        break label293;
      this.vo.setOutletLatelyTime(DateFmtUtil.getTime4HHmm(this.vo.getStartTime()));
    }
    int i;
    while (true)
    {
      i = 0;
      for (int j = 0; j < 8; j++)
      {
        if (!((RepeatDayVo)this.bussines.days.get(j)).isSeleted())
          continue;
        i = 1;
      }
      bool4 = false;
      break;
      label288: bool1 = false;
      break label213;
      label293: if (this.vo.getStartTime().length() > 0)
      {
        this.vo.setOutletLatelyTime(DateFmtUtil.getTime4HHmm(this.vo.getStartTime()));
        continue;
      }
      if (this.vo.getEndTime().length() <= 0)
        continue;
      this.vo.setOutletLatelyTime(DateFmtUtil.getTime4HHmm(this.vo.getEndTime()));
    }
    if (i == 0)
    {
      toast(2131099935);
      return;
    }
    this.vos = TimingData.getInstance(this).getTimingVos4Sd();
    this.vo.setIsOther(false);
    this.bussines.saveCacheVos(this.vo);
    setResult(-1, new Intent());
    finish();
    System.out.println("#$%^&*()    ++++++++++    " + this.vo.getXuHao());
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
  }

  public void open(View paramView)
  {
    findViewById(2131558590).setVisibility(View.VISIBLE);
    this.lightStatus = getString(R.string.on);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(R.mipmap.add_timing);
    setEditTextRes(2131100530, getResources().getColor(2131492964));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.timing.act.ActAddTiming
 * JD-Core Version:    0.6.0
 */