package com.ex.ltech.hongwai.time.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import com.ex.ltech.hongwai.yaokong.YaoKongAdapter;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtYongkongList extends MyBaseActivity
{
  private YaoKongAdapter adt;
  private ListView lv;

  private void findView()
  {
    this.lv = ((ListView)findViewById(R.id.lv_act_repeat_day));
  }

  private void init()
  {
    this.adt = new YaoKongAdapter(this);
    this.lv.setAdapter(this.adt);
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        String str1 = AtYongkongList.this.getIntent().getStringExtra("fromWhere");
        int i = -1;
        switch (str1.hashCode())
        {
        default:
        case -937103523:
        }
        while (true)
        {
          switch (i)
          {
          default:
            String str2 = ((TextView)paramView.findViewById(2131559008)).getText().toString();
            Intent localIntent = new Intent();
            localIntent.putExtra("yaokong", str2);
            AtYongkongList.this.setResult(200, localIntent);
            AtYongkongList.this.finish();
          case 0:
          }
          return;
          if (!str1.equals("atYaoKongNewScene"))
            continue;
          i = 0;
        }
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.act_repeat_day);
    findView();
    setTitleView();
    init();
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(R.string.repeat);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.time.act.AtYongkongList
 * JD-Core Version:    0.6.0
 */