package com.ex.ltech.led.my_view.swipemenulistview;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;

public class SwipeMenuItem
{
  private Drawable background;
  private int height;
  private Drawable icon;
  private int id;
  private Context mContext;
  private String title;
  private int titleColor;
  private int titleSize;
  private int width;

  public SwipeMenuItem(Context paramContext)
  {
    this.mContext = paramContext;
  }

  public Drawable getBackground()
  {
    return this.background;
  }

  public int getHeight()
  {
    return this.height;
  }

  public Drawable getIcon()
  {
    return this.icon;
  }

  public int getId()
  {
    return this.id;
  }

  public String getTitle()
  {
    return this.title;
  }

  public int getTitleColor()
  {
    return this.titleColor;
  }

  public int getTitleSize()
  {
    return this.titleSize;
  }

  public int getWidth()
  {
    return this.width;
  }

  public void setBackground(int paramInt)
  {
    this.background = this.mContext.getResources().getDrawable(paramInt);
  }

  public void setBackground(Drawable paramDrawable)
  {
    this.background = paramDrawable;
  }

  public void setHeight(int paramInt)
  {
    this.height = paramInt;
  }

  public void setIcon(int paramInt)
  {
    this.icon = this.mContext.getResources().getDrawable(paramInt);
  }

  public void setIcon(Drawable paramDrawable)
  {
    this.icon = paramDrawable;
  }

  public void setId(int paramInt)
  {
    this.id = paramInt;
  }

  public void setTitle(int paramInt)
  {
    setTitle(this.mContext.getString(paramInt));
  }

  public void setTitle(String paramString)
  {
    this.title = paramString;
  }

  public void setTitleColor(int paramInt)
  {
    this.titleColor = paramInt;
  }

  public void setTitleSize(int paramInt)
  {
    this.titleSize = paramInt;
  }

  public void setWidth(int paramInt)
  {
    this.width = paramInt;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.swipemenulistview.SwipeMenuItem
 * JD-Core Version:    0.6.0
 */