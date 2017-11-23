package com.ex.ltech.led.acti;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ex.ltech.hongwai.StringUtil;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.colors.ActColor;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.acti.mode.ActMode;
import com.ex.ltech.led.acti.music.ActiMusic;
import com.ex.ltech.led.acti.timing.act.ActTiming;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.LogTool;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.DeviceVo;

import java.util.List;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

public class Main extends TabActivity {
    public static boolean canAddRcOrPanel;
    public static String colorCmd;
    public static DeviceVo deviceVo = new DeviceVo();
    public static boolean firstOpenCustomMode;
    public static boolean isOnAllOff;
    public static String lastSendCmd;
    public static String modeCmd;
    public static String musicCmd;
//    public static ServicePlayer myService;
    public static int seekSecond;
    public static int sonActHeightWithouTitle;
    public static int tabIndex;
    private final int WIFI_101_DMX4_UP_V0_VERSION = -2;
    private final int WIFI_101_RGBW_UP_V0_VERSION = -2;
    private final int WIFI_75_RGB_UP_V0_VERSION = -2;
    private final int WIFI_800_RGB_UP_V0_VERSION = -2;
    boolean aBoolean;
    RelativeLayout act_gray_layer;
    private boolean allOn;
    private CmdDateBussiness cmdDateBussiness;
    int curSwitchCmd = 160;
    ProgressDialog dialog;
    private int[] drawableArray = {R.mipmap.mode_2, R.mipmap.mode_1, R.mipmap.ic_kong, R.mipmap.mode_3, R.mipmap.mode_4};
    private int[] drawableSelectedArray = {R.mipmap.mode_22, R.mipmap.mode_11, R.mipmap.ic_kong, R.mipmap.mode_33, R.mipmap.mode_44};
    Handler handler = new Handler();
    private LayoutInflater inflater;
    boolean isDestroy = false;
    boolean isOpenHeatbeat = true;
    boolean isRespTimeOut;
    boolean isUpdataIng = false;
    private ImageButton iv_act_main_all_off;
    private ImageButton iv_act_main_all_on;
    /*private ServiceConnection mServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder) {
            Main.myService = ((ServicePlayer.MyBinder) paramIBinder).getService();
        }

        public void onServiceDisconnected(ComponentName paramComponentName) {
            System.out.println("fuckingSSSS         onServiceDisconnected");
        }
    };*/
//    MyAlertDialog14 myAlertDialog14;
    MyPhoneStateListener myPhoneStateListener = new MyPhoneStateListener();
    MySendPipeListener mySendPipeListener = new MySendPipeListener();
    MyXlinkNetListener myXlinkNetListener = new MyXlinkNetListener();
    private RelativeLayout rl_load;
    private SocketManager socketManager;
    String softVersion;
//    SynProgram2Device synProgram2Device;
    private TabHost tabHost;
    private TabHost.OnTabChangeListener tab_listener = new TabHost.OnTabChangeListener() {
        public void onTabChanged(String paramString) {
            boolean bool = paramString.equals("mode");
            int i = 0;
            if (bool) {
                XlinkAgent.getInstance().addXlinkListener(Main.this.myXlinkNetListener);
                i = 1;
            }
            if (paramString.equals("color")) {
                XlinkAgent.getInstance().addXlinkListener(Main.this.myXlinkNetListener);
                i = 0;
            }
            if (paramString.equals("music")) {
                XlinkAgent.getInstance().addXlinkListener(Main.this.myXlinkNetListener);
                i = 3;
            }
            if (paramString.equals("time")) {
                XlinkAgent.getInstance().removeListener(Main.this.myXlinkNetListener);
                i = 4;
            }
            Main.this.changeTabItemBG(i);
        }
    };
    private int tempSonActHeightWithouTitle;
    Runnable timeOutThread = new Runnable() {
        public void run() {
            if (Main.this.isRespTimeOut) {
                Main.this.handler.removeCallbacks(Main.this.timeOutThread);
                Main.this.handler.postDelayed(Main.this.timeOutThread, 1000L);
                XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                DeviceManage.getInstance();
                localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getAllOnOffCmd(160), Main.this.mySendPipeListener);
            }
        }
    };
    TelephonyManager tpm;
    private TextView tvPercent;
    private TabWidget widget;

    static {
        colorCmd = "colorCmd";
        modeCmd = "modeCmd";
        musicCmd = "musicCmd";
        lastSendCmd = modeCmd;
//        myService = null;
        seekSecond = -1;
    }

    private void beginUpdata(String paramString) {
        /*if (this.synProgram2Device == null) {
            this.isUpdataIng = true;
            this.rl_load.setVisibility(View.VISIBLE);
            DeviceManage.getInstance();
            this.synProgram2Device = new SynProgram2Device(this, DeviceManage.getxDevice(), paramString);
            this.synProgram2Device.setListener(new SynProgram2Device.SynListener() {
                public void failed() {
                    Main.this.isUpdataIng = false;
                    Main.this.rl_load.setVisibility(View.GONE);
                    Main.this.findViewById(R.id.rl_failde).setVisibility(View.VISIBLE);
                    Main.this.findViewById(R.id.finish).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View paramView) {
                            Main.this.findViewById(R.id.rl_failde).setVisibility(View.GONE);
                            Main.this.finish();
                        }
                    });
                    Main.this.synProgram2Device.close();
                }

                public void ok() {
                    Main.this.isUpdataIng = false;
                    Main.this.rl_load.setVisibility(View.GONE);
                    Main.this.findViewById(R.id.rl_ok).setVisibility(View.VISIBLE);
                    new Handler() {
                    }
                            .postDelayed(new Runnable() {
                                             public void run() {
                                                 Main.this.findViewById(R.id.rl_ok).setVisibility(View.GONE);
                                             }
                                         }
                                    , 1000L);
                    Main.this.synProgram2Device.close();
                }

                public void onPercent(int paramInt) {
                    Main.this.tvPercent.setText("" + paramInt + "%");
                }
            });
            this.synProgram2Device.syn();
        }*/
    }

    private void changeTabItemBG(int paramInt) {
        tabIndex = paramInt;
        this.widget = getTabHost().getTabWidget();
        int i = this.widget.getChildCount();
        int j = 0;
        if (j < i) {
            ImageView localImageView = (ImageView) this.widget.getChildAt(j).findViewById(R.id.btn_app_main_mune);
            if (paramInt == j)
                localImageView.setImageResource(this.drawableSelectedArray[j]);
            else {
                j++;
                localImageView.setImageResource(this.drawableArray[j]);
            }
        }
    }

    private void findView() {
        this.tabHost = getTabHost();
        this.tabHost.setOnTabChangedListener(this.tab_listener);
        this.inflater = LayoutInflater.from(this);
        View localView1 = setMenuView(R.mipmap.mode_1);
        View localView2 = setMenuView(R.mipmap.mode_2);
        View localView3 = setMenuView(R.mipmap.ic_kong);
        View localView4 = setMenuView(R.mipmap.mode_3);
        View localView5 = setMenuView(R.mipmap.mode_4);
        this.rl_load = ((RelativeLayout) findViewById(R.id.rl_load));
        this.tvPercent = ((TextView) findViewById(R.id.tv_percent));
        new Intent(this, ActMode.class);
        this.tabHost.addTab(this.tabHost.newTabSpec("color").setContent(new Intent(this, ActColor.class)).setIndicator(localView3));
        this.tabHost.addTab(this.tabHost.newTabSpec("mode").setContent(new Intent(this, ActMode.class)).setIndicator(localView1));
        this.tabHost.addTab(this.tabHost.newTabSpec("null").setContent(new Intent(this, ActNull.class)).setIndicator(localView4));
        this.tabHost.addTab(this.tabHost.newTabSpec("music").setContent(new Intent(this, ActiMusic.class)).setIndicator(localView2));
        if (DeviceListActivity.devicePid.equals("3864ebbb24cf4cab9d3ce823a0cfe93f"))
            this.tabHost.addTab(this.tabHost.newTabSpec("time").setContent(new Intent(this, ActTiming.class)).setIndicator(localView5));
        else {
            this.act_gray_layer = ((RelativeLayout) findViewById(R.id.act_gray_layer));
            this.iv_act_main_all_on = ((ImageButton) findViewById(R.id.iv_act_main_all_on));
//            this.tabHost.addTab(this.tabHost.newTabSpec("time").setContent(new Intent(this, ActNewRgbTiming.class)).setIndicator(localView5));
            this.tabHost.addTab(this.tabHost.newTabSpec("time").setContent(new Intent(this, ActNull.class)).setIndicator(localView5));
        }
    }

    private void regOnCallBroadCastRec() {
        this.tpm = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        this.tpm.listen(this.myPhoneStateListener, 32);
    }

    private View setMenuView(int paramInt) {
        View localView = this.inflater.inflate(R.layout.app_main_button, null);
        ((ImageView) localView.findViewById(R.id.btn_app_main_mune)).setImageResource(paramInt);
        return localView;
    }

    private void updataDialog(String paramString) {
        /*if (this.myAlertDialog14 == null) {
            this.myAlertDialog14 = new MyAlertDialog14(this);
            this.myAlertDialog14.show();
            this.myAlertDialog14.setCancelable(false);
            this.myAlertDialog14.setMsg(getString(R.string.version_code) + paramString);
            this.myAlertDialog14.setMyOnClickListener(new MyAlertDialog.MyOnClickListener() {
                public void onClick(View paramView, boolean paramBoolean) {
                    if (paramBoolean) {
                        Main.this.handler.postDelayed(new Runnable() {
                                                          public void run() {
                                                              XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                                              DeviceManage.getInstance();
                                                              localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getCallDeviceUpdataStatusCmd(), Main.this.mySendPipeListener);
                                                          }
                                                      }
                                , 50L);
                        Main.this.handler.postDelayed(new Runnable() {
                                                          public void run() {
                                                              XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                                              DeviceManage.getInstance();
                                                              localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getCallDeviceUpdataStatusCmd(), Main.this.mySendPipeListener);
                                                          }
                                                      }
                                , 100L);
                    }
                }
            });
        }*/
    }

    public void allOff(View paramView) {
        isOnAllOff = true;
        this.curSwitchCmd = 160;
        this.iv_act_main_all_on.setVisibility(View.GONE);
        this.act_gray_layer.setVisibility(View.VISIBLE);
        this.allOn = true;
        this.isRespTimeOut = true;
        if (seekSecond != -1) {
            /*Intent localIntent = new Intent(this, ServicePlayer.class);
            Bundle localBundle = new Bundle();
            localBundle.putString("operation", "pause");
            localIntent.putExtras(localBundle);
            startService(localIntent);
            this.handler.postDelayed(new Runnable() {
                                         public void run() {
                                             XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                             DeviceManage.getInstance();
                                             localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getMusicCmdT(false, 0, 0, 0), null);
                                         }
                                     }
                    , 800L);
            this.handler.postDelayed(new Runnable() {
                                         public void run() {
                                             XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                             DeviceManage.getInstance();
                                             localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getAllOnOffCmd(160), Main.this.mySendPipeListener);
                                         }
                                     }
                    , 1100L);
            return;*/
        }
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getAllOnOffCmd(160), this.mySendPipeListener);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                         DeviceManage.getInstance();
                                         localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getAllOnOffCmd(160), Main.this.mySendPipeListener);
                                     }
                                 }
                , 100L);
    }

    public void allOn(View paramView) {
        this.curSwitchCmd = 161;
        this.iv_act_main_all_on.setVisibility(View.VISIBLE);
        this.act_gray_layer.setVisibility(View.GONE);
        this.allOn = false;
        if (seekSecond != -1) {
            /*Intent localIntent = new Intent(this, ServicePlayer.class);
            Bundle localBundle = new Bundle();
            localBundle.putString("operation", "seek");
            localBundle.putInt("seceond", seekSecond);
            localIntent.putExtras(localBundle);
            startService(localIntent);
            this.handler.postDelayed(new Runnable() {
                                         public void run() {
                                             XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                             DeviceManage.getInstance();
                                             localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getMusicCmdT(true, 0, 0, 0), null);
                                         }
                                     }
                    , 800L);*/
        }
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getAllOnOffCmd(161), this.mySendPipeListener);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                         DeviceManage.getInstance();
                                         localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), Main.this.cmdDateBussiness.getAllOnOffCmd(161), Main.this.mySendPipeListener);
                                     }
                                 }
                , 100L);
    }

    public int dip2px(int paramInt) {
        return (int) (0.5F + getResources().getDisplayMetrics().density * paramInt);
    }

    public boolean dispatchKeyEvent(KeyEvent paramKeyEvent) {
        if (this.isUpdataIng)
            return true;
        return super.dispatchKeyEvent(paramKeyEvent);
    }

    public boolean isEmpty(String paramString) {
        return (paramString == null) || (paramString == "") || (paramString.trim().equals(""));
    }

    public void onBackPressed() {
        if (!this.isUpdataIng) ;
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        deviceVo.setIp("");
        deviceVo.setDeviceName(SharedPreferencesUtil.queryValue("dname"));
        deviceVo.setMacAddress(SharedPreferencesUtil.queryValue("dMacAddress"));
        setContentView(R.layout.app_main);
        findView();
        changeTabItemBG(0);
        this.socketManager = SocketManager.instance();
        this.cmdDateBussiness = new CmdDateBussiness(this, "0000");
//        bindService(new Intent(this, ServicePlayer.class), this.mServiceConnection, Context.BIND_AUTO_CREATE);
        if (SharedPreferencesUtil.queryValue("dStatus").equals(getString(R.string.off_device))) {
            this.iv_act_main_all_on.setVisibility(View.GONE);
            this.act_gray_layer.setVisibility(View.VISIBLE);
        }
        StringUtil.byte2Hexstr(this.cmdDateBussiness.getDeviceOnOffInfoCmd());
        XlinkAgent.getInstance().addXlinkListener(this.myXlinkNetListener);
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmdDateBussiness.getDeviceOnOffInfoCmd(), this.mySendPipeListener);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                         DeviceManage.getInstance();
                                         localXlinkAgent.sendPipeData(DeviceManage.getxDevice(),
                                                 cmdDateBussiness.getDeviceOnOffInfoCmd(), mySendPipeListener);
                                     }
                                 }
                , 100L);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                         DeviceManage.getInstance();
                                         localXlinkAgent.sendPipeData(DeviceManage.getxDevice(),
                                                 cmdDateBussiness.checkRgbwDeviceVersionCmd(), mySendPipeListener);
                                     }
                                 }
                , 300L);
        this.handler.postDelayed(new Runnable() {
                                     public void run() {
                                         XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                                         DeviceManage.getInstance();
                                         localXlinkAgent.sendPipeData(DeviceManage.getxDevice(),
                                                 cmdDateBussiness.checkRgbwDeviceVersionCmd(), mySendPipeListener);
                                     }
                                 }
                , 600L);
    }

    protected void onDestroy() {
        super.onDestroy();
        this.isDestroy = true;
        seekSecond = -1;
        /*new Intent(this, ServicePlayer.class);
        Intent localIntent = new Intent(this, ServicePlayer.class);
        Bundle localBundle = new Bundle();
        localBundle.putString("operation", "destroyPlayer");
        localIntent.putExtras(localBundle);
        startService(localIntent);
        unbindService(this.mServiceConnection);*/
        this.isOpenHeatbeat = false;
    }

    protected void onPause() {
        super.onPause();
        XlinkAgent.getInstance().removeListener(this.myXlinkNetListener);
    }

    protected void onResume() {
        super.onResume();
        this.isDestroy = false;
    }

    protected void onStop() {
        super.onStop();
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        this.tempSonActHeightWithouTitle = (this.tabHost.getMeasuredHeight() - this.widget.getMeasuredHeight() - dip2px(100));
        if (UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1) == -1) {
            sonActHeightWithouTitle = this.tabHost.getHeight() - this.widget.getHeight() - dip2px(100);
            UserFerences.getUserFerences(this).putValue("sonActHeightWithouTitle ", Integer.valueOf(sonActHeightWithouTitle));
        } else {
            sonActHeightWithouTitle = this.tempSonActHeightWithouTitle;
            Activity localActivity = getLocalActivityManager().getActivity(this.tabHost.getCurrentTabTag());
            if ((localActivity != null) && ((localActivity instanceof ActColor)))
                ((ActColor) localActivity).setPikerView();
            System.out.println("tabHost.getHeight() = " + this.tempSonActHeightWithouTitle);
            sonActHeightWithouTitle = UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1);
        }
    }

    class MyPhoneStateListener extends PhoneStateListener {
        MyPhoneStateListener() {
        }

        public void onCallStateChanged(int paramInt, String paramString) {
      /*switch (paramInt)
      {
      case 0:
      default:
      case 1:
      case 2:
      }
      while (true)
      {
        super.onCallStateChanged(paramInt, paramString);
        return;
        if (Main.seekSecond == -1)
          continue;
        Intent localIntent2 = new Intent(Main.this, ServicePlayer.class);
        Bundle localBundle2 = new Bundle();
        localBundle2.putString("operation", "pause");
        localIntent2.putExtras(localBundle2);
        Main.this.startService(localIntent2);
        continue;
        if (Main.seekSecond == -1)
          continue;
        Intent localIntent1 = new Intent(Main.this, ServicePlayer.class);
        Bundle localBundle1 = new Bundle();
        localBundle1.putString("operation", "seek");
        localBundle1.putInt("seceond", Main.seekSecond);
        localIntent1.putExtras(localBundle1);
        Main.this.startService(localIntent1);
      }*/
        }
    }

    class MySendPipeListener extends SendPipeListener {
        MySendPipeListener() {
        }

        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
        }
    }

    class MyXlinkNetListener implements XlinkNetListener {
        MyXlinkNetListener() {
        }

        public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt) {
            LogTool.d("onDataPointUpdate");
        }

        public void onDeviceStateChanged(XDevice paramXDevice, int paramInt) {
            LogTool.d("onDeviceStateChanged");
        }

        public void onDisconnect(int paramInt) {
            LogTool.d("onDisconnect");
        }

        public void onEventNotify(EventNotify paramEventNotify) {
            LogTool.d("onEventNotify");
        }

        public void onLocalDisconnect(int paramInt) {
            LogTool.d("onLocalDisconnect");
        }

        public void onLogin(int paramInt) {
            LogTool.d("onLogin");
        }

        public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte) {
            LogTool.d("onRecvPipeData");
            /*Main.this.isRespTimeOut = false;
            if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0)){
                return;
            }
            if ((!paramXDevice.getMacAddress().equalsIgnoreCase(DeviceListActivity.deviceMacAddress)) || (Main.this.isDestroy))
                return;
            String str1 = StringUtils.btye2Str(paramArrayOfByte);
            LogTool.d("main onRecvPipeData      " + StringUtils.btye2Str(paramArrayOfByte));
            Activity localActivity2 = null;
            if ((str1.length() == 18) && ((str1.indexOf("AAEB") != -1) || (str1.indexOf("DDEB") != -1) || (str1.indexOf("CCEB") != -1)))
            {
                if (str1.substring(12, 14).equals("01"))
                {
                    Main.this.iv_act_main_all_on.setVisibility(View.VISIBLE);
                    Main.this.act_gray_layer.setVisibility(View.GONE);
                    Main.access$202(Main.this, false);
                }
                if (str1.substring(12, 14).equals("00"))
                {
                    Main.this.iv_act_main_all_on.setVisibility(View.GONE);
                    Main.this.act_gray_layer.setVisibility(View.VISIBLE);
                    Main.access$202(Main.this, true);
                }
                if (str1.substring(14, 16).equalsIgnoreCase("DD"))
                {
                    SharedPreferencesUtil.keepShared(DeviceListActivity.deviceMacAddress + "lampType", "rgbw");
                    Main.canAddRcOrPanel = true;
                    localActivity2 = Main.this.getLocalActivityManager().getActivity(Main.this.tabHost.getCurrentTabTag());
                    if ((localActivity2 == null) || (!(localActivity2 instanceof ActColor)));
                }
            }
            try
            {
                ((ActColor)localActivity2).showRgbwSeekBarStatus();
                if (str1.substring(14, 16).equalsIgnoreCase("CC"))
                {
                    SharedPreferencesUtil.keepShared(DeviceListActivity.deviceMacAddress + "lampType", "rgb");
                    Main.canAddRcOrPanel = true;

                }
            }
            catch (Exception localException2)
            {
                localException2.printStackTrace();
            }
            String str3 = null;
            String str2 = null;
            int j = -1;
            int k = -1;
            try
            {
                str2 = str1.substring(22, 24);
                switch (str2.hashCode())
                {
                    case 1537:
                        if (i <= Integer.parseInt(Main.this.softVersion))
                            continue;
                        Main.this.softVersion = StringUtils.bytesStr2WordStr(str1.substring(48, 72));
                        return;
                    case 1538:
                    case 1539:
                    case 1540:
                }

                if (str2.equals("01"))
                    j = 0;

                if (str2.equals("02"))
                    j = 1;

                if (str2.equals("03"))
                    j = 2;

                if (str2.equals("04"))
                    j = 3;
            }
            catch (NumberFormatException localNumberFormatException)
            {
                localNumberFormatException.printStackTrace();
            }
            try
            {

                Activity localActivity1;
                localActivity1 = Main.this.getLocalActivityManager().getActivity(Main.this.tabHost.getCurrentTabTag());
                if ((localActivity1 == null) || (!(localActivity1 instanceof ActColor)));
                ((ActColor)localActivity1).showRgbSeekBarStatus();
                if ((str1.length() == 18) && (str1.indexOf("FDEB device updata status return") != -1))
                    str3 = str1.substring(12, 14);
                switch (str3.hashCode())
                {
                    default:

                    case 1536:
                    case 1537:
                    case 1538:
                    case 1539:
                }

                if (str3.equals("00"))
                    k = 0;
                if (str3.equals("01"))
                    k = 1;

                if (str3.equals("02"))
                    k = 2;

                if (str3.equals("03"))
                    k = 3;
            }
            catch (Exception localException1)
            {
                localException1.printStackTrace();
            }


            switch (k)
            {
                default:
                    if ((str1.indexOf("FE") == -1) || (str1.length() < 80))
                        continue;
                    Main.this.softVersion = StringUtils.bytesStr2WordStr(str1.substring(48, 72));
                    Main.this.softVersion = Main.this.softVersion.replace(".", "");
                    Main.this.softVersion = Main.this.softVersion.substring(4, Main.this.softVersion.length());
                    if (Main.this.softVersion.equals("000000"))
                    {
                        Main.this.softVersion = "0";
                        i = 1;
                    }
                case 0:
                case 1:
                case 2:
                case 3:
            }



                continue;
                Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_FILE_NAME);
                continue;
                Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_75_FILE_NAME);
                continue;
                Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_800FILE_NAME);
                continue;
                Main.this.beginUpdata(SynProgram2Device.WIFI_101_DMX4_UP_V0);
                continue;
                Main.this.softVersion = Main.this.softVersion.replaceFirst("^0*", "");
                continue;
            int i = -1;
            switch (j)
            {
                default:
                case 0:
                    i = -2;
                case 1:
                    i = -2;
                case 2:
                    i = -2;
                case 3:
                    i = -2;
            }*/


      /*Main.this.isRespTimeOut = false;
      if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0)){
          return;
      }
        if ((!paramXDevice.getMacAddress().equalsIgnoreCase(DeviceListActivity.deviceMacAddress)) || (Main.this.isDestroy))
          return;
        String str1 = StringUtils.btye2Str(paramArrayOfByte);
        LogTool.d("main onRecvPipeData      " + StringUtils.btye2Str(paramArrayOfByte));
        Activity localActivity2 = null;
        if ((str1.length() == 18) && ((str1.indexOf("AAEB") != -1) || (str1.indexOf("DDEB") != -1) || (str1.indexOf("CCEB") != -1)))
        {
          if (str1.substring(12, 14).equals("01"))
          {
            Main.this.iv_act_main_all_on.setVisibility(View.VISIBLE);
            Main.this.act_gray_layer.setVisibility(View.GONE);
            Main.access$202(Main.this, false);
          }
          if (str1.substring(12, 14).equals("00"))
          {
            Main.this.iv_act_main_all_on.setVisibility(View.GONE);
            Main.this.act_gray_layer.setVisibility(View.VISIBLE);
            Main.access$202(Main.this, true);
          }
          if (str1.substring(14, 16).equalsIgnoreCase("DD"))
          {
            SharedPreferencesUtil.keepShared(DeviceListActivity.deviceMacAddress + "lampType", "rgbw");
            Main.canAddRcOrPanel = true;
            localActivity2 = Main.this.getLocalActivityManager().getActivity(Main.this.tabHost.getCurrentTabTag());
            if ((localActivity2 == null) || (!(localActivity2 instanceof ActColor)));
          }
        }
        try
        {
          ((ActColor)localActivity2).showRgbwSeekBarStatus();
          if (str1.substring(14, 16).equalsIgnoreCase("CC"))
          {
            SharedPreferencesUtil.keepShared(DeviceListActivity.deviceMacAddress + "lampType", "rgb");
            Main.canAddRcOrPanel = true;
            localActivity1 = Main.this.getLocalActivityManager().getActivity(Main.this.tabHost.getCurrentTabTag());
            if ((localActivity1 == null) || (!(localActivity1 instanceof ActColor)));
          }
        }
        catch (Exception localException2)
        {
          try
          {
            Activity localActivity1;
            ((ActColor)localActivity1).showRgbSeekBarStatus();
            if ((str1.length() == 18) && (str1.indexOf("FDEB device updata status return") != -1))
              str3 = str1.substring(12, 14);
            switch (str3.hashCode())
            {
            default:
              k = -1;
              switch (k)
              {
              default:
                if ((str1.indexOf("FE") == -1) || (str1.length() < 80))
                  continue;
                Main.this.softVersion = StringUtils.bytesStr2WordStr(str1.substring(48, 72));
                Main.this.softVersion = Main.this.softVersion.replace(".", "");
                Main.this.softVersion = Main.this.softVersion.substring(4, Main.this.softVersion.length());
                if (Main.this.softVersion.equals("000000"))
                {
                  Main.this.softVersion = "0";
                  i = 1;
                  try
                  {
                    str2 = str1.substring(22, 24);
                    switch (str2.hashCode())
                    {
                    case 1537:
                      if (i <= Integer.parseInt(Main.this.softVersion))
                        continue;
                      Main.this.softVersion = StringUtils.bytesStr2WordStr(str1.substring(48, 72));
                      return;
                    case 1538:
                    case 1539:
                    case 1540:
                    }
                  }
                  catch (NumberFormatException localNumberFormatException)
                  {
                    localNumberFormatException.printStackTrace();
                    return;
                  }
                  localException2 = localException2;
                  localException2.printStackTrace();
                }
              case 0:
              case 1:
              case 2:
              case 3:
              }
            case 1536:
            case 1537:
            case 1538:
            case 1539:
            }
          }
          catch (Exception localException1)
          {
            while (true)
            {
              String str3;
              String str2;
              localException1.printStackTrace();
              continue;
              if (!str3.equals("00"))
                continue;
              int k = 0;
              continue;
              if (!str3.equals("01"))
                continue;
              k = 1;
              continue;
              if (!str3.equals("02"))
                continue;
              k = 2;
              continue;
              if (!str3.equals("03"))
                continue;
              k = 3;
              continue;
              Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_FILE_NAME);
              continue;
              Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_75_FILE_NAME);
              continue;
              Main.this.beginUpdata(SynProgram2Device.ONE_PIONT_ONE_800FILE_NAME);
              continue;
              Main.this.beginUpdata(SynProgram2Device.WIFI_101_DMX4_UP_V0);
              continue;
              Main.this.softVersion = Main.this.softVersion.replaceFirst("^0*", "");
              continue;
              if (!str2.equals("01"))
                break;
              j = 0;
              break label974;
              if (!str2.equals("02"))
                break;
              j = 1;
              break label974;
              if (!str2.equals("03"))
                break;
              j = 2;
              break label974;
              boolean bool = str2.equals("04");
              if (!bool)
                break;
              j = 3;
              break label974;
              int i = -2;
              continue;
              i = -2;
              continue;
              i = -2;
              continue;
              i = -2;
            }
            int j = -1;
            label974: switch (j)
            {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
            }
          }
        }*/
        }

        public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte) {
            onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
            System.out.println(" main onRecvPipeSyncData      " + StringUtils.btye2Str(paramArrayOfByte));
        }

        public void onStart(int paramInt) {
        }
    }
}