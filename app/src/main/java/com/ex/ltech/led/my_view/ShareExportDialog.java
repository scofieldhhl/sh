package com.ex.ltech.led.my_view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.view.Window;

public class ShareExportDialog extends AlertDialog
{
  private Context context;

  public ShareExportDialog(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
  }

  public ShareExportDialog(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    this.context = paramContext;
  }

  public ShareExportDialog(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    this.context = paramContext;
  }

  private void init()
  {
    getWindow().setGravity(1);
    getWindow().setLayout(-2, -2);
    setContentView(2130968885);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    init();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.my_view.ShareExportDialog
 * JD-Core Version:    0.6.0
 */