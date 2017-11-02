package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtGeoFencing$$ViewBinder<T extends FtGeoFencing>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.rlHome = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559190, "field 'rlHome'"), 2131559190, "field 'rlHome'"));
    paramT.rlLeaveHome = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559194, "field 'rlLeaveHome'"), 2131559194, "field 'rlLeaveHome'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.rlHome = null;
    paramT.rlLeaveHome = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencing..ViewBinder
 * JD-Core Version:    0.6.0
 */