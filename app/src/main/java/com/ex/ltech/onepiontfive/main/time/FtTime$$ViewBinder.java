package com.ex.ltech.onepiontfive.main.time;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.indris.material.RippleView;

public class FtTime$$ViewBinder<T extends FtTime>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.lvActTiming = ((SwipeMenuListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.lv_act_timing, "field 'lvActTiming'"), R.id.lv_act_timing, "field 'lvActTiming'"));
    paramT.refreshView = ((PullToRefreshLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558584, "field 'refreshView'"), 2131558584, "field 'refreshView'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.lvActTiming = null;
    paramT.refreshView = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtTime..ViewBinder
 * JD-Core Version:    0.6.0
 */