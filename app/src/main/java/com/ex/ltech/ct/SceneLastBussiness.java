package com.ex.ltech.ct;

import android.app.Activity;
import android.graphics.BitmapFactory;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.acti.mode.ModeBusiness;
import java.util.ArrayList;

public class SceneLastBussiness extends ModeBusiness
{
  private String modeDataKey = DeviceListActivity.deviceMacAddress + "CtSceneVos";

  public SceneLastBussiness(Activity paramActivity)
  {
    super(paramActivity);
  }

  public void initGalleryData()
  {
    this.reses.add(Integer.valueOf(2130903158));
    this.reses.add(Integer.valueOf(2130903159));
    this.reses.add(Integer.valueOf(2130903160));
    this.reses.add(Integer.valueOf(2130903161));
    this.reses.add(Integer.valueOf(2130903162));
    this.reses.add(Integer.valueOf(2130903163));
    this.reses.add(Integer.valueOf(2130903164));
    this.reses.add(Integer.valueOf(2130903165));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903158));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903159));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903160));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903161));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903162));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903163));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903164));
    this.imgList.add(BitmapFactory.decodeResource(this.pct.getResources(), 2130903165));
    this.modesDefultName.add(this.pct.getString(2131099976));
    this.modesDefultName.add(this.pct.getString(2131099977));
    this.modesDefultName.add(this.pct.getString(2131099978));
    this.modesDefultName.add(this.pct.getString(2131099979));
    this.modesDefultName.add(this.pct.getString(2131099980));
    this.modesDefultName.add(this.pct.getString(2131099981));
    this.modesDefultName.add(this.pct.getString(2131099982));
    this.modesDefultName.add(this.pct.getString(2131099983));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_11));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_22));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_33));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_44));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_55));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_66));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_77));
    this.gradViewDefultName.add(this.pct.getString(R.string.mode_text_88));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.ct.SceneLastBussiness
 * JD-Core Version:    0.6.0
 */