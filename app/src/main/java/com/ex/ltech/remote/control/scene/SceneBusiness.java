package com.ex.ltech.remote.control.scene;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Handler;
import android.os.SystemClock;
import android.widget.Toast;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.remote.control.vo.YkSceneVo;
import com.ex.ltech.remote.control.vo.YkVo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETDeviceDVD;
import et.song.etclass.ETDeviceFANS;
import et.song.etclass.ETDeviceIPTV;
import et.song.etclass.ETDevicePJT;
import et.song.etclass.ETDeviceSTB;
import et.song.etclass.ETDeviceTV;
import et.song.etclass.ETGroup;
import et.song.etclass.ETPage;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.listener.SendPipeListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SceneBusiness extends BaseBusiness
{
  String atSceneGvItHeight = "atSceneGvItHeight";
  String atSceneGvItWidth = "atSceneGvItWidth";
  Context c;
  public CmdDateBussiness cmd = new CmdDateBussiness(this.c, "0000");
  List<YkSceneVo> data = null;
  Handler handler = new Handler()
  {
  };
  ETGroup mGroup;
  public int saveYkOk = 1000;
  List<YkVo> ykVos = new ArrayList();

  public SceneBusiness(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
    this.mGroup = ((ETGroup)ETPage.getInstance(paramContext).GetItem(0));
  }

  private void loopSend()
    throws Exception
  {
    YkVo localYkVo = (YkVo)this.ykVos.get(0);
    String str1 = localYkVo.getType();
    int i = -1;
    switch (str1.hashCode())
    {
    default:
      arrayOfByte = null;
      switch (i)
      {
      default:
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      }
    case 96586:
    case 3714:
    case 110741513:
    case 3239401:
    case -894830916:
    case 101139:
    case 99858:
    }
    while (true)
    {
      if (this.ykVos.size() > 1)
      {
        Handler localHandler = this.handler;
        1 local1 = new Runnable()
        {
          public void run()
          {
            try
            {
              SceneBusiness.this.ykVos.remove(0);
              SceneBusiness.this.loopSend();
              return;
            }
            catch (Exception localException)
            {
              localException.printStackTrace();
            }
          }
        };
        localHandler.postDelayed(local1, 1000 * localYkVo.getSpaceTime());
      }
      return;
      if (!str1.equals("air"))
        break;
      i = 0;
      break;
      if (!str1.equals("tv"))
        break;
      i = 1;
      break;
      if (!str1.equals("tvbox"))
        break;
      i = 2;
      break;
      if (!str1.equals("iptv"))
        break;
      i = 3;
      break;
      if (!str1.equals("projector"))
        break;
      i = 4;
      break;
      if (!str1.equals("fan"))
        break;
      i = 5;
      break;
      if (!str1.equals("dvd"))
        break;
      i = 6;
      break;
      ETDeviceAIR localETDeviceAIR = (ETDeviceAIR)this.mGroup.GetItem(localYkVo.getId());
      localETDeviceAIR.Load(ETDB.getInstance(this.c));
      if (!localYkVo.getStatus().equals(this.c.getString(2131100226)))
        try
        {
          localETDeviceAIR.GetKeyValue(49153);
          localETDeviceAIR.GetKeyValue(49153);
          String[] arrayOfString = localYkVo.getStatus().split(",");
          localETDeviceAIR.SetTemp(Byte.parseByte(arrayOfString[1].substring(0, arrayOfString[1].indexOf("℃"))));
          localETDeviceAIR.GetKeyValue(49165);
          String str2 = this.c.getString(2131099860);
          String str3 = this.c.getString(2131099861);
          String str4 = this.c.getString(2131099862);
          String str5 = this.c.getString(2131099863);
          String str6 = this.c.getString(2131099864);
          if (arrayOfString[0].equals(str2))
            localETDeviceAIR.SetMode(1);
          if (arrayOfString[0].equals(str3))
            localETDeviceAIR.SetMode(2);
          if (arrayOfString[0].equals(str4))
            localETDeviceAIR.SetMode(3);
          if (arrayOfString[0].equals(str5))
            localETDeviceAIR.SetMode(4);
          if (arrayOfString[0].equals(str6))
            localETDeviceAIR.SetMode(5);
          arrayOfByte = localETDeviceAIR.GetKeyValue(49155);
          sendCmd(arrayOfByte);
          System.out.println("Tuhwerluifaweoiugyewrfg" + StringUtils.btye2Str(arrayOfByte));
          localETDeviceAIR.GetKeyValue(49153);
        }
        catch (Exception localException)
        {
          while (true)
          {
            localETDeviceAIR.GetKeyValue(49153);
            arrayOfByte = localETDeviceAIR.GetKeyValue(49153);
            sendCmd(arrayOfByte);
            localException.printStackTrace();
          }
        }
      arrayOfByte = localETDeviceAIR.GetKeyValue(49153);
      sendCmd(arrayOfByte);
    }
    ETDeviceTV localETDeviceTV = (ETDeviceTV)this.mGroup.GetItem(localYkVo.getId());
    localETDeviceTV.Load(ETDB.getInstance(this.c));
    if ((localYkVo.getStatus().equals(this.c.getString(R.string.on))) || (localYkVo.getStatus().equals(this.c.getString(2131100226))))
    {
      boolean bool3 = localYkVo.getStatus().equals(this.c.getString(R.string.on));
      arrayOfByte = null;
      if (bool3)
        arrayOfByte = localETDeviceTV.GetKeyValue(8203);
      if (localYkVo.getStatus().equals(this.c.getString(2131100226)))
      {
        localETDeviceTV.GetKeyValue(8203);
        arrayOfByte = localETDeviceTV.GetKeyValue(8203);
      }
    }
    while (true)
    {
      sendCmd(arrayOfByte);
      break;
      int i3 = Integer.parseInt(localYkVo.getStatus().substring(0, localYkVo.getStatus().indexOf(this.c.getString(2131099929))));
      arrayOfByte = null;
      if (i3 > 99)
      {
        sendCmd(localETDeviceTV.GetKeyValue(8207));
        SystemClock.sleep(500L);
        sendCmd(localETDeviceTV.GetKeyValue(8227));
        SystemClock.sleep(500L);
        arrayOfByte = localETDeviceTV.GetKeyValue(8227);
      }
      if ((i3 < 100) && (i3 > 9))
      {
        int i4 = i3 / 10;
        int i5 = i3 - i4 * 10;
        switch (i4)
        {
        default:
          label968: sendCmd(arrayOfByte);
          SystemClock.sleep(500L);
          switch (i5)
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
      while (i3 < 10)
        switch (i3)
        {
        default:
          break;
        case 0:
          arrayOfByte = localETDeviceTV.GetKeyValue(8227);
          break;
          arrayOfByte = localETDeviceTV.GetKeyValue(8227);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8207);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8209);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8211);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8213);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8215);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8217);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8219);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8221);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8223);
          break label968;
          arrayOfByte = localETDeviceTV.GetKeyValue(8227);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8207);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8209);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8211);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8213);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8215);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8217);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8219);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8221);
          continue;
          arrayOfByte = localETDeviceTV.GetKeyValue(8223);
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
      arrayOfByte = localETDeviceTV.GetKeyValue(8207);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8209);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8211);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8213);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8215);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8217);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8219);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8221);
      continue;
      arrayOfByte = localETDeviceTV.GetKeyValue(8223);
    }
    ETDeviceSTB localETDeviceSTB = (ETDeviceSTB)this.mGroup.GetItem(localYkVo.getId());
    localETDeviceSTB.Load(ETDB.getInstance(this.c));
    if ((localYkVo.getStatus().equals(this.c.getString(R.string.on))) || (localYkVo.getStatus().equals(this.c.getString(2131100226))))
    {
      boolean bool2 = localYkVo.getStatus().equals(this.c.getString(R.string.on));
      arrayOfByte = null;
      if (bool2)
        arrayOfByte = localETDeviceSTB.GetKeyValue(16385);
      if (localYkVo.getStatus().equals(this.c.getString(2131100226)))
      {
        localETDeviceSTB.GetKeyValue(16385);
        arrayOfByte = localETDeviceSTB.GetKeyValue(16385);
      }
    }
    while (true)
    {
      sendCmd(arrayOfByte);
      break;
      int n = Integer.parseInt(localYkVo.getStatus().substring(0, localYkVo.getStatus().indexOf(this.c.getString(2131099929))));
      arrayOfByte = null;
      if (n > 99)
      {
        sendCmd(localETDeviceSTB.GetKeyValue(16387));
        SystemClock.sleep(500L);
        sendCmd(localETDeviceSTB.GetKeyValue(16407));
        SystemClock.sleep(500L);
        arrayOfByte = localETDeviceSTB.GetKeyValue(16407);
      }
      if ((n < 100) && (n > 9))
      {
        int i1 = n / 10;
        int i2 = n - i1 * 10;
        switch (i1)
        {
        default:
          label1816: sendCmd(arrayOfByte);
          SystemClock.sleep(500L);
          switch (i2)
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
      while (n < 10)
        switch (n)
        {
        default:
          break;
        case 0:
          arrayOfByte = localETDeviceSTB.GetKeyValue(16407);
          break;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16407);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16387);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16389);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16391);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16393);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16395);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16397);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16399);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16401);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16403);
          break label1816;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16407);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16387);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16389);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16391);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16393);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16395);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16397);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16399);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16401);
          continue;
          arrayOfByte = localETDeviceSTB.GetKeyValue(16403);
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
      arrayOfByte = localETDeviceSTB.GetKeyValue(16387);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16389);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16391);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16393);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16395);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16397);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16399);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16401);
      continue;
      arrayOfByte = localETDeviceSTB.GetKeyValue(16403);
    }
    ETDeviceIPTV localETDeviceIPTV = (ETDeviceIPTV)this.mGroup.GetItem(localYkVo.getId());
    localETDeviceIPTV.Load(ETDB.getInstance(this.c));
    if ((localYkVo.getStatus().equals(this.c.getString(R.string.on))) || (localYkVo.getStatus().equals(this.c.getString(2131100226))))
    {
      boolean bool1 = localYkVo.getStatus().equals(this.c.getString(R.string.on));
      arrayOfByte = null;
      if (bool1)
        arrayOfByte = localETDeviceIPTV.GetKeyValue(8449);
      if (localYkVo.getStatus().equals(this.c.getString(2131100226)))
      {
        localETDeviceIPTV.GetKeyValue(8449);
        arrayOfByte = localETDeviceIPTV.GetKeyValue(8449);
      }
    }
    while (true)
    {
      sendCmd(arrayOfByte);
      break;
      int j = Integer.parseInt(localYkVo.getStatus().substring(0, localYkVo.getStatus().indexOf(this.c.getString(2131099929))));
      arrayOfByte = null;
      if (j > 99)
      {
        sendCmd(localETDeviceIPTV.GetKeyValue(8473));
        SystemClock.sleep(500L);
        sendCmd(localETDeviceIPTV.GetKeyValue(8491));
        SystemClock.sleep(500L);
        arrayOfByte = localETDeviceIPTV.GetKeyValue(8491);
      }
      if ((j < 100) && (j > 9))
      {
        int k = j / 10;
        int m = j - k * 10;
        switch (k)
        {
        default:
          label2664: sendCmd(arrayOfByte);
          SystemClock.sleep(500L);
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
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8491);
          break;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8491);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8473);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8475);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8477);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8479);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8481);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8483);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8485);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8487);
          break label2664;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8489);
          break label2664;
          localETDeviceIPTV.GetKeyValue(8491);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8473);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8475);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8477);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8479);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8481);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8483);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8485);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8487);
          continue;
          arrayOfByte = localETDeviceIPTV.GetKeyValue(8489);
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
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8473);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8475);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8477);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8479);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8481);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8483);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8485);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8487);
      continue;
      arrayOfByte = localETDeviceIPTV.GetKeyValue(8489);
    }
    ETDevicePJT localETDevicePJT = (ETDevicePJT)this.mGroup.GetItem(localYkVo.getId());
    localETDevicePJT.Load(ETDB.getInstance(this.c));
    if (localYkVo.getStatus().equals(this.c.getString(R.string.on)));
    for (byte[] arrayOfByte = localETDevicePJT.GetKeyValue(40961); ; arrayOfByte = localETDevicePJT.GetKeyValue(40963))
    {
      sendCmd(arrayOfByte);
      break;
    }
    ETDeviceFANS localETDeviceFANS = (ETDeviceFANS)this.mGroup.GetItem(localYkVo.getId());
    localETDeviceFANS.Load(ETDB.getInstance(this.c));
    if (localYkVo.getStatus().equals(this.c.getString(R.string.on)))
      localETDeviceFANS.GetKeyValue(32769);
    for (arrayOfByte = localETDeviceFANS.GetKeyValue(32769); ; arrayOfByte = localETDeviceFANS.GetKeyValue(32769))
    {
      sendCmd(arrayOfByte);
      break;
    }
    ETDeviceDVD localETDeviceDVD = (ETDeviceDVD)this.mGroup.GetItem(localYkVo.getId());
    localETDeviceDVD.Load(ETDB.getInstance(this.c));
    if (localYkVo.getStatus().equals(this.c.getString(R.string.on)))
      localETDeviceDVD.GetKeyValue(24587);
    for (arrayOfByte = localETDeviceDVD.GetKeyValue(24587); ; arrayOfByte = localETDeviceDVD.GetKeyValue(24587))
    {
      sendCmd(arrayOfByte);
      break;
    }
  }

  public List<YkSceneVo> getData()
  {
    if (this.data == null);
    try
    {
      this.data = ((List)this.gs.fromJson(this.getter.getString(DeviceListActivity.deviceMacAddress + "yaoKongScenes", ""), new TypeToken()
      {
      }
      .getType()));
      label66: if (this.data == null)
        initDefaultData();
      return this.data;
    }
    catch (JsonSyntaxException localJsonSyntaxException)
    {
      break label66;
    }
  }

  public int getGvItHeight()
  {
    return this.getter.getInt(this.atSceneGvItHeight, 0);
  }

  public int getGvItWidth()
  {
    return this.getter.getInt(this.atSceneGvItWidth, 0);
  }

  public void hideEdit()
  {
    for (int i = 0; i < this.data.size(); i++)
    {
      YkSceneVo localYkSceneVo = (YkSceneVo)this.data.get(i);
      localYkSceneVo.setIsEdit(false);
      this.data.remove(i);
      this.data.add(i, localYkSceneVo);
    }
    saveData(this.data);
  }

  public void initDefaultData()
  {
    this.data = new ArrayList();
    YkSceneVo localYkSceneVo1 = new YkSceneVo();
    localYkSceneVo1.setPicRes(2130903840);
    localYkSceneVo1.setName(this.ct.getString(2131100531));
    localYkSceneVo1.setBgCol(this.ct.getResources().getColor(2131492998));
    this.data.add(localYkSceneVo1);
    YkSceneVo localYkSceneVo2 = new YkSceneVo();
    localYkSceneVo2.setPicRes(2130903841);
    localYkSceneVo2.setBgCol(this.ct.getResources().getColor(2131492999));
    localYkSceneVo2.setName(this.ct.getString(2131100532));
    this.data.add(localYkSceneVo2);
    YkSceneVo localYkSceneVo3 = new YkSceneVo();
    localYkSceneVo3.setPicRes(2130903842);
    localYkSceneVo3.setBgCol(this.ct.getResources().getColor(2131493000));
    localYkSceneVo3.setName(this.ct.getString(2131100533));
    this.data.add(localYkSceneVo3);
    YkSceneVo localYkSceneVo4 = new YkSceneVo();
    localYkSceneVo4.setPicRes(2130903843);
    localYkSceneVo4.setBgCol(this.ct.getResources().getColor(2131493001));
    localYkSceneVo4.setName(this.ct.getString(2131100534));
    this.data.add(localYkSceneVo4);
  }

  public void onAdd(int paramInt)
  {
    Intent localIntent = new Intent(this.ct, AtYkNewSceneActivity.class);
    localIntent.putExtra("scenePosi", paramInt);
    this.ct.startActivity(localIntent);
  }

  public void onEdit(int paramInt, String paramString)
  {
    ((YkSceneVo)this.data.get(paramInt));
    Intent localIntent = new Intent(this.ct, AtYkNewSceneActivity.class);
    localIntent.putExtra("scenePosi", paramInt);
    localIntent.putExtra("sceneName", paramString);
    this.ct.startActivity(localIntent);
  }

  public void onItemClick(int paramInt)
  {
    YkSceneVo localYkSceneVo = (YkSceneVo)getData().get(paramInt);
    if (localYkSceneVo.getYks() == null)
      Toast.makeText(this.ct, 2131100012, 0).show();
    do
      return;
    while (localYkSceneVo.isEdit());
    this.ykVos.clear();
    this.ykVos.addAll(localYkSceneVo.getYks());
    try
    {
      loopSend();
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onItemLongClick(int paramInt)
  {
    this.data.remove(paramInt);
    saveData(this.data);
  }

  public void onRename(int paramInt, String paramString)
  {
    YkSceneVo localYkSceneVo = (YkSceneVo)getData().get(paramInt);
    localYkSceneVo.setName(paramString);
    this.data.remove(paramInt);
    this.data.add(paramInt, localYkSceneVo);
    saveData(this.data);
  }

  public void resetData()
  {
    this.data = null;
  }

  public void saveData(List<YkSceneVo> paramList)
  {
    this.setter.putValue(DeviceListActivity.deviceMacAddress + "yaoKongScenes", new Gson().toJson(paramList));
  }

  public void saveGvItWidthHeight(int paramInt1, int paramInt2)
  {
    this.setter.putValue(this.atSceneGvItWidth, Integer.valueOf(paramInt1));
    this.setter.putValue(this.atSceneGvItHeight, Integer.valueOf(paramInt2));
  }

  public void sendCmd(byte[] paramArrayOfByte)
  {
    try
    {
      Thread.sleep(400L);
      XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), this.cmd.hongWaiTest(paramArrayOfByte), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      while (true)
        localInterruptedException.printStackTrace();
    }
  }

  public void showEdit()
  {
    int i = 0;
    if (i < this.data.size())
    {
      YkSceneVo localYkSceneVo = (YkSceneVo)this.data.get(i);
      if (!localYkSceneVo.isEdit());
      for (boolean bool = true; ; bool = false)
      {
        localYkSceneVo.setIsEdit(bool);
        this.data.remove(i);
        this.data.add(i, localYkSceneVo);
        i++;
        break;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.SceneBusiness
 * JD-Core Version:    0.6.0
 */