package com.ex.ltech.remote.control.scene;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.vo.YkSceneVo;
import com.ex.ltech.remote.control.yaokong.AtSaveYongkongList;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.util.List;

public class AtYkNewSceneActivity extends YkAt
{
  private int SHOT_REQ_CODE = 1;
  private ItAtYkNSAdapter adt;
  private NewSceneBusiness business;
  private File currentFile;
  private ImageView ivAtYkNewSceneBg;
  private ImageView ivAtYkNewSceneBg2;
  private ListView lvAtYkNS;
  String picPath = "";
  private RelativeLayout rlMain;
  private RelativeLayout rlPhoto;
  private RelativeLayout rlSecond;
  private int sceneVoPosi;
  private int screenWidth;
  Bitmap tempBm = null;
  private MyTimePickerView tpSecond;
  private EditText tvYkNSName;

  private void beginCrop(Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
  }

  private void handleCrop(int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
      new Thread(new Runnable(paramIntent)
      {
        public void run()
        {
          AtYkNewSceneActivity.this.tempBm = BitmapUtils.squareCropRectangle(BitmapUtils.getBitmapFromUri(AtYkNewSceneActivity.this.getApplicationContext(), Crop.getOutput(this.val$result)), AtYkNewSceneActivity.this.business.getGvItWidth(), AtYkNewSceneActivity.this.business.getGvItHeight());
          String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
          FileUtil.saveMyBitmap(str, AtYkNewSceneActivity.this.tempBm, "/ltech/led/image");
          AtYkNewSceneActivity.this.picPath = str;
          AtYkNewSceneActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              AtYkNewSceneActivity.this.ivAtYkNewSceneBg2.setImageBitmap(AtYkNewSceneActivity.this.tempBm);
            }
          });
        }
      }).start();
  }

  public void cancelPic(View paramView)
  {
    this.rlPhoto.setVisibility(8);
  }

  public void okSecondOk(View paramView)
  {
    this.rlSecond.setVisibility(8);
    this.business.okSecondOk(Integer.parseInt(this.tpSecond.getValue()), this.sceneVoPosi);
    this.adt = new ItAtYkNSAdapter(this, this.business.getYkVos(this.sceneVoPosi));
    this.lvAtYkNS.setAdapter(this.adt);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
    {
      this.picPath = this.currentFile.getPath();
      new Thread()
      {
        public void run()
        {
          super.run();
          AtYkNewSceneActivity.this.tempBm = BitmapUtils.squareCropRectangle(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(AtYkNewSceneActivity.this.currentFile.getPath())), AtYkNewSceneActivity.this.business.getGvItWidth(), AtYkNewSceneActivity.this.business.getGvItHeight());
          AtYkNewSceneActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              AtYkNewSceneActivity.this.ivAtYkNewSceneBg2.setImageBitmap(AtYkNewSceneActivity.this.tempBm);
            }
          });
          AtYkNewSceneActivity.this.picPath = (Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg");
          FileUtil.saveMyBitmap(AtYkNewSceneActivity.this.picPath, AtYkNewSceneActivity.this.tempBm, "/ltech/led/image");
        }
      }
      .start();
    }
    while (true)
    {
      if (paramInt2 == 200)
      {
        this.adt = new ItAtYkNSAdapter(this, this.business.getYkVos(this.sceneVoPosi));
        this.lvAtYkNS.setAdapter(this.adt);
      }
      if (paramInt2 == this.saveYkOk)
        finish();
      return;
      if ((paramInt1 == 9162) && (paramInt2 == -1))
      {
        beginCrop(paramIntent.getData());
        continue;
      }
      if (paramInt1 != 6709)
        continue;
      handleCrop(paramInt2, paramIntent);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968713);
    this.business = new NewSceneBusiness(this);
    this.ivAtYkNewSceneBg = ((ImageView)findViewById(2131559052));
    this.ivAtYkNewSceneBg2 = ((ImageView)findViewById(2131559053));
    this.tvYkNSName = ((EditText)findViewById(2131559055));
    this.lvAtYkNS = ((ListView)findViewById(2131559056));
    this.rlSecond = ((RelativeLayout)findViewById(2131559057));
    this.tpSecond = ((MyTimePickerView)findViewById(2131559058));
    this.rlPhoto = ((RelativeLayout)findViewById(2131559059));
    this.rlMain = ((RelativeLayout)findViewById(2131558902));
    this.sceneVoPosi = getIntent().getIntExtra("scenePosi", -1);
    this.adt = new ItAtYkNSAdapter(this, this.business.getYkVos(this.sceneVoPosi));
    this.lvAtYkNS.setAdapter(this.adt);
    setTitleView();
    if (this.sceneVoPosi < 3)
      findViewById(2131559054).setVisibility(8);
    getWindow().setSoftInputMode(3);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    CacheSceneData.ykVos.clear();
  }

  protected void onEdit()
  {
    super.onEdit();
    NewSceneBusiness localNewSceneBusiness = this.business;
    int i = this.sceneVoPosi;
    if (this.tvYkNSName.getText().toString().length() == 0);
    for (String str = getString(2131100205) + (-4 + (1 + this.sceneVoPosi)); ; str = this.tvYkNSName.getText().toString())
    {
      localNewSceneBusiness.saveScene(i, str, this.picPath);
      finish();
      return;
    }
  }

  public void onItemClick(int paramInt)
  {
    try
    {
      if (paramInt == -1 + this.adt.getCount())
      {
        Intent localIntent = new Intent(this, AtSaveYongkongList.class);
        localIntent.putExtra("scenePosi", this.sceneVoPosi);
        localIntent.putExtra("ykPosi", paramInt);
        startActivityForResult(localIntent, 0);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    this.screenWidth = this.rlMain.getWidth();
  }

  public void phonePhoto(View paramView)
  {
    Crop.pickImage(this);
    this.rlPhoto.setVisibility(8);
  }

  public void seletedIc(View paramView)
  {
    this.rlPhoto.setVisibility(0);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    if (this.business.isNewScene(this.sceneVoPosi))
    {
      setTiTleText(getString(2131100205) + (-4 + (1 + this.sceneVoPosi)));
      this.tvYkNSName.setText(getString(2131100205) + (-4 + (1 + this.sceneVoPosi)));
    }
    while (true)
    {
      if (!this.business.isNewScene(this.sceneVoPosi))
      {
        YkSceneVo localYkSceneVo = (YkSceneVo)this.business.getData().get(this.sceneVoPosi);
        if (localYkSceneVo.getPicPath().length() > 0)
          this.ivAtYkNewSceneBg2.setImageBitmap(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(localYkSceneVo.getPicPath())));
      }
      this.tvYkNSName.setSelection(this.tvYkNSName.getText().toString().length());
      setEditTextRes(2131100358, getResources().getColor(2131492897));
      return;
      setTiTleText(getIntent().getStringExtra("sceneName"));
      this.tvYkNSName.setText(getIntent().getStringExtra("sceneName"));
    }
  }

  public void showSecond(int paramInt)
  {
    this.rlSecond.setVisibility(0);
    this.tpSecond.setData(this.business.getStrArr());
    this.business.showSecond(paramInt);
  }

  public void takePhoto(View paramView)
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(this.currentFile));
    startActivityForResult(localIntent, this.SHOT_REQ_CODE);
    this.rlPhoto.setVisibility(8);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.AtYkNewSceneActivity
 * JD-Core Version:    0.6.0
 */