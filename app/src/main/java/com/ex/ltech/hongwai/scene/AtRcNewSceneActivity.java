package com.ex.ltech.hongwai.scene;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.time.TimingBussines;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.CtTimingAction;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.SceneVo;
import com.ex.ltech.hongwai.vo.SceneVos;
import com.ex.ltech.hongwai.yaokong.AtSaveYongkongList;
import com.ex.ltech.hongwai.yaokong.AtYkLightMode;
import com.ex.ltech.hongwai.yaokong.AtYkSwitchSelectActivity;
import com.ex.ltech.led.AtClip;
import com.ex.ltech.led.my_view.MyAlertDialog17;
import com.ex.ltech.led.my_view.MyAlertDialog17.OnListener;
import com.ex.ltech.led.my_view.MyAlertDialog6;
import com.ex.ltech.led.my_view.MyAlertDialog6.MyOnClickListener;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.soundcloud.android.crop.Crop;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AtRcNewSceneActivity extends YkAt
{
  private int SHOT_REQ_CODE = 1;
  private NewSceneAdapter adt;
  private MyBiz business;
  private int clickTimeItemPosi;
  private File currentFile;
  Intent in;
  int innerRcEditClickPosi;
  private ImageView ivAtYkNewSceneBg;
  private ImageView ivAtYkNewSceneBg2;
  private ListView lvAtYkNS;
  private String opRcType;
  String picPath = "";
  private RelativeLayout rlMain;
  private RelativeLayout rlPhoto;
  private RelativeLayout rlSecond;
  List<InnerRcVo> sceneInnerRcVos;
  SceneVo sceneVo;
  private int sceneVoPosi;
  SceneVos sceneVos;
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
          AtRcNewSceneActivity.this.tempBm = BitmapUtils.squareCropRectangle(BitmapUtils.getBitmapFromUri(AtRcNewSceneActivity.this.getApplicationContext(), Crop.getOutput(this.val$result)), 180, 180);
          String str = Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg";
          FileUtil.saveMyBitmap(str, AtRcNewSceneActivity.this.tempBm, "/ltech/led/image");
          AtRcNewSceneActivity.this.picPath = str;
          AtRcNewSceneActivity.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              AtRcNewSceneActivity.this.ivAtYkNewSceneBg2.setImageBitmap(AtRcNewSceneActivity.this.tempBm);
            }
          });
        }
      }).start();
  }

  public void cancelPic(View paramView)
  {
    this.rlPhoto.setVisibility(View.GONE);
  }

  public void delName(View paramView)
  {
    if (this.sceneVoPosi > 3)
      this.tvYkNSName.setText("");
  }

  public void okSecondOk(View paramView)
  {
    this.rlSecond.setVisibility(View.GONE);
    ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).setSpaceTime(Integer.parseInt(this.tpSecond.getValue()));
    this.adt.notifyDataSetChanged();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
    {
      Bitmap localBitmap3 = this.business.autoZoomInBM(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(this.currentFile.getPath())), 500.0D, 500.0D);
      int k = BitmapUtils.getExifOrientation(this.currentFile.getPath());
      if ((k == 90) || (k == 180) || (k == 270))
      {
        Matrix localMatrix = new Matrix();
        localMatrix.postRotate(k);
        localBitmap3 = Bitmap.createBitmap(localBitmap3, 0, 0, localBitmap3.getWidth(), localBitmap3.getHeight(), localMatrix, true);
      }
      FileUtil.saveMyBitmap(this.currentFile.getPath(), localBitmap3, "/ltech/led/image");
      startActivityForResult(new Intent(this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_CAMARE").putExtra("OP_CLIP_IC_FILE_PATH_KEY", this.currentFile.getPath()), 0);
    }
    CtTimingAction localCtTimingAction;
    if (paramInt2 == 230000)
      localCtTimingAction = (CtTimingAction)paramIntent.getSerializableExtra(CtTimingAction.class.getName());
    label515: int i;
    switch (localCtTimingAction.seletedType)
    {
    default:
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).setStatus(getString(2131100054));
      this.adt.notifyDataSetChanged();
      if (paramInt2 == 300)
      {
        ImageView localImageView2 = this.ivAtYkNewSceneBg2;
        Bitmap localBitmap2 = this.business.autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), this.business.dp2px(100), this.business.dp2px(100));
        this.tempBm = localBitmap2;
        localImageView2.setImageBitmap(localBitmap2);
        this.picPath = (Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg");
        FileUtil.saveMyBitmap(this.picPath, this.tempBm, "/ltech/led/image");
      }
      if (paramInt2 != 400)
        break;
      ImageView localImageView1 = this.ivAtYkNewSceneBg2;
      Bitmap localBitmap1 = this.business.autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), this.business.dp2px(100), this.business.dp2px(100));
      this.tempBm = localBitmap1;
      localImageView1.setImageBitmap(localBitmap1);
      this.picPath = (Environment.getExternalStorageDirectory() + "/ltech/led/image" + "/" + System.currentTimeMillis() + ".jpg");
      FileUtil.saveMyBitmap(this.picPath, this.tempBm, "/ltech/led/image");
      if (paramInt2 == 20000)
      {
        InnerRcVo localInnerRcVo3 = (InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName());
        ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.add(-1 + ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.size(), localInnerRcVo3);
        this.adt.notifyDataSetChanged();
      }
      if (paramInt2 == 220000)
      {
        i = 1;
        label607: if (paramInt2 != 210000)
          break label1218;
      }
    case 1:
    case 2:
    }
    label1218: for (int j = 1; ; j = 0)
    {
      if ((j | i) != 0)
      {
        InnerRcVo localInnerRcVo2 = (InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName());
        ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.add(-1 + ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.size(), localInnerRcVo2);
        this.adt.notifyDataSetChanged();
      }
      if (paramInt2 == 30000)
      {
        InnerRcVo localInnerRcVo1 = (InnerRcVo)paramIntent.getSerializableExtra(InnerRcVo.class.getName());
        ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.remove(this.innerRcEditClickPosi);
        ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.add(this.innerRcEditClickPosi, localInnerRcVo1);
        this.adt.notifyDataSetChanged();
      }
      if (paramInt2 == this.saveYkOk)
        finish();
      return;
      Ct1ScenesVo localCt1ScenesVo = new TimingBussines(this).getCt1SceneVos();
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1Brt = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1Brt;
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1C = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1C;
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1W = ((Ct1SceneVo)localCt1ScenesVo.ct1SceneVos.get(localCtTimingAction.scenePosi)).irCt1W;
      break;
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1Brt = localCtTimingAction.brt;
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1C = localCtTimingAction.c;
      ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(this.clickTimeItemPosi)).nonIrDevice.irCt1W = localCtTimingAction.w;
      break;
      if ((paramInt1 == 9162) && (paramInt2 == -1))
      {
        startActivityForResult(new Intent(this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_GRALLRY").putExtra("OP_CLIP_IC_URI_KEY", paramIntent.getData().toString()).putExtra("OP_CLIP_IC_FILE_PATH_KEY", this.currentFile.getPath()).putExtra("OP_CLIP_VIEW_MAX_WIDTH_KEY", 1080), 0);
        break label515;
      }
      if (paramInt1 != 6709)
        break label515;
      handleCrop(paramInt2, paramIntent);
      break label515;
      i = 0;
      break label607;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968713);
    this.ivAtYkNewSceneBg = ((ImageView)findViewById(2131559052));
    this.ivAtYkNewSceneBg2 = ((ImageView)findViewById(2131559053));
    this.tvYkNSName = ((EditText)findViewById(2131559055));
    this.lvAtYkNS = ((ListView)findViewById(2131559056));
    this.rlSecond = ((RelativeLayout)findViewById(2131559057));
    this.tpSecond = ((MyTimePickerView)findViewById(2131559058));
    this.rlPhoto = ((RelativeLayout)findViewById(2131559059));
    this.rlMain = ((RelativeLayout)findViewById(2131558902));
    this.sceneVoPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.opRcType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.business = new MyBiz(this);
    this.tpSecond.setmColorText(Color.parseColor("#5b5b5b"));
    this.tpSecond.setData(this.business.getTimeStrArr());
    this.sceneVos = this.business.getSceneVos();
    if (this.opRcType.equals("OP_AT_TYPE_CREATE"))
    {
      this.sceneVos.sceneVos.add(new SceneVo());
      InnerRcVo localInnerRcVo = new InnerRcVo();
      localInnerRcVo.setIsAdd(true);
      ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.add(localInnerRcVo);
    }
    if (this.sceneVoPosi == 0)
    {
      this.tvYkNSName.setKeyListener(null);
      findViewById(2131559054).setVisibility(View.GONE);
      this.ivAtYkNewSceneBg2.setBackgroundResource(2130903560);
    }
    if (this.sceneVoPosi == 1)
    {
      this.tvYkNSName.setKeyListener(null);
      findViewById(2131559054).setVisibility(View.GONE);
      this.ivAtYkNewSceneBg2.setBackgroundResource(2130903647);
    }
    if (this.sceneVoPosi == 2)
    {
      this.tvYkNSName.setKeyListener(null);
      findViewById(2131559054).setVisibility(View.GONE);
      this.ivAtYkNewSceneBg2.setBackgroundResource(2130903649);
    }
    if (this.sceneVoPosi == 3)
    {
      this.tvYkNSName.setKeyListener(null);
      findViewById(2131559054).setVisibility(View.GONE);
      this.ivAtYkNewSceneBg2.setBackgroundResource(2130903648);
    }
    this.adt = new NewSceneAdapter(this, ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos);
    this.lvAtYkNS.setAdapter(this.adt);
    setTitleView();
    getWindow().setSoftInputMode(3);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    CacheSceneData.sceneInnerRcVos.clear();
  }

  protected void onEdit()
  {
    super.onEdit();
    if (((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).getInnerRcVos().size() == 1)
      return;
    findViewById(2131559060).setVisibility(View.VISIBLE);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        String str;
        if (AtRcNewSceneActivity.this.tvYkNSName.getText().toString().length() == 0)
        {
          str = AtRcNewSceneActivity.this.getString(2131100205) + (-2 + (1 + AtRcNewSceneActivity.this.sceneVoPosi));
          if (AtRcNewSceneActivity.this.sceneVoPosi > 3)
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setName(str);
          ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setPicPath(AtRcNewSceneActivity.this.picPath);
          if (AtRcNewSceneActivity.this.opRcType.equals("OP_AT_TYPE_CREATE"))
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setPicRes(2130903568);
          if (AtRcNewSceneActivity.this.tempBm == null)
            break label416;
          ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("custom");
        }
        while (true)
        {
          if (AtRcNewSceneActivity.this.sceneVoPosi == 0)
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("goHome");
          if (AtRcNewSceneActivity.this.sceneVoPosi == 1)
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("outHome");
          if (AtRcNewSceneActivity.this.sceneVoPosi == 2)
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("sleep");
          if (AtRcNewSceneActivity.this.sceneVoPosi == 3)
            ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("wakeup");
          AtRcNewSceneActivity.this.business.saveSceneVos(AtRcNewSceneActivity.this.sceneVos);
          AtRcNewSceneActivity.this.setResult(20000);
          AtRcNewSceneActivity.this.finish();
          return;
          str = AtRcNewSceneActivity.this.tvYkNSName.getText().toString();
          break;
          label416: ((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).setSenceIcType("goHome");
        }
      }
    }
    , 1000L);
  }

  public void onInnerRcDel(int paramInt)
  {
    ((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.remove(paramInt);
    this.adt.notifyDataSetChanged();
  }

  public void onInnerRcEdit(int paramInt)
  {
    this.innerRcEditClickPosi = paramInt;
    int i = ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(paramInt)).getmType();
    if ((i == 2) || (i == 1))
      while (true)
      {
        try
        {
          MyAlertDialog6 localMyAlertDialog6 = new MyAlertDialog6(this);
          localMyAlertDialog6.show();
          localMyAlertDialog6.getWindow().setGravity(80);
          localMyAlertDialog6.setMyOnClickListener(new MyAlertDialog6.MyOnClickListener(i, paramInt)
          {
            public void onChanel()
            {
              AtRcNewSceneActivity.this.in = new Intent(AtRcNewSceneActivity.this, AtSimpleRcSetActivity.class);
              AtRcNewSceneActivity.this.in.putExtra("OP_SCENE_IN_TV_TYPE_KEY", "OP_SCENE_IN_TV_TYPE_CHANNEL");
              AtRcNewSceneActivity.this.in.putExtra("RC_TYPE_KEY", this.val$mType);
              AtRcNewSceneActivity.this.in.putExtra("OP_RC_POSI_KEY", ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).getmSaveRcListPosi());
              AtRcNewSceneActivity.this.in.putExtra("RC_NAME_KEY", ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).getName());
              AtRcNewSceneActivity.this.in.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_EXIST");
              AtRcNewSceneActivity.this.in.putExtra(InnerRcVo.class.getName(), (Serializable)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position));
              AtRcNewSceneActivity.this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", AtRcNewSceneActivity.this.getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
              AtRcNewSceneActivity.this.startActivityForResult(AtRcNewSceneActivity.this.in, 0);
            }

            public void onOnOff()
            {
              AtRcNewSceneActivity.this.in = new Intent(AtRcNewSceneActivity.this, AtSimpleRcSetActivity.class);
              AtRcNewSceneActivity.this.in.putExtra("OP_SCENE_IN_TV_TYPE_KEY", "OP_SCENE_IN_TV_TYPE_ON_OFF");
              AtRcNewSceneActivity.this.in.putExtra("RC_TYPE_KEY", this.val$mType);
              AtRcNewSceneActivity.this.in.putExtra("OP_RC_POSI_KEY", ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).getmSaveRcListPosi());
              AtRcNewSceneActivity.this.in.putExtra("RC_NAME_KEY", ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).getName());
              AtRcNewSceneActivity.this.in.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_EXIST");
              AtRcNewSceneActivity.this.in.putExtra(InnerRcVo.class.getName(), (Serializable)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position));
              AtRcNewSceneActivity.this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", AtRcNewSceneActivity.this.getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
              AtRcNewSceneActivity.this.startActivityForResult(AtRcNewSceneActivity.this.in, 0);
            }
          });
          return;
          this.in = new Intent(this, AtSimpleRcSetActivity.class);
          this.in.putExtra("RC_TYPE_KEY", i);
          this.in.putExtra("OP_RC_POSI_KEY", ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(paramInt)).getmSaveRcListPosi());
          this.in.putExtra("RC_NAME_KEY", ((InnerRcVo)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(paramInt)).getName());
          this.in.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_EXIST");
          this.in.putExtra(InnerRcVo.class.getName(), (Serializable)((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).innerRcVos.get(paramInt));
          this.in.putExtra("OP_SCENE_IN_RC_POSI_KEY", getIntent().getIntExtra("OP_SCENE_IN_RC_POSI_KEY", 0));
          startActivityForResult(this.in, 0);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          return;
        }
        this.in = new Intent(this, AtAirRcSetActivity.class);
        continue;
        this.in = new Intent(this, AtYkSwitchSelectActivity.class);
        continue;
        this.in = new Intent(this, AtYkSwitchSelectActivity.class);
        continue;
        MyAlertDialog17 localMyAlertDialog17 = new MyAlertDialog17(this);
        localMyAlertDialog17.setOnListener(new MyAlertDialog17.OnListener(paramInt)
        {
          public void cancel()
          {
          }

          public void effect()
          {
            AtRcNewSceneActivity.this.goAct4result(AtYkLightMode.class, 0);
          }

          public void off()
          {
            ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).nonIrDevice.irCt1Onoff = false;
            ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).setStatus(AtRcNewSceneActivity.this.getString(2131100226));
            AtRcNewSceneActivity.this.adt.notifyDataSetChanged();
          }

          public void on()
          {
            ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).nonIrDevice.irCt1Onoff = true;
            ((InnerRcVo)((SceneVo)AtRcNewSceneActivity.this.sceneVos.sceneVos.get(AtRcNewSceneActivity.this.sceneVoPosi)).innerRcVos.get(this.val$position)).setStatus(AtRcNewSceneActivity.this.getString(R.string.on));
            AtRcNewSceneActivity.this.adt.notifyDataSetChanged();
          }
        });
        localMyAlertDialog17.show();
      }
    switch (i)
    {
    case 6:
    case 7:
    case 8:
    case 9:
    default:
    case 5:
    case 10:
    case 11:
    case 12:
    }
  }

  public void onItemClick(int paramInt)
  {
    try
    {
      if (paramInt == -1 + this.adt.getCount())
      {
        Intent localIntent = new Intent(this, AtSaveYongkongList.class);
        localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
        localIntent.putExtra("OP_SCENE_IN_RC_POSI_KEY", this.adt.getCount());
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
    File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
    if (!localFile.exists())
      localFile.mkdirs();
    this.currentFile = new File(localFile, System.currentTimeMillis() + ".jpg");
    Crop.pickImage(this);
    this.rlPhoto.setVisibility(View.GONE);
  }

  public void seletedIc(View paramView)
  {
    this.rlPhoto.setVisibility(View.VISIBLE);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    if (this.opRcType.equals("OP_AT_TYPE_CREATE"))
    {
      setTiTleText(getString(2131100205) + (-2 + (1 + this.sceneVoPosi)));
      this.tvYkNSName.setText(getString(2131100205) + (-2 + (1 + this.sceneVoPosi)));
    }
    while (true)
    {
      this.tvYkNSName.setSelection(this.tvYkNSName.getText().toString().length());
      setEditTextRes(2131100358, getResources().getColor(2131492897));
      return;
      setTiTleText(((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).getName());
      this.tvYkNSName.setText(((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).getName());
      if (((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).getPicPath().length() <= 0)
        continue;
      try
      {
        ImageView localImageView = this.ivAtYkNewSceneBg2;
        Bitmap localBitmap = BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(((SceneVo)this.sceneVos.sceneVos.get(this.sceneVoPosi)).getPicPath()));
        this.tempBm = localBitmap;
        localImageView.setImageBitmap(localBitmap);
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }

  public void showSecond(int paramInt, String paramString)
  {
    this.clickTimeItemPosi = paramInt;
    this.rlSecond.setVisibility(View.VISIBLE);
    this.tpSecond.setSelected(paramString);
  }

  public void spaceTimeCancel(View paramView)
  {
    this.rlSecond.setVisibility(View.GONE);
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
    this.rlPhoto.setVisibility(View.GONE);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.AtRcNewSceneActivity
 * JD-Core Version:    0.6.0
 */