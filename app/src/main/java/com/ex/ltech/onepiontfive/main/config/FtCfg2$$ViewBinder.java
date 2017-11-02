package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FtCfg2$$ViewBinder<T extends FtCfg2>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivDevice = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558808, "field 'ivDevice'"), 2131558808, "field 'ivDevice'"));
    paramT.info1 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558809, "field 'info1'"), 2131558809, "field 'info1'"));
    paramT.info2 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558810, "field 'info2'"), 2131558810, "field 'info2'"));
    paramT.center = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558457, "field 'center'"), 2131558457, "field 'center'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivDevice = null;
    paramT.info1 = null;
    paramT.info2 = null;
    paramT.center = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtCfg2..ViewBinder
 * JD-Core Version:    0.6.0
 */