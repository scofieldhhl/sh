package com.ex.ltech.led.acti.device;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtCfg4Activity$$ViewBinder<T extends AtCfg4Activity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivCfgOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558878, "field 'ivCfgOk'"), 2131558878, "field 'ivCfgOk'"));
    paramT.ivCfgNoOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558879, "field 'ivCfgNoOk'"), 2131558879, "field 'ivCfgNoOk'"));
    paramT.deviceConnet = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558877, "field 'deviceConnet'"), 2131558877, "field 'deviceConnet'"));
    paramT.info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.info, "field 'info'"), R.id.info, "field 'info'"));
    paramT.ivBottomLeft = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558811, "field 'ivBottomLeft'"), 2131558811, "field 'ivBottomLeft'"));
    paramT.ivBottomRight = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558812, "field 'ivBottomRight'"), 2131558812, "field 'ivBottomRight'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivCfgOk = null;
    paramT.ivCfgNoOk = null;
    paramT.deviceConnet = null;
    paramT.info = null;
    paramT.ivBottomLeft = null;
    paramT.ivBottomRight = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.AtCfg4Activity..ViewBinder
 * JD-Core Version:    0.6.0
 */