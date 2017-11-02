package com.ex.ltech.remote.control.yaokong;

import android.app.Activity;
import com.ex.ltech.led.vo.RepeatDayVo;
import java.util.ArrayList;
import java.util.List;

public class YaokongListBussines
{
  public static final String tag = "timingVo";
  public List<RepeatDayVo> longNameDayVos = new ArrayList();
  private Activity pct;

  public YaokongListBussines(Activity paramActivity)
  {
    this.pct = paramActivity;
  }

  public List<RepeatDayVo> getInitRepeatDayVos()
  {
    initRepeatDayVos();
    return this.longNameDayVos;
  }

  public void initRepeatDayVos()
  {
    this.longNameDayVos.clear();
    RepeatDayVo localRepeatDayVo1 = new RepeatDayVo();
    localRepeatDayVo1.setDay(this.pct.getString(2131100055));
    this.longNameDayVos.add(localRepeatDayVo1);
    RepeatDayVo localRepeatDayVo2 = new RepeatDayVo();
    localRepeatDayVo2.setDay(this.pct.getString(2131100003));
    this.longNameDayVos.add(localRepeatDayVo2);
    RepeatDayVo localRepeatDayVo3 = new RepeatDayVo();
    localRepeatDayVo3.setDay(this.pct.getString(2131100004));
    this.longNameDayVos.add(localRepeatDayVo3);
    RepeatDayVo localRepeatDayVo4 = new RepeatDayVo();
    localRepeatDayVo4.setDay(this.pct.getString(2131100005));
    this.longNameDayVos.add(localRepeatDayVo4);
    RepeatDayVo localRepeatDayVo5 = new RepeatDayVo();
    localRepeatDayVo5.setDay(this.pct.getString(2131100006));
    this.longNameDayVos.add(localRepeatDayVo5);
    RepeatDayVo localRepeatDayVo6 = new RepeatDayVo();
    localRepeatDayVo6.setDay(this.pct.getString(2131100007));
    this.longNameDayVos.add(localRepeatDayVo6);
    RepeatDayVo localRepeatDayVo7 = new RepeatDayVo();
    localRepeatDayVo7.setDay(this.pct.getString(2131100008));
    this.longNameDayVos.add(localRepeatDayVo7);
    RepeatDayVo localRepeatDayVo8 = new RepeatDayVo();
    localRepeatDayVo8.setDay(this.pct.getString(2131100009));
    this.longNameDayVos.add(localRepeatDayVo8);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.YaokongListBussines
 * JD-Core Version:    0.6.0
 */