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
import android.widget.Toast;

public class MyAlertDialog11 extends AlertDialog
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
        MyAlertDialog11.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog11.this.myListener != null)
            {
              MyAlertDialog11.this.myListener.onClick(1);
              MyAlertDialog11.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559413:
        MyAlertDialog11.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog11.this.myListener != null)
            {
              MyAlertDialog11.this.myListener.onClick(2);
              MyAlertDialog11.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559414:
      }
      MyAlertDialog11.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (MyAlertDialog11.this.myListener != null)
          {
            MyAlertDialog11.this.myListener.onClick(3);
            MyAlertDialog11.this.dismiss();
          }
        }
      }
      , 500L);
    }
  };
  TextView cancel;
  private Context context;
  Handler h = new Handler()
  {
  };
  TextView learning;
  private MyOnClickListener myListener;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (MyAlertDialog11.this.timerTime > 0)
      {
        MyAlertDialog11.this.timeHandler.postDelayed(MyAlertDialog11.this.runnable, 1000L);
        MyAlertDialog11.this.second.setText(MyAlertDialog11.this.context.getString(2131100302) + MyAlertDialog11.this.timerTime + MyAlertDialog11.this.context.getString(R.string.sec) + ")");
        MyAlertDialog11 localMyAlertDialog11 = MyAlertDialog11.this;
        localMyAlertDialog11.timerTime = (-1 + localMyAlertDialog11.timerTime);
        return;
      }
      Toast.makeText(MyAlertDialog11.this.context, 2131100215, 0).show();
      MyAlertDialog11.this.timerTime = 20;
      MyAlertDialog11.this.dismiss();
    }
  };
  TextView second;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime;
  private LinearLayout viewBackGroud;

  public MyAlertDialog11(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog11(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog11(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
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
    setContentView(2130968841);
    findView();
    this.learning = ((TextView)findViewById(2131559408));
    this.second = ((TextView)findViewById(2131558875));
    this.cancel = ((TextView)findViewById(2131559030));
    findViewById(2131558840).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyAlertDialog11.this.dismiss();
      }
    });
    this.cancel.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyAlertDialog11.this.dismiss();
      }
    });
  }

  public void dismiss()
  {
    super.dismiss();
    removeDelayRunnable();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void removeDelayRunnable()
  {
    this.timeHandler.removeCallbacks(this.runnable);
  }

  public void setMyOnClickListener(MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public void test(String paramString)
  {
    this.learning.setText(paramString);
  }

  public void timer()
  {
    this.timerTime = 20;
    removeDelayRunnable();
    this.timeHandler.post(this.runnable);
    this.second.setOnClickListener(null);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog11
 * JD-Core Version:    0.6.0
 */