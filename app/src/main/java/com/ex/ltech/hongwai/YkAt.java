package com.ex.ltech.hongwai;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Toast;
import com.ex.ltech.SystemBarTintManager;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MyAlertDialog3;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import et.song.db.ETDB;
import et.song.etclass.ETDevice;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETDeviceDC;
import et.song.etclass.ETDeviceDVD;
import et.song.etclass.ETDeviceFANS;
import et.song.etclass.ETDeviceIPTV;
import et.song.etclass.ETDeviceLIGHT;
import et.song.etclass.ETDevicePJT;
import et.song.etclass.ETDeviceSTB;
import et.song.etclass.ETDeviceTV;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;
import et.song.remote.instance.AIR;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class YkAt extends MyBaseActivity
{
  public int brandId = 0;
  public CmdDateBussiness cmd = new CmdDateBussiness(this, "0000");
  public int gId = 0;
  private IR mIR = null;
  String mName;
  public int saveYkOk = 1000;
  public byte[] tBytes;
  XlinkNetListener tempXlinkNetListener;
  int type;

  public static int bytesToInt(byte[] paramArrayOfByte)
  {
    return 0xFF & paramArrayOfByte[0] | 0xFF00 & paramArrayOfByte[1] << 8 | paramArrayOfByte[2] << 24 >>> 8 | paramArrayOfByte[3] << 24;
  }

  public static void initSystemBar(Activity paramActivity, int paramInt)
  {
    if (Build.VERSION.SDK_INT >= 19)
      setTranslucentStatus(paramActivity, true);
    SystemBarTintManager localSystemBarTintManager = new SystemBarTintManager(paramActivity);
    localSystemBarTintManager.setStatusBarTintEnabled(true);
    localSystemBarTintManager.setStatusBarTintColor(paramInt);
  }

  @TargetApi(19)
  private static void setTranslucentStatus(Activity paramActivity, boolean paramBoolean)
  {
    Window localWindow = paramActivity.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    if (paramBoolean);
    for (localLayoutParams.flags = (0x4000000 | localLayoutParams.flags); ; localLayoutParams.flags = (0xFBFFFFFF & localLayoutParams.flags))
    {
      localWindow.setAttributes(localLayoutParams);
      return;
    }
  }

  private byte[] subZero(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte;
    for (int i = -1 + paramArrayOfByte.length; ; i--)
    {
      arrayOfByte = null;
      if (i <= 0)
        break;
      if (paramArrayOfByte[i] == 0)
        continue;
      arrayOfByte = new byte[i + 1];
      for (int j = 0; j < i + 1; j++)
        arrayOfByte[j] = paramArrayOfByte[j];
    }
    return arrayOfByte;
  }

  public static String toHex(byte paramByte)
  {
    String str = Integer.toHexString(paramByte & 0xFF);
    if (str.length() == 1)
      str = '0' + str;
    return str;
  }

  public void addCheckSumData(List<Integer> paramList)
  {
    int i = 0;
    for (int j = 0; j < paramList.size(); j++)
      i += ((Integer)paramList.get(j)).intValue();
    String str1 = Integer.toHexString(i);
    if (str1.length() == 3)
      str1 = "0" + str1;
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = str1.substring(0, 2);
    paramList.add(Integer.valueOf(str2, 16));
    paramList.add(Integer.valueOf(str3, 16));
  }

  public void learnCmd(byte[] paramArrayOfByte)
  {
    if (DeviceListActivity.isOnePointFive)
    {
      Dvc localDvc = (Dvc)new RoomBusiness(this).getData("dvcInRoom", Dvc.class);
      localDvc.getId();
      ArrayList localArrayList = new ArrayList();
      MyBusiness localMyBusiness = new MyBusiness(this);
      localMyBusiness.addNormalHeadData(localArrayList);
      localArrayList.add(Integer.valueOf(20));
      localArrayList.add(Integer.valueOf(4));
      localArrayList.add(Integer.valueOf(97));
      localArrayList.add(Integer.valueOf(1 + localDvc.getRoomIndex()));
      localArrayList.add(Integer.valueOf(localDvc.getmIndex()));
      localArrayList.add(Integer.valueOf(2));
      localArrayList.add(Integer.valueOf(1));
      addCheckSumData(localArrayList);
      localArrayList.add(Integer.valueOf(22));
      byte[] arrayOfByte = localMyBusiness.getCmdData(localArrayList);
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
    }
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(paramArrayOfByte), new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    try
    {
      XlinkAgent.getInstance().removeListener(this.tempXlinkNetListener);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void onLearn()
  {
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onRecHongWaiCode(byte[] paramArrayOfByte)
  {
  }

  public void saveYaokong()
  {
    this.mIR = ETIR.Builder(this.type);
    ETPage.getInstance(this).Load(ETDB.getInstance(this));
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.SetType(et.song.global.ETGlobal.mGroupTypes[0]);
    localETGroup.SetRes(0);
    localETGroup.SetID(1);
    if (localETGroup.GetCount() == 0)
      localETGroup.Inster(ETDB.getInstance(this));
    switch (this.type)
    {
    default:
      return;
    case 8192:
      ETDeviceTV localETDeviceTV = new ETDeviceTV(this.brandId, this.gId);
      localETDeviceTV.SetName(this.mName);
      localETDeviceTV.SetType(8192);
      localETDeviceTV.SetRes(0);
      localETDeviceTV.SetGID(localETGroup.GetID());
      localETDeviceTV.Inster(ETDB.getInstance(this));
      return;
    case 16384:
      ETDeviceSTB localETDeviceSTB = new ETDeviceSTB(this.brandId, this.gId);
      localETDeviceSTB.SetName(this.mName);
      localETDeviceSTB.SetType(16384);
      localETDeviceSTB.SetRes(2);
      localETDeviceSTB.SetGID(localETGroup.GetID());
      localETDeviceSTB.Inster(ETDB.getInstance(this));
      return;
    case 24576:
      ETDeviceDVD localETDeviceDVD = new ETDeviceDVD(this.brandId, this.gId);
      localETDeviceDVD.SetName(this.mName);
      localETDeviceDVD.SetType(24576);
      localETDeviceDVD.SetRes(3);
      localETDeviceDVD.SetGID(localETGroup.GetID());
      localETDeviceDVD.Inster(ETDB.getInstance(this));
      return;
    case 40960:
      ETDevicePJT localETDevicePJT = new ETDevicePJT(this.brandId, this.gId);
      localETDevicePJT.SetName(this.mName);
      localETDevicePJT.SetType(40960);
      localETDevicePJT.SetRes(5);
      localETDevicePJT.SetGID(localETGroup.GetID());
      localETDevicePJT.Inster(ETDB.getInstance(this));
      return;
    case 32768:
      ETDeviceFANS localETDeviceFANS = new ETDeviceFANS(this.brandId, this.gId);
      localETDeviceFANS.SetName(this.mName);
      localETDeviceFANS.SetType(32768);
      localETDeviceFANS.SetRes(4);
      localETDeviceFANS.SetGID(localETGroup.GetID());
      localETDeviceFANS.Inster(ETDB.getInstance(this));
      return;
    case 8448:
      ETDeviceIPTV localETDeviceIPTV = new ETDeviceIPTV(this.brandId, this.gId);
      localETDeviceIPTV.SetName(this.mName);
      localETDeviceIPTV.SetType(8448);
      localETDeviceIPTV.SetRes(1);
      localETDeviceIPTV.SetGID(localETGroup.GetID());
      localETDeviceIPTV.Inster(ETDB.getInstance(this));
      return;
    case 49152:
      ETDeviceAIR localETDeviceAIR = new ETDeviceAIR(this.brandId, this.gId);
      localETDeviceAIR.SetName(this.mName);
      localETDeviceAIR.SetType(49152);
      localETDeviceAIR.SetRes(7);
      localETDeviceAIR.SetGID(localETGroup.GetID());
      ((ETDeviceAIR)localETDeviceAIR).SetTemp(((AIR)this.mIR).GetTemp());
      ((ETDeviceAIR)localETDeviceAIR).SetMode(((AIR)this.mIR).GetMode());
      ((ETDeviceAIR)localETDeviceAIR).SetPower(((AIR)this.mIR).GetPower());
      ((ETDeviceAIR)localETDeviceAIR).SetWindRate(((AIR)this.mIR).GetWindRate());
      ((ETDeviceAIR)localETDeviceAIR).SetWindDir(((AIR)this.mIR).GetWindDir());
      ((ETDeviceAIR)localETDeviceAIR).SetAutoWindDir(((AIR)this.mIR).GetAutoWindDir());
      localETDeviceAIR.Inster(ETDB.getInstance(this));
      return;
    case 57344:
      ETDeviceLIGHT localETDeviceLIGHT = new ETDeviceLIGHT(this.brandId, this.gId);
      localETDeviceLIGHT.SetName(this.mName);
      localETDeviceLIGHT.SetType(57344);
      localETDeviceLIGHT.SetRes(6);
      localETDeviceLIGHT.SetGID(localETGroup.GetID());
      localETDeviceLIGHT.Inster(ETDB.getInstance(this));
      return;
    case 8960:
    }
    ETDeviceDC localETDeviceDC = new ETDeviceDC(this.brandId, this.gId);
    localETDeviceDC.SetName(this.mName);
    localETDeviceDC.SetType(8960);
    localETDeviceDC.SetRes(8);
    localETDeviceDC.SetGID(localETGroup.GetID());
    localETDeviceDC.Inster(ETDB.getInstance(this));
  }

  public void sendCmd(byte[] paramArrayOfByte)
  {
    try
    {
      if (DeviceListActivity.isOnePointFive)
      {
        Dvc localDvc = (Dvc)new RoomBusiness(this).getData("dvcInRoom", Dvc.class);
        localDvc.getId();
        ArrayList localArrayList = new ArrayList();
        MyBusiness localMyBusiness = new MyBusiness(this);
        localMyBusiness.addNormalHeadData(localArrayList);
        localArrayList.add(Integer.valueOf(20));
        localArrayList.add(Integer.valueOf(4 + paramArrayOfByte.length));
        localArrayList.add(Integer.valueOf(97));
        localArrayList.add(Integer.valueOf(1 + localDvc.getRoomIndex()));
        localArrayList.add(Integer.valueOf(localDvc.getmIndex()));
        localArrayList.add(Integer.valueOf(1));
        for (int i = 0; i < paramArrayOfByte.length; i++)
          localArrayList.add(Integer.valueOf(toHex(paramArrayOfByte[i]), 16));
        localArrayList.add(Integer.valueOf(1));
        addCheckSumData(localArrayList);
        localArrayList.add(Integer.valueOf(22));
        byte[] arrayOfByte = localMyBusiness.getCmdData(localArrayList);
        StringUtils.btye2Str(arrayOfByte);
        XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
        return;
      }
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(paramArrayOfByte), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void setYaokongNameAndType(String paramString, int paramInt1, int paramInt2)
  {
    this.mName = paramString;
    this.type = paramInt1;
    this.brandId = paramInt2;
  }

  public void showLearnDialog()
  {
    MyAlertDialog3 localMyAlertDialog3 = new MyAlertDialog3(this);
    localMyAlertDialog3.show();
    if (DeviceListActivity.isOnePointFive)
    {
      Dvc localDvc = (Dvc)new RoomBusiness(this).getData("dvcInRoom", Dvc.class);
      localDvc.getId();
      ArrayList localArrayList = new ArrayList();
      MyBusiness localMyBusiness = new MyBusiness(this);
      localMyBusiness.addNormalHeadData(localArrayList);
      localArrayList.add(Integer.valueOf(20));
      localArrayList.add(Integer.valueOf(4));
      localArrayList.add(Integer.valueOf(97));
      localArrayList.add(Integer.valueOf(1 + localDvc.getRoomIndex()));
      localArrayList.add(Integer.valueOf(localDvc.getmIndex()));
      localArrayList.add(Integer.valueOf(2));
      localArrayList.add(Integer.valueOf(1));
      addCheckSumData(localArrayList);
      localArrayList.add(Integer.valueOf(22));
      byte[] arrayOfByte = localMyBusiness.getCmdData(localArrayList);
      XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent3.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      XlinkAgent localXlinkAgent4 = XlinkAgent.getInstance();
      2 local2 = new XlinkNetListener(localMyAlertDialog3)
      {
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
          YkAt.this.runOnUiThread(new Runnable(paramArrayOfByte)
          {
            public void run()
            {
              String str1 = StringUtils.btye2Str(this.val$bytes);
              System.out.println("M1    hongwai    " + str1);
              if (DeviceListActivity.isOnePointFive)
              {
                int i = Integer.parseInt(str1.substring(20, 22), 16);
                str1.substring(20, 22);
                if (i > 4)
                {
                  YkAt.2.this.val$dialog.dismiss();
                  String str2 = str1.substring(28, 28 + 2 * (i - 3));
                  byte[] arrayOfByte = new byte[str2.length() / 2];
                  for (int j = 0; j < str2.length(); j += 2)
                  {
                    String str3 = str2.substring(j, j + 2);
                    System.out.print(str3);
                    arrayOfByte[(j / 2)] = (byte)Integer.parseInt(str3, 16);
                  }
                  StringUtils.btye2Str(arrayOfByte);
                  YkAt.this.onRecHongWaiCode(YkAt.this.subZero(arrayOfByte));
                }
              }
            }
          });
        }

        public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
        {
        }

        public void onStart(int paramInt)
        {
        }
      };
      this.tempXlinkNetListener = local2;
      localXlinkAgent4.addXlinkListener(local2);
      return;
    }
    XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiLearn(), new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
    XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
    4 local4 = new XlinkNetListener(localMyAlertDialog3)
    {
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
        if (StringUtils.btye2Str(paramArrayOfByte).indexOf("66BB") != -1)
          return;
        YkAt.this.tBytes = YkAt.this.subZero(paramArrayOfByte);
        this.val$dialog.dismiss();
        YkAt.this.onRecHongWaiCode(YkAt.this.tBytes);
      }

      public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
      }

      public void onStart(int paramInt)
      {
      }
    };
    this.tempXlinkNetListener = local4;
    localXlinkAgent2.addXlinkListener(local4);
  }

  protected void toastL(int paramInt)
  {
    Toast.makeText(this, paramInt, 0).show();
  }

  public void vibrate()
  {
    ((Vibrator)getSystemService("vibrator")).vibrate(100L);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.YkAt
 * JD-Core Version:    0.6.0
 */