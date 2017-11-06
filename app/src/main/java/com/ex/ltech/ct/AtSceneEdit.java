package com.ex.ltech.ct;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.ex.ltech.led.vo.CtSceneVo;

import io.xlink.wifi.js.manage.DeviceManage;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.listener.SendPipeListener;

public class AtSceneEdit extends MyBaseActivity
{
  int b;
  int brt;
  private int brtType = 209;
  private ColorBussiness bussiness;
  int c;
  private CmdDateBussiness cmdDateBussiness;
  String ctSceneName;
  int ctScenePosi;
  int g;
  int r;
  SendPipeListener sendPipeListener = new SendPipeListener()
  {
    public void onSendLocalPipeData(XDevice paramXDevice, int paramInt1, int paramInt2)
    {
    }
  };
  CtSceneVo vo;
  int w;
  private int warmWhiteType = 210;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 1000)
    {
      setResult(1000, new Intent());
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968672);
    this.bussiness = new ColorBussiness(this);
    this.cmdDateBussiness = new CmdDateBussiness("0000");
    this.bussiness.loadCtSceneVos();
    this.ctScenePosi = getIntent().getIntExtra("ctScenePosi", -1);
    this.ctSceneName = getIntent().getStringExtra("ctSceneName");
    this.vo = ((CtSceneVo)this.bussiness.vos.get(this.ctScenePosi));
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleText(this.ctSceneName);
    setEditTextRes(R.string.finish, getResources().getColor(R.color.color1));
    SeekBar localSeekBar = (SeekBar)findViewById(R.id.sb);
    localSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
      {
        if (paramSeekBar.getProgress() > 4)
        {
          CtSceneVo localCtSceneVo = AtSceneEdit.this.vo;
          AtSceneEdit localAtSceneEdit = AtSceneEdit.this;
          int i = 255 * paramSeekBar.getProgress() / 100;
          localAtSceneEdit.brt = i;
          localCtSceneVo.setBrt(i);
          XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
          DeviceManage.getInstance();
          localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtSceneEdit.this.cmdDateBussiness.getCtColorCmd(AtSceneEdit.this.brtType, AtSceneEdit.this.brt, AtSceneEdit.this.c, AtSceneEdit.this.w), AtSceneEdit.this.sendPipeListener);
          System.out.println(AtSceneEdit.this.brt);
        }
      }

      public void onStartTrackingTouch(SeekBar paramSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramSeekBar)
      {
      }
    });
    SimpleColorPickerView localSimpleColorPickerView = (SimpleColorPickerView)findViewById(R.id.color);
    new Handler().postDelayed(new Runnable(localSeekBar, localSimpleColorPickerView)
    {
      public void run()
      {
        this.val$seekBar.setProgress(100 * AtSceneEdit.this.vo.getBrt() / 255);
        this.val$simpleColorPickerView.setPikerXy(100 * AtSceneEdit.this.vo.getW() / 255);
      }
    }
    , 1000L);
    localSimpleColorPickerView.setListener(new SimpleColorPickerView.OnColorChangedListener()
    {
      public void onPikerTouchUp(int paramInt)
      {
      }

      public void onPikerXYChange(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
      {
        AtSceneEdit.this.r = paramInt2;
        AtSceneEdit.this.g = paramInt3;
        AtSceneEdit.this.b = paramInt4;
        if (AtSceneEdit.this.r == 255)
          AtSceneEdit.this.c = (255 - AtSceneEdit.this.b / 2);
        if (AtSceneEdit.this.b > 247)
          AtSceneEdit.this.c = (AtSceneEdit.this.r / 2);
      }

      public void onProgressPercent(float paramFloat)
      {
        AtSceneEdit.this.w = (int)(255.0F * paramFloat);
        if (AtSceneEdit.this.w < 1)
          AtSceneEdit.this.w = 0;
        if (AtSceneEdit.this.w > 254)
          AtSceneEdit.this.w = 255;
        AtSceneEdit.this.c = (255 - AtSceneEdit.this.w);
        AtSceneEdit.this.vo.setC(AtSceneEdit.this.c);
        AtSceneEdit.this.vo.setW(AtSceneEdit.this.w);
        System.out.println(" c = " + AtSceneEdit.this.c + " w = " + AtSceneEdit.this.w);
        XlinkAgent localXlinkAgent = XlinkAgent.getInstance();
        DeviceManage.getInstance();
        localXlinkAgent.sendPipeData(DeviceManage.getxDevice(), AtSceneEdit.this.cmdDateBussiness.getCtColorCmd(AtSceneEdit.this.warmWhiteType, AtSceneEdit.this.brt, AtSceneEdit.this.c, AtSceneEdit.this.w), AtSceneEdit.this.sendPipeListener);
      }
    });
    localSimpleColorPickerView.setViewBgRes(2130903149, true);
  }

  protected void onEdit()
  {
    super.onEdit();
    this.bussiness.saveCtSceneVo(this.vo, this.ctScenePosi);
    Intent localIntent = new Intent(this, ActCtSceneLast.class);
    localIntent.putExtra("ctScenePosi", this.ctScenePosi);
    localIntent.putExtra("sceneDataStr", this.bussiness.gs.toJson(this.vo));
    localIntent.putExtra("ctSceneName", this.ctSceneName);
    localIntent.putExtra("cValue", this.c);
    localIntent.putExtra("wValue", this.w);
    localIntent.putExtra("brtValue", this.brt);
    startActivityForResult(localIntent, 0);
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
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.ct.AtSceneEdit
 * JD-Core Version:    0.6.0
 */