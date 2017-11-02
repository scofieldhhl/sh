package com.ex.ltech.led.my_view;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class MyAlertDialog17$$ViewBinder<T extends MyAlertDialog17>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    ((View)paramFinder.findRequiredView(paramObject, 2131558964, "method 'on'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.on();
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559409, "method 'off'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.off();
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559410, "method 'effect'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.effect();
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559028, "method 'cancel'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.cancel();
      }
    });
  }

  public void unbind(T paramT)
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog17..ViewBinder
 * JD-Core Version:    0.6.0
 */