package com.ex.ltech.led.acti.main;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.ex.ltech.MyDb;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.vo.CityVo;
import com.ex.ltech.led.vo.RealtimeTemp;
import com.ex.ltech.led.vo.RealtimeTemp.HeWeather5Bean;
import com.ex.ltech.led.vo.RealtimeTemp.HeWeather5Bean.NowBean;
import com.ex.ltech.led.vo.RespWeatherVo;
import com.ex.ltech.led.vo.RespWeatherVo.HeWeather5Bean;
import com.ex.ltech.led.vo.RespWeatherVo.HeWeather5Bean.DailyForecastBean;
import com.ex.ltech.led.vo.RespWeatherVo.HeWeather5Bean.DailyForecastBean.CondBean;
import com.ex.ltech.led.vo.RespWeatherVo.HeWeather5Bean.DailyForecastBean.TmpBean;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Locale;
import org.apache.http.Header;

public class LocationAndWeatherBusiness
{
  public static final int locatNoOk = 2;
  public static final int locatOk = 1;
  public static final int weatherNoOk = 4;
  public static final int weatherOk = 3;
  public boolean canGetWeather = false;
  private String city = null;
  private Gson gs;
  LocationManager locationManager;
  private LocationClient mLocationClient;
  MyLocationListener mMyLocationListener;
  public boolean netWorkStatus;
  private Handler pHandler;
  private Context pct;
  CityVo vo;

  public LocationAndWeatherBusiness(Context paramContext)
  {
    this.pct = paramContext;
    this.gs = new Gson();
    this.vo = new CityVo();
  }

  private List<Address> getLocationList(double paramDouble1, double paramDouble2)
  {
    Geocoder localGeocoder = new Geocoder(this.pct, Locale.getDefault());
    try
    {
      List localList = localGeocoder.getFromLocation(paramDouble1, paramDouble2, 1);
      return localList;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  private void getWeather(String paramString)
  {
    new AsyncHttpClient().get(this.pct, "https://free-api.heweather.com/v5/now?city=" + paramString + "&key=cba337ab22b6420ca978f97605ce2b00", null, new TextHttpResponseHandler(paramString)
    {
      public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
      {
      }

      public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
      {
        RealtimeTemp localRealtimeTemp = (RealtimeTemp)new Gson().fromJson(paramString, RealtimeTemp.class);
        try
        {
          LocationAndWeatherBusiness.this.getWeather4City(this.val$city, ((RealtimeTemp.HeWeather5Bean)localRealtimeTemp.getHeWeather5().get(0)).getNow().getTmp());
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    });
  }

  private void getWeather4City(String paramString1, String paramString2)
  {
    new AsyncHttpClient().get(this.pct, "https://free-api.heweather.com/v5/forecast?city=" + paramString1 + "&key=cba337ab22b6420ca978f97605ce2b00&lang=En", null, new TextHttpResponseHandler(paramString2)
    {
      public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
      {
      }

      public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
      {
        try
        {
          RespWeatherVo localRespWeatherVo = (RespWeatherVo)new Gson().fromJson(paramString, RespWeatherVo.class);
          LocationAndWeatherBusiness.this.vo.morningInfo = ((RespWeatherVo.HeWeather5Bean.DailyForecastBean)((RespWeatherVo.HeWeather5Bean)localRespWeatherVo.getHeWeather5().get(0)).getDaily_forecast().get(0)).getCond().getTxt_d();
          LocationAndWeatherBusiness.this.vo.nightInfo = ((RespWeatherVo.HeWeather5Bean.DailyForecastBean)((RespWeatherVo.HeWeather5Bean)localRespWeatherVo.getHeWeather5().get(0)).getDaily_forecast().get(0)).getCond().getTxt_n();
          LocationAndWeatherBusiness.this.vo.setH_tmp(((RespWeatherVo.HeWeather5Bean.DailyForecastBean)((RespWeatherVo.HeWeather5Bean)localRespWeatherVo.getHeWeather5().get(0)).getDaily_forecast().get(0)).getTmp().getMax());
          LocationAndWeatherBusiness.this.vo.setL_tmp(((RespWeatherVo.HeWeather5Bean.DailyForecastBean)((RespWeatherVo.HeWeather5Bean)localRespWeatherVo.getHeWeather5().get(0)).getDaily_forecast().get(0)).getTmp().getMin());
          LocationAndWeatherBusiness.this.vo.setTemp(this.val$temp);
          MyDb.getInstance(MyApp.getApp()).putBean("cityVo", LocationAndWeatherBusiness.this.vo);
          LocationAndWeatherBusiness.this.pHandler.sendEmptyMessage(3);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          LocationAndWeatherBusiness.this.pHandler.sendEmptyMessage(4);
        }
      }
    });
  }

  private void initLocation()
  {
    LocationClientOption.LocationMode localLocationMode = LocationClientOption.LocationMode.Hight_Accuracy;
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setLocationMode(localLocationMode);
    localLocationClientOption.setCoorType("gcj02");
    localLocationClientOption.setScanSpan(1000);
    localLocationClientOption.setIsNeedAddress(true);
    localLocationClientOption.setOpenGps(true);
    localLocationClientOption.setLocationNotify(true);
    localLocationClientOption.setIgnoreKillProcess(true);
    localLocationClientOption.setEnableSimulateGps(false);
    localLocationClientOption.setIsNeedLocationDescribe(true);
    localLocationClientOption.setIsNeedLocationPoiList(true);
    this.mLocationClient.setLocOption(localLocationClientOption);
  }

  public void getCity()
  {
    this.locationManager = ((LocationManager)this.pct.getSystemService("location"));
    if (((this.locationManager.getProvider("network") == null) && (this.locationManager.getProvider("gps") == null)) || ((ActivityCompat.checkSelfPermission(this.pct, "android.permission.ACCESS_FINE_LOCATION") != 0) && (ActivityCompat.checkSelfPermission(this.pct, "android.permission.ACCESS_COARSE_LOCATION") != 0)))
      return;
    this.locationManager.requestLocationUpdates("network", 1000L, 10.0F, new LocationListener()
    {
      public void onLocationChanged(Location paramLocation)
      {
        double d1 = paramLocation.getLatitude();
        double d2 = paramLocation.getLongitude();
        List localList = LocationAndWeatherBusiness.this.getLocationList(d1, d2);
        if ((localList != null) && (!localList.isEmpty()))
        {
          String str = ((Address)localList.get(0)).getLocality().replace(" ", "%20");
          LocationAndWeatherBusiness.this.getWeather(str);
          System.out.println("");
        }
      }

      public void onProviderDisabled(String paramString)
      {
      }

      public void onProviderEnabled(String paramString)
      {
      }

      public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
      {
      }
    });
  }

  public CityVo getCityVo()
  {
    CityVo localCityVo = (CityVo)MyDb.getInstance(MyApp.getApp()).getBean("cityVo", CityVo.class);
    if (localCityVo != null)
      this.vo = localCityVo;
    return this.vo;
  }

  public void setpHandler(Handler paramHandler)
  {
    this.pHandler = paramHandler;
  }

  public void startLocat()
  {
    this.mLocationClient = new LocationClient(this.pct);
    this.mMyLocationListener = new MyLocationListener();
    this.mLocationClient.registerLocationListener(this.mMyLocationListener);
    initLocation();
    this.mLocationClient.start();
  }

  public void stopLocat()
  {
    if (this.mLocationClient != null)
    {
      this.mLocationClient.unRegisterLocationListener(this.mMyLocationListener);
      this.mLocationClient.stop();
    }
  }

  public class MyLocationListener
    implements BDLocationListener
  {
    public MyLocationListener()
    {
    }

    public void onReceiveLocation(BDLocation paramBDLocation)
    {
      if ((paramBDLocation != null) && (paramBDLocation.getCity() != null))
      {
        String str1 = paramBDLocation.getCountry();
        if (str1 != null)
          LocationAndWeatherBusiness.this.vo.setCountry(str1);
        String str2 = paramBDLocation.getDistrict();
        if (str2 != null)
          LocationAndWeatherBusiness.this.vo.setArea(str2);
        String str3 = paramBDLocation.getCity();
        String str4;
        if (str3.indexOf("市") != -1)
        {
          LocationAndWeatherBusiness.this.vo.setCity(str3.substring(0, str3.indexOf("市")));
          str4 = paramBDLocation.getProvince();
          if (str4.indexOf("省") == -1)
            break label217;
          LocationAndWeatherBusiness.this.vo.setProvince(str4.substring(0, str4.indexOf("省")));
        }
        while (true)
        {
          MyDb.getInstance(MyApp.getApp()).putBean("cityVo", LocationAndWeatherBusiness.this.vo);
          if (LocationAndWeatherBusiness.this.canGetWeather)
            LocationAndWeatherBusiness.this.getWeather(LocationAndWeatherBusiness.this.vo.getCity());
          LocationAndWeatherBusiness.this.pHandler.sendEmptyMessage(1);
          LocationAndWeatherBusiness.this.mLocationClient.stop();
          return;
          LocationAndWeatherBusiness.this.vo.setCity(str3);
          break;
          label217: LocationAndWeatherBusiness.this.vo.setProvince(str4);
        }
      }
      LocationAndWeatherBusiness.this.pHandler.sendEmptyMessage(2);
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
      ((Vibrator)LocationAndWeatherBusiness.this.pct.getSystemService("vibrator")).vibrate(1000L);
    }
  }

  class TempeVo
  {
    String h_tmp = "";
    String l_tmp = "";
    String temp = "";
    String weather = "";

    TempeVo()
    {
    }

    public String getH_tmp()
    {
      return this.h_tmp;
    }

    public String getL_tmp()
    {
      return this.l_tmp;
    }

    public String getTemp()
    {
      return this.temp;
    }

    public String getWeather()
    {
      return this.weather;
    }

    public void setH_tmp(String paramString)
    {
      this.h_tmp = paramString;
    }

    public void setL_tmp(String paramString)
    {
      this.l_tmp = paramString;
    }

    public void setTemp(String paramString)
    {
      this.temp = paramString;
    }

    public void setWeather(String paramString)
    {
      this.weather = paramString;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.main.LocationAndWeatherBusiness
 * JD-Core Version:    0.6.0
 */