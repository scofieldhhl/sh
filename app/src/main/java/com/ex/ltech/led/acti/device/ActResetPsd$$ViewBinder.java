package com.ex.ltech.led.acti.device;

import android.view.View;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActResetPsd$$ViewBinder<T extends ActResetPsd>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etActReset2Psd1 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558622, "field 'etActReset2Psd1'"), 2131558622, "field 'etActReset2Psd1'"));
    paramT.etActReset2Psd2 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558623, "field 'etActReset2Psd2'"), 2131558623, "field 'etActReset2Psd2'"));
    paramT.etActReset2Psd3 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558624, "field 'etActReset2Psd3'"), 2131558624, "field 'etActReset2Psd3'"));
  }

  public void unbind(T paramT)
  {
    paramT.etActReset2Psd1 = null;
    paramT.etActReset2Psd2 = null;
    paramT.etActReset2Psd3 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ActResetPsd..ViewBinder
 * JD-Core Version:    0.6.0
 */