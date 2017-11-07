package com.ex.ltech.onepiontfive.main.room.sensor;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.newscene.OnSwichOrItemClickListener;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.RoomLvData;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.ArrayList;
import java.util.Iterator;

public class FtSensorInfo extends MyBaseFt
  implements OnSwichOrItemClickListener, View.OnClickListener
{
  DvcListAdapter adapter;
  BaseAdapter adapter2;

  @Bind({R.id.btn_acti_mode_menu})
  Button btnActiModeMenu;
  ExpandableLvSceneBusiness business;

  @Bind({2131559270})
  RippleView device;
  ExpandableLvSceneBusiness expandableLvSceneBusiness;

  @Bind({2131559269})
  LinearLayout llRippleView;

  @Bind({2131558769})
  ViewPager pager;

  @Bind({R.id.rl_act_timing_mode})
  RelativeLayout rlActTimingMode;
  String seletedSceneName = "";
  int seletedScenePosi = 0;

  @Bind({2131559271})
  RippleView sence;
  SensorBiz sensorBiz;
  View view;
  View view1;
  View view2;
  SensorVo vo;

  private void setSecondView(SwipeMenuListView paramSwipeMenuListView)
  {
    this.business = new ExpandableLvSceneBusiness(getActivity());
    for (int i = 0; i < this.business.getSmartScenes().smartScenes.size(); i++)
      ((Scene)this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
    if ((this.vo.getTouchScenePosi() != -1) && (this.vo.getTouchScenePosi() < this.business.getSmartScenes().smartScenes.size()))
      ((Scene)this.business.getSmartScenes().smartScenes.get(this.vo.getTouchScenePosi())).setSwich(true);
    paramSwipeMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    if (this.business.getSmartScenes().smartScenes.size() > 0)
      paramSwipeMenuListView.setVisibility(View.VISIBLE);
    paramSwipeMenuListView.setDividerHeight(0);
    3 local3 = new BaseAdapter()
    {
      public int getCount()
      {
        return FtSensorInfo.this.business.getSmartScenes().smartScenes.size();
      }

      public Object getItem(int paramInt)
      {
        return null;
      }

      public long getItemId(int paramInt)
      {
        return 0L;
      }

      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        ItemHolder localItemHolder;
        Scene localScene;
        if (paramView == null)
        {
          paramView = LayoutInflater.from(FtSensorInfo.this.getActivity()).inflate(2130968816, null);
          localItemHolder = new ItemHolder();
          localItemHolder.condition = ((TextView)paramView.findViewById(2131559111));
          localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
          localItemHolder.icNext = ((TextView)paramView.findViewById(2131558971));
          localItemHolder.status = ((TextView)paramView.findViewById(2131559169));
          localItemHolder.swich = ((Button)paramView.findViewById(2131559047));
          localItemHolder.ic = ((ImageView)paramView.findViewById(2131558883));
          paramView.setTag(localItemHolder);
          localScene = (Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(paramInt);
          if ((localScene.getCondition() == null) || (!localScene.getCondition().equals(FtSensorInfo.this.getString(2131100083))))
            break label330;
          localItemHolder.ic.setBackgroundResource(2130903762);
          label181: localItemHolder.ic.setBackgroundResource(2130903560);
          localItemHolder.name.setText(localScene.getName());
          if (!localScene.isSwich())
            break label384;
          localItemHolder.swich.setBackgroundResource(2130903786);
        }
        String str1;
        while (true)
        {
          localItemHolder.swich.setOnClickListener(new View.OnClickListener(paramInt)
          {
            public void onClick(View paramView)
            {
              for (int i = 0; i < FtSensorInfo.this.business.getSmartScenes().smartScenes.size(); i++)
                ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
              ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(this.val$i)).setSwich(true);
              FtSensorInfo.this.adapter.notifyDataSetChanged();
              FtSensorInfo.this.seletedScenePosi = this.val$i;
              FtSensorInfo.this.seletedSceneName = ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(this.val$i)).getName();
              FtSensorInfo.3.this.notifyDataSetChanged();
            }
          });
          paramView.setOnClickListener(new View.OnClickListener(paramInt)
          {
            public void onClick(View paramView)
            {
              for (int i = 0; i < FtSensorInfo.this.business.getSmartScenes().smartScenes.size(); i++)
                ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
              ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(this.val$i)).setSwich(true);
              FtSensorInfo.this.adapter.notifyDataSetChanged();
              FtSensorInfo.this.seletedScenePosi = this.val$i;
              FtSensorInfo.this.seletedSceneName = ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(this.val$i)).getName();
              FtSensorInfo.3.this.notifyDataSetChanged();
            }
          });
          str1 = "";
          Iterator localIterator = localScene.getRoomNames().iterator();
          while (localIterator.hasNext())
          {
            String str2 = (String)localIterator.next();
            str1 = str1 + str2 + "\t\t";
          }
          localItemHolder = (ItemHolder)paramView.getTag();
          break;
          label330: if ((localScene.getCondition() != null) && (localScene.getCondition().equals(FtSensorInfo.this.getString(2131100045))))
          {
            localItemHolder.ic.setBackgroundResource(2130903088);
            break label181;
          }
          localItemHolder.ic.setBackgroundResource(2130903227);
          break label181;
          label384: localItemHolder.swich.setBackgroundResource(2130903784);
        }
        localItemHolder.status.setText(str1);
        return paramView;
      }

      class ItemHolder
      {
        public TextView condition;
        public ImageView ic;
        public TextView icNext;
        public TextView name;
        public TextView status;
        public Button swich;

        ItemHolder()
        {
        }
      }
    };
    this.adapter2 = local3;
    paramSwipeMenuListView.setAdapter(local3);
  }

  public void initView()
  {
    this.device.setOnClickListener(this);
    this.sence.setOnClickListener(this);
    this.btnActiModeMenu.setOnClickListener(this);
    this.view1 = LayoutInflater.from(getActivity()).inflate(2130968780, null);
    this.view2 = LayoutInflater.from(getActivity()).inflate(2130968782, null);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131559271:
      this.vo.setTouchType("SensorTouchSceneType");
      this.pager.setCurrentItem(1);
      this.device.setBackgroundResource(2130903244);
      this.sence.setBackgroundResource(2130903247);
      return;
    case 2131559270:
      this.vo.setTouchType("SensorTouchDeviceType");
      this.pager.setCurrentItem(0);
      this.device.setBackgroundResource(2130903245);
      this.sence.setBackgroundResource(2130903246);
      return;
    case R.id.btn_acti_mode_menu:
    }
    this.vo.setRooms(this.expandableLvSceneBusiness.getRoomExpandableLvInnerItem().getRooms());
    SensorVo localSensorVo = this.vo;
    if (this.seletedSceneName.length() > 0);
    for (int i = this.seletedScenePosi; ; i = -1)
    {
      localSensorVo.setTouchScenePosi(i);
      this.vo.setTouchSceneName(this.seletedSceneName);
      this.sensorBiz.saveCacheData(this.vo);
      setResult(10000);
      finish();
      return;
    }
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    FragmentActivity localFragmentActivity;
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968781, null);
      ButterKnife.bind(this, this.view);
      initView();
      this.expandableLvSceneBusiness = new ExpandableLvSceneBusiness(getActivity());
      this.sensorBiz = new SensorBiz(getActivity());
      this.vo = this.sensorBiz.getCacheSensorVo();
      localFragmentActivity = getActivity();
      if (this.vo.getRooms() != null)
        break label164;
    }
    label164: for (RoomLvData localRoomLvData = this.expandableLvSceneBusiness.getAddBlubListData(); ; localRoomLvData = this.expandableLvSceneBusiness.getAddTaskListData(this.vo.getRooms()))
    {
      this.adapter = new DvcListAdapter(localFragmentActivity, this, localRoomLvData);
      this.vo.setRooms(this.expandableLvSceneBusiness.getRoomExpandableLvInnerItem().getRooms());
      this.vo.setTouchSceneName(this.seletedSceneName);
      this.sensorBiz.saveCacheData(this.vo);
      ButterKnife.bind(this, this.view);
      return this.view;
    }
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 20000)
    {
      this.vo = this.sensorBiz.getCacheSensorVo();
      int i = paramRequest.getIntExtra("RoomPosiKey", 0);
      int j = paramRequest.getIntExtra("PanelPosiKey", 0);
      this.expandableLvSceneBusiness.savePanelData(i, j, (RoomLvChildVo)((Room)this.vo.getRooms().get(i)).getExpandableLvInnerItemVos().get(j));
      this.adapter.notifyDataSetChanged();
    }
  }

  public void onItemClick(int paramInt1, int paramInt2, int paramInt3)
  {
    startFragmentForResult(new Request(FtSeletedPanel.class).putExtra("RoomPosiKey", paramInt1).putExtra("PanelPosiKey", paramInt2), 201);
  }

  public void onSwich(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.expandableLvSceneBusiness.onDeviceSwich(paramInt1, paramInt2, paramBoolean, true);
    this.adapter.notifyDataSetChanged();
    for (int i = 0; i < this.business.getSmartScenes().smartScenes.size(); i++)
      ((Scene)this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
    this.adapter2.notifyDataSetChanged();
  }

  public void onToggleSwich(String paramString)
  {
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.pager.setAdapter(new MyPagerAdapter(null));
    this.pager.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
      }

      public void onPageSelected(int paramInt)
      {
        if (paramInt == 1)
        {
          FtSensorInfo.this.vo.setTouchType("SensorTouchSceneType");
          FtSensorInfo.this.device.setBackgroundResource(2130903244);
          FtSensorInfo.this.sence.setBackgroundResource(2130903247);
          FtSensorInfo.this.expandableLvSceneBusiness.clearDeviceSeleted();
          FtSensorInfo.this.adapter.notifyDataSetChanged();
          return;
        }
        FtSensorInfo.this.vo.setTouchType("SensorTouchDeviceType");
        FtSensorInfo.this.device.setBackgroundResource(2130903245);
        FtSensorInfo.this.sence.setBackgroundResource(2130903246);
        for (int i = 0; i < FtSensorInfo.this.business.getSmartScenes().smartScenes.size(); i++)
          ((Scene)FtSensorInfo.this.business.getSmartScenes().smartScenes.get(i)).setSwich(false);
        FtSensorInfo.this.adapter2.notifyDataSetChanged();
      }
    });
    if (this.vo.getTouchType().equals("SensorTouchDeviceType"))
      onClick(this.device);
    if (this.vo.getTouchType().equals("SensorTouchSceneType"))
      onClick(this.sence);
  }

  private class MyPagerAdapter extends PagerAdapter
  {
    private MyPagerAdapter()
    {
    }

    public void destroyItem(ViewGroup paramViewGroup, int paramInt, Object paramObject)
    {
      paramViewGroup.removeView((View)paramObject);
    }

    public int getCount()
    {
      return 2;
    }

    public Object instantiateItem(ViewGroup paramViewGroup, int paramInt)
    {
      if (paramInt == 0)
      {
        paramViewGroup.addView(FtSensorInfo.this.view1);
        ExpandableListView localExpandableListView = (ExpandableListView)FtSensorInfo.this.view1.findViewById(2131559163);
        localExpandableListView.setDividerHeight(0);
        localExpandableListView.setGroupIndicator(null);
        localExpandableListView.setAdapter(FtSensorInfo.this.adapter);
        return FtSensorInfo.this.view1;
      }
      paramViewGroup.addView(FtSensorInfo.this.view2);
      SwipeMenuListView localSwipeMenuListView = (SwipeMenuListView)FtSensorInfo.this.view2.findViewById(2131558961);
      FtSensorInfo.this.setSecondView(localSwipeMenuListView);
      return FtSensorInfo.this.view2;
    }

    public boolean isViewFromObject(View paramView, Object paramObject)
    {
      return paramView == paramObject;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.FtSensorInfo
 * JD-Core Version:    0.6.0
 */