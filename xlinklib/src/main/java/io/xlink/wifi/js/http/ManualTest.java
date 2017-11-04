package io.xlink.wifi.js.http;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;
import io.xlink.wifi.js.util.XlinkUtils;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.json.JSONObject;

@Deprecated
public class ManualTest
{
  private static final String AccessID = "X-AccessId";
  private static final String X_ContentMD5 = "X-ContentMD5";
  private static final String X_Sign = "X-Sign";
  private static ManualTest instance;
  public static String url = "http://app.xlink.cn";
  private String ACCESS_ID = "7cf62da652e941a49665d959fafeb38f";
  private String SECRET_KEY = "a3adfe7bfce044edbfdb0e0f7c84d061";
  public final String URL_CODE_LIST = url + "/v1/recipe/tag/code_list";
  public final String URL_GROUP_LIST = url + "/v1/recipe/tag/group_list";
  public final String URL_MANUAL_GET = url + "/v1/recipe/manual/get";

  public static ManualTest getInstance()
  {
    if (instance == null)
      instance = new ManualTest();
    return instance;
  }

  private Header getSign(String paramString)
  {
    return new BasicHeader("X-Sign", HttpAgent.MD5(this.SECRET_KEY + paramString).toLowerCase());
  }

  private void post(String paramString, Header[] paramArrayOfHeader, HttpEntity paramHttpEntity, AsyncHttpResponseHandler paramAsyncHttpResponseHandler)
  {
    HttpAgent.getInstance().post(paramString, paramArrayOfHeader, paramHttpEntity, paramAsyncHttpResponseHandler);
  }

  public void getCode_list(String paramString, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("corp", paramString);
    localHashMap.put("act", "code_list");
    String str1 = XlinkUtils.getJsonObject(localHashMap).toString();
    try
    {
      StringEntity localStringEntity1 = new StringEntity(str1, "UTF-8");
      localStringEntity2 = localStringEntity1;
      Header[] arrayOfHeader = new Header[3];
      arrayOfHeader[0] = new XHeader("X-AccessId", this.ACCESS_ID, null);
      String str2 = HttpAgent.MD5(str1);
      arrayOfHeader[1] = new XHeader("X-ContentMD5", str2, null);
      arrayOfHeader[2] = getSign(str2);
      post(this.URL_CODE_LIST, arrayOfHeader, localStringEntity2, paramTextHttpResponseHandler);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }

  public void getFood_list(String paramString, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    try
    {
      StringEntity localStringEntity1 = new StringEntity(paramString, "UTF-8");
      localStringEntity2 = localStringEntity1;
      Header[] arrayOfHeader = new Header[3];
      arrayOfHeader[0] = new XHeader("X-AccessId", this.ACCESS_ID, null);
      String str = HttpAgent.MD5(paramString);
      arrayOfHeader[1] = new XHeader("X-ContentMD5", str, null);
      arrayOfHeader[2] = getSign(str);
      post(this.URL_MANUAL_GET, arrayOfHeader, localStringEntity2, paramTextHttpResponseHandler);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }

  public void getGroup_list(String paramString, TextHttpResponseHandler paramTextHttpResponseHandler)
  {
    try
    {
      StringEntity localStringEntity1 = new StringEntity(paramString, "UTF-8");
      localStringEntity2 = localStringEntity1;
      Header[] arrayOfHeader = new Header[3];
      arrayOfHeader[0] = new XHeader("X-AccessId", this.ACCESS_ID, null);
      String str = HttpAgent.MD5(paramString);
      arrayOfHeader[1] = new XHeader("X-ContentMD5", str, null);
      arrayOfHeader[2] = getSign(str);
      post(this.URL_GROUP_LIST, arrayOfHeader, localStringEntity2, paramTextHttpResponseHandler);
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      while (true)
      {
        localUnsupportedEncodingException.printStackTrace();
        StringEntity localStringEntity2 = null;
      }
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.ManualTest
 * JD-Core Version:    0.6.0
 */