package com.ex.ltech.hongwai.yaokong;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.ex.ltech.hongwai.YkAt;
import com.ex.ltech.hongwai.atRcs.AtDiy;

public class NewAtYkTypeList extends YkAt
{
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 != 0)
    {
      setResult(paramInt2);
      finish();
    }
  }

  public void onClick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131559017:
    case 2131559018:
    default:
      return;
    case 2131559010:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 2, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559011:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 1, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559012:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 3, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559013:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 5, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559014:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 8, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559015:
      goAct4result2(AtYkBrandActivity.class, 0, "D_TYPE_KEY", 6, "OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      return;
    case 2131559016:
      Intent localIntent = new Intent(this, AtDiy.class);
      localIntent.putExtra("OP_AT_TYPE_KEY", "OP_AT_TYPE_CREATE");
      localIntent.putExtra("OP_AT_POSI_KEY", getIntent().getIntExtra("OP_AT_POSI_KEY", -1));
      startActivityForResult(localIntent, 0);
      return;
    case 2131559019:
      goAct4result(AtYkRfSwitchActivity.class, 0);
      return;
    case 2131559020:
    }
    goAct4result(AtYkCfgHelpActivity.class, 0, "cfg_type", 3);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968699);
    setTitleView();
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
    setTiTleTextRes(2131099816);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.yaokong.NewAtYkTypeList
 * JD-Core Version:    0.6.0
 */