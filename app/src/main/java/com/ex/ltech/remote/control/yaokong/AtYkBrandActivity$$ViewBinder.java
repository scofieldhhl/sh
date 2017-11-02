package com.ex.ltech.remote.control.yaokong;

import android.view.View;
import android.widget.RelativeLayout;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtYkBrandActivity$$ViewBinder<T extends AtYkBrandActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.rlSearch = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558756, "field 'rlSearch'"), 2131558756, "field 'rlSearch'"));
  }

  public void unbind(T paramT)
  {
    paramT.rlSearch = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtYkBrandActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */