package com.ex.ltech.onepiontfive.main.room;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;

public class ModeFragment extends Fragment
  implements View.OnClickListener
{
  private RippleView btnActiModePlay;
  private GridView gvActMode;
  private LinearLayout llActiMode3;
  private LinearLayout llActiModeBottom;
  private RelativeLayout rlActiModeParent;
  private SeekBar sbActiMode;
  private TextView tvActiModeProgress;

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131558716:
    }
    new Request(RoomFragment.class);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    return paramLayoutInflater.inflate(2130968757, null);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.rlActiModeParent = ((RelativeLayout)paramView.findViewById(2131558705));
    this.tvActiModeProgress = ((TextView)paramView.findViewById(2131558706));
    this.llActiMode3 = ((LinearLayout)paramView.findViewById(2131558707));
    this.sbActiMode = ((SeekBar)paramView.findViewById(2131558708));
    this.btnActiModePlay = ((RippleView)paramView.findViewById(2131558709));
    this.gvActMode = ((GridView)paramView.findViewById(2131558714));
    this.llActiModeBottom = ((LinearLayout)paramView.findViewById(2131558715));
    paramView.findViewById(2131558716).setOnClickListener(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.ModeFragment
 * JD-Core Version:    0.6.0
 */