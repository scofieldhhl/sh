package com.ex.ltech.LogRegForget.ShareSdk;

public class UserInfo
{
  private Gender userGender;
  private String userIcon;
  private String userName;
  private String userNote;

  public Gender getUserGender()
  {
    return this.userGender;
  }

  public String getUserIcon()
  {
    return this.userIcon;
  }

  public String getUserName()
  {
    return this.userName;
  }

  public String getUserNote()
  {
    return this.userNote;
  }

  public void setUserGender(Gender paramGender)
  {
    this.userGender = paramGender;
  }

  public void setUserIcon(String paramString)
  {
    this.userIcon = paramString;
  }

  public void setUserName(String paramString)
  {
    this.userName = paramString;
  }

  public void setUserNote(String paramString)
  {
    this.userNote = paramString;
  }

  public static enum Gender
  {
    static
    {
      FEMALE = new Gender("FEMALE", 1);
      Gender[] arrayOfGender = new Gender[2];
      arrayOfGender[0] = MALE;
      arrayOfGender[1] = FEMALE;
      $VALUES = arrayOfGender;
    }
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.LogRegForget.ShareSdk.UserInfo
 * JD-Core Version:    0.6.0
 */