package com.ex.ltech.led.acti.scene;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.CircleColorView;
import com.ex.ltech.led.my_view.CircleColorView.OnCilcleColorSeletedListner;
import com.ex.ltech.led.my_view.MyEditAlertDialog;
import com.ex.ltech.led.my_view.SceneColorPickerView;
import com.ex.ltech.led.my_view.SceneColorPickerView.OnColorChangedListener;
import java.io.PrintStream;

public class ActNewCustom extends MyBaseActivity
  implements SceneColorPickerView.OnColorChangedListener, CircleColorView.OnCilcleColorSeletedListner, View.OnClickListener
{
  private Button bt_acti_new_custom_ping;
  private Button bt_acti_new_custom_tiao;
  private Button bt_acti_new_custom_zan;
  private int cilcleBallPosi = -1;
  private CircleColorView circleColorView;
  private MyEditAlertDialog dialog;
  private boolean isThisBG;
  private SceneColorPickerView sceneColorPickerView;
  private RelativeLayout thisRL;
  private TextView tv_acti_new_custom_circle_color_posi;

  private void confirmPhoneGurdPswd()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    View localView = View.inflate(this, 2130968856, null);
    AlertDialog localAlertDialog = localBuilder.create();
    localAlertDialog.getWindow().setLayout(-2, -2);
    localAlertDialog.setView(localView);
    localAlertDialog.setOnShowListener(new DialogInterface.OnShowListener()
    {
      public void onShow(DialogInterface paramDialogInterface)
      {
        ((InputMethodManager)ActNewCustom.this.getSystemService("input_method"));
      }
    });
    localAlertDialog.show();
  }

  private void findView()
  {
  }

  private void init()
  {
  }

  private void setListener()
  {
    this.circleColorView.setMyListener(this);
    this.sceneColorPickerView.setListener(this);
    this.bt_acti_new_custom_tiao.setOnClickListener(this);
    this.bt_acti_new_custom_zan.setOnClickListener(this);
    this.bt_acti_new_custom_ping.setOnClickListener(this);
  }

  private void setTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131100202);
    setEditStrColor(getResources().getColor(2131492962));
    setEditTextRes(2131100063);
  }

  private void setTitleView()
  {
  }

  public void onCilcleBgSeleted(int paramInt)
  {
    this.cilcleBallPosi = paramInt;
    this.tv_acti_new_custom_circle_color_posi.setText("" + (paramInt + 1));
    this.tv_acti_new_custom_circle_color_posi.setVisibility(0);
    this.tv_acti_new_custom_circle_color_posi.setAnimation(AnimationUtils.loadAnimation(this, 2131034125));
  }

  public void onCilcleBgTouchUp()
  {
    this.tv_acti_new_custom_circle_color_posi.setVisibility(8);
    this.tv_acti_new_custom_circle_color_posi.setAnimation(AnimationUtils.loadAnimation(this, 2131034123));
  }

  public void onClick(View paramView)
  {
    this.bt_acti_new_custom_ping.setBackgroundResource(2130903309);
    this.bt_acti_new_custom_zan.setBackgroundResource(2130903313);
    this.bt_acti_new_custom_tiao.setBackgroundResource(2130903311);
    if (paramView == this.bt_acti_new_custom_ping)
      this.bt_acti_new_custom_ping.setBackgroundResource(2130903310);
    if (paramView == this.bt_acti_new_custom_zan)
      this.bt_acti_new_custom_zan.setBackgroundResource(2130903314);
    if (paramView == this.bt_acti_new_custom_tiao)
      this.bt_acti_new_custom_tiao.setBackgroundResource(2130903312);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968609);
    findView();
    setListener();
    setTitle();
  }

  protected void onEdit()
  {
    super.onEdit();
    confirmPhoneGurdPswd();
  }

  public void onLongClick(int paramInt)
  {
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onPikerTouchUp(int paramInt)
  {
    if (this.cilcleBallPosi != -1)
      this.circleColorView.saveColor(paramInt, this.cilcleBallPosi);
  }

  public void onPikerXYChange(int paramInt)
  {
    this.circleColorView.changeCirclrColor(paramInt, this.cilcleBallPosi);
  }

  public void onRgbChange(int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    this.sceneColorPickerView.setPactHeight(this.thisRL.getHeight());
    System.out.println("onWindowFocusChanged");
    super.onWindowFocusChanged(paramBoolean);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.scene.ActNewCustom
 * JD-Core Version:    0.6.0
 */