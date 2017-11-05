package io.xlink.wifi.js.http;

import com.ex.ltech.led.MyApp;
import com.google.gson.Gson;
import com.google.gson.internal.Gson.Types;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;
import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.js.util.XlinkUtils;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

public class HttpManage
{
  private static final String AccessToken = "Access-Token";
  private static final String ApplicationJson = "application/json";
  public static String COMPANY_ID = "1007d2ada2c3e800";
  private static final String ContentType = "Content-Type";
  public static String Invalid_Mail = "@invalid.com";
  private static AsyncHttpClient client = new AsyncHttpClient();
  private static HttpManage instance;
  public final String acceptShareUrl = "http://api2.xlink.cn/v2/share/device/accept";
  public final String checkUpdateUrl = "http://app.xlink.cn/v1/user/device/version";
  public final String deleteShareUrl = "http://api2.xlink.cn/v2/share/device/delete/{invite_code}";
  public final String denyShareUrl = "http://api2.xlink.cn/v2/share/device/deny";
  public final String forgetUrl = "http://api2.xlink.cn/v2/user/password/forgot";
  public final String getDatapointsUrl = "http://api2.xlink.cn/v2/product/{product_id}/datapoints";
  public final String getDeviceUrl = "http://api2.xlink.cn/v2/product/{product_id}/device/{device_id}";
  public final String getUserInfoUrl = "http://api2.xlink.cn/v2/user/{user_id}";
  private final String host = "http://api2.xlink.cn";
  public final String loginUrl = "http://api2.xlink.cn/v2/user_auth";
  public final String modifyUserUrl = "http://api2.xlink.cn/v2/user/{user_id}";
  public final String registerUrl = "http://api2.xlink.cn/v2/user_register";
  public final String resetPasswordUrl = "http://api2.xlink.cn/v2/user/password/reset";
  public final String shareDeviceUrl = "http://api2.xlink.cn/v2/share/device";
  public final String shareListUrl = "http://api2.xlink.cn/v2/share/device/list";
  public final String subscribeListUrl = "http://api2.xlink.cn/v2/user/%d/subscribe/devices";
  public final String subscribeUrl = "http://api2.xlink.cn/v2/user/{user_id}/subscribe";
  public final String unsubscribeUrl = "http://api2.xlink.cn/v2/user/{user_id}/unsubscribe";
  public final String upgradeUrl = "http://api2.xlink.cn/v2/upgrade/device";
  public final String verifycodeUrl = "http://api2.xlink.cn/v2/user_register/captcha";

  static
  {
    client.setTimeout(5000);
    client.setConnectTimeout(3000);
  }

  private void delete(String paramString, Map<String, String> paramMap, ResultCallback paramResultCallback)
  {
    Header[] arrayOfHeader = map2Header(paramMap);
    client.delete(MyApp.getApp(), paramString, arrayOfHeader, null, paramResultCallback);
  }

  private void get(String paramString, Map<String, String> paramMap, ResultCallback paramResultCallback)
  {
    Header[] arrayOfHeader = map2Header(paramMap);
    client.get(MyApp.getApp(), paramString, arrayOfHeader, null, paramResultCallback);
  }

  public static HttpManage getInstance()
  {
    if (instance == null)
      instance = new HttpManage();
    return instance;
  }

  private Header[] map2Header(Map<String, String> paramMap)
  {
    Header[] arrayOfHeader;
    if (paramMap == null)
      arrayOfHeader = null;
    while (true)
    {
      return arrayOfHeader;
      arrayOfHeader = new Header[paramMap.size()];
      int i = 0;
      Iterator localIterator = paramMap.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        arrayOfHeader[i] = new XHeader(str, (String)paramMap.get(str));
        i++;
      }
    }
  }

  private StringEntity params2StringEntity(Map<String, String> paramMap)
  {
    try
    {
      StringEntity localStringEntity = new StringEntity(new Gson().toJson(paramMap), "UTF-8");
      return localStringEntity;
    }
    catch (Exception localException)
    {
    }
    return null;
  }

  private void post(String paramString, Map<String, String> paramMap, ResultCallback paramResultCallback)
  {
    StringEntity localStringEntity = params2StringEntity(paramMap);
    client.post(MyApp.getApp(), paramString, localStringEntity, "application/json", paramResultCallback);
  }

  private void post(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, ResultCallback paramResultCallback)
  {
    StringEntity localStringEntity = params2StringEntity(paramMap2);
    Header[] arrayOfHeader = map2Header(paramMap1);
    client.post(MyApp.getApp(), paramString, arrayOfHeader, localStringEntity, "application/json", paramResultCallback);
  }

  private void put(String paramString, Map<String, String> paramMap1, Map<String, String> paramMap2, ResultCallback paramResultCallback)
  {
    StringEntity localStringEntity = params2StringEntity(paramMap2);
    Header[] arrayOfHeader = map2Header(paramMap1);
    client.put(MyApp.getApp(), paramString, arrayOfHeader, localStringEntity, "application/json", paramResultCallback);
  }

  public void acceptShare(String paramString, ResultCallback paramResultCallback)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("invite_code", paramString);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    post("http://api2.xlink.cn/v2/share/device/accept", localHashMap2, localHashMap1, paramResultCallback);
  }

  public void deleteShare(String paramString, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/share/device/delete/{invite_code}".replace("{invite_code}", paramString);
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", MyApp.getApp().getAccessToken());
    delete(str, localHashMap, paramResultCallback);
  }

  public void denyShare(String paramString, ResultCallback paramResultCallback)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("invite_code", paramString);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    post("http://api2.xlink.cn/v2/share/device/deny", localHashMap2, localHashMap1, paramResultCallback);
  }

  public void forgetPasswd(String paramString, ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("email", paramString);
    localHashMap.put("corp_id", COMPANY_ID);
    post("http://api2.xlink.cn/v2/user/password/forgot", localHashMap, paramResultCallback);
  }

  public void getDatapoints(String paramString, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/product/{product_id}/datapoints".replace("{product_id}", paramString);
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", MyApp.getApp().getAccessToken());
    get(str, localHashMap, paramResultCallback);
  }

  public void getDevice(String paramString, int paramInt, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/product/{product_id}/device/{device_id}".replace("{device_id}", paramInt + "").replace("{product_id}", paramString);
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", MyApp.getApp().getAccessToken());
    get(str, localHashMap, paramResultCallback);
  }

  public void getShareList(ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", MyApp.getApp().getAccessToken());
    get("http://api2.xlink.cn/v2/share/device/list", localHashMap, paramResultCallback);
  }

  public void getSubscribeList(int paramInt, ResultCallback paramResultCallback)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    String str = String.format("http://api2.xlink.cn/v2/user/%d/subscribe/devices", arrayOfObject);
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", SharedPreferencesUtil.queryValue("accessToken"));
    get(str, localHashMap, paramResultCallback);
  }

  public void getUserInfo(int paramInt, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/user/{user_id}".replace("{user_id}", paramInt + "");
    HashMap localHashMap = new HashMap();
    localHashMap.put("Access-Token", MyApp.getApp().getAccessToken());
    get(str, localHashMap, paramResultCallback);
  }

  public void login(String paramString1, String paramString2, ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    if (paramString1.indexOf("@") != -1);
    while (true)
    {
      localHashMap.put("email", paramString1);
      localHashMap.put("corp_id", COMPANY_ID);
      localHashMap.put("password", paramString2);
      post("http://api2.xlink.cn/v2/user_auth", localHashMap, paramResultCallback);
      return;
      paramString1 = paramString1 + Invalid_Mail;
    }
  }

  public void modifyUser(int paramInt, String paramString, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/user/{user_id}".replace("{user_id}", paramInt + "");
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("nickname", paramString);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    put(str, localHashMap2, localHashMap1, paramResultCallback);
  }

  public void registerUser(String paramString1, String paramString2, ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    if (paramString1.indexOf("@") != -1);
    for (String str = paramString1; ; str = paramString1 + Invalid_Mail)
    {
      localHashMap.put("email", str);
      localHashMap.put("nickname", paramString1);
      localHashMap.put("corp_id", COMPANY_ID);
      localHashMap.put("password", paramString2);
      localHashMap.put("source", "2");
      post("http://api2.xlink.cn/v2/user_register", localHashMap, paramResultCallback);
      return;
    }
  }

  public void registerUserByPhone(String paramString1, String paramString2, ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", paramString1);
    localHashMap.put("nickname", paramString1);
    localHashMap.put("corp_id", COMPANY_ID);
    localHashMap.put("password", paramString2);
    localHashMap.put("source", "2");
    localHashMap.put("verifycode", "");
    post("http://api2.xlink.cn/v2/user_register", localHashMap, paramResultCallback);
  }

  public void registerUserByVerifycode(String paramString, ResultCallback paramResultCallback)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("phone", paramString);
    localHashMap.put("corp_id", COMPANY_ID);
    localHashMap.put("phone_zone", "+86");
    post("http://api2.xlink.cn/v2/user_register/captcha", localHashMap, paramResultCallback);
  }

  public void resetPassword(String paramString1, String paramString2, ResultCallback paramResultCallback)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("old_password", paramString2);
    localHashMap1.put("new_password", paramString1);
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    put("http://api2.xlink.cn/v2/user/password/reset", localHashMap2, localHashMap1, paramResultCallback);
  }

  public void shareDevice(String paramString, int paramInt, ResultCallback paramResultCallback)
  {
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("user", paramString);
    localHashMap1.put("expire", "7200");
    localHashMap1.put("mode", "email");
    localHashMap1.put("device_id", paramInt + "");
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    post("http://api2.xlink.cn/v2/share/device", localHashMap2, localHashMap1, paramResultCallback);
  }

  public void subscribe(String paramString1, String paramString2, int paramInt, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/user/{user_id}/subscribe".replace("{user_id}", paramString1);
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("product_id", paramString2);
    localHashMap1.put("device_id", paramInt + "");
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    post(str, localHashMap2, localHashMap1, paramResultCallback);
  }

  public void unsubscribe(int paramInt1, int paramInt2, ResultCallback paramResultCallback)
  {
    String str = "http://api2.xlink.cn/v2/user/{user_id}/unsubscribe".replace("{user_id}", paramInt1 + "");
    HashMap localHashMap1 = new HashMap();
    localHashMap1.put("device_id", paramInt2 + "");
    HashMap localHashMap2 = new HashMap();
    localHashMap2.put("Access-Token", MyApp.getApp().getAccessToken());
    post(str, localHashMap2, localHashMap1, paramResultCallback);
  }

  public void upgradeDevice(String paramString1, String paramString2, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("product_id", paramString1);
    localHashMap.put("device_id", paramString2);
    localHashMap.put("type", "");
    localHashMap.put("identify", "");
    JSONObject localJSONObject = XlinkUtils.getJsonObject(localHashMap);
    try
    {
      new StringEntity(localJSONObject.toString(), "UTF-8");
      Header[] arrayOfHeader = new Header[2];
      arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
      arrayOfHeader[1] = new XHeader("Access-Token", SharedPreferencesUtil.queryValue("accessToken"), null);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
        localUnsupportedEncodingException.printStackTrace();
    }
  }

  public static class Error
  {
    private int code;
    private String msg;

    public int getCode()
    {
      return this.code;
    }

    public String getMsg()
    {
      return this.msg;
    }

    public void setCode(int paramInt)
    {
      this.code = paramInt;
    }

    public void setMsg(String paramString)
    {
      this.msg = paramString;
    }
  }

  private static class ErrorEntity
  {
    public Error error = new Error();
  }

  public static abstract class ResultCallback<T> extends TextHttpResponseHandler
  {
    private Gson mGson = new Gson();
    Type mType = getSuperclassTypeParameter(getClass());

    static Type getSuperclassTypeParameter(Class<?> paramClass)
    {
      Type localType = paramClass.getGenericSuperclass();
      System.out.println(localType);
      if ((localType instanceof Class))
        System.out.println(localType);
      return .Gson.Types.canonicalize(((java.lang.reflect.ParameterizedType)localType).getActualTypeArguments()[0]);
    }

    public abstract void onError(Header[] paramArrayOfHeader, Error paramError);

    public void onFailure(int paramInt, Header[] paramArrayOfHeader, String paramString, Throwable paramThrowable)
    {
      if (paramInt > 0)
        try
        {
          onError(paramArrayOfHeader, ((ErrorEntity)this.mGson.fromJson(paramString, ErrorEntity.class)).error);
          return;
        }
        catch (Exception localException)
        {
          ErrorEntity localErrorEntity2 = new ErrorEntity();
          localErrorEntity2.error.setMsg(paramThrowable.getMessage());
          localErrorEntity2.error.setCode(1001001);
          onError(paramArrayOfHeader, localErrorEntity2.error);
          return;
        }
      ErrorEntity localErrorEntity1 = new ErrorEntity();
      localErrorEntity1.error.setMsg(paramThrowable.getMessage());
      localErrorEntity1.error.setCode(1001001);
      onError(paramArrayOfHeader, localErrorEntity1.error);
    }

    public abstract void onSuccess(int paramInt, T paramT);

    public void onSuccess(int paramInt, Header[] paramArrayOfHeader, String paramString)
    {
      if (this.mType == String.class)
      {
        onSuccess(paramInt, paramString);
        return;
      }
      onSuccess(paramInt, this.mGson.fromJson(paramString, this.mType));
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.HttpManage
 * JD-Core Version:    0.6.0
 */