package com.ex.ltech.remote.control.scene;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.ButterKnife;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyTimePickerView;
import com.ex.ltech.led.my_view.MyTimePickerView.onSelectListener;
import com.ex.ltech.remote.control.vo.YkVo;
import com.zcw.togglebutton.ToggleButton;
import com.zcw.togglebutton.ToggleButton.OnToggleChangedInListView;
import java.util.ArrayList;
import java.util.List;

public class AtSimpleYKSetActivity extends MyBaseActivity
{
  SceneBusiness business;
  String chanel = "1";
  MyTimePickerView myTimePickerView;
  int scenePosi;
  private ToggleButton swich;
  private TextView wendu;
  ImageView yaokongIc;
  private TextView yaokong_name;
  String ykName;
  int ykPosi;
  String ykType;

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
    this.business = new SceneBusiness(this);
    setTitleView();
    this.ykType = getIntent().getStringExtra("ykType");
    this.ykName = getIntent().getStringExtra("ykName");
    this.myTimePickerView = ((MyTimePickerView)findViewById(2131559050));
    this.myTimePickerView.setData(getChanelDate());
    this.myTimePickerView.setSelected(1);
    this.myTimePickerView.setmColorText(getResources().getColor(2131492897));
    this.myTimePickerView.setOnSelectListener(new MyTimePickerView.onSelectListener()
    {
      public void onSelect(String paramString)
      {
        AtSimpleYKSetActivity.this.chanel = paramString;
      }
    });
    if ((this.ykType.equals("tv")) || (this.ykType.equals("iptv")) || (this.ykType.equals("tvbox")))
    {
      if (getIntent().getStringExtra("ykTvStatusType").equals("onOff"))
        findViewById(2131559049).setVisibility(View.GONE);
      if (getIntent().getStringExtra("ykTvStatusType").equals("chanel"))
        findViewById(2131559051).setVisibility(View.GONE);
    }
    while (true)
    {
      this.yaokong_name.setText(this.ykName);
      this.yaokongIc.setBackgroundResource(getIntent().getIntExtra("icRes", -1));
      this.swich.setOnToggleChangedInListView(new ToggleButton.OnToggleChangedInListView()
      {
        public void onToggleInListView(boolean paramBoolean, int paramInt)
        {
        }
      });
      return;
      findViewById(2131559049).setVisibility(View.GONE);
    }
  }

  protected void onEdit()
  {
    super.onEdit();
    YkVo localYkVo1 = new YkVo();
    localYkVo1.setId(getIntent().getIntExtra("ykId", 0));
    localYkVo1.setName(this.ykName);
    localYkVo1.setType(this.ykType);
    if ((this.ykType.equals("tv")) || (this.ykType.equals("iptv")) || (this.ykType.equals("tvbox")))
      if (getIntent().getStringExtra("ykTvStatusType").equals("onOff"))
      {
        if (this.swich.isToggle())
          localYkVo1.setStatus(getString(R.string.on));
      }
      else if (getIntent().getStringExtra("ykTvStatusType").equals("chanel"))
        localYkVo1.setStatus(this.chanel + getString(2131099929));
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
      break;
      if (this.swich.isToggle())
      {
        localYkVo1.setStatus(getString(R.string.on));
        continue;
      }
      localYkVo1.setStatus(getString(2131100226));
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
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleText(this.ykName);
    setEditTextRes(2131100358, getResources().getColor(2131492897));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.remote.control.scene.AtSimpleYKSetActivity
 * JD-Core Version:    0.6.0
 */