package com.ex.ltech.hongwai.vo;

import com.ex.ltech.led.MyApp;
import java.util.ArrayList;

public class Ct1ScenesVo
{
  public ArrayList<Ct1SceneVo> ct1SceneVos = new ArrayList();

  public void initCt1SceneVos()
  {
    if (this.ct1SceneVos.size() == 0)
    {
      Ct1SceneVo localCt1SceneVo1 = new Ct1SceneVo();
      localCt1SceneVo1.name = MyApp.getApp().getString(2131099976);
      localCt1SceneVo1.irCt1Brt = 255;
      localCt1SceneVo1.irCt1C = 0;
      localCt1SceneVo1.irCt1W = 255;
      this.ct1SceneVos.add(localCt1SceneVo1);
      Ct1SceneVo localCt1SceneVo2 = new Ct1SceneVo();
      localCt1SceneVo2.name = MyApp.getApp().getString(2131099977);
      localCt1SceneVo2.irCt1Brt = 255;
      localCt1SceneVo2.irCt1C = 80;
      localCt1SceneVo2.irCt1W = 175;
      this.ct1SceneVos.add(localCt1SceneVo2);
      Ct1SceneVo localCt1SceneVo3 = new Ct1SceneVo();
      localCt1SceneVo3.name = MyApp.getApp().getString(2131099978);
      localCt1SceneVo3.irCt1Brt = 255;
      localCt1SceneVo3.irCt1C = 120;
      localCt1SceneVo3.irCt1W = 135;
      this.ct1SceneVos.add(localCt1SceneVo3);
      Ct1SceneVo localCt1SceneVo4 = new Ct1SceneVo();
      localCt1SceneVo4.name = MyApp.getApp().getString(2131099979);
      localCt1SceneVo4.irCt1Brt = 204;
      localCt1SceneVo4.irCt1C = 135;
      localCt1SceneVo4.irCt1W = 120;
      this.ct1SceneVos.add(localCt1SceneVo4);
      Ct1SceneVo localCt1SceneVo5 = new Ct1SceneVo();
      localCt1SceneVo5.name = MyApp.getApp().getString(2131099980);
      localCt1SceneVo5.irCt1Brt = 204;
      localCt1SceneVo5.irCt1C = 200;
      localCt1SceneVo5.irCt1W = 55;
      this.ct1SceneVos.add(localCt1SceneVo5);
      Ct1SceneVo localCt1SceneVo6 = new Ct1SceneVo();
      localCt1SceneVo6.name = MyApp.getApp().getString(2131099981);
      localCt1SceneVo6.irCt1Brt = 178;
      localCt1SceneVo6.irCt1C = 225;
      localCt1SceneVo6.irCt1W = 30;
      this.ct1SceneVos.add(localCt1SceneVo6);
      Ct1SceneVo localCt1SceneVo7 = new Ct1SceneVo();
      localCt1SceneVo7.name = MyApp.getApp().getString(2131099982);
      localCt1SceneVo7.irCt1Brt = 15;
      localCt1SceneVo7.irCt1C = 255;
      localCt1SceneVo7.irCt1W = 255;
      this.ct1SceneVos.add(localCt1SceneVo7);
      Ct1SceneVo localCt1SceneVo8 = new Ct1SceneVo();
      localCt1SceneVo8.name = MyApp.getApp().getString(2131099983);
      localCt1SceneVo8.irCt1Brt = 8;
      localCt1SceneVo8.irCt1C = 255;
      localCt1SceneVo8.irCt1W = 0;
      this.ct1SceneVos.add(localCt1SceneVo8);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.Ct1ScenesVo
 * JD-Core Version:    0.6.0
 */