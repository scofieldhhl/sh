package com.ex.ltech.onepiontfive.main.updataHardWareProgram;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtUpgrade$$ViewBinder<T extends AtUpgrade>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.rlLoad = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558800, "field 'rlLoad'"), 2131558800, "field 'rlLoad'"));
    paramT.tvVs1 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559042, "field 'tvVs1'"), 2131559042, "field 'tvVs1'"));
    paramT.tvVs2 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559043, "field 'tvVs2'"), 2131559043, "field 'tvVs2'"));
    paramT.tvVs3 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559044, "field 'tvVs3'"), 2131559044, "field 'tvVs3'"));
  }

  public void unbind(T paramT)
  {
    paramT.rlLoad = null;
    paramT.tvVs1 = null;
    paramT.tvVs2 = null;
    paramT.tvVs3 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.updataHardWareProgram.AtUpgrade..ViewBinder
 * JD-Core Version:    0.6.0
 */