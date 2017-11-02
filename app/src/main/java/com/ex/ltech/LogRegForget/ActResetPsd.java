package com.ex.ltech.LogRegForget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.loopj.android.http.TextHttpResponseHandler;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

public class ActResetPsd extends MyBaseActivity
{

  @Bind({2131558619})
  EditText etActResetPsd1;

  @Bind({2131558620})
  EditText etActResetPsd2;
  boolean isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
  String mail = "";
  String phone = "";

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968619);
    setTitleView();
    ButterKnife.bind(this);
    if (this.isZh)
    {
      this.phone = getIntent().getStringExtra("phone");
      return;
    }
    this.mail = getIntent().getStringExtra("mail");
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
  }

  public void yes(View paramView)
  {
    int i = 1;
    String str1 = this.etActResetPsd1.getText().toString().trim();
    String str2 = this.etActResetPsd2.getText().toString().trim();
    int j;
    if (str1.length() < 6)
    {
      j = i;
      if (str2.length() >= 6)
        break label73;
    }
    while (true)
    {
      if ((j | i) == 0)
        break label78;
      toast(2131100278);
      return;
      j = 0;
      break;
      label73: i = 0;
    }
    label78: if (!str1.equals(str2))
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
      UserFerences.getUserFerences(this).putValue("user", localRegVo.getUid());
      UserFerences.getUserFerences(this).putValue("userPsd", localRegVo.getPwd());
      HttpAgent.getInstance().onReset(localRegVo.getUid(), localRegVo.getName(), localRegVo.getPwd(), new TextHttpResponseHandler()
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
              ActResetPsd.this.goAct(DeviceListActivity.class);
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
 * Qualified Name:     com.ex.ltech.LogRegForget.ActResetPsd
 * JD-Core Version:    0.6.0
 */