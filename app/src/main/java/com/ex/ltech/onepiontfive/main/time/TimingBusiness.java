package com.ex.ltech.onepiontfive.main.time;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.newscene.ExpandableLvSceneBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.Scene;
import com.ex.ltech.onepiontfive.main.vo.Scenes;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import com.ex.ltech.onepiontfive.main.vo.Timing;
import com.ex.ltech.onepiontfive.main.vo.TimingInnerDeivces;
import com.ex.ltech.onepiontfive.main.vo.Timings;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;

public class TimingBusiness extends MyBusiness
{
  ArrayList<Integer> cmd = new ArrayList();
  private Context context;
  int dataLenthPosi = 10;
  Home home;
  boolean isAllSeleted;
  private String lastReturnData = "";
  private String mac;
  RoomBusiness roomBusiness;
  ArrayList<String> seletedDvc2Hex = new ArrayList();
  int seletedDvcType = -1;
  ArrayList<String> seletedPanel2Hex = new ArrayList();
  TimingInnerDeivces timingInnerDevice;
  public Timings timings;
  private int[] uId = new int[4];
  private String userIdHexString;

  public TimingBusiness(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.timingInnerDevice = new TimingInnerDeivces();
    this.roomBusiness = new RoomBusiness(this.context);
    this.userIdHexString = Integer.toHexString(SharedPreferencesUtil.queryIntValue("appId").intValue()).toUpperCase();
    for (int i = this.userIdHexString.length(); i < 8; i++)
      this.userIdHexString = ("0" + this.userIdHexString);
    this.uId[0] = Integer.parseInt(this.userIdHexString.substring(0, 2), 16);
    this.uId[1] = Integer.parseInt(this.userIdHexString.substring(2, 4), 16);
    this.uId[2] = Integer.parseInt(this.userIdHexString.substring(4, 6), 16);
    this.uId[3] = Integer.parseInt(this.userIdHexString.substring(6, 8), 16);
  }

  public List compareWithReturnInfo(String paramString)
  {
    int i = paramString.length();
    ArrayList localArrayList;
    if (i >= 32)
    {
      boolean bool1 = paramString.substring(18, 20).equalsIgnoreCase("a2");
      localArrayList = null;
      if (bool1)
      {
        paramString.substring(0, i - 6);
        boolean bool2 = addCheckSumData(paramString);
        int j = Integer.parseInt(paramString.substring(20, 22), 16);
        localArrayList = new ArrayList();
        if ((bool2) && (j > 2))
        {
          String str = paramString.substring(26, -8 + paramString.length());
          for (int k = 0; k < j - 2; k++)
            localArrayList.add(Integer.valueOf(Integer.parseInt(str.substring(k * 2, 2 + k * 2), 16)));
        }
      }
    }
    else
    {
      localArrayList = null;
    }
    return localArrayList;
  }

  public Timing getCacheData()
  {
    return (Timing)getCacheBean(Timing.class);
  }

  public Timing getCacheSensorVo()
  {
    Timing localTiming = (Timing)getCacheBean(SensorVo.class);
    if (localTiming == null)
      localTiming = new Timing();
    return localTiming;
  }

  public List<String> getHourDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 24)
    {
      if (i < 10);
      for (String str = "0" + i; ; str = "" + i)
      {
        localArrayList.add(str);
        i++;
        break;
      }
    }
    return localArrayList;
  }

  public List<String> getMinDate()
  {
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    if (i < 60)
    {
      if (i < 10);
      for (String str = "0" + i; ; str = "" + i)
      {
        localArrayList.add(str);
        i++;
        break;
      }
    }
    return localArrayList;
  }

  public TimingInnerDeivces getRoomListCacheData()
  {
    return (TimingInnerDeivces)getCacheBean(TimingInnerDeivces.class);
  }

  public TimingInnerDeivces getSeletedTimingDeviceListData(List<Integer> paramList)
  {
    TimingInnerDeivces localTimingInnerDeivces = getUnseletedTimingDeviceListData();
    for (int i = 0; i < paramList.size(); i++)
      for (int j = 0; j < localTimingInnerDeivces.getRooms().size(); j++)
        for (int k = 0; k < ((Room)localTimingInnerDeivces.getRooms().get(j)).getExpandableLvInnerItemVos().size(); k++)
        {
          if (((Integer)paramList.get(i)).intValue() != ((RoomLvChildVo)((Room)localTimingInnerDeivces.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).getDvcIndex())
            continue;
          ((RoomLvChildVo)((Room)localTimingInnerDeivces.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).setIsSeted(true);
        }
    return this.timingInnerDevice;
  }

  @Deprecated
  public TimingInnerDeivces getTimingDeviceListData(List<Integer> paramList)
  {
    ArrayList localArrayList1 = this.home.getRooms();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      localArrayList2.clear();
      int j = 0;
      if (j < ((Room)localArrayList1.get(i)).getDvcVos().size())
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        int m;
        label96: int n;
        label109: int i2;
        if (!localDvc.isGroup())
          if (localDvc.getType() == 8)
          {
            m = 1;
            if (localDvc.getType() != 9)
              break label253;
            n = 1;
            int i1 = n | m;
            if (localDvc.getType() != 11)
              break label259;
            i2 = 1;
            label129: if ((i2 | i1) != 0)
            {
              RoomLvChildVo localRoomLvChildVo2 = new RoomLvChildVo();
              localRoomLvChildVo2.setInnerDeviceName(localDvc.getName());
              localRoomLvChildVo2.setInnerItemType(localDvc.getType());
              if ((paramList != null) && (paramList.contains(Integer.valueOf(localDvc.getmIndex()))) && ((localDvc.getType() == 8) || (localDvc.getType() == 9) || (localDvc.getType() == 11)))
                localRoomLvChildVo2.setIsSeted(true);
              localRoomLvChildVo2.setDvcIndex(localDvc.getmIndex());
              localArrayList2.add(localRoomLvChildVo2);
            }
          }
        while (true)
        {
          j++;
          break;
          m = 0;
          break label96;
          label253: n = 0;
          break label109;
          label259: i2 = 0;
          break label129;
          for (int k = 0; k < localDvc.innerDvcVos.size(); k++)
          {
            RoomLvChildVo localRoomLvChildVo1 = new RoomLvChildVo();
            localRoomLvChildVo1.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(k)).getName());
            localRoomLvChildVo1.setInnerItemType(((Dvc)localDvc.innerDvcVos.get(k)).getType());
            localRoomLvChildVo1.setDvcIndex(((Dvc)localDvc.innerDvcVos.get(k)).getmIndex());
            localArrayList2.add(localRoomLvChildVo1);
          }
        }
      }
      ((Room)localArrayList1.get(i)).setExpandableLvInnerItemVos(localArrayList2);
    }
    for (int i3 = 0; i3 < localArrayList1.size(); i3++)
    {
      if (((Room)localArrayList1.get(i3)).getExpandableLvInnerItemVos().size() >= 1)
        continue;
      localArrayList1.remove(i3);
      i3--;
    }
    this.timingInnerDevice.setRooms(localArrayList1);
    return this.timingInnerDevice;
  }

  public Timings getTimings()
  {
    this.timings = ((Timings)getBean4ClassName(this.mac, Timings.class));
    if (this.timings == null)
      this.timings = new Timings();
    return this.timings;
  }

  public Timings getTimings4DB()
  {
    this.timings = ((Timings)getBean4ClassName(this.mac, Timings.class));
    return this.timings;
  }

  public TimingInnerDeivces getUnseletedTimingDeviceListData()
  {
    ArrayList localArrayList1 = this.home.getRooms();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      localArrayList2.clear();
      int j = 0;
      if (j < ((Room)localArrayList1.get(i)).getDvcVos().size())
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        int m;
        label94: int n;
        label107: int i2;
        label127: int i4;
        if (!localDvc.isGroup())
          if (localDvc.getType() == 8)
          {
            m = 1;
            if (localDvc.getType() != 12)
              break label213;
            n = 1;
            int i1 = n | m;
            if (localDvc.getType() != 9)
              break label219;
            i2 = 1;
            int i3 = i1 | i2;
            if (localDvc.getType() != 11)
              break label225;
            i4 = 1;
            label147: if ((i4 | i3) != 0)
            {
              RoomLvChildVo localRoomLvChildVo2 = new RoomLvChildVo();
              localRoomLvChildVo2.setInnerDeviceName(localDvc.getName());
              localRoomLvChildVo2.setInnerItemType(localDvc.getType());
              localRoomLvChildVo2.setDvcIndex(localDvc.getmIndex());
              localArrayList2.add(localRoomLvChildVo2);
            }
          }
        while (true)
        {
          j++;
          break;
          m = 0;
          break label94;
          label213: n = 0;
          break label107;
          label219: i2 = 0;
          break label127;
          label225: i4 = 0;
          break label147;
          for (int k = 0; k < localDvc.innerDvcVos.size(); k++)
          {
            RoomLvChildVo localRoomLvChildVo1 = new RoomLvChildVo();
            localRoomLvChildVo1.setInnerDeviceName(((Dvc)localDvc.innerDvcVos.get(k)).getName());
            localRoomLvChildVo1.setInnerItemType(((Dvc)localDvc.innerDvcVos.get(k)).getType());
            localRoomLvChildVo1.setDvcIndex(((Dvc)localDvc.innerDvcVos.get(k)).getmIndex());
            localArrayList2.add(localRoomLvChildVo1);
          }
        }
      }
      ((Room)localArrayList1.get(i)).setExpandableLvInnerItemVos(localArrayList2);
    }
    for (int i5 = 0; i5 < localArrayList1.size(); i5++)
    {
      if (((Room)localArrayList1.get(i5)).getExpandableLvInnerItemVos().size() >= 1)
        continue;
      localArrayList1.remove(i5);
      i5--;
    }
    this.timingInnerDevice.setRooms(localArrayList1);
    return this.timingInnerDevice;
  }

  public boolean onDeviceSeleted(int paramInt1, int paramInt2)
  {
    RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2);
    if (!((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).isSeted());
    for (boolean bool = true; ; bool = false)
    {
      localRoomLvChildVo.setIsSeted(bool);
      return true;
    }
  }

  public List<Integer> onDeviceSeletedSyncDvc(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    int i = 1;
    ArrayList localArrayList = new ArrayList();
    for (int j = 0; j < this.timingInnerDevice.getRooms().size(); j++)
      for (int k = 0; k < ((Room)this.timingInnerDevice.getRooms().get(j)).getExpandableLvInnerItemVos().size(); k++)
      {
        if (((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).getDvcIndex() != paramInt3)
          continue;
        i = 0;
        this.seletedDvcType = ((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(j)).getExpandableLvInnerItemVos().get(k)).getInnerItemType();
      }
    if (paramBoolean)
      localArrayList.add(Integer.valueOf(paramInt3));
    if ((i == 0) && (((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).getInnerItemType() != this.seletedDvcType))
      return null;
    RoomLvChildVo localRoomLvChildVo = (RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2);
    if (!((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).isSeted());
    for (boolean bool = true; ; bool = false)
    {
      localRoomLvChildVo.setIsSeted(bool);
      this.seletedDvcType = ((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).getInnerItemType();
      return localArrayList;
    }
  }

  public void onGroupSeleted(int paramInt)
  {
    Room localRoom = (Room)this.timingInnerDevice.getRooms().get(paramInt);
    if (!((Room)this.timingInnerDevice.getRooms().get(paramInt)).isSeleted());
    for (boolean bool = true; ; bool = false)
    {
      localRoom.setSeleted(bool);
      for (int i = 0; i < ((Room)this.timingInnerDevice.getRooms().get(paramInt)).getExpandableLvInnerItemVos().size(); i++)
        ((RoomLvChildVo)((Room)this.timingInnerDevice.getRooms().get(paramInt)).getExpandableLvInnerItemVos().get(i)).setIsSeted(((Room)this.timingInnerDevice.getRooms().get(paramInt)).isSeleted());
    }
  }

  public void queryTimeInfo(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(34));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void reSyncDeviceInfo(int paramInt)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(38));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
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

  public void saveCacheData(Timing paramTiming)
  {
    putCacheData(paramTiming);
  }

  public void saveRoomListCacheData()
  {
    putCacheData(this.timingInnerDevice);
  }

  public void saveTagTimingCacheData(int paramInt)
  {
    putCacheData(this.timings.timingList.get(paramInt));
  }

  public boolean saveTimeData(String paramString)
  {
    Timing localTiming;
    ArrayList localArrayList1;
    StringBuffer localStringBuffer1;
    int j;
    int k;
    int m;
    int i1;
    String str2;
    try
    {
      int i = paramString.length();
      if (!paramString.substring(18, 20).equalsIgnoreCase("a6"))
        break label1695;
      paramString.substring(0, i - 6);
      boolean bool = addCheckSumData(paramString);
      Integer.parseInt(paramString.substring(20, 22), 16);
      if ((!bool) || (i < 62))
        break label1695;
      localTiming = new Timing();
      if (Integer.parseInt(paramString.substring(32, 34), 16) == 0)
        localTiming.setSwich(false);
      while (true)
      {
        localTiming.setOrder(Integer.parseInt(paramString.substring(34, 36), 16));
        localArrayList1 = new ArrayList();
        RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
        localRepeatDayVo1.setDay(this.context.getString(2131100055));
        localRepeatDayVo1.setSeleted(false);
        localArrayList1.add(localRepeatDayVo1);
        RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
        localRepeatDayVo2.setDay(this.context.getString(2131100003));
        localRepeatDayVo2.setSeleted(false);
        localArrayList1.add(localRepeatDayVo2);
        RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
        localRepeatDayVo3.setDay(this.context.getString(2131100004));
        localRepeatDayVo3.setSeleted(false);
        localArrayList1.add(localRepeatDayVo3);
        RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
        localRepeatDayVo4.setDay(this.context.getString(2131100005));
        localRepeatDayVo4.setSeleted(false);
        localArrayList1.add(localRepeatDayVo4);
        RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
        localRepeatDayVo5.setDay(this.context.getString(2131100006));
        localRepeatDayVo5.setSeleted(false);
        localArrayList1.add(localRepeatDayVo5);
        RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
        localRepeatDayVo6.setDay(this.context.getString(2131100007));
        localRepeatDayVo6.setSeleted(false);
        localArrayList1.add(localRepeatDayVo6);
        RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
        localRepeatDayVo7.setDay(this.context.getString(2131100008));
        localRepeatDayVo7.setSeleted(false);
        localArrayList1.add(localRepeatDayVo7);
        RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
        localRepeatDayVo8.setDay(this.context.getString(2131100009));
        localRepeatDayVo8.setSeleted(false);
        localArrayList1.add(localRepeatDayVo8);
        paramString.substring(38, 40);
        localStringBuffer1 = new StringBuffer();
        localStringBuffer1.append(" ");
        String str1 = StringUtils.hexString2binaryString(paramString.substring(36, 38));
        j = 0;
        k = 0;
        m = 0;
        String[] arrayOfString1 = str1.split("");
        int n = arrayOfString1.length;
        i1 = 0;
        if (i1 >= n)
          break;
        str2 = arrayOfString1[i1];
        if (str2.equals(""))
          break label1700;
        if (!str2.equals("1"))
          break label1697;
        switch (m)
        {
        case 0:
          localTiming.setSwich(true);
        case 1:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
        case 7:
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return false;
    }
    label1412: label1449: label1459: ArrayList localArrayList2;
    int i5;
    StringBuffer localStringBuffer2;
    if (!str2.equals("0"))
    {
      ((RepeatDayVo)localArrayList1.get(0)).setSeleted(true);
      localStringBuffer1.append(this.context.getString(R.string.once)).append("\t\t");
      j = 1;
      break label1697;
      if (!str2.equals("0"))
      {
        ((RepeatDayVo)localArrayList1.get(7)).setSeleted(true);
        localStringBuffer1.append(this.context.getString(2131100394)).append("\t\t");
        k++;
        break label1697;
        if (!str2.equals("0"))
        {
          ((RepeatDayVo)localArrayList1.get(6)).setSeleted(true);
          localStringBuffer1.append(this.context.getString(2131100404)).append("\t\t");
          k++;
          break label1697;
          if (!str2.equals("0"))
          {
            ((RepeatDayVo)localArrayList1.get(5)).setSeleted(true);
            localStringBuffer1.append(this.context.getString(2131100066)).append("\t\t");
            k++;
            break label1697;
            if (!str2.equals("0"))
            {
              ((RepeatDayVo)localArrayList1.get(4)).setSeleted(true);
              localStringBuffer1.append(this.context.getString(2131100071)).append("\t\t");
              k++;
              break label1697;
              if (!str2.equals("0"))
              {
                ((RepeatDayVo)localArrayList1.get(3)).setSeleted(true);
                localStringBuffer1.append(this.context.getString(2131100430)).append("\t\t");
                k++;
                break label1697;
                if (!str2.equals("0"))
                {
                  ((RepeatDayVo)localArrayList1.get(2)).setSeleted(true);
                  localStringBuffer1.append(this.context.getString(2131100467)).append("\t\t");
                  k++;
                  break label1697;
                  if (!str2.equals("0"))
                  {
                    ((RepeatDayVo)localArrayList1.get(1)).setSeleted(true);
                    localStringBuffer1.append(this.context.getString(2131100240)).append("\t\t");
                    k++;
                    break label1697;
                    String str3;
                    if (j == 0)
                      str3 = StringUtils.reverse(localStringBuffer1.toString());
                    while (true)
                    {
                      localTiming.setShotDaysStr(str3.trim());
                      if (k != 7)
                        break;
                      localTiming.setShotDaysStr(this.context.getString(2131100055));
                      int i2 = 0;
                      while (true)
                        if (i2 < localArrayList1.size())
                        {
                          ((RepeatDayVo)localArrayList1.get(i2)).setSeleted(false);
                          i2++;
                          continue;
                          str3 = localStringBuffer1.toString();
                          break;
                        }
                      ((RepeatDayVo)localArrayList1.get(0)).setSeleted(true);
                    }
                    localTiming.setRepeatDayVos(localArrayList1);
                    int i3 = Integer.parseInt(paramString.substring(38, 40), 16);
                    int i4 = Integer.parseInt(paramString.substring(40, 42), 16);
                    StringBuilder localStringBuilder1 = new StringBuilder();
                    Object localObject1;
                    StringBuilder localStringBuilder2;
                    if (i4 < 10)
                    {
                      localObject1 = "0" + i4;
                      localStringBuilder2 = localStringBuilder1.append(localObject1).append(":");
                      if (i3 >= 10)
                        break label1449;
                    }
                    for (Object localObject2 = "0" + i3; ; localObject2 = Integer.valueOf(i3))
                    {
                      localTiming.setTime(localObject2);
                      localTiming.setHour(i4);
                      localTiming.setMin(i3);
                      localTiming.setSeletedScenePosi(-1 + Integer.parseInt(paramString.substring(44, 46), 16));
                      if (Integer.parseInt(paramString.substring(42, 44), 16) != 0)
                        break label1459;
                      ExpandableLvSceneBusiness localExpandableLvSceneBusiness = new ExpandableLvSceneBusiness(this.context);
                      localTiming.setSeletedScenePosi(-1 + Integer.parseInt(paramString.substring(44, 46), 16));
                      localTiming.setOnOff(true);
                      if (localExpandableLvSceneBusiness.getSmartScenes().smartScenes.size() > localTiming.getSeletedScenePosi())
                        localTiming.setSeletedInfo(((Scene)localExpandableLvSceneBusiness.getSmartScenes().smartScenes.get(localTiming.getSeletedScenePosi())).getName());
                      this.timings.timingList.add(localTiming);
                      putData4ClassName(this.mac, this.timings);
                      return true;
                      localObject1 = Integer.valueOf(i4);
                      break;
                    }
                    localTiming.setOnOff(false);
                    localArrayList2 = new ArrayList();
                    i5 = 0;
                    localStringBuffer2 = new StringBuffer();
                  }
                }
              }
            }
          }
        }
      }
    }
    label1695: label1697: label1700: label1706: label1715: for (int i6 = 0; ; i6++)
    {
      String[] arrayOfString2;
      int i7;
      if (i6 < 4)
      {
        arrayOfString2 = StringUtils.reverse(StringUtils.hexString2binaryString(paramString.substring(46 + i6 * 2, 48 + i6 * 2))).split("");
        i7 = arrayOfString2.length;
      }
      for (int i8 = 0; ; i8++)
      {
        if (i8 >= i7)
          break label1715;
        String str4 = arrayOfString2[i8];
        if (str4.equals(""))
          continue;
        if (str4.equals("1"))
        {
          localArrayList2.add(Integer.valueOf(1 + i5 * 1));
          if (this.roomBusiness.getDvcBymIndex(1 + i5 * 1) != null)
          {
            localStringBuffer2.append(this.roomBusiness.getDvcBymIndex(1 + i5 * 1).getName()).append("\t");
            break label1706;
            localTiming.setSelectedDevicesmIndex(localArrayList2);
            localTiming.setSeletedInfo(localStringBuffer2.toString());
            localTiming.setTimingInnerDeivces(getSeletedTimingDeviceListData(localArrayList2));
            localTiming.setOnOff(false);
            if (!localTiming.getShotDaysStr().equalsIgnoreCase(this.context.getString(R.string.once)))
              break label1412;
            localTiming.setIsJustOnce(true);
            break label1412;
            return false;
            m++;
            i1++;
            break;
          }
        }
        i5++;
      }
    }
  }

  public void saveTiming(Timing paramTiming)
  {
    this.timings = getTimings();
    int i = this.timings.timingList.size();
    int j = 0;
    int k = 0;
    if (j < i)
    {
      if (paramTiming.getOrder() == ((Timing)this.timings.timingList.get(j)).getOrder())
        k = 1;
    }
    else
    {
      if ((this.timings.timingList.size() <= 0) || (k == 0))
        break label117;
      this.timings.timingList.remove(j);
      this.timings.timingList.add(0, paramTiming);
    }
    while (true)
    {
      putData4ClassName(this.mac, this.timings);
      return;
      j++;
      break;
      label117: this.timings.timingList.add(paramTiming);
    }
  }

  public void seletedAddDevice()
  {
    boolean bool;
    if (!this.isAllSeleted)
    {
      bool = true;
      this.isAllSeleted = bool;
    }
    for (int i = 0; ; i++)
    {
      if (i >= this.timingInnerDevice.getRooms().size())
        return;
      ((Room)this.timingInnerDevice.getRooms().get(i)).setSeleted(this.isAllSeleted);
      int j = 0;
      while (true)
        if (j < ((Room)this.timingInnerDevice.getRooms().get(i)).getDvcVos().size())
        {
          ((Dvc)((Room)this.timingInnerDevice.getRooms().get(i)).getDvcVos().get(j)).setIsSeleted(this.isAllSeleted);
          j++;
          continue;
          bool = false;
          break;
        }
    }
  }

  public void sendNewCreateTiming(Timing paramTiming, int paramInt)
  {
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(19));
    this.cmd.add(Integer.valueOf(this.uId[3]));
    this.cmd.add(Integer.valueOf(this.uId[2]));
    this.cmd.add(Integer.valueOf(this.uId[1]));
    this.cmd.add(Integer.valueOf(this.uId[0]));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(paramTiming.getOrder()));
    String str1 = "";
    if (paramTiming.getShotDaysStr().equals(this.ct.getString(R.string.once)))
      str1 = "10000000";
    while (true)
    {
      this.seletedDvc2Hex.clear();
      int j = 0;
      while (true)
        if (j < 32)
        {
          this.seletedDvc2Hex.add("0");
          j++;
          continue;
          List localList = paramTiming.getRepeatDayVos();
          if (((RepeatDayVo)localList.get(0)).isSeleted())
          {
            str1 = "01111111";
            break;
          }
          int i = 1;
          label212: if (i < localList.size())
            if (!((RepeatDayVo)localList.get(i)).isSeleted())
              break label269;
          label269: for (str1 = 1 + str1; ; str1 = 0 + str1)
          {
            i++;
            break label212;
            break;
          }
        }
    }
    this.seletedPanel2Hex.clear();
    for (int k = 0; k < 8; k++)
      this.seletedPanel2Hex.add("0");
    this.cmd.add(Integer.valueOf(Integer.parseInt(str1, 2)));
    this.cmd.add(Integer.valueOf(paramTiming.getMin()));
    this.cmd.add(Integer.valueOf(paramTiming.getHour()));
    ArrayList localArrayList1 = this.cmd;
    int m;
    TimingInnerDeivces localTimingInnerDeivces;
    ArrayList localArrayList2;
    if (paramTiming.isOnOff())
    {
      m = 85;
      localArrayList1.add(Integer.valueOf(m));
      if (!paramTiming.isOnOff())
      {
        localTimingInnerDeivces = paramTiming.getTimingInnerDeivces();
        localArrayList2 = new ArrayList();
      }
    }
    else
    {
      for (int i7 = 0; ; i7++)
      {
        if (i7 >= localTimingInnerDeivces.getRooms().size())
          break label541;
        int i10 = 0;
        while (true)
          if (i10 < ((Room)localTimingInnerDeivces.getRooms().get(i7)).getExpandableLvInnerItemVos().size())
          {
            if (((RoomLvChildVo)((Room)localTimingInnerDeivces.getRooms().get(i7)).getExpandableLvInnerItemVos().get(i10)).isSeted())
              localArrayList2.add(((Room)localTimingInnerDeivces.getRooms().get(i7)).getExpandableLvInnerItemVos().get(i10));
            i10++;
            continue;
            m = 0;
            break;
          }
      }
      label541: if ((localTimingInnerDeivces.getRooms().size() == 0) && (paramTiming.getSelectedDevicesmIndex() != null))
        for (int i9 = 0; i9 < paramTiming.getSelectedDevicesmIndex().size(); i9++)
        {
          RoomLvChildVo localRoomLvChildVo = new RoomLvChildVo();
          if (((Integer)paramTiming.getSelectedDevicesmIndex().get(i9)).intValue() <= 0)
            continue;
          localRoomLvChildVo.setDvcIndex(((Integer)paramTiming.getSelectedDevicesmIndex().get(i9)).intValue());
          localArrayList2.add(localRoomLvChildVo);
        }
      int i8 = 0;
      if (i8 < localArrayList2.size())
      {
        if (((RoomLvChildVo)localArrayList2.get(i8)).getDvcIndex() < 143)
        {
          this.seletedDvc2Hex.remove(-1 + ((RoomLvChildVo)localArrayList2.get(i8)).getDvcIndex());
          this.seletedDvc2Hex.add(-1 + ((RoomLvChildVo)localArrayList2.get(i8)).getDvcIndex(), "1");
        }
        while (true)
        {
          ((RoomLvChildVo)localArrayList2.get(i8)).getInnerItemType();
          i8++;
          break;
          this.seletedDvc2Hex.remove(-142 + (-1 + ((RoomLvChildVo)localArrayList2.get(i8)).getDvcIndex()));
          this.seletedDvc2Hex.add(-142 + (-1 + ((RoomLvChildVo)localArrayList2.get(i8)).getDvcIndex()), "1");
        }
      }
    }
    if (paramTiming.isOnOff())
      this.cmd.add(Integer.valueOf(1 + paramTiming.getSeletedScenePosi()));
    String str2;
    while (true)
    {
      str2 = "";
      for (int i6 = -1 + this.seletedDvc2Hex.size(); i6 > -1; i6--)
        str2 = str2 + (String)this.seletedDvc2Hex.get(i6);
      int n;
      label889: int i1;
      label901: int i3;
      label920: int i4;
      if (this.seletedDvcType == 15)
      {
        n = 1;
        if (this.seletedDvcType != 16)
          break label969;
        i1 = 1;
        int i2 = i1 | n;
        if (this.seletedDvcType != 17)
          break label975;
        i3 = 1;
        i4 = i2 | i3;
        if (this.seletedDvcType != 18)
          break label981;
      }
      label969: label975: label981: for (int i5 = 1; ; i5 = 0)
      {
        if ((i5 | i4) == 0)
          break label987;
        this.cmd.add(Integer.valueOf(47));
        break;
        n = 0;
        break label889;
        i1 = 0;
        break label901;
        i3 = 0;
        break label920;
      }
      label987: this.cmd.add(Integer.valueOf(79));
    }
    if (!paramTiming.isOnOff())
    {
      this.cmd.add(Integer.valueOf(str2.substring(24, 32), 2));
      this.cmd.add(Integer.valueOf(str2.substring(16, 24), 2));
      this.cmd.add(Integer.valueOf(str2.substring(8, 16), 2));
      this.cmd.add(Integer.valueOf(str2.substring(0, 8), 2));
    }
    this.cmd.add(this.dataLenthPosi, Integer.valueOf(this.cmd.size() - this.dataLenthPosi));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void syncDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(38));
    this.cmd.add(Integer.valueOf(2));
    this.cmd.add(Integer.valueOf(4));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmdWithoutCrasySend(this.cmd);
  }

  public void sysTime()
  {
    ArrayList localArrayList = new ArrayList();
    addNormalHeadData(localArrayList);
    localArrayList.add(Integer.valueOf(25));
    localArrayList.add(Integer.valueOf(8));
    localArrayList.add(Integer.valueOf(1));
    String str1 = DateFmtUtil.getYY();
    String str2 = DateFmtUtil.getMonthNow();
    String str3 = DateFmtUtil.getDD();
    String str4 = DateFmtUtil.getHH();
    String str5 = DateFmtUtil.getMin();
    String str6 = DateFmtUtil.getSS();
    String str7 = DateFmtUtil.getSSS();
    String str8 = Integer.toHexString(1000 * Integer.valueOf(str6).intValue() + Integer.valueOf(str7).intValue());
    if (str8.length() == 3)
      str8 = "0" + str8;
    while (true)
    {
      localArrayList.add(Integer.valueOf(str8.substring(2, 4), 16));
      localArrayList.add(Integer.valueOf(str8.substring(0, 2), 16));
      localArrayList.add(Integer.valueOf(Integer.parseInt(str5)));
      localArrayList.add(Integer.valueOf(Integer.parseInt(str4)));
      localArrayList.add(Integer.valueOf(Integer.parseInt(str3)));
      localArrayList.add(Integer.valueOf(Integer.parseInt(str2)));
      localArrayList.add(Integer.valueOf(Integer.parseInt(str1)));
      localArrayList.add(Integer.valueOf(1));
      addCheckSumData(localArrayList);
      localArrayList.add(Integer.valueOf(22));
      sendCmd(localArrayList);
      return;
      if (str8.length() == 2)
      {
        str8 = "00" + str8;
        continue;
      }
      if (str8.length() != 1)
        continue;
      str8 = "000" + str8;
    }
  }

  public void updateTiming(Timing paramTiming, int paramInt)
  {
    this.timings = getTimings();
    if (this.timings.timingList.size() > 0)
    {
      this.timings.timingList.remove(paramInt);
      this.timings.timingList.add(paramInt, paramTiming);
    }
    while (true)
    {
      putData4ClassName(this.mac, this.timings);
      return;
      this.timings.timingList.add(paramTiming);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.time.TimingBusiness
 * JD-Core Version:    0.6.0
 */