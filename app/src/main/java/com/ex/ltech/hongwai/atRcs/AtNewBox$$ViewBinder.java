package com.ex.ltech.hongwai.atRcs;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.zhy.android.percent.support.PercentRelativeLayout;

public class AtNewBox$$ViewBinder<T extends AtNewBox>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.rl_parent = ((PercentRelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558559, "field 'rl_parent'"), 2131558559, "field 'rl_parent'"));
  }

  public void unbind(T paramT)
  {
    paramT.rl_parent = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtNewBox..ViewBinder
 * JD-Core Version:    0.6.0
 */