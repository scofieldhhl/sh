package com.ex.ltech.remote.control.atYaoKongs;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtNewTvActivity$$ViewBinder<T extends AtNewTvActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ok = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558968, "field 'ok'"), 2131558968, "field 'ok'"));
    paramT.up = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558410, "field 'up'"), 2131558410, "field 'up'"));
    paramT.left = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558458, "field 'left'"), 2131558458, "field 'left'"));
    paramT.right = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558459, "field 'right'"), 2131558459, "field 'right'"));
    paramT.down = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558969, "field 'down'"), 2131558969, "field 'down'"));
  }

  public void unbind(T paramT)
  {
    paramT.ok = null;
    paramT.up = null;
    paramT.left = null;
    paramT.right = null;
    paramT.down = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */