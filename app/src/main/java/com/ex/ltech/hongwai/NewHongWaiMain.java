package com.ex.ltech.hongwai;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.lamp.AtLamp;
import com.ex.ltech.hongwai.scene.AtSceneActivity;
import com.ex.ltech.hongwai.time.act.ActTiming;
import com.ex.ltech.hongwai.vo.IrSetting;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.hongwai.yaokong.AtYaokongActivity;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.my_view.MyAlertDialog;
import com.ex.ltech.led.my_view.MyAlertDialog14;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.updataHardWareProgram.SynProgram2Device;
import com.hzy.tvmao.KookongSDK;

import java.util.List;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;

public class NewHongWaiMain extends TabActivity
  implements XlinkNetListener
{
  public static boolean IS_NEW_IR_SORFT_WARE = false;
  public static final String KU_KONG_APP_KEY = "075160883AF24B2E116A97180F75E216";
  public static DeviceVo deviceVo = new DeviceVo();
  public static int sonActHeightWithouTitle;
  public static int tabIndex;
  private final String LIGHT_DEL_OK_REPS_CODE = "C1";
  private final int M_MCU_VERSION = -2;
  private final String PANEL_DEL_OK_REPS_CODE = "D6";
  MyBusiness business;
  private ProgressDialog dialog;
  private int[] drawableArray = { 2130903653, 2130903655, 2130903657, 2130903659 };
  private int[] drawableSelectedArray = { 2130903652, 2130903654, 2130903656, 2130903658 };
  private LayoutInflater inflater;
  boolean isUpdataIng = false;
  private String lastRecCmd = "";
  private MyRcDevices rcDevices;
  byte[] resend;
  private RelativeLayout rl_load;
  SynProgram2Device synProgram2Device;
  private TabHost tabHost;
  private TabHost.OnTabChangeListener tab_listener = new TabHost.OnTabChangeListener()
  {
    public void onTabChanged(String paramString)
    {
      if (paramString.equals("yaokong"));
      boolean bool = paramString.equals("scene");
      int i = 0;
      if (bool)
        i = 1;
      if (paramString.equals("lamp"))
        i = 2;
      if (paramString.equals("time"))
        i = 3;
      NewHongWaiMain.this.changeTabItemBG(i);
    }
  };
  private int tempSonActHeightWithouTitle;
  private TextView tvPercent;
  private TabWidget widget;

  static
  {
    IS_NEW_IR_SORFT_WARE = false;
  }

  private void changeTabItemBG(int paramInt)
  {
    tabIndex = paramInt;
    this.widget = getTabHost().getTabWidget();
    int i = this.widget.getChildCount();
    int j = 0;
    if (j < i)
    {
      ImageView localImageView = (ImageView)this.widget.getChildAt(j).findViewById(2131558803);
      if (paramInt == j)
        localImageView.setImageResource(this.drawableSelectedArray[j]);
      while (true)
      {
        j++;
        break;
        localImageView.setImageResource(this.drawableArray[j]);
      }
    }
  }

  private void findView()
  {
    this.rl_load = ((RelativeLayout)findViewById(2131558800));
    this.tvPercent = ((TextView)findViewById(2131558801));
    this.tabHost = getTabHost();
    this.tabHost.setOnTabChangedListener(this.tab_listener);
    this.inflater = LayoutInflater.from(this);
    View localView1 = setMenuView(R.mipmap.mode_1);
    View localView2 = setMenuView(R.mipmap.mode_2);
    View localView3 = setMenuView(R.mipmap.mode_3);
    View localView4 = setMenuView(R.mipmap.mode_4);
    this.tabHost.addTab(this.tabHost.newTabSpec("yaokong").setContent(new Intent(this, AtYaokongActivity.class)).setIndicator(localView1));
    this.tabHost.addTab(this.tabHost.newTabSpec("scene").setContent(new Intent(this, AtSceneActivity.class)).setIndicator(localView2));
    this.tabHost.addTab(this.tabHost.newTabSpec("lamp").setContent(new Intent(this, AtLamp.class)).setIndicator(localView3));
    this.tabHost.addTab(this.tabHost.newTabSpec("time").setContent(new Intent(this, ActTiming.class)).setIndicator(localView4));
  }

  private View setMenuView(int paramInt)
  {
    View localView = this.inflater.inflate(2130968650, null);
    ((ImageView)localView.findViewById(2131558803)).setImageResource(paramInt);
    return localView;
  }

  private void updataDialog(String paramString)
  {
    MyAlertDialog14 localMyAlertDialog14 = new MyAlertDialog14(this);
    localMyAlertDialog14.show();
    localMyAlertDialog14.setCancelable(false);
    localMyAlertDialog14.setMsg(getString(R.string.version_code) + paramString);
    localMyAlertDialog14.setMyOnClickListener(new MyAlertDialog.MyOnClickListener()
    {
      public void onClick(View paramView, boolean paramBoolean)
      {
        if (paramBoolean)
        {
          NewHongWaiMain.this.isUpdataIng = true;
          NewHongWaiMain.this.rl_load.setVisibility(View.VISIBLE);
          NewHongWaiMain localNewHongWaiMain1 = NewHongWaiMain.this;
          NewHongWaiMain localNewHongWaiMain2 = NewHongWaiMain.this;
          DeviceManage.getInstance();
          localNewHongWaiMain1.synProgram2Device = new SynProgram2Device(localNewHongWaiMain2, DeviceManage.getxDevice(), SynProgram2Device.IR_FILE_NAME);
          NewHongWaiMain.this.synProgram2Device.setListener(new SynProgram2Device.SynListener()
          {
            public void failed()
            {
              NewHongWaiMain.this.isUpdataIng = false;
              NewHongWaiMain.this.rl_load.setVisibility(View.GONE);
              NewHongWaiMain.this.findViewById(2131558798).setVisibility(View.VISIBLE);
              NewHongWaiMain.this.findViewById(2131558799).setOnClickListener(new View.OnClickListener()
              {
                public void onClick(View paramView)
                {
                  NewHongWaiMain.this.findViewById(2131558798).setVisibility(View.GONE);
                  NewHongWaiMain.this.finish();
                }
              });
              NewHongWaiMain.this.synProgram2Device.close();
            }

            public void ok()
            {
              NewHongWaiMain.this.isUpdataIng = false;
              NewHongWaiMain.this.rl_load.setVisibility(View.GONE);
              NewHongWaiMain.this.findViewById(2131558802).setVisibility(View.VISIBLE);
              new Handler()
              {
              }
              .postDelayed(new Runnable()
              {
                public void run()
                {
                  NewHongWaiMain.this.findViewById(2131558802).setVisibility(View.GONE);
                }
              }
              , 1000L);
              NewHongWaiMain.this.synProgram2Device.close();
            }

            public void onPercent(int paramInt)
            {
              NewHongWaiMain.this.tvPercent.setText("" + paramInt + "%");
            }
          });
          NewHongWaiMain.this.synProgram2Device.syn();
        }
      }
    });
  }

  public int dip2px(int paramInt)
  {
    return (int)(0.5F + getResources().getDisplayMetrics().density * paramInt);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (this.isUpdataIng)
      return true;
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    int i = 1;
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    int j;
    if (paramInt2 == SynProgram2Device.UPDATA_CANCEL)
    {
      j = i;
      if (paramInt2 != SynProgram2Device.UPDATA_OK)
        break label47;
    }
    while (true)
    {
      if ((j | i) != 0)
        finish();
      return;
      j = 0;
      break;
      label47: i = 0;
    }
  }

  public void onBackPressed()
  {
    if (!this.isUpdataIng);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968862);
    findView();
    changeTabItemBG(0);
    KookongSDK.init(this, "075160883AF24B2E116A97180F75E216", DeviceListActivity.deviceMacAddress);
    this.business = new MyBusiness(this);
    this.business.setMySendListener(new MyBusiness.MySendListener()
    {
      public void onFail()
      {
      }

      public void onOk(byte[] paramArrayOfByte)
      {
        String str1 = StringUtils.btye2Str(paramArrayOfByte);
        if ((str1.length() < 78) || (!str1.substring(74, 76).equalsIgnoreCase("BD")));
        do
        {
          return;
          NewHongWaiMain.this.business.setMySendListener(null);
          StringUtils.bytesStr2WordStr(str1.substring(14, 74));
          StringUtils.bytesStr2WordStr(str1.substring(14, 44));
          String str2 = StringUtils.bytesStr2WordStr(str1.substring(44, 74));
          IrSetting localIrSetting = (IrSetting)MyDb.getInstance(NewHongWaiMain.this).getBean(DeviceListActivity.deviceMacAddress, IrSetting.class);
          if (localIrSetting == null)
            localIrSetting = new IrSetting();
          localIrSetting.setVersion(str2);
          MyDb.getInstance(NewHongWaiMain.this).putBean(DeviceListActivity.deviceMacAddress, localIrSetting);
          String str3 = str2.replace(".", "");
          String str4 = str3.substring(4, str3.length()).replaceFirst("^0*", "");
          try
          {
            if (-2 <= Integer.parseInt(str4))
              continue;
            NewHongWaiMain.this.updataDialog(StringUtils.bytesStr2WordStr(str1.substring(44, 74)));
            return;
          }
          catch (NumberFormatException localNumberFormatException)
          {
            localNumberFormatException.printStackTrace();
            return;
          }
        }
        while (!DeviceListActivity.devicePid.equals("160fa2b2db6403e9160fa2b2db646801"));
        XlinkAgent.getInstance().addXlinkListener(NewHongWaiMain.this);
        SocketManager.instance().sendData(new CmdDateBussiness(NewHongWaiMain.this, "0000").queryIr$Devices());
        SocketManager.instance().sendData(new CmdDateBussiness(NewHongWaiMain.this, "0000").queryIr$Devices());
        SocketManager.instance().sendData(new CmdDateBussiness(NewHongWaiMain.this, "0000").queryIr$Devices());
        NewHongWaiMain.access$102(NewHongWaiMain.this, new ProgressDialog(NewHongWaiMain.this));
        NewHongWaiMain.this.dialog.setMessage(NewHongWaiMain.this.getString(R.string.get_d_info));
        NewHongWaiMain.this.dialog.show();
      }

      public void onTimeOut()
      {
      }
    });
    new Handler()
    {
    }
    .postDelayed(new Runnable()
    {
      public void run()
      {
        MyBusiness localMyBusiness = NewHongWaiMain.this.business;
        NewHongWaiMain localNewHongWaiMain = NewHongWaiMain.this;
        byte[] arrayOfByte = new CmdDateBussiness(NewHongWaiMain.this, "0000").irVersion();
        localNewHongWaiMain.resend = arrayOfByte;
        localMyBusiness.sendCmd(arrayOfByte);
      }
    }
    , 200L);
  }

  public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
  {
  }

  protected void onDestroy()
  {
    super.onDestroy();
    if (this.synProgram2Device != null)
      this.synProgram2Device.close();
    XlinkAgent.getInstance().removeListener(this);
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

  protected void onPause()
  {
    super.onPause();
  }

  public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
  {
    String str = StringUtils.btye2Str(paramArrayOfByte);
    System.out.println("onRecvPipeData      rec" + str);
    if ((paramXDevice.getMacAddress() == null) || (paramXDevice.getMacAddress().length() == 0))
      return;
    int i;
    if (!DeviceListActivity.deviceMacAddress.equals(paramXDevice.getMacAddress()))
    {
      i = 1;
      label67: if ((i | this.lastRecCmd.equals(str)) != 0)
        break label147;
      if ((str.length() != 18) || (!(str.substring(14, 16).equals("D6") | str.substring(14, 16).equals("C1"))))
        break label149;
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          Activity localActivity = NewHongWaiMain.this.getLocalActivityManager().getActivity(NewHongWaiMain.this.tabHost.getCurrentTabTag());
          if ((localActivity != null) && ((localActivity instanceof AtYaokongActivity)));
          try
          {
            ((AtYaokongActivity)localActivity).onDelDeviceOk();
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
    }
    while (true)
    {
      this.lastRecCmd = str;
      return;
      i = 0;
      break label67;
      label147: break;
      label149: if ((str.length() >= 18) && (str.indexOf("C6EB") != -1))
      {
        runOnUiThread(new Runnable(Integer.parseInt(str.substring(12, 13), 16), Integer.parseInt(str.substring(13, 14), 16))
        {
          public void run()
          {
            Activity localActivity = NewHongWaiMain.this.getLocalActivityManager().getActivity(NewHongWaiMain.this.tabHost.getCurrentTabTag());
            if ((localActivity != null) && ((localActivity instanceof AtYaokongActivity)));
            try
            {
              ((AtYaokongActivity)localActivity).updataDeviceOnlineStatus(this.val$dId, this.val$dStatus);
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        });
        continue;
      }
      if ((str.length() < 208) || (str.indexOf("C5EB") == -1))
        continue;
      new Thread(str)
      {
        public void run()
        {
          Activity localActivity = NewHongWaiMain.this.getLocalActivityManager().getActivity(NewHongWaiMain.this.tabHost.getCurrentTabTag());
          if ((localActivity != null) && ((localActivity instanceof AtYaokongActivity)));
          int i;
          label814: label1023: int i2;
          label1025: label1031: int i3;
          label1048: 
          do
          {
            while (true)
            {
              String str4;
              MyRcDevice localMyRcDevice;
              int i1;
              try
              {
                NewHongWaiMain.access$602(NewHongWaiMain.this, ((AtYaokongActivity)localActivity).getRcDevices());
                String str1 = this.val$s.substring(12, -4 + this.val$s.length());
                i = 0;
                if (i >= 24)
                  break label1258;
                String str2 = str1.substring(0, 8);
                str1 = str1.substring(8, str1.length());
                String str3 = str2.substring(0, 1);
                if (!(str3.equalsIgnoreCase("e") | str3.equalsIgnoreCase("c")))
                  continue;
                boolean bool1 = str3.equalsIgnoreCase("f") | str3.equalsIgnoreCase("d");
                int j = 0;
                if (!bool1)
                  continue;
                j = 1;
                int k = Integer.parseInt(str2.substring(2, 4), 16);
                str4 = str2.substring(5, 6);
                m = -1;
                switch (str4.hashCode())
                {
                default:
                  bool2 = false;
                  bool3 = false;
                  switch (m)
                  {
                  default:
                    Integer.parseInt(str2.substring(6, 8), 16);
                    if (j == 0)
                      break label1031;
                    localMyRcDevice = new MyRcDevice();
                    localMyRcDevice.nonIrDevice = new NonIrDevice();
                    switch (k)
                    {
                    case 2:
                    default:
                      if (i <= 11)
                        break label814;
                      localMyRcDevice.nonIrDevice.nonIrDeviceId = (i - 12);
                      System.out.println("mName = " + localMyRcDevice.mName + "   " + bool3);
                      n = 0;
                      i1 = 0;
                      if (i1 >= NewHongWaiMain.this.rcDevices.myRcDevices.size())
                        continue;
                      if ((((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice == null) || (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.mType != localMyRcDevice.nonIrDevice.mType))
                        continue;
                      switch (localMyRcDevice.nonIrDevice.mType)
                      {
                      default:
                        if (n == 0)
                          break label1025;
                        if (n != 0)
                          continue;
                        NewHongWaiMain.this.rcDevices.myRcDevices.add(localMyRcDevice);
                        i++;
                        continue;
                      case 10:
                      case 11:
                      case 12:
                      }
                    case 0:
                    case 1:
                    case 3:
                    }
                  case 0:
                  case 1:
                  case 2:
                  }
                case 48:
                case 49:
                case 50:
                }
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
                continue;
              }
              NewHongWaiMain.access$602(NewHongWaiMain.this, (MyRcDevices)MyDb.getInstance(NewHongWaiMain.this.getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
              if (NewHongWaiMain.this.rcDevices != null)
                continue;
              NewHongWaiMain.access$602(NewHongWaiMain.this, new MyRcDevices());
              continue;
              if (!str4.equals("0"))
                continue;
              int m = 0;
              continue;
              if (!str4.equals("1"))
                continue;
              m = 1;
              continue;
              if (!str4.equals("2"))
                continue;
              m = 2;
              continue;
              boolean bool2 = true;
              boolean bool3 = false;
              continue;
              bool3 = true;
              bool2 = true;
              continue;
              bool2 = false;
              bool3 = false;
              continue;
              localMyRcDevice.mType = 10;
              localMyRcDevice.nonIrDevice.mType = 10;
              localMyRcDevice.isShowListInfo = true;
              localMyRcDevice.mName = NewHongWaiMain.this.getString(2131100423);
              continue;
              localMyRcDevice.mType = 11;
              localMyRcDevice.nonIrDevice.mType = 11;
              localMyRcDevice.isShowListInfo = true;
              localMyRcDevice.mName = NewHongWaiMain.this.getString(2131100424);
              continue;
              localMyRcDevice.mType = 12;
              localMyRcDevice.nonIrDevice.mType = 12;
              localMyRcDevice.isShowListInfo = false;
              localMyRcDevice.mName = NewHongWaiMain.this.getString(2131100037);
              localMyRcDevice.nonIrDevice.irCt1Onoff = bool3;
              localMyRcDevice.nonIrDevice.irCt1Online = bool2;
              continue;
              localMyRcDevice.nonIrDevice.nonIrDeviceId = i;
              continue;
              if (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.nonIrDeviceId == localMyRcDevice.nonIrDevice.nonIrDeviceId);
              for (int n = 1; ; n = 0)
                break;
              if (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.nonIrDeviceId == localMyRcDevice.nonIrDevice.nonIrDeviceId);
              for (n = 1; ; n = 0)
                break;
              if (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.nonIrDeviceId == localMyRcDevice.nonIrDevice.nonIrDeviceId);
              for (n = 1; ; n = 0)
              {
                if (n == 0)
                  break label1023;
                ((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.irCt1Onoff = bool3;
                ((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i1)).nonIrDevice.irCt1Online = bool2;
                break;
              }
              continue;
              i1++;
            }
            if (i <= 11)
              break;
            i2 = i - 12;
            i3 = 0;
          }
          while (i3 >= NewHongWaiMain.this.rcDevices.myRcDevices.size());
          if (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i3)).nonIrDevice != null)
            switch (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i3)).nonIrDevice.mType)
            {
            default:
              if ((i <= 11) || (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i3)).nonIrDevice.nonIrDeviceId != i2))
                break;
              NewHongWaiMain.this.rcDevices.myRcDevices.remove(i3);
              i3--;
            case 12:
            }
          while (true)
          {
            i3++;
            break label1048;
            i2 = i;
            break;
            if ((i >= 11) || (((MyRcDevice)NewHongWaiMain.this.rcDevices.myRcDevices.get(i3)).nonIrDevice.nonIrDeviceId != i2))
              continue;
            NewHongWaiMain.this.rcDevices.myRcDevices.remove(i3);
            i3--;
          }
          label1258: MyDb.getInstance(NewHongWaiMain.this.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, NewHongWaiMain.this.rcDevices);
          NewHongWaiMain localNewHongWaiMain = NewHongWaiMain.this;
          1 local1 = new Runnable()
          {
            public void run()
            {
              if ((NewHongWaiMain.this.dialog != null) && (NewHongWaiMain.this.dialog.isShowing()))
                NewHongWaiMain.this.dialog.dismiss();
              Activity localActivity = NewHongWaiMain.this.getLocalActivityManager().getActivity(NewHongWaiMain.this.tabHost.getCurrentTabTag());
              if ((localActivity != null) && ((localActivity instanceof AtYaokongActivity)));
              try
              {
                ((AtYaokongActivity)localActivity).updataDeviceList();
                return;
              }
              catch (Exception localException)
              {
                localException.printStackTrace();
              }
            }
          };
          localNewHongWaiMain.runOnUiThread(local1);
        }
      }
      .start();
    }
  }

  public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
  {
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onStart(int paramInt)
  {
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.tempSonActHeightWithouTitle = (this.tabHost.getMeasuredHeight() - this.widget.getMeasuredHeight() - dip2px(100));
    if (UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1) == -1)
    {
      sonActHeightWithouTitle = this.tabHost.getHeight() - this.widget.getHeight() - dip2px(100);
      UserFerences.getUserFerences(this).putValue("sonActHeightWithouTitle ", Integer.valueOf(sonActHeightWithouTitle));
    }
    while (true)
    {
      sonActHeightWithouTitle = this.tempSonActHeightWithouTitle;
      return;
      sonActHeightWithouTitle = UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.NewHongWaiMain
 * JD-Core Version:    0.6.0
 */