package com.ex.ltech.onepiontfive.main.time;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.indris.material.RippleView;

public class FtAddTime$$ViewBinder<T extends FtAddTime>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tpActAddTimingHour = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558504, "field 'tpActAddTimingHour'"), 2131558504, "field 'tpActAddTimingHour'"));
    paramT.tpActAddTimingMin = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558505, "field 'tpActAddTimingMin'"), 2131558505, "field 'tpActAddTimingMin'"));
    paramT.tvActAddTimingOn = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558507, "field 'tvActAddTimingOn'"), 2131558507, "field 'tvActAddTimingOn'"));
    paramT.tvActAddTimingOff = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558508, "field 'tvActAddTimingOff'"), 2131558508, "field 'tvActAddTimingOff'"));
    paramT.tvActAddTimingModeStatus = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558515, "field 'tvActAddTimingModeStatus'"), 2131558515, "field 'tvActAddTimingModeStatus'"));
    paramT.tvActAddTimingRepeatStatus = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558520, "field 'tvActAddTimingRepeatStatus'"), 2131558520, "field 'tvActAddTimingRepeatStatus'"));
    paramT.tvActAddTimingMode = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558512, "field 'tvActAddTimingMode'"), 2131558512, "field 'tvActAddTimingMode'"));
    paramT.rlSeletedDevice = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558510, "field 'rlSeletedDevice'"), 2131558510, "field 'rlSeletedDevice'"));
    paramT.tvActAddTimingRepeat = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558518, "field 'tvActAddTimingRepeat'"), 2131558518, "field 'tvActAddTimingRepeat'"));
    paramT.ivActAddTimingGo2 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558519, "field 'ivActAddTimingGo2'"), 2131558519, "field 'ivActAddTimingGo2'"));
    paramT.rlSeletedDays = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558516, "field 'rlSeletedDays'"), 2131558516, "field 'rlSeletedDays'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tpActAddTimingHour = null;
    paramT.tpActAddTimingMin = null;
    paramT.tvActAddTimingOn = null;
    paramT.tvActAddTimingOff = null;
    paramT.tvActAddTimingModeStatus = null;
    paramT.tvActAddTimingRepeatStatus = null;
    paramT.tvActAddTimingMode = null;
    paramT.rlSeletedDevice = null;
    paramT.tvActAddTimingRepeat = null;
    paramT.ivActAddTimingGo2 = null;
    paramT.rlSeletedDays = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtAddTime..ViewBinder
 * JD-Core Version:    0.6.0
 */