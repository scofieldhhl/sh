package com.ex.ltech.onepiontfive.main.room.sensor;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import com.ex.ltech.onepiontfive.main.vo.RoomLvChildVo;
import com.ex.ltech.onepiontfive.main.vo.SmartSensorVo;
import com.ex.ltech.onepiontfive.main.vo.SmartSensorsVo;
import et.song.etclass.ETDevice;
import et.song.etclass.ETGroup;
import java.util.ArrayList;
import java.util.List;

public class SensorBusiness extends MyBusiness
{
  private Context c;
  ArrayList<Integer> cmd = new ArrayList();
  Home home;
  private String mac;
  SmartSensorVo sensorVo;
  SmartSensorsVo smartSensorsVo;

  public SensorBusiness(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.sensorVo = new SmartSensorVo();
  }

  public SmartSensorVo getCacheData()
  {
    return (SmartSensorVo)getCacheBean(SmartSensorVo.class);
  }

  public ArrayList<RoomLvChildVo> getEnableAddTaskInnerItemVos()
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList3 = getCacheData().getRooms();
    for (int i = 0; i < localArrayList3.size(); i++)
    {
      ArrayList localArrayList4 = ((Room)localArrayList3.get(i)).getExpandableLvInnerItemVos();
      int j = 0;
      if (j >= localArrayList4.size())
        continue;
      boolean bool1 = ((RoomLvChildVo)localArrayList4.get(j)).isSwich();
      if (((RoomLvChildVo)localArrayList4.get(j)).getInnerDeviceStatus() != null);
      for (boolean bool2 = true; ; bool2 = false)
      {
        if ((bool2 | bool1))
        {
          localArrayList2.add(localArrayList4.get(j));
          localArrayList1.add(((Room)localArrayList3.get(i)).getName());
        }
        j++;
        break;
      }
    }
    return localArrayList2;
  }

  public SmartSensorVo getSmartSensorVo()
  {
    ArrayList localArrayList1 = this.home.getRooms();
    for (int i = 0; i < localArrayList1.size(); i++)
    {
      ArrayList localArrayList2 = new ArrayList();
      int j = 0;
      if (j < ((Room)localArrayList1.get(i)).getDvcVos().size())
      {
        Dvc localDvc = (Dvc)((Room)localArrayList1.get(i)).getDvcVos().get(j);
        if (localDvc.getRemoteDevices() == null)
        {
          RoomLvChildVo localRoomLvChildVo1 = new RoomLvChildVo();
          localRoomLvChildVo1.setInnerDeviceName(localDvc.getName());
          localRoomLvChildVo1.setInnerItemType(localDvc.getType());
          localArrayList2.add(localRoomLvChildVo1);
        }
        while (true)
        {
          j++;
          break;
          ETGroup localETGroup = localDvc.getRemoteDevices();
          for (int k = 0; k < -1 + localETGroup.getmDeviceList().size(); k++)
          {
            RoomLvChildVo localRoomLvChildVo2 = new RoomLvChildVo(localDvc.getName(), (ETDevice)localETGroup.GetItem(k));
            localRoomLvChildVo2.setInnerItemType(localRoomLvChildVo2.getRemoteDevice().GetRes());
            localArrayList2.add(localRoomLvChildVo2);
          }
        }
      }
      ((Room)localArrayList1.get(i)).setExpandableLvInnerItemVos(localArrayList2);
    }
    this.sensorVo.setRooms(localArrayList1);
    return this.sensorVo;
  }

  public SmartSensorsVo getSmartSensorsVo()
  {
    this.smartSensorsVo = ((SmartSensorsVo)getBean4ClassName(this.mac, SmartSensorsVo.class));
    if (this.smartSensorsVo == null)
      this.smartSensorsVo = new SmartSensorsVo();
    return this.smartSensorsVo;
  }

  public void onDeviceSwich(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    ((RoomLvChildVo)((Room)this.sensorVo.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setSwich(paramBoolean);
  }

  public void onTvDataResult(int paramInt1, int paramInt2, String paramString, boolean paramBoolean)
  {
    ((RoomLvChildVo)((Room)this.sensorVo.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setSwich(paramBoolean);
    ((RoomLvChildVo)((Room)this.sensorVo.getRooms().get(paramInt1)).getExpandableLvInnerItemVos().get(paramInt2)).setInnerDeviceStatus(paramString);
  }

  public void saveCacheData()
  {
    putCacheData(this.sensorVo);
  }

  public void setSensorCondition(String paramString)
  {
    this.sensorVo.setCondition(paramString);
  }

  public void testCmdOne(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(26));
    this.cmd.add(Integer.valueOf(19));
    this.cmd.add(Integer.valueOf(21));
    this.cmd.add(Integer.valueOf(97));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(0));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(0));
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

  public void testCmdTwo(MyBusiness.MySendListener paramMySendListener)
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
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.sensor.SensorBusiness
 * JD-Core Version:    0.6.0
 */