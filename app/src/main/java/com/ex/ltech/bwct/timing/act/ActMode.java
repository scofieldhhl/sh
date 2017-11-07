package com.ex.ltech.bwct.timing.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import com.ex.ltech.bwct.timing.TimingData;
import com.ex.ltech.bwct.timing.fragment.ColorFragment;
import com.ex.ltech.bwct.timing.fragment.ModeFragment;
import com.ex.ltech.led.R;
import com.ex.ltech.led.fragment.FragmentPageAdapter;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.ex.ltech.led.vo.CtSceneVo;
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
  private ColorFragment fragmentTimingColor;
  Gson gs = new Gson();
  private LayoutInflater layoutInflater;
  private List<Fragment> mFragments = new ArrayList();
  private NoScrollViewPager mVPFragments;
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
    this.mVPFragments = ((NoScrollViewPager)findViewById(R.id.pager2));
    this.mVPFragments.setOnPageChangeListener(this);
    this.mVPFragments.setOffscreenPageLimit(3);
    this.layoutInflater = LayoutInflater.from(this);
    this.modeFragment = new ModeFragment();
    this.fragmentTimingColor = new ColorFragment();
    this.mFragments.add(this.modeFragment);
    this.mFragments.add(this.fragmentTimingColor);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
    this.mVPFragments.setNoScroll(true);
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
    switch (paramView.getId())
    {
    default:
      break;
    case R.id.btn_acti_mode_menu:
      if (this.pagerCurrent == 0)
      {
        this.vo.setSeletedCtScenes(this.modeFragment.ctSceneVos);
        this.vo.setModeName(this.modeFragment.modeName);
        this.vo.setBrt(0);
        this.vo.setR(0);
        this.vo.setG(0);
        this.vo.setB(0);
        this.vo.setW(0);
        this.vo.setType(0);
      }
      String str2;
      TimingVo localTimingVo;
      while (true)
      {
        List localList = this.vo.getSeletedCtScenes();
        String str1 = "";
        if (localList == null)
          break;
        int i = 0;
        while (true)
          if (i < localList.size())
          {
            CtSceneVo localCtSceneVo = (CtSceneVo)localList.get(i);
            if (localCtSceneVo.isEdit())
              str1 = str1 + localCtSceneVo.getName() + "\t\t";
            i++;
            continue;
            this.vo.setColor(this.fragmentTimingColor.color);
            this.vo.setBrt(this.fragmentTimingColor.brt);
            this.vo.setR(this.fragmentTimingColor.r);
            this.vo.setG(this.fragmentTimingColor.g);
            this.vo.setB(this.fragmentTimingColor.b);
            this.vo.setW(this.fragmentTimingColor.w);
            this.vo.setC(this.fragmentTimingColor.c);
            this.vo.setType(1);
            Toast.makeText(this, getString(R.string.col_seleted), Toast.LENGTH_SHORT).show();
            break;
          }
        str2 = str1.substring(0, -2 + str1.length());
        localTimingVo = this.vo;
        if (str2.length() >= 12)
          break label388;
      }
      for (String str3 = str2; ; str3 = str2.substring(0, 11) + "...")
      {
        localTimingVo.setModeName(str3);
        setResult(2000);
        TimingData.getInstance(this).saveCacheVos(this.vo);
        finish();
        return;
      }
    case R.id.btn_acti_timing_mode_mode:
      label388: this.mVPFragments.setCurrentItem(0);
      return;
    case R.id.btn_acti_timing_mode_col:
    }
    this.mVPFragments.setCurrentItem(1);
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
    this.fragmentTimingColor.setPactHeight();
  }
}
