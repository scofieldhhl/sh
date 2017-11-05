package com.ex.ltech.LogRegForget;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.LogRegForget.demo.CountryListView;
import com.ex.ltech.LogRegForget.demo.GroupListView;
import com.ex.ltech.LogRegForget.demo.GroupListView.OnItemClickListener;
import com.ex.ltech.LogRegForget.demo.SearchEngine;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.LocationAndWeatherBusiness;
import com.ex.ltech.led.vo.CityVo;

public class AtRegAreaActivity extends MyBaseActivity
  implements TextWatcher, GroupListView.OnItemClickListener
{

  @Bind({2131558757})
  EditText etSearch;
  LocationAndWeatherBusiness locationAndWeatherBusiness;

  @Bind({2131559029})
  CountryListView lvAtYkType;

  @Bind({2131558756})
  LinearLayout rlSearch;

  @Bind({2131559028})
  TextView tv_cancel;

  private void onLoactionOk()
  {
    this.locationAndWeatherBusiness.setpHandler(new Handler()
    {
      public void handleMessage(Message paramMessage)
      {
        super.handleMessage(paramMessage);
        switch (paramMessage.what)
        {
        default:
        case 1:
        }
        String str1;
        String str2;
        do
        {
          return;
          AtRegAreaActivity.this.locationAndWeatherBusiness.stopLocat();
          str1 = AtRegAreaActivity.this.locationAndWeatherBusiness.getCityVo().getCountry();
          str2 = AtRegAreaActivity.this.locationAndWeatherBusiness.getCityVo().getProvince();
        }
        while (str1.equals(""));
        if (str2.equals("香港"))
        {
          AtRegAreaActivity.this.lvAtYkType.setlocalItem2Frist(str2);
          return;
        }
        if (str2.equals("澳门"))
        {
          AtRegAreaActivity.this.lvAtYkType.setlocalItem2Frist(str2);
          return;
        }
        if (str2.equals("台湾"))
        {
          AtRegAreaActivity.this.lvAtYkType.setlocalItem2Frist(str2);
          return;
        }
        AtRegAreaActivity.this.lvAtYkType.setlocalItem2Frist(str1);
      }
    });
    this.locationAndWeatherBusiness.startLocat();
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (paramEditable.toString().length() > 0)
    {
      this.tv_cancel.setVisibility(View.VISIBLE);
      return;
    }
    this.tv_cancel.setVisibility(View.GONE);
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.locationAndWeatherBusiness = new LocationAndWeatherBusiness(this);
    SearchEngine.prepare(this, new Runnable()
    {
      public void run()
      {
        AtRegAreaActivity.this.runOnUiThread(new Runnable()
        {
          public void run()
          {
            AtRegAreaActivity.this.setContentView(2130968701);
            ButterKnife.bind(AtRegAreaActivity.this);
            AtRegAreaActivity.this.lvAtYkType.setOnItemClickListener(AtRegAreaActivity.this);
            AtRegAreaActivity.this.etSearch.addTextChangedListener(AtRegAreaActivity.this);
            AtRegAreaActivity.this.tv_cancel.setOnClickListener(new View.OnClickListener()
            {
              public void onClick(View paramView)
              {
                AtRegAreaActivity.this.etSearch.setText("");
              }
            });
            AtRegAreaActivity.this.setTitleView();
            AtRegAreaActivity.this.onLoactionOk();
          }
        });
      }
    });
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.locationAndWeatherBusiness.stopLocat();
  }

  public void onItemClick(GroupListView paramGroupListView, View paramView, int paramInt1, int paramInt2)
  {
    if (paramInt2 >= 0)
    {
      String[] arrayOfString = this.lvAtYkType.getCountry(paramInt1, paramInt2);
      String str1 = arrayOfString[0];
      String str2 = arrayOfString[1];
      setResult(500, new Intent().putExtra("AREA_KEY ", str1).putExtra("AREA_CODE_KEY", str2));
      finish();
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    this.lvAtYkType.onSearch(paramCharSequence.toString().toLowerCase());
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(2131100384);
    showBottomLine();
    setBgWhite();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.AtRegAreaActivity
 * JD-Core Version:    0.6.0
 */