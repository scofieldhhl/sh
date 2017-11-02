package com.ex.ltech.hongwai.yaokong;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.atRcs.AtAirConActivity;
import com.ex.ltech.hongwai.atRcs.AtFan;
import com.ex.ltech.hongwai.atRcs.AtNewTv;
import com.ex.ltech.hongwai.atRcs.AtProjecter;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import et.song.db.ETDB;
import et.song.device.FastItem;
import et.song.etclass.ETDevice;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETDeviceDVD;
import et.song.etclass.ETDeviceFANS;
import et.song.etclass.ETDeviceIPTV;
import et.song.etclass.ETDevicePJT;
import et.song.etclass.ETDeviceSTB;
import et.song.etclass.ETDeviceTV;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import et.song.jni.ir.ETIR;
import et.song.remote.face.IR;
import et.song.remote.instance.AIR;
import et.song.remote.instance.DC;
import et.song.remote.instance.DVD;
import et.song.remote.instance.FANS;
import et.song.remote.instance.IPTV;
import et.song.remote.instance.LIGHT;
import et.song.remote.instance.PJT;
import et.song.remote.instance.POWER;
import et.song.remote.instance.STB;
import et.song.remote.instance.TV;
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

public class AtLearnActivity extends YkAt
{

  @Bind({2131558927})
  TextView again;
  CmdDateBussiness cmd;
  private int currentTestYkPosi = -1;
  List<FastItem> data = new ArrayList();
  int group;

  @Bind({2131558928})
  TextView handCfg;
  Handler handler = new Handler();
  int index;

  @Bind({2131558816})
  RelativeLayout info;

  @Bind({2131558809})
  TextView info1;

  @Bind({2131558925})
  TextView info10;

  @Bind({2131558810})
  LinearLayout info2;

  @Bind({2131558813})
  TextView info3;

  @Bind({2131558921})
  TextView info4;

  @Bind({2131558922})
  TextView info5;

  @Bind({2131558923})
  TextView info6;

  @Bind({2131558930})
  TextView info8;
  private boolean isRecDataOk;
  private boolean isReceivePipeData = false;

  @Bind({2131558920})
  ImageView iv_center;

  @Bind({2131558924})
  TextView last;

  @Bind({2131558926})
  LinearLayout learn_again;
  private int mCol;
  private IR mIR = null;
  private String mName;

  @Bind({2131558795})
  TextView next;

  @Bind({2131558874})
  TextView on;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      AtLearnActivity.this.nextTest();
      AtLearnActivity.this.handler.postDelayed(this, 3000L);
    }
  };

  @Bind({2131558929})
  RelativeLayout save;
  int type;
  XlinkNetListener xlinkNetListener;

  public static IR Builder(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return null;
    case 8192:
      return new TV();
    case 8448:
      return new IPTV();
    case 8960:
      return new DC();
    case 11008:
      return new POWER();
    case 16384:
      return new STB();
    case 24576:
      return new DVD();
    case 32768:
      return new FANS();
    case 40960:
      return new PJT();
    case 49152:
      return new AIR();
    case 57344:
    }
    return new LIGHT();
  }

  // ERROR //
  private List<FastItem> getYaokongList(int paramInt1, int paramInt2, String paramString)
  {
    // Byte code:
    //   0: ldc 157
    //   2: astore 4
    //   4: iload_1
    //   5: lookupswitch	default:+75->80, 8192:+205->210, 8448:+212->217, 16384:+219->224, 24576:+226->231, 32768:+240->245, 40960:+233->238, 49152:+247->252, 57344:+75->80
    //   81: invokestatic 163	et/song/tool/ETTool:HexStringToBytes	(Ljava/lang/String;)[B
    //   84: astore 13
    //   86: aload 13
    //   88: astore 6
    //   90: new 74	java/util/ArrayList
    //   93: dup
    //   94: invokespecial 75	java/util/ArrayList:<init>	()V
    //   97: astore 7
    //   99: new 165	java/io/BufferedReader
    //   102: dup
    //   103: new 167	java/io/InputStreamReader
    //   106: dup
    //   107: aload_0
    //   108: invokevirtual 173	java/lang/Object:getClass	()Ljava/lang/Class;
    //   111: invokevirtual 179	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   114: new 181	java/lang/StringBuilder
    //   117: dup
    //   118: invokespecial 182	java/lang/StringBuilder:<init>	()V
    //   121: ldc 184
    //   123: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: aload 4
    //   128: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: ldc 190
    //   133: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   136: iload_2
    //   137: invokevirtual 193	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   140: ldc 195
    //   142: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   145: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   148: invokevirtual 205	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   151: invokespecial 208	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   154: invokespecial 211	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   157: astore 8
    //   159: iconst_0
    //   160: istore 9
    //   162: aload 8
    //   164: invokevirtual 214	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   167: astore 11
    //   169: aload 11
    //   171: ifnull +101 -> 272
    //   174: aload 7
    //   176: new 216	et/song/device/FastItem
    //   179: dup
    //   180: iload_2
    //   181: iload 9
    //   183: ldc 157
    //   185: aload 11
    //   187: invokestatic 163	et/song/tool/ETTool:HexStringToBytes	(Ljava/lang/String;)[B
    //   190: aload 6
    //   192: invokestatic 220	et/song/tool/ETTool:Dice	([B[B)D
    //   195: invokespecial 223	et/song/device/FastItem:<init>	(IILjava/lang/String;D)V
    //   198: invokeinterface 229 2 0
    //   203: pop
    //   204: iinc 9 1
    //   207: goto -45 -> 162
    //   210: ldc 231
    //   212: astore 4
    //   214: goto -134 -> 80
    //   217: ldc 233
    //   219: astore 4
    //   221: goto -141 -> 80
    //   224: ldc 235
    //   226: astore 4
    //   228: goto -148 -> 80
    //   231: ldc 237
    //   233: astore 4
    //   235: goto -155 -> 80
    //   238: ldc 239
    //   240: astore 4
    //   242: goto -162 -> 80
    //   245: ldc 241
    //   247: astore 4
    //   249: goto -169 -> 80
    //   252: ldc 243
    //   254: astore 4
    //   256: goto -176 -> 80
    //   259: astore 5
    //   261: aload 5
    //   263: invokevirtual 246	java/lang/Exception:printStackTrace	()V
    //   266: aconst_null
    //   267: astore 6
    //   269: goto -179 -> 90
    //   272: aload 8
    //   274: invokevirtual 249	java/io/BufferedReader:close	()V
    //   277: aload 7
    //   279: new 251	et/song/device/FastComparator
    //   282: dup
    //   283: invokespecial 252	et/song/device/FastComparator:<init>	()V
    //   286: invokestatic 258	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   289: aload 7
    //   291: areturn
    //   292: astore 10
    //   294: aload 10
    //   296: invokevirtual 246	java/lang/Exception:printStackTrace	()V
    //   299: aload 7
    //   301: iconst_0
    //   302: iconst_1
    //   303: invokeinterface 262 3 0
    //   308: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   80	86	259	java/lang/Exception
    //   162	169	292	java/lang/Exception
    //   174	204	292	java/lang/Exception
    //   272	289	292	java/lang/Exception
  }

  private void init()
  {
    this.index = getIntent().getIntExtra("index", -1);
    this.type = getIntent().getIntExtra("type", -1);
    this.group = getIntent().getIntExtra("group", -1);
    this.mName = getIntent().getStringExtra("name");
    this.cmd = new CmdDateBussiness(this, "0000");
    learn();
    setListener();
    if ((this.type == 8192) || (this.type == 16384))
      longToast(2131099955);
  }

  private void learn()
  {
    this.isRecDataOk = false;
    if (DeviceListActivity.isOnePointFive)
    {
      Dvc localDvc = (Dvc)new RoomBusiness(this).getData("dvcInRoom", Dvc.class);
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
      StringUtils.btye2Str(arrayOfByte);
      XlinkAgent localXlinkAgent2 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent2.sendPipeData(DeviceManage.getxDevice(), arrayOfByte, new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
    while (true)
    {
      new Handler().postDelayed(new Runnable()
      {
        public void run()
        {
          if (!AtLearnActivity.this.isRecDataOk)
          {
            AtLearnActivity.this.learn_again.setVisibility(0);
            AtLearnActivity.this.info1.setVisibility(8);
            AtLearnActivity.this.info2.setVisibility(8);
            AtLearnActivity.this.info3.setVisibility(8);
            AtLearnActivity.this.info10.setVisibility(0);
          }
        }
      }
      , 10000L);
      return;
      XlinkAgent localXlinkAgent1 = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent1.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiLearn(), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
    }
  }

  private void nextTest()
  {
    if (this.currentTestYkPosi < -1 + this.data.size())
    {
      this.currentTestYkPosi = (1 + this.currentTestYkPosi);
      testYaokong((FastItem)this.data.get(this.currentTestYkPosi), this.type, this.index);
      this.info6.setText(getString(2131100425) + "( " + (1 + this.currentTestYkPosi) + "/" + this.data.size() + " )");
      System.out.println("currentTestYkPosi : " + this.currentTestYkPosi);
    }
    this.save.setVisibility(0);
  }

  private void setListener()
  {
    this.xlinkNetListener = new XlinkNetListener()
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
        String str1;
        if (!AtLearnActivity.this.isReceivePipeData)
        {
          str1 = StringUtils.btye2Str(paramArrayOfByte);
          System.out.println("onRecvPipeData      " + str1);
          if (!DeviceListActivity.isOnePointFive)
            break label400;
          int j = Integer.parseInt(str1.substring(18, 20), 16);
          int k = Integer.parseInt(str1.substring(20, 22), 16);
          if (str1.length() > 38)
          {
            AtLearnActivity.access$102(AtLearnActivity.this, true);
            int m = Integer.parseInt(str1.substring(26, 28), 16);
            if ((j == 148) && (m == 2))
            {
              AtLearnActivity.access$002(AtLearnActivity.this, true);
              AtLearnActivity.this.data.clear();
              (k - 6);
              String str3 = str1.substring(28, -8 + str1.length());
              AtLearnActivity.this.data.addAll(AtLearnActivity.this.getYaokongList(AtLearnActivity.this.type, AtLearnActivity.this.index, str3));
              System.out.println("M1    hongwai    " + AtLearnActivity.this.type + "     " + AtLearnActivity.this.index);
              AtLearnActivity.this.info2.setVisibility(8);
              AtLearnActivity.this.info3.setVisibility(8);
              AtLearnActivity.this.iv_center.setVisibility(8);
              AtLearnActivity.this.info4.setVisibility(0);
              AtLearnActivity.this.info5.setVisibility(0);
              AtLearnActivity.this.info6.setVisibility(0);
              AtLearnActivity.this.last.setVisibility(0);
              AtLearnActivity.this.next.setVisibility(0);
              AtLearnActivity.this.on.setVisibility(0);
              AtLearnActivity.this.info10.setVisibility(0);
              AtLearnActivity.this.info1.setText(2131100133);
              AtLearnActivity.this.info10.setText(2131100134);
              AtLearnActivity.this.nextTest();
            }
          }
        }
        while (true)
        {
          return;
          try
          {
            label400: if ((str1.indexOf("66") != -1) || (str1.indexOf("BB") != -1))
              continue;
            AtLearnActivity.access$102(AtLearnActivity.this, true);
            AtLearnActivity.this.data.clear();
            String str2 = StringUtils.btye2Str(AtLearnActivity.this.subZero(paramArrayOfByte));
            AtLearnActivity.this.data.addAll(AtLearnActivity.this.getYaokongList(AtLearnActivity.this.type, AtLearnActivity.this.index, str2));
            System.out.println("M1    hongwai    " + AtLearnActivity.this.type + "     " + AtLearnActivity.this.index);
            AtLearnActivity.this.info2.setVisibility(8);
            AtLearnActivity.this.info3.setVisibility(8);
            AtLearnActivity.this.iv_center.setVisibility(8);
            AtLearnActivity.this.info4.setVisibility(0);
            AtLearnActivity.this.info5.setVisibility(0);
            AtLearnActivity.this.info6.setVisibility(0);
            AtLearnActivity.this.last.setVisibility(0);
            AtLearnActivity.this.next.setVisibility(0);
            AtLearnActivity.this.on.setVisibility(0);
            AtLearnActivity.this.info10.setVisibility(0);
            AtLearnActivity.this.info1.setText(2131100133);
            AtLearnActivity.this.info10.setText(2131100134);
            AtLearnActivity.this.nextTest();
            for (int i = 0; i < AtLearnActivity.this.data.size(); i++)
              System.out.println("M1    FastItem = " + ((FastItem)AtLearnActivity.this.data.get(i)).col);
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      }

      public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
      }

      public void onStart(int paramInt)
      {
      }
    };
    XlinkAgent.getInstance().addXlinkListener(this.xlinkNetListener);
    this.on.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    this.on.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
      {
        if (paramMotionEvent.getAction() == 0)
          AtLearnActivity.this.handler.post(AtLearnActivity.this.runnable);
        if (paramMotionEvent.getAction() == 1)
          AtLearnActivity.this.handler.removeCallbacks(AtLearnActivity.this.runnable);
        return false;
      }
    });
  }

  private void testYaokong(FastItem paramFastItem, int paramInt1, int paramInt2)
  {
    this.mIR = ETIR.Builder(paramInt1);
    this.mCol = paramFastItem.col;
    int i = 0;
    switch (paramInt1)
    {
    default:
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
        byte[] arrayOfByte = this.mIR.Search(paramInt2, this.mCol, i);
        if ((i == 49153) && (((AIR)this.mIR).GetPower() == 0))
          arrayOfByte = this.mIR.Search(paramInt2, this.mCol, i);
        if (arrayOfByte == null)
          continue;
        sendCmd(arrayOfByte);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public void again(View paramView)
  {
    this.learn_again.setVisibility(8);
    this.info1.setVisibility(0);
    this.info2.setVisibility(0);
    this.info3.setVisibility(0);
    this.info10.setVisibility(8);
    learn();
  }

  public void cancel(View paramView)
  {
  }

  public void handCfg(View paramView)
  {
    setYaokongNameAndType(this.mName, this.type, this.index);
    saveYaokong();
    int i = this.type;
    Intent localIntent = null;
    switch (i)
    {
    case 24576:
    case 32768:
    case 57344:
    default:
    case 8192:
    case 8448:
    case 16384:
    case 40960:
    case 49152:
    }
    while (true)
    {
      localIntent.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      startActivity(localIntent);
      setResult(this.saveYkOk);
      this.isReceivePipeData = false;
      finish();
      return;
      localIntent = new Intent(this, AtNewTv.class);
      localIntent.putExtra("tvType", "tv");
      continue;
      localIntent = new Intent(this, AtNewTv.class);
      localIntent.putExtra("tvType", "iptv");
      continue;
      localIntent = new Intent(this, AtNewTv.class);
      localIntent.putExtra("tvType", "tvbox");
      continue;
      localIntent = new Intent(this, AtProjecter.class);
      continue;
      localIntent = new Intent(this, AtAirConActivity.class);
    }
  }

  public void lastTest(View paramView)
  {
    if (this.currentTestYkPosi > 0)
    {
      this.currentTestYkPosi = (-1 + this.currentTestYkPosi);
      this.info6.setText(getString(2131100425) + "( " + (1 + this.currentTestYkPosi) + "/" + this.data.size() + " )");
      testYaokong((FastItem)this.data.get(this.currentTestYkPosi), this.type, this.index);
      System.out.println("currentTestYkPosi : " + this.currentTestYkPosi);
    }
    this.save.setVisibility(0);
  }

  public void nextTest(View paramView)
  {
    nextTest();
  }

  public void no(View paramView)
  {
    this.currentTestYkPosi = 0;
    this.isRecDataOk = false;
    this.isReceivePipeData = false;
    this.save.setVisibility(8);
    this.learn_again.setVisibility(0);
    this.info1.setVisibility(8);
    this.info2.setVisibility(8);
    this.info3.setVisibility(8);
    this.info10.setVisibility(0);
    this.info10.setText(2131100131);
    this.iv_center.setVisibility(0);
    this.info4.setVisibility(8);
    this.info5.setVisibility(8);
    this.info6.setVisibility(8);
    this.last.setVisibility(8);
    this.next.setVisibility(8);
    this.on.setVisibility(8);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968680);
    ButterKnife.bind(this);
    init();
    setTitleView();
  }

  protected void onDestroy()
  {
    super.onDestroy();
    XlinkAgent.getInstance().removeListener(this.xlinkNetListener);
  }

  void saveMyYaokong(String paramString, int paramInt1, int paramInt2)
  {
    ETPage.getInstance(this).Load(ETDB.getInstance(this));
    ETGroup localETGroup1 = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup1.SetType(et.song.global.ETGlobal.mGroupTypes[0]);
    localETGroup1.SetRes(0);
    localETGroup1.SetID(1);
    if (localETGroup1.GetCount() == 0)
      localETGroup1.Inster(ETDB.getInstance(this));
    switch (this.type)
    {
    default:
      setResult(this.saveYkOk);
      finish();
      return;
    case 8192:
      ETDeviceTV localETDeviceTV = new ETDeviceTV(paramInt1, paramInt2);
      localETDeviceTV.SetName(paramString);
      localETDeviceTV.SetType(8192);
      localETDeviceTV.SetRes(0);
      localETDeviceTV.SetGID(localETGroup1.GetID());
      localETDeviceTV.Inster(ETDB.getInstance(this));
      Intent localIntent7 = new Intent(this, AtNewTv.class);
      localETGroup1.Load(ETDB.getInstance(this));
      if (localETGroup1.GetCount() == 0)
        localIntent7.putExtra("index", 0);
      while (true)
      {
        localIntent7.putExtra("tvType", "tv");
        startActivity(localIntent7);
        break;
        localIntent7.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 16384:
      ETDeviceSTB localETDeviceSTB = new ETDeviceSTB(paramInt1, paramInt2);
      localETDeviceSTB.SetName(paramString);
      localETDeviceSTB.SetType(16384);
      localETDeviceSTB.SetRes(2);
      localETDeviceSTB.SetGID(localETGroup1.GetID());
      localETDeviceSTB.Inster(ETDB.getInstance(this));
      Intent localIntent6 = new Intent(this, AtNewTv.class);
      if (localETGroup1.GetCount() == 0)
        localIntent6.putExtra("index", 0);
      while (true)
      {
        localIntent6.putExtra("tvType", "tvbox");
        startActivity(localIntent6);
        break;
        localIntent6.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 24576:
      ETDeviceDVD localETDeviceDVD = new ETDeviceDVD(paramInt1, paramInt2);
      localETDeviceDVD.SetName(paramString);
      localETDeviceDVD.SetType(24576);
      localETDeviceDVD.SetRes(3);
      localETDeviceDVD.SetGID(localETGroup1.GetID());
      localETDeviceDVD.Inster(ETDB.getInstance(this));
      Intent localIntent5 = new Intent(this, AtLearnActivity.class);
      if (localETGroup1.GetCount() == 0)
        localIntent5.putExtra("index", 0);
      while (true)
      {
        startActivity(localIntent5);
        break;
        localIntent5.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 40960:
      ETDevicePJT localETDevicePJT = new ETDevicePJT(paramInt1, paramInt2);
      localETDevicePJT.SetName(paramString);
      localETDevicePJT.SetType(40960);
      localETDevicePJT.SetRes(5);
      localETDevicePJT.SetGID(localETGroup1.GetID());
      localETDevicePJT.Inster(ETDB.getInstance(this));
      Intent localIntent4 = new Intent(this, AtProjecter.class);
      if (localETGroup1.GetCount() == 0)
        localIntent4.putExtra("index", 0);
      while (true)
      {
        startActivity(localIntent4);
        break;
        localIntent4.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 32768:
      ETDeviceFANS localETDeviceFANS = new ETDeviceFANS(paramInt1, paramInt2);
      localETDeviceFANS.SetName(paramString);
      localETDeviceFANS.SetType(32768);
      localETDeviceFANS.SetRes(4);
      localETDeviceFANS.SetGID(localETGroup1.GetID());
      localETDeviceFANS.Inster(ETDB.getInstance(this));
      Intent localIntent3 = new Intent(this, AtFan.class);
      if (localETGroup1.GetCount() == 0)
        localIntent3.putExtra("index", 0);
      while (true)
      {
        startActivity(localIntent3);
        break;
        localIntent3.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 8448:
      ETDeviceIPTV localETDeviceIPTV = new ETDeviceIPTV(paramInt1, paramInt2);
      localETDeviceIPTV.SetName(paramString);
      localETDeviceIPTV.SetType(8448);
      localETDeviceIPTV.SetRes(1);
      localETDeviceIPTV.SetGID(localETGroup1.GetID());
      localETDeviceIPTV.Inster(ETDB.getInstance(this));
      Intent localIntent2 = new Intent(this, AtNewTv.class);
      if (localETGroup1.GetCount() == 0)
        localIntent2.putExtra("index", 0);
      while (true)
      {
        localIntent2.putExtra("tvType", "iptv");
        startActivity(localIntent2);
        break;
        localIntent2.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
      }
    case 49152:
    }
    ETDeviceAIR localETDeviceAIR = new ETDeviceAIR(paramInt1, paramInt2);
    localETDeviceAIR.SetName(paramString);
    localETDeviceAIR.SetType(49152);
    localETDeviceAIR.SetRes(7);
    localETDeviceAIR.SetGID(localETGroup1.GetID());
    ((ETDeviceAIR)localETDeviceAIR).SetTemp(((AIR)this.mIR).GetTemp());
    ((ETDeviceAIR)localETDeviceAIR).SetMode(((AIR)this.mIR).GetMode());
    ((ETDeviceAIR)localETDeviceAIR).SetPower(((AIR)this.mIR).GetPower());
    ((ETDeviceAIR)localETDeviceAIR).SetWindRate(((AIR)this.mIR).GetWindRate());
    ((ETDeviceAIR)localETDeviceAIR).SetWindDir(((AIR)this.mIR).GetWindDir());
    ((ETDeviceAIR)localETDeviceAIR).SetAutoWindDir(((AIR)this.mIR).GetAutoWindDir());
    localETDeviceAIR.Inster(ETDB.getInstance(this));
    ETGroup localETGroup2 = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup2.Load(ETDB.getInstance(this));
    Intent localIntent1 = new Intent(this, AtAirConActivity.class);
    if (localETGroup2.GetCount() == 0)
      localIntent1.putExtra("index", 0);
    while (true)
    {
      startActivity(localIntent1);
      break;
      localIntent1.putExtra("index", getIntent().getIntExtra("yaoKongAllCount", -1));
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131100536);
  }

  public byte[] subZero(byte[] paramArrayOfByte)
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

  public void yes(View paramView)
  {
    int i = 10;
    this.save.setVisibility(8);
    View localView = LayoutInflater.from(this).inflate(2130968729, null);
    EditText localEditText = (EditText)localView.findViewById(2131559104);
    if (this.mName.length() > i);
    while (true)
    {
      localEditText.setText(this.mName.substring(0, i));
      AlertDialog localAlertDialog = new AlertDialog.Builder(this).setView(localView).setPositiveButton(2131100418, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramDialogInterface, int paramInt)
        {
          AtLearnActivity.this.saveMyYaokong(AtLearnActivity.this.mName, AtLearnActivity.this.index, AtLearnActivity.this.mCol);
        }
      }).create();
      localAlertDialog.setTitle(2131100417);
      localAlertDialog.show();
      return;
      i = this.mName.length();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtLearnActivity
 * JD-Core Version:    0.6.0
 */