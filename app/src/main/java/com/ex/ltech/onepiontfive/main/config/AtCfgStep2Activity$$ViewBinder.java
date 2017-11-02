package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtCfgStep2Activity$$ViewBinder<T extends AtCfgStep2Activity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivDeviceTop = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558807, "field 'ivDeviceTop'"), 2131558807, "field 'ivDeviceTop'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivDeviceTop = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStep2Activity..ViewBinder
 * JD-Core Version:    0.6.0
 */