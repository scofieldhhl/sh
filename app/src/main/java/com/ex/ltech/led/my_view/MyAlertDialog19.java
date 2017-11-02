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

public class MyAlertDialog19 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131558621:
        MyAlertDialog19.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog19.this.myListener != null)
            {
              MyAlertDialog19.this.myListener.onClick(0);
              MyAlertDialog19.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559411:
      }
      MyAlertDialog19.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (MyAlertDialog19.this.myListener != null)
            MyAlertDialog19.this.dismiss();
        }
      }
      , 500L);
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  private TextView no;
  private TextView rv_my_alertdialog_1;
  private TextView tv_my_alertdialog_msg;
  private LinearLayout viewBackGroud;
  private TextView yes;

  public MyAlertDialog19(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog19(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog19(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.yes = ((TextView)findViewById(2131558621));
    this.no = ((TextView)findViewById(2131559411));
    this.yes.setOnClickListener(this.btn_listener);
    this.no.setOnClickListener(this.btn_listener);
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968846);
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

  public void showDiyStyle()
  {
    show();
    ((TextView)findViewById(2131558469)).setText(2131100469);
    this.yes.setText(2131100456);
    this.no.setText(2131099891);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog19
 * JD-Core Version:    0.6.0
 */