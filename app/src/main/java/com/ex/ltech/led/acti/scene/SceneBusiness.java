package com.ex.ltech.led.acti.scene;

import android.app.Activity;
import android.content.res.Resources;
import com.ex.ltech.led.vo.SceneSysCustomItemVo;
import com.ex.ltech.led.vo.SceneSysInsideItemVo;
import java.util.ArrayList;
import java.util.List;

public class SceneBusiness
{
  private List<SceneSysInsideItemVo> InsideVos;
  private List<SceneSysCustomItemVo> customVos;
  private Activity pct;

  public SceneBusiness(Activity paramActivity)
  {
    this.pct = paramActivity;
  }

  public List<SceneSysInsideItemVo> getItemVos()
  {
    return this.InsideVos;
  }

  public List<SceneSysCustomItemVo> getSysCustomListVos()
  {
    this.customVos = new ArrayList();
    SceneSysCustomItemVo localSceneSysCustomItemVo1 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo1.setModeName("11111111");
    localSceneSysCustomItemVo1.setBlingName("非常亮");
    localSceneSysCustomItemVo1.setSeleted(false);
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492955)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492981)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492979)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492970)));
    localArrayList.add(Integer.valueOf(this.pct.getResources().getColor(2131492889)));
    localSceneSysCustomItemVo1.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo1);
    SceneSysCustomItemVo localSceneSysCustomItemVo2 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo2.setModeName("2222222");
    localSceneSysCustomItemVo2.setBlingName("非常亮");
    localSceneSysCustomItemVo2.setSeleted(false);
    localSceneSysCustomItemVo2.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo2);
    SceneSysCustomItemVo localSceneSysCustomItemVo3 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo3.setModeName("3333333");
    localSceneSysCustomItemVo3.setBlingName("非常亮");
    localSceneSysCustomItemVo3.setSeleted(false);
    localSceneSysCustomItemVo3.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo3);
    SceneSysCustomItemVo localSceneSysCustomItemVo4 = new SceneSysCustomItemVo();
    localSceneSysCustomItemVo4.setModeName("3333333");
    localSceneSysCustomItemVo4.setBlingName("非常亮");
    localSceneSysCustomItemVo4.setSeleted(false);
    localSceneSysCustomItemVo4.setColors(localArrayList);
    this.customVos.add(localSceneSysCustomItemVo4);
    return this.customVos;
  }

  public void initSysInsideListVos()
  {
    this.InsideVos = new ArrayList();
    SceneSysInsideItemVo localSceneSysInsideItemVo1 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo1.setTvName(this.pct.getString(2131100369));
    localSceneSysInsideItemVo1.setIvLeftRes(2130903710);
    localSceneSysInsideItemVo1.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo1);
    SceneSysInsideItemVo localSceneSysInsideItemVo2 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo2.setTvName(this.pct.getString(2131100371));
    localSceneSysInsideItemVo2.setIvLeftRes(2130903712);
    localSceneSysInsideItemVo2.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo2);
    SceneSysInsideItemVo localSceneSysInsideItemVo3 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo3.setTvName(this.pct.getString(2131100372));
    localSceneSysInsideItemVo3.setIvLeftRes(2130903713);
    localSceneSysInsideItemVo3.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo3);
    SceneSysInsideItemVo localSceneSysInsideItemVo4 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo4.setTvName(this.pct.getString(2131100373));
    localSceneSysInsideItemVo4.setIvLeftRes(2130903714);
    localSceneSysInsideItemVo4.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo4);
    SceneSysInsideItemVo localSceneSysInsideItemVo5 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo5.setTvName(this.pct.getString(2131100374));
    localSceneSysInsideItemVo5.setIvLeftRes(2130903715);
    localSceneSysInsideItemVo5.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo5);
    SceneSysInsideItemVo localSceneSysInsideItemVo6 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo6.setTvName(this.pct.getString(2131100375));
    localSceneSysInsideItemVo6.setIvLeftRes(2130903716);
    localSceneSysInsideItemVo6.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo6);
    SceneSysInsideItemVo localSceneSysInsideItemVo7 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo7.setTvName(this.pct.getString(2131100376));
    localSceneSysInsideItemVo7.setIvLeftRes(2130903717);
    localSceneSysInsideItemVo7.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo7);
    SceneSysInsideItemVo localSceneSysInsideItemVo8 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo8.setTvName(this.pct.getString(2131100377));
    localSceneSysInsideItemVo8.setIvLeftRes(2130903718);
    localSceneSysInsideItemVo8.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo8);
    SceneSysInsideItemVo localSceneSysInsideItemVo9 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo9.setTvName(this.pct.getString(2131100378));
    localSceneSysInsideItemVo9.setIvLeftRes(2130903719);
    localSceneSysInsideItemVo9.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo9);
    SceneSysInsideItemVo localSceneSysInsideItemVo10 = new SceneSysInsideItemVo();
    localSceneSysInsideItemVo10.setTvName(this.pct.getString(2131100370));
    localSceneSysInsideItemVo10.setIvLeftRes(2130903711);
    localSceneSysInsideItemVo10.setSeleted(false);
    this.InsideVos.add(localSceneSysInsideItemVo10);
  }

  public void onCustomItemClick(int paramInt)
  {
    SceneSysCustomItemVo localSceneSysCustomItemVo = (SceneSysCustomItemVo)this.customVos.get(paramInt);
    if (!localSceneSysCustomItemVo.isSeleted());
    for (boolean bool = true; ; bool = false)
    {
      localSceneSysCustomItemVo.setSeleted(bool);
      this.customVos.remove(paramInt);
      this.customVos.add(paramInt, localSceneSysCustomItemVo);
      return;
    }
  }

  public void onItemClick(int paramInt)
  {
    SceneSysInsideItemVo localSceneSysInsideItemVo = (SceneSysInsideItemVo)this.InsideVos.get(paramInt);
    if (!localSceneSysInsideItemVo.isSeleted());
    for (boolean bool = true; ; bool = false)
    {
      localSceneSysInsideItemVo.setSeleted(bool);
      this.InsideVos.remove(paramInt);
      this.InsideVos.add(paramInt, localSceneSysInsideItemVo);
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.scene.SceneBusiness
 * JD-Core Version:    0.6.0
 */