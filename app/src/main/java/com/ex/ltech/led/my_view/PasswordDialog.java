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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.indris.material.RippleView;

public class PasswordDialog extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131559446:
        while (true)
        {
          return;
          try
          {
            if (!PasswordDialog.this.et_password_alertdialog_pwd1.getText().toString().equals(PasswordDialog.this.et_password_alertdialog_pwd2.getText().toString()))
              continue;
            PasswordDialog.this.h.postDelayed(new Runnable(paramView)
            {
              public void run()
              {
                if (PasswordDialog.this.myListener != null)
                {
                  PasswordDialog.this.myListener.onClick(this.val$v, PasswordDialog.this.et_password_alertdialog_pwd1.getText().toString());
                  PasswordDialog.this.dismiss();
                }
              }
            }
            , 500L);
            return;
          }
          catch (Exception localException)
          {
            Toast.makeText(PasswordDialog.this.context, 2131100100, 1).show();
            return;
          }
        }
      case 2131559445:
      }
      PasswordDialog.this.h.postDelayed(new Runnable(paramView)
      {
        public void run()
        {
          if (PasswordDialog.this.myListener != null)
          {
            PasswordDialog.this.myListener.onClick(this.val$v, "");
            PasswordDialog.this.dismiss();
          }
        }
      }
      , 500L);
    }
  };
  private Context context;
  private EditText et_password_alertdialog_pwd1;
  private EditText et_password_alertdialog_pwd2;
  Handler h = new Handler()
  {
  };
  private PasswordDialogOnClickListener myListener;
  private RippleView rv_password_alertdialog_cancle;
  private RippleView rv_password_alertdialog_ok;
  private TextView tv_password_alertdialog_msg;
  private LinearLayout viewBackGroud;

  public PasswordDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public PasswordDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public PasswordDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.tv_password_alertdialog_msg = ((TextView)findViewById(2131559442));
    this.et_password_alertdialog_pwd1 = ((EditText)findViewById(2131559443));
    this.et_password_alertdialog_pwd2 = ((EditText)findViewById(2131559444));
    this.rv_password_alertdialog_ok = ((RippleView)findViewById(2131559446));
    this.rv_password_alertdialog_cancle = ((RippleView)findViewById(2131559445));
    this.rv_password_alertdialog_ok.setOnClickListener(this.btn_listener);
    this.rv_password_alertdialog_cancle.setOnClickListener(this.btn_listener);
    this.rv_password_alertdialog_ok.setDuration(500);
    this.rv_password_alertdialog_cancle.setDuration(500);
    this.tv_password_alertdialog_msg.setMovementMethod(new ScrollingMovementMethod());
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968873);
    findView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setPasswordDialogOnClickListener(PasswordDialogOnClickListener paramPasswordDialogOnClickListener)
  {
    this.myListener = paramPasswordDialogOnClickListener;
  }

  public static abstract interface PasswordDialogOnClickListener
  {
    public abstract void onClick(View paramView, String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.PasswordDialog
 * JD-Core Version:    0.6.0
 */