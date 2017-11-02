package com.ex.ltech.onepiontfive.main;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.res.Resources.NotFoundException;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;
import com.fragmentmaster.app.MasterFragment;

public class MyBaseFt extends MasterFragment
{
  BroadcastReceiver mBroadcastReceiver;

  protected void regBroadcast(BroadcastReceiver paramBroadcastReceiver)
  {
    this.mBroadcastReceiver = paramBroadcastReceiver;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("ShowListviewBroadCast");
    localIntentFilter.addAction("HideListviewBroadCast");
    localIntentFilter.addAction("AddDevicePlugBroadCast");
    localIntentFilter.addAction("AddDeviceRcBroadCast");
    localIntentFilter.addAction("AddDeviceSenorBroadCast");
    localIntentFilter.addAction("SenserOnOffBroadCast");
    localIntentFilter.addAction("AddDevicePanelBroadCast");
    localIntentFilter.addAction("PanelOnOffBroadCast");
    localIntentFilter.addAction("AddDeviceLightBroadCast");
    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mBroadcastReceiver, localIntentFilter);
  }

  protected void regUpataBroadcast(BroadcastReceiver paramBroadcastReceiver)
  {
    this.mBroadcastReceiver = paramBroadcastReceiver;
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("showFirmware");
    LocalBroadcastManager.getInstance(getActivity()).registerReceiver(this.mBroadcastReceiver, localIntentFilter);
  }

  protected void shortToast(int paramInt)
  {
    try
    {
      Toast.makeText(getActivity(), paramInt, 0).show();
      return;
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      localNotFoundException.printStackTrace();
    }
  }

  protected void unRegBroadcast()
  {
    if (this.mBroadcastReceiver != null)
      LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.mBroadcastReceiver);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.MyBaseFt
 * JD-Core Version:    0.6.0
 */