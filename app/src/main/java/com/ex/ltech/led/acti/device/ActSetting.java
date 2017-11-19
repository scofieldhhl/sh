package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MLImageView;

public class ActSetting extends MyBaseActivity
{
  MLImageView iv_setting_acti_1;
  MLImageView iv_setting_acti_2;

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.setting);
    setBgAlpha();
  }

  public void goAbout(View paramView)
  {
    Intent localIntent = new Intent(this, ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 8);
    startActivity(localIntent);
  }

  public void goDelete(View paramView)
  {
//    startActivity(new Intent(this, AtFragmentMaster.class).putExtra("AtTypeKey", "DeleteDevice"));
  }

  public void goDeviceManager(View paramView)
  {
    Intent localIntent = new Intent(this, ActDeviceManager.class);
    localIntent.putExtra("isShare", false);
    startActivity(localIntent);
  }

  public void goDeviceShare(View paramView)
  {
    Intent localIntent = new Intent(this, ActDeviceManager.class);
    localIntent.putExtra("isShare", true);
    startActivity(localIntent);
  }

  public void goGuide(View paramView)
  {
    goAct(ReadmeActivity.class);
  }

  public void goUserSetting(View paramView)
  {
    goAct4result(ActiUserActivity.class, 0);
  }

  public void goVersion(View paramView)
  {
    toast(R.string.now_new);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 400)
    {
      setResult(400);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.acti_setting);
    setMyTitle();
    this.iv_setting_acti_1 = ((MLImageView)findViewById(R.id.iv_setting_acti_1));
    this.iv_setting_acti_2 = ((MLImageView)findViewById(R.id.iv_setting_acti_2));
    this.iv_setting_acti_1.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.white_piont));
    this.iv_setting_acti_2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.log_head_6));
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onResume()
  {
    super.onResume();
    /*String str1 = UserFerences.getUserFerences(this).spFerences.getString("uHeadPath", "");
    String str2 = UserFerences.getUserFerences(this).spFerences.getString("user", "");
    ((TextView)findViewById(R.id.tv_act_setting_u_name)).setText(str2);
    if (str1.indexOf("http") != -1)
    {
      new NormalLoadPictrue().getPicture(str1, this.iv_setting_acti_2);
      return;
    }
    try
    {
      Bitmap localBitmap = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(str1), 300.0D, 300.0D);
      int i = BitmapUtils.getExifOrientation(str1);
      if ((i == 90) || (i == 180) || (i == 270))
      {
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(i);
        localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, 300, 300, localMatrix, true);
      }
      this.iv_setting_acti_2.setImageBitmap(localBitmap);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      this.iv_setting_acti_2.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.log_head_6));
    }*/
  }
}