package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.NonIrDeviceAt;
import com.ex.ltech.hongwai.atRcs.AtRcSet;
import com.ex.ltech.hongwai.view.BrightEditDialog;
import com.ex.ltech.hongwai.view.SceneModeDialog;
import com.ex.ltech.hongwai.vo.Ct1SceneVo;
import com.ex.ltech.hongwai.vo.Ct1ScenesVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.SimpleColorPickerView;
import com.zhy.android.percent.support.PercentRelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

public class AtYkLampActivity extends NonIrDeviceAt
{
  private Ct1ScenesVo ct1ScenesVo;
  int existRcPosi;

  @Bind({2131558647})
  PercentRelativeLayout layoutOption;

  @Bind({2131558654})
  PercentRelativeLayout mGrayLayer;

  @Bind({R.id.color})
  SimpleColorPickerView mSimpleColorPickerView;
  private MyRcDevices rcDevices;

  @Bind({2131558651})
  LinearLayout rlBrt;

  @Bind({R.id.sb})
  SeekBar sb;

  @Bind({2131558653})
  TextView tvBrtPrecent;

  private void initCt1SceneVos()
  {
    this.ct1ScenesVo = ((Ct1ScenesVo)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, Ct1ScenesVo.class));
    if (this.ct1ScenesVo == null)
    {
      this.ct1ScenesVo = new Ct1ScenesVo();
      this.ct1ScenesVo.initCt1SceneVos();
    }
  }

  private void onBrightProgressChanged(int paramInt, boolean paramBoolean)
  {
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1BtrProgrees = paramInt;
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt = (paramInt * 255 / 100);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt < 5)
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt = 5;
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff = true;
    byte[] arrayOfByte = this.cmd.controlIrCtLight(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 2, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1C, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1W);
    if (paramBoolean);
    for (int i = 1; ; i = 3)
    {
      fillterSend(arrayOfByte, i);
      this.tvBrtPrecent.setText(paramInt + "%");
      if (paramInt < 2)
        this.tvBrtPrecent.setText("2%");
      return;
    }
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
    setEditImageRes(2130903591);
  }

  private void setView()
  {
    setTitleView();
    this.tvBrtPrecent.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1BtrProgrees + "%");
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1BtrProgrees < 2)
      this.tvBrtPrecent.setText("2%");
    this.sb.setOnSeekBarChangeListener(null);
    this.sb.setProgress(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1BtrProgrees);
    this.sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
    {
      public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
      {
        AtYkLampActivity.this.onBrightProgressChanged(paramInt, false);
      }

      public void onStartTrackingTouch(SeekBar paramSeekBar)
      {
      }

      public void onStopTrackingTouch(SeekBar paramSeekBar)
      {
        AtYkLampActivity.this.onBrightProgressChanged(paramSeekBar.getProgress(), true);
      }
    });
    this.mSimpleColorPickerView.setPikerXY(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1X, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1X);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff)
    {
      this.mGrayLayer.setVisibility(View.GONE);
      return;
    }
    this.mGrayLayer.setVisibility(View.VISIBLE);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 12)
    {
      setResult(12);
      finish();
      return;
    }
    if (paramInt2 == 100000)
    {
      MyRcDevice localMyRcDevice = (MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi);
      String str = paramIntent.getStringExtra("RC_NAME_KEY");
      localMyRcDevice.mName = str;
      setTiTleText(str);
      return;
    }
    if (paramInt2 == 90000)
    {
      setResult(110000);
      finish();
      return;
    }
    initCt1SceneVos();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131558651:
    case R.id.sb:
    case 2131558653:
    case 2131558654:
    default:
      return;
    case 2131558648:
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff = false;
      cracySend(this.cmd.controlIrCtLight(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 1, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1C, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1W), 3);
      setView();
      return;
    case 2131558649:
      new BrightEditDialog(this).setBrightProgress(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1BtrProgrees).setOnListener(new BrightEditDialog.OnListener()
      {
        public void onBrightProgressChanged(int paramInt, boolean paramBoolean)
        {
          ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1BtrProgrees = paramInt;
          ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Brt = (paramInt * 255 / 100);
          if (((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Brt < 5)
            ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Brt = 5;
          ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Onoff = true;
          AtYkLampActivity localAtYkLampActivity = AtYkLampActivity.this;
          byte[] arrayOfByte = AtYkLampActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.nonIrDeviceId, 2, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Onoff, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1C, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1W);
          if (paramBoolean);
          for (int i = 1; ; i = 3)
          {
            localAtYkLampActivity.fillterSend(arrayOfByte, i);
            return;
          }
        }
      }).show();
      return;
    case 2131558650:
      new SceneModeDialog(this, this.ct1ScenesVo.ct1SceneVos).setOnListener(new SceneModeDialog.OnListener()
      {
        public void onItemClick(int paramInt)
        {
          AtYkLampActivity.this.cracySend(AtYkLampActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.nonIrDeviceId, 2, ((Ct1SceneVo)AtYkLampActivity.this.ct1ScenesVo.ct1SceneVos.get(paramInt)).irCt1Onoff, ((Ct1SceneVo)AtYkLampActivity.this.ct1ScenesVo.ct1SceneVos.get(paramInt)).irCt1Brt, ((Ct1SceneVo)AtYkLampActivity.this.ct1ScenesVo.ct1SceneVos.get(paramInt)).irCt1C, ((Ct1SceneVo)AtYkLampActivity.this.ct1ScenesVo.ct1SceneVos.get(paramInt)).irCt1W), 3);
        }

        public void onItemEditClick(int paramInt)
        {
          Intent localIntent = new Intent(AtYkLampActivity.this, AtYkSceneEditActivity.class);
          localIntent.putExtra("OP_AT_POSI_KEY", AtYkLampActivity.this.existRcPosi);
          localIntent.putExtra("OP_IR_CT_SCENE_POSI_KEY", paramInt);
          AtYkLampActivity.this.startActivityForResult(localIntent, 0);
        }
      }).show();
      return;
    case 2131558655:
    }
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff = true;
    cracySend(this.cmd.controlIrCtLight(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 1, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Onoff, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1Brt, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1C, ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irCt1W), 3);
    setView();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968627);
    ButterKnife.bind(this);
    this.mSimpleColorPickerView.setViewBgFullScreen(2130903079, false);
    this.mSimpleColorPickerView.setOnXYChangedListener(new SimpleColorPickerView.OnXYChangedListener()
    {
      public void onPikerXYChange(int paramInt1, int paramInt2, float paramFloat, boolean paramBoolean)
      {
        ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1X = paramInt1;
        ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Y = paramInt2;
        int i = (int)(255.0F * paramFloat);
        if (i < 1)
          i = 0;
        if (i > 254)
          i = 255;
        int j = 255 - i;
        System.out.println("c : " + j);
        System.out.println("w : " + i);
        ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1C = j;
        ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1W = i;
        ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Onoff = true;
        AtYkLampActivity localAtYkLampActivity = AtYkLampActivity.this;
        byte[] arrayOfByte = AtYkLampActivity.this.cmd.controlIrCtLight(((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.nonIrDeviceId, 3, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Onoff, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1Brt, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1C, ((MyRcDevice)AtYkLampActivity.this.rcDevices.myRcDevices.get(AtYkLampActivity.this.existRcPosi)).nonIrDevice.irCt1W);
        if (paramBoolean);
        for (int k = 1; ; k = 3)
        {
          localAtYkLampActivity.fillterSend(arrayOfByte, k);
          if (!paramBoolean)
            break;
          AtYkLampActivity.this.sb.setVisibility(View.VISIBLE);
          AtYkLampActivity.this.layoutOption.setVisibility(View.VISIBLE);
          AtYkLampActivity.this.rlBrt.setVisibility(View.VISIBLE);
          return;
        }
        AtYkLampActivity.this.sb.setVisibility(View.GONE);
        AtYkLampActivity.this.layoutOption.setVisibility(View.GONE);
        AtYkLampActivity.this.rlBrt.setVisibility(View.GONE);
      }
    });
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    initCt1SceneVos();
    setView();
  }

  protected void onEdit()
  {
    super.onEdit();
    startActivityForResult(new Intent(this, AtRcSet.class).putExtra("OP_AT_POSI_KEY", this.existRcPosi), 0);
  }

  protected void onMenu()
  {
    super.onMenu();
    MyDb.getInstance(getApplicationContext()).putBean(DeviceListActivity.deviceMacAddress, this.rcDevices);
    setResult(110000);
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkLampActivity
 * JD-Core Version:    0.6.0
 */