package et.song;

public class AdapterPYinItem
{
  public String ename = "";
  boolean isShow;
  private String mName = "";
  private int mPos = 0;
  private String mPyin = "";
  private String mPyinUpper = "";

  public AdapterPYinItem()
  {
  }

  public AdapterPYinItem(String paramString1, String paramString2, int paramInt)
  {
    this.mName = paramString1;
    this.mPyin = paramString2;
    this.mPos = paramInt;
  }

  public AdapterPYinItem(String paramString1, String paramString2, int paramInt, String paramString3)
  {
    this.mName = paramString1;
    this.ename = paramString2;
    this.mPos = paramInt;
    this.mPyinUpper = paramString3;
    this.mPyin = paramString3;
  }

  public AdapterPYinItem(String paramString1, String paramString2, int paramInt, boolean paramBoolean, String paramString3)
  {
    this.mName = paramString1;
    this.mPyin = paramString2;
    this.mPos = paramInt;
    this.isShow = paramBoolean;
    this.mPyinUpper = paramString3;
  }

  public String getName()
  {
    return this.mName;
  }

  public int getPos()
  {
    return this.mPos;
  }

  public String getPyin()
  {
    return this.mPyin;
  }

  public String getmPyinUpper()
  {
    return this.mPyinUpper;
  }

  public boolean isShow()
  {
    return this.isShow;
  }

  public void setIsShow(boolean paramBoolean)
  {
    this.isShow = paramBoolean;
  }

  public void setName(String paramString)
  {
    this.mName = paramString;
  }

  public void setPos(int paramInt)
  {
    this.mPos = paramInt;
  }

  public void setPyin(String paramString)
  {
    this.mPyin = paramString;
  }

  public void setmPyinUpper(String paramString)
  {
    this.mPyinUpper = paramString;
  }

  public String toString()
  {
    return "AdapterPYinItem{mName='" + this.mName + '\'' + ", mPyinUpper='" + this.mPyinUpper + '\'' + ", isShow=" + this.isShow + '}';
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.AdapterPYinItem
 * JD-Core Version:    0.6.0
 */