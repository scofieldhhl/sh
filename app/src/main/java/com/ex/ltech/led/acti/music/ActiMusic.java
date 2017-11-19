package com.ex.ltech.led.acti.music;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.vo.SongRecord;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.indris.material.RippleView;


import butterknife.ButterKnife;

public class ActiMusic extends MyBaseActivity
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
  public static ActiMusic instance;
  public static boolean isRecording = false;
  private RippleView bt_acti_mus_list;
  private RippleView bt_acti_music_mic;
  private Button bt_music_act;
  private Button bt_music_act_go_list;
  private long clickPlaySpace;
  int currentTime = 0;
  private Handler handler = new Handler();
  boolean isMicClick;
  private boolean isOnActResult;
  private boolean isOpenMusList;
  boolean isPlay;
  boolean isTouchSB;
  private Button iv_acti_music_back;
  private ImageView iv_acti_music_disc;
  private ImageView iv_acti_music_loader;
  private RippleView iv_acti_music_mic;
  private Button iv_acti_music_next;
  private RippleView iv_acti_music_play;
  final RotateAnimation loaderLoadAnimation = new RotateAnimation(0.0F, 35.0F, 1, 0.426F, 1, 0.12F);
  final RotateAnimation loaderReturnAnimation = new RotateAnimation(35.0F, 0.0F, 1, 0.426F, 1, 0.12F);
//  private MusicServiceBusiness playSongBusiness;
  private SeekBar sb_music_acti;
  private int songProgress;
  private SongRecord songRecord;
  int tempSecond;
  long time;
  private TextView tv_acti_music_song_all_time;
  private TextView tv_acti_music_song_current_time;
  private TextView tv_acti_music_song_name;
//  private VisualizerView vv_act_music;

  private void findView()
  {
    this.iv_acti_music_disc = ((ImageView)findViewById(R.id.iv_acti_music_disc));
    this.iv_acti_music_loader = ((ImageView)findViewById(R.id.iv_acti_music_loader));
    this.iv_acti_music_back = ((Button)findViewById(R.id.iv_acti_music_back));
    this.iv_acti_music_play = ((RippleView)findViewById(R.id.iv_acti_music_play));
    this.iv_acti_music_next = ((Button)findViewById(R.id.iv_acti_music_next));
    this.iv_acti_music_mic = ((RippleView)findViewById(R.id.iv_acti_music_mic));
    this.iv_acti_music_mic.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        /*ActiMusic localActiMusic = ActiMusic.this;
        if (!ActiMusic.this.isMicClick);
        for (boolean bool = true; ; bool = false)
        {
          localActiMusic.isMicClick = bool;
          if (!ActiMusic.this.isMicClick)
            break;
          ActiMusic.this.playSongBusiness.recordStart();
          ActiMusic.this.iv_acti_music_mic.setBackgroundResource(R.mipmap.music_mic);
          return;
        }
        ActiMusic.this.iv_acti_music_mic.setBackgroundResource(R.mipmap.music_mic_dark);
        ActiMusic.this.playSongBusiness.recordStop();
        ActiMusic.this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            ActiMusic.this.vv_act_music.setNullArr();
          }
        }
        , 100L);*/
      }
    });
    this.sb_music_acti = ((SeekBar)findViewById(R.id.sb_music_acti));
    this.sb_music_acti.setOnSeekBarChangeListener(this);
    this.tv_acti_music_song_name = ((TextView)findViewById(R.id.tv_acti_music_song_name));
    this.tv_acti_music_song_current_time = ((TextView)findViewById(R.id.tv_acti_music_song_current_time));
    this.tv_acti_music_song_all_time = ((TextView)findViewById(R.id.tv_acti_music_song_all_time));
    this.bt_acti_mus_list = ((RippleView)findViewById(R.id.bt_acti_mus_list));
    this.bt_acti_music_mic = ((RippleView)findViewById(R.id.bt_acti_music_mic));
    this.bt_music_act_go_list = ((Button)findViewById(R.id.bt_music_act_go_list));
//    this.vv_act_music = ((VisualizerView)findViewById(R.id.vv_act_music));
    this.bt_acti_mus_list.setBackgroundResource(R.mipmap.song_seleted);
    this.bt_acti_music_mic.setBackgroundResource(R.mipmap.act_mus_mic);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(13);
//    this.vv_act_music.setLayoutParams(localLayoutParams);
  }

  private void hidePlaySongViews()
  {
    /*if (this.playSongBusiness.getIsPlaying())
    {
      this.iv_acti_music_loader.clearAnimation();
      this.iv_acti_music_loader.startAnimation(this.loaderReturnAnimation);
      this.isPlay = false;
      stopSong();
    }*/
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActiMusic.this.bt_music_act_go_list.setVisibility(View.GONE);
        ActiMusic.this.sb_music_acti.setVisibility(View.GONE);
        ActiMusic.this.iv_acti_music_disc.setVisibility(View.GONE);
        ActiMusic.this.findViewById(R.id.iv_acti_null_loader).setVisibility(View.VISIBLE);
        ActiMusic.this.tv_acti_music_song_name.setVisibility(View.GONE);
        ActiMusic.this.tv_acti_music_song_current_time.setVisibility(View.GONE);
        ActiMusic.this.tv_acti_music_song_all_time.setVisibility(View.GONE);
        ActiMusic.this.sb_music_acti.setVisibility(View.GONE);
        ActiMusic.this.iv_acti_music_back.setVisibility(View.GONE);
        ActiMusic.this.iv_acti_music_play.setVisibility(View.GONE);
        ActiMusic.this.iv_acti_music_next.setVisibility(View.GONE);
        ActiMusic.this.iv_acti_music_mic.setVisibility(View.VISIBLE);
//        ActiMusic.this.vv_act_music.setVisibility(View.VISIBLE);
        ActiMusic.this.bt_acti_music_mic.setBackgroundResource(R.mipmap.act_mus_mic_seleted);
        ActiMusic.this.bt_acti_mus_list.setBackgroundResource(R.mipmap.song);
      }
    }
    , 200L);
  }

  private void init()
  {
    /*intAnimation();
    this.songRecord = ((SongRecord)new MyBusiness(this).getBean4ClassName(DeviceListActivity.deviceMacAddress, SongRecord.class));
    if ((this.songRecord != null) && (this.songRecord.getPosi() != -1) && (this.songRecord.getPercent() != -1) && (this.songRecord.songs != null) && (this.songRecord.songs.size() > 0))
    {
      Main.myService.setSongRecordData(this.songRecord.getPosi(), this.songRecord.getPercent(), this.songRecord.songs);
      this.tv_acti_music_song_name.setText(((SongVo)this.songRecord.songs.get(this.songRecord.getPosi())).getTitle());
      return;
    }
    this.songRecord = new SongRecord();*/
  }

  private void playSong()
  {
    /*this.iv_acti_music_play.setBackgroundResource(R.mipmap.music_stop);
    this.iv_acti_music_loader.clearAnimation();
    this.iv_acti_music_loader.startAnimation(this.loaderLoadAnimation);
    this.playSongBusiness.playTrack();
    ServicePlayer.isPlay = true;
    if (!isRecording)
      isRecording = true;*/
  }

  private void setListener()
  {
    this.bt_acti_mus_list.setOnClickListener(this);
    this.bt_acti_music_mic.setOnClickListener(this);
    this.iv_acti_music_back.setOnClickListener(this);
    this.iv_acti_music_play.setOnClickListener(this);
    this.iv_acti_music_next.setOnClickListener(this);
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.device_ic);
    setTiTleTextRes(2131100194);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
  }

  private void showPlaySongViews()
  {
    /*this.bt_music_act_go_list.setVisibility(View.VISIBLE);
    this.sb_music_acti.setVisibility(View.VISIBLE);
    this.iv_acti_music_disc.setVisibility(View.VISIBLE);
    findViewById(R.id.iv_acti_null_loader).setVisibility(View.GONE);
    this.tv_acti_music_song_name.setVisibility(View.VISIBLE);
    this.tv_acti_music_song_current_time.setVisibility(View.VISIBLE);
    this.tv_acti_music_song_all_time.setVisibility(View.VISIBLE);
    this.sb_music_acti.setVisibility(View.VISIBLE);
    this.iv_acti_music_back.setVisibility(View.VISIBLE);
    this.iv_acti_music_play.setVisibility(View.VISIBLE);
    this.iv_acti_music_next.setVisibility(View.VISIBLE);
    this.bt_acti_mus_list.setBackgroundResource(R.mipmap.song_seleted);
    this.bt_acti_music_mic.setBackgroundResource(R.mipmap.act_mus_mic);
    this.iv_acti_music_mic.setVisibility(View.GONE);
    this.playSongBusiness.recordStop();
    this.vv_act_music.setNullArr();
    this.vv_act_music.setVisibility(View.GONE);*/
  }

  private void startMic()
  {
    isRecording = true;
  }

  private void stopSong()
  {
    /*this.playSongBusiness.stopPLayer();
    ServicePlayer.isPlay = false;
    if (isRecording)
      isRecording = false;*/
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void intAnimation()
  {
    this.loaderLoadAnimation.setDuration(100L);
    this.loaderLoadAnimation.setFillAfter(true);
    this.loaderLoadAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
    this.loaderReturnAnimation.setDuration(100L);
    this.loaderReturnAnimation.setFillAfter(true);
    this.loaderReturnAnimation.setInterpolator(new AccelerateDecelerateInterpolator());
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    /*this.isOnActResult = true;
    this.playSongBusiness = new MusicServiceBusiness(this);
    if (paramInt2 == -1)
    {
      this.isOpenMusList = false;
      this.playSongBusiness.playTrack();
      if (this.playSongBusiness.getSongs().size() > 0)
        this.sb_music_acti.setOnSeekBarChangeListener(this);
    }
    else
    {
      return;
    }
    this.sb_music_acti.setOnSeekBarChangeListener(null);*/
  }

  public void onClick(View paramView)
  {
    /*switch (paramView.getId())
    {
    case R.id.iv_acti_music_mic:
    case R.id.vv_act_music:
    default:
    case R.id.iv_acti_music_back:
    case R.id.iv_acti_music_play:
    case R.id.iv_acti_music_next:
    case R.id.bt_acti_mus_list:
      do
      {
        do
        {
          do
          {
            do
              return;
            while (this.playSongBusiness.getSongs().size() == 0);
            if (!this.playSongBusiness.getIsPlaying())
            {
              this.iv_acti_music_loader.clearAnimation();
              this.iv_acti_music_loader.startAnimation(this.loaderLoadAnimation);
            }
            ServicePlayer.isPlay = true;
            this.playSongBusiness.backSong();
            this.iv_acti_music_play.setBackgroundResource(R.mipmap.music_stop);
            return;
          }
          while (this.playSongBusiness.getSongs().size() == 0);
          if (System.currentTimeMillis() - this.clickPlaySpace < 200L)
          {
            this.clickPlaySpace = System.currentTimeMillis();
            return;
          }
          this.clickPlaySpace = System.currentTimeMillis();
          if (this.playSongBusiness.getIsPlaying())
          {
            stopSong();
            return;
          }
          playSong();
          return;
        }
        while (this.playSongBusiness.getSongs().size() == 0);
        if (!this.playSongBusiness.getIsPlaying())
        {
          this.iv_acti_music_loader.clearAnimation();
          this.iv_acti_music_loader.startAnimation(this.loaderLoadAnimation);
        }
        this.playSongBusiness.nextSong();
        ServicePlayer.isPlay = true;
        this.iv_acti_music_play.setBackgroundResource(R.mipmap.music_stop);
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
          }
        }
        , 500L);
        return;
        showPlaySongViews();
      }
      while (!isRecording);
      isRecording = false;
      return;
    case R.id.bt_acti_music_mic:
    }
    if (this.playSongBusiness.getIsPlaying())
      stopSong();
    hidePlaySongViews();
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (!ActiMusic.isRecording)
          ActiMusic.isRecording = true;
      }
    }
    , 100L);*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.acti_music);
    ButterKnife.bind(this);
    findView();
    setMyTitle();
    setListener();
    init();
  }

  protected void onDestroy()
  {
    new MyBusiness(this).putData4ClassName(DeviceListActivity.deviceMacAddress, this.songRecord);
    super.onDestroy();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onPause()
  {
    super.onPause();
    /*Main.myService.setCallBack(null);
    Main.myService.setRecordCallBack(null);
    this.isMicClick = false;
    this.playSongBusiness.recordStop();
    this.vv_act_music.setNullArr();
    showPlaySongViews();
    if (isRecording)
      isRecording = false;
    if (!this.isOpenMusList)
      this.playSongBusiness = null;*/
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
  }

  protected void onResume()
  {
    super.onResume();
    /*Main.myService.setRecordCallBack(new ServicePlayer.RecordCallBack()
    {
      public void callBack(int[] paramArrayOfInt)
      {
        ActiMusic.this.runOnUiThread(new Runnable(paramArrayOfInt)
        {
          public void run()
          {
            ActiMusic.this.vv_act_music.setRateArr(this.val$data);
            for (int i = 0; i < this.val$data.length; i++)
              System.out.println("  ￥%……&*（   " + this.val$data[i]);
          }
        });
      }
    });
    this.playSongBusiness = new MusicServiceBusiness(this);
    Main.myService.setCallBack(new ServicePlayer.SpCallBack()
    {
      public void songInfoCallBack(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
      {
        ActiMusic.this.tv_acti_music_song_name.setText(paramString1);
        ActiMusic.this.tv_acti_music_song_current_time.setText(paramString2);
        ActiMusic.this.tv_acti_music_song_all_time.setText(paramString3);
        ActiMusic.this.sb_music_acti.setProgress(paramInt);
        if (paramString4.equals("play"))
        {
          ActiMusic.this.iv_acti_music_play.setBackgroundResource(R.mipmap.music_stop);
          if (!ActiMusic.this.isPlay)
            ActiMusic.this.iv_acti_music_loader.startAnimation(ActiMusic.this.loaderLoadAnimation);
        }
        for (ActiMusic.this.isPlay = true; ; ActiMusic.this.isPlay = false)
        {
          System.out.println("songInfoCallBack  " + paramString1 + "      " + paramInt + "   " + paramString2 + "        " + paramString3 + "     " + paramString4);
          return;
          ActiMusic.this.iv_acti_music_play.setBackgroundResource(R.mipmap.music_paly);
          if (!ActiMusic.this.isPlay)
            continue;
          ActiMusic.this.iv_acti_music_loader.startAnimation(ActiMusic.this.loaderReturnAnimation);
        }
      }
    });
    Main.myService.setSongRecordCallBack(new ServicePlayer.SongRecordCallBack()
    {
      public void onSetSongs(List<SongVo> paramList)
      {
        ActiMusic.this.songRecord.songs.clear();
        ActiMusic.this.songRecord.songs.addAll(paramList);
      }

      public void onSongPercentChange(int paramInt)
      {
        ActiMusic.this.songRecord.setPercent(paramInt);
      }

      public void onSongPosiChange(int paramInt)
      {
        ActiMusic.this.songRecord.setPosi(paramInt);
      }
    });
    if (this.isOnActResult == true)
    {
      this.isOnActResult = false;
      startMic();
    }*/
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this.isTouchSB = true;
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    /*this.isTouchSB = false;
    if (this.playSongBusiness.getIsPlaying())
    {
      this.playSongBusiness.seek(this.playSongBusiness.getCurrentSongVo().getDuration() / 100 * paramSeekBar.getProgress());
      return;
    }
    paramSeekBar.setProgress(0);*/
  }

  public void seletedMus(View paramView)
  {
    /*this.isOpenMusList = true;
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(ActiMusic.this, ActiPlayList.class);
        ActiMusic.this.startActivityForResult(localIntent, 0);
      }
    }
    , 0L);*/
  }
}
