package com.ex.ltech.led.vo;

import com.ex.ltech.led.utils.CharacterParser;

import java.io.Serializable;

public class SongVo
  implements Serializable
{
  private boolean addState;
  private String artist;
  private String data;
  private String date_added;
  private String display_name;
  private int duration;
  private int id;
  private boolean isPlaying;
  private String mPyin;
  private String path;
  private String sortLetters;
  private String title;

  public SongVo()
  {
  }

  public SongVo(String paramString)
  {
    this.title = paramString;
    if (paramString.equals("安居客"))
      System.out.println();
    String str = CharacterParser.getFirstLetter(paramString);
    if (str.matches("^[a-zA-Z]$"))
    {
      this.sortLetters = str.toUpperCase();
      return;
    }
    this.sortLetters = "#";
  }

  public SongVo(String paramString1, String paramString2)
  {
    this.title = paramString1;
    this.sortLetters = paramString2;
  }

  public String getArtist()
  {
    return this.artist;
  }

  public String getData()
  {
    return this.data;
  }

  public String getDate_added()
  {
    return this.date_added;
  }

  public String getDisplay_name()
  {
    return this.display_name;
  }

  public int getDuration()
  {
    return this.duration;
  }

  public String getDurationTime()
  {
    return "TimeUtil.toTime(duration)";
  }

  public int getId()
  {
    return this.id;
  }

  public String getPath()
  {
    return this.path;
  }

  public String getPyin()
  {
    return this.mPyin;
  }

  public String getSortLetters()
  {
    return CharacterParser.getFirstUpperLetter(this.title);
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isAddState()
  {
    return this.addState;
  }

  public boolean isPlaying()
  {
    return this.isPlaying;
  }

  public void setAddState(boolean paramBoolean)
  {
    this.addState = paramBoolean;
  }

  public void setArtist(String paramString)
  {
    this.artist = paramString;
  }

  public void setData(String paramString)
  {
    this.data = paramString;
  }

  public void setDate_added(String paramString)
  {
    this.date_added = paramString;
  }

  public void setDisplay_name(String paramString)
  {
    this.display_name = paramString;
  }

  public void setDuration(int paramInt)
  {
    this.duration = paramInt;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setPath(String paramString)
  {
    this.path = paramString;
  }

  public void setPlaying(boolean paramBoolean)
  {
    this.isPlaying = paramBoolean;
  }

  public void setPyin(String paramString)
  {
    this.mPyin = paramString;
  }

  public void setSortLetters(String paramString)
  {
    this.sortLetters = paramString;
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public String toString()
  {
    return "SongVo{title='" + this.title + '\'' + ", mPyin='" + this.mPyin + '\'' + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.vo.SongVo
 * JD-Core Version:    0.6.0
 */