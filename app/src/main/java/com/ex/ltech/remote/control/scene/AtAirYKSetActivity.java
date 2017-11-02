package com.ex.ltech.remote.control.scene;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.remote.control.vo.YkVo;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import et.song.db.ETDB;
import et.song.etclass.ETDeviceAIR;
import et.song.etclass.ETGroup;
import et.song.etclass.ETKey;
import et.song.etclass.ETPage;
import java.util.List;

public class AtAirYKSetActivity extends MyBaseActivity
{
  SceneBusiness business;
  boolean codeBaseConflict;
  private TextView mode;
  private ToggleButton swich;
  private TextView wendu;
  String wenduStr;
  private ImageView yaokongIc;
  private TextView yaokongName;
  String ykName;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968708);
    this.yaokongIc = ((ImageView)findViewById(2131559045));
    this.yaokongName = ((TextView)findViewById(2131559046));
    this.swich = ((ToggleButton)findViewById(2131559047));
    this.mode = ((TextView)findViewById(2131558835));
    this.wendu = ((TextView)findViewById(2131558841));
    this.business = new SceneBusiness(this);
    this.ykName = getIntent().getStringExtra("ykName");
    setTitleView();
    this.yaokongName.setText(this.ykName);
    this.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
    {
      public void onToggleInListView(boolean paramBoolean, int paramInt)
      {
      }
    });
    ETGroup localETGroup = (ETGroup)ETPage.getInstance(this).GetItem(0);
    localETGroup.Load(ETDB.getInstance(this));
    ETDeviceAIR localETDeviceAIR = (ETDeviceAIR)localETGroup.GetItem(getIntent().getIntExtra("ykId", -1));
    TextView localTextView = (TextView)findViewById(2131558816);
    if (localETDeviceAIR.GetKeyByValue(49153).GetState() == 1)
    {
      findViewById(2131558540).setVisibility(8);
      findViewById(2131559048).setVisibility(8);
      localTextView.setVisibility(0);
      localTextView.setText(2131100247);
      this.mode.setText("");
      this.wendu.setText("");
      this.codeBaseConflict = true;
    }
    do
      return;
    while (localETDeviceAIR.GetKeyByValue(49155).GetState() != 1);
    this.codeBaseConflict = true;
    findViewById(2131558540).setVisibility(8);
    findViewById(2131559048).setVisibility(8);
    localTextView.setVisibility(0);
    localTextView.setText(2131100164);
    this.mode.setText("");
    this.wendu.setText("");
  }

  protected void onEdit()
  {
    super.onEdit();
    YkVo localYkVo1 = new YkVo();
    localYkVo1.setId(getIntent().getIntExtra("ykId", 0));
    localYkVo1.setName(this.ykName);
    localYkVo1.setType("air");
    if (this.swich.isToggle())
      localYkVo1.setStatus(this.mode.getText().toString() + "," + this.wendu.getText().toString());
    while (true)
    {
      if ((!DeviceListActivity.isOnePointFive) && (CacheSceneData.ykVos.size() != 0))
        CacheSceneData.ykVos.remove(-1 + CacheSceneData.ykVos.size());
      CacheSceneData.ykVos.add(localYkVo1);
      YkVo localYkVo2 = new YkVo();
      localYkVo2.setIsAdd(true);
      if (!DeviceListActivity.isOnePointFive)
        CacheSceneData.ykVos.add(localYkVo2);
      setResult(200);
      finish();
      return;
      localYkVo1.setStatus(getString(2131100226));
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void seletedMode(View paramView)
  {
    String[] arrayOfString = new String[5];
    arrayOfString[0] = getString(2131099860);
    arrayOfString[1] = getString(2131099861);
    arrayOfString[2] = getString(2131099862);
    arrayOfString[3] = getString(2131099863);
    arrayOfString[4] = getString(2131099864);
    new AlertDialog.Builder(this).setItems(arrayOfString, new DialogInterface.OnClickListener(arrayOfString)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        AtAirYKSetActivity.this.mode.setText(this.val$arrayFruit[paramInt]);
      }
    }).create().show();
  }

  public void seletedWendu(View paramView)
  {
    String[] arrayOfString = { "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30" };
    new AlertDialog.Builder(this).setItems(arrayOfString, new DialogInterface.OnClickListener(arrayOfString)
    {
      public void onClick(DialogInterface paramDialogInterface, int paramInt)
      {
        TextView localTextView = AtAirYKSetActivity.this.wendu;
        AtAirYKSetActivity localAtAirYKSetActivity = AtAirYKSetActivity.this;
        String str = this.val$arrayFruit[paramInt] + "℃";
        localAtAirYKSetActivity.wenduStr = str;
        localTextView.setText(str);
      }
    }).create().show();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903074);
    setTiTleText(this.ykName);
    setEditTextRes(2131100358, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.AtAirYKSetActivity
 * JD-Core Version:    0.6.0
 */