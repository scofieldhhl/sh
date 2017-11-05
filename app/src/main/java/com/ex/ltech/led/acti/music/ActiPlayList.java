package com.ex.ltech.led.acti.music;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.provider.MediaStore.Audio.Media;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.musci_service.ServicePlayer;
import com.ex.ltech.led.my_view.SideBar;
import com.ex.ltech.led.vo.SongVo;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import net.sourceforge.pinyin4j.PinyinHelper;

public class ActiPlayList extends MyBaseActivity
{
  private SongListAdapter adt;
  private Button btn_acti_play_add_song;
  private EditText etSearch;
  private List<SongVo> localSongVos = new ArrayList();
  private ListView lv_acti_play_list;
  public MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener()
  {
    public void onCompletion(MediaPlayer paramMediaPlayer)
    {
      paramMediaPlayer.release();
    }
  };
  private MediaPlayer mediaPlayer;
  private SideBar sideBar;
  private List<SongVo> vos = new ArrayList();

  private void findView()
  {
    this.lv_acti_play_list = ((ListView)findViewById(2131558758));
    this.btn_acti_play_add_song = ((Button)findViewById(2131558760));
    this.etSearch = ((EditText)findViewById(2131558757));
    this.sideBar = ((SideBar)findViewById(2131558759));
    this.sideBar.setListView(this.lv_acti_play_list);
    TextView localTextView = (TextView)findViewById(2131558761);
    this.sideBar.setTextView(localTextView);
  }

  private void init()
  {
    this.localSongVos = getSongsFromSQL();
    this.vos = getSongsFromSQL();
    this.adt = new SongListAdapter(this, this.vos);
    this.lv_acti_play_list.setAdapter(this.adt);
    this.mediaPlayer = new MediaPlayer();
    this.mediaPlayer.setOnCompletionListener(this.mOnCompletionListener);
  }

  private void setListener()
  {
    this.lv_acti_play_list.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
      }
    });
    this.btn_acti_play_add_song.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
      }
    });
    this.etSearch.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramEditable)
      {
      }

      public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
      }

      public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
      {
        if (paramCharSequence.length() != 0)
        {
          ArrayList localArrayList = new ArrayList();
          for (int i = 0; i < ActiPlayList.this.localSongVos.size(); i++)
          {
            if (!((SongVo)ActiPlayList.this.localSongVos.get(i)).getTitle().contains(paramCharSequence))
              continue;
            localArrayList.add(ActiPlayList.this.localSongVos.get(i));
          }
          ActiPlayList.this.vos.clear();
          ActiPlayList.this.vos.addAll(localArrayList);
          ActiPlayList.this.adt.notifyDataSetChanged();
          return;
        }
        ActiPlayList.this.vos.clear();
        ActiPlayList.this.vos.addAll(ActiPlayList.this.localSongVos);
        ActiPlayList.this.adt.notifyDataSetChanged();
      }
    });
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(2131100194);
    setEditTextRes(R.string.finish, getResources().getColor(2131492892));
  }

  public String getPinYinHeadChar(String paramString)
  {
    String str = "";
    int i = 0;
    if (i < 1)
    {
      char c = paramString.charAt(i);
      String[] arrayOfString = PinyinHelper.toHanyuPinyinStringArray(c);
      if (arrayOfString != null);
      for (str = str + arrayOfString[0].charAt(0); ; str = str + c)
      {
        i++;
        break;
      }
    }
    return str.toUpperCase();
  }

  public List<SongVo> getSongsFromSQL()
  {
    Cursor localCursor = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, MusicManager.media_info, null, null, "title_key");
    localCursor.moveToFirst();
    int i = 0;
    try
    {
      while (i < localCursor.getCount())
      {
        SongVo localSongVo = new SongVo();
        localSongVo.setTitle(localCursor.getString(localCursor.getColumnIndex("title")));
        localSongVo.setArtist(localCursor.getString(localCursor.getColumnIndex("artist")));
        localSongVo.setDuration((int)localCursor.getLong(localCursor.getColumnIndex("duration")));
        localSongVo.setId((int)localCursor.getLong(localCursor.getColumnIndex("_id")));
        localSongVo.setPath(localCursor.getString(localCursor.getColumnIndex("_data")));
        localSongVo.setPyin(getPinYinHeadChar(localSongVo.getTitle()));
        this.vos.add(localSongVo);
        System.out.println(localSongVo.toString());
        localCursor.moveToNext();
        i++;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      localCursor.close();
    }
    return this.vos;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968640);
    findView();
    init();
    setListener();
    setMyTitle();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.vos.size() != 0)
    {
      ArrayList localArrayList = new ArrayList();
      for (int i = 0; i < this.vos.size(); i++)
      {
        SongVo localSongVo = (SongVo)this.vos.get(i);
        if (!localSongVo.isAddState())
          continue;
        localArrayList.add(localSongVo);
      }
      if (localArrayList.size() != 0)
      {
        ServicePlayer.songList.clear();
        ServicePlayer.songList.addAll(localArrayList);
        setResult(-1);
      }
    }
    finish();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onMusicPause(int paramInt)
  {
    for (int i = 0; i < this.vos.size(); i++)
      ((SongVo)this.vos.get(i)).setPlaying(false);
    this.adt.notifyDataSetChanged();
    try
    {
      if (this.mediaPlayer != null)
      {
        if (this.mediaPlayer.isPlaying())
          this.mediaPlayer.stop();
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

  // ERROR //
  public void onMusicPlay(int paramInt)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: iload_2
    //   3: aload_0
    //   4: getfield 33	com/ex/ltech/led/acti/music/ActiPlayList:vos	Ljava/util/List;
    //   7: invokeinterface 321 1 0
    //   12: if_icmpge +26 -> 38
    //   15: aload_0
    //   16: getfield 33	com/ex/ltech/led/acti/music/ActiPlayList:vos	Ljava/util/List;
    //   19: iload_2
    //   20: invokeinterface 325 2 0
    //   25: checkcast 228	com/ex/ltech/led/vo/SongVo
    //   28: iconst_0
    //   29: invokevirtual 362	com/ex/ltech/led/vo/SongVo:setPlaying	(Z)V
    //   32: iinc 2 1
    //   35: goto -33 -> 2
    //   38: aload_0
    //   39: getfield 33	com/ex/ltech/led/acti/music/ActiPlayList:vos	Ljava/util/List;
    //   42: iload_1
    //   43: invokeinterface 325 2 0
    //   48: checkcast 228	com/ex/ltech/led/vo/SongVo
    //   51: iconst_1
    //   52: invokevirtual 362	com/ex/ltech/led/vo/SongVo:setPlaying	(Z)V
    //   55: aload_0
    //   56: getfield 47	com/ex/ltech/led/acti/music/ActiPlayList:adt	Lcom/ex/ltech/led/acti/music/SongListAdapter;
    //   59: invokevirtual 365	com/ex/ltech/led/acti/music/SongListAdapter:notifyDataSetChanged	()V
    //   62: aload_0
    //   63: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   66: invokevirtual 387	android/media/MediaPlayer:reset	()V
    //   69: aload_0
    //   70: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   73: aload_0
    //   74: getfield 33	com/ex/ltech/led/acti/music/ActiPlayList:vos	Ljava/util/List;
    //   77: iload_1
    //   78: invokeinterface 325 2 0
    //   83: checkcast 228	com/ex/ltech/led/vo/SongVo
    //   86: invokevirtual 390	com/ex/ltech/led/vo/SongVo:getPath	()Ljava/lang/String;
    //   89: invokevirtual 393	android/media/MediaPlayer:setDataSource	(Ljava/lang/String;)V
    //   92: aload_0
    //   93: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   96: invokevirtual 396	android/media/MediaPlayer:prepare	()V
    //   99: aload_0
    //   100: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   103: invokevirtual 399	android/media/MediaPlayer:start	()V
    //   106: return
    //   107: astore_3
    //   108: aload_3
    //   109: invokevirtual 377	java/lang/Exception:printStackTrace	()V
    //   112: aload_0
    //   113: new 99	android/media/MediaPlayer
    //   116: dup
    //   117: invokespecial 100	android/media/MediaPlayer:<init>	()V
    //   120: putfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   123: aload_0
    //   124: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   127: aload_0
    //   128: getfield 40	com/ex/ltech/led/acti/music/ActiPlayList:mOnCompletionListener	Landroid/media/MediaPlayer$OnCompletionListener;
    //   131: invokevirtual 106	android/media/MediaPlayer:setOnCompletionListener	(Landroid/media/MediaPlayer$OnCompletionListener;)V
    //   134: aload_0
    //   135: getfield 102	com/ex/ltech/led/acti/music/ActiPlayList:mediaPlayer	Landroid/media/MediaPlayer;
    //   138: invokevirtual 387	android/media/MediaPlayer:reset	()V
    //   141: goto -72 -> 69
    //   144: astore 7
    //   146: aload 7
    //   148: invokevirtual 400	java/lang/IllegalArgumentException:printStackTrace	()V
    //   151: return
    //   152: astore 6
    //   154: aload 6
    //   156: invokevirtual 401	java/lang/SecurityException:printStackTrace	()V
    //   159: return
    //   160: astore 5
    //   162: aload 5
    //   164: invokevirtual 402	java/lang/IllegalStateException:printStackTrace	()V
    //   167: return
    //   168: astore 4
    //   170: aload 4
    //   172: invokevirtual 377	java/lang/Exception:printStackTrace	()V
    //   175: return
    //
    // Exception table:
    //   from	to	target	type
    //   62	69	107	java/lang/Exception
    //   69	106	144	java/lang/IllegalArgumentException
    //   69	106	152	java/lang/SecurityException
    //   69	106	160	java/lang/IllegalStateException
    //   69	106	168	java/lang/Exception
  }

  protected void onPause()
  {
    super.onPause();
    onMusicPause(0);
  }

  public void onSeleted(int paramInt)
  {
    updateSongList(paramInt);
    this.adt.notifyDataSetChanged();
  }

  public void updateSongList(int paramInt)
  {
    SongVo localSongVo = (SongVo)this.vos.get(paramInt);
    if (!localSongVo.isAddState());
    for (boolean bool = true; ; bool = false)
    {
      localSongVo.setAddState(bool);
      this.vos.remove(paramInt);
      this.vos.add(paramInt, localSongVo);
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.music.ActiPlayList
 * JD-Core Version:    0.6.0
 */