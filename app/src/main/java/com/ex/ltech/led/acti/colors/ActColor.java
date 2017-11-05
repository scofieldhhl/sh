package com.ex.ltech.led.acti.colors;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;
import com.ex.ltech.led.AtClip;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.acti.Main;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.connetion.SocketManager;
import com.ex.ltech.led.my_view.ColorPickerView;
import com.ex.ltech.led.my_view.ColorPickerView.OnColorChangedListener;
import com.ex.ltech.led.my_view.MyAlertDialog2;
import com.ex.ltech.led.my_view.MyAlertDialog2.MyOnClickListener;
import com.ex.ltech.led.my_view.MySeekBar;
import com.ex.ltech.led.my_view.MySeekBar.onMySBtouchListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.ex.ltech.led.utils.FileUtil;
import com.ex.ltech.led.vo.DeviceVo;
import com.indris.material.RippleView;
import com.soundcloud.android.crop.Crop;
import com.zcw.togglebutton.ToggleButton;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import java.io.File;
import java.io.PrintStream;

public class ActColor extends MyBaseActivity
  implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, ColorPickerView.OnColorChangedListener, MySeekBar.onMySBtouchListener
{
  int SHOT_REQ_CODE = 1;
  private BaseBusiness baseBusiness;
  private int blue;
  private PopupWindow bottomPopWin = null;
  private int bright = 130;
  private Button btn_acti_color_op_photo;
  private Button btn_acti_color_show_lights;
  private Button btn_acti_color_son_dwon;
  private Button btn_acti_color_son_light_1;
  private Button btn_acti_color_son_light_2;
  private Button btn_acti_color_son_light_3;
  private Button btn_acti_color_son_light_4;
  private RippleView btn_color_act_pop_photo;
  private RippleView btn_color_act_pop_rainbow;
  private Business business;
  CmdDateBussiness bussiness;
  private ColorPickerView color_picker_view;
  private File currentFile;
  private int gray;
  private int green;
  Handler handler = new Handler()
  {
  };
  boolean isInitPikerView = false;
  boolean isRbgLamp = false;
  private int lightNum;
  SocketManager manager;
  private boolean myPhoneExist = false;
  private boolean myPhoneIsShow = false;
  private View popView;
  private int popY;
  private int red;
  private RelativeLayout rl_acti_color_parent = null;
  private MySeekBar sb_color_acti_1;
  private SeekBar sb_color_acti_2;
  private View sonView = null;
  private ToggleButton tb_acti_color_son_swc_1;
  private ToggleButton tb_acti_color_son_swc_2;
  private ToggleButton tb_acti_color_son_swc_3;
  private ToggleButton tb_acti_color_son_swc_4;
  int time;

  private void beginCrop(Uri paramUri)
  {
    Crop.of(paramUri, Uri.fromFile(new File(getCacheDir(), "cropped"))).asSquare().start(this);
  }

  private void findView()
  {
    this.sb_color_acti_2 = ((SeekBar)findViewById(2131558676));
    this.sb_color_acti_1 = ((MySeekBar)findViewById(2131558674));
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActColor.this.sb_color_acti_1.setProgress(100);
        System.out.println(ActColor.this.business.getPikerY4Xml("2") + "噢噢噢");
        ActColor.this.sb_color_acti_1.setProgressColor(ActColor.this.color_picker_view.getCurPikerColor());
      }
    }
    , 100L);
    this.sb_color_acti_2.setProgress(100);
    this.btn_acti_color_op_photo = ((Button)findViewById(2131558678));
    this.btn_acti_color_show_lights = ((Button)findViewById(2131558679));
    this.rl_acti_color_parent = ((RelativeLayout)findViewById(2131558672));
    getLayoutInflater();
    this.sonView = LayoutInflater.from(this).inflate(2130968632, null);
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(12);
    this.rl_acti_color_parent.addView(this.sonView, localLayoutParams);
    this.color_picker_view = ((ColorPickerView)findViewById(2131558677));
    this.color_picker_view.setOnColorChangedListener(this);
    this.btn_acti_color_son_light_1 = ((Button)this.sonView.findViewById(2131558695));
    this.btn_acti_color_son_light_2 = ((Button)this.sonView.findViewById(2131558696));
    this.btn_acti_color_son_light_3 = ((Button)this.sonView.findViewById(2131558697));
    this.btn_acti_color_son_light_4 = ((Button)this.sonView.findViewById(2131558698));
    this.btn_acti_color_son_dwon = ((Button)this.sonView.findViewById(2131558703));
    this.tb_acti_color_son_swc_1 = ((ToggleButton)this.sonView.findViewById(2131558699));
    this.tb_acti_color_son_swc_2 = ((ToggleButton)this.sonView.findViewById(2131558700));
    this.tb_acti_color_son_swc_3 = ((ToggleButton)this.sonView.findViewById(2131558701));
    this.tb_acti_color_son_swc_4 = ((ToggleButton)this.sonView.findViewById(2131558702));
    getLayoutInflater();
    this.popView = LayoutInflater.from(this).inflate(2130968716, null);
    this.bottomPopWin = new PopupWindow(this.popView, -2, -2);
    this.bottomPopWin.setAnimationStyle(2131296562);
    this.bottomPopWin.setOutsideTouchable(true);
    this.bottomPopWin.setTouchable(true);
    this.bottomPopWin.setBackgroundDrawable(new ColorDrawable(0));
  }

  private void handleCrop(int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
    {
      this.business.saveBgType("gallery");
      this.business.saveBgPath(Crop.getOutput(paramIntent).toString());
      this.color_picker_view.setSellPhoneBm(this.business.autoZoomInBM(BitmapUtils.getBitmapFromUri(this, Crop.getOutput(paramIntent)), this.color_picker_view.width, this.color_picker_view.width));
      this.myPhoneExist = true;
      this.myPhoneIsShow = true;
    }
    do
      return;
    while (paramInt != 404);
    Toast.makeText(this, Crop.getError(paramIntent).getMessage(), 0).show();
  }

  private void init()
  {
    this.business = new Business(this);
    this.baseBusiness = new BaseBusiness(this);
    this.popY = (1470 * getWindowManager().getDefaultDisplay().getHeight() / 1920);
    this.bussiness = new CmdDateBussiness(this, Main.deviceVo.getPwd());
    this.manager = SocketManager.instance();
    this.manager.ip = Main.deviceVo.getIp();
  }

  private void setListener()
  {
    this.sb_color_acti_1.setListener(this);
    this.sb_color_acti_2.setOnSeekBarChangeListener(this);
    this.btn_acti_color_op_photo.setOnClickListener(this);
    this.btn_acti_color_show_lights.setOnClickListener(this);
    this.btn_acti_color_son_dwon.setOnClickListener(this);
    this.popView.findViewById(2131559063).setOnClickListener(this);
    this.popView.findViewById(2131559064).setOnClickListener(this);
  }

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.device_ic);
    setTiTleTextRes(2131099965);
    setDeviceTextRes(Main.deviceVo.getDeviceName());
  }

  public void addPanel(View paramView)
  {
    findViewById(2131558680).setVisibility(View.GONE);
    findViewById(2131558681).setVisibility(View.VISIBLE);
  }

  public void addPanel1(View paramView)
  {
    findViewById(2131558681).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "panel11"; ; str = "panel1")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addPanel2(View paramView)
  {
    findViewById(2131558681).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "panel22"; ; str = "panel2")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addPanel3(View paramView)
  {
    findViewById(2131558681).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "panel33"; ; str = "panel3")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addPanel4(View paramView)
  {
    findViewById(2131558681).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "panel44"; ; str = "panel4")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addRc(View paramView)
  {
    findViewById(2131558680).setVisibility(View.GONE);
    findViewById(2131558690).setVisibility(View.VISIBLE);
  }

  public void addRc1(View paramView)
  {
    findViewById(2131558690).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "rc11"; ; str = "rc1")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addRc2(View paramView)
  {
    findViewById(2131558690).setVisibility(View.GONE);
    Intent localIntent = new Intent(this, AtPanelLearnActivity.class);
    if (this.isRbgLamp);
    for (String str = "rc22"; ; str = "rc2")
    {
      startActivity(localIntent.putExtra("type", str));
      return;
    }
  }

  public void addView(View paramView)
  {
    RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(-2, -2);
    localLayoutParams.addRule(12);
    this.sonView.setAnimation(AnimationUtils.loadAnimation(this, 2131034124));
    this.rl_acti_color_parent.addView(this.sonView, localLayoutParams);
  }

  public int getLightNum()
  {
    return this.lightNum;
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

  public void hideAddPanelDialog(View paramView)
  {
    findViewById(2131558681).setVisibility(View.GONE);
  }

  public void hideAddRcDialog(View paramView)
  {
    findViewById(2131558690).setVisibility(View.GONE);
  }

  public void hidePopWindow(View paramView)
  {
    paramView.setVisibility(View.GONE);
  }

  public void hideSonView()
  {
    this.sonView.setAnimation(AnimationUtils.loadAnimation(this, 2131034122));
    this.sonView.setVisibility(View.GONE);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((-1 == paramInt2) && (paramInt1 == this.SHOT_REQ_CODE))
      new Thread()
      {
        public void run()
        {
          super.run();
          Bitmap localBitmap = ActColor.this.business.autoZoomInBM(BitmapUtils.ImageCenterCrop(BitmapFactory.decodeFile(ActColor.this.currentFile.getPath())), ActColor.this.color_picker_view.width, ActColor.this.color_picker_view.width);
          ActColor.this.business.saveBgType("camare");
          ActColor.this.business.saveBgPath(ActColor.this.currentFile.getPath());
          int i = BitmapUtils.getExifOrientation(ActColor.this.currentFile.getPath());
          if ((i == 90) || (i == 180) || (i == 270))
          {
            Matrix localMatrix = new Matrix();
            localMatrix.postRotate(i);
            localBitmap = Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true);
          }
          FileUtil.saveMyBitmap(ActColor.this.currentFile.getPath(), localBitmap, "/ltech/led/image");
          ActColor.this.runOnUiThread(new Runnable()
          {
            public void run()
            {
              ActColor.this.startActivityForResult(new Intent(ActColor.this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_CAMARE").putExtra("OP_CLIP_IC_FILE_PATH_KEY", ActColor.this.currentFile.getPath()), 0);
            }
          });
        }
      }
      .start();
    if ((paramInt1 == 9162) && (paramInt2 == -1))
      startActivityForResult(new Intent(this, AtClip.class).putExtra("OP_CLIP_TYPE", "OP_CLIP_TYPE_GRALLRY").putExtra("OP_CLIP_IC_URI_KEY", paramIntent.getData().toString()).putExtra("OP_CLIP_IC_FILE_PATH_KEY", this.currentFile.getPath()).putExtra("OP_CLIP_VIEW_MAX_WIDTH_KEY", this.color_picker_view.width), 0);
    while (true)
    {
      if (paramInt2 == 300)
      {
        this.color_picker_view.isRainbowBg = false;
        this.color_picker_view.setSellPhoneBm(this.business.autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), this.color_picker_view.width, this.color_picker_view.width));
      }
      if (paramInt2 == 400)
      {
        this.color_picker_view.isRainbowBg = false;
        this.color_picker_view.setSellPhoneBm(this.business.autoZoomInBM(BitmapFactory.decodeFile(this.currentFile.getPath()), this.color_picker_view.width, this.color_picker_view.width));
        this.business.saveBgType("camare");
        this.business.saveBgPath(this.currentFile.getPath());
        this.myPhoneExist = true;
        this.myPhoneIsShow = true;
      }
      return;
      if (paramInt1 != 6709)
        continue;
      handleCrop(paramInt2, paramIntent);
      this.color_picker_view.isRainbowBg = false;
    }
  }

  public void onBrightnessChange(int paramInt)
  {
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131558678:
      MyAlertDialog2 localMyAlertDialog2 = new MyAlertDialog2(this);
      localMyAlertDialog2.show();
      localMyAlertDialog2.setMyOnClickListener(new MyAlertDialog2.MyOnClickListener()
      {
        public void onClick(int paramInt)
        {
          if (paramInt == 1)
            ActColor.this.goCarmare();
          if (paramInt == 2)
          {
            File localFile = new File(Environment.getExternalStorageDirectory(), "/ltech/led/mainTitle");
            if (!localFile.exists())
              localFile.mkdirs();
            ActColor.access$302(ActColor.this, new File(localFile, System.currentTimeMillis() + ".jpg"));
            Crop.pickImage(ActColor.this);
          }
          if (paramInt == 3)
          {
            ActColor.this.color_picker_view.isRainbowBg = true;
            ActColor.this.color_picker_view.setSellPhoneBm(null);
            ActColor.this.business.saveBgType("rainbow");
          }
        }
      });
      return;
    case 2131558679:
      showSonView();
      return;
    case 2131558703:
      hideSonView();
      return;
    case 2131559063:
      this.handler.postDelayed(new Runnable()
      {
        public void run()
        {
          Crop.pickImage(ActColor.this);
          ActColor.this.bottomPopWin.dismiss();
        }
      }
      , 400L);
      return;
    case 2131559064:
    }
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActColor.this.color_picker_view.isRainbowBg = true;
        ActColor.this.color_picker_view.setSellPhoneBm(null);
        ActColor.access$502(ActColor.this, false);
        ActColor.this.bottomPopWin.dismiss();
      }
    }
    , 400L);
  }

  public void onColorChange(int paramInt)
  {
    this.sb_color_acti_1.setProgressColor(paramInt);
  }

  public void onColorChange(int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onColorChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.red = paramInt1;
    this.green = paramInt2;
    this.blue = paramInt3;
    this.bright = 255;
    this.manager.postTask(this.bussiness.getColorCmd(210, this.bright, paramInt1, paramInt2, paramInt3, 0));
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }

  protected void onEdit()
  {
    super.onEdit();
    findViewById(2131558680).setVisibility(View.VISIBLE);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onMySbBrightChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.bright = paramInt4;
    if (this.bright > 245)
      this.bright = 255;
    if (this.bright < 11)
      this.bright = 10;
    System.out.println("brightness--------" + this.bright);
    this.manager.postTask(this.bussiness.getColorCmd(209, this.bright, paramInt1, paramInt2, paramInt3, 0));
  }

  public void onMySbUp(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramInt4 > 245)
      paramInt4 = 255;
    if (paramInt4 < 11)
      paramInt4 = 10;
    System.out.println("brightness--------" + paramInt4);
    this.manager.postTask(this.bussiness.getColorCmd(209, paramInt4, paramInt1, paramInt2, paramInt3, 0));
  }

  protected void onPause()
  {
    super.onPause();
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    this.gray = (12 + 243 * paramSeekBar.getProgress() / 100);
    this.time = (1 + this.time);
    if (this.time % 5 == 0)
      this.manager.postTask(this.bussiness.getColorCmd(211, this.bright, this.red, this.green, this.blue, this.gray));
  }

  protected void onResume()
  {
    super.onResume();
  }

  public void onSeletedStart(int paramInt1, int paramInt2, int paramInt3)
  {
    this.sb_color_acti_1.setProgress(100);
  }

  protected void onStart()
  {
    super.onStart();
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  protected void onStop()
  {
    super.onStop();
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
    this.gray = (243 * paramSeekBar.getProgress() / 100);
    this.manager.postTask(this.bussiness.getColorCmd(211, this.bright, this.red, this.green, this.blue, this.gray));
  }

  public void setLightNum(int paramInt)
  {
    this.lightNum = paramInt;
    switch (paramInt)
    {
    default:
      return;
    case 1:
      if (this.business.getPikerONOff4Xml("1"))
        this.color_picker_view.showPiker(1, this.business.getPikerX4Xml("1"), this.business.getPikerY4Xml("1"));
      while (true)
      {
        this.business.savePikerONOff2Xml("1", true);
        return;
        this.color_picker_view.showPiker(1);
        this.business.savePikerX2Xml("1", this.color_picker_view.pickerX1);
        this.business.savePikerY2Xml("1", -100 + this.color_picker_view.pikerAraeHight);
      }
    case 2:
      if (this.business.getPikerONOff4Xml("2"))
        this.color_picker_view.showPiker(2, this.business.getPikerX4Xml("2"), this.business.getPikerY4Xml("2"));
      while (true)
      {
        this.business.savePikerONOff2Xml("2", true);
        return;
        this.color_picker_view.showPiker(2);
        this.business.savePikerX2Xml("2", this.color_picker_view.pickerX2);
        this.business.savePikerY2Xml("2", -100 + this.color_picker_view.pikerAraeHight);
      }
    case 3:
      if (this.business.getPikerONOff4Xml("3"))
        this.color_picker_view.showPiker(3, this.business.getPikerX4Xml("3"), this.business.getPikerY4Xml("3"));
      while (true)
      {
        this.business.savePikerONOff2Xml("3", true);
        return;
        this.color_picker_view.showPiker(3);
        this.business.savePikerX2Xml("3", this.color_picker_view.pickerX3);
        this.business.savePikerY2Xml("3", -100 + this.color_picker_view.pikerAraeHight);
      }
    case 4:
    }
    if (this.business.getPikerONOff4Xml("4"))
      this.color_picker_view.showPiker(4, this.business.getPikerX4Xml("4"), this.business.getPikerY4Xml("4"));
    while (true)
    {
      this.business.savePikerONOff2Xml("4", true);
      return;
      this.color_picker_view.showPiker(4);
      this.business.savePikerX2Xml("4", this.color_picker_view.pickerX4);
      this.business.savePikerY2Xml("4", -100 + this.color_picker_view.pikerAraeHight);
    }
  }

  public void setPikerView()
  {
    if (this.isInitPikerView)
      return;
    String str1 = SharedPreferencesUtil.queryValue(DeviceListActivity.deviceMacAddress + "lampType");
    label72: int i;
    label74: label100: String str2;
    label212: int j;
    switch (str1.hashCode())
    {
    default:
      i = -1;
      switch (i)
      {
      default:
        this.isInitPikerView = true;
        init();
        setMyTitle();
        findView();
        setListener();
        this.color_picker_view.setpActHeight(Main.sonActHeightWithouTitle);
        setLightNum(2);
        this.handler.postDelayed(new Runnable()
        {
          public void run()
          {
            if ((ActColor.this.business.getBgType().equals("camare")) && (new File(ActColor.this.business.getBgPath()).isFile()))
            {
              Bitmap localBitmap2 = ActColor.this.business.autoZoomInBM(BitmapFactory.decodeFile(ActColor.this.business.getBgPath()), ActColor.this.color_picker_view.width, ActColor.this.color_picker_view.width);
              int j = BitmapUtils.getExifOrientation(ActColor.this.business.getBgPath());
              if ((j == 90) || (j == 180) || (j == 270))
              {
                Matrix localMatrix2 = new Matrix();
                localMatrix2.postRotate(j);
                localBitmap2 = Bitmap.createBitmap(localBitmap2, 0, 0, localBitmap2.getWidth(), localBitmap2.getHeight(), localMatrix2, true);
              }
              ActColor.this.color_picker_view.setSellPhoneBm(localBitmap2);
            }
            if ((ActColor.this.business.getBgType().equals("gallery")) && (new File(ActColor.this.business.getBgPath()).isFile()))
            {
              Bitmap localBitmap1 = ActColor.this.business.autoZoomInBM(BitmapFactory.decodeFile(ActColor.this.business.getBgPath()), ActColor.this.color_picker_view.width, ActColor.this.color_picker_view.width);
              int i = BitmapUtils.getExifOrientation(ActColor.this.business.getBgPath());
              if ((i == 90) || (i == 180) || (i == 270))
              {
                Matrix localMatrix1 = new Matrix();
                localMatrix1.postRotate(i);
                localBitmap1 = Bitmap.createBitmap(localBitmap1, 0, 0, localBitmap1.getWidth(), localBitmap1.getHeight(), localMatrix1, true);
              }
              ActColor.this.color_picker_view.setSellPhoneBm(localBitmap1);
            }
            if (ActColor.this.business.getBgType().equals("rainbow"));
          }
        }
        , 10L);
        str2 = SharedPreferencesUtil.queryValue(DeviceListActivity.deviceMacAddress + "lampType");
        switch (str2.hashCode())
        {
        default:
          j = -1;
        case 112845:
        case 3498314:
        }
      case 0:
      case 1:
      case 2:
      }
    case 0:
    case 112845:
    case 3498314:
    }
    while (true)
      switch (j)
      {
      default:
        return;
      case 0:
        showRgbSeekBarStatus();
        return;
        if (!str1.equals(""))
          break label72;
        i = 0;
        break label74;
        if (!str1.equals("rgb"))
          break label72;
        i = 1;
        break label74;
        if (!str1.equals("rgbw"))
          break label72;
        i = 2;
        break label74;
        setContentView(2130968630);
        break label100;
        setContentView(2130968631);
        break label100;
        setContentView(2130968630);
        break label100;
        if (!str2.equals("rgb"))
          break label212;
        j = 0;
        continue;
        if (!str2.equals("rgbw"))
          break label212;
        j = 1;
      case 1:
      }
    showRgbwSeekBarStatus();
  }

  public void showRgbSeekBarStatus()
  {
    this.isRbgLamp = true;
    TextView localTextView1 = (TextView)findViewById(2131558686);
    TextView localTextView2 = (TextView)findViewById(2131558687);
    TextView localTextView3 = (TextView)findViewById(2131558688);
    TextView localTextView4 = (TextView)findViewById(2131558689);
    TextView localTextView5 = (TextView)findViewById(2131558693);
    TextView localTextView6 = (TextView)findViewById(2131558694);
    localTextView1.setText(2131099829);
    localTextView3.setText(2131099831);
    localTextView2.setText(2131099833);
    localTextView4.setText(2131099835);
    localTextView5.setText(2131099842);
    localTextView6.setText(2131099844);
    setEditTextRes(2131099812, getResources().getColor(R.color.oringe));
    findViewById(2131558674).setVisibility(View.VISIBLE);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActColor.this.sb_color_acti_1.setProgress(100);
        ActColor.this.findViewById(2131558676).setVisibility(View.GONE);
      }
    }
    , 100L);
    this.handler.postDelayed(new Runnable()
    {
      public void run()
      {
        ActColor.this.findViewById(2131558675).setVisibility(View.GONE);
      }
    }
    , 0L);
  }

  public void showRgbwSeekBarStatus()
  {
    setEditImageText(2131099812, getResources().getColor(R.color.oringe));
  }

  public void showSonView()
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.colors.ActColor
 * JD-Core Version:    0.6.0
 */