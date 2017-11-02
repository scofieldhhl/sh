package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.ex.ltech.hongwai.view.ImageTextBigButton;

public class AtYkSwitchRf1Activity$$ViewBinder<T extends AtYkSwitchRf1Activity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mBackground = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559035, "field 'mBackground'"), 2131559035, "field 'mBackground'"));
    paramT.mTextViewKeyName = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558662, "field 'mTextViewKeyName'"), 2131558662, "field 'mTextViewKeyName'"));
    View localView1 = (View)paramFinder.findRequiredView(paramObject, 2131558857, "field 'btOn' and method 'onViewClicked'");
    paramT.btOn = ((ImageTextBigButton)paramFinder.castView(localView1, 2131558857, "field 'btOn'"));
    localView1.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    View localView2 = (View)paramFinder.findRequiredView(paramObject, 2131558847, "field 'btOff' and method 'onViewClicked'");
    paramT.btOff = ((ImageTextBigButton)paramFinder.castView(localView2, 2131558847, "field 'btOff'"));
    localView2.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mBackground = null;
    paramT.mTextViewKeyName = null;
    paramT.btOn = null;
    paramT.btOff = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchRf1Activity..ViewBinder
 * JD-Core Version:    0.6.0
 */