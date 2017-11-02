package com.ex.ltech.onepiontfive.main.newscene;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtAddScene$$ViewBinder<T extends FtAddScene>
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
    paramT.go1 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559147, "field 'go1'"), 2131559147, "field 'go1'"));
    paramT.condition = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559111, "field 'condition'"), 2131559111, "field 'condition'"));
    paramT.rl1 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559145, "field 'rl1'"), 2131559145, "field 'rl1'"));
    paramT.go2 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559150, "field 'go2'"), 2131559150, "field 'go2'"));
    paramT.result = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559151, "field 'result'"), 2131559151, "field 'result'"));
    paramT.rl2 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559148, "field 'rl2'"), 2131559148, "field 'rl2'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
    paramT.rlTitle = null;
    paramT.go1 = null;
    paramT.condition = null;
    paramT.rl1 = null;
    paramT.go2 = null;
    paramT.result = null;
    paramT.rl2 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtAddScene..ViewBinder
 * JD-Core Version:    0.6.0
 */