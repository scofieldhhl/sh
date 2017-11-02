package com.ex.ltech.hongwai.yaokong.fragment;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import com.ex.ltech.led.my_view.MLImageView;

public class AtYkLightModeFragment$MyAdapter$ViewHolder$$ViewBinder<T extends AtYkLightModeFragment.MyAdapter.ViewHolder>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.ivActiTimingListItem1 = ((MLImageView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559393, "field 'ivActiTimingListItem1'"), 2131559393, "field 'ivActiTimingListItem1'"));
    paramT.tvActiTimingListItem2 = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559394, "field 'tvActiTimingListItem2'"), 2131559394, "field 'tvActiTimingListItem2'"));
  }

  public void unbind(T paramT)
  {
    paramT.ivActiTimingListItem1 = null;
    paramT.tvActiTimingListItem2 = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.fragment.AtYkLightModeFragment.MyAdapter.ViewHolder..ViewBinder
 * JD-Core Version:    0.6.0
 */