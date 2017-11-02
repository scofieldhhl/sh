package et.song.etclass;

import android.content.Context;
import android.database.Cursor;
import et.song.db.ETDB;
import et.song.face.IOp;
import java.util.ArrayList;
import java.util.List;

public class ETPage
  implements IOp
{
  private static ETPage instance;
  private static List<ETGroup> mGroupList = null;

  static
  {
    instance = null;
  }

  private ETPage()
  {
    mGroupList = new ArrayList();
  }

  public static final ETPage getInstance(Context paramContext)
  {
    if (instance == null)
      instance = new ETPage();
    return instance;
  }

  public void Delete(ETDB paramETDB)
  {
  }

  public int GetCount()
  {
    return mGroupList.size();
  }

  public Object GetItem(int paramInt)
  {
    return mGroupList.get(paramInt);
  }

  public void Inster(ETDB paramETDB)
  {
  }

  public void Load(ETDB paramETDB)
  {
    mGroupList.clear();
    while (true)
    {
      Cursor localCursor2;
      try
      {
        Cursor localCursor1 = paramETDB.queryData2Cursor("select count(*) from ETGroup", null);
        localCursor1.moveToFirst();
        long l = localCursor1.getLong(0);
        localCursor1.close();
        if (l != 0L)
          continue;
        ETGroup localETGroup2 = new ETGroup();
        localETGroup2.SetID(0);
        localETGroup2.SetName("");
        localETGroup2.SetType(16777217);
        localETGroup2.SetRes(2130903040);
        mGroupList.add(localETGroup2);
        return;
        localCursor2 = paramETDB.queryData2Cursor("select * from ETGroup", null);
        localCursor2.moveToFirst();
        if (!localCursor2.isAfterLast())
        {
          ETGroup localETGroup1 = new ETGroup();
          int i = localCursor2.getInt(localCursor2.getColumnIndex("id"));
          String str = localCursor2.getString(localCursor2.getColumnIndex("group_name"));
          int j = localCursor2.getInt(localCursor2.getColumnIndex("group_type"));
          int k = localCursor2.getInt(localCursor2.getColumnIndex("group_res"));
          localETGroup1.SetID(i);
          localETGroup1.SetName(str);
          localETGroup1.SetType(j);
          localETGroup1.SetRes(k);
          localETGroup1.Load(paramETDB);
          mGroupList.add(localETGroup1);
          localCursor2.moveToNext();
          continue;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        return;
      }
      localCursor2.close();
    }
  }

  public void Update(ETDB paramETDB)
  {
  }
}

/* Location:           E:\android逆向助手2——2\com.ex.ltech.led_1.9.7_197_dex2jar.jar
 * Qualified Name:     et.song.etclass.ETPage
 * JD-Core Version:    0.6.0
 */