package com.ex.ltech.remote.control.atYaoKongs;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class AtAirConActivity$$ViewBinder<T extends AtAirConActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.sub = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558829, "field 'sub'"), 2131558829, "field 'sub'"));
    paramT.add = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558831, "field 'add'"), 2131558831, "field 'add'"));
    paramT.mode = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558835, "field 'mode'"), 2131558835, "field 'mode'"));
    paramT.upDown = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558836, "field 'upDown'"), 2131558836, "field 'upDown'"));
    paramT.leftRight = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558837, "field 'leftRight'"), 2131558837, "field 'leftRight'"));
    paramT.speed = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558838, "field 'speed'"), 2131558838, "field 'speed'"));
    paramT.power = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558822, "field 'power'"), 2131558822, "field 'power'"));
  }

  public void unbind(T paramT)
  {
    paramT.sub = null;
    paramT.add = null;
    paramT.mode = null;
    paramT.upDown = null;
    paramT.leftRight = null;
    paramT.speed = null;
    paramT.power = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtAirConActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */