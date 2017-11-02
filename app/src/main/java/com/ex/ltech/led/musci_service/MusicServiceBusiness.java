package com.ex.ltech.led.musci_service;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.vo.SongVo;
import java.util.List;

public class MusicServiceBusiness
{
  private MediaPlayer mediaPlayer;
  private Activity pActivity;
  private int songListPosi = 0;

  public MusicServiceBusiness(Activity paramActivity)
  {
    this.pActivity = paramActivity;
  }

  public void backSong()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "back");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void destroyPlayer()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "destroyPlayer");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public SongVo getCurrentSongVo()
  {
    return Main.myService.getCurrentSongVo();
  }

  public boolean getIsPlaying()
  {
    return Main.myService.getIsPlay();
  }

  public List<SongVo> getSongs()
  {
    return ServicePlayer.songList;
  }

  public void nextSong()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "next");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void pausePlayer()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "pause");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void playTrack()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "play");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void playTrack105(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "play105");
    localBundle.putInt("DTypeKey", paramInt1);
    localBundle.putInt("DRoomNumKey", paramInt2);
    localBundle.putInt("DIndexKey", paramInt3);
    localBundle.putBoolean("MusicIsGroupKey", paramBoolean);
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void playerCompletion()
  {
  }

  public void recordStart()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "recordStart");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void recordStart(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "recordStart");
    localBundle.putInt("DTypeKey", paramInt1);
    localBundle.putInt("DRoomNumKey", paramInt2);
    localBundle.putInt("DIndexKey", paramInt3);
    localBundle.putBoolean("MusicIsGroupKey", paramBoolean);
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void recordStop()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "recordStop");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void resumePlayer()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "resume");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void seek(int paramInt)
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "seek");
    localBundle.putInt("seceond", paramInt);
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }

  public void stopPLayer()
  {
    Intent localIntent = new Intent(this.pActivity, ServicePlayer.class);
    Bundle localBundle = new Bundle();
    localBundle.putString("operation", "stop");
    localIntent.putExtras(localBundle);
    this.pActivity.startService(localIntent);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.musci_service.MusicServiceBusiness
 * JD-Core Version:    0.6.0
 */