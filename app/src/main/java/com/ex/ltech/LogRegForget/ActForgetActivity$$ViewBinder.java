package com.ex.ltech.LogRegForget;

import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class ActForgetActivity$$ViewBinder<T extends ActForgetActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etArea = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558909, "field 'etArea'"), 2131558909, "field 'etArea'"));
    paramT.tvCountryCode = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558910, "field 'tvCountryCode'"), 2131558910, "field 'tvCountryCode'"));
    paramT.etActLogPhone = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558911, "field 'etActLogPhone'"), 2131558911, "field 'etActLogPhone'"));
    paramT.etVerfyCode = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558913, "field 'etVerfyCode'"), 2131558913, "field 'etVerfyCode'"));
    paramT.verifyTime = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558914, "field 'verifyTime'"), 2131558914, "field 'verifyTime'"));
    paramT.rlVerfy = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558912, "field 'rlVerfy'"), 2131558912, "field 'rlVerfy'"));
    paramT.etPsd1 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558916, "field 'etPsd1'"), 2131558916, "field 'etPsd1'"));
    paramT.etPsd2 = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558917, "field 'etPsd2'"), 2131558917, "field 'etPsd2'"));
    paramT.rlPsd = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558915, "field 'rlPsd'"), 2131558915, "field 'rlPsd'"));
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