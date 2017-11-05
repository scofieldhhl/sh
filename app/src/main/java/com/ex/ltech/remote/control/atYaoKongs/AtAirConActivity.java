package com.ex.ltech.remote.control.atYaoKongs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.remote.control.YkAt;
import com.ex.ltech.remote.control.yaokong.AtYkBrandActivity;
import com.indris.material.RippleView;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
import et.song.remote.face.IR;
import java.io.PrintStream;

public class AtAirConActivity extends YkAt
  implements View.OnLongClickListener
{

  @Bind({2131558831})
  RippleView add;
  BaseBusiness business;
  int handWindDirectionClickCount;
  int hig;
  boolean isFromMain;
  boolean isOn;
  int key;

  @Bind({2131558837})
  RippleView leftRight;
  int low;
  private ETDeviceAIR mDevice = null;
  private IR mIR = null;

  @Bind({2131558835})
  RippleView mode;

  @Bind({2131558822})
  ImageView power;

  @Bind({2131558838})
  RippleView speed;

  @Bind({2131558829})
  RippleView sub;
  int tempHightLowDrop;
  TextView tv_at_air_tag_wendu;
  TextView tv_at_air_tag_wendu_c;

  @Bind({2131558836})
  RippleView upDown;
  boolean windDirectionLRRote;
  boolean windDirectionRote;

  private void hideStatus()
  {
    findViewById(2131558823).setVisibility(View.GONE);
    findViewById(2131558824).setVisibility(View.GONE);
    findViewById(2131558825).setVisibility(View.GONE);
    findViewById(2131558826).setVisibility(View.GONE);
    findViewById(2131558827).setVisibility(View.GONE);
    findViewById(2131558828).setVisibility(View.GONE);
  }

  private void initAir()
  {
    ETPage.getInstance(this).Load(ETDB.getInstance(this));
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    this.mDevice = ((ETDeviceAIR)localETGroup.GetItem(getIntent().getIntExtra("index", 0)));
    this.mDevice.Load(ETDB.getInstance(this));
    this.mDevice.SetMode(2);
  }

  private void showStatus()
  {
    findViewById(2131558823).setVisibility(View.VISIBLE);
    findViewById(2131558824).setVisibility(View.VISIBLE);
    findViewById(2131558825).setVisibility(View.VISIBLE);
    findViewById(2131558826).setVisibility(View.VISIBLE);
    findViewById(2131558827).setVisibility(View.VISIBLE);
    findViewById(2131558828).setVisibility(View.VISIBLE);
  }

  public void F5()
  {
    try
    {
      if (this.mDevice.GetPower() == 1)
      {
        showStatus();
        switch (this.mDevice.GetMode())
        {
        default:
        case 1:
          while (true)
            switch (this.mDevice.GetWindRate())
            {
            default:
              if (this.mDevice.GetAutoWindDir() != 1)
                break label259;
              findViewById(2131558824).setVisibility(View.VISIBLE);
              this.mDevice.Update(ETDB.getInstance(this));
              return;
              findViewById(2131558825).setBackgroundResource(2130903422);
            case 1:
            case 2:
            case 3:
            case 4:
            }
        case 2:
        case 3:
        case 4:
        case 5:
        }
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        findViewById(2131558825).setBackgroundResource(2130903421);
        continue;
        findViewById(2131558825).setBackgroundResource(2130903417);
        continue;
        findViewById(2131558825).setBackgroundResource(2130903416);
        continue;
        findViewById(2131558825).setBackgroundResource(2130903415);
        continue;
        findViewById(2131558828).setBackgroundResource(2130903057);
        continue;
        findViewById(2131558828).setBackgroundResource(2130903059);
        continue;
        findViewById(2131558828).setBackgroundResource(2130903060);
        continue;
        findViewById(2131558828).setBackgroundResource(2130903061);
        continue;
        label259: switch (this.mDevice.GetWindDir())
        {
        case 1:
        case 2:
          hideStatus();
          continue;
        }
      }
    }
  }

  public void add(View paramView)
  {
    vibrate();
    if (this.isFromMain);
    try
    {
      if (this.mDevice.GetTemp() < 30)
      {
        byte[] arrayOfByte = this.mDevice.GetKeyValue(49163);
        this.tv_at_air_tag_wendu.setText(this.mDevice.GetTemp() + "");
        sendCmd(arrayOfByte);
      }
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void autoWindDirection(View paramView)
  {
    vibrate();
    if (this.isFromMain)
      this.mDevice.GetKeyByValue(49161);
    try
    {
      sendCmd(this.mDevice.GetKeyValue(49161));
      findViewById(2131558826).setAlpha(1.0F);
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void brt(View paramView)
  {
  }

  public void cool(View paramView)
  {
  }

  public void dry(View paramView)
  {
  }

  public void fan(View paramView)
  {
  }

  public void goMode(View paramView)
  {
    vibrate();
    if (this.isFromMain)
      this.mDevice.GetKeyByValue(49155);
    try
    {
      sendCmd(this.mDevice.GetKeyValue(49155));
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void handWindDirection(View paramView)
  {
    vibrate();
    this.handWindDirectionClickCount = (1 + this.handWindDirectionClickCount);
    if (this.isFromMain);
    try
    {
      sendCmd(this.mDevice.GetKeyValue(49159));
      if (!this.windDirectionRote)
      {
        bool = true;
        this.windDirectionRote = bool;
        View localView = findViewById(2131558826);
        if (!this.windDirectionRote)
          break label91;
        f = 1.0F;
        localView.setAlpha(f);
        F5();
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        boolean bool = false;
        continue;
        label91: float f = 0.4F;
      }
    }
  }

  public void leftRight(View paramView)
  {
    vibrate();
    if (this.isFromMain);
    try
    {
      sendCmd(this.mDevice.GetKeyValue(114688));
      if (!this.windDirectionLRRote)
      {
        bool = true;
        this.windDirectionLRRote = bool;
        View localView = findViewById(2131558827);
        if (!this.windDirectionLRRote)
          break label81;
        f = 1.0F;
        localView.setAlpha(f);
        F5();
        return;
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        continue;
        boolean bool = false;
        continue;
        label81: float f = 0.4F;
      }
    }
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
        this.mDevice = ((ETDeviceAIR)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
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
    setContentView(2130968656);
    ButterKnife.bind(this);
    this.business = new BaseBusiness(this);
    this.isFromMain = true;
    initAir();
    this.tv_at_air_tag_wendu = ((TextView)findViewById(2131558823));
    this.tv_at_air_tag_wendu_c = ((TextView)findViewById(2131558824));
    this.sub.setOnLongClickListener(this);
    this.add.setOnLongClickListener(this);
    this.mode.setOnLongClickListener(this);
    this.upDown.setOnLongClickListener(this);
    this.leftRight.setOnLongClickListener(this);
    this.speed.setOnLongClickListener(this);
    this.power.setOnLongClickListener(this);
    setTitleView();
    F5();
    this.tv_at_air_tag_wendu.setText(this.mDevice.GetTemp() + "");
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent(this, AtYkBrandActivity.class);
    localIntent.putExtra("type", 49152);
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
    case 2131558835:
    case 2131558837:
    case 2131558836:
    case 2131558838:
    case 2131558831:
    case 2131558829:
    }
    while (true)
    {
      showLearnDialog();
      return false;
      this.key = 49153;
      continue;
      this.key = 49155;
      continue;
      this.key = 114688;
      continue;
      this.key = 49159;
      continue;
      this.key = 49157;
      continue;
      this.key = 49163;
      continue;
      this.key = 49165;
    }
  }

  public void onOff(View paramView)
  {
    if (this.isFromMain);
    try
    {
      vibrate();
      this.mDevice.GetKeyByValue(49153);
      sendCmd(this.mDevice.GetKeyValue(49153));
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
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
      this.mDevice = ((ETDeviceAIR)localETGroup.GetItem(getIntent().getIntExtra("index", -1)));
      this.mDevice.Load(ETDB.getInstance(this));
      ETKey localETKey1 = this.mDevice.GetKeyByValue(this.key);
      if (localETKey1 != null)
      {
        localETKey1.SetState(1);
        localETKey1.SetValue(paramArrayOfByte);
        localETKey1.Update(ETDB.getInstance(this));
        System.out.println("hihihihihi" + StringUtils.btye2Str(paramArrayOfByte));
        this.key = -1;
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    ETKey localETKey2 = this.mDevice.GetKeyByValue(49161);
    localETKey2.SetId(8);
    localETKey2.SetValue(paramArrayOfByte);
    localETKey2.SetState(1);
    localETKey2.SetKey(114688);
    if (this.mDevice.GetCount() == 8)
      localETKey2.Update(ETDB.getInstance(this));
    while (true)
    {
      this.mDevice.SetKey(localETKey2);
      break;
      localETKey2.Inster(ETDB.getInstance(this));
    }
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903830);
    setTiTleText(this.mDevice.GetName());
    setEditImageRes(2130903831);
    setBgAlpha();
  }

  public void speed(View paramView)
  {
    vibrate();
    if (this.isFromMain)
      this.mDevice.GetKeyByValue(49157);
    try
    {
      sendCmd(this.mDevice.GetKeyValue(49157));
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }

  public void stub(View paramView)
  {
    vibrate();
    if (this.isFromMain);
    try
    {
      if (this.mDevice.GetTemp() > 16)
      {
        byte[] arrayOfByte = this.mDevice.GetKeyValue(49165);
        this.tv_at_air_tag_wendu.setText(this.mDevice.GetTemp() + "");
        sendCmd(arrayOfByte);
      }
      F5();
      return;
    }
    catch (Exception localException)
    {
      while (true)
        localException.printStackTrace();
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.atYaoKongs.AtAirConActivity
 * JD-Core Version:    0.6.0
 */