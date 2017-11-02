package com.ex.ltech.led.acti.device;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.ex.ltech.led.Global;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;

public class AtCfg3Activity extends MyBaseActivity
{
  private TextView btn_acti_net_config_device_connet;
  private EditText et_acti_net_config_wifi_name;
  boolean isEyesClose = true;
  private EditText tv_acti_net_config_pwd;
  private TextView tv_remenber_tag;

  private void findView()
  {
    this.et_acti_net_config_wifi_name = ((EditText)findViewById(2131558732));
    this.et_acti_net_config_wifi_name.setText(getSSid());
    this.tv_acti_net_config_pwd = ((EditText)findViewById(2131558733));
    this.btn_acti_net_config_device_connet = ((TextView)findViewById(2131558736));
    this.tv_remenber_tag = ((TextView)findViewById(2131558735));
    this.btn_acti_net_config_device_connet.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramView)
      {
        if (AtCfg3Activity.this.tv_acti_net_config_pwd.getText() != null)
        {
          Intent localIntent = new Intent(AtCfg3Activity.this, AtCfg4Activity.class);
          localIntent.putExtra("wifi", AtCfg3Activity.this.et_acti_net_config_wifi_name.getText().toString());
          localIntent.putExtra("psd", AtCfg3Activity.this.tv_acti_net_config_pwd.getText().toString());
          AtCfg3Activity.this.startActivityForResult(localIntent, 200);
        }
      }
    });
    setViewTitle();
    setMenuBackgroundRes(2130903274);
    setTiTleTextRes(2131100200);
  }

  private void init()
  {
    this.tv_acti_net_config_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
    this.tv_acti_net_config_pwd.setText(UserFerences.getUserFerences(this).spFerences.getString("wifiPsd", ""));
    showIsRemenberPsd();
    this.isEyesClose = UserFerences.getUserFerences(this).spFerences.getBoolean("wifiPsdEye", false);
  }

  private void showEyes(ImageView paramImageView)
  {
    if (this.isEyesClose)
    {
      paramImageView.setBackgroundResource(2130903243);
      this.tv_acti_net_config_pwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      return;
    }
    paramImageView.setBackgroundResource(2130903242);
    this.tv_acti_net_config_pwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
  }

  public void addMd5(View paramView)
  {
  }

  public void eyes(View paramView)
  {
    showEyes((ImageView)paramView);
    Editable localEditable = this.tv_acti_net_config_pwd.getText();
    if ((localEditable instanceof Spannable))
      Selection.setSelection((Spannable)localEditable, localEditable.length());
    UserFerences.getUserFerences(this).putValue("wifiPsdEye", Boolean.valueOf(this.isEyesClose));
    if (!this.isEyesClose);
    for (boolean bool = true; ; bool = false)
    {
      this.isEyesClose = bool;
      return;
    }
  }

  public String getSSid()
  {
    WifiManager localWifiManager = (WifiManager)getSystemService("wifi");
    if (localWifiManager != null)
    {
      WifiInfo localWifiInfo = localWifiManager.getConnectionInfo();
      if (localWifiInfo != null)
      {
        String str = localWifiInfo.getSSID();
        if (((str != null) || (str.length() > 0)) && (str.length() > 2) && (str.charAt(0) == '"') && (str.charAt(-1 + str.length()) == '"'))
          str = str.substring(1, -1 + str.length());
        return str;
      }
    }
    return "";
  }

  public void goReset(View paramView)
  {
    startActivityForResult(new Intent(this, AtCfg2Activity.class).putExtra("cfgType", getIntent().getStringExtra("cfgType")).putExtra("isReset2", true), 0);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == Global.net_config_ok)
    {
      setResult(Global.net_config_ok);
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968636);
    findView();
    init();
    eyes(findViewById(2131558734));
  }

  protected void onEdit()
  {
    super.onEdit();
    Intent localIntent = new Intent(getApplicationContext(), ActSimpleReadmeInfoActivity.class);
    localIntent.putExtra("page", 8);
    startActivity(localIntent);
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void remenberPsd(View paramView)
  {
    UserFerences localUserFerences = UserFerences.getUserFerences(this);
    boolean bool1 = UserFerences.getUserFerences(this).spFerences.getBoolean("isRemenberPsd", false);
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    localUserFerences.putValue("isRemenberPsd", Boolean.valueOf(bool2));
    showIsRemenberPsd();
  }

  public void showIsRemenberPsd()
  {
    if (UserFerences.getUserFerences(this).spFerences.getBoolean("isRemenberPsd", false))
    {
      this.tv_remenber_tag.setBackgroundResource(2130903674);
      UserFerences.getUserFerences(this).putValue("wifiPsd", this.tv_acti_net_config_pwd.getText().toString());
      return;
    }
    this.tv_remenber_tag.setBackgroundResource(2130903277);
    UserFerences.getUserFerences(this).putValue("wifiPsd", "");
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.acti.device.AtCfg3Activity
 * JD-Core Version:    0.6.0
 */