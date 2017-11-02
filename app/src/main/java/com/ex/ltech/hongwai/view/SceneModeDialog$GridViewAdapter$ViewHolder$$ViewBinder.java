package com.ex.ltech.hongwai.view;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MLImageView;

public class SceneModeDialog$GridViewAdapter$ViewHolder$$ViewBinder<T extends SceneModeDialog.GridViewAdapter.ViewHolder>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.customIc = ((MLImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559310, "field 'customIc'"), 2131559310, "field 'customIc'"));
    paramT.edit = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559311, "field 'edit'"), 2131559311, "field 'edit'"));
    paramT.name = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559312, "field 'name'"), 2131559312, "field 'name'"));
  }

  public void unbind(T paramT)
  {
    paramT.customIc = null;
    paramT.edit = null;
    paramT.name = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.view.SceneModeDialog.GridViewAdapter.ViewHolder..ViewBinder
 * JD-Core Version:    0.6.0
 */