package com.ex.ltech.led.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.ex.ltech.led.acti.scene.ActNewCustom;
import com.ex.ltech.led.acti.scene.SceneBusiness;
import com.ex.ltech.led.acti.scene.SysCustomAdapter;
import com.indris.material.RippleView;
import java.io.PrintStream;

public class FragmentSysCustom extends Fragment
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemClickListener
{
  private Button btn_acti_scene_add;
  private Button btn_acti_scene_light_1;
  private Button btn_acti_scene_light_2;
  private Button btn_acti_scene_light_3;
  private Button btn_acti_scene_light_4;
  private RippleView btn_acti_scene_play;
  private SceneBusiness business;
  private boolean isAnyViewHide;
  private LinearLayout ll_acti_scene_3;
  private LinearLayout ll_acti_scene_bottom;
  private ListView lv_acti_scene;
  private View mRootView = null;
  private SysCustomAdapter madapter;
  private SeekBar sb_acti_scene;
  private TextView tv_acti_scene_progress;

  private void findView()
  {
    this.btn_acti_scene_light_1 = ((Button)this.mRootView.findViewById(2131559127));
    this.btn_acti_scene_light_2 = ((Button)this.mRootView.findViewById(2131559128));
    this.btn_acti_scene_light_3 = ((Button)this.mRootView.findViewById(2131559129));
    this.btn_acti_scene_light_4 = ((Button)this.mRootView.findViewById(2131559130));
    this.btn_acti_scene_add = ((Button)this.mRootView.findViewById(2131559137));
    this.ll_acti_scene_3 = ((LinearLayout)this.mRootView.findViewById(2131559133));
    this.ll_acti_scene_bottom = ((LinearLayout)this.mRootView.findViewById(2131559136));
    this.ll_acti_scene_bottom.setVisibility(View.VISIBLE);
    this.btn_acti_scene_play = ((RippleView)this.mRootView.findViewById(2131559131));
    this.tv_acti_scene_progress = ((TextView)this.mRootView.findViewById(2131559132));
    this.sb_acti_scene = ((SeekBar)this.mRootView.findViewById(2131559134));
    this.lv_acti_scene = ((ListView)this.mRootView.findViewById(2131559135));
  }

  private void setListener()
  {
    this.btn_acti_scene_light_1.setOnClickListener(this);
    this.btn_acti_scene_light_2.setOnClickListener(this);
    this.btn_acti_scene_light_3.setOnClickListener(this);
    this.btn_acti_scene_light_4.setOnClickListener(this);
    this.btn_acti_scene_add.setOnClickListener(this);
    this.btn_acti_scene_play.setOnClickListener(this);
    this.sb_acti_scene.setOnSeekBarChangeListener(this);
    this.lv_acti_scene.setOnItemClickListener(this);
  }

  public void hideAnyView()
  {
    this.btn_acti_scene_play.setVisibility(View.GONE);
    this.tv_acti_scene_progress.setVisibility(View.GONE);
    this.ll_acti_scene_3.setVisibility(View.GONE);
    this.ll_acti_scene_bottom.setVisibility(View.GONE);
  }

  public void init()
  {
    this.business = new SceneBusiness(getActivity());
    this.madapter = new SysCustomAdapter(getActivity(), this.business.getSysCustomListVos());
    this.lv_acti_scene.setAdapter(this.madapter);
  }

  public void onActivityCreated(@Nullable Bundle paramBundle)
  {
    super.onActivityCreated(paramBundle);
    findView();
    init();
    setListener();
    System.out.println("onActivityCreated");
    if (this.isAnyViewHide)
      hideAnyView();
  }

  public void onClick(View paramView)
  {
    System.out.println("FragmentSysCustom                       onClick");
    switch (paramView.getId())
    {
    case 2131558766:
    case 2131558767:
    case 2131559127:
    case 2131559128:
    case 2131559129:
    case 2131559130:
    case 2131559131:
    default:
      return;
    case 2131559137:
    }
    startActivity(new Intent(getActivity(), ActNewCustom.class));
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.mRootView = paramLayoutInflater.inflate(2130968738, paramViewGroup, false);
    System.out.println("onCreateView");
    return this.mRootView;
  }

  public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
  {
    this.business.onCustomItemClick(paramInt);
    this.madapter.notifyDataSetChanged();
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void setAnyViewHide(boolean paramBoolean)
  {
    this.isAnyViewHide = paramBoolean;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.fragment.FragmentSysCustom
 * JD-Core Version:    0.6.0
 */