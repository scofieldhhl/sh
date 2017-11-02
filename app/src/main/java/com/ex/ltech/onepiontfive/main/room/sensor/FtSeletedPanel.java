package com.ex.ltech.onepiontfive.main.room.sensor;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.SensorDelay;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.List;

public class FtSeletedPanel extends MyBaseFt
{
  SeletedPanelAdapter adapter;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  String delay;
  public List<SensorDelay> delayDayVos = new ArrayList();

  @Bind({2131559265})
  ListView delayList;
  int panelPosi;

  @Bind({2131558993})
  RelativeLayout rlTitle;
  int roomPosi;
  SensorBiz sensorBiz;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;
  SensorVo vo;

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968779, null);
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onSeletedBlub(int paramInt)
  {
    boolean bool1 = ((PanelLampVO)((RoomLvChildVo)((Room)this.vo.getRooms().get(this.roomPosi)).getExpandableLvInnerItemVos().get(this.panelPosi)).getPanelLampVO().get(paramInt)).isSeleted();
    PanelLampVO localPanelLampVO = (PanelLampVO)((RoomLvChildVo)((Room)this.vo.getRooms().get(this.roomPosi)).getExpandableLvInnerItemVos().get(this.panelPosi)).getPanelLampVO().get(paramInt);
    if (!bool1);
    for (boolean bool2 = true; ; bool2 = false)
    {
      localPanelLampVO.setIsSeleted(bool2);
      this.adapter.notifyDataSetChanged();
      return;
    }
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131099933);
    this.sensorBiz = new SensorBiz(getActivity());
    this.vo = this.sensorBiz.getCacheSensorVo();
    this.roomPosi = getRequest().getIntExtra("RoomPosiKey", 0);
    this.panelPosi = getRequest().getIntExtra("PanelPosiKey", 0);
    this.adapter = new SeletedPanelAdapter(getActivity(), ((RoomLvChildVo)((Room)this.vo.getRooms().get(this.roomPosi)).getExpandableLvInnerItemVos().get(this.panelPosi)).getPanelLampVO(), this);
    this.delayList.setAdapter(this.adapter);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        String str = "";
        int i = 0;
        if (i < ((RoomLvChildVo)((Room)FtSeletedPanel.this.vo.getRooms().get(FtSeletedPanel.this.roomPosi)).getExpandableLvInnerItemVos().get(FtSeletedPanel.this.panelPosi)).getPanelLampVO().size())
        {
          if (((PanelLampVO)((RoomLvChildVo)((Room)FtSeletedPanel.this.vo.getRooms().get(FtSeletedPanel.this.roomPosi)).getExpandableLvInnerItemVos().get(FtSeletedPanel.this.panelPosi)).getPanelLampVO().get(i)).isSeleted())
            if (i != 0)
              break label178;
          label178: for (str = ((PanelLampVO)((RoomLvChildVo)((Room)FtSeletedPanel.this.vo.getRooms().get(FtSeletedPanel.this.roomPosi)).getExpandableLvInnerItemVos().get(FtSeletedPanel.this.panelPosi)).getPanelLampVO().get(i)).getName(); ; str = str + "\t" + ((PanelLampVO)((RoomLvChildVo)((Room)FtSeletedPanel.this.vo.getRooms().get(FtSeletedPanel.this.roomPosi)).getExpandableLvInnerItemVos().get(FtSeletedPanel.this.panelPosi)).getPanelLampVO().get(i)).getName())
          {
            i++;
            break;
          }
        }
        ((RoomLvChildVo)((Room)FtSeletedPanel.this.vo.getRooms().get(FtSeletedPanel.this.roomPosi)).getExpandableLvInnerItemVos().get(FtSeletedPanel.this.panelPosi)).setInnerDeviceStatus(str);
        FtSeletedPanel.this.sensorBiz.saveCacheData(FtSeletedPanel.this.vo);
        FtSeletedPanel.this.setResult(20000, FtSeletedPanel.this.getRequest().putExtra("RoomPosiKey", FtSeletedPanel.this.roomPosi).putExtra("PanelPosiKey", FtSeletedPanel.this.panelPosi));
        FtSeletedPanel.this.finish();
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSeletedPanel
 * JD-Core Version:    0.6.0
 */