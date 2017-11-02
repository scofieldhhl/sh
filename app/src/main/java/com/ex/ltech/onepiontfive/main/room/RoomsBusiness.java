package com.ex.ltech.onepiontfive.main.room;

import android.content.Context;
import com.ex.ltech.led.UserFerences;
import com.ex.ltech.onepiontfive.main.MyBusiness;
import com.ex.ltech.onepiontfive.main.vo.Home;
import com.ex.ltech.onepiontfive.main.vo.Room;
import java.util.ArrayList;

public class RoomsBusiness extends MyBusiness
{
  public Context c;
  Home home;

  public RoomsBusiness(Context paramContext)
  {
    super(paramContext);
    this.c = paramContext;
  }

  public Home getDefaultHome()
  {
    this.home = new Home();
    Room localRoom1 = new Room();
    localRoom1.setName(this.ct.getString(2131100349));
    this.home.getRooms().add(localRoom1);
    Room localRoom2 = new Room();
    localRoom2.setName(this.ct.getString(2131100350));
    this.home.getRooms().add(localRoom2);
    Room localRoom3 = new Room();
    localRoom3.setName(this.ct.getString(2131100351));
    this.home.getRooms().add(localRoom3);
    Room localRoom4 = new Room();
    localRoom4.setName(this.ct.getString(2131100352));
    this.home.getRooms().add(localRoom4);
    Room localRoom5 = new Room();
    localRoom5.setName(this.ct.getString(2131100353));
    this.home.getRooms().add(localRoom5);
    Room localRoom6 = new Room();
    localRoom6.setName(this.ct.getString(2131100354));
    this.home.getRooms().add(localRoom6);
    Room localRoom7 = new Room();
    localRoom7.setName(this.ct.getString(2131100355));
    this.home.getRooms().add(localRoom7);
    Room localRoom8 = new Room();
    localRoom8.setName(this.ct.getString(2131100356));
    this.home.getRooms().add(localRoom8);
    return this.home;
  }

  public Home getHome()
  {
    String str = UserFerences.getUserFerences(this.c).getValue("GateWayMacIdKey");
    this.home = ((Home)getBean4ClassName(str, Home.class));
    if (this.home == null)
    {
      this.home = getDefaultHome();
      putData4ClassName(str, this.home);
    }
    return this.home;
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     com.ex.ltech.onepiontfive.main.room.RoomsBusiness
 * JD-Core Version:    0.6.0
 */