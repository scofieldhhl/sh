package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.TextView;

public class DiscoverDeviceDialog extends AlertDialog
{
  private View.OnClickListener btn_listener = new View.OnClickListener()
  {
    public void onClick(View paramView)
    {
      switch (paramView.getId())
      {
      default:
        return;
      case 2131558968:
      }
      if (DiscoverDeviceDialog.this.myListener != null)
        DiscoverDeviceDialog.this.myListener.onClick(paramView, true);
      DiscoverDeviceDialog.this.dismiss();
    }
  };
  private Context context;
  Handler h = new Handler()
  {
  };
  private MyAlertDialog.MyOnClickListener myListener;
  private TextView tvCount;

  public DiscoverDeviceDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public DiscoverDeviceDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public DiscoverDeviceDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968730);
    findViewById(2131558968).setOnClickListener(this.btn_listener);
    this.tvCount = ((TextView)findViewById(2131559105));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setCount(String paramString)
  {
    this.tvCount.setText(paramString);
  }

  public void setMyOnClickListener(MyAlertDialog.MyOnClickListener paramMyOnClickListener)
  {
    this.myListener = paramMyOnClickListener;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.DiscoverDeviceDialog
 * JD-Core Version:    0.6.0
 */