package com.ex.ltech.onepiontfive.main.room.sensor;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtSensorInfo$$ViewBinder<T extends FtSensorInfo>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.device = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559270, "field 'device'"), 2131559270, "field 'device'"));
    paramT.sence = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559271, "field 'sence'"), 2131559271, "field 'sence'"));
    paramT.llRippleView = ((LinearLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559269, "field 'llRippleView'"), 2131559269, "field 'llRippleView'"));
    paramT.btnActiModeMenu = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.btn_acti_mode_menu, "field 'btnActiModeMenu'"), R.id.btn_acti_mode_menu, "field 'btnActiModeMenu'"));
    paramT.pager = ((ViewPager)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558769, "field 'pager'"), 2131558769, "field 'pager'"));
    paramT.rlActTimingMode = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.rl_act_timing_mode, "field 'rlActTimingMode'"), R.id.rl_act_timing_mode, "field 'rlActTimingMode'"));
  }

  public void unbind(T paramT)
  {
    paramT.device = null;
    paramT.sence = null;
    paramT.llRippleView = null;
    paramT.btnActiModeMenu = null;
    paramT.pager = null;
    paramT.rlActTimingMode = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensorInfo..ViewBinder
 * JD-Core Version:    0.6.0
 */