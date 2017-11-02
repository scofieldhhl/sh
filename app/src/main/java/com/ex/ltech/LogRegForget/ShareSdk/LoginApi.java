package com.ex.ltech.LogRegForget.ShareSdk;

import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import java.util.HashMap;

public class LoginApi
  implements Handler.Callback
{
  private static final int MSG_AUTH_CANCEL = 1;
  private static final int MSG_AUTH_COMPLETE = 3;
  private static final int MSG_AUTH_ERROR = 2;
  private Context context;
  private Handler handler = new Handler(Looper.getMainLooper(), this);
  private OnLoginListener loginListener;
  private String platform;

  public boolean handleMessage(Message paramMessage)
  {
    switch (paramMessage.what)
    {
    default:
    case 1:
    case 2:
    case 3:
    }
    String str;
    HashMap localHashMap;
    do
    {
      return false;
      Toast.makeText(this.context, 2131099891, 0).show();
      return false;
      Throwable localThrowable = (Throwable)paramMessage.obj;
      new StringBuilder().append("caught error: ").append(localThrowable.getMessage()).toString();
      Toast.makeText(this.context, 2131100334, 0).show();
      localThrowable.printStackTrace();
      return false;
      Object[] arrayOfObject = (Object[])(Object[])paramMessage.obj;
      str = (String)arrayOfObject[0];
      localHashMap = (HashMap)arrayOfObject[1];
    }
    while ((this.loginListener == null) || (!this.loginListener.onLogin(str, localHashMap)));
    return false;
  }

  public void login(Context paramContext)
  {
    this.context = paramContext.getApplicationContext();
    if (this.platform == null);
    Platform localPlatform;
    do
    {
      return;
      ShareSDK.initSDK(paramContext);
      localPlatform = ShareSDK.getPlatform(this.platform);
    }
    while (localPlatform == null);
    if (localPlatform.isAuthValid())
    {
      localPlatform.removeAccount(true);
      return;
    }
    localPlatform.SSOSetting(false);
    localPlatform.setPlatformActionListener(new PlatformActionListener()
    {
      public void onCancel(Platform paramPlatform, int paramInt)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 1;
          localMessage.arg2 = paramInt;
          localMessage.obj = paramPlatform;
          LoginApi.this.handler.sendMessage(localMessage);
        }
      }

      public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 3;
          localMessage.arg2 = paramInt;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramPlatform.getName();
          arrayOfObject[1] = paramHashMap;
          localMessage.obj = arrayOfObject;
          LoginApi.this.handler.sendMessage(localMessage);
        }
      }

      public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 2;
          localMessage.arg2 = paramInt;
          localMessage.obj = paramThrowable;
          LoginApi.this.handler.sendMessage(localMessage);
        }
        paramThrowable.printStackTrace();
      }
    });
    localPlatform.showUser(null);
  }

  public void login(Platform paramPlatform)
  {
    this.context = this.context.getApplicationContext();
    if (this.platform == null);
    do
    {
      return;
      ShareSDK.initSDK(this.context);
    }
    while (paramPlatform == null);
    if (paramPlatform.isAuthValid())
    {
      paramPlatform.removeAccount(true);
      return;
    }
    paramPlatform.SSOSetting(false);
    paramPlatform.setPlatformActionListener(new PlatformActionListener()
    {
      public void onCancel(Platform paramPlatform, int paramInt)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 1;
          localMessage.arg2 = paramInt;
          localMessage.obj = paramPlatform;
          LoginApi.this.handler.sendMessage(localMessage);
        }
      }

      public void onComplete(Platform paramPlatform, int paramInt, HashMap<String, Object> paramHashMap)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 3;
          localMessage.arg2 = paramInt;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = paramPlatform.getName();
          arrayOfObject[1] = paramHashMap;
          localMessage.obj = arrayOfObject;
          LoginApi.this.handler.sendMessage(localMessage);
        }
      }

      public void onError(Platform paramPlatform, int paramInt, Throwable paramThrowable)
      {
        if (paramInt == 8)
        {
          Message localMessage = new Message();
          localMessage.what = 2;
          localMessage.arg2 = paramInt;
          localMessage.obj = paramThrowable;
          LoginApi.this.handler.sendMessage(localMessage);
        }
        paramThrowable.printStackTrace();
      }
    });
    paramPlatform.showUser(null);
  }

  public void setOnLoginListener(OnLoginListener paramOnLoginListener)
  {
    this.loginListener = paramOnLoginListener;
  }

  public void setPlatform(String paramString)
  {
    this.platform = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ShareSdk.LoginApi
 * JD-Core Version:    0.6.0
 */