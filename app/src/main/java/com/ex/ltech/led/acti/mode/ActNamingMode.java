package com.ex.ltech.led.acti.mode;

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
import com.ex.ltech.led.AtClip;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.colors.Business;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowAdapter;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowView;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowView.OnCoverFlowViewTouchEvent;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.UriUtil;
import com.ex.ltech.led.vo.ModeVo;
import com.google.gson.Gson;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ActNamingMode extends MyBaseActivity
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
  private String modesNames;
  private int modesPosi;
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
    this.tv_act_name_mode.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        ActNamingMode.this.tv_act_name_mode.setVisibility(View.GONE);
        ActNamingMode.this.et_act_name_mode.setVisibility(View.VISIBLE);
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
    this.business.initGalleryData();
    this.business.initModes();
    MyCoverFlowAdapter localMyCoverFlowAdapter = new MyCoverFlowAdapter();
    this.mCoverFlowView.setAdapter(localMyCoverFlowAdapter);
    this.mCoverFlowView.setSelection(0);
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
    setTiTleTextRes(2131100202);
    setEditStrColor(getResources().getColor(R.color.oringe));
    setEditTextRes(2131100358);
  }

  public void actionDown()
  {
    this.et_act_name_mode.setVisibility(View.GONE);
    this.tv_act_name_mode.setVisibility(View.VISIBLE);
  }

  public void actionUp()
  {
    String str = this.tv_act_name_mode.getText().toString();
    this.tv_act_name_mode.setVisibility(View.GONE);
    this.et_act_name_mode.setVisibility(View.VISIBLE);
    this.et_act_name_mode.setText(str);
  }

  public void getLocalPhoto(View paramView)
  {
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Crop.pickImage(this);
  }

  public void goCarmare()
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 9162) && (paramInt2 == -1))
      startActivityForResult(new Intent(this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_GRALLRY").putExtra("OP_CLIP_IC_URI_KEY", paramIntent.getData().toString()).putExtra("OP_CLIP_IC_FILE_PATH_KEY", this.currentFile.getPath()).putExtra("OP_CLIP_VIEW_MAX_WIDTH_KEY", 500), 0);
    while (true)
    {
      if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
      {
        Bitmap localBitmap = new Business(this).autoZoomInBM(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(this.currentFile.getPath())), 500.0D, 500.0D);
        int i = BitmapUtils.getExifOrientation(this.currentFile.getPath());
        if ((i == 90) || (i == 180) || (i == 270))
        {
          Matrix localMatrix = new Matrix();
          localMatrix.postRotate(i);
          localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true);
        }
        FileUtil.saveMyBitmap(this.currentFile.getPath(), localBitmap, "/ltech/led/image");
        startActivityForResult(new Intent(this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_CAMARE").putExtra("OP_CLIP_IC_FILE_PATH_KEY", this.currentFile.getPath()), 0);
      }
      if (paramInt2 == 300)
      {
        this.tempBm = new Business(this).autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), 100.0D, 100.0D);
        this.vo.setNewCreateModeBitmapPath(this.currentFile.getPath());
        ArrayList localArrayList2 = this.business.getModesDefultName();
        this.business.addModesDefultName(getString(R.string.mode) + (1 + localArrayList2.size()));
        BitmapShader localBitmapShader2 = new BitmapShader(this.tempBm, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        ShapeDrawable localShapeDrawable2 = new ShapeDrawable(new OvalShape());
        localShapeDrawable2.getPaint().setShader(localBitmapShader2);
        localShapeDrawable2.setBounds(20, 20, -140 + this.tempBm.getWidth(), this.tempBm.getHeight());
        this.business.changeGalleryData(BitmapUtils.drawableToBitmap(localShapeDrawable2, 100, 100));
        setCoverFlowView();
      }
      if (paramInt2 == 400)
      {
        this.tempBm = new Business(this).autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), 100.0D, 100.0D);
        this.vo.setNewCreateModeBitmapPath(this.currentFile.getPath());
        ArrayList localArrayList1 = this.business.getModesDefultName();
        this.business.addModesDefultName(getString(R.string.mode) + (1 + localArrayList1.size()));
        BitmapShader localBitmapShader1 = new BitmapShader(this.tempBm, Shader.TileMode.MIRROR, Shader.TileMode.REPEAT);
        ShapeDrawable localShapeDrawable1 = new ShapeDrawable(new OvalShape());
        localShapeDrawable1.getPaint().setShader(localBitmapShader1);
        localShapeDrawable1.setBounds(20, 20, -140 + this.tempBm.getWidth(), this.tempBm.getHeight());
        this.business.changeGalleryData(BitmapUtils.drawableToBitmap(localShapeDrawable1, 100, 100));
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
    setTitle();
    init();
  }

  protected void onEdit()
  {
    super.onEdit();
    String str;
    label58: List localList;
    if (this.et_act_name_mode.getText().toString().length() == 0)
    {
      str = this.tv_act_name_mode.getText().toString();
      if (this.bitmapPosi <= 7)
        break label163;
      this.vo.setType(3);
      this.vo.setNewCreateModeName(str);
      localList = this.business.getItemVos();
      if (this.modesPosi == -1)
        break label209;
      localList.remove(this.modesPosi);
      localList.add(this.modesPosi, this.vo);
      localList.remove(-1 + localList.size());
    }
    while (true)
    {
      this.business.saveModesData2local(localList);
      this.business.getItemVos();
      setResult(1000, new Intent());
      finish();
      return;
      str = this.et_act_name_mode.getText().toString();
      break;
      label163: this.vo.setType(1);
      this.vo.setIvLeftRes(((Integer)this.business.getReses().get(this.bitmapPosi)).intValue());
      this.vo.setTvName(str);
      break label58;
      label209: localList.remove(-1 + localList.size());
      localList.add(this.vo);
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
      return ActNamingMode.this.business.getGalleryData().size();
    }

    public Bitmap getImage(int paramInt)
    {
      ActNamingMode.access$002(ActNamingMode.this, paramInt);
      ActNamingMode.access$102(ActNamingMode.this, (String)ActNamingMode.this.business.getModesDefultName().get(paramInt));
      ActNamingMode.this.tv_act_name_mode.setText(ActNamingMode.this.defaultModeName);
      return (Bitmap)ActNamingMode.this.business.getGalleryData().get(paramInt);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.mode.ActNamingMode
 * JD-Core Version:    0.6.0
 */