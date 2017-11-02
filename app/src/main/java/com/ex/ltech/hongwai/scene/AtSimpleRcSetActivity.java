package com.ex.ltech.hongwai.scene;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.ex.ltech.hongwai.vo.InnerRcVo;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.led.utils.BitmapUtils;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.ArrayList;
import java.util.List;

public class AtSimpleRcSetActivity extends MyBaseActivity
{
  String chanel = "1";
  MyTimePickerView myTimePickerView;
  String opType;
  String rcName;
  int rcPosi;
  int rcType;
  InnerRcVo sceneInnerRcVo = new InnerRcVo();
  private ToggleButton swich;
  ImageView yaokongIc;
  private TextView yaokong_name;

  public List<String> getChanelDate()
  {
    ArrayList localArrayList = new ArrayList();
    for (int i = 0; i < 100; i++)
      localArrayList.add("" + i);
    return localArrayList;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968709);
    ButterKnife.bind(this);
    this.swich = ((ToggleButton)findViewById(2131559047));
    this.yaokongIc = ((ImageView)findViewById(2131559045));
    this.yaokong_name = ((TextView)findViewById(2131559046));
    setTitleView();
    this.opType = getIntent().getStringExtra("OP_AT_TYPE_KEY");
    if (this.opType.equals("OP_AT_TYPE_CREATE"))
    {
      this.rcPosi = getIntent().getIntExtra("OP_RC_POSI_KEY", 0);
      this.rcType = getIntent().getIntExtra("RC_TYPE_KEY", 0);
      this.rcName = getIntent().getStringExtra("RC_NAME_KEY");
      setTiTleText(this.rcName);
      this.myTimePickerView = ((MyTimePickerView)findViewById(2131559050));
      this.myTimePickerView.setData(getChanelDate());
      this.myTimePickerView.setSelected(1);
      this.myTimePickerView.setmColorText(getResources().getColor(2131492897));
      this.myTimePickerView.setOnSelectListener(new MyTimePickerView.onSelectListener()
      {
        public void onSelect(String paramString)
        {
          AtSimpleRcSetActivity.this.chanel = paramString;
        }
      });
      if ((this.rcType != 2) && (this.rcType != 1))
        break label410;
      if (getIntent().getStringExtra("OP_SCENE_IN_TV_TYPE_KEY").equals("OP_SCENE_IN_TV_TYPE_ON_OFF"))
        findViewById(2131559049).setVisibility(8);
      if (getIntent().getStringExtra("OP_SCENE_IN_TV_TYPE_KEY").equals("OP_SCENE_IN_TV_TYPE_CHANNEL"))
        findViewById(2131559051).setVisibility(8);
      label267: this.yaokong_name.setText(this.rcName);
      switch (this.rcType)
      {
      case 4:
      case 7:
      default:
      case 5:
      case 2:
      case 3:
      case 1:
      case 8:
      case 6:
      }
    }
    while (true)
    {
      this.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
        }
      });
      return;
      InnerRcVo localInnerRcVo = (InnerRcVo)getIntent().getSerializableExtra(InnerRcVo.class.getName());
      this.rcPosi = localInnerRcVo.getmSaveRcListPosi();
      this.rcType = localInnerRcVo.getmType();
      this.rcName = localInnerRcVo.getName();
      if (!localInnerRcVo.getStatus().equals(getString(2131100232)))
        break;
      this.swich.toggle();
      break;
      label410: findViewById(2131559049).setVisibility(8);
      break label267;
      this.sceneInnerRcVo.setTypeStr("air");
      this.yaokongIc.setBackgroundResource(2130903523);
      continue;
      this.sceneInnerRcVo.setTypeStr("tv");
      this.yaokongIc.setBackgroundResource(2130903574);
      this.sceneInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903574), 100.0D)));
      continue;
      this.sceneInnerRcVo.setTypeStr("box");
      this.yaokongIc.setBackgroundResource(2130903526);
      this.sceneInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903526), 100.0D)));
      continue;
      this.sceneInnerRcVo.setTypeStr("stb");
      this.yaokongIc.setBackgroundResource(2130903572);
      this.sceneInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903572), 100.0D)));
      continue;
      this.sceneInnerRcVo.setTypeStr("fan");
      this.yaokongIc.setBackgroundResource(2130903555);
      this.sceneInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903555), 100.0D)));
      continue;
      this.sceneInnerRcVo.setTypeStr("projector");
      this.yaokongIc.setBackgroundResource(2130903564);
      this.sceneInnerRcVo.setIcData(BitmapUtils.Bitmap2Bytes(BitmapUtils.zoomOutBM(BitmapFactory.decodeResource(getResources(), 2130903564), 100.0D)));
    }
  }

  protected void onEdit()
  {
    super.onEdit();
    this.sceneInnerRcVo.setName(this.rcName);
    this.sceneInnerRcVo.setmType(this.rcType);
    this.sceneInnerRcVo.setmSaveRcListPosi(this.rcPosi);
    label144: Intent localIntent;
    if ((this.rcType == 2) || (this.rcType == 1))
      if (getIntent().getStringExtra("OP_SCENE_IN_TV_TYPE_KEY").equals("OP_SCENE_IN_TV_TYPE_ON_OFF"))
      {
        if (this.swich.isToggle())
          this.sceneInnerRcVo.setStatus(getString(2131100232));
      }
      else
      {
        if (getIntent().getStringExtra("OP_SCENE_IN_TV_TYPE_KEY").equals("OP_SCENE_IN_TV_TYPE_CHANNEL"))
          this.sceneInnerRcVo.setStatus(this.chanel + getString(2131099929));
        localIntent = new Intent();
        localIntent.putExtra(InnerRcVo.class.getName(), this.sceneInnerRcVo);
        localIntent.putExtra("OP_AT_TYPE_KEY", this.opType);
        if (!this.opType.equals("OP_AT_TYPE_CREATE"))
          break label262;
        setResult(20000, localIntent);
      }
    while (true)
    {
      finish();
      return;
      this.sceneInnerRcVo.setStatus(getString(2131100226));
      break;
      if (this.swich.isToggle())
      {
        this.sceneInnerRcVo.setStatus(getString(2131100232));
        break label144;
      }
      this.sceneInnerRcVo.setStatus(getString(2131100226));
      break label144;
      label262: setResult(30000, localIntent);
    }
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
    setTiTleText(this.rcName);
    setBgWhite();
    setEditTextRes(2131100358, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.scene.AtSimpleRcSetActivity
 * JD-Core Version:    0.6.0
 */