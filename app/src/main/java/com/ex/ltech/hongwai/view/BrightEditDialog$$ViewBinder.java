package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.SeekBar;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class BrightEditDialog$$ViewBinder<T extends BrightEditDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mSeekBar = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558652, "field 'mSeekBar'"), 2131558652, "field 'mSeekBar'"));
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
    paramT.mSeekBar = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.BrightEditDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */