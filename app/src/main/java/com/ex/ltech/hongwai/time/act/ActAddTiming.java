package com.ex.ltech.hongwai.time.act;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ex.ltech.hongwai.time.TimingBussines;
import com.ex.ltech.hongwai.time.TimingData;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.CtTimingAction;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.hongwai.vo.TimingVo;
import com.ex.ltech.hongwai.yaokong.AtSaveYongkongList;
import com.ex.ltech.hongwai.yaokong.AtYkGradientTime;
import com.ex.ltech.hongwai.yaokong.AtYkLightMode;
import com.ex.ltech.hongwai.yaokong.AtYkSwitchSelectActivity;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.my_view.MyAlertDialog10;
import com.ex.ltech.led.my_view.MyAlertDialog10.MyOnClickListener;
import com.ex.ltech.led.my_view.MyAlertDialog17;
import com.ex.ltech.led.my_view.MyAlertDialog17.OnListener;
import com.ex.ltech.led.my_view.MyAlertDialog6;
import com.ex.ltech.led.my_view.MyAlertDialog6.MyOnClickListener;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ActAddTiming extends MyBaseActivity
{
  private TimingBussines bussines;
  private String chanel = "1";
  private ColorSeletedView cv_act_add_timing_mode;
  private Gson gson = new Gson();
  private String hour = "12";
  private boolean isPm = false;
  private int lastActItemPosi;
  private String lightStatus;
  private String min = "30";
  int modeReqCode = 2000;
  int repeatDayReqCode = 4000;
  private RelativeLayout rl_act_add_timing_action;
  private RelativeLayout rl_at_rc_add_timing_chanel;
  private RelativeLayout rl_gradient_time;
  MyTimePickerView tp_act_add_timing_chanel;
  MyTimePickerView tp_act_add_timing_hour;
  MyTimePickerView tp_act_add_timing_min;
  MyTimePickerView tp_act_add_timing_time_range;
  MyTimePickerView tp_act_add_timing_wendu;
  private TextView tv_act_add_timing_action;
  private TextView tv_act_add_timing_repeat;
  private TextView tv_act_add_timing_yaokong;
  private TextView tv_gradient_time;
  private TimingVo vo = null;
  private List<TimingVo> vos;
  private String wendu = "25";
  int yaokongReqCode = 1000;

  private void findView()
  {
    this.tp_act_add_timing_time_range = ((MyTimePickerView)findViewById(2131558536));
    this.tp_act_add_timing_hour = ((MyTimePickerView)findViewById(R.id.tp_act_add_timing_hour));
    this.tp_act_add_timing_min = ((MyTimePickerView)findViewById(R.id.tp_act_add_timing_min));
    this.tp_act_add_timing_chanel = ((MyTimePickerView)findViewById(2131558554));
    this.tp_act_add_timing_wendu = ((MyTimePickerView)findViewById(2131558552));
    this.rl_act_add_timing_action = ((RelativeLayout)findViewById(2131558541));
    this.rl_at_rc_add_timing_chanel = ((RelativeLayout)findViewById(2131558553));
    this.rl_gradient_time = ((RelativeLayout)findViewById(2131558548));
    this.tv_act_add_timing_yaokong = ((TextView)findViewById(2131558539));
    this.tv_act_add_timing_action = ((TextView)findViewById(2131558544));
    this.tv_act_add_timing_repeat = ((TextView)findViewById(2131558518));
    this.cv_act_add_timing_mode = ((ColorSeletedView)findViewById(R.id.cv_act_add_timing_mode));
    this.tv_gradient_time = ((TextView)findViewById(2131558550));
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      this.lastActItemPosi = localIntent.getIntExtra("itemPosi", -1);
      if (this.lastActItemPosi == -1)
        break label529;
      this.vo = ((TimingVo)new Gson().fromJson(localIntent.getStringExtra("voData"), TimingVo.class));
      String[] arrayOfString = this.vo.getTime().split(":");
      this.hour = arrayOfString[0];
      this.min = arrayOfString[1];
      int k = Integer.parseInt(this.hour);
      boolean bool3;
      label156: MyTimePickerView localMyTimePickerView3;
      if (this.vo.getGradientMins() > 0)
      {
        this.tv_gradient_time.setText(this.vo.getGradientMins() + getString(R.string.min));
        this.rl_gradient_time.setVisibility(View.VISIBLE);
        if (k < 12)
          break label322;
        bool3 = true;
        this.isPm = bool3;
        localMyTimePickerView3 = this.tp_act_add_timing_time_range;
        if (!this.isPm)
          break label328;
      }
      String str3;
      label322: label328: for (int m = 1; ; m = 0)
      {
        localMyTimePickerView3.setSelected(m);
        MyTimePickerView localMyTimePickerView4 = this.tp_act_add_timing_hour;
        if (this.isPm)
          k -= 12;
        localMyTimePickerView4.setSelected(k);
        this.tp_act_add_timing_min.setSelected(Integer.parseInt(this.min));
        this.tp_act_add_timing_chanel.setSelected(1);
        this.tp_act_add_timing_wendu.setSelected(25);
        List localList = this.vo.getShotNameDays();
        str3 = "";
        for (int n = 0; n < localList.size(); n++)
          str3 = str3 + "\t\t" + (String)localList.get(n);
        this.rl_gradient_time.setVisibility(View.GONE);
        break;
        bool3 = false;
        break label156;
      }
      this.tv_act_add_timing_repeat.setText(str3);
      if (this.vo.getRcVo().nonIrDevice == null)
        break label453;
      this.tv_act_add_timing_yaokong.setText(this.vo.getYaoKongName());
      this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
      this.tv_act_add_timing_action.setText(this.vo.getStatus());
      this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
      if (this.vo.getRcVo() == null)
        break label498;
      this.tv_act_add_timing_yaokong.setText(this.vo.getRcVo().getName());
      this.tv_act_add_timing_action.setText(this.vo.getRcVo().getStatus());
    }
    while (true)
    {
      this.vo.setOnOff(true);
      return;
      label453: this.tv_act_add_timing_yaokong.setText(this.vo.getRcVo().getName());
      this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
      this.tv_act_add_timing_action.setText(this.vo.getRcVo().getStatus());
      break;
      label498: this.tv_act_add_timing_yaokong.setText(this.vo.getYaoKongName());
      this.tv_act_add_timing_action.setText(this.vo.getStatus());
    }
    label529: this.tp_act_add_timing_chanel.setSelected(1);
    this.tp_act_add_timing_wendu.setSelected(9);
    int i = Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis())));
    if (i >= 12);
    for (boolean bool1 = true; ; bool1 = false)
    {
      this.isPm = bool1;
      MyTimePickerView localMyTimePickerView1 = this.tp_act_add_timing_time_range;
      boolean bool2 = this.isPm;
      int j = 0;
      if (bool2)
        j = 1;
      localMyTimePickerView1.setSelected(j);
      MyTimePickerView localMyTimePickerView2 = this.tp_act_add_timing_hour;
      if (this.isPm)
        i -= 12;
      localMyTimePickerView2.setSelected(i);
      this.tp_act_add_timing_min.setSelected(Integer.parseInt(new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()))));
      this.vo = new TimingVo();
      TimingVo localTimingVo = this.vo;
      StringBuilder localStringBuilder1 = new StringBuilder();
      String str1 = new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()));
      this.hour = str1;
      StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(":");
      String str2 = new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()));
      this.min = str2;
      localTimingVo.setTime(str2);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(getString(R.string.once));
      this.vo.setShotNameDays(localArrayList);
      this.vo.setSwich(true);
      break;
    }
  }

  private void init()
  {
    this.bussines = new TimingBussines(this);
    this.bussines.prepareLink();
    this.tp_act_add_timing_time_range.setData(this.bussines.getTimeRange(this));
    this.tp_act_add_timing_time_range.setmColorText(getResources().getColor(2131492890));
    this.tp_act_add_timing_hour.setData(this.bussines.getHourDate());
    this.tp_act_add_timing_min.setData(this.bussines.getMinDate());
    this.tp_act_add_timing_hour.setTextCol(getResources().getColor(2131492890));
    this.tp_act_add_timing_min.setTextCol(getResources().getColor(2131492890));
    this.tp_act_add_timing_chanel.setData(this.bussines.getChanelDate());
    this.tp_act_add_timing_chanel.setmColorText(getResources().getColor(2131492890));
    this.tp_act_add_timing_wendu.setData(this.bussines.getWenduDate());
  }

  private void setListener()
  {
    this.tp_act_add_timing_time_range.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$502(ActAddTiming.this, ActAddTiming.this.getResources().getString(2131100274).equals(paramString));
        int i = Integer.parseInt(ActAddTiming.this.hour);
        if ((ActAddTiming.this.isPm) && (i < 12))
          ActAddTiming.access$602(ActAddTiming.this, String.valueOf(12 + Integer.parseInt(ActAddTiming.this.hour)));
        if ((!ActAddTiming.this.isPm) && (i >= 12))
          ActAddTiming.access$602(ActAddTiming.this, String.valueOf(-12 + Integer.parseInt(ActAddTiming.this.hour)));
      }
    });
    this.tp_act_add_timing_hour.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming localActAddTiming = ActAddTiming.this;
        if (ActAddTiming.this.isPm)
          paramString = String.valueOf(12 + Integer.parseInt(paramString));
        ActAddTiming.access$602(localActAddTiming, paramString);
      }
    });
    this.tp_act_add_timing_min.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$702(ActAddTiming.this, paramString);
      }
    });
    this.tp_act_add_timing_chanel.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$802(ActAddTiming.this, paramString);
        ActAddTiming.this.vo.setChanel(ActAddTiming.this.chanel);
      }
    });
    this.tp_act_add_timing_wendu.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$902(ActAddTiming.this, paramString);
      }
    });
  }

  private void showHideEffectLayout()
  {
    if (this.tv_act_add_timing_action.getText().toString().equalsIgnoreCase(getString(2131100054)))
    {
      this.rl_gradient_time.setVisibility(View.VISIBLE);
      return;
    }
    this.rl_gradient_time.setVisibility(View.GONE);
  }

  public void goAction(View paramView)
  {
    int i = 1;
    if ((this.vo.getRcVo().getmType() == 10) || (this.vo.getRcVo().getmType() == 11))
    {
      Intent localIntent = new Intent(this, AtYkSwitchSelectActivity.class);
      localIntent.putExtra("RC_TYPE_KEY", this.vo.getRcVo().getmType());
      localIntent.putExtra("OP_RC_POSI_KEY", this.vo.getRcVo().getmSaveRcListPosi());
      startActivityForResult(localIntent, 0);
    }
    do
    {
      return;
      if (this.vo.getRcVo().getmType() != 12)
        continue;
      MyAlertDialog17 localMyAlertDialog17 = new MyAlertDialog17(this);
      localMyAlertDialog17.setOnListener(new MyAlertDialog17.OnListener()
      {
        public void cancel()
        {
        }

        public void effect()
        {
          ActAddTiming.this.goAct4result(AtYkLightMode.class, 0);
        }

        public void off()
        {
          ActAddTiming.this.vo.getRcVo().nonIrDevice.irCt1Onoff = false;
          ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(2131100226));
          ActAddTiming.this.vo.setStatus(ActAddTiming.this.getString(2131100226));
          ActAddTiming.this.showHideEffectLayout();
        }

        public void on()
        {
          ActAddTiming.this.vo.getRcVo().nonIrDevice.irCt1Onoff = true;
          ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(R.string.on));
          ActAddTiming.this.vo.setStatus(ActAddTiming.this.getString(R.string.on));
          ActAddTiming.this.showHideEffectLayout();
        }
      });
      localMyAlertDialog17.show();
      return;
    }
    while ((this.vo.getRcVo().getmType() == 5) || (this.vo.getRcVo().getmType() == 8));
    int j;
    int k;
    label194: int m;
    if (this.vo.getRcVo().getmType() == i)
    {
      j = i;
      if (this.vo.getRcVo().getmType() != 2)
        break label267;
      k = i;
      m = j | k;
      if (this.vo.getRcVo().getmType() != 3)
        break label273;
    }
    while (true)
    {
      if ((m | i) == 0)
        break label278;
      MyAlertDialog6 localMyAlertDialog6 = new MyAlertDialog6(this);
      localMyAlertDialog6.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener()
      {
        public void onChanel()
        {
          ActAddTiming.this.rl_at_rc_add_timing_chanel.setVisibility(View.VISIBLE);
        }

        public void onOnOff()
        {
          MyAlertDialog10 localMyAlertDialog10 = new MyAlertDialog10(ActAddTiming.this);
          localMyAlertDialog10.setMyOnClickListener(new MyAlertDialog10.MyOnClickListener()
          {
            public void off()
            {
              ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(2131100226));
              ActAddTiming.this.vo.getRcVo().setStatus(ActAddTiming.this.getString(2131100226));
            }

            public void on()
            {
              ActAddTiming.this.vo.getRcVo().setStatus(ActAddTiming.this.getString(R.string.on));
              ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(R.string.on));
            }
          });
          localMyAlertDialog10.show();
          localMyAlertDialog10.getWindow().setGravity(80);
        }
      });
      localMyAlertDialog6.show();
      localMyAlertDialog6.getWindow().setGravity(80);
      return;
      j = 0;
      break;
      label267: k = 0;
      break label194;
      label273: i = 0;
    }
    label278: MyAlertDialog10 localMyAlertDialog10 = new MyAlertDialog10(this);
    localMyAlertDialog10.setMyOnClickListener(new MyAlertDialog10.MyOnClickListener()
    {
      public void off()
      {
        ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(2131100226));
        ActAddTiming.this.vo.getRcVo().setStatus(ActAddTiming.this.getString(2131100226));
      }

      public void on()
      {
        ActAddTiming.this.vo.getRcVo().setStatus(ActAddTiming.this.getString(R.string.on));
        ActAddTiming.this.tv_act_add_timing_action.setText(ActAddTiming.this.getString(R.string.on));
      }
    });
    localMyAlertDialog10.show();
  }

  public void goGradientTime(View paramView)
  {
    goAct4result(AtYkGradientTime.class, 0);
  }

  public void goMode(View paramView)
  {
    goAct4result(AtModeList.class, this.modeReqCode);
  }

  public void goRepeat(View paramView)
  {
    Intent localIntent = new Intent(this, ActRepeatDay.class);
    localIntent.putExtra("timingVo", this.gson.toJson(this.vo));
    startActivityForResult(localIntent, this.repeatDayReqCode);
  }

  public void goWendu(View paramView)
  {
    findViewById(2131558551).setVisibility(View.VISIBLE);
  }

  public void goYaoKong(View paramView)
  {
    Intent localIntent = new Intent(this, AtSaveYongkongList.class);
    localIntent.putExtra("OP_AT_TYPE_KEY", "OP_RC_TYPE_TIME");
    startActivityForResult(localIntent, 0);
  }

  public void hideChanel(View paramView)
  {
    findViewById(2131558553).setVisibility(View.GONE);
    this.tv_act_add_timing_action.setText(this.chanel + getString(2131099929));
    this.vo.setChanel(this.tv_act_add_timing_action.getText().toString());
  }

  public void hideWendu(View paramView)
  {
    findViewById(2131558551).setVisibility(View.GONE);
    this.vo.setWendu(this.wendu + "℃");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 210000)
    {
      String str4 = paramIntent.getStringExtra("gradient_time");
      this.vo.setGradientMins(Integer.parseInt(str4.substring(0, str4.length() - getString(R.string.min).length())));
      this.tv_gradient_time.setText(str4);
    }
    int i3;
    int i4;
    if (paramInt2 == 50000)
      if (paramIntent == null)
      {
        i3 = 1;
        if ((this.lastActItemPosi == -1) || (this.vo.getRcVo() != null))
          break label113;
        i4 = 1;
        label98: if ((i3 | i4) == 0)
          break label119;
      }
    while (true)
    {
      return;
      i3 = 0;
      break;
      label113: i4 = 0;
      break label98;
      label119: this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
      InnerRcVo localInnerRcVo4 = (InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName());
      this.vo.setRcVo(localInnerRcVo4);
      this.tv_act_add_timing_yaokong.setText(localInnerRcVo4.getName());
      this.vo.setYaoKongName(localInnerRcVo4.getName());
      this.vo.setYkType(localInnerRcVo4.getTypeStr());
      this.vo.setId(localInnerRcVo4.getId());
      int i1;
      label576: int i2;
      label585: CtTimingAction localCtTimingAction;
      if (!localInnerRcVo4.getStatus().equals(getString(2131100226)))
      {
        this.vo.setYkType(localInnerRcVo4.getTypeStr());
        String[] arrayOfString = localInnerRcVo4.getStatus().split(",");
        this.vo.setAirMode(arrayOfString[0]);
        this.vo.setWendu(arrayOfString[1]);
        this.vo.setOnOff(true);
        this.tv_act_add_timing_action.setText(localInnerRcVo4.getStatus());
        if (paramInt2 == 100000)
        {
          if ((paramIntent == null) || ((this.lastActItemPosi != -1) && (this.vo.getRcVo().getmType() > 9)))
            continue;
          this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
          InnerRcVo localInnerRcVo3 = (InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName());
          this.vo.setRcVo(localInnerRcVo3);
          this.tv_act_add_timing_yaokong.setText(localInnerRcVo3.getName());
          this.vo.setYaoKongName(localInnerRcVo3.getName());
          this.vo.setYkType(localInnerRcVo3.getTypeStr());
          this.vo.setId(localInnerRcVo3.getId());
          this.tv_act_add_timing_action.setText(localInnerRcVo3.getStatus());
        }
        if (paramInt2 == 70000)
        {
          if ((paramIntent == null) || ((this.lastActItemPosi != -1) && (this.vo.getRcVo().getmType() > 9)))
            continue;
          this.tv_act_add_timing_action.setText(getString(R.string.on));
          this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
          InnerRcVo localInnerRcVo2 = new InnerRcVo();
          int m = paramIntent.getIntExtra("OP_RC_POSI_KEY", 0);
          int n = paramIntent.getIntExtra("RC_TYPE_KEY", 0);
          String str3 = paramIntent.getStringExtra("RC_NAME_KEY");
          this.tv_act_add_timing_yaokong.setText(str3);
          localInnerRcVo2.setStatus(getString(R.string.on));
          localInnerRcVo2.setName(str3);
          localInnerRcVo2.setmType(n);
          this.vo.setYkType(localInnerRcVo2.getTypeStr());
          localInnerRcVo2.setmSaveRcListPosi(m);
          this.vo.setRcVo(localInnerRcVo2);
          if (n != 1)
            break label1296;
          i1 = 1;
          if (n != 2)
            break label1302;
          i2 = 1;
          if ((i2 | i1) != 0)
            this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
          this.vo.setYaoKongName(localInnerRcVo2.getName());
          this.vo.setYkType(localInnerRcVo2.getTypeStr());
        }
        if (paramInt2 == 240000)
        {
          if ((paramIntent == null) || ((this.lastActItemPosi != -1) && (this.vo.getRcVo().getmType() < 10)))
            continue;
          this.rl_act_add_timing_action.setVisibility(View.VISIBLE);
          String str2 = paramIntent.getStringExtra("RC_NAME_KEY");
          this.tv_act_add_timing_yaokong.setText(str2);
          int j = paramIntent.getIntExtra("OP_RC_POSI_KEY", 0);
          this.bussines.initRcData();
          InnerRcVo localInnerRcVo1 = new InnerRcVo();
          int k = paramIntent.getIntExtra("RC_TYPE_KEY", 0);
          localInnerRcVo1.setStatus(getString(R.string.on));
          localInnerRcVo1.setName(str2);
          localInnerRcVo1.setmSaveRcListPosi(j);
          this.vo.setYaoKongName(localInnerRcVo1.getName());
          localInnerRcVo1.setmType(k);
          this.vo.setYkType(localInnerRcVo1.getTypeStr());
          this.vo.setRcVo(localInnerRcVo1);
          this.vo.getRcVo().nonIrDevice = ((MyRcDevice)this.bussines.getRcDevices().myRcDevices.get(j)).nonIrDevice;
          this.tv_act_add_timing_action.setText("");
          this.vo.setStatus("");
        }
        if (paramInt2 == 220000)
        {
          this.tv_act_add_timing_action.setText(2131100234);
          this.vo.getRcVo().nonIrDevice.mType = this.vo.getRcVo().getmType();
          this.vo.getRcVo().nonIrDevice.irPanelSwitch1 = paramIntent.getBooleanExtra("WayOneOnoffKey", false);
          this.vo.getRcVo().nonIrDevice.irPanelSwitch2 = paramIntent.getBooleanExtra("WayTwoOnoffKey", false);
          this.vo.getRcVo().nonIrDevice.irPanelSwitchSelected1 = ((InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName())).nonIrDevice.irPanelSwitchSelected1;
          this.vo.getRcVo().nonIrDevice.irPanelSwitchSelected2 = ((InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName())).nonIrDevice.irPanelSwitchSelected2;
          this.vo.getRcVo().setStatus(((InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName())).getStatus());
          this.tv_act_add_timing_action.setText(this.vo.getRcVo().getStatus());
          this.vo.setStatus(this.vo.getRcVo().getStatus());
        }
        if (paramInt2 == 230000)
        {
          if (paramIntent == null)
            continue;
          this.vo.getRcVo().nonIrDevice.irCt1Onoff = true;
          this.tv_act_add_timing_action.setText(2131100054);
          showHideEffectLayout();
          this.vo.setStatus(getString(2131100054));
          localCtTimingAction = (CtTimingAction)paramIntent.getSerializableExtra(CtTimingAction.class.getName());
        }
        switch (localCtTimingAction.seletedType)
        {
        default:
          label1136: if (paramInt1 != this.modeReqCode)
            break;
        case 1:
        case 2:
        }
      }
      try
      {
        this.tv_act_add_timing_action.setText(paramIntent.getStringExtra("mode"));
        this.vo.setAirMode(paramIntent.getStringExtra("mode"));
        if (paramInt1 != this.repeatDayReqCode)
          continue;
        TimingVo localTimingVo = TimingData.getInstance(this).getCacheTimingVo4Sd();
        this.vo.setShotNameDays(localTimingVo.getShotNameDays());
        this.vo.setLongNameDays(localTimingVo.getLongNameDays());
        List localList = this.vo.getShotNameDays();
        str1 = "";
        int i = 0;
        while (true)
          if (i < localList.size())
          {
            str1 = str1 + "\t\t " + (String)localList.get(i);
            i++;
            continue;
            this.vo.setOnOff(false);
            break;
            label1296: i1 = 0;
            break label576;
            label1302: i2 = 0;
            break label585;
            Ct1ScenesVo localCt1ScenesVo = this.bussines.getCt1SceneVos();
            this.vo.getRcVo().nonIrDevice.irCt1Brt = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1Brt;
            this.vo.getRcVo().nonIrDevice.irCt1C = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1C;
            this.vo.getRcVo().nonIrDevice.irCt1W = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1W;
            break label1136;
            this.vo.getRcVo().nonIrDevice.irCt1Brt = localCtTimingAction.brt;
            this.vo.getRcVo().nonIrDevice.irCt1C = localCtTimingAction.c;
            this.vo.getRcVo().nonIrDevice.irCt1W = localCtTimingAction.w;
          }
      }
      catch (Exception localException)
      {
        String str1;
        while (true)
          localException.printStackTrace();
        if (str1.length() > 0)
        {
          this.tv_act_add_timing_repeat.setText(str1);
          return;
        }
        this.tv_act_add_timing_repeat.setText(getString(R.string.once));
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968607);
    findView();
    init();
    setTitleView();
    setListener();
    getMyIntent();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.tv_act_add_timing_yaokong.getText().toString().length() == 0)
    {
      toast(2131100528);
      return;
    }
    if (this.tv_act_add_timing_action.getText().toString().length() == 0)
    {
      toast(2131100527);
      return;
    }
    if (this.tv_act_add_timing_yaokong.getText().toString().length() > 0)
    {
      this.vo.setSwich(true);
      this.vo.setTime(this.hour + ":" + this.min);
      if (this.vo.getXuHao() == -1)
        this.vo.setXuHao(this.bussines.getTimgItemXuHao());
      if (this.tv_act_add_timing_repeat.getText().toString().indexOf(getString(R.string.once)) != -1)
      {
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(getString(R.string.once));
        this.vo.setShotNameDays(localArrayList);
        this.vo.setIsJustOnce(true);
        if (DateFmtUtil.getTime4HHmm(this.vo.getTime()) > System.currentTimeMillis())
        {
          this.vo.setJustOnceCurrentTime(DateFmtUtil.getTime4HHmm(this.vo.getTime()));
          this.vos = TimingData.getInstance(this).getTimingVos4Sd();
        }
      }
      for (int i = 0; ; i++)
      {
        if (i >= this.vos.size())
          break label553;
        if ((!((TimingVo)this.vos.get(i)).getTime().equalsIgnoreCase(this.vo.getTime())) || (!((TimingVo)this.vos.get(i)).getTime().equalsIgnoreCase(this.vo.getTime())))
          continue;
        String str1 = "";
        List localList1 = ((TimingVo)this.vos.get(i)).getShotNameDays();
        if (localList1 != null)
        {
          int k = 0;
          while (true)
            if (k < localList1.size())
            {
              str1 = str1 + (String)localList1.get(k) + "\t";
              k++;
              continue;
              this.vo.setJustOnceCurrentTime(17280000L + DateFmtUtil.getTime4HHmm(this.vo.getTime()));
              break;
              this.vo.setIsJustOnce(false);
              break;
            }
        }
        String str2 = "";
        List localList2 = this.vo.getShotNameDays();
        if (localList2 != null)
          for (int j = 0; j < localList2.size(); j++)
            str2 = str2 + (String)localList2.get(j) + "\t";
        if ((!str1.equalsIgnoreCase(str2)) || (this.lastActItemPosi != -1))
          continue;
        toast(2131100241);
        return;
      }
      label553: this.vo.setIsOther(false);
      if (this.vo.getRcVo() != null)
        this.vo.getRcVo().setStatus(this.tv_act_add_timing_action.getText().toString());
      this.bussines.saveCacheVos(this.vo);
      setResult(-1);
      finish();
    }
    while (true)
    {
      System.out.println("#$%^&*()    ++++++++++    " + this.vo.toString());
      return;
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

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(R.mipmap.add_timing);
    setBgWhite();
    setEditTextRes(2131100416, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.time.act.ActAddTiming
 * JD-Core Version:    0.6.0
 */