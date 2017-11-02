package com.ex.ltech.outlet;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.zcw.togglebutton.ToggleButton;

public class ActOutLetInfo$$ViewBinder<T extends ActOutLetInfo>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.tb_act_outlet_led_menu = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558753, "field 'tb_act_outlet_led_menu'"), 2131558753, "field 'tb_act_outlet_led_menu'"));
    paramT.iv_act_outlet_led_menu_led = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558754, "field 'iv_act_outlet_led_menu_led'"), 2131558754, "field 'iv_act_outlet_led_menu_led'"));
    paramT.tv_act_outlet_led_menu_led = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558755, "field 'tv_act_outlet_led_menu_led'"), 2131558755, "field 'tv_act_outlet_led_menu_led'"));
  }

  public void unbind(T paramT)
  {
    paramT.tb_act_outlet_led_menu = null;
    paramT.iv_act_outlet_led_menu_led = null;
    paramT.tv_act_outlet_led_menu_led = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.outlet.ActOutLetInfo..ViewBinder
 * JD-Core Version:    0.6.0
 */