package com.ex.ltech.bwct.timing.act;

import android.content.Intent;
import android.os.Bundle;
import android.widget.RelativeLayout;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MyTimePickerView;
import java.util.ArrayList;
import java.util.List;

public class AtCtTimeJianbianActivity extends MyBaseActivity
{
  private MyTimePickerView min;
  private RelativeLayout rlMain;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968673);
    setTitleView();
    this.rlMain = ((RelativeLayout)findViewById(2131558902));
    this.min = ((MyTimePickerView)findViewById(2131558903));
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getString(2131100085));
    localArrayList.add(30 + getString(2131100382));
    for (int i = 1; i < 61; i++)
      localArrayList.add(i + getString(2131100160));
    this.min.setData(localArrayList);
  }

  protected void onMenu()
  {
    super.onMenu();
    Intent localIntent = new Intent();
    localIntent.putExtra("jianbianSec", this.min.getValue());
    setResult(3000, localIntent);
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131100333);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.bwct.timing.act.AtCtTimeJianbianActivity
 * JD-Core Version:    0.6.0
 */