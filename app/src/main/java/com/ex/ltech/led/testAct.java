package com.ex.ltech.led;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
import et.song.db.ETDB;
import et.song.device.FastComparator;
import et.song.device.FastItem;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETGroup;
import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;
import et.song.remote.instance.AIR;
import et.song.tool.ETTool;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class testAct extends Activity
  implements View.OnClickListener
{

  @Bind({2131559503})
  TextView TextAirAuto;

  @Bind({2131559492})
  TextView TextAirDir;

  @Bind({2131559491})
  TextView TextAirDirShow;

  @Bind({2131559502})
  TextView TextAirHand;

  @Bind({2131559504})
  TextView TextAirMode;

  @Bind({2131559497})
  TextView TextAirModeAuto;

  @Bind({2131559498})
  TextView TextAirModeCool;

  @Bind({2131559499})
  TextView TextAirModeDrying;

  @Bind({2131559496})
  TextView TextAirModeShow;

  @Bind({2131559501})
  TextView TextAirModeWarm;

  @Bind({2131559500})
  TextView TextAirModeWind;

  @Bind({2131559505})
  TextView TextAirPower;

  @Bind({2131559494})
  TextView TextAirRate;

  @Bind({2131559493})
  TextView TextAirRateShow;

  @Bind({2131559506})
  TextView TextAirSpeed;

  @Bind({2131559495})
  TextView TextAirTemp;

  @Bind({2131559507})
  TextView TextAirTempadd;

  @Bind({2131559508})
  TextView TextAirTempsub;
  byte[] add;
  byte[] autoWind;
  CmdDateBussiness cmd = new CmdDateBussiness(this, "0000");
  byte[] headWind;
  int key = 0;
  private ETDeviceAIR mDevice = null;
  int mDeviceIndex = 3;
  private ETGroup mGroup = null;
  int mGroupIndex = 0;
  private IR mIR = null;
  byte[] mode;
  byte[] off;
  byte[] on;
  byte[] speed;
  byte[] stub;
  byte[] td = { 48, 1, 3, 62, 25, 1, 2, 1, 1, 1, 1, 3, 0, 0, -1, -108 };
  byte[] temp;
  int type = 0;

  private List<FastItem> getYaokongList(int paramInt1, int paramInt2, String paramString)
  {
    String str1 = "";
    switch (paramInt1)
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
    }
    label68: ArrayList localArrayList;
    while (true)
    {
      byte[] arrayOfByte1;
      if ("0".equals("0"))
      {
        arrayOfByte1 = new byte[110];
        Arrays.fill(arrayOfByte1, 0);
      }
      try
      {
        byte[] arrayOfByte3 = ETTool.HexStringToBytes(paramString);
        arrayOfByte2 = arrayOfByte3;
        localArrayList = new ArrayList();
        localBufferedReader = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream("assets/" + str1 + "/" + paramInt2 + ".db")));
        i = 0;
      }
      catch (Exception localException1)
      {
        try
        {
          BufferedReader localBufferedReader;
          while (true)
          {
            int i;
            String str2 = localBufferedReader.readLine();
            if (str2 == null)
              break label365;
            localArrayList.add(new FastItem(paramInt2, i, "", ETTool.Dice(ETTool.HexStringToBytes(str2), arrayOfByte2)));
            System.out.println(str2 + "martinfuck");
            i++;
            continue;
            str1 = "air";
            break;
            str1 = "dvd";
            break;
            str1 = "fans";
            break;
            str1 = "fonts";
            break;
            str1 = "iptv";
            break;
            str1 = "pjt";
            break;
            str1 = "stb";
            break;
            str1 = "tv";
            break;
            if ("0".equals("1"))
            {
              arrayOfByte1 = new byte['æ'];
              break label68;
            }
            if ("0".equals("99"))
            {
              arrayOfByte1 = new byte[110];
              break label68;
            }
            boolean bool = "0".equals("100");
            arrayOfByte1 = null;
            if (!bool)
              break label68;
            arrayOfByte1 = new byte['æ'];
            break label68;
            localException1 = localException1;
            localException1.printStackTrace();
            byte[] arrayOfByte2 = null;
          }
          label365: localBufferedReader.close();
          Collections.sort(localArrayList, new FastComparator());
          if (7 < localArrayList.size());
          int k;
          for (int j = 7; ; j = k)
          {
            return localArrayList.subList(0, j);
            k = localArrayList.size();
          }
        }
        catch (Exception localException2)
        {
          localException2.printStackTrace();
          System.out.println("martinfuck");
        }
      }
    }
    return localArrayList.subList(0, 1);
  }

  private void saveYaokong(String paramString, int paramInt1, int paramInt2)
  {
    ETDeviceAIR localETDeviceAIR = new ETDeviceAIR(paramInt1, paramInt2);
    localETDeviceAIR.SetName(paramString);
    localETDeviceAIR.SetType(49152);
    localETDeviceAIR.SetRes(7);
    localETDeviceAIR.SetGID(0);
    localETDeviceAIR.SetTemp(((AIR)this.mIR).GetTemp());
    localETDeviceAIR.SetMode(2);
    localETDeviceAIR.SetPower(((AIR)this.mIR).GetPower());
    localETDeviceAIR.SetWindRate(((AIR)this.mIR).GetWindRate());
    localETDeviceAIR.SetWindDir(((AIR)this.mIR).GetWindDir());
    localETDeviceAIR.SetAutoWindDir(((AIR)this.mIR).GetAutoWindDir());
    localETDeviceAIR.Inster(ETDB.getInstance(this));
  }

  public static byte[] subBytes(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = new byte[paramInt2];
    for (int i = paramInt1; i < paramInt1 + paramInt2; i++)
      arrayOfByte[(i - paramInt1)] = paramArrayOfByte[i];
    return arrayOfByte;
  }

  private void testYaokong(FastItem paramFastItem, int paramInt1, int paramInt2)
  {
    this.mIR = ETIR.Builder(paramInt1);
    int i = 0;
    Object localObject;
    switch (paramInt1)
    {
    default:
      localObject = null;
      if (i != 0)
        break;
    case 8192:
    case 8448:
    case 16384:
    case 24576:
    case 40960:
    case 57344:
    case 32768:
    case 49152:
    case 8960:
    }
    while (true)
    {
      return;
      i = 8203;
      break;
      i = 8449;
      break;
      i = 16385;
      break;
      i = 24589;
      break;
      i = 40961;
      break;
      i = 57349;
      break;
      i = 32773;
      break;
      i = 49153;
      break;
      i = 8961;
      break;
      try
      {
        localObject = this.mIR.Search(paramInt2, 1, i);
        if ((i == 49153) && (((AIR)this.mIR).GetPower() == 0))
        {
          byte[] arrayOfByte = this.mIR.Search(paramInt2, 1, i);
          localObject = arrayOfByte;
        }
        if (localObject == null)
          continue;
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(localObject), new SendPipeListener()
        {
          public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
          {
          }
        });
        saveYaokong("", paramInt2, 1);
        return;
      }
      catch (Exception localException)
      {
        while (true)
          localException.printStackTrace();
      }
    }
  }

  public void onClick(View paramView)
  {
    if (paramView == this.TextAirHand)
    {
      this.key = 49159;
      XlinkAgent localXlinkAgent7 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent7.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(this.headWind), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    if (paramView == this.TextAirAuto)
    {
      this.key = 49161;
      XlinkAgent localXlinkAgent6 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent6.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(this.autoWind), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    if (paramView == this.TextAirModeShow)
    {
      this.key = 49155;
      XlinkAgent localXlinkAgent5 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent5.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(this.mode), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    if (paramView == this.TextAirPower)
    {
      this.key = 49153;
      XlinkAgent localXlinkAgent4 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent4.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiLearn(), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    if (paramView == this.TextAirSpeed)
    {
      this.key = 49157;
      XlinkAgent localXlinkAgent3 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent3.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(this.td), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      Toast.makeText(getApplicationContext(), StringUtils.btye2Str(this.cmd.hongWaiTest(this.td)), 1).show();
    }
    if (paramView == this.TextAirTempadd)
    {
      this.key = 49163;
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(this.add), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    if (paramView == this.TextAirTempsub)
    {
      this.key = 49165;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.getAllOnOffCmd(160), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968892);
    ButterKnife.bind(this);
    this.TextAirHand.setOnClickListener(this);
    this.TextAirAuto.setOnClickListener(this);
    this.TextAirModeShow.setOnClickListener(this);
    this.TextAirPower.setOnClickListener(this);
    this.TextAirSpeed.setOnClickListener(this);
    this.TextAirTempadd.setOnClickListener(this);
    this.TextAirTempsub.setOnClickListener(this);
    this.mIR = ETIR.Builder(this.type);
    this.headWind = new byte[] { 48, 1, 0, 66, 25, 2, 3, 0, 1, 7, 2, 3, 3, 80, -1, -16 };
    this.autoWind = new byte[] { 48, 1, 0, 66, 25, 2, 3, 1, 1, 9, 2, 3, 3, 80, -1, -13 };
    this.mode = new byte[] { 48, 1, 0, 66, 25, 1, 2, 1, 1, 3, 2, 3, 3, 80, -1, -21 };
    this.off = new byte[] { 48, 1, 0, 66, 25, 1, 2, 1, 1, 1, 1, 3, 3, 80, -1, -24 };
    this.on = new byte[] { 48, 1, 0, 66, 25, 1, 2, 1, 0, 1, 1, 3, 3, 80, -1, -25 };
    this.speed = new byte[] { 48, 1, 0, 66, 25, 2, 2, 1, 1, 5, 2, 3, 3, 80, -1, -18 };
    this.add = new byte[] { 48, 1, 0, 66, 26, 2, 3, 1, 1, 11, 2, 3, 3, 80, -1, -10 };
    this.stub = new byte[] { 48, 1, 0, 66, 25, 2, 3, 1, 1, 13, 2, 3, 3, 80, -1, -9 };
    XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener()
    {
      protected Object clone()
        throws CloneNotSupportedException
      {
        return super.clone();
      }

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
        if ((str.indexOf("66") == -1) && (str.indexOf("BB") == -1))
        {
          testAct.this.temp = paramArrayOfByte;
          System.out.println("M1    hongwai    " + StringUtils.btye2Str(testAct.this.temp));
          List localList = testAct.this.getYaokongList(2, 3, StringUtils.btye2Str(testAct.this.temp));
          testAct.this.testYaokong(null, 49152, 3);
          for (int i = 0; i < localList.size(); i++)
            System.out.println("M1    FastItem = " + ((FastItem)localList.get(i)).col);
        }
      }

      public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
        String str = StringUtils.btye2Str(paramArrayOfByte);
        if ((str.indexOf("66") == -1) && (str.indexOf("BB") == -1))
        {
          testAct.this.temp = paramArrayOfByte;
          try
          {
            System.out.println("M2   hongwai    " + StringUtils.btye2Str(testAct.this.temp));
            List localList = testAct.this.getYaokongList(2, 3, StringUtils.btye2Str(testAct.this.temp));
            testAct.this.testYaokong(null, 2, 3);
            for (int i = 0; i < localList.size(); i++)
              System.out.println("M2   FastItem = " + ((FastItem)localList.get(i)).col);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }

      public void onStart(int paramInt)
      {
      }
    });
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.testAct
 * JD-Core Version:    0.6.0
 */