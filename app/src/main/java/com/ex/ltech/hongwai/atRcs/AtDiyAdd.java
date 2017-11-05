package com.ex.ltech.hongwai.atRcs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.view.ImageTextBigButton;
import com.ex.ltech.hongwai.vo.DiyKey;

public class AtDiyAdd extends AtBaseYk
  implements View.OnClickListener
{
  DiyKey diyKey = new DiyKey();
  boolean isLearnOk;

  @Bind({2131558935})
  ImageTextBigButton k1;

  @Bind({2131558944})
  ImageTextBigButton k10;

  @Bind({2131558945})
  ImageTextBigButton k11;

  @Bind({2131558946})
  ImageTextBigButton k12;

  @Bind({2131558947})
  ImageTextBigButton k13;

  @Bind({2131558948})
  ImageTextBigButton k14;

  @Bind({2131558949})
  ImageTextBigButton k15;

  @Bind({2131558950})
  ImageTextBigButton k16;

  @Bind({2131558951})
  ImageTextBigButton k17;

  @Bind({2131558936})
  ImageTextBigButton k2;

  @Bind({2131558937})
  ImageTextBigButton k3;

  @Bind({2131558938})
  ImageTextBigButton k4;

  @Bind({2131558939})
  ImageTextBigButton k5;

  @Bind({2131558940})
  ImageTextBigButton k6;

  @Bind({2131558941})
  ImageTextBigButton k7;

  @Bind({2131558942})
  ImageTextBigButton k8;

  @Bind({2131558943})
  ImageTextBigButton k9;
  MyOnClickListener myOnClickListener = new MyOnClickListener();

  public void back()
  {
    finish();
  }

  public void changeRcCode()
  {
  }

  public void last(int paramInt)
  {
  }

  public void next(int paramInt)
  {
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 130000)
    {
      setResult(130000, paramIntent);
      finish();
    }
  }

  public void onBottomViewShow()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968684);
    ButterKnife.bind(this);
    setTitleView();
    initSystemBar(this, Color.parseColor("#FFFFFF"));
    setBackIc(2130903274);
    setSettingText(R.string.finish);
    setTitleText(2131100129, -16777216);
    setListener();
    this.k17.setBtnTextColor(Color.parseColor("#717171"));
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    this.diyKey.setKeyCode(paramArrayOfByte);
  }

  public void seleted(int paramInt)
  {
  }

  void setListener()
  {
    this.k1.setOnClickListener(this.myOnClickListener);
    this.k2.setOnClickListener(this.myOnClickListener);
    this.k3.setOnClickListener(this.myOnClickListener);
    this.k4.setOnClickListener(this.myOnClickListener);
    this.k5.setOnClickListener(this.myOnClickListener);
    this.k6.setOnClickListener(this.myOnClickListener);
    this.k7.setOnClickListener(this.myOnClickListener);
    this.k8.setOnClickListener(this.myOnClickListener);
    this.k9.setOnClickListener(this.myOnClickListener);
    this.k10.setOnClickListener(this.myOnClickListener);
    this.k11.setOnClickListener(this.myOnClickListener);
    this.k12.setOnClickListener(this.myOnClickListener);
    this.k13.setOnClickListener(this.myOnClickListener);
    this.k14.setOnClickListener(this.myOnClickListener);
    this.k15.setOnClickListener(this.myOnClickListener);
    this.k16.setOnClickListener(this.myOnClickListener);
    this.k17.setOnClickListener(this.myOnClickListener);
  }

  public void setting()
  {
    if (this.diyKey.getKeyCode() != null)
      setResult(130000, new Intent().putExtra(DiyKey.class.getName(), this.diyKey));
    finish();
  }

  class MyOnClickListener
    implements View.OnClickListener
  {
    MyOnClickListener()
    {
    }

    public void onClick(View paramView)
    {
      if (paramView == AtDiyAdd.this.k17)
      {
        AtDiyAdd.this.startActivityForResult(new Intent(AtDiyAdd.this, AtEditDiyAddBtn.class), 0);
        return;
      }
      switch (paramView.getId())
      {
      default:
      case 2131558935:
      case 2131558936:
      case 2131558937:
      case 2131558938:
      case 2131558939:
      case 2131558940:
      case 2131558941:
      case 2131558942:
      case 2131558943:
      case 2131558944:
      case 2131558945:
      case 2131558946:
      case 2131558947:
      case 2131558948:
      case 2131558949:
      case 2131558950:
      }
      while (true)
      {
        AtDiyAdd.this.diyKey.setName(((ImageTextBigButton)paramView).mName);
        AtDiyAdd.this.showLearn(((ImageTextBigButton)paramView).mName);
        return;
        AtDiyAdd.this.diyKey.setRes(1);
        continue;
        AtDiyAdd.this.diyKey.setRes(2);
        continue;
        AtDiyAdd.this.diyKey.setRes(3);
        continue;
        AtDiyAdd.this.diyKey.setRes(4);
        continue;
        AtDiyAdd.this.diyKey.setRes(5);
        continue;
        AtDiyAdd.this.diyKey.setRes(6);
        continue;
        AtDiyAdd.this.diyKey.setRes(7);
        continue;
        AtDiyAdd.this.diyKey.setRes(8);
        continue;
        AtDiyAdd.this.diyKey.setRes(9);
        continue;
        AtDiyAdd.this.diyKey.setRes(10);
        continue;
        AtDiyAdd.this.diyKey.setRes(11);
        continue;
        AtDiyAdd.this.diyKey.setRes(12);
        continue;
        AtDiyAdd.this.diyKey.setRes(13);
        continue;
        AtDiyAdd.this.diyKey.setRes(14);
        continue;
        AtDiyAdd.this.diyKey.setRes(15);
        continue;
        AtDiyAdd.this.diyKey.setRes(16);
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtDiyAdd
 * JD-Core Version:    0.6.0
 */