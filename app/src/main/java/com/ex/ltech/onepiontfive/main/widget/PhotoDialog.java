package com.ex.ltech.onepiontfive.main.widget;

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
import com.indris.material.RippleView;

public class PhotoDialog extends AlertDialog
{
  public static final int GRALLRY = 2;
  public static final int PHOTO = 1;
  public static final int RESET = 3;
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131559412:
        PhotoDialog.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (PhotoDialog.this.myListener != null)
            {
              PhotoDialog.this.myListener.onClick(1);
              PhotoDialog.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559413:
        PhotoDialog.this.h.postDelayed(new Runnable()
        {
          public void run()
          {
            if (PhotoDialog.this.myListener != null)
            {
              PhotoDialog.this.myListener.onClick(2);
              PhotoDialog.this.dismiss();
            }
          }
        }
        , 500L);
        return;
      case 2131559414:
      }
      PhotoDialog.this.h.postDelayed(new Runnable()
      {
        public void run()
        {
          if (PhotoDialog.this.myListener != null)
          {
            PhotoDialog.this.myListener.onClick(3);
            PhotoDialog.this.dismiss();
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
  private RippleView rv_my_alertdialog_1;
  private RippleView rv_my_alertdialog_2;
  private RippleView rv_my_alertdialog_3;
  private TextView tv_my_alertdialog_msg;
  private LinearLayout viewBackGroud;

  public PhotoDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public PhotoDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public PhotoDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.tv_my_alertdialog_msg = ((TextView)findViewById(2131559405));
    this.rv_my_alertdialog_1 = ((RippleView)findViewById(2131559412));
    this.rv_my_alertdialog_2 = ((RippleView)findViewById(2131559413));
    this.rv_my_alertdialog_3 = ((RippleView)findViewById(2131559414));
    this.rv_my_alertdialog_1.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_2.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_3.setOnClickListener(this.btn_listener);
    this.rv_my_alertdialog_1.setDuration(500);
    this.rv_my_alertdialog_2.setDuration(500);
    this.rv_my_alertdialog_3.setDuration(500);
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968874);
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
 * Qualified Name:     com.ex.ltech.onepiontfive.main.widget.PhotoDialog
 * JD-Core Version:    0.6.0
 */