package com.ex.ltech.led.wxapi;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.WXAppExtendObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;


public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
  public void onGetMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
    startActivity(getPackageManager().getLaunchIntentForPackage(getPackageName()));
  }

  public void onShowMessageFromWXReq(WXMediaMessage paramWXMediaMessage)
  {
    if ((paramWXMediaMessage != null) && (paramWXMediaMessage.mediaObject != null) && ((paramWXMediaMessage.mediaObject instanceof WXAppExtendObject)))
      Toast.makeText(this, ((WXAppExtendObject)paramWXMediaMessage.mediaObject).extInfo, 0).show();
  }

  @Override
  public void onReq(BaseReq baseReq) {

  }

  @Override
  public void onResp(BaseResp baseResp) {

  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.led.wxapi.WXEntryActivity
 * JD-Core Version:    0.6.0
 */