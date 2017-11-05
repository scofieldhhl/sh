package com.ex.ltech.hongwai.scene;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.utils.BitmapUtils;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;

public class AtAirRcSetActivity extends MyBaseActivity
{
  boolean codeBaseConflict;
  private TextView mode;
  String opType;
  String rcName;
  int rcPosi;
  int rcType;
  InnerRcVo sceneInnerRcVo = new InnerRcVo();
  private ToggleButton swich;
  private TextView wendu;
  String wenduStr;
  private TextView yaokongName;

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968708);
    findViewById(2131558540).setVisibility(View.GONE);
    findViewById(2131559048).setVisibility(View.GONE);
    this.yaokongName = ((TextView)findViewById(2131559046));
    this.swich = ((ToggleButton)findViewById(2131559047));
    this.mode = ((TextView)findViewById(2131558835));
    this.wendu = ((TextView)findViewById(2131558841));
    this.opType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    this.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
    {
      public void onToggleInListView(boolean paramBoolean, int paramInt)
      {
        if (paramBoolean)
        {
          AtAirRcSetActivity.this.findViewById(2131558540).setVisibility(View.VISIBLE);
          AtAirRcSetActivity.this.findViewById(2131559048).setVisibility(View.VISIBLE);
          return;
        }
        AtAirRcSetActivity.this.findViewById(2131558540).setVisibility(View.GONE);
        AtAirRcSetActivity.this.findViewById(2131559048).setVisibility(View.GONE);
      }
    });
    if (this.opType.equals("OP_AT_TYPE_CREATE"))
    {
      this.rcPosi = getIntent().getIntExtra("OP_RC_POSI_KEY", 0);
      this.rcType = getIntent().getIntExtra("RC_TYPE_KEY", 0);
      this.rcName = getIntent().getStringExtra("RC_NAME_KEY");
    }
    while (true)
    {
      setTitleView();
      this.yaokongName.setText(this.rcName);
      return;
      InnerRcVo localInnerRcVo = (InnerRcVo)getIntent().getSerializableExtra(InnerRcVo.class.getName());
      this.rcPosi = localInnerRcVo.getmSaveRcListPosi();
      this.rcType = localInnerRcVo.getmType();
      this.rcName = localInnerRcVo.getName();
      String[] arrayOfString = localInnerRcVo.getStatus().split(",");
      if (localInnerRcVo.getStatus().equals(getString(2131100226)))
        continue;
      this.swich.toggle();
      this.mode.setText(arrayOfString[0]);
      this.wendu.setText(arrayOfString[1]);
    }
  }

  protected void onEdit()
  {
    super.onEdit();
    InnerRcVo localInnerRcVo = new InnerRcVo();
    localInnerRcVo.setName(this.rcName);
    localInnerRcVo.setTypeStr("air");
    localInnerRcVo.setmSaveRcListPosi(this.rcPosi);
    localInnerRcVo.setmType(this.rcType);
    Intent localIntent;
    if (this.swich.isToggle())
    {
      localInnerRcVo.setStatus(this.mode.getText().toString() + "," + this.wendu.getText().toString());
      localInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903521), 100.0D)));
      localIntent = new Intent();
      localIntent.putExtra(InnerRcVo.class.getName(), localInnerRcVo);
      if (!this.opType.equals("OP_AT_TYPE_CREATE"))
        break label180;
      setResult(20000, localIntent);
    }
    while (true)
    {
      finish();
      return;
      localInnerRcVo.setStatus(getString(2131100226));
      break;
      label180: setResult(30000, localIntent);
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
        AtAirRcSetActivity.this.mode.setText(this.val$arrayFruit[paramInt]);
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
        TextView localTextView = AtAirRcSetActivity.this.wendu;
        AtAirRcSetActivity localAtAirRcSetActivity = AtAirRcSetActivity.this;
        String str = this.val$arrayFruit[paramInt] + "℃";
        localAtAirRcSetActivity.wenduStr = str;
        localTextView.setText(str);
      }
    }).create().show();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleText(this.rcName);
    setEditTextRes(2131100358, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.AtAirRcSetActivity
 * JD-Core Version:    0.6.0
 */