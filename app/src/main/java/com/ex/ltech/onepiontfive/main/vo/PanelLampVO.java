package com.ex.ltech.onepiontfive.main.vo;

public class PanelLampVO
{
  private int chosenIndex;
  private String imgPath;
  private boolean isRealation;
  private boolean isSeleted;
  private String name;
  private boolean on;
  private boolean showRelationBtn;
  private int type;

  public int getChosenIndex()
  {
    return this.chosenIndex;
  }

  public String getImgPath()
  {
    return this.imgPath;
  }

  public String getName()
  {
    return this.name;
  }

  public int getType()
  {
    return this.type;
  }

  public boolean isOn()
  {
    return this.on;
  }

  public boolean isRealation()
  {
    return this.isRealation;
  }

  public boolean isSeleted()
  {
    return this.isSeleted;
  }

  public boolean isShowRelationBtn()
  {
    return this.showRelationBtn;
  }

  public void setChosenIndex(int paramInt)
  {
    this.chosenIndex = paramInt;
  }

  public void setImgPath(String paramString)
  {
    this.imgPath = paramString;
  }

  public void setIsSeleted(boolean paramBoolean)
  {
    this.isSeleted = paramBoolean;
  }

  public void setName(String paramString)
  {
    this.name = paramString;
  }

  public void setOn(boolean paramBoolean)
  {
    this.on = paramBoolean;
  }

  public void setRealation(boolean paramBoolean)
  {
    this.isRealation = paramBoolean;
  }

  public void setShowRelationBtn(boolean paramBoolean)
  {
    this.showRelationBtn = paramBoolean;
  }

  public void setType(int paramInt)
  {
    this.type = paramInt;
  }

  public String toString()
  {
    return "PanelLampVO{type=" + this.type + ", name='" + this.name + '\'' + ", on=" + this.on + ", isSeleted=" + this.isSeleted + ", showRelationBtn=" + this.showRelationBtn + ", isRealation=" + this.isRealation + ", chosenIndex=" + this.chosenIndex + ", imgPath='" + this.imgPath + '\'' + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.vo.PanelLampVO
 * JD-Core Version:    0.6.0
 */