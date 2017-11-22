package com.ex.ltech.led.acti.main;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ex.ltech.WifiReceiver;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.device.ActSetting;
import com.ex.ltech.led.acti.device.AtCfg1Activity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.pullfresh.PullToRefreshLayout;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenu;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuCreator;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem;
import com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuListView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.LogTool;
import com.ex.ltech.led.utils.StringUtils;
import com.fragmentmaster.app.MasterActionBarActivity;
import com.loopj.android.http.TextHttpResponseHandler;
import com.soundcloud.android.crop.Crop;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import io.xlink.wifi.js.bean.Device;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.js.util.XlinkUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.ConnectDeviceListener;
import io.xlink.wifi.sdk.listener.ScanDeviceListener;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.SetDeviceAccessKeyListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

public class DeviceListActivity extends MasterActionBarActivity implements View.OnClickListener {
    public static String deviceMacAddress = "";
    public static String devicePid = "";
    public static String dname;
    public static boolean isOnePointFive = false;
    public static int netStats;
    Runnable Heartbeat = new Runnable() {
        public void run() {
            DeviceListActivity.this.handler.removeCallbacks(this);
            try {
                byte[] arrayOfByte = {102};
                XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
                DeviceManage.getInstance();
                localXlinkAgent.sendPipeData(DeviceManage.xDevice, arrayOfByte, new SendPipeListener() {
                    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                    }
                });
                DeviceListActivity.this.handler.postDelayed(DeviceListActivity.this.Heartbeat, 8000L);
            } catch (Exception localException) {
                    localException.printStackTrace();
                    DeviceListActivity.this.handler.postDelayed(DeviceListActivity.this.Heartbeat, 8000L);
            }
        }
    };
    Runnable LoopDeviceListRunnable = new Runnable() {
        public void run() {
            if (DeviceListActivity.this.loopTime < DeviceListActivity.this.deviceListAdapter.getCount()) {
                DeviceListActivity.this.connetAndgetInfo4device(DeviceListActivity.this.loopTime);
                if (DeviceListActivity.this.loopTime < -1 + DeviceListActivity.this.deviceListAdapter.getCount()) {
                    DeviceListActivity localDeviceListActivity = DeviceListActivity.this;
                    localDeviceListActivity.loopTime = (1 + localDeviceListActivity.loopTime);
                    DeviceListActivity.this.handler.postDelayed(DeviceListActivity.this.LoopDeviceListRunnable, 300L);
                }
            } else {
        isLayoutRefreshing = false;
                DeviceListActivity.this.mRefreshLayout.refreshFinish(0);
                isLayoutRefreshing = false;
            }

        }
    };
    int SHOT_REQ_CODE = 1;
    private int appid;
    private String authKey;
    private PopupWindow bottomPopWin = null;
    private ImageView btn_act_new_main_camera;
    private Business business;
    CmdDateBussiness cmdDateBussiness = new CmdDateBussiness(this, "0000");
    private ConnectDeviceListener connectDeviceListener = new ConnectDeviceListener() {
        public void onConnectDevice(XDevice paramXDevice, int paramInt) {
            /*int i = 1;
            if (!DeviceListActivity.this.isOnResume) {

            }else {
                if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getProductId() == null) ||
                (paramXDevice.getMacAddress().length() == 0) || (paramXDevice.getProductId().length() == 0))
                    continue;
                switch (paramInt) {
                    default:
                        XlinkUtils.shortTips("连接设备失败，其他错误码:" + paramInt);
                        return;
                    case 0:
                        DeviceListActivity.netStats = 0;
                        if (!DeviceListActivity.this.isOnResume)
                            continue;
                        DeviceListActivity.isOnePointFive = false;
                        DeviceManage.getInstance().updateDevice(paramXDevice);
                        DeviceListActivity.deviceMacAddress = paramXDevice.getMacAddress();
                        DeviceListActivity.devicePid = paramXDevice.getProductId();
                        if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                            continue;
                        XlinkUtils.shortTips("正在局域网控制设备(" + paramXDevice.getMacAddress() + ")");
                        XlinkAgent.getInstance().sendProbe(paramXDevice);
                        if ((paramXDevice.getProductId().equals("3864ebbb24cf4cab9d3ce823a0cfe93f") | paramXDevice.getProductId().equals("160fa2af1948f800160fa2af1948f801"))) {
                            Intent localIntent8 = new Intent(DeviceListActivity.this, Main.class);
                            DeviceListActivity.this.startActivity(localIntent8);
                        }
                        if ((paramXDevice.getProductId().equals("fc02a10aec1c46b8922630f6acd15ed6") | paramXDevice.getProductId().equals("160fa2b3051a03e9160fa2b3051ac601"))) {
                            Intent localIntent9 = new Intent(DeviceListActivity.this, com.ex.ltech.ct.AtColor.class);
                            DeviceListActivity.this.startActivity(localIntent9);
                        }
                        if (paramXDevice.getProductId().equals("df9725268da14c08ba806ec5a69edbf6")) {
                            Intent localIntent10 = new Intent(DeviceListActivity.this, com.ex.ltech.bwct.AtColor.class);
                            DeviceListActivity.this.startActivity(localIntent10);
                        }
                        if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) {
                            DeviceListActivity.isOnePointFive = i;
                            if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                                continue;
                            if (UserFerences.getUserFerences(DeviceListActivity.this).getValue("GateWayIdKey" + paramXDevice.getMacAddress()).length() != 0)
                                break label602;
                        }
                        while (true) {
                            try {
                                DeviceListActivity.this.setDeviceItem(paramXDevice);
                                DeviceListActivity.this.setDeviceItem(paramXDevice);
                                DeviceListActivity.this.handler.postDelayed(new Runnable() {
                                                                                public void run() {
                                                                                    Intent localIntent = new Intent(DeviceListActivity.this, AtMain.class);
                                                                                    DeviceListActivity.this.startActivity(localIntent);
                                                                                }
                                                                            }
                                        , 1000L);
                                UserFerences.getUserFerences(DeviceListActivity.this).putValue("GateWayMacIdKey", paramXDevice.getMacAddress());
                                if (!paramXDevice.getProductId().equals("8e28d6ebd1634ecf86161997912b895e"))
                                    continue;
                                Intent localIntent11 = new Intent(DeviceListActivity.this, ActOutLet.class);
                                DeviceListActivity.this.startActivity(localIntent11);
                                if (!paramXDevice.getProductId().equals("5b05f623bdcf48d9b0fc1507a79bb847"))
                                    break label664;
                                if (Build.CPU_ABI.indexOf("arm") != -1)
                                    break label629;
                                j = i;
                                if (Build.CPU_ABI.indexOf("") != -1)
                                    break label635;
                                if ((j | i) == 0)
                                    break;
                                Toast.makeText(DeviceListActivity.this.getApplicationContext(), DeviceListActivity.this.getString(R.string.no_arm_cpu), Toast.LENGTH_SHORT).show();
                                return;
                            } catch (Exception localException2) {
                                localException2.printStackTrace();
                                continue;
                            }
                            Intent localIntent14 = new Intent(DeviceListActivity.this, AtMain.class);
                            DeviceListActivity.this.startActivity(localIntent14);
                            continue;
                            int j = 0;
                            continue;
                            i = 0;
                        }
                        Intent localIntent13 = new Intent(DeviceListActivity.this, HongWaiMain.class);
                        DeviceListActivity.this.startActivity(localIntent13);
                        if (!(paramXDevice.getProductId().equals("160fa2afd1a7f600160fa2afd1a7f601") | paramXDevice.getProductId().equals("160fa2b2db6403e9160fa2b2db646801")))
                            continue;
                        Intent localIntent12 = new Intent(DeviceListActivity.this, NewHongWaiMain.class);
                        DeviceListActivity.this.startActivity(localIntent12);
                        return;
                    case 1:
                        label602:
                        label629:
                        label635:
                        DeviceListActivity.netStats = i;
                        label664:
                        if ((!DeviceListActivity.this.isOnResume) || (paramXDevice.getMacAddress() == null) || (paramXDevice.getProductId() == null) || (paramXDevice.getMacAddress().length() == 0) || (paramXDevice.getProductId().length() == 0))
                            continue;
                        DeviceListActivity.isOnePointFive = false;
                        DeviceManage.getInstance().updateDevice(paramXDevice);
                        DeviceListActivity.deviceMacAddress = paramXDevice.getMacAddress();
                        DeviceListActivity.devicePid = paramXDevice.getProductId();
                        XlinkUtils.shortTips("正在通过云端控制设备(" + paramXDevice.getMacAddress() + ")");
                        DeviceManage.getInstance().addDevice(paramXDevice);
                        if ((paramXDevice.getProductId().equals("fc02a10aec1c46b8922630f6acd15ed6") | paramXDevice.getProductId().equals("160fa2b3051a03e9160fa2b3051ac601"))) {
                            Intent localIntent1 = new Intent(DeviceListActivity.this, com.ex.ltech.ct.AtColor.class);
                            DeviceListActivity.this.startActivity(localIntent1);
                        }
                        if ((paramXDevice.getProductId().equals("3864ebbb24cf4cab9d3ce823a0cfe93f") | paramXDevice.getProductId().equals("160fa2af1948f800160fa2af1948f801"))) {
                            Intent localIntent2 = new Intent(DeviceListActivity.this, Main.class);
                            DeviceListActivity.this.startActivity(localIntent2);
                        }
                        if (paramXDevice.getProductId().equals("8e28d6ebd1634ecf86161997912b895e")) {
                            Intent localIntent3 = new Intent(DeviceListActivity.this, ActOutLet.class);
                            DeviceListActivity.this.startActivity(localIntent3);
                        }
                        if (paramXDevice.getProductId().equals("5b05f623bdcf48d9b0fc1507a79bb847")) {
                            if (Build.CPU_ABI.indexOf("arm") == -1) {
                                Toast.makeText(DeviceListActivity.this.getApplicationContext(), DeviceListActivity.this.getString(R.string.no_arm_cpu), Toast.LENGTH_SHORT).show();
                                return;
                            }
                            Intent localIntent7 = new Intent(DeviceListActivity.this, HongWaiMain.class);
                            DeviceListActivity.this.startActivity(localIntent7);
                        }
                        if ((paramXDevice.getProductId().equals("160fa2afd1a7f600160fa2afd1a7f601") | paramXDevice.getProductId().equals("160fa2b2db6403e9160fa2b2db646801"))) {
                            Intent localIntent4 = new Intent(DeviceListActivity.this, NewHongWaiMain.class);
                            DeviceListActivity.this.startActivity(localIntent4);
                        }
                        if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) {
                            DeviceListActivity.isOnePointFive = i;
                            if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                                continue;
                            if (UserFerences.getUserFerences(DeviceListActivity.this).getValue("GateWayIdKey" + paramXDevice.getMacAddress()).length() != 0)
                                break label1239;
                        }
                    case 102:
                        XlinkUtils.shortTips("设备认证失败");
                        break;
                    case 110:
                        XlinkUtils.shortTips("设备不在线");
                        break;
                    case 200:
                        XlinkUtils.shortTips("连接设备超时");
                        break;
                    case 104:
                        XlinkUtils.shortTips("连接设备失败，服务器内部错误");
                        break;
                    case 111:
                        XlinkUtils.shortTips("连接设备失败，设备未在局域网内，且当前手机只有局域网环境");
                        break;
                }
            }
            while (true) {
                try {
                    DeviceListActivity.this.setDeviceItem(paramXDevice);
                    DeviceListActivity.this.setDeviceItem(paramXDevice);
                    DeviceListActivity.this.handler.postDelayed(new Runnable() {
                                                                    public void run() {
                                                                        Intent localIntent = new Intent(DeviceListActivity.this, AtMain.class);
                                                                        DeviceListActivity.this.startActivity(localIntent);
                                                                    }
                                                                }
                            , 500L);
                    UserFerences.getUserFerences(DeviceListActivity.this).putValue("GateWayMacIdKey", paramXDevice.getMacAddress());
                    if (!paramXDevice.getProductId().equals("df9725268da14c08ba806ec5a69edbf6"))
                        break;
                    Intent localIntent5 = new Intent(DeviceListActivity.this, com.ex.ltech.bwct.AtColor.class);
                    DeviceListActivity.this.startActivity(localIntent5);
                    return;
                } catch (Exception localException1) {
                    localException1.printStackTrace();
                    continue;
                }
                Intent localIntent6 = new Intent(DeviceListActivity.this, AtMain.class);
                DeviceListActivity.this.startActivity(localIntent6);
            }*/

        }
    };
    private File currentFile;
    int dCount;
    private DeviceListAdapter deviceListAdapter;
    private ArrayList<Device> devices = new ArrayList();
    AlertDialog dialog;
    Handler handler = new Handler() {
        public void handleMessage(Message paramMessage) {
            super.handleMessage(paramMessage);
            isLayoutRefreshing = false;
            if (paramMessage.what != 0)
                Toast.makeText(DeviceListActivity.this.getApplicationContext(), DeviceListActivity.this.getString(R.string.no_find_device), Toast.LENGTH_SHORT).show();
        }
    };
    private String id;
    boolean isGetDeviceOk;
    private boolean isGoSetting;
    private boolean isLayoutRefreshing;
    boolean isOnResume = true;
    int itemPosi;
    private ImageView iv_act_new_main;
    private ImageView iv_act_new_main_piker;
    private ImageView iv_acti_neww_main_weather;
    Handler locatAndWeatherHandler = new Handler() {
        public void handleMessage(Message paramMessage) {
            super.handleMessage(paramMessage);
            /*switch (paramMessage.what) {
                case 2:
                default:
                    return;
                case 1:
                    DeviceListActivity.this.tv_act_new_main_city.setText(DeviceListActivity.this.locationAndWeatherBusiness.getCityVo().getCity());
                    return;
                case 3:
            }
            DeviceListActivity.this.setWeatherView();*/
        }
    };
//    private LocationAndWeatherBusiness locationAndWeatherBusiness;
    int loopTime = 0;
    private SwipeMenuListView lv;
    private PullToRefreshLayout mRefreshLayout;
    private View popView;
    List<String> recDataList = new ArrayList();
    RelativeLayout rl_act_new;
    private ScanDeviceListener scanListener = new ScanDeviceListener() {
        public void onGotDeviceByScan(XDevice paramXDevice) {
            LogTool.d("onGotDeviceByScan");
            DeviceManage.getInstance().addDevice(paramXDevice);
            XlinkAgent.getInstance().setDeviceAccessKey(paramXDevice, 8888, new SetDeviceAccessKeyListener() {
                public void onSetLocalDeviceAccessKey(XDevice paramXDevice, int paramInt1, int paramInt2) {
                    if (DeviceListActivity.this.isOnResume)
                        DeviceManage.getInstance().updateDevice(paramXDevice);
                }
            });
            paramXDevice.getDeviceId();
            DeviceListActivity.this.devices.clear();
            isLayoutRefreshing = false;
            LogTool.d("size :" + DeviceManage.getInstance().getDevices().size());
            DeviceListActivity.this.devices.addAll(DeviceManage.getInstance().getDevices());
            DeviceManage.getInstance().saveDevices(DeviceListActivity.this.devices);
            DeviceListActivity.this.handler.postDelayed(new Runnable() {
                                                            public void run() {
                                                            }
                                                        }
                    , 200L);
        }
    };
    private int screenWidth;
    private Bitmap tempBm;
    TelephonyManager tm;
    private TextView tv_act_new_main_city;
    private TextView tv_act_new_main_wendu;
    private TextView tv_act_new_main_wendu_max_min;
    private WifiReceiver wifiReceiver;

    static {
        dname = "";
    }

    private void beginCrop(Uri paramUri) {
        Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
    }

    /*private void connectDevice(Device paramDevice) {
        int i = XlinkAgent.getInstance().connectDevice(paramDevice.getXDevice(), this.connectDeviceListener);
        if (i != 0) ;
        switch (i) {
            case -10:
            case -9:
            case -8:
            case -7:
            case -5:
            default:
            case -4:
                do
                    return;
                while (!XlinkUtils.isConnected());
                int j = SharedPreferencesUtil.queryIntValue("appId").intValue();
                String str = SharedPreferencesUtil.queryValue("authKey", "");
                XlinkAgent.getInstance().start();
                XlinkAgent.getInstance().login(j, str);
                return;
            case -6:
        }
        XlinkAgent.getInstance().initDevice(paramDevice.getXDevice());
    }*/

    private void connetAndgetInfo4device(int paramInt) {
        /*XlinkAgent.getInstance().connectDevice(((Device) this.devices.get(paramInt)).getXDevice(), new ConnectDeviceListener(paramInt) {
            public void onConnectDevice(XDevice paramXDevice, int paramInt) {
                if ((itemPosi >= DeviceListActivity.this.deviceListAdapter.getCount()) || (paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                    ;
                do {
                    do
                        return;
                    while (!paramXDevice.getMacAddress().equalsIgnoreCase(((Device) DeviceListActivity.this.devices.get(itemPosi)).getMacAddress()));
                    if ((DeviceListActivity.this.dialog == null) || (!DeviceListActivity.this.dialog.isShowing()))
                        continue;
                    DeviceListActivity.this.dialog.dismiss();
                }
                while (((paramInt != 1) && (paramInt != 0)) || (!DeviceListActivity.this.isOnResume));
                Device localDevice = (Device) DeviceListActivity.this.devices.get(itemPosi);
                ((Device) DeviceListActivity.this.devices.get(itemPosi)).setIsOnline(true);
                DeviceListActivity.this.updataLvStatus();
                if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) ;
                for (byte[] arrayOfByte = DeviceListActivity.this.cmdDateBussiness.getOneZeroFiveDeviceInfoCmd(); ; arrayOfByte = DeviceListActivity.this.cmdDateBussiness.getDeviceInfoCmd()) {
                    XlinkAgent.getInstance().sendPipeData(localDevice.getXDevice(), arrayOfByte, new SendPipeListener() {
                        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                        }
                    });
                    return;
                }
            }
        });*/
    }

    private void findV() {
        this.iv_acti_neww_main_weather = ((ImageView) findViewById(R.id.iv_acti_neww_main_weather));
        this.iv_act_new_main_piker = ((ImageView) findViewById(R.id.iv_act_new_main_piker));
        this.iv_act_new_main = ((ImageView) findViewById(R.id.iv_act_new_main));
        this.btn_act_new_main_camera = ((ImageView) findViewById(R.id.btn_act_new_main_camera));
        this.tv_act_new_main_wendu_max_min = ((TextView) findViewById(R.id.tv_act_new_main_wendu_1));
        this.tv_act_new_main_wendu = ((TextView) findViewById(R.id.tv_act_new_main_wendu_2));
        this.tv_act_new_main_city = ((TextView) findViewById(R.id.tv_act_new_main_city));
        this.lv = ((SwipeMenuListView) findViewById(R.id.tv_act_new_main));
        this.lv.setDividerHeight(0);
        this.lv.setMenuCreator(new SwipeMenuCreator() {
            public void create(SwipeMenu paramSwipeMenu) {
                SwipeMenuItem localSwipeMenuItem = new SwipeMenuItem(DeviceListActivity.this.getApplicationContext());
                localSwipeMenuItem.setBackground(new ColorDrawable(DeviceListActivity.this.getResources().getColor(R.color.color5)));
                localSwipeMenuItem.setWidth(BitmapUtils.dp2px(DeviceListActivity.this.getApplicationContext(), 50.0F));
                localSwipeMenuItem.setIcon(R.mipmap.ci_del_btn);
                paramSwipeMenu.addMenuItem(localSwipeMenuItem);
            }
        });
        this.lv.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            public boolean onMenuItemClick(int paramInt1, SwipeMenu paramSwipeMenu, int paramInt2) {
                if (paramInt2 == 0) {
                    /*if ((paramInt2 == 0) && (((Device) DeviceListActivity.this.devices.get(paramInt1)).getXDevice().getProductId()
                            .equalsIgnoreCase("160fa2b1d84e03e9160fa2b1d84eaa01"))) {
                        RoomsBusiness localRoomsBusiness = new RoomsBusiness(DeviceListActivity.this);
                        localRoomsBusiness.putData4ClassName(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress(), localRoomsBusiness.getDefaultHome());
                        localRoomsBusiness.putData4ClassName(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress(), new Scenes());
                        localRoomsBusiness.putData4ClassName(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress(), new Timings());
                        localRoomsBusiness.putData4ClassName(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress() + "GohomeGeoSpaceVo ", new GeoSpaceVo());
                        localRoomsBusiness.putData4ClassName(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress() + "OuthomeGeoSpaceVo", new GeoSpaceVo());
                    }
                    DeviceManage.getInstance().removeDevice(((Device) DeviceListActivity.this.devices.get(paramInt1)).getMacAddress());
                    DeviceListActivity.this.devices.remove(paramInt1);
                    DeviceListActivity.this.reSortDeviceData(DeviceListActivity.this.devices);
                    DeviceListActivity.this.deviceListAdapter.notifyDataSetChanged();*/
                } else {
                   /*  XlinkAgent.getInstance().sendPipeData(((Device) DeviceListActivity.this.devices.get(paramInt1)).getXDevice(),
                            DeviceListActivity.this.cmdDateBussiness.sendUpdataWifiCmd(), new SendPipeListener() {
                                public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                                }
                            });
                   HttpAgent.getInstance().upgradeDevice(((Device) DeviceListActivity.this.devices.get(paramInt1)).getXDevice().getProductId(),
                            ((Device) DeviceListActivity.this.devices.get(paramInt1)).getXDevice().getDeviceId() + "", new TextHttpResponseHandler() {

                                @Override
                                public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString, Throwable throwable) {
                                    Toast.makeText(DeviceListActivity.this.getApplicationContext(), "onFailure", Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, String responseString) {
                                    Toast.makeText(DeviceListActivity.this.getApplicationContext(), "onSuccess", Toast.LENGTH_SHORT).show();
                                }

                            });*/
                }
                return true;
            }
            });
        this.rl_act_new = ((RelativeLayout) findViewById(R.id.rl_act_new));
        getLayoutInflater();
        this.popView = LayoutInflater.from(this).inflate(R.layout.col_act_pop, null);
        this.bottomPopWin = new PopupWindow(this.popView, -2, -2);
        this.bottomPopWin.setAnimationStyle(R.style.popwin_anim_style);
        this.bottomPopWin.setOutsideTouchable(true);
        this.bottomPopWin.setTouchable(true);
        this.bottomPopWin.setBackgroundDrawable(new ColorDrawable(0));
        this.mRefreshLayout = ((PullToRefreshLayout) findViewById(R.id.refresh_view));
    }

    private void handleCrop(int paramInt, Intent paramIntent) {
        /*if (paramInt == -1)
            new Thread(new Runnable(paramIntent) {
                public void run() {
                    DeviceListActivity.this.tempBm = BitmapUtils.squareCropRectangle(
                            BitmapUtils.getBitmapFromUri(DeviceListActivity.this.getApplicationContext(),
                                    Crop.getOutput(this.val$result)), DeviceListActivity.this.screenWidth,
                            BitmapUtils.dp2px(DeviceListActivity.this.getApplicationContext(), 220.0F));
                    String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
                    DeviceListActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            DeviceListActivity.this.iv_act_new_main.setImageBitmap(DeviceListActivity.this.tempBm);
                        }
                    });
                    UserFerences.getUserFerences(DeviceListActivity.this.getApplicationContext()).putValue("/ltech/led/mainTitle", str);
                    FileUtil.saveMyBitmap(str, DeviceListActivity.this.tempBm, "/ltech/led/image");
                }
            }).start();*/
    }

    private void init() {
        this.business = new Business(this);
        this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong) {
                try {
                    /*DeviceListActivity.this.handler.removeCallbacks(DeviceListActivity.this.LoopDeviceListRunnable);
                    DeviceListActivity.this.connectDevice((Device) DeviceListActivity.this.devices.get(paramInt));
                    TextView localTextView1 = (TextView) (TextView) paramView.findViewById(R.id.tv_device_name);
                    TextView localTextView2 = (TextView) (TextView) paramView.findViewById(R.id.tv_status);
                    DeviceListActivity.dname = localTextView1.getText().toString();
                    SharedPreferencesUtil.keepShared("dStatus", localTextView2.getText().toString());
                    SharedPreferencesUtil.keepShared("dname", DeviceListActivity.dname);
                    SharedPreferencesUtil.keepShared("dMacAddress", ((Device) DeviceListActivity.this.devices.get(paramInt)).getMacAddress());*/
                    return;
                } catch (Exception localException) {
                    localException.printStackTrace();
                }
            }
        });
        String str = UserFerences.getUserFerences(this).spFerences.getString("/ltech/led/mainTitle", "");
        if (!str.equals("")) {
            int i = BitmapUtils.getExifOrientation(str);
            /*if ((i != 90) && (i != 180) && (i != 270))
                break label184;
            new Thread(str) {
                public void run() {
                    super.run();
                    Bitmap localBitmap1 = BitmapUtils.zoomOutBM(BitmapFactory.decodeFile(this.val$titleBgPath),
                            BitmapUtils.dp2px(DeviceListActivity.this.getApplicationContext(), 220.0F));
                    int i = BitmapUtils.getExifOrientation(this.val$titleBgPath);
                    Matrix localMatrix = new Matrix();
                    localMatrix.postRotate(i);
                    final Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0,
                            localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, false);
                    DeviceListActivity.this.runOnUiThread(new Runnable() {
                        public void run() {
                            DeviceListActivity.this.iv_act_new_main.setImageBitmap(localBitmap2);
                        }
                    });
                }
            }
                    .start();*/
        }else {
            this.iv_act_new_main.setImageBitmap(BitmapFactory.decodeFile(str));
        }
        reSortDeviceData(devices);
        this.deviceListAdapter = new DeviceListAdapter(this, this.devices);
        this.lv.setAdapter(this.deviceListAdapter);
        this.deviceListAdapter.setOnFreshDevice(new DeviceListAdapter.OnFreshDevice() {
            public void OnClick(int paramInt) {
                DeviceListActivity.this.itemPosi = paramInt;
                DeviceListActivity.this.dialog = ProgressDialog.show(DeviceListActivity.this, "", DeviceListActivity.this.getString(R.string.get_d_info), false);
                DeviceListActivity.this.dialog.setCancelable(true);
                DeviceListActivity.this.dialog.show();
                DeviceListActivity.this.isGetDeviceOk = false;
                try {
                    XlinkAgent.getInstance().connectDevice(((Device) DeviceListActivity.this.devices.get(paramInt)).getXDevice(),
                            new ConnectDeviceListener() {
                                public void onConnectDevice(XDevice paramXDevice, int paramInt) {
                                    if ((DeviceListActivity.this.dialog != null) && (DeviceListActivity.this.dialog.isShowing()))
                                        DeviceListActivity.this.dialog.dismiss();
                                    if ((paramInt == 1) || (paramInt == 0))
                                        try {
                                            DeviceListActivity.this.setDeviceItem(itemPosi);
                                            return;
                                        } catch (Exception localException) {
                                            localException.printStackTrace();
                                            return;
                                        }
                                    Toast.makeText(DeviceListActivity.this.getApplicationContext(), DeviceListActivity.this.getString(R.string.load_fail), Toast.LENGTH_SHORT).show();
                                }
                            });
                    return;
                } catch (Exception localException) {
                    localException.printStackTrace();
                }
            }
        });
        this.mRefreshLayout.setOnRefreshListener(new PullToRefreshLayout.OnRefreshListener() {
            public void onLoadMore(PullToRefreshLayout paramPullToRefreshLayout) {
            }

            public void onRefresh(PullToRefreshLayout paramPullToRefreshLayout) {
                if (!DeviceListActivity.this.isLayoutRefreshing) {
                    DeviceListActivity.this.refreshAction();
                    System.out.println("onRefresh" + Thread.currentThread().getId());
                    new Thread() {
                        public void run() {
                            super.run();
                        }
                    }
                            .start();
                }
            }
        });
    }

    private void scan() {
        this.isLayoutRefreshing = false;
        this.recDataList.clear();
        scanDevice();
        this.loopTime = 0;
        this.handler.postDelayed(this.LoopDeviceListRunnable, 1000L);
    }

    private void scanDevice() {
        int value = XlinkAgent.getInstance().scanDeviceByProductId("160fa2b1d84e03e9160fa2b1d84eaa01", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("3864ebbb24cf4cab9d3ce823a0cfe93f", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("160fa2af1948f800160fa2af1948f801", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("8e28d6ebd1634ecf86161997912b895e", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("5b05f623bdcf48d9b0fc1507a79bb847", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("fc02a10aec1c46b8922630f6acd15ed6", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("df9725268da14c08ba806ec5a69edbf6", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("160fa2afd1a7f600160fa2afd1a7f601", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("160fa2b2db6403e9160fa2b2db646801", this.scanListener);
        XlinkAgent.getInstance().scanDeviceByProductId("160fa2b3051a03e9160fa2b3051ac601", this.scanListener);
    }

    private void sdkLogin() {//TODO
        XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener() {
            public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt) {
            }

            public void onDeviceStateChanged(XDevice paramXDevice, int paramInt) {
            }

            public void onDisconnect(int paramInt) {
                LogTool.e("login code" + paramInt);
            }

            public void onEventNotify(EventNotify paramEventNotify) {
            }

            public void onLocalDisconnect(int paramInt) {
                LogTool.e("login code" + paramInt);
            }

            public void onLogin(int paramInt) {
                LogTool.d("onLogin" + paramInt);
                DeviceListActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        DeviceListActivity.this.refreshAction();
                    }
                });
            }

            public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte) {
                String str;
                try {
                    if (!DeviceListActivity.this.isOnResume)
                        return;
                    str = StringUtils.btye2Str(paramArrayOfByte);
                    LogTool.d("onRecvPipeData" + str + " XDevice :" + paramXDevice.getMacAddress());
                    for(int i = 0; i < DeviceListActivity.this.recDataList.size(); i++){
                        if (str.equalsIgnoreCase((String) DeviceListActivity.this.recDataList.get(i)))
                            DeviceListActivity.this.recDataList.add(str);
                    }

                    if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) {
                        if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0)){

                        }else {
                            DeviceListActivity.this.business.savaOneZeroFiveData(str, paramXDevice.getMacAddress());
                            DeviceListActivity.this.isGetDeviceOk = true;
                            DeviceListActivity.this.reSortDeviceData(DeviceListActivity.this.devices);
                            DeviceListActivity.this.deviceListAdapter.notifyDataSetChanged();
                        }

                    }
                } catch (Exception localException) {
                    localException.printStackTrace();
                    return;
                }
                if ((str.indexOf("66BB") == -1) || (str.indexOf("EB") == -1) || (paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0)){

                }else
                    DeviceListActivity.this.business.saveDeviceInfo4Device(str, paramXDevice.getMacAddress());


                    /*String str;
                    try {
                        if (!DeviceListActivity.this.isOnResume)
                            return;
                        str = StringUtils.btye2Str(paramArrayOfByte);
                        int i = 0;
                        if (i >= DeviceListActivity.this.recDataList.size())
                            continue;
                        if (str.equalsIgnoreCase((String) DeviceListActivity.this.recDataList.get(i)))
                            break;
                        i++;
                        continue;
                        DeviceListActivity.this.recDataList.add(str);
                        if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) {
                            if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                                break;
                            DeviceListActivity.this.business.savaOneZeroFiveData(str, paramXDevice.getMacAddress());
                            DeviceListActivity.this.isGetDeviceOk = true;
                            DeviceListActivity.this.reSortDeviceData(DeviceListActivity.this.devices);
                            DeviceListActivity.this.deviceListAdapter.notifyDataSetChanged();
                            return;
                        }
                    } catch (Exception localException) {
                        localException.printStackTrace();
                        return;
                    }
                    if ((str.indexOf("66BB") == -1) || (str.indexOf("EB") == -1) || (paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
                        break;
                    DeviceListActivity.this.business.saveDeviceInfo4Device(str, paramXDevice.getMacAddress());*/
            }

            public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte) {
                onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
            }

            public void onStart(int paramInt) {
                Log.e("TAG", "login code" + paramInt);
            }
        });
        if (!XlinkAgent.getInstance().isConnectedLocal())
            XlinkAgent.getInstance().start();
        if (!XlinkAgent.getInstance().isConnectedOuterNet()) {
            XlinkAgent.getInstance().login(MyApp.getApp().getAppid(), MyApp.getApp().getAuth());
            System.out.println("");
        }
        MyApp.getApp().setAppid(this.appid);
        MyApp.getApp().setAuth(this.authKey);
        MyApp.getApp().auth = true;
    }

    private void setDeviceItem(int paramInt) {
        /*Device localDevice = (Device) this.devices.get(paramInt);
        if (localDevice.getXDevice().getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) ;
        for (byte[] arrayOfByte = this.cmdDateBussiness.getOneZeroFiveDeviceInfoCmd(); ; arrayOfByte = this.cmdDateBussiness.getDeviceInfoCmd()) {
            XlinkAgent.getInstance().sendPipeData(localDevice.getXDevice(), arrayOfByte, new SendPipeListener() {
                public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                }
            });
            return;
        }*/
    }

    private void setDeviceItem(XDevice paramXDevice) {
        if (paramXDevice.getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01")) ;
        for (byte[] arrayOfByte = this.cmdDateBussiness.getOneZeroFiveDeviceInfoCmd(); ; arrayOfByte = this.cmdDateBussiness.getDeviceInfoCmd()) {
            XlinkAgent.getInstance().sendPipeData(paramXDevice, arrayOfByte, new SendPipeListener() {
                public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                }
            });
            return;
        }
    }

    private void setDeviceListOfflineData() {
        for (int i = 0; i < this.devices.size(); i++)
            ((Device) this.devices.get(i)).setIsOnline(false);
        reSortDeviceData(this.devices);
    }

    private void setWeatherView() {
        /*CityVo localCityVo = this.locationAndWeatherBusiness.getCityVo();
        if (!UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", false)) {
            this.tv_act_new_main_city.setVisibility(View.GONE);
            this.tv_act_new_main_wendu_max_min.setVisibility(View.GONE);
            this.tv_act_new_main_wendu.setVisibility(View.GONE);
            this.iv_acti_neww_main_weather.setVisibility(View.GONE);
            this.iv_act_new_main_piker.setVisibility(View.GONE);
            this.tv_act_new_main_wendu_max_min.setVisibility(View.GONE);
        }
        this.tv_act_new_main_city.setText(this.locationAndWeatherBusiness.getCityVo().getCity());
        if ((localCityVo.getL_tmp() != null) && (localCityVo.getH_tmp() != null))
            this.tv_act_new_main_wendu_max_min.setText(localCityVo.getL_tmp() + "-" + localCityVo.getH_tmp() + "℃");
        if (localCityVo.getTemp() != null)
            this.tv_act_new_main_wendu.setText(localCityVo.getTemp() + "℃");
        if (Integer.parseInt(new SimpleDateFormat("HH").format(new Date())) > 12) ;
        for (String str = localCityVo.morningInfo; str.indexOf(getString(R.string.weather1)) != -1; str = localCityVo.nightInfo) {
            this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_1);
            return;
        }
        if (str.indexOf(getString(R.string.weather2)) != -1) {
            this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_2);
            return;
        }
        if (str.indexOf(getString(R.string.weather3)) != -1) {
            this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_3);
            return;
        }
        if (str.indexOf(getString(R.string.weather4)) != -1) {
            this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_4);
            return;
        }
        if (str.indexOf(getString(R.string.weather5)) != -1) {
            this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_5);
            return;
        }
        this.iv_acti_neww_main_weather.setBackgroundResource(R.drawable.weather_ic_6);*/
    }

    private void updataLvStatus() {
        reSortDeviceData(this.devices);
        this.deviceListAdapter.notifyDataSetChanged();
        System.out.println("updataLvStatus" + Thread.currentThread().getId());
    }

    public void add(View paramView) {
        startActivityForResult(new Intent(this, AtCfg1Activity.class), 0);
    }

    public void fresh(View paramView) {
    }

    public void getAppid() {
        /*HttpAgent.getInstance().getAppId(this.id, this.id, new TextHttpResponseHandler() {


            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
                XlinkAgent.getInstance().addXlinkListener(com.ex.ltech.MyApp.getApp());
                XlinkAgent.getInstance().start();
            }

            @Override
            public void onSuccess(int i, Header[] headers, String responseString) {
                try {
                    JSONObject localJSONObject1 = new JSONObject(responseString);
                    if (localJSONObject1.getInt("status") != 200)
                        return;
                    JSONObject localJSONObject2 = localJSONObject1.getJSONObject("user");
                    DeviceListActivity.this.appid=  localJSONObject2.getInt("id");
                    DeviceListActivity.this.authKey = localJSONObject2.getString("key");
                    SharedPreferencesUtil.keepShared("appId", DeviceListActivity.this.appid);
                    SharedPreferencesUtil.keepShared("authKey", DeviceListActivity.this.authKey);
                    DeviceListActivity.this.sdkLogin();
                    return;
                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                    XlinkUtils.shortTips("用户信息，json解析错误");
                }
            }

        });*/
    }

    public void goCarmare() {
        File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
        if (!localFile.exists())
            localFile.mkdirs();
        this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        localIntent.putExtra("output", Uri.fromFile(this.currentFile));
        startActivityForResult(localIntent, this.SHOT_REQ_CODE);
    }

    public boolean isHaveAppid() {
        return (this.appid != 0) && (!this.authKey.equals(""));
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        /*if (paramInt2 == 400)
            finish();
        if (paramInt2 == Global.net_config_ok)
            refreshAction();
        else {
            if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE)) {
                new Thread() {
                    public void run() {
                        super.run();
                        Bitmap localBitmap1 = BitmapUtils.zoomOutBM(BitmapFactory.decodeFile(DeviceListActivity.this.currentFile.getPath()), BitmapUtils.dp2px(DeviceListActivity.this.getApplicationContext(), 220.0F));
                        int i = BitmapUtils.getExifOrientation(DeviceListActivity.this.currentFile.getPath());
                        if ((i == 90) || (i == 180) || (i == 270) || (i == 0)) {
                            Matrix localMatrix = new Matrix();
                            localMatrix.postRotate(i);
                            Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, false);
                            DeviceListActivity.this.runOnUiThread(new Runnable() {
                                public void run() {
//                                    DeviceListActivity.this.iv_act_new_main.setImageBitmap(this.val$bitmap);
                                    DeviceListActivity.this.iv_act_new_main.setImageBitmap(tempBm);
                                }
                            });
                        }
                    }
                }.start();
                UserFerences.getUserFerences(this).putValue("/ltech/led/mainTitle", this.currentFile.getPath());
                return;
            }
            if ((paramInt1 != 9162) || (paramInt2 != -1))
                beginCrop(paramIntent.getData());
        }
        while (paramInt1 != 6709);
        handleCrop(paramInt2, paramIntent);*/
    }

    public void onClick(View paramView) {
        switch (paramView.getId()){
            case R.id.iv_act_new_main:
            case R.id.iv_acti_neww_main_weather:
                Intent localIntent8 = new Intent(DeviceListActivity.this, Main.class);
                DeviceListActivity.this.startActivity(localIntent8);
                break;
        }
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.act_new_main);
        findV();
        init();
        this.devices.addAll(DeviceManage.getInstance().getDevices());
        setDeviceListOfflineData();
        MyApp.getApp().auth = true;
        this.tm = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE));
        this.appid = SharedPreferencesUtil.queryIntValue("appId").intValue();
        this.authKey = SharedPreferencesUtil.queryValue("authKey", "");
        if (isHaveAppid())
            sdkLogin();
        else {
            UserFerences.getUserFerences(this).putValue("isLog", Boolean.valueOf(true));
            this.handler.postDelayed(new Runnable() {
                                         public void run() {
                                            /* DeviceListActivity.this.locationAndWeatherBusiness.canGetWeather = true;
                                             if (UserFerences.getUserFerences(DeviceListActivity.this).spFerences.getBoolean("isZh", false))
                                                 DeviceListActivity.this.locationAndWeatherBusiness.startLocat();*/
                                             DeviceListActivity.this.wifiReceiver = new WifiReceiver();
                                             IntentFilter localIntentFilter = new IntentFilter();
                                             localIntentFilter.addAction("android.net.wifi.STATE_CHANGE");
                                             localIntentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
                                             localIntentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
                                             DeviceListActivity.this.registerReceiver(DeviceListActivity.this.wifiReceiver, localIntentFilter);
                                         }
                                     }
                    , 1000L);
            registerUser();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.Heartbeat);
        this.handler.removeCallbacks(this.LoopDeviceListRunnable);
        if (this.wifiReceiver != null)
            unregisterReceiver(this.wifiReceiver);
        XlinkAgent.getInstance().stop();
    }

    protected void onPause() {
        super.onPause();
        this.isOnResume = false;
        this.business.flag = false;
        /*this.locationAndWeatherBusiness.netWorkStatus = false;
        this.locationAndWeatherBusiness.stopLocat();*/
    }

    protected void onResume() {
        super.onResume();
        this.isOnResume = true;
    }

    protected void onStop() {
        super.onStop();
        this.isOnResume = false;
    }

    public void onWindowFocusChanged(boolean paramBoolean) {
        super.onWindowFocusChanged(paramBoolean);
        this.screenWidth = this.rl_act_new.getWidth();
    }

    public void reSortDeviceData(List<Device> paramList) {
//    public void reSortDeviceData(List<Device> paramList) {
        /*ArrayList localArrayList1 = new ArrayList();
        ArrayList localArrayList2 = new ArrayList();
        int i = 0;
        if (i < paramList.size()) {
            Device localDevice = (Device) paramList.get(i);
            if (localDevice.isOnline()) {
                localDevice.setIsShowOnline(false);
                localDevice.setIsShowOffline(false);
                localArrayList1.add(localDevice);
            }else {
                localDevice.setIsShowOnline(false);
                localDevice.setIsShowOffline(false);
                localArrayList2.add(localDevice);
            }
        }
        paramList.clear();
        if (localArrayList1.size() > 0)
            ((Device) localArrayList1.get(0)).setIsShowOnline(true);
        if (localArrayList2.size() > 0)
            ((Device) localArrayList2.get(0)).setIsShowOffline(true);
        paramList.addAll(localArrayList1);
        paramList.addAll(localArrayList2);*/
    }

    void refreshAction() {
        this.isLayoutRefreshing = true;
        setDeviceListOfflineData();
        this.deviceListAdapter.notifyDataSetChanged();
        scan();
    }

    public void registerUser() {
        this.id = XlinkUtils.MD5(this.tm.getDeviceId());
        HttpAgent.getInstance().onRegister(this.id, this.id, this.id, new TextHttpResponseHandler() {

            @Override
            public void onFailure(int i, Header[] headers, String s, Throwable throwable) {

            }

            @Override
            public void onSuccess(int i, Header[] headers, String responseString) {
                try {
                    JSONObject localJSONObject1 = new JSONObject(responseString);
                    int value = localJSONObject1.getInt("status");
                    if (value== 200) {
                        JSONObject localJSONObject2 = localJSONObject1.getJSONObject("user");
                        DeviceListActivity.this.appid = localJSONObject2.getInt("id");
                        DeviceListActivity.this.authKey = localJSONObject2.getString("key");
                        SharedPreferencesUtil.keepShared("appId", DeviceListActivity.this.appid);
                        SharedPreferencesUtil.keepShared("authKey", DeviceListActivity.this.authKey);
                        DeviceListActivity.this.sdkLogin();
                        return;
                    }
                    if (value == 201) {
                        DeviceListActivity.this.getAppid();
                        return;
                    }
                } catch (JSONException localJSONException) {
                    localJSONException.printStackTrace();
                }
            }
        });
    }

    public void seletedPic(View paramView) {
        /*MyAlertDialog2 localMyAlertDialog2 = new MyAlertDialog2(this);
        localMyAlertDialog2.show();
        localMyAlertDialog2.setMyOnClickListener(new MyAlertDialog2.MyOnClickListener() {
            public void onClick(int paramInt) {
                if (paramInt == 1)
                    DeviceListActivity.this.goCarmare();
                if (paramInt == 2)
                    Crop.pickImage(DeviceListActivity.this);
                if (paramInt == 3) {
                    DeviceListActivity.this.iv_act_new_main.setImageBitmap(null);
                    UserFerences.getUserFerences(DeviceListActivity.this.getApplicationContext()).putValue("/ltech/led/mainTitle", "");
                    DeviceListActivity.this.iv_act_new_main.setBackgroundResource(R.mipmap.device_bg);
                }
            }
        });*/
    }

    public void setting(View paramView) {
        startActivityForResult(new Intent(this, ActSetting.class), 0);
        this.isGoSetting = true;
    }
}
