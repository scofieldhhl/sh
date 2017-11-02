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

public class testSLAVE extends MyBaseActivity
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

  private void acceptShareDevice(String paramString)
  {
    HttpManage.getInstance().acceptShare(paramString, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testSLAVE.this.acceptShareInfo.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, String> paramMap)
      {
        testSLAVE.this.acceptShareInfo.setText("onsuccess:" + paramInt);
      }
    });
  }

  private void deleteShareMsg(String paramString)
  {
    HttpManage.getInstance().deleteShare(paramString, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testSLAVE.this.deleteShareInfo.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, String> paramMap)
      {
        testSLAVE.this.deleteShareInfo.setText("onsuccess:" + paramInt);
      }
    });
  }

  private void denyShareDevice(String paramString)
  {
    HttpManage.getInstance().denyShare(paramString, new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testSLAVE.this.denyShareInfo.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, String> paramMap)
      {
        testSLAVE.this.denyShareInfo.setText("onsuccess:" + paramInt);
      }
    });
  }

  private void listShareDevice()
  {
    HttpManage.getInstance().getShareList(new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testSLAVE.this.shareDeviceList.setText("onError:" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, Object>[] paramArrayOfMap)
      {
        testSLAVE.this.inviteCodeList.clear();
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
            testSLAVE.this.inviteCodeList.add((String)localEntry.getValue());
          }
          localStringBuilder.append("========================================\n");
        }
        testSLAVE.this.shareDeviceList.setText("device:" + localStringBuilder.toString());
      }
    });
  }

  private void logMsg(String paramString)
  {
    Log.i("ppp", "===" + paramString);
  }

  private void login()
  {
    HttpManage.getInstance().login("13751753381", "123456", new HttpManage.ResultCallback()
    {
      public void onError(Header[] paramArrayOfHeader, HttpManage.Error paramError)
      {
        testSLAVE.this.login_info.setText("===onerror==" + paramError.toString());
      }

      public void onSuccess(int paramInt, Map<String, Object> paramMap)
      {
        testSLAVE.this.logMsg("==onsuccess==" + paramMap.toString());
        testSLAVE.access$202(testSLAVE.this, (String)paramMap.get("authorize"));
        testSLAVE.access$302(testSLAVE.this, (String)paramMap.get("access_token"));
        testSLAVE.access$402(testSLAVE.this, (String)paramMap.get("refresh_token"));
        testSLAVE.access$502(testSLAVE.this, ((Double)paramMap.get("user_id")).intValue());
        testSLAVE.access$602(testSLAVE.this, ((Double)paramMap.get("expire_in")).intValue());
        MyApp.getApp().setAccessToken(testSLAVE.this.accessToken);
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("==authKey==:" + testSLAVE.this.authKey + "\n");
        localStringBuilder.append("==accessToken==:" + testSLAVE.this.accessToken + "\n");
        localStringBuilder.append("==refreshToken==:" + testSLAVE.this.refreshToken + "\n");
        localStringBuilder.append("==appid==:" + testSLAVE.this.appid + "\n");
        localStringBuilder.append("==expire_in==:" + testSLAVE.this.expire_in + "\n");
        System.out.println(testSLAVE.this.login_info.getText().toString());
      }
    });
  }

  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2130968893);
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
        testSLAVE.this.errorMsg.setText(paramEventNotify.toString() + "==");
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
    default:
    case 2131559477:
    case 2131559483:
    case 2131559486:
    case 2131559509:
    case 2131559510:
    }
    while (true)
    {
      return;
      login();
      return;
      listShareDevice();
      return;
      for (int k = 0; k < this.inviteCodeList.size(); k++)
        deleteShareMsg((String)this.inviteCodeList.get(k));
      continue;
      for (int j = 0; j < this.inviteCodeList.size(); j++)
        acceptShareDevice((String)this.inviteCodeList.get(j));
      continue;
      for (int i = 0; i < this.inviteCodeList.size(); i++)
        denyShareDevice((String)this.inviteCodeList.get(i));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.sharedevice.testSLAVE
 * JD-Core Version:    0.6.0
 */