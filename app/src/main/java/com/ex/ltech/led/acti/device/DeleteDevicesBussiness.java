package com.ex.ltech.led.acti.device;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.ex.ltech.onepiontfive.main.vo.Dvc;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.PanelLampVO;
import com.ex.ltech.onepiontfive.main.vo.Room;
import java.util.ArrayList;
import java.util.List;

public class DeleteDevicesBussiness extends MyBusiness
{
  ArrayList<Integer> cmd = new ArrayList();
  int dIndex;
  public Home home;
  public List<Dvc> lights;
  private String mac;
  public List<PanelLampVO> panelLampVos;
  int panelPosi;
  int roomPosi;

  DeleteDevicesBussiness(Context paramContext)
  {
    super(paramContext);
    this.mac = UserFerences.getUserFerences(paramContext).getValue("GateWayMacIdKey");
  }

  public void deleteDevices(MyBusiness.MySendListener paramMySendListener)
  {
    setMySendListener(paramMySendListener);
    addNormalHeadData(this.cmd);
    this.cmd.add(Integer.valueOf(28));
    this.cmd.add(Integer.valueOf(1));
    this.cmd.add(Integer.valueOf(255));
    this.cmd.add(Integer.valueOf(1));
    addCheckSumData(this.cmd);
    this.cmd.add(Integer.valueOf(22));
    sendCmd(this.cmd);
  }

  public void initData()
  {
    this.home = ((Home)getBean4ClassName(this.mac, Home.class));
    this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
    if (this.lights.size() == 0)
      this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
  }

  public void initData4Cache()
  {
    this.home = ((Home)getCacheBean(Home.class));
    this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
    if (this.lights.size() == 0)
      this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
  }

  public void initData4Result()
  {
    this.home = ((Home)getCacheBean(Home.class));
    this.lights = ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).getInnerDvcVos();
    if (this.lights.size() == 0)
      this.lights = ((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos();
  }

  public void onProgressChanged(int paramInt1, int paramInt2)
  {
    ((Dvc)this.lights.get(paramInt1)).setBrtProgress(paramInt2);
  }

  public void putCacheData()
  {
    ((Dvc)((Room)this.home.getRooms().get(this.roomPosi)).getDvcVos().get(this.panelPosi)).setPanelLampVO(this.panelLampVos);
    putCacheData(this.home);
  }

  public void saveData()
  {
    putData4ClassName(this.mac, this.home);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.DeleteDevicesBussiness
 * JD-Core Version:    0.6.0
 */