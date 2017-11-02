package com.ex.ltech.hongwai;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;

public abstract class NonIrDeviceAt extends MyBaseActivity
{
  public CmdDateBussiness cmd;
  Handler handler;
  int sendTime;

  protected void cracySend(byte[] paramArrayOfByte, int paramInt)
  {
    for (int i = 0; i < paramInt; i++)
      this.handler.postDelayed(new Runnable(paramArrayOfByte)
      {
        public void run()
        {
          SocketManager.instance().sendData(this.val$data);
        }
      }
      , i * 100);
  }

  protected void fillterSend(byte[] paramArrayOfByte, int paramInt)
  {
    this.sendTime = (1 + this.sendTime);
    if (this.sendTime % paramInt == 0)
      SocketManager.instance().sendData(paramArrayOfByte);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.handler = new Handler(Looper.myLooper());
    this.cmd = new CmdDateBussiness(this, "0000");
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
      onMenu();
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.NonIrDeviceAt
 * JD-Core Version:    0.6.0
 */