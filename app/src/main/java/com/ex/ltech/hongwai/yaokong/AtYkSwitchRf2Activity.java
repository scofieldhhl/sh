package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.NonIrDeviceAt;
import com.ex.ltech.hongwai.ScreenUtil;
import com.ex.ltech.hongwai.atRcs.AtRcSet;
import com.ex.ltech.hongwai.view.EditDialog;
import com.ex.ltech.hongwai.view.EditDialog.OnListener;
import com.ex.ltech.hongwai.view.ImageTextBigButton;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.hongwai.vo.NonIrDevice;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.connetion.CmdDateBussiness;
import java.util.ArrayList;

public class AtYkSwitchRf2Activity extends NonIrDeviceAt
  implements View.OnTouchListener
{

  @Bind({2131559038})
  ImageTextBigButton btOff1;

  @Bind({2131559040})
  ImageTextBigButton btOff2;

  @Bind({2131559037})
  ImageTextBigButton btOn1;

  @Bind({2131559039})
  ImageTextBigButton btOn2;
  int existRcPosi;

  @Bind({2131559035})
  RelativeLayout mBackground;

  @Bind({2131558666})
  TextView mTextViewKeyName1;

  @Bind({2131558670})
  TextView mTextViewKeyName2;
  private MyRcDevices rcDevices;

  private void fullScreen()
  {
    ScreenUtil.fullScreen(this);
    this.mBackground.setPadding(this.mBackground.getPaddingLeft(), this.mBackground.getPaddingTop() + ScreenUtil.getStatusBarHeight(this), this.mBackground.getPaddingRight(), this.mBackground.getPaddingBottom());
  }

  private void setView()
  {
    setTitleView();
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitchName1 != null)
      this.mTextViewKeyName1.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitchName1);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitchName2 != null)
      this.mTextViewKeyName2.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitchName2);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 100000)
    {
      MyRcDevice localMyRcDevice = (MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi);
      String str = paramIntent.getStringExtra("RC_NAME_KEY");
      localMyRcDevice.mName = str;
      setTiTleText(str);
    }
    do
      return;
    while (paramInt2 != 90000);
    setResult(110000);
    finish();
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
      return;
    case 2131558666:
      new EditDialog(this).setTitle(getResources().getString(2131100051)).setEditContent(this.mTextViewKeyName1.getText().toString()).setListener(new EditDialog.OnListener()
      {
        public void onCancelBtnClick()
        {
        }

        public void onOkBtnClick(String paramString)
        {
          ((MyRcDevice)AtYkSwitchRf2Activity.this.rcDevices.myRcDevices.get(AtYkSwitchRf2Activity.this.existRcPosi)).nonIrDevice.irPanelSwitchName1 = paramString.trim();
          AtYkSwitchRf2Activity.this.setView();
        }
      }).show();
      return;
    case 2131558670:
    }
    new EditDialog(this).setTitle(getResources().getString(2131100051)).setEditContent(this.mTextViewKeyName2.getText().toString()).setListener(new EditDialog.OnListener()
    {
      public void onCancelBtnClick()
      {
      }

      public void onOkBtnClick(String paramString)
      {
        ((MyRcDevice)AtYkSwitchRf2Activity.this.rcDevices.myRcDevices.get(AtYkSwitchRf2Activity.this.existRcPosi)).nonIrDevice.irPanelSwitchName2 = paramString.trim();
        AtYkSwitchRf2Activity.this.setView();
      }
    }).show();
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968706);
    ButterKnife.bind(this);
    fullScreen();
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    setView();
    this.btOn1.setOnTouchListener(this);
    this.btOff1.setOnTouchListener(this);
    this.btOn2.setOnTouchListener(this);
    this.btOff2.setOnTouchListener(this);
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

  public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
  {
    switch (paramMotionEvent.getAction())
    {
    case 2:
    default:
      return false;
    case 0:
      ((ImageTextBigButton)paramView).setBtnRes(2130903395);
      return false;
    case 1:
    case 3:
    }
    ((ImageTextBigButton)paramView).setBtnRes(2130903394);
    switch (paramView.getId())
    {
    default:
      return false;
    case 2131559037:
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch1 = true;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 226), 3);
      return false;
    case 2131559038:
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch1 = false;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 194), 3);
      return false;
    case 2131559039:
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch2 = true;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 227), 3);
      return false;
    case 2131559040:
    }
    ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch2 = false;
    cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 195), 3);
    return false;
  }

  @OnClick({2131559037, 2131559038, 2131559039, 2131559040})
  public void onViewClicked(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131559037:
    case 2131559038:
    case 2131559039:
    case 2131559040:
    }
    while (true)
    {
      setView();
      return;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch1 = true;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 226), 3);
      continue;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch1 = false;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 194), 3);
      continue;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch2 = true;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 227), 3);
      continue;
      ((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch2 = false;
      cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 195), 3);
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setTiTleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
    setTiTleTextColor(getResources().getColor(2131492997));
    setMenuBackgroundRes(2130903076);
    setEditImageRes(2130903385);
    setBgAlpha();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchRf2Activity
 * JD-Core Version:    0.6.0
 */