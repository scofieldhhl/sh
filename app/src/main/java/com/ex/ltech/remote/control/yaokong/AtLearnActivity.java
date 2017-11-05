package com.ex.ltech.remote.control.yaokong;

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
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.atYaoKongs.AtAirConActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtDvdActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtFanActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity;
import com.ex.ltech.remote.control.atYaoKongs.AtProjectionActivity;
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
    //   5: lookupswitch	default:+75->80, 8192:+228->233, 8448:+235->240, 16384:+242->247, 24576:+249->254, 32768:+263->268, 40960:+256->261, 49152:+270->275, 57344:+75->80
    //   81: invokestatic 163	et/song/tool/ETTool:HexStringToBytes	(Ljava/lang/String;)[B
    //   84: pop
    //   85: new 74	java/util/ArrayList
    //   88: dup
    //   89: invokespecial 75	java/util/ArrayList:<init>	()V
    //   92: astore 6
    //   94: new 165	java/io/BufferedReader
    //   97: dup
    //   98: new 167	java/io/InputStreamReader
    //   101: dup
    //   102: aload_0
    //   103: invokevirtual 173	java/lang/Object:getClass	()Ljava/lang/Class;
    //   106: invokevirtual 179	java/lang/Class:getClassLoader	()Ljava/lang/ClassLoader;
    //   109: new 181	java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial 182	java/lang/StringBuilder:<init>	()V
    //   116: ldc 184
    //   118: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aload 4
    //   123: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: ldc 190
    //   128: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   131: iload_2
    //   132: invokevirtual 193	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   135: ldc 195
    //   137: invokevirtual 188	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   140: invokevirtual 199	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokevirtual 205	java/lang/ClassLoader:getResourceAsStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   146: invokespecial 208	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   149: invokespecial 211	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   152: astore 7
    //   154: new 213	com/ex/ltech/remote/control/Cosine
    //   157: dup
    //   158: invokespecial 214	com/ex/ltech/remote/control/Cosine:<init>	()V
    //   161: astore 8
    //   163: new 216	com/ex/ltech/remote/control/NGram
    //   166: iconst_2
    //   167: invokespecial 219	com/ex/ltech/remote/control/NGram:<init>	(I)V
    //   170: new 221	com/ex/ltech/remote/control/Levenshtein
    //   173: dup
    //   174: invokespecial 222	com/ex/ltech/remote/control/Levenshtein:<init>	()V
    //   177: pop
    //   178: iconst_0
    //   179: istore 10
    //   181: aload 7
    //   183: invokevirtual 225	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   186: astore 12
    //   188: aload 12
    //   190: ifnull +102 -> 292
    //   193: aload 12
    //   195: invokestatic 163	et/song/tool/ETTool:HexStringToBytes	(Ljava/lang/String;)[B
    //   198: pop
    //   199: aload 6
    //   201: new 227	et/song/device/FastItem
    //   204: dup
    //   205: iload_2
    //   206: iload 10
    //   208: ldc 157
    //   210: aload 8
    //   212: aload_3
    //   213: aload 12
    //   215: invokevirtual 231	com/ex/ltech/remote/control/Cosine:similarity	(Ljava/lang/String;Ljava/lang/String;)D
    //   218: invokespecial 234	et/song/device/FastItem:<init>	(IILjava/lang/String;D)V
    //   221: invokeinterface 240 2 0
    //   226: pop
    //   227: iinc 10 1
    //   230: goto -49 -> 181
    //   233: ldc 242
    //   235: astore 4
    //   237: goto -157 -> 80
    //   240: ldc 244
    //   242: astore 4
    //   244: goto -164 -> 80
    //   247: ldc 246
    //   249: astore 4
    //   251: goto -171 -> 80
    //   254: ldc 248
    //   256: astore 4
    //   258: goto -178 -> 80
    //   261: ldc 250
    //   263: astore 4
    //   265: goto -185 -> 80
    //   268: ldc 252
    //   270: astore 4
    //   272: goto -192 -> 80
    //   275: ldc 254
    //   277: astore 4
    //   279: goto -199 -> 80
    //   282: astore 5
    //   284: aload 5
    //   286: invokevirtual 257	java/lang/Exception:printStackTrace	()V
    //   289: goto -204 -> 85
    //   292: aload 7
    //   294: invokevirtual 260	java/io/BufferedReader:close	()V
    //   297: aload 6
    //   299: new 262	et/song/device/FastComparator
    //   302: dup
    //   303: invokespecial 263	et/song/device/FastComparator:<init>	()V
    //   306: invokestatic 269	java/util/Collections:sort	(Ljava/util/List;Ljava/util/Comparator;)V
    //   309: aload 6
    //   311: areturn
    //   312: astore 11
    //   314: aload 11
    //   316: invokevirtual 257	java/lang/Exception:printStackTrace	()V
    //   319: aload 6
    //   321: iconst_0
    //   322: iconst_1
    //   323: invokeinterface 273 3 0
    //   328: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   80	85	282	java/lang/Exception
    //   181	188	312	java/lang/Exception
    //   193	227	312	java/lang/Exception
    //   292	309	312	java/lang/Exception
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
            AtLearnActivity.this.learn_again.setVisibility(View.VISIBLE);
            AtLearnActivity.this.info1.setVisibility(View.GONE);
            AtLearnActivity.this.info2.setVisibility(View.GONE);
            AtLearnActivity.this.info3.setVisibility(View.GONE);
            AtLearnActivity.this.info10.setVisibility(View.VISIBLE);
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
    this.save.setVisibility(View.VISIBLE);
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
              AtLearnActivity.this.info2.setVisibility(View.GONE);
              AtLearnActivity.this.info3.setVisibility(View.GONE);
              AtLearnActivity.this.iv_center.setVisibility(View.GONE);
              AtLearnActivity.this.info4.setVisibility(View.VISIBLE);
              AtLearnActivity.this.info5.setVisibility(View.VISIBLE);
              AtLearnActivity.this.info6.setVisibility(View.VISIBLE);
              AtLearnActivity.this.last.setVisibility(View.VISIBLE);
              AtLearnActivity.this.next.setVisibility(View.VISIBLE);
              AtLearnActivity.this.on.setVisibility(View.VISIBLE);
              AtLearnActivity.this.info10.setVisibility(View.VISIBLE);
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
            AtLearnActivity.this.info2.setVisibility(View.GONE);
            AtLearnActivity.this.info3.setVisibility(View.GONE);
            AtLearnActivity.this.iv_center.setVisibility(View.GONE);
            AtLearnActivity.this.info4.setVisibility(View.VISIBLE);
            AtLearnActivity.this.info5.setVisibility(View.VISIBLE);
            AtLearnActivity.this.info6.setVisibility(View.VISIBLE);
            AtLearnActivity.this.last.setVisibility(View.VISIBLE);
            AtLearnActivity.this.next.setVisibility(View.VISIBLE);
            AtLearnActivity.this.on.setVisibility(View.VISIBLE);
            AtLearnActivity.this.info10.setVisibility(View.VISIBLE);
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
    this.learn_again.setVisibility(View.GONE);
    this.info1.setVisibility(View.VISIBLE);
    this.info2.setVisibility(View.VISIBLE);
    this.info3.setVisibility(View.VISIBLE);
    this.info10.setVisibility(View.GONE);
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
    case 57344:
    default:
    case 8192:
    case 8448:
    case 16384:
    case 24576:
    case 40960:
    case 32768:
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
      localIntent = new Intent(this, AtNewTvActivity.class);
      localIntent.putExtra("tvType", "tv");
      continue;
      localIntent = new Intent(this, AtNewTvActivity.class);
      localIntent.putExtra("tvType", "iptv");
      continue;
      localIntent = new Intent(this, AtNewTvActivity.class);
      localIntent.putExtra("tvType", "tvbox");
      continue;
      localIntent = new Intent(this, AtDvdActivity.class);
      continue;
      localIntent = new Intent(this, AtProjectionActivity.class);
      continue;
      localIntent = new Intent(this, AtFanActivity.class);
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
    this.save.setVisibility(View.VISIBLE);
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
    this.save.setVisibility(View.GONE);
    this.learn_again.setVisibility(View.VISIBLE);
    this.info1.setVisibility(View.GONE);
    this.info2.setVisibility(View.GONE);
    this.info3.setVisibility(View.GONE);
    this.info10.setVisibility(View.VISIBLE);
    this.info10.setText(2131100131);
    this.iv_center.setVisibility(View.VISIBLE);
    this.info4.setVisibility(View.GONE);
    this.info5.setVisibility(View.GONE);
    this.info6.setVisibility(View.GONE);
    this.last.setVisibility(View.GONE);
    this.next.setVisibility(View.GONE);
    this.on.setVisibility(View.GONE);
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
      Intent localIntent7 = new Intent(this, AtNewTvActivity.class);
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
      Intent localIntent6 = new Intent(this, AtNewTvActivity.class);
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
      Intent localIntent5 = new Intent(this, AtDvdActivity.class);
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
      Intent localIntent4 = new Intent(this, AtProjectionActivity.class);
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
      Intent localIntent3 = new Intent(this, AtFanActivity.class);
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
      Intent localIntent2 = new Intent(this, AtNewTvActivity.class);
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
    setMenuBackgroundRes(R.mipmap.back_ic);
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
    this.save.setVisibility(View.GONE);
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
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtLearnActivity
 * JD-Core Version:    0.6.0
 */