package com.ex.ltech.ct;

import android.content.Context;
import android.content.SharedPreferences;
import com.ex.ltech.led.BaseBusiness;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.utils.StringUtils;
import com.ex.ltech.led.vo.CtSceneVo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class ColorBussiness extends BaseBusiness
{
  public static String key;
  public List<CtSceneVo> finalVos = new ArrayList();
  List<CtSceneVo> frontVos = new ArrayList();
  public boolean isFristParseData = true;
  List<CtSceneVo> realVos = new ArrayList();
  public List<CtSceneVo> vos;

  public ColorBussiness(Context paramContext)
  {
    super(paramContext);
    try
    {
      key = DeviceListActivity.deviceMacAddress + "CtSceneVos";
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  public void hideEditBtn()
  {
    for (int i = 0; i < 8; i++)
    {
      CtSceneVo localCtSceneVo = (CtSceneVo)this.vos.get(i);
      localCtSceneVo.setIsEdit(false);
      this.vos.remove(i);
      this.vos.add(i, localCtSceneVo);
    }
  }

  public void loadCtSceneVos()
  {
    this.vos = ((List)this.gs.fromJson(this.getter.getString(key, ""), new TypeToken()
    {
    }
    .getType()));
    if (this.vos == null)
    {
      this.vos = new ArrayList();
      CtSceneVo localCtSceneVo1 = new CtSceneVo();
      localCtSceneVo1.setPosi(1);
      localCtSceneVo1.setIcResPosi(0);
      localCtSceneVo1.setName(this.ct.getString(2131099976));
      System.out.println(StringUtils.btye2Str(localCtSceneVo1.getName().getBytes()) + "    hahaha");
      localCtSceneVo1.setBrt(255);
      localCtSceneVo1.setC(0);
      localCtSceneVo1.setW(255);
      this.vos.add(localCtSceneVo1);
      CtSceneVo localCtSceneVo2 = new CtSceneVo();
      localCtSceneVo2.setPosi(2);
      localCtSceneVo2.setIcResPosi(1);
      localCtSceneVo2.setName(this.ct.getString(2131099977));
      System.out.println(StringUtils.btye2Str(localCtSceneVo2.getName().getBytes()) + "     hahaha");
      localCtSceneVo2.setBrt(255);
      localCtSceneVo2.setC(80);
      localCtSceneVo2.setW(175);
      this.vos.add(localCtSceneVo2);
      CtSceneVo localCtSceneVo3 = new CtSceneVo();
      localCtSceneVo3.setPosi(3);
      localCtSceneVo3.setIcResPosi(2);
      localCtSceneVo3.setName(this.ct.getString(2131099978));
      System.out.println(StringUtils.btye2Str(localCtSceneVo3.getName().getBytes()) + "     hahaha");
      localCtSceneVo3.setBrt(255);
      localCtSceneVo3.setC(120);
      localCtSceneVo3.setW(135);
      this.vos.add(localCtSceneVo3);
      CtSceneVo localCtSceneVo4 = new CtSceneVo();
      localCtSceneVo4.setPosi(4);
      localCtSceneVo4.setIcResPosi(3);
      localCtSceneVo4.setName(this.ct.getString(2131099979));
      System.out.println(StringUtils.btye2Str(localCtSceneVo4.getName().getBytes()) + "     hahaha");
      localCtSceneVo4.setBrt(204);
      localCtSceneVo4.setC(135);
      localCtSceneVo4.setW(120);
      this.vos.add(localCtSceneVo4);
      CtSceneVo localCtSceneVo5 = new CtSceneVo();
      localCtSceneVo5.setPosi(5);
      localCtSceneVo5.setIcResPosi(4);
      localCtSceneVo5.setName(this.ct.getString(2131099980));
      System.out.println(StringUtils.btye2Str(localCtSceneVo5.getName().getBytes()) + "    hahaha");
      localCtSceneVo5.setBrt(204);
      localCtSceneVo5.setC(200);
      localCtSceneVo5.setW(55);
      this.vos.add(localCtSceneVo5);
      CtSceneVo localCtSceneVo6 = new CtSceneVo();
      localCtSceneVo6.setPosi(6);
      localCtSceneVo6.setIcResPosi(5);
      localCtSceneVo6.setName(this.ct.getString(2131099981));
      System.out.println(StringUtils.btye2Str(localCtSceneVo6.getName().getBytes()) + "     hahaha");
      localCtSceneVo6.setBrt(178);
      localCtSceneVo6.setC(225);
      localCtSceneVo6.setW(30);
      this.vos.add(localCtSceneVo6);
      CtSceneVo localCtSceneVo7 = new CtSceneVo();
      localCtSceneVo7.setPosi(7);
      localCtSceneVo7.setIcResPosi(6);
      localCtSceneVo7.setName(this.ct.getString(2131099982));
      System.out.println(StringUtils.btye2Str(localCtSceneVo7.getName().getBytes()) + "      hahaha");
      localCtSceneVo7.setBrt(26);
      localCtSceneVo7.setC(255);
      localCtSceneVo7.setW(0);
      this.vos.add(localCtSceneVo7);
      CtSceneVo localCtSceneVo8 = new CtSceneVo();
      localCtSceneVo8.setPosi(8);
      localCtSceneVo8.setIcResPosi(7);
      localCtSceneVo8.setName(this.ct.getString(2131099983));
      System.out.println(StringUtils.btye2Str(localCtSceneVo8.getName().getBytes()) + "       hahaha");
      localCtSceneVo8.setBrt(8);
      localCtSceneVo8.setC(255);
      localCtSceneVo8.setW(0);
      this.vos.add(localCtSceneVo8);
      this.setter.putValue(key, this.gs.toJson(this.vos));
    }
    this.finalVos.addAll(this.vos);
  }

  public void parseData(String paramString)
  {
    String str1 = paramString.substring(12, -4 + paramString.length());
    for (int i = 0; i < 8; i++)
    {
      String str2 = str1.substring(0, 8);
      int j = Integer.valueOf(str2.substring(2, 4), 16).intValue();
      int k = Integer.valueOf(str2.substring(4, 6), 16).intValue();
      int m = Integer.valueOf(str2.substring(6, 8), 16).intValue();
      ((CtSceneVo)this.vos.get(i)).setBrt(j);
      ((CtSceneVo)this.vos.get(i)).setC(k);
      ((CtSceneVo)this.vos.get(i)).setW(m);
      str1 = str1.substring(8, str1.length());
    }
    this.setter.putValue(key, this.gs.toJson(this.vos));
  }

  public void saveCtSceneVo(CtSceneVo paramCtSceneVo, int paramInt)
  {
    this.vos.remove(paramInt);
    this.vos.add(paramInt, paramCtSceneVo);
    this.setter.putValue(key, this.gs.toJson(this.vos));
  }

  public void showEditBtn()
  {
    for (int i = 0; i < 8; i++)
    {
      CtSceneVo localCtSceneVo = (CtSceneVo)this.vos.get(i);
      localCtSceneVo.setIsEdit(true);
      this.vos.remove(i);
      this.vos.add(i, localCtSceneVo);
    }
  }

  public void updateData()
  {
    this.vos.clear();
    this.vos.addAll((List)this.gs.fromJson(this.getter.getString(key, ""), new TypeToken()
    {
    }
    .getType()));
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.ct.ColorBussiness
 * JD-Core Version:    0.6.0
 */