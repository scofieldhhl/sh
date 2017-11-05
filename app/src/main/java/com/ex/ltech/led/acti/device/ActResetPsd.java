package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.LogRegForget.RegVo;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.loopj.android.http.TextHttpResponseHandler;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class ActResetPsd extends MyBaseActivity
{

  @Bind({2131558622})
  EditText etActReset2Psd1;

  @Bind({2131558623})
  EditText etActReset2Psd2;

  @Bind({2131558624})
  EditText etActReset2Psd3;
  boolean isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
  String mail = "";
  String phone = "";

  private void setMyTitle()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.back_ic);
    setTiTleTextRes(2131100393);
    setBgAlpha();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968620);
    setMyTitle();
    ButterKnife.bind(this);
    if (this.isZh)
      this.phone = getIntent().getStringExtra("phone");
    while (true)
    {
      setTitleView();
      return;
      this.mail = getIntent().getStringExtra("mail");
    }
  }

  protected void onEdit()
  {
    super.onEdit();
    yes(null);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(2131100344);
    setEditStrColor(getResources().getColor(R.color.oringe));
    setEditStrRes(2131100530);
    setEditTextRes(2131100530);
  }

  public void yes(View paramView)
  {
    int i = 1;
    if (!this.etActReset2Psd1.getText().toString().equals(UserFerences.getUserFerences(this).spFerences.getString("userPsd", "")))
    {
      toast(2131100010);
      return;
    }
    String str1 = this.etActReset2Psd2.getText().toString().trim();
    String str2 = this.etActReset2Psd3.getText().toString().trim();
    int j;
    if (str1.length() < 6)
    {
      j = i;
      if (str2.length() >= 6)
        break label112;
    }
    while (true)
    {
      if ((j | i) == 0)
        break label117;
      toast(2131100278);
      return;
      j = 0;
      break;
      label112: i = 0;
    }
    label117: if (!str1.equals(str2))
    {
      toast(2131100218);
      return;
    }
    RegVo localRegVo = new RegVo();
    localRegVo.setName("LT");
    if (this.isZh)
      localRegVo.setUid(this.phone);
    while (true)
    {
      localRegVo.setPwd(str1);
      HttpAgent.getInstance().onReset(UserFerences.getUserFerences(this).spFerences.getString("user", ""), localRegVo.getName(), localRegVo.getPwd(), new TextHttpResponseHandler()
      {
        public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
        {
          ActResetPsd.this.toast(2131100201);
        }

        public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
        {
          try
          {
            JSONObject localJSONObject1 = new JSONObject(paramString);
            int i = localJSONObject1.getInt("status");
            if (i == 200)
            {
              JSONObject localJSONObject2 = localJSONObject1.getJSONObject("user");
              SharedPreferencesUtil.keepShared("appId", localJSONObject2.getInt("id"));
              SharedPreferencesUtil.keepShared("authKey", localJSONObject2.getString("key"));
              ActResetPsd.this.toast(2131100335);
              ActResetPsd.this.finish();
              return;
            }
            if (i == 201)
            {
              ActResetPsd.this.toast(2131100321);
              return;
            }
          }
          catch (JSONException localJSONException)
          {
            localJSONException.printStackTrace();
          }
        }
      });
      return;
      localRegVo.setUid(this.mail);
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.ActResetPsd
 * JD-Core Version:    0.6.0
 */