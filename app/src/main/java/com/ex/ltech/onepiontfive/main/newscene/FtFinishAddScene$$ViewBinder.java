package com.ex.ltech.onepiontfive.main.newscene;

import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.indris.material.RippleView;

public class FtFinishAddScene$$ViewBinder<T extends FtFinishAddScene>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.condition = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559111, "field 'condition'"), 2131559111, "field 'condition'"));
    paramT.etName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559157, "field 'etName'"), 2131559157, "field 'etName'"));
    paramT.tvSo = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559161, "field 'tvSo'"), 2131559161, "field 'tvSo'"));
    paramT.listview = ((ListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559158, "field 'listview'"), 2131559158, "field 'listview'"));
    paramT.conditionIc = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559149, "field 'conditionIc'"), 2131559149, "field 'conditionIc'"));
    paramT.go3 = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559160, "field 'go3'"), 2131559160, "field 'go3'"));
    paramT.rl1 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559145, "field 'rl1'"), 2131559145, "field 'rl1'"));
    paramT.rl2 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559148, "field 'rl2'"), 2131559148, "field 'rl2'"));
    paramT.rl3 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559159, "field 'rl3'"), 2131559159, "field 'rl3'"));
    paramT.tpSecond = ((MyTimePickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559058, "field 'tpSecond'"), 2131559058, "field 'tpSecond'"));
    paramT.rlTime = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559162, "field 'rlTime'"), 2131559162, "field 'rlTime'"));
    paramT.delName = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559147, "field 'delName'"), 2131559147, "field 'delName'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.condition = null;
    paramT.etName = null;
    paramT.tvSo = null;
    paramT.listview = null;
    paramT.conditionIc = null;
    paramT.go3 = null;
    paramT.rl1 = null;
    paramT.rl2 = null;
    paramT.rl3 = null;
    paramT.tpSecond = null;
    paramT.rlTime = null;
    paramT.delName = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtFinishAddScene..ViewBinder
 * JD-Core Version:    0.6.0
 */