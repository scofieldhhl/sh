package com.ex.ltech.led.acti.music;

import android.content.Context;
import android.content.Intent;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.ex.ltech.led.vo.SongVo;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MusicManager
{
  public static final String BOTTOMACTIONBAR_CHECKED = "BOTTOMACTIONBAR_CHECKED";
  public static final String BOTTOMACTIONBAR_UNCHECKED = "BOTTOMACTIONBAR_UNCHECKED";
  public static final String BOTTOMACTIONBAR_UPDATE = "BOTTOMACTIONBAR_UPDATE";
  public static final String PLAY_BTN_CHECKED = "PLAY_BTN_CHECKED";
  public static final String PLAY_BTN_UNCHECKED = "PLAY_BTN_UNCHECKED";
  private static MusicManager instance;
  public static boolean isPlaying = false;
  public static String[] media_info = { "title", "duration", "artist", "_id", "_display_name", "_data", "album_id", "date_added" };
  private Context context;
  private ListView nowPlayingListView;
  private int nowplaying_index = -1;
  private List<SongVo> songLists = new ArrayList();

  public static MusicManager getInstance()
  {
    if (instance == null)
      instance = new MusicManager();
    return instance;
  }

  public void addSong(SongVo paramSongVo)
  {
    this.songLists.add(paramSongVo);
  }

  public void bind(Context paramContext)
  {
    this.context = paramContext;
  }

  public ListView getNowPlayingListView()
  {
    return this.nowPlayingListView;
  }

  public SongVo getNowPlayingSong()
  {
    return (SongVo)this.nowPlayingListView.getAdapter().getItem(this.nowplaying_index);
  }

  public int getNowplaying_index()
  {
    return this.nowplaying_index;
  }

  public int getRandom_song()
  {
    setNowplaying_index(new Random().nextInt(this.nowPlayingListView.getAdapter().getCount()));
    return this.nowplaying_index;
  }

  public SongVo getSongByIndex(int paramInt)
  {
    return (SongVo)this.nowPlayingListView.getAdapter().getItem(paramInt);
  }

  public List<SongVo> getSongLists()
  {
    return this.songLists;
  }

  public void nextSong()
  {
    setNowplaying_index(1 + this.nowplaying_index);
    restartPlay();
  }

  public void pause()
  {
    Intent localIntent1 = new Intent();
    localIntent1.setAction("com.huwei.sweetmusicplayer.services.LocalMusicService");
    localIntent1.putExtra("op", "MusicState.PAUSE.ordinal()");
    this.context.startService(localIntent1);
    isPlaying = false;
    Intent localIntent2 = new Intent("PLAY_BTN_UNCHECKED");
    this.context.sendBroadcast(localIntent2);
  }

  public void play()
  {
    Intent localIntent1 = new Intent();
    SongVo localSongVo = (SongVo)this.nowPlayingListView.getAdapter().getItem(this.nowplaying_index);
    localIntent1.setAction("com.huwei.sweetmusicplayer.services.LocalMusicService");
    localIntent1.putExtra("SongVo", localSongVo);
    localIntent1.putExtra("op", "MusicState.PLAYING.ordinal()");
    this.context.startService(localIntent1);
    Intent localIntent2 = new Intent("PLAY_BTN_CHECKED");
    this.context.sendBroadcast(localIntent2);
    isPlaying = true;
  }

  public void previousSong()
  {
    setNowplaying_index(-1 + this.nowplaying_index);
    restartPlay();
  }

  public void random_a_song()
  {
    getRandom_song();
    restartPlay();
  }

  public void restartPlay()
  {
    stopUnNotifyView();
    play();
  }

  public void setNowPlayingListView(ListView paramListView)
  {
    this.nowPlayingListView = paramListView;
  }

  public void setNowplaying_index(int paramInt)
  {
    int i = this.nowPlayingListView.getCount();
    this.nowplaying_index = ((paramInt + i) % i);
    ((BaseAdapter)this.nowPlayingListView.getAdapter()).notifyDataSetChanged();
    Intent localIntent1 = new Intent("BOTTOMACTIONBAR_UPDATE");
    this.context.sendBroadcast(localIntent1);
    Intent localIntent2 = new Intent("PlayingFragment.PLAYSONG_UPDATE");
    this.context.sendBroadcast(localIntent2);
  }

  public void setProgress(int paramInt)
  {
    Intent localIntent = new Intent("com.huwei.sweetmusicplayer.services.LocalMusicService");
    localIntent.putExtra("progress", paramInt);
    localIntent.putExtra("op", "MusicState.PROGRESS_CHANGE.ordinal()");
    this.context.startService(localIntent);
  }

  public void stop()
  {
    stopUnNotifyView();
  }

  public void stopUnNotifyView()
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.huwei.sweetmusicplayer.services.LocalMusicService");
    localIntent.putExtra("op", "MusicState.STOP.ordinal()");
    this.context.startService(localIntent);
  }

  public static class OperateState
  {
    public static String READLRCFILE_FAIL;
    public static String READLRCONLINE_FAIL;
    public static String READLRC_LISTNULL;
    public static String READLRC_ONLINE;
    public static String READLRC_SUCCESS = "READLRC_SUCCESS";

    static
    {
      READLRC_LISTNULL = "READLRC_LISTNULL";
      READLRC_ONLINE = "READLRC_ONLINE";
      READLRCFILE_FAIL = "READLRCFILE_FAIL";
      READLRCONLINE_FAIL = "READLRCONLINE_FAIL";
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.music.MusicManager
 * JD-Core Version:    0.6.0
 */