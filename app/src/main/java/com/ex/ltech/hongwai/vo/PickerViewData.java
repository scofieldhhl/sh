package com.ex.ltech.hongwai.vo;

import com.ex.ltech.hongwai.view.pickerview.model.IPickerViewData;

public class PickerViewData
  implements IPickerViewData
{
  private String content;

  public PickerViewData(String paramString)
  {
    this.content = paramString;
  }

  public String getPickerViewText()
  {
    return this.content;
  }

  public void setContent(String paramString)
  {
    this.content = paramString;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.hongwai.vo.PickerViewData
 * JD-Core Version:    0.6.0
 */