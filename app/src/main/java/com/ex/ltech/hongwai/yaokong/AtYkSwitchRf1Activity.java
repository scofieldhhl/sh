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

public class AtYkSwitchRf1Activity extends NonIrDeviceAt
  implements View.OnTouchListener
{

  @Bind({2131558847})
  ImageTextBigButton btOff;

  @Bind({2131558857})
  ImageTextBigButton btOn;
  int existRcPosi;

  @Bind({2131559035})
  RelativeLayout mBackground;

  @Bind({2131558662})
  TextView mTextViewKeyName;
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
      this.mTextViewKeyName.setText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitchName1);
    if (((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.irPanelSwitch1);
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
    case 2131558662:
    }
    new EditDialog(this).setTitle(getResources().getString(2131100051)).setEditContent(this.mTextViewKeyName.getText().toString()).setListener(new EditDialog.OnListener()
    {
      public void onCancelBtnClick()
      {
      }

      public void onOkBtnClick(String paramString)
      {
        ((MyRcDevice)AtYkSwitchRf1Activity.this.rcDevices.myRcDevices.get(AtYkSwitchRf1Activity.this.existRcPosi)).nonIrDevice.irPanelSwitchName1 = paramString.trim();
        AtYkSwitchRf1Activity.this.setView();
      }
    }).show();
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968705);
    ButterKnife.bind(this);
    fullScreen();
    this.existRcPosi = getIntent().getIntExtra("OP_AT_POSI_KEY", -1);
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(getApplicationContext()).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    setView();
    this.btOn.setOnTouchListener(this);
    this.btOff.setOnTouchListener(this);
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
    case 0:
    case 1:
    case 3:
    }
    while (true)
    {
      return false;
      ((ImageTextBigButton)paramView).setBtnRes(2130903395);
      continue;
      ((ImageTextBigButton)paramView).setBtnRes(2130903394);
      switch (paramView.getId())
      {
      default:
        break;
      case 2131558847:
        cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 194), 3);
        break;
      case 2131558857:
        cracySend(this.cmd.onOffK1RFAndK2RF(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).nonIrDevice.nonIrDeviceId, 226), 3);
      }
    }
  }

  @OnClick({2131558857, 2131558847})
  public void onViewClicked(View paramView)
  {
  }

  public void setTitleView()
  {
    setViewTitle();
    setTiTleText(((MyRcDevice)this.rcDevices.myRcDevices.get(this.existRcPosi)).mName);
    setTiTleTextColor(getResources().getColor(R.color.white));
    setMenuBackgroundRes(2130903076);
    setEditImageRes(2130903385);
    setBgAlpha();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchRf1Activity
 * JD-Core Version:    0.6.0
 */