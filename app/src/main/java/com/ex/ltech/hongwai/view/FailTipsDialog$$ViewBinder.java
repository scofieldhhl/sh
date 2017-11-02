package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.indris.material.RippleView;

public class FailTipsDialog$$ViewBinder<T extends FailTipsDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mImageViewIcon = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559095, "field 'mImageViewIcon'"), 2131559095, "field 'mImageViewIcon'"));
    paramT.mTextViewContent = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558886, "field 'mTextViewContent'"), 2131558886, "field 'mTextViewContent'"));
    View localView1 = (View)paramFinder.findRequiredView(paramObject, 2131559096, "field 'mNegativeBtn' and method 'negativeClick'");
    paramT.mNegativeBtn = ((RippleView)paramFinder.castView(localView1, 2131559096, "field 'mNegativeBtn'"));
    localView1.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.negativeClick(paramView);
      }
    });
    View localView2 = (View)paramFinder.findRequiredView(paramObject, 2131559097, "field 'mPositiveBtn' and method 'positiveClick'");
    paramT.mPositiveBtn = ((RippleView)paramFinder.castView(localView2, 2131559097, "field 'mPositiveBtn'"));
    localView2.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.positiveClick(paramView);
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mImageViewIcon = null;
    paramT.mTextViewContent = null;
    paramT.mNegativeBtn = null;
    paramT.mPositiveBtn = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.FailTipsDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */