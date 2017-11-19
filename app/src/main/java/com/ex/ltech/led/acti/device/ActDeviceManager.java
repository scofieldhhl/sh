package com.ex.ltech.led.acti.device;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.vo.DeviceVo;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;



public class ActDeviceManager extends MyBaseActivity {
    int SHOT_REQ_CODE = 1;
    Adt adt;
    String bitmapPath;
    private CmdDateBussiness cmdDateBussiness;
    String curMacAddress;
    File currentFile;
//    private ArrayList<Device> devices = new ArrayList();
    private boolean isShare;
    private ListView lv_acti_device_manager;
    private Pattern pattern = Pattern.compile("^([a-z]|[A-Z]|[0-9]|[⺀-鿿]){3,}|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$");
    private final String reg = "^([a-z]|[A-Z]|[0-9]|[⺀-鿿]){3,}|@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?|[wap.]{4}|[www.]{4}|[blog.]{5}|[bbs.]{4}|[.com]{4}|[.cn]{3}|[.net]{4}|[.org]{4}|[http://]{7}|[ftp://]{6}$";
    private DeviceVo renameVo;
    private SocketManager socketManager;
    private List<DeviceVo> vos;

    private void beginCrop(Uri paramUri) {
        Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
    }

    private void handleCrop(int paramInt, Intent paramIntent) {
        /*if (paramInt == -1) {
            Bitmap localBitmap = BitmapUtils.autoZoomInBM(BitmapUtils.getBitmapFromUri(this, Crop.getOutput(paramIntent)), 300.0D, 300.0D);
            UriUtil.getRealFilePath(this, paramIntent.getData());
            String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
            FileUtil.saveMyBitmap(str, localBitmap, "/ltech/led/image");
            UserFerences.getUserFerences(this).putValue(this.curMacAddress + "devicePicPath", str);
        }*/
    }

    private void init() {
        this.isShare = getIntent().getBooleanExtra("isShare", false);
        ArrayList localArrayList;
        int i;
        /*if ((this.vos == null) || (this.isShare)) {
            localArrayList = new ArrayList();
            localArrayList.addAll(DeviceManage.getInstance().getDevices());
            this.devices.clear();
            i = 0;
        }
        while (i < localArrayList.size()) {
            if ((((Device) localArrayList.get(i)).getXDevice().getProductId().equals("5b05f623bdcf48d9b0fc1507a79bb847") | ((Device) localArrayList.get(i)).getXDevice().getProductId().equals("160fa2afd1a7f600160fa2afd1a7f601") | ((Device) localArrayList.get(i)).getXDevice().getProductId().equals("160fa2b2db6403e9160fa2b2db646801")))
                this.devices.add(localArrayList.get(i));
            i++;
            continue;
            this.devices.addAll(DeviceManage.getInstance().getDevices());
        }
        this.adt = new Adt(this, this.devices);
        this.lv_acti_device_manager.setAdapter(this.adt);*/
    }

    private void setMyTitle() {
        setViewTitle();
        setMenuBackgroundRes(R.mipmap.back_ic);
        if (this.isShare) {
            setTiTleTextRes(R.string.share_data);
            return;
        }
        setTiTleTextRes(R.string.setting);
    }

    public static String stringFilter(String paramString)
            throws PatternSyntaxException {
        return Pattern.compile("[^a-zA-Z0-9一-龥]").matcher(paramString).replaceAll("").trim();
    }

    public void goCarmare() {
        File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
        if (!localFile.exists())
            localFile.mkdirs();
        this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
        this.bitmapPath = this.currentFile.getPath();
        Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        localIntent.putExtra("output", Uri.fromFile(this.currentFile));
        startActivityForResult(localIntent, this.SHOT_REQ_CODE);
    }

    protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {
        super.onActivityResult(paramInt1, paramInt2, paramIntent);
        /*if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE)) {
            new Thread() {
                public void run() {
                    super.run();
                    Bitmap localBitmap = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(ActDeviceManager.this.bitmapPath), 200.0D, 200.0D);
                    int i = BitmapUtils.getExifOrientation(ActDeviceManager.this.bitmapPath);
                    if ((i == 90) || (i == 180) || (i == 270)) {
                        Matrix localMatrix = new Matrix();
                        localMatrix.postRotate(i);
                        localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, 200, 200, localMatrix, true);
                    }
                    String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
                    FileUtil.saveMyBitmap(str, localBitmap, "/ltech/led/image");
                    UserFerences.getUserFerences(ActDeviceManager.this).putValue(ActDeviceManager.this.curMacAddress + "devicePicPath", str);
                    ActDeviceManager.this.runOnUiThread(new Runnable() {
                        public void run() {
                            ActDeviceManager.this.adt.notifyDataSetChanged();
                        }
                    });
                }
            }
                    .start();
            return;
        }*/
        /*if ((paramInt1 == 9162) && (paramInt2 == -1))
            beginCrop(paramIntent.getData());
        while (true) {
            this.adt.notifyDataSetChanged();
            return;
            if (paramInt1 != 6709)
                continue;
            handleCrop(paramInt2, paramIntent);
        }*/
    }

    protected void onCreate(Bundle paramBundle) {
        super.onCreate(paramBundle);
        setContentView(R.layout.acti_device_manager);
        this.lv_acti_device_manager = ((ListView) findViewById(R.id.lv_acti_device_manager));
        init();
        setMyTitle();
    }

    protected void onMenu() {
        super.onMenu();
        finish();
    }

    class Adt extends BaseAdapter {
//        public List<Device> devices;
        private Activity pct;

        /*public Adt(Activity activity, ArrayList<Device> arg2) {
            this.pct = activity;
            this.devices = arg2;
        }

        public int getCount() {
            return this.devices.size();
        }

        public Object getItem(int paramInt) {
            return this.devices.get(paramInt);
        }*/

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int paramInt) {
            return 0L;
        }

        public View getView(int paramInt, View paramView, ViewGroup paramViewGroup) {
            Holder localHolder = null;
            /*Device localDevice = null;
            String str1;
            String str2;
            if (paramView == null) {
                localHolder = new Holder();
                ActDeviceManager.this.getLayoutInflater();
                paramView = LayoutInflater.from(ActDeviceManager.this).inflate(R.layout.item_act_device_manager, null);
                localHolder.iv_act_device_rename = ((MLImageView) paramView.findViewById(R.id.iv_act_device_rename));
                localHolder.tv_act_device_name = ((TextView) paramView.findViewById(R.id.tv_act_device_name));
                localHolder.tv_act_device_del = ((TextView) paramView.findViewById(R.id.tv_act_device_del));
                localHolder.tv_act_device_rename = ((TextView) paramView.findViewById(R.id.tv_act_device_rename));
                localHolder.tv_act_device_share = ((TextView) paramView.findViewById(R.id.tv_act_device_share));
                paramView.setTag(localHolder);
                localDevice = (Device) this.devices.get(paramInt);
                if (!ActDeviceManager.this.isShare)
                    break label793;
                localHolder.tv_act_device_del.setVisibility(View.GONE);
                localHolder.tv_act_device_rename.setVisibility(View.GONE);
                localHolder.tv_act_device_share.setVisibility(View.VISIBLE);
                localHolder.tv_act_device_share.setOnClickListener(new View.OnClickListener(localDevice) {
                    public void onClick(View paramView) {
                        DeviceManage.getInstance().updateDevice(this.val$vo.getXDevice());
                        ActDeviceManager.this.goAct(AtShare.class);
                    }
                });
                str1 = UserFerences.getUserFerences(ActDeviceManager.this.getApplicationContext()).spFerences.getString(localDevice.getMacAddress() + "devicePicPath", "");
                str2 = "";
                if (localDevice.getXDevice().getProductId().equals("160fa2afd1a7f600160fa2afd1a7f601")) {
                    IrSetting localIrSetting = (IrSetting) MyDb.getInstance(ActDeviceManager.this).getBean(localDevice.getMacAddress(), IrSetting.class);
                    if (localIrSetting == null)
                        localIrSetting = new IrSetting();
                    str2 = "\t\t" + localIrSetting.getVersion();
                }
                if (str1.length() <= 0)
                    break label834;
            }
            while (true) {
                try {
                    Bitmap localBitmap = BitmapFactory.decodeFile(str1);
                    localHolder.iv_act_device_rename.setImageBitmap(localBitmap);
                    String str3 = UserFerences.getUserFerences(this.pct).spFerences.getString("dName" + localDevice.getMacAddress(), "");
                    TextView localTextView = localHolder.tv_act_device_name;
                    StringBuilder localStringBuilder = new StringBuilder();
                    if (str3.length() <= 0)
                        break label1125;
                    str4 = str3 + "\nMacId:" + localDevice.getMacAddress();
                    localTextView.setText(str4 + str2);
                    if (ActDeviceManager.this.isZh())
                        continue;
                    if (!str3.equals("幻彩灯带"))
                        continue;
                    localHolder.tv_act_device_name.setText(ActDeviceManager.this.getString(R.string.d_name_1) + "\nMacId:" + localDevice.getMacAddress());
                    if (!str3.equals("万能遥控器"))
                        continue;
                    localHolder.tv_act_device_name.setText(ActDeviceManager.this.getString(R.string.d_name_2) + "\nMacId:" + localDevice.getMacAddress());
                    if (!str3.equals("智能插座"))
                        continue;
                    localHolder.tv_act_device_name.setText(ActDeviceManager.this.getString(R.string.d_name_3) + "\nMacId:" + localDevice.getMacAddress());
                    if (!str3.equals("色温灯带"))
                        continue;
                    localHolder.tv_act_device_name.setText(ActDeviceManager.this.getString(R.string.d_name_4) + "\nMacId:" + localDevice.getMacAddress());
                    if ((!(localDevice.getXDevice().getProductId().equals("fc02a10aec1c46b8922630f6acd15ed6") | localDevice.getXDevice().getProductId().equals("160fa2b3051a03e9160fa2b3051ac601"))) || (str3.indexOf("调光设备") == -1))
                        continue;
                    localHolder.tv_act_device_name.setText(R.string.d_name_4);
                    localHolder.tv_act_device_del.setOnClickListener(new View.OnClickListener(paramInt) {
                        public void onClick(View paramView) {
                            ActDeviceManager.this.vos.remove(this.val$i);
                            ActDeviceManager.Adt.this.notifyDataSetChanged();
                        }
                    });
                    localHolder.tv_act_device_del.setOnClickListener(new View.OnClickListener(localDevice) {
                        public void onClick(View paramView) {
                            ActDeviceManager.this.curMacAddress = this.val$vo.getMacAddress();
                            MyAlertDialog2 localMyAlertDialog2 = new MyAlertDialog2(ActDeviceManager.this);
                            localMyAlertDialog2.show();
                            localMyAlertDialog2.setMyOnClickListener(new MyAlertDialog2.MyOnClickListener() {
                                public void onClick(int paramInt) {
                                    if (paramInt == 1)
                                        ActDeviceManager.this.goCarmare();
                                    if (paramInt == 2)
                                        Crop.pickImage(ActDeviceManager.this);
                                    if (paramInt == 3) {
                                        UserFerences.getUserFerences(ActDeviceManager.this).putValue(ActDeviceManager.this.curMacAddress + "devicePicPath", "");
                                        ActDeviceManager.this.adt.notifyDataSetChanged();
                                    }
                                }
                            });
                        }
                    });
                    localHolder.tv_act_device_rename.setOnClickListener(new View.OnClickListener(str3, localDevice) {
                        public void onClick(View paramView) {
                            final MyEditAlertDialog localMyEditAlertDialog = new MyEditAlertDialog(ActDeviceManager.this);
                            localMyEditAlertDialog.show();
                            EditText localEditText = localMyEditAlertDialog.et_my_edit_alertdialog;
                            if (this.val$name.length() > 0) ;
                            for (String str = this.val$name; ; str = this.val$vo.getMacAddress()) {
                                localEditText.setText(str);
                                localMyEditAlertDialog.getWindow().clearFlags(131080);
                                localMyEditAlertDialog.getWindow().setSoftInputMode(4);
                                localMyEditAlertDialog.rv_my_edit_alertdialog_ok.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View paramView) {
                                        String str = localMyEditAlertDialog.et_my_edit_alertdialog.getText().toString().trim();
                                        ActDeviceManager.stringFilter(str);
                                        if (str.getBytes().length > 24)
                                            ActDeviceManager.this.toast(R.string.rename_count);
                                        do
                                            return;
                                        while (str.length() <= 0);
                                        XlinkAgent.getInstance().connectDevice(ActDeviceManager.Adt
                                        .4.
                                        this.val$vo.getXDevice(), new ConnectDeviceListener(str) {
                                            public void onConnectDevice(XDevice paramXDevice, int paramInt) {
                                                if ((paramInt == 1) || (paramInt == 0)) {
                                                    DeviceManage.getInstance().updateDevice(paramXDevice);
                                                    String str = paramXDevice.getMacAddress();
                                                    if ((str == null) || (str.length() == 0))
                                                        return;
                                                    com.ex.ltech.led.acti.main.DeviceListActivity.deviceMacAddress = str;
                                                    CmdDateBussiness localCmdDateBussiness = new CmdDateBussiness(ActDeviceManager.this, "0000");
                                                    if (ActDeviceManager.Adt .4.
                                                    this.val$vo.getXDevice().getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01"))
                                                    {
                                                        byte[] arrayOfByte = this.val$newName.getBytes();
                                                        StringUtils.btye2Str(arrayOfByte);
                                                        ArrayList localArrayList = new ArrayList();
                                                        MyBusiness localMyBusiness = new MyBusiness(ActDeviceManager.this.getApplicationContext());
                                                        localMyBusiness.addNormalHeadData2(localArrayList);
                                                        localArrayList.add(Integer.valueOf(24));
                                                        localArrayList.add(Integer.valueOf(1 + arrayOfByte.length));
                                                        localArrayList.add(Integer.valueOf(1));
                                                        for (int i = 0; i < arrayOfByte.length; i++)
                                                            localArrayList.add(Integer.valueOf(arrayOfByte[i]));
                                                        localArrayList.add(Integer.valueOf(1));
                                                        localMyBusiness.addCheckSumData(localArrayList);
                                                        localArrayList.add(Integer.valueOf(22));
                                                        localMyBusiness.sendCmd(localArrayList, paramXDevice);
                                                        localMyBusiness.setMySendListener(new MyBusiness.MySendListener(localMyBusiness) {
                                                            public void onFail() {
                                                                ActDeviceManager.this.toast(R.string.rename_no_ok);
                                                            }

                                                            public void onOk(byte[] paramArrayOfByte) {
                                                                if (StringUtils.btye2Str(paramArrayOfByte).length() == 32) {
                                                                    this.val$myBusiness.setMySendListener(null);
                                                                    ActDeviceManager.Adt .4 .1.
                                                                    localMyEditAlertDialog.dismiss();
                                                                    ActDeviceManager.this.toast(R.string.rename_ok);
                                                                    UserFerences.getUserFerences(ActDeviceManager.Adt.this.pct).putValue("dName" + ActDeviceManager.Adt
                                                                    .4.
                                                                    this.val$vo.getMacAddress(), ActDeviceManager.Adt
                                                                    .4 .1 .1. this.val$newName);
                                                                }
                                                            }

                                                            public void onTimeOut() {
                                                                ActDeviceManager.this.toast(R.string.cfging_time_out);
                                                            }
                                                        });
                                                        return;
                                                    }
                                                    localCmdDateBussiness.getSaveDeviceNameCmd(this.val$newName);
                                                    XlinkAgent.getInstance().sendPipeData(ActDeviceManager.Adt
                                                    .4.
                                                    this.val$vo.getXDevice(), localCmdDateBussiness.getSaveDeviceNameCmd(this.val$newName), new SendPipeListener() {
                                                        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2) {
                                                            System.out.println("TEST      XPGWifiDevice.write    =" + paramInt1);
                                                            if (paramInt1 == 0) {
                                                                ActDeviceManager.this.toast(R.string.rename_ok);
                                                                UserFerences.getUserFerences(ActDeviceManager.Adt.this.pct).putValue("dName" + ActDeviceManager.Adt
                                                                .4.
                                                                this.val$vo.getMacAddress(), ActDeviceManager.Adt
                                                                .4 .1 .1. this.val$newName);
                                                            }
                                                            while (true) {
                                                                ActDeviceManager.Adt .4 .1.
                                                                localMyEditAlertDialog.dismiss();
                                                                return;
                                                                ActDeviceManager.this.toast(R.string.rename_no_ok);
                                                            }
                                                        }
                                                    });
                                                    return;
                                                }
                                                Toast.makeText(ActDeviceManager.this.getApplicationContext(), ActDeviceManager.this.getString(R.string.load_fail), 0).show();
                                            }
                                        });
                                    }
                                });
                                localMyEditAlertDialog.rv_my_edit_alertdialog_cancle.setOnClickListener(new View.OnClickListener() {
                                    public void onClick(View paramView) {
                                        localMyEditAlertDialog.dismiss();
                                    }
                                });
                                return;
                            }
                        }
                    });
                    return paramView;
                    localHolder = (Holder) paramView.getTag();
                    break;
                    label793:
                    localHolder.tv_act_device_del.setVisibility(View.VISIBLE);
                    localHolder.tv_act_device_rename.setVisibility(View.VISIBLE);
                    localHolder.tv_act_device_share.setVisibility(View.GONE);
                } catch (Exception localException) {
                    localException.printStackTrace();
                    continue;
                }
                label834:
                if (localDevice.getXDevice().getProductId().equals("3864ebbb24cf4cab9d3ce823a0cfe93f"))
                    localHolder.iv_act_device_rename.setImageBitmap(
                            BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.rgb));
                if (localDevice.getXDevice().getProductId().equals("160fa2af1948f800160fa2af1948f801"))
                    localHolder.iv_act_device_rename.setImageBitmap(
                            BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.rgb));
                if (localDevice.getXDevice().getProductId().equals("8e28d6ebd1634ecf86161997912b895e"))
                    localHolder.iv_act_device_rename.setImageBitmap(
                            BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.device_plug));
                if ((localDevice.getXDevice().getProductId().equals("fc02a10aec1c46b8922630f6acd15ed6") | localDevice.getXDevice().getProductId().equals("160fa2b3051a03e9160fa2b3051ac601")))
                    localHolder.iv_act_device_rename.setImageBitmap(
                            BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.device_light));
                if ((localDevice.getXDevice().getProductId().equals("5b05f623bdcf48d9b0fc1507a79bb847") | localDevice.getXDevice().getProductId().equals("160fa2afd1a7f600160fa2afd1a7f601") | localDevice.getXDevice().getProductId().equals("160fa2b2db6403e9160fa2b2db646801")))
                    localHolder.iv_act_device_rename.setImageBitmap(
                            BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.d_remote));
                if (!localDevice.getXDevice().getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01"))
                    continue;
                localHolder.iv_act_device_rename.setImageBitmap(
                        BitmapFactory.decodeResource(ActDeviceManager.this.getApplicationContext().getResources(), R.mipmap.icon_105));
                continue;
                label1125:
                String str4 = localDevice.getMacAddress();*/
            return paramView;
            }

        }

        public boolean isEmpty(String paramString) {
            return (paramString == null) || (paramString == "") || (paramString.trim().equals(""));
        }

        class Holder {
            /*MLImageView iv_act_device_rename;
            TextView tv_act_device_del;
            TextView tv_act_device_name;
            TextView tv_act_device_rename;
            TextView tv_act_device_share;*/

            Holder() {
            }
        }
}
