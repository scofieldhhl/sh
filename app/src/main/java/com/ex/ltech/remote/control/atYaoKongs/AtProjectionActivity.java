package com.ex.ltech.remote.control.atYaoKongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.yaokong.AtYkBrandActivity;
import com.indris.material.RippleView;
import et.song.db.ETDB;
import et.song.etclass.ETDevicePJT;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;

public class AtProjectionActivity extends YkAt
  implements View.OnLongClickListener
{

  @Bind({2131558858})
  RippleView back;

  @Bind({2131558969})
  ImageView down;
  int key;

  @Bind({2131558458})
  ImageView left;
  private ETDevicePJT mDevice = null;

  @Bind({2131558989})
  RippleView menu;

  @Bind({2131558873})
  RippleView off;

  @Bind({2131558968})
  ImageView ok;

  @Bind({2131558874})
  RippleView on;

  @Bind({2131558459})
  ImageView right;

  @Bind({2131558967})
  RelativeLayout rlAtProjection1;

  @Bind({2131558986})
  TextView tvV;

  @Bind({2131558991})
  TextView tvZoom;

  @Bind({2131558410})
  ImageView up;

  @Bind({2131558987})
  RippleView vSub;

  @Bind({2131558985})
  RippleView vadd;

  @Bind({2131558988})
  TextView vol;

  @Bind({2131558974})
  RippleView voladd;

  @Bind({2131558975})
  RippleView volsub;

  @Bind({2131558990})
  RippleView zoomin;

  @Bind({2131558992})
  RippleView zoomout;

  private void init()
  {
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    this.mDevice = ((ETDevicePJT)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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

  public void auto(View paramView)
  {
    vibrate();
    sendMyCmd(40999);
  }

  public void brt(View paramView)
  {
    vibrate();
    sendMyCmd(41003);
  }

  public void cpt(View paramView)
  {
    vibrate();
    sendMyCmd(40965);
  }

  public void down(View paramView)
  {
    vibrate();
    sendMyCmd(40989);
  }

  public void exit(View paramView)
  {
    vibrate();
    sendMyCmd(40991);
  }

  public void left(View paramView)
  {
    vibrate();
    sendMyCmd(40985);
  }

  public void menu(View paramView)
  {
    vibrate();
    sendMyCmd(40979);
  }

  public void off(View paramView)
  {
    vibrate();
    sendMyCmd(40963);
  }

  public void ok(View paramView)
  {
    vibrate();
    sendMyCmd(40981);
  }

  public void on(View paramView)
  {
    vibrate();
    sendMyCmd(40961);
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
        this.mDevice = ((ETDevicePJT)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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
    setContentView(2130968690);
    ButterKnife.bind(this);
    init();
    setTitleView();
    this.ok.setOnLongClickListener(this);
    this.left.setOnLongClickListener(this);
    this.right.setOnLongClickListener(this);
    this.up.setOnLongClickListener(this);
    this.down.setOnLongClickListener(this);
    this.on.setOnLongClickListener(this);
    this.off.setOnLongClickListener(this);
    this.menu.setOnLongClickListener(this);
    this.back.setOnLongClickListener(this);
    this.voladd.setOnLongClickListener(this);
    this.volsub.setOnLongClickListener(this);
    this.zoomin.setOnLongClickListener(this);
    this.zoomout.setOnLongClickListener(this);
    this.vadd.setOnLongClickListener(this);
    this.vSub.setOnLongClickListener(this);
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    localIntent.putExtra("type", 40960);
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
    case 2131558874:
    case 2131558873:
    case 2131559113:
    case 2131558990:
    case 2131558992:
    case 2131558858:
    case 2131558985:
    case 2131558987:
    case 2131558973:
    case 2131558974:
    case 2131558975:
    case 2131558989:
    case 2131558410:
    case 2131558969:
    case 2131558458:
    case 2131558459:
    case 2131558968:
    }
    while (true)
    {
      showLearnDialog();
      return false;
      this.key = 40961;
      continue;
      this.key = 40963;
      continue;
      this.key = 40997;
      continue;
      this.key = 40971;
      continue;
      this.key = 40973;
      continue;
      this.key = 40991;
      continue;
      this.key = 40975;
      continue;
      this.key = 40977;
      continue;
      this.key = 41001;
      continue;
      this.key = 40993;
      continue;
      this.key = 40995;
      continue;
      this.key = 40979;
      continue;
      this.key = 40983;
      continue;
      this.key = 40989;
      continue;
      this.key = 40985;
      continue;
      this.key = 40987;
      continue;
      this.key = 40981;
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
      this.mDevice = ((ETDevicePJT)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
      this.mDevice.Load(ETDB.getInstance(this));
      ETKey localETKey = this.mDevice.GetKeyByValue(this.key);
      if (localETKey != null)
      {
        localETKey.SetState(1);
        localETKey.SetValue(paramArrayOfByte);
        localETKey.Update(ETDB.getInstance(this));
      }
      this.key = -1;
      toastL(2131100127);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void pause(View paramView)
  {
    vibrate();
    sendMyCmd(41001);
  }

  public void queit(View paramView)
  {
    vibrate();
    sendMyCmd(40997);
  }

  public void right(View paramView)
  {
    vibrate();
    sendMyCmd(40987);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.yk_back);
    setTiTleText(this.mDevice.GetName());
    setEditImageRes(2130903831);
  }

  public void signal(View paramView)
  {
    vibrate();
    sendMyCmd(40969);
  }

  public void up(View paramView)
  {
    vibrate();
    sendMyCmd(40983);
  }

  public void vidio(View paramView)
  {
    vibrate();
    sendMyCmd(40967);
  }

  public void viewAdd(View paramView)
  {
    vibrate();
    sendMyCmd(40975);
  }

  public void viewStub(View paramView)
  {
    vibrate();
    sendMyCmd(40977);
  }

  public void volAdd(View paramView)
  {
    vibrate();
    sendMyCmd(40993);
  }

  public void volStub(View paramView)
  {
    vibrate();
    sendMyCmd(40995);
  }

  public void zoomIn(View paramView)
  {
    vibrate();
    sendMyCmd(40971);
  }

  public void zoomOut(View paramView)
  {
    vibrate();
    sendMyCmd(40973);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtProjectionActivity
 * JD-Core Version:    0.6.0
 */