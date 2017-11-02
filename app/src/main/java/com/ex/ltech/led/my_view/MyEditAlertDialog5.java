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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.io.File;

public class MyEditAlertDialog5 extends AlertDialog
{
  public ImageView btn_album;
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      case 2131559424:
      case 2131559426:
      default:
      case 2131559407:
        do
          return;
        while (MyEditAlertDialog5.this.myListener == null);
        MyEditAlertDialog5.this.dismiss();
        return;
      case 2131559406:
        MyEditAlertDialog5.this.dismiss();
        return;
      case 2131559429:
      }
      MyEditAlertDialog5.this.dismiss();
    }
  };
  public ImageView btn_takephoto;
  private Context context;
  private File currentFile;
  public TextView et_my_edit_alertdialog;
  Handler h = new Handler()
  {
  };
  public ImageView ib_cancel;
  public LinearLayout ll_album;
  public LinearLayout ll_takephoto;
  private MyOnClickListener myListener;
  public Button rv_my_edit_alertdialog_cancle;
  public Button rv_my_edit_alertdialog_ok;
  private TextView tv_my_edit_alertdialog_msg;
  private LinearLayout viewBackGroud;

  public MyEditAlertDialog5(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public MyEditAlertDialog5(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public MyEditAlertDialog5(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void findView()
  {
    this.tv_my_edit_alertdialog_msg = ((TextView)findViewById(2131559067));
    this.ll_takephoto = ((LinearLayout)findViewById(2131559424));
    this.ll_album = ((LinearLayout)findViewById(2131559426));
    this.et_my_edit_alertdialog = ((TextView)findViewById(2131559068));
    this.btn_takephoto = ((ImageView)findViewById(2131559425));
    this.btn_album = ((ImageView)findViewById(2131559427));
    this.rv_my_edit_alertdialog_ok = ((Button)findViewById(2131559070));
    this.rv_my_edit_alertdialog_cancle = ((Button)findViewById(2131559069));
    this.ib_cancel = ((ImageView)findViewById(2131559429));
    this.ib_cancel.setOnClickListener(this.btn_listener);
    this.ll_takephoto.setOnClickListener(this.btn_listener);
    this.ll_album.setOnClickListener(this.btn_listener);
    this.rv_my_edit_alertdialog_ok.setOnClickListener(this.btn_listener);
    this.rv_my_edit_alertdialog_cancle.setOnClickListener(this.btn_listener);
    this.tv_my_edit_alertdialog_msg.setMovementMethod(new ScrollingMovementMethod());
  }

  private void init()
  {
    getWindow().setBackgroundDrawableResource(2130837649);
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968860);
    findView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setMsg(int paramInt)
  {
    this.tv_my_edit_alertdialog_msg.setText(paramInt);
  }

  public void setMyOnClickListener(MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }

  public static abstract interface MyOnClickListener
  {
    public abstract void onMyEditAlertDialogBtnClick(View paramView, String paramString);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.MyEditAlertDialog5
 * JD-Core Version:    0.6.0
 */