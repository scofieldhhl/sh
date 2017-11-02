package com.ex.ltech.led.acti.music;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import com.ex.ltech.led.vo.SongVo;
import java.util.List;
import java.util.Locale;

public class SongListAdapter extends BaseAdapter
  implements SectionIndexer
{
  ActiPlayList actiPlayList;
  private List<SongVo> itemVos;
  private Context pct;

  SongListAdapter(Activity paramActivity, List<SongVo> paramList)
  {
    this.pct = paramActivity;
    this.itemVos = paramList;
    this.actiPlayList = ((ActiPlayList)paramActivity);
  }

  public int getCount()
  {
    return this.itemVos.size();
  }

  public Object getItem(int paramInt)
  {
    return this.itemVos.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public List<SongVo> getItemVos()
  {
    return this.itemVos;
  }

  @SuppressLint({"DefaultLocale"})
  public int getPositionForSection(int paramInt)
  {
    for (int i = 0; i < this.itemVos.size(); i++)
      if (((SongVo)this.itemVos.get(i)).getPyin().substring(0, 1).toUpperCase(Locale.getDefault()).charAt(0) == paramInt)
        return i;
    return -1;
  }

  public int getSectionForPosition(int paramInt)
  {
    return 0;
  }

  public Object[] getSections()
  {
    return null;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    Holder localHolder;
    SongVo localSongVo;
    if (paramView == null)
    {
      localHolder = new Holder();
      paramView = LayoutInflater.from(this.pct).inflate(2130968641, null);
      localHolder.tv_acti_play_list_song_name = ((TextView)paramView.findViewById(2131558762));
      localHolder.iv_acti_play_list_song_selet = ((ImageView)paramView.findViewById(2131558764));
      localHolder.iv_acti_play_list_song_play = ((ImageView)paramView.findViewById(2131558763));
      paramView.setTag(localHolder);
      localSongVo = (SongVo)this.itemVos.get(paramInt);
      localHolder.tv_acti_play_list_song_name.setText(localSongVo.getTitle());
      if (!localSongVo.isAddState())
        break label188;
      localHolder.iv_acti_play_list_song_selet.setBackgroundResource(2130903724);
    }
    while (true)
    {
      localHolder.iv_acti_play_list_song_selet.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          SongListAdapter.this.actiPlayList.onSeleted(this.val$i);
        }
      });
      if (!localSongVo.isPlaying())
        break label201;
      localHolder.iv_acti_play_list_song_play.setOnClickListener(new View.OnClickListener(paramInt)
      {
        public void onClick(View paramView)
        {
          SongListAdapter.this.actiPlayList.onMusicPause(this.val$i);
        }
      });
      localHolder.iv_acti_play_list_song_play.setBackgroundResource(2130903611);
      return paramView;
      localHolder = (Holder)paramView.getTag();
      break;
      label188: localHolder.iv_acti_play_list_song_selet.setBackgroundResource(2130903595);
    }
    label201: localHolder.iv_acti_play_list_song_play.setOnClickListener(new View.OnClickListener(paramInt)
    {
      public void onClick(View paramView)
      {
        SongListAdapter.this.actiPlayList.onMusicPlay(this.val$i);
      }
    });
    localHolder.iv_acti_play_list_song_play.setBackgroundResource(2130903620);
    return paramView;
  }

  public void setItemVos(List<SongVo> paramList)
  {
    this.itemVos = paramList;
    notifyDataSetChanged();
  }

  class Holder
  {
    ImageView iv_acti_play_list_song_play;
    ImageView iv_acti_play_list_song_selet;
    TextView tv_acti_play_list_song_name;

    Holder()
    {
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.music.SongListAdapter
 * JD-Core Version:    0.6.0
 */