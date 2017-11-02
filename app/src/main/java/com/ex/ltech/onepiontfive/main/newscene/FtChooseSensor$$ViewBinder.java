package com.ex.ltech.onepiontfive.main.newscene;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class FtChooseSensor$$ViewBinder<T extends FtChooseSensor>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.lv = ((ListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558961, "field 'lv'"), 2131558961, "field 'lv'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.lv = null;
    paramT.tvTitleViewEdit = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtChooseSensor..ViewBinder
 * JD-Core Version:    0.6.0
 */