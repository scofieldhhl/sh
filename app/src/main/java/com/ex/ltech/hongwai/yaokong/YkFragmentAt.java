package com.ex.ltech.hongwai.yaokong;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.MyAlertDialog3;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
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

public class YkFragmentAt extends FragmentActivity
{
  public CmdDateBussiness cmd = new CmdDateBussiness(this, "0000");
  private int gId = 0;
  private IR mIR = null;
  String mName;
  private int otherBrandId = 22;
  public int saveYkOk = 1000;
  public byte[] tBytes;
  XlinkNetListener tempXlinkNetListener;
  int type;

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
    localETGroup.Inster(ETDB.getInstance(this));
    switch (this.type)
    {
    default:
      return;
    case 8192:
      ETDeviceTV localETDeviceTV = new ETDeviceTV(this.otherBrandId, this.gId);
      localETDeviceTV.SetName(this.mName);
      localETDeviceTV.SetType(8192);
      localETDeviceTV.SetRes(0);
      localETDeviceTV.SetGID(localETGroup.GetID());
      localETDeviceTV.Inster(ETDB.getInstance(this));
      return;
    case 16384:
      ETDeviceSTB localETDeviceSTB = new ETDeviceSTB(this.otherBrandId, this.gId);
      localETDeviceSTB.SetName(this.mName);
      localETDeviceSTB.SetType(16384);
      localETDeviceSTB.SetRes(2);
      localETDeviceSTB.SetGID(localETGroup.GetID());
      localETDeviceSTB.Inster(ETDB.getInstance(this));
      return;
    case 24576:
      ETDeviceDVD localETDeviceDVD = new ETDeviceDVD(this.otherBrandId, this.gId);
      localETDeviceDVD.SetName(this.mName);
      localETDeviceDVD.SetType(24576);
      localETDeviceDVD.SetRes(3);
      localETDeviceDVD.SetGID(localETGroup.GetID());
      localETDeviceDVD.Inster(ETDB.getInstance(this));
      return;
    case 40960:
      ETDevicePJT localETDevicePJT = new ETDevicePJT(this.otherBrandId, this.gId);
      localETDevicePJT.SetName(this.mName);
      localETDevicePJT.SetType(40960);
      localETDevicePJT.SetRes(5);
      localETDevicePJT.SetGID(localETGroup.GetID());
      localETDevicePJT.Inster(ETDB.getInstance(this));
      return;
    case 32768:
      ETDeviceFANS localETDeviceFANS = new ETDeviceFANS(this.otherBrandId, this.gId);
      localETDeviceFANS.SetName(this.mName);
      localETDeviceFANS.SetType(32768);
      localETDeviceFANS.SetRes(4);
      localETDeviceFANS.SetGID(localETGroup.GetID());
      localETDeviceFANS.Inster(ETDB.getInstance(this));
      return;
    case 8448:
      ETDeviceIPTV localETDeviceIPTV = new ETDeviceIPTV(this.otherBrandId, this.gId);
      localETDeviceIPTV.SetName(this.mName);
      localETDeviceIPTV.SetType(8448);
      localETDeviceIPTV.SetRes(1);
      localETDeviceIPTV.SetGID(localETGroup.GetID());
      localETDeviceIPTV.Inster(ETDB.getInstance(this));
      return;
    case 49152:
      ETDeviceAIR localETDeviceAIR = new ETDeviceAIR(this.otherBrandId, this.gId);
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
      ETDeviceLIGHT localETDeviceLIGHT = new ETDeviceLIGHT(this.otherBrandId, this.gId);
      localETDeviceLIGHT.SetName(this.mName);
      localETDeviceLIGHT.SetType(57344);
      localETDeviceLIGHT.SetRes(6);
      localETDeviceLIGHT.SetGID(localETGroup.GetID());
      localETDeviceLIGHT.Inster(ETDB.getInstance(this));
      return;
    case 8960:
    }
    ETDeviceDC localETDeviceDC = new ETDeviceDC(this.otherBrandId, this.gId);
    localETDeviceDC.SetName(this.mName);
    localETDeviceDC.SetType(8960);
    localETDeviceDC.SetRes(8);
    localETDeviceDC.SetGID(localETGroup.GetID());
    localETDeviceDC.Inster(ETDB.getInstance(this));
  }

  public void sendCmd(byte[] paramArrayOfByte)
  {
    if (DeviceListActivity.isOnePointFive)
    {
      ArrayList localArrayList = new ArrayList();
      MyBusiness localMyBusiness = new MyBusiness(this);
      localMyBusiness.addNormalHeadData(localArrayList);
      localArrayList.add(Integer.valueOf(20));
      localArrayList.add(Integer.valueOf(4));
      localArrayList.add(Integer.valueOf(97));
      localArrayList.add(Integer.valueOf(0));
      localArrayList.add(Integer.valueOf(1));
      localArrayList.add(Integer.valueOf(2));
      localArrayList.add(Integer.valueOf(1));
      localMyBusiness.addCheckSumData(localArrayList);
      localArrayList.add(Integer.valueOf(22));
      byte[] arrayOfByte = localMyBusiness.getCmdData(localArrayList);
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
    }
    XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(paramArrayOfByte), new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
  }

  public void setYaokongNameAndType(String paramString, int paramInt)
  {
    this.mName = paramString;
    this.type = paramInt;
  }

  public void showLearnDialog()
  {
    MyAlertDialog3 localMyAlertDialog3 = new MyAlertDialog3(this);
    localMyAlertDialog3.show();
    XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
    DeviceManage.getInstance();
    localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiLearn(), new SendPipeListener()
    {
      public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
      {
      }
    });
    XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener(localMyAlertDialog3)
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
        String str = StringUtils.btye2Str(paramArrayOfByte);
        System.out.println(StringUtils.btye2Str(paramArrayOfByte) + " %#^&*()&^%*Y         onRecvPipeSyncData   2");
        if (str.indexOf("66BB") != -1)
          return;
        YkFragmentAt.this.tBytes = YkFragmentAt.this.subZero(paramArrayOfByte);
        this.val$dialog.dismiss();
        YkFragmentAt.this.onRecHongWaiCode(YkFragmentAt.this.tBytes);
        System.out.println(StringUtils.btye2Str(YkFragmentAt.this.tBytes) + " onRecHongWaiCode    2");
      }

      public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        System.out.println(StringUtils.btye2Str(paramArrayOfByte) + " %#^&*()&^%*Y         onRecvPipeSyncData   1");
        if (str.indexOf("66BB") != -1)
          return;
        YkFragmentAt.this.tBytes = YkFragmentAt.this.subZero(paramArrayOfByte);
        this.val$dialog.dismiss();
        YkFragmentAt.this.onRecHongWaiCode(YkFragmentAt.this.tBytes);
        System.out.println(StringUtils.btye2Str(YkFragmentAt.this.tBytes) + " onRecHongWaiCode    1");
      }

      public void onStart(int paramInt)
      {
      }
    });
  }

  protected void toast(int paramInt)
  {
    Toast.makeText(this, paramInt, 0).show();
  }

  protected void toast(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void toastL(int paramInt)
  {
    Toast.makeText(this, paramInt, 1).show();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.YkFragmentAt
 * JD-Core Version:    0.6.0
 */