package com.ex.ltech.onepiontfive.main.room.mode;

import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.indris.material.RippleView;

public class ActMode$$ViewBinder<T extends ActMode>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
    paramT.tvActiModeProgress = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558706, "field 'tvActiModeProgress'"), 2131558706, "field 'tvActiModeProgress'"));
    paramT.sbActiMode = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558708, "field 'sbActiMode'"), 2131558708, "field 'sbActiMode'"));
    paramT.btnActiModePlay = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558709, "field 'btnActiModePlay'"), 2131558709, "field 'btnActiModePlay'"));
    paramT.ivFragSysInsideAllSeleted = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558711, "field 'ivFragSysInsideAllSeleted'"), 2131558711, "field 'ivFragSysInsideAllSeleted'"));
    paramT.gvActMode = ((GridView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558714, "field 'gvActMode'"), 2131558714, "field 'gvActMode'"));
    paramT.btnActiModeAdd = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558716, "field 'btnActiModeAdd'"), 2131558716, "field 'btnActiModeAdd'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
    paramT.tvActiModeProgress = null;
    paramT.sbActiMode = null;
    paramT.btnActiModePlay = null;
    paramT.ivFragSysInsideAllSeleted = null;
    paramT.gvActMode = null;
    paramT.btnActiModeAdd = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.ActMode..ViewBinder
 * JD-Core Version:    0.6.0
 */