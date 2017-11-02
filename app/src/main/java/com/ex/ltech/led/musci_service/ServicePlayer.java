package com.ex.ltech.led.musci_service;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.audiofx.Visualizer;
import android.media.audiofx.Visualizer.OnDataCaptureListener;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.ex.ltech.led.acti.music.PlaySongBusiness;
import com.ex.ltech.led.acti.music.RecordBusiness;
import com.ex.ltech.led.vo.SongVo;
import java.util.ArrayList;
import java.util.List;

public class ServicePlayer extends Service
{
  private static final String TAG = "ServicePlayer";
  public static boolean isPlay;
  private static int mIntCMDindex;
  public static int seekSecond;
  public static List<SongVo> songList = new ArrayList();
  public static int songListPosi = 0;
  public SpCallBack callBack = null;
  private byte[] cmdBytes = { 85, -86, 0, 8, 49, 0, 0, 0, 2, 1, 0, 0, 0, -22 };
  private boolean isRunningPlay = false;
  private MyBinder mBinder = new MyBinder();
  private byte[] mBytes;
  private Handler mHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
    }
  };
  Handler mSendCMDHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      super.handleMessage(paramMessage);
      ServicePlayer.this.sendCmd(false);
    }
  };
  private Visualizer mVisualizer;
  private MediaPlayer mp;
  Handler myMessageHandler = new Handler()
  {
    public void handleMessage(Message paramMessage)
    {
      Thread.currentThread().interrupt();
    }
  };
  PlaySongBusiness playSongBusiness;
  RecordBusiness recordBusiness;
  public RecordCallBack recordCallBack = null;

  static
  {
    seekSecond = 0;
    mIntCMDindex = 0;
  }

  private void setListenerOfMediaPlayer()
  {
    this.mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
    {
      public void onCompletion(MediaPlayer paramMediaPlayer)
      {
        ServicePlayer.this.mp.seekTo(0);
        ServicePlayer.this.mp.start();
      }
    });
  }

  @SuppressLint({"NewApi"})
  private void setupVisualizerFxAndUI()
  {
    this.mVisualizer = new Visualizer(this.mp.getAudioSessionId());
    this.mVisualizer.setCaptureSize(128);
    this.mVisualizer.setDataCaptureListener(new Visualizer.OnDataCaptureListener()
    {
      public void onFftDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfByte, int paramInt)
      {
        ServicePlayer.this.updateVisualizer(paramArrayOfByte);
      }

      public void onWaveFormDataCapture(Visualizer paramVisualizer, byte[] paramArrayOfByte, int paramInt)
      {
        ServicePlayer.this.updateVisualizer(paramArrayOfByte);
      }
    }
    , 5000, false, true);
  }

  public SongVo getCurrentSongVo()
  {
    return this.playSongBusiness.getCurrentSongVo();
  }

  public boolean getIsPlay()
  {
    return this.playSongBusiness.getIsPlaying();
  }

  public int getSongListPosi()
  {
    return this.playSongBusiness.getSongListPosi();
  }

  public IBinder onBind(Intent paramIntent)
  {
    return this.mBinder;
  }

  public void onCreate()
  {
    this.mp = new MediaPlayer();
    this.playSongBusiness = new PlaySongBusiness();
    this.recordBusiness = new RecordBusiness();
  }

  public void onDestroy()
  {
    songList.clear();
    this.playSongBusiness.destroyPlayer();
    this.isRunningPlay = false;
    if (this.mp != null)
    {
      this.mp.stop();
      this.mp.release();
      if (this.mVisualizer != null)
        this.mVisualizer.release();
    }
    super.onDestroy();
  }

  public void onStart(Intent paramIntent, int paramInt)
  {
    if (paramIntent == null)
      return;
    Bundle localBundle = paramIntent.getExtras();
    String str = localBundle.getString("operation");
    int i = -1;
    switch (str.hashCode())
    {
    default:
    case -493619646:
    case 3443508:
    case 106440182:
    case 3540994:
    case 3526264:
    case 3377907:
    case 3015911:
    case 1759101819:
    case 1306358417:
    case 734877683:
    }
    while (true)
      switch (i)
      {
      default:
        return;
      case 0:
        this.playSongBusiness.playTrack();
        this.playSongBusiness.setdIndex(localBundle.getInt("DIndexKey"));
        this.playSongBusiness.setdType105(localBundle.getInt("DTypeKey"));
        this.playSongBusiness.setRoomNum105(localBundle.getInt("DRoomNumKey"));
        this.playSongBusiness.setIsGroup(localBundle.getBoolean("MusicIsGroupKey"));
        this.playSongBusiness.setOnOff(true);
        return;
        if (!str.equals("play105"))
          continue;
        i = 0;
        continue;
        if (!str.equals("play"))
          continue;
        i = 1;
        continue;
        if (!str.equals("pause"))
          continue;
        i = 2;
        continue;
        if (!str.equals("stop"))
          continue;
        i = 3;
        continue;
        if (!str.equals("seek"))
          continue;
        i = 4;
        continue;
        if (!str.equals("next"))
          continue;
        i = 5;
        continue;
        if (!str.equals("back"))
          continue;
        i = 6;
        continue;
        if (!str.equals("destroyPlayer"))
          continue;
        i = 7;
        continue;
        if (!str.equals("recordStart"))
          continue;
        i = 8;
        continue;
        if (!str.equals("recordStop"))
          continue;
        i = 9;
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
    this.playSongBusiness.playTrack();
    return;
    this.playSongBusiness.pausePlayer();
    return;
    this.playSongBusiness.stopPLayer();
    return;
    this.playSongBusiness.seek(localBundle.getInt("seceond"));
    this.playSongBusiness.resumePlayer();
    return;
    this.playSongBusiness.nextSong();
    return;
    this.playSongBusiness.backSong();
    return;
    this.playSongBusiness.destroyPlayer();
    songList.clear();
    return;
    this.recordBusiness.setdIndex(localBundle.getInt("DIndexKey"));
    this.recordBusiness.setdType105(localBundle.getInt("DTypeKey"));
    this.recordBusiness.setRoomNum105(localBundle.getInt("DRoomNumKey"));
    this.recordBusiness.setIsGroup(localBundle.getBoolean("MusicIsGroupKey"));
    this.recordBusiness.recordingStart();
    this.recordBusiness.setOnOff(true);
    return;
    this.recordBusiness.stopRecording();
  }

  public boolean onUnbind(Intent paramIntent)
  {
    return super.onUnbind(paramIntent);
  }

  public void sendCmd(boolean paramBoolean)
  {
  }

  public void setCallBack(SpCallBack paramSpCallBack)
  {
    this.callBack = paramSpCallBack;
    this.playSongBusiness.setCallBack(paramSpCallBack);
  }

  public void setRecordCallBack(RecordCallBack paramRecordCallBack)
  {
    this.recordCallBack = paramRecordCallBack;
    this.recordBusiness.stopRecording();
    this.recordBusiness.setRecordCallBack(this.recordCallBack);
  }

  public void setSongRecordCallBack(SongRecordCallBack paramSongRecordCallBack)
  {
    this.playSongBusiness.setSongRecordCallBack(paramSongRecordCallBack);
  }

  public void setSongRecordData(int paramInt1, int paramInt2, List<SongVo> paramList)
  {
    this.playSongBusiness.setSongListPosi(paramInt1);
    this.playSongBusiness.setPercent(paramInt2);
    if (songList != null)
      songList.addAll(paramList);
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
    this.mBytes = arrayOfByte;
    int k = 0;
    for (int m = 0; m < this.mBytes.length; m++)
      k += this.mBytes[m];
    int n = (int)(100.0F * (k / 1000.0F));
    this.cmdBytes[10] = (byte)(n & 0xFF);
    this.mSendCMDHandler.sendEmptyMessageDelayed(n, 100L);
  }

  public class MyBinder extends Binder
  {
    public MyBinder()
    {
    }

    public ServicePlayer getService()
    {
      return ServicePlayer.this;
    }
  }

  public static abstract interface RecordCallBack
  {
    public abstract void callBack(int[] paramArrayOfInt);
  }

  public static abstract interface SongRecordCallBack
  {
    public abstract void onSetSongs(List<SongVo> paramList);

    public abstract void onSongPercentChange(int paramInt);

    public abstract void onSongPosiChange(int paramInt);
  }

  public static abstract interface SpCallBack
  {
    public abstract void songInfoCallBack(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4);
  }

  class updateMusic
    implements Runnable
  {
    updateMusic()
    {
    }

    public void run()
    {
      while (ServicePlayer.this.isRunningPlay)
      {
        if ((ServicePlayer.this.mp != null) && (ServicePlayer.this.mp.isPlaying()))
        {
          int i = ServicePlayer.this.mp.getDuration() / 1000;
          int j = ServicePlayer.this.mp.getCurrentPosition() / 1000;
          Intent localIntent = new Intent("music_pro");
          localIntent.putExtra("pro", j);
          localIntent.putExtra("total", i);
          ServicePlayer.this.sendBroadcast(localIntent);
        }
        try
        {
          Thread.sleep(1000L);
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
        }
      }
      ServicePlayer.this.myMessageHandler.sendEmptyMessage(0);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.musci_service.ServicePlayer
 * JD-Core Version:    0.6.0
 */