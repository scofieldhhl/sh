package com.ex.ltech.sharedevice;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.acti.MyBaseActivity;
import io.xlink.wifi.sdk.XDevice;
import io.xlink.wifi.sdk.XlinkAgent;
import io.xlink.wifi.sdk.bean.DataPoint;
import io.xlink.wifi.sdk.bean.EventNotify;
import io.xlink.wifi.sdk.listener.XlinkNetListener;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;

public class testMain extends MyBaseActivity
{
  private static final int DEVICE_ID = 1144505373;
  private static final String PWD = "123456";
  private static final String SHARE_ACCOUNT = "13751753381";

  @Bind({2131559488})
  TextView acceptShareInfo;
  private String accessToken;
  private String account;
  private int appid;
  private String authKey;

  @Bind({2131559482})
  TextView cancelShareDeviceInfo;

  @Bind({2131559487})
  TextView deleteShareInfo;

  @Bind({2131559489})
  TextView denyShareInfo;

  @Bind({2131559490})
  TextView errorMsg;
  private int expire_in;
  private String inviteCode;
  private List<String> inviteCodeList = new ArrayList();

  @Bind({2131559478})
  TextView login_info;
  private String refreshToken;

  @Bind({2131559485})
  TextView shareDeviceList;

  @Bind({2131559480})
  TextView share_info;
  private int type;

  private void cancelShareDeviceRequest(String paramString)
  {
    HttpManage.getInstance().cancelShareDevice(paramString, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testMain.this.cancelShareDeviceInfo.setText("onError" + paramError.toString() + "====");
      }

      public void onSuccess(int paramInt, Map<String, String> paramMap)
      {
        testMain.this.cancelShareDeviceInfo.setText("onsuccess" + paramInt + "====");
      }
    });
  }

  private void deleteShareMsg(String paramString)
  {
    HttpManage.getInstance().deleteShare(paramString, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testMain.this.deleteShareInfo.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, String> paramMap)
      {
        testMain.this.deleteShareInfo.setText("onsuccess:" + paramInt);
      }
    });
  }

  private void listShareDevice()
  {
    HttpManage.getInstance().getShareList(new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testMain.this.shareDeviceList.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, Object>[] paramArrayOfMap)
      {
        testMain.this.inviteCodeList.clear();
        StringBuilder localStringBuilder = new StringBuilder();
        for (int i = 0; i < paramArrayOfMap.length; i++)
        {
          Iterator localIterator = paramArrayOfMap[i].entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            localStringBuilder.append("key:" + (String)localEntry.getKey() + "------value:" + localEntry.getValue() + "\n");
            if (!"invite_code".equals(localEntry.getKey()))
              continue;
            testMain.this.inviteCodeList.add((String)localEntry.getValue());
          }
          localStringBuilder.append("========================================\n");
        }
        testMain.this.shareDeviceList.setText("device:" + localStringBuilder.toString());
      }
    });
  }

  private void logMsg(String paramString)
  {
    Log.i("ppp", "===" + paramString);
  }

  private void login()
  {
    HttpManage.getInstance().login("308584004@qq.com", "123456", new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testMain.this.login_info.setText("===onerror==" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, Object> paramMap)
      {
        testMain.this.logMsg("==onsuccess==" + paramMap.toString());
        testMain.access$202(testMain.this, (String)paramMap.get("authorize"));
        testMain.access$302(testMain.this, (String)paramMap.get("access_token"));
        testMain.access$402(testMain.this, (String)paramMap.get("refresh_token"));
        testMain.access$502(testMain.this, ((Double)paramMap.get("user_id")).intValue());
        testMain.access$602(testMain.this, ((Double)paramMap.get("expire_in")).intValue());
        MyApp.getApp().setAccessToken(testMain.this.accessToken);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("==authKey==:" + testMain.this.authKey + "\n");
        localStringBuilder.append("==accessToken==:" + testMain.this.accessToken + "\n");
        localStringBuilder.append("==refreshToken==:" + testMain.this.refreshToken + "\n");
        localStringBuilder.append("==appid==:" + testMain.this.appid + "\n");
        localStringBuilder.append("==expire_in==:" + testMain.this.expire_in + "\n");
        testMain.this.login_info.setText(localStringBuilder.toString());
        System.out.println(testMain.this.login_info.getText().toString());
      }
    });
  }

  private void shareDevice()
  {
    HttpManage.getInstance().shareDevice("13751753381", 1144505373, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testMain.this.share_info.setText("error:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, Object> paramMap)
      {
        testMain.access$702(testMain.this, (String)paramMap.get("invite_code"));
        testMain.this.share_info.setText("inviteCode:" + testMain.this.inviteCode);
      }
    });
  }

  private void subscribeDevice()
  {
    HttpManage.getInstance().getSubscribeList(this.appid + "", this.accessToken, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        System.out.println("");
      }

      public void onSuccess(int paramInt, String paramString)
      {
        System.out.println("");
      }
    });
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968891);
    ButterKnife.bind(this);
    Intent localIntent = getIntent();
    this.account = localIntent.getStringExtra("account");
    this.type = localIntent.getIntExtra("type", 1);
    XlinkAgent.getInstance().addXlinkListener(new XlinkNetListener()
    {
      public void onDataPointUpdate(XDevice paramXDevice, List<DataPoint> paramList, int paramInt)
      {
      }

      public void onDeviceStateChanged(XDevice paramXDevice, int paramInt)
      {
      }

      public void onDisconnect(int paramInt)
      {
      }

      public void onEventNotify(EventNotify paramEventNotify)
      {
        testMain.this.errorMsg.setText(paramEventNotify.toString() + "==");
      }

      public void onLocalDisconnect(int paramInt)
      {
      }

      public void onLogin(int paramInt)
      {
      }

      public void onRecvPipeData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
      }

      public void onRecvPipeSyncData(short paramShort, XDevice paramXDevice, byte[] paramArrayOfByte)
      {
      }

      public void onStart(int paramInt)
      {
      }
    });
  }

  public void onclick(View paramView)
  {
    switch (paramView.getId())
    {
    case 2131559478:
    case 2131559480:
    case 2131559482:
    case 2131559485:
    default:
      return;
    case 2131559477:
      login();
      return;
    case 2131559479:
      shareDevice();
      return;
    case 2131559483:
      listShareDevice();
      return;
    case 2131559481:
      cancelShareDeviceRequest(this.inviteCode);
      return;
    case 2131559486:
      for (int i = 0; i < this.inviteCodeList.size(); i++)
        deleteShareMsg((String)this.inviteCodeList.get(i));
    case 2131559484:
    }
    subscribeDevice();
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.sharedevice.testMain
 * JD-Core Version:    0.6.0
 */