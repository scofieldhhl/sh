package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtCfgStepTwoActivity$$ViewBinder<T extends AtCfgStepTwoActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.icCenter = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558814, "field 'icCenter'"), 2131558814, "field 'icCenter'"));
    paramT.icMatchParent = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558815, "field 'icMatchParent'"), 2131558815, "field 'icMatchParent'"));
    paramT.info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558816, "field 'info'"), 2131558816, "field 'info'"));
    paramT.next = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558795, "field 'next'"), 2131558795, "field 'next'"));
    paramT.tvLampBlinkSeleted = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558817, "field 'tvLampBlinkSeleted'"), 2131558817, "field 'tvLampBlinkSeleted'"));
    paramT.tvLampBlink = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558818, "field 'tvLampBlink'"), 2131558818, "field 'tvLampBlink'"));
  }

  public void unbind(T paramT)
  {
    paramT.icCenter = null;
    paramT.icMatchParent = null;
    paramT.info = null;
    paramT.next = null;
    paramT.tvLampBlinkSeleted = null;
    paramT.tvLampBlink = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.AtCfgStepTwoActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */