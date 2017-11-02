package com.ex.ltech.onepiontfive.main.more.GeoFencing;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatus.Builder;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.MyLocationData.Builder;
import com.baidu.mapapi.model.LatLng;
import com.ex.ltech.hongwai.StringUtil;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.Constant;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.time.FtRepeatDay;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceLatLngVo;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import com.fragmentmaster.app.Request;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.indris.material.RippleView;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FtGeoFencingHome extends MyBaseFt
  implements View.OnClickListener
{
  private String GEOFENCINGRESULT;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  GeoFencingBusiness business;
  GeoSpaceLatLngVo geoSpaceLatLngVo = new GeoSpaceLatLngVo();
  GeoSpaceVo geoSpaceVo;
  boolean isFirstLoc = true;
  private BaiduMap mBaiduMap;
  private MyLocationConfiguration.LocationMode mCurrentMode;
  private MapView mMapView;
  public MyLocationListenner myListener = new MyLocationListenner();
  private MyNotifyListener notifyListener;

  @Bind({2131558546})
  RelativeLayout rlRepeat;
  private int[] shotDay = { 2131100240, 2131100240, 2131100467, 2131100430, 2131100071, 2131100066, 2131100404, 2131100394 };

  @Bind({2131559203})
  TextView tvRepeat;

  @Bind({2131559201})
  TextView tvSenceHome;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  private View view;

  private void init()
  {
    this.business = new GeoFencingBusiness(getActivity());
    String str1 = UserFerences.getUserFerences(getActivity()).spFerences.getString("myHome_lng", "113.496793");
    String str2 = UserFerences.getUserFerences(getActivity()).spFerences.getString("myHome_lat", "22.226321");
    this.mMapView = ((MapView)this.view.findViewById(2131559198));
    this.mBaiduMap = this.mMapView.getMap();
    this.mBaiduMap.setMyLocationEnabled(true);
    LocationClient localLocationClient = new LocationClient(getActivity().getApplicationContext());
    localLocationClient.registerLocationListener(this.myListener);
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setOpenGps(true);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setScanSpan(10000);
    localLocationClient.setLocOption(localLocationClientOption);
    localLocationClient.start();
    this.notifyListener = new MyNotifyListener();
    this.notifyListener.SetNotifyLocation(Double.parseDouble(str2), Double.parseDouble(str1), Constant.RANGE, "bd09ll");
    this.mCurrentMode = MyLocationConfiguration.LocationMode.FOLLOWING;
    this.mBaiduMap.setMyLocationConfigeration(new MyLocationConfiguration(this.mCurrentMode, true, null));
    ((RelativeLayout)this.view.findViewById(2131559199)).setOnClickListener(this);
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.geoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + getRequest().getStringExtra("SceneInnerRemoteTyptKey"), GeoSpaceVo.class));
    if (this.geoSpaceVo == null)
      this.geoSpaceVo = new GeoSpaceVo();
    if (getRequest().getStringExtra("SceneInnerRemoteTyptKey").equals("GohomeGeoSpaceVo "))
    {
      this.tvTitleViewTitle.setText(2131099877);
      this.geoSpaceVo.setName(getString(2131099877));
    }
    while (true)
    {
      this.btnTitleViewEdit.setBackgroundResource(2130903706);
      this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          FtGeoFencingHome.this.finish();
        }
      });
      this.btnTitleViewEdit.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if ((FtGeoFencingHome.this.geoSpaceVo.getLat() != 0.0D) && (FtGeoFencingHome.this.geoSpaceVo.getLng() != 0.0D) && (FtGeoFencingHome.this.geoSpaceVo.getRepeatDayVos() != null) && (FtGeoFencingHome.this.geoSpaceVo.getTouchSceneIndex() != -1))
            FtGeoFencingHome.this.business.setGeoFencingInfo(new MyBusiness.MySendListener()
            {
              public void onFail()
              {
              }

              public void onOk(byte[] paramArrayOfByte)
              {
                String str = StringUtil.byte2Hexstr(paramArrayOfByte);
                if ((str.length() == 30) && (str.substring(18, 20).equalsIgnoreCase("9d")))
                {
                  FtGeoFencingHome.this.geoSpaceVo.setStart(true);
                  FtGeoFencingHome.this.business.setMySendListener(null);
                  if (!FtGeoFencingHome.this.getRequest().getStringExtra("SceneInnerRemoteTyptKey").equals("GohomeGeoSpaceVo "))
                    break label193;
                  FtGeoFencingHome.this.business.putData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", FtGeoFencingHome.this.geoSpaceVo);
                }
                while (true)
                {
                  FtGeoFencingHome.this.business.putData(DeviceListActivity.deviceMacAddress + "CacheGeoSpaceVo ", FtGeoFencingHome.this.geoSpaceVo);
                  FtGeoFencingHome.this.setResult(207);
                  FtGeoFencingHome.this.finish();
                  return;
                  label193: FtGeoFencingHome.this.business.putData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", FtGeoFencingHome.this.geoSpaceVo);
                }
              }

              public void onTimeOut()
              {
                Toast.makeText(FtGeoFencingHome.this.getActivity(), 2131100336, 0).show();
              }
            }
            , FtGeoFencingHome.this.geoSpaceVo);
          do
          {
            return;
            if (FtGeoFencingHome.this.geoSpaceVo.getRepeatDayVos() == null)
              continue;
            Toast.makeText(FtGeoFencingHome.this.getActivity(), 2131099940, 0).show();
            return;
          }
          while (FtGeoFencingHome.this.geoSpaceVo.getTouchSceneIndex() == -1);
          Toast.makeText(FtGeoFencingHome.this.getActivity(), 2131099944, 0).show();
        }
      });
      this.rlRepeat.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          FtGeoFencingHome.this.business.putCacheData(FtGeoFencingHome.this.geoSpaceVo);
          FtGeoFencingHome.this.startFragmentForResult(new Request(FtRepeatDay.class).putExtra("SensorForwardKey", true), 0);
        }
      });
      return;
      this.tvTitleViewTitle.setText(2131100143);
      this.geoSpaceVo.setName(getString(2131100143));
    }
  }

  private void initView()
  {
    String str = UserFerences.getUserFerences(getActivity()).spFerences.getString("geofencing", "");
    if (!str.equals(""))
      this.tvSenceHome.setText(str);
    this.tvSenceHome.setText(this.geoSpaceVo.getSceneName());
    this.tvRepeat.setText(this.geoSpaceVo.getRepeatDay());
  }

  private void showDetail()
  {
    startFragmentForResult(new Request(FtGeoFencingHomeScene.class).putExtra("GEO_TOUCH_SCENE_POSI", this.geoSpaceVo.getTouchSceneIndex()), 205);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131559199:
    }
    showDetail();
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    SDKInitializer.initialize(getActivity().getApplicationContext());
    this.view = paramLayoutInflater.inflate(2130968760, null);
    ButterKnife.bind(this, this.view);
    init();
    initView();
    setSlideable(false);
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
    if (paramInt2 == 203)
    {
      String str2 = paramRequest.getStringExtra("TimingRepeatDayResultKey");
      showShotDay((ArrayList)new Gson().fromJson(str2, new TypeToken()
      {
      }
      .getType()));
    }
    if (paramInt2 == 4000)
    {
      String str1 = paramRequest.getStringExtra("GeoSceneStrResultKey");
      int i = paramRequest.getIntExtra("GeoSceneIndexResultKey", 0);
      this.geoSpaceVo.setSceneName(str1);
      this.geoSpaceVo.setTouchSceneIndex(i);
      this.tvSenceHome.setText(this.geoSpaceVo.getSceneName());
    }
    System.out.print("");
  }

  public void onPause()
  {
    super.onPause();
    this.business.setMySendListener(null);
  }

  public void showShotDay(ArrayList<RepeatDayVo> paramArrayList)
  {
    String str = "";
    int i = 0;
    if (i < paramArrayList.size())
    {
      if (((RepeatDayVo)paramArrayList.get(i)).isSeleted())
        if (i != 0)
          break label73;
      label73: for (str = str + ((RepeatDayVo)paramArrayList.get(i)).getDay() + "\t\t"; ; str = str + getString(this.shotDay[i]) + "\t\t")
      {
        i++;
        break;
      }
    }
    if (str.length() == 0)
      return;
    this.geoSpaceVo.setRepeatDayVos(paramArrayList);
    this.geoSpaceVo.setRepeatDay(str);
    this.tvRepeat.setText(str);
  }

  public class MyLocationListenner
    implements BDLocationListener
  {
    public MyLocationListenner()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      if ((paramBDLocation == null) || (FtGeoFencingHome.this.mMapView == null));
      do
      {
        return;
        MyLocationData localMyLocationData = new MyLocationData.Builder().accuracy(paramBDLocation.getRadius()).direction(100.0F).latitude(paramBDLocation.getLatitude()).longitude(paramBDLocation.getLongitude()).build();
        FtGeoFencingHome.this.mBaiduMap.setMyLocationData(localMyLocationData);
      }
      while (!FtGeoFencingHome.this.isFirstLoc);
      FtGeoFencingHome.this.isFirstLoc = false;
      LatLng localLatLng = new LatLng(paramBDLocation.getLatitude(), paramBDLocation.getLongitude());
      FtGeoFencingHome.this.geoSpaceVo.setLat(localLatLng.latitude);
      FtGeoFencingHome.this.geoSpaceVo.setLng(localLatLng.longitude);
      MapStatus.Builder localBuilder = new MapStatus.Builder();
      localBuilder.target(localLatLng).zoom(18.0F);
      FtGeoFencingHome.this.mBaiduMap.animateMapStatus(MapStatusUpdateFactory.newMapStatus(localBuilder.build()));
    }

    public void onReceivePoi(BDLocation paramBDLocation)
    {
    }
  }

  class MyNotifyListener extends BDNotifyListener
  {
    MyNotifyListener()
    {
    }

    public void onNotify(BDLocation paramBDLocation, float paramFloat)
    {
      super.onNotify(paramBDLocation, paramFloat);
      ((Vibrator)FtGeoFencingHome.this.getActivity().getSystemService("vibrator")).vibrate(1000L);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.more.GeoFencing.FtGeoFencingHome
 * JD-Core Version:    0.6.0
 */