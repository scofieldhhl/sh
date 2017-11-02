package com.ex.ltech.remote.control.atYaoKongs;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class AtFanActivity$$ViewBinder<T extends AtFanActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.power = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558822, "field 'power'"), 2131558822, "field 'power'"));
    paramT.hig = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558978, "field 'hig'"), 2131558978, "field 'hig'"));
    paramT.mid = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558977, "field 'mid'"), 2131558977, "field 'mid'"));
    paramT.low = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558979, "field 'low'"), 2131558979, "field 'low'"));
    paramT.fanSpeed = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558983, "field 'fanSpeed'"), 2131558983, "field 'fanSpeed'"));
    paramT.fanType = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558982, "field 'fanType'"), 2131558982, "field 'fanType'"));
    paramT.time = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558981, "field 'time'"), 2131558981, "field 'time'"));
    paramT.shake = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558984, "field 'shake'"), 2131558984, "field 'shake'"));
    paramT.queit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558976, "field 'queit'"), 2131558976, "field 'queit'"));
  }

  public void unbind(T paramT)
  {
    paramT.power = null;
    paramT.hig = null;
    paramT.mid = null;
    paramT.low = null;
    paramT.fanSpeed = null;
    paramT.fanType = null;
    paramT.time = null;
    paramT.shake = null;
    paramT.queit = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtFanActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */