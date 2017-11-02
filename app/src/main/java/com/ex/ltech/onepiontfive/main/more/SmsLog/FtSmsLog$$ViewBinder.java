package com.ex.ltech.onepiontfive.main.more.SmsLog;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.indris.material.RippleView;

public class FtSmsLog$$ViewBinder<T extends FtSmsLog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.refreshView = ((PullToRefreshLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558584, "field 'refreshView'"), 2131558584, "field 'refreshView'"));
    paramT.swipeContent = ((SwipeMenuListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559207, "field 'swipeContent'"), 2131559207, "field 'swipeContent'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
  }

  public void unbind(T paramT)
  {
    paramT.refreshView = null;
    paramT.swipeContent = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.SmsLog.FtSmsLog..ViewBinder
 * JD-Core Version:    0.6.0
 */