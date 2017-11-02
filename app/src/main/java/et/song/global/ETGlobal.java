package et.song.global;

import et.song.tg.face.ITg;

public final class ETGlobal
{
  public static final String APPName = "RemoteStar_HXD.apk";
  public static final String APPVer = "version_hxd.json";
  public static final String BROADCAST_APP_BACK = "ET.SONG.BROADCAST.APP.HXD.RSBACK";
  public static final String BROADCAST_APP_BUY_NO = "ET.SONG.BROADCAST.APP.HXD.RSBUY.NO";
  public static final String BROADCAST_APP_BUY_YES = "ET.SONG.BROADCAST.APP.HXD.RSBUY.YES";
  public static final String BROADCAST_APP_UPDATE_LOADING = "ET.SONG.BROADCAST.APP.HXD.RSUPDATE.LOADING";
  public static final String BROADCAST_APP_UPDATE_START = "ET.SONG.BROADCAST.APP.HXD.RSUPDATE.START";
  public static final String BROADCAST_DATABASE_LOAD = "ET.SONG.BROADCAST.APP.HXD.RS.DATABASE.LOAD";
  public static final String BROADCAST_DATA_RECV = "ET.SONG.APP.HXD.RS.DATA_RECV";
  public static final String BROADCAST_DATA_SEND = "ET.SONG.APP.HXD.RS.DATA_SEND";
  public static final String BROADCAST_END_LEARN = "ET.SONG.BROADCAST.APP.HXD.RS.END.LEARN";
  public static final String BROADCAST_FOUND_COL = "ET.SONG.BROADCAST.APP.HXD.RSFOUND.COL";
  public static final String BROADCAST_KEYCODE_VOLUME_DOWN = "ET.SONG.BROADCAST.APP.HXD.RS.VOLUME_DOWN";
  public static final String BROADCAST_KEYCODE_VOLUME_UP = "ET.SONG.BROADCAST.APP.HXD.RS.VOLUME_UP";
  public static final String BROADCAST_OPEN_FINISH = "ET.SONG.BROADCAST.APP.HXD.RS.OPEN.FINISH";
  public static final String BROADCAST_PASS_LEARN = "ET.SONG.BROADCAST.APP.HXD.RS.PASS.LEARN";
  public static final String BROADCAST_REPEAT_LEARN = "ET.SONG.BROADCAST.APP.HXD.RS.REPEAT.LEARN";
  public static final String BROADCAST_START_LEARN = "ET.SONG.BROADCAST.APP.HXD.RS.START.LEARN";
  public static final int ETGROUP_TYPE = 16777216;
  public static final int ETGROUP_TYPE_ADD = 16777217;
  public static final int ETGROUP_TYPE_BABYROOM = 16777223;
  public static final int ETGROUP_TYPE_BATHROOM = 16777222;
  public static final int ETGROUP_TYPE_BEDROOM = 16777218;
  public static final int ETGROUP_TYPE_COOKROOM = 16777221;
  public static final int ETGROUP_TYPE_CUSTOM = 16842496;
  public static final int ETGROUP_TYPE_DININGROOM = 16777225;
  public static final int ETGROUP_TYPE_LIVINGROOM = 16777219;
  public static final int ETGROUP_TYPE_MEETINGROOM = 16777224;
  public static final int ETGROUP_TYPE_OFFICROOM = 16777220;
  public static final int ETWIFIDEVICE_TYPE = 50331648;
  public static int H = 0;
  public static final String NETWORK_REMOTE_HOST = "http://www.hxdkj88.com/";
  public static int W = 0;
  public static final String WIFI_DEVICE_DEFAULT_PORT = "8695";
  public static int[] mDeviceTypes;
  public static int[] mGroupTypes;
  public static boolean mIsWifiWan;
  public static ITg mTg;
  public static int[] mWifiTypes;

  static
  {
    H = 0;
    mTg = null;
    mIsWifiWan = false;
    mGroupTypes = new int[] { 16777218, 16777219, 16777220, 16777224, 16777221, 16777225, 16777222, 16777223, 16777217 };
    mDeviceTypes = new int[] { 8192, 8448, 16384, 24576, 32768, 40960, 57344, 49152, 8960, -33554432, 11008, -16777216 };
    mWifiTypes = new int[] { 50331648 };
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.global.ETGlobal
 * JD-Core Version:    0.6.0
 */