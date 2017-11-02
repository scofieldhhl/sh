package com.fragmentmaster.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

class Records
{
  private static final String TAG = "Records";
  private ArrayList<IMasterFragment> mFragments = new ArrayList();

  public void add(IMasterFragment paramIMasterFragment1, IMasterFragment paramIMasterFragment2, int paramInt)
  {
    if (paramIMasterFragment2 == null);
    for (Fragment localFragment = null; ; localFragment = paramIMasterFragment2.getFragment())
    {
      paramIMasterFragment1.setTargetFragment(localFragment, paramInt);
      this.mFragments.add(paramIMasterFragment1);
      return;
    }
  }

  public List<IMasterFragment> getFragments()
  {
    return Collections.unmodifiableList(this.mFragments);
  }

  public boolean has(IMasterFragment paramIMasterFragment)
  {
    return this.mFragments.contains(paramIMasterFragment);
  }

  public int indexOf(IMasterFragment paramIMasterFragment)
  {
    return this.mFragments.indexOf(paramIMasterFragment);
  }

  public void remove(IMasterFragment paramIMasterFragment)
  {
    int i = this.mFragments.indexOf(paramIMasterFragment);
    this.mFragments.remove(i);
    for (int j = i; j < this.mFragments.size(); j++)
    {
      IMasterFragment localIMasterFragment = (IMasterFragment)this.mFragments.get(j);
      if ((IMasterFragment)localIMasterFragment.getTargetFragment() != paramIMasterFragment)
        continue;
      localIMasterFragment.setTargetFragment(null, -1);
    }
  }

  public void restore(FragmentManager paramFragmentManager, Bundle paramBundle)
  {
    this.mFragments.clear();
    if (paramBundle != null)
    {
      Iterator localIterator = paramBundle.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        if (!str.startsWith("f"))
          continue;
        int i = Integer.parseInt(str.substring(1));
        IMasterFragment localIMasterFragment = (IMasterFragment)paramFragmentManager.getFragment(paramBundle, str);
        if (localIMasterFragment != null)
        {
          while (this.mFragments.size() <= i)
            this.mFragments.add(null);
          localIMasterFragment.setMenuVisibility(false);
          this.mFragments.set(i, localIMasterFragment);
          continue;
        }
        Log.w("Records", "Bad fragment at key " + str);
      }
    }
  }

  public Bundle save(FragmentManager paramFragmentManager)
  {
    Bundle localBundle = null;
    for (int i = 0; i < this.mFragments.size(); i++)
    {
      Fragment localFragment = ((IMasterFragment)this.mFragments.get(i)).getFragment();
      if (localFragment == null)
        continue;
      if (localBundle == null)
        localBundle = new Bundle();
      paramFragmentManager.putFragment(localBundle, "f" + i, localFragment);
    }
    return localBundle;
  }

  public int size()
  {
    return this.mFragments.size();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.fragmentmaster.app.Records
 * JD-Core Version:    0.6.0
 */