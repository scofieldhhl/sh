package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtGeoFencingHome$$ViewBinder<T extends FtGeoFencingHome>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvSenceHome = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559201, "field 'tvSenceHome'"), 2131559201, "field 'tvSenceHome'"));
    paramT.tvRepeat = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559203, "field 'tvRepeat'"), 2131559203, "field 'tvRepeat'"));
    paramT.rlRepeat = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558546, "field 'rlRepeat'"), 2131558546, "field 'rlRepeat'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvSenceHome = null;
    paramT.tvRepeat = null;
    paramT.rlRepeat = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencingHome..ViewBinder
 * JD-Core Version:    0.6.0
 */