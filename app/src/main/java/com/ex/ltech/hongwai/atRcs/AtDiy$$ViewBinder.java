package com.ex.ltech.hongwai.atRcs;

import android.view.View;
import android.widget.GridView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtDiy$$ViewBinder<T extends AtDiy>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.tips = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558934, "field 'tips'"), 2131558934, "field 'tips'"));
    paramT.gvDiy = ((GridView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558819, "field 'gvDiy'"), 2131558819, "field 'gvDiy'"));
  }

  public void unbind(T paramT)
  {
    paramT.tips = null;
    paramT.gvDiy = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtDiy..ViewBinder
 * JD-Core Version:    0.6.0
 */