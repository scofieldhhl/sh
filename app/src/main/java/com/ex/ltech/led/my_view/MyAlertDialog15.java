package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.view.ImageTextBigButton;

public class MyAlertDialog15 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      MyAlertDialog15.this.selelted1.setVisibility(8);
      MyAlertDialog15.this.selelted2.setVisibility(8);
      MyAlertDialog15.this.selelted3.setVisibility(8);
      MyAlertDialog15.this.selelted4.setVisibility(8);
      MyAlertDialog15.this.selelted5.setVisibility(8);
      MyAlertDialog15.this.selelted6.setVisibility(8);
      MyAlertDialog15.this.selelted7.setVisibility(8);
      MyAlertDialog15.this.selelted8.setVisibility(8);
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559071:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(0);
        MyAlertDialog15.this.selelted1.setVisibility(0);
        return;
      case 2131559072:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(1);
        MyAlertDialog15.this.selelted2.setVisibility(0);
        return;
      case 2131559073:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(2);
        MyAlertDialog15.this.selelted3.setVisibility(0);
        return;
      case 2131559074:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(3);
        MyAlertDialog15.this.selelted4.setVisibility(0);
        return;
      case 2131559075:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(4);
        MyAlertDialog15.this.selelted5.setVisibility(0);
        return;
      case 2131559076:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(5);
        MyAlertDialog15.this.selelted6.setVisibility(0);
        return;
      case 2131559077:
        if (MyAlertDialog15.this.myListener != null)
          MyAlertDialog15.this.myListener.onClick(6);
        MyAlertDialog15.this.selelted7.setVisibility(0);
        return;
      case 2131559078:
      }
      if (MyAlertDialog15.this.myListener != null)
        MyAlertDialog15.this.myListener.onClick(7);
      MyAlertDialog15.this.selelted8.setVisibility(0);
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;

  @Bind({2131559071})
  ImageTextBigButton scene1;

  @Bind({2131559072})
  ImageTextBigButton scene2;

  @Bind({2131559073})
  ImageTextBigButton scene3;

  @Bind({2131559074})
  ImageTextBigButton scene4;

  @Bind({2131559075})
  ImageTextBigButton scene5;

  @Bind({2131559076})
  ImageTextBigButton scene6;

  @Bind({2131559077})
  ImageTextBigButton scene7;

  @Bind({2131559078})
  ImageTextBigButton scene8;

  @Bind({2131559079})
  Button selelted1;

  @Bind({2131559080})
  Button selelted2;

  @Bind({2131559081})
  Button selelted3;

  @Bind({2131559082})
  Button selelted4;

  @Bind({2131559083})
  Button selelted5;

  @Bind({2131559084})
  Button selelted6;

  @Bind({2131559085})
  Button selelted7;

  @Bind({2131559086})
  Button selelted8;

  public MyAlertDialog15(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog15(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog15(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void init()
  {
    getWindow().setGravity(80);
    getWindow().setLayout(1080, -2);
    setContentView(2130968720);
    ButterKnife.bind(this);
    this.scene1.setOnClickListener(this.btn_listener);
    this.scene2.setOnClickListener(this.btn_listener);
    this.scene3.setOnClickListener(this.btn_listener);
    this.scene4.setOnClickListener(this.btn_listener);
    this.scene5.setOnClickListener(this.btn_listener);
    this.scene6.setOnClickListener(this.btn_listener);
    this.scene7.setOnClickListener(this.btn_listener);
    this.scene8.setOnClickListener(this.btn_listener);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setMyOnClickListener(MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog15
 * JD-Core Version:    0.6.0
 */