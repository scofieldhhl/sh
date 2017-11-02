package com.ex.ltech.hongwai.yaokong.fragment;

import android.view.View;
import android.widget.ListView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtYkLightModeFragment$$ViewBinder<T extends AtYkLightModeFragment>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.lv_f_timing_mode = ((ListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559138, "field 'lv_f_timing_mode'"), 2131559138, "field 'lv_f_timing_mode'"));
  }

  public void unbind(T paramT)
  {
    paramT.lv_f_timing_mode = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.fragment.AtYkLightModeFragment..ViewBinder
 * JD-Core Version:    0.6.0
 */