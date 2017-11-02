package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.indris.material.RippleView;

public class SearchDeviceDialog$$ViewBinder<T extends SearchDeviceDialog>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mTimeProgressBar = ((TimeProgressBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559102, "field 'mTimeProgressBar'"), 2131559102, "field 'mTimeProgressBar'"));
    paramT.mTvContent = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558886, "field 'mTvContent'"), 2131558886, "field 'mTvContent'"));
    View localView = (View)paramFinder.findRequiredView(paramObject, 2131559103, "field 'mTvCancelBtn' and method 'cancel'");
    paramT.mTvCancelBtn = ((RippleView)paramFinder.castView(localView, 2131559103, "field 'mTvCancelBtn'"));
    localView.setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.cancel();
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.mTimeProgressBar = null;
    paramT.mTvContent = null;
    paramT.mTvCancelBtn = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SearchDeviceDialog..ViewBinder
 * JD-Core Version:    0.6.0
 */