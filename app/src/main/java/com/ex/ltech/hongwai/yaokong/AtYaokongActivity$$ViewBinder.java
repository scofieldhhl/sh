package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.zcw.togglebutton.ToggleButton;

public class AtYaokongActivity$$ViewBinder<T extends AtYaokongActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.zzzSwich = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558965, "field 'zzzSwich'"), 2131558965, "field 'zzzSwich'"));
    paramT.version = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558966, "field 'version'"), 2131558966, "field 'version'"));
    paramT.pop = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558963, "field 'pop'"), 2131558963, "field 'pop'"));
  }

  public void unbind(T paramT)
  {
    paramT.zzzSwich = null;
    paramT.version = null;
    paramT.pop = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYaokongActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */