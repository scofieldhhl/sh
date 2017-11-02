package com.ex.ltech.led.acti.mode;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.CircleColorView;
import com.ex.ltech.led.my_view.CircleColorView.OnCilcleColorSeletedListner;
import com.ex.ltech.led.my_view.SceneColorPickerView;
import com.ex.ltech.led.my_view.SceneColorPickerView.OnColorChangedListener;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.ModeVo;
import com.google.gson.Gson;
import java.io.PrintStream;
import java.util.List;

public class ActNewMode extends MyBaseActivity
  implements SceneColorPickerView.OnColorChangedListener, CircleColorView.OnCilcleColorSeletedListner, View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
  private Intent _intent = new Intent();
  int b;
  int brt = 255;
  private Button bt_acti_new_mode_ping;
  private Button bt_acti_new_mode_tiao;
  private Button bt_acti_new_mode_zan;
  private ModeBusiness business = new ModeBusiness(this);
  private int changeMode = 1;
  private int cilcleBallPosi = -1;
  private CircleColorView circleColorView;
  int colorCount = 1;
  private int currentColor;
  private EditText et_act_new_custom_b;
  private EditText et_act_new_custom_g;
  private EditText et_act_new_custom_r;
  int g;
  private boolean isPlay;
  private boolean isThisBG;
  private int modeCount;
  String modeDataStr = null;
  private String modesNames;
  private int modesPosi;
  private int newCreateModesPosi;
  int r;
  private RelativeLayout rl_new_custom_color_bar;
  private int runStatus;
  private SeekBar sb_acti_new_mode_brt;
  private SeekBar sb_acti_new_mode_speed;
  private SceneColorPickerView sceneColorPickerView;
  int speed = 1;
  private RelativeLayout thisRL;
  int time;
  private TextView tv_acti_new_mode_circle_color_posi;
  private ModeVo vo = new ModeVo();

  private void findView()
  {
    this.thisRL = ((RelativeLayout)findViewById(2131558558));
    this.rl_new_custom_color_bar = ((RelativeLayout)findViewById(2131558570));
    this.tv_acti_new_mode_circle_color_posi = ((TextView)findViewById(2131558561));
    this.sceneColorPickerView = ((SceneColorPickerView)findViewById(2131558560));
    this.circleColorView = ((CircleColorView)findViewById(2131558562));
    this.bt_acti_new_mode_tiao = ((Button)findViewById(2131558563));
    this.bt_acti_new_mode_zan = ((Button)findViewById(2131558565));
    this.bt_acti_new_mode_ping = ((Button)findViewById(2131558566));
    this.sb_acti_new_mode_speed = ((SeekBar)findViewById(2131558568));
    this.sb_acti_new_mode_brt = ((SeekBar)findViewById(2131558569));
    this.et_act_new_custom_r = ((EditText)findViewById(2131558572));
    this.et_act_new_custom_r.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        ActNewMode.this.onInputColor();
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        try
        {
          ActNewMode.this.r = Integer.parseInt(paramCharSequence.toString());
          if (ActNewMode.this.r > 255)
          {
            EditText localEditText = ActNewMode.this.et_act_new_custom_r;
            StringBuilder localStringBuilder = new StringBuilder();
            ActNewMode.this.r = 255;
            localEditText.setText(255 + "");
          }
          return;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
          ActNewMode.this.r = 0;
        }
      }
    });
    this.et_act_new_custom_g = ((EditText)findViewById(2131558571));
    this.et_act_new_custom_g.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        ActNewMode.this.onInputColor();
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        try
        {
          ActNewMode.this.g = Integer.parseInt(paramCharSequence.toString());
          if (ActNewMode.this.g > 255)
          {
            EditText localEditText = ActNewMode.this.et_act_new_custom_g;
            StringBuilder localStringBuilder = new StringBuilder();
            ActNewMode.this.g = 255;
            localEditText.setText(255 + "");
          }
          return;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
          ActNewMode.this.g = 0;
        }
      }
    });
    this.et_act_new_custom_b = ((EditText)findViewById(2131558574));
    this.et_act_new_custom_b.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
        ActNewMode.this.onInputColor();
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        try
        {
          ActNewMode.this.b = Integer.parseInt(paramCharSequence.toString());
          if (ActNewMode.this.b > 255)
          {
            EditText localEditText = ActNewMode.this.et_act_new_custom_b;
            StringBuilder localStringBuilder = new StringBuilder();
            ActNewMode.this.b = 255;
            localEditText.setText(255 + "");
          }
          return;
        }
        catch (NumberFormatException localNumberFormatException)
        {
          localNumberFormatException.printStackTrace();
          ActNewMode.this.b = 0;
        }
      }
    });
  }

  private void init()
  {
    this.business.prepareLink();
    this.vo.setIsNewCreateMode(true);
    this.bt_acti_new_mode_zan.setBackgroundResource(2130903314);
    this.modeCount = (2 + getIntent().getIntExtra("modeCount", 1));
    this.modesNames = getIntent().getStringExtra("modesNames");
    this.modesPosi = getIntent().getIntExtra("modesPosi", -1);
    this.newCreateModesPosi = getIntent().getIntExtra("newCreateModesPosi", -1);
    RelativeLayout localRelativeLayout = this.rl_new_custom_color_bar;
    int i = Color.argb(255, 155, 254, 255);
    this.currentColor = i;
    localRelativeLayout.setBackgroundColor(i);
    setupView();
    this.speed = (this.sb_acti_new_mode_speed.getProgress() / 12);
    this.brt = (255 * this.sb_acti_new_mode_brt.getProgress() / 100);
  }

  private void onInputColor()
  {
    try
    {
      Color.argb(0, this.r, this.g, this.b);
      RelativeLayout localRelativeLayout = this.rl_new_custom_color_bar;
      int i = Color.argb(255, this.r, this.g, this.b);
      this.currentColor = i;
      localRelativeLayout.setBackgroundColor(i);
      this.business.sendColorCmd(this.brt, this.r, this.g, this.b);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      toast(2131100097);
    }
  }

  private void setListener()
  {
    this.circleColorView.setMyListener(this);
    this.sceneColorPickerView.setListener(this);
    this.bt_acti_new_mode_tiao.setOnClickListener(this);
    this.bt_acti_new_mode_zan.setOnClickListener(this);
    this.bt_acti_new_mode_ping.setOnClickListener(this);
    this.sb_acti_new_mode_speed.setOnSeekBarChangeListener(this);
    this.sb_acti_new_mode_brt.setOnSeekBarChangeListener(this);
  }

  private void setTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903197);
    setTiTleTextRes(2131100163);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
    setEditStrColor(getResources().getColor(2131492962));
    setEditTextRes(2131100063);
  }

  private void setTitleView()
  {
  }

  private void setupView()
  {
    if (this.modesPosi != -1)
    {
      List localList = this.business.getEditableModes();
      if (localList != null)
      {
        ModeVo localModeVo = (ModeVo)localList.get(this.modesPosi);
        this.circleColorView.setColors(localModeVo.getColors());
        this.circleColorView.invalidate();
        this.sb_acti_new_mode_brt.setProgress(100 * localModeVo.getBrt() / 255);
        this.sb_acti_new_mode_speed.setProgress(12 * localModeVo.getSpeed());
        this.speed = localModeVo.getSpeed();
        this.brt = localModeVo.getBrt();
        this.bt_acti_new_mode_ping.setBackgroundResource(2130903309);
        this.bt_acti_new_mode_zan.setBackgroundResource(2130903313);
        this.bt_acti_new_mode_tiao.setBackgroundResource(2130903311);
        this.changeMode = 1;
        if (localModeVo.getTransformation() == 1)
        {
          this.bt_acti_new_mode_zan.setBackgroundResource(2130903314);
          this.changeMode = 1;
        }
        if (localModeVo.getTransformation() == 2)
        {
          this.bt_acti_new_mode_tiao.setBackgroundResource(2130903312);
          this.changeMode = 2;
        }
        if (localModeVo.getTransformation() == 3)
        {
          this.bt_acti_new_mode_ping.setBackgroundResource(2130903310);
          this.changeMode = 3;
        }
      }
    }
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      setResult(1000, paramIntent);
      finish();
    }
  }

  public void onCilcleBgSeleted(int paramInt)
  {
    this.cilcleBallPosi = paramInt;
    this.circleColorView.saveColor(this.currentColor, paramInt);
  }

  public void onCilcleBgTouchUp()
  {
    this.tv_acti_new_mode_circle_color_posi.setVisibility(8);
    this.tv_acti_new_mode_circle_color_posi.setAnimation(AnimationUtils.loadAnimation(this, 2131034123));
  }

  public void onClick(View paramView)
  {
    this.bt_acti_new_mode_ping.setBackgroundResource(2130903309);
    this.bt_acti_new_mode_zan.setBackgroundResource(2130903313);
    this.bt_acti_new_mode_tiao.setBackgroundResource(2130903311);
    this.changeMode = 1;
    if (paramView == this.bt_acti_new_mode_zan)
    {
      this.bt_acti_new_mode_zan.setBackgroundResource(2130903314);
      this.changeMode = 1;
    }
    if (paramView == this.bt_acti_new_mode_ping)
    {
      this.bt_acti_new_mode_ping.setBackgroundResource(2130903310);
      this.changeMode = 3;
    }
    if (paramView == this.bt_acti_new_mode_tiao)
    {
      this.bt_acti_new_mode_tiao.setBackgroundResource(2130903312);
      this.changeMode = 2;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968609);
    findView();
    setTitle();
    init();
    setListener();
  }

  protected void onEdit()
  {
    super.onEdit();
    for (int i = 0; i < this.circleColorView.colors.size(); i++)
    {
      if (((Integer)this.circleColorView.colors.get(i)).intValue() == -16777216)
        continue;
      int j = 1;
      for (int k = -1 + this.circleColorView.colors.size(); ; k--)
      {
        if (k > 1)
        {
          if (((Integer)this.circleColorView.colors.get(k)).intValue() == -16777216)
            continue;
          System.out.println("rtyuklhjkl       " + k);
          j = k + 1;
        }
        this.business.sendCustomMode(this.newCreateModesPosi, this.changeMode, 227, this.speed, this.brt, j, this.circleColorView.colors);
        this.vo.setColorCount(j);
        this.vo.setColors(this.circleColorView.colors);
        this.vo.setSpeed(this.speed);
        this.vo.setBrt(this.brt);
        this.vo.setTransformation(this.changeMode);
        this.vo.setIsNewCreateMode(true);
        this.vo.setType(3);
        this.modeDataStr = this.business.gs.toJson(this.vo);
        Intent localIntent = new Intent(this, ActNamingMode.class);
        localIntent.putExtra("modeDataStr", this.modeDataStr);
        localIntent.putExtra("modesPosi", this.modesPosi);
        localIntent.putExtra("modesNames", this.modesNames);
        startActivityForResult(localIntent, 1);
        return;
      }
    }
    toast(2131100273);
  }

  public void onLongClick(int paramInt)
  {
    this.circleColorView.colors.remove(paramInt);
    this.circleColorView.colors.add(paramInt, Integer.valueOf(-16777216));
    this.circleColorView.invalidate();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onPikerTouchUp(int paramInt)
  {
  }

  public void onPikerXYChange(int paramInt)
  {
    this.currentColor = paramInt;
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (this.sb_acti_new_mode_brt == paramSeekBar)
    {
      if (paramSeekBar.getProgress() > 4)
      {
        this.brt = (255 * paramSeekBar.getProgress() / 100);
        this.business.sendbrightCmd(this.brt, this.r, this.g, this.b);
      }
      return;
    }
    this.speed = (paramSeekBar.getProgress() / 12);
    this.business.sendCustomMode(this.newCreateModesPosi, this.changeMode, 225, this.speed, this.brt, this.colorCount, this.circleColorView.colors);
  }

  protected void onResume()
  {
    super.onResume();
    this.sceneColorPickerView.setPactHeight(this.thisRL.getHeight());
    System.out.println("709394onResume" + this.thisRL.getHeight());
  }

  public void onRgbChange(int paramInt1, int paramInt2, int paramInt3)
  {
    this.r = paramInt1;
    this.g = paramInt2;
    this.b = paramInt3;
    this.et_act_new_custom_r.setText(paramInt1 + "");
    this.et_act_new_custom_g.setText(paramInt2 + "");
    this.et_act_new_custom_b.setText(paramInt3 + "");
    this.business.sendColorCmd(this.brt, paramInt1, paramInt2, paramInt3);
    try
    {
      Color.argb(0, paramInt1, paramInt2, paramInt3);
      RelativeLayout localRelativeLayout = this.rl_new_custom_color_bar;
      int i = Color.argb(255, paramInt1, paramInt2, paramInt3);
      this.currentColor = i;
      localRelativeLayout.setBackgroundColor(i);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  protected void onStop()
  {
    super.onStop();
    if (this.isPlay)
    {
      this.business.sendCustomMode(this.newCreateModesPosi, this.changeMode, 226, this.speed, this.brt, this.circleColorView.colors.size(), this.circleColorView.colors);
      this.isPlay = false;
      findViewById(2131558564).setBackgroundResource(2130903500);
    }
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    if (paramSeekBar == this.sb_acti_new_mode_speed)
      this.speed = (paramSeekBar.getProgress() / 12);
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    this.sceneColorPickerView.setPactHeight(this.thisRL.getHeight());
    System.out.println("709394onWindowFocusChanged" + this.thisRL.getHeight());
    super.onWindowFocusChanged(paramBoolean);
  }

  public void play(View paramView)
  {
    if (this.isPlay)
    {
      this.business.sendCustomMode(this.newCreateModesPosi, this.changeMode, 226, this.speed, this.brt, this.circleColorView.colors.size(), this.circleColorView.colors);
      this.isPlay = false;
      paramView.setBackgroundResource(2130903500);
      return;
    }
    for (int i = -1 + this.circleColorView.colors.size(); ; i--)
    {
      if (i > 1)
      {
        if (((Integer)this.circleColorView.colors.get(i)).intValue() == -16777216)
          continue;
        System.out.println("rtyuklhjkl       " + i);
        this.colorCount = (i + 1);
      }
      this.business.sendCustomMode(this.newCreateModesPosi, this.changeMode, 225, this.speed, this.brt, this.colorCount, this.circleColorView.colors);
      this.isPlay = true;
      paramView.setBackgroundResource(2130903501);
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.mode.ActNewMode
 * JD-Core Version:    0.6.0
 */