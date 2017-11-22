package com.ex.ltech.LogRegForget;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;

import java.util.Random;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.xlink.wifi.js.util.SharedPreferencesUtil;

public class ActRegisterActivity extends MyBaseActivity
{
  private int appid;
  private String authKey;

  @BindView(R.id.bt_act_reg_seleted)
  Button btActRegSeleted;
  String countryCode;

  @BindView(R.id.et_act_log_psd)
  EditText etActLogPsd;

  @BindView(R.id.et_area)
  EditText etArea;

  @BindView(R.id.phone)
  EditText etPhone;

  @BindView(R.id.verfy_code)
  EditText etVerfyCode;
  boolean isEyesClose = true;
  boolean isNumVerfly;
  boolean isProtocol = true;
  boolean isVerify;
  boolean isZh;

  @BindView(R.id.iv_step)
  ImageView ivStep;
  String mail = "";
  private String mailCode;
  String phone = "";
  String psd = "";
  TextView redBgTextView;
  ImageView regView;

  @BindView(R.id.rl_act_reg_seleted)
  RelativeLayout rlActRegSeleted;

  @BindView(R.id.rl_verfy)
  RelativeLayout rlVerfy;

  @BindView(R.id.rl_area)
  RelativeLayout rl_area;

  @BindView(R.id.rl_phone)
  RelativeLayout rl_phone;

  @BindView(R.id.rl_psd)
  RelativeLayout rl_psd;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (ActRegisterActivity.this.timerTime > 1)
      {
        ActRegisterActivity localActRegisterActivity = ActRegisterActivity.this;
        localActRegisterActivity.timerTime = (-1 + localActRegisterActivity.timerTime);
        ActRegisterActivity.this.timeHandler.postDelayed(ActRegisterActivity.this.runnable, 1000L);
        ActRegisterActivity.this.verifyTime.setText(ActRegisterActivity.this.getString(R.string.resend) + "(" + ActRegisterActivity.this.timerTime + "s)");
        return;
      }
      ActRegisterActivity.this.timerTime = 60;
      ActRegisterActivity.this.verifyTime.setText(ActRegisterActivity.this.getString(R.string.resend));
      ((GradientDrawable)ActRegisterActivity.this.verifyTime.getBackground()).setColor(Color.parseColor("#ff3636"));
      ActRegisterActivity.this.verifyTime.setOnClickListener(new OnClickListener()
      {
        public void onClick(View paramView)
        {
          ActRegisterActivity.this.getVerfyCode(ActRegisterActivity.this.redBgTextView);
          ActRegisterActivity.this.timer();
        }
      });
    }
  };
  private BroadcastReceiver smsReceiver;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 60;

  @BindView(R.id.tv_country_code)
  TextView tvCountryCode;

  @BindView(R.id.tv_verfy_code_send)
  TextView tvVerfyCodeSend;

  @BindView(R.id.verify_time)
  TextView verifyTime;
  RegVo vo;

  private String randomCode()
  {
    String[] arrayOfString = "0,1,2,3,4,5,6,7,8,9".split(",");
    int i = 0;
    for (int j = 0; j < arrayOfString.length; j++)
    {
      i++;
      if (i % 10 == 0);
      System.out.print(arrayOfString[j] + " ");
    }
    Random localRandom = new Random();
    new Scanner(System.in);
    String str = "";
    for (int k = 0; k < 4; k++)
    {
      int m = localRandom.nextInt(-1 + arrayOfString.length);
      str = str + arrayOfString[m];
    }
    System.out.println("验证码：" + str);
    return str;
  }

  private void reg2Server()
  {
    this.vo = new RegVo();
    this.vo.setName("LT");
    this.vo.setUid(this.etPhone.getText().toString());
    this.vo.setPwd(this.etActLogPsd.getText().toString());
    UserFerences.getUserFerences(this).putValue("user", this.vo.getUid());
    UserFerences.getUserFerences(this).putValue("userPsd", this.vo.getPwd());
//    SharedPreferencesUtil.keepShared("COMPANY_ID", HttpManage.COMPANY_ID);
    SharedPreferencesUtil.keepShared("EMAIL_ID", this.vo.getUid());
    SharedPreferencesUtil.keepShared("PASSWD_ID", this.psd);
    /*HttpAgent.getInstance().onRegister(this.vo.getUid(), this.vo.getName(), this.vo.getPwd(), new TextHttpResponseHandler()
    {

      @Override
      public void onFailure(int i, Header[] headers, String s, Throwable throwable) {
        ActRegisterActivity.this.toast(R.string.net_no_ok);
      }

      @Override
      public void onSuccess(int i, Header[] headers, String s) {
        try
        {
          JSONObject localJSONObject1 = new JSONObject(s);
          int response = localJSONObject1.getInt("status");
          if (response == 200)
          {
            JSONObject localJSONObject2 = localJSONObject1.getJSONObject("user");
            SharedPreferencesUtil.keepShared("appId", localJSONObject2.getInt("id"));
            SharedPreferencesUtil.keepShared("authKey", localJSONObject2.getString("key"));
            ActRegisterActivity.this.goAct(DeviceListActivity.class);
            ActRegisterActivity.this.setResult(200);
            ActRegisterActivity.this.finish();
            return;
          }
          if (response == 201)
          {
            ActRegisterActivity.this.toast(R.string.reged);
            return;
          }
        }
        catch (JSONException localJSONException)
        {
          localJSONException.printStackTrace();
        }
      }

    });*/
  }

  private void timer()
  {
    this.timeHandler.removeCallbacks(this.runnable);
    this.timeHandler.postDelayed(this.runnable, 1000L);
    this.verifyTime.setOnClickListener(null);
    ((GradientDrawable)this.verifyTime.getBackground()).setColor(Color.parseColor("#666666"));
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
        break label94;
    }
    label94: for (boolean bool = true; ; bool = false)
    {
      this.isEyesClose = bool;
      return;
      localImageView.setBackgroundResource(R.mipmap.eyes_close);
      this.etActLogPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
      break;
    }*/
  }

  public void getVerfyCode(View paramView)
  {
    /*this.redBgTextView = ((TextView)paramView);
    if (this.redBgTextView.getText().toString().equals(getString(R.string.next)))
      if (this.etPhone.getText().toString().indexOf("@") != -1)
      {
        this.rl_area.setVisibility(View.GONE);
        this.rl_phone.setVisibility(View.GONE);
        this.rlVerfy.setVisibility(View.GONE);
        this.rl_psd.setVisibility(View.VISIBLE);
        this.rlActRegSeleted.setVisibility(View.VISIBLE);
        this.redBgTextView.setText(getString(R.string.finish));
        this.rl_psd.setVisibility(View.VISIBLE);
      }
    do
    {
      do
        return;
      while (!isMobileNO(this.etPhone.getText().toString()));
      if (this.tvCountryCode.getText().toString().length() < 1)
      {
        toast(R.string.seleted_area);
        return;
      }
      this.countryCode = this.tvCountryCode.getText().toString().substring(1, this.tvCountryCode.getText().toString().length());
      this.rl_area.setVisibility(View.GONE);
      this.rl_phone.setVisibility(View.GONE);
      this.isNumVerfly = true;
      this.rlActRegSeleted.setVisibility(View.VISIBLE);
      this.rlVerfy.setVisibility(View.VISIBLE);
      this.rl_psd.setVisibility(View.VISIBLE);
      this.redBgTextView.setText(getString(R.string.finish));
      SMSSDK.initSDK(this, "c2e74330ba24", "daa45b125ea9ecb0968b2a53705ca2eb", false);
      SMSSDK.registerEventHandler(new EventHandler()
      {
        public void afterEvent(int paramInt1, int paramInt2, Object paramObject)
        {
          ActRegisterActivity.this.runOnUiThread(new Runnable(paramInt2, paramInt1)
          {
            public void run()
            {
              if ((this.val$result == -1) && (this.val$event == 3))
              {
                ActRegisterActivity.this.reg2Server();
                SMSSDK.unregisterAllEventHandler();
              }
              if (this.val$result == 0)
                ActRegisterActivity.this.toast(R.string.verify_no_ok);
            }
          });
        }
      });
      SMSSDK.getVerificationCode(this.countryCode, this.etPhone.getText().toString());
      try
      {
        this.smsReceiver = new SMSReceiver(new SMSSDK.VerifyCodeReadListener()
        {
          public void onReadVerifyCode(String paramString)
          {
            ActRegisterActivity.this.runOnUiThread(new Runnable(paramString)
            {
              public void run()
              {
                ActRegisterActivity.this.toast(2131100501);
                ActRegisterActivity.this.etVerfyCode.setText(this.val$verifyCode);
              }
            });
          }
        });
        registerReceiver(this.smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
        this.ivStep.setBackgroundResource(2130903772);
        this.timeHandler.post(this.runnable);
        timer();
        return;
      }
      catch (Throwable localThrowable)
      {
        while (true)
        {
          localThrowable.printStackTrace();
          this.smsReceiver = null;
        }
      }
      if (!this.redBgTextView.getText().toString().equals(getString(2131100418)))
        continue;
      if (this.isNumVerfly)
      {
        SMSSDK.submitVerificationCode(this.countryCode, this.etPhone.getText().toString(), this.etVerfyCode.getText().toString().trim());
        return;
      }
      if (this.etVerfyCode.getText().toString().trim().equals(this.mailCode))
      {
        this.redBgTextView.setText(getString(R.string.finish));
        this.rlActRegSeleted.setVisibility(View.VISIBLE);
        this.rl_psd.setVisibility(View.VISIBLE);
        this.tvVerfyCodeSend.setVisibility(View.GONE);
        this.rlVerfy.setVisibility(View.GONE);
        this.ivStep.setBackgroundResource(2130903773);
        return;
      }
      toast(R.string.verify_no_ok);
      return;
    }
    while ((!this.redBgTextView.getText().toString().equals(getString(R.string.finish))) || (!this.isProtocol));
    if (this.isNumVerfly)
    {
      if (this.etActLogPsd.getText().toString().length() < 6)
      {
        toast(R.string.psw);
        return;
      }
      SMSSDK.submitVerificationCode(this.countryCode, this.etPhone.getText().toString(), this.etVerfyCode.getText().toString().trim());
      return;
    }
    reg2Server();*/
  }

  public void goProtocol(View paramView)
  {
//    goAct(ActProtocol.class);
  }

  public void isProtocol(View paramView)
  {
    /*if (this.isProtocol)
    {
      paramView.setBackgroundResource(2130903277);
      if (this.isProtocol)
        break label39;
    }
    label39: for (boolean bool = true; ; bool = false)
    {
      this.isProtocol = bool;
      return;
      paramView.setBackgroundResource(2130903786);
      break;
    }*/
  }

  public int maiVerifly()
  {
    /*Mail localMail = new Mail("L-Home@ltech.cn", "Lt201511");
    localMail.set_debuggable(false);
    String[] arrayOfString = new String[1];
    arrayOfString[0] = this.mail;
    localMail.set_to(arrayOfString);
    localMail.set_from("L-Home@ltech.cn");
    localMail.set_subject("Lhome app verify code");
    localMail.setBody("Dear " + this.mail + ":\nThanks for registration and use of L-Home. \nPlease enter the code " + this.mailCode + " in L-Home APP,verify and register an account.");
    try
    {
      boolean bool = localMail.send();
      if (bool);
      return 1;
    }
    catch (Exception localException)
    {
    }
    return 1;*/
    return -1;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    /*if (paramInt2 == 200)
    {
      if (!this.isZh)
        break label91;
      this.regView.setBackgroundResource(2130903461);
      this.isVerify = true;
    }
    while (true)
    {
      if (paramInt2 == 500)
      {
        this.etArea.setText(paramIntent.getStringExtra("AREA_KEY "));
        this.tvCountryCode.setText("+" + paramIntent.getStringExtra("AREA_CODE_KEY"));
      }
      return;
      label91: setResult(200);
      finish();
    }*/
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
    setContentView(R.layout.at_reg);
    ButterKnife.bind(this);
    setTitleView();
    this.ivStep.setBackgroundResource(R.mipmap.step_1);
    this.etActLogPsd.setTransformationMethod(PasswordTransformationMethod.getInstance());
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void reg(View paramView)
  {
    /*boolean bool1 = true;
    try
    {
      this.regView = ((ImageView)paramView);
      if (!this.etActLogPsd.getText().toString().equals(this.etActLogPsd.getText().toString().trim()))
      {
        toast(R.string.psw_again);
        return;
      }
      if (!this.isProtocol)
      {
        toast(R.string.no_protocol);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    this.phone = this.etPhone.getText().toString().trim();
    this.psd = this.etActLogPsd.getText().toString().trim();
    boolean bool2;
    if (this.isZh)
    {
      bool2 = isMobileNO(this.phone);
      if (this.psd.length() <= 5)
        break label273;
    }
    while (true)
    {
      if ((bool1 & bool2))
      {
        if (!this.isVerify)
        {
          Intent localIntent2 = new Intent(this, ActVerify.class);
          localIntent2.putExtra("phone", this.phone);
          localIntent2.putExtra("psd", this.psd);
          localIntent2.putExtra("isReg", true);
          startActivityForResult(localIntent2, 0);
          return;
        }
      }
      else
      {
        toast(R.string.input_ok_phone);
        return;
        if (!this.isVerify)
        {
          Intent localIntent1 = new Intent(this, ActVerify.class);
          localIntent1.putExtra("mail", this.phone);
          localIntent1.putExtra("psd", this.psd);
          localIntent1.putExtra("isReg", true);
          startActivityForResult(localIntent1, 0);
        }
      }
      return;
      label273: bool1 = false;
    }*/
  }

  public void seleteArea(View paramView)
  {
//    startActivityForResult(new Intent(this, AtRegAreaActivity.class), 0);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.plug_back);
    setTiTleTextRes(R.string.reg);
    showBottomLine();
    setBgWhite();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActRegisterActivity
 * JD-Core Version:    0.6.0
 */