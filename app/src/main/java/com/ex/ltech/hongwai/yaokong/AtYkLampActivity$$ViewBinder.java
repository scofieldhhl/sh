package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.zhy.android.percent.support.PercentRelativeLayout;

public class AtYkLampActivity$$ViewBinder<T extends AtYkLampActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mSimpleColorPickerView = ((SimpleColorPickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558646, "field 'mSimpleColorPickerView'"), 2131558646, "field 'mSimpleColorPickerView'"));
    paramT.mGrayLayer = ((PercentRelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558654, "field 'mGrayLayer'"), 2131558654, "field 'mGrayLayer'"));
    paramT.sb = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558652, "field 'sb'"), 2131558652, "field 'sb'"));
    paramT.tvBrtPrecent = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558653, "field 'tvBrtPrecent'"), 2131558653, "field 'tvBrtPrecent'"));
    paramT.layoutOption = ((PercentRelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558647, "field 'layoutOption'"), 2131558647, "field 'layoutOption'"));
    paramT.rlBrt = ((LinearLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558651, "field 'rlBrt'"), 2131558651, "field 'rlBrt'"));
  }

  public void unbind(T paramT)
  {
    paramT.mSimpleColorPickerView = null;
    paramT.mGrayLayer = null;
    paramT.sb = null;
    paramT.tvBrtPrecent = null;
    paramT.layoutOption = null;
    paramT.rlBrt = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkLampActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */