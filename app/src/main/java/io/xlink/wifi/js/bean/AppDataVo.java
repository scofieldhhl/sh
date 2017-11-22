package io.xlink.wifi.js.bean;

public class AppDataVo
{
  private FieldEntity field;
  private String name;
  private String[] permission = { "create", "get", "find", "update", "delete" };
  private String type = "string";

  public FieldEntity getField()
  {
    return this.field;
  }

  public String getName()
  {
    return this.name;
  }

  public String getType()
  {
    return this.type;
  }

  public void setField(FieldEntity paramFieldEntity)
  {
    this.field = paramFieldEntity;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setType(String paramString)
  {
    this.type = paramString;
  }

  public static class FieldEntity
  {
    private String appdata;

    public String getAppdata()
    {
      return this.appdata;
    }

    public void setAppdata(String paramString)
    {
      this.appdata = paramString;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     io.xlink.wifi.js.bean.AppDataVo
 * JD-Core Version:    0.6.0
 */