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

public class MyAlertDialog5 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559288:
        MyAlertDialog5.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog5.this.myListener != null)
            {
              MyAlertDialog5.this.myListener.onDel();
              MyAlertDialog5.this.dismiss();
            }
          }
        }
        , 50L);
        return;
      case 2131559418:
        MyAlertDialog5.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog5.this.myListener != null)
            {
              MyAlertDialog5.this.myListener.onRename();
              MyAlertDialog5.this.dismiss();
            }
          }
        }
        , 50L);
        return;
      case 2131558953:
        MyAlertDialog5.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (MyAlertDialog5.this.myListener != null)
            {
              MyAlertDialog5.this.myListener.onEdit();
              MyAlertDialog5.this.dismiss();
            }
          }
        }
        , 50L);
        return;
      case 2131559030:
      }
      MyAlertDialog5.this.dismiss();
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  public TextView tvEdit;
  public TextView tvRename;
  private LinearLayout viewBackGroud;

  public MyAlertDialog5(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog5(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog5(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
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
    setContentView(2130968852);
    findView();
    findViewById(2131558840).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        MyAlertDialog5.this.dismiss();
      }
    });
    TextView localTextView1 = (TextView)findViewById(2131559418);
    this.tvRename = localTextView1;
    localTextView1.setOnClickListener(this.btn_listener);
    findViewById(2131559288).setOnClickListener(this.btn_listener);
    TextView localTextView2 = (TextView)findViewById(2131558953);
    this.tvEdit = localTextView2;
    localTextView2.setOnClickListener(this.btn_listener);
    findViewById(2131559030).setOnClickListener(this.btn_listener);
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
    public abstract void onDel();

    public abstract void onEdit();

    public abstract void onRename();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog5
 * JD-Core Version:    0.6.0
 */