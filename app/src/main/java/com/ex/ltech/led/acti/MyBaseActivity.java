package com.ex.ltech.led.acti;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.my_view.MyAlertDialog13;
import com.indris.material.RippleView;
import java.util.Locale;

public class MyBaseActivity extends Activity
{
  private RippleView btn_title_view_edit;
  private RippleView btn_title_view_menu;
  private RelativeLayout relativeLayout;
  private View.OnClickListener title_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131558781:
      case 2131558783:
        MyBaseActivity.this.onMenu();
        return;
      case 2131558782:
        MyBaseActivity.this.onMenu();
        return;
      case 2131558784:
        MyBaseActivity.this.onEdit();
        return;
      case 2131558785:
      }
      MyBaseActivity.this.onEdit();
    }
  };
  private TextView tv_title_device_name;
  private TextView tv_title_view_edit;
  private TextView tv_title_view_title;

  protected void goAct(Class paramClass)
  {
    startActivity(new Intent(this, paramClass));
  }

  protected void goAct4result(Class paramClass, int paramInt)
  {
    startActivityForResult(new Intent(this, paramClass), paramInt);
  }

  protected void goAct4result(Class paramClass, int paramInt1, String paramString, int paramInt2)
  {
    Intent localIntent = new Intent(this, paramClass);
    localIntent.putExtra(paramString, paramInt2);
    startActivityForResult(localIntent, paramInt1);
  }

  protected void goAct4result2(Class paramClass, int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3)
  {
    Intent localIntent = new Intent(this, paramClass);
    localIntent.putExtra(paramString1, paramInt2);
    localIntent.putExtra(paramString2, paramInt3);
    startActivityForResult(localIntent, paramInt1);
  }

  protected void hideEdit(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.btn_title_view_edit.setVisibility(8);
      return;
    }
    this.btn_title_view_edit.setVisibility(0);
  }

  protected boolean isMobileNO(String paramString)
  {
    boolean bool = true;
    if (!UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", bool));
    do
    {
      return bool;
      if (TextUtils.isEmpty(paramString))
        return false;
      bool = paramString.matches("13\\d{9}|14[57]\\d{8}|15[012356789]\\d{8}|18[012356789]\\d{8}|17[0678]\\d{8}");
    }
    while (bool);
    new MyAlertDialog13(this).showInputTureNumDialog();
    return bool;
  }

  public boolean isZh()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    String str1 = localLocale.getCountry();
    String str2 = localLocale.getLanguage();
    return (str2 + "_" + str1).equals("zh_CN");
  }

  protected void longToast(int paramInt)
  {
    Toast.makeText(this, paramInt, 1).show();
  }

  protected void onEdit()
  {
  }

  protected void onMenu()
  {
  }

  protected void onPause()
  {
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
  }

  protected void setBgAlpha()
  {
    this.relativeLayout.setBackgroundColor(Color.argb(0, 0, 0, 0));
  }

  protected void setBgWhite()
  {
    this.relativeLayout.setBackgroundColor(Color.rgb(255, 255, 255));
  }

  protected void setDeviceTextRes(String paramString)
  {
    this.tv_title_device_name.setVisibility(0);
    this.tv_title_device_name.setText(paramString);
    this.tv_title_device_name.setOnClickListener(this.title_listener);
  }

  protected void setDeviceTextRes(String paramString, int paramInt)
  {
    this.tv_title_device_name.setVisibility(0);
    this.tv_title_device_name.setText(paramString);
    this.tv_title_device_name.setTextColor(getResources().getColor(paramInt));
    this.tv_title_device_name.setOnClickListener(this.title_listener);
  }

  protected void setEditImageRes(int paramInt)
  {
    this.tv_title_view_edit.setVisibility(8);
    this.btn_title_view_edit.setOnClickListener(this.title_listener);
    this.btn_title_view_edit.setVisibility(0);
    this.btn_title_view_edit.setBackgroundResource(paramInt);
  }

  protected void setEditImageText(int paramInt1, int paramInt2)
  {
    this.tv_title_view_edit.setVisibility(8);
    this.btn_title_view_edit.setOnClickListener(this.title_listener);
    this.btn_title_view_edit.setVisibility(0);
    this.btn_title_view_edit.setTextColor(paramInt2);
    this.btn_title_view_edit.setTextSize(1, 18.0F);
    this.btn_title_view_edit.setText(paramInt1);
  }

  protected void setEditStr(String paramString)
  {
    this.tv_title_view_edit.setVisibility(8);
    this.btn_title_view_edit.setText(paramString);
    this.btn_title_view_edit.setOnClickListener(this.title_listener);
    this.btn_title_view_edit.setVisibility(0);
  }

  protected void setEditStrColor(int paramInt)
  {
    this.tv_title_view_edit.setVisibility(8);
    this.btn_title_view_edit.setTextColor(paramInt);
  }

  protected void setEditStrRes(int paramInt)
  {
    this.tv_title_view_edit.setVisibility(8);
    this.btn_title_view_edit.setVisibility(0);
    this.btn_title_view_edit.setText(paramInt);
    this.btn_title_view_edit.setOnClickListener(this.title_listener);
  }

  protected void setEditTextRes(int paramInt)
  {
    setEditImageText(paramInt, getResources().getColor(2131492962));
  }

  protected void setEditTextRes(int paramInt1, int paramInt2)
  {
    setEditImageText(paramInt1, paramInt2);
  }

  protected void setMenuBackgroundRes(int paramInt)
  {
    this.btn_title_view_menu.setBackgroundResource(paramInt);
    this.btn_title_view_menu.setOnClickListener(this.title_listener);
    this.btn_title_view_menu.setVisibility(0);
  }

  protected void setMenuStr(String paramString)
  {
    this.btn_title_view_menu.setText(paramString);
    this.btn_title_view_menu.setOnClickListener(this.title_listener);
    this.btn_title_view_menu.setVisibility(0);
  }

  protected void setMenuStrRes(int paramInt)
  {
    this.btn_title_view_menu.setText(paramInt);
    this.btn_title_view_menu.setOnClickListener(this.title_listener);
    this.btn_title_view_menu.setVisibility(0);
  }

  protected void setSlidingMenu()
  {
  }

  protected void setSlidingNoTouchMode()
  {
  }

  protected void setTiTleText(String paramString)
  {
    this.tv_title_view_title.setText(paramString);
  }

  protected void setTiTleTextColor(int paramInt)
  {
    this.tv_title_view_title.setTextColor(paramInt);
  }

  protected void setTiTleTextRes(int paramInt)
  {
    this.tv_title_view_title.setText(paramInt);
  }

  protected void setViewTitle()
  {
    this.relativeLayout = ((RelativeLayout)findViewById(2131558780));
    this.tv_title_view_title = ((TextView)findViewById(2131558783));
    this.tv_title_device_name = ((TextView)findViewById(2131558782));
    this.btn_title_view_menu = ((RippleView)findViewById(2131558781));
    this.btn_title_view_edit = ((RippleView)findViewById(2131558784));
    this.tv_title_view_edit = ((TextView)findViewById(2131558785));
  }

  protected void showBottomLine()
  {
    findViewById(2131558786).setVisibility(0);
  }

  protected void toast(int paramInt)
  {
    Toast.makeText(this, paramInt, 0).show();
  }

  protected void toast(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  protected void toggleMenu()
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.MyBaseActivity
 * JD-Core Version:    0.6.0
 */