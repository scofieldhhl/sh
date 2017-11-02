package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.ex.ltech.hongwai.view.ImageTextBigButton;

public class AtYkSwitchRf2Activity$$ViewBinder<T extends AtYkSwitchRf2Activity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mBackground = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559035, "field 'mBackground'"), 2131559035, "field 'mBackground'"));
    paramT.mTextViewKeyName1 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558666, "field 'mTextViewKeyName1'"), 2131558666, "field 'mTextViewKeyName1'"));
    paramT.mTextViewKeyName2 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558670, "field 'mTextViewKeyName2'"), 2131558670, "field 'mTextViewKeyName2'"));
    View localView1 = (View)paramFinder.findRequiredView(paramObject, 2131559037, "field 'btOn1' and method 'onViewClicked'");
    paramT.btOn1 = ((ImageTextBigButton)paramFinder.castView(localView1, 2131559037, "field 'btOn1'"));
    localView1.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    View localView2 = (View)paramFinder.findRequiredView(paramObject, 2131559038, "field 'btOff1' and method 'onViewClicked'");
    paramT.btOff1 = ((ImageTextBigButton)paramFinder.castView(localView2, 2131559038, "field 'btOff1'"));
    localView2.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    View localView3 = (View)paramFinder.findRequiredView(paramObject, 2131559039, "field 'btOn2' and method 'onViewClicked'");
    paramT.btOn2 = ((ImageTextBigButton)paramFinder.castView(localView3, 2131559039, "field 'btOn2'"));
    localView3.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    View localView4 = (View)paramFinder.findRequiredView(paramObject, 2131559040, "field 'btOff2' and method 'onViewClicked'");
    paramT.btOff2 = ((ImageTextBigButton)paramFinder.castView(localView4, 2131559040, "field 'btOff2'"));
    localView4.setOnClickListener(new DebouncingOnClickListener(paramT)
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
    paramT.mTextViewKeyName1 = null;
    paramT.mTextViewKeyName2 = null;
    paramT.btOn1 = null;
    paramT.btOff1 = null;
    paramT.btOn2 = null;
    paramT.btOff2 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchRf2Activity..ViewBinder
 * JD-Core Version:    0.6.0
 */