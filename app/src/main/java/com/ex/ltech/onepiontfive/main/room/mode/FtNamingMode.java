package com.ex.ltech.onepiontfive.main.room.mode;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader.TileMode;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowAdapter;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowView;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowView.OnCoverFlowViewTouchEvent;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.utils.UriUtil;
import com.ex.ltech.led.vo.ModeVo;
import com.ex.ltech.onepiontfive.main.MyBusiness.MySendListener;
import com.google.gson.Gson;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class FtNamingMode extends MyBaseActivity
  implements CoverFlowView.OnCoverFlowViewTouchEvent
{
  public int PHOTO_REQ_CODE = 2;
  int SHOT_REQ_CODE = 1;
  private int bitmapPosi;
  ModeBusiness business = new ModeBusiness(this);
  private File currentFile;
  private String defaultModeName;
  EditText et_act_name_mode;
  CoverFlowView<MyCoverFlowAdapter> mCoverFlowView;
  ModeBusiness modeBusiness;
  private String modesNames;
  private int modesPosi;
  String saveModeName = "";
  private Bitmap tempBm;
  TextView tv_act_name_mode;
  private ModeVo vo;

  private void beginCrop(Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
  }

  private void findView()
  {
    this.mCoverFlowView = ((CoverFlowView)findViewById(2131558555));
    this.et_act_name_mode = ((EditText)findViewById(2131558556));
    this.tv_act_name_mode = ((TextView)findViewById(2131558557));
    this.tv_act_name_mode.setVisibility(View.GONE);
    this.tv_act_name_mode.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        FtNamingMode.this.tv_act_name_mode.setVisibility(View.GONE);
      }
    });
  }

  private void handleCrop(int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
    {
      this.tempBm = BitmapUtils.autoZoomInBM(BitmapUtils.getBitmapFromUri(this, Crop.getOutput(paramIntent)), 100.0D, 100.0D);
      UriUtil.getRealFilePath(this, paramIntent.getData());
      String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
      FileUtil.saveMyBitmap(str, this.tempBm, "/ltech/led/image");
      this.vo.setNewCreateModeBitmapPath(str);
      ArrayList localArrayList = this.business.getModesDefultName();
      this.business.addModesDefultName(getString(R.string.mode) + (1 + localArrayList.size()));
      BitmapShader localBitmapShader = new BitmapShader(this.tempBm, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
      ShapeDrawable localShapeDrawable = new ShapeDrawable(new OvalShape());
      localShapeDrawable.getPaint().setShader(localBitmapShader);
      localShapeDrawable.setBounds(20, 20, -140 + this.tempBm.getWidth(), this.tempBm.getHeight());
      this.business.changeGalleryData(BitmapUtils.drawableToBitmap(localShapeDrawable, 100, 100));
      setCoverFlowView();
      System.out.println();
    }
    do
      return;
    while (paramInt != 404);
    Toast.makeText(this, Crop.getError(paramIntent).getMessage(), 0).show();
  }

  private void init()
  {
    String str = getIntent().getStringExtra("modeDataStr");
    this.modesNames = getIntent().getStringExtra("modesNames");
    this.modesPosi = getIntent().getIntExtra("modesPosi", -1);
    this.vo = ((ModeVo)new Gson().fromJson(str, ModeVo.class));
    if (this.vo.getTvName() != null)
      this.et_act_name_mode.setText(this.vo.getTvName());
    while (true)
    {
      this.business.initGalleryData();
      MyCoverFlowAdapter localMyCoverFlowAdapter = new MyCoverFlowAdapter();
      this.mCoverFlowView.setAdapter(localMyCoverFlowAdapter);
      this.mCoverFlowView.setSelection(0);
      return;
      this.et_act_name_mode.setText(2131100202);
    }
  }

  private void returnDataToLastAct()
  {
  }

  private void setCoverFlowView()
  {
    MyCoverFlowAdapter localMyCoverFlowAdapter = new MyCoverFlowAdapter();
    this.mCoverFlowView.setAdapter(localMyCoverFlowAdapter);
    this.mCoverFlowView.setSelection(-1 + this.business.getGalleryData().size());
  }

  private void setListener()
  {
    this.mCoverFlowView.setOnCoverFlowViewTouchEvent(this);
  }

  private void setTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    if (this.vo.getTvName() != null)
      setTiTleText(getString(2131100049) + getString(R.string.mode));
    while (true)
    {
      setEditStrColor(getResources().getColor(2131492897));
      setEditTextRes(2131100358);
      return;
      setTiTleTextRes(2131100202);
    }
  }

  public void actionDown()
  {
  }

  public void actionUp()
  {
    this.tv_act_name_mode.getText().toString();
    this.tv_act_name_mode.setVisibility(View.GONE);
  }

  public void getLocalPhoto(View paramView)
  {
    Crop.pickImage(this);
  }

  public void goCarmare()
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 9162) && (paramInt2 == -1))
      beginCrop(paramIntent.getData());
    while (true)
    {
      if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
      {
        this.tempBm = BitmapUtils.autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), 100.0D, 100.0D);
        int i = BitmapUtils.getExifOrientation(this.currentFile.getPath());
        if ((i == 90) || (i == 180) || (i == 270))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(i);
          this.tempBm = Bitmap.createBitmap(this.tempBm, 0, 0, 100, 100, localMatrix, true);
        }
        String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
        FileUtil.saveMyBitmap(str, this.tempBm, "/ltech/led/image");
        this.vo.setNewCreateModeBitmapPath(str);
        ArrayList localArrayList = this.business.getModesDefultName();
        this.business.addModesDefultName(getString(R.string.mode) + (1 + localArrayList.size()));
        BitmapShader localBitmapShader = new BitmapShader(this.tempBm, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        ShapeDrawable localShapeDrawable = new ShapeDrawable(new OvalShape());
        localShapeDrawable.getPaint().setShader(localBitmapShader);
        localShapeDrawable.setBounds(20, 20, -140 + this.tempBm.getWidth(), this.tempBm.getHeight());
        this.business.changeGalleryData(BitmapUtils.drawableToBitmap(localShapeDrawable, 100, 100));
        setCoverFlowView();
      }
      return;
      if (paramInt1 != 6709)
        continue;
      handleCrop(paramInt2, paramIntent);
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968608);
    findView();
    setListener();
    init();
    setTitle();
  }

  public void onDestroy()
  {
    super.onDestroy();
    System.out.println("ftNameMOde destroy ");
    if (this.modeBusiness != null)
      this.modeBusiness.setSendListener(null);
  }

  protected void onEdit()
  {
    super.onEdit();
    if (this.et_act_name_mode.getText().toString().getBytes().length > 24)
    {
      Toast.makeText(this, 2131100328, 0).show();
      return;
    }
    if (this.et_act_name_mode.getText().toString().length() == 0);
    for (this.saveModeName = this.tv_act_name_mode.getText().toString(); ; this.saveModeName = this.et_act_name_mode.getText().toString())
    {
      this.modeBusiness = new ModeBusiness(this);
      try
      {
        ModeBusiness localModeBusiness = this.modeBusiness;
        2 local2 = new MyBusiness.MySendListener()
        {
          public void onFail()
          {
            FtNamingMode.this.toast(2131100334);
          }

          public void onOk(byte[] paramArrayOfByte)
          {
            String str = StringUtils.btye2Str(paramArrayOfByte);
            if (!str.substring(18, 20).equalsIgnoreCase("87"))
              return;
            int i = Integer.parseInt(str.substring(24, 26), 16);
            if (i > 16)
            {
              FtNamingMode.this.toast(2131100042);
              return;
            }
            FtNamingMode.this.vo.setOrder(i);
            FtNamingMode.this.vo.setOther(true);
            List localList;
            if (FtNamingMode.this.bitmapPosi > 7)
            {
              FtNamingMode.this.vo.setType(3);
              FtNamingMode.this.vo.setNewCreateModeName(FtNamingMode.this.saveModeName);
              FtNamingMode.this.vo.setTvName(FtNamingMode.this.saveModeName);
              localList = FtNamingMode.this.business.getEditableModes();
              if (FtNamingMode.this.modesPosi == -1)
                break label316;
              localList.remove(FtNamingMode.this.modesPosi);
              localList.add(FtNamingMode.this.modesPosi, FtNamingMode.this.vo);
              localList.remove(-1 + localList.size());
            }
            while (true)
            {
              FtNamingMode.this.modeBusiness.saveModesData2local(localList);
              FtNamingMode.this.business.getItemVos();
              FtNamingMode.this.modeBusiness.setMySendListener(null);
              FtNamingMode.this.setResult(1000, new Intent());
              FtNamingMode.this.finish();
              return;
              FtNamingMode.this.vo.setType(1);
              FtNamingMode.this.vo.setIvLeftRes(((Integer)FtNamingMode.this.business.getReses().get(FtNamingMode.this.bitmapPosi)).intValue());
              break;
              label316: FtNamingMode.this.vo.setAppId(FtNamingMode.this.modeBusiness.userIdHexString);
              localList.remove(-1 + localList.size());
              localList.add(FtNamingMode.this.vo);
            }
          }

          public void onTimeOut()
          {
          }
        };
        int i = this.modesPosi;
        boolean bool = false;
        if (i == -1)
          bool = true;
        localModeBusiness.saveCustomMode(local2, bool, 1 + this.modesPosi, this.vo.getBrt(), this.vo.getSpeed(), this.vo.getTransformation(), this.vo.getColors(), this.saveModeName.getBytes());
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
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

  public void shot(View paramView)
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Intent localIntent = new Intent("android.media.action.IMAGE_CAPTURE");
    localIntent.putExtra("output", Uri.fromFile(this.currentFile));
    startActivityForResult(localIntent, this.SHOT_REQ_CODE);
  }

  public class MyCoverFlowAdapter extends CoverFlowAdapter
  {
    public MyCoverFlowAdapter()
    {
    }

    public int getCount()
    {
      return FtNamingMode.this.business.getGalleryData().size();
    }

    public Bitmap getImage(int paramInt)
    {
      FtNamingMode.access$002(FtNamingMode.this, paramInt);
      FtNamingMode.access$102(FtNamingMode.this, (String)FtNamingMode.this.business.getModesDefultName().get(paramInt));
      return (Bitmap)FtNamingMode.this.business.getGalleryData().get(paramInt);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.mode.FtNamingMode
 * JD-Core Version:    0.6.0
 */