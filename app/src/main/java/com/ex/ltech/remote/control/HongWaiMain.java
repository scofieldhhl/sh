package com.ex.ltech.remote.control;

import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import android.widget.TabWidget;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.vo.DeviceVo;
import com.ex.ltech.remote.control.scene.AtSceneActivity;
import com.ex.ltech.remote.control.time.act.ActTiming;
import com.ex.ltech.remote.control.yaokong.AtYaokongActivity;
import java.io.PrintStream;

public class HongWaiMain extends TabActivity
{
  public static DeviceVo deviceVo = new DeviceVo();
  public static int sonActHeightWithouTitle;
  public static int tabIndex;
  private int[] drawableArray = { 2130903834, 2130903836, 2130903838 };
  private int[] drawableSelectedArray = { 2130903833, 2130903835, 2130903837 };
  private LayoutInflater inflater;
  private TabHost tabHost;
  private TabHost.OnTabChangeListener tab_listener = new TabHost.OnTabChangeListener()
  {
    public void onTabChanged(String paramString)
    {
      if (paramString.equals("yaokong"));
      boolean bool = paramString.equals("scene");
      int i = 0;
      if (bool)
        i = 1;
      if (paramString.equals("time"))
        i = 2;
      HongWaiMain.this.changeTabItemBG(i);
    }
  };
  private int tempSonActHeightWithouTitle;
  private TabWidget widget;

  private void changeTabItemBG(int paramInt)
  {
    tabIndex = paramInt;
    this.widget = getTabHost().getTabWidget();
    int i = this.widget.getChildCount();
    int j = 0;
    if (j < i)
    {
      ImageView localImageView = (ImageView)this.widget.getChildAt(j).findViewById(2131558803);
      if (paramInt == j)
        localImageView.setImageResource(this.drawableSelectedArray[j]);
      while (true)
      {
        j++;
        break;
        localImageView.setImageResource(this.drawableArray[j]);
      }
    }
  }

  private void findView()
  {
    this.tabHost = getTabHost();
    this.tabHost.setOnTabChangedListener(this.tab_listener);
    this.inflater = LayoutInflater.from(this);
    View localView1 = setMenuView(2130903467);
    View localView2 = setMenuView(2130903469);
    View localView3 = setMenuView(2130903471);
    this.tabHost.addTab(this.tabHost.newTabSpec("yaokong").setContent(new Intent(this, AtYaokongActivity.class)).setIndicator(localView1));
    this.tabHost.addTab(this.tabHost.newTabSpec("scene").setContent(new Intent(this, AtSceneActivity.class)).setIndicator(localView2));
    this.tabHost.addTab(this.tabHost.newTabSpec("time").setContent(new Intent(this, ActTiming.class)).setIndicator(localView3));
  }

  private View setMenuView(int paramInt)
  {
    View localView = this.inflater.inflate(2130968650, null);
    ((ImageView)localView.findViewById(2131558803)).setImageResource(paramInt);
    return localView;
  }

  public int dip2px(int paramInt)
  {
    return (int)(0.5F + getResources().getDisplayMetrics().density * paramInt);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968787);
    findView();
    changeTabItemBG(0);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.tempSonActHeightWithouTitle = (this.tabHost.getMeasuredHeight() - this.widget.getMeasuredHeight() - dip2px(100));
    if (UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1) == -1)
    {
      sonActHeightWithouTitle = this.tabHost.getHeight() - this.widget.getHeight() - dip2px(100);
      UserFerences.getUserFerences(this).putValue("sonActHeightWithouTitle ", Integer.valueOf(sonActHeightWithouTitle));
    }
    while (true)
    {
      sonActHeightWithouTitle = this.tempSonActHeightWithouTitle;
      System.out.println("tabHost.getHeight() = " + this.tempSonActHeightWithouTitle);
      return;
      sonActHeightWithouTitle = UserFerences.getUserFerences(this).spFerences.getInt("sonActHeightWithouTitle ", -1);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.HongWaiMain
 * JD-Core Version:    0.6.0
 */