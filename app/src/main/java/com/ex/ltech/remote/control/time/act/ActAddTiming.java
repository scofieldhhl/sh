package com.ex.ltech.remote.control.time.act;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.ColorSeletedView;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.remote.control.time.TimingBussines;
import com.ex.ltech.remote.control.time.TimingData;
import com.ex.ltech.remote.control.vo.YaokongTimingVo;
import com.ex.ltech.remote.control.yaokong.AtSaveYongkongList;
import com.google.gson.Gson;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
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
  private int lastActItemPosi;
  private String lightStatus;
  private String min = "30";
  int modeReqCode = 2000;
  int repeatDayReqCode = 4000;
  private RelativeLayout rl_act_add_timing_chanel;
  private RelativeLayout rl_act_add_timing_mode;
  private RelativeLayout rl_act_add_timing_repeat;
  private RelativeLayout rl_act_add_timing_wendu;
  private RelativeLayout rl_act_add_timing_yaokong;
  MyTimePickerView tp_act_add_timing_chanel;
  MyTimePickerView tp_act_add_timing_hour;
  MyTimePickerView tp_act_add_timing_min;
  MyTimePickerView tp_act_add_timing_wendu;
  private TextView tv_act_add_timing_chanel;
  private TextView tv_act_add_timing_mode;
  private TextView tv_act_add_timing_repeat;
  private TextView tv_act_add_timing_wendu;
  private TextView tv_act_add_timing_yaokong;
  private YaokongTimingVo vo = null;
  private List<YaokongTimingVo> vos;
  private String wendu = "25";
  int yaokongReqCode = 1000;
  String ykType = "";

  private void findView()
  {
    this.tp_act_add_timing_hour = ((MyTimePickerView)findViewById(2131558504));
    this.tp_act_add_timing_min = ((MyTimePickerView)findViewById(2131558505));
    this.tp_act_add_timing_chanel = ((MyTimePickerView)findViewById(2131558554));
    this.tp_act_add_timing_wendu = ((MyTimePickerView)findViewById(2131558552));
    this.rl_act_add_timing_yaokong = ((RelativeLayout)findViewById(2131558510));
    this.rl_act_add_timing_mode = ((RelativeLayout)findViewById(2131558516));
    this.rl_act_add_timing_wendu = ((RelativeLayout)findViewById(2131558521));
    this.rl_act_add_timing_repeat = ((RelativeLayout)findViewById(2131558608));
    this.rl_act_add_timing_chanel = ((RelativeLayout)findViewById(2131558606));
    this.tv_act_add_timing_yaokong = ((TextView)findViewById(2131558539));
    this.tv_act_add_timing_mode = ((TextView)findViewById(2131558512));
    this.tv_act_add_timing_chanel = ((TextView)findViewById(2131558543));
    this.tv_act_add_timing_wendu = ((TextView)findViewById(2131558545));
    this.tv_act_add_timing_repeat = ((TextView)findViewById(2131558518));
    this.cv_act_add_timing_mode = ((ColorSeletedView)findViewById(2131558513));
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
    {
      this.lastActItemPosi = localIntent.getIntExtra("itemPosi", -1);
      if (this.lastActItemPosi == -1)
        break label338;
      this.vo = ((YaokongTimingVo)new Gson().fromJson(localIntent.getStringExtra("voData"), YaokongTimingVo.class));
      String[] arrayOfString = this.vo.getTime().split(":");
      this.hour = arrayOfString[0];
      this.min = arrayOfString[1];
      this.tp_act_add_timing_hour.setSelected(Integer.parseInt(this.hour));
      this.tp_act_add_timing_min.setSelected(Integer.parseInt(this.min));
      this.tp_act_add_timing_chanel.setSelected(1);
      this.tp_act_add_timing_wendu.setSelected(25);
      List localList = this.vo.getShotNameDays();
      String str3 = "";
      for (int i = 0; i < localList.size(); i++)
        str3 = str3 + "\t\t" + (String)localList.get(i);
      this.tv_act_add_timing_repeat.setText(str3);
      if (this.vo.isOffDevice())
        break label330;
      open(null);
      if (this.vo.getYaoKongName().equals(getString(2131099858)))
      {
        this.rl_act_add_timing_mode.setVisibility(View.VISIBLE);
        this.rl_act_add_timing_wendu.setVisibility(View.VISIBLE);
      }
      if ((this.vo.getYaoKongName().equals(getString(2131100457))) || ((this.vo.getYaoKongName().equals(getString(2131100459)) | this.vo.getYaoKongName().equals(getString(2131100109)))))
        this.rl_act_add_timing_chanel.setVisibility(View.VISIBLE);
    }
    while (true)
    {
      this.vo.setOnOff(true);
      return;
      label330: close(null);
      break;
      label338: this.tp_act_add_timing_chanel.setSelected(1);
      this.tp_act_add_timing_wendu.setSelected(9);
      this.tp_act_add_timing_hour.setSelected(Integer.parseInt(new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()))));
      this.tp_act_add_timing_min.setSelected(Integer.parseInt(new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()))));
      this.vo = new YaokongTimingVo();
      YaokongTimingVo localYaokongTimingVo = this.vo;
      StringBuilder localStringBuilder1 = new StringBuilder();
      String str1 = new SimpleDateFormat("HH").format(Long.valueOf(System.currentTimeMillis()));
      this.hour = str1;
      StringBuilder localStringBuilder2 = localStringBuilder1.append(str1).append(":");
      String str2 = new SimpleDateFormat("mm").format(Long.valueOf(System.currentTimeMillis()));
      this.min = str2;
      localYaokongTimingVo.setTime(str2);
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(getString(R.string.once));
      this.vo.setShotNameDays(localArrayList);
      this.vo.setSwich(true);
      open(null);
    }
  }

  private void init()
  {
    this.bussines = new TimingBussines(this);
    this.tp_act_add_timing_hour.setData(this.bussines.getHourDate());
    this.tp_act_add_timing_min.setData(this.bussines.getMinDate());
    this.tp_act_add_timing_hour.setTextCol(getResources().getColor(2131492897));
    this.tp_act_add_timing_min.setTextCol(getResources().getColor(2131492897));
    this.tp_act_add_timing_chanel.setData(this.bussines.getChanelDate());
    this.tp_act_add_timing_chanel.setmColorText(getResources().getColor(2131492897));
    this.tp_act_add_timing_wendu.setData(this.bussines.getWenduDate());
  }

  private void setListener()
  {
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
    this.tp_act_add_timing_chanel.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$202(ActAddTiming.this, paramString);
        ActAddTiming.this.vo.setChanel(ActAddTiming.this.chanel);
      }
    });
    this.tp_act_add_timing_wendu.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        ActAddTiming.access$402(ActAddTiming.this, paramString);
      }
    });
  }

  public void close(View paramView)
  {
    findViewById(2131558507).setBackgroundResource(2130903318);
    findViewById(2131558508).setBackgroundResource(2130903280);
    this.vo.setType(2);
    this.lightStatus = getString(R.string.off_device);
    this.vo.setOnOff(false);
  }

  public void goChanel(View paramView)
  {
    findViewById(2131558553).setVisibility(View.VISIBLE);
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
    localIntent.putExtra("isFromTimg", true);
    startActivityForResult(localIntent, this.yaokongReqCode);
  }

  public void hideChanel(View paramView)
  {
    findViewById(2131558553).setVisibility(View.GONE);
    this.tv_act_add_timing_chanel.setText(this.chanel + getString(2131099929));
    this.vo.setChanel(this.tv_act_add_timing_chanel.getText().toString());
  }

  public void hideWendu(View paramView)
  {
    findViewById(2131558551).setVisibility(View.GONE);
    this.tv_act_add_timing_wendu.setText(this.wendu + "℃");
    this.vo.setWendu(this.wendu + "℃");
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt1 == this.yaokongReqCode)
      if (paramIntent != null);
    while (true)
    {
      return;
      this.tv_act_add_timing_yaokong.setText(paramIntent.getStringExtra("ykName"));
      this.vo.setYaoKongName(paramIntent.getStringExtra("ykName"));
      this.vo.setYkType(paramIntent.getStringExtra("ykType"));
      this.vo.setId(paramIntent.getIntExtra("ykId", -1));
      ETDeviceAIR localETDeviceAIR;
      if (this.vo.getYkType().equals("air"))
      {
        this.ykType = "air";
        ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
        localETGroup.Load(ETDB.getInstance(this));
        localETDeviceAIR = (ETDeviceAIR)localETGroup.GetItem(paramIntent.getIntExtra("ykId", -1));
        if (localETDeviceAIR.GetKeyByValue(49153).GetState() == 1)
        {
          Toast.makeText(this, 2131100247, 1).show();
          this.rl_act_add_timing_mode.setVisibility(View.VISIBLE);
          this.rl_act_add_timing_wendu.setVisibility(View.VISIBLE);
          this.rl_act_add_timing_chanel.setVisibility(View.GONE);
          this.vo.setAirMode(getString(2131099860));
          label205: if (paramInt1 != this.modeReqCode);
        }
      }
      try
      {
        this.tv_act_add_timing_mode.setText(paramIntent.getStringExtra("mode"));
        this.vo.setAirMode(paramIntent.getStringExtra("mode"));
        if (paramInt1 != this.repeatDayReqCode)
          continue;
        YaokongTimingVo localYaokongTimingVo = TimingData.getInstance(this).getCacheTimingVo4Sd();
        this.vo.setShotNameDays(localYaokongTimingVo.getShotNameDays());
        this.vo.setLongNameDays(localYaokongTimingVo.getLongNameDays());
        List localList = this.vo.getShotNameDays();
        str = "";
        int i = 0;
        while (true)
          if (i < localList.size())
          {
            str = str + "\t\t " + (String)localList.get(i);
            i++;
            continue;
            if (localETDeviceAIR.GetKeyByValue(49155).GetState() != 1)
              break;
            Toast.makeText(this, 2131100164, 1).show();
            break;
            if ((paramIntent.getStringExtra("ykType").equals("tv") | paramIntent.getStringExtra("ykType").equals("tvbox") | paramIntent.getStringExtra("ykType").equals("iptv")))
            {
              this.rl_act_add_timing_chanel.setVisibility(View.VISIBLE);
              this.rl_act_add_timing_mode.setVisibility(View.GONE);
              this.rl_act_add_timing_wendu.setVisibility(View.GONE);
              this.ykType = "tv";
              break label205;
            }
            this.rl_act_add_timing_mode.setVisibility(View.GONE);
            this.rl_act_add_timing_wendu.setVisibility(View.GONE);
            this.rl_act_add_timing_chanel.setVisibility(View.GONE);
          }
      }
      catch (Exception localException)
      {
        String str;
        while (true)
          localException.printStackTrace();
        if (str.length() > 0)
        {
          this.tv_act_add_timing_repeat.setText(str);
          return;
        }
        this.tv_act_add_timing_repeat.setText(getString(R.string.once));
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968614);
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
    if (this.vo.getYaoKongName().equals(getString(2131099858)))
    {
      if (this.tv_act_add_timing_mode.getText().toString().length() == 0)
      {
        toast(2131100528);
        return;
      }
      if (this.tv_act_add_timing_wendu.getText().toString().length() == 0)
      {
        toast(2131099872);
        return;
      }
    }
    if (((this.vo.getYaoKongName().equals(getString(2131100457))) || ((this.vo.getYaoKongName().equals(getString(2131100459)) | this.vo.getYaoKongName().equals(getString(2131100109))))) && (this.tv_act_add_timing_chanel.getText().toString().length() == 0))
    {
      toast(2131099930);
      return;
    }
    if (this.vo.getYaoKongName().length() > 0)
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
        if (this.tv_act_add_timing_repeat.getText().toString().indexOf(getString(R.string.once)) == -1)
          break label522;
        ArrayList localArrayList = new ArrayList();
        localArrayList.add(getString(R.string.once));
        this.vo.setShotNameDays(localArrayList);
        this.vo.setIsJustOnce(true);
        if (DateFmtUtil.getTime4HHmm(this.vo.getTime()) <= System.currentTimeMillis())
          break label498;
        this.vo.setJustOnceCurrentTime(DateFmtUtil.getTime4HHmm(this.vo.getTime()));
        label415: this.vos = TimingData.getInstance(this).getTimingVos4Sd();
        this.vo.setIsOther(false);
        this.bussines.saveCacheVos(this.vo);
        setResult(-1);
        finish();
      }
    }
    while (true)
    {
      System.out.println("#$%^&*()    ++++++++++    " + this.vo.toString());
      return;
      this.vo.setIsOffDevice(false);
      break;
      label498: this.vo.setJustOnceCurrentTime(17280000L + DateFmtUtil.getTime4HHmm(this.vo.getTime()));
      break label415;
      label522: this.vo.setIsJustOnce(false);
      break label415;
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
    findViewById(2131558507).setBackgroundResource(2130903281);
    findViewById(2131558508).setBackgroundResource(R.mipmap.ic_off);
    findViewById(2131558510).setVisibility(View.VISIBLE);
    this.lightStatus = getString(R.string.on);
    this.vo.setOnOff(true);
    if (this.ykType.equals("air"))
      goWendu(null);
    if (this.ykType.equals("tv"))
      goChanel(null);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(R.mipmap.add_timing);
    setEditTextRes(R.string.finish, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.time.act.ActAddTiming
 * JD-Core Version:    0.6.0
 */