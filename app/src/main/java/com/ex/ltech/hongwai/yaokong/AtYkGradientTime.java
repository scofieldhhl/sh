package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import java.util.ArrayList;
import java.util.List;

public class AtYkGradientTime extends MyBaseActivity
{
  private static final int START = 1;
  private String gradientTime;

  @Bind({2131558530})
  MyTimePickerView myTimePickerView;

  private void initTimePickView()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 1; i < 61; i++)
    {
      if (i == 1)
        this.gradientTime = (i + getString(R.string.min));
      localArrayList.add(i + getString(R.string.min));
    }
    this.myTimePickerView.setData(localArrayList);
    this.myTimePickerView.setTextCol(5855577);
    this.myTimePickerView.setSelected(0);
    this.myTimePickerView.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        AtYkGradientTime.access$002(AtYkGradientTime.this, paramString);
      }
    });
  }

  private void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100120);
    setEditImageText(2131100358, getResources().getColor(2131492935));
    setBgWhite();
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968605);
    ButterKnife.bind(this);
    setTitleView();
    initTimePickView();
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent();
    localIntent.putExtra("gradient_time", this.gradientTime);
    setResult(210000, localIntent);
    finish();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkGradientTime
 * JD-Core Version:    0.6.0
 */