package com.ex.ltech.led;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.support.multidex.MultiDex;

import java.util.List;

import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

public class MyApp extends Application
  implements XlinkNetListener
{
  private static final String TAG = "com.ex.ltech.MyApp";
//  public static Device dvc;
  private static Handler mainHandler = null;
  public static SharedPreferences sharedPreferences;
  private String accessToken;
  private int appid;
  public boolean auth;
  private String authKey;
  private Activity currentActivity;
  public String packageName;
  public int versionCode;
  public String versionName;
  private static MyApp application;

  public static MyApp getApp()
  {
    return application;
  }

  public static void initHandler()
  {
    mainHandler = new Handler();
  }

  public static void postToMainThread(Runnable paramRunnable)
  {
    mainHandler.post(paramRunnable);
  }

  protected void attachBaseContext(Context paramContext)
  {
    super.attachBaseContext(paramContext);
    MultiDex.install(this);
  }

  public String getAccessToken()
  {
    return this.accessToken;
  }

  public int getAppid()
  {
    return this.appid;
  }

  public String getAuth()
  {
    return this.authKey;
  }

  public Activity getCurrentActivity()
  {
    return this.currentActivity;
  }

  public void onCreate()
  {
    super.onCreate();
//    CrashHandler.init(this);
    application = this;
    this.auth = false;
    XlinkAgent.init(this);
    sharedPreferences = getSharedPreferences("XlinkOfficiaDemo", 0);
    /*this.appid = SharedPreferencesUtil.queryIntValue(this, "appId").intValue();
    this.authKey = SharedPreferencesUtil.queryValue(this, "authKey", "");
    String str = Integer.toHexString(SharedPreferencesUtil.queryIntValue(this, "appId").intValue()).toUpperCase();
    for (int i = str.length(); i < 8; i++)
      str = "0" + str;
    initHandler();
    Iterator localIterator = DeviceManage.getInstance().getDevices().iterator();
    while (localIterator.hasNext())
    {
      Device localDevice = (Device)localIterator.next();
      XlinkAgent.getInstance().initDevice(localDevice.getXDevice());
    }*/
    try
    {
      PackageInfo localPackageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
      this.versionCode = localPackageInfo.versionCode;
      this.versionName = localPackageInfo.versionName;
      this.packageName = localPackageInfo.packageName;
      return;
    }
    catch (NameNotFoundException localNameNotFoundException)
    {
    }
  }

  public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
  {
  }

  public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
  {
  }

  public void onDisconnect(int paramInt)
  {
  }

  public void onEventNotify(EventNotify paramEventNotify)
  {
  }

  public void onLocalDisconnect(int paramInt)
  {
  }

  public void onLogin(int paramInt)
  {
  }

  public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
  {
  }

  public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
  {
  }

  public void onStart(int paramInt)
  {
  }

  public void sendBroad(String paramString, int paramInt)
  {
    Intent localIntent = new Intent(paramString);
    localIntent.putExtra("status", paramInt);
    sendBroadcast(localIntent);
  }

  /*public void sendPipeBroad(String paramString, Device paramDevice, byte[] paramArrayOfByte)
  {
    Intent localIntent = new Intent(paramString);
    if ((paramDevice.getMacAddress() == null) || (paramDevice.getMacAddress().length() == 0))
      return;
    localIntent.putExtra("device-mac", paramDevice.getMacAddress());
    if (paramArrayOfByte != null)
      localIntent.putExtra("data", paramArrayOfByte);
    sendBroadcast(localIntent);
  }*/

  public void setAccessToken(String paramString)
  {
    this.accessToken = paramString;
  }

  public void setAppid(int paramInt)
  {
    this.appid = paramInt;
  }

  public void setAuth(String paramString)
  {
    this.authKey = paramString;
  }

  public void setCurrentActivity(Activity paramActivity)
  {
    this.currentActivity = paramActivity;
  }
}