package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MyTimePickerView;

public class AtYkGradientTime$$ViewBinder<T extends AtYkGradientTime>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.myTimePickerView = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558530, "field 'myTimePickerView'"), 2131558530, "field 'myTimePickerView'"));
  }

  public void unbind(T paramT)
  {
    paramT.myTimePickerView = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkGradientTime..ViewBinder
 * JD-Core Version:    0.6.0
 */