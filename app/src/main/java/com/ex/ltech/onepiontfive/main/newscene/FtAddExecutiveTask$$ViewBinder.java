package com.ex.ltech.onepiontfive.main.newscene;

import android.view.View;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtAddExecutiveTask$$ViewBinder<T extends FtAddExecutiveTask>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'btnTitleViewEdit'"), 2131558785, "field 'btnTitleViewEdit'"));
    paramT.expendlist = ((ExpandableListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559163, "field 'expendlist'"), 2131559163, "field 'expendlist'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.expendlist = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtAddExecutiveTask..ViewBinder
 * JD-Core Version:    0.6.0
 */