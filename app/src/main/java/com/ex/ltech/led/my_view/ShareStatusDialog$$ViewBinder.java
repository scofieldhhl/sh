package com.ex.ltech.led.my_view;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ShareStatusDialog$$ViewBinder<T extends ShareStatusDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.tvShareStatus = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559474, "field 'tvShareStatus'"), 2131559474, "field 'tvShareStatus'"));
  }

  public void unbind(T paramT)
  {
    paramT.tvShareStatus = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ShareStatusDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */