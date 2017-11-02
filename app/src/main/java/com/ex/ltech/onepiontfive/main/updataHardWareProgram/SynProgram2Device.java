package com.ex.ltech.onepiontfive.main.updataHardWareProgram;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.utils.StringUtils;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.CRC32;

public class SynProgram2Device
{
  public static String IR2_FILE_NAME;
  public static String IR_FILE_NAME;
  public static String ONEPIONTFIVE_FILE_NAME;
  public static String ONE_PIONT_ONE_75_FILE_NAME;
  public static String ONE_PIONT_ONE_800FILE_NAME;
  public static String ONE_PIONT_ONE_FILE_NAME;
  public static int UPDATA_CANCEL;
  public static int UPDATA_OK;
  public static String UPDATA_TYPE_KEY;
  public static String UPDATA_VS_NAME = "UPDATA_VS_NAME";
  public static String WIFI_101_DMX4_UP_V0;
  private Activity activity;
  private AssetManager am;
  Runnable checkFinishRunnable = new Runnable()
  {
    public void run()
    {
      if (SynProgram2Device.this.listener != null)
      {
        SynProgram2Device.this.listener.failed();
        SynProgram2Device.this.handler.removeCallbacks(SynProgram2Device.this.checkFinishRunnable);
        SynProgram2Device.this.handler.removeCallbacks(SynProgram2Device.this.reSendRunnable);
      }
    }
  };
  int count = (int)(100.0D * Math.random());
  CRC32 crc32;
  Crc32AllFileListener crc32AllFileListener;
  ArrayList<Byte> crc32Data = new ArrayList();
  private String crc32HexString;
  private int currentSendFileTime = 1;
  ArrayList<Byte> currentSendFileTimeData = new ArrayList();
  private String currentSendFileTimeHexString;
  private XDevice device;
  String gatewayId;
  public SharedPreferences getter;
  Handler handler = new Handler();
  InputStream in = null;
  byte[] lastBuf;
  byte[] lastCmd;
  private int lastCurrentSendFileTime = 1;
  String lastRecCmd = "";
  SynListener listener;
  XlinkNetListener loopGetDeviceInfoXlinkNetListener = new XlinkNetListener()
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
      String str1 = StringUtils.btye2Str(paramArrayOfByte);
      if (SynProgram2Device.this.mfileName.equals(SynProgram2Device.IR_FILE_NAME))
        if ((str1.length() >= 28) && (str1.substring(24, 26).equalsIgnoreCase("BC")) && (!SynProgram2Device.this.lastRecCmd.equalsIgnoreCase(str1)));
      do
      {
        return;
        String str4 = str1.substring(18, 22);
        String str5 = str4.substring(0, 2) + str4.substring(2, 4);
        SynProgram2Device.this.handleRecCode(str1.substring(22, 24), str5);
        SynProgram2Device.this.lastRecCmd = str1;
        return;
      }
      while ((str1.length() < 48) || (!str1.substring(18, 20).equalsIgnoreCase("AF")) || (SynProgram2Device.this.lastRecCmd.equalsIgnoreCase(str1)));
      String str2 = str1.substring(30, 38);
      String str3 = str2.substring(6, 8) + str2.substring(4, 6) + str2.substring(2, 4) + str2.substring(0, 2);
      System.out.println("  onRecvPipeDataonRecvPipeData      回码中的第" + str1);
      System.out.println(str1.substring(28, 30) + "        onRecvPipeDataonRecvPipeData      回码中的第" + Integer.parseInt(str3, 16));
      SynProgram2Device.this.handleRecCode(str1.substring(38, 40), str3);
      SynProgram2Device.this.lastRecCmd = str1;
    }

    public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
    {
      onRecvPipeData(paramShort, paramXDevice, paramArrayOfByte);
    }

    public void onStart(int paramInt)
    {
    }
  };
  String mfileName;
  private int onePiontFiveFrameLength = 512;
  Runnable reSendRunnable = new Runnable()
  {
    public void run()
    {
      if (SynProgram2Device.this.reSendTime > 6)
      {
        if (SynProgram2Device.this.listener != null)
        {
          SynProgram2Device.this.listener.failed();
          SynProgram2Device.this.handler.removeCallbacks(SynProgram2Device.this.checkFinishRunnable);
          SynProgram2Device.this.handler.removeCallbacks(SynProgram2Device.this.reSendRunnable);
        }
        return;
      }
      SynProgram2Device.access$108(SynProgram2Device.this);
      SynProgram2Device.this.reSend();
    }
  };
  private int reSendTime = 0;
  private int sendFileTime;
  ArrayList<Byte> sendFileTimeData = new ArrayList();
  private String sendFileTimeHexString;
  public UserFerences setter;

  static
  {
    UPDATA_TYPE_KEY = "UPDATA_TYPE_KEY";
    ONEPIONTFIVE_FILE_NAME = "gateway_app.bin";
    ONE_PIONT_ONE_FILE_NAME = "WIFI-101-RGBW-UP-V0.bin";
    ONE_PIONT_ONE_75_FILE_NAME = "WIFI-101-75-UP-V0.bin";
    ONE_PIONT_ONE_800FILE_NAME = "WIFI-800-RGB-UP-V0.bin";
    WIFI_101_DMX4_UP_V0 = "WIFI-101-DMX4-UP-V0.bin";
    IR_FILE_NAME = "IR-WF-A.bin";
    IR2_FILE_NAME = "IR-WF-BB.bin";
    UPDATA_CANCEL = 10000;
    UPDATA_OK = 20000;
  }

  public SynProgram2Device(Activity paramActivity, XDevice paramXDevice, String paramString)
  {
    this.mfileName = paramString;
    this.activity = paramActivity;
    this.setter = UserFerences.getUserFerences(this.activity);
    this.getter = this.setter.spFerences;
    this.gatewayId = this.getter.getString("GateWayIdKey" + this.getter.getString("GateWayMacIdKey", "11223344"), "00000000");
    this.device = paramXDevice;
    this.am = paramActivity.getResources().getAssets();
    if ((paramString.equalsIgnoreCase(ONE_PIONT_ONE_FILE_NAME) | paramString.equalsIgnoreCase(ONE_PIONT_ONE_75_FILE_NAME) | paramString.equalsIgnoreCase(ONE_PIONT_ONE_800FILE_NAME) | paramString.equalsIgnoreCase(WIFI_101_DMX4_UP_V0)))
      this.onePiontFiveFrameLength = 256;
    initSendFileTimeData();
    initCurrentSendFileTimeData();
    byte[] arrayOfByte = new byte[this.onePiontFiveFrameLength * this.sendFileTime];
    try
    {
      this.in = this.am.open(this.mfileName);
      this.in.read(arrayOfByte);
      this.in.close();
    }
    catch (Exception localException1)
    {
      try
      {
        while (true)
        {
          this.in = this.am.open(this.mfileName);
          XlinkAgent.getInstance().addXlinkListener(this.loopGetDeviceInfoXlinkNetListener);
          return;
          localException1 = localException1;
          localException1.printStackTrace();
        }
      }
      catch (Exception localException2)
      {
        while (true)
          localException2.printStackTrace();
      }
    }
  }

  private ArrayList<Byte> assembleData(byte[] paramArrayOfByte)
  {
    initCrc32DataData(paramArrayOfByte);
    if (this.mfileName.equals(IR_FILE_NAME))
    {
      ArrayList localArrayList1 = new ArrayList();
      localArrayList1.add(Byte.valueOf(102));
      localArrayList1.add(Byte.valueOf(-69));
      localArrayList1.add(Byte.valueOf((byte)this.currentSendFileTime));
      localArrayList1.add(Byte.valueOf(-64));
      localArrayList1.add(Byte.valueOf(0));
      localArrayList1.add(Byte.valueOf(0));
      localArrayList1.add(Byte.valueOf(44));
      localArrayList1.add(Byte.valueOf(2));
      localArrayList1.add(Byte.valueOf(8));
      localArrayList1.addAll(this.sendFileTimeData);
      localArrayList1.addAll(this.currentSendFileTimeData);
      for (int i = 0; i < paramArrayOfByte.length; i++)
        localArrayList1.add(Byte.valueOf(paramArrayOfByte[i]));
      localArrayList1.addAll(this.crc32Data);
      localArrayList1.add(Byte.valueOf(-21));
      return localArrayList1;
    }
    if (this.mfileName.equals(ONEPIONTFIVE_FILE_NAME))
    {
      ArrayList localArrayList2 = new ArrayList();
      localArrayList2.add(Byte.valueOf(90));
      localArrayList2.add(Byte.valueOf(-91));
      localArrayList2.add(Byte.valueOf((byte)this.currentSendFileTime));
      localArrayList2.add(Byte.valueOf(1));
      localArrayList2.add(Byte.valueOf(17));
      localArrayList2.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(0, 2), 16)));
      localArrayList2.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(2, 4), 16)));
      localArrayList2.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(4, 6), 16)));
      localArrayList2.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(6, 8), 16)));
      localArrayList2.add(Byte.valueOf(47));
      localArrayList2.add(Byte.valueOf(-1));
      localArrayList2.addAll(this.sendFileTimeData);
      localArrayList2.addAll(this.currentSendFileTimeData);
      for (int j = 0; j < paramArrayOfByte.length; j++)
        localArrayList2.add(Byte.valueOf(paramArrayOfByte[j]));
      localArrayList2.addAll(this.crc32Data);
      localArrayList2.add(Byte.valueOf(1));
      addCheckSumData(localArrayList2);
      localArrayList2.add(Byte.valueOf(22));
      return localArrayList2;
    }
    ArrayList localArrayList3 = new ArrayList();
    localArrayList3.add(Byte.valueOf(90));
    localArrayList3.add(Byte.valueOf(-91));
    localArrayList3.add(Byte.valueOf((byte)this.currentSendFileTime));
    localArrayList3.add(Byte.valueOf(1));
    localArrayList3.add(Byte.valueOf(17));
    localArrayList3.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(0, 2), 16)));
    localArrayList3.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(2, 4), 16)));
    localArrayList3.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(4, 6), 16)));
    localArrayList3.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(6, 8), 16)));
    localArrayList3.add(Byte.valueOf(47));
    localArrayList3.add(Byte.valueOf(12));
    localArrayList3.addAll(this.sendFileTimeData);
    localArrayList3.addAll(this.currentSendFileTimeData);
    for (int k = 0; k < paramArrayOfByte.length; k++)
      localArrayList3.add(Byte.valueOf(paramArrayOfByte[k]));
    localArrayList3.addAll(this.crc32Data);
    localArrayList3.add(Byte.valueOf(1));
    addCheckSumData(localArrayList3);
    localArrayList3.add(Byte.valueOf(22));
    return localArrayList3;
  }

  // ERROR //
  private void createFileWithByte(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: new 320	java/io/File
    //   3: dup
    //   4: invokestatic 326	android/os/Environment:getExternalStorageDirectory	()Ljava/io/File;
    //   7: ldc 72
    //   9: invokespecial 329	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   12: astore_2
    //   13: aconst_null
    //   14: astore_3
    //   15: aconst_null
    //   16: astore 4
    //   18: aload_2
    //   19: invokevirtual 333	java/io/File:exists	()Z
    //   22: istore 11
    //   24: aconst_null
    //   25: astore 4
    //   27: aconst_null
    //   28: astore_3
    //   29: iload 11
    //   31: ifeq +8 -> 39
    //   34: aload_2
    //   35: invokevirtual 336	java/io/File:delete	()Z
    //   38: pop
    //   39: aload_2
    //   40: invokevirtual 339	java/io/File:createNewFile	()Z
    //   43: pop
    //   44: new 341	java/io/FileOutputStream
    //   47: dup
    //   48: aload_2
    //   49: invokespecial 344	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   52: astore 14
    //   54: new 346	java/io/BufferedOutputStream
    //   57: dup
    //   58: aload 14
    //   60: invokespecial 349	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   63: astore 15
    //   65: aload 15
    //   67: aload_1
    //   68: invokevirtual 352	java/io/BufferedOutputStream:write	([B)V
    //   71: aload 15
    //   73: invokevirtual 355	java/io/BufferedOutputStream:flush	()V
    //   76: aload 14
    //   78: ifnull +8 -> 86
    //   81: aload 14
    //   83: invokevirtual 356	java/io/FileOutputStream:close	()V
    //   86: aload 15
    //   88: ifnull +160 -> 248
    //   91: aload 15
    //   93: invokevirtual 357	java/io/BufferedOutputStream:close	()V
    //   96: return
    //   97: astore 17
    //   99: aload 17
    //   101: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   104: goto -18 -> 86
    //   107: astore 16
    //   109: aload 16
    //   111: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   114: return
    //   115: astore 8
    //   117: aload 8
    //   119: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   122: aload_3
    //   123: ifnull +7 -> 130
    //   126: aload_3
    //   127: invokevirtual 356	java/io/FileOutputStream:close	()V
    //   130: aload 4
    //   132: ifnull -36 -> 96
    //   135: aload 4
    //   137: invokevirtual 357	java/io/BufferedOutputStream:close	()V
    //   140: return
    //   141: astore 9
    //   143: aload 9
    //   145: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   148: return
    //   149: astore 10
    //   151: aload 10
    //   153: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   156: goto -26 -> 130
    //   159: astore 5
    //   161: aload_3
    //   162: ifnull +7 -> 169
    //   165: aload_3
    //   166: invokevirtual 356	java/io/FileOutputStream:close	()V
    //   169: aload 4
    //   171: ifnull +8 -> 179
    //   174: aload 4
    //   176: invokevirtual 357	java/io/BufferedOutputStream:close	()V
    //   179: aload 5
    //   181: athrow
    //   182: astore 7
    //   184: aload 7
    //   186: invokevirtual 358	java/io/IOException:printStackTrace	()V
    //   189: goto -20 -> 169
    //   192: astore 6
    //   194: aload 6
    //   196: invokevirtual 265	java/lang/Exception:printStackTrace	()V
    //   199: goto -20 -> 179
    //   202: astore 5
    //   204: aload 14
    //   206: astore_3
    //   207: aconst_null
    //   208: astore 4
    //   210: goto -49 -> 161
    //   213: astore 5
    //   215: aload 15
    //   217: astore 4
    //   219: aload 14
    //   221: astore_3
    //   222: goto -61 -> 161
    //   225: astore 8
    //   227: aload 14
    //   229: astore_3
    //   230: aconst_null
    //   231: astore 4
    //   233: goto -116 -> 117
    //   236: astore 8
    //   238: aload 15
    //   240: astore 4
    //   242: aload 14
    //   244: astore_3
    //   245: goto -128 -> 117
    //   248: return
    //
    // Exception table:
    //   from	to	target	type
    //   81	86	97	java/io/IOException
    //   91	96	107	java/lang/Exception
    //   18	24	115	java/lang/Exception
    //   34	39	115	java/lang/Exception
    //   39	54	115	java/lang/Exception
    //   135	140	141	java/lang/Exception
    //   126	130	149	java/io/IOException
    //   18	24	159	finally
    //   34	39	159	finally
    //   39	54	159	finally
    //   117	122	159	finally
    //   165	169	182	java/io/IOException
    //   174	179	192	java/lang/Exception
    //   54	65	202	finally
    //   65	76	213	finally
    //   54	65	225	java/lang/Exception
    //   65	76	236	java/lang/Exception
  }

  private int getSendAllFileTime()
  {
    int i = 0;
    byte[] arrayOfByte = new byte[this.onePiontFiveFrameLength];
    try
    {
      InputStream localInputStream = this.am.open(this.mfileName);
      while (localInputStream.read(arrayOfByte) != -1)
        i++;
      localInputStream.close();
      return i;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return i;
  }

  private void handleRecCode(String paramString1, String paramString2)
  {
    int i = -1;
    switch (paramString1.hashCode())
    {
    default:
      switch (i)
      {
      default:
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
    do
    {
      do
      {
        return;
        if (!paramString1.equals("00"))
          break;
        i = 0;
        break;
        if (!paramString1.equals("01"))
          break;
        i = 1;
        break;
        if (!paramString1.equals("02"))
          break;
        i = 2;
        break;
        if (!paramString1.equals("03"))
          break;
        i = 3;
        break;
        SystemClock.sleep(50L);
        System.out.println("onRecvPipeData = 发送成功");
        if ((Integer.parseInt(paramString2, 16) >= this.sendFileTime) || (Integer.parseInt(paramString2, 16) != this.currentSendFileTime) || (this.listener == null))
          continue;
        this.listener.onPercent(100 * Integer.parseInt(paramString2, 16) / this.sendFileTime);
        this.currentSendFileTime = (1 + this.currentSendFileTime);
        syn();
      }
      while ((Integer.parseInt(paramString2, 16) != this.sendFileTime) || (this.listener == null));
      this.listener.ok();
      this.handler.removeCallbacks(this.checkFinishRunnable);
      this.handler.removeCallbacks(this.reSendRunnable);
      return;
      System.out.println("onRecvPipeData = 可能丢包了");
      return;
      System.out.println("onRecvPipeData = 失败");
      return;
      System.out.println("onRecvPipeData = 最终检验成功");
    }
    while (this.crc32AllFileListener == null);
    this.crc32AllFileListener.ok();
    this.crc32AllFileListener = null;
  }

  private void initCrc32DataData(byte[] paramArrayOfByte)
  {
    this.crc32 = new CRC32();
    this.crc32Data.clear();
    this.crc32.update(paramArrayOfByte);
    this.crc32HexString = Long.toHexString(this.crc32.getValue());
    for (int i = this.crc32HexString.length(); i < 8; i++)
      this.crc32HexString = ("0" + this.crc32HexString);
    if (this.mfileName.equals(IR_FILE_NAME))
    {
      this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(0, 2), 16).byteValue()));
      this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(2, 4), 16).byteValue()));
      this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(4, 6), 16).byteValue()));
      this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(6, 8), 16).byteValue()));
      return;
    }
    this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(6, 8), 16).byteValue()));
    this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(4, 6), 16).byteValue()));
    this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(2, 4), 16).byteValue()));
    this.crc32Data.add(Byte.valueOf(Integer.valueOf(this.crc32HexString.substring(0, 2), 16).byteValue()));
  }

  private void initCurrentSendFileTimeData()
  {
    this.currentSendFileTimeData.clear();
    this.sendFileTimeHexString = Integer.toHexString(this.currentSendFileTime);
    if (this.mfileName.equals(IR_FILE_NAME))
    {
      for (int j = this.sendFileTimeHexString.length(); j < 4; j++)
        this.sendFileTimeHexString = ("0" + this.sendFileTimeHexString);
      this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(0, 2), 16).byteValue()));
      this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(2, 4), 16).byteValue()));
      return;
    }
    for (int i = this.sendFileTimeHexString.length(); i < 8; i++)
      this.sendFileTimeHexString = ("0" + this.sendFileTimeHexString);
    this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(6, 8), 16).byteValue()));
    this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(4, 6), 16).byteValue()));
    this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(2, 4), 16).byteValue()));
    this.currentSendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(0, 2), 16).byteValue()));
  }

  private void initSendFileTimeData()
  {
    this.sendFileTimeData.clear();
    this.sendFileTime = getSendAllFileTime();
    this.sendFileTimeHexString = Integer.toHexString(this.sendFileTime);
    if (this.mfileName.equals(IR_FILE_NAME))
    {
      for (int j = this.sendFileTimeHexString.length(); j < 4; j++)
        this.sendFileTimeHexString = ("0" + this.sendFileTimeHexString);
      this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(0, 2), 16).byteValue()));
      this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(2, 4), 16).byteValue()));
      return;
    }
    for (int i = this.sendFileTimeHexString.length(); i < 8; i++)
      this.sendFileTimeHexString = ("0" + this.sendFileTimeHexString);
    this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(6, 8), 16).byteValue()));
    this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(4, 6), 16).byteValue()));
    this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(2, 4), 16).byteValue()));
    this.sendFileTimeData.add(Byte.valueOf(Integer.valueOf(this.sendFileTimeHexString.substring(0, 2), 16).byteValue()));
  }

  private void reSend()
  {
    this.currentSendFileTime = this.lastCurrentSendFileTime;
    SocketManager.instance().sendData(getCmdData(assembleData(this.lastBuf)));
    this.handler.removeCallbacks(this.reSendRunnable);
    this.handler.postDelayed(this.reSendRunnable, 5000L);
  }

  private ArrayList<Byte> upgradeCheck(byte[] paramArrayOfByte)
  {
    initCrc32DataData(paramArrayOfByte);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Byte.valueOf(90));
    localArrayList.add(Byte.valueOf(-91));
    localArrayList.add(Byte.valueOf((byte)this.currentSendFileTime));
    localArrayList.add(Byte.valueOf(1));
    localArrayList.add(Byte.valueOf(17));
    localArrayList.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(0, 2), 16)));
    localArrayList.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(2, 4), 16)));
    localArrayList.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(4, 6), 16)));
    localArrayList.add(Byte.valueOf((byte)Integer.parseInt(this.gatewayId.substring(6, 8), 16)));
    localArrayList.add(Byte.valueOf(47));
    localArrayList.add(Byte.valueOf(12));
    localArrayList.addAll(this.sendFileTimeData);
    localArrayList.addAll(this.sendFileTimeData);
    localArrayList.addAll(this.crc32Data);
    localArrayList.add(Byte.valueOf(1));
    addCheckSumData(localArrayList);
    localArrayList.add(Byte.valueOf(22));
    return localArrayList;
  }

  public void addCheckSumData(List<Byte> paramList)
  {
    int i = 0;
    for (int j = 0; j < paramList.size(); j++)
      i += Integer.parseInt(Integer.toHexString(0xFF & ((Byte)paramList.get(j)).byteValue()), 16);
    String str1 = Integer.toHexString(i);
    if (str1.length() == 3)
      str1 = "0" + str1;
    if (str1.length() == 2)
      str1 = "00" + str1;
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = str1.substring(0, 2);
    paramList.add(Byte.valueOf((byte)Integer.valueOf(str2, 16).intValue()));
    paramList.add(Byte.valueOf((byte)Integer.valueOf(str3, 16).intValue()));
  }

  public void addCheckSumData2(List<Integer> paramList)
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

  public void allFileCrc32Check()
  {
    try
    {
      this.in = this.am.open(this.mfileName);
      byte[] arrayOfByte = new byte[this.onePiontFiveFrameLength * this.sendFileTime];
      int i = this.in.available();
      this.in.read(arrayOfByte);
      for (int j = i; j < this.onePiontFiveFrameLength * this.sendFileTime; j++)
        arrayOfByte[j] = 0;
      this.in.close();
      SocketManager.instance().sendData(getCmdData(upgradeCheck(arrayOfByte)));
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void close()
  {
    setListener(null);
    XlinkAgent.getInstance().removeListener(this.loopGetDeviceInfoXlinkNetListener);
    this.loopGetDeviceInfoXlinkNetListener = null;
  }

  public byte[] getCmdData(ArrayList<Byte> paramArrayList)
  {
    byte[] arrayOfByte = new byte[paramArrayList.size()];
    for (int i = 0; i < paramArrayList.size(); i++)
      arrayOfByte[i] = ((Byte)paramArrayList.get(i)).byteValue();
    return arrayOfByte;
  }

  public void setCrc32AllFileListener(Crc32AllFileListener paramCrc32AllFileListener)
  {
    this.crc32AllFileListener = paramCrc32AllFileListener;
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        if (SynProgram2Device.this.crc32AllFileListener != null)
        {
          SynProgram2Device.this.crc32AllFileListener.failed();
          SynProgram2Device.this.crc32AllFileListener = null;
        }
      }
    }
    , 2000L);
  }

  public void setListener(SynListener paramSynListener)
  {
    this.listener = paramSynListener;
  }

  public void syn()
  {
    try
    {
      this.handler.removeCallbacks(this.checkFinishRunnable);
      this.handler.postDelayed(this.checkFinishRunnable, 20000L);
      this.handler.removeCallbacks(this.reSendRunnable);
      this.handler.postDelayed(this.reSendRunnable, 5000L);
      this.reSendTime = 0;
      initCurrentSendFileTimeData();
      byte[] arrayOfByte1 = new byte[this.onePiontFiveFrameLength];
      try
      {
        this.in.read(arrayOfByte1);
        this.lastBuf = arrayOfByte1;
        this.lastCurrentSendFileTime = this.currentSendFileTime;
        SocketManager localSocketManager = SocketManager.instance();
        byte[] arrayOfByte2 = getCmdData(assembleData(arrayOfByte1));
        this.lastCmd = arrayOfByte2;
        localSocketManager.sendData(arrayOfByte2);
        return;
      }
      catch (Exception localException2)
      {
        while (true)
          localException2.printStackTrace();
      }
    }
    catch (Exception localException1)
    {
      localException1.printStackTrace();
    }
  }

  public static abstract interface Crc32AllFileListener
  {
    public abstract void failed();

    public abstract void ok();
  }

  public static abstract interface SynListener
  {
    public abstract void failed();

    public abstract void ok();

    public abstract void onPercent(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.updataHardWareProgram.SynProgram2Device
 * JD-Core Version:    0.6.0
 */