package com.ex.ltech.led.acti.scene;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.ex.ltech.led.R;
import com.ex.ltech.led.fragment.FragmentPageAdapter;
import com.ex.ltech.led.fragment.FragmentSysCustom;
import com.ex.ltech.led.fragment.FragmentSysInside;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActScene extends FragmentActivity
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ViewPager.OnPageChangeListener
{
  private RippleView btn_acti_scene_custom;
  private Button btn_acti_scene_menu;
  private RippleView btn_acti_scene_sys_inside;
  private FragmentSysCustom fragmentSysCustom;
  private FragmentSysInside fragmentSysInside;
  private LayoutInflater layoutInflater;
  private List<Fragment> mFragments = new ArrayList();
  private ViewPager mVPFragments;
  private int pagerCurrent = 0;

  private void findView()
  {
    this.btn_acti_scene_menu = ((Button)findViewById(2131558768));
    this.btn_acti_scene_sys_inside = ((RippleView)findViewById(2131558766));
    this.btn_acti_scene_custom = ((RippleView)findViewById(2131558767));
  }

  private void init()
  {
    this.btn_acti_scene_sys_inside.setBackgroundResource(2130903356);
  }

  private void initViewPager()
  {
    this.mVPFragments = ((ViewPager)findViewById(2131558769));
    this.mVPFragments.setOnPageChangeListener(this);
    this.mVPFragments.setOffscreenPageLimit(2);
    this.layoutInflater = LayoutInflater.from(this);
    this.fragmentSysInside = new FragmentSysInside();
    this.fragmentSysCustom = new FragmentSysCustom();
    this.mFragments.add(this.fragmentSysInside);
    this.mFragments.add(this.fragmentSysCustom);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
  }

  private void pagerChange(int paramInt)
  {
    if (paramInt == this.pagerCurrent)
      return;
    if (paramInt == 0)
    {
      this.btn_acti_scene_sys_inside.setBackgroundResource(2130903356);
      this.btn_acti_scene_custom.setBackgroundResource(2130903353);
    }
    while (true)
    {
      this.pagerCurrent = paramInt;
      return;
      this.btn_acti_scene_sys_inside.setBackgroundResource(2130903355);
      this.btn_acti_scene_custom.setBackgroundResource(2130903354);
    }
  }

  private void setListener()
  {
    this.btn_acti_scene_menu.setOnClickListener(this);
    this.btn_acti_scene_sys_inside.setOnClickListener(this);
    this.btn_acti_scene_custom.setOnClickListener(this);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131558768:
    default:
      return;
    case 2131558766:
      this.mVPFragments.setCurrentItem(0);
      return;
    case 2131558767:
    }
    this.mVPFragments.setCurrentItem(1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.acti_scene_two);
    initViewPager();
    findView();
    init();
    setListener();
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
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.scene.ActScene
 * JD-Core Version:    0.6.0
 */