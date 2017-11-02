package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.indris.material.RippleView;

public class MyAlertDialog14 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559407:
        MyAlertDialog14.this.h.postDelayed(new Runnable(paramView)
        {
          public void run()
          {
            if (MyAlertDialog14.this.myListener != null)
            {
              MyAlertDialog14.this.myListener.onClick(this.val$v, true);
              MyAlertDialog14.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559406:
      }
      MyAlertDialog14.this.h.postDelayed(new Runnable(paramView)
      {
        public void run()
        {
          if (MyAlertDialog14.this.myListener != null)
          {
            MyAlertDialog14.this.myListener.onClick(this.val$v, false);
            MyAlertDialog14.this.dismiss();
          }
        }
      }
      , 500L);
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyAlertDialog.MyOnClickListener myListener;
  public RippleView rv_my_alertdialog_cancle;
  public RippleView rv_my_alertdialog_ok;
  private TextView tv_my_alertdialog_msg;
  private LinearLayout viewBackGroud;

  public MyAlertDialog14(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog14(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog14(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.tv_my_alertdialog_msg = ((TextView)findViewById(2131559405));
    this.rv_my_alertdialog_ok = ((RippleView)findViewById(2131559407));
    this.rv_my_alertdialog_cancle = ((RippleView)findViewById(2131559406));
    this.rv_my_alertdialog_ok.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_cancle.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_ok.setDuration(500);
    this.rv_my_alertdialog_cancle.setDuration(500);
    this.tv_my_alertdialog_msg.setMovementMethod(new ScrollingMovementMethod());
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968839);
    findView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setMsg(int paramInt)
  {
    this.tv_my_alertdialog_msg.setText(paramInt);
  }

  public void setMsg(String paramString)
  {
    this.tv_my_alertdialog_msg.setText(paramString);
  }

  public void setMyOnClickListener(MyAlertDialog.MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public void setTitle(int paramInt)
  {
    this.tv_my_alertdialog_msg.setText(paramInt);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onClick(View paramView, boolean paramBoolean);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog14
 * JD-Core Version:    0.6.0
 */