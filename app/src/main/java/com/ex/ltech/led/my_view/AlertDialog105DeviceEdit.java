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

public class AlertDialog105DeviceEdit extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559106:
        AlertDialog105DeviceEdit.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (AlertDialog105DeviceEdit.this.myListener != null)
            {
              AlertDialog105DeviceEdit.this.myListener.onClick(0);
              AlertDialog105DeviceEdit.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559108:
        AlertDialog105DeviceEdit.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (AlertDialog105DeviceEdit.this.myListener != null)
            {
              AlertDialog105DeviceEdit.this.myListener.onClick(1);
              AlertDialog105DeviceEdit.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559107:
        AlertDialog105DeviceEdit.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (AlertDialog105DeviceEdit.this.myListener != null)
            {
              AlertDialog105DeviceEdit.this.myListener.onClick(2);
              AlertDialog105DeviceEdit.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559028:
      }
      AlertDialog105DeviceEdit.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (AlertDialog105DeviceEdit.this.myListener != null)
          {
            AlertDialog105DeviceEdit.this.myListener.onClick(3);
            AlertDialog105DeviceEdit.this.dismiss();
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
  private TextView rv_my_alertdialog_0;
  private TextView rv_my_alertdialog_1;
  private TextView rv_my_alertdialog_2;
  private TextView rv_my_alertdialog_3;
  private LinearLayout viewBackGroud;

  public AlertDialog105DeviceEdit(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public AlertDialog105DeviceEdit(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public AlertDialog105DeviceEdit(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.rv_my_alertdialog_0 = ((TextView)findViewById(2131559106));
    this.rv_my_alertdialog_1 = ((TextView)findViewById(2131559108));
    this.rv_my_alertdialog_2 = ((TextView)findViewById(2131559107));
    this.rv_my_alertdialog_3 = ((TextView)findViewById(2131559028));
    this.rv_my_alertdialog_0.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_1.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_2.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_3.setOnClickListener(this.btn_listener);
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968731);
    findView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setButtonText(String paramString1, String paramString2, String paramString3)
  {
    this.rv_my_alertdialog_1.setText(paramString1);
    this.rv_my_alertdialog_2.setText(paramString2);
    this.rv_my_alertdialog_3.setText(paramString3);
  }

  public void setDelDeviceTextGone()
  {
    this.rv_my_alertdialog_1.setVisibility(8);
  }

  public void setDelDeviceTextVisiable()
  {
    this.rv_my_alertdialog_0.setVisibility(8);
    this.rv_my_alertdialog_1.setVisibility(0);
  }

  public void setMyOnClickListener(MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public void setRemoveOutGroupTextVisiable()
  {
    this.rv_my_alertdialog_0.setVisibility(0);
    this.rv_my_alertdialog_1.setVisibility(8);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onClick(int paramInt);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.AlertDialog105DeviceEdit
 * JD-Core Version:    0.6.0
 */