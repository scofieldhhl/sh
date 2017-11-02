package com.ex.ltech.hongwai.vo;

import com.ex.ltech.hongwai.view.pickerview.model.IPickerViewData;

public class ProvinceBean
  implements IPickerViewData
{
  private String description;
  private long id;
  private String name;
  private String others;

  public ProvinceBean(long paramLong, String paramString1, String paramString2, String paramString3)
  {
    this.id = paramLong;
    this.name = paramString1;
    this.description = paramString2;
    this.others = paramString3;
  }

  public String getDescription()
  {
    return this.description;
  }

  public long getId()
  {
    return this.id;
  }

  public String getName()
  {
    return this.name;
  }

  public String getOthers()
  {
    return this.others;
  }

  public String getPickerViewText()
  {
    return this.name;
  }

  public void setDescription(String paramString)
  {
    this.description = paramString;
  }

  public void setId(long paramLong)
  {
    this.id = paramLong;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setOthers(String paramString)
  {
    this.others = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.ProvinceBean
 * JD-Core Version:    0.6.0
 */