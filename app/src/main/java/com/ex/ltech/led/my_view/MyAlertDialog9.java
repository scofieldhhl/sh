package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyAlertDialog9 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131559070:
        do
          return;
        while (MyAlertDialog9.this.myListener == null);
        MyAlertDialog9.this.myListener.onMyEditAlertDialogBtnClick(paramView, MyAlertDialog9.this.et_my_edit_alertdialog.getText().toString());
        MyAlertDialog9.this.dismiss();
        return;
      case 2131559069:
        MyAlertDialog9.this.dismiss();
        return;
      case 2131559430:
      }
      MyAlertDialog9.this.et_my_edit_alertdialog.setText("");
    }
  };
  private Context context;
  public Button del_text;
  EditText et_my_edit_alertdialog;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  public Button rv_my_edit_alertdialog_cancle;
  public Button rv_my_edit_alertdialog_ok;
  private LinearLayout viewBackGroud;

  public MyAlertDialog9(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyAlertDialog9(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyAlertDialog9(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.et_my_edit_alertdialog = ((EditText)findViewById(2131559068));
    this.rv_my_edit_alertdialog_ok = ((Button)findViewById(2131559070));
    this.rv_my_edit_alertdialog_cancle = ((Button)findViewById(2131559069));
    this.del_text = ((Button)findViewById(2131559430));
    this.rv_my_edit_alertdialog_ok.setOnClickListener(this.btn_listener);
    this.rv_my_edit_alertdialog_cancle.setOnClickListener(this.btn_listener);
    this.del_text.setOnClickListener(this.btn_listener);
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968861);
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

  public void showSoftKeyBorad()
  {
    ((InputMethodManager)this.context.getSystemService("input_method"));
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onMyEditAlertDialogBtnClick(View paramView, String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyAlertDialog9
 * JD-Core Version:    0.6.0
 */