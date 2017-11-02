package com.ex.ltech.led.acti.mode;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.led.vo.ModeVo;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.util.List;

public class ActMode extends MyBaseActivity
  implements AdapterView.OnItemClickListener, SeekBar.OnSeekBarChangeListener
{
  private RippleView btn_acti_mode_play;
  private ModeBusiness business;
  private GridView gridView;
  boolean isAllSeleted;
  boolean isLongClick;
  private boolean isMultiSeleted;
  private boolean isPlay;
  private boolean isSingleSeleted;
  ImageView iv_frag_sys_inside_all_seleted;
  private ModeGridViewAdapter madapter;
  private SeekBar sb_acti_mode;
  private int singleSeletedIndex = -1;
  private int speed = 2;
  int tempSpeed;

  private void findView()
  {
    this.iv_frag_sys_inside_all_seleted = ((ImageView)findViewById(2131558711));
    this.gridView = ((GridView)findViewById(2131558714));
    this.sb_acti_mode = ((SeekBar)findViewById(2131558708));
    this.btn_acti_mode_play = ((RippleView)findViewById(2131558709));
  }

  private void init()
  {
    this.business = new ModeBusiness(this);
    this.business.initModes();
    setAdt();
    this.business.prepareLink();
  }

  private void setAdt()
  {
    this.madapter = new ModeGridViewAdapter(this, this.business.modes, this.business.getNewCreateModeBitmaps());
    this.gridView.setAdapter(this.madapter);
    this.madapter.setMoreSeletedListener(new MoreSeletedListener()
    {
      public void onMoreSeleted(int paramInt)
      {
        ActMode.this.business.onMoreSeleted(paramInt);
        ActMode.this.madapter.notifyDataSetChanged();
        if (ActMode.this.business.isMultiSeleted())
        {
          ActMode.this.sb_acti_mode.setVisibility(4);
          return;
        }
        ActMode.this.sb_acti_mode.setVisibility(0);
      }
    });
    this.madapter.setSingleSeletedListener(new SingleSeletedListener()
    {
      public void onLongClick(int paramInt)
      {
        ActMode.this.isLongClick = true;
        ActMode.this.business.editMode(paramInt);
      }

      public void onSingleSeleted(int paramInt)
      {
        if (ActMode.this.isLongClick)
          ActMode.this.isLongClick = false;
        do
        {
          ModeVo localModeVo;
          do
          {
            return;
            ActMode.access$502(ActMode.this, paramInt);
            ActMode.this.business.onSingleSeleted(paramInt);
            ActMode.this.madapter.notifyDataSetChanged();
            localModeVo = (ModeVo)ActMode.this.business.modes.get(paramInt);
          }
          while (localModeVo.isAddBtn());
          ActMode.this.sb_acti_mode.setProgress(12 * localModeVo.getSpeed());
          ActMode.this.sb_acti_mode.setVisibility(0);
          ActMode.this.iv_frag_sys_inside_all_seleted.setBackgroundResource(2130903315);
          ActMode.this.isAllSeleted = false;
          ActMode.this.sb_acti_mode.setVisibility(0);
        }
        while (!ActMode.this.isPlay);
        ActMode.this.btn_acti_mode_play.setBackgroundResource(2130903500);
        ActMode.access$002(ActMode.this, false);
      }
    });
  }

  private void setListener()
  {
    this.sb_acti_mode.setOnSeekBarChangeListener(this);
    this.btn_acti_mode_play.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActMode localActMode = ActMode.this;
        if (!ActMode.this.isPlay);
        for (boolean bool = true; ; bool = false)
        {
          ActMode.access$002(localActMode, bool);
          if (ActMode.this.business.isMultiSeleted())
          {
            if (!ActMode.this.isPlay)
              break;
            ActMode.this.btn_acti_mode_play.setBackgroundResource(2130903501);
            ActMode.this.business.sendModes();
          }
          return;
        }
        ActMode.this.business.offModes();
        ActMode.this.btn_acti_mode_play.setBackgroundResource(2130903500);
      }
    });
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903197);
    setTiTleTextRes(2131100163);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      this.business.initModes();
      setAdt();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968634);
    setMyTitle();
    findView();
    init();
    setListener();
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.business.onSingleSeleted(paramInt);
    this.madapter.notifyDataSetChanged();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    int i = 1;
    this.speed = (paramSeekBar.getProgress() / 12);
    int j;
    ModeBusiness localModeBusiness1;
    if (this.tempSpeed != this.speed)
    {
      this.tempSpeed = this.speed;
      if (this.isSingleSeleted)
      {
        ModeBusiness localModeBusiness2 = this.business;
        if (this.speed != 0)
          break label160;
        j = i;
        localModeBusiness2.changeMoveSpeed(j);
        this.business.sendSingleMode(this.singleSeletedIndex);
      }
      if (this.isMultiSeleted)
      {
        localModeBusiness1 = this.business;
        if (this.speed != 0)
          break label169;
      }
    }
    while (true)
    {
      localModeBusiness1.sendModesWithSameSpeed(i);
      System.out.println("isMultiSeleted            -----      " + this.isMultiSeleted);
      System.out.println("isSingleSeleted            -----      " + this.speed);
      return;
      label160: j = this.speed;
      break;
      label169: i = this.speed;
    }
  }

  protected void onResume()
  {
    super.onResume();
    if (!Main.lastSendCmd.equals(Main.modeCmd))
    {
      this.isPlay = false;
      this.btn_acti_mode_play.setBackgroundResource(2130903500);
    }
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this.isMultiSeleted = this.business.isMultiSeleted();
    this.isSingleSeleted = this.business.isSingleSeleted();
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    this.business.saveMoveSpeed2Sd();
  }

  public void seletedAll(View paramView)
  {
    this.singleSeletedIndex = -1;
    if (!this.isAllSeleted)
    {
      this.iv_frag_sys_inside_all_seleted.setBackgroundResource(2130903475);
      this.isAllSeleted = true;
      this.sb_acti_mode.setVisibility(4);
    }
    while (true)
    {
      this.business.seletedAll(this.isAllSeleted);
      this.madapter.notifyDataSetChanged();
      return;
      this.iv_frag_sys_inside_all_seleted.setBackgroundResource(2130903315);
      this.isAllSeleted = false;
      this.sb_acti_mode.setVisibility(0);
    }
  }

  public static abstract interface MoreSeletedListener
  {
    public abstract void onMoreSeleted(int paramInt);
  }

  public static abstract interface SingleSeletedListener
  {
    public abstract void onLongClick(int paramInt);

    public abstract void onSingleSeleted(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.mode.ActMode
 * JD-Core Version:    0.6.0
 */