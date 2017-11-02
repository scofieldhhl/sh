package com.ex.ltech.led.acti.music;

import android.media.AudioRecord;
import android.media.audiofx.Visualizer;
import android.os.SystemClock;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.musci_service.ServicePlayer.RecordCallBack;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.room.RoomBusiness;
import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import java.io.PrintStream;

public class RecordBusiness
{
  AudioRecord audioRecord;
  private CmdDateBussiness cmdDateBussiness;
  int dIndex;
  int dType105;
  boolean isGroup;
  private boolean isPlaying = false;
  private boolean isRecording = false;
  private short[] mshort;
  MyBusiness myBusiness = null;
  boolean onOff;
  private int[] rateArr = new int[6];
  int recBufSize;
  ServicePlayer.RecordCallBack recordCallBack = null;
  RoomBusiness roomBusiness;
  int roomNum105;
  private SocketManager socketManager;
  int time;
  int ttfValue;
  Visualizer visualizer;

  public RecordBusiness()
  {
    prepareLink();
  }

  private int getMeanvalueFor100(short[] paramArrayOfShort, int paramInt)
  {
    int i = 10;
    int j = 0;
    for (int k = paramInt; k < paramInt + 10; k++)
      j += paramArrayOfShort[k];
    if (Math.abs(j / 2000) == 0)
      i = 1;
    do
      return i;
    while (Math.abs(j / 2000) > i);
    return Math.abs(j / 2000);
  }

  public boolean isRecording()
  {
    return this.isRecording;
  }

  public void prepareLink()
  {
    this.socketManager = SocketManager.instance();
    this.socketManager.ip = Main.deviceVo.getIp();
    this.cmdDateBussiness = new CmdDateBussiness(null, "0000");
    this.roomBusiness = new RoomBusiness(MyApp.getApp());
  }

  public void recordingStart()
  {
    this.isRecording = false;
    SystemClock.sleep(50L);
    this.isRecording = true;
    new RecordThread().start();
  }

  public void sendVolumes(int paramInt1, int paramInt2, int paramInt3)
  {
    System.out.println("FGHJafuhqefuhasnc啊快乐的食物覅我·         " + paramInt1);
    DeviceManage.getInstance();
    if (DeviceManage.getxDevice().getProductId().equals("160fa2b1d84e03e9160fa2b1d84eaa01"))
    {
      this.roomBusiness.sendCmdNoResponse(this.roomBusiness.sendMusic(this.isGroup, this.dIndex, this.roomNum105, paramInt1, this.onOff));
      System.out.println("isGroup " + this.isGroup + "       dIndex = " + this.dIndex + "            roomNum105 = " + this.roomNum105 + "         value1 = " + (paramInt1 + 50) + "         onOff = " + this.onOff);
      return;
    }
    this.socketManager.postTaskNoReturn(this.cmdDateBussiness.getMusicCmdT(true, paramInt1, paramInt2, paramInt3));
  }

  public void setIsGroup(boolean paramBoolean)
  {
    this.isGroup = paramBoolean;
  }

  public void setOnOff(boolean paramBoolean)
  {
    this.onOff = paramBoolean;
  }

  public void setRecordCallBack(ServicePlayer.RecordCallBack paramRecordCallBack)
  {
    this.recordCallBack = paramRecordCallBack;
  }

  public void setRoomNum105(int paramInt)
  {
    this.roomNum105 = paramInt;
  }

  public void setdIndex(int paramInt)
  {
    this.dIndex = paramInt;
  }

  public void setdType105(int paramInt)
  {
    this.dType105 = paramInt;
  }

  public void stopMusic(int paramInt)
  {
    this.socketManager.postTask(this.cmdDateBussiness.getMusicCmd(false, paramInt));
  }

  public void stopRecording()
  {
    if (this.isRecording == true)
    {
      this.isRecording = false;
      stopMusic(0);
    }
  }

  public void updateVisualizer(byte[] paramArrayOfByte)
  {
    byte[] arrayOfByte = new byte[1 + paramArrayOfByte.length / 2];
    arrayOfByte[0] = (byte)Math.abs(paramArrayOfByte[0]);
    int i = 2;
    for (int j = 1; j < 48; j++)
    {
      arrayOfByte[j] = (byte)(int)Math.hypot(paramArrayOfByte[i], paramArrayOfByte[(i + 1)]);
      i += 2;
    }
    int k = 0;
    for (int m = 0; m < arrayOfByte.length; m++)
      k += arrayOfByte[m];
    this.ttfValue = (byte)(0xFF & (int)(100.0F * (k / 1000.0F)));
  }

  class RecordThread extends Thread
  {
    RecordThread()
    {
    }

    public void run()
    {
      RecordBusiness.this.recBufSize = AudioRecord.getMinBufferSize(44100, 2, 2);
      RecordBusiness.this.audioRecord = new AudioRecord(1, 44100, 2, 2, RecordBusiness.this.recBufSize);
      try
      {
        RecordBusiness.this.audioRecord.startRecording();
        byte[] arrayOfByte = new byte[100];
        arrayOfShort = new short[100];
        if (RecordBusiness.this.isRecording)
        {
          RecordBusiness.this.audioRecord.read(arrayOfByte, 0, 100);
          RecordBusiness.this.audioRecord.read(arrayOfShort, 0, 100);
          RecordBusiness.access$102(RecordBusiness.this, arrayOfShort);
          RecordBusiness.this.updateVisualizer(arrayOfByte);
          for (int i = 0; i < 6; i++)
            RecordBusiness.this.rateArr[i] = RecordBusiness.access$300(RecordBusiness.this, RecordBusiness.access$100(RecordBusiness.this), i);
        }
      }
      catch (IllegalStateException localIllegalStateException1)
      {
        short[] arrayOfShort;
        do
        {
          while (true)
            localIllegalStateException1.printStackTrace();
          RecordBusiness localRecordBusiness1 = RecordBusiness.this;
          localRecordBusiness1.time = (1 + localRecordBusiness1.time);
        }
        while (RecordBusiness.this.time % 20 != 0);
        if (RecordBusiness.this.recordCallBack != null)
          RecordBusiness.this.recordCallBack.callBack(RecordBusiness.this.rateArr);
        int j = 0;
        for (int k = 0; k < arrayOfShort.length; k++)
          j += Math.abs(arrayOfShort[k]);
        int m = j / arrayOfShort.length;
        int n;
        if (m / 400 > 0)
        {
          RecordBusiness localRecordBusiness2 = RecordBusiness.this;
          if (RecordBusiness.this.ttfValue > 0)
          {
            n = 7 * RecordBusiness.this.ttfValue / 5;
            label304: localRecordBusiness2.sendVolumes(n, 10, 10);
            System.out.println("你的音量 " + m / 400 + "                          你的ttfValue     " + RecordBusiness.this.ttfValue);
          }
        }
        while (true)
        {
          RecordBusiness.this.time = 0;
          break;
          n = 0;
          break label304;
          RecordBusiness.this.sendVolumes(0, 10, 10);
        }
      }
      try
      {
        RecordBusiness.this.audioRecord.stop();
        RecordBusiness.this.audioRecord.release();
        super.run();
        return;
      }
      catch (IllegalStateException localIllegalStateException2)
      {
        while (true)
          localIllegalStateException2.printStackTrace();
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.music.RecordBusiness
 * JD-Core Version:    0.6.0
 */