package com.ex.ltech.onepiontfive.main.config;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;
import butterknife.internal.DebouncingOnClickListener;
import com.indris.material.RippleView;

public class FtAddDeviceList$$ViewBinder<T extends FtAddDeviceList>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.btnTitleViewMenu = ((RippleView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558781, "field 'btnTitleViewMenu'"), 2131558781, "field 'btnTitleViewMenu'"));
    paramT.tvTitleViewTitle = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558783, "field 'tvTitleViewTitle'"), 2131558783, "field 'tvTitleViewTitle'"));
    paramT.rlPlug = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559139, "field 'rlPlug'"), 2131559139, "field 'rlPlug'"));
    paramT.rlRc = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131558538, "field 'rlRc'"), 2131558538, "field 'rlRc'"));
    paramT.rlSensor = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559140, "field 'rlSensor'"), 2131559140, "field 'rlSensor'"));
    paramT.rlPanel = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559141, "field 'rlPanel'"), 2131559141, "field 'rlPanel'"));
    ((View)paramFinder.findRequiredView(paramObject, 2131559142, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559143, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
    ((View)paramFinder.findRequiredView(paramObject, 2131559144, "method 'onViewClicked'")).setOnClickListener(new DebouncingOnClickListener(paramT)
    {
      public void doClick(View paramView)
      {
        this.val$target.onViewClicked(paramView);
      }
    });
  }

  public void unbind(T paramT)
  {
    paramT.btnTitleViewMenu = null;
    paramT.tvTitleViewTitle = null;
    paramT.rlPlug = null;
    paramT.rlRc = null;
    paramT.rlSensor = null;
    paramT.rlPanel = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.config.FtAddDeviceList..ViewBinder
 * JD-Core Version:    0.6.0
 */