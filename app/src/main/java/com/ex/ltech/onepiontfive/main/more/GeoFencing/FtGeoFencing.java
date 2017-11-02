package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.AtMain;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.io.PrintStream;

public class FtGeoFencing extends MyBaseFt
  implements View.OnClickListener
{

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private Button btn_home_on_off;
  private Button btn_leave_home_on_off;
  private Button btn_refresh;
  GeoFencingBusiness business;
  int count4A2 = 0;
  AlertDialog dialog;
  GeoSpaceVo goHomeGeoSpaceVo;
  GeoSpaceVo outHomeGeoSpaceVo;

  @Bind({2131559190})
  RelativeLayout rlHome;

  @Bind({2131559194})
  RelativeLayout rlLeaveHome;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void getData()
  {
    this.goHomeGeoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", GeoSpaceVo.class));
    if (this.goHomeGeoSpaceVo == null)
    {
      this.goHomeGeoSpaceVo = new GeoSpaceVo();
      this.business.putData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", this.goHomeGeoSpaceVo);
    }
    this.outHomeGeoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", GeoSpaceVo.class));
    if (this.outHomeGeoSpaceVo == null)
    {
      this.outHomeGeoSpaceVo = new GeoSpaceVo();
      this.business.putData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", this.outHomeGeoSpaceVo);
    }
  }

  private void init()
  {
    this.btn_home_on_off = ((Button)this.view.findViewById(2131559193));
    this.btn_leave_home_on_off = ((Button)this.view.findViewById(2131559197));
    this.btn_refresh = ((Button)this.view.findViewById(2131559189));
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100074);
    this.business = new GeoFencingBusiness(getActivity());
    getData();
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtGeoFencing.this.finish();
      }
    });
    refreshGeoFencing(1);
    new Handler()
    {
    }
    .postDelayed(new Runnable()
    {
      public void run()
      {
        if (FtGeoFencing.this.dialog != null);
        for (int i = 1; ; i = 0)
        {
          if ((i & FtGeoFencing.this.dialog.isShowing()) != 0)
            FtGeoFencing.this.refreshGeoFencing(2);
          return;
        }
      }
    }
    , 1000L);
    new Handler()
    {
    }
    .postDelayed(new Runnable()
    {
      public void run()
      {
        if (FtGeoFencing.this.dialog != null);
        for (int i = 1; ; i = 0)
        {
          if ((i & FtGeoFencing.this.dialog.isShowing()) != 0)
            FtGeoFencing.this.dialog.dismiss();
          return;
        }
      }
    }
    , 2000L);
    this.dialog = ProgressDialog.show(getActivity(), "", getString(2131100002), false);
    this.dialog.show();
  }

  private void initView()
  {
    setBtnText();
    this.btn_home_on_off.setOnClickListener(this);
    this.btn_leave_home_on_off.setOnClickListener(this);
    this.rlHome.setOnClickListener(this);
    this.rlLeaveHome.setOnClickListener(this);
    this.btn_refresh.setOnClickListener(this);
  }

  private void refreshGeoFencing(int paramInt)
  {
    this.business.syncGeoFencingInfo(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str3(paramArrayOfByte);
        GeoSpaceVo localGeoSpaceVo = FtGeoFencing.this.business.compareWithReturnInfo(StringUtils.btye2Str(paramArrayOfByte));
        if (localGeoSpaceVo != null)
        {
          if (!localGeoSpaceVo.isGoHome())
            break label200;
          boolean bool2 = FtGeoFencing.this.goHomeGeoSpaceVo.isStart();
          FtGeoFencing.this.goHomeGeoSpaceVo = localGeoSpaceVo;
          FtGeoFencing.this.goHomeGeoSpaceVo.setStart(bool2);
          localGeoSpaceVo.setName(FtGeoFencing.this.getString(2131099877));
          FtGeoFencing.this.business.putData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", FtGeoFencing.this.goHomeGeoSpaceVo);
          System.out.println("goHomeGeoSpaceVo = " + localGeoSpaceVo.toString());
        }
        while (true)
        {
          if (str.substring(18, 20).equalsIgnoreCase("a7"))
          {
            FtGeoFencing localFtGeoFencing = FtGeoFencing.this;
            localFtGeoFencing.count4A2 = (1 + localFtGeoFencing.count4A2);
          }
          if ((FtGeoFencing.this.count4A2 == 4) || (localGeoSpaceVo != null))
            FtGeoFencing.this.count4A2 = 0;
          return;
          label200: boolean bool1 = FtGeoFencing.this.outHomeGeoSpaceVo.isStart();
          FtGeoFencing.this.outHomeGeoSpaceVo = localGeoSpaceVo;
          FtGeoFencing.this.outHomeGeoSpaceVo.setStart(bool1);
          FtGeoFencing.this.outHomeGeoSpaceVo.setName(FtGeoFencing.this.getString(2131100143));
          FtGeoFencing.this.business.putData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", FtGeoFencing.this.outHomeGeoSpaceVo);
          FtGeoFencing.this.business.setMySendListener(null);
          FtGeoFencing.this.setBtnText();
          FtGeoFencing.this.dialog.dismiss();
          System.out.println("outHomeGeoSpaceVo = " + localGeoSpaceVo.toString());
        }
      }

      public void onTimeOut()
      {
        FtGeoFencing.this.dialog.dismiss();
      }
    }
    , paramInt);
  }

  private void responseMessage(String paramString1, String paramString2)
  {
    this.business.responseMessage(null, paramString1, paramString2);
  }

  private void setBtnText()
  {
    int i = 2131099956;
    int j;
    if ((this.goHomeGeoSpaceVo.getLat() != 0.0D) && (this.goHomeGeoSpaceVo.getLng() != 0.0D) && (this.goHomeGeoSpaceVo.getRepeatDayVos() != null) && (this.goHomeGeoSpaceVo.getTouchSceneIndex() != -1))
    {
      this.btn_home_on_off.setVisibility(0);
      Button localButton2 = this.btn_home_on_off;
      if (this.goHomeGeoSpaceVo.isStart())
      {
        j = 2131100412;
        localButton2.setText(j);
      }
    }
    while (true)
    {
      if ((this.outHomeGeoSpaceVo.getLat() == 0.0D) || (this.outHomeGeoSpaceVo.getLng() == 0.0D) || (this.outHomeGeoSpaceVo.getRepeatDayVos() == null) || (this.outHomeGeoSpaceVo.getTouchSceneIndex() == -1))
        break label176;
      this.btn_leave_home_on_off.setVisibility(0);
      Button localButton1 = this.btn_leave_home_on_off;
      if (this.outHomeGeoSpaceVo.isStart())
        i = 2131100412;
      localButton1.setText(i);
      return;
      j = i;
      break;
      this.btn_home_on_off.setVisibility(8);
    }
    label176: this.btn_leave_home_on_off.setVisibility(8);
  }

  private void showDetail()
  {
    startFragment(new Request(FtGeoFencingHome.class));
  }

  public void onClick(View paramView)
  {
    boolean bool = true;
    switch (paramView.getId())
    {
    case 2131559189:
    case 2131559191:
    case 2131559192:
    case 2131559195:
    case 2131559196:
    default:
      return;
    case 2131559193:
      GeoSpaceVo localGeoSpaceVo2 = this.goHomeGeoSpaceVo;
      if (!this.goHomeGeoSpaceVo.isStart());
      while (true)
      {
        localGeoSpaceVo2.setStart(bool);
        setBtnText();
        this.business.putData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", this.goHomeGeoSpaceVo);
        if (!this.goHomeGeoSpaceVo.isStart())
          break;
        this.business.putData(DeviceListActivity.deviceMacAddress + "CacheGeoSpaceVo ", this.goHomeGeoSpaceVo);
        return;
        bool = false;
      }
    case 2131559197:
      GeoSpaceVo localGeoSpaceVo1 = this.outHomeGeoSpaceVo;
      if (!this.outHomeGeoSpaceVo.isStart());
      while (true)
      {
        localGeoSpaceVo1.setStart(bool);
        setBtnText();
        this.business.putData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", this.outHomeGeoSpaceVo);
        if (!this.outHomeGeoSpaceVo.isStart())
          break;
        this.business.putData(DeviceListActivity.deviceMacAddress + "CacheGeoSpaceVo ", this.outHomeGeoSpaceVo);
        return;
        bool = false;
      }
    case 2131559190:
      startFragmentForResult(new Request(FtGeoFencingHome.class).putExtra("SceneInnerRemoteTyptKey", "GohomeGeoSpaceVo "), 0);
      return;
    case 2131559194:
    }
    startFragmentForResult(new Request(FtGeoFencingHome.class).putExtra("SceneInnerRemoteTyptKey", "OuthomeGeoSpaceVo"), 0);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    this.view = paramLayoutInflater.inflate(2130968759, null);
    ButterKnife.bind(this, this.view);
    init();
    initView();
    return this.view;
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
    try
    {
      if ((this.goHomeGeoSpaceVo.isStart() | this.outHomeGeoSpaceVo.isStart()))
        AtMain.instance.addGeofence();
      while (true)
      {
        this.business.setMySendListener(null);
        return;
        AtMain.instance.removeGeofence();
      }
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void onFragmentResult(int paramInt1, int paramInt2, Request paramRequest)
  {
    super.onFragmentResult(paramInt1, paramInt2, paramRequest);
    if (paramInt2 == 207)
    {
      getData();
      setBtnText();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencing
 * JD-Core Version:    0.6.0
 */