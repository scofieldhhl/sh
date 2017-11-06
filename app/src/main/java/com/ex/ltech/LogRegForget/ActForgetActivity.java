package com.ex.ltech.LogRegForget;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.ex.ltech.led.R;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.ex.ltech.led.my_view.MyAlertDialog13;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.Scanner;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.SMSSDK.VerifyCodeReadListener;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.util.SharedPreferencesUtil;

public class ActForgetActivity extends MyBaseActivity
{
  String countryCode;

  @BindView(R.id.et_act_log_phone)
  EditText etActLogPhone;

  @BindView(R.id.et_area)
  EditText etArea;

  @BindView(R.id.et_psd_1)
  EditText etPsd1;

  @BindView(R.id.et_psd_2)
  EditText etPsd2;

  @BindView(R.id.verfy_code)
  EditText etVerfyCode;
  boolean isEyesClose1 = true;
  boolean isEyesClose2 = true;
  boolean isNumVerfly;
  boolean isZh;
  String mail;
  String mailCode;
  TextView redBgTextView;

  @BindView(R.id.rl_psd)
  RelativeLayout rlPsd;

  @BindView({R.id.rl_verfy})
  RelativeLayout rlVerfy;
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (ActForgetActivity.this.timerTime > 1)
      {
        ActForgetActivity localActForgetActivity = ActForgetActivity.this;
        localActForgetActivity.timerTime = (-1 + localActForgetActivity.timerTime);
        ActForgetActivity.this.timeHandler.postDelayed(ActForgetActivity.this.runnable, 1000L);
        ActForgetActivity.this.verifyTime.setText(ActForgetActivity.this.getString(R.string.resend) + "(" + ActForgetActivity.this.timerTime + "s)");
        return;
      }
      ActForgetActivity.this.timerTime = 60;
      ActForgetActivity.this.verifyTime.setText(ActForgetActivity.this.getString(R.string.resend));
      ((GradientDrawable)ActForgetActivity.this.verifyTime.getBackground()).setColor(Color.parseColor("#ff3636"));
      ActForgetActivity.this.verifyTime.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ActForgetActivity.this.getVerfyCode(ActForgetActivity.this.redBgTextView);
          ActForgetActivity.this.timer();
        }
      });
    }
  };
  SMSReceiver smsReceiver;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 60;

  @BindView(R.id.tv_country_code)
  TextView tvCountryCode;

  @BindView(R.id.verify_time)
  TextView verifyTime;

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
    return str;
  }

  private void timer()
  {
    this.timeHandler.removeCallbacks(this.runnable);
    this.timeHandler.postDelayed(this.runnable, 1000L);
    this.verifyTime.setOnClickListener(null);
    ((GradientDrawable)this.verifyTime.getBackground()).setColor(Color.parseColor("#666666"));
  }

  public void changePsd(View paramView)
  {
    /*int i = 1;
    String str1 = this.etPsd1.getText().toString().trim();
    String str2 = this.etPsd2.getText().toString().trim();
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
      toast(R.string.psw);
      return;
      j = 0;
      break;
      label73: i = 0;
    }
    label78: if (!str1.equals(str2))
    {
      toast(R.string.no_same_psd);
      return;
    }
    RegVo localRegVo = new RegVo();
    localRegVo.setName("LT");
    localRegVo.setUid(this.etActLogPhone.getText().toString());
    localRegVo.setPwd(str1);
    UserFerences.getUserFerences(this).putValue("user", localRegVo.getUid());
    UserFerences.getUserFerences(this).putValue("userPsd", localRegVo.getPwd());
    HttpAgent.getInstance().onReset(localRegVo.getUid(), localRegVo.getName(), localRegVo.getPwd(), new TextHttpResponseHandler()
    {
      public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
      {
        ActForgetActivity.this.toast(R.string.net_no_ok);
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
            ActForgetActivity.this.goAct(DeviceListActivity.class);
            ActForgetActivity.this.setResult(200);
            ActForgetActivity.this.finish();
            return;
          }
          if (i == 201)
          {
            ActForgetActivity.this.toast(R.string.reged);
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

  public void eyes1(View paramView)
  {
    /*ImageView localImageView = (ImageView)paramView;
    if (this.isEyesClose1)
    {
      localImageView.setBackgroundResource(R.mipmap.eyes_open);
      this.etPsd1.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      Editable localEditable = this.etPsd1.getText();
      if ((localEditable instanceof Spannable))
        Selection.setSelection((Spannable)localEditable, localEditable.length());
      if (this.isEyesClose1)
        break label94;
    }
    label94: for (boolean bool = true; ; bool = false)
    {
      this.isEyesClose1 = bool;
      return;
      localImageView.setBackgroundResource(R.mipmap.eyes_close);
      this.etPsd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
      break;
    }*/
  }

  public void eyes2(View paramView)
  {
    /*ImageView localImageView = (ImageView)paramView;
    if (this.isEyesClose2)
    {
      localImageView.setBackgroundResource(R.mipmap.eyes_open);
      this.etPsd2.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
      Editable localEditable = this.etPsd2.getText();
      if ((localEditable instanceof Spannable))
        Selection.setSelection((Spannable)localEditable, localEditable.length());
      if (this.isEyesClose2)
        break label94;
    }
    label94: for (boolean bool = true; ; bool = false)
    {
      this.isEyesClose2 = bool;
      return;
      localImageView.setBackgroundResource(R.mipmap.eyes_close);
      this.etPsd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
      break;
    }*/
  }

  public void finish(View paramView)
  {
    if (this.isZh)
    {
      if (isMobileNO(this.etActLogPhone.getText().toString().trim()))
      {
        Intent localIntent2 = new Intent(this, ActVerify.class);
        localIntent2.putExtra("phone", this.etActLogPhone.getText().toString().trim());
        localIntent2.putExtra("isReg", false);
        startActivityForResult(localIntent2, 0);
        return;
      }
      toast(R.string.input_ok_phone);
      return;
    }
    Intent localIntent1 = new Intent(this, ActVerify.class);
    localIntent1.putExtra("mail", this.etActLogPhone.getText().toString().trim());
    localIntent1.putExtra("isReg", false);
    startActivityForResult(localIntent1, 0);
  }

  public void getVerfyCode(View paramView)
  {
    this.redBgTextView = ((TextView)paramView);
    /*if (this.etActLogPhone.getText().toString().indexOf("@") != -1)
    {
      this.isNumVerfly = false;
      this.mailCode = randomCode();
      this.mail = this.etActLogPhone.getText().toString();
      new Thread()
      {
        public void run()
        {
          ActForgetActivity.this.maiVerifly();
        }
      }
      .start();
    }
    while (true)
    {
      this.timeHandler.post(this.runnable);
      timer();
      return;
      if (!isMobileNO(this.etActLogPhone.getText().toString()))
        continue;
      if (this.tvCountryCode.getText().toString().length() < 1)
      {
        toast(R.string.seleted_area);
        return;
      }
      this.isNumVerfly = true;
      this.countryCode = this.tvCountryCode.getText().toString().substring(1, this.tvCountryCode.getText().toString().length());
      this.rlVerfy.setVisibility(View.VISIBLE);
      SMSSDK.initSDK(this, "c2e74330ba24", "daa45b125ea9ecb0968b2a53705ca2eb", false);
      SMSSDK.registerEventHandler(new EventHandler()
      {
        public void afterEvent(int paramInt1, int paramInt2, Object paramObject)
        {
          ActForgetActivity.this.runOnUiThread(new Runnable(paramInt2, paramInt1)
          {
            public void run()
            {
              if ((this.val$result == -1) && (this.val$event == 3))
              {
                ActForgetActivity.this.rlVerfy.setVisibility(View.GONE);
                ActForgetActivity.this.rlPsd.setVisibility(View.VISIBLE);
                SMSSDK.unregisterAllEventHandler();
                ActForgetActivity.this.toast(R.string.verify_ok);
              }
              if (this.val$result == 0)
                ActForgetActivity.this.toast(R.string.verify_no_ok);
            }
          });
        }
      });
      SMSSDK.getVerificationCode(this.countryCode, this.etActLogPhone.getText().toString());
      new MyAlertDialog13(this).showSmsSendedDialog();
      try
      {
        this.smsReceiver = new SMSReceiver(new SMSSDK.VerifyCodeReadListener()
        {
          public void onReadVerifyCode(String paramString)
          {
            ActForgetActivity.this.runOnUiThread(new Runnable(paramString)
            {
              public void run()
              {
                ActForgetActivity.this.toast(2131100501);
                ActForgetActivity.this.etVerfyCode.setText(this.val$verifyCode);
              }
            });
          }
        });
        registerReceiver(this.smsReceiver, new IntentFilter("android.provider.Telephony.SMS_RECEIVED"));
      }
      catch (Throwable localThrowable)
      {
        localThrowable.printStackTrace();
        this.smsReceiver = null;
      }
    }*/
  }

  public int maiVerifly()
  {
    Mail localMail = new Mail("L-Home@ltech.cn", "Lt201511");
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
    return 1;
  }

  public void next(View paramView)
  {
    /*if (this.isNumVerfly)
    {
      SMSSDK.submitVerificationCode(this.countryCode, this.etActLogPhone.getText().toString(), this.etVerfyCode.getText().toString().trim());
      return;
    }
    if (this.etVerfyCode.getText().toString().trim().equals(this.mailCode))
    {
      toast(R.string.verify_ok);
      this.redBgTextView.setText(getString(R.string.finish));
      this.rlVerfy.setVisibility(View.GONE);
      this.rlPsd.setVisibility(View.VISIBLE);
      return;
    }*/
    toast(R.string.verify_no_ok);
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if (paramInt2 == 200)
    {
      setResult(200);
      finish();
    }
    if (paramInt2 == 500)
    {
      this.etArea.setText(paramIntent.getStringExtra("AREA_KEY "));
      this.tvCountryCode.setText("+" + paramIntent.getStringExtra("AREA_CODE_KEY"));
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.at_forget_psd);
    this.isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);
    ButterKnife.bind(this);
    setTitleView();
    this.etPsd1.setTransformationMethod(PasswordTransformationMethod.getInstance());
    this.etPsd2.setTransformationMethod(PasswordTransformationMethod.getInstance());
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void seleteArea(View paramView)
  {
    startActivityForResult(new Intent(this, AtRegAreaActivity.class), 0);
  }

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(R.mipmap.plug_back);
    setTiTleTextRes(R.string.find_back_psd);
    showBottomLine();
    setBgWhite();
  }
}
