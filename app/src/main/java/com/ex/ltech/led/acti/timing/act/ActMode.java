package com.ex.ltech.led.acti.timing.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.timing.fragment.ModeFragment;
import com.ex.ltech.led.vo.TimingVo;
import com.google.gson.Gson;
import com.indris.material.RippleView;

import java.util.ArrayList;
import java.util.List;

public class ActMode extends FragmentActivity
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ViewPager.OnPageChangeListener
{
  public static int sonActHeightWithouTitle;
  private Button btn_acti_mode_menu;
  private RippleView btn_acti_timing_mode_col;
  private RippleView btn_acti_timing_mode_mode;
//  private ColorFragment fragmentTimingColor;
  Gson gs = new Gson();
  private LayoutInflater layoutInflater;
  private List<Fragment> mFragments = new ArrayList();
//  private NoScrollViewPager mVPFragments;
  private ModeFragment modeFragment;
  private int pagerCurrent = 0;
  private TimingVo vo;

  private void findView()
  {
    this.btn_acti_mode_menu = ((Button)findViewById(R.id.btn_acti_mode_menu));
    this.btn_acti_timing_mode_mode = ((RippleView)findViewById(R.id.btn_acti_timing_mode_mode));
    this.btn_acti_timing_mode_col = ((RippleView)findViewById(R.id.btn_acti_timing_mode_col));
  }

  private void getMyIntent()
  {
    Intent localIntent = getIntent();
    if (localIntent != null)
      this.vo = ((TimingVo)this.gs.fromJson(localIntent.getStringExtra("timingVo"), TimingVo.class));
  }

  private void init()
  {
  }

  private void initViewPager()
  {
    /*this.mVPFragments = ((NoScrollViewPager)findViewById(R.id.pager2));
    this.mVPFragments.setOnPageChangeListener(this);
    this.mVPFragments.setOffscreenPageLimit(3);
    this.layoutInflater = LayoutInflater.from(this);
    this.modeFragment = new ModeFragment();
    this.fragmentTimingColor = new ColorFragment();
    this.mFragments.add(this.modeFragment);
    this.mFragments.add(this.fragmentTimingColor);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
    this.mVPFragments.setNoScroll(true);*/
  }

  private void pagerChange(int paramInt)
  {
    if (paramInt == this.pagerCurrent)
      return;
    if (paramInt == 0)
    {
      this.btn_acti_timing_mode_mode.setBackgroundResource(R.mipmap.ic_btn_mode_press);
      this.btn_acti_timing_mode_col.setBackgroundResource(R.mipmap.ic_btn_color);
    }
    if (paramInt == 1)
    {
      this.btn_acti_timing_mode_mode.setBackgroundResource(R.mipmap.ic_btn_mode);
      this.btn_acti_timing_mode_col.setBackgroundResource(R.mipmap.ic_btn_color_press);
    }
    this.pagerCurrent = paramInt;
  }

  private void setListener()
  {
    this.btn_acti_mode_menu.setOnClickListener(this);
    this.btn_acti_timing_mode_mode.setOnClickListener(this);
    this.btn_acti_timing_mode_col.setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    /*switch (paramView.getId())
    {
    default:
      break;
    case R.id.btn_acti_mode_menu:
      if (this.pagerCurrent == 0)
      {
        this.vo.setSeletedModes(this.modeFragment.modes);
        this.vo.setModeName(this.modeFragment.modeName);
        this.vo.setBrt(0);
        this.vo.setR(0);
        this.vo.setG(0);
        this.vo.setB(0);
        this.vo.setW(0);
        this.vo.setType(0);
      }
      String str1;
      while (true)
      {
        List localList = this.vo.getSeletedModes();
        str1 = "";
        if (localList == null)
          break label351;
        for (int i = 0; i < localList.size(); i++)
        {
          ModeVo localModeVo = (ModeVo)localList.get(i);
          if (!localModeVo.isSeleted())
            continue;
          str1 = str1 + localModeVo.getTvName() + "\t\t";
        }
        this.vo.setColor(this.fragmentTimingColor.color);
        this.vo.setBrt(this.fragmentTimingColor.brt);
        this.vo.setR(this.fragmentTimingColor.r);
        this.vo.setG(this.fragmentTimingColor.g);
        this.vo.setB(this.fragmentTimingColor.b);
        this.vo.setW(this.fragmentTimingColor.w);
        this.vo.setType(1);
        Toast.makeText(this, getString(R.string.col_seleted), Toast.LENGTH_SHORT).show();
      }
      try
      {
        String str2 = str1.substring(0, -2 + str1.length());
        TimingVo localTimingVo = this.vo;
        if (str2.length() < 12);
        String str3;
        for (Object localObject = str2; ; localObject = str3)
        {
          localTimingVo.setModeName((String)localObject);
          setResult(2000);
          TimingData.getInstance(this).saveCacheVos(this.vo);
          finish();
          return;
          str3 = str2.substring(0, 11) + "...";
        }
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    case R.id.btn_acti_timing_mode_mode:
      label351: this.mVPFragments.setCurrentItem(0);
      return;
    case R.id.btn_acti_timing_mode_col:
    }
    this.mVPFragments.setCurrentItem(1);*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_timing_mode);
    initViewPager();
    findView();
    init();
    setListener();
    getMyIntent();
  }

  public void onPageScrollStateChanged(int paramInt)
  {
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
  }

  public void onPageSelected(int paramInt)
  {
    System.out.println("onPageSelected" + paramInt);
    pagerChange(paramInt);
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    sonActHeightWithouTitle = findViewById(R.id.rl_act_timing_mode).getHeight();
//    this.fragmentTimingColor.setPactHeight();
  }
}
