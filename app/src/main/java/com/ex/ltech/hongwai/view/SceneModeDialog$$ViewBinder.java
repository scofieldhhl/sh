package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.GridView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class SceneModeDialog$$ViewBinder<T extends SceneModeDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mGridViewScene = ((GridView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559098, "field 'mGridViewScene'"), 2131559098, "field 'mGridViewScene'"));
    ((View)paramFinder.findRequiredView(paramObject, 2131559094, "method 'cancel'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.cancel();
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mGridViewScene = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SceneModeDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */