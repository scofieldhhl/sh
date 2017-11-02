package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.zcw.togglebutton.ToggleButton;

public class AtYkSwitchSelectActivity$$ViewBinder<T extends AtYkSwitchSelectActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mLayoutLightSelect = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558657, "field 'mLayoutLightSelect'"), 2131558657, "field 'mLayoutLightSelect'"));
    paramT.mActLightSwitch = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558660, "field 'mActLightSwitch'"), 2131558660, "field 'mActLightSwitch'"));
    paramT.mLayoutSelect = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558661, "field 'mLayoutSelect'"), 2131558661, "field 'mLayoutSelect'"));
    paramT.mActSwitch = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558663, "field 'mActSwitch'"), 2131558663, "field 'mActSwitch'"));
    paramT.mLayoutSelect1 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558664, "field 'mLayoutSelect1'"), 2131558664, "field 'mLayoutSelect1'"));
    View localView1 = (View)paramFinder.findRequiredView(paramObject, 2131558665, "field 'mImageViewSelect1' and method 'select1OnClick'");
    paramT.mImageViewSelect1 = ((ImageView)paramFinder.castView(localView1, 2131558665, "field 'mImageViewSelect1'"));
    localView1.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.select1OnClick();
      }
    });
    paramT.mActSwitch1 = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558667, "field 'mActSwitch1'"), 2131558667, "field 'mActSwitch1'"));
    paramT.mLayoutSelect2 = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558668, "field 'mLayoutSelect2'"), 2131558668, "field 'mLayoutSelect2'"));
    View localView2 = (View)paramFinder.findRequiredView(paramObject, 2131558669, "field 'mImageViewSelect2' and method 'select2OnClick'");
    paramT.mImageViewSelect2 = ((ImageView)paramFinder.castView(localView2, 2131558669, "field 'mImageViewSelect2'"));
    localView2.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.select2OnClick();
      }
    });
    paramT.mActSwitch2 = ((ToggleButton)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558671, "field 'mActSwitch2'"), 2131558671, "field 'mActSwitch2'"));
  }

  public void unbind(T paramT)
  {
    paramT.mLayoutLightSelect = null;
    paramT.mActLightSwitch = null;
    paramT.mLayoutSelect = null;
    paramT.mActSwitch = null;
    paramT.mLayoutSelect1 = null;
    paramT.mImageViewSelect1 = null;
    paramT.mActSwitch1 = null;
    paramT.mLayoutSelect2 = null;
    paramT.mImageViewSelect2 = null;
    paramT.mActSwitch2 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchSelectActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */