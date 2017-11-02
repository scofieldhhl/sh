package com.ex.ltech.onepiontfive.main.room;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class FtRooms$$ViewBinder<T extends FtRooms>
  implements ButterKnife.ViewBinder<T>
{
  public void bind(ButterKnife.Finder paramFinder, T paramT, Object paramObject)
  {
    paramT.dvcOkOperea = ((TextView)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559259, "field 'dvcOkOperea'"), 2131559259, "field 'dvcOkOperea'"));
    paramT.banFastClickTabsLayout = ((RelativeLayout)paramFinder.castView((View)paramFinder.findRequiredView(paramObject, 2131559257, "field 'banFastClickTabsLayout'"), 2131559257, "field 'banFastClickTabsLayout'"));
  }

  public void unbind(T paramT)
  {
    paramT.dvcOkOperea = null;
    paramT.banFastClickTabsLayout = null;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.FtRooms..ViewBinder
 * JD-Core Version:    0.6.0
 */