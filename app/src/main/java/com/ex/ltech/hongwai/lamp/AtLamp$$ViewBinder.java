package com.ex.ltech.hongwai.lamp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MyRainbowSeekBar;
import com.ex.ltech.led.my_view.MySeekBar;

public class AtLamp$$ViewBinder<T extends AtLamp>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.sbMyRainbow = ((MyRainbowSeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558741, "field 'sbMyRainbow'"), 2131558741, "field 'sbMyRainbow'"));
    paramT.roomCoverLay = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558996, "field 'roomCoverLay'"), 2131558996, "field 'roomCoverLay'"));
    paramT.off = ((ImageButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558873, "field 'off'"), 2131558873, "field 'off'"));
    paramT.on = ((ImageButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558874, "field 'on'"), 2131558874, "field 'on'"));
    paramT.rlOff = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558856, "field 'rlOff'"), 2131558856, "field 'rlOff'"));
    paramT.sb_act_outlet_led = ((MySeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558998, "field 'sb_act_outlet_led'"), 2131558998, "field 'sb_act_outlet_led'"));
    paramT.bt_act_outlet_led_5 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558745, "field 'bt_act_outlet_led_5'"), 2131558745, "field 'bt_act_outlet_led_5'"));
    paramT.bt_act_outlet_led_6 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558746, "field 'bt_act_outlet_led_6'"), 2131558746, "field 'bt_act_outlet_led_6'"));
    paramT.bt_act_outlet_led_7 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558747, "field 'bt_act_outlet_led_7'"), 2131558747, "field 'bt_act_outlet_led_7'"));
    paramT.bt_act_outlet_led_8 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558748, "field 'bt_act_outlet_led_8'"), 2131558748, "field 'bt_act_outlet_led_8'"));
    paramT.ll_bot_act_outlet_led = ((LinearLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558744, "field 'll_bot_act_outlet_led'"), 2131558744, "field 'll_bot_act_outlet_led'"));
    paramT.bt_act_outlet_led_1 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558749, "field 'bt_act_outlet_led_1'"), 2131558749, "field 'bt_act_outlet_led_1'"));
    paramT.bt_act_outlet_led_2 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558750, "field 'bt_act_outlet_led_2'"), 2131558750, "field 'bt_act_outlet_led_2'"));
    paramT.bt_act_outlet_led_3 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558751, "field 'bt_act_outlet_led_3'"), 2131558751, "field 'bt_act_outlet_led_3'"));
    paramT.bt_act_outlet_led_4 = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558752, "field 'bt_act_outlet_led_4'"), 2131558752, "field 'bt_act_outlet_led_4'"));
  }

  public void unbind(T paramT)
  {
    paramT.sbMyRainbow = null;
    paramT.roomCoverLay = null;
    paramT.off = null;
    paramT.on = null;
    paramT.rlOff = null;
    paramT.sb_act_outlet_led = null;
    paramT.bt_act_outlet_led_5 = null;
    paramT.bt_act_outlet_led_6 = null;
    paramT.bt_act_outlet_led_7 = null;
    paramT.bt_act_outlet_led_8 = null;
    paramT.ll_bot_act_outlet_led = null;
    paramT.bt_act_outlet_led_1 = null;
    paramT.bt_act_outlet_led_2 = null;
    paramT.bt_act_outlet_led_3 = null;
    paramT.bt_act_outlet_led_4 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.lamp.AtLamp..ViewBinder
 * JD-Core Version:    0.6.0
 */