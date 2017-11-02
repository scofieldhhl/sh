package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.indris.material.RippleView;

public class AtYkLightMode$$ViewBinder<T extends AtYkLightMode>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    View localView1 = (View)paramFinder.findRequiredView(paramObject, 2131558640, "field 'mBtnBack' and method 'back'");
    paramT.mBtnBack = ((Button)paramFinder.castView(localView1, 2131558640, "field 'mBtnBack'"));
    localView1.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.back();
      }
    });
    View localView2 = (View)paramFinder.findRequiredView(paramObject, 2131558638, "field 'btn_acti_timing_mode_mode' and method 'mode'");
    paramT.btn_acti_timing_mode_mode = ((RippleView)paramFinder.castView(localView2, 2131558638, "field 'btn_acti_timing_mode_mode'"));
    localView2.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.mode();
      }
    });
    View localView3 = (View)paramFinder.findRequiredView(paramObject, 2131558639, "field 'btn_acti_timing_mode_col' and method 'color'");
    paramT.btn_acti_timing_mode_col = ((RippleView)paramFinder.castView(localView3, 2131558639, "field 'btn_acti_timing_mode_col'"));
    localView3.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.color();
      }
    });
    View localView4 = (View)paramFinder.findRequiredView(paramObject, 2131558656, "field 'mSave' and method 'save'");
    paramT.mSave = ((TextView)paramFinder.castView(localView4, 2131558656, "field 'mSave'"));
    localView4.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.save();
      }
    });
    paramT.mVPFragments = ((NoScrollViewPager)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558641, "field 'mVPFragments'"), 2131558641, "field 'mVPFragments'"));
  }

  public void unbind(T paramT)
  {
    paramT.mBtnBack = null;
    paramT.btn_acti_timing_mode_mode = null;
    paramT.btn_acti_timing_mode_col = null;
    paramT.mSave = null;
    paramT.mVPFragments = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkLightMode..ViewBinder
 * JD-Core Version:    0.6.0
 */