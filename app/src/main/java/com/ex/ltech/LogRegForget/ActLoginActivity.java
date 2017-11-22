package com.ex.ltech.LogRegForget;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.utils.LogTool;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.js.util.XlinkUtils;

public class ActLoginActivity extends MyBaseActivity
{

  @BindView(R.id.et_act_log_phone)
  EditText etActLogPhone;

  @BindView(R.id.et_act_log_psd)
  EditText etActLogPsd;

  @BindView(R.id.et_area)
  EditText etArea;
  boolean isEyesClose = true;
  boolean isZh;
  String phone = "";
  String psd = "";

  @BindView(R.id.tv_act_log_forget)
  TextView tvActLogForget;

  @BindView(R.id.tv_country_code)
  TextView tvCountryCode;
//  RegVo vo = new RegVo();

  private void initTitle()
  {
    setViewTitle();
    setTiTleTextRes(R.string.login);
    setEditTextRes(R.string.reg, Color.parseColor("#ff3636"));
    showBottomLine();
    setBgWhite();
  }

  private void login(String paramString)
  {
    /*LoginApi localLoginApi = new LoginApi();
    localLoginApi.setPlatform(paramString);
    localLoginApi.setOnLoginListener(new OnLoginListener(paramString)
    {
      public boolean onLogin(String paramString, HashMap<String, Object> paramHashMap)
      {
        PlatformDb localPlatformDb = ShareSDK.getPlatform(ActLoginActivity.this, this.val$platformName).getDb();
        String str1 = localPlatformDb.getUserIcon();
        String str2 = localPlatformDb.getUserName();
        UserFerences.getUserFerences(ActLoginActivity.this).putValue("uHeadPath", str1);
        UserFerences.getUserFerences(ActLoginActivity.this).putValue("user", str2);
        ActLoginActivity.this.goAct(DeviceListActivity.class);
        ActLoginActivity.this.finish();
        return true;
      }

      public boolean onRegister(UserInfo paramUserInfo)
      {
        return false;
      }
    });
    localLoginApi.login(this);*/
  }

  public void changeHead(View paramView)
  {
  }

  public void eyes(View paramView)
  {
    /*ImageView localImageView = (ImageView)paramView;
    if (this.isEyesClose)
    {
      localImageView.setBackgroundResource(R.mipmap.eyes_open);
      this.etActLogPsd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      Editable localEditable = this.etActLogPsd.getText();
      if ((localEditable instanceof Spannable))
        Selection.setSelection((Spannable)localEditable, localEditable.length());
      if (this.isEyesClose)
        break label92;
    }
    label92: for (boolean bool = true; ; bool = false)
    {
      this.isEyesClose = bool;
      return;
      localImageView.setBackgroundResource(R.mipmap.eyes_close);
      this.etActLogPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
      break;
    }*/
  }

 /* public void fastLog1(View paramView)
  {
    if (this.isZh)
    {
      login(Wechat.NAME);
      return;
    }
    login(Facebook.NAME);
  }

  public void fastLog2(View paramView)
  {
    if (this.isZh)
    {
      login(QQ.NAME);
      return;
    }
    login(Twitter.NAME);
  }*/

  public void fbLogin(View paramView)
  {
    login("Facebook");
  }

  public void forgetPsw(View paramView)
  {
//    startActivityForResult(new Intent(this, ActForgetActivity.class), 0);
  }

  public void log(View paramView)
  {
    this.phone = this.etActLogPhone.getText().toString().trim();
    this.psd = this.etActLogPsd.getText().toString().trim();
    if ((this.phone.indexOf("@") != -1) || (isMobileNO(this.phone)))
    {
      if (this.psd.length() <= 5)
        toast(R.string.input_ok_phone);
    }
    HttpAgent.getInstance().getAppId(this.phone, this.psd, new TextHttpResponseHandler()
    {
      public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
      {
        ActLoginActivity.this.toast(R.string.net_no_ok);
      }

      public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
      {
        LogTool.d("onSuccess : " + paramString );//onSuccess : {"user":{"id":950902743,"key":"220fa2b3f38c5400"},"status":200}
        try
        {
          JSONObject localJSONObject1 = new JSONObject(paramString);
          if (localJSONObject1.getInt("status") != 200)
          {
            ActLoginActivity.this.toast(R.string.psd_error);
            return;
          }
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("user");
          UserFerences.getUserFerences(ActLoginActivity.this.getApplicationContext()).putValue("user", ActLoginActivity.this.phone);
          SharedPreferencesUtil.keepShared("appId", localJSONObject2.getInt("id"));
          SharedPreferencesUtil.keepShared("authKey", localJSONObject2.getString("key"));
          MyApp.getApp().setAppid(localJSONObject2.getInt("id"));
          MyApp.getApp().setAuth(localJSONObject2.getString("key"));
          ActLoginActivity.this.goAct(DeviceListActivity.class);
          ActLoginActivity.this.finish();
          return;
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
          XlinkUtils.shortTips("用户信息，json解析错误");
        }
      }
    });

  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 200)
      finish();
    if (paramInt2 == 500)
    {
      this.etArea.setText(paramIntent.getStringExtra("AREA_KEY "));
      this.tvCountryCode.setText("+" + paramIntent.getStringExtra("AREA_CODE_KEY"));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.at_login);
    /*ShareSDK.initSDK(this);
    ShareSDK.getPlatformList();*/
    this.isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
    ButterKnife.bind(this);
    if (this.isZh){
      this.tvActLogForget.getPaint().setFlags(8);
      this.tvActLogForget.setText(getString(R.string.forget_psd));
      this.etActLogPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
      initTitle();
      eyes(findViewById(R.id.eyes));
      /*return;
      this.etActLogPhone.setHint(R.string.e_mail);*/
    }
  }

  protected void onEdit()
  {
    super.onEdit();
    reg(null);
  }

  public void qqLogin(View paramView)
  {
    login("QQ");
  }

  public void reg(View paramView)
  {
    startActivityForResult(new Intent(this, ActRegisterActivity.class), 0);
  }

  public void seleteArea(View paramView)
  {
//    startActivityForResult(new Intent(this, AtRegAreaActivity.class), 0);
  }

  public void twiterLogin(View paramView)
  {
    login("Twitter");
  }

  public void wxLogin(View paramView)
  {
    login("QQ");
  }
}
