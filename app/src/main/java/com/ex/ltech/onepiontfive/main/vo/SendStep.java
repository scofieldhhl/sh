package com.ex.ltech.onepiontfive.main.vo;

public class SendStep
{
  byte[] rcCode;
  int step;

  public SendStep(int paramInt)
  {
    this.step = paramInt;
  }

  public SendStep(byte[] paramArrayOfByte)
  {
    this.rcCode = paramArrayOfByte;
  }

  public byte[] getRcCode()
  {
    return this.rcCode;
  }

  public void setRcCode(byte[] paramArrayOfByte)
  {
    this.rcCode = paramArrayOfByte;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.SendStep
 * JD-Core Version:    0.6.0
 */