package io.xlink.wifi.js;

import com.ex.ltech.led.MyApp;

public class Constant
{
  public static final String BROADCAST_CLOUD_DISCONNECT;
  public static final String BROADCAST_DEVICE_CHANGED;
  public static final String BROADCAST_DEVICE_SYNC;
  public static final String BROADCAST_EXIT;
  public static final String BROADCAST_LOCAL_DISCONNECT;
  public static final String BROADCAST_ON_LOGIN;
  public static final String BROADCAST_ON_START;
  public static final String BROADCAST_RECVPIPE;
  public static final String BROADCAST_SOCKET_STATUS;
  public static final String BROADCAST_TIMER_UPDATE;
  public static final String BwCtId = "df9725268da14c08ba806ec5a69edbf6";
  public static final String DATA = "data";
  public static final String DEVICE_MAC = "device-mac";
  public static final int HTTP_NETWORK_ERR = 1;
  public static final String IR_WF_B = "160fa2b2db6403e9160fa2b2db646801";
  public static final String IS_INIT = "isinit";
  public static final String KEY = "key";
  public static final String LedV2Id = "160fa2af1948f800160fa2af1948f801";
  public static final String PACKAGE_NAME = MyApp.getApp().getPackageName();
  public static final String PlugId = "8e28d6ebd1634ecf86161997912b895e";
  public static final String SAVE_COMPANY_ID = "COMPANY_ID";
  public static final String SAVE_EMAIL_ID = "EMAIL_ID";
  public static final String SAVE_PASSWORD_ID = "PASSWD_ID";
  public static final String SAVE_PRODUCTID = "pid";
  public static final String SAVE_appId = "appId";
  public static final String SAVE_authKey = "authKey";
  public static final String STATUS = "status";
  public static final int TIMEOUT = 10;
  public static final int TIMER_BUFF_SIZE = 6;
  public static final int TIMER_MAX = 19;
  public static final int TIMER_OFF = 0;
  public static final int TIMER_ON = 1;
  public static final String TYPE = "type";
  public static final String WIFI_101_CT = "fc02a10aec1c46b8922630f6acd15ed6";
  public static final String WIFI_101_CTRF_SIP120 = "160fa2b3051a03e9160fa2b3051ac601";
  public static final String WIFI_105_ZB_A = "160fa2b1d84e03e9160fa2b1d84eaa01";
  public static final String WiFi_101_RGBW = "3864ebbb24cf4cab9d3ce823a0cfe93f";
  public static final String WiFi_101_RGBW_V1 = "160fa2af1948f800160fa2af1948f801";
  public static final String XL_IR_01 = "5b05f623bdcf48d9b0fc1507a79bb847";
  public static final String XL_IR_02 = "160fa2afd1a7f600160fa2afd1a7f601";
  public static final String passwrod = "8888";

  static
  {
    BROADCAST_ON_START = PACKAGE_NAME + ".onStart";
    BROADCAST_ON_LOGIN = PACKAGE_NAME + ".xlinkonLogin";
    BROADCAST_CLOUD_DISCONNECT = PACKAGE_NAME + ".clouddisconnect";
    BROADCAST_LOCAL_DISCONNECT = PACKAGE_NAME + ".localdisconnect";
    BROADCAST_RECVPIPE = PACKAGE_NAME + ".recv-pipe";
    BROADCAST_DEVICE_CHANGED = PACKAGE_NAME + ".device-changed";
    BROADCAST_DEVICE_SYNC = PACKAGE_NAME + ".device-sync";
    BROADCAST_EXIT = PACKAGE_NAME + ".exit";
    BROADCAST_TIMER_UPDATE = PACKAGE_NAME + "timer-update";
    BROADCAST_SOCKET_STATUS = PACKAGE_NAME + "socket-status";
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.Constant
 * JD-Core Version:    0.6.0
 */