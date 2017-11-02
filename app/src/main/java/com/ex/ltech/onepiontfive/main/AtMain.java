package com.ex.ltech.onepiontfive.main;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.BDNotifyListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.LocationClientOption.LocationMode;
import com.baidu.mapapi.SDKInitializer;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.musci_service.ServicePlayer;
import com.ex.ltech.led.musci_service.ServicePlayer.MyBinder;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.more.FtMore;
import com.ex.ltech.onepiontfive.main.newscene.FtScene;
import com.ex.ltech.onepiontfive.main.room.FtRooms;
import com.ex.ltech.onepiontfive.main.time.TimingBusiness;
import com.ex.ltech.onepiontfive.main.vo.GeoSpaceVo;
import io.xlink.wifi.js.manage.DeviceManage;
import java.io.PrintStream;
import java.util.ArrayList;

public class AtMain extends FragmentActivity
{
  public static AtMain instance;
  public static ServicePlayer myService = null;
  private final int M_MCU_VERSION = 13;
  MyBusiness business;
  GeoSpaceVo cacheGeoSpaceVo;
  AlertDialog dialog;
  private Class[] fragmentArray = { FtRooms.class, FtScene.class, FtMore.class };
  GeoSpaceVo geoSpaceVo = null;
  GeoSpaceVo goHomeGeoSpaceVo;
  int i;
  String lastRecCmd = "";
  private LayoutInflater layoutInflater;
  private LoopAddListenerThread loopAddListenerThread = new LoopAddListenerThread();
  float mDistance;
  private int[] mImageViewArray = { 2130837671, 2130837672, 2130837674, 2130837675, 2130837673 };
  LocationClient mLocClient;
  private ServiceConnection mServiceConnection = new ServiceConnection()
  {
    public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
    {
      AtMain.myService = ((ServicePlayer.MyBinder)paramIBinder).getService();
    }

    public void onServiceDisconnected(ComponentName paramComponentName)
    {
    }
  };
  private FragmentTabHost mTabHost;
  private String[] mTextviewArray = { "首页", "消息", "好友", "广场", "更多" };
  private String mac;
  MyNotifyListener notifyListener;
  GeoSpaceVo outHomeGeoSpaceVo;
  TextView show_text;
  String tips = "";

  private boolean chechGeoStatus()
  {
    this.goHomeGeoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", GeoSpaceVo.class));
    if (this.goHomeGeoSpaceVo == null)
      this.goHomeGeoSpaceVo = new GeoSpaceVo();
    this.outHomeGeoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", GeoSpaceVo.class));
    if (this.outHomeGeoSpaceVo == null)
      this.outHomeGeoSpaceVo = new GeoSpaceVo();
    if ((!this.goHomeGeoSpaceVo.isStart()) && (!this.outHomeGeoSpaceVo.isStart()))
    {
      removeGeofence();
      return false;
    }
    return true;
  }

  private void checkVersion()
  {
    ArrayList localArrayList = new ArrayList();
    this.business.addNormalHeadData(localArrayList);
    localArrayList.add(Integer.valueOf(126));
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(1));
    localArrayList.add(Integer.valueOf(1));
    this.business.addCheckSumData(localArrayList);
    localArrayList.add(Integer.valueOf(22));
    this.business.setMySendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str1 = StringUtils.btye2Str(paramArrayOfByte);
        if (str1.length() < 92);
        while (true)
        {
          return;
          AtMain.this.business.setMySendListener(null);
          StringUtils.bytesStr2WordStr(str1.substring(24, 54));
          String str2 = StringUtils.bytesStr2WordStr(str1.substring(54, 84)).replace(".", "");
          String str3 = str2.substring(4, str2.length()).replaceFirst("^0*", "");
          try
          {
            if (13 >= Integer.parseInt(str3))
              continue;
            AtMain.this.updataDialog();
            return;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
          }
        }
      }

      public void onTimeOut()
      {
      }
    });
    this.business.sendCmd(localArrayList);
  }

  private View getTabItemView(int paramInt)
  {
    View localView = this.layoutInflater.inflate(2130968890, null);
    ((ImageView)localView.findViewById(2131559475)).setImageResource(this.mImageViewArray[paramInt]);
    return localView;
  }

  private void initView()
  {
    this.layoutInflater = LayoutInflater.from(this);
    this.mTabHost = ((FragmentTabHost)findViewById(16908306));
    this.mTabHost.setup(this, getSupportFragmentManager(), 2131559403);
    this.mTabHost.getTabWidget().setDividerDrawable(null);
    int j = this.fragmentArray.length;
    for (int k = 0; k < j; k++)
    {
      TabHost.TabSpec localTabSpec = this.mTabHost.newTabSpec(this.mTextviewArray[k]).setIndicator(getTabItemView(k));
      this.mTabHost.addTab(localTabSpec, this.fragmentArray[k], null);
    }
  }

  private void onDistandceChange(float paramFloat)
  {
    if (!chechGeoStatus())
      return;
    ArrayList localArrayList;
    try
    {
      this.i = (1 + this.i);
      if (this.i % 3 == 0)
        this.notifyListener.SetNotifyLocation(this.cacheGeoSpaceVo.getLat(), this.cacheGeoSpaceVo.getLng(), Constant.RANGE, "bd09ll");
      this.tips = "";
      this.tips = (this.tips + paramFloat + "   time:" + this.i);
      localArrayList = new ArrayList();
      this.business.addNormalHeadData(localArrayList);
      localArrayList.add(Integer.valueOf(30));
      localArrayList.add(Integer.valueOf(2));
      if (paramFloat < Constant.RANGE)
      {
        this.tips += "\n在围栏内内内内内内";
        this.geoSpaceVo = ((GeoSpaceVo)new MyBusiness(this).getData(DeviceListActivity.deviceMacAddress + "GohomeGeoSpaceVo ", GeoSpaceVo.class));
        if (this.geoSpaceVo == null)
        {
          GeoSpaceVo localGeoSpaceVo5 = new GeoSpaceVo();
          localGeoSpaceVo5.setName(getString(2131099877));
          this.business.putData(DeviceListActivity.deviceMacAddress + "LastGeoSpaceVo ", localGeoSpaceVo5);
          return;
        }
      }
    }
    catch (Exception localException)
    {
      this.tips = "";
    }
    while (true)
    {
      this.mDistance = paramFloat;
      if (paramFloat < Constant.RANGE)
      {
        GeoSpaceVo localGeoSpaceVo1 = new GeoSpaceVo();
        localGeoSpaceVo1.setName(getString(2131099877));
        this.business.putData(DeviceListActivity.deviceMacAddress + "LastGeoSpaceVo ", localGeoSpaceVo1);
      }
      if (paramFloat <= Constant.RANGE)
        break;
      GeoSpaceVo localGeoSpaceVo2 = new GeoSpaceVo();
      localGeoSpaceVo2.setName(getString(2131100143));
      this.business.putData(DeviceListActivity.deviceMacAddress + "LastGeoSpaceVo ", localGeoSpaceVo2);
      return;
      localArrayList.add(Integer.valueOf(1));
      if (paramFloat > Constant.RANGE)
      {
        this.tips += "\n在围栏外外外外外外";
        this.geoSpaceVo = ((GeoSpaceVo)new MyBusiness(this).getData(DeviceListActivity.deviceMacAddress + "OuthomeGeoSpaceVo", GeoSpaceVo.class));
        if (this.geoSpaceVo == null)
        {
          GeoSpaceVo localGeoSpaceVo4 = new GeoSpaceVo();
          localGeoSpaceVo4.setName(getString(2131100143));
          this.business.putData(DeviceListActivity.deviceMacAddress + "LastGeoSpaceVo ", localGeoSpaceVo4);
          return;
        }
        localArrayList.add(Integer.valueOf(2));
      }
      PendingIntent localPendingIntent = PendingIntent.getActivity(this, 0, new Intent(), 0);
      localArrayList.add(Integer.valueOf(1 + this.geoSpaceVo.getTouchSceneIndex()));
      GeoSpaceVo localGeoSpaceVo3 = (GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "LastGeoSpaceVo ", GeoSpaceVo.class);
      this.tips = (this.tips + "\ncacheGeoSpaceVo：" + localGeoSpaceVo3.getName());
      this.tips = (this.tips + "\ngeoSpaceVo：" + this.geoSpaceVo.getName());
      if ((localGeoSpaceVo3 != null) && (!localGeoSpaceVo3.getName().equals(this.geoSpaceVo.getName())))
      {
        this.tips = ("触发了" + this.geoSpaceVo.getName());
        localArrayList.add(Integer.valueOf(1));
        this.business.addCheckSumData(localArrayList);
        localArrayList.add(Integer.valueOf(22));
        this.business.setMySendListener(new MyBusiness.MySendListener(localPendingIntent)
        {
          public void onFail()
          {
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            String str = StringUtils.btye2Str(paramArrayOfByte);
            if ((str.length() != 30) || (!str.substring(18, 20).equalsIgnoreCase("9E")))
              return;
            System.out.println("onOk=" + StringUtils.btye2Str3(paramArrayOfByte));
            Notification localNotification = new Notification.Builder(AtMain.this).setSmallIcon(2130903307).setContentTitle(AtMain.this.getString(2131100074)).setContentText("触发了" + AtMain.this.geoSpaceVo.getName()).setContentIntent(this.val$pendingIntent).setAutoCancel(true).getNotification();
            localNotification.flags = (0x10 | localNotification.flags);
            ((NotificationManager)AtMain.this.getSystemService("notification")).notify((int)System.currentTimeMillis(), localNotification);
            AtMain.this.business.setMySendListener(null);
          }

          public void onTimeOut()
          {
          }
        });
        this.business.sendCmd(localArrayList);
      }
      this.business.putData(DeviceListActivity.deviceMacAddress + "CacheGeoSpaceVo ", this.geoSpaceVo);
      System.out.println("/n");
      System.out.println("/n");
      this.tips = "";
    }
  }

  private void updataDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(2131100472);
    localBuilder.setMessage(2131100471);
    localBuilder.setNegativeButton(2131099891, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        AtMain.this.finish();
      }
    });
    localBuilder.setPositiveButton(2131100530, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        paramDialogInterface.dismiss();
        AtMain.this.mTabHost.setCurrentTab(2);
        new Handler().postDelayed(new Runnable()
        {
          public void run()
          {
            Intent localIntent = new Intent("showFirmware");
            LocalBroadcastManager.getInstance(AtMain.this).sendBroadcast(localIntent);
          }
        }
        , 500L);
      }
    });
    localBuilder.create().show();
  }

  public void addGeofence()
  {
    if (this.mLocClient == null)
      this.mLocClient = new LocationClient(getApplicationContext());
    if (!chechGeoStatus());
    do
    {
      return;
      this.cacheGeoSpaceVo = ((GeoSpaceVo)this.business.getData(DeviceListActivity.deviceMacAddress + "CacheGeoSpaceVo ", GeoSpaceVo.class));
    }
    while (this.cacheGeoSpaceVo == null);
    SDKInitializer.initialize(getApplicationContext());
    LocationClientOption localLocationClientOption = new LocationClientOption();
    localLocationClientOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
    localLocationClientOption.setCoorType("bd09ll");
    localLocationClientOption.setScanSpan(1000);
    localLocationClientOption.setScanSpan(30000);
    this.mLocClient.setLocOption(localLocationClientOption);
    this.mLocClient.start();
    this.notifyListener = new MyNotifyListener();
    this.notifyListener.SetNotifyLocation(this.cacheGeoSpaceVo.getLat(), this.cacheGeoSpaceVo.getLng(), Constant.RANGE, "bd09ll");
    this.mLocClient.registerNotify(this.notifyListener);
    this.mLocClient.registerLocationListener(new BDLocationListener()
    {
      public void onReceiveLocation(BDLocation paramBDLocation)
      {
      }
    });
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968837);
    this.show_text = ((TextView)findViewById(2131559404));
    this.mac = UserFerences.getUserFerences(this).getValue("GateWayMacIdKey");
    this.business = new MyBusiness(MyApp.getApp());
    byte[] arrayOfByte = new CmdDateBussiness(this, "0000").getOneZeroFiveDeviceInfoCmd();
    this.business.setMySendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        try
        {
          String str1 = StringUtils.btye2Str(paramArrayOfByte);
          if (!str1.substring(18, 20).equalsIgnoreCase("98"))
            return;
          String str2 = str1.substring(20, str1.length()).substring(4, 12);
          UserFerences localUserFerences = UserFerences.getUserFerences(AtMain.this);
          StringBuilder localStringBuilder = new StringBuilder().append("GateWayIdKey");
          DeviceManage.getInstance();
          localUserFerences.putValue(DeviceManage.getxDevice(), str2);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }

      public void onTimeOut()
      {
      }
    });
    this.business.sendCmd(arrayOfByte);
    initView();
    instance = this;
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        new TimingBusiness(AtMain.this).sysTime();
      }
    }
    , 300L);
    addGeofence();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        AtMain.this.checkVersion();
      }
    }
    , 600L);
    bindService(new Intent(this, ServicePlayer.class), this.mServiceConnection, 1);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    Intent localIntent = new Intent(this, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "destroyPlayer");
    localIntent.putExtras(localBundle);
    startService(localIntent);
    unbindService(this.mServiceConnection);
  }

  public void receivePush()
  {
  }

  public void removeGeofence()
  {
    if (this.mLocClient != null)
    {
      this.mLocClient.removeNotifyEvent(this.notifyListener);
      this.mLocClient.stop();
      this.mLocClient = null;
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
      AtMain.this.onDistandceChange(paramFloat);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.AtMain
 * JD-Core Version:    0.6.0
 */