package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.ex.ltech.MyDb;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.hongwai.vo.MyRcDevice;
import com.ex.ltech.hongwai.vo.MyRcDevices;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChanged;
import java.io.PrintStream;
import java.util.ArrayList;

public class AtYkSwitchSelectActivity extends MyBaseActivity
{
  private boolean bSelect1 = false;
  private boolean bSelect2 = false;

  @Bind({2131558660})
  ToggleButton mActLightSwitch;

  @Bind({2131558663})
  ToggleButton mActSwitch;

  @Bind({2131558667})
  ToggleButton mActSwitch1;

  @Bind({2131558671})
  ToggleButton mActSwitch2;

  @Bind({2131558665})
  ImageView mImageViewSelect1;

  @Bind({2131558669})
  ImageView mImageViewSelect2;

  @Bind({2131558657})
  RelativeLayout mLayoutLightSelect;

  @Bind({2131558661})
  RelativeLayout mLayoutSelect;

  @Bind({2131558664})
  RelativeLayout mLayoutSelect1;

  @Bind({2131558668})
  RelativeLayout mLayoutSelect2;
  MyRcDevices rcDevices;
  InnerRcVo sceneInnerRcVo;
  private int type = 10;
  boolean wayOneOnoff;
  boolean wayTwoOnoff;

  private void initViewByType(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 10:
      this.bSelect1 = true;
      this.mLayoutSelect.setVisibility(0);
      setTiTleTextRes(2131100423);
      return;
    case 11:
    }
    setTiTleTextRes(2131100424);
    this.mLayoutSelect1.setVisibility(0);
    this.mLayoutSelect2.setVisibility(0);
  }

  private void setSelectBackground(ImageView paramImageView, Boolean paramBoolean)
  {
    if (paramBoolean.booleanValue());
    for (int i = 2130903786; ; i = 2130903595)
    {
      paramImageView.setBackgroundResource(i);
      return;
    }
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setBgWhite();
    setEditTextRes(2131100416, getResources().getColor(2131492897));
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968629);
    ButterKnife.bind(this);
    setTitleView();
    this.type = getIntent().getExtras().getInt("RC_TYPE_KEY", 10);
    initViewByType(this.type);
    this.mActSwitch.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        AtYkSwitchSelectActivity.this.wayOneOnoff = paramBoolean;
      }
    });
    this.mActSwitch1.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        AtYkSwitchSelectActivity.this.wayOneOnoff = paramBoolean;
      }
    });
    this.mActSwitch2.setOnToggleChanged(new ToggleButton.OnToggleChanged()
    {
      public void onToggle(boolean paramBoolean)
      {
        AtYkSwitchSelectActivity.this.wayTwoOnoff = paramBoolean;
      }
    });
    this.rcDevices = ((MyRcDevices)MyDb.getInstance(this).getBean(DeviceListActivity.deviceMacAddress, MyRcDevices.class));
    if (this.rcDevices == null)
      this.rcDevices = new MyRcDevices();
    this.sceneInnerRcVo = new InnerRcVo();
    this.sceneInnerRcVo.nonIrDevice = ((MyRcDevice)this.rcDevices.myRcDevices.get(getIntent().getExtras().getInt("OP_RC_POSI_KEY"))).nonIrDevice;
    System.out.println("");
  }

  protected void onEdit()
  {
    super.onEdit();
    StringBuffer localStringBuffer;
    label167: label230: Intent localIntent;
    switch (this.type)
    {
    default:
      this.sceneInnerRcVo.setmSaveRcListPosi(getIntent().getExtras().getInt("OP_RC_POSI_KEY"));
      this.sceneInnerRcVo.setmType(this.type);
      this.sceneInnerRcVo.nonIrDevice.mType = this.type;
      this.sceneInnerRcVo.nonIrDevice.irPanelSwitch1 = this.wayOneOnoff;
      this.sceneInnerRcVo.nonIrDevice.irPanelSwitch2 = this.wayTwoOnoff;
      localStringBuffer = new StringBuffer();
      if (this.bSelect1)
      {
        if (!this.wayOneOnoff)
          break;
        localStringBuffer.append(getString(2131100509) + " " + getString(2131100232));
      }
      else
      {
        if (this.bSelect2)
        {
          localStringBuffer.append(" ");
          if (!this.wayTwoOnoff)
            break label452;
          localStringBuffer.append(getString(2131100510) + " " + getString(2131100232));
        }
        this.sceneInnerRcVo.setStatus(localStringBuffer.toString());
        localIntent = new Intent();
        localIntent.putExtra(InnerRcVo.class.getName(), this.sceneInnerRcVo);
        localIntent.putExtra("WayOneOnoffKey", this.wayOneOnoff);
        localIntent.putExtra("WayTwoOnoffKey", this.wayTwoOnoff);
        if (!getIntent().getExtras().getString("OP_AT_TYPE_KEY", "").equals("OP_AT_TYPE_EXIST"))
          break label496;
        setResult(30000, localIntent);
      }
    case 10:
    case 11:
    }
    while (true)
    {
      finish();
      return;
      this.sceneInnerRcVo.setName(getString(2131100423));
      this.sceneInnerRcVo.setTypeStr(getString(2131100423));
      break;
      if ((!this.bSelect1) && (!this.bSelect2))
      {
        Toast.makeText(this, 2131100385, 0).show();
        return;
      }
      this.sceneInnerRcVo.setName(getString(2131100424));
      this.sceneInnerRcVo.setTypeStr(getString(2131100424));
      break;
      localStringBuffer.append(getString(2131100509) + " " + getString(2131100226));
      break label167;
      label452: localStringBuffer.append(getString(2131100510) + " " + getString(2131100226));
      break label230;
      label496: setResult(220000, localIntent);
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  @OnClick({2131558665})
  public void select1OnClick()
  {
    if (!this.bSelect1);
    for (boolean bool = true; ; bool = false)
    {
      this.bSelect1 = bool;
      setSelectBackground(this.mImageViewSelect1, Boolean.valueOf(this.bSelect1));
      this.sceneInnerRcVo.nonIrDevice.irPanelSwitchSelected1 = this.bSelect1;
      return;
    }
  }

  @OnClick({2131558669})
  public void select2OnClick()
  {
    if (!this.bSelect2);
    for (boolean bool = true; ; bool = false)
    {
      this.bSelect2 = bool;
      setSelectBackground(this.mImageViewSelect2, Boolean.valueOf(this.bSelect2));
      this.sceneInnerRcVo.nonIrDevice.irPanelSwitchSelected2 = this.bSelect2;
      return;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkSwitchSelectActivity
 * JD-Core Version:    0.6.0
 */