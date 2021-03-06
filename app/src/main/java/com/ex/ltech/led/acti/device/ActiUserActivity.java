package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;

import com.ex.ltech.LogRegForget.ActLoginActivity;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MLImageView;
import com.ex.ltech.led.my_view.MyAlertDialog;
import com.ex.ltech.led.my_view.MyAlertDialog2;
import com.ex.ltech.led.my_view.MyEditAlertDialog;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.NormalLoadPictrue;
import com.soundcloud.android.crop.Crop;

import java.io.File;

import io.xlink.wifi.js.util.SharedPreferencesUtil;

public class ActiUserActivity extends MyBaseActivity
{
  int SHOT_REQ_CODE = 1;
  File currentFile;
  String headPtah;
  MLImageView iv_acti_user_head;
  String nName;
  TextView tv_acti_user_name;

  private void beginCrop(Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
  }

  private void handleCrop(int paramInt, Intent paramIntent)
  {
    /*String str;
    if (paramInt == -1)
    {
      Bitmap localBitmap1 = BitmapUtils.autoZoomInBM(BitmapUtils.getBitmapFromUri(this, Crop.getOutput(paramIntent)), 300.0D, 300.0D);
      UriUtil.getRealFilePath(this, paramIntent.getData());
      str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
      FileUtil.saveMyBitmap(str, localBitmap1, "/ltech/led/image");
      UserFerences.getUserFerences(this).putValue("uHeadPath", str);
    }
    try
    {
      Bitmap localBitmap2 = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(str), 300.0D, 300.0D);
      int i = BitmapUtils.getExifOrientation(str);
      if ((i == 90) || (i == 180) || (i == 270))
      {
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(i);
        localBitmap2 = Bitmap.createBitmap(localBitmap2, 0, 0, 300, 300, localMatrix, true);
      }
      this.iv_acti_user_head.setImageBitmap(localBitmap2);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }*/
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.setting);
    setBgAlpha();
  }

  public void changeHead(View paramView)
  {
    final MyAlertDialog2 localMyAlertDialog2 = new MyAlertDialog2(this);
    localMyAlertDialog2.show();
    localMyAlertDialog2.setMyOnClickListener(new MyAlertDialog2.MyOnClickListener()
    {
      public void onClick(int paramInt)
      {
        if (paramInt == 1)
          ActiUserActivity.this.goCarmare();
        if (paramInt == 2)
          Crop.pickImage(ActiUserActivity.this);
        if (paramInt == 3)
        {
          UserFerences.getUserFerences(ActiUserActivity.this).putValue("uHeadPath", "");
          ActiUserActivity.this.iv_acti_user_head.setImageResource(R.mipmap.log_head_6);
          localMyAlertDialog2.dismiss();
        }
      }
    });
  }

  public void changeName(View paramView)
  {
    final MyEditAlertDialog localMyEditAlertDialog = new MyEditAlertDialog(this);
    localMyEditAlertDialog.show();
    localMyEditAlertDialog.setMsg(2131100107);
    localMyEditAlertDialog.getWindow().clearFlags(131080);
    localMyEditAlertDialog.getWindow().setSoftInputMode(4);
    localMyEditAlertDialog.rv_my_edit_alertdialog_ok.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        String str = localMyEditAlertDialog.et_my_edit_alertdialog.getText().toString();
        UserFerences.getUserFerences(ActiUserActivity.this.getApplicationContext()).putValue("user", str);
        ActiUserActivity.this.tv_acti_user_name.setText(str);
        localMyEditAlertDialog.dismiss();
      }
    });
    localMyEditAlertDialog.rv_my_edit_alertdialog_cancle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        localMyEditAlertDialog.dismiss();
      }
    });
  }

  public void changePsd(View paramView)
  {
    goAct(ActResetPsd.class);
  }

  public void goCarmare()
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(this.currentFile));
    startActivityForResult(localIntent, this.SHOT_REQ_CODE);
  }

  public void logOut(View paramView)
  {
    final MyAlertDialog localMyAlertDialog = new MyAlertDialog(this);
    localMyAlertDialog.show();
    localMyAlertDialog.setTitle(R.string.tips);
    localMyAlertDialog.setMsg(R.string.msg_log_out);
    localMyAlertDialog.getWindow().clearFlags(131080);
    localMyAlertDialog.getWindow().setSoftInputMode(4);
    localMyAlertDialog.rv_my_alertdialog_cancle.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        localMyAlertDialog.dismiss();
      }
    });
    localMyAlertDialog.rv_my_alertdialog_ok.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        SharedPreferencesUtil.keepShared("appId", 0);
        SharedPreferencesUtil.keepShared("authKey", "");
        SharedPreferencesUtil.keepShared("accessToken", "");
        UserFerences.getUserFerences(ActiUserActivity.this).putValue("isLog", Boolean.valueOf(false));
        ActiUserActivity.this.goAct(ActLoginActivity.class);
        ActiUserActivity.this.setResult(400);
        ActiUserActivity.this.finish();
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    /*String str;
    if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
    {
      str = this.currentFile.getPath();
      UserFerences.getUserFerences(this).putValue("uHeadPath", str);
    }
    do
    {
      try
      {
        Bitmap localBitmap = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(str), 300.0D, 300.0D);
        int i = BitmapUtils.getExifOrientation(str);
        if ((i == 90) || (i == 180) || (i == 270))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(i);
          localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, 300, 300, localMatrix, true);
        }
        this.iv_acti_user_head.setImageBitmap(localBitmap);
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      if ((paramInt1 != 9162) || (paramInt2 != -1))
        continue;
      beginCrop(paramIntent.getData());
      return;
    }
    while (paramInt1 != 6709);
    handleCrop(paramInt2, paramIntent);*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.acti_user);
    this.iv_acti_user_head = ((MLImageView)findViewById(R.id.iv_acti_user_head));
    this.tv_acti_user_name = ((TextView)findViewById(R.id.tv_acti_user_name));
    setMyTitle();
    this.headPtah = UserFerences.getUserFerences(this).spFerences.getString("uHeadPath", "");
    this.nName = UserFerences.getUserFerences(this).spFerences.getString("user", "");
    this.tv_acti_user_name.setText(this.nName);
    if (this.headPtah.indexOf("http") != -1)
    {
      new NormalLoadPictrue().getPicture(this.headPtah, this.iv_acti_user_head);
      return;
    }
    try
    {
      Bitmap localBitmap = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(this.headPtah), 300.0D, 300.0D);
      int i = BitmapUtils.getExifOrientation(this.headPtah);
      if ((i == 90) || (i == 180) || (i == 270))
      {
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(i);
        localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, 300, 300, localMatrix, true);
      }
      this.iv_acti_user_head.setImageBitmap(localBitmap);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.iv_acti_user_head.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.log_head_6));
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ActiUserActivity
 * JD-Core Version:    0.6.0
 */