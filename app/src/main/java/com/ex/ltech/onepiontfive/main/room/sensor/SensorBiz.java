package com.ex.ltech.onepiontfive.main.room.sensor;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.vo.RepeatDayVo;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.SensorVo;
import java.util.ArrayList;

public class SensorBiz extends MyBusiness
{
  ArrayList<String> blubIndex2Hex = new ArrayList();
  ArrayList<String> blubOnOff2Hex = new ArrayList();
  private Context c;
  ArrayList<Integer> cmd = new ArrayList();
  public Home home;
  private String mac;
  SensorVo sensorVo;

  public SensorBiz(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
    init();
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.sensorVo = getCacheSensorVo();
  }

  public SensorBiz(Context paramContext, int paramInt1, int paramInt2)
  {
    super(paramContext);
    this.c = paramContext;
    init();
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.sensorVo = ((Dvc)((Room)this.home.getRooms().get(paramInt1)).getDvcVos().get(paramInt2)).getSensorVo();
  }

  private void init()
  {
    this.mac = UserFerences.getUserFerences(this.c).getValue("GateWayMacIdKey");
  }

  public SensorVo getCacheSensorVo()
  {
    this.sensorVo = ((SensorVo)getCacheBean(SensorVo.class));
    if (this.sensorVo == null)
      this.sensorVo = new SensorVo();
    return this.sensorVo;
  }

  public SensorVo getSensorVo()
  {
    if (this.sensorVo == null)
      this.sensorVo = new SensorVo();
    return this.sensorVo;
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

  public void saveCacheData()
  {
    putCacheData(this.sensorVo);
  }

  public void saveCacheData(SensorVo paramSensorVo)
  {
    putCacheData(paramSensorVo);
  }

  public void saveSensor2Room(int paramInt1, int paramInt2, SensorVo paramSensorVo)
  {
    ((Dvc)((Room)this.home.getRooms().get(paramInt1)).getDvcVos().get(paramInt2)).setSensorVo(paramSensorVo);
    putData4ClassName(this.mac, this.home);
  }

  public void sendSensorTouchBlub(MyBusiness.MySendListener paramMySendListener, SensorVo paramSensorVo, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(26));
    this.cmd.add(Integer.valueOf(19));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(81));
    this.cmd.add(Integer.valueOf(0));
    ArrayList localArrayList1 = this.cmd;
    int i;
    if (paramSensorVo.isOpen())
      i = 1;
    while (true)
    {
      localArrayList1.add(Integer.valueOf(i));
      ArrayList localArrayList2 = paramSensorVo.getRooms();
      if (localArrayList2 == null)
        break;
      this.blubIndex2Hex.clear();
      this.blubOnOff2Hex.clear();
      int i1 = 0;
      while (true)
        if (i1 < 32)
        {
          this.blubIndex2Hex.add("0");
          this.blubOnOff2Hex.add("0");
          i1++;
          continue;
          i = 0;
          break;
        }
      for (int i2 = 0; i2 < localArrayList2.size(); i2++)
        for (int i5 = 0; i5 < ((Room)localArrayList2.get(i2)).getExpandableLvInnerItemVos().size(); i5++)
        {
          if (!((RoomLvChildVo)((Room)localArrayList2.get(i2)).getExpandableLvInnerItemVos().get(i5)).isSwich())
            continue;
          this.blubIndex2Hex.remove(-1 + ((RoomLvChildVo)((Room)localArrayList2.get(i2)).getExpandableLvInnerItemVos().get(i5)).getDvcIndex());
          this.blubIndex2Hex.add(-1 + ((RoomLvChildVo)((Room)localArrayList2.get(i2)).getExpandableLvInnerItemVos().get(i5)).getDvcIndex(), "1");
        }
      for (int i3 = 0; i3 < localArrayList2.size(); i3++)
        for (int i4 = 0; i4 < ((Room)localArrayList2.get(i3)).getExpandableLvInnerItemVos().size(); i4++)
        {
          if (!((RoomLvChildVo)((Room)localArrayList2.get(i3)).getExpandableLvInnerItemVos().get(i4)).isSwich())
            continue;
          this.blubOnOff2Hex.remove(-1 + ((RoomLvChildVo)((Room)localArrayList2.get(i3)).getExpandableLvInnerItemVos().get(i4)).getDvcIndex());
          this.blubOnOff2Hex.add(-1 + ((RoomLvChildVo)((Room)localArrayList2.get(i3)).getExpandableLvInnerItemVos().get(i4)).getDvcIndex(), "1");
        }
    }
    String str1 = "";
    for (int j = 0; j < this.blubIndex2Hex.size(); j++)
      str1 = str1 + (String)this.blubIndex2Hex.get(j) + "";
    String str2 = new StringBuffer(str1).reverse().toString();
    this.cmd.add(Integer.valueOf(str2.substring(24, 32), 2));
    this.cmd.add(Integer.valueOf(str2.substring(16, 24), 2));
    this.cmd.add(Integer.valueOf(str2.substring(8, 16), 2));
    this.cmd.add(Integer.valueOf(str2.substring(0, 8), 2));
    String str3 = "";
    for (int k = 0; k < this.blubOnOff2Hex.size(); k++)
      str3 = str3 + (String)this.blubOnOff2Hex.get(k) + "";
    String str4 = new StringBuffer(str3).reverse().toString();
    this.cmd.add(Integer.valueOf(str4.substring(24, 32), 2));
    this.cmd.add(Integer.valueOf(str4.substring(16, 24), 2));
    this.cmd.add(Integer.valueOf(str4.substring(8, 16), 2));
    this.cmd.add(Integer.valueOf(str4.substring(0, 8), 2));
    ArrayList localArrayList3 = paramSensorVo.getRepeatDayVos();
    String str5 = "";
    String str6;
    ArrayList localArrayList4;
    if (((RepeatDayVo)localArrayList3.get(0)).isSeleted())
    {
      str6 = "01111111";
      this.cmd.add(Integer.valueOf(Integer.parseInt(str6, 2)));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getStartMin())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getStartHour())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getEndMin())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getEndHout())));
      this.cmd.add(Integer.valueOf(paramSensorVo.getDelayType()));
      localArrayList4 = this.cmd;
      if (!paramSensorVo.isPush())
        break label1106;
    }
    label1106: for (int n = 1; ; n = 0)
    {
      localArrayList4.add(Integer.valueOf(n));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      int m = 1;
      if (m < localArrayList3.size())
      {
        if (((RepeatDayVo)localArrayList3.get(m)).isSeleted());
        for (str5 = str5 + "1"; ; str5 = str5 + "0")
        {
          m++;
          break;
        }
      }
      str6 = new StringBuffer(str5 + "0").reverse().toString();
      break;
    }
  }

  public void sendSensorTouchScene(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(26));
    this.cmd.add(Integer.valueOf(12));
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(97));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(30));
    this.cmd.add(Integer.valueOf(10));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void sendSensorTouchScene(MyBusiness.MySendListener paramMySendListener, SensorVo paramSensorVo, int paramInt)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(26));
    this.cmd.add(Integer.valueOf(12));
    this.cmd.add(Integer.valueOf(paramInt));
    this.cmd.add(Integer.valueOf(81));
    this.cmd.add(Integer.valueOf(1));
    ArrayList localArrayList1 = this.cmd;
    int i;
    ArrayList localArrayList2;
    String str1;
    if (paramSensorVo.isOpen())
    {
      i = 1;
      localArrayList1.add(Integer.valueOf(i));
      this.cmd.add(Integer.valueOf(1 + paramSensorVo.getTouchScenePosi()));
      localArrayList2 = paramSensorVo.getRepeatDayVos();
      str1 = "";
      if (!((RepeatDayVo)localArrayList2.get(0)).isSeleted())
        break label335;
    }
    for (String str2 = "1111111"; ; str2 = new StringBuffer(str1).reverse().toString())
    {
      this.cmd.add(Integer.valueOf(Integer.parseInt(str2, 2)));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getStartMin())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getStartHour())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getEndMin())));
      this.cmd.add(Integer.valueOf(Integer.parseInt(paramSensorVo.getEndHout())));
      this.cmd.add(Integer.valueOf(paramSensorVo.getDelayType()));
      ArrayList localArrayList3 = this.cmd;
      boolean bool = paramSensorVo.isPush();
      int k = 0;
      if (bool)
        k = 1;
      localArrayList3.add(Integer.valueOf(k));
      this.cmd.add(Integer.valueOf(1));
      addCheckSumData(this.cmd);
      this.cmd.add(Integer.valueOf(22));
      sendCmd(this.cmd);
      return;
      i = 0;
      break;
      label335: int j = 1;
      if (j >= localArrayList2.size())
        continue;
      if (((RepeatDayVo)localArrayList2.get(j)).isSeleted());
      for (str1 = str1 + "1"; ; str1 = str1 + "0")
      {
        j++;
        break;
      }
    }
  }

  public void syncDeviceInfo(MyBusiness.MySendListener paramMySendListener, int paramInt1, int paramInt2, int paramInt3)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(35));
    this.cmd.add(Integer.valueOf(3));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(paramInt2 + 1));
    this.cmd.add(Integer.valueOf(paramInt3));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void testCmdThree(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(27));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.SensorBiz
 * JD-Core Version:    0.6.0
 */