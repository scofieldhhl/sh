package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.SecondArc;

public class FtCfg4$$ViewBinder<T extends FtCfg4>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.sbActOutletLed = ((SecondArc)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558742, "field 'sbActOutletLed'"), 2131558742, "field 'sbActOutletLed'"));
    paramT.second = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558875, "field 'second'"), 2131558875, "field 'second'"));
    paramT.smallSecond = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558876, "field 'smallSecond'"), 2131558876, "field 'smallSecond'"));
    paramT.info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, R.id.info, "field 'info'"), R.id.info, "field 'info'"));
    paramT.deviceConnet = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558877, "field 'deviceConnet'"), 2131558877, "field 'deviceConnet'"));
    paramT.ivCfgOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558878, "field 'ivCfgOk'"), 2131558878, "field 'ivCfgOk'"));
    paramT.ivCfgNoOk = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558879, "field 'ivCfgNoOk'"), 2131558879, "field 'ivCfgNoOk'"));
  }

  public void unbind(T paramT)
  {
    paramT.sbActOutletLed = null;
    paramT.second = null;
    paramT.smallSecond = null;
    paramT.info = null;
    paramT.deviceConnet = null;
    paramT.ivCfgOk = null;
    paramT.ivCfgNoOk = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtCfg4..ViewBinder
 * JD-Core Version:    0.6.0
 */