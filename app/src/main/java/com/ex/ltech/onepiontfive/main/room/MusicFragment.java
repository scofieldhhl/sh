package com.ex.ltech.onepiontfive.main.room;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.acti.music.ActiPlayList;
import com.ex.ltech.led.musci_service.MusicServiceBusiness;
import com.ex.ltech.led.musci_service.ServicePlayer;
import com.ex.ltech.led.musci_service.ServicePlayer.RecordCallBack;
import com.ex.ltech.led.musci_service.ServicePlayer.SongRecordCallBack;
import com.ex.ltech.led.musci_service.ServicePlayer.SpCallBack;
import com.ex.ltech.led.my_view.VisualizerView;
import com.ex.ltech.led.vo.SongRecord;
import com.ex.ltech.led.vo.SongVo;
import com.ex.ltech.onepiontfive.main.AtMain;
import com.ex.ltech.onepiontfive.main.MyBaseFt;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.fragmentmaster.app.Request;
import com.indris.material.RippleView;
import java.util.List;

public class MusicFragment extends MyBaseFt
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener
{
  public static boolean isRecording = false;
  BroadcastReceiver broadcastReceiver;

  @Bind({2131558730})
  RippleView btActiMusList;

  @Bind({2131558731})
  RippleView btActiMusicMic;

  @Bind({2131558722})
  Button btMusicActGoList;

  @Bind({2131558784})
  RippleView btnTitleViewEdit;

  @Bind({2131558781})
  RippleView btnTitleViewMenu;
  private long clickPlaySpace;
  private Handler handler = new Handler();
  private Intent i;
  boolean isMicClick;
  private boolean isOnActResult;
  private boolean isOpenMusList;
  boolean isPlay;
  private boolean isReturn = false;
  boolean isTouchSB;

  @Bind({2131558725})
  Button ivActiMusicBack;

  @Bind({2131558717})
  ImageView ivActiMusicDisc;

  @Bind({2131558718})
  ImageView ivActiMusicLoader;

  @Bind({2131558727})
  RippleView ivActiMusicMic;

  @Bind({2131558728})
  Button ivActiMusicNext;

  @Bind({2131558726})
  RippleView ivActiMusicPlay;

  @Bind({2131558719})
  ImageView ivActiNullLoader;
  final RotateAnimation loaderLoadAnimation = new RotateAnimation(0.0F, 35.0F, 1, 0.426F, 1, 0.12F);
  final RotateAnimation loaderReturnAnimation = new RotateAnimation(35.0F, 0.0F, 1, 0.426F, 1, 0.12F);
  private MusicServiceBusiness playSongBusiness;

  @Bind({2131558721})
  SeekBar sbMusicActi;
  private SongRecord songRecord;

  @Bind({2131558724})
  TextView tvActiMusicSongAllTime;

  @Bind({2131558723})
  TextView tvActiMusicSongCurrentTime;

  @Bind({2131558720})
  TextView tvActiMusicSongName;

  @Bind({2131558782})
  TextView tvTitleDeviceName;

  @Bind({2131558785})
  TextView tvTitleViewEdit;

  @Bind({2131558783})
  TextView tvTitleViewTitle;
  View view;

  @Bind({2131558729})
  VisualizerView vvActMusic;

  private void hidePlaySongViews()
  {
    if (getIsPlaying())
    {
      this.ivActiMusicLoader.clearAnimation();
      this.ivActiMusicLoader.startAnimation(this.loaderReturnAnimation);
      this.isPlay = false;
      stopSong();
    }
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        MusicFragment.this.btMusicActGoList.setVisibility(View.GONE);
        MusicFragment.this.sbMusicActi.setVisibility(View.GONE);
        MusicFragment.this.ivActiMusicDisc.setVisibility(View.GONE);
        MusicFragment.this.view.findViewById(2131558719).setVisibility(View.VISIBLE);
        MusicFragment.this.tvActiMusicSongName.setVisibility(View.GONE);
        MusicFragment.this.tvActiMusicSongCurrentTime.setVisibility(View.GONE);
        MusicFragment.this.tvActiMusicSongAllTime.setVisibility(View.GONE);
        MusicFragment.this.sbMusicActi.setVisibility(View.GONE);
        MusicFragment.this.ivActiMusicBack.setVisibility(View.GONE);
        MusicFragment.this.ivActiMusicPlay.setVisibility(View.GONE);
        MusicFragment.this.ivActiMusicNext.setVisibility(View.GONE);
        MusicFragment.this.ivActiMusicMic.setVisibility(View.VISIBLE);
        MusicFragment.this.vvActMusic.setVisibility(View.VISIBLE);
        MusicFragment.this.btActiMusicMic.setBackgroundResource(2130903044);
        MusicFragment.this.btActiMusList.setBackgroundResource(2130903757);
      }
    }
    , 200L);
  }

  private void init()
  {
    intAnimation();
    this.songRecord = ((SongRecord)new MyBusiness(AtMain.instance).getBean4ClassName(DeviceListActivity.deviceMacAddress, SongRecord.class));
    if ((this.songRecord != null) && (this.songRecord.getPosi() != -1) && (this.songRecord.getPercent() != -1) && (this.songRecord.songs != null) && (this.songRecord.songs.size() > 0))
    {
      AtMain.myService.setSongRecordData(this.songRecord.getPosi(), this.songRecord.getPercent(), this.songRecord.songs);
      this.tvActiMusicSongName.setText(((SongVo)this.songRecord.songs.get(this.songRecord.getPosi())).getTitle());
    }
    while (true)
    {
      AtMain.myService.setSongRecordCallBack(new ServicePlayer.SongRecordCallBack()
      {
        public void onSetSongs(List<SongVo> paramList)
        {
          MusicFragment.this.songRecord.songs.clear();
          MusicFragment.this.songRecord.songs.addAll(paramList);
        }

        public void onSongPercentChange(int paramInt)
        {
          MusicFragment.this.songRecord.setPercent(paramInt);
        }

        public void onSongPosiChange(int paramInt)
        {
          MusicFragment.this.songRecord.setPosi(paramInt);
        }
      });
      AtMain.myService.setRecordCallBack(new ServicePlayer.RecordCallBack()
      {
        public void callBack(int[] paramArrayOfInt)
        {
          MusicFragment.this.getActivity().runOnUiThread(new Runnable(paramArrayOfInt)
          {
            public void run()
            {
              if (this.val$data != null)
                MusicFragment.this.vvActMusic.setRateArr(this.val$data);
            }
          });
        }
      });
      this.playSongBusiness = new MusicServiceBusiness(getActivity());
      AtMain.myService.setCallBack(new ServicePlayer.SpCallBack()
      {
        public void songInfoCallBack(String paramString1, int paramInt, String paramString2, String paramString3, String paramString4)
        {
          if (MusicFragment.this.isReturn)
            return;
          MusicFragment.this.tvActiMusicSongName.setText(paramString1);
          MusicFragment.this.tvActiMusicSongCurrentTime.setText(paramString2);
          MusicFragment.this.tvActiMusicSongAllTime.setText(paramString3);
          MusicFragment.this.sbMusicActi.setProgress(paramInt);
          if (paramString4.equals("play"))
          {
            MusicFragment.this.ivActiMusicPlay.setBackgroundResource(R.mipmap.music_stop);
            if (!MusicFragment.this.isPlay)
              MusicFragment.this.ivActiMusicLoader.startAnimation(MusicFragment.this.loaderLoadAnimation);
            MusicFragment.this.isPlay = true;
            return;
          }
          MusicFragment.this.ivActiMusicPlay.setBackgroundResource(R.mipmap.music_paly);
          if (MusicFragment.this.isPlay)
            MusicFragment.this.ivActiMusicLoader.startAnimation(MusicFragment.this.loaderReturnAnimation);
          MusicFragment.this.isPlay = false;
        }
      });
      new IntentFilter().addAction("MusicFragmentOnPressBack");
      this.broadcastReceiver = new BroadcastReceiver()
      {
        public void onReceive(Context paramContext, Intent paramIntent)
        {
          new MyBusiness(AtMain.instance).putData4ClassName(DeviceListActivity.deviceMacAddress, MusicFragment.this.songRecord);
          AtMain.myService.setRecordCallBack(null);
          AtMain.myService.setCallBack(null);
          LocalBroadcastManager.getInstance(MusicFragment.this.getActivity()).unregisterReceiver(MusicFragment.this.broadcastReceiver);
        }
      };
      return;
      this.songRecord = new SongRecord();
    }
  }

  private void playSong()
  {
    this.ivActiMusicPlay.setBackgroundResource(R.mipmap.music_stop);
    this.ivActiMusicLoader.clearAnimation();
    this.ivActiMusicLoader.startAnimation(this.loaderLoadAnimation);
    this.playSongBusiness.playTrack105(getRequest().getIntExtra("DTypeKey", 0), getRequest().getIntExtra("DRoomNumKey", 0), getRequest().getIntExtra("DIndexKey", 0), getRequest().getBooleanExtra("MusicIsGroupKey", false));
    ServicePlayer.isPlay = true;
    if (!isRecording)
      isRecording = true;
  }

  private void showPlaySongViews()
  {
    this.btMusicActGoList.setVisibility(View.VISIBLE);
    this.sbMusicActi.setVisibility(View.VISIBLE);
    this.ivActiMusicDisc.setVisibility(View.VISIBLE);
    this.view.findViewById(2131558719).setVisibility(View.GONE);
    this.tvActiMusicSongName.setVisibility(View.VISIBLE);
    this.tvActiMusicSongCurrentTime.setVisibility(View.VISIBLE);
    this.tvActiMusicSongAllTime.setVisibility(View.VISIBLE);
    this.sbMusicActi.setVisibility(View.VISIBLE);
    this.ivActiMusicBack.setVisibility(View.VISIBLE);
    this.ivActiMusicPlay.setVisibility(View.VISIBLE);
    this.ivActiMusicNext.setVisibility(View.VISIBLE);
    this.btActiMusList.setBackgroundResource(2130903758);
    this.btActiMusicMic.setBackgroundResource(2130903043);
    this.ivActiMusicMic.setVisibility(View.GONE);
    this.playSongBusiness.recordStop();
    this.vvActMusic.setNullArr();
    this.vvActMusic.setVisibility(View.GONE);
  }

  private void stopSong()
  {
    this.playSongBusiness.stopPLayer();
    ServicePlayer.isPlay = false;
    if (isRecording)
      isRecording = false;
  }

  public boolean getIsPlaying()
  {
    return AtMain.myService.getIsPlay();
  }

  public void initTitleView()
  {
    this.btnTitleViewMenu.setBackgroundResource(2130903274);
    this.tvTitleViewTitle.setText(2131100194);
    this.btnTitleViewMenu.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (MusicFragment.this.isMicClick)
        {
          MusicFragment.this.ivActiMusicMic.setBackgroundResource(2130903497);
          MusicFragment.this.playSongBusiness.recordStop();
          MusicFragment.this.vvActMusic.setNullArr();
        }
        while (true)
        {
          MusicFragment.access$202(MusicFragment.this, true);
          MusicFragment.this.finish();
          return;
          MusicFragment.this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
            }
          }
          , 100L);
        }
      }
    });
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

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    this.isOnActResult = true;
    this.playSongBusiness = new MusicServiceBusiness(getActivity());
    if (paramInt2 == -1)
    {
      this.isOpenMusList = false;
      playSong();
    }
    if (this.playSongBusiness.getSongs().size() > 0)
    {
      this.sbMusicActi.setOnSeekBarChangeListener(this);
      return;
    }
    this.sbMusicActi.setOnSeekBarChangeListener(null);
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131558723:
    case 2131558724:
    case 2131558729:
    default:
    case 2131558722:
    case 2131558731:
    case 2131558725:
    case 2131558728:
    case 2131558730:
    case 2131558726:
      do
      {
        do
        {
          do
          {
            do
            {
              return;
              this.isOpenMusList = true;
              this.handler.postDelayed(new Runnable()
              {
                public void run()
                {
                  Intent localIntent = new Intent(MusicFragment.this.getActivity(), ActiPlayList.class);
                  MusicFragment.this.startActivityForResult(localIntent, 0);
                }
              }
              , 0L);
              return;
              if (getIsPlaying())
                stopSong();
              hidePlaySongViews();
              new Handler().postDelayed(new Runnable()
              {
                public void run()
                {
                  if (!MusicFragment.isRecording)
                    MusicFragment.isRecording = true;
                }
              }
              , 100L);
              return;
            }
            while (this.playSongBusiness.getSongs().size() == 0);
            if (!getIsPlaying())
            {
              this.ivActiMusicLoader.clearAnimation();
              this.ivActiMusicLoader.startAnimation(this.loaderLoadAnimation);
            }
            ServicePlayer.isPlay = true;
            this.playSongBusiness.backSong();
            this.ivActiMusicPlay.setBackgroundResource(R.mipmap.music_stop);
            return;
          }
          while (this.playSongBusiness.getSongs().size() == 0);
          if (!getIsPlaying())
          {
            this.ivActiMusicLoader.clearAnimation();
            this.ivActiMusicLoader.startAnimation(this.loaderLoadAnimation);
          }
          this.playSongBusiness.nextSong();
          ServicePlayer.isPlay = true;
          this.ivActiMusicPlay.setBackgroundResource(R.mipmap.music_stop);
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
      }
      while (this.playSongBusiness.getSongs().size() == 0);
      if (System.currentTimeMillis() - this.clickPlaySpace < 200L)
      {
        this.clickPlaySpace = System.currentTimeMillis();
        return;
      }
      this.clickPlaySpace = System.currentTimeMillis();
      if (getIsPlaying())
      {
        stopSong();
        return;
      }
      playSong();
      return;
    case 2131558727:
    }
    try
    {
      boolean bool1 = this.isMicClick;
      boolean bool2 = false;
      if (!bool1)
        bool2 = true;
      this.isMicClick = bool2;
      if (this.isMicClick)
      {
        this.playSongBusiness.recordStart(getRequest().getIntExtra("DTypeKey", 0), getRequest().getIntExtra("DRoomNumKey", 0), getRequest().getIntExtra("DIndexKey", 0), getRequest().getBooleanExtra("MusicIsGroupKey", false));
        this.ivActiMusicMic.setBackgroundResource(2130903498);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      Toast.makeText(getActivity(), localException.getMessage(), 1).show();
      return;
    }
    this.ivActiMusicMic.setBackgroundResource(2130903497);
    this.playSongBusiness.recordStop();
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        MusicFragment.this.vvActMusic.setNullArr();
      }
    }
    , 100L);
  }

  public View onCreateView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    if (this.view == null)
      this.view = paramLayoutInflater.inflate(2130968635, null);
    ButterKnife.bind(this, this.view);
    initTitleView();
    init();
    return this.view;
  }

  public void onDestroy()
  {
    super.onDestroy();
  }

  public void onDestroyView()
  {
    super.onDestroyView();
    ButterKnife.unbind(this);
    new MyBusiness(AtMain.instance).putData4ClassName(DeviceListActivity.deviceMacAddress, this.songRecord);
    AtMain.myService.setRecordCallBack(null);
    AtMain.myService.setCallBack(null);
    LocalBroadcastManager.getInstance(getActivity()).unregisterReceiver(this.broadcastReceiver);
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
  }

  public void onResume()
  {
    super.onResume();
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
    this.isTouchSB = true;
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    this.isTouchSB = false;
    if (getIsPlaying())
    {
      AtMain.myService.getCurrentSongVo().getDuration();
      MusicServiceBusiness localMusicServiceBusiness = this.playSongBusiness;
      localMusicServiceBusiness.seek(AtMain.myService.getCurrentSongVo().getDuration() / 100 * paramSeekBar.getProgress());
      return;
    }
    paramSeekBar.setProgress(0);
  }

  public void onViewCreated(View paramView, Bundle paramBundle)
  {
    super.onViewCreated(paramView, paramBundle);
    this.btActiMusList.setBackgroundResource(2130903758);
    this.btActiMusicMic.setBackgroundResource(2130903043);
    this.btMusicActGoList.setOnClickListener(this);
    this.btActiMusicMic.setOnClickListener(this);
    this.ivActiMusicPlay.setOnClickListener(this);
    this.btActiMusList.setOnClickListener(this);
    this.ivActiMusicMic.setOnClickListener(this);
    this.ivActiMusicBack.setOnClickListener(this);
    this.ivActiMusicNext.setOnClickListener(this);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.MusicFragment
 * JD-Core Version:    0.6.0
 */