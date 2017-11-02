package com.ex.ltech.onepiontfive.main;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import com.baidu.location.BDLocation;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import java.io.PrintStream;

public class GeofenceService extends Service
{
  MyBusiness business;
  GeoSpaceVo geoSpaceVo = null;
  LocationClient mLocClient;
  MyNotifyListener notifyListener;

  private void addGeofence()
  {
    this.business = new MyBusiness(this);
    this.geoSpaceVo = ((GeoSpaceVo)this.business.getData("CacheGeoSpaceVo ", GeoSpaceVo.class));
    if (this.geoSpaceVo == null)
    {
      this.geoSpaceVo = new GeoSpaceVo();
      this.geoSpaceVo.setLat(0.0D);
      this.geoSpaceVo.setLng(0.0D);
    }
    SDKInitializer.initialize(getApplicationContext());
    this.mLocClient = new LocationClient(getApplicationContext());
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setOpenGps(true);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setScanSpan(1000);
    this.mLocClient.setLocOption(localLocationClientOption);
    this.mLocClient.start();
    this.notifyListener = new MyNotifyListener();
    this.notifyListener.SetNotifyLocation(this.geoSpaceVo.getLat(), this.geoSpaceVo.getLng(), Constant.RANGE, "bd09ll");
    this.mLocClient.registerNotify(this.notifyListener);
  }

  @Nullable
  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    System.out.println("GeofenceService.onCreate");
    addGeofence();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    super.onStart(paramIntent, paramInt);
    System.out.println("GeofenceService.onStart");
  }

  class MyNotifyListener extends BDNotifyListener
  {
    MyNotifyListener()
    {
    }

    public void onNotify(BDLocation paramBDLocation, float paramFloat)
    {
      super.onNotify(paramBDLocation, paramFloat);
      GeoSpaceVo localGeoSpaceVo1 = (GeoSpaceVo)GeofenceService.this.business.getData("GohomeGeoSpaceVo ", GeoSpaceVo.class);
      GeoSpaceVo localGeoSpaceVo2 = (GeoSpaceVo)GeofenceService.this.business.getData("OuthomeGeoSpaceVo", GeoSpaceVo.class);
      if ((paramFloat < Constant.RANGE) && (localGeoSpaceVo1 != null) && (localGeoSpaceVo1.isStart()) && (!localGeoSpaceVo1.isGoHome()))
      {
        if ((localGeoSpaceVo2 != null) && (localGeoSpaceVo2.isStart()))
        {
          localGeoSpaceVo2.setIsOutHome(false);
          GeofenceService.this.business.putData("OuthomeGeoSpaceVo", localGeoSpaceVo2);
        }
        localGeoSpaceVo1.setIsGoHome(true);
        GeofenceService.this.business.putData("GohomeGeoSpaceVo ", localGeoSpaceVo1);
        System.out.println("GeofenceService.Go");
      }
      if ((paramFloat > Constant.RANGE) && (localGeoSpaceVo2 != null) && (localGeoSpaceVo2.isStart()) && (!localGeoSpaceVo2.isOutHome()))
      {
        if ((localGeoSpaceVo1 != null) && (localGeoSpaceVo1.isStart()))
        {
          localGeoSpaceVo1.setIsGoHome(false);
          GeofenceService.this.business.putData("GohomeGeoSpaceVo ", localGeoSpaceVo1);
        }
        localGeoSpaceVo2.setIsOutHome(true);
        GeofenceService.this.business.putData("OuthomeGeoSpaceVo", localGeoSpaceVo2);
        System.out.println("GeofenceService.Out");
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.GeofenceService
 * JD-Core Version:    0.6.0
 */