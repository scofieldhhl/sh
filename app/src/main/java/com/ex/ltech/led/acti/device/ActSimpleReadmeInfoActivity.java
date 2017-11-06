package com.ex.ltech.led.acti.device;

import android.os.Bundle;
import android.view.View;

import com.ex.ltech.led.R;
import com.ex.ltech.led.acti.MyBaseActivity;

public class ActSimpleReadmeInfoActivity extends MyBaseActivity
{
  public void onBackPressed()
  {
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_simple_readme_info);
    int i = getIntent().getIntExtra("page", 0);
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    if (i != 7)
      setTiTleTextRes(R.string.guide);
    else
      setTiTleTextRes(R.string.about);
      switch (i)
      {
      default:
        break;
        case 1:
        findViewById(R.id.iv_readme_info_page_1).setVisibility(View.VISIBLE);
        break;
        case 2:
        findViewById(R.id.iv_readme_info_page_2).setVisibility(View.VISIBLE);
        break;
        case 3:
        findViewById(R.id.iv_readme_info_page_3).setVisibility(View.VISIBLE);
        break;
        case 4:
        findViewById(R.id.iv_readme_info_page_4).setVisibility(View.VISIBLE);
        break;
        case 5:
        findViewById(R.id.iv_readme_info_page_5).setVisibility(View.VISIBLE);
        break;
        case 6:
        findViewById(R.id.iv_readme_info_page_6).setVisibility(View.VISIBLE);
        break;
        case 7:
        findViewById(R.id.iv_readme_info_page_7).setVisibility(View.VISIBLE);
        break;
        case 8:
        findViewById(R.id.iv_readme_info_page_8).setVisibility(View.VISIBLE);
      }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }
}
