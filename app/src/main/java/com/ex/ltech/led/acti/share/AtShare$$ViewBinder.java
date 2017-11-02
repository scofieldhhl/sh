package com.ex.ltech.led.acti.share;

import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class AtShare$$ViewBinder<T extends AtShare>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.cancel = ((Button)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559030, "field 'cancel'"), 2131559030, "field 'cancel'"));
    paramT.list = ((ListView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559031, "field 'list'"), 2131559031, "field 'list'"));
    paramT.rlPhones = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559023, "field 'rlPhones'"), 2131559023, "field 'rlPhones'"));
    paramT.tvExportCancel = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559034, "field 'tvExportCancel'"), 2131559034, "field 'tvExportCancel'"));
    paramT.rlExport = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559032, "field 'rlExport'"), 2131559032, "field 'rlExport'"));
  }

  public void unbind(T paramT)
  {
    paramT.cancel = null;
    paramT.list = null;
    paramT.rlPhones = null;
    paramT.tvExportCancel = null;
    paramT.rlExport = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.share.AtShare..ViewBinder
 * JD-Core Version:    0.6.0
 */