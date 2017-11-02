package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class OkTipsDialog$$ViewBinder<T extends OkTipsDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mTextViewContent = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558886, "field 'mTextViewContent'"), 2131558886, "field 'mTextViewContent'"));
    ((View)paramFinder.findRequiredView(paramObject, 2131559099, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559100, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559101, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mTextViewContent = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.OkTipsDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */