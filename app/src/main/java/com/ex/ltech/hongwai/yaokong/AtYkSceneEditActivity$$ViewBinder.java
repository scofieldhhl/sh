package com.ex.ltech.hongwai.yaokong;

import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.SimpleColorPickerView;

public class AtYkSceneEditActivity$$ViewBinder<T extends AtYkSceneEditActivity>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.mSimpleColorPickerView = ((SimpleColorPickerView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558646, "field 'mSimpleColorPickerView'"), 2131558646, "field 'mSimpleColorPickerView'"));
    paramT.mImageviewBright = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558829, "field 'mImageviewBright'"), 2131558829, "field 'mImageviewBright'"));
    paramT.mImageviewAdd = ((ImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558831, "field 'mImageviewAdd'"), 2131558831, "field 'mImageviewAdd'"));
    paramT.sb = ((SeekBar)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558652, "field 'sb'"), 2131558652, "field 'sb'"));
  }

  public void unbind(T paramT)
  {
    paramT.mSimpleColorPickerView = null;
    paramT.mImageviewBright = null;
    paramT.mImageviewAdd = null;
    paramT.sb = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSceneEditActivity..ViewBinder
 * JD-Core Version:    0.6.0
 */