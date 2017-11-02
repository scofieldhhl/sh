package com.ex.ltech.led;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.ex.ltech.onepiontfive.main.widget.EasySlidingTabs;

public class FtTest$$ViewBinder<T extends FtTest>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.easySlidingTabs = ((EasySlidingTabs)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559256, "field 'easySlidingTabs'"), 2131559256, "field 'easySlidingTabs'"));
    paramT.easyVp = ((NoScrollViewPager)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559258, "field 'easyVp'"), 2131559258, "field 'easyVp'"));
  }

  public void unbind(T paramT)
  {
    paramT.easySlidingTabs = null;
    paramT.easyVp = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.FtTest..ViewBinder
 * JD-Core Version:    0.6.0
 */