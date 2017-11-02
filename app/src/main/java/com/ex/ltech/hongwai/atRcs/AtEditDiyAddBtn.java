package com.ex.ltech.hongwai.atRcs;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.ex.ltech.hongwai.AtBaseYk;
import com.ex.ltech.hongwai.vo.DiyKey;

public class AtEditDiyAddBtn extends AtBaseYk
{
  private EditText et;
  String kName;

  public void back()
  {
    finish();
  }

  public void changeRc(View paramView)
  {
    setResult(80000);
    finish();
  }

  public void changeRcCode()
  {
  }

  public void clearName(View paramView)
  {
    this.et.setText("");
  }

  public void last(int paramInt)
  {
  }

  public void next(int paramInt)
  {
  }

  public void next(View paramView)
  {
    if (this.et.getText().toString().equalsIgnoreCase(""))
    {
      Toast.makeText(this, getString(2131100098), 0).show();
      return;
    }
    showLearn(this.et.getText().toString());
  }

  public void onBottomViewShow()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968674);
    setTitleView();
    initSystemBar(this, Color.parseColor("#FFFFFF"));
    setBackIc(2130903274);
    hideSetting();
    setTitleText(2131100048, -16777216);
    this.et = ((EditText)findViewById(2131558904));
    this.kName = getIntent().getStringExtra("BT_NAME_KEY");
  }

  public void onLearnOk(String paramString, byte[] paramArrayOfByte)
  {
    DiyKey localDiyKey = new DiyKey();
    localDiyKey.setKeyCode(paramArrayOfByte);
    localDiyKey.setName(paramString);
    localDiyKey.setRes(17);
    setResult(130000, new Intent().putExtra(DiyKey.class.getName(), localDiyKey));
    finish();
  }

  public void seleted(int paramInt)
  {
  }

  public void setting()
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.atRcs.AtEditDiyAddBtn
 * JD-Core Version:    0.6.0
 */