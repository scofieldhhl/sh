package com.ex.ltech.onepiontfive.main.newscene;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.RoomLvData;
import com.ex.ltech.onepiontfive.main.vo.SceneTouchSensor;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;
import java.util.ArrayList;

public class FtChooseSensor extends MyBaseFt
{

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  ExpandableLvSceneBusiness business;
  String conditioncondition;
  int delayTouch = 0;
  View foot;

  @Bind({2131558961})
  ListView lv;
  private String mac;
  ArrayList<Dvc> tempDvcVos;
  ToggleButton toggleButton1;
  ToggleButton toggleButton2;
  ToggleButton toggleButton3;
  ToggleButton toggleButton4;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  private void initTitle()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (FtChooseSensor.this.tempDvcVos.size() > 0)
        {
          SceneTouchSensor localSceneTouchSensor = new SceneTouchSensor();
          localSceneTouchSensor.setSensors(FtChooseSensor.this.tempDvcVos);
          localSceneTouchSensor.setDelayTouch(FtChooseSensor.this.delayTouch);
          RoomLvData localRoomLvData = FtChooseSensor.this.business.getCacheData();
          localRoomLvData.setTouchSensors(localSceneTouchSensor);
          localRoomLvData.setDelayTouch(FtChooseSensor.this.delayTouch);
          localSceneTouchSensor.setDelayTouch(FtChooseSensor.this.delayTouch);
          localRoomLvData.setSwich(true);
          FtChooseSensor.this.business.putData4ClassName(FtChooseSensor.this.mac, localSceneTouchSensor);
          FtChooseSensor.this.business.putCacheData(localRoomLvData);
          FtChooseSensor.this.setResult(212, FtChooseSensor.this.getRequest().putExtra("ConditionExtraKey", FtChooseSensor.this.getString(2131099885)));
        }
        FtChooseSensor.this.finish();
      }
    });
    this.tvTitleViewEdit.setText(2131100206);
    this.tvTitleViewEdit.setVisibility(View.VISIBLE);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492877));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    this.tvTitleViewTitle.setText(2131100408);
    this.business = new ExpandableLvSceneBusiness(getActivity());
    this.tempDvcVos = this.business.getAddSensorListData();
    this.lv.setDividerHeight(0);
    if (this.tempDvcVos.size() > 0)
      this.lv.addFooterView(this.foot);
    this.lv.setAdapter(new BaseAdapter()
    {
      public int getCount()
      {
        return FtChooseSensor.this.tempDvcVos.size();
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
        ImageView localImageView;
        if (paramView == null)
        {
          paramView = LayoutInflater.from(FtChooseSensor.this.getActivity()).inflate(2130968715, null);
          localItemHolder = new ItemHolder();
          localItemHolder.name = ((TextView)paramView.findViewById(2131559008));
          localItemHolder.seleted = ((ImageView)paramView.findViewById(2131559062));
          paramView.setTag(localItemHolder);
          Dvc localDvc = (Dvc)FtChooseSensor.this.tempDvcVos.get(paramInt);
          localItemHolder.name.setText(localDvc.getName());
          localImageView = localItemHolder.seleted;
          if (!localDvc.isClickSeleted())
            break label147;
        }
        label147: for (int i = 2130903786; ; i = 2130903784)
        {
          localImageView.setBackgroundResource(i);
          paramView.setOnClickListener(new View.OnClickListener(paramInt)
          {
            public void onClick(View paramView)
            {
              Dvc localDvc = (Dvc)FtChooseSensor.this.tempDvcVos.get(this.val$i);
              if (!((Dvc)FtChooseSensor.this.tempDvcVos.get(this.val$i)).isClickSeleted());
              for (boolean bool = true; ; bool = false)
              {
                localDvc.setIsClickSeleted(bool);
                FtChooseSensor.8.this.notifyDataSetChanged();
                return;
              }
            }
          });
          return paramView;
          localItemHolder = (ItemHolder)paramView.getTag();
          break;
        }
      }

      class ItemHolder
      {
        public TextView name;
        public ImageView seleted;

        ItemHolder()
        {
        }
      }
    });
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
    {
      this.view = paramLayoutInflater.inflate(2130968747, null);
      this.foot = paramLayoutInflater.inflate(2130968880, null);
      this.mac = UserFerences.getUserFerences(getActivity().getApplicationContext()).getValue("GateWayMacIdKey");
      ButterKnife.bind(this, this.view);
      this.toggleButton1 = ((ToggleButton)this.foot.findViewById(2131559467));
      this.toggleButton2 = ((ToggleButton)this.foot.findViewById(2131559468));
      this.toggleButton3 = ((ToggleButton)this.foot.findViewById(2131559469));
      this.toggleButton4 = ((ToggleButton)this.foot.findViewById(2131559470));
      this.toggleButton1.setOnToggleChanged(new ToggleButton.OnToggleChanged()
      {
        public void onToggle(boolean paramBoolean)
        {
          if (paramBoolean)
          {
            FtChooseSensor.this.toggleButton2.setToggleOff();
            FtChooseSensor.this.toggleButton3.setToggleOff();
            FtChooseSensor.this.toggleButton4.setToggleOff();
            FtChooseSensor.this.delayTouch = 0;
            return;
          }
          FtChooseSensor.this.delayTouch = 0;
        }
      });
      this.toggleButton2.setOnToggleChanged(new ToggleButton.OnToggleChanged()
      {
        public void onToggle(boolean paramBoolean)
        {
          if (paramBoolean)
          {
            FtChooseSensor.this.toggleButton1.setToggleOff();
            FtChooseSensor.this.toggleButton3.setToggleOff();
            FtChooseSensor.this.toggleButton4.setToggleOff();
            FtChooseSensor.this.delayTouch = 1;
            return;
          }
          FtChooseSensor.this.delayTouch = 0;
        }
      });
      this.toggleButton3.setOnToggleChanged(new ToggleButton.OnToggleChanged()
      {
        public void onToggle(boolean paramBoolean)
        {
          if (paramBoolean)
          {
            FtChooseSensor.this.toggleButton2.setToggleOff();
            FtChooseSensor.this.toggleButton1.setToggleOff();
            FtChooseSensor.this.toggleButton4.setToggleOff();
            FtChooseSensor.this.delayTouch = 5;
            return;
          }
          FtChooseSensor.this.delayTouch = 0;
        }
      });
      this.toggleButton4.setOnToggleChanged(new ToggleButton.OnToggleChanged()
      {
        public void onToggle(boolean paramBoolean)
        {
          if (paramBoolean)
          {
            FtChooseSensor.this.toggleButton2.setToggleOff();
            FtChooseSensor.this.toggleButton3.setToggleOff();
            FtChooseSensor.this.toggleButton1.setToggleOff();
            FtChooseSensor.this.delayTouch = 10;
            return;
          }
          FtChooseSensor.this.delayTouch = 0;
        }
      });
      initTitle();
    }
    ButterKnife.bind(this, this.view);
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 202)
    {
      setResult(202);
      finish();
    }
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.newscene.FtChooseSensor
 * JD-Core Version:    0.6.0
 */