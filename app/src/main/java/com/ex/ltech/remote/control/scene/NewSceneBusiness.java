package com.ex.ltech.remote.control.scene;

import android.content.Context;
import android.content.res.Resources;
import com.ex.ltech.remote.control.vo.YkSceneVo;
import com.ex.ltech.remote.control.vo.YkVo;
import java.util.ArrayList;
import java.util.List;

public class NewSceneBusiness extends SceneBusiness
{
  private int clickItemPosi;
  int lastCacheSceneDataSize = -1;
  YkSceneVo vo;

  public NewSceneBusiness(Context paramContext)
  {
    super(paramContext);
  }

  public List<String> getStrArr()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 1; i < 61; i++)
      localArrayList.add(i + "");
    return localArrayList;
  }

  public List<YkVo> getYkVos(int paramInt)
  {
    YkVo localYkVo = new YkVo();
    localYkVo.setIsAdd(true);
    if (isNewScene(paramInt))
    {
      if (CacheSceneData.ykVos.size() == 0)
        CacheSceneData.ykVos.add(localYkVo);
      return CacheSceneData.ykVos;
    }
    this.vo = ((YkSceneVo)getData().get(paramInt));
    if (this.vo.getYks() == null)
    {
      if (CacheSceneData.ykVos.size() == 0)
        CacheSceneData.ykVos.add(localYkVo);
      return CacheSceneData.ykVos;
    }
    if ((this.lastCacheSceneDataSize != -1) && (this.lastCacheSceneDataSize != CacheSceneData.ykVos.size()))
      return CacheSceneData.ykVos;
    CacheSceneData.ykVos.addAll(this.vo.getYks());
    CacheSceneData.ykVos.add(localYkVo);
    this.lastCacheSceneDataSize = CacheSceneData.ykVos.size();
    return CacheSceneData.ykVos;
  }

  public boolean isNewScene(int paramInt)
  {
    return getData().size() == paramInt;
  }

  public void okSecondOk(int paramInt1, int paramInt2)
  {
    YkVo localYkVo = (YkVo)getYkVos(paramInt2).get(this.clickItemPosi);
    localYkVo.setSpaceTime(paramInt1);
    CacheSceneData.ykVos.remove(this.clickItemPosi);
    CacheSceneData.ykVos.add(this.clickItemPosi, localYkVo);
    this.clickItemPosi = -1;
  }

  public void saveScene(int paramInt, String paramString1, String paramString2)
  {
    List localList1 = getData();
    if (paramInt < localList1.size())
      localList1.remove(paramInt);
    YkSceneVo localYkSceneVo = new YkSceneVo();
    localYkSceneVo.setName(paramString1);
    if (paramString2.length() > 0)
      localYkSceneVo.setPicPath(paramString2);
    while (true)
    {
      List localList2 = getYkVos(paramInt);
      localList2.remove(-1 + localList2.size());
      localYkSceneVo.setYks(localList2);
      localList1.add(paramInt, localYkSceneVo);
      saveData(localList1);
      return;
      if (paramInt == 0)
      {
        localYkSceneVo.setPicRes(2130903840);
        localYkSceneVo.setBgCol(this.ct.getResources().getColor(2131492998));
      }
      if (paramInt == 1)
      {
        localYkSceneVo.setPicRes(2130903841);
        localYkSceneVo.setBgCol(this.ct.getResources().getColor(2131492999));
      }
      if (paramInt == 2)
      {
        localYkSceneVo.setPicRes(2130903842);
        localYkSceneVo.setBgCol(this.ct.getResources().getColor(2131493000));
      }
      if (paramInt == 3)
      {
        localYkSceneVo.setPicRes(2130903843);
        localYkSceneVo.setBgCol(this.ct.getResources().getColor(2131493001));
      }
      if (paramInt <= 3)
        continue;
      localYkSceneVo.setPicRes(2130903754);
    }
  }

  public void showSecond(int paramInt)
  {
    this.clickItemPosi = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.NewSceneBusiness
 * JD-Core Version:    0.6.0
 */