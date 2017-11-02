package com.ex.ltech.remote.control.atYaoKongs;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.fragment.FragmentPageAdapter;
import com.ex.ltech.led.my_view.NoScrollViewPager;
import com.ex.ltech.remote.control.atYaoKongs.fragment.NumFragment;
import com.ex.ltech.remote.control.atYaoKongs.fragment.TvFragment;
import com.ex.ltech.remote.control.yaokong.AtYkBrandActivity;
import com.ex.ltech.remote.control.yaokong.YkFragmentAt;
import com.indris.material.RippleView;
import et.song.db.ETDB;
import et.song.etclass.ETDevice;
import et.song.etclass.ETDeviceIPTV;
import et.song.etclass.ETDeviceSTB;
import et.song.etclass.ETDeviceTV;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
import java.util.ArrayList;
import java.util.List;

public class AtNewTvActivity extends YkFragmentAt
{

  @Bind({2131558969})
  ImageView down;
  int index;
  int key;
  private LayoutInflater layoutInflater;

  @Bind({2131558458})
  ImageView left;
  private ETDevice mDevice = null;
  private List<Fragment> mFragments = new ArrayList();
  private NoScrollViewPager mVPFragments;
  private NumFragment numFragment;

  @Bind({2131558968})
  ImageView ok;
  private ImageView pageIndex;
  private int pagerCurrent = 0;

  @Bind({2131558459})
  ImageView right;
  private TvFragment tvFragment;
  private String type;

  @Bind({2131558410})
  ImageView up;

  private void init()
  {
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    if (this.type.equals("tv"))
      this.mDevice = ((ETDeviceTV)localETGroup.GetItem(this.index));
    if (this.type.equals("iptv"))
      this.mDevice = ((ETDeviceIPTV)localETGroup.GetItem(this.index));
    if (this.type.equals("tvbox"))
      this.mDevice = ((ETDeviceSTB)localETGroup.GetItem(this.index));
    this.mDevice.Load(ETDB.getInstance(this));
    this.ok.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        AtNewTvActivity.this.onLongClick(paramView);
        return false;
      }
    });
    this.up.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        AtNewTvActivity.this.onLongClick(paramView);
        return false;
      }
    });
    this.down.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        AtNewTvActivity.this.onLongClick(paramView);
        return false;
      }
    });
    this.left.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        AtNewTvActivity.this.onLongClick(paramView);
        return false;
      }
    });
    this.right.setOnLongClickListener(new View.OnLongClickListener()
    {
      public boolean onLongClick(View paramView)
      {
        AtNewTvActivity.this.onLongClick(paramView);
        return false;
      }
    });
  }

  private void initViewPager()
  {
    this.mVPFragments = ((NoScrollViewPager)findViewById(2131558769));
    this.mVPFragments.setOnPageChangeListener(new ViewPager.OnPageChangeListener()
    {
      public void onPageScrollStateChanged(int paramInt)
      {
      }

      public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
      {
        if (paramInt1 == 0)
        {
          AtNewTvActivity.this.pageIndex.setBackgroundResource(2130903798);
          return;
        }
        AtNewTvActivity.this.pageIndex.setBackgroundResource(2130903799);
      }

      public void onPageSelected(int paramInt)
      {
      }
    });
    this.mVPFragments.setOffscreenPageLimit(3);
    this.layoutInflater = LayoutInflater.from(this);
    this.tvFragment = new TvFragment();
    this.numFragment = new NumFragment();
    this.tvFragment.setType(this.type);
    this.mFragments.add(this.tvFragment);
    this.mFragments.add(this.numFragment);
    this.mVPFragments.setAdapter(new FragmentPageAdapter(getSupportFragmentManager(), this.mFragments));
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

  public void back()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8231);
    if (this.type.equals("iptv"))
      sendMyCmd(8493);
    if (this.type.equals("tvbox"))
      sendMyCmd(16409);
  }

  public void chAdd()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8195);
    if (this.type.equals("iptv"))
      sendMyCmd(8457);
    if (this.type.equals("tvbox"))
      sendMyCmd(16425);
  }

  public void chStub()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8199);
    if (this.type.equals("iptv"))
      sendMyCmd(8459);
    if (this.type.equals("tvbox"))
      sendMyCmd(16427);
  }

  public void down(View paramView)
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8241);
    if (this.type.equals("iptv"))
      sendMyCmd(8469);
    if (this.type.equals("tvbox"))
      sendMyCmd(16419);
  }

  public void home()
  {
    vibrate();
    sendMyCmd(73985);
  }

  public void k1()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8207);
    if (this.type.equals("iptv"))
      sendMyCmd(8473);
    if (this.type.equals("tvbox"))
      sendMyCmd(16387);
  }

  public void k10()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8227);
    if (this.type.equals("iptv"))
      sendMyCmd(8491);
    if (this.type.equals("tvbox"))
      sendMyCmd(16407);
  }

  public void k11()
  {
  }

  public void k2()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8209);
    if (this.type.equals("iptv"))
      sendMyCmd(8475);
    if (this.type.equals("tvbox"))
      sendMyCmd(16389);
  }

  public void k3()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8211);
    if (this.type.equals("iptv"))
      sendMyCmd(8477);
    if (this.type.equals("tvbox"))
      sendMyCmd(16391);
  }

  public void k4()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8213);
    if (this.type.equals("iptv"))
      sendMyCmd(8479);
    if (this.type.equals("tvbox"))
      sendMyCmd(16393);
  }

  public void k5()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8215);
    if (this.type.equals("iptv"))
      sendMyCmd(8481);
    if (this.type.equals("tvbox"))
      sendMyCmd(16395);
  }

  public void k6()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8217);
    if (this.type.equals("iptv"))
      sendMyCmd(8483);
    if (this.type.equals("tvbox"))
      sendMyCmd(16397);
  }

  public void k7()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8219);
    if (this.type.equals("iptv"))
      sendMyCmd(8485);
    if (this.type.equals("tvbox"))
      sendMyCmd(16399);
  }

  public void k8()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8221);
    if (this.type.equals("iptv"))
      sendMyCmd(8487);
    if (this.type.equals("tvbox"))
      sendMyCmd(16401);
  }

  public void k9()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8223);
    if (this.type.equals("iptv"))
      sendMyCmd(8489);
    if (this.type.equals("tvbox"))
      sendMyCmd(16403);
  }

  public void kDouble()
  {
    vibrate();
  }

  public void left(View paramView)
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8237);
    if (this.type.equals("iptv"))
      sendMyCmd(8463);
    if (this.type.equals("tvbox"))
      sendMyCmd(16413);
  }

  public void menu()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8197);
    if (this.type.equals("iptv"))
      sendMyCmd(8465);
    if (this.type.equals("tvbox"))
      sendMyCmd(16429);
  }

  public void ok(View paramView)
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8233);
    if (this.type.equals("iptv"))
      sendMyCmd(8465);
    if (this.type.equals("tvbox"))
      sendMyCmd(16415);
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
        if (this.type.equals("tv"))
          this.mDevice = ((ETDeviceTV)localETGroup.GetItem(this.index));
        if (this.type.equals("iptv"))
          this.mDevice = ((ETDeviceIPTV)localETGroup.GetItem(this.index));
        if (this.type.equals("tvbox"))
          this.mDevice = ((ETDeviceSTB)localETGroup.GetItem(this.index));
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
    setContentView(2130968692);
    ButterKnife.bind(this);
    this.pageIndex = ((ImageView)findViewById(2131558908));
    this.type = getIntent().getStringExtra("tvType");
    this.index = getIntent().getIntExtra("index", -1);
    init();
    setTitleView();
    initViewPager();
  }

  protected void onEdit()
  {
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    localIntent.putExtra("type", 8192);
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
    case 2131559112:
    case 2131558935:
    case 2131558936:
    case 2131558937:
    case 2131558938:
    case 2131558939:
    case 2131558940:
    case 2131558941:
    case 2131558942:
    case 2131558943:
    case 2131558410:
    case 2131558969:
    case 2131558458:
    case 2131558459:
    case 2131559116:
    case 2131559117:
    case 2131559215:
    case 2131559216:
    case 2131558970:
    case 2131558976:
    case 2131558968:
    case 2131558858:
    case 2131558874:
    case 2131558405:
    case 2131558974:
    case 2131558975:
    case 2131559115:
    case 2131559113:
    case 2131559114:
    }
    while (true)
    {
      showLearnDialog();
      return false;
      if (this.type.equals("tv"))
        this.key = 8227;
      if (this.type.equals("iptv"))
        this.key = 8491;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16407;
      continue;
      if (this.type.equals("tv"))
        this.key = 8207;
      if (this.type.equals("iptv"))
        this.key = 8473;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16387;
      continue;
      if (this.type.equals("tv"))
        this.key = 8209;
      if (this.type.equals("iptv"))
        this.key = 8475;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16389;
      continue;
      if (this.type.equals("tv"))
        this.key = 8211;
      if (this.type.equals("iptv"))
        this.key = 8477;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16391;
      continue;
      if (this.type.equals("tv"))
        this.key = 8213;
      if (this.type.equals("iptv"))
        this.key = 8479;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16393;
      continue;
      if (this.type.equals("tv"))
        this.key = 8215;
      if (this.type.equals("iptv"))
        this.key = 8481;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16395;
      continue;
      if (this.type.equals("tv"))
        this.key = 8217;
      if (this.type.equals("iptv"))
        this.key = 8483;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16397;
      continue;
      if (this.type.equals("tv"))
        this.key = 8219;
      if (this.type.equals("iptv"))
        this.key = 8485;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16399;
      continue;
      if (this.type.equals("tv"))
        this.key = 8221;
      if (this.type.equals("iptv"))
        this.key = 8487;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16401;
      continue;
      if (this.type.equals("tv"))
        this.key = 8223;
      if (this.type.equals("iptv"))
        this.key = 8489;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16403;
      continue;
      if (this.type.equals("tv"))
        this.key = 8235;
      if (this.type.equals("iptv"))
        this.key = 8461;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16411;
      continue;
      if (this.type.equals("tv"))
        this.key = 8241;
      if (this.type.equals("iptv"))
        this.key = 8469;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16419;
      continue;
      if (this.type.equals("tv"))
        this.key = 8237;
      if (this.type.equals("iptv"))
        this.key = 8463;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16413;
      continue;
      if (this.type.equals("tv"))
        this.key = 8239;
      if (this.type.equals("iptv"))
        this.key = 8467;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16417;
      continue;
      if (this.type.equals("tv"))
        this.key = 8195;
      if (this.type.equals("iptv"))
        this.key = 8457;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16425;
      continue;
      if (this.type.equals("tv"))
        this.key = 8199;
      if (this.type.equals("iptv"))
        this.key = 8459;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16427;
      continue;
      if (this.type.equals("tv"))
        this.key = 8201;
      if (this.type.equals("iptv"))
        this.key = 8453;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16421;
      continue;
      if (this.type.equals("tv"))
        this.key = 8193;
      if (this.type.equals("iptv"))
        this.key = 8455;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16423;
      continue;
      if (this.type.equals("tv"))
        this.key = 8197;
      if (this.type.equals("iptv"))
        this.key = 8465;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16429;
      continue;
      if (this.type.equals("tv"))
        this.key = 8205;
      if (this.type.equals("iptv"))
        this.key = 8451;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 81920;
      continue;
      if (this.type.equals("tv"))
        this.key = 8233;
      if (this.type.equals("iptv"))
        this.key = 8465;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16415;
      continue;
      if (this.type.equals("tv"))
        this.key = 8231;
      if (this.type.equals("iptv"))
        this.key = 8493;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16409;
      continue;
      if (this.type.equals("tv"))
        this.key = 8203;
      if (this.type.equals("iptv"))
        this.key = 8449;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16385;
      continue;
      if (this.type.equals("tv"))
        this.key = 73729;
      if (this.type.equals("iptv"))
        this.key = 73985;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 73729;
      continue;
      if (this.type.equals("tv"))
        this.key = 8201;
      if (this.type.equals("iptv"))
        this.key = 8453;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16421;
      continue;
      if (this.type.equals("tv"))
        this.key = 8193;
      if (this.type.equals("iptv"))
        this.key = 8455;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16423;
      continue;
      if (this.type.equals("iptv"))
        this.key = 8465;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16429;
      continue;
      if (this.type.equals("tv"))
        this.key = 8205;
      if (this.type.equals("iptv"))
        this.key = 8451;
      if (!this.type.equals("tvbox"))
        continue;
      this.key = 16429;
      continue;
      if (!this.type.equals("tv"))
        continue;
      this.key = 8229;
    }
  }

  public void onOff()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8203);
    if (this.type.equals("iptv"))
      sendMyCmd(8449);
    if (this.type.equals("tvbox"))
      sendMyCmd(16385);
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
      if (this.type.equals("tv"))
        this.mDevice = ((ETDeviceTV)localETGroup.GetItem(this.index));
      if (this.type.equals("iptv"))
        this.mDevice = ((ETDeviceIPTV)localETGroup.GetItem(this.index));
      if (this.type.equals("tvbox"))
        this.mDevice = ((ETDeviceSTB)localETGroup.GetItem(this.index));
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

  public void queit()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8205);
    if (this.type.equals("iptv"))
      sendMyCmd(8451);
    if (this.type.equals("tvbox"))
      sendMyCmd(16429);
  }

  public void res()
  {
    vibrate();
    sendMyCmd(8229);
  }

  public void right(View paramView)
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8239);
    if (this.type.equals("iptv"))
      sendMyCmd(8467);
    if (this.type.equals("tvbox"))
      sendMyCmd(16417);
  }

  public void seletedBrand(View paramView)
  {
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    if (this.type.equals("tv"))
      localIntent.putExtra("type", 8192);
    if (this.type.equals("iptv"))
      localIntent.putExtra("type", 8448);
    if (this.type.equals("tvbox"))
      localIntent.putExtra("type", 16384);
    startActivityForResult(localIntent, 0);
  }

  public void setTitleView()
  {
    RippleView localRippleView = (RippleView)findViewById(2131558781);
    TextView localTextView = (TextView)findViewById(2131558783);
    localRippleView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        AtNewTvActivity.this.finish();
      }
    });
    localTextView.setText(this.mDevice.GetName());
  }

  public void up(View paramView)
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8235);
    if (this.type.equals("iptv"))
      sendMyCmd(8461);
    if (this.type.equals("tvbox"))
      sendMyCmd(16411);
  }

  public void vibrate()
  {
    ((Vibrator)getSystemService("vibrator")).vibrate(100L);
  }

  public void volAdd()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8201);
    if (this.type.equals("iptv"))
      sendMyCmd(8453);
    if (this.type.equals("tvbox"))
      sendMyCmd(16421);
  }

  public void volStub()
  {
    vibrate();
    if (this.type.equals("tv"))
      sendMyCmd(8193);
    if (this.type.equals("iptv"))
      sendMyCmd(8455);
    if (this.type.equals("tvbox"))
      sendMyCmd(16423);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtNewTvActivity
 * JD-Core Version:    0.6.0
 */