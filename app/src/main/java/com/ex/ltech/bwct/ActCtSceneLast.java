package com.ex.ltech.bwct;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowAdapter;
import com.ex.ltech.led.my_view.gallery_view.CoverFlowView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.utils.UriUtil;
import com.ex.ltech.led.vo.CtSceneVo;
import com.google.gson.Gson;
import com.soundcloud.android.crop.Crop;

import java.io.File;
import java.util.ArrayList;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.listener.SendPipeListener;

public class ActCtSceneLast extends MyBaseActivity
  implements CoverFlowView.OnCoverFlowViewTouchEvent
{
  public int PHOTO_REQ_CODE = 2;
  int SHOT_REQ_CODE = 1;
  private int bitmapPosi;
  SceneLastBussiness business = new SceneLastBussiness(this);
  private ColorBussiness colorBussiness;
  private File currentFile;
  private String defaultModeName;
  EditText et_act_name_mode;
  CoverFlowView<MyCoverFlowAdapter> mCoverFlowView;
  private String modesNames;
  private int modesPosi;
  private Bitmap tempBm;
  TextView tv_act_name_mode;
  private CtSceneVo vo;

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
        ActCtSceneLast.this.tv_act_name_mode.setVisibility(View.GONE);
        ActCtSceneLast.this.et_act_name_mode.setVisibility(View.VISIBLE);
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
      this.vo.setIcPath(str);
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
    String str = getIntent().getStringExtra("sceneDataStr");
    this.modesNames = getIntent().getStringExtra("ctSceneName");
    this.modesPosi = getIntent().getIntExtra("ctScenePosi", -1);
    this.vo = ((CtSceneVo)new Gson().fromJson(str, CtSceneVo.class));
    this.business.initGalleryData();
    this.business.initModes();
    MyCoverFlowAdapter localMyCoverFlowAdapter = new MyCoverFlowAdapter();
    this.mCoverFlowView.setAdapter(localMyCoverFlowAdapter);
    this.mCoverFlowView.setSelection(0);
    this.colorBussiness = new ColorBussiness(this);
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
    setTiTleTextRes(2131100364);
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
        this.vo.setIcPath(str);
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
    setTitle();
    init();
  }

  protected void onEdit()
  {
    super.onEdit();
    String str;
    if (this.et_act_name_mode.getText().toString().length() == 0)
    {
      str = this.tv_act_name_mode.getText().toString();
      if (this.bitmapPosi <= 7)
        break label202;
      this.vo.setName(str);
    }
    while (true)
    {
      this.business.getItemVos();
      if (this.modesPosi != -1)
      {
        this.colorBussiness.loadCtSceneVos();
        this.colorBussiness.saveCtSceneVo(this.vo, this.modesPosi);
      }
      this.business.getItemVos();
      setResult(1000, new Intent());
      finish();
      XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
      DeviceManage.getInstance();
      localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), new CmdDateBussiness("0000").getCtSceneColorCmd(1 + this.modesPosi, this.vo.getName(), this.vo.getBrt(), this.vo.getC(), this.vo.getW()), new SendPipeListener()
      {
        public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
        {
        }
      });
      return;
      str = this.et_act_name_mode.getText().toString();
      break;
      label202: this.vo.setName(str);
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
      return 8;
    }

    public Bitmap getImage(int paramInt)
    {
      ActCtSceneLast.access$002(ActCtSceneLast.this, paramInt);
      ActCtSceneLast.access$102(ActCtSceneLast.this, (String)ActCtSceneLast.this.business.getModesDefultName().get(paramInt));
      ActCtSceneLast.this.vo.setIcResPosi(paramInt);
      ActCtSceneLast.this.tv_act_name_mode.setText(ActCtSceneLast.this.defaultModeName);
      return (Bitmap)ActCtSceneLast.this.business.getGalleryData().get(paramInt);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.bwct.ActCtSceneLast
 * JD-Core Version:    0.6.0
 */