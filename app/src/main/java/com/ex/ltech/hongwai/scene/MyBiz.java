package com.ex.ltech.hongwai.scene;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Handler;
import android.os.SystemClock;
import android.util.DisplayMetrics;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.NewHongWaiMain;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.hongwai.vo.SceneVo;
import com.ex.ltech.hongwai.vo.SceneVos;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.hzy.tvmao.KKACManagerV2;
import com.hzy.tvmao.KKNonACManager;
import com.hzy.tvmao.ir.ac.ACModelV2;
import com.hzy.tvmao.ir.ac.ACStateV2;
import com.kookong.app.data.api.IrData;
import com.kookong.app.data.api.IrData.IrKey;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MyBiz extends BaseBusiness
{
  String atSceneGvItHeight = "atSceneGvItHeight";
  String atSceneGvItWidth = "atSceneGvItWidth";
  Context c;
  CmdDateBussiness cmd;
  Handler handler = new Handler()
  {
  };
  KKACManagerV2 kkACManager;
  KKNonACManager kkNonACManager;
  private MyRcDevices rcDevices;
  byte[] resendData;
  List<InnerRcVo> sceneInnerRcVos = new ArrayList();
  SceneVo vo;

  public MyBiz(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
    this.cmd = new CmdDateBussiness(paramContext, "0000");
  }

  private void loop()
  {
    InnerRcVo localInnerRcVo = (InnerRcVo)this.sceneInnerRcVos.get(0);
    MyRcDevice localMyRcDevice = (MyRcDevice)this.rcDevices.myRcDevices.get(localInnerRcVo.getmSaveRcListPosi());
    if (localInnerRcVo.isAdd());
    label442: label1102: 
    while (true)
    {
      return;
      if (localInnerRcVo.getmType() > 9)
        switch (localInnerRcVo.getmType())
        {
        default:
        case 10:
        case 11:
        case 12:
        }
      label80: 
      while (this.sceneInnerRcVos.size() > 1)
      {
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            try
            {
              MyBiz.this.sceneInnerRcVos.remove(0);
              MyBiz.this.loop();
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        }
        , 1000 * localInnerRcVo.getSpaceTime());
        return;
        SocketManager localSocketManager3 = SocketManager.instance();
        CmdDateBussiness localCmdDateBussiness2 = this.cmd;
        int i3 = localInnerRcVo.nonIrDevice.nonIrDeviceId;
        if (localInnerRcVo.nonIrDevice.irPanelSwitch1);
        for (int i4 = 226; ; i4 = 194)
        {
          byte[] arrayOfByte2 = localCmdDateBussiness2.onOffK1RFAndK2RF(i3, i4);
          this.resendData = arrayOfByte2;
          localSocketManager3.sendData(arrayOfByte2);
          break;
        }
        int i2 = 225;
        if ((localInnerRcVo.nonIrDevice.irPanelSwitchSelected1) && (localInnerRcVo.nonIrDevice.irPanelSwitchSelected2))
          if ((localInnerRcVo.nonIrDevice.irPanelSwitch1) && (localInnerRcVo.nonIrDevice.irPanelSwitch2))
          {
            i2 = -31;
            System.out.println("CMD_K2RF_ALL_ON");
          }
        while (true)
        {
          SocketManager localSocketManager2 = SocketManager.instance();
          byte[] arrayOfByte1 = this.cmd.onOffK1RFAndK2RF(localInnerRcVo.nonIrDevice.nonIrDeviceId, i2);
          this.resendData = arrayOfByte1;
          localSocketManager2.sendData(arrayOfByte1);
          break;
          if ((!localInnerRcVo.nonIrDevice.irPanelSwitch1) && (!localInnerRcVo.nonIrDevice.irPanelSwitch2))
          {
            i2 = -63;
            System.out.println("CMD_K2RF_ALL_OFF");
            continue;
          }
          if ((localInnerRcVo.nonIrDevice.irPanelSwitch1) && (!localInnerRcVo.nonIrDevice.irPanelSwitch2))
          {
            i2 = -25;
            System.out.println("CMD_K2RF_WAY1_ON_WAY2_OFF");
            continue;
          }
          if ((localInnerRcVo.nonIrDevice.irPanelSwitch1) || (!localInnerRcVo.nonIrDevice.irPanelSwitch2))
            continue;
          i2 = -24;
          System.out.println("CMD_K2RF_WAY1_OFF_WAY2_ON");
          continue;
          if (localInnerRcVo.nonIrDevice.irPanelSwitchSelected1)
          {
            if (localInnerRcVo.nonIrDevice.irPanelSwitch1);
            for (i2 = -30; ; i2 = -62)
            {
              if (!localInnerRcVo.nonIrDevice.irPanelSwitch1)
                break label442;
              System.out.println("CMD_K1RF_ON");
              break;
            }
            System.out.println("CMD_K1RF_OFF");
            continue;
          }
          if (!localInnerRcVo.nonIrDevice.irPanelSwitchSelected2)
            continue;
          if (localInnerRcVo.nonIrDevice.irPanelSwitch2);
          for (i2 = -29; ; i2 = -61)
          {
            if (!localInnerRcVo.nonIrDevice.irPanelSwitch2)
              break label505;
            System.out.println("CMD_K2RF_ON");
            break;
          }
          label505: System.out.println("CMD_K2RF_OFF");
        }
        SocketManager localSocketManager1 = SocketManager.instance();
        CmdDateBussiness localCmdDateBussiness1 = this.cmd;
        int n = localInnerRcVo.nonIrDevice.nonIrDeviceId;
        if (localInnerRcVo.getStatus().equalsIgnoreCase(this.ct.getString(2131100054)));
        for (int i1 = 2; ; i1 = 1)
        {
          localSocketManager1.sendData(localCmdDateBussiness1.controlIrCtLight(n, i1, localInnerRcVo.nonIrDevice.irCt1Onoff, localInnerRcVo.nonIrDevice.irCt1Brt, localInnerRcVo.nonIrDevice.irCt1C, localInnerRcVo.nonIrDevice.irCt1W));
          break;
        }
        String str1;
        int i;
        if (localInnerRcVo.getmType() == 5)
        {
          this.kkACManager = new KKACManagerV2((IrData)localMyRcDevice.irDatas.get(0));
          this.kkACManager.setACStateV2FromString("");
          sendRcParmas(this.kkACManager.getAcParams());
          SystemClock.sleep(400L);
          str1 = localInnerRcVo.getTypeStr();
          i = -1;
          switch (str1.hashCode())
          {
          default:
          case 96586:
          }
        }
        while (true)
          switch (i)
          {
          default:
            if ((!localInnerRcVo.getStatus().equals(this.c.getString(2131100232))) && (!localInnerRcVo.getStatus().equals(this.c.getString(2131100226))))
              break label1102;
            sendRcCodeByChineseName("电源");
            break label80;
            this.kkNonACManager = new KKNonACManager((IrData)localMyRcDevice.irDatas.get(0));
            sendRcParmas(this.kkNonACManager.getParams());
            break label661;
            if (!str1.equals("air"))
              continue;
            i = 0;
          case 0:
          }
        if (!localInnerRcVo.getStatus().equals(this.c.getString(2131100226)))
        {
          this.kkACManager.changePowerState();
          String[] arrayOfString = localInnerRcVo.getStatus().split(",");
          String str2 = this.c.getString(2131099860);
          String str3 = this.c.getString(2131099861);
          String str4 = this.c.getString(2131099862);
          String str5 = this.c.getString(2131099863);
          String str6 = this.c.getString(2131099864);
          if (arrayOfString[0].equals(str2))
            this.kkACManager.getAcStateV2().changeToTargetModel(0);
          if (arrayOfString[0].equals(str3))
            this.kkACManager.getAcStateV2().changeToTargetModel(1);
          if (arrayOfString[0].equals(str4))
            this.kkACManager.getAcStateV2().changeToTargetModel(2);
          if (arrayOfString[0].equals(str5))
            this.kkACManager.getAcStateV2().changeToTargetModel(3);
          if (arrayOfString[0].equals(str6))
            this.kkACManager.getAcStateV2().changeToTargetModel(4);
          this.kkACManager.getCurrentACModel().setTemperature(Integer.parseInt(arrayOfString[1].substring(0, arrayOfString[1].indexOf("℃"))));
        }
        while (true)
        {
          sendCmd(this.kkACManager.getACKeyIr());
          break;
          this.kkACManager.changePowerState();
          this.kkACManager.changePowerState();
        }
        int j = Integer.parseInt(localInnerRcVo.getStatus().substring(0, localInnerRcVo.getStatus().indexOf(this.c.getString(2131099929))));
        if (j > 99)
        {
          sendRcCodeByChineseName("1");
          SystemClock.sleep(500L);
          sendRcCodeByChineseName("0");
          sendCmd(null);
          SystemClock.sleep(500L);
          sendRcCodeByChineseName("0");
        }
        if ((j < 100) && (j > 9))
        {
          int k = j / 10;
          int m = j - k * 10;
          switch (k)
          {
          default:
            label1264: SystemClock.sleep(500L);
            switch (m)
            {
            default:
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
            }
          case 0:
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
          case 8:
          case 9:
          }
        }
        while (j < 10)
          switch (j)
          {
          default:
            break;
          case 0:
            sendRcCodeByChineseName("0");
            break;
            sendRcCodeByChineseName("0");
            break label1264;
            sendRcCodeByChineseName("1");
            break label1264;
            sendRcCodeByChineseName("2");
            break label1264;
            sendRcCodeByChineseName("3");
            break label1264;
            sendRcCodeByChineseName("4");
            break label1264;
            sendRcCodeByChineseName("5");
            break label1264;
            sendRcCodeByChineseName("6");
            break label1264;
            sendRcCodeByChineseName("7");
            break label1264;
            sendRcCodeByChineseName("8");
            break label1264;
            sendRcCodeByChineseName("9");
            break label1264;
            sendRcCodeByChineseName("0");
            continue;
            sendRcCodeByChineseName("1");
            continue;
            sendRcCodeByChineseName("2");
            continue;
            sendRcCodeByChineseName("3");
            continue;
            sendRcCodeByChineseName("4");
            continue;
            sendRcCodeByChineseName("5");
            continue;
            sendRcCodeByChineseName("6");
            continue;
            sendRcCodeByChineseName("7");
            continue;
            sendRcCodeByChineseName("8");
            continue;
            sendRcCodeByChineseName("9");
          case 1:
          case 2:
          case 3:
          case 4:
          case 5:
          case 6:
          case 7:
          case 8:
          case 9:
          }
        sendRcCodeByChineseName("1");
        continue;
        sendRcCodeByChineseName("2");
        continue;
        sendRcCodeByChineseName("3");
        continue;
        sendRcCodeByChineseName("4");
        continue;
        sendRcCodeByChineseName("5");
        continue;
        sendRcCodeByChineseName("6");
        continue;
        sendRcCodeByChineseName("7");
        continue;
        sendRcCodeByChineseName("8");
        continue;
        sendRcCodeByChineseName("9");
      }
    }
  }

  private void loopSend()
  {
    InnerRcVo localInnerRcVo = (InnerRcVo)this.sceneInnerRcVos.get(0);
    if (this.sceneInnerRcVos.size() > 1)
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          try
          {
            MyBiz.this.sceneInnerRcVos.remove(0);
            MyBiz.this.loopSend();
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }
      , 1000 * localInnerRcVo.getSpaceTime());
  }

  private void sendRcCodeByChineseName(String paramString)
  {
    for (int i = 0; i < this.kkNonACManager.getAllKeys().size(); i++)
    {
      if (!((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fname.equals(paramString))
        continue;
      sendCmd(this.kkNonACManager.getKeyIr(((IrData.IrKey)this.kkNonACManager.getAllKeys().get(i)).fkey));
    }
  }

  public Bitmap autoZoomInBM(Bitmap paramBitmap, double paramDouble1, double paramDouble2)
  {
    float f1 = paramBitmap.getWidth();
    float f2 = paramBitmap.getHeight();
    Matrix localMatrix = new Matrix();
    localMatrix.postScale((float)paramDouble1 / f1, (float)paramDouble2 / f2);
    return Bitmap.createBitmap(paramBitmap, 0, 0, (int)f1, (int)f2, localMatrix, true);
  }

  public int dp2px(int paramInt)
  {
    return (int)(0.5F + this.c.getResources().getDisplayMetrics().density * paramInt);
  }

  public int getGvItHeight()
  {
    return this.getter.getInt(this.atSceneGvItHeight, 0);
  }

  public int getGvItWidth()
  {
    return this.getter.getInt(this.atSceneGvItWidth, 0);
  }

  public SceneVos getSceneVos()
  {
    SceneVos localSceneVos = (SceneVos)MyDb.getInstance(this.c).getBean(DeviceListActivity.deviceMacAddress, SceneVos.class);
    if (localSceneVos == null)
    {
      InnerRcVo localInnerRcVo = new InnerRcVo();
      localInnerRcVo.setIsAdd(true);
      localSceneVos = new SceneVos();
      SceneVo localSceneVo1 = new SceneVo();
      localSceneVo1.setPicRes(0);
      localSceneVo1.setSenceIcType("goHome");
      localSceneVo1.setName(this.c.getString(2131100094));
      localSceneVo1.innerRcVos.add(localInnerRcVo);
      localSceneVos.sceneVos.add(localSceneVo1);
      SceneVo localSceneVo2 = new SceneVo();
      localSceneVo2.setPicRes(1);
      localSceneVo2.setSenceIcType("outHome");
      localSceneVo2.setName(this.c.getString(2131100146));
      localSceneVo2.innerRcVos.add(localInnerRcVo);
      localSceneVos.sceneVos.add(localSceneVo2);
      SceneVo localSceneVo3 = new SceneVo();
      localSceneVo3.setPicRes(1);
      localSceneVo3.setSenceIcType("sleep");
      localSceneVo3.setName(this.c.getString(2131100401));
      localSceneVo3.innerRcVos.add(localInnerRcVo);
      localSceneVos.sceneVos.add(localSceneVo3);
      SceneVo localSceneVo4 = new SceneVo();
      localSceneVo4.setPicRes(1);
      localSceneVo4.setSenceIcType("wakeup");
      localSceneVo4.setName(this.c.getString(2131100283));
      localSceneVo4.innerRcVos.add(localInnerRcVo);
      localSceneVos.sceneVos.add(localSceneVo4);
    }
    return localSceneVos;
  }

  public List<String> getTimeStrArr()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 3; i < 61; i++)
      localArrayList.add(i + "");
    return localArrayList;
  }

  public void saveGvItWidthHeight(int paramInt1, int paramInt2)
  {
    this.setter.putValue(this.atSceneGvItWidth, Integer.valueOf(paramInt1));
    this.setter.putValue(this.atSceneGvItHeight, Integer.valueOf(paramInt2));
  }

  public void saveSceneVos(SceneVos paramSceneVos)
  {
    MyDb.getInstance(this.c.getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, paramSceneVos);
  }

  public void send(SceneVos paramSceneVos, int paramInt)
  {
    this.sceneInnerRcVos.clear();
    this.sceneInnerRcVos.addAll(((SceneVo)paramSceneVos.sceneVos.get(paramInt)).innerRcVos);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(this.c).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    try
    {
      loop();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void sendCmd(byte[] paramArrayOfByte)
  {
    SocketManager localSocketManager = SocketManager.instance();
    byte[] arrayOfByte = this.cmd.hongWaiTest(paramArrayOfByte);
    this.resendData = arrayOfByte;
    localSocketManager.sendData(arrayOfByte);
    if (NewHongWaiMain.IS_NEW_IR_SORFT_WARE)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          SocketManager.instance().sendData(MyBiz.this.resendData);
        }
      }
      , 30L);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          SocketManager.instance().sendData(MyBiz.this.resendData);
        }
      }
      , 60L);
    }
  }

  public void sendRcParmas(byte[] paramArrayOfByte)
  {
    SocketManager localSocketManager = SocketManager.instance();
    byte[] arrayOfByte = this.cmd.hongWaiId(paramArrayOfByte);
    this.resendData = arrayOfByte;
    localSocketManager.sendData(arrayOfByte);
    if (NewHongWaiMain.IS_NEW_IR_SORFT_WARE)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          SocketManager.instance().sendData(MyBiz.this.resendData);
        }
      }
      , 30L);
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          SocketManager.instance().sendData(MyBiz.this.resendData);
        }
      }
      , 60L);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.MyBiz
 * JD-Core Version:    0.6.0
 */