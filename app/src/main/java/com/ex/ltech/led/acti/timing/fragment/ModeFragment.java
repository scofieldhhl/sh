package com.ex.ltech.led.acti.timing.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.ex.ltech.led.acti.mode.ModeBusiness;
import com.ex.ltech.led.vo.ModeVo;
import java.io.PrintStream;
import java.util.List;

public class ModeFragment extends Fragment
  implements AdapterView.OnItemClickListener
{
  private ListView lv_f_timing_mode;
  private View mRootView = null;
  private MyAdapter madapter;
  private ModeBusiness modeBusiness;
  public String modeName;
  public List<ModeVo> modes;

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    this.modeBusiness = new ModeBusiness(getActivity());
    this.modeBusiness.initModes();
    this.modeBusiness.removeAddBtnVos();
    this.lv_f_timing_mode = ((ListView)this.mRootView.findViewById(2131559138));
    this.madapter = new MyAdapter(getActivity(), this.modeBusiness.modes, this.modeBusiness.getNewCreateModeBitmaps());
    this.lv_f_timing_mode.setAdapter(this.madapter);
    this.lv_f_timing_mode.setOnItemClickListener(this);
    this.modes = this.modeBusiness.modes;
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968740, paramViewGroup, false);
    System.out.println("onCreateView");
    return this.mRootView;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.modeBusiness.onListViewItemClick(paramInt);
    this.modes = this.modeBusiness.modes;
    this.modeName = ((ModeVo)this.madapter.getItem(paramInt)).getTvName();
    this.madapter.notifyDataSetChanged();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.timing.fragment.ModeFragment
 * JD-Core Version:    0.6.0
 */