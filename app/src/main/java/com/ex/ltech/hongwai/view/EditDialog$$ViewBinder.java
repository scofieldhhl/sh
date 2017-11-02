package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;

public class EditDialog$$ViewBinder<T extends EditDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mEditContent = ((EditText)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559091, "field 'mEditContent'"), 2131559091, "field 'mEditContent'"));
    paramT.mTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559088, "field 'mTitle'"), 2131559088, "field 'mTitle'"));
    ((View)paramFinder.findRequiredView(paramObject, 2131559090, "method 'clearText'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.clearText();
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131558890, "method 'onCancelBtnClick'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onCancelBtnClick();
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559093, "method 'onOkBtnClick'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onOkBtnClick();
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mEditContent = null;
    paramT.mTitle = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.EditDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */