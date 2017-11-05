package com.ex.ltech.onepiontfive.main.room.panel;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;

public class FtPanelAssociated extends MyBaseFt
  implements FtPanelAssociatedAdapter.Callback
{
  FtPanelAssociatedAdapter adapter;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private PanelBussiness bussiness;
  int chosenIndex = 0;
  public int currentIndex;
  int dIndex = 0;
  private String dvcName;
  private ArrayList<Dvc> dvcVos;
  private Home home;
  private int iconId;
  private boolean isClicked = false;
  boolean isRelated = false;
  private ListView llPanelAssociated;
  int panelPosi = 0;
  private Room room;
  private int roomNum;
  int roomPosi = 0;
  private ArrayList<Dvc> showDvcVos;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void init()
  {
    this.showDvcVos = new ArrayList();
    this.roomPosi = getRequest().getIntExtra("RoomPosiKey", 0);
    this.panelPosi = getRequest().getIntExtra("PanelPosiKey", 0);
    this.currentIndex = getRequest().getIntExtra("currentIndex", 0);
    this.dIndex = getRequest().getIntExtra("dIndex", -1);
    this.isRelated = getRequest().getBooleanExtra("isSelectedRelation", false);
    this.chosenIndex = getRequest().getIntExtra("chosenIndex", -1);
    this.roomNum = getRequest().getIntExtra("roomNum", 0);
    this.llPanelAssociated = ((ListView)this.view.findViewById(2131559218));
    this.bussiness = new PanelBussiness(getActivity(), this.roomPosi, this.panelPosi, this.dIndex);
    this.bussiness.initData();
    this.home = this.bussiness.home;
    this.dvcVos = ((Room)this.bussiness.home.getRooms().get(this.roomNum)).getDvcVos();
    label281: label418: label424: label430: label436: label452: for (int i = 0; i < this.dvcVos.size(); i++)
    {
      int j;
      int k;
      label253: int n;
      int i1;
      if (((Dvc)this.dvcVos.get(i)).getType() == 8)
      {
        j = 1;
        if (((Dvc)this.dvcVos.get(i)).getType() != 12)
          break label418;
        k = 1;
        int m = j | k;
        if (((Dvc)this.dvcVos.get(i)).getType() != 11)
          break label424;
        n = 1;
        i1 = m | n;
        if (((Dvc)this.dvcVos.get(i)).getType() != 9)
          break label430;
      }
      for (int i2 = 1; ; i2 = 0)
      {
        if ((i2 | i1) == 0)
          break label452;
        if ((!((Dvc)this.dvcVos.get(i)).isGroup()) || (((Dvc)this.dvcVos.get(i)).innerDvcVos.size() <= 0))
          break label436;
        for (int i3 = 0; i3 < ((Dvc)this.dvcVos.get(i)).innerDvcVos.size(); i3++)
          this.showDvcVos.add(((Dvc)this.dvcVos.get(i)).innerDvcVos.get(i3));
        j = 0;
        break;
        k = 0;
        break label253;
        n = 0;
        break label281;
      }
      this.showDvcVos.add(this.dvcVos.get(i));
    }
    this.dvcVos.clear();
    this.dvcVos.addAll(this.showDvcVos);
    this.adapter = new FtPanelAssociatedAdapter(getActivity(), this.dvcVos, this, this.chosenIndex);
    this.llPanelAssociated.setAdapter(this.adapter);
    this.llPanelAssociated.setChoiceMode(1);
  }

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099943);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtPanelAssociated.this.finish();
      }
    });
    this.tvTitleViewEdit.setVisibility(View.VISIBLE);
    this.tvTitleViewEdit.setText(2131100358);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (FtPanelAssociated.this.tvTitleViewEdit.getText().toString().equals(FtPanelAssociated.this.getString(2131100358)))
          if (FtPanelAssociated.this.isClicked)
            break label48;
        while (true)
        {
          FtPanelAssociated.this.finish();
          return;
          label48: FtPanelAssociated.this.setResult(209, FtPanelAssociated.this.getRequest().putExtra("iconId", FtPanelAssociated.this.iconId).putExtra("name", FtPanelAssociated.this.dvcName).putExtra("name", FtPanelAssociated.this.dvcName).putExtra("index", FtPanelAssociated.this.currentIndex).putExtra("chosenIndex", FtPanelAssociated.this.chosenIndex).putExtra("backSign", true));
        }
      }
    });
  }

  public void click(View paramView, Dvc paramDvc, int paramInt)
  {
  }

  public void click(Dvc paramDvc, int paramInt)
  {
    this.isClicked = true;
    Iterator localIterator = this.dvcVos.iterator();
    while (localIterator.hasNext())
    {
      Dvc localDvc = (Dvc)localIterator.next();
      if (!localDvc.isRelation())
        continue;
      localDvc.setIsRelation(false);
    }
    ((Dvc)this.dvcVos.get(paramInt)).setIsRelation(true);
    this.iconId = paramDvc.getType();
    this.dvcName = paramDvc.getName();
    this.chosenIndex = paramDvc.getmIndex();
    this.adapter.notifyDataSetChanged();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968770, null);
    ButterKnife.bind(this, this.view);
    init();
    initTitle();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.panel.FtPanelAssociated
 * JD-Core Version:    0.6.0
 */