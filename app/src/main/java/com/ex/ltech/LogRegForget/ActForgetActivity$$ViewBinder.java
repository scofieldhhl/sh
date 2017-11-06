package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.led.R;

import butterknife.ButterKnife;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActForgetActivity$$ViewBinder<T extends ActForgetActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etArea = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_area, "field 'etArea'"), R.id.et_area, "field 'etArea'"));
    paramT.tvCountryCode = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.tv_country_code, "field 'tvCountryCode'"), R.id.tv_country_code, "field 'tvCountryCode'"));
    paramT.etActLogPhone = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_act_log_phone, "field 'etActLogPhone'"), R.id.et_act_log_phone, "field 'etActLogPhone'"));
    paramT.etVerfyCode = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.verfy_code, "field 'etVerfyCode'"), R.id.verfy_code, "field 'etVerfyCode'"));
    paramT.verifyTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.verify_time, "field 'verifyTime'"), R.id.verify_time, "field 'verifyTime'"));
    paramT.rlVerfy = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.rl_verfy, "field 'rlVerfy'"), R.id.rl_verfy, "field 'rlVerfy'"));
    paramT.etPsd1 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_psd_1, "field 'etPsd1'"), R.id.et_psd_1, "field 'etPsd1'"));
    paramT.etPsd2 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.et_psd_2, "field 'etPsd2'"), R.id.et_psd_2, "field 'etPsd2'"));
    paramT.rlPsd = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.rl_psd, "field 'rlPsd'"), R.id.rl_psd, "field 'rlPsd'"));
  }

  public void unbind(T paramT)
  {
    paramT.etArea = null;
    paramT.tvCountryCode = null;
    paramT.etActLogPhone = null;
    paramT.etVerfyCode = null;
    paramT.verifyTime = null;
    paramT.rlVerfy = null;
    paramT.etPsd1 = null;
    paramT.etPsd2 = null;
    paramT.rlPsd = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActForgetActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */