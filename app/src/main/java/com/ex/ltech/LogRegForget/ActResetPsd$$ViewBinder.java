package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.EditText;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActResetPsd$$ViewBinder<T extends ActResetPsd>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etActResetPsd1 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558619, "field 'etActResetPsd1'"), 2131558619, "field 'etActResetPsd1'"));
    paramT.etActResetPsd2 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558620, "field 'etActResetPsd2'"), 2131558620, "field 'etActResetPsd2'"));
  }

  public void unbind(T paramT)
  {
    paramT.etActResetPsd1 = null;
    paramT.etActResetPsd2 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActResetPsd..ViewBinder
 * JD-Core Version:    0.6.0
 */