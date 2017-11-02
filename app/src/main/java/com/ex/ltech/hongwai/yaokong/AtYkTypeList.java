package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.atRcs.AtDiy;

public class AtYkTypeList extends YkAt
{
  private YaoKongAdapter adt;
  private GridView gv;

  private void findView()
  {
    this.gv = ((GridView)findViewById(2131559007));
  }

  private void init()
  {
    this.adt = new YaoKongAdapter(this);
    this.gv.setAdapter(this.adt);
    this.gv.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAdapterView, View paramView, int paramInt, long paramLong)
      {
        switch (paramInt)
        {
        default:
          return;
        case 0:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 2, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
          return;
        case 1:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 1, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
          return;
        case 2:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 5, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
          return;
        case 3:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 8, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
          return;
        case 4:
          AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 3, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
          return;
        case 5:
        }
        AtYkTypeList.this.goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 6, "OP_AT_POSI_KEY", AtYkTypeList.this.getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      }
    });
  }

  public void diy(View paramView)
  {
    Intent localIntent = new Intent(this, AtDiy.class);
    localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
    localIntent.putExtra("OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
    startActivityForResult(localIntent, 0);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 10000)
    {
      setResult(10000);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968698);
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
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100537);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.AtYkTypeList
 * JD-Core Version:    0.6.0
 */