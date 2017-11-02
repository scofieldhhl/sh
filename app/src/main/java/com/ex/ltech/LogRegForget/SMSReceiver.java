package com.ex.ltech.LogRegForget;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import cn.smssdk.SMSSDK;
import cn.smssdk.SMSSDK.VerifyCodeReadListener;

public class SMSReceiver extends BroadcastReceiver
{
  private static final String ACTION_SMS_RECEIVER = "android.provider.Telephony.SMS_RECEIVED";
  private SMSSDK.VerifyCodeReadListener listener;

  public SMSReceiver()
  {
  }

  public SMSReceiver(SMSSDK.VerifyCodeReadListener paramVerifyCodeReadListener)
  {
    this.listener = paramVerifyCodeReadListener;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    if ("android.provider.Telephony.SMS_RECEIVED".equals(paramIntent.getAction()))
    {
      Bundle localBundle = paramIntent.getExtras();
      if (localBundle != null)
      {
        Object[] arrayOfObject = (Object[])(Object[])localBundle.get("pdus");
        SmsMessage[] arrayOfSmsMessage = new SmsMessage[arrayOfObject.length];
        for (int i = 0; i < arrayOfObject.length; i++)
          arrayOfSmsMessage[i] = SmsMessage.createFromPdu((byte[])(byte[])arrayOfObject[i]);
        int j = arrayOfSmsMessage.length;
        for (int k = 0; k < j; k++)
        {
          SmsMessage localSmsMessage = arrayOfSmsMessage[k];
          if (localSmsMessage == null)
            continue;
          SMSSDK.readVerificationCode(localSmsMessage, this.listener);
        }
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.SMSReceiver
 * JD-Core Version:    0.6.0
 */