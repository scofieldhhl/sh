package com.ex.ltech.remote.control.vo;

import et.song.etclass.ETGroup;
import java.util.List;

public class RcVo
{
  ETGroup group;
  private List<YaokongTimingVo> yaokongTimingVos;
  List<YkSceneVo> ykSceneVos;

  public ETGroup getGroup()
  {
    return this.group;
  }

  public List<YaokongTimingVo> getYaokongTimingVos()
  {
    return this.yaokongTimingVos;
  }

  public List<YkSceneVo> getYkSceneVos()
  {
    return this.ykSceneVos;
  }

  public void setGroup(ETGroup paramETGroup)
  {
    this.group = paramETGroup;
  }

  public void setYaokongTimingVos(List<YaokongTimingVo> paramList)
  {
    this.yaokongTimingVos = paramList;
  }

  public void setYkSceneVos(List<YkSceneVo> paramList)
  {
    this.ykSceneVos = paramList;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.vo.RcVo
 * JD-Core Version:    0.6.0
 */