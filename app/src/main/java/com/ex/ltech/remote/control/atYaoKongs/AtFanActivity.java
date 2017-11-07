package com.ex.ltech.remote.control.atYaoKongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.yaokong.AtYkBrandActivity;
import com.indris.material.RippleView;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceFANS;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
import java.io.PrintStream;

public class AtFanActivity extends YkAt
  implements View.OnLongClickListener
{

  @Bind({2131558983})
  RippleView fanSpeed;

  @Bind({2131558982})
  RippleView fanType;

  @Bind({2131558978})
  TextView hig;
  boolean isSavedYk;
  int key;

  @Bind({2131558979})
  TextView low;
  private ETDeviceFANS mDevice = null;

  @Bind({2131558977})
  TextView mid;

  @Bind({2131558822})
  RippleView power;

  @Bind({2131558976})
  RippleView queit;

  @Bind({2131558984})
  RippleView shake;

  @Bind({2131558981})
  RippleView time;

  private void init()
  {
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    this.mDevice = ((ETDeviceFANS)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
    this.mDevice.Load(ETDB.getInstance(this));
  }

  private void sendMyCmd(int paramInt)
  {
    try
    {
      sendCmd(this.mDevice.GetKeyValue(paramInt));
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void fanLeftRight(View paramView)
  {
    vibrate();
    sendMyCmd(32773);
  }

  public void fanOn(View paramView)
  {
    vibrate();
    sendMyCmd(32769);
  }

  public void fanQueit(View paramView)
  {
    vibrate();
    sendMyCmd(32801);
  }

  public void fanSpeed(View paramView)
  {
    vibrate();
    sendMyCmd(32771);
  }

  public void fanTime(View paramView)
  {
    vibrate();
    sendMyCmd(32777);
  }

  public void fanType(View paramView)
  {
    vibrate();
    sendMyCmd(32775);
  }

  public void hight(View paramView)
  {
    vibrate();
    sendMyCmd(32811);
  }

  public void low(View paramView)
  {
    vibrate();
    sendMyCmd(32807);
  }

  public void mid(View paramView)
  {
    vibrate();
    sendMyCmd(32809);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == this.saveYkOk)
    {
      if (this.mDevice == null)
      {
        ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
        localETGroup.Load(ETDB.getInstance(this));
        this.mDevice = ((ETDeviceFANS)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
        this.mDevice.Load(ETDB.getInstance(this));
      }
      this.mDevice.Delete(ETDB.getInstance(getApplicationContext()));
      setResult(this.saveYkOk);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968689);
    ButterKnife.bind(this);
    init();
    setTitleView();
    this.power.setOnLongClickListener(this);
    this.hig.setOnLongClickListener(this);
    this.mid.setOnLongClickListener(this);
    this.low.setOnLongClickListener(this);
    this.fanType.setOnLongClickListener(this);
    this.time.setOnLongClickListener(this);
    this.shake.setOnLongClickListener(this);
    this.fanSpeed.setOnLongClickListener(this);
    this.queit.setOnLongClickListener(this);
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    localIntent.putExtra("type", 32768);
    startActivityForResult(localIntent, 0);
  }

  protected void onLearn()
  {
    super.onLearn();
  }

  public boolean onLongClick(View paramView)
  {
    switch (paramView.getId())
    {
    default:
    case 2131558822:
    case 2131558978:
    case 2131558977:
    case 2131558983:
    case 2131558979:
    case 2131558982:
    case 2131558981:
    case 2131558984:
    case 2131558976:
    }
    while (true)
    {
      if (!this.isSavedYk)
        this.isSavedYk = false;
      showLearnDialog();
      return false;
      this.key = 32769;
      continue;
      this.key = 32811;
      continue;
      this.key = 32809;
      continue;
      this.key = 32771;
      continue;
      this.key = 32807;
      continue;
      this.key = 32775;
      continue;
      this.key = 32777;
      continue;
      this.key = 32773;
      continue;
      this.key = 32801;
    }
  }

  protected void onRecHongWaiCode(byte[] paramArrayOfByte)
  {
    super.onRecHongWaiCode(paramArrayOfByte);
    if (this.key == -1)
      return;
    try
    {
      ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
      localETGroup.Load(ETDB.getInstance(this));
      this.mDevice = ((ETDeviceFANS)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
      this.mDevice.Load(ETDB.getInstance(this));
      ETKey localETKey = this.mDevice.GetKeyByValue(this.key);
      if (localETKey != null)
      {
        localETKey.SetState(1);
        localETKey.SetValue(paramArrayOfByte);
        localETKey.Update(ETDB.getInstance(this));
      }
      System.out.println("hihihihihi" + StringUtils.btye2Str(paramArrayOfByte));
      System.out.println("hihihihihi" + StringUtils.btye2Str(this.mDevice.GetKeyValue(32769)));
      this.key = -1;
      toastL(2131100127);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.yk_back);
    setTiTleText(this.mDevice.GetName());
    setEditImageRes(2130903831);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtFanActivity
 * JD-Core Version:    0.6.0
 */