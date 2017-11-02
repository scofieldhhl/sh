package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActRegisterActivity$$ViewBinder<T extends ActRegisterActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.rl_area = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559022, "field 'rl_area'"), 2131559022, "field 'rl_area'"));
    paramT.rl_phone = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559023, "field 'rl_phone'"), 2131559023, "field 'rl_phone'"));
    paramT.ivStep = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559021, "field 'ivStep'"), 2131559021, "field 'ivStep'"));
    paramT.etArea = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558909, "field 'etArea'"), 2131558909, "field 'etArea'"));
    paramT.tvCountryCode = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558910, "field 'tvCountryCode'"), 2131558910, "field 'tvCountryCode'"));
    paramT.etVerfyCode = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558913, "field 'etVerfyCode'"), 2131558913, "field 'etVerfyCode'"));
    paramT.verifyTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558914, "field 'verifyTime'"), 2131558914, "field 'verifyTime'"));
    paramT.rlVerfy = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558912, "field 'rlVerfy'"), 2131558912, "field 'rlVerfy'"));
    paramT.etPhone = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559024, "field 'etPhone'"), 2131559024, "field 'etPhone'"));
    paramT.etActLogPsd = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558931, "field 'etActLogPsd'"), 2131558931, "field 'etActLogPsd'"));
    paramT.rl_psd = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558915, "field 'rl_psd'"), 2131558915, "field 'rl_psd'"));
    paramT.tvVerfyCodeSend = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559025, "field 'tvVerfyCodeSend'"), 2131559025, "field 'tvVerfyCodeSend'"));
    paramT.btActRegSeleted = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559027, "field 'btActRegSeleted'"), 2131559027, "field 'btActRegSeleted'"));
    paramT.rlActRegSeleted = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559026, "field 'rlActRegSeleted'"), 2131559026, "field 'rlActRegSeleted'"));
  }

  public void unbind(T paramT)
  {
    paramT.rl_area = null;
    paramT.rl_phone = null;
    paramT.ivStep = null;
    paramT.etArea = null;
    paramT.tvCountryCode = null;
    paramT.etVerfyCode = null;
    paramT.verifyTime = null;
    paramT.rlVerfy = null;
    paramT.etPhone = null;
    paramT.etActLogPsd = null;
    paramT.rl_psd = null;
    paramT.tvVerfyCodeSend = null;
    paramT.btActRegSeleted = null;
    paramT.rlActRegSeleted = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActRegisterActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */