package com.ex.ltech.onepiontfive.main.room.mode;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.SceneColorPickerView;
import com.indris.material.RippleView;

public class ActNewMode$$ViewBinder<T extends ActNewMode>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleDeviceName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558782, "field 'tvTitleDeviceName'"), 2131558782, "field 'tvTitleDeviceName'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.btnTitleViewEdit = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558784, "field 'btnTitleViewEdit'"), 2131558784, "field 'btnTitleViewEdit'"));
    paramT.tvTitleViewEdit = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558785, "field 'tvTitleViewEdit'"), 2131558785, "field 'tvTitleViewEdit'"));
    paramT.scpvActiNewMode = ((SceneColorPickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558560, "field 'scpvActiNewMode'"), 2131558560, "field 'scpvActiNewMode'"));
    paramT.tvActiNewModeCircleColorPosi = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558561, "field 'tvActiNewModeCircleColorPosi'"), 2131558561, "field 'tvActiNewModeCircleColorPosi'"));
    paramT.btActiNewModeTiao = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558563, "field 'btActiNewModeTiao'"), 2131558563, "field 'btActiNewModeTiao'"));
    paramT.btActiNewModeZan = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558565, "field 'btActiNewModeZan'"), 2131558565, "field 'btActiNewModeZan'"));
    paramT.btActiNewModePing = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558566, "field 'btActiNewModePing'"), 2131558566, "field 'btActiNewModePing'"));
    paramT.btActiNewModePlay = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558564, "field 'btActiNewModePlay'"), 2131558564, "field 'btActiNewModePlay'"));
    paramT.btActiNewModeBringness = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558567, "field 'btActiNewModeBringness'"), 2131558567, "field 'btActiNewModeBringness'"));
    paramT.sbActiNewMode1 = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558568, "field 'sbActiNewMode1'"), 2131558568, "field 'sbActiNewMode1'"));
    paramT.sbActiNewMode2 = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558569, "field 'sbActiNewMode2'"), 2131558569, "field 'sbActiNewMode2'"));
    paramT.etActNewCustomG = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558571, "field 'etActNewCustomG'"), 2131558571, "field 'etActNewCustomG'"));
    paramT.etActNewCustomR = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558572, "field 'etActNewCustomR'"), 2131558572, "field 'etActNewCustomR'"));
    paramT.tvNewCustomB = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558573, "field 'tvNewCustomB'"), 2131558573, "field 'tvNewCustomB'"));
    paramT.etActNewCustomB = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558574, "field 'etActNewCustomB'"), 2131558574, "field 'etActNewCustomB'"));
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleDeviceName = null;
    paramT.tvTitleViewTitle = null;
    paramT.btnTitleViewEdit = null;
    paramT.tvTitleViewEdit = null;
    paramT.scpvActiNewMode = null;
    paramT.tvActiNewModeCircleColorPosi = null;
    paramT.btActiNewModeTiao = null;
    paramT.btActiNewModeZan = null;
    paramT.btActiNewModePing = null;
    paramT.btActiNewModePlay = null;
    paramT.btActiNewModeBringness = null;
    paramT.sbActiNewMode1 = null;
    paramT.sbActiNewMode2 = null;
    paramT.etActNewCustomG = null;
    paramT.etActNewCustomR = null;
    paramT.tvNewCustomB = null;
    paramT.etActNewCustomB = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.ActNewMode..ViewBinder
 * JD-Core Version:    0.6.0
 */