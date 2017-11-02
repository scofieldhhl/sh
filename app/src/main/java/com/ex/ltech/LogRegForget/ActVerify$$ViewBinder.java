package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.ImageView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActVerify$$ViewBinder<T extends ActVerify>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivActVerifyOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558645, "field 'ivActVerifyOk'"), 2131558645, "field 'ivActVerifyOk'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivActVerifyOk = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActVerify..ViewBinder
 * JD-Core Version:    0.6.0
 */