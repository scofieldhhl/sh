package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyAlertDialog20 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559415:
        MyAlertDialog20.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog20.this.myListener != null)
            {
              MyAlertDialog20.this.myListener.onClick(0);
              MyAlertDialog20.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559416:
        MyAlertDialog20.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog20.this.myListener != null)
            {
              MyAlertDialog20.this.myListener.onClick(1);
              MyAlertDialog20.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559030:
      }
      MyAlertDialog20.this.dismiss();
    }
  };
  private TextView cancel;
  private Context context;
  private TextView del_device;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  private TextView refresh;
  private TextView rv_my_alertdialog_1;
  private TextView tv_my_alertdialog_msg;
  private LinearLayout viewBackGroud;

  public MyAlertDialog20(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog20(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog20(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.refresh = ((TextView)findViewById(2131559415));
    this.del_device = ((TextView)findViewById(2131559416));
    this.cancel = ((TextView)findViewById(2131559030));
    this.cancel.setOnClickListener(this.btn_listener);
    this.refresh.setOnClickListener(this.btn_listener);
    this.del_device.setOnClickListener(this.btn_listener);
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968848);
    findView();
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
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog20
 * JD-Core Version:    0.6.0
 */