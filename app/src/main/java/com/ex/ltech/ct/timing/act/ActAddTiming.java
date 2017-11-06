package com.ex.ltech.ct.timing.act;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.ct.timing.TimingBussines;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.timing.TimingData;
import com.ex.ltech.led.acti.timing.act.ActRepeatDay;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.vo.TimingVo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActAddTiming extends MyBaseActivity
  implements View.OnClickListener
{
  private TimingBussines bussines;
  private ColorSeletedView cv_act_add_timing_mode;
  private UserFerences ferences;
  private Gson gson = new Gson();
  private String hour = "12";
  public boolean isCreateNewTime;
  private int lastActItemPosi;
  private String lightStatus;
  private String min = "30";
  private RelativeLayout rl_act_add_timing_1;
  private RelativeLayout rl_act_add_timing_2;
  private RelativeLayout rl_act_add_timing_3;
  MyTimePickerView tp_act_add_timing_hour;
  MyTimePickerView tp_act_add_timing_min;
  private TextView tv_act_add_timing_jianbian_status;
  private TextView tv_act_add_timing_mode_status;
  private TextView tv_act_add_timing_repeat_status;
  private TimingVo vo = null;
  private List<TimingVo> vos;

  private void findView()
  {
    this.tp_act_add_timing_hour = ((MyTimePickerView)findViewById(R.id.tp_act_add_timing_hour));
    this.tp_act_add_timing_min = ((MyTimePickerView)findViewById(R.id.tp_act_add_timing_min));
    this.rl_act_add_timing_3 = ((RelativeLayout)findViewById(R.id.rl_act_add_timing_3));
    this.rl_act_add_timing_1 = ((RelativeLayout)findViewById(R.id.rl_act_add_timing_1));
    this.rl_act_add_timing_2 = ((RelativeLayout)findViewById(R.id.rl_act_add_timing_2));
    this.cv_act_add_timing_mode = ((ColorSeletedView)findViewById(R.id.cv_act_add_timing_mode));
    this.tv_act_add_timing_repeat_status = ((TextView)findViewById(R.id.tv_act_add_timing_repeat_status));
    this.tv_act_add_timing_mode_status = ((TextView)findViewById(R.id.tv_act_add_timing_mode_status));
    this.tv_act_add_timing_jianbian_status = ((TextView)findViewById(2131558524));
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      this.lastActItemPosi = localIntent.getIntExtra("itemPosi", -1);
      if (this.lastActItemPosi == -1)
        break label479;
      localIntent.getStringExtra("voData");
      this.vo = ((TimingVo)new Gson().fromJson(localIntent.getStringExtra("voData"), TimingVo.class));
      String[] arrayOfString = this.vo.getTime().split(":");
      this.hour = arrayOfString[0];
      this.min = arrayOfString[1];
      this.tp_act_add_timing_hour.setSelected(Integer.parseInt(this.hour));
      this.tp_act_add_timing_min.setSelected(Integer.parseInt(this.min));
      String str3;
      int i;
      label157: int j;
      if (this.vo.getCtGradualTime() == 0)
      {
        this.tv_act_add_timing_jianbian_status.setText(getString(R.string.has_no));
        List localList = this.vo.getShotNameDays();
        str3 = "";
        i = 0;
        if (i >= localList.size())
          break label365;
        str3 = str3 + (String)localList.get(i);
        if (localList.size() <= 1)
          break label353;
        j = 1;
        label215: if (i >= -1 + localList.size())
          break label359;
      }
      label353: label359: for (int k = 1; ; k = 0)
      {
        if ((j & k) != 0)
          str3 = str3 + "\t\t";
        i++;
        break label157;
        if (this.vo.getCtGradualTime() == 61)
        {
          this.tv_act_add_timing_jianbian_status.setText(30 + getString(R.string.sec));
          break;
        }
        this.tv_act_add_timing_jianbian_status.setText(this.vo.getCtGradualTime() + getString(R.string.min));
        break;
        j = 0;
        break label215;
      }
      label365: this.tv_act_add_timing_mode_status.setText(this.vo.getModeName());
      this.tv_act_add_timing_repeat_status.setText(str3);
      if (this.vo.getType() == 1)
      {
        this.tv_act_add_timing_mode_status.setVisibility(View.GONE);
        this.cv_act_add_timing_mode.setVisibility(View.VISIBLE);
        this.cv_act_add_timing_mode.setColor(this.vo.getColor());
      }
      if (this.vo.getType() == 0)
      {
        this.cv_act_add_timing_mode.setVisibility(View.GONE);
        this.tv_act_add_timing_mode_status.setVisibility(View.VISIBLE);
      }
      if (!this.vo.isOffDevice())
        open(null);
    }
    else
    {
      return;
    }
    close(null);
    return;
    label479: this.tp_act_add_timing_hour.setSelected(Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(60000L + System.currentTimeMillis()))));
    this.tp_act_add_timing_min.setSelected(Integer.parseInt(new SimpleDateFormat("mm").format(Long.valueOf(60000L + System.currentTimeMillis()))));
    this.vo = new TimingVo();
    TimingVo localTimingVo = this.vo;
    StringBuilder localStringBuilder1 = new StringBuilder();
    String str1 = new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()));
    this.hour = str1;
    StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(":");
    String str2 = new SimpleDateFormat("mm").format(Long.valueOf(60000L + System.currentTimeMillis()));
    this.min = str2;
    localTimingVo.setTime(str2);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getString(R.string.once));
    this.vo.setShotNameDays(localArrayList);
    this.vo.setSwich(true);
    open(null);
  }

  private void init()
  {
    this.bussines = new TimingBussines(this);
    this.tp_act_add_timing_hour.setData(this.bussines.getHourDate());
    this.tp_act_add_timing_min.setData(this.bussines.getMinDate());
  }

  private void setListener()
  {
    this.rl_act_add_timing_1.setOnClickListener(this);
    this.rl_act_add_timing_2.setOnClickListener(this);
    this.rl_act_add_timing_3.setOnClickListener(this);
    this.tp_act_add_timing_hour.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$002(ActAddTiming.this, paramString);
      }
    });
    this.tp_act_add_timing_min.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$102(ActAddTiming.this, paramString);
      }
    });
  }

  public void close(View paramView)
  {
    findViewById(R.id.tv_act_add_timing_on).setBackgroundResource(R.mipmap.ic_on);
    findViewById(R.id.tv_act_add_timing_off).setBackgroundResource(R.mipmap.ic_off_press);
    findViewById(R.id.rl_act_add_timing_1).setVisibility(View.GONE);
    this.vo.setType(2);
    this.lightStatus = getString(R.string.off_device);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    /*String str3;
    String str2;
    int i;
    label110: int j;
    if (paramInt2 == 3000)
    {
      str3 = paramIntent.getStringExtra("jianbianSec");
      this.tv_act_add_timing_jianbian_status.setText(str3);
      if (str3.equals(getString(R.string.has_no)))
        this.vo.setCtGradualTime(0);
    }
    else
    {
      if (paramInt2 != 1000)
        break label328;
      TimingVo localTimingVo2 = TimingData.getInstance(this).getCacheTimingVo4Sd();
      this.vo.setShotNameDays(localTimingVo2.getShotNameDays());
      this.vo.setLongNameDays(localTimingVo2.getLongNameDays());
      List localList = this.vo.getShotNameDays();
      str2 = "";
      i = 0;
      if (i >= localList.size())
        break label311;
      str2 = str2 + (String)localList.get(i);
      if (localList.size() <= 1)
        break label299;
      j = 1;
      label168: if (i >= -1 + localList.size())
        break label305;
    }
    label299: label305: for (int k = 1; ; k = 0)
    {
      if ((j & k) != 0)
        str2 = str2 + "\t\t";
      i++;
      break label110;
      if (str3.equals(30 + getString(R.string.sec)))
      {
        this.vo.setCtGradualTime(61);
        break;
      }
      String str4 = str3.substring(0, str3.indexOf(getString(R.string.min)));
      this.vo.setCtGradualTime(Integer.parseInt(str4));
      break;
      j = 0;
      break label168;
    }
    label311: label328: String str1;
    if (str2.length() > 0)
    {
      this.tv_act_add_timing_repeat_status.setText(str2);
      if (paramInt2 == 2000)
      {
        TimingVo localTimingVo1 = TimingData.getInstance(this).getCacheTimingVo4Sd();
        this.vo.setModeName(localTimingVo1.getModeName());
        this.vo.setSeletedModes(localTimingVo1.getSeletedModes());
        this.vo.setSeletedCtScenes(localTimingVo1.getSeletedCtScenes());
        this.vo.setColor(localTimingVo1.getColor());
        this.vo.setR(localTimingVo1.getR());
        this.vo.setG(localTimingVo1.getG());
        this.vo.setB(localTimingVo1.getB());
        this.vo.setBrt(localTimingVo1.getBrt());
        this.vo.setW(localTimingVo1.getW());
        this.vo.setC(localTimingVo1.getC());
        this.vo.setType(localTimingVo1.getType());
        str1 = this.vo.getModeName();
        if (this.vo.getColor() == 0)
          break label591;
        this.cv_act_add_timing_mode.setColor(this.vo.getColor());
      }
    }
    while (true)
    {
      if (this.vo.getType() == 1)
      {
        this.tv_act_add_timing_mode_status.setVisibility(View.GONE);
        this.cv_act_add_timing_mode.setVisibility(View.VISIBLE);
      }
      if (this.vo.getType() == 0)
      {
        this.cv_act_add_timing_mode.setVisibility(View.GONE);
        this.tv_act_add_timing_mode_status.setVisibility(View.VISIBLE);
      }
      this.tv_act_add_timing_mode_status.setText(str1);
      return;
      this.tv_act_add_timing_repeat_status.setText(getString(R.string.once));
      break;
      label591: this.cv_act_add_timing_mode.setColor(-1);
    }*/
  }

  public void onClick(View paramView)
  {
    if (paramView == this.rl_act_add_timing_1)
    {
      Intent localIntent1 = new Intent(this, ActMode.class);
      localIntent1.putExtra("timingVo", this.gson.toJson(this.vo));
      startActivityForResult(localIntent1, 0);
    }
    if (paramView == this.rl_act_add_timing_2)
    {
      Intent localIntent2 = new Intent(this, ActRepeatDay.class);
      localIntent2.putExtra("timingVo", this.gson.toJson(this.vo));
      startActivityForResult(localIntent2, 0);
    }
    if (paramView == this.rl_act_add_timing_3)
      startActivityForResult(new Intent(this, AtCtTimeJianbianActivity.class), 0);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968603);
    findView();
    init();
    setTitleView();
    setListener();
    getMyIntent();
  }

  protected void onEdit()
  {
    super.onEdit();
    if ((this.vo.getSeletedCtScenes() != null) || (this.vo.getColor() != 0) || (this.lightStatus.equals(getString(R.string.off_device))))
    {
      this.vo.setSwich(true);
      this.vo.setLightStatus(this.lightStatus);
      this.vo.setTime(this.hour + ":" + this.min);
      if (this.vo.getXuHao() == -1)
        this.vo.setXuHao(this.bussines.getTimgItemXuHao());
      if (this.vo.getType() != 1)
        this.vo.setColor(0);
      if (this.lightStatus.equals(getString(R.string.off_device)))
      {
        this.vo.setIsOffDevice(true);
        if (this.tv_act_add_timing_repeat_status.getText().toString().indexOf(getString(R.string.once)) == -1)
          break label364;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(getString(R.string.once));
        this.vo.setShotNameDays(localArrayList);
        this.vo.setIsJustOnce(true);
        if (DateFmtUtil.getTime4HHmm(this.vo.getTime()) <= System.currentTimeMillis())
          break label340;
        this.vo.setJustOnceCurrentTime(DateFmtUtil.getTime4HHmm(this.vo.getTime()));
        label261: this.vo.setIsOther(false);
        this.bussines.saveCacheVos(this.vo);
        setResult(-1, new Intent());
        finish();
      }
    }
    while (true)
    {
      System.out.println("#$%^&*()    ++++++++++    " + this.vo.getXuHao());
      return;
      this.vo.setIsOffDevice(false);
      break;
      label340: this.vo.setJustOnceCurrentTime(17280000L + DateFmtUtil.getTime4HHmm(this.vo.getTime()));
      break label261;
      label364: this.vo.setIsJustOnce(false);
      break label261;
      toast(R.string.config_failed_timing);
    }
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
    findViewById(R.id.tv_act_add_timing_on).setBackgroundResource(R.mipmap.ic_on_press);
    findViewById(R.id.tv_act_add_timing_off).setBackgroundResource(R.mipmap.ic_off);
    findViewById(R.id.rl_act_add_timing_1).setVisibility(View.VISIBLE);
    this.lightStatus = getString(R.string.on);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.mipmap.add_timing);
    setEditTextRes(R.string.finish, Color.parseColor("#fe5f33"));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.ct.timing.act.ActAddTiming
 * JD-Core Version:    0.6.0
 */