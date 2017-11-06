package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActLoginActivity$$ViewBinder<T extends ActLoginActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etActLogPhone = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_act_log_phone, "field 'etActLogPhone'"), R.id.et_act_log_phone, "field 'etActLogPhone'"));
    paramT.etActLogPsd = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558931, "field 'etActLogPsd'"), 2131558931, "field 'etActLogPsd'"));
    paramT.tvActLogForget = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558932, "field 'tvActLogForget'"), 2131558932, "field 'tvActLogForget'"));
    paramT.etArea = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_area, "field 'etArea'"), R.id.et_area, "field 'etArea'"));
    paramT.tvCountryCode = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.tv_country_code, "field 'tvCountryCode'"), R.id.tv_country_code, "field 'tvCountryCode'"));
  }

  public void unbind(T paramT)
  {
    paramT.etActLogPhone = null;
    paramT.etActLogPsd = null;
    paramT.tvActLogForget = null;
    paramT.etArea = null;
    paramT.tvCountryCode = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActLoginActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */