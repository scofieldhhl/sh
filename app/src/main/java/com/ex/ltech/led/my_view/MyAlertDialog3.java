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

public class MyAlertDialog3 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559412:
        MyAlertDialog3.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog3.this.myListener != null)
            {
              MyAlertDialog3.this.myListener.onClick(1);
              MyAlertDialog3.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559413:
        MyAlertDialog3.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog3.this.myListener != null)
            {
              MyAlertDialog3.this.myListener.onClick(2);
              MyAlertDialog3.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559414:
      }
      MyAlertDialog3.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (MyAlertDialog3.this.myListener != null)
          {
            MyAlertDialog3.this.myListener.onClick(3);
            MyAlertDialog3.this.dismiss();
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
  private MyOnClickListener myListener;
  private LinearLayout viewBackGroud;

  public MyAlertDialog3(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog3(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog3(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968850);
    findView();
    findViewById(2131558840).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyAlertDialog3.this.dismiss();
      }
    });
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
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog3
 * JD-Core Version:    0.6.0
 */