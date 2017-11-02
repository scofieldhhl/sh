package com.ex.ltech.LogRegForget;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.SMSSDK.VerifyCodeReadListener;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.led.acti.MyBaseActivity;
import com.ex.ltech.led.acti.main.DeviceListActivity;
import com.loopj.android.http.TextHttpResponseHandler;
import io.xlink.wifi.js.http.HttpAgent;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

@Deprecated
public class ActVerify extends MyBaseActivity
{
  boolean isReg;
  boolean isZh = UserFerences.getUserFerences(this).spFerences.getBoolean("isZh", true);

  @Bind({2131558645})
  ImageView ivActVerifyOk;
  String mail = "";
  private String mailCode;
  String phone = "";
  String psd = "";
  Runnable runnable = new Runnable()
  {
    public void run()
    {
      if (ActVerify.this.timerTime > 1)
      {
        ActVerify localActVerify = ActVerify.this;
        localActVerify.timerTime = (-1 + localActVerify.timerTime);
        ActVerify.this.timeHandler.postDelayed(ActVerify.this.runnable, 1000L);
        ActVerify.this.tvActVerifyTime.setText(ActVerify.this.getString(2131100338) + "(" + ActVerify.this.timerTime + "s)");
        return;
      }
      ActVerify.this.timerTime = 60;
      ActVerify.this.tvActVerifyTime.setText(ActVerify.this.getString(2131100338));
      ActVerify.this.tvActVerifyTime.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          ActVerify.this.smsVerify();
          ActVerify.this.timer();
        }
      });
    }
  };
  private BroadcastReceiver smsReceiver;
  Handler timeHandler = new Handler()
  {
  };
  int timerTime = 60;
  private TextView tvActVerifyTime;
  private TextView tv_act_verify_code;
  private TextView tv_act_verify_info;

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

  private void smsVerify()
  {
    SMSSDK.initSDK(this, "c2e74330ba24", "daa45b125ea9ecb0968b2a53705ca2eb", false);
    SMSSDK.registerEventHandler(new EventHandler()
    {
      public void afterEvent(int paramInt1, int paramInt2, Object paramObject)
      {
        ActVerify.this.runOnUiThread(new Runnable(paramInt2, paramInt1)
        {
          public void run()
          {
            if ((this.val$result == -1) && (this.val$event == 3))
            {
              SMSSDK.unregisterAllEventHandler();
              ActVerify.this.toast(2131100499);
              if (!ActVerify.this.isReg)
                break label74;
              ActVerify.this.reg();
            }
            while (true)
            {
              if (this.val$result == 0)
                ActVerify.this.toast(2131100498);
              return;
              label74: Intent localIntent = new Intent(ActVerify.this, ActResetPsd.class);
              localIntent.putExtra("phone", ActVerify.this.phone);
              ActVerify.this.startActivity(localIntent);
              ActVerify.this.setResult(200);
              ActVerify.this.finish();
            }
          }
        });
      }
    });
    SMSSDK.getVerificationCode("86", this.phone);
    try
    {
      this.smsReceiver = new SMSReceiver(new SMSSDK.VerifyCodeReadListener()
      {
        public void onReadVerifyCode(String paramString)
        {
          ActVerify.this.runOnUiThread(new Runnable(paramString)
          {
            public void run()
            {
              ActVerify.this.toast(2131100501);
              ActVerify.this.tv_act_verify_code.setText(this.val$verifyCode);
              SMSSDK.submitVerificationCode("86", ActVerify.this.phone, ActVerify.this.tv_act_verify_code.getText().toString().trim());
            }
          });
        }
      });
      return;
    }
    catch (Throwable localThrowable)
    {
      localThrowable.printStackTrace();
      this.smsReceiver = null;
    }
  }

  private void timer()
  {
    this.timeHandler.postDelayed(this.runnable, 1000L);
    this.tvActVerifyTime.setOnClickListener(null);
  }

  public int maiVerifyl()
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

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968625);
    ButterKnife.bind(this);
    this.tv_act_verify_code = ((TextView)findViewById(2131558643));
    this.tv_act_verify_info = ((TextView)findViewById(2131558642));
    this.tvActVerifyTime = ((TextView)findViewById(2131558644));
    this.isReg = getIntent().getBooleanExtra("isReg", true);
    if (this.isReg)
    {
      this.ivActVerifyOk.setBackgroundResource(2130903750);
      if (!this.isZh)
        break label184;
      this.tv_act_verify_info.setHint(2131100500);
      this.phone = getIntent().getStringExtra("phone");
      this.psd = getIntent().getStringExtra("psd");
      smsVerify();
    }
    while (true)
    {
      timer();
      this.ivActVerifyOk.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramView)
        {
          if (ActVerify.this.isReg)
            if (ActVerify.this.isZh)
              SMSSDK.submitVerificationCode("86", ActVerify.this.phone, ActVerify.this.tv_act_verify_code.getText().toString().trim());
          do
          {
            do
              return;
            while (!ActVerify.this.tv_act_verify_code.getText().toString().equals(ActVerify.this.mailCode));
            ActVerify.this.toast(2131100499);
            ActVerify.this.reg();
            return;
            if (!ActVerify.this.isZh)
              continue;
            SMSSDK.submitVerificationCode("86", ActVerify.this.phone, ActVerify.this.tv_act_verify_code.getText().toString().trim());
            return;
          }
          while (!ActVerify.this.tv_act_verify_code.getText().toString().equals(ActVerify.this.mailCode));
          ActVerify.this.toast(2131100499);
          Intent localIntent = new Intent(ActVerify.this, ActResetPsd.class);
          localIntent.putExtra("mail", ActVerify.this.mail);
          ActVerify.this.startActivity(localIntent);
        }
      });
      setTitleView();
      this.mailCode = randomCode();
      return;
      this.ivActVerifyOk.setBackgroundResource(2130903750);
      break;
      label184: this.mail = getIntent().getStringExtra("mail");
      this.psd = getIntent().getStringExtra("psd");
      this.tv_act_verify_info.setHint(2131100497);
      new Thread()
      {
        public void run()
        {
          ActVerify.this.maiVerifyl();
        }
      }
      .start();
    }
  }

  protected void onMenu()
  {
    super.onMenu();
    finish();
  }

  public void reg()
  {
    RegVo localRegVo = new RegVo();
    localRegVo.setName("LT");
    if (this.isZh)
      localRegVo.setUid(this.phone);
    while (true)
    {
      localRegVo.setPwd(this.psd);
      UserFerences.getUserFerences(this).putValue("user", localRegVo.getUid());
      UserFerences.getUserFerences(this).putValue("userPsd", this.psd);
      HttpAgent.getInstance().onRegister(localRegVo.getUid(), localRegVo.getName(), localRegVo.getPwd(), new TextHttpResponseHandler()
      {
        public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
        {
          ActVerify.this.toast(2131100201);
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
              ActVerify.this.goAct(DeviceListActivity.class);
              ActVerify.this.setResult(200);
              ActVerify.this.finish();
              return;
            }
            if (i == 201)
            {
              ActVerify.this.toast(2131100321);
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

  public void setTitleView()
  {
    setViewTitle();
    setMenuBackgroundRes(2130903623);
    setTiTleTextRes(2131100410);
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ActVerify
 * JD-Core Version:    0.6.0
 */