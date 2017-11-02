package com.ex.ltech.hongwai.atRcs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MyAlertDialog;
import com.ex.ltech.led.my_view.MyAlertDialog.MyOnClickListener;
import com.ex.ltech.led.my_view.MyEditAlertDialog;
import com.ex.ltech.led.my_view.MyEditAlertDialog.MyOnClickListener;
import com.indris.material.RippleView;

public class AtEditDiyBtn extends MyBaseActivity
{
  String kName;
  private TextView tvRcName;

  public void changeName(View paramView)
  {
    MyEditAlertDialog localMyEditAlertDialog = new MyEditAlertDialog(this);
    localMyEditAlertDialog.show();
    localMyEditAlertDialog.setMsg(2131099890);
    localMyEditAlertDialog.rv_my_edit_alertdialog_cancle.setTextColor(Color.parseColor("#000000"));
    localMyEditAlertDialog.rv_my_edit_alertdialog_ok.setTextColor(Color.parseColor("#000000"));
    localMyEditAlertDialog.getWindow().clearFlags(131080);
    localMyEditAlertDialog.getWindow().setSoftInputMode(4);
    localMyEditAlertDialog.et_my_edit_alertdialog.setText(this.kName);
    localMyEditAlertDialog.setMyOnClickListener(new MyEditAlertDialog.MyOnClickListener()
    {
      public void onMyEditAlertDialogBtnClick(View paramView, String paramString)
      {
        AtEditDiyBtn.this.tvRcName.setText(paramString);
        AtEditDiyBtn.this.kName = paramString;
      }
    });
  }

  public void changeRc(View paramView)
  {
    setResult(80000);
    finish();
  }

  public void delRc(View paramView)
  {
    setResult(150000);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968675);
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setEditTextRes(2131100358, Color.parseColor("#f82525"));
    setBgWhite();
    setTiTleTextRes(2131100048);
    this.tvRcName = ((TextView)findViewById(2131558907));
    this.kName = getIntent().getStringExtra("BT_NAME_KEY");
  }

  protected void onEdit()
  {
    super.onEdit();
    setResult(140000, new Intent().putExtra("BT_NAME_KEY", this.kName));
    finish();
  }

  protected void onMenu()
  {
    super.onMenu();
    if (this.kName.equals(getIntent().getStringExtra("BT_NAME_KEY")))
    {
      MyAlertDialog localMyAlertDialog = new MyAlertDialog(this);
      localMyAlertDialog.setMyOnClickListener(new MyAlertDialog.MyOnClickListener(localMyAlertDialog)
      {
        public void onClick(View paramView, boolean paramBoolean)
        {
          if (paramBoolean)
          {
            AtEditDiyBtn.this.finish();
            return;
          }
          this.val$myAlertDialog12.dismiss();
        }
      });
      localMyAlertDialog.show();
      localMyAlertDialog.setMsg(2131100212);
      localMyAlertDialog.rv_my_alertdialog_cancle.setText(2131100070);
      localMyAlertDialog.rv_my_alertdialog_ok.setText(2131100530);
      localMyAlertDialog.rv_my_alertdialog_cancle.setTextColor(Color.parseColor("#000000"));
      localMyAlertDialog.rv_my_alertdialog_ok.setTextColor(Color.parseColor("#000000"));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtEditDiyBtn
 * JD-Core Version:    0.6.0
 */