package com.ex.ltech.onepiontfive.main.room.sensor;

import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtSeletedPanel$$ViewBinder<T extends FtSeletedPanel>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
    paramT.rlTitle = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558993, "field 'rlTitle'"), 2131558993, "field 'rlTitle'"));
    paramT.delayList = ((ListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559265, "field 'delayList'"), 2131559265, "field 'delayList'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
    paramT.rlTitle = null;
    paramT.delayList = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSeletedPanel..ViewBinder
 * JD-Core Version:    0.6.0
 */