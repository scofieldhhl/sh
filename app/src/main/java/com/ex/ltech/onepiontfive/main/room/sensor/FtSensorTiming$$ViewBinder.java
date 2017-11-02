package com.ex.ltech.onepiontfive.main.room.sensor;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.indris.material.RippleView;

public class FtSensorTiming$$ViewBinder<T extends FtSensorTiming>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.hourPiker = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559455, "field 'hourPiker'"), 2131559455, "field 'hourPiker'"));
    paramT.minPiker = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558903, "field 'minPiker'"), 2131558903, "field 'minPiker'"));
    paramT.openTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559471, "field 'openTime'"), 2131559471, "field 'openTime'"));
    paramT.rlActOutletAddTiming1 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558590, "field 'rlActOutletAddTiming1'"), 2131558590, "field 'rlActOutletAddTiming1'"));
    paramT.offTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559472, "field 'offTime'"), 2131559472, "field 'offTime'"));
    paramT.rlActOutletAddTiming2 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558596, "field 'rlActOutletAddTiming2'"), 2131558596, "field 'rlActOutletAddTiming2'"));
    paramT.rlActOutletAddTiming3 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558600, "field 'rlActOutletAddTiming3'"), 2131558600, "field 'rlActOutletAddTiming3'"));
    paramT.every_day = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559473, "field 'every_day'"), 2131559473, "field 'every_day'"));
    paramT.ivActOutletAddTimingGo1 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558594, "field 'ivActOutletAddTimingGo1'"), 2131558594, "field 'ivActOutletAddTimingGo1'"));
    paramT.ivActOutletAddTimingGo2 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558598, "field 'ivActOutletAddTimingGo2'"), 2131558598, "field 'ivActOutletAddTimingGo2'"));
    paramT.ivActOutletAddTimingGo3 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558602, "field 'ivActOutletAddTimingGo3'"), 2131558602, "field 'ivActOutletAddTimingGo3'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.hourPiker = null;
    paramT.minPiker = null;
    paramT.openTime = null;
    paramT.rlActOutletAddTiming1 = null;
    paramT.offTime = null;
    paramT.rlActOutletAddTiming2 = null;
    paramT.rlActOutletAddTiming3 = null;
    paramT.every_day = null;
    paramT.ivActOutletAddTimingGo1 = null;
    paramT.ivActOutletAddTimingGo2 = null;
    paramT.ivActOutletAddTimingGo3 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensorTiming..ViewBinder
 * JD-Core Version:    0.6.0
 */