package io.xlink.wifi.js.http;

import android.content.Context;

import com.ex.ltech.led.MyApp;
import com.ex.ltech.led.UserFerences;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.HashMap;

import io.xlink.wifi.js.util.SharedPreferencesUtil;
import io.xlink.wifi.js.util.XlinkUtils;
import io.xlink.wifi.sdk.XDevice;

public class HttpAgent
{
  public static String ACCESS_ID;
  private static final String AccessID = "X-AccessId";
  private static final String AccessToken = "Access-Token";
  private static final String ApplicationJson = "application/json";
  private static final String CallToken = "调用凭证";
  private static final String ContentType = "Content-Type";
  public static String SECRET_KEY = "9fe31b61602e4081bdec477bfb1adb23";
  private static final String X_ContentMD5 = "X-ContentMD5";
  private static final String X_Sign = "X-Sign";
  private static AsyncHttpClient client;
  private static HttpAgent instance;
  public final String UPGRAGE_DEVICE = "http://api2.xlink.cn/v2/upgrade/device";
  private String corp_id = "1007d2ada2c3e800";
  public final String getUserAuthUrl = "http://app.xlink.cn/v2/user_auth";
  public final String getUserUrl = "http://app.xlink.cn/v2/users";
  public final String loginUrl = "http://app.xlink.cn/v1/user/login";
  private final String new_url = "http://admin.xlink.cn";
  public final String registerUrl = "http://app.xlink.cn/v1/user/register";
  public final String resetUrl = "http://app.xlink.cn/v1/user/reset";
  public final String shareDeviceUrl = "http://app.xlink.cn/v2/share/device";
  private final String url = "http://app.xlink.cn";
  public final String userDeviceUrl = "http://app.xlink.cn/v2/share/device/list";

  static
  {
    ACCESS_ID = "d49fe1a2088b4d3eb531a172fccc31e4";
  }

  private HttpAgent()
  {
    client = new AsyncHttpClient();
  }

  public static final String MD5(String paramString)
  {
    char[] arrayOfChar1 = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70 };
    try
    {
      byte[] arrayOfByte1 = paramString.getBytes();
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      localMessageDigest.update(arrayOfByte1);
      byte[] arrayOfByte2 = localMessageDigest.digest();
      int i = arrayOfByte2.length;
      char[] arrayOfChar2 = new char[i * 2];
      int j = 0;
      int k = 0;
      while (j < i)
      {
        int m = arrayOfByte2[j];
        int n = k + 1;
        arrayOfChar2[k] = arrayOfChar1[(0xF & m >>> 4)];
        k = n + 1;
        arrayOfChar2[n] = arrayOfChar1[(m & 0xF)];
        j++;
      }
      String str = new String(arrayOfChar2);
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  private Header[] getHeaders(String paramString)
  {
    Header[] arrayOfHeader = new Header[3];
    arrayOfHeader[0] = new XHeader("X-AccessId", ACCESS_ID, null);
    String str = MD5(paramString.toString());
    arrayOfHeader[1] = new XHeader("X-ContentMD5", str, null);
    arrayOfHeader[2] = getSign(str);
    return arrayOfHeader;
  }

  public static HttpAgent getInstance()
  {
    if (instance == null)
      instance = new HttpAgent();
    return instance;
  }

  private XHeader getSign(String paramString)
  {
    return new XHeader("X-Sign", MD5(SECRET_KEY + paramString), null);
  }

  public void get(String paramString, Header[] paramArrayOfHeader, RequestParams paramRequestParams, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    client.get(MyApp.getApp(), paramString, paramArrayOfHeader, paramRequestParams, paramAsyncHttpResponseHandler);
  }

  public void getAppId(String paramString1, String paramString2, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString1);
    localHashMap.put("pwd", paramString2);
    JSONObject localJSONObject = XlinkUtils.getJsonObject(localHashMap);
    try
    {
      StringEntity localStringEntity1 = new StringEntity(localJSONObject.toString(), "UTF-8");
      post("http://app.xlink.cn/v1/user/login", getHeaders(localJSONObject.toString()), localStringEntity1, paramTextHttpResponseHandler);
    }
    catch (Exception localUnsupportedEncodingException)
    {
        localUnsupportedEncodingException.printStackTrace();
    }
  }

  public void onGetCompanyAuth(Context paramContext, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    /*try
    {
      String str1 = UserFerences.getUserFerences(paramContext).spFerences.getString("user", "");
      String str2 = UserFerences.getUserFerences(paramContext).spFerences.getString("userPsd", "");
      PhoneUserAuthReqsVo localPhoneUserAuthReqsVo;
      if (UserFerences.getUserFerences(paramContext).spFerences.getBoolean("isZh", true))
      {
        localPhoneUserAuthReqsVo = new PhoneUserAuthReqsVo();
        localPhoneUserAuthReqsVo.setCorp_id("");
        localPhoneUserAuthReqsVo.setPhone(str1);
        localPhoneUserAuthReqsVo.setPassword(str2);
      }
      JSONObject localJSONObject;
      for (Object localObject = new JSONObject(new Gson().toJson(localPhoneUserAuthReqsVo)); ; localObject = localJSONObject)
      {
        StringEntity localStringEntity2 = new StringEntity(((JSONObject)localObject).toString(), "UTF-8");
        Header[] arrayOfHeader = new Header[1];
        arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
        post("http://app.xlink.cn/v2/user_auth", arrayOfHeader, localStringEntity2, paramTextHttpResponseHandler);
        return;
        MailUserAuthReqsVo localMailUserAuthReqsVo = new MailUserAuthReqsVo();
        localMailUserAuthReqsVo.setCorp_id("");
        localMailUserAuthReqsVo.setPhone(str1);
        localMailUserAuthReqsVo.setPassword(str2);
        localJSONObject = new JSONObject(new Gson().toJson(localMailUserAuthReqsVo));
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        StringEntity localStringEntity1 = null;
      }
    }*/
  }

  public void onGetDevice$Users(Context paramContext, String paramString, XDevice paramXDevice, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    RequestParams localRequestParams = new RequestParams();
    Header[] arrayOfHeader = new Header[2];
    arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
    arrayOfHeader[1] = new XHeader("Access-Token", UserFerences.getUserFerences(paramContext).spFerences.getString(UserFerences.SHARE_TOKEN, ""), null);
    get("/v2/user/" + paramString + "/subscribe_users?device=" + paramXDevice.getDeviceId(), arrayOfHeader, localRequestParams, paramTextHttpResponseHandler);
  }

  public void onGetShareDevice(String paramString, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    Header[] arrayOfHeader = new Header[2];
    arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
    arrayOfHeader[1] = new XHeader("Access-Token", paramString, null);
    get("http://app.xlink.cn/v2/share/device/list", arrayOfHeader, null, paramTextHttpResponseHandler);
  }

  // ERROR //
  public void onGetUsers(TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    // Byte code:
    //   0: new 320	io/xlink/wifi/js/bean/UserReqsVo
    //   3: dup
    //   4: invokespecial 321	io/xlink/wifi/js/bean/UserReqsVo:<init>	()V
    //   7: astore_2
    //   8: aload_2
    //   9: new 323	java/util/ArrayList
    //   12: dup
    //   13: invokespecial 324	java/util/ArrayList:<init>	()V
    //   16: invokevirtual 328	io/xlink/wifi/js/bean/UserReqsVo:setFilter	(Ljava/util/List;)V
    //   19: aload_2
    //   20: new 330	io/xlink/wifi/js/bean/UserReqsVo$QueryEntity
    //   23: dup
    //   24: invokespecial 331	io/xlink/wifi/js/bean/UserReqsVo$QueryEntity:<init>	()V
    //   27: invokevirtual 335	io/xlink/wifi/js/bean/UserReqsVo:setQuery	(Lio/xlink/wifi/js/bean/UserReqsVo$QueryEntity;)V
    //   30: aload_2
    //   31: new 337	io/xlink/wifi/js/bean/UserReqsVo$OrderEntity
    //   34: dup
    //   35: invokespecial 338	io/xlink/wifi/js/bean/UserReqsVo$OrderEntity:<init>	()V
    //   38: invokevirtual 342	io/xlink/wifi/js/bean/UserReqsVo:setOrder	(Lio/xlink/wifi/js/bean/UserReqsVo$OrderEntity;)V
    //   41: new 220	org/json/JSONObject
    //   44: dup
    //   45: new 278	com/google/gson/Gson
    //   48: dup
    //   49: invokespecial 279	com/google/gson/Gson:<init>	()V
    //   52: aload_2
    //   53: invokevirtual 283	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   56: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   59: astore 6
    //   61: new 218	org/apache/http/entity/StringEntity
    //   64: dup
    //   65: aload 6
    //   67: invokevirtual 221	org/json/JSONObject:toString	()Ljava/lang/String;
    //   70: ldc 223
    //   72: invokespecial 226	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   75: astore 7
    //   77: aload 7
    //   79: astore 4
    //   81: iconst_2
    //   82: anewarray 155	org/apache/http/Header
    //   85: astore 5
    //   87: aload 5
    //   89: iconst_0
    //   90: new 157	io/xlink/wifi/js/http/XHeader
    //   93: dup
    //   94: ldc 21
    //   96: ldc 15
    //   98: aconst_null
    //   99: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   102: aastore
    //   103: aload 5
    //   105: iconst_1
    //   106: new 157	io/xlink/wifi/js/http/XHeader
    //   109: dup
    //   110: ldc 12
    //   112: getstatic 53	io/xlink/wifi/js/http/HttpAgent:ACCESS_ID	Ljava/lang/String;
    //   115: aconst_null
    //   116: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   119: aastore
    //   120: aload_0
    //   121: ldc 78
    //   123: aload 5
    //   125: aload 4
    //   127: aload_1
    //   128: invokevirtual 232	io/xlink/wifi/js/http/HttpAgent:post	(Ljava/lang/String;[Lorg/apache/http/Header;Lorg/apache/http/HttpEntity;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   131: return
    //   132: astore_3
    //   133: aload_3
    //   134: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   137: aconst_null
    //   138: astore 4
    //   140: goto -59 -> 81
    //   143: astore_3
    //   144: goto -11 -> 133
    //
    // Exception table:
    //   from	to	target	type
    //   0	61	132	java/lang/Exception
    //   61	77	143	java/lang/Exception
  }

  public void onGetUsers$Device(Context paramContext, String paramString, XDevice paramXDevice, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    RequestParams localRequestParams = new RequestParams();
    Header[] arrayOfHeader = new Header[2];
    arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
    arrayOfHeader[1] = new XHeader("Access-Token", UserFerences.getUserFerences(paramContext).spFerences.getString(UserFerences.SHARE_TOKEN, ""), null);
    get("/v2/user/" + paramString + "/subscribe?device=" + paramXDevice.getDeviceId(), arrayOfHeader, localRequestParams, paramTextHttpResponseHandler);
  }

  public void onGetUsersAuth(Context paramContext, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    /*try
    {
      String str1 = UserFerences.getUserFerences(paramContext).spFerences.getString("user", "");
      String str2 = UserFerences.getUserFerences(paramContext).spFerences.getString("userPsd", "");
      PhoneUserAuthReqsVo localPhoneUserAuthReqsVo;
      if (UserFerences.getUserFerences(paramContext).spFerences.getBoolean("isZh", true))
      {
        localPhoneUserAuthReqsVo = new PhoneUserAuthReqsVo();
        localPhoneUserAuthReqsVo.setCorp_id(this.corp_id);
        localPhoneUserAuthReqsVo.setPhone(str1);
        localPhoneUserAuthReqsVo.setPassword(str2);
      }
      JSONObject localJSONObject;
      for (Object localObject = new JSONObject(new Gson().toJson(localPhoneUserAuthReqsVo)); ; localObject = localJSONObject)
      {
        StringEntity localStringEntity2 = new StringEntity(((JSONObject)localObject).toString(), "UTF-8");
        localStringEntity1 = localStringEntity2;
        Header[] arrayOfHeader = new Header[1];
        arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
        post("http://app.xlink.cn/v2/user_auth", arrayOfHeader, localStringEntity1, paramTextHttpResponseHandler);
        return;
        MailUserAuthReqsVo localMailUserAuthReqsVo = new MailUserAuthReqsVo();
        localMailUserAuthReqsVo.setCorp_id(this.corp_id);
        localMailUserAuthReqsVo.setPhone(str1);
        localMailUserAuthReqsVo.setPassword(str2);
        localJSONObject = new JSONObject(new Gson().toJson(localMailUserAuthReqsVo));
      }
    }
    catch (Exception localException)
    {
      while (true)
      {
        localException.printStackTrace();
        StringEntity localStringEntity1 = null;
      }
    }*/
  }

  // ERROR //
  public void onGetUsersCancelShare(Context paramContext, String paramString, XDevice paramXDevice, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    // Byte code:
    //   0: new 349	io/xlink/wifi/js/bean/CancelDeviceReqVo
    //   3: dup
    //   4: invokespecial 350	io/xlink/wifi/js/bean/CancelDeviceReqVo:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: new 176	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   18: aload_3
    //   19: invokevirtual 309	io/xlink/wifi/sdk/XDevice:getDeviceId	()I
    //   22: invokevirtual 312	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   25: ldc 249
    //   27: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   33: invokevirtual 353	io/xlink/wifi/js/bean/CancelDeviceReqVo:setDevice_id	(Ljava/lang/String;)V
    //   36: new 220	org/json/JSONObject
    //   39: dup
    //   40: new 278	com/google/gson/Gson
    //   43: dup
    //   44: invokespecial 279	com/google/gson/Gson:<init>	()V
    //   47: aload 5
    //   49: invokevirtual 283	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   52: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   55: astore 9
    //   57: new 218	org/apache/http/entity/StringEntity
    //   60: dup
    //   61: aload 9
    //   63: invokevirtual 221	org/json/JSONObject:toString	()Ljava/lang/String;
    //   66: ldc 223
    //   68: invokespecial 226	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   71: astore 10
    //   73: aload 10
    //   75: astore 7
    //   77: iconst_2
    //   78: anewarray 155	org/apache/http/Header
    //   81: astore 8
    //   83: aload 8
    //   85: iconst_0
    //   86: new 157	io/xlink/wifi/js/http/XHeader
    //   89: dup
    //   90: ldc 21
    //   92: ldc 15
    //   94: aconst_null
    //   95: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   98: aastore
    //   99: aload 8
    //   101: iconst_1
    //   102: new 157	io/xlink/wifi/js/http/XHeader
    //   105: dup
    //   106: ldc 12
    //   108: aload_1
    //   109: invokestatic 241	com/ex/ltech/led/UserFerences:getUserFerences	(Landroid/content/Context;)Lcom/ex/ltech/led/UserFerences;
    //   112: getfield 245	com/ex/ltech/led/UserFerences:spFerences	Landroid/content/SharedPreferences;
    //   115: getstatic 299	com/ex/ltech/led/UserFerences:SHARE_TOKEN	Ljava/lang/String;
    //   118: ldc 249
    //   120: invokeinterface 255 3 0
    //   125: aconst_null
    //   126: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   129: aastore
    //   130: aload_0
    //   131: new 176	java/lang/StringBuilder
    //   134: dup
    //   135: invokespecial 177	java/lang/StringBuilder:<init>	()V
    //   138: ldc_w 301
    //   141: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: aload_2
    //   145: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   148: ldc_w 355
    //   151: invokevirtual 181	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   154: invokevirtual 182	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: aload 8
    //   159: aload 7
    //   161: aload 4
    //   163: invokevirtual 232	io/xlink/wifi/js/http/HttpAgent:post	(Ljava/lang/String;[Lorg/apache/http/Header;Lorg/apache/http/HttpEntity;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   166: return
    //   167: astore 6
    //   169: aload 6
    //   171: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   174: aconst_null
    //   175: astore 7
    //   177: goto -100 -> 77
    //   180: astore 6
    //   182: goto -13 -> 169
    //
    // Exception table:
    //   from	to	target	type
    //   0	57	167	java/lang/Exception
    //   57	73	180	java/lang/Exception
  }

  // ERROR //
  public void onGetUsersShare(String paramString1, int paramInt, String paramString2, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    // Byte code:
    //   0: new 359	io/xlink/wifi/js/http/vos/ShareDeviceVo
    //   3: dup
    //   4: invokespecial 360	io/xlink/wifi/js/http/vos/ShareDeviceVo:<init>	()V
    //   7: astore 5
    //   9: aload 5
    //   11: iload_2
    //   12: invokevirtual 363	io/xlink/wifi/js/http/vos/ShareDeviceVo:setDevice_id	(I)V
    //   15: aload 5
    //   17: sipush 7200
    //   20: invokevirtual 366	io/xlink/wifi/js/http/vos/ShareDeviceVo:setExpire	(I)V
    //   23: aload 5
    //   25: ldc_w 368
    //   28: invokevirtual 371	io/xlink/wifi/js/http/vos/ShareDeviceVo:setMode	(Ljava/lang/String;)V
    //   31: aload 5
    //   33: aload_1
    //   34: invokevirtual 374	io/xlink/wifi/js/http/vos/ShareDeviceVo:setUser	(Ljava/lang/String;)V
    //   37: new 220	org/json/JSONObject
    //   40: dup
    //   41: new 278	com/google/gson/Gson
    //   44: dup
    //   45: invokespecial 279	com/google/gson/Gson:<init>	()V
    //   48: aload 5
    //   50: invokevirtual 283	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   53: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   56: astore 9
    //   58: new 218	org/apache/http/entity/StringEntity
    //   61: dup
    //   62: aload 9
    //   64: invokevirtual 221	org/json/JSONObject:toString	()Ljava/lang/String;
    //   67: ldc 223
    //   69: invokespecial 226	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   72: astore 10
    //   74: aload 10
    //   76: astore 7
    //   78: iconst_2
    //   79: anewarray 155	org/apache/http/Header
    //   82: astore 8
    //   84: aload 8
    //   86: iconst_0
    //   87: new 157	io/xlink/wifi/js/http/XHeader
    //   90: dup
    //   91: ldc 21
    //   93: ldc 15
    //   95: aconst_null
    //   96: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   99: aastore
    //   100: aload 8
    //   102: iconst_1
    //   103: new 157	io/xlink/wifi/js/http/XHeader
    //   106: dup
    //   107: ldc 12
    //   109: aload_3
    //   110: aconst_null
    //   111: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   114: aastore
    //   115: aload_0
    //   116: ldc 86
    //   118: aload 8
    //   120: aload 7
    //   122: aload 4
    //   124: invokevirtual 232	io/xlink/wifi/js/http/HttpAgent:post	(Ljava/lang/String;[Lorg/apache/http/Header;Lorg/apache/http/HttpEntity;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   127: return
    //   128: astore 6
    //   130: aload 6
    //   132: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   135: aconst_null
    //   136: astore 7
    //   138: goto -60 -> 78
    //   141: astore 6
    //   143: goto -13 -> 130
    //
    // Exception table:
    //   from	to	target	type
    //   0	58	128	java/lang/Exception
    //   58	74	141	java/lang/Exception
  }

  // ERROR //
  public void onPullAppData(Context paramContext, io.xlink.wifi.js.bean.Device paramDevice, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 4
    //   3: new 378	io/xlink/wifi/js/http/HttpAgent$RepVo
    //   6: dup
    //   7: aload_0
    //   8: ldc_w 380
    //   11: ldc_w 382
    //   14: invokespecial 385	io/xlink/wifi/js/http/HttpAgent$RepVo:<init>	(Lio/xlink/wifi/js/http/HttpAgent;Ljava/lang/String;Ljava/lang/String;)V
    //   17: astore 5
    //   19: new 220	org/json/JSONObject
    //   22: dup
    //   23: new 278	com/google/gson/Gson
    //   26: dup
    //   27: invokespecial 279	com/google/gson/Gson:<init>	()V
    //   30: aload 5
    //   32: invokevirtual 283	com/google/gson/Gson:toJson	(Ljava/lang/Object;)Ljava/lang/String;
    //   35: invokespecial 285	org/json/JSONObject:<init>	(Ljava/lang/String;)V
    //   38: astore 6
    //   40: new 218	org/apache/http/entity/StringEntity
    //   43: dup
    //   44: aload 6
    //   46: invokevirtual 221	org/json/JSONObject:toString	()Ljava/lang/String;
    //   49: ldc 223
    //   51: invokespecial 226	org/apache/http/entity/StringEntity:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   54: astore 7
    //   56: aload 7
    //   58: astore 8
    //   60: aload 6
    //   62: astore 4
    //   64: iconst_3
    //   65: anewarray 155	org/apache/http/Header
    //   68: astore 9
    //   70: aload 9
    //   72: iconst_0
    //   73: new 157	io/xlink/wifi/js/http/XHeader
    //   76: dup
    //   77: ldc 9
    //   79: getstatic 53	io/xlink/wifi/js/http/HttpAgent:ACCESS_ID	Ljava/lang/String;
    //   82: aconst_null
    //   83: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   86: aastore
    //   87: aload 4
    //   89: invokevirtual 221	org/json/JSONObject:toString	()Ljava/lang/String;
    //   92: invokestatic 166	io/xlink/wifi/js/http/HttpAgent:MD5	(Ljava/lang/String;)Ljava/lang/String;
    //   95: astore 10
    //   97: aload 9
    //   99: iconst_1
    //   100: new 157	io/xlink/wifi/js/http/XHeader
    //   103: dup
    //   104: ldc 25
    //   106: aload 10
    //   108: aconst_null
    //   109: invokespecial 160	io/xlink/wifi/js/http/XHeader:<init>	(Ljava/lang/String;Ljava/lang/String;[Lorg/apache/http/HeaderElement;)V
    //   112: aastore
    //   113: aload 9
    //   115: iconst_2
    //   116: aload_0
    //   117: aload 10
    //   119: invokespecial 170	io/xlink/wifi/js/http/HttpAgent:getSign	(Ljava/lang/String;)Lio/xlink/wifi/js/http/XHeader;
    //   122: aastore
    //   123: aload_0
    //   124: ldc_w 387
    //   127: aload 9
    //   129: aload 8
    //   131: aload_3
    //   132: invokevirtual 232	io/xlink/wifi/js/http/HttpAgent:post	(Ljava/lang/String;[Lorg/apache/http/Header;Lorg/apache/http/HttpEntity;Lcom/loopj/android/http/AsyncHttpResponseHandler;)V
    //   135: return
    //   136: astore 11
    //   138: aload 11
    //   140: invokevirtual 151	java/lang/Exception:printStackTrace	()V
    //   143: aconst_null
    //   144: astore 8
    //   146: goto -82 -> 64
    //   149: astore 11
    //   151: aload 6
    //   153: astore 4
    //   155: goto -17 -> 138
    //
    // Exception table:
    //   from	to	target	type
    //   19	40	136	java/lang/Exception
    //   40	56	149	java/lang/Exception
  }

  public void onRegister(String paramString1, String paramString2, String paramString3, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString1);
    localHashMap.put("name", paramString2);
    localHashMap.put("pwd", paramString3);
    JSONObject localJSONObject = XlinkUtils.getJsonObject(localHashMap);
    try
    {
      StringEntity localStringEntity1 = new StringEntity(localJSONObject.toString(), "UTF-8");
      Header[] arrayOfHeader = new Header[3];
      arrayOfHeader[0] = new XHeader("X-AccessId", ACCESS_ID, null);
      String str = MD5(localJSONObject.toString());
      arrayOfHeader[1] = new XHeader("X-ContentMD5", str, null);
      arrayOfHeader[2] = getSign(str);
      post("http://app.xlink.cn/v1/user/register", arrayOfHeader, localStringEntity1, paramTextHttpResponseHandler);
      return;
    }
    catch (Exception localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }

  public void onReset(String paramString1, String paramString2, String paramString3, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("uid", paramString1);
    localHashMap.put("name", paramString2);
    localHashMap.put("pwd", paramString3);
    JSONObject localJSONObject = XlinkUtils.getJsonObject(localHashMap);
    System.out.println(paramString1 + localJSONObject.toString());
    try
    {
      StringEntity localStringEntity1 = new StringEntity(localJSONObject.toString(), "UTF-8");
      Header[] arrayOfHeader = new Header[3];
      arrayOfHeader[0] = new XHeader("X-AccessId", ACCESS_ID, null);
      String str = MD5(localJSONObject.toString());
      arrayOfHeader[1] = new XHeader("X-ContentMD5", str, null);
      arrayOfHeader[2] = getSign(str);
      post("http://app.xlink.cn/v1/user/reset", arrayOfHeader, localStringEntity1, paramTextHttpResponseHandler);
      return;
    }
    catch (Exception localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }

  public void post(String paramString, Header[] paramArrayOfHeader, HttpEntity paramHttpEntity, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    client.post(MyApp.getApp(), paramString, paramArrayOfHeader, paramHttpEntity, "text/html", paramAsyncHttpResponseHandler);
  }

  public void upgradeDevice(String paramString1, String paramString2, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("product_id", paramString1);
    localHashMap.put("device_id", Integer.valueOf(1744594799));
    localHashMap.put("type", "1");
    localHashMap.put("identify", "0");
    JSONObject localJSONObject = XlinkUtils.getJsonObject(localHashMap);
    try
    {
      StringEntity localStringEntity1 = new StringEntity(localJSONObject.toString(), "UTF-8");
      Header[] arrayOfHeader = new Header[2];
      arrayOfHeader[0] = new XHeader("Content-Type", "application/json", null);
      arrayOfHeader[1] = new XHeader("Access-Token", SharedPreferencesUtil.queryValue("accessToken"), null);
      post("http://api2.xlink.cn/v2/upgrade/device", arrayOfHeader, localStringEntity1, paramTextHttpResponseHandler);
      return;
    }
    catch (Exception localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }

  class RepVo
  {
    String id;
    String table;

    public RepVo(String paramString1, String arg3)
    {
      this.id = paramString1;
      this.table = arg3;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.HttpAgent
 * JD-Core Version:    0.6.0
 */