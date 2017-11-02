package com.ex.ltech.remote.control.atYaoKongs.fragment;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class TvFragment$$ViewBinder<T extends TvFragment>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.quiet = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559113, "field 'quiet'"), 2131559113, "field 'quiet'"));
    paramT.res = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559114, "field 'res'"), 2131559114, "field 'res'"));
    paramT.centerMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559115, "field 'centerMenu'"), 2131559115, "field 'centerMenu'"));
    paramT.menu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558970, "field 'menu'"), 2131558970, "field 'menu'"));
    paramT.on = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558874, "field 'on'"), 2131558874, "field 'on'"));
    paramT.voladd = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558974, "field 'voladd'"), 2131558974, "field 'voladd'"));
    paramT.vol = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558988, "field 'vol'"), 2131558988, "field 'vol'"));
    paramT.volsub = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558975, "field 'volsub'"), 2131558975, "field 'volsub'"));
    paramT.back = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558858, "field 'back'"), 2131558858, "field 'back'"));
    paramT.chAdd = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559116, "field 'chAdd'"), 2131559116, "field 'chAdd'"));
    paramT.tvZoom = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558991, "field 'tvZoom'"), 2131558991, "field 'tvZoom'"));
    paramT.chSub = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559117, "field 'chSub'"), 2131559117, "field 'chSub'"));
  }

  public void unbind(T paramT)
  {
    paramT.quiet = null;
    paramT.res = null;
    paramT.centerMenu = null;
    paramT.menu = null;
    paramT.on = null;
    paramT.voladd = null;
    paramT.vol = null;
    paramT.volsub = null;
    paramT.back = null;
    paramT.chAdd = null;
    paramT.tvZoom = null;
    paramT.chSub = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.fragment.TvFragment..ViewBinder
 * JD-Core Version:    0.6.0
 */