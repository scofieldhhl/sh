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

public class MyAlertDialog16 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      MyAlertDialog16.this.selelted1.setVisibility(8);
      MyAlertDialog16.this.selelted2.setVisibility(8);
      MyAlertDialog16.this.selelted3.setVisibility(8);
      MyAlertDialog16.this.selelted4.setVisibility(8);
      MyAlertDialog16.this.selelted5.setVisibility(8);
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559071:
        if (MyAlertDialog16.this.myListener != null)
          MyAlertDialog16.this.myListener.onClick(0);
        MyAlertDialog16.this.selelted1.setVisibility(0);
        return;
      case 2131559072:
        if (MyAlertDialog16.this.myListener != null)
          MyAlertDialog16.this.myListener.onClick(1);
        MyAlertDialog16.this.selelted2.setVisibility(0);
        return;
      case 2131559073:
        if (MyAlertDialog16.this.myListener != null)
          MyAlertDialog16.this.myListener.onClick(2);
        MyAlertDialog16.this.selelted3.setVisibility(0);
        return;
      case 2131559074:
        if (MyAlertDialog16.this.myListener != null)
          MyAlertDialog16.this.myListener.onClick(3);
        MyAlertDialog16.this.selelted4.setVisibility(0);
        return;
      case 2131559075:
      }
      if (MyAlertDialog16.this.myListener != null)
        MyAlertDialog16.this.myListener.onClick(4);
      MyAlertDialog16.this.selelted5.setVisibility(0);
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

  public MyAlertDialog16(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog16(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog16(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968721);
    ButterKnife.bind(this);
    this.scene1.setOnClickListener(this.btn_listener);
    this.scene2.setOnClickListener(this.btn_listener);
    this.scene3.setOnClickListener(this.btn_listener);
    this.scene4.setOnClickListener(this.btn_listener);
    this.scene5.setOnClickListener(this.btn_listener);
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
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog16
 * JD-Core Version:    0.6.0
 */