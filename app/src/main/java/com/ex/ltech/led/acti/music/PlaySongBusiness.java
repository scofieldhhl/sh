package com.ex.ltech.led.acti.music;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import com.ex.ltech.led.musci_service.ServicePlayer;
import com.ex.ltech.led.musci_service.ServicePlayer.SongRecordCallBack;
import com.ex.ltech.led.musci_service.ServicePlayer.SpCallBack;
import com.ex.ltech.led.utils.DateFmtUtil;
import com.ex.ltech.led.vo.SongVo;
import java.io.PrintStream;
import java.util.List;

public class PlaySongBusiness
{
  ServicePlayer.SpCallBack callBack;
  SongVo currentSongVo;
  int currentTime = 0;
  private Handler handler = new Handler();
  public MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      paramMediaPlayer.release();
    }
  };
  private MediaPlayer mediaPlayer = new MediaPlayer();
  private int percent;
  int progress = 0;
  RecordBusiness recordBusiness;
  private Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (DateFmtUtil.getMMSS(PlaySongBusiness.this.currentTime).equals(DateFmtUtil.getMMSS(PlaySongBusiness.this.getCurrentSongVo().getDuration())))
      {
        PlaySongBusiness.this.restartProgress();
        PlaySongBusiness.this.nextSong();
      }
      while (true)
      {
        if (PlaySongBusiness.this.callBack != null)
          PlaySongBusiness.this.callBack.songInfoCallBack(PlaySongBusiness.this.getCurrentSongVo().getTitle(), PlaySongBusiness.this.progress, DateFmtUtil.getMMSS(PlaySongBusiness.this.currentTime), DateFmtUtil.getMMSS(PlaySongBusiness.this.getCurrentSongVo().getDuration()), "play");
        if (PlaySongBusiness.this.songRecordCallBack != null)
          PlaySongBusiness.this.songRecordCallBack.onSongPercentChange(PlaySongBusiness.this.progress);
        com.ex.ltech.led.acti.Main.seekSecond = PlaySongBusiness.this.currentTime;
        System.out.println("songInfoCallBack  PRO       " + 100 * PlaySongBusiness.this.currentTime + "        " + PlaySongBusiness.this.getCurrentSongVo().getDuration() + "     " + PlaySongBusiness.this.progress);
        return;
        PlaySongBusiness localPlaySongBusiness = PlaySongBusiness.this;
        localPlaySongBusiness.currentTime = (1000 + localPlaySongBusiness.currentTime);
        try
        {
          PlaySongBusiness.this.progress = (100 * PlaySongBusiness.this.currentTime / PlaySongBusiness.this.getCurrentSongVo().getDuration());
          PlaySongBusiness.this.handler.postDelayed(this, 1000L);
        }
        catch (Exception localException)
        {
          while (true)
            localException.printStackTrace();
        }
      }
    }
  };
  private int songListPosi = 0;
  ServicePlayer.SongRecordCallBack songRecordCallBack;

  public PlaySongBusiness()
  {
    this.mediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
    this.recordBusiness = new RecordBusiness();
  }

  private void restartProgress()
  {
    this.currentTime = 0;
    this.handler.removeCallbacks(this.runnable);
    this.handler.post(this.runnable);
  }

  public void backSong()
  {
    if (this.songListPosi != 0);
    for (this.songListPosi = (-1 + this.songListPosi); ; this.songListPosi = (-1 + ServicePlayer.songList.size()))
    {
      playTrack();
      return;
    }
  }

  public void destroyPlayer()
  {
    try
    {
      if (this.mediaPlayer != null)
      {
        if (this.mediaPlayer.isPlaying())
          stopPLayer();
        this.mediaPlayer.release();
        this.mediaPlayer = null;
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public SongVo getCurrentSongVo()
  {
    try
    {
      SongVo localSongVo = (SongVo)ServicePlayer.songList.get(this.songListPosi);
      System.out.println();
      return localSongVo;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return new SongVo();
  }

  public boolean getIsPlaying()
  {
    try
    {
      boolean bool = this.mediaPlayer.isPlaying();
      return bool;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return false;
  }

  public int getSongListPosi()
  {
    return this.songListPosi;
  }

  public void nextSong()
  {
    int i = this.songListPosi;
    if (i < -1 + ServicePlayer.songList.size());
    for (this.songListPosi = (1 + this.songListPosi); ; this.songListPosi = 0)
    {
      playTrack();
      return;
    }
  }

  public void pausePlayer()
  {
    this.mediaPlayer.pause();
    this.recordBusiness.stopRecording();
    this.handler.removeCallbacks(this.runnable);
  }

  // ERROR //
  public void playTrack()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   4: invokevirtual 157	android/media/MediaPlayer:reset	()V
    //   7: aload_0
    //   8: getfield 66	com/ex/ltech/led/acti/music/PlaySongBusiness:recordBusiness	Lcom/ex/ltech/led/acti/music/RecordBusiness;
    //   11: invokevirtual 148	com/ex/ltech/led/acti/music/RecordBusiness:stopRecording	()V
    //   14: aload_0
    //   15: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   18: astore 6
    //   20: getstatic 90	com/ex/ltech/led/acti/Main:myService	Lcom/ex/ltech/led/musci_service/ServicePlayer;
    //   23: pop
    //   24: aload 6
    //   26: getstatic 96	com/ex/ltech/led/musci_service/ServicePlayer:songList	Ljava/util/List;
    //   29: aload_0
    //   30: getfield 31	com/ex/ltech/led/acti/music/PlaySongBusiness:songListPosi	I
    //   33: invokeinterface 124 2 0
    //   38: checkcast 126	com/ex/ltech/led/vo/SongVo
    //   41: invokevirtual 161	com/ex/ltech/led/vo/SongVo:getPath	()Ljava/lang/String;
    //   44: invokevirtual 165	android/media/MediaPlayer:setDataSource	(Ljava/lang/String;)V
    //   47: aload_0
    //   48: getfield 167	com/ex/ltech/led/acti/music/PlaySongBusiness:songRecordCallBack	Lcom/ex/ltech/led/musci_service/ServicePlayer$SongRecordCallBack;
    //   51: ifnull +23 -> 74
    //   54: aload_0
    //   55: getfield 167	com/ex/ltech/led/acti/music/PlaySongBusiness:songRecordCallBack	Lcom/ex/ltech/led/musci_service/ServicePlayer$SongRecordCallBack;
    //   58: astore 8
    //   60: getstatic 90	com/ex/ltech/led/acti/Main:myService	Lcom/ex/ltech/led/musci_service/ServicePlayer;
    //   63: pop
    //   64: aload 8
    //   66: getstatic 96	com/ex/ltech/led/musci_service/ServicePlayer:songList	Ljava/util/List;
    //   69: invokeinterface 173 2 0
    //   74: aload_0
    //   75: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   78: invokevirtual 176	android/media/MediaPlayer:prepare	()V
    //   81: aload_0
    //   82: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   85: invokevirtual 179	android/media/MediaPlayer:start	()V
    //   88: aload_0
    //   89: getfield 66	com/ex/ltech/led/acti/music/PlaySongBusiness:recordBusiness	Lcom/ex/ltech/led/acti/music/RecordBusiness;
    //   92: invokevirtual 182	com/ex/ltech/led/acti/music/RecordBusiness:recordingStart	()V
    //   95: aload_0
    //   96: invokespecial 70	com/ex/ltech/led/acti/music/PlaySongBusiness:restartProgress	()V
    //   99: aload_0
    //   100: getfield 167	com/ex/ltech/led/acti/music/PlaySongBusiness:songRecordCallBack	Lcom/ex/ltech/led/musci_service/ServicePlayer$SongRecordCallBack;
    //   103: ifnull +16 -> 119
    //   106: aload_0
    //   107: getfield 167	com/ex/ltech/led/acti/music/PlaySongBusiness:songRecordCallBack	Lcom/ex/ltech/led/musci_service/ServicePlayer$SongRecordCallBack;
    //   110: aload_0
    //   111: getfield 31	com/ex/ltech/led/acti/music/PlaySongBusiness:songListPosi	I
    //   114: invokeinterface 186 2 0
    //   119: return
    //   120: astore_1
    //   121: aload_1
    //   122: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   125: aload_0
    //   126: new 54	android/media/MediaPlayer
    //   129: dup
    //   130: invokespecial 55	android/media/MediaPlayer:<init>	()V
    //   133: putfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   136: aload_0
    //   137: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   140: aload_0
    //   141: getfield 52	com/ex/ltech/led/acti/music/PlaySongBusiness:mOnCompletionListener	Landroid/media/MediaPlayer$OnCompletionListener;
    //   144: invokevirtual 61	android/media/MediaPlayer:setOnCompletionListener	(Landroid/media/MediaPlayer$OnCompletionListener;)V
    //   147: aload_0
    //   148: getfield 57	com/ex/ltech/led/acti/music/PlaySongBusiness:mediaPlayer	Landroid/media/MediaPlayer;
    //   151: invokevirtual 157	android/media/MediaPlayer:reset	()V
    //   154: goto -147 -> 7
    //   157: astore 5
    //   159: aload 5
    //   161: invokevirtual 187	java/lang/IllegalArgumentException:printStackTrace	()V
    //   164: return
    //   165: astore 4
    //   167: aload 4
    //   169: invokevirtual 188	java/lang/SecurityException:printStackTrace	()V
    //   172: return
    //   173: astore_3
    //   174: aload_3
    //   175: invokevirtual 189	java/lang/IllegalStateException:printStackTrace	()V
    //   178: return
    //   179: astore_2
    //   180: aload_2
    //   181: invokevirtual 118	java/lang/Exception:printStackTrace	()V
    //   184: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	120	java/lang/Exception
    //   7	74	157	java/lang/IllegalArgumentException
    //   74	119	157	java/lang/IllegalArgumentException
    //   7	74	165	java/lang/SecurityException
    //   74	119	165	java/lang/SecurityException
    //   7	74	173	java/lang/IllegalStateException
    //   74	119	173	java/lang/IllegalStateException
    //   7	74	179	java/lang/Exception
    //   74	119	179	java/lang/Exception
  }

  public void playerCompletion()
  {
    this.mediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
  }

  public void resumePlayer()
  {
    this.mediaPlayer.start();
  }

  public void seek(int paramInt)
  {
    this.mediaPlayer.seekTo(paramInt);
    this.currentTime = paramInt;
    this.handler.removeCallbacks(this.runnable);
    this.handler.post(this.runnable);
    if (!this.recordBusiness.isRecording())
      this.recordBusiness.recordingStart();
  }

  public void setCallBack(ServicePlayer.SpCallBack paramSpCallBack)
  {
    this.callBack = paramSpCallBack;
  }

  public void setIsGroup(boolean paramBoolean)
  {
    this.recordBusiness.setIsGroup(paramBoolean);
  }

  public void setOnOff(boolean paramBoolean)
  {
    this.recordBusiness.setOnOff(paramBoolean);
  }

  public void setPercent(int paramInt)
  {
    this.percent = paramInt;
  }

  public void setRoomNum105(int paramInt)
  {
    this.recordBusiness.setRoomNum105(paramInt);
  }

  public void setSongListPosi(int paramInt)
  {
    this.songListPosi = paramInt;
  }

  public void setSongRecordCallBack(ServicePlayer.SongRecordCallBack paramSongRecordCallBack)
  {
    this.songRecordCallBack = paramSongRecordCallBack;
  }

  public void setdIndex(int paramInt)
  {
    this.recordBusiness.setdIndex(paramInt);
  }

  public void setdType105(int paramInt)
  {
    this.recordBusiness.setdType105(paramInt);
  }

  public void stopPLayer()
  {
    this.mediaPlayer.stop();
    com.ex.ltech.led.acti.Main.seekSecond = -1;
    this.recordBusiness.stopRecording();
    this.handler.removeCallbacks(this.runnable);
    if (this.callBack != null)
      this.callBack.songInfoCallBack(getCurrentSongVo().getTitle(), 0, DateFmtUtil.getMMSS(0L), DateFmtUtil.getMMSS(getCurrentSongVo().getDuration()), "stop");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.music.PlaySongBusiness
 * JD-Core Version:    0.6.0
 */