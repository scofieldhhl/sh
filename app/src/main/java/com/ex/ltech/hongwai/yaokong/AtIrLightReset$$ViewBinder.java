package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtIrLightReset$$ViewBinder<T extends AtIrLightReset>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mImageViewHelp = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558919, "field 'mImageViewHelp'"), 2131558919, "field 'mImageViewHelp'"));
    paramT.mTextViewContent = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558886, "field 'mTextViewContent'"), 2131558886, "field 'mTextViewContent'"));
    paramT.mTextViewNext = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558869, "field 'mTextViewNext'"), 2131558869, "field 'mTextViewNext'"));
    paramT.mTextViewSelected = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558867, "field 'mTextViewSelected'"), 2131558867, "field 'mTextViewSelected'"));
    paramT.mTextViewTip = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558889, "field 'mTextViewTip'"), 2131558889, "field 'mTextViewTip'"));
  }

  public void unbind(T paramT)
  {
    paramT.mImageViewHelp = null;
    paramT.mTextViewContent = null;
    paramT.mTextViewNext = null;
    paramT.mTextViewSelected = null;
    paramT.mTextViewTip = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtIrLightReset..ViewBinder
 * JD-Core Version:    0.6.0
 */