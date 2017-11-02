package com.fragmentmaster.app;

import android.app.Activity;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import com.fragmentmaster.R.attr;
import com.fragmentmaster.annotation.Configuration;

class FragmentContext extends ContextThemeWrapper
{
  FragmentContext(IMasterFragment paramIMasterFragment)
  {
    super(paramIMasterFragment.getActivity(), getMasterFragmentThemeRes(paramIMasterFragment.getActivity(), paramIMasterFragment));
  }

  private static int getMasterFragmentThemeRes(Activity paramActivity, IMasterFragment paramIMasterFragment)
  {
    int i = -1;
    Class localClass = paramIMasterFragment.getClass();
    if (localClass.isAnnotationPresent(Configuration.class))
      i = ((Configuration)localClass.getAnnotation(Configuration.class)).theme();
    if (i == -1)
    {
      TypedValue localTypedValue = new TypedValue();
      paramActivity.getTheme().resolveAttribute(R.attr.masterFragmentTheme, localTypedValue, true);
      i = localTypedValue.resourceId;
    }
    return i;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.FragmentContext
 * JD-Core Version:    0.6.0
 */