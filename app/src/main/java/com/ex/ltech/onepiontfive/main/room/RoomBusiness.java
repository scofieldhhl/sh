package com.ex.ltech.onepiontfive.main.room;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.DeviceStatusVo;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public class RoomBusiness extends MyBusiness
{
  public static final int ADD_DEVICES = 203;
  public static final int ADD_LIGHTS = 202;
  public static final int PHOTO_REQ_CODE = 1;
  private int DvcSeletedItemPosi = -1;
  private int DvcTagItemPosi = -1;
  private File bgFile;
  ArrayList<Integer> cmd = new ArrayList();
  private Context context;
  private int defaultBgRes = 2130903119;
  public String dragOutDvcId;
  ArrayList<Dvc> dvcVos = new ArrayList();
  private int groupInnerDvcSeletedItemPosi = -1;
  List<Dvc> groupInnerDvcVos = new ArrayList();
  private int groupOutsideDvcTagItemPosi = -1;
  private int groupPosi = -1;
  Handler handler = new Handler();
  private String lastReturnData = "";
  private int lastTimeClickInnerLightPosi = -1;
  private int lastTimeClickLightPosi = -1;
  List<Dvc> listviewDvcVos = new ArrayList();
  private int mLastDragPosi = -1;
  private String mac;
  int roomIndex;
  private String roomName = "";

  public RoomBusiness(Activity paramActivity, List<Dvc> paramList)
  {
    super(paramActivity);
    this.context = paramActivity;
    this.dvcVos.clear();
    this.dvcVos.addAll(paramList);
    this.mac = UserFerences.getUserFerences(paramActivity).getValue("GateWayMacIdKey");
  }

  public RoomBusiness(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
  }

  private int checkExitsVo(Dvc paramDvc)
  {
    for (int i = 0; i < this.dvcVos.size(); i++)
    {
      if (paramDvc.getmIndex() != ((Dvc)this.dvcVos.get(i)).getmIndex())
        continue;
      this.dvcVos.remove(i);
      return i;
    }
    return -1;
  }

  private void checkThisVoInOtherRoom(int paramInt)
  {
    ArrayList localArrayList = null;
    Home localHome = new RoomsBusiness(this.ct).getHome();
    for (int i = 0; i < localHome.getRooms().size(); i++)
      for (int j = 0; j < ((Room)localHome.getRooms().get(i)).getDvcVos().size(); j++)
      {
        if (((Dvc)((Room)localHome.getRooms().get(i)).getDvcVos().get(j)).getmIndex() != paramInt)
          continue;
        if (((Dvc)((Room)localHome.getRooms().get(i)).getDvcVos().get(j)).isGroup())
        {
          localArrayList = ((Dvc)((Room)localHome.getRooms().get(i)).getDvcVos().get(j)).getInnerDvcVos();
          if (localArrayList.size() > 0)
            localArrayList.remove(0);
        }
        ((Room)localHome.getRooms().get(i)).getDvcVos().remove(j);
        if (localArrayList != null)
          ((Room)localHome.getRooms().get(i)).getDvcVos().addAll(0, localArrayList);
        j--;
        putData4ClassName(this.mac, localHome);
      }
  }

  private Dvc createSimpleDvc(Dvc paramDvc)
  {
    Dvc localDvc = new Dvc();
    localDvc.setName(paramDvc.getName());
    localDvc.setType(paramDvc.getType());
    localDvc.setId(paramDvc.getId());
    localDvc.setmIndex(paramDvc.getmIndex());
    localDvc.setRoomIndex(paramDvc.getRoomIndex());
    localDvc.setGroupId(paramDvc.getGroupId());
    localDvc.setIsOnLine(paramDvc.isOnLine());
    localDvc.setOnOff(paramDvc.isOnOff());
    return localDvc;
  }

  public boolean addCheckSumData(String paramString)
  {
    try
    {
      String str = paramString.substring(0, -6 + paramString.length());
      int i = str.length();
      int j = 0;
      for (int k = 0; k < i / 2; k++)
        j += Integer.parseInt(str.substring(k * 2, 2 + k * 2), 16);
      int m = Integer.parseInt(paramString.substring(-4 + paramString.length(), -2 + paramString.length()) + paramString.substring(-6 + paramString.length(), -4 + paramString.length()), 16);
      int n = 0;
      if (m == j)
        n = 1;
      return n;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public void beginCrop(Fragment paramFragment, Activity paramActivity, Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(paramActivity.getCacheDir(), "cropped"))).asSquare().start(paramFragment, paramActivity);
  }

  public boolean canDrag()
  {
    int i;
    int j;
    label23: int k;
    if (this.DvcSeletedItemPosi == this.DvcTagItemPosi)
    {
      i = 1;
      if (this.DvcTagItemPosi != -1)
        break label60;
      j = 1;
      k = j | i;
      if (this.DvcSeletedItemPosi != -1)
        break label65;
    }
    label60: label65: for (int m = 1; ; m = 0)
    {
      if ((m | k) == 0)
        break label91;
      if (this.DvcSeletedItemPosi != -1)
        break label71;
      return false;
      i = 0;
      break;
      j = 0;
      break label23;
    }
    label71: ((Dvc)this.dvcVos.get(this.DvcSeletedItemPosi)).setIsSeleted(false);
    return false;
    label91: Dvc localDvc1 = (Dvc)this.dvcVos.get(this.DvcSeletedItemPosi);
    Dvc localDvc2 = (Dvc)this.dvcVos.get(this.DvcTagItemPosi);
    int n;
    int i1;
    label149: int i3;
    label169: int i5;
    label189: int i6;
    label210: int i7;
    label223: int i9;
    label243: int i10;
    if (localDvc1.getType() == 8)
    {
      n = 1;
      if (localDvc1.getType() != 9)
        break label292;
      i1 = 1;
      int i2 = i1 | n;
      if (localDvc1.getType() != 10)
        break label298;
      i3 = 1;
      int i4 = i2 | i3;
      if (localDvc1.getType() != 11)
        break label304;
      i5 = 1;
      if ((i5 | i4) == 0)
        break label374;
      if (localDvc2.getType() != 8)
        break label310;
      i6 = 1;
      if (localDvc2.getType() != 9)
        break label316;
      i7 = 1;
      int i8 = i7 | i6;
      if (localDvc2.getType() != 10)
        break label322;
      i9 = 1;
      i10 = i8 | i9;
      if (localDvc2.getType() != 11)
        break label328;
    }
    label292: label298: label304: label310: label316: label322: label328: for (int i11 = 1; ; i11 = 0)
    {
      if ((i11 | i10) == 0)
        break label354;
      if (localDvc2.getType() != localDvc1.getType())
        break label334;
      return true;
      n = 0;
      break;
      i1 = 0;
      break label149;
      i3 = 0;
      break label169;
      i5 = 0;
      break label189;
      i6 = 0;
      break label210;
      i7 = 0;
      break label223;
      i9 = 0;
      break label243;
    }
    label334: ((Dvc)this.dvcVos.get(this.DvcSeletedItemPosi)).setIsSeleted(false);
    return false;
    label354: ((Dvc)this.dvcVos.get(this.DvcSeletedItemPosi)).setIsSeleted(false);
    return false;
    label374: ((Dvc)this.dvcVos.get(this.DvcSeletedItemPosi)).setIsSeleted(false);
    return false;
  }

  public boolean canHideGroupLayout(int paramInt)
  {
    return this.groupInnerDvcSeletedItemPosi != paramInt;
  }

  public void canelAllDeviceSetetedStatus()
  {
    for (int i = 0; i < this.groupInnerDvcVos.size(); i++)
      ((Dvc)this.groupInnerDvcVos.get(i)).setIsClickSeleted(false);
    for (int j = 0; j < this.dvcVos.size(); j++)
      ((Dvc)this.dvcVos.get(j)).setIsClickSeleted(false);
  }

  public void checkDeviceIsOnline(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void checkGroupStatus()
  {
    int i = 0;
    if (i < this.dvcVos.size())
    {
      boolean bool1 = ((Dvc)this.dvcVos.get(i)).isGroup();
      boolean bool2;
      if (((Dvc)this.dvcVos.get(i)).innerDvcVos.size() > 1)
        bool2 = true;
      while ((bool2 & bool1))
      {
        int j = 0;
        int k = 0;
        int m = 0;
        while (true)
          if (m < ((Dvc)this.dvcVos.get(i)).innerDvcVos.size())
          {
            if (!((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(m)).isOnLine())
              j++;
            if (!((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(m)).isOnOff())
              k++;
            m++;
            continue;
            bool2 = false;
            break;
          }
        if (j != ((Dvc)this.dvcVos.get(i)).innerDvcVos.size())
          break label241;
        ((Dvc)this.dvcVos.get(i)).setIsOnLine(false);
        label198: if (k != ((Dvc)this.dvcVos.get(i)).innerDvcVos.size())
          break label259;
        ((Dvc)this.dvcVos.get(i)).setOnOff(false);
      }
      while (true)
      {
        i++;
        break;
        label241: ((Dvc)this.dvcVos.get(i)).setIsOnLine(true);
        break label198;
        label259: ((Dvc)this.dvcVos.get(i)).setOnOff(true);
      }
    }
  }

  public boolean checkIsDiffrentType()
  {
    int i;
    int j;
    if (this.DvcSeletedItemPosi == this.DvcTagItemPosi)
    {
      i = 1;
      if (this.DvcTagItemPosi != -1)
        break label36;
      j = 1;
      label23: if ((i | j) == 0)
        break label41;
    }
    while (true)
    {
      return false;
      i = 0;
      break;
      label36: j = 0;
      break label23;
      label41: boolean bool1 = ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).isGroup();
      if (((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.size() > 1);
      for (boolean bool2 = true; !(bool2 & bool1); bool2 = false)
        return true;
    }
  }

  public Dvc checkSumDeviceInfo(String paramString, int paramInt1, int paramInt2)
  {
    Dvc localDvc = new Dvc();
    ArrayList localArrayList;
    int k;
    String str3;
    int m;
    int i2;
    int i1;
    label216: Object localObject2;
    Object localObject1;
    boolean bool20;
    label357: boolean bool21;
    label384: boolean bool18;
    label516: boolean bool19;
    label543: boolean bool16;
    label675: boolean bool17;
    label702: boolean bool14;
    label834: boolean bool15;
    label861: boolean bool13;
    label976: boolean bool12;
    label1038: boolean bool11;
    label1059: boolean bool7;
    label1203: boolean bool8;
    label1224: boolean bool5;
    label1393: boolean bool6;
    label1414: boolean bool3;
    label1583: boolean bool4;
    while (true)
    {
      try
      {
        localArrayList = new ArrayList();
        String str1 = paramString.substring(18, 20);
        Integer.parseInt(paramString.substring(20, 22), 16);
        int i = -1 + Integer.parseInt(paramString.substring(24, 26), 16);
        int j = Integer.parseInt(paramString.substring(26, 28), 16);
        k = Integer.parseInt(paramString.substring(28, 30), 16);
        String str2 = paramString.substring(30, 38);
        str3 = paramString.substring(38, 40);
        if (k != 65)
          break label1981;
        m = 1;
        break label1949;
        int i3 = i2 | i1;
        int i4 = 0;
        if (i3 == 0)
          continue;
        i4 = Integer.parseInt(paramString.substring(58, 60), 16);
        if ((!str1.equalsIgnoreCase("A3")) || (i != paramInt1 - 1))
          break label1979;
        localDvc.setmIndex(j);
        localDvc.setRoomIndex(i);
        localDvc.setId(str2);
        if (i4 == 0)
          break label1999;
        localDvc.setGroupId(i4);
        localDvc.setIsGroup(true);
        break label1999;
        localDvc.setGroupName((String)localObject2);
        localDvc.setName((String)localObject1);
        System.out.println("roomIndex groupName" + (String)localObject2);
        return localDvc;
      }
      catch (Exception localException1)
      {
        localException1.printStackTrace();
        return null;
      }
      if (paramString.length() < 166)
        return null;
      localDvc.setType(11);
      localDvc.setName("DIM");
      int i8 = Integer.parseInt(paramString.substring(60, 62), 16);
      try
      {
        localObject1 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(62, 110)));
        String str14 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(106, 154)));
        localObject2 = str14;
        if (i8 != 1)
          break label2116;
        bool20 = true;
        localDvc.setIsOnLine(bool20);
        if (!paramString.substring(38, 40).equals("01"))
          break label2122;
        bool21 = true;
        localDvc.setOnOff(bool21);
      }
      catch (Exception localException11)
      {
        while (true)
        {
          localException11.printStackTrace();
          localObject1 = this.context.getString(2131100036);
          localObject2 = this.context.getString(2131100081);
        }
      }
      if (paramString.length() < 166)
        return null;
      localDvc.setType(9);
      localDvc.setName("CT");
      int i7 = Integer.parseInt(paramString.substring(60, 62), 16);
      try
      {
        localObject1 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(62, 110)));
        String str13 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(106, 154)));
        localObject2 = str13;
        if (i7 != 1)
          break label2128;
        bool18 = true;
        localDvc.setIsOnLine(bool18);
        if (!paramString.substring(38, 40).equals("01"))
          break label2134;
        bool19 = true;
        localDvc.setOnOff(bool19);
      }
      catch (Exception localException10)
      {
        while (true)
        {
          localException10.printStackTrace();
          localObject1 = this.context.getString(2131099975);
          localObject2 = this.context.getString(2131100081);
        }
      }
      if (paramString.length() < 166)
        return null;
      localDvc.setType(8);
      localDvc.setName("RGB");
      int i6 = Integer.parseInt(paramString.substring(60, 62), 16);
      try
      {
        localObject1 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(62, 110)));
        String str12 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(110, 158)));
        localObject2 = str12;
        if (i6 != 1)
          break label2140;
        bool16 = true;
        localDvc.setIsOnLine(bool16);
        if (!paramString.substring(38, 40).equals("01"))
          break label2146;
        bool17 = true;
        localDvc.setOnOff(bool17);
      }
      catch (Exception localException9)
      {
        while (true)
        {
          localException9.printStackTrace();
          localObject1 = this.context.getString(2131100345);
          localObject2 = this.context.getString(2131100081);
        }
      }
      if (paramString.length() < 166)
        return null;
      localDvc.setType(12);
      localDvc.setName("RGBW");
      int i5 = Integer.parseInt(paramString.substring(60, 62), 16);
      try
      {
        localObject1 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(62, 110)));
        String str11 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(110, 158)));
        localObject2 = str11;
        if (i5 != 1)
          break label2152;
        bool14 = true;
        localDvc.setIsOnLine(bool14);
        if (!paramString.substring(38, 40).equals("01"))
          break label2158;
        bool15 = true;
        localDvc.setOnOff(bool15);
      }
      catch (Exception localException8)
      {
        while (true)
        {
          localException8.printStackTrace();
          localObject1 = this.context.getString(2131100345);
          localObject2 = this.context.getString(2131100081);
        }
      }
      if (paramString.length() < 122)
        return null;
      localDvc.setType(14);
      String str8 = paramString.substring(40, 42);
      boolean bool9 = str8.equals("01");
      if (bool9);
      try
      {
        String str10 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(58, 106)));
        localObject1 = str10;
        if (1 != 1)
          break label2164;
        bool13 = true;
        localDvc.setIsOnLine(bool13);
        boolean bool10 = str8.equals("00");
        if (!bool10);
      }
      catch (Exception localException7)
      {
        try
        {
          String str9 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(66, 114)));
          localObject1 = str9;
          if (Integer.parseInt(paramString.substring(64, 66), 16) != 1)
            break label2170;
          bool12 = true;
          localDvc.setIsOnLine(bool12);
          if (!str3.equals("01"))
            break label2176;
          bool11 = true;
          localDvc.setOnOff(bool11);
          continue;
          localException7 = localException7;
          localException7.printStackTrace();
          localObject1 = this.context.getString(2131100386);
        }
        catch (Exception localException6)
        {
          while (true)
          {
            localException6.printStackTrace();
            localObject1 = this.context.getString(2131100386);
          }
        }
      }
      localDvc.setName(this.ct.getString(2131100045));
      localDvc.setType(19);
      continue;
      if (paramString.length() < 106)
        return null;
      localDvc.setType(18);
      try
      {
        String str7 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(50, 98)));
        localObject1 = str7;
        localDvc.setPanelLampVO(localArrayList);
        if (Integer.parseInt(paramString.substring(48, 50), 16) != 1)
          break label2182;
        bool7 = true;
        localDvc.setIsOnLine(bool7);
        if (!str3.equals("00"))
          break label2188;
        bool8 = false;
        localDvc.setOnOff(bool8);
        System.out.println("name = " + paramString.substring(38, 40) + "game = " + (String)localObject2 + " onOff = " + localDvc.isOnOff() + " online = " + localDvc.isOnLine());
      }
      catch (Exception localException5)
      {
        while (true)
        {
          localException5.printStackTrace();
          localObject1 = this.context.getString(2131100258);
        }
      }
      if (paramString.length() < 106)
        return null;
      localDvc.setType(17);
      try
      {
        String str6 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(50, 98)));
        localObject1 = str6;
        localDvc.setPanelLampVO(localArrayList);
        if (Integer.parseInt(paramString.substring(48, 50), 16) != 1)
          break label2194;
        bool5 = true;
        localDvc.setIsOnLine(bool5);
        if (!str3.equals("00"))
          break label2200;
        bool6 = false;
        localDvc.setOnOff(bool6);
        System.out.println("name = " + paramString.substring(38, 40) + "game = " + (String)localObject2 + " onOff = " + localDvc.isOnOff() + " online = " + localDvc.isOnLine());
      }
      catch (Exception localException4)
      {
        while (true)
        {
          localException4.printStackTrace();
          localObject1 = this.context.getString(2131100258);
        }
      }
      if (paramString.length() < 106)
        return null;
      localDvc.setType(16);
      try
      {
        String str5 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(50, 98)));
        localObject1 = str5;
        localDvc.setPanelLampVO(localArrayList);
        if (Integer.parseInt(paramString.substring(48, 50), 16) != 1)
          break label2206;
        bool3 = true;
        localDvc.setIsOnLine(bool3);
        if (!str3.equals("00"))
          break label2212;
        bool4 = false;
        label1604: localDvc.setOnOff(bool4);
        System.out.println("name = " + paramString.substring(38, 40) + "game = " + (String)localObject2 + " onOff = " + localDvc.isOnOff() + " online = " + localDvc.isOnLine());
      }
      catch (Exception localException3)
      {
        while (true)
        {
          localException3.printStackTrace();
          localObject1 = this.context.getString(2131100258);
        }
      }
    }
    if (paramString.length() < 106)
      return null;
    localDvc.setType(15);
    while (true)
    {
      try
      {
        String str4 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(50, 98)));
        localObject1 = str4;
        localDvc.setPanelLampVO(localArrayList);
        if (Integer.parseInt(paramString.substring(48, 50), 16) != 1)
          break label2218;
        bool1 = true;
        localDvc.setIsOnLine(bool1);
        if (!str3.equals("00"))
          break label2224;
        bool2 = false;
        localDvc.setOnOff(bool2);
        System.out.println("name = " + paramString.substring(38, 40) + "game = " + (String)localObject2 + " onOff = " + localDvc.isOnOff() + " online = " + localDvc.isOnLine());
      }
      catch (Exception localException2)
      {
        localException2.printStackTrace();
        localObject1 = this.context.getString(2131100258);
        continue;
      }
      localDvc.setType(21);
      localObject1 = StringUtils.bytesStr2WordStr(StringUtils.subEndZero(paramString.substring(42, 66)));
      break label216;
      localDvc.setName(this.ct.getString(2131099906));
      localDvc.setType(13);
      break label216;
      label1949: if (k == 66);
      for (int n = 1; ; n = 0)
      {
        i1 = m | n;
        if (k != 67)
          break label1993;
        i2 = 1;
        break;
        label1979: return null;
        label1981: m = 0;
        break label1949;
      }
      label1993: i2 = 0;
      break;
      label1999: localObject1 = "";
      localObject2 = "";
      switch (k)
      {
      default:
      case 65:
      case 66:
      case 67:
      case 68:
      case 81:
      case 84:
      case 37:
      case 36:
      case 35:
      case 34:
      case 97:
      case 98:
      }
      label2116: bool20 = false;
      break label357;
      label2122: bool21 = false;
      break label384;
      label2128: bool18 = false;
      break label516;
      label2134: bool19 = false;
      break label543;
      label2140: bool16 = false;
      break label675;
      label2146: bool17 = false;
      break label702;
      label2152: bool14 = false;
      break label834;
      label2158: bool15 = false;
      break label861;
      label2164: bool13 = false;
      break label976;
      label2170: bool12 = false;
      break label1038;
      label2176: bool11 = false;
      break label1059;
      label2182: bool7 = false;
      break label1203;
      label2188: bool8 = true;
      break label1224;
      label2194: bool5 = false;
      break label1393;
      label2200: bool6 = true;
      break label1414;
      label2206: bool3 = false;
      break label1583;
      label2212: bool4 = true;
      break label1604;
      label2218: boolean bool1 = false;
      continue;
      label2224: boolean bool2 = true;
    }
  }

  public void clearVosThisRoom(int paramInt)
  {
    this.dvcVos.clear();
    this.listviewDvcVos.clear();
    Home localHome = new RoomsBusiness(this.ct).getHome();
    ((Room)localHome.getRooms().get(paramInt)).setDvcVos(this.dvcVos);
    putData4ClassName(this.mac, localHome);
  }

  // ERROR //
  public List compareWithReturnInfo(String paramString1, int paramInt, String paramString2)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: aload_1
    //   4: bipush 18
    //   6: bipush 20
    //   8: invokevirtual 241	java/lang/String:substring	(II)Ljava/lang/String;
    //   11: astore 6
    //   13: aload_1
    //   14: bipush 20
    //   16: bipush 22
    //   18: invokevirtual 241	java/lang/String:substring	(II)Ljava/lang/String;
    //   21: bipush 16
    //   23: invokestatic 247	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   26: istore 7
    //   28: aload_0
    //   29: aload_1
    //   30: invokevirtual 420	com/ex/ltech/onepiontfive/main/room/RoomBusiness:addCheckSumData	(Ljava/lang/String;)Z
    //   33: istore 8
    //   35: aconst_null
    //   36: astore 4
    //   38: iload 8
    //   40: ifeq +141 -> 181
    //   43: aload 6
    //   45: ldc_w 422
    //   48: invokevirtual 341	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   51: istore 9
    //   53: aconst_null
    //   54: astore 4
    //   56: iload 9
    //   58: ifeq +123 -> 181
    //   61: aload_3
    //   62: aload_1
    //   63: bipush 10
    //   65: bipush 18
    //   67: invokevirtual 241	java/lang/String:substring	(II)Ljava/lang/String;
    //   70: invokevirtual 341	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   73: istore 10
    //   75: aconst_null
    //   76: astore 4
    //   78: iload 10
    //   80: ifeq +101 -> 181
    //   83: aconst_null
    //   84: astore 4
    //   86: iload 7
    //   88: iconst_1
    //   89: if_icmple +92 -> 181
    //   92: aconst_null
    //   93: astore 4
    //   95: iload 7
    //   97: iconst_4
    //   98: if_icmple +83 -> 181
    //   101: new 71	java/util/ArrayList
    //   104: dup
    //   105: invokespecial 74	java/util/ArrayList:<init>	()V
    //   108: astore 11
    //   110: aload_1
    //   111: bipush 30
    //   113: bipush 248
    //   115: aload_1
    //   116: invokevirtual 237	java/lang/String:length	()I
    //   119: iadd
    //   120: invokevirtual 241	java/lang/String:substring	(II)Ljava/lang/String;
    //   123: astore 12
    //   125: iconst_0
    //   126: istore 13
    //   128: iload 13
    //   130: iload 7
    //   132: iconst_4
    //   133: isub
    //   134: if_icmpge +59 -> 193
    //   137: aload 11
    //   139: aload 12
    //   141: iload 13
    //   143: iconst_2
    //   144: imul
    //   145: iconst_2
    //   146: iload 13
    //   148: iconst_2
    //   149: imul
    //   150: iadd
    //   151: invokevirtual 241	java/lang/String:substring	(II)Ljava/lang/String;
    //   154: bipush 16
    //   156: invokestatic 247	java/lang/Integer:parseInt	(Ljava/lang/String;I)I
    //   159: invokestatic 319	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   162: invokeinterface 423 2 0
    //   167: pop
    //   168: iinc 13 1
    //   171: goto -43 -> 128
    //   174: astore 5
    //   176: aload 5
    //   178: invokevirtual 260	java/lang/Exception:printStackTrace	()V
    //   181: aload 4
    //   183: areturn
    //   184: astore 5
    //   186: aload 11
    //   188: astore 4
    //   190: goto -14 -> 176
    //   193: aload 11
    //   195: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   3	35	174	java/lang/Exception
    //   43	53	174	java/lang/Exception
    //   61	75	174	java/lang/Exception
    //   101	110	174	java/lang/Exception
    //   110	125	184	java/lang/Exception
    //   137	168	184	java/lang/Exception
  }

  public void cracySendDIMnCTnRGB(Dvc paramDvc, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2)
  {
    addNormalHeadData(this.cmd);
    ArrayList localArrayList1 = this.cmd;
    int i;
    label68: int j;
    if (paramDvc.isGroup())
    {
      i = 2;
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(12));
      if (paramInt4 != 11)
        break label305;
      this.cmd.add(Integer.valueOf(65));
      this.cmd.add(Integer.valueOf(1 + paramDvc.getRoomIndex()));
      ArrayList localArrayList2 = this.cmd;
      if (!paramDvc.isGroup())
        break label328;
      j = paramDvc.getGroupId();
      label104: localArrayList2.add(Integer.valueOf(j));
      if (paramInt4 != 11)
        break label337;
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(paramInt3));
      this.cmd.add(Integer.valueOf((int)paramFloat1));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat1 - (int)paramFloat1))));
      this.cmd.add(Integer.valueOf((int)paramFloat2));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat2 - (int)paramFloat2))));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      crasySendCmdNoResponse(this.cmd);
      return;
      i = 1;
      break;
      label305: if (paramInt4 != 9)
        break label68;
      this.cmd.add(Integer.valueOf(66));
      break label68;
      label328: j = paramDvc.getmIndex();
      break label104;
      label337: if (paramInt4 != 9)
        continue;
      this.cmd.add(Integer.valueOf(paramInt2));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(0));
    }
  }

  public void ctrlAir(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(20));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(97));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void ctrlAirFun(MyBusiness.MySendListener paramMySendListener)
  {
    Dvc localDvc = (Dvc)this.dvcVos.get(this.DvcSeletedItemPosi);
    localDvc.getId();
    Integer.parseInt(localDvc.getId(), 16);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(20));
    this.cmd.add(Integer.valueOf(8));
    this.cmd.add(Integer.valueOf(97));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void ctrlBulb(Dvc paramDvc)
  {
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    switch (paramDvc.getType())
    {
    default:
    case 11:
    case 9:
    case 10:
    case 8:
    }
    int i;
    while (true)
    {
      addUserId2Cmd(this.cmd);
      this.cmd.add(Integer.valueOf(1));
      this.cmd.add(Integer.valueOf(7));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getmIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getR()));
      this.cmd.add(Integer.valueOf(paramDvc.getG()));
      this.cmd.add(Integer.valueOf(paramDvc.getB()));
      this.cmd.add(Integer.valueOf(paramDvc.getW()));
      this.cmd.add(Integer.valueOf(paramDvc.getBrt()));
      this.cmd.add(Integer.valueOf(1));
      i = 0;
      for (int j = 0; j < this.cmd.size(); j++)
        i += ((Integer)this.cmd.get(j)).intValue();
      this.cmd.add(Integer.valueOf(69));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(71));
    }
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str2, 16));
    this.cmd.add(Integer.valueOf(str3, 16));
    this.cmd.add(Integer.valueOf(90));
  }

  public void ctrlCTColor(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(66));
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(paramInt4));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void ctrlGroupBulb(Dvc paramDvc)
  {
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    switch (paramDvc.getType())
    {
    default:
    case 11:
    case 9:
    case 10:
    case 8:
    }
    int i;
    while (true)
    {
      addUserId2Cmd(this.cmd);
      this.cmd.add(Integer.valueOf(2));
      this.cmd.add(Integer.valueOf(7));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getmIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
      this.cmd.add(Integer.valueOf(paramDvc.getR()));
      this.cmd.add(Integer.valueOf(paramDvc.getG()));
      this.cmd.add(Integer.valueOf(paramDvc.getB()));
      this.cmd.add(Integer.valueOf(paramDvc.getW()));
      this.cmd.add(Integer.valueOf(paramDvc.getBrt()));
      this.cmd.add(Integer.valueOf(1));
      i = 0;
      for (int j = 0; j < this.cmd.size(); j++)
        i += ((Integer)this.cmd.get(j)).intValue();
      this.cmd.add(Integer.valueOf(69));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(71));
    }
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str2, 16));
    this.cmd.add(Integer.valueOf(str3, 16));
    this.cmd.add(Integer.valueOf(90));
  }

  public boolean dataCheckSum(String paramString, int paramInt1, int paramInt2)
  {
    try
    {
      String str = paramString.substring(18, 20);
      Integer.parseInt(paramString.substring(20, 22), 16);
      Integer.parseInt(paramString.substring(22, 24), 16);
      Integer.parseInt(paramString.substring(24, 26), 16);
      Integer.parseInt(paramString.substring(26, 28), 16);
      Integer.parseInt(paramString.substring(28, 30), 16);
      if (str.equalsIgnoreCase("a2"))
      {
        responseMessage(paramString.substring(4, 6), "22");
        paramString.substring(30, -8 + paramString.length());
      }
      return false;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void delDeivceInRoom(int paramInt1, int paramInt2)
  {
    this.dvcVos.remove(paramInt2);
    Home localHome = new RoomsBusiness(this.ct).getHome();
    ((Room)localHome.getRooms().get(paramInt1)).setDvcVos(this.dvcVos);
    putData4ClassName(this.mac, localHome);
  }

  public void delDevice(Dvc paramDvc, MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(17));
    addUserId2Cmd(this.cmd);
    this.cmd.add(Integer.valueOf(17));
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(136));
    addDeviceId2Cmd(this.cmd, paramDvc.getId());
    this.cmd.add(Integer.valueOf(paramDvc.getRoomIndex()));
    this.cmd.add(Integer.valueOf(paramDvc.getmIndex()));
    this.cmd.add(Integer.valueOf(1));
    int i = 0;
    for (int j = 0; j < this.cmd.size(); j++)
      i += ((Integer)this.cmd.get(j)).intValue();
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str2, 16));
    this.cmd.add(Integer.valueOf(str3, 16));
    this.cmd.add(Integer.valueOf(90));
    sendCmd(this.cmd);
  }

  public void delGroupInnerDevice(int paramInt)
  {
    this.groupInnerDvcVos.remove(paramInt);
  }

  public void deleteDvc(MyBusiness.MySendListener paramMySendListener, Dvc paramDvc)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(17));
    this.cmd.add(Integer.valueOf(7));
    this.cmd.add(Integer.valueOf(136));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramDvc.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramDvc.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramDvc.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramDvc.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(1 + paramDvc.getRoomIndex()));
    this.cmd.add(Integer.valueOf(paramDvc.getmIndex()));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void deviceGroupOnOff(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    System.out.println("deviceGroupOnOff = " + paramBoolean);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    this.cmd.add(Integer.valueOf(5));
    switch (paramInt2)
    {
    case 10:
    default:
      this.cmd.add(Integer.valueOf(paramInt1 + 1));
      this.cmd.add(Integer.valueOf(paramInt3));
      if (!paramBoolean)
        break;
      this.cmd.add(Integer.valueOf(255));
      this.cmd.add(Integer.valueOf(255));
    case 8:
    case 12:
    case 11:
    case 9:
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      this.cmd.add(Integer.valueOf(67));
      break;
      this.cmd.add(Integer.valueOf(68));
      break;
      this.cmd.add(Integer.valueOf(65));
      break;
      this.cmd.add(Integer.valueOf(66));
      break;
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(0));
    }
  }

  public void deviceOff(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, int paramInt4)
  {
    System.out.println("deviceGroupOnOff = " + paramBoolean1);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    if (paramBoolean2)
    {
      this.cmd.add(Integer.valueOf(22));
      this.cmd.add(Integer.valueOf(4));
      this.cmd.add(Integer.valueOf(paramInt1 + 1));
      this.cmd.add(Integer.valueOf(paramInt4));
      this.cmd.add(Integer.valueOf(0));
      if (paramBoolean1)
        this.cmd.add(Integer.valueOf(0));
      while (true)
      {
        this.cmd.add(Integer.valueOf(1));
        addCheckSumData(this.cmd);
        this.cmd.add(Integer.valueOf(22));
        sendCmd(this.cmd);
        return;
        this.cmd.add(Integer.valueOf(1));
      }
    }
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(5));
    switch (paramInt3)
    {
    case 10:
    default:
      label244: this.cmd.add(Integer.valueOf(paramInt1 + 1));
      this.cmd.add(Integer.valueOf(paramInt2));
      if (!paramBoolean1)
        break;
      this.cmd.add(Integer.valueOf(255));
      this.cmd.add(Integer.valueOf(255));
    case 8:
    case 12:
    case 11:
    case 9:
    }
    while (true)
    {
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      break;
      this.cmd.add(Integer.valueOf(67));
      break label244;
      this.cmd.add(Integer.valueOf(68));
      break label244;
      this.cmd.add(Integer.valueOf(65));
      break label244;
      this.cmd.add(Integer.valueOf(66));
      break label244;
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(0));
    }
  }

  public void deviceOnOff(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(paramInt1 + 1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(67));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void dragOutGroup(MyBusiness.MySendListener paramMySendListener)
  {
    Dvc localDvc = (Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.get(this.groupInnerDvcSeletedItemPosi);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(18));
    this.cmd.add(Integer.valueOf(6));
    this.cmd.add(Integer.valueOf(136));
    this.dragOutDvcId = localDvc.getId();
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(localDvc.getGroupId()));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void dragOutGroupRemoveAllSon(MyBusiness.MySendListener paramMySendListener)
  {
    Dvc localDvc1 = (Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.get(0);
    Dvc localDvc2 = (Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.get(this.groupInnerDvcSeletedItemPosi);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(18));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(136));
    this.dragOutDvcId = localDvc2.getId();
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(localDvc2.getGroupId()));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void dragToGroup(MyBusiness.MySendListener paramMySendListener)
  {
    Dvc localDvc1 = (Dvc)this.dvcVos.get(this.DvcSeletedItemPosi);
    Dvc localDvc2 = (Dvc)this.dvcVos.get(this.DvcTagItemPosi);
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(18));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(85));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc1.getId().substring(6, 8), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(0, 2), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(2, 4), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(4, 6), 16)));
    this.cmd.add(Integer.valueOf(Integer.parseInt(localDvc2.getId().substring(6, 8), 16)));
    if (!localDvc2.isGroup())
      this.cmd.add(Integer.valueOf(255));
    while (true)
    {
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      this.cmd.add(Integer.valueOf(localDvc2.getGroupId()));
    }
  }

  public void getCtSceneColorCmd(MyBusiness.MySendListener paramMySendListener, int paramInt1, String paramString, int paramInt2, int paramInt3, int paramInt4)
  {
  }

  public Dvc getDvcBymIndex(int paramInt)
  {
    Home localHome = new RoomsBusiness(this.ct).getHome();
    Object localObject = null;
    for (int i = 0; i < 7; i++)
    {
      Iterator localIterator = ((Room)localHome.getRooms().get(i)).getDvcVos().iterator();
      while (localIterator.hasNext())
      {
        Dvc localDvc = (Dvc)localIterator.next();
        if ((!localDvc.isGroup()) && (localDvc.innerDvcVos.size() < 2))
        {
          if (localDvc.getmIndex() != paramInt)
            continue;
          localObject = localDvc;
          continue;
        }
        for (int j = 0; j < localDvc.innerDvcVos.size(); j++)
        {
          if (((Dvc)localDvc.innerDvcVos.get(j)).getmIndex() != paramInt)
            continue;
          localObject = (Dvc)localDvc.innerDvcVos.get(j);
        }
      }
    }
    return (Dvc)localObject;
  }

  public int getGroupInnerDvcCount()
  {
    return ((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.size();
  }

  public void getMcDeivceIndexs(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt1 + 1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
      }
    }
    , 100L);
  }

  public List getMcDvcIndexs(String paramString, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    try
    {
      String str1 = paramString.substring(18, 20);
      Integer.parseInt(paramString.substring(20, 22), 16);
      int i = Integer.parseInt(paramString.substring(22, 24), 16);
      int j = Integer.parseInt(paramString.substring(24, 26), 16);
      int k = Integer.parseInt(paramString.substring(26, 28), 16);
      int m = Integer.parseInt(paramString.substring(28, 30), 16);
      String str2;
      int n;
      if ((str1.equalsIgnoreCase("a2")) && (i == 1) && (j == paramInt1) && (k == paramInt2))
      {
        str2 = paramString.substring(30, -8 + paramString.length());
        n = 0;
      }
      while (n < m)
      {
        localArrayList.add(Integer.valueOf(Integer.parseInt(str2.substring(n * 2, 2 + n * 2), 16)));
        n++;
        continue;
        localArrayList = null;
      }
      return localArrayList;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public void goCarmare(Fragment paramFragment)
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.bgFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(this.bgFile));
    paramFragment.startActivityForResult(localIntent, 1);
  }

  public List handlLoopCheckDeviceIsOnlineData(byte[] paramArrayOfByte)
  {
    ArrayList localArrayList = new ArrayList();
    int i = -1;
    String str = StringUtils.btye2Str(paramArrayOfByte);
    boolean bool1;
    if ((str.length() >= 38) && (str.substring(18, 20).equals("80")))
    {
      int i8 = Integer.parseInt(str.substring(22, 24), 16);
      int i9 = Integer.parseInt(str.substring(24, 26), 16);
      int i10 = Integer.parseInt(str.substring(26, 28), 16);
      int i11 = Integer.parseInt(str.substring(28, 30), 16);
      bool1 = false;
      if (i8 != 11)
      {
        int i12 = 1 + this.roomIndex;
        bool1 = false;
        if (i12 == i9)
        {
          int i13 = 0;
          if (i13 < this.dvcVos.size())
          {
            boolean bool7 = ((Dvc)this.dvcVos.get(i13)).isGroup();
            boolean bool8;
            int i14;
            label204: Dvc localDvc6;
            if (((Dvc)this.dvcVos.get(i13)).innerDvcVos.size() > 1)
            {
              bool8 = true;
              if (!(bool8 & bool7))
                break label387;
              i14 = 0;
              if (i14 >= ((Dvc)this.dvcVos.get(i13)).innerDvcVos.size())
                break label482;
              if (((Dvc)((Dvc)this.dvcVos.get(i13)).innerDvcVos.get(i14)).getmIndex() == i10)
              {
                localDvc6 = (Dvc)((Dvc)this.dvcVos.get(i13)).innerDvcVos.get(i14);
                if (i11 != 1)
                  break label381;
              }
            }
            label381: for (boolean bool10 = true; ; bool10 = false)
            {
              localDvc6.setIsOnLine(bool10);
              if ((((Dvc)((Dvc)this.dvcVos.get(i13)).innerDvcVos.get(i14)).isClickSeleted()) && (((Dvc)((Dvc)this.dvcVos.get(i13)).innerDvcVos.get(i14)).getType() <= 12))
                bool1 = true;
              checkGroupStatus();
              i = i13;
              i14++;
              break label204;
              bool8 = false;
              break;
            }
            label387: Dvc localDvc5;
            if (((Dvc)this.dvcVos.get(i13)).getmIndex() == i10)
            {
              localDvc5 = (Dvc)this.dvcVos.get(i13);
              if (i11 != 1)
                break label488;
            }
            label482: label488: for (boolean bool9 = true; ; bool9 = false)
            {
              localDvc5.setIsOnLine(bool9);
              if ((((Dvc)this.dvcVos.get(i13)).isClickSeleted()) && (((Dvc)this.dvcVos.get(i13)).getType() <= 12))
                bool1 = true;
              checkGroupStatus();
              i13++;
              break;
            }
          }
        }
      }
    }
    else
    {
      int j = str.length();
      bool1 = false;
      if (j >= 50)
      {
        boolean bool2 = str.substring(18, 20).equals("A1");
        bool1 = false;
        if (bool2)
        {
          int k = Integer.parseInt(str.substring(26, 28), 16);
          int m = Integer.parseInt(str.substring(22, 24), 16);
          int n = Integer.parseInt(str.substring(24, 26), 16);
          int i1 = Integer.parseInt(str.substring(28, 30), 16);
          int i2;
          int i6;
          int i7;
          label703: Dvc localDvc4;
          if (Integer.parseInt(str.substring(48, 50), 16) == 0)
          {
            i2 = 0;
            if (i2 == 0)
              break label831;
            i6 = 0;
            if (i6 >= this.dvcVos.size())
              break label1093;
            if ((((Dvc)this.dvcVos.get(i6)).getGroupId() != n) || (!((Dvc)this.dvcVos.get(i6)).isGroup()) || (((Dvc)this.dvcVos.get(i6)).innerDvcVos.size() <= 1))
              break label819;
            i7 = 0;
            if (i7 >= ((Dvc)this.dvcVos.get(i6)).innerDvcVos.size())
              break label784;
            localDvc4 = (Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7);
            if (i1 != 0)
              break label778;
          }
          label778: for (boolean bool6 = false; ; bool6 = true)
          {
            localDvc4.setOnOff(bool6);
            i7++;
            break label703;
            i2 = 1;
            break;
          }
          label784: Dvc localDvc3 = (Dvc)this.dvcVos.get(i6);
          if (i1 == 0);
          for (boolean bool5 = false; ; bool5 = true)
          {
            localDvc3.setOnOff(bool5);
            bool1 = true;
            i = i6;
            label819: i6++;
            break;
          }
          label831: bool1 = false;
          if (k != 11)
          {
            int i3 = 1 + this.roomIndex;
            bool1 = false;
            if (i3 == m)
            {
              int i4 = 0;
              if (i4 < this.dvcVos.size())
              {
                if ((((Dvc)this.dvcVos.get(i4)).isGroup()) && (((Dvc)this.dvcVos.get(i4)).innerDvcVos.size() > 1))
                {
                  int i5 = 0;
                  if (i5 < ((Dvc)this.dvcVos.get(i4)).innerDvcVos.size())
                  {
                    Dvc localDvc2;
                    if (((Dvc)((Dvc)this.dvcVos.get(i4)).innerDvcVos.get(i5)).getmIndex() == n)
                    {
                      localDvc2 = (Dvc)((Dvc)this.dvcVos.get(i4)).innerDvcVos.get(i5);
                      if (i1 != 0)
                        break label1023;
                    }
                    label1023: for (boolean bool4 = false; ; bool4 = true)
                    {
                      localDvc2.setOnOff(bool4);
                      bool1 = true;
                      i = i4;
                      i5++;
                      break;
                    }
                  }
                }
                Dvc localDvc1;
                if (((Dvc)this.dvcVos.get(i4)).getmIndex() == n)
                {
                  localDvc1 = (Dvc)this.dvcVos.get(i4);
                  if (i1 != 0)
                    break label1087;
                }
                label1087: for (boolean bool3 = false; ; bool3 = true)
                {
                  localDvc1.setOnOff(bool3);
                  bool1 = true;
                  i4++;
                  break;
                }
              }
            }
          }
        }
      }
    }
    label1093: localArrayList.add(Boolean.valueOf(bool1));
    localArrayList.add(Integer.valueOf(i));
    return localArrayList;
  }

  public void handleCrop(Activity paramActivity, Intent paramIntent, ImageView paramImageView)
  {
    new Thread(new Runnable(paramActivity, paramIntent, paramImageView)
    {
      public void run()
      {
        Bitmap localBitmap = BitmapUtils.squareCropRectangle(BitmapUtils.getBitmapFromUri(this.val$activity.getApplicationContext(), Crop.getOutput(this.val$result)), this.val$imageView.getWidth(), BitmapUtils.dp2px(this.val$activity, 220.0F));
        String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
        this.val$activity.runOnUiThread(new Runnable(localBitmap)
        {
          public void run()
          {
            RoomBusiness.1.this.val$imageView.setImageBitmap(this.val$tempBm);
          }
        });
        RoomBusiness.this.setter.putValue(RoomBusiness.this.roomName, str);
        FileUtil.saveMyBitmap(str, localBitmap, "/ltech/led/image");
      }
    }).start();
  }

  public DeviceStatusVo handleDeviceStatus(byte[] paramArrayOfByte)
  {
    DeviceStatusVo localDeviceStatusVo = new DeviceStatusVo();
    String str = StringUtils.btye2Str(paramArrayOfByte);
    if ((str.length() >= 38) && (str.substring(18, 20).equals("80")))
    {
      int i3 = Integer.parseInt(str.substring(22, 24), 16);
      int i4 = Integer.parseInt(str.substring(24, 26), 16);
      int i5 = Integer.parseInt(str.substring(26, 28), 16);
      boolean bool3;
      if (Integer.parseInt(str.substring(28, 30), 16) == 1)
      {
        bool3 = true;
        if ((i3 == 11) || (1 + this.roomIndex != i4));
      }
      else
      {
        for (int i6 = 0; ; i6++)
        {
          if (i6 >= this.dvcVos.size())
            break label1531;
          boolean bool4 = ((Dvc)this.dvcVos.get(i6)).isGroup();
          if (((Dvc)this.dvcVos.get(i6)).innerDvcVos.size() > 1);
          for (boolean bool5 = true; ; bool5 = false)
          {
            if (!(bool5 & bool4))
              break label424;
            for (int i7 = 0; i7 < ((Dvc)this.dvcVos.get(i6)).innerDvcVos.size(); i7++)
            {
              if ((((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7)).getmIndex() != i5) || (((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7)).isOnLine() == bool3))
                continue;
              ((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7)).setIsOnLine(bool3);
              localDeviceStatusVo.isGroupMenberChange = true;
              localDeviceStatusVo.isChanged = true;
              if ((((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7)).isClickSeleted()) && (((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7)).getType() <= 12))
                localDeviceStatusVo.seletedDevice = ((Dvc)((Dvc)this.dvcVos.get(i6)).innerDvcVos.get(i7));
              localDeviceStatusVo.changedgroupPosi = i6;
            }
            bool3 = false;
            break;
          }
          label424: if ((((Dvc)this.dvcVos.get(i6)).getmIndex() != i5) || (((Dvc)this.dvcVos.get(i6)).isOnLine() == bool3))
            continue;
          ((Dvc)this.dvcVos.get(i6)).setIsOnLine(bool3);
          localDeviceStatusVo.isDeviceChange = true;
          localDeviceStatusVo.isChanged = true;
          if ((!((Dvc)this.dvcVos.get(i6)).isClickSeleted()) || (((Dvc)this.dvcVos.get(i6)).getType() > 12))
            continue;
          localDeviceStatusVo.seletedDevice = ((Dvc)this.dvcVos.get(i6));
        }
      }
    }
    else if ((str.length() >= 50) && (str.substring(18, 20).equals("A1")))
    {
      int i = Integer.parseInt(str.substring(26, 28), 16);
      int j = Integer.parseInt(str.substring(22, 24), 16);
      int k = Integer.parseInt(str.substring(24, 26), 16);
      boolean bool1;
      boolean bool2;
      if (Integer.parseInt(str.substring(28, 30), 16) == 1)
      {
        bool1 = true;
        if (Integer.parseInt(str.substring(48, 50), 16) != 0)
          break label883;
        bool2 = false;
        label661: System.out.println("状态呀：   roomposi = )" + j + " dType  " + i + "   dIndex = )" + k + " dOnOffStatus =" + bool1 + " isGroup =" + this.dvcVos.size());
        if (!bool2)
          break label996;
      }
      for (int i1 = 0; ; i1++)
      {
        if (i1 >= this.dvcVos.size())
          break label1531;
        if ((((Dvc)this.dvcVos.get(i1)).getGroupId() != k) || (!((Dvc)this.dvcVos.get(i1)).isGroup()) || (((Dvc)this.dvcVos.get(i1)).innerDvcVos.size() <= 1))
          continue;
        int i2 = 0;
        while (true)
          if (i2 < ((Dvc)this.dvcVos.get(i1)).innerDvcVos.size())
          {
            ((Dvc)((Dvc)this.dvcVos.get(i1)).innerDvcVos.get(i2)).setOnOff(bool1);
            i2++;
            continue;
            bool1 = false;
            break;
            label883: bool2 = true;
            break label661;
          }
        localDeviceStatusVo.isGroupMenberChange = true;
        localDeviceStatusVo.isDeviceChange = true;
        localDeviceStatusVo.isChanged = true;
        if ((((Dvc)this.dvcVos.get(i1)).isClickSeleted()) && (((Dvc)this.dvcVos.get(i1)).getType() <= 12))
          localDeviceStatusVo.seletedDevice = ((Dvc)this.dvcVos.get(i1));
        ((Dvc)this.dvcVos.get(i1)).setOnOff(bool1);
        System.out.println("状态呀： innerDvcChanged  ");
        localDeviceStatusVo.changedgroupPosi = i1;
      }
      label996: if ((i != 11) && (1 + this.roomIndex == j))
        for (int m = 0; m < this.dvcVos.size(); m++)
        {
          if ((((Dvc)this.dvcVos.get(m)).isGroup()) && (((Dvc)this.dvcVos.get(m)).innerDvcVos.size() > 1))
            for (int n = 0; n < ((Dvc)this.dvcVos.get(m)).innerDvcVos.size(); n++)
            {
              if (((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).getmIndex() != k)
                continue;
              ((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).setOnOff(bool1);
              localDeviceStatusVo.isGroupMenberChange = true;
              localDeviceStatusVo.isChanged = true;
              if ((((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).isClickSeleted()) && (((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).getType() <= 12))
                localDeviceStatusVo.seletedDevice = ((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n));
              System.out.println("状态呀： isGroup  " + bool2 + "  " + ((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).getName() + "  " + ((Dvc)((Dvc)this.dvcVos.get(m)).innerDvcVos.get(n)).isOnOff());
              localDeviceStatusVo.changedgroupPosi = m;
            }
          if (((Dvc)this.dvcVos.get(m)).getmIndex() != k)
            continue;
          ((Dvc)this.dvcVos.get(m)).setOnOff(bool1);
          localDeviceStatusVo.isChanged = true;
          if ((((Dvc)this.dvcVos.get(m)).isClickSeleted()) && (((Dvc)this.dvcVos.get(m)).getType() <= 12))
            localDeviceStatusVo.seletedDevice = ((Dvc)this.dvcVos.get(m));
          System.out.println("状态呀：" + ((Dvc)this.dvcVos.get(m)).getName() + "  " + ((Dvc)this.dvcVos.get(m)).isOnOff());
          localDeviceStatusVo.isDeviceChange = true;
        }
    }
    label1531: return localDeviceStatusVo;
  }

  public void handleHasGroupDevice()
  {
    HashMap localHashMap = new HashMap();
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < this.dvcVos.size(); i++)
    {
      if ((((Dvc)this.dvcVos.get(i)).getGroupId() == 0) || (localHashMap.get(((Dvc)this.dvcVos.get(i)).getGroupId() + "") != null))
        continue;
      ((Dvc)this.dvcVos.get(i)).setIsGroup(true);
      localHashMap.put(((Dvc)this.dvcVos.get(i)).getGroupId() + "", this.dvcVos.get(i));
      ((Dvc)this.dvcVos.get(i)).setIsGroup(false);
    }
    Iterator localIterator = localHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry2 = (Map.Entry)localIterator.next();
      for (int k = 0; k < this.dvcVos.size(); k++)
      {
        if (!(((Dvc)this.dvcVos.get(k)).getGroupId() + "").equals(localEntry2.getKey().toString()))
          continue;
        ((Dvc)this.dvcVos.get(k)).innerDvcVos.clear();
        localHashMap.put(localEntry2.getKey().toString(), this.dvcVos.get(k));
      }
    }
    int j = 0;
    if (j < this.dvcVos.size())
    {
      if (((Dvc)this.dvcVos.get(j)).getGroupId() != 0)
      {
        ((Dvc)this.dvcVos.get(j)).innerDvcVos.clear();
        ((Dvc)localHashMap.get(((Dvc)this.dvcVos.get(j)).getGroupId() + ""));
      }
      while (true)
      {
        j++;
        break;
        localArrayList.add(this.dvcVos.get(j));
      }
    }
    localHashMap.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator.next();
      localArrayList.add(-1 + localArrayList.size(), (Dvc)localEntry1.getValue());
    }
    this.dvcVos.clear();
    this.dvcVos.addAll(localArrayList);
  }

  public void heartbeat()
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendHeartbeatCmd(this.cmd);
  }

  public boolean hideDelBtn()
  {
    int i = 0;
    for (int j = 0; j < this.dvcVos.size(); j++)
    {
      if (((Dvc)this.dvcVos.get(j)).isShowDelBtn())
        i = 1;
      ((Dvc)this.dvcVos.get(j)).setIsShowDelBtn(false);
    }
    return i;
  }

  public void initDvcsData(int paramInt)
  {
    Home localHome = new RoomsBusiness(this.ct).getHome();
    this.dvcVos.clear();
    this.dvcVos.addAll(((Room)localHome.getRooms().get(paramInt)).getDvcVos());
  }

  public boolean isGroupInnerDvcDragOk()
  {
    ((Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.get(this.groupInnerDvcSeletedItemPosi)).setIsSeleted(false);
    return true;
  }

  public boolean isShowDelBtn()
  {
    int i = 0;
    for (int j = 0; j < this.dvcVos.size(); j++)
    {
      if (!((Dvc)this.dvcVos.get(j)).isShowDelBtn())
        continue;
      i = 1;
    }
    return i;
  }

  public void moveBulb2Group(Dvc paramDvc1, Dvc paramDvc2)
  {
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    switch (paramDvc2.getType())
    {
    default:
    case 11:
    case 9:
    case 10:
    case 8:
    }
    int i;
    while (true)
    {
      addUserId2Cmd(this.cmd);
      this.cmd.add(Integer.valueOf(18));
      this.cmd.add(Integer.valueOf(7));
      addDeviceId2Cmd(this.cmd, paramDvc1.getId());
      this.cmd.add(Integer.valueOf(paramDvc2.getmIndex()));
      this.cmd.add(Integer.valueOf(1));
      i = 0;
      for (int j = 0; j < this.cmd.size(); j++)
        i += ((Integer)this.cmd.get(j)).intValue();
      this.cmd.add(Integer.valueOf(69));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(71));
    }
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str2, 16));
    this.cmd.add(Integer.valueOf(str3, 16));
    this.cmd.add(Integer.valueOf(90));
  }

  public void moveOutGroup(Dvc paramDvc1, Dvc paramDvc2)
  {
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    switch (paramDvc2.getType())
    {
    default:
    case 11:
    case 9:
    case 10:
    case 8:
    }
    int i;
    while (true)
    {
      addUserId2Cmd(this.cmd);
      this.cmd.add(Integer.valueOf(18));
      this.cmd.add(Integer.valueOf(7));
      this.cmd.add(Integer.valueOf(136));
      addDeviceId2Cmd(this.cmd, paramDvc1.getId());
      this.cmd.add(Integer.valueOf(paramDvc2.getmIndex()));
      this.cmd.add(Integer.valueOf(1));
      i = 0;
      for (int j = 0; j < this.cmd.size(); j++)
        i += ((Integer)this.cmd.get(j)).intValue();
      this.cmd.add(Integer.valueOf(69));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(70));
      continue;
      this.cmd.add(Integer.valueOf(71));
    }
    String str1 = Integer.toHexString(i);
    String str2 = str1.substring(-2 + str1.length(), str1.length());
    String str3 = "";
    if (str1.length() == 3)
      str3 = str1.substring(0, 1);
    if (str1.length() == 4)
      str3 = str1.substring(0, 2);
    this.cmd.add(Integer.valueOf(str2, 16));
    this.cmd.add(Integer.valueOf(str3, 16));
    this.cmd.add(Integer.valueOf(90));
  }

  public void offRoomLight()
  {
    for (int i = 0; i < this.dvcVos.size(); i++)
      ((Dvc)this.dvcVos.get(i)).setAllOnOff(false);
  }

  public void onDeviceEdit()
  {
    for (int i = 0; i < -1 + this.dvcVos.size(); i++)
      ((Dvc)this.dvcVos.get(i)).setIsShowDelBtn(true);
  }

  public void onDragInGroup(String paramString, int paramInt1, int paramInt2)
  {
    Dvc localDvc1 = (Dvc)this.dvcVos.get(this.DvcSeletedItemPosi);
    Dvc localDvc2 = (Dvc)this.dvcVos.get(this.DvcTagItemPosi);
    localDvc2.setGroupId(paramInt2);
    localDvc1.innerDvcVos.clear();
    localDvc1.setGroupId(paramInt2);
    Dvc localDvc3 = createSimpleDvc(localDvc2);
    localDvc1.setIsClickSeleted(false);
    if (((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.size() == 0)
      ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.add(localDvc3);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.add(localDvc1);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setIsGroup(true);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setGroupId(paramInt2);
    if ((((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).getGroupName() == null) || (((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).getGroupName().equals("")))
      ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setGroupName(paramString + paramInt2);
    this.dvcVos.remove(this.dvcVos.get(this.DvcSeletedItemPosi));
  }

  public void onDragInGroupFailde()
  {
    ((Dvc)this.dvcVos.get(this.DvcSeletedItemPosi)).setIsSeleted(false);
  }

  public void onDragMove(int paramInt)
  {
    this.DvcTagItemPosi = paramInt;
  }

  public void onDragOutGroup(String paramString, int paramInt1, int paramInt2)
  {
    Dvc localDvc1 = (Dvc)this.dvcVos.get(this.DvcSeletedItemPosi);
    Dvc localDvc2 = (Dvc)this.dvcVos.get(this.DvcTagItemPosi);
    localDvc1.innerDvcVos.clear();
    Dvc localDvc3 = new Dvc();
    localDvc3.setName(localDvc2.getName());
    localDvc3.setType(localDvc2.getType());
    localDvc3.setId(localDvc2.getId());
    localDvc3.setmIndex(localDvc2.getmIndex());
    localDvc3.setRoomIndex(localDvc2.getRoomIndex());
    if (((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.size() == 0)
      ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.add(localDvc3);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).innerDvcVos.add(localDvc1);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setIsGroup(true);
    ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setGroupId(paramInt2);
    if (((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).getGroupName() == null)
      ((Dvc)this.dvcVos.get(this.DvcTagItemPosi)).setGroupName(paramString);
    this.dvcVos.remove(this.dvcVos.get(this.DvcSeletedItemPosi));
  }

  public void onDragStart(int paramInt)
  {
    this.DvcSeletedItemPosi = paramInt;
    ((Dvc)this.dvcVos.get(paramInt)).setIsSeleted(true);
  }

  public void onGroupInnerDeviceEdit(Dvc paramDvc, int paramInt)
  {
    for (int i = 0; i < paramDvc.innerDvcVos.size(); i++)
      ((Dvc)paramDvc.innerDvcVos.get(i)).setIsShowDelBtn(true);
  }

  public boolean onGroupInnerDvcDragFinish()
  {
    Dvc localDvc = (Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.remove(this.groupInnerDvcSeletedItemPosi);
    localDvc.setIsSeleted(false);
    if (((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.size() == 1)
    {
      ((Dvc)this.dvcVos.get(this.groupPosi)).setIsGroup(false);
      ((Dvc)this.dvcVos.get(this.groupPosi)).setInnerDvcVos(new ArrayList());
    }
    if (this.groupOutsideDvcTagItemPosi != -1)
    {
      this.dvcVos.add(this.groupOutsideDvcTagItemPosi, localDvc);
      return true;
    }
    this.dvcVos.add(-1 + this.dvcVos.size(), localDvc);
    return true;
  }

  public void onGroupInnerDvcDragMove(int paramInt)
  {
    this.groupOutsideDvcTagItemPosi = paramInt;
  }

  public void onGroupInnerDvcDragStart(int paramInt)
  {
    this.groupInnerDvcSeletedItemPosi = paramInt;
    try
    {
      ((Dvc)((Dvc)this.dvcVos.get(this.groupPosi)).innerDvcVos.get(this.groupInnerDvcSeletedItemPosi)).setIsSeleted(true);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void onInnerLightCancelSeleted()
  {
    for (int i = 0; i < this.groupInnerDvcVos.size(); i++)
      ((Dvc)this.groupInnerDvcVos.get(i)).setIsClickSeleted(false);
  }

  public void onInnerLightFinishLongClick()
  {
    for (int i = 0; i < this.groupInnerDvcVos.size(); i++)
      ((Dvc)this.groupInnerDvcVos.get(i)).setIsShowDelBtn(false);
  }

  public void onInnerLightLongClick()
  {
    for (int i = 0; i < this.groupInnerDvcVos.size(); i++)
      ((Dvc)this.groupInnerDvcVos.get(i)).setIsShowDelBtn(true);
  }

  public Dvc onInnerLightSeleted2(int paramInt)
  {
    for (int i = 0; i < this.groupInnerDvcVos.size(); i++)
      ((Dvc)this.groupInnerDvcVos.get(i)).setIsClickSeleted(false);
    ((Dvc)this.groupInnerDvcVos.get(paramInt)).setIsClickSeleted(true);
    System.out.println("groupInnerDvcVos.get(posi) = " + ((Dvc)this.groupInnerDvcVos.get(paramInt)).getName());
    return (Dvc)this.groupInnerDvcVos.get(paramInt);
  }

  public void onLightOnOff(int paramInt)
  {
    Dvc localDvc = (Dvc)this.dvcVos.get(paramInt);
    if (!((Dvc)this.dvcVos.get(paramInt)).isOnOff());
    for (boolean bool = true; ; bool = false)
    {
      localDvc.setOnOff(bool);
      return;
    }
  }

  public void onLightSeleted(int paramInt)
  {
    for (int i = 0; i < -1 + this.dvcVos.size(); i++)
      ((Dvc)this.dvcVos.get(i)).setIsClickSeleted(false);
    ((Dvc)this.dvcVos.get(paramInt)).setIsClickSeleted(true);
  }

  public void onRoomLight()
  {
    for (int i = 0; i < this.dvcVos.size(); i++)
      ((Dvc)this.dvcVos.get(i)).setAllOnOff(true);
  }

  public void panelAssociated(MyBusiness.MySendListener paramMySendListener)
  {
  }

  public void pickImage(Fragment paramFragment)
  {
    Crop.pickImage(paramFragment);
  }

  public void queryDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt1 + 1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void reSyncDeviceInfo(int paramInt1, int paramInt2, int paramInt3)
  {
    this.cmdCount = (-1 + this.cmdCount);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(35));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2 + 1));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
    sysoutCmdData(this.cmd);
  }

  public void renameGroupInnerDevice(int paramInt, String paramString)
  {
    ((Dvc)this.groupInnerDvcVos.get(paramInt)).setName(paramString);
  }

  public void resetBg(ImageView paramImageView)
  {
    paramImageView.setBackgroundResource(this.defaultBgRes);
    this.setter.putValue(this.roomName, "");
  }

  public boolean resolveDvcData(String paramString)
  {
    if (paramString.length() == 46)
    {
      String str1 = paramString.substring(20, paramString.length());
      if (Integer.parseInt(str1.substring(2, 4), 16) != 85)
        return false;
      String str2 = str1.substring(4, 12);
      int i = -1 + Integer.parseInt(str1.substring(12, 14), 16);
      int j = Integer.parseInt(str1.substring(14, 16), 16);
      int k = Integer.parseInt(str1.substring(16, 18), 16);
      Dvc localDvc1 = new Dvc();
      localDvc1.setmIndex(j);
      localDvc1.setRoomIndex(i);
      localDvc1.setId(str2);
      Home localHome;
      Dvc localDvc2;
      switch (k)
      {
      default:
        checkThisVoInOtherRoom(localDvc1.getmIndex());
        localHome = (Home)getBean4ClassName(this.mac, Home.class);
        this.dvcVos.clear();
        this.dvcVos.addAll(((Room)localHome.getRooms().get(this.roomIndex)).getDvcVos());
        localDvc2 = new Dvc();
        localDvc2.setName(this.context.getString(R.string.add));
        localDvc2.setType(22);
        if (this.dvcVos.size() != 0)
          break;
        this.dvcVos.add(localDvc1);
        this.dvcVos.add(localDvc2);
      case 65:
      case 66:
      case 67:
      case 68:
      case 81:
      case 84:
      case 34:
      case 35:
      case 36:
      case 37:
      case 97:
      case 98:
      }
      while (true)
      {
        if (i > 8)
          Log.i("", "");
        ((Room)localHome.getRooms().get(i)).setDvcVos(this.dvcVos);
        putData4ClassName(this.mac, localHome);
        return true;
        localDvc1.setType(11);
        localDvc1.setName(this.context.getString(2131100345) + j);
        break;
        localDvc1.setType(9);
        localDvc1.setName(this.context.getString(2131100345) + j);
        break;
        localDvc1.setType(8);
        localDvc1.setName(this.context.getString(2131100345) + j);
        break;
        localDvc1.setType(12);
        localDvc1.setName(this.context.getString(2131100345) + j);
        break;
        localDvc1.setType(14);
        localDvc1.setName(this.context.getString(2131100386));
        break;
        localDvc1.setName(this.ct.getString(2131100045));
        localDvc1.setType(19);
        break;
        localDvc1.setType(15);
        localDvc1.setName(this.context.getString(2131100258));
        break;
        localDvc1.setType(16);
        localDvc1.setName(this.context.getString(2131100258));
        break;
        localDvc1.setType(17);
        localDvc1.setName(this.context.getString(2131100258));
        break;
        localDvc1.setType(18);
        localDvc1.setName(this.context.getString(2131100258));
        break;
        localDvc1.setType(21);
        localDvc1.setName(this.ct.getString(2131100526));
        break;
        localDvc1.setName(this.ct.getString(2131099906));
        localDvc1.setType(13);
        break;
        this.dvcVos.remove(-1 + this.dvcVos.size());
        this.dvcVos.add(localDvc1);
        this.dvcVos.add(localDvc2);
      }
    }
    return false;
  }

  public void resortListviewData()
  {
    this.listviewDvcVos.clear();
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = 0;
    if (i < this.dvcVos.size())
    {
      int j;
      int k;
      label82: int n;
      label111: int i1;
      if (((Dvc)this.dvcVos.get(i)).getType() == 8)
      {
        j = 1;
        if (((Dvc)this.dvcVos.get(i)).getType() != 9)
          break label228;
        k = 1;
        int m = j | k;
        if (((Dvc)this.dvcVos.get(i)).getType() != 10)
          break label234;
        n = 1;
        i1 = m | n;
        if (((Dvc)this.dvcVos.get(i)).getType() != 11)
          break label240;
      }
      label228: label234: label240: for (int i2 = 1; ; i2 = 0)
      {
        if ((i2 | i1) == 0)
          break label267;
        if (!((Dvc)this.dvcVos.get(i)).isGroup())
          break label246;
        for (int i3 = 0; i3 < ((Dvc)this.dvcVos.get(i)).innerDvcVos.size(); i3++)
          localArrayList1.add(((Dvc)this.dvcVos.get(i)).innerDvcVos.get(i3));
        j = 0;
        break;
        k = 0;
        break label82;
        n = 0;
        break label111;
      }
      label246: localArrayList1.add(this.dvcVos.get(i));
      while (true)
      {
        i++;
        break;
        label267: if (((Dvc)this.dvcVos.get(i)).getType() == 22)
          continue;
        localArrayList2.add(this.dvcVos.get(i));
      }
    }
    if (localArrayList1.size() > 0)
    {
      ((Dvc)localArrayList1.get(0)).setIsShowLightTitle(true);
      this.listviewDvcVos.addAll(localArrayList1);
    }
    if (localArrayList2.size() > 0)
    {
      ((Dvc)localArrayList2.get(0)).setIsShowDeviceTitle(true);
      this.listviewDvcVos.addAll(localArrayList2);
    }
  }

  public void responseMessage(String paramString1, String paramString2)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString2, 16)));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(Integer.parseInt(paramString1, 16)));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void saveDeivceInRoom(int paramInt)
  {
    Home localHome = (Home)getBean4ClassName(this.mac, Home.class);
    ((Room)localHome.getRooms().get(paramInt)).setDvcVos(this.dvcVos);
    putData4ClassName(this.mac, localHome);
  }

  public void saveDevice(Dvc paramDvc)
  {
    checkThisVoInOtherRoom(paramDvc.getmIndex());
    int i = checkExitsVo(paramDvc);
    if (this.dvcVos.size() == 0)
    {
      Dvc localDvc = new Dvc();
      localDvc.setType(22);
      localDvc.setName(this.context.getString(R.string.add));
      if (i != -1)
      {
        this.dvcVos.add(i, paramDvc);
        if (paramDvc.isGroup())
        {
          if (i == -1)
            break label123;
          ((Dvc)this.dvcVos.get(i)).innerDvcVos.add(createSimpleDvc(paramDvc));
        }
      }
      while (true)
      {
        this.dvcVos.add(localDvc);
        return;
        this.dvcVos.add(paramDvc);
        break;
        label123: ((Dvc)this.dvcVos.get(-1 + this.dvcVos.size())).innerDvcVos.add(createSimpleDvc(paramDvc));
      }
    }
    int j;
    int k;
    label180: int n;
    label199: int i2;
    label218: int i4;
    label237: int i6;
    label256: int i7;
    if (paramDvc.getGroupId() == -1)
    {
      j = 1;
      if (paramDvc.getType() != 14)
        break label330;
      k = 1;
      int m = k | j;
      if (paramDvc.getType() != 15)
        break label336;
      n = 1;
      int i1 = m | n;
      if (paramDvc.getType() != 16)
        break label342;
      i2 = 1;
      int i3 = i1 | i2;
      if (paramDvc.getType() != 17)
        break label348;
      i4 = 1;
      int i5 = i3 | i4;
      if (paramDvc.getType() != 18)
        break label354;
      i6 = 1;
      i7 = i5 | i6;
      if (paramDvc.getType() != 21)
        break label360;
    }
    label330: label336: label342: label348: label354: label360: for (int i8 = 1; ; i8 = 0)
    {
      int i9 = i8 | i7;
      int i10 = paramDvc.getType();
      int i11 = 0;
      if (i10 == 13)
        i11 = 1;
      if ((i9 | i11) == 0)
        break label384;
      if (i == -1)
        break label366;
      this.dvcVos.add(i, paramDvc);
      return;
      j = 0;
      break;
      k = 0;
      break label180;
      n = 0;
      break label199;
      i2 = 0;
      break label218;
      i4 = 0;
      break label237;
      i6 = 0;
      break label256;
    }
    label366: this.dvcVos.add(-1 + this.dvcVos.size(), paramDvc);
    return;
    label384: for (int i12 = 0; ; i12++)
    {
      int i13 = this.dvcVos.size();
      int i14 = 0;
      int i15 = 0;
      if (i12 < i13)
      {
        if (((Dvc)this.dvcVos.get(i12)).getGroupId() != paramDvc.getGroupId())
          continue;
        i14 = 1;
        i15 = i12;
      }
      if (i14 == 0)
        break;
      ((Dvc)this.dvcVos.get(i15)).innerDvcVos.add(createSimpleDvc(paramDvc));
      return;
    }
    int i16 = -1 + this.dvcVos.size();
    ((Dvc)this.dvcVos.get(i16)).setIsGroup(true);
    this.dvcVos.add(i16, paramDvc);
    ((Dvc)this.dvcVos.get(i16)).innerDvcVos.add(createSimpleDvc(paramDvc));
  }

  public void saveGroupName(int paramInt, String paramString)
  {
    ((Dvc)this.dvcVos.get(paramInt)).setGroupName(paramString);
  }

  public void saveHome(Home paramHome)
  {
    putData4ClassName(this.mac, paramHome);
  }

  public void searchDevice(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(16));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(paramInt1 + 1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void searchMain(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    this.cmd.clear();
    this.cmd.add(Integer.valueOf(90));
    this.cmd.add(Integer.valueOf(165));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(17));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(16));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(17));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendActionUpColor(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, float paramFloat1, float paramFloat2)
  {
    addNormalHeadData(this.cmd);
    int i;
    if (paramBoolean)
    {
      i = 2;
      this.cmd.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(12));
      if (paramInt2 == 8)
        this.cmd.add(Integer.valueOf(67));
      if (paramInt2 == 12)
        this.cmd.add(Integer.valueOf(68));
      if (paramInt2 == 11)
        this.cmd.add(Integer.valueOf(65));
      if (paramInt2 == 9)
        this.cmd.add(Integer.valueOf(66));
      this.cmd.add(Integer.valueOf(paramInt3 + 1));
      if (!paramBoolean)
        break label339;
    }
    label339: for (int j = paramInt1; ; j = paramInt4)
    {
      this.cmd.add(Integer.valueOf(j));
      this.cmd.add(Integer.valueOf(paramInt5));
      this.cmd.add(Integer.valueOf(paramInt6));
      this.cmd.add(Integer.valueOf(paramInt7));
      this.cmd.add(Integer.valueOf(paramInt8));
      this.cmd.add(Integer.valueOf(paramInt9));
      this.cmd.add(Integer.valueOf((int)paramFloat1));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat1 - (int)paramFloat1))));
      this.cmd.add(Integer.valueOf((int)paramFloat2));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat2 - (int)paramFloat2))));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      crasySendCmdNoResponse(this.cmd);
      return;
      i = 1;
      break;
    }
  }

  public void sendBright(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, float paramFloat1, float paramFloat2)
  {
    addNormalHeadData(this.cmd);
    int i;
    if (paramBoolean)
    {
      i = 54;
      this.cmd.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(4));
      if (paramInt2 == 8)
        this.cmd.add(Integer.valueOf(67));
      if (paramInt2 == 12)
        this.cmd.add(Integer.valueOf(68));
      if (paramInt2 == 11)
        this.cmd.add(Integer.valueOf(65));
      if (paramInt2 == 9)
        this.cmd.add(Integer.valueOf(66));
      this.cmd.add(Integer.valueOf(paramInt3 + 1));
      if (!paramBoolean)
        break label225;
    }
    label225: for (int j = paramInt1; ; j = paramInt4)
    {
      this.cmd.add(Integer.valueOf(j));
      if (paramInt9 < 12)
        paramInt9 = 12;
      this.cmd.add(Integer.valueOf(paramInt9));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmdNoResponse(this.cmd);
      return;
      i = 53;
      break;
    }
  }

  public void sendCT(MyBusiness.MySendListener paramMySendListener, Dvc paramDvc, int paramInt1, int paramInt2, int paramInt3)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(8));
    this.cmd.add(Integer.valueOf(66));
    this.cmd.add(Integer.valueOf(1 + paramDvc.getRoomIndex()));
    ArrayList localArrayList = this.cmd;
    if (paramDvc.isGroup());
    for (int i = paramDvc.getGroupId(); ; i = 1 + paramDvc.getmIndex())
    {
      localArrayList.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt2));
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(paramInt3));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
    }
  }

  public void sendColor(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8, int paramInt9, float paramFloat1, float paramFloat2)
  {
    System.out.println(toString(paramInt5, paramInt6, paramInt7, paramInt8, paramInt9));
    addNormalHeadData(this.cmd);
    int i;
    if (paramBoolean)
    {
      i = 2;
      this.cmd.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(12));
      if (paramInt2 == 8)
        this.cmd.add(Integer.valueOf(67));
      if (paramInt2 == 12)
        this.cmd.add(Integer.valueOf(68));
      if (paramInt2 == 11)
        this.cmd.add(Integer.valueOf(65));
      if (paramInt2 == 9)
        this.cmd.add(Integer.valueOf(66));
      this.cmd.add(Integer.valueOf(paramInt3 + 1));
      if (!paramBoolean)
        break label359;
    }
    label359: for (int j = paramInt1; ; j = paramInt4)
    {
      this.cmd.add(Integer.valueOf(j));
      this.cmd.add(Integer.valueOf(paramInt5));
      this.cmd.add(Integer.valueOf(paramInt6));
      this.cmd.add(Integer.valueOf(paramInt7));
      this.cmd.add(Integer.valueOf(paramInt8));
      this.cmd.add(Integer.valueOf(paramInt9));
      this.cmd.add(Integer.valueOf((int)paramFloat1));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat1 - (int)paramFloat1))));
      this.cmd.add(Integer.valueOf((int)paramFloat2));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat2 - (int)paramFloat2))));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmdNoResponse(this.cmd);
      return;
      i = 1;
      break;
    }
  }

  public void sendDIMnCTnRGB(Dvc paramDvc, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat1, float paramFloat2)
  {
    addNormalHeadData(this.cmd);
    ArrayList localArrayList1 = this.cmd;
    int i;
    label68: int j;
    if (paramDvc.isGroup())
    {
      i = 2;
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(12));
      if (paramInt4 != 11)
        break label316;
      this.cmd.add(Integer.valueOf(65));
      this.cmd.add(Integer.valueOf(1 + paramDvc.getRoomIndex()));
      ArrayList localArrayList2 = this.cmd;
      if (!paramDvc.isGroup())
        break label339;
      j = paramDvc.getGroupId();
      label104: localArrayList2.add(Integer.valueOf(j));
      if (paramInt4 != 11)
        break label348;
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt1));
    }
    while (true)
    {
      if (paramInt3 < 12)
        paramInt3 = 12;
      this.cmd.add(Integer.valueOf(paramInt3));
      this.cmd.add(Integer.valueOf((int)paramFloat1));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat1 - (int)paramFloat1))));
      this.cmd.add(Integer.valueOf((int)paramFloat2));
      this.cmd.add(Integer.valueOf((int)(100.0F * (paramFloat2 - (int)paramFloat2))));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmdNoResponse(this.cmd);
      return;
      i = 1;
      break;
      label316: if (paramInt4 != 9)
        break label68;
      this.cmd.add(Integer.valueOf(66));
      break label68;
      label339: j = paramDvc.getmIndex();
      break label104;
      label348: if (paramInt4 != 9)
        continue;
      this.cmd.add(Integer.valueOf(paramInt2));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(0));
      this.cmd.add(Integer.valueOf(0));
    }
  }

  public ArrayList<Integer> sendMusic(boolean paramBoolean1, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean2)
  {
    addNormalHeadData(this.cmd);
    ArrayList localArrayList = this.cmd;
    if (paramBoolean1);
    for (int i = 4; ; i = 3)
    {
      localArrayList.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(4));
      this.cmd.add(Integer.valueOf(67));
      this.cmd.add(Integer.valueOf(paramInt2));
      this.cmd.add(Integer.valueOf(paramInt1));
      this.cmd.add(Integer.valueOf(paramInt3));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      return this.cmd;
    }
  }

  public void setBgView(Activity paramActivity, ImageView paramImageView)
  {
    new Thread(paramActivity, paramImageView)
    {
      public void run()
      {
        super.run();
        Bitmap localBitmap1 = BitmapUtils.zoomOutBM(BitmapFactory.decodeFile(RoomBusiness.this.bgFile.getPath()), BitmapUtils.dp2px(RoomBusiness.this.ct, 220.0F));
        int i = BitmapUtils.getExifOrientation(RoomBusiness.this.bgFile.getPath());
        RoomBusiness.this.setter.putValue(RoomBusiness.this.roomName, RoomBusiness.this.bgFile.getPath());
        if ((i == 90) || (i == 180) || (i == 270) || (i == 0))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(i);
          Bitmap localBitmap2 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix, false);
          this.val$activity.runOnUiThread(new Runnable(localBitmap2)
          {
            public void run()
            {
              RoomBusiness.2.this.val$imageView.setImageBitmap(this.val$bitmap);
            }
          });
        }
      }
    }
    .start();
  }

  public void setDvcStatusListener()
  {
  }

  public void setGroupInnerDvcVos(List<Dvc> paramList)
  {
    this.groupInnerDvcVos.clear();
    this.groupInnerDvcVos.addAll(paramList);
  }

  public void setGroupPosi(int paramInt)
  {
    this.groupPosi = paramInt;
  }

  public void setRoomIndex(int paramInt)
  {
    this.roomIndex = paramInt;
  }

  public void startListener(MyBusiness.MySendListener paramMySendListener, String paramString)
  {
    setMySendListener(paramMySendListener);
    listenerCmd(this.dvcVos, paramString);
    Log.i("", "");
  }

  public void synTime(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(67));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void syncDeviceInfo(int paramInt1, int paramInt2)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(35));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt1 + 1));
    this.cmd.add(Integer.valueOf(paramInt2));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
    sysoutCmdData(this.cmd);
  }

  public void syncDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(35));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(paramInt1));
    this.cmd.add(Integer.valueOf(paramInt2 + 1));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
    sysoutCmdData(this.cmd);
  }

  public byte[] sysoutCmdData(ArrayList<Integer> paramArrayList)
  {
    byte[] arrayOfByte = new byte[paramArrayList.size()];
    for (int i = 0; i < paramArrayList.size(); i++)
      arrayOfByte[i] = (byte)((Integer)paramArrayList.get(i)).intValue();
    return arrayOfByte;
  }

  public int tenToSixteen(int paramInt)
  {
    return Integer.valueOf(Integer.toHexString(paramInt), 16).intValue();
  }

  public String toString(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    return "sendColor{, r=" + paramInt1 + ", g=" + paramInt2 + ", b=" + paramInt3 + ", w=" + paramInt4 + ", brt=" + paramInt5 + '}';
  }

  public void udataDvcs(List<Integer> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < paramList.size(); i++)
      for (int j = 0; j < this.dvcVos.size(); j++)
      {
        if (((Integer)paramList.get(i)).intValue() != ((Dvc)this.dvcVos.get(j)).getmIndex())
          continue;
        localArrayList.add(this.dvcVos.get(j));
      }
    this.dvcVos.clear();
    this.dvcVos.addAll(localArrayList);
    Dvc localDvc = new Dvc();
    localDvc.setName(this.context.getString(R.string.add));
    localDvc.setType(22);
    this.dvcVos.add(localDvc);
  }

  public void updataDeviceBrt(int paramInt1, int paramInt2)
  {
    for (int i = 0; i < this.dvcVos.size(); i++)
    {
      boolean bool1 = ((Dvc)this.dvcVos.get(i)).isGroup();
      boolean bool2;
      if (((Dvc)this.dvcVos.get(i)).innerDvcVos.size() > 1)
        bool2 = true;
      while (true)
      {
        if ((bool2 & bool1))
        {
          for (int j = 0; j < ((Dvc)this.dvcVos.get(i)).innerDvcVos.size(); j++)
          {
            if (((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(j)).getmIndex() != paramInt1)
              continue;
            ((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(j)).setBrtProgress(paramInt2);
          }
          bool2 = false;
          continue;
        }
        if (((Dvc)this.dvcVos.get(i)).getmIndex() != paramInt1)
          break;
        ((Dvc)this.dvcVos.get(i)).setBrtProgress(paramInt2);
      }
    }
  }

  public void updataDeviceGridData(int paramInt, boolean paramBoolean)
  {
    for (int i = 0; i < this.dvcVos.size(); i++)
    {
      boolean bool1 = ((Dvc)this.dvcVos.get(i)).isGroup();
      boolean bool2;
      if (((Dvc)this.dvcVos.get(i)).innerDvcVos.size() > 1)
        bool2 = true;
      while (true)
      {
        if ((bool2 & bool1))
        {
          for (int j = 0; j < ((Dvc)this.dvcVos.get(i)).innerDvcVos.size(); j++)
          {
            if (((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(j)).getmIndex() != paramInt)
              continue;
            ((Dvc)((Dvc)this.dvcVos.get(i)).innerDvcVos.get(j)).setOnOff(paramBoolean);
          }
          bool2 = false;
          continue;
        }
        if (((Dvc)this.dvcVos.get(i)).getmIndex() != paramInt)
          break;
        ((Dvc)this.dvcVos.get(i)).setOnOff(paramBoolean);
      }
    }
  }

  public void updataGroupDvc(int paramInt, List<Dvc> paramList)
  {
    ((Dvc)this.dvcVos.get(paramInt)).innerDvcVos.clear();
    ((Dvc)this.dvcVos.get(paramInt)).innerDvcVos.addAll(paramList);
  }

  public class a
  {
    public int b;
    public int brt;
    public int g;
    int groupId;
    boolean isGroup;
    int mIndex;
    public int r;
    int roomIndex;
    float touchXPercent;
    float touchYPercent;
    int type;
    public int w;

    public a()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.RoomBusiness
 * JD-Core Version:    0.6.0
 */