package com.ex.ltech.remote.control.yaokong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.ex.ltech.remote.control.YkAt;

public class AtYkTypeList extends YkAt
{
  private YaoKongAdapter adt;
  private ListView lv;

  private void findView()
  {
    this.lv = ((ListView)findViewById(2131558610));
  }

  private void init()
  {
    this.adt = new YaoKongAdapter(this);
    this.lv.setAdapter(this.adt);
    this.lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        switch (paramInt)
        {
        default:
          return;
        case 0:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 49152, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 1:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 8192, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 2:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 16384, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 3:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 40960, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 4:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 32768, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 5:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 8448, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 6:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "type", 24576, "yaoKongAllCount", AtYkTypeList.this.getIntent().getIntExtra("yaoKongAllCount", -1));
          return;
        case 7:
        }
        Intent localIntent = new Intent(AtYkTypeList.this, AtRcDiyActivity.class);
        localIntent.putExtra("diyIndex", -1);
        AtYkTypeList.this.startActivity(localIntent);
        AtYkTypeList.this.finish();
      }
    });
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == this.saveYkOk)
    {
      setResult(this.saveYkOk);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968618);
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
    setMenuBackgroundRes(2130903074);
    setTiTleTextRes(2131100537);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.yaokong.AtYkTypeList
 * JD-Core Version:    0.6.0
 */