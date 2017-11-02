package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.LogRegForget.demo.CountryListView;

public class AtRegAreaActivity$$ViewBinder<T extends AtRegAreaActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etSearch = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558757, "field 'etSearch'"), 2131558757, "field 'etSearch'"));
    paramT.rlSearch = ((LinearLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558756, "field 'rlSearch'"), 2131558756, "field 'rlSearch'"));
    paramT.lvAtYkType = ((CountryListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559029, "field 'lvAtYkType'"), 2131559029, "field 'lvAtYkType'"));
    paramT.tv_cancel = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559028, "field 'tv_cancel'"), 2131559028, "field 'tv_cancel'"));
  }

  public void unbind(T paramT)
  {
    paramT.etSearch = null;
    paramT.rlSearch = null;
    paramT.lvAtYkType = null;
    paramT.tv_cancel = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.AtRegAreaActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */