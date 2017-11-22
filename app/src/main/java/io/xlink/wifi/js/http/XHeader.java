package io.xlink.wifi.js.http;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.ParseException;

public class XHeader
  implements Header
{
  private String name;
  private String value;

  public XHeader(String paramString1, String paramString2)
  {
    this.value = paramString2;
    this.name = paramString1;
  }

  public XHeader(String paramString1, String paramString2, HeaderElement[] paramArrayOfHeaderElement)
  {
    this.value = paramString2;
    this.name = paramString1;
  }

  public HeaderElement[] getElements()
    throws ParseException
  {
    return null;
  }

  public String getName()
  {
    return this.name;
  }

  public String getValue()
  {
    return this.value;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.http.XHeader
 * JD-Core Version:    0.6.0
 */