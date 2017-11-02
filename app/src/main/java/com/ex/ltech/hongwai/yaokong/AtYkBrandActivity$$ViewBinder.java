package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtYkBrandActivity$$ViewBinder<T extends AtYkBrandActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.rl_search = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558756, "field 'rl_search'"), 2131558756, "field 'rl_search'"));
    paramT.location = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559061, "field 'location'"), 2131559061, "field 'location'"));
  }

  public void unbind(T paramT)
  {
    paramT.rl_search = null;
    paramT.location = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkBrandActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */