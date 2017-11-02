package com.ex.ltech.led.acti.guide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ProductTourFragment extends Fragment
{
  static final String LAYOUT_ID = "layoutid";

  public static ProductTourFragment newInstance(int paramInt)
  {
    ProductTourFragment localProductTourFragment = new ProductTourFragment();
    Bundle localBundle = new Bundle();
    localBundle.putInt("layoutid", paramInt);
    localProductTourFragment.setArguments(localBundle);
    return localProductTourFragment;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return (ViewGroup)paramLayoutInflater.inflate(getArguments().getInt("layoutid", -1), paramViewGroup, false);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.guide.ProductTourFragment
 * JD-Core Version:    0.6.0
 */