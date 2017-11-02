package com.ex.ltech.onepiontfive.main.vo;

import java.util.ArrayList;
import java.util.List;

public class SmsLogsVo
{
  private int deviceNum;
  private List<SmsLogVo> smsLogs = new ArrayList();

  public int getDeviceNum()
  {
    return this.deviceNum;
  }

  public List<SmsLogVo> getSmsLogs()
  {
    return this.smsLogs;
  }

  public void setDeviceNum(int paramInt)
  {
    this.deviceNum = paramInt;
  }

  public void setSmsLogs(List<SmsLogVo> paramList)
  {
    this.smsLogs = paramList;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SmsLogsVo
 * JD-Core Version:    0.6.0
 */