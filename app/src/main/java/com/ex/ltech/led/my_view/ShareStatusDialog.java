package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;
import butterknife.Bind;

public class ShareStatusDialog extends AlertDialog
{
  private Context context;
  private String status;

  @Bind({2131559474})
  TextView tvShareStatus;

  public ShareStatusDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public ShareStatusDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public ShareStatusDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968886);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }

  public void setRecStatus()
  {
    this.tvShareStatus.setText(2131100313);
  }

  public void setShareStatus()
  {
    this.tvShareStatus.setText(2131100397);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ShareStatusDialog
 * JD-Core Version:    0.6.0
 */