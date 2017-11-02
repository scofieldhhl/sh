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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyEditAlertDialog4 extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
      case 2131559407:
        do
          return;
        while (MyEditAlertDialog4.this.myListener == null);
        MyEditAlertDialog4.this.dismiss();
        return;
      case 2131559406:
      }
      MyEditAlertDialog4.this.dismiss();
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyOnClickListener myListener;
  public Button rv_my_edit_alertdialog_cancle;
  public Button rv_my_edit_alertdialog_ok;
  TextView tv_my_edit_alertdialog_content;
  private TextView tv_my_edit_alertdialog_msg;
  TextView tv_my_edit_alertdialog_tips;
  private LinearLayout viewBackGroud;

  public MyEditAlertDialog4(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyEditAlertDialog4(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyEditAlertDialog4(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.tv_my_edit_alertdialog_msg = ((TextView)findViewById(2131559067));
    this.tv_my_edit_alertdialog_tips = ((TextView)findViewById(2131559421));
    this.tv_my_edit_alertdialog_content = ((TextView)findViewById(2131559422));
    this.rv_my_edit_alertdialog_ok = ((Button)findViewById(2131559070));
    this.rv_my_edit_alertdialog_cancle = ((Button)findViewById(2131559069));
    this.rv_my_edit_alertdialog_ok.setOnClickListener(this.btn_listener);
    this.rv_my_edit_alertdialog_cancle.setOnClickListener(this.btn_listener);
    this.tv_my_edit_alertdialog_msg.setMovementMethod(new ScrollingMovementMethod());
  }

  private void init()
  {
    getWindow().setBackgroundDrawableResource(2130837649);
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968859);
    findView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setContent(int paramInt)
  {
    this.tv_my_edit_alertdialog_content.setText(paramInt);
  }

  public void setMsg(int paramInt)
  {
    this.tv_my_edit_alertdialog_msg.setText(paramInt);
  }

  public void setMyOnClickListener(MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public void setTips(int paramInt)
  {
    this.tv_my_edit_alertdialog_tips.setText(paramInt);
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onMyEditAlertDialogBtnClick(View paramView, String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyEditAlertDialog4
 * JD-Core Version:    0.6.0
 */