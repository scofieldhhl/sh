package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtCfgStepFourActivity$$ViewBinder<T extends AtCfgStepFourActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivCfgOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558878, "field 'ivCfgOk'"), 2131558878, "field 'ivCfgOk'"));
    paramT.ivCfgNoOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558879, "field 'ivCfgNoOk'"), 2131558879, "field 'ivCfgNoOk'"));
    paramT.deviceConnet = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558877, "field 'deviceConnet'"), 2131558877, "field 'deviceConnet'"));
    paramT.info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558816, "field 'info'"), 2131558816, "field 'info'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivCfgOk = null;
    paramT.ivCfgNoOk = null;
    paramT.deviceConnet = null;
    paramT.info = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStepFourActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */