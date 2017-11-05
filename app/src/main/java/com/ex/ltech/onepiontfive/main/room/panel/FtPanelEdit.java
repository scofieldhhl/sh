package com.ex.ltech.onepiontfive.main.room.panel;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;

public class FtPanelEdit extends MyBaseFt
  implements FtPanelEditAdapter.Callback
{
  private boolean CHOSE = true;
  FtPanelEditAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private PanelBussiness bussiness;

  @Bind({2131559164})
  SwipeMenuListView lvLampItemEdit;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void init()
  {
    this.bussiness = new PanelBussiness(getActivity(), getRequest().getIntExtra("RoomPosiKey", 0), getRequest().getIntExtra("PanelPosiKey", 0), getRequest().getIntExtra("dIndex", -1));
    this.bussiness.initData4Cache();
    this.adapter = new FtPanelEditAdapter(getActivity(), this.bussiness.lights, this);
    this.lvLampItemEdit.setAdapter(this.adapter);
  }

  private void initView()
  {
  }

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100052);
    this.tvTitleViewEdit.setVisibility(View.VISIBLE);
    this.tvTitleViewEdit.setText(2131099874);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtPanelEdit.this.finish();
      }
    });
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtPanelEdit.this.bussiness.onSeletedAll(true);
        FtPanelEdit.this.adapter.notifyDataSetChanged();
      }
    });
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtPanelEdit.this.bussiness.putCacheData();
        FtPanelEdit.this.setResult(-1);
        FtPanelEdit.this.finish();
      }
    });
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968754, null);
    ButterKnife.bind(this, this.view);
    init();
    initTitleView();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onProgressChanged(int paramInt1, int paramInt2)
  {
    this.bussiness.onProgressChanged(paramInt1, paramInt2);
  }

  public void onRelationSeleted(int paramInt)
  {
    this.bussiness.onRelationSeleted(paramInt);
    this.adapter.notifyDataSetChanged();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanelEdit
 * JD-Core Version:    0.6.0
 */