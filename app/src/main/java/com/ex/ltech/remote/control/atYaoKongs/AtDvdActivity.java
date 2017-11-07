package com.ex.ltech.remote.control.atYaoKongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.yaokong.AtYkBrandActivity;
import com.indris.material.RippleView;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceDVD;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;

public class AtDvdActivity extends YkAt
  implements View.OnLongClickListener
{

  @Bind({2131558858})
  RippleView back;

  @Bind({2131558969})
  ImageView down;

  @Bind({2131558971})
  RippleView go;
  int key;

  @Bind({2131558924})
  RippleView last;

  @Bind({2131558458})
  ImageView left;
  private ETDeviceDVD mDevice = null;

  @Bind({2131558970})
  RippleView menu;

  @Bind({2131558795})
  RippleView next;

  @Bind({2131558968})
  ImageView ok;

  @Bind({2131558874})
  RippleView on;

  @Bind({2131558893})
  RippleView open;

  @Bind({2131558973})
  RippleView pause;

  @Bind({2131558972})
  RippleView play;

  @Bind({2131558976})
  RippleView queit;

  @Bind({2131558459})
  ImageView right;

  @Bind({2131558967})
  RelativeLayout rlAtProjection1;

  @Bind({2131558894})
  RippleView stop;

  @Bind({2131558410})
  ImageView up;

  @Bind({2131558974})
  RippleView voladd;

  @Bind({2131558975})
  RippleView volsub;

  private void init()
  {
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    this.mDevice = ((ETDeviceDVD)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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

  public void back(View paramView)
  {
    vibrate();
    sendMyCmd(24613);
  }

  public void down(View paramView)
  {
    vibrate();
    sendMyCmd(24583);
  }

  public void fastBack(View paramView)
  {
    vibrate();
    sendMyCmd(24591);
  }

  public void fastGo(View paramView)
  {
    vibrate();
    sendMyCmd(24595);
  }

  public void last(View paramView)
  {
    vibrate();
    sendMyCmd(24597);
  }

  public void left(View paramView)
  {
    vibrate();
    sendMyCmd(24577);
  }

  public void menu(View paramView)
  {
    vibrate();
    sendMyCmd(24611);
  }

  public void next(View paramView)
  {
    vibrate();
    sendMyCmd(24601);
  }

  public void ok(View paramView)
  {
    vibrate();
    sendMyCmd(24581);
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
        this.mDevice = ((ETDeviceDVD)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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
    setContentView(2130968688);
    ButterKnife.bind(this);
    init();
    this.on.setOnLongClickListener(this);
    this.open.setOnLongClickListener(this);
    this.ok.setOnLongClickListener(this);
    this.left.setOnLongClickListener(this);
    this.right.setOnLongClickListener(this);
    this.up.setOnLongClickListener(this);
    this.down.setOnLongClickListener(this);
    this.menu.setOnLongClickListener(this);
    this.pause.setOnLongClickListener(this);
    this.play.setOnLongClickListener(this);
    this.stop.setOnLongClickListener(this);
    this.queit.setOnLongClickListener(this);
    this.go.setOnLongClickListener(this);
    this.back.setOnLongClickListener(this);
    this.last.setOnLongClickListener(this);
    this.next.setOnLongClickListener(this);
    this.voladd.setOnLongClickListener(this);
    this.volsub.setOnLongClickListener(this);
    setTitleView();
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    localIntent.putExtra("type", 24576);
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
    case 2131558893:
    case 2131558973:
    case 2131558972:
    case 2131558894:
    case 2131558976:
    case 2131558924:
    case 2131558795:
    case 2131558970:
    case 2131558971:
    case 2131558858:
    case 2131558469:
    case 2131558968:
    case 2131558410:
    case 2131558969:
    case 2131558458:
    case 2131558459:
    case 2131558974:
    case 2131558975:
    }
    while (true)
    {
      showLearnDialog();
      return false;
      this.key = 24587;
      continue;
      this.key = 24609;
      continue;
      this.key = 24605;
      continue;
      this.key = 24593;
      continue;
      this.key = 24599;
      continue;
      this.key = 24589;
      continue;
      this.key = 24597;
      continue;
      this.key = 24601;
      continue;
      this.key = 24611;
      continue;
      this.key = 24595;
      continue;
      this.key = 24591;
      continue;
      this.key = 24607;
      continue;
      this.key = 24581;
      continue;
      this.key = 24579;
      continue;
      this.key = 24583;
      continue;
      this.key = 24577;
      continue;
      this.key = 24585;
      continue;
      this.key = 24607;
      continue;
      this.key = 24603;
    }
  }

  public void onOff(View paramView)
  {
    vibrate();
    sendMyCmd(24587);
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
      this.mDevice = ((ETDeviceDVD)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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

  public void open(View paramView)
  {
    vibrate();
    sendMyCmd(24609);
  }

  public void pause(View paramView)
  {
    vibrate();
    sendMyCmd(24605);
  }

  public void play(View paramView)
  {
    vibrate();
    sendMyCmd(24593);
  }

  public void queit(View paramView)
  {
    vibrate();
    sendMyCmd(24589);
  }

  public void right(View paramView)
  {
    vibrate();
    sendMyCmd(24585);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.yk_back);
    setTiTleText(this.mDevice.GetName());
    setEditImageRes(2130903831);
  }

  public void stop(View paramView)
  {
    vibrate();
    sendMyCmd(24599);
  }

  public void title(View paramView)
  {
    vibrate();
    sendMyCmd(24607);
  }

  public void type(View paramView)
  {
    vibrate();
    sendMyCmd(24603);
  }

  public void up(View paramView)
  {
    vibrate();
    sendMyCmd(24579);
  }

  public void voladd(View paramView)
  {
    vibrate();
    sendMyCmd(24607);
  }

  public void volsub(View paramView)
  {
    vibrate();
    sendMyCmd(24603);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtDvdActivity
 * JD-Core Version:    0.6.0
 */