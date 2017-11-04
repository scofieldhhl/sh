package io.xlink.wifi.sdk.bean;

import java.io.Serializable;
import java.util.Arrays;

public class EventNotify
  implements Serializable
{
  public int formId;
  public int messageId;
  public int messageType;
  public byte[] notifyData;
  public byte notyfyFlags;

  public String toString()
  {
    return "EventNotify{notyfyFlags=" + this.notyfyFlags + ", formId=" + this.formId + ", messageId=" + this.messageId + ", messageType=" + this.messageType + ", notifyData=" + Arrays.toString(this.notifyData) + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.sdk.bean.EventNotify
 * JD-Core Version:    0.6.0
 */