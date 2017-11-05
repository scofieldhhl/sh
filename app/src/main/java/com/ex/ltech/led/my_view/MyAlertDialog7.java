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

public class MyAlertDialog7 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559419:
        MyAlertDialog7.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog7.this.myListener != null)
            {
              MyAlertDialog7.this.myListener.onAddDevice();
              MyAlertDialog7.this.dismiss();
            }
          }
        }
        , 50L);
        return;
      case 2131559420:
      }
      MyAlertDialog7.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (MyAlertDialog7.this.myListener != null)
          {
            MyAlertDialog7.this.myListener.onAddLight();
            MyAlertDialog7.this.dismiss();
          }
        }
      }
      , 50L);
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  private LinearLayout viewBackGroud;

  public MyAlertDialog7(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog7(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog7(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
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
    setContentView(2130968854);
    findView();
    findViewById(2131558840).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyAlertDialog7.this.dismiss();
      }
    });
    findViewById(2131559419).setOnClickListener(this.btn_listener);
    findViewById(2131559420).setOnClickListener(this.btn_listener);
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

  public void setOnOffItemName()
  {
    ((TextView)findViewById(2131559419)).setText(R.string.on);
    ((TextView)findViewById(2131559420)).setText(2131100226);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onAddDevice();

    public abstract void onAddLight();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog7
 * JD-Core Version:    0.6.0
 */