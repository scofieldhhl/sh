package com.ex.ltech.onepiontfive.main.backupScene;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.indris.material.RippleView;

public class FtScene$$ViewBinder<T extends FtScene>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewMenu'"), 2131558784, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.ivNoListIc = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559261, "field 'ivNoListIc'"), 2131559261, "field 'ivNoListIc'"));
    paramT.tvNoListIc = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559262, "field 'tvNoListIc'"), 2131559262, "field 'tvNoListIc'"));
    paramT.lv = ((SwipeMenuListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558961, "field 'lv'"), 2131558961, "field 'lv'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.ivNoListIc = null;
    paramT.tvNoListIc = null;
    paramT.lv = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.backupScene.FtScene..ViewBinder
 * JD-Core Version:    0.6.0
 */