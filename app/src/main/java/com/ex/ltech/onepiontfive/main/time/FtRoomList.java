package com.ex.ltech.onepiontfive.main.time;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class FtRoomList extends MyBaseFt
  implements TimingRoomListAdapter.OnMySeletedListener
{
  TimingRoomListAdapter adapter;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  TimingBusiness business;
  int devicePosi;

  @Bind({2131559163})
  ExpandableListView expendlist;
  private ArrayList<Integer> order;
  private List<Integer> result;
  int roomPosi;

  @Bind({2131558783})
  TextView tvTitleViewTitle;

  @Bind({2131558785})
  TextView tv_title_view_edit;
  View view;

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099958);
    this.tv_title_view_edit.setVisibility(View.VISIBLE);
    this.tv_title_view_edit.setTextColor(-16777216);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        Request localRequest = new Request();
        localRequest.putIntegerArrayListExtra("SelectedDevicesmIndex", FtRoomList.this.order);
        FtRoomList.this.business.saveRoomListCacheData();
        FtRoomList.this.setResult(204, localRequest);
        FtRoomList.this.finish();
      }
    });
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968773, null);
      ButterKnife.bind(this, this.view);
      this.business = new TimingBusiness(getActivity());
      this.order = new ArrayList();
      Timing localTiming = (Timing)getRequest().getBundleExtra("selectDvc").get("selectDvc");
      if (localTiming.getSelectedDevicesmIndex() != null)
        this.order.addAll(localTiming.getSelectedDevicesmIndex());
      this.adapter = new TimingRoomListAdapter(getActivity(), this, this.business.getSeletedTimingDeviceListData(this.order), this.order);
      this.expendlist.setAdapter(this.adapter);
      initTitle();
      getRequest().getIntExtra("timingOrder", -1);
      this.expendlist.setDividerHeight(0);
    }
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onDeviceSeleted(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    this.business.onDeviceSeleted(paramInt1, paramInt2);
    if (paramBoolean)
      this.order.add(Integer.valueOf(paramInt3));
    while (true)
    {
      this.adapter.notifyDataSetChanged();
      return;
      for (int i = 0; i < this.order.size(); i++)
      {
        if (((Integer)this.order.get(i)).intValue() != paramInt3)
          continue;
        this.order.remove(i);
      }
    }
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt1 == -1);
  }

  public void onGroupSeleted(int paramInt)
  {
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.FtRoomList
 * JD-Core Version:    0.6.0
 */