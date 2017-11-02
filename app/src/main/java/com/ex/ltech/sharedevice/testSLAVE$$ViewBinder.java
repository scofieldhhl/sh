package com.ex.ltech.sharedevice;

import android.view.View;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class testSLAVE$$ViewBinder<T extends testSLAVE>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.login_info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559478, "field 'login_info'"), 2131559478, "field 'login_info'"));
    paramT.share_info = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559480, "field 'share_info'"), 2131559480, "field 'share_info'"));
    paramT.shareDeviceList = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559485, "field 'shareDeviceList'"), 2131559485, "field 'shareDeviceList'"));
    paramT.cancelShareDeviceInfo = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559482, "field 'cancelShareDeviceInfo'"), 2131559482, "field 'cancelShareDeviceInfo'"));
    paramT.deleteShareInfo = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559487, "field 'deleteShareInfo'"), 2131559487, "field 'deleteShareInfo'"));
    paramT.acceptShareInfo = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559488, "field 'acceptShareInfo'"), 2131559488, "field 'acceptShareInfo'"));
    paramT.denyShareInfo = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559489, "field 'denyShareInfo'"), 2131559489, "field 'denyShareInfo'"));
    paramT.errorMsg = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559490, "field 'errorMsg'"), 2131559490, "field 'errorMsg'"));
  }

  public void unbind(T paramT)
  {
    paramT.login_info = null;
    paramT.share_info = null;
    paramT.shareDeviceList = null;
    paramT.cancelShareDeviceInfo = null;
    paramT.deleteShareInfo = null;
    paramT.acceptShareInfo = null;
    paramT.denyShareInfo = null;
    paramT.errorMsg = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.sharedevice.testSLAVE..ViewBinder
 * JD-Core Version:    0.6.0
 */