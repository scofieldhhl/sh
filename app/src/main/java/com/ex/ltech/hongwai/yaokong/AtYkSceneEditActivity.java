package com.ex.ltech.hongwai.yaokong;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.NonIrDeviceAt;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.SimpleColorPickerView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AtYkSceneEditActivity extends NonIrDeviceAt
{
  private Ct1ScenesVo ct1ScenesVo;
  int existRcPosi;
  int irCtScenePosi;

  @Bind({2131558831})
  ImageView mImageviewAdd;

  @Bind({2131558829})
  ImageView mImageviewBright;

  @Bind({R.id.color})
  SimpleColorPickerView mSimpleColorPickerView;
  private MyRcDevices rcDevices;

  @Bind({R.id.sb})
  SeekBar sb;

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setEditImageText(2131100358, getResources().getColor(2131492964));
    setTiTleTextRes(2131100037);
  }

  private void setView()
  {
    this.mSimpleColorPickerView.setPikerXY(((Ct1SceneVo)this.ct1ScenesVo.ct1SceneVos.get(this.irCtScenePosi)).irCt1X, ((Ct1SceneVo)this.ct1ScenesVo.ct1SceneVos.get(this.irCtScenePosi)).irCt1Y);
    this.mSimpleColorPickerView.setOnXYChangedListener(new SimpleColorPickerView.OnXYChangedListener()
    {
      public void onPikerXYChange(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
      {
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1X = paramInt1;
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Y = paramInt2;
        int i = (int)(255.0F * paramFloat);
        if (i < 1)
          i = 0;
        if (i > 254)
          i = 255;
        int j = 255 - i;
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1C = j;
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1W = i;
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Onoff = true;
        AtYkSceneEditActivity localAtYkSceneEditActivity = AtYkSceneEditActivity.this;
        byte[] arrayOfByte = AtYkSceneEditActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYkSceneEditActivity.this.rcDevices.myRcDevices.get(AtYkSceneEditActivity.this.existRcPosi)).nonIrDevice.nonIrDeviceId, 3, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Onoff, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Brt, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1C, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1W);
        if (paramBoolean);
        for (int k = 1; ; k = 3)
        {
          localAtYkSceneEditActivity.fillterSend(arrayOfByte, k);
          return;
        }
      }
    });
    this.sb.setProgress(((Ct1SceneVo)this.ct1ScenesVo.ct1SceneVos.get(this.irCtScenePosi)).irCt1BtrProgrees);
    this.sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
      {
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1BtrProgrees = paramSeekBar.getProgress();
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Brt = (255 * paramSeekBar.getProgress() / 100);
        ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Onoff = true;
        AtYkSceneEditActivity.this.fillterSend(AtYkSceneEditActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYkSceneEditActivity.this.rcDevices.myRcDevices.get(AtYkSceneEditActivity.this.existRcPosi)).nonIrDevice.nonIrDeviceId, 2, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Onoff, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1Brt, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1C, ((Ct1SceneVo)AtYkSceneEditActivity.this.ct1ScenesVo.ct1SceneVos.get(AtYkSceneEditActivity.this.irCtScenePosi)).irCt1W), 3);
      }

      public void onStartTrackingTouch(SeekBar paramSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramSeekBar)
      {
        onProgressChanged(paramSeekBar, 0, true);
      }
    });
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968672);
    ButterKnife.bind(this);
    setTitleView();
    this.mSimpleColorPickerView.setViewBgRes(2130903079, false);
    this.mImageviewBright.setBackgroundResource(2130903361);
    this.mImageviewAdd.setVisibility(View.GONE);
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.irCtScenePosi = getIntent().getIntExtra("OP_IR_CT_SCENE_POSI_KEY", -1);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    this.ct1ScenesVo = ((Ct1ScenesVo)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, Ct1ScenesVo.class));
    if (this.ct1ScenesVo == null)
    {
      this.ct1ScenesVo = new Ct1ScenesVo();
      this.ct1ScenesVo.initCt1SceneVos();
    }
    setView();
  }

  protected void onEdit()
  {
    super.onEdit();
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.ct1ScenesVo);
    finish();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSceneEditActivity
 * JD-Core Version:    0.6.0
 */