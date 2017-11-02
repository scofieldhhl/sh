package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FtNetConfig$$ViewBinder<T extends FtNetConfig>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.etActiNetConfigWifiName = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558732, "field 'etActiNetConfigWifiName'"), 2131558732, "field 'etActiNetConfigWifiName'"));
    paramT.tvActiNetConfigPwd = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558733, "field 'tvActiNetConfigPwd'"), 2131558733, "field 'tvActiNetConfigPwd'"));
    paramT.ivActiNetConfigGo = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559209, "field 'ivActiNetConfigGo'"), 2131559209, "field 'ivActiNetConfigGo'"));
    paramT.tvActiNetConfigDeviceStatus = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559208, "field 'tvActiNetConfigDeviceStatus'"), 2131559208, "field 'tvActiNetConfigDeviceStatus'"));
    paramT.btnActiNetConfigDeviceConnet = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558736, "field 'btnActiNetConfigDeviceConnet'"), 2131558736, "field 'btnActiNetConfigDeviceConnet'"));
    paramT.tvActiNetConfigLoading = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559210, "field 'tvActiNetConfigLoading'"), 2131559210, "field 'tvActiNetConfigLoading'"));
    paramT.pbActiNetConfigLoading = ((ProgressBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559211, "field 'pbActiNetConfigLoading'"), 2131559211, "field 'pbActiNetConfigLoading'"));
  }

  public void unbind(T paramT)
  {
    paramT.etActiNetConfigWifiName = null;
    paramT.tvActiNetConfigPwd = null;
    paramT.ivActiNetConfigGo = null;
    paramT.tvActiNetConfigDeviceStatus = null;
    paramT.btnActiNetConfigDeviceConnet = null;
    paramT.tvActiNetConfigLoading = null;
    paramT.pbActiNetConfigLoading = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtNetConfig..ViewBinder
 * JD-Core Version:    0.6.0
 */