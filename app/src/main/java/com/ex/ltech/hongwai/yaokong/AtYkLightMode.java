package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ex.ltech.hongwai.vo.CtTimingAction;
import com.ex.ltech.hongwai.yaokong.fragment.AtYkLightColorFragment;
import com.ex.ltech.hongwai.yaokong.fragment.AtYkLightModeFragment;
import com.ex.ltech.led.fragment.FragmentPageAdapter;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class AtYkLightMode extends FragmentActivity
  implements ViewPager.OnPageChangeListener
{

  @Bind({2131558639})
  RippleView btn_acti_timing_mode_col;

  @Bind({2131558638})
  RippleView btn_acti_timing_mode_mode;
  private AtYkLightColorFragment colorFragment;
  public CtTimingAction ctTimingAction = new CtTimingAction();

  @Bind({2131558640})
  Button mBtnBack;
  private List<Fragment> mFragments = new ArrayList();

  @Bind({2131558656})
  TextView mSave;

  @Bind({2131558641})
  NoScrollViewPager mVPFragments;
  private AtYkLightModeFragment modeFragment;
  private int pagerCurrent = 0;

  private void initViewPager()
  {
    this.mVPFragments.setOnPageChangeListener(this);
    this.mVPFragments.setOffscreenPageLimit(3);
    this.modeFragment = new AtYkLightModeFragment();
    this.colorFragment = new AtYkLightColorFragment();
    this.mFragments.add(this.modeFragment);
    this.mFragments.add(this.colorFragment);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
    this.mVPFragments.setNoScroll(true);
  }

  private void pagerChange(int paramInt)
  {
    if (paramInt == this.pagerCurrent)
      return;
    if (paramInt == 0)
    {
      this.mSave.setText(getResources().getString(2131100416));
      this.btn_acti_timing_mode_mode.setBackgroundResource(2130903299);
      this.btn_acti_timing_mode_col.setBackgroundResource(2130903296);
    }
    if (paramInt == 1)
    {
      this.mSave.setText(getResources().getString(2131100358));
      this.btn_acti_timing_mode_mode.setBackgroundResource(2130903298);
      this.btn_acti_timing_mode_col.setBackgroundResource(2130903297);
    }
    this.pagerCurrent = paramInt;
  }

  @OnClick({2131558640})
  public void back()
  {
    finish();
  }

  @OnClick({2131558639})
  public void color()
  {
    this.mVPFragments.setCurrentItem(1);
    CtTimingAction localCtTimingAction = this.ctTimingAction;
    localCtTimingAction.seletedType = 2;
  }

  @OnClick({2131558638})
  public void mode()
  {
    this.mVPFragments.setCurrentItem(0);
    CtTimingAction localCtTimingAction = this.ctTimingAction;
    localCtTimingAction.seletedType = 1;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968628);
    ButterKnife.bind(this);
    initViewPager();
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

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.colorFragment.setPactHeight(findViewById(2131558636).getHeight());
  }

  @OnClick({2131558656})
  public void save()
  {
    Intent localIntent = new Intent();
    localIntent.putExtra(CtTimingAction.class.getName(), this.ctTimingAction);
    setResult(230000, localIntent);
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkLightMode
 * JD-Core Version:    0.6.0
 */