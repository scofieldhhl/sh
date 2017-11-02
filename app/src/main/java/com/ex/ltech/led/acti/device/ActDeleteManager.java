package com.ex.ltech.led.acti.device;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.indris.material.RippleView;
import java.util.ArrayList;

public class ActDeleteManager extends MyBaseFt
{

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private DeleteDevicesBussiness business;
  private ArrayList<Integer> cmd;
  private Home home;

  @Bind({2131558529})
  RadioButton rb5;

  @Bind({2131558528})
  RelativeLayout rlItem5;

  @Bind({2131558993})
  RelativeLayout rlTitle;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void deleteItem()
  {
    this.business.deleteDevices(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
        Toast.makeText(ActDeleteManager.this.getActivity(), "删除失败", 1).show();
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        for (int i = 0; i < 8; i++)
        {
          ((Room)ActDeleteManager.this.business.home.getRooms().get(i)).getDvcVos().clear();
          ((Room)ActDeleteManager.this.business.home.getRooms().get(i)).getExpandableLvInnerItemVos().clear();
        }
        Toast.makeText(ActDeleteManager.this.getActivity(), "删除成功", 1).show();
      }

      public void onTimeOut()
      {
        Toast.makeText(ActDeleteManager.this.getActivity(), "删除失败", 1).show();
      }
    });
  }

  private void init()
  {
    this.business = new DeleteDevicesBussiness(getActivity());
    this.business.initData();
  }

  private void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100024);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActDeleteManager.this.finish();
      }
    });
    this.tvTitleViewEdit.setVisibility(0);
    this.tvTitleViewEdit.setText(2131100063);
    this.tvTitleViewEdit.setTextColor(getResources().getColor(2131492897));
    this.tvTitleViewEdit.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActDeleteManager.this.deleteItem();
      }
    });
  }

  public void delete5(View paramView)
  {
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968604, null);
    ButterKnife.bind(this, this.view);
    initTitleView();
    init();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ActDeleteManager
 * JD-Core Version:    0.6.0
 */